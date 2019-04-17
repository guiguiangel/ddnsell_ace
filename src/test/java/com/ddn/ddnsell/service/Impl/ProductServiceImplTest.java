package com.ddn.ddnsell.service.Impl;

import com.ddn.ddnsell.config.WechatAccountConfig;
import com.ddn.ddnsell.entity.ProductInfo;
import com.ddn.ddnsell.service.ProductService;
import com.ddn.ddnsell.utils.KeyUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;
    @Autowired
    private WechatAccountConfig wechatAccountConfig;
    @Test
    public void findUpAll() {
        String appid = wechatAccountConfig.getAppid();
        List<ProductInfo> upAll = productService.findUpAll();
        System.out.println(upAll);
    }

    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("1553504220929551801");
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<ProductInfo> all = productService.findAll(pageRequest);
        System.out.println(all);
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId(KeyUtil.genUniqueKey());
        Date date = new Date();
        productInfo.setCreateTime(date);
        productInfo.setCategoryType(1);
        productInfo.setProductName("蔬菜饼");
        productInfo.setProductPrice(new BigDecimal(3.5));
        productInfo.setProductStock(1000);
        productInfo.setProductDescription("营养丰富的蔬菜饼");
        productInfo.setProductStatus(0);
        //productInfo.setProductIcon("http://cu.dodonew.com/sell/image/manguobing.png");
       // productInfo.setProductIcon("http://cu.dodonew.com/sell/image/pidanzhou.png");
        productInfo.setProductIcon("http://cu.dodonew.com/sell/image/shucaibing.png");
//        http://cu.dodonew.com/sell/image/pidanzhou.png
////        http://cu.dodonew.com/sell/image/shucaibing.png
        productService.save(productInfo);
    }

    @Test
    public void onSale() {
        ProductInfo productInfo = productService.onSale("1553504220929551801");
        System.out.println(productInfo);
    }

    @Test
    public void offSale() {
        ProductInfo productInfo = productService.offSale("1553504220929551801");
        System.out.println(productInfo);
    }
}