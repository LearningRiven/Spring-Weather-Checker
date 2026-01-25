package com.weatherapp.service;

import com.weatherapp.client.OpenWeatherClient;
import com.weatherapp.dto.request.GeocodingZipRequestDTO;
import com.weatherapp.dto.request.WeatherRequestDTO;
import com.weatherapp.dto.response.GeocodingResponseDTO;
import com.weatherapp.dto.response.SimplifiedWeatherResponseDTO;
import com.weatherapp.dto.response.WeatherResponseDTO;
import org.springframework.stereotype.Service;
import com.weatherapp.utils.DateTimeUtils;

@Service
public class WeatherService {

    private final OpenWeatherClient weatherClient;

    public WeatherService(OpenWeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    public SimplifiedWeatherResponseDTO getCurrentWeather(String zip, String country, String units, String langCode){
        //Fetch the latitude and longitude from weather api
        GeocodingZipRequestDTO zipRequest = new GeocodingZipRequestDTO(zip, country);
        GeocodingResponseDTO geocodingResponse = weatherClient.getCoordinatesByZip(zipRequest);
        WeatherRequestDTO weatherRequest = new WeatherRequestDTO(geocodingResponse.lat(), geocodingResponse.lon(), units, langCode);
        WeatherResponseDTO currentWeather = weatherClient.getCurrentWeather(weatherRequest);

        String localTime = DateTimeUtils.toLocalDateTimeString(currentWeather.dt(), currentWeather.timezone());

        return SimplifiedWeatherResponseDTO.builder()
                .locationName(geocodingResponse.name())
                .temperature(currentWeather.main().temp())  // Record accessor style
                .weatherConditions(currentWeather.weather())  // Assuming weather is a List<Weather>
                .humidity(currentWeather.main().humidity())
                .windSpeed(currentWeather.wind().speed())
                .units(units)
                .currentTime(localTime)
                .build();
    }

}
