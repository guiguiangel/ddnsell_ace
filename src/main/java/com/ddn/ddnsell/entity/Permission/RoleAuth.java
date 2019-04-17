package com.ddn.ddnsell.entity.Permission;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author qincx
 * @date 2019/4/11
 * @description
 */

@Entity
@Data
@Table(name = "role_auth")
@IdClass(RoleAuth.RoleAuthPrimaryKey.class)
public class RoleAuth {
    /**
     * 角色ID
     */
    @Id
    private Long roleId;
    /**
     * 权限ID
     */
    @Id
    private Long authId;

    private Integer status = 0;

    @Data
    public static class RoleAuthPrimaryKey implements Serializable {
        public RoleAuthPrimaryKey(Long roleId, Long authId) {
            this.roleId = roleId;
            this.authId = authId;
        }

        public RoleAuthPrimaryKey() {
        }

        /**
         * 角色ID
         */

        private Long roleId;
        /**
         * 权限ID
         */
        private Long authId;
    }

}
