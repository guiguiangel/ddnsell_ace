package com.ddn.ddnsell.dao;

import com.ddn.ddnsell.entity.Permission.RoleInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleInfoDao extends JpaRepository<RoleInfo, Long> {
   // public Page<RoleInfo> fi(Pageable pageable);

   // @Query(value = "select R.role_id roleId, R.role_code roleCode, R.role_name roleName, R.role_description roleDescription from " +
//   @Query(value = "select R.role_id , R.role_code , R.role_name , R.role_description  from " +
////            "role_info R Left join user_role UR on R.role_id = UR.role_id left join seller_user SU on UR.user_id = SU.id where SU.id = 1?",
////    nativeQuery = true)
////    public List<RoleInfo> findListByUserId(Long id);

    @Query(value = "select R.role_id , R.role_code , R.role_name , R.role_description  from " +
            "role_info R Left join user_role UR on R.role_id = UR.role_id left join seller_user SU on UR.user_id = SU.id where SU.id = ?1",
            nativeQuery = true)
    public List<RoleInfo> findListByUserId(Long id);
}
