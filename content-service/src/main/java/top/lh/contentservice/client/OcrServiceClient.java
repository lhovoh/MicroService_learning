package top.lh.contentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "ocr-service", url = "http://localhost:8084", fallback = OcrServiceFallback.class) // 指定服务名称和 URL
public interface OcrServiceClient {

    @PostMapping("/ocr/recognize")
    String recognizeDocument(@RequestBody Map<String, Object> request);
}
