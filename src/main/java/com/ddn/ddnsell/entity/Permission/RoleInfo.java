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
 * @description 角色表
 */
@Entity
@Data
public class RoleInfo {

    /**
     * 角色ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    /**
     * 角色名称
     */
    @NotBlank
    private String roleName;
    /**
     * 角色编号
     */
    @NotBlank
    private String roleCode;
    /**
     * 角色描述
     */
    @NotBlank
    private String roleDescription;

}
