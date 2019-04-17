package com.ddn.ddnsell.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * @author qincx
 * @date 2019/3/29
 * @description
 */
@Aspect
@Configuration
public class SellerAuthorizeAspect {

//    校验
    @Pointcut("execution(public * com.ddn.ddnsell.controller.Sell*.*(..))"+
            "&& !execution(public * com.ddn.ddnsell.controller.SellUserController.*(..))"
    )
    public void verify(){
    }

    @Before("verify()")
    public void doVerify(){

        //TODO 这里面实现在切点前的调用
        System.out.println("start doverify()");
    }
}
