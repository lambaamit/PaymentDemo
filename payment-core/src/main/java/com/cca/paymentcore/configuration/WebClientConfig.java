package com.cca.paymentcore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    WebClient getWebClient(WebClient.Builder builder) {
        return builder
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}