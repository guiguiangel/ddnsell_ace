package com.ddn.ddnsell.converter;

import com.ddn.ddnsell.dto.OrderDTO;
import com.ddn.ddnsell.entity.OrderDetail;
import com.ddn.ddnsell.enums.ResultEnum;
import com.ddn.ddnsell.exception.SellException;
import com.ddn.ddnsell.vo.OrderVo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qincx
 * @date 2019/3/25
 * @description
 */
@Slf4j
public class OrderVoConverter2OrderDTO {
    public static OrderDTO converter2OrderDTO(OrderVo orderVo){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderVo.getName());
        orderDTO.setBuyerAddress(orderVo.getAddress());
        orderDTO.setBuyerOpenid(orderVo.getOpenid());
        orderDTO.setBuyerPhone(orderVo.getPhone());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        Gson gson = new Gson();
        try{
            orderDetailList = gson.fromJson(orderVo.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        }
        catch (Exception e){
            log.error("【订单数据转化错误】,message:{}",e.getMessage());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;

    }
}
