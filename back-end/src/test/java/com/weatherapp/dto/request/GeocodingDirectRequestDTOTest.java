package com.weatherapp.dto.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeocodingDirectRequestDTOTest {

    @Test
    @DisplayName("formats city, state, country correctly")
    void test_formatsAllFields() {
        GeocodingDirectRequestDTO direct = new GeocodingDirectRequestDTO("Austin", "TX", "US", 5);
        assertEquals("Austin,TX,US", direct.toQueryParam());
    }

    @Test
    @DisplayName("formats state and country correctly when city is blank/null")
    void test_nullBlankNull_city() {
        assertEquals("TX,US", new GeocodingDirectRequestDTO("", "TX", "US", 5).toQueryParam());
        assertEquals("TX,US", new GeocodingDirectRequestDTO("  ", "TX", "US", 5).toQueryParam());
        assertEquals("TX,US", new GeocodingDirectRequestDTO(null, "TX", "US", 5).toQueryParam());
    }

    @Test
    @DisplayName("formats city and country correctly when state is blank/null")
    void test_nullBlankNull_state() {
        assertEquals("Austin,US", new GeocodingDirectRequestDTO("Austin", "", "US", 5).toQueryParam());
        assertEquals("Austin,US", new GeocodingDirectRequestDTO("Austin", null, "US", 5).toQueryParam());
    }

    @Test
    @DisplayName("formats city and state correctly when country is blank/null")
    void test_nullBlankNull_country() {
        assertEquals("Austin", new GeocodingDirectRequestDTO("Austin", "TX", "", 5).toQueryParam());
        assertEquals("Austin", new GeocodingDirectRequestDTO("Austin", "TX", null, 5).toQueryParam());
    }

    @Test
    @DisplayName("includes state only when the country is US (case exclusive)")
    void test_countryNotUS() {
        GeocodingDirectRequestDTO direct = new GeocodingDirectRequestDTO("Worcester", "MA", "GB", 5);
        assertEquals("Worcester,GB", direct.toQueryParam());
    }

    @Test
    @DisplayName("Nothing inside of the object")
    void test_objectIsEmptyNull() {
        assertEquals("", new GeocodingDirectRequestDTO("", "", "", 5).toQueryParam());
        assertEquals("", new GeocodingDirectRequestDTO(null, null, null, 5).toQueryParam());
    }
}
