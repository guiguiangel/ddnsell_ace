package com.ddn.ddnsell.service.Impl;

import com.ddn.ddnsell.entity.ProductCategory;
import com.ddn.ddnsell.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;
    @Test
    public void findOne() {
        ProductCategory category = categoryService.findOne(1);
        System.out.println(category);
    }

    @Test
    public void findAll() {
        List<ProductCategory> all = categoryService.findAll();
        for (ProductCategory category:all
             ) {
            System.out.println(category);
        }
    }

    @Test
    public void save() {
        ProductCategory productCategory =new ProductCategory("冷饮",2);
        productCategory.setCreateTime(new Date());
        categoryService.save(productCategory);
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> lists = categoryService.findByCategoryTypeIn(Arrays.asList(1, 2, 3, 4));
        for (ProductCategory category:lists
             ) {
            System.out.println(category);
        }
    }
}