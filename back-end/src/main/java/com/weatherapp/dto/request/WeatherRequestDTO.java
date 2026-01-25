package com.weatherapp.dto.request;

public class WeatherRequestDTO {

    private String latitude;
    private String longitude;
    private String units;
    private String language;

    public WeatherRequestDTO() {
    }

    public WeatherRequestDTO(String latitude, String longitude, String units, String language) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.units = units;
        this.language = language;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
