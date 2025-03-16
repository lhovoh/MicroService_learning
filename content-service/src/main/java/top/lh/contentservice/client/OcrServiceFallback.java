package top.lh.contentservice.client;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OcrServiceFallback implements OcrServiceClient {

    @Override
    public String recognizeDocument(Map<String, Object> request) {
        return "OCR 服务不可用，请稍后重试";
        // 熔断降级后的默认返回值
    }
}
