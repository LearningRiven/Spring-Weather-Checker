package com.weatherapp.client;

import com.weatherapp.dto.request.GeocodingZipRequestDTO;
import com.weatherapp.dto.request.WeatherRequestDTO;
import com.weatherapp.dto.response.GeocodingResponseDTO;
import com.weatherapp.dto.response.WeatherResponseDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import org.apache.commons.lang3.StringUtils;

@Component
public class OpenWeatherClient {

    private final RestClient restClient;
    private final String baseURL = "http://api.openweathermap.org";
    private final String apiKey;

    public OpenWeatherClient(@Qualifier("openWeatherRestClient") RestClient restClient, @Value("${openweather.api.key}") String apiKey){
        this.restClient = restClient;
        this.apiKey = apiKey;
    }

    public GeocodingResponseDTO getCoordinatesByZip(GeocodingZipRequestDTO dto){
        //Default path for getting the geo code by zipcode
        String path = "/geo/1.0/zip";

        URI uri = UriComponentsBuilder.fromUriString(baseURL)
                .path(path)
                .queryParam("zip",dto.toZipParam())
                .queryParam("appid", apiKey)
                .encode()
                .build()
                .toUri();

        return restClient.get()
                .uri(uri)
                .retrieve()
                .body(GeocodingResponseDTO.class);
    }

    public WeatherResponseDTO getCurrentWeather(WeatherRequestDTO dto){
        //Default path for getting the weather
        String path = "/data/2.5/weather";

        //Defaults if none supplied
        String wUnits = !StringUtils.isBlank(dto.getUnits()) ? dto.getUnits() : "imperial";
        String wLang = !StringUtils.isBlank(dto.getLanguage()) ? dto.getLanguage() : "en";

        URI uri = UriComponentsBuilder.fromUriString(baseURL)
                .path(path)
                .queryParam("lat",dto.getLatitude())
                .queryParam("lon",dto.getLongitude())
                .queryParam("units",wUnits)
                .queryParam("lang",wLang)
                .queryParam("appid", apiKey)
                .encode()
                .build()
                .toUri();

        return restClient.get()
                .uri(uri)
                .retrieve()
                .body(WeatherResponseDTO.class);
    }
}
