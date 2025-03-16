package top.lh.aiservice.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.net.URI;
import java.util.Map;

public class HttpUtils {
    public static HttpResponse doPost(String host, String path, String method, Map<String, String> headers, Map<String, String> querys, String bodys) throws Exception {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(host + path);

        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }

        if (querys != null) {
            String queryString = buildQueryString(querys);
            httpPost.setURI(new URI(host + path + "?" + queryString));
        }

        if (bodys != null) {
            StringEntity entity = new StringEntity(bodys, "UTF-8");
            httpPost.setEntity(entity);
        }

        return httpClient.execute(httpPost);
    }

    private static String buildQueryString(Map<String, String> querys) {
        StringBuilder queryString = new StringBuilder();
        for (Map.Entry<String, String> entry : querys.entrySet()) {
            if (queryString.length() > 0) {
                queryString.append("&");
            }
            queryString.append(entry.getKey()).append("=").append(entry.getValue());
        }
        return queryString.toString();
    }
}
