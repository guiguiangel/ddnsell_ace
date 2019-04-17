package com.ddn.ddnsell.dao;

import com.ddn.ddnsell.entity.Permission.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRoleDao extends JpaRepository<UserRole,Long> {

    @Modifying
    @Query(value = "update user_role set status = 1 where user_id = :userId", nativeQuery = true)
    public int deleteUserRoleBySetStatus(@Param("userId") Long userId);

    public int deleteUserRolesByUserId(Long userId);
}
