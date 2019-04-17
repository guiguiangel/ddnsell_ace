package com.ddn.ddnsell.vo;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author qincx
 * @date 2019/3/26
 * @description
 */
@Data
public class ProductVo {
//                        "id": "123457",
//                                "name": "慕斯蛋糕",
//                                "price": 10.9,
//                                "description": "美味爽口",
//                                "icon": "http://xxx.com",

    private String productId;

    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品价格
     */
    private BigDecimal productPrice;
    /**
     * 描述
     */
    private String productDescription;
    /**
     * 小图
     */
    private String productIcon;
}
