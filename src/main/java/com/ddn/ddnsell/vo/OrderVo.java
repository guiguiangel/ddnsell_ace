package com.ddn.ddnsell.vo;

import lombok.Data;

/**
 * @author qincx
 * @date 2019/3/25
 * @description 用于接收前端订单的参数
 */

//name: "张三"
//        phone: "18868822111"
//        address: "慕课网总部"
//        openid: "ew3euwhd7sjw9diwkq" //用户的微信openid
//        items: [{
//        productId: "1423113435324",
//        productQuantity: 2 //购买数量
//        }]

@Data
public class OrderVo {
    /**
     * 买家姓名
     */
    private String name;
    /**
     * 买家手机号码
     */
    private String phone;
    /**
     * 买家地址
     */
    private String address;
    /**
     * 买家微信openid
     */
    private String openid;
    /**
     * 购物车信息
     */
    private String items;
}
