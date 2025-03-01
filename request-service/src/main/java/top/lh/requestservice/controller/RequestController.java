package top.lh.requestservice.controller;

import top.lh.requestservice.controller.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.lh.requestservice.openfeign.NodeServiceClient;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RequestController {

    @Autowired
    private NodeServiceClient nodeServiceClient;

    @GetMapping("/node")
    public String getNode(@RequestParam String user) {
        return nodeServiceClient.getNode(user);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return nodeServiceClient.getUsers();
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return nodeServiceClient.createUser(user);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return nodeServiceClient.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        nodeServiceClient.deleteUser(id);
    }
}
