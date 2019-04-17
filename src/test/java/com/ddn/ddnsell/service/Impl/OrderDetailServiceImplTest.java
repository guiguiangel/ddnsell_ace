package com.ddn.ddnsell.service.Impl;

import com.ddn.ddnsell.entity.OrderDetail;
import com.ddn.ddnsell.service.OrderDetailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailServiceImplTest {

    @Autowired
    private OrderDetailService orderDetailService;
    @Test
    public void findListByOrderId() {
        List<OrderDetail> listByOrderId = orderDetailService.findListByOrderId("1");
        System.out.println(listByOrderId);
    }
}