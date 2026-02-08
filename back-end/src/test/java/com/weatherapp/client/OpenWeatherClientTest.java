package com.weatherapp.client;

import com.weatherapp.dto.request.GeocodingZipRequestDTO;
import com.weatherapp.dto.request.WeatherRequestDTO;
import com.weatherapp.dto.response.GeocodingResponseDTO;
import com.weatherapp.dto.response.WeatherResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import java.io.IOException;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class OpenWeatherClientTest {
    private MockRestServiceServer server;
    private OpenWeatherClient openWeatherClient;
    @BeforeEach
    void setUp() {
        RestClient.Builder builder = RestClient.builder();
        server = MockRestServiceServer.bindTo(builder).build();
        openWeatherClient = new OpenWeatherClient("https://api.openweathermap.org", "test-api-key", builder);
    }

    @Nested
    @DisplayName("getCoordinatesByZip")
    class GetCoordinatesByZip {

        @Test
        @DisplayName("calls geocoding endpoint")
        void callsGeocodingEndpoint() throws IOException {
            GeocodingZipRequestDTO requestDTO = new GeocodingZipRequestDTO("78727", "US");
            String responseJSON = loadJson("zipResponse.json");

            server.expect(requestTo(startsWith("https://api.openweathermap.org/geo/1.0/zip")))
                    .andExpect(method(HttpMethod.GET))
                    .andExpect(queryParam("zip", "78727,US"))
                    .andExpect(queryParam("appid", "test-api-key"))
                    .andRespond(withSuccess(responseJSON, MediaType.APPLICATION_JSON));


            GeocodingResponseDTO responseDTO = openWeatherClient.getCoordinatesByZip(requestDTO);

            server.verify();

            assertEquals("78727",responseDTO.zip());
            assertEquals("Austin",responseDTO.name());
            assertEquals(30.4254,responseDTO.lat());
            assertEquals(-97.7195,responseDTO.lon());
            assertEquals("US",responseDTO.country());
        }
    }

    @Nested
    @DisplayName("getCurrentWeather")
    class GetCurrentWeather {

        @Test
        @DisplayName("Test using the metric values response")
        void callsWeatherEndpointMetric() throws IOException {
            //Request
            WeatherRequestDTO requestDTO = new WeatherRequestDTO(30.4254,-97.7195,"metric","en");
            String responseJSON = loadJson("weatherResponseMetric.json");

            server.expect(requestTo(startsWith("https://api.openweathermap.org/data/2.5/weather")))
                    .andExpect(method(HttpMethod.GET))
                    .andExpect(queryParam("lat", "30.4254"))
                    .andExpect(queryParam("lon", "-97.7195"))
                    .andExpect(queryParam("units", "metric"))
                    .andExpect(queryParam("lang", "en"))
                    .andExpect(queryParam("appid", "test-api-key"))
                    .andRespond(withSuccess(responseJSON, MediaType.APPLICATION_JSON));

            WeatherResponseDTO responseDTO = openWeatherClient.getCurrentWeather(requestDTO);

            server.verify();

            //Coord
            assertEquals(-97.7195,responseDTO.coord().lon());
            assertEquals(30.4254,responseDTO.coord().lat());

            //WeatherDTO
            assertEquals(800,responseDTO.weather().get(0).conditionId());
            assertEquals("Clear",responseDTO.weather().get(0).currentCondition());
            assertEquals("clear sky",responseDTO.weather().get(0).description());
            assertEquals("01d",responseDTO.weather().get(0).icon());

            //MainDTO
            assertEquals(27.08,responseDTO.main().temp());
            assertEquals(26.29,responseDTO.main().feelsLike());
            assertEquals(26.17,responseDTO.main().tempMin());
            assertEquals(28.19,responseDTO.main().tempMax());
            assertEquals(1020,responseDTO.main().pressure());
            assertEquals(25,responseDTO.main().humidity());
            assertEquals(1020,responseDTO.main().seaLevel());
            assertEquals(992,responseDTO.main().groundLevel());

            //WindDTO
            assertEquals(4.12,responseDTO.wind().speed());
            assertEquals(150,responseDTO.wind().deg());
            assertNull(responseDTO.wind().gust());

            //CloudsDTO
            assertEquals(0,responseDTO.clouds().all());

            //SysDTO
            assertEquals(2,responseDTO.sys().type());
            assertEquals(2108492,responseDTO.sys().sysId());
            assertEquals("US",responseDTO.sys().country());
            assertEquals(1770470271L,responseDTO.sys().sunrise());
            assertEquals(1770509535L,responseDTO.sys().sunset());

            //WeatherResponseDTO
            assertEquals("stations",responseDTO.base());
            assertEquals(10000,responseDTO.visibility());
            assertEquals(1770501220L,responseDTO.dt());
            assertEquals(-21600,responseDTO.timezone());
            assertEquals(4740584,responseDTO.responseId());
            assertEquals("Wells Branch",responseDTO.name());
            assertEquals(200,responseDTO.cod());
        }

        @Test
        @DisplayName("Test using the imperial values response")
        void callsWeatherEndpointImperial() throws IOException {
            //Request
            WeatherRequestDTO requestDTO = new WeatherRequestDTO(30.4254,-97.7195,"imperial","en");
            String responseJSON = loadJson("weatherResponseImperial.json");

            server.expect(requestTo(startsWith("https://api.openweathermap.org/data/2.5/weather")))
                    .andExpect(method(HttpMethod.GET))
                    .andExpect(queryParam("lat", "30.4254"))
                    .andExpect(queryParam("lon", "-97.7195"))
                    .andExpect(queryParam("units", "imperial"))
                    .andExpect(queryParam("lang", "en"))
                    .andExpect(queryParam("appid", "test-api-key"))
                    .andRespond(withSuccess(responseJSON, MediaType.APPLICATION_JSON));

            WeatherResponseDTO responseDTO = openWeatherClient.getCurrentWeather(requestDTO);

            server.verify();

            //Coord
            assertEquals(-97.7195,responseDTO.coord().lon());
            assertEquals(30.4254,responseDTO.coord().lat());

            //WeatherDTO
            assertEquals(800,responseDTO.weather().get(0).conditionId());
            assertEquals("Clear",responseDTO.weather().get(0).currentCondition());
            assertEquals("clear sky",responseDTO.weather().get(0).description());
            assertEquals("01d",responseDTO.weather().get(0).icon());

            //MainDTO
            assertEquals(80.76,responseDTO.main().temp());
            assertEquals(79.32,responseDTO.main().feelsLike());
            assertEquals(79.11,responseDTO.main().tempMin());
            assertEquals(82.74,responseDTO.main().tempMax());
            assertEquals(1020,responseDTO.main().pressure());
            assertEquals(25,responseDTO.main().humidity());
            assertEquals(1020,responseDTO.main().seaLevel());
            assertEquals(992,responseDTO.main().groundLevel());

            //WindDTO
            assertEquals(9.22,responseDTO.wind().speed());
            assertEquals(150,responseDTO.wind().deg());
            assertNull(responseDTO.wind().gust());

            //CloudsDTO
            assertEquals(0,responseDTO.clouds().all());

            //SysDTO
            assertEquals(2,responseDTO.sys().type());
            assertEquals(2108492,responseDTO.sys().sysId());
            assertEquals("US",responseDTO.sys().country());
            assertEquals(1770470271L,responseDTO.sys().sunrise());
            assertEquals(1770509535L,responseDTO.sys().sunset());

            //WeatherResponseDTO
            assertEquals("stations",responseDTO.base());
            assertEquals(10000,responseDTO.visibility());
            assertEquals(1770501204L,responseDTO.dt());
            assertEquals(-21600,responseDTO.timezone());
            assertEquals(4740584,responseDTO.responseId());
            assertEquals("Wells Branch",responseDTO.name());
            assertEquals(200,responseDTO.cod());
        }
    }

    private String loadJson(String filename) throws IOException {
        var resource = getClass().getClassLoader().getResourceAsStream(filename);
        if (resource == null) {
            throw new IllegalStateException("Test resource not found: " + filename);
        }
        return new String(resource.readAllBytes());
    }
}