package com.weatherapp.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.weatherapp.dto.response.shared.CloudsDTO;
import com.weatherapp.dto.response.shared.CoordDTO;
import com.weatherapp.dto.response.shared.WeatherConditionDTO;
import com.weatherapp.dto.response.shared.WeatherMeasurementsDTO;
import com.weatherapp.dto.response.shared.WindDTO;

import java.util.List;

public record WeatherResponseDTO(
        CoordDTO coord,
        List<WeatherConditionDTO> weather,
        String base,
        WeatherMeasurementsDTO main,
        Integer visibility,
        WindDTO wind,
        CloudsDTO clouds,
        Long dt,
        Sys sys,
        Integer timezone,
        @JsonProperty("id") Integer responseId,
        String name,
        Integer cod
){
    //Kept this as a subclass, not really reusable, rest moved to their own DTOs
    public record Sys (
        Integer type,
        @JsonProperty("id") Integer sysId,
        String country,
        Long sunrise,
        Long sunset
    ){}
}
