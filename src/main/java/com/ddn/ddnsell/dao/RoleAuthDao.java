package com.ddn.ddnsell.dao;

import com.ddn.ddnsell.entity.Permission.RoleAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleAuthDao extends JpaRepository<RoleAuth, Long> {
    @Modifying
    @Query(value = "update role_auth set status = 1 where role_id = :roleId", nativeQuery = true)
    public int deleteRoleAuthBySetStatus(@Param("roleId") Long roleId);
}
