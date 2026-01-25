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
        int visibility,
        WindDTO wind,
        CloudsDTO clouds,
        long dt,
        Sys sys,
        int timezone,
        @JsonProperty("id") int responseId,
        String name,
        int cod
){
    //Kept this as a subclass, not really reusable, rest moved to their own DTOs
    public record Sys (
        int type,
        @JsonProperty("id") int sysId,
        String country,
        long sunrise,
        long sunset
    ){}
}
