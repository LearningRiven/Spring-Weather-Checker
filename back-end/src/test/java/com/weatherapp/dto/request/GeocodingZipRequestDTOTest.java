package com.weatherapp.dto.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GeocodingZipRequestDTOTest {

    @Nested
    @DisplayName("toZipParam")
    class ToZipParam {

        @Test
        @DisplayName("formats zip and country correctly")
        void formatsZipAndCountry() {
            // TODO
        }

        @Test
        @DisplayName("returns only zip when country is null")
        void nullCountry_returnsOnlyZip() {
            // TODO
        }

        @Test
        @DisplayName("returns only zip when country is blank")
        void blankCountry_returnsOnlyZip() {
            // TODO
        }

        @Test
        @DisplayName("returns only zip when country is empty string")
        void emptyCountry_returnsOnlyZip() {
            // TODO
        }
    }

    @Nested
    @DisplayName("constructor and getters")
    class ConstructorAndGetters {

        @Test
        @DisplayName("all-args constructor sets fields correctly")
        void allArgsConstructor_setsFields() {
            GeocodingZipRequestDTO direct = new GeocodingZipRequestDTO("78727","US");
            assertEquals("78727", direct.getZipCode());
            assertEquals("US", direct.getCountryCode());
        }

        @Test
        @DisplayName("no-args constructor creates empty object")
        void constructor_createsEmptyObjectAndSets() {
            GeocodingZipRequestDTO direct = new GeocodingZipRequestDTO();
            assertNull(direct.getZipCode());
            assertNull(direct.getCountryCode());

            direct.setZipCode("01013");
            direct.setCountryCode("US");

            assertEquals("01013", direct.getZipCode());
            assertEquals("US", direct.getCountryCode());
        }
    }
}