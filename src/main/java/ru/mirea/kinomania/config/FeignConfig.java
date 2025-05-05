package ru.mirea.kinomania.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;

@Configuration
@EnableFeignClients(basePackages = "ru.mirea.kinomania.client")
public class FeignConfig {
    
    @Value("${kinopoisk.api.key}")
    private String apiKey;
    
    @Bean
    public RequestInterceptor kinopoiskApiKeyInterceptor() {
        return requestTemplate -> 
            requestTemplate.header("X-API-KEY", apiKey);
    }

}