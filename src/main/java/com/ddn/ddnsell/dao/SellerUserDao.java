package com.ddn.ddnsell.dao;

import com.ddn.ddnsell.entity.Permission.RoleInfo;
import com.ddn.ddnsell.entity.Permission.UserRole;
import com.ddn.ddnsell.entity.SellerUser;
import com.ddn.ddnsell.vo.SellerUserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface SellerUserDao extends JpaRepository<SellerUser, Long> {
    public SellerUser findSellerUserByNickname(String name);

//    @Query(value = "select SU.id id, SU.nickname nickname, SU.head head, SU.register_date registerDate, SU.last_login_date lastLoginDate, SU.login_count loginCount, SU.mobile mobile, RI.role_name roleName" +
//            "from seller_user SU Left join user_role UR on SU.id = UR.user_id Left join role_info RI on UR.role_id = RI.role_id ", nativeQuery = true)
//    public Page<Object[]> findAllVo(Pageable pageable);

//    @Query(value = "select new com.ddn.ddnsell.vo.SellerUserVo(SU.id as id, SU.nickname AS nickname, SU.head AS head, SU.registerDate AS registerDate, SU.lastLoginDate AS lastLoginDate, SU.loginCount AS  loginCount, SU.mobile AS mobile, RI.roleName AS roleName) " +
//            "from SellerUser SU Left join UserRole UR on SU.id = UR.userId Left join RoleInfo RI on UR.roleId = RI.roleId ")
//    public Page<SellerUserVo> findAllVo(Pageable pageable);

//    @Query(value = "select new com.ddn.ddnsell.vo.SellerUserVo(SU.id , SU.nickname , SU.head , SU.registerDate , SU.lastLoginDate, SU.loginCount , SU.mobile , RI.roleName) " +
//            "from SellerUser SU Left join UserRole UR on SU.id = UR.userId Left join RoleInfo RI on UR.roleId = RI.roleId where SU.id = 1")
//    public SellerUserVo findUserVo();

//        @Query(value = "select SU.id id, SU.nickname nickname, SU.head head, SU.register_date registerDate, SU.last_login_date lastLoginDate, SU.login_count loginCount, SU.mobile mobile, RI.role_name roleName" +
//            "from seller_user SU Left join user_role UR on SU.id = UR.user_id Left join role_info RI on UR.role_id = RI.role_id ", nativeQuery = true)
//    public Object[] findUserVo();

//    @Query(value = "select SU.id , SU.nickname , SU.mobile , SU.head , SU.register_date , SU.last_login_date , SU.login_count ,  RI.role_name " +
//            "from seller_user SU Left join user_role UR on SU.id = UR.user_id Left join role_info RI on UR.role_id = RI.role_id ", nativeQuery = true)
//    public List<Object[]> findUserVo();

    @Query(value = "select id, nickname, mobile, head, register_date, last_login_date, login_count, group_concat(role_name) as role_name from " +
            "(select SU.id , SU.nickname , SU.mobile , SU.head , SU.register_date , SU.last_login_date , SU.login_count ,  RI.role_name " +
            "from seller_user SU Left join user_role UR on SU.id = UR.user_id Left join role_info RI on UR.role_id = RI.role_id limit :page, :size)T group by id",
            nativeQuery = true)
    public List<Object[]> findUserVo(@Param("page") Integer page, @Param("size")Integer size);


}
