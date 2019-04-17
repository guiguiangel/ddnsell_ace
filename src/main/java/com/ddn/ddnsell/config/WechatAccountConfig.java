package com.ddn.ddnsell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author qincx
 * @date 2019/3/27
 * @description
 */
@Data
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    private String appid;
    private String appsecret;
    private String redirect_url;
}
