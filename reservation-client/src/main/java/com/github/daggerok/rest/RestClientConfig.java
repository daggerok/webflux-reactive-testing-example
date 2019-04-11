package com.github.daggerok.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RestClientConfig {

  @Bean
  public WebClient webClient(@Value("${reservation-service.base-url:http://127.0.0.1:8080}") String baseUrl) {
    return WebClient.builder().baseUrl(baseUrl).build();
  }
}
