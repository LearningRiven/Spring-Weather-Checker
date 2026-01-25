package com.weatherapp.dto.response.shared;

public record WindDTO(
        double speed,
        int deg,
        double gust
){}
