package top.lh.contentservice.client;

import com.alibaba.nacos.api.model.v2.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.lh.contentservice.page.User;

@FeignClient("user-service")
public interface UserClient {
    @GetMapping("/user/{id}")
    Result<User> getUserById(@PathVariable Integer id);
}
