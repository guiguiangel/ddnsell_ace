package com.ddn.ddnsell.dao;

import com.ddn.ddnsell.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductInfoDao extends JpaRepository<ProductInfo, String> {

    List<ProductInfo> findProductInfoByProductStatus(Integer status);
   // List<ProductInfo> findProductInfoByCategoryType();
}
