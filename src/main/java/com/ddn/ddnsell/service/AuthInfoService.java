package com.ddn.ddnsell.service;

import com.ddn.ddnsell.entity.Permission.AuthInfo;
import com.ddn.ddnsell.vo.AuthTreeVo;

import java.util.List;

public interface AuthInfoService {
    public AuthInfo save(AuthInfo ahthInfo);
    public AuthInfo findOne(Long authId);
    public List<AuthInfo> findAllList();
    public int deleteAuth(Long authId);
    public List<AuthTreeVo> findAuthTreeVoListByRoleId(Long roleId);
}
