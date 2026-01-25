package com.weatherapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    @Primary
    public RestClient restClient(){
        return RestClient.builder().build();
    }

    @Bean
    public RestClient openWeatherRestClient(){
        return RestClient.builder().build();
    }
}
