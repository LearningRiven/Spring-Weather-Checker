package com.weatherapp.dto.request;

public class WeatherRequestDTO {

    private double latitude;
    private double longitude;
    private String units;
    private String language;

    public WeatherRequestDTO() {
    }

    public WeatherRequestDTO(Double latitude, Double longitude, String units, String language) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.units = units;
        this.language = language;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
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
