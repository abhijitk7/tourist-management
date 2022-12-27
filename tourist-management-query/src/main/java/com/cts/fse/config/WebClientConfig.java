package com.cts.fse.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean(name = "userWebClient")
    @LoadBalanced
    WebClient getUserWebClient() {
        return WebClient.builder().
                baseUrl("http://localhost:8082/").
                build();
    }
}
