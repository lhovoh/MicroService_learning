package top.lh.userservice.controller;

import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class UserController {

    @Resource
    private RestTemplate restTemplate;


//    @GetMapping("/user")
//    public String getUser(@RequestParam String username) {
//        return "User: " + username;
//    }

    @GetMapping("/user")
    public String askAI(@RequestParam String question) {
        // AI问答服务的URL（假设已经部署了AI问答服务）
        String aiServiceUrl = "http://localhost:8084/api/ai/callWithMessage?question=" + question;

        // 使用RestTemplate调用AI问答服务
        String response = restTemplate.getForObject(aiServiceUrl,String.class);

        // 返回AI的回答
        return response;
    }

}
