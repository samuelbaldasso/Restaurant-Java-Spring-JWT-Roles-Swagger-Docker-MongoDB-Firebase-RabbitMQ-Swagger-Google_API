package com.sbaldass.combo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GoogleGeolocationService {

    private final WebClient webClient;

    @Value("google.api.key")
    private String apiKey;

    @Autowired
    public GoogleGeolocationService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> getAccurateLocation(String requestBody) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("key", apiKey)
                        .build())
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class);
    }
}