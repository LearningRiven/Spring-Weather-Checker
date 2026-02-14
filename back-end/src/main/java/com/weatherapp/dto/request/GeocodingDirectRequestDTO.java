package com.weatherapp.dto.request;

import org.apache.commons.lang3.StringUtils;

public record GeocodingDirectRequestDTO (
        String cityName,
        String stateCode,
        String countryCode,
        Integer limit
){

    /**
     * Formats the query parameter as expected by OpenWeatherMap API: "cityName,stateCode,countryCode"
     */
    public String toQueryParam() {
        StringBuilder sb = new StringBuilder(!StringUtils.isBlank(cityName) ? cityName : "");
        if (!StringUtils.isBlank(stateCode) && "US".equalsIgnoreCase(countryCode)) {
            sb.append(",").append(stateCode);
        }
        if (!StringUtils.isBlank(countryCode)) {
            sb.append(",").append(countryCode);
        }

        //Remove comma if first character
        if(!sb.isEmpty() && sb.charAt(0) == ','){
            sb.deleteCharAt(0);
        }

        return sb.toString();
    }
}
