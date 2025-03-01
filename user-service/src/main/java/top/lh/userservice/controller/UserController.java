package top.lh.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lh.userservice.mapper.UserMapper;
import top.lh.userservice.page.User;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id) {
        System.out.println("Node 1");
        return userMapper.selectById(id);
    }

//    @Resource
//    private RestTemplate restTemplate;


//    @GetMapping("/user")
//    public String getUser(@RequestParam String username) {
//        return "User: " + username;
//    }

//    @GetMapping("/user")
//    public String askAI(@RequestParam String question) {
//        // AI问答服务的URL（假设已经部署了AI问答服务）
//        String aiServiceUrl = "http://localhost:8084/api/ai/callWithMessage?question=" + question;
//
//        // 使用RestTemplate调用AI问答服务
//        String response = restTemplate.getForObject(aiServiceUrl,String.class);
//
//        // 返回AI的回答
//        return response;
//    }

}
