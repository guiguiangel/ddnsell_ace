package com.ddn.ddnsell.service.Impl;

import com.ddn.ddnsell.dao.OrderDetailDao;
import com.ddn.ddnsell.dao.OrderMasterDao;
import com.ddn.ddnsell.dao.ProductInfoDao;
import com.ddn.ddnsell.dto.OrderDTO;
import com.ddn.ddnsell.entity.OrderDetail;
import com.ddn.ddnsell.entity.OrderMaster;
import com.ddn.ddnsell.entity.ProductInfo;
import com.ddn.ddnsell.enums.OrderStatusEnum;
import com.ddn.ddnsell.enums.PayStatusEnum;
import com.ddn.ddnsell.enums.ResultEnum;
import com.ddn.ddnsell.exception.SellException;
import com.ddn.ddnsell.service.OrderMasterService;
import com.ddn.ddnsell.service.ProductService;
import com.ddn.ddnsell.service.WebSocket;
import com.ddn.ddnsell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author qincx
 * @date 2019/3/26
 * @description
 */
@Service
@Slf4j
public class OrderMasterServiceImpl implements OrderMasterService {
    @Autowired
    private ProductInfoDao productInfoDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Autowired
    private ProductService productService;

    @Autowired
    private WebSocket webSocket;

    @Override
    public OrderDTO findOne(String orderId,String openid) {
       // orderId="333";
        OrderMaster orderMaster = orderMasterDao.findOne(orderId);
        if (orderMaster == null){
            log.error("【订单查询】message:查询订单失败,orderid:{}",orderId);
            throw new SellException(ResultEnum.ORDER_ORDERID_ERROR);
        }
        if (orderMaster.getBuyerOpenid().equals(openid) == false){
            log.error("【订单查询】message:查询订单失败,openid:{}",openid);
            throw new SellException(ResultEnum.ORDER_OPENID_ERROR);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);

        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)){
            log.error("【订单查询】message:查询订单失败,orderid:{}",orderId);
            throw new SellException(ResultEnum.ORDER_ORDERID_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findAll(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterDao.findAll(pageable);
        if (orderMasterPage == null){
            return null;
        }
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (OrderMaster orderMaster:orderMasterPage.getContent()
        ) {
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderMaster, orderDTO);
            orderDTOList.add(orderDTO);
        }
        PageImpl<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList, pageable, orderDTOList.size());
        return orderDTOPage;
    }

    @Override
    public List<OrderDTO> findListByOpenId(String openId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterDao.findByBuyerOpenid(openId, pageable);
        if (orderMasterPage == null){
            return null;
        }
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (OrderMaster orderMaster:orderMasterPage.getContent()
             ) {
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderMaster, orderDTO);
            orderDTOList.add(orderDTO);
        }

        return orderDTOList;
    }

    /**
     * 创建订单
     * @param orderDTO
     * @return
     */
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setCreateTime(new Date());
        orderDTO.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderDTO.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderDTO.setOrderId(KeyUtil.genUniqueKey());
        BeanUtils.copyProperties(orderDTO,orderMaster);

        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        BigDecimal totalMoney = new BigDecimal(0);
        for (OrderDetail orderDetail: orderDetailList
             ) {
            ProductInfo productInfo = productInfoDao.findOne(orderDetail.getProductId());
            if (productInfo == null){
                log.error("【创建订单】:获取商品信息失败");
                throw new SellException(ResultEnum.PRODUCT_NO_EXIST);
            }
            BeanUtils.copyProperties(productInfo,orderDetail);

//            orderDetail.setProductName(productInfo.getProductName());
//            orderDetail.setProductIcon(productInfo.getProductIcon());
//            orderDetail.setProductPrice(productInfo.getProductPrice());
            orderDetail.setOrderId(orderDTO.getOrderId());
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetailDao.save(orderDetail);

//            统计金额
            totalMoney = totalMoney.add(productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())));
//            扣库存
            productService.delStock(orderDetail.getProductId(),orderDetail.getProductQuantity());
        }
        orderMaster.setOrderAmount(totalMoney);
        orderMasterDao.save(orderMaster);

        //发送给后端页面通知有新的订单
        webSocket.sendMessage(orderDTO.getOrderId());

        return orderDTO;
    }

    @Override
    @Transactional
    public void cancel(String orderId, String openid) {
        OrderMaster orderMaster = orderMasterDao.findOne(orderId);
        if (orderMaster == null){
            log.error("【取消订单】message:查询订单失败,orderid:{}",orderId);
            throw new SellException(ResultEnum.ORDER_ORDERID_ERROR);
        }
        if (orderMaster.getBuyerOpenid().equals(openid) == false){
            log.error("【取消订单】message:查询订单失败,openid:{}",openid);
            throw new SellException(ResultEnum.ORDER_OPENID_ERROR);
        }

        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        orderMasterDao.save(orderMaster);

        //TODO 恢复商品库存
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)){
            log.error("【取消订单】message:查询订单详情失败,orderid:{}",orderId);
            throw new SellException(ResultEnum.ORDER_ORDERID_ERROR);
        }

        for (OrderDetail orderDetail: orderDetailList
             ) {
            productService.addStock(orderDetail.getProductId(), orderDetail.getProductQuantity());
        }

    }

    @Override
    @Transactional
    public void finish(String orderId, String openid) {
        OrderMaster orderMaster = orderMasterDao.findOne(orderId);
        if (orderMaster == null){
            log.error("【完成订单】message:查询订单失败,orderid:{}",orderId);
            throw new SellException(ResultEnum.ORDER_ORDERID_ERROR);
        }
        if (orderMaster.getBuyerOpenid().equals(openid) == false){
            log.error("【完成订单】message:查询订单失败,openid:{}",openid);
            throw new SellException(ResultEnum.ORDER_OPENID_ERROR);
        }

        if (orderMaster.getPayStatus() == PayStatusEnum.WAIT.getCode()){
            log.error("【完成订单】message:订单未支付不能完结,openid:{}",openid);
            throw new SellException(ResultEnum.ORDER_NOPAY_ERROR);
        }
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterDao.save(orderMaster);
    }
}
