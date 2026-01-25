package com.weatherapp.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RestClientConfigTest {

    @Autowired
    private RestClient restClient;

    @Autowired
    @Qualifier("openWeatherRestClient")
    private RestClient openWeatherRestClient;

    @Test
    @DisplayName("primary RestClient bean is created")
    void primaryRestClientBean_isCreated() {
        assertThat(restClient).isNotNull();
    }

    @Test
    @DisplayName("openWeatherRestClient bean is created")
    void openWeatherRestClientBean_isCreated() {
        assertThat(openWeatherRestClient).isNotNull();
    }

    @Test
    @DisplayName("primary and openWeather RestClients are different instances")
    void restClients_areDifferentInstances() {
        assertThat(restClient).isNotSameAs(openWeatherRestClient);
    }
}