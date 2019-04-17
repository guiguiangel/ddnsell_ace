package com.ddn.ddnsell.vo;

import com.ddn.ddnsell.enums.ResultEnum;
import lombok.Data;

/**
 * @author qincx
 * @date 2019/3/26
 * @description
 */
@Data
public class ResultVO<T> {
    private Integer code;
    private String msg;
    /**
     *  返回消息类型:true 表示 success false 代表error
     */
    private boolean type = true;
    private T data;

    public ResultVO() {
    }

    public ResultVO(Integer code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public ResultVO(Integer code, String message, boolean type, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
        this.type = type;
    }

    public ResultVO(ResultEnum resultEnum, T data){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
        this.data = data;
    }
    public ResultVO(ResultEnum resultEnum, boolean type, T data){
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
        this.data = data;
        this.type = type;
    }
}
