package com.ddn.ddnsell.controller;

import com.ddn.ddnsell.entity.ProductCategory;
import com.ddn.ddnsell.entity.ProductInfo;
import com.ddn.ddnsell.enums.ProductStatusEnum;
import com.ddn.ddnsell.enums.ResultEnum;
import com.ddn.ddnsell.service.CategoryService;
import com.ddn.ddnsell.service.ProductService;
import com.ddn.ddnsell.utils.KeyUtil;
import com.ddn.ddnsell.vo.ProductFrom;
import com.ddn.ddnsell.vo.ResultVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author qincx
 * @date 2019/3/26
 * @description
 */

@Controller
@RequestMapping("/seller")
public class SellProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("product/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size){
        //基数是0
        PageRequest pageRequest = new PageRequest(page -1, size);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productInfoPage", productInfoPage);
        modelAndView.setViewName("/product/list");
        return modelAndView;
    }

    /**
     * 打开添加页面
     * @return
     */
    @GetMapping("product/index")
    public ModelAndView index(){
        ProductInfo productInfo = new ProductInfo();
        List<ProductCategory> categoryList = categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productInfo", productInfo);
        modelAndView.addObject("categoryList", categoryList);
        modelAndView.setViewName("/product/index");
        return modelAndView;
    }

    /**
     * 打开修改页面
     * @param productId
     * @param map
     * @return
     */
    @GetMapping("product/toupdate/{id}")
    public ModelAndView toSave(@PathVariable(value = "id") String productId, Map<String,Object>map){

        if (StringUtils.isNotBlank(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            map.put("productInfo", productInfo);
        }
        else{
            map.put("msg", "参数错误");
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("/common/error", map);
        }

        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList", categoryList);
        return new ModelAndView("/product/index", map);
    }

    /**
     *  添加商品
     * @param productFrom
     * @return
     */
    @PostMapping("product/save")
    public ModelAndView save(@Valid ProductFrom productFrom, BindingResult bindingResult, Map<String, Object>map){
        if (productFrom == null){
            map.put("msg", "参数错误");
            map.put("url", "/sell/seller/product/index");
            return new ModelAndView("/common/error", map);
        }
        ProductInfo productInfo = new ProductInfo();
//        ID 是空就是添加
        if (StringUtils.isBlank(productFrom.getProductId())){
            productFrom.setProductId(KeyUtil.genUniqueKey());
        }
        else{
            productInfo = productService.findOne(productFrom.getProductId());
            if (productInfo == null){
                map.put("msg", "参数错误");
                map.put("url", "/sell/seller/product/index");
                return new ModelAndView("/common/error", map);
            }
        }
        BeanUtils.copyProperties(productFrom,productInfo);
        productService.save(productInfo);
        map.put("url", "/sell/seller/product/index");
        return new ModelAndView("/common/success", map);
    }

//    /**
//     *  修改商品
//     * @param productFrom
//     * @return
//     */
//    @PostMapping("product/update")
//    public ModelAndView update(@Valid ProductFrom productFrom, BindingResult bindingResult, Map<String, Object>map){
//        if (productFrom == null){
//            map.put("msg", "参数错误");
//            map.put("url", "/sell/seller/product/index");
//            return new ModelAndView("/common/error", map);
//        }
//        ProductInfo productInfo = new ProductInfo();
////        ID 是空就是添加
//        if (StringUtils.isBlank(productFrom.getProductId())){
//            productFrom.setProductId(KeyUtil.genUniqueKey());
//        }
//        else{
//            productInfo = productService.findOne(productFrom.getProductId());
//            if (productInfo == null){
//                map.put("msg", "参数错误");
//                map.put("url", "/sell/seller/product/index");
//                return new ModelAndView("/common/error", map);
//            }
//        }
//        BeanUtils.copyProperties(productFrom,productInfo);
//        productService.save(productInfo);
//        map.put("url", "/sell/seller/product/index");
//        return new ModelAndView("/common/success", map);
//    }

    /**
     * 商品上架
     * @param productId
     * @param map
     * @return
     */
    @PostMapping("product/onsale/{id}")
    @ResponseBody
    public ResultVO onSale(@PathVariable(value = "id") String productId, Map<String,Object>map){
        if (StringUtils.isNotBlank(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            if (productInfo == null){
                //TODO 查询异常
                ResultVO<ProductInfo> resultVO = new ResultVO<>(ResultEnum.SELL_ONSALE_ERROR, false,null);
                return resultVO;
            }
            productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
            productService.save(productInfo);
            ResultVO<ProductInfo> resultVO = new ResultVO<>(ResultEnum.SELL_ONSALE_SUCCESS, productInfo);
            return resultVO;
        }
        else{
            ResultVO<ProductInfo> resultVO = new ResultVO<>(ResultEnum.SELL_ONSALE_ERROR, false,null);
            return resultVO;
        }
    }

    @PostMapping("product/offsale/{id}")
    @ResponseBody
    public ResultVO offSale(@PathVariable(value = "id") String productId, Map<String,Object>map){
        if (StringUtils.isNotBlank(productId)){
            ProductInfo productInfo = productService.findOne(productId);
            if (productInfo == null){
                //TODO 查询异常
            }
            productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
            productService.save(productInfo);
            ResultVO<ProductInfo> resultVO = new ResultVO<>(ResultEnum.SELL_OFFSALE_SUCCESS, null);
            return resultVO;
        }
        else{
            ResultVO<ProductInfo> resultVO = new ResultVO<>(ResultEnum.SELL_OFFSALE_ERROR, false,null);
            return resultVO;
        }
    }
}
