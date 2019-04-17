package com.ddn.ddnsell.enums;

import lombok.Getter;

@Getter
public enum CategoryDeleteEnum implements CodeEnum{
    WORK(0, "有效"),
    NOWORK(1, "失效")
    ;
    private Integer code;
    private String message;

    CategoryDeleteEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
