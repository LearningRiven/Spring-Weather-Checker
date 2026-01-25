package com.weatherapp.dto.response;

public record GeocodingResponseDTO (
        String zip,
        String name,
        Double lat,
        Double lon,
        String country
){}