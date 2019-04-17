package com.ddn.ddnsell.entity.Permission;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author qincx
 * @date 2019/4/11
 * @description
 */
@Entity
@Data
@Table(name = "user_role")
@IdClass(UserRole.UserRolePk.class)
public class UserRole {
    /**
     * 用户ID
     */
    @Id
    private Long userId;
    /**
     * 角色ID
     */
    @Id
    private Long roleId;

    /**
     *  0 表示正常 1表示删除
     */
    private Integer status = 0;

    @Data
    public static class UserRolePk implements Serializable {
        /**
         * 用户ID
         */
        private Long userId;
        /**
         * 角色ID
         */
        private Long roleId;
    }
}
