package com.ddn.ddnsell.service.Impl;

import com.ddn.ddnsell.dao.OrderDetailDao;
import com.ddn.ddnsell.entity.OrderDetail;
import com.ddn.ddnsell.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qincx
 * @date 2019/3/27
 * @description
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailDao orderDetailDao;


    public List<OrderDetail> findListByOrderId(String orderId){
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        return orderDetailList;
    }
}
