package top.lh.requestservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RestTemplateController {
    @GetMapping("/restTemplate")
    public String getBlogRest() {
        // 创建 RestTemplate 实例
        RestTemplate restTemplate = new RestTemplate();

        // 定义接口 URL
        String url = "https://www.wanandroid.com/blog/show/2";

        // 发送 GET 请求
        return restTemplate.getForObject(url, String.class);
    }
}
