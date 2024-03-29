package com.ddn.ddnsell.dto;


import com.ddn.ddnsell.entity.OrderDetail;
import com.ddn.ddnsell.enums.OrderStatusEnum;
import com.ddn.ddnsell.enums.PayStatusEnum;
import com.ddn.ddnsell.utils.EnumsUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author qincx
 * @date 2019/3/25
 * @description
 */
@Data
public class OrderDTO {
    /** 订单id. */
    private String orderId;

    /** 买家名字. */
    private String buyerName;

    /** 买家手机号. */
    private String buyerPhone;

    /** 买家地址. */
    private String buyerAddress;

    /** 买家微信Openid. */
    private String buyerOpenid;

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单. */
    private Integer orderStatus ;

    /** 支付状态, 默认为0未支付. */
    private Integer payStatus;

    /** 创建时间. */
    private Date createTime;

    /** 更新时间. */
    private Date updateTime;

    /**
     * 订单详情
     */
    private List<OrderDetail> orderDetailList;

    @JsonIgnore
    public String getOrderStatusMessage(){
        return  EnumsUtil.getByCode(this.orderStatus, OrderStatusEnum.class).getMessage();
    }

    @JsonIgnore
    public String getPayStatusMessage(){
        return  EnumsUtil.getByCode(this.payStatus, PayStatusEnum.class).getMessage();
    }
}
