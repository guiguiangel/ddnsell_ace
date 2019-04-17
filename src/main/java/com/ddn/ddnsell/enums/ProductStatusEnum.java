package com.ddn.ddnsell.enums;

import lombok.Getter;

/**
 * @author qincx
 * @date 2019/3/25
 * @description
 */

@Getter
public enum  ProductStatusEnum implements CodeEnum{
    UP(0, "上架"),
    DOWN(1, "下架")
    ;
    private Integer code;
    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
