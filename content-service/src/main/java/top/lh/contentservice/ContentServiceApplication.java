package top.lh.contentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import top.lh.contentservice.handler.SentineConfig;

@SpringBootApplication
@EnableFeignClients
@Import({SentineConfig.class})
public class ContentServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(ContentServiceApplication.class, args);
    }

}
