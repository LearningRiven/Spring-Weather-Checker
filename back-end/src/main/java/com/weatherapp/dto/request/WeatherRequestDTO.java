package com.weatherapp.dto.request;

public record WeatherRequestDTO (
        double latitude,
        double longitude,
        String units,
        String language
){}
