package top.lh.contentservice.client;

import com.alibaba.nacos.api.model.v2.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import top.lh.contentservice.page.User;

@Slf4j
@Component
public class UserServiceFallBackFactory implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable cause) {
        log.error("调用用户服务异常:", cause);

        return new UserClient() {
            @Override
            public Result<User> getUserById(Integer id) {
                User user = new User();
                user.setId(id);
                user.setUserName("默认用户");
                return Result.success(user);
            }
        };
    }
}
