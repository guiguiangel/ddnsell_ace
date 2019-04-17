package com.ddn.ddnsell.service.Impl;

import com.ddn.ddnsell.entity.Permission.RoleInfo;
import com.ddn.ddnsell.service.RoleInfoService;
import com.ddn.ddnsell.vo.RoleVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleInfoServiceImplTest {

    @Autowired
    private RoleInfoService roleInfoService;

    @Test
    public void save() {
        RoleInfo roleInfo = new RoleInfo();
        roleInfo.setRoleName("test");
        roleInfo.setRoleCode("test");
        roleInfo.setRoleDescription("erwrqwrwerw");
        List<String> authIds = new ArrayList<>();
        authIds.add("1");
        authIds.add("2");
        authIds.add("3");
        RoleInfo save = roleInfoService.save(roleInfo, authIds);
        System.out.println(save);
    }

    @Test
    public void findRoleVoListByUserId() {
        List<RoleVo> roleVos = roleInfoService.findRoleVoListByUserId(1l);
        System.out.println(roleVos);
    }
}