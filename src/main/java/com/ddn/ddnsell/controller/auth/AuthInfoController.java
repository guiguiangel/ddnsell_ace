package com.ddn.ddnsell.controller.auth;

import com.ddn.ddnsell.entity.Permission.AuthInfo;
import com.ddn.ddnsell.enums.ResultEnum;
import com.ddn.ddnsell.service.AuthInfoService;
import com.ddn.ddnsell.utils.ResultVoUtil;
import com.ddn.ddnsell.vo.AuthTreeVo;
import com.ddn.ddnsell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author qincx
 * @date 2019/4/11
 * @description
 */
@Controller
@RequestMapping("/auth")
public class AuthInfoController {

    /**
     *  打开 修改和保存页面
     */
    @Autowired
    private AuthInfoService authInfoService;
    @GetMapping("/index")
    public ModelAndView toAuthIndex(@RequestParam(value = "authId", required = false) Long authId
    , Map<String ,Object>map){
        AuthInfo authInfo = new AuthInfo();
        if (authId != null){
            AuthInfo authInfoServiceOne = authInfoService.findOne(authId);
            if (authInfoServiceOne != null){
                authInfo = authInfoServiceOne;
            }
        }
        map.put("authInfo", authInfo);
        List<AuthInfo> authInfoList = authInfoService.findAllList();
        map.put("authInfoList", authInfoList);

        return new ModelAndView("/auth/index", map);
    }

    /**
     * 保存
     * @param authInfo
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public ResultVO saveAuth(@Valid AuthInfo authInfo){
        System.out.println(authInfo);
        //TODO:这里先对查询 权限名称和编号是否已经存在，如果存在着返回错误
        if (authInfo.getAuthId() != null){
            ResultVoUtil.error(ResultEnum.AUTH_CREATE_PARAM_ERROR, null);
        }
        AuthInfo save = authInfoService.save(authInfo);
        if (save == null){
            ResultVoUtil.error(ResultEnum.AUTH_CREATE_PARAM_ERROR, null);
        }
        return ResultVoUtil.success();
    }

    /**
     * 修改
     * @param authInfo
     * @return
     */
    @PostMapping("/update")
    @ResponseBody
    public ResultVO updateAuth(@Valid AuthInfo authInfo){
        System.out.println(authInfo);
        if (authInfo.getAuthId() == null){
            ResultVoUtil.error(ResultEnum.AUTH_UPDATE_PARAM_ERROR, null);
        }

        AuthInfo save = authInfoService.save(authInfo);
        if (save == null){
            ResultVoUtil.error(ResultEnum.AUTH_UPDATE_PARAM_ERROR, null);
        }
        return ResultVoUtil.success();
    }

    @GetMapping("/authtree")
    @ResponseBody
    public List<AuthTreeVo> listTree(@RequestParam(value = "roleId", required = false) Long roleId) {
        List<AuthTreeVo> authTreeVoList = authInfoService.findAuthTreeVoListByRoleId(roleId);
        return authTreeVoList;
    }


}
