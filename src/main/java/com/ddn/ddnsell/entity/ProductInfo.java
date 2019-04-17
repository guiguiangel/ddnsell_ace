package com.ddn.ddnsell.entity;

import com.ddn.ddnsell.enums.ProductStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qincx
 * @date 2019/3/25
 * @description
 */
@Entity
@Data
@ToString
@DynamicUpdate
public class ProductInfo {
    @Id
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
    /**
     * 创建时间
     */
    private Date   createTime;
    /**
     * 更新时间
     */
    private Date  updateTime;

    @JsonIgnore
    public String getProductStatusEnums(){
        for (ProductStatusEnum productStatusEnum: ProductStatusEnum.values()){
            if (this.productStatus == productStatusEnum.getCode()){
                return productStatusEnum.getMessage();
            }
        }
        return null;
    }
}
