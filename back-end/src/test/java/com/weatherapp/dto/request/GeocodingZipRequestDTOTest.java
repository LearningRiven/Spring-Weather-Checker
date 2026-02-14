package com.weatherapp.dto.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeocodingZipRequestDTOTest {

    @Test
    @DisplayName("formats zip and country correctly")
    void test_formatsZipAndCountry() {
        GeocodingZipRequestDTO zipReq = new GeocodingZipRequestDTO("01013", "US");
        assertEquals("01013,US", zipReq.toZipParam());
    }

    @Test
    @DisplayName("invalid country (blank/null)")
    void test_invalidCountry_returnsOnlyZip() {
        assertEquals("01013", new GeocodingZipRequestDTO("01013", null).toZipParam());
        assertEquals("01013", new GeocodingZipRequestDTO("01013", "").toZipParam());
        assertEquals("01013", new GeocodingZipRequestDTO("01013", "      ").toZipParam());
    }

    @Test
    @DisplayName("invalid DTO")
    void test_empty_returnsBlank() {
        GeocodingZipRequestDTO zipReq = new GeocodingZipRequestDTO(null, null);
        assertEquals("", zipReq.toZipParam());
    }

    @Test
    @DisplayName("invalid zip (blank/null)")
    void test_invalidZip_returnsOnlyCountry() {
        assertEquals("US", new GeocodingZipRequestDTO(null, "US").toZipParam());
        assertEquals("US", new GeocodingZipRequestDTO("", "US").toZipParam());
        assertEquals("US", new GeocodingZipRequestDTO("    ", "US").toZipParam());
    }
}
