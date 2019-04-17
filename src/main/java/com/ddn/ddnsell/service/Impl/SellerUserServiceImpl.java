package com.ddn.ddnsell.service.Impl;

import com.ddn.ddnsell.dao.SellerUserDao;
import com.ddn.ddnsell.dao.UserRoleDao;
import com.ddn.ddnsell.entity.Permission.UserRole;
import com.ddn.ddnsell.entity.SellerUser;
import com.ddn.ddnsell.service.SellerUserService;
import com.ddn.ddnsell.utils.JpaUtil;
import com.ddn.ddnsell.vo.SellerUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author qincx
 * @date 2019/4/11
 * @description
 */
@Service
public class SellerUserServiceImpl implements SellerUserService {

    @Autowired
    private SellerUserDao sellerUserDao;

    @Autowired
    private UserRoleDao userRoleDao;
    @Override
    public SellerUser findOneByName(String name) {
        SellerUser user = sellerUserDao.findSellerUserByNickname(name);
        return user;
    }

    @Override
    public Page<SellerUser> findAllPage(Integer page, Integer size) {
        PageRequest pageRequest = new PageRequest(page, size);
        Page<SellerUser> sellerUserPage = sellerUserDao.findAll(pageRequest);
        return sellerUserPage;
    }

    @Override
    public List<SellerUserVo> findAllVoPage(Integer page, Integer size) {
        List<Object[]> userDaoUserVo = sellerUserDao.findUserVo(page, size);

        try {
            List<SellerUserVo> entityWithConstructor = JpaUtil.createEntityWithConstructor(userDaoUserVo, SellerUserVo.class);
            return entityWithConstructor;
            //List<SellerUserVo> entityWithField = JpaUtil.createEntityWithField(userDaoUserVo, SellerUserVo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public SellerUser findOneById(Long userId) {
        return sellerUserDao.findOne(userId);
    }

    @Override
    public SellerUser save(SellerUser sellerUser) {
        return null;
    }

    @Override
    @Transactional
    public SellerUser update(SellerUser sellerUser, String[] roleIds) {
        SellerUser user = sellerUserDao.findOne(sellerUser.getId());
        if (user == null){
            //TODO: 参数错误查不到用户
        }
        user.setNickname(sellerUser.getNickname());
        user.setHead(sellerUser.getHead());
        user.setMobile(sellerUser.getMobile());
        sellerUserDao.save(user);
       // userRoleDao.deleteUserRoleBySetStatus(user.getId());
       // userRoleDao.deleteUserRoleBySetStatus(user.getId());
        userRoleDao.deleteUserRolesByUserId(user.getId());
        if (roleIds != null){
            List<String> asList = Arrays.asList(roleIds);
            List<UserRole> userRoles = new ArrayList<>();
            for (String roleid: asList
                 ) {
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(Long.parseLong(roleid));
                userRoles.add(userRole);
            }
            userRoleDao.save(userRoles);
        }

        return user;
    }
}
