package top.lh.nacosconfigservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import top.lh.nacosconfigservice.config.DeepSeekConfig;

@SpringBootApplication
@EnableConfigurationProperties(DeepSeekConfig.class)
public class NacosConfigServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigServiceApplication.class, args);
    }

}
