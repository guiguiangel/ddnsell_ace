package com.ddn.ddnsell.service;

import com.ddn.ddnsell.entity.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    public ProductCategory findOne(Integer categoryId);
    public List<ProductCategory> findAll();
    public Page<ProductCategory> findPageList(Pageable pageable);
    public ProductCategory save(ProductCategory productCategory);
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> types);
}
