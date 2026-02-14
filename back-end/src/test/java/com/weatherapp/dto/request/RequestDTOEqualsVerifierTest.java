package com.weatherapp.dto.request;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// Records guarantee correct equals/hashCode, so these tests will always pass.
// Kept as a safety net in case a record is ever converted back to a class.
class RequestDTOEqualsVerifierTest {

    @Test
    @DisplayName("GeocodingZipRequestDTO satisfies equals/hashCode contract")
    void test_geocodingZipRequestDTO_equalsContract() {
        EqualsVerifier.forClass(GeocodingZipRequestDTO.class).verify();
    }

    @Test
    @DisplayName("GeocodingDirectRequestDTO satisfies equals/hashCode contract")
    void test_geocodingDirectRequestDTO_equalsContract() {
        EqualsVerifier.forClass(GeocodingDirectRequestDTO.class).verify();
    }

    @Test
    @DisplayName("WeatherRequestDTO satisfies equals/hashCode contract")
    void test_weatherRequestDTO_equalsContract() {
        EqualsVerifier.forClass(WeatherRequestDTO.class).verify();
    }
}
