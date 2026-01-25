package com.weatherapp.dto.response;

public record GeocodingResponseDTO (
        String zip,
        String name,
        double lat,
        double lon,
        String country
){}