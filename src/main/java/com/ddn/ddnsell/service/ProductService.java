package com.ddn.ddnsell.service;

import com.ddn.ddnsell.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    public List<ProductInfo> findUpAll();
    public ProductInfo findOne(String productId);
    public Page<ProductInfo> findAll(Pageable pageable);
    public ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     */
    public void addStock(String productId ,Integer count);

    /**
     * 减库存
     */
    public void delStock(String productId, Integer count);

    /**
     * 上架
     * @return
     */
    public ProductInfo onSale(String productId);

    /**
     * 下架
     * @return
     */
    public ProductInfo offSale(String productId);
}
