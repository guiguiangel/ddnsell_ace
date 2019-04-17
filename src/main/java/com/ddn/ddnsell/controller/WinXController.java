package com.ddn.ddnsell.controller;

import com.ddn.ddnsell.config.WechatAccountConfig;
import com.ddn.ddnsell.entity.OrderDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * @author qincx
 * @date 2019/3/27
 * @description
 */
@Controller
@RequestMapping("/wechat")
public class WinXController {
    @Autowired
    private WechatAccountConfig wechatAccountConfig;
//    https://open.weixin.qq.com/connect/oauth2/authorize?
//    // appid=wx153f204f5aa9dcbe&redirect_uri=http://test.yytop.com.cn/wechat/authorize&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect
    //scope 有两个参数可以传递, snsapi_base、snsapi_userinfo
    @GetMapping("/authorize")
    public void authorize(String code, String state){
        System.out.println(code);
        //获取access_token
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+wechatAccountConfig.getAppid()+"&secret="+wechatAccountConfig.getAppsecret()+"&code="+code+"&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String responseString = restTemplate.getForObject(url, String.class);
        Gson gson = new Gson();

        Map<String,Object> data = gson.fromJson(responseString,
                new TypeToken<Map<String,Object>>(){}.getType());
        System.out.println(data);
    }
}
