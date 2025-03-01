package top.lh.requestservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class ConsumerController3 {
    private final String SERVER_URL = "http://localhost:8080";

    private final WebClient webClient = WebClient.builder()
            .baseUrl(SERVER_URL)
            .build();

    @GetMapping("/webClinetTest")
    public String webClinetTest() {
        Mono<String> mono = webClient
                .get()
                .uri("/hello")
                .retrieve()
                .bodyToMono(String.class);
        mono.subscribe(System.out::println);
        return "请求成功";
    }
}
