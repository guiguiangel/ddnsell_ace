package com.ddn.ddnsell.service;

import com.ddn.ddnsell.dao.SellerUserDao;
import com.ddn.ddnsell.entity.SellerUser;
import com.ddn.ddnsell.vo.SellerUserVo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SellerUserService {
    public SellerUser findOneByName(String name);
    public SellerUser findOneById(Long userId);
    public Page<SellerUser> findAllPage(Integer page, Integer size);
    public List<SellerUserVo> findAllVoPage(Integer page, Integer size);

    public SellerUser save(SellerUser sellerUser);
    public SellerUser update(SellerUser sellerUser, String[]  roleIds);
}
