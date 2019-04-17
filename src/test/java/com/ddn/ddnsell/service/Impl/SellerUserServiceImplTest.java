package com.ddn.ddnsell.service.Impl;

import com.ddn.ddnsell.service.SellerUserService;
import com.ddn.ddnsell.vo.SellerUserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SellerUserServiceImplTest {

    @Autowired
    private SellerUserService sellerUserService;
    @Test
    public void findAllVoPage() {
        List<SellerUserVo> allVoPage = sellerUserService.findAllVoPage(0, 10);
        System.out.println(allVoPage);
    }
}