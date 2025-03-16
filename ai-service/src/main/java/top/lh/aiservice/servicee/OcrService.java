package top.lh.aiservice.servicee;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import top.lh.aiservice.util.HttpUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class OcrService {

    private static final String HOST = "https://ocrapi-document.taobao.com";
    private static final String PATH = "/ocrservice/document";
    private static final String METHOD = "POST";
    private static final String APPCODE = "835a29aa70654bfe81a439830939e971";

    public String recognizeDocument(String img, String url, boolean prob) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "APPCODE " + APPCODE);
        headers.put("Content-Type", "application/json; charset=UTF-8");

        Map<String, String> querys = new HashMap<>();

        // 构建请求体
        String bodys;
        if (img != null) {
            bodys = "{\"img\":\"" + img + "\",\"prob\":" + prob + "}";
        } else if (url != null) {
            bodys = "{\"url\":\"" + url + "\",\"prob\":" + prob + "}";
        } else {
            return "错误：必须提供 img 或 url 参数";
        }

        try {
            HttpResponse response = HttpUtils.doPost(HOST, PATH, METHOD, headers, querys, bodys);
            if (response.getStatusLine().getStatusCode() != 200) {
                return "OCR API 错误：" + EntityUtils.toString(response.getEntity());
            }
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
            return "OCR 识别过程中发生错误：" + e.getMessage();
        }
    }
}
