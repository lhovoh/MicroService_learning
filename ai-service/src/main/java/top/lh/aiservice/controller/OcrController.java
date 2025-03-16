package top.lh.aiservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lh.aiservice.servicee.OcrService;

import java.util.Map;

@RestController
@RequestMapping("/ocr")
public class OcrController {

    @Autowired
    private OcrService ocrService;

    @PostMapping("/recognize")
    public String recognizeDocument(@RequestBody Map<String, Object> request) {
        String img = (String) request.get("img");
        String url = (String) request.get("url");
        boolean prob = (boolean) request.getOrDefault("prob", false);

        return ocrService.recognizeDocument(img, url, prob);
    }
}
