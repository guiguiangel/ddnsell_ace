package com.ddn.ddnsell.shiro;

import com.ddn.ddnsell.entity.Permission.AuthInfo;
import com.ddn.ddnsell.entity.SellerUser;
import com.ddn.ddnsell.service.AuthInfoService;
import com.ddn.ddnsell.service.SellerUserService;
import com.ddn.ddnsell.utils.MD5MsUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author qincx
 * @date 2019/4/11
 * @description
 */

@Component
public class DdnShiroRealm extends AuthorizingRealm {

    @Autowired
    private SellerUserService sellerUserService;

    @Autowired
    private AuthInfoService authInfoService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        SellerUser user = (SellerUser) SecurityUtils.getSubject().getPrincipal();
        if (user.getNickname().equals("admin")){
            List<AuthInfo> authInfoList = authInfoService.findAllList();
            for (AuthInfo authInfo:authInfoList
                 ) {
                info.addStringPermission(authInfo.getAuthCode());
            }
        }
        return info;
    }
    /**
     * Shiro的认证过程最终会交由Realm执行，这时会调用Realm的getAuthenticationInfo(token)方法
     * 该方法主要执行以下操作:
     1、检查提交的进行认证的令牌信息;
     2、根据令牌信息从数据源(通常为数据库)中获取用户信息;
     3、对用户信息进行匹配验证;
     4、验证通过将返回一个封装了用户信息的AuthenticationInfo实例;
     5、验证失败则抛出AuthenticationException异常信息。
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName  = (String) authenticationToken.getPrincipal();
        SellerUser user = sellerUserService.findOneByName(userName);
        if (user == null){
            throw new UnknownAccountException("账号密码不匹配");
        }
        Object credentials = authenticationToken.getCredentials();
        if (credentials == null){
            throw new UnknownAccountException("账号密码不匹配");
        }
        String password = new String((char[]) credentials);
        String passToDBPass = MD5MsUtil.formPassToDBPass(password, user.getSalt());
        if (passToDBPass.equals(user.getPassword()) == false){
            throw new IncorrectCredentialsException("账号密码不匹配");
        }
        // TODO:这里可以设置账号锁定功能 达到多少次输入错误以后

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, user.getNickname());
        return info;
    }
}
