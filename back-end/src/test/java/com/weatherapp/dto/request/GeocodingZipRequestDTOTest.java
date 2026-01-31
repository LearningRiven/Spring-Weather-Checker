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
            GeocodingZipRequestDTO zipReq = new GeocodingZipRequestDTO("01013","US");
            assertEquals("01013,US", zipReq.toZipParam());
        }

        @Test
        @DisplayName("invalid country (blank/null)")
        void invalidCountry_returnsOnlyZip() {
            GeocodingZipRequestDTO zipReq = new GeocodingZipRequestDTO("01013",null);
            assertEquals("01013", zipReq.toZipParam());

            zipReq.setCountryCode("");
            assertEquals("01013", zipReq.toZipParam());

            zipReq.setCountryCode("      ");
            assertEquals("01013", zipReq.toZipParam());
        }

        @Test
        @DisplayName("invalid DTO")
        void empty_returnsBlank() {
            GeocodingZipRequestDTO zipReq = new GeocodingZipRequestDTO();
            assertEquals("", zipReq.toZipParam());
        }

        @Test
        @DisplayName("invalid zip (blank/null)")
        void invalidCountry_returnsOnlyCountry() {
            GeocodingZipRequestDTO zipReq = new GeocodingZipRequestDTO(null,"US");
            assertEquals("US", zipReq.toZipParam());

            zipReq.setZipCode("");
            assertEquals("US", zipReq.toZipParam());

            zipReq.setZipCode("    ");
            assertEquals("US", zipReq.toZipParam());
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