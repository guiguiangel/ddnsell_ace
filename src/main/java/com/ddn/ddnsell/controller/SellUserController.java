package com.ddn.ddnsell.controller;

import com.ddn.ddnsell.entity.Permission.RoleInfo;
import com.ddn.ddnsell.entity.SellerUser;
import com.ddn.ddnsell.service.RoleInfoService;
import com.ddn.ddnsell.service.SellerUserService;
import com.ddn.ddnsell.utils.MD5MsUtil;
import com.ddn.ddnsell.utils.ResultVoUtil;
import com.ddn.ddnsell.vo.LoginVo;
import com.ddn.ddnsell.vo.ResultVO;
import com.ddn.ddnsell.vo.RoleVo;
import com.ddn.ddnsell.vo.SellerUserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author qincx
 * @date 2019/3/29
 * @description
 */
@Controller
@RequestMapping("/seller")
public class SellUserController {

    @GetMapping("/login")
    public String toLogin(){
        return "/login";
    }

    @GetMapping("/unauthor")
    public String unAuthor(){
        return "/common/unauthor";
    }

    @Autowired
    private SellerUserService sellerUserService;
    @Autowired
    private RoleInfoService roleInfoService;

    @GetMapping("/user/login")
    @ResponseBody
    //public ResultVO login(@Valid LoginVo loginVo){
    public ResultVO login(@RequestParam("username") String name, @RequestParam("password") String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(name, password);
        try {
            subject.login(usernamePasswordToken);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVoUtil.error(-1, "登录失败", null);
        }

        //TODO:登录成功就要生成 cookie token redis里面保存用户信息，实现SSO 单点登录
        return ResultVoUtil.success();
    }

    @GetMapping("/user/unlogin")
    @ResponseBody
    public ResultVO unlogin(){

        System.out.println("usr unlogin");
        return ResultVoUtil.success();
    }


    @GetMapping("/selluser/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1")Integer page,
                             @RequestParam(value = "size", defaultValue = "10")Integer size,
                             Map<String,Object> map){

        List<SellerUserVo> sellerUserVoList = sellerUserService.findAllVoPage(page - 1, size);
        map.put("sellerUserVoList", sellerUserVoList);
        return new ModelAndView("/auth/selluserlist", map);
    }

    @GetMapping("/selluser/index")
    public ModelAndView index(@RequestParam(value = "userId",required = false)Long userId, Map<String,Object> map){
        SellerUser sellerUser = new SellerUser();
        if (userId != null){
            sellerUser = sellerUserService.findOneById(userId);
            if (sellerUser == null){
                //TODO: 如果为空要返回错误
            }
        }
        //List<RoleVo> roleVoList = roleInfoService.findRoleVoListByUserId(userId);
       // map.put("roleVoList", roleVoList);
        map.put("sellerUser", sellerUser);
        return new ModelAndView("/auth/selluser", map);
    }

    @PostMapping("/selluser/update")
    @ResponseBody
    public ResultVO update(SellerUser sellerUser, String [] roleIds){

        sellerUserService.update(sellerUser, roleIds);
        return ResultVoUtil.success();
    }

}
