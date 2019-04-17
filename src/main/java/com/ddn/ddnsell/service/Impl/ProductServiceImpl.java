package com.ddn.ddnsell.service.Impl;

import com.ddn.ddnsell.dao.ProductInfoDao;
import com.ddn.ddnsell.entity.ProductInfo;
import com.ddn.ddnsell.enums.ProductStatusEnum;
import com.ddn.ddnsell.enums.ResultEnum;
import com.ddn.ddnsell.exception.SellException;
import com.ddn.ddnsell.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author qincx
 * @date 2019/3/25
 * @description
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoDao productInfoDao;

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoDao.findProductInfoByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public ProductInfo findOne(String productId) {
        return  productInfoDao.findOne(productId);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoDao.findAll(pageable);
    }

    @Override
    @Transactional
    public ProductInfo save(ProductInfo productInfo) {
        if (StringUtils.isBlank(productInfo.getProductId()) && productInfo.getCreateTime() == null){
            Date date = new Date();
            productInfo.setCreateTime(date);
        }
        return productInfoDao.save(productInfo);
    }

    @Override
    @Transactional
    public void addStock(String productId ,Integer count) {
        if (StringUtils.isBlank(productId)){
            log.error("【加商品库存】message: 商品id不能为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        ProductInfo productInfo = productInfoDao.findOne(productId);
        if (productInfo == null){
            log.error("【加商品库存】message: 查询商品失败 productId:{}", productId);
            throw new SellException(ResultEnum.PRODUCT_NO_EXIST);
        }
        Integer productStock = productInfo.getProductStock();
        productInfo.setProductStock(productStock + count);
        productInfoDao.save(productInfo);
    }

    @Override
    @Transactional
    public void delStock(String productId ,Integer count) {
        if (StringUtils.isBlank(productId)){
            log.error("【减商品库存】message: 商品id不能为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        ProductInfo productInfo = productInfoDao.findOne(productId);
        if (productInfo == null){
            log.error("【减商品库存】message: 查询商品失败 productId:{}", productId);
            throw new SellException(ResultEnum.PRODUCT_NO_EXIST);
        }
        Integer productStock = productInfo.getProductStock();
        if ((productStock - count) < 0){
            log.error("【减商品库存】message: 商品库存不足 productId:{}", productId);
            throw new SellException(ResultEnum.PRODUCT_NO_ENOUGH);
        }
        productInfo.setProductStock(productStock - count);
        productInfoDao.save(productInfo);
    }

    @Override
    @Transactional
    public ProductInfo onSale(String productId) {
        if (StringUtils.isBlank(productId)){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        ProductInfo productInfo = productInfoDao.findOne(productId);
        if (productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NO_EXIST);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        ProductInfo result = productInfoDao.save(productInfo);
        return result;
    }

    @Override
    public ProductInfo offSale(String productId) {
        if (StringUtils.isBlank(productId)){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        ProductInfo productInfo = productInfoDao.findOne(productId);
        if (productInfo == null){
            throw new SellException(ResultEnum.PRODUCT_NO_EXIST);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        ProductInfo result = productInfoDao.save(productInfo);
        return result;
    }
}
