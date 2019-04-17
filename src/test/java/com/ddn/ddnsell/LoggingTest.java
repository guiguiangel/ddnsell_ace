package com.ddn.ddnsell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author qincx
 * @date 2019/3/26
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggingTest {
    @Test
    public void test(){
        String name = "imooc";
        String password = "123456";
        log.debug("debug...");
        log.info("name: " + name + " ,password: " + password);
        log.info("name: {}, password: {}", name, password);
        log.error("error...");
        log.warn("warn...");
    }
}
