package com.weatherapp.client;

import com.weatherapp.dto.request.GeocodingZipRequestDTO;
import com.weatherapp.dto.request.WeatherRequestDTO;
import com.weatherapp.dto.response.GeocodingResponseDTO;
import com.weatherapp.dto.response.WeatherResponseDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class OpenWeatherClient {

    private final RestClient restClient;
    private final String apiKey;


    public OpenWeatherClient(
            @Value("${openweather.api.base-url:https://api.openweathermap.org}") String baseUrl,
            @Value("${openweather.api.key:}") String apiKey) {
        this.apiKey = apiKey;
        this.restClient = RestClient.builder().baseUrl(baseUrl).build();
    }

    public GeocodingResponseDTO getCoordinatesByZip(GeocodingZipRequestDTO dto){
        //Default path for getting the geo code by zipcode
        String path = "/geo/1.0/zip";

        return restClient.get()
                .uri(path, uriBuilder -> uriBuilder
                        .queryParam("zip",dto.toZipParam())
                        .queryParam("appid", apiKey)
                        .build()
                )
                .retrieve()
                .body(GeocodingResponseDTO.class);
    }

    public WeatherResponseDTO getCurrentWeather(WeatherRequestDTO dto){
        //Default path for getting the weather
        String path = "/data/2.5/weather";

        return restClient.get()
                .uri(path, uriBuilder -> uriBuilder
                        .queryParam("lat",dto.getLatitude())
                        .queryParam("lon", dto.getLongitude())
                        .queryParam("units", dto.getUnits())
                        .queryParam("lang", dto.getLanguage())
                        .queryParam("appid", apiKey)
                        .build()
                )
                .retrieve()
                .body(WeatherResponseDTO.class);
    }
}
