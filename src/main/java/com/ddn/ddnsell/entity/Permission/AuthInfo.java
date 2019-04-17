package com.ddn.ddnsell.entity.Permission;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * @author qincx
 * @date 2019/4/11
 * @description 权限对象
 */
@Entity
@Data
public class AuthInfo {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authId;
    /**
     * 权限中文名称
     */
    @NotBlank
    private String authName;
    /**
     * 权限编号
     */
    @NotBlank
    private String authCode;
    /**
     * 权限描述
     */
    @NotBlank
    private String authDescription;
    /**
     * 权限对应的页面访问路径
     */
    @NotBlank
    private String authPage;
    /**
     * 是否是左侧栏的菜单 0不是菜单 1是菜单
     */
    private Integer authIsmenu;
    /**
     * 父权限的ID
     */
    private Long authPid;
    /**
     * 权限的状态 0表示正常 1 表示失效
     */
    private  Integer authStatus;

    /**
     * 是否是父节点 0  否 1是
     */
    private Integer isParent = 0;

}
