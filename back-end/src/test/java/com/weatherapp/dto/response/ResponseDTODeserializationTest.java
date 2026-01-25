package com.weatherapp.dto.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ResponseDTODeserializationTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Nested
    @DisplayName("GeocodingResponseDTO")
    class GeocodingResponseDTOTests {

        @Test
        @DisplayName("deserializes all fields from JSON")
        void deserializesAllFields() throws Exception {
            // TODO
        }

        @Test
        @DisplayName("handles null optional fields")
        void handlesNullOptionalFields() throws Exception {
            // TODO
        }
    }

    @Nested
    @DisplayName("WeatherResponseDTO")
    class WeatherResponseDTOTests {

        @Test
        @DisplayName("deserializes all fields from JSON")
        void deserializesAllFields() throws Exception {
            // TODO
        }

        @Test
        @DisplayName("deserializes nested Sys record")
        void deserializesNestedSys() throws Exception {
            // TODO
        }

        @Test
        @DisplayName("deserializes weather list")
        void deserializesWeatherList() throws Exception {
            // TODO
        }

        @Test
        @DisplayName("handles optional fields missing")
        void handlesOptionalFieldsMissing() throws Exception {
            // TODO
        }

        @Test
        @DisplayName("maps @JsonProperty fields correctly")
        void mapsJsonPropertyFields() throws Exception {
            // TODO
        }
    }
}