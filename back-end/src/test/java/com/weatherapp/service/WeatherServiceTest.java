package com.weatherapp.service;

import com.weatherapp.client.OpenWeatherClient;
import com.weatherapp.dto.request.GeocodingZipRequestDTO;
import com.weatherapp.dto.request.WeatherRequestDTO;
import com.weatherapp.dto.response.GeocodingResponseDTO;
import com.weatherapp.dto.response.SimplifiedWeatherResponseDTO;
import com.weatherapp.dto.response.WeatherResponseDTO;
import com.weatherapp.dto.response.shared.CloudsDTO;
import com.weatherapp.dto.response.shared.CoordDTO;
import com.weatherapp.dto.response.shared.WeatherConditionDTO;
import com.weatherapp.dto.response.shared.WeatherMeasurementsDTO;
import com.weatherapp.dto.response.shared.WindDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock
    private OpenWeatherClient weatherClient;

    @InjectMocks
    private WeatherService weatherService;

    @Test
    @DisplayName("getCurrentWeather returns simplified weather response")
    void test_getCurrentWeather_returnsSimplifiedResponse() {
        GeocodingZipRequestDTO zipRequestDTO = new GeocodingZipRequestDTO("78727", "US");
        GeocodingResponseDTO geoResponseDTO = new GeocodingResponseDTO("78727", "Austin", 30.4254, -97.7195, "US");
        WeatherRequestDTO weatherRequest = new WeatherRequestDTO(30.4254,-97.7195,"imperial","en");
        WeatherResponseDTO weatherResponseDTO = new WeatherResponseDTO(
                new CoordDTO(-97.7195, 30.4254),
                List.of(new WeatherConditionDTO(800, "Clear", "clear sky", "01d")),
                "stations",
                new WeatherMeasurementsDTO(80.76, 79.32, 79.11, 82.74, 1020, 25, 1020, 992),
                10000,
                new WindDTO(9.22, 150, null),
                new CloudsDTO(0),
                1770501204L,
                new WeatherResponseDTO.Sys(2, 2108492, "US", 1770470271L, 1770509535L),
                -21600,
                4740584,
                "Wells Branch",
                200
        );

        when(weatherClient.getCoordinatesByZip(zipRequestDTO)).thenReturn(geoResponseDTO);
        when(weatherClient.getCurrentWeather(weatherRequest)).thenReturn(weatherResponseDTO);

        SimplifiedWeatherResponseDTO finalResponse = weatherService.getCurrentWeather("78727", "US", "imperial", "en");

        assertEquals("Austin", finalResponse.locationName());
        assertEquals(80.76, finalResponse.temperature());
        assertEquals("Clear", finalResponse.weatherConditions().get(0).currentCondition());
        assertEquals("clear sky", finalResponse.weatherConditions().get(0).description());
        assertEquals("01d", finalResponse.weatherConditions().get(0).icon());
        assertEquals(25, finalResponse.humidity());
        assertEquals(9.22, finalResponse.windSpeed());
        assertEquals("imperial", finalResponse.units());
        assertEquals("February 7, 2026 3:53 PM", finalResponse.currentTime());
    }
}