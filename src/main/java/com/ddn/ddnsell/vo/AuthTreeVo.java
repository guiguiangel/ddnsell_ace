package com.ddn.ddnsell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author qincx
 * @date 2019/4/12
 * @description
 */
@Data
public class AuthTreeVo {

    @JsonProperty("id")
    private Long authId;
    /**
     * 权限中文名称
     */
  @JsonProperty("name")
    private String authName;
    /**
     * 父权限的ID
     */
    @JsonProperty("pId")
    private Long authPid;

    private boolean checked = false;

    /**
     * 是否是父节点 0  否 1是
     */
    @JsonProperty("open")
    private boolean open = false;

}
