package com.ddn.ddnsell.controller.auth;

import com.ddn.ddnsell.entity.Permission.RoleInfo;
import com.ddn.ddnsell.enums.ResultEnum;
import com.ddn.ddnsell.service.AuthInfoService;
import com.ddn.ddnsell.service.RoleInfoService;
import com.ddn.ddnsell.utils.ResultVoUtil;
import com.ddn.ddnsell.vo.ResultVO;
import com.ddn.ddnsell.vo.RoleVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author qincx
 * @date 2019/4/12
 * @description
 */
@Controller
@RequestMapping("/auth")
public class RoleInfoController {
    /**
     *  打开 修改和保存页面
     */
    @Autowired
    private RoleInfoService roleInfoService;

    @GetMapping("/role/index")
    public ModelAndView toRoleIndex(@RequestParam(value = "roleId", required = false) Long roleId
            , Map<String ,Object> map){
        RoleInfo roleInfo = new RoleInfo();
        if (roleId != null){
            RoleInfo roleInfoServiceOne = roleInfoService.findOne(roleId);
            if (roleInfoServiceOne != null){
                roleInfo = roleInfoServiceOne;
            }
        }
        map.put("roleInfo", roleInfo);

        return new ModelAndView("/auth/role", map);
    }

    @PostMapping("/role/save")
    @ResponseBody
    public ResultVO saveRole(@Valid RoleInfo roleInfo, @RequestParam(value = "authIds", required = false) String authIds){
        System.out.println(roleInfo);
        //TODO:这里先对查询 权限名称和编号是否已经存在，如果存在着返回错误
        if (roleInfo.getRoleId() != null){
            ResultVoUtil.error(ResultEnum.ROLE_CREATE_PARAM_ERROR, null);
        }

        List<String> asList = new ArrayList<>();
        if (StringUtils.isNotBlank(authIds)){
            String[] split = authIds.split(",");
           asList = Arrays.asList(split);

        }
        RoleInfo save = roleInfoService.save(roleInfo, asList);
        if (save == null){
            ResultVoUtil.error(ResultEnum.ROLE_CREATE_PARAM_ERROR, null);
        }
        return ResultVoUtil.success();
    }

    @PostMapping("/role/update")
    @ResponseBody
    public ResultVO updateRole(@Valid RoleInfo roleInfo, @RequestParam(value = "authIds", required = false) String authIds){

        if (roleInfo.getRoleId() == null){
            ResultVoUtil.error(ResultEnum.ROLE_UPDATE_PARAM_ERROR, null);
        }
        List<String> asList = new ArrayList<>();
        if (StringUtils.isNotBlank(authIds)){
            String[] split = authIds.split(",");
            asList = Arrays.asList(split);

        }
        RoleInfo roleInfo1 = roleInfoService.update(roleInfo, asList);
        if (roleInfo1 == null){
            ResultVoUtil.error(ResultEnum.ROLE_UPDATE_ERROR, null);
        }
        return ResultVoUtil.success();
    }

    @GetMapping("/role/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1")Integer page,
                             @RequestParam(value = "size", defaultValue = "10")Integer size,
                             Map<String,Object>map){

        Page<RoleInfo> roleInfoPage = roleInfoService.findAllPage(page - 1, size);
        map.put("roleInfoPage", roleInfoPage);
        return new ModelAndView("/auth/rolelist", map);
    }

    /**
     * 用户 包含的角色列表
     * @param userId
     * @return
     */
    @GetMapping("/role/userrolelist")
    @ResponseBody
    public  List<RoleVo> userRoleList(@RequestParam(value = "userId",required = false)Long userId){
        List<RoleVo> roleVoList = roleInfoService.findRoleVoListByUserId(userId);
        return roleVoList;
    }
}
