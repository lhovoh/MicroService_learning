package top.lh.userservice.controller;

import com.alibaba.nacos.api.model.v2.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.lh.userservice.mapper.UserMapper;
import top.lh.userservice.config.User;

@RefreshScope
@RestController
@RequestMapping("/user")
public class UserController {
//    @Value("${lh.serviceFlag}")
//    private boolean serviceFlag;
//
    @Autowired
    private UserMapper userMapper;

//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getUser(@PathVariable Integer id) {
//        if (serviceFlag) {
//            User user = userMapper.selectById(id);
//            return ResponseEntity.ok(user);
//        } else {
//            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
//                    .body("用户服务正在维护中，请稍后。。。");
//        }
//    }

    @GetMapping("/{id}")
    public Result<User> getUser(@PathVariable Integer id) {
        User user = userMapper.selectById(id);
        return Result.success(user);
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
