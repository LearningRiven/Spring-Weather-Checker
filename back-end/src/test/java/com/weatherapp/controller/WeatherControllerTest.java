package com.weatherapp.controller;

import com.weatherapp.service.WeatherService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WeatherService weatherService;

    @Nested
    @DisplayName("GET /getCurrentWeather")
    class GetCurrentWeather {

        @Test
        @DisplayName("returns weather for valid request")
        void validRequest_returnsWeather() throws Exception {
            // TODO
        }

        @Test
        @DisplayName("returns 400 when zip is missing")
        void missingZip_returnsBadRequest() throws Exception {
            // TODO
        }

        @Test
        @DisplayName("returns 400 when zip is blank")
        void blankZip_returnsBadRequest() throws Exception {
            // TODO
        }

        @Test
        @DisplayName("returns 400 when country is missing")
        void missingCountry_returnsBadRequest() throws Exception {
            // TODO
        }

        @Test
        @DisplayName("returns 400 when country format is invalid")
        void invalidCountryFormat_returnsBadRequest() throws Exception {
            // TODO
        }

        @Test
        @DisplayName("accepts lowercase country and converts to uppercase")
        void lowercaseCountry_convertsToUppercase() throws Exception {
            // TODO
        }

        @Test
        @DisplayName("uses default units when not provided")
        void missingUnits_usesDefault() throws Exception {
            // TODO
        }

        @Test
        @DisplayName("uses default langCode when not provided")
        void missingLangCode_usesDefault() throws Exception {
            // TODO
        }

        @Test
        @DisplayName("returns 400 when units is invalid")
        void invalidUnits_returnsBadRequest() throws Exception {
            // TODO
        }
    }
}