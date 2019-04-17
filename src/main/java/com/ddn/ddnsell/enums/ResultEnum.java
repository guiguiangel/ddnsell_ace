package com.ddn.ddnsell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    SUCCESS(0,"成功"),
    SELL_ONSALE_SUCCESS(1001, "商品上架成功"),
    SELL_ONSALE_ERROR(1002, "商品上架失败"),
    SELL_OFFSALE_SUCCESS(1003, "商品下架成功"),
    SELL_OFFSALE_ERROR(1004, "商品下架失败"),

    SELL_CATEGORY_PARAMERROR(1010, "商品分类参数错误"),
    SELL_SAVECATEGORY_SUCCESS(1011, "保存商品分类成功"),
    SELL_SAVECATEGORY_ERROR(1012, "保存商品分类失败"),
    SELL_UPDATECATEGORY_SUCCESS(1013, "修改商品分类成功"),
    SELL_UPDATECATEGORY_ERROR(1014, "修改商品分类失败"),

    PARAM_ERROR(1,"参数错误"),
    PRODUCT_NO_EXIST(10,"商品不存在"),
    PRODUCT_NO_ENOUGH(11,"商品不足，扣库存失败"),
    ORDER_CREATE_PARAM_ERROR(12,"创建订单参数错误"),
    ORDER_OPENID_ERROR(13,"订单openid参数错误"),
    ORDER_ORDERID_ERROR(14,"订单orderid参数错误"),
    ORDER_NOPAY_ERROR(15,"订单未支付，不能完结"),

    WX_GETOPENID_ERROR(800,"微信授权获取openid错误"),
    WX_RETURNURL_ERROR(801,"微信授权returnurl参数错误")


    //权限
    ,AUTH_CREATE_PARAM_ERROR(500001, "添加权限的参数错误")
    ,AUTH_UPDATE_PARAM_ERROR(500002, "修改权限的参数错误")
    ,AUTH_DELETE_PARAM_ERROR(500003, "删除权限的参数错误")
    ,AUTH_DELETE_ERROR(500004, "删除失败")
    ,ROLE_CREATE_PARAM_ERROR(500005, "添加角色的参数错误")
    ,ROLE_UPDATE_PARAM_ERROR(500006, "修改角色的参数错误")
    ,ROLE_UPDATE_ERROR(500007, "修改角色失败")
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
