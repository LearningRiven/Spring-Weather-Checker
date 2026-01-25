package com.weatherapp.dto.response.shared;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherConditionDTO(
        @JsonProperty("id") Integer conditionId,
        @JsonProperty("main") String currentCondition,
        String description,
        String icon
){}