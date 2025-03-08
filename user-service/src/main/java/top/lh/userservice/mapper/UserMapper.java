package top.lh.userservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.lh.userservice.config.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
