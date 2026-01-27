package com.weatherapp.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import static org.mockito.Mockito.mock;

class OpenWeatherClientTest {

    private RestClient restClient;
    private OpenWeatherClient openWeatherClient;

    @BeforeEach
    void setUp() {
        restClient = mock(RestClient.class);
        openWeatherClient = new OpenWeatherClient(restClient, "https://api.openweathermap.org", "test-api-key");
    }

    @Nested
    @DisplayName("getCoordinatesByZip")
    class GetCoordinatesByZip {

        @Test
        @DisplayName("calls geocoding endpoint")
        void callsGeocodingEndpoint() {
            // TODO
        }

        @Test
        @DisplayName("includes zip parameter in request")
        void includesZipParameter() {
            // TODO
        }

        @Test
        @DisplayName("includes API key in request")
        void includesApiKey() {
            // TODO
        }

        @Test
        @DisplayName("returns GeocodingResponseDTO")
        void returnsGeocodingResponse() {
            // TODO
        }
    }

    @Nested
    @DisplayName("getCurrentWeather")
    class GetCurrentWeather {

        @Test
        @DisplayName("calls weather endpoint")
        void callsWeatherEndpoint() {
            // TODO
        }

        @Test
        @DisplayName("includes lat/lon parameters")
        void includesLatLonParameters() {
            // TODO
        }

        @Test
        @DisplayName("includes API key in request")
        void includesApiKey() {
            // TODO
        }

        @Test
        @DisplayName("uses provided units")
        void usesProvidedUnits() {
            // TODO
        }

        @Test
        @DisplayName("uses default units when blank")
        void usesDefaultUnitsWhenBlank() {
            // TODO
        }

        @Test
        @DisplayName("uses provided language")
        void usesProvidedLanguage() {
            // TODO
        }

        @Test
        @DisplayName("uses default language when blank")
        void usesDefaultLanguageWhenBlank() {
            // TODO
        }

        @Test
        @DisplayName("returns WeatherResponseDTO")
        void returnsWeatherResponse() {
            // TODO
        }
    }
}