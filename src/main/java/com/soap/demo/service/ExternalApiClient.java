package com.soap.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExternalApiClient {

    private final WebClient webClient;

    public ExternalApiClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://api.mathjs.org/v4/")
                .build();
    }

    public int callExternalAddService(int a, int b) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/")
                        .queryParam("expr", "{expr}")
                        .build(a + "+" + b))
                .retrieve()
                .bodyToMono(String.class)
                .map(Double::parseDouble)
                .map(Double::intValue)
                .block();
    }
}
