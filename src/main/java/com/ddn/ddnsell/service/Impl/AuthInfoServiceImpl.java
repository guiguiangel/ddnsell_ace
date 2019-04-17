package com.ddn.ddnsell.service.Impl;

import com.ddn.ddnsell.dao.AuthInfoDao;
import com.ddn.ddnsell.entity.Permission.AuthInfo;
import com.ddn.ddnsell.enums.ResultEnum;
import com.ddn.ddnsell.service.AuthInfoService;
import com.ddn.ddnsell.utils.ResultVoUtil;
import com.ddn.ddnsell.vo.AuthTreeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qincx
 * @date 2019/4/11
 * @description
 */
@Service
public class AuthInfoServiceImpl implements AuthInfoService {

    @Autowired
    private AuthInfoDao authInfoDao;


    @Override
    @Transactional
    public AuthInfo save(AuthInfo authInfo) {
        //1 设置默认值
        if (authInfo.getAuthIsmenu() == null){
            authInfo.setAuthIsmenu(0);
        }
        if (authInfo.getAuthStatus() == null){
            authInfo.setAuthStatus(0);
        }
        //2 设置父节点状态 为父节点
        Long authPid = authInfo.getAuthPid();
        if (authPid != null){
            authInfoDao.updateAuthIsParent(authPid, 1);
        }else
        {
            authInfo.setAuthPid(0l);
        }

        //3 添加权限
        AuthInfo auth = authInfoDao.save(authInfo);
        return auth;
    }

    @Override
    public AuthInfo findOne(Long authId) {
        return authInfoDao.findOne(authId);
    }

    @Override
    public List<AuthInfo> findAllList() {
        List<AuthInfo> authInfoList = authInfoDao.findAll();
        return authInfoList;
    }


    @Override
    @Transactional
    public int deleteAuth(Long authId) {
        int count = authInfoDao.updateAuthStatus(authId, 1);
        return count;
    }

    @Override
    public List<AuthTreeVo> findAuthTreeVoListByRoleId(Long roleId) {
        // 1 获取所有 权限
        List<AuthInfo> authInfoList = authInfoDao.findAll();
        //2 获取角色 包含的权限
        List<BigInteger> allByRoleId = new ArrayList<>();
        if (roleId != null){
            allByRoleId = authInfoDao.findAllByRoleId(roleId);
        }

        if (CollectionUtils.isEmpty(authInfoList)) {
            ResultVoUtil.error(ResultEnum.AUTH_DELETE_ERROR, null);
        }
        //3 吧 authInfo 转化成 roletreeVo
        List<AuthTreeVo> authTreeVoList = new ArrayList<>();
        for (AuthInfo authInfo:authInfoList
        ) {
            AuthTreeVo authTreeVo = new AuthTreeVo();
            BeanUtils.copyProperties(authInfo, authTreeVo);
            //4 如果 有用户权限，要设置 checked  状态
            for (BigInteger id:allByRoleId
                 ) {
                if (authTreeVo.getAuthId().equals(id.longValue())){
                    authTreeVo.setChecked(true);
                }
            }
            //5如果是 父节点 ，设置展开
            if (authInfo.getIsParent() == 1){
                authTreeVo.setOpen(true);
            }
            authTreeVoList.add(authTreeVo);
        }

        return authTreeVoList;
    }
}
