package com.weatherapp.controller;

import com.weatherapp.dto.response.SimplifiedWeatherResponseDTO;
import com.weatherapp.service.WeatherService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/getCurrentWeather")
    public SimplifiedWeatherResponseDTO currentWeather(
            @RequestParam(required = false) @NotBlank(message = "Zip code is required")
            @Size(min = 2, max = 10, message = "Zip code must be 2-10 characters") //Countries have postals starting from 2 characters
            String zip,

            @RequestParam(required = false) @NotBlank(message = "Country is required")
            @Size(min = 2, max = 2, message = "Country must be a 2-letter ISO code")
            @Pattern(regexp = "^[a-zA-Z]{2}$", message = "Invalid country code (e.g., US)") //Country codes are always expected uppercase 2 letter ISO codes
            String country,

            @RequestParam(defaultValue = "imperial")
            @Pattern(regexp = "^(imperial|metric)$", message = "Units must be 'imperial' or 'metric'")  // Restrict to common ones
            String units,

            @RequestParam(defaultValue = "en")
            @Size(min = 2, max = 5, message = "Language code must be 2-5 characters (example 'en','zh_cn')")
            String langCode
            ){

        return weatherService.getCurrentWeather(zip, country.toUpperCase(), units, langCode);
    }
}
