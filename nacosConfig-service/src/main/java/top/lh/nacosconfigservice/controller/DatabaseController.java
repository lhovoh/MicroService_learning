package top.lh.nacosconfigservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
public class DatabaseController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/db")
    public String testDatabase() {
        try (Connection connection = dataSource.getConnection()) {
            return "数据库连接成功！";
        } catch (SQLException e) {
            return "数据库连接失败: " + e.getMessage();
        }
    }
}
