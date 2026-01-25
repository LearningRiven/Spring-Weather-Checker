package com.weatherapp.dto.response.shared;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class SharedDTODeserializationTest {

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Nested
    @DisplayName("WindDTO")
    class WindDTOTests {

        @Test
        @DisplayName("deserializes with all fields present")
        void deserializesAllFields() throws Exception {
            // TODO
        }

        @Test
        @DisplayName("deserializes with gust missing (null)")
        void gustMissing_deserializesToNull() throws Exception {
            // TODO
        }
    }

    @Nested
    @DisplayName("WeatherMeasurementsDTO")
    class WeatherMeasurementsDTOTests {

        @Test
        @DisplayName("deserializes with @JsonProperty mappings")
        void deserializesJsonPropertyMappings() throws Exception {
            // TODO
        }

        @Test
        @DisplayName("deserializes with optional fields missing")
        void optionalFieldsMissing_deserializesToNull() throws Exception {
            // TODO
        }
    }

    @Nested
    @DisplayName("WeatherConditionDTO")
    class WeatherConditionDTOTests {

        @Test
        @DisplayName("deserializes with @JsonProperty mappings")
        void deserializesJsonPropertyMappings() throws Exception {
            // TODO
        }
    }

    @Nested
    @DisplayName("CloudsDTO")
    class CloudsDTOTests {

        @Test
        @DisplayName("deserializes all field")
        void deserializesAllField() throws Exception {
            // TODO
        }
    }

    @Nested
    @DisplayName("CoordDTO")
    class CoordDTOTests {

        @Test
        @DisplayName("deserializes lat and lon")
        void deserializesLatAndLon() throws Exception {
            // TODO
        }
    }
}