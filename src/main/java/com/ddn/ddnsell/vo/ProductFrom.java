package com.ddn.ddnsell.vo;

import com.ddn.ddnsell.enums.ProductStatusEnum;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author qincx
 * @date 2019/3/26
 * @description
 */
@Data
public class ProductFrom {

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
     * 商品库存
     */
    private Integer productStock;
    /**
     * 描述
     */
    private String productDescription;
    /**
     * 小图
     */
    private String productIcon;

    /** 状态, 0正常1下架. */
    private Integer productStatus = ProductStatusEnum.UP.getCode();
    /**
     * 商品类别
     */
    private Integer categoryType;
}
