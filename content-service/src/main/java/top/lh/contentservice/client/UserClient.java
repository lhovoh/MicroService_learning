package top.lh.contentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.lh.contentservice.page.User;

@FeignClient("user-service")
public interface UserClient {
    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Integer id);
}
