package com.weatherapp.dto.response.shared;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherConditionDTO(
        @JsonProperty("id") int conditionId,
        @JsonProperty("main") String currentCondition,
        String description,
        String icon
){}