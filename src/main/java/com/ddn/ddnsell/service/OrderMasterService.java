package com.ddn.ddnsell.service;

import com.ddn.ddnsell.dto.OrderDTO;
import com.ddn.ddnsell.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface OrderMasterService {
    public OrderDTO findOne(String orderId,String openid);
    public Page<OrderDTO> findAll(Pageable pageable);
    public List<OrderDTO> findListByOpenId(String openId, Pageable pageable);
    public OrderDTO create(OrderDTO orderDTO);
    public void finish(String orderId, String openid);
    public void cancel(String orderId,String openid);
}
