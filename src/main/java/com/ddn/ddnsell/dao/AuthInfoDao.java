package com.ddn.ddnsell.dao;

import com.ddn.ddnsell.entity.Permission.AuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

public interface AuthInfoDao extends JpaRepository<AuthInfo, Long> {
    /**
     *  更新 权限状态
     * @param authId
     * @param authStatus 0 正常  1 就是删除
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "update auth_info set auth_status = ?2 where ahth_id = ?1", nativeQuery = true)
    public int updateAuthStatus(Long authId, Integer authStatus);

    /**
     * 获取指定角色的 权限ID列表
     * @param roleId
     * @return
     */
    @Query(value = "select A.auth_id from auth_info A left join role_auth RA on A.auth_id = RA.auth_id where RA.role_id = :roleId and RA.status = 0", nativeQuery = true)
    public List<BigInteger> findAllByRoleId(@Param("roleId") Long roleId);

    /**
     *  更新 权限是否是父节点
     * @param authId
     * @param isParent 0  不是父节点  1是父节点
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "update auth_info set is_parent = ?2 where auth_id = ?1", nativeQuery = true)
    public int updateAuthIsParent(Long authId, Integer isParent);
}
