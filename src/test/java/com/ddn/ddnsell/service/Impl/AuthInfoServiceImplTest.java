package com.ddn.ddnsell.service.Impl;

import com.ddn.ddnsell.entity.Permission.AuthInfo;
import com.ddn.ddnsell.service.AuthInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthInfoServiceImplTest {

    @Autowired
    private AuthInfoService authInfoService;
    @Test
    public void save() {
        AuthInfo authInfo = new AuthInfo();
        authInfo.setAuthName("权限管理");
        authInfo.setAuthCode("auth_manager");
        authInfo.setAuthDescription("负责对权限管理的权限");
        authInfo.setAuthPage("/auth");
        authInfo.setAuthIsmenu(1);
        authInfo.setAuthPid(0L);
        authInfo.setAuthStatus(0);
        AuthInfo save = authInfoService.save(authInfo);
        System.out.println(save);
    }

    @Test
    public void findOne() {
        authInfoService.findOne(1L);
        System.out.println("ee");
    }

    @Test
    public void findAuthTreeVoListByRoleId() {
        authInfoService.findAuthTreeVoListByRoleId(1L);
    }
}