package com.ddn.ddnsell.service.Impl;

import com.ddn.ddnsell.dto.OrderDTO;
import com.ddn.ddnsell.entity.OrderDetail;
import com.ddn.ddnsell.service.OrderMasterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterServiceImplTest {

    @Autowired
    private OrderMasterService orderMasterService;

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderMasterService.findOne(null, null);
        System.out.println(orderDTO);
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findListByOpenId() {
        PageRequest pageRequest = new PageRequest(0, 10);
        List<OrderDTO> listByOpenId = orderMasterService.findListByOpenId("123456789", pageRequest);
        System.out.println(listByOpenId);
    }

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("小红");
        orderDTO.setBuyerOpenid("123456789");
        orderDTO.setBuyerPhone("18588269536");
        orderDTO.setBuyerAddress("北京朝阳区");
        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderDTO.setOrderDetailList(orderDetailList);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("1553504220929551801");
        orderDetail.setProductQuantity(2);
        orderDTO.getOrderDetailList().add(orderDetail);

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("1553505373082895429");
        orderDetail1.setProductQuantity(4);
        orderDTO.getOrderDetailList().add(orderDetail1);


        orderMasterService.create(orderDTO);

    }

    @Test
    public void finish() {
    }
}