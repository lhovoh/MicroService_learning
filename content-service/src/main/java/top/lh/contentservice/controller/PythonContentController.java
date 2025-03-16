package top.lh.contentservice.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.lh.contentservice.client.PythonServiceClient;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PythonContentController {

    @Autowired
    private PythonServiceClient pythonServiceClient;

    @PostMapping("/wordcloud")
    @SentinelResource(value = "generateWordCloud", blockHandler = "handleBlock", fallback = "handleFallback")
    public Map<String, String> generateWordCloud(@RequestBody String text) throws InterruptedException {
        // 模拟延时传输
//        Thread.sleep(400);

        // 调用 PythonServiceClient 获取二进制数据
        byte[] imageBytes = pythonServiceClient.generateWordCloud(text);

        // 将二进制数据转换为 Base64 编码的字符串
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        // 返回 Base64 编码的图像
        Map<String, String> response = new HashMap<>();
        response.put("image", base64Image);
        return response;
    }

    @PostMapping("/barchart")
    @SentinelResource(value = "generateBarChart", blockHandler = "handleBlock", fallback = "handleFallback")
    public Map<String, String> generateBarChart(@RequestBody String data) throws InterruptedException {
        // 模拟延时传输（例如 3 秒）
        Thread.sleep(3000);

        // 调用 PythonServiceClient 获取二进制数据
        byte[] imageBytes = pythonServiceClient.generateBarChart(data);

        // 将二进制数据转换为 Base64 编码的字符串
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        // 返回 Base64 编码的图像
        Map<String, String> response = new HashMap<>();
        response.put("image", base64Image);
        return response;
    }

    // 流控或熔断时的处理逻辑
    public Map<String, String> handleBlock(String text, BlockException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Blocked by Sentinel");
        return response;
    }

    // 降级时的处理逻辑
    public Map<String, String> handleFallback(String text, Throwable ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Fallback by Sentinel");
        return response;
    }
}
