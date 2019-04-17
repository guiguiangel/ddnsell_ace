package com.ddn.ddnsell.controller;

import com.ddn.ddnsell.dto.OrderDTO;
import com.ddn.ddnsell.enums.ResultEnum;
import com.ddn.ddnsell.exception.SellException;
import com.ddn.ddnsell.service.OrderMasterService;
import com.ddn.ddnsell.utils.ResultVoUtil;
import com.ddn.ddnsell.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author qincx
 * @date 2019/3/26
 * @description
 */

@Controller
@RequestMapping("/seller")
public class SellOrderController {

    @Autowired
    private OrderMasterService orderMasterService;

    @GetMapping("/order/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "10") Integer size, Map<String , Object> map){
        PageRequest pageRequest = new PageRequest(page -1, size);
        Page<OrderDTO> orderDTOPage = orderMasterService.findAll(pageRequest);
        map.put("orderDTOPage", orderDTOPage);
        return new ModelAndView("/order/list", map);
    }

//    @PostMapping("/order/detail")
//    public String detail(@RequestParam("orderId") String orderId, @RequestParam("openid") String openid, Model model){
//        OrderDTO orderDTO = orderMasterService.findOne(orderId, openid);
//        if (orderDTO == null){
//            throw new SellException(ResultEnum.PARAM_ERROR);
//        }
//        model.addAttribute("orderDTO", orderDTO);
//       // map.put("c", orderDTO);
//        return "/order/detail";
//    }

    @GetMapping("/order/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId, @RequestParam("openid") String openid, Map<String , Object> map){
        OrderDTO orderDTO = orderMasterService.findOne(orderId, openid);
        if (orderDTO == null){
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        map.put("orderDTO", orderDTO);
        return new ModelAndView("/order/detail", map);
    }

    @PostMapping("/order/finish")
    @ResponseBody
    public ResultVO finish(@RequestParam("orderId") String orderId, @RequestParam("openid") String openid){
        try {
            orderMasterService.finish(orderId, openid);
        } catch (Exception e) {
           // e.printStackTrace();
            return ResultVoUtil.error(ResultEnum.ORDER_NOPAY_ERROR,null);
        }
        return ResultVoUtil.success();
    }

    @PostMapping("/order/cancel")
    @ResponseBody
    public ResultVO cancel(@RequestParam("orderId") String orderId, @RequestParam("openid") String openid){
        try {
            orderMasterService.cancel(orderId, openid);
        } catch (Exception e) {
            //e.printStackTrace();
            return ResultVoUtil.error(ResultEnum.ORDER_NOPAY_ERROR,null);
        }
        return ResultVoUtil.success();
    }
}
