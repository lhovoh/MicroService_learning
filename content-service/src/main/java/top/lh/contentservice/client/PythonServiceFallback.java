package top.lh.contentservice.client;

import org.springframework.stereotype.Component;

@Component
public class PythonServiceFallback implements PythonServiceClient {

    @Override
    public byte[] generateWordCloud(String text) {
        // 返回一个默认的图像或错误信息
        return "Service unavailable".getBytes();
    }

    @Override
    public byte[] generateBarChart(String data) {
        // 返回一个默认的图像或错误信息
        return "Service unavailable".getBytes();
    }
}
