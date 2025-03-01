package top.lh.requestservice.controller;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ConsumerController {
    private final String SERVICE_URL = "http://localhost:8080";

    @GetMapping("/httClientTest")
    public String httClientTest() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建httpGet远程连接
        HttpGet httpGet = new HttpGet(SERVICE_URL + "/hello");
        CloseableHttpResponse response = null;
        // 执行get请求
        response = httpClient.execute(httpGet);
        // 判断返回状态是否为200
        if (response.getStatusLine().getStatusCode() == 200) {
            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
            System.out.println(result);
        }
        return "请求成功";
    }
}
