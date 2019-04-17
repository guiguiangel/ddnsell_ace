package com.ddn.ddnsell.service.Impl;

import com.ddn.ddnsell.dao.RoleAuthDao;
import com.ddn.ddnsell.dao.RoleInfoDao;
import com.ddn.ddnsell.dao.UserRoleDao;
import com.ddn.ddnsell.entity.Permission.RoleAuth;
import com.ddn.ddnsell.entity.Permission.RoleInfo;
import com.ddn.ddnsell.entity.Permission.UserRole;
import com.ddn.ddnsell.enums.ResultEnum;
import com.ddn.ddnsell.exception.SellException;
import com.ddn.ddnsell.service.RoleInfoService;
import com.ddn.ddnsell.vo.RoleVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qincx
 * @date 2019/4/12
 * @description
 */

@Service
public class RoleInfoServiceImpl implements RoleInfoService {

    @Autowired
    private RoleInfoDao roleInfoDao;
    @Autowired
    private RoleAuthDao roleAuthDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public RoleInfo findOne(Long roleId) {
        RoleInfo roleInfo = roleInfoDao.findOne(roleId);
        return roleInfo;
    }

    @Override
    @Transactional
    public RoleInfo save(RoleInfo roleInfo, List<String> authIds) {
        RoleInfo roleInfo1 = roleInfoDao.save(roleInfo);
        if (CollectionUtils.isNotEmpty(authIds)){
            List<RoleAuth> roleAuthList = new ArrayList<>();
            for (String authId:authIds
                 ) {
                RoleAuth roleAuth = new RoleAuth();
                roleAuth.setRoleId(roleInfo1.getRoleId());
                roleAuth.setAuthId(Long.parseLong(authId));
                roleAuthList.add(roleAuth);
            }
            roleAuthDao.save(roleAuthList);
        }
        return roleInfo1;
    }

    @Override
    @Transactional
    public RoleInfo update(RoleInfo roleInfo, List<String> authIds){
        RoleInfo roleInfo1 = roleInfoDao.findOne(roleInfo.getRoleId());
        //1 查询 角色
        if (roleInfo1 == null){
            throw new SellException(ResultEnum.ROLE_UPDATE_PARAM_ERROR);
        }
        //2 设置多表 状态 为删除
        roleAuthDao.deleteRoleAuthBySetStatus(roleInfo1.getRoleId());
        //3添加 多表
        List<RoleAuth> roleAuthList = new ArrayList<>();
        for (String authId:authIds
             ) {
            RoleAuth roleAuth = new RoleAuth();
            roleAuth.setAuthId(Long.parseLong(authId));
            roleAuth.setRoleId(roleInfo1.getRoleId());
            roleAuth.setStatus(0);
           roleAuthList.add(roleAuth);
        }
        roleAuthDao.save(roleAuthList);

        //4 保存 角色
        BeanUtils.copyProperties(roleInfo, roleInfo1);
        roleInfoDao.save(roleInfo1);
        return roleInfo1;
    }

    @Override
    public List<UserRole> addUserRole(Long userId, List<Long> roleIds) {
        List<UserRole> userRoleList = new ArrayList<>();
        for (Long roleId:roleIds
             ) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleList.add(userRole);

        }
        List<UserRole> userRoles = userRoleDao.save(userRoleList);
        return userRoles;
    }

    @Override
    public Page<RoleInfo> findAllPage(Integer page, Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);
        Page<RoleInfo> roleInfoPage = roleInfoDao.findAll(pageRequest);
        return roleInfoPage;
    }

    @Override
    public List<RoleInfo> findAllList() {
        roleInfoDao.findAll();

        return roleInfoDao.findAll();
    }

    @Override
    public List<RoleInfo> findRoleInfoList(Long userId) {
        List<RoleInfo> roleInfoList = roleInfoDao.findListByUserId(userId);
        return roleInfoList;
        //return null;
    }

    @Override
    public List<RoleVo> findRoleVoListByUserId(Long userId) {
        //List<Object> objectList = roleInfoDao.findListByUserId(userId);
        List<RoleInfo> userRoleList = new ArrayList<>();
        if (userId != null){
            userRoleList = roleInfoDao.findListByUserId(userId);
        }
       List<RoleInfo> roleInfoList = roleInfoDao.findAll();

        List<RoleVo> roleVoList = new ArrayList<>();

        for (RoleInfo roleInfo : roleInfoList){
            RoleVo roleVo = new RoleVo();
            BeanUtils.copyProperties(roleInfo, roleVo);

            for (RoleInfo uRole:userRoleList
                 ) {
                if (uRole.getRoleId().equals(roleVo.getRoleId())){
                    roleVo.setChecked(true);
                }
            }
           roleVoList.add(roleVo);
        }
        return roleVoList;
    }
}
