package com.ddn.ddnsell.service.Impl;

import com.ddn.ddnsell.dao.CategoryDao;
import com.ddn.ddnsell.entity.ProductCategory;
import com.ddn.ddnsell.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qincx
 * @date 2019/3/25
 * @description
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;
    @Override
    public ProductCategory findOne(Integer categoryId) {
        return categoryDao.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return categoryDao.save(productCategory);
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> types) {
        return categoryDao.findByCategoryTypeIn(types);
    }

    @Override
    public Page<ProductCategory> findPageList(Pageable pageable) {
        return categoryDao.findAll(pageable);
    }
}
