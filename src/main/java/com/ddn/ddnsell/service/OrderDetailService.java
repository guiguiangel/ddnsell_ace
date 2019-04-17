package com.ddn.ddnsell.service;

import com.ddn.ddnsell.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    public List<OrderDetail> findListByOrderId(String orderId);
}
