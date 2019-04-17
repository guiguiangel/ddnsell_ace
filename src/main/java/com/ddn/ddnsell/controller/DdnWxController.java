package com.ddn.ddnsell.controller;

import com.ddn.ddnsell.config.WechatAccountConfig;
import com.ddn.ddnsell.enums.ResultEnum;
import com.ddn.ddnsell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.apache.http.client.utils.URLEncodedUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author qincx
 * @date 2019/3/27
 * @description
 */
@Controller
@RequestMapping("/weixin")
@Slf4j
public class DdnWxController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WechatAccountConfig wechatAccountConfig;
    @GetMapping("/authorize")
    public String authorize(String returnUrl){
        String redirect_url = wechatAccountConfig.getRedirect_url() + "/weixin/userinfo";
        String encode = null;
        try {
            encode = URLEncoder.encode(returnUrl, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            log.error("【微信授权】参数传递错误 returnUrl:{}", returnUrl);
            throw new SellException(ResultEnum.WX_RETURNURL_ERROR);
        }
        String wxUrl = wxMpService.oauth2buildAuthorizationUrl(redirect_url, WxConsts.OAuth2Scope.SNSAPI_BASE, encode);
        return "redirect:"+wxUrl;
    }

    @GetMapping("/userinfo")
    public String userInfo(String code,String state){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = null;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            if (wxMpOAuth2AccessToken == null){
                log.error("【微信授权】获取openid失败wxMpOAuth2AccessToken: null");
                throw new SellException(ResultEnum.WX_GETOPENID_ERROR);
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
            log.error("【微信授权】获取openid失败 message:{}", e.getMessage());
            throw new SellException(ResultEnum.WX_GETOPENID_ERROR);
        }

       // String url = state + "?openid=" + wxMpOAuth2AccessToken.getOpenId();
        String url = state;
        return "redirect:"+url;
    }
}
