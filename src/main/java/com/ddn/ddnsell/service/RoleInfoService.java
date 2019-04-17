package com.ddn.ddnsell.service;

import com.ddn.ddnsell.entity.Permission.RoleInfo;
import com.ddn.ddnsell.entity.Permission.UserRole;
import com.ddn.ddnsell.vo.RoleVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleInfoService {
    public RoleInfo findOne(Long roleId);
    public RoleInfo save(RoleInfo roleInfo, List<String> authIds);
    public RoleInfo update(RoleInfo roleInfo, List<String> authIds);

    public List<UserRole> addUserRole(Long userId, List<Long> roleIds);
    public Page<RoleInfo> findAllPage(Integer page, Integer size);

    public List<RoleInfo> findAllList();
    public List<RoleInfo> findRoleInfoList(Long userId);
    public List<RoleVo> findRoleVoListByUserId(Long userId);
}
