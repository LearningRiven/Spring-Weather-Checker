package com.weatherapp.dto.request;

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
        if (countryCode == null || countryCode.isBlank()) {
            return zipCode;
        }
        return zipCode + "," + countryCode;
    }
}
