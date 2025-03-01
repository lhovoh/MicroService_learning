package top.lh.requestservice.openfeign;

import top.lh.requestservice.controller.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "node-service", url = "http://127.0.0.1:8086")
public interface NodeServiceClient {

    @GetMapping("/node")
    String getNode(@RequestParam("user") String user);

    @GetMapping("/users")
    List<User> getUsers();

    @PostMapping("/users")
    User createUser(@RequestBody User user);

    @PutMapping("/users/{id}")
    User updateUser(@PathVariable("id") Long id, @RequestBody User user);

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable("id") Long id);
}
