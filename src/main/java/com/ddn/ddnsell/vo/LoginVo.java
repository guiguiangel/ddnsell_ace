package com.ddn.ddnsell.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author qincx
 * @date 2019/4/8
 * @description
 */
@Data
public class LoginVo {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
   // @Length(min=6)
    private String password;
}
