package com.weatherapp.dto.request;

import org.apache.commons.lang3.StringUtils;

public class GeocodingZipRequestDTO {

    private String zipCode;
    private String countryCode;

    public GeocodingZipRequestDTO() {
    }

    public GeocodingZipRequestDTO(String zipCode, String countryCode) {
        this.zipCode = zipCode;
        this.countryCode = countryCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

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
