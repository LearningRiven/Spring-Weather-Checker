package com.weatherapp.dto.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class WeatherRequestDTOTest {

    @Nested
    @DisplayName("constructor")
    class test_Constructor {

        @Test
        @DisplayName("all-args constructor sets all fields correctly")
        void test_allArgsConstructor_setsAllFields() {
            WeatherRequestDTO requestDTO = new WeatherRequestDTO(1.2,-1.2,"imperial","en");
            assertEquals(1.2,requestDTO.getLatitude());
            assertEquals(-1.2,requestDTO.getLongitude());
            assertEquals("imperial",requestDTO.getUnits());
            assertEquals("en",requestDTO.getLanguage());
        }

        @Test
        @DisplayName("no-args constructor creates object with default values")
        void test_noArgsConstructor_createsObjectWithDefaults() {
            WeatherRequestDTO requestDTO = new WeatherRequestDTO();
            assertEquals(0,requestDTO.getLatitude());
            assertEquals(0,requestDTO.getLongitude());
            assertNull(requestDTO.getUnits());
            assertNull(requestDTO.getLanguage());
        }
    }

    @Nested
    @DisplayName("getters and setters")
    class test_GettersAndSetters {

        @Test
        @DisplayName("handle values")
        void test_set_updatesPositive() {
            WeatherRequestDTO requestDTO = new WeatherRequestDTO();
            requestDTO.setLatitude(2.7);
            requestDTO.setLongitude(2.33);
            requestDTO.setUnits("imperial");
            requestDTO.setLanguage("en");


            assertEquals(2.7,requestDTO.getLatitude());
            assertEquals(2.33,requestDTO.getLongitude());
            assertEquals("imperial",requestDTO.getUnits());
            assertEquals("en",requestDTO.getLanguage());
        }

        @Test
        @DisplayName("handle null/blank")
        void test_set_updatesBlankNull() {
            WeatherRequestDTO requestDTO = new WeatherRequestDTO();
            requestDTO.setUnits("");
            requestDTO.setLanguage("");

            assertEquals("",requestDTO.getUnits());
            assertEquals("",requestDTO.getLanguage());

            requestDTO.setUnits(null);
            requestDTO.setLanguage(null);

            assertNull(requestDTO.getUnits());
            assertNull(requestDTO.getLanguage());
        }
    }
}