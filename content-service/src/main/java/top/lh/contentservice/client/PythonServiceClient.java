package top.lh.contentservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "python-service", url = "http://localhost:5000", fallback = PythonServiceFallback.class)
public interface PythonServiceClient {

    @PostMapping(value = "/generate_wordcloud", consumes = "application/json")
    byte[] generateWordCloud(@RequestBody String text);

    @PostMapping(value = "/generate_bar_chart", consumes = "application/json")
    byte[] generateBarChart(@RequestBody String data);
}
