package top.lh.contentservice.handler;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.lh.contentservice.client.OcrServiceClient;
import top.lh.contentservice.client.OcrServiceFallback;
import top.lh.contentservice.client.PythonServiceClient;
import top.lh.contentservice.client.PythonServiceFallback;

@Configuration
public class SentineConfig {
    @Bean
    public BlockExceptionHandler sentinelHandler() {
        return new SentineExceptionHandler();
    }

    @Bean
    public RequestOriginParser requestOriginParser() {
        return new SentineRequestOriginParser();
    }

    @Bean
    public OcrServiceClient ocrServiceClient() {
        return new OcrServiceFallback();
    }

    @Bean
    public PythonServiceClient pythonServiceClient() {
        return new PythonServiceFallback();
    }
}
