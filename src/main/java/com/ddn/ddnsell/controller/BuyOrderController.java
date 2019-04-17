package com.ddn.ddnsell.controller;

import com.ddn.ddnsell.converter.OrderVoConverter2OrderDTO;
import com.ddn.ddnsell.dto.OrderDTO;
import com.ddn.ddnsell.enums.ResultEnum;
import com.ddn.ddnsell.service.OrderMasterService;
import com.ddn.ddnsell.utils.ResultVoUtil;
import com.ddn.ddnsell.vo.OrderVo;
import com.ddn.ddnsell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qincx
 * @date 2019/3/27
 * @description
 */

@RestController
@RequestMapping("/buyer")
@Slf4j
public class BuyOrderController {

    @Autowired
    private OrderMasterService orderMasterService;

    @PostMapping("/order/create")
    public ResultVO create(OrderVo orderVo){
        if (orderVo == null){
            log.error("【创建订单】message:参数错误{}", orderVo);
            return ResultVoUtil.error(ResultEnum.ORDER_CREATE_PARAM_ERROR,null);
        }
        OrderDTO orderDTO = OrderVoConverter2OrderDTO.converter2OrderDTO(orderVo);
        OrderDTO orderDTO1 = orderMasterService.create(orderDTO);
        if (orderDTO1 != null){
            return ResultVoUtil.success(orderDTO1.getOrderId());
        }
        return ResultVoUtil.error(ResultEnum.ORDER_CREATE_PARAM_ERROR,null);
    }

    @GetMapping("/order/list")
    public ResultVO list( @RequestParam("openid")String openid, @RequestParam(value = "page",defaultValue = "1") Integer page,
                         @RequestParam(value = "size",defaultValue = "10") Integer size){
        if (StringUtils.isBlank(openid)){
            log.error("【查询订单】message: openid 参数错误{}", openid);
            return ResultVoUtil.error(ResultEnum.ORDER_OPENID_ERROR,null);
        }
        PageRequest pageRequest = new PageRequest(page - 1, size);
        List<OrderDTO> orderDTOList = orderMasterService.findListByOpenId(openid, pageRequest);
        if (orderDTOList == null){
            ResultVoUtil.success(null);
        }
        return  ResultVoUtil.success(orderDTOList);
    }

    @GetMapping("/order/detail")
    public ResultVO detail(@RequestParam("orderId")String orderId, @RequestParam("openid")String openid){
        if (StringUtils.isBlank(openid)){
            log.error("【查询订单详情】message: openid 参数错误{}", openid);
            return ResultVoUtil.error(ResultEnum.ORDER_OPENID_ERROR,null);
        }
        if (StringUtils.isBlank(orderId)){
            log.error("【查询订单详情】message: orderId 参数错误{}", orderId);
            return ResultVoUtil.error(ResultEnum.ORDER_ORDERID_ERROR,null);
        }
        OrderDTO orderDTO = orderMasterService.findOne(orderId, openid);
        return ResultVoUtil.success(orderDTO);
    }

    @GetMapping("/order/cancel")
    public ResultVO cancel(@RequestParam("orderId")String orderId, @RequestParam("openid")String openid){
        if (StringUtils.isBlank(openid)){
            log.error("【取消订单】message: openid 参数错误{}", openid);
            return ResultVoUtil.error(ResultEnum.ORDER_OPENID_ERROR,null);
        }
        if (StringUtils.isBlank(orderId)){
            log.error("【取消订单】message: orderId 参数错误{}", orderId);
            return ResultVoUtil.error(ResultEnum.ORDER_ORDERID_ERROR,null);
        }
        orderMasterService.cancel(orderId,openid);
        return ResultVoUtil.success();
    }

}
