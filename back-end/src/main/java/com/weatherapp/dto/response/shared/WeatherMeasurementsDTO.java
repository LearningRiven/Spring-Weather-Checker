package com.weatherapp.dto.response.shared;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherMeasurementsDTO(
        double temp,
        @JsonProperty("feels_like") double feelsLike,
        @JsonProperty("temp_min") double tempMin,
        @JsonProperty("temp_max") double tempMax,
        int pressure,
        int humidity,
        @JsonProperty("sea_level") int seaLevel,
        @JsonProperty("grnd_level") int groundLevel
) {}
