package top.lh.nacosconfigservice.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import top.lh.nacosconfigservice.config.DeepSeekConfig;
import top.lh.nacosconfigservice.config.OssConfig;

import javax.sql.DataSource;
import java.io.ByteArrayInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private OssConfig ossConfig;

    @Autowired
    private DeepSeekConfig deepSeekConfig;

    @GetMapping("/db")
    public String testDatabase() {
        try (Connection connection = dataSource.getConnection()) {
            return "数据库连接成功！";
        } catch (SQLException e) {
            return "数据库连接失败:" + e.getMessage();
        }
    }

    @GetMapping("/redis")
    public String testRedis() {
        String key = "testKey";
        String value = "testValue";
        redisTemplate.opsForValue().set(key, value);
        String result = redisTemplate.opsForValue().get(key);
        return result != null ? "Redis连接成功！" : "Redis连接失败";
    }

    @GetMapping("/oss")
    public String testOss() {
        if (ossConfig.getAccessKeyId() == null || ossConfig.getAccessKeyId().isEmpty()) {
            return "访问密钥ID不应为空.";
        }
        OSS ossClient = new OSSClientBuilder().build(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
        ossClient.putObject(ossConfig.getBucketName(), "test.txt", new ByteArrayInputStream("Test file".getBytes()));
        ossClient.shutdown();
        return "OSS上传成功！";
    }

    @GetMapping("/deepseek")
    public String testDeepSeek() {
        if (deepSeekConfig == null || deepSeekConfig.getApiKey() == null) {
            return "DeepSeek配置或API Key未设置！";
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + deepSeekConfig.getApiKey());

        HttpEntity<String> request = new HttpEntity<>(headers);
        try {
            // 增加请求间隔为 10 秒
            Thread.sleep(10000);
            ResponseEntity<String> response = restTemplate.exchange(
                    new URI(deepSeekConfig.getApiUrl()),
                    HttpMethod.GET,
                    request,
                    String.class
            );
            return response.getStatusCode().is2xxSuccessful() ? "DeepSeek API调用成功！" : "DeepSeek API调用失败";
        } catch (HttpClientErrorException.TooManyRequests e) {
            return "请求过于频繁，请稍后再试！";
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return "DeepSeek API URL无效！";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "请求延迟失败！";
        }
    }
}
