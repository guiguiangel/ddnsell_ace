package com.ddn.ddnsell.entity;

import com.ddn.ddnsell.enums.CategoryDeleteEnum;
import com.ddn.ddnsell.utils.EnumsUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
public class ProductCategory {
    /**
     * id
     */
    @Id
    @GeneratedValue
    private Integer categoryId;
    /**
     * 类目名字
     */
    private String categoryName;
    /**
     * 类目编号
     */
    private Integer categoryType;
    /**
     * 类目是否删除 0 没删除 1 代表删除
     */
    private Integer isdelete;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date  updateTime;

    @JsonIgnore
    public String getCategoryDelete(){
        return  EnumsUtil.getByCode(this.getIsdelete(), CategoryDeleteEnum.class).getMessage();
    }

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, int categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
