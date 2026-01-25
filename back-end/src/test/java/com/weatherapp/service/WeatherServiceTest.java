package com.weatherapp.service;

import com.weatherapp.client.OpenWeatherClient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock
    private OpenWeatherClient weatherClient;

    @InjectMocks
    private WeatherService weatherService;

    @Test
    @DisplayName("getCurrentWeather returns simplified weather response")
    void getCurrentWeather_returnsSimplifiedResponse() {
        // TODO
    }

    @Test
    @DisplayName("getCurrentWeather calls geocoding client with correct parameters")
    void getCurrentWeather_callsGeocodingWithCorrectParams() {
        // TODO
    }

    @Test
    @DisplayName("getCurrentWeather calls weather client with coordinates from geocoding")
    void getCurrentWeather_callsWeatherWithCoordinates() {
        // TODO
    }

    @Test
    @DisplayName("getCurrentWeather passes units to weather client")
    void getCurrentWeather_passesUnits() {
        // TODO
    }

    @Test
    @DisplayName("getCurrentWeather passes langCode to weather client")
    void getCurrentWeather_passesLangCode() {
        // TODO
    }

    @Test
    @DisplayName("getCurrentWeather maps location name from geocoding response")
    void getCurrentWeather_mapsLocationName() {
        // TODO
    }

    @Test
    @DisplayName("getCurrentWeather formats current time correctly")
    void getCurrentWeather_formatsCurrentTime() {
        // TODO
    }
}