package com.ddn.ddnsell.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author qincx
 * @date 2019/4/11
 * @description
 */
@Entity
@Data
public class SellerUser {
    @Id
    private Long id;
    private String nickname;
    private String mobile;
    private String password;
    private String salt;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;
}
