package com.ddn.ddnsell.exception;

import com.ddn.ddnsell.enums.ResultEnum;
import lombok.Getter;

/**
 * @author qincx
 * @date 2019/3/25
 * @description
 */
@Getter
public class SellException extends RuntimeException {
    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
