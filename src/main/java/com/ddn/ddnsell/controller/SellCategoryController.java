package com.ddn.ddnsell.controller;

import com.ddn.ddnsell.entity.ProductCategory;
import com.ddn.ddnsell.enums.ResultEnum;
import com.ddn.ddnsell.service.CategoryService;
import com.ddn.ddnsell.utils.KeyUtil;
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
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author qincx
 * @date 2019/3/26
 * @description
 */
@Controller
@RequestMapping("/seller")
public class SellCategoryController {

    @Autowired
    private CategoryService categoryService;
    @GetMapping("/category/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "size", defaultValue = "10")Integer size, Map<String, Object>map){
        PageRequest pageRequest = new PageRequest(page-1, size);
        Page<ProductCategory> categoryPage = categoryService.findPageList(pageRequest);
        map.put("categoryPage", categoryPage);
        return new ModelAndView("/category/list", map);
    }

    @GetMapping("/category/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId, Map<String, Object>map){
        ProductCategory category = new ProductCategory();
        if (categoryId != null){
            category = categoryService.findOne(categoryId);
            if (category == null){
                //TODO 分类如果错误要处理
            }
        }
        map.put("category", category);
        return new ModelAndView("/category/index", map);
    }

    @PostMapping("/category/save")
    @ResponseBody
    public ResultVO save(@Valid ProductCategory productCategory, BindingResult bindingResult, Map<String, Object>map){
        if (bindingResult.hasErrors()){
            return new ResultVO(ResultEnum.SELL_CATEGORY_PARAMERROR, false, null);
        }
        ResultVO resultVO = null;
        ProductCategory category = new ProductCategory();
        if (productCategory.getCategoryId() == null) {
            productCategory.setCreateTime(new Date());
            resultVO = new ResultVO(ResultEnum.SELL_SAVECATEGORY_SUCCESS, null);
        }
        else{
            category = categoryService.findOne(productCategory.getCategoryId());
            if (category == null){
                return new ResultVO(ResultEnum.SELL_CATEGORY_PARAMERROR, false, null);
            }
            resultVO = new ResultVO(ResultEnum.SELL_UPDATECATEGORY_SUCCESS, null);
            productCategory.setCategoryType(category.getCategoryType());
        }
        BeanUtils.copyProperties(productCategory, category);
        categoryService.save(category);
        return resultVO;
    }
}
