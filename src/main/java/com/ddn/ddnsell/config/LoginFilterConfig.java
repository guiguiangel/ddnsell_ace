package com.ddn.ddnsell.config;

import com.ddn.ddnsell.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author qincx
 * @date 2019/3/28
 * @description
 */
@Configuration
public class LoginFilterConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new LoginFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("loginfilter");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
