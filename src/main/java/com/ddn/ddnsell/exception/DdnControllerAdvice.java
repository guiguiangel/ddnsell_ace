package com.ddn.ddnsell.exception;

import com.ddn.ddnsell.enums.ResultEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author qincx
 * @date 2019/3/28
 * @description
 */
@ControllerAdvice
public class DdnControllerAdvice {

    @ExceptionHandler(value = SellException.class)
    //@ResponseBody
    public ModelAndView sellExceptionHandler(SellException e){
        Map<String,Object> map = new HashMap<>();
        map.put("msg", e.getMessage());
        map.put("url", null);
        return new ModelAndView("/common/error", map);
    }

    @ExceptionHandler(value = Exception.class)
    //@ResponseBody
    public ModelAndView ExceptionHandler(Exception e){
        Map<String,Object> map = new HashMap<>();
        map.put("msg", e.getMessage());
        map.put("url", null);
        e.printStackTrace();
        return new ModelAndView("/common/error", map);
    }
}
