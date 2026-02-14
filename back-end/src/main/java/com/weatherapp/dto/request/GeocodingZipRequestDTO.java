package com.weatherapp.dto.request;

import org.apache.commons.lang3.StringUtils;

public record GeocodingZipRequestDTO (
        String zipCode,
        String countryCode
){

    /**
     * Formats the zip parameter as expected by OpenWeatherMap API: "zipCode,countryCode"
     */
    public String toZipParam() {
        StringBuilder sb = new StringBuilder(!StringUtils.isBlank(zipCode) ? zipCode.trim() : "");
        if (!StringUtils.isBlank(countryCode)) {
            sb.append(",").append(countryCode.trim());
        }

        //Remove comma if first character
        if(!sb.isEmpty() && sb.charAt(0) == ','){
            sb.deleteCharAt(0);
        }

        return sb.toString();
    }
}
