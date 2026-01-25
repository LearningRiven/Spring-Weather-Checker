package com.weatherapp.dto.response.shared;

public record WindDTO(
        Double speed,
        Integer deg,
        Double gust
){}
