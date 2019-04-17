package com.ddn.ddnsell.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qincx
 * @date 2019/3/26
 * @description
 */
@Data
public class ProductListVo {
//          "name": "好吃的",
//                  "type": 2,
//                  "foods"
    private String name;
    private Integer type;
    private List<ProductVo> foods = new ArrayList<>();

}
