package com.ddn.ddnsell.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author qincx
 * @date 2019/4/16
 * @description
 */
@Controller
public class IndexController {

//    @GetMapping("/{path1}/{path2}")
//    public String index(@PathVariable("path1") String path1, @PathVariable("path2")String path2){
//        String path = "/" + path1 + "/" + path2;
//        return path;
//    }

//    @GetMapping("/{path1}")
//    public String index1(@PathVariable("path1") String path1){
//        String path = "/" + path1;
//        return path;
//    }

    @GetMapping("/index")
    public String index1(){

        return "/index";
    }
}
