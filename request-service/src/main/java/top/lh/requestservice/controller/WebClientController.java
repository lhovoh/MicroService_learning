package top.lh.requestservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class WebClientController {
    private final WebClient webClient = WebClient.builder().baseUrl("https://www.wanandroid.com").build();

    @GetMapping("/WebClient")
    public Mono<String> getBlogWebClient() {
        // 定义接口 URL 和参数
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/article/list/0/json")
                        .queryParam("cid", 60)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

}
