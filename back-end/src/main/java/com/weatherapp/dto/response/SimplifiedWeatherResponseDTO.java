package com.weatherapp.dto.response;

import com.weatherapp.dto.response.shared.WeatherConditionDTO;

import java.util.List;

public record SimplifiedWeatherResponseDTO(
        String locationName,    // e.g., "Austin"
        Double temperature,     // Current Temp (F/C) depends on units
        List<WeatherConditionDTO> weatherConditions,  // e.g., "Clear sky + fog" basically covers multiple conditions active at the same time
        Integer humidity,        // Percentage
        Double windSpeed,        // MPH (depends on units)
        String units,
        String currentTime
) {
    // Static builder class (nested for encapsulation)
    public static class Builder {
        private String locationName;
        private Double temperature;
        private List<WeatherConditionDTO> weatherConditions;
        private Integer humidity;
        private Double windSpeed;
        private String units;
        private String currentTime;

        public Builder locationName(String locationName) {
            this.locationName = locationName;
            return this;
        }

        public Builder temperature(Double temperature) {
            this.temperature = temperature;
            return this;
        }

        public Builder weatherConditions(List<WeatherConditionDTO> weatherConditions) {
            this.weatherConditions = weatherConditions;
            return this;
        }

        public Builder humidity(Integer humidity) {
            this.humidity = humidity;
            return this;
        }

        public Builder windSpeed(Double windSpeed) {
            this.windSpeed = windSpeed;
            return this;
        }

        public Builder units(String units){
            this.units = units;
            return this;
        }

        public Builder currentTime(String currentTime){
            this.currentTime = currentTime;
            return this;
        }

        public SimplifiedWeatherResponseDTO build() {
            // Optional: Can add validation, but for now we trust openweathermap will do its job
            return new SimplifiedWeatherResponseDTO(locationName, temperature, weatherConditions, humidity, windSpeed, units, currentTime);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
