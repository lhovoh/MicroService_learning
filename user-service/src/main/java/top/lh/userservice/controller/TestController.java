package top.lh.userservice.controller;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lh.userservice.config.TestConfig;

@RefreshScope
@RestController
//@RefreshScope
public class TestController {
//    @Value("${lh.username}")
//    private String username;
//
//    @Value("${lh.job}")
//    private String job;
    @Resource
    private TestConfig testConfig;

    @GetMapping("/test")
    public String get() {
        return "读取到的配置值：" + testConfig.getUsername() + "，" + testConfig.getJob();
    }
}
