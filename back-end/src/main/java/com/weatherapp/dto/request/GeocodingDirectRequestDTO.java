package com.weatherapp.dto.request;

import io.micrometer.common.util.StringUtils;

public class GeocodingDirectRequestDTO {

    private String cityName;
    private String stateCode;
    private String countryCode;
    private Integer limit;

    public GeocodingDirectRequestDTO() {
    }

    public GeocodingDirectRequestDTO(String cityName, String stateCode, String countryCode, Integer limit) {
        this.cityName = cityName;
        this.stateCode = stateCode;
        this.countryCode = countryCode;
        this.limit = limit;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * Formats the query parameter as expected by OpenWeatherMap API: "cityName,stateCode,countryCode"
     */
    public String toQueryParam() {
        StringBuilder sb = new StringBuilder(cityName);
        if (!StringUtils.isBlank(stateCode)) {
            sb.append(",").append(stateCode);
        }
        if (!StringUtils.isBlank(countryCode)) {
            sb.append(",").append(countryCode);
        }
        return sb.toString();
    }
}
