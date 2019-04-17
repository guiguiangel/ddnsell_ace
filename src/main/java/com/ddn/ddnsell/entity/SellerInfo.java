package com.ddn.ddnsell.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author qincx
 * @date 2019/3/25
 * @description 卖家表
 */
@Entity
@Data
public class SellerInfo {

    @Id
    private String sellerId;
    /**
     *卖家登录系统的用户名
     */
    private String username;
    /**
     *卖家登录系统的密码
     */
    private String password;
    /**
     *卖家微信openid
     */
    private String openid;

    /**
     * 卖家手机号码
     */
    private String phone;
}
