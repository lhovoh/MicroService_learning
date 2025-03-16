package top.lh.contentservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lh.contentservice.client.OcrServiceClient;

import java.util.Map;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private OcrServiceClient ocrServiceClient;

    @PostMapping("/process")
    public String processContent(@RequestBody Map<String, Object> request) {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "处理请求时发生错误";
        }
        // 调用 OCR 服务
        String ocrResult = ocrServiceClient.recognizeDocument(request);

        // 处理 OCR 结果
        return "OCR 识别结果：" + ocrResult;
    }
}
