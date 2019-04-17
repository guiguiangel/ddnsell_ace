package com.ddn.ddnsell.vo;

import com.ddn.ddnsell.entity.SellerUser;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author qincx
 * @date 2019/4/15
 * @description
 */


@Data
public class SellerUserVo {
    private Long id;
    private String nickname;
    private String mobile;
    private String head;
    private Date registerDate;
    private Date lastLoginDate;
    private Integer loginCount;
    private String roleName;

    public SellerUserVo() {
    }

    public SellerUserVo(BigInteger id, String nickname, String mobile, String head, Timestamp registerDate, Timestamp lastLoginDate, Integer loginCount, String roleName) {
        this.id = id.longValue();
        this.nickname = nickname;
        this.mobile = mobile;
        this.head = head;
        this.registerDate = registerDate;
        this.lastLoginDate = lastLoginDate;
        this.loginCount = loginCount;
        this.roleName = roleName;
    }
}
