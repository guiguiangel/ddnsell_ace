package com.ddn.ddnsell.controller;

import com.ddn.ddnsell.entity.ProductCategory;
import com.ddn.ddnsell.entity.ProductInfo;
import com.ddn.ddnsell.service.CategoryService;
import com.ddn.ddnsell.service.ProductService;
import com.ddn.ddnsell.utils.ResultVoUtil;
import com.ddn.ddnsell.vo.ProductListVo;
import com.ddn.ddnsell.vo.ProductVo;
import com.ddn.ddnsell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qincx
 * @date 2019/3/26
 * @description
 */
@Controller
@RequestMapping("/buyer")
public class BuyProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/product/list")
    @ResponseBody
    public ResultVO list(){
        List<ProductInfo> productInfoList = productService.findUpAll();
        List<Integer> categoryTypeList = new ArrayList<>();
        for (ProductInfo productInfo:productInfoList
             ) {
            categoryTypeList.add(productInfo.getCategoryType());
        }
        List<ProductCategory> categoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        List<ProductListVo> productListVos = new ArrayList<>();
        for (ProductCategory category:categoryList
             ) {
            ProductListVo  productListVo = new ProductListVo();
            productListVo.setName(category.getCategoryName());
            productListVo.setType(category.getCategoryType());
            for (ProductInfo productInfo:productInfoList
                 ) {
                if (productInfo.getCategoryType().equals(category.getCategoryType())){
                    ProductVo productVo = new ProductVo();
                    BeanUtils.copyProperties(productInfo,productVo);
                    productListVo.getFoods().add(productVo);
                }

            }
            productListVos.add(productListVo);
        }

        return  ResultVoUtil.success(productListVos);
    }
}
