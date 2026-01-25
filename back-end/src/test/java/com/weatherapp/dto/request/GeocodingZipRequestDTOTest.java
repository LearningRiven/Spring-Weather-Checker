package com.weatherapp.dto.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
            // TODO
        }

        @Test
        @DisplayName("no-args constructor creates empty object")
        void noArgsConstructor_createsEmptyObject() {
            // TODO
        }

        @Test
        @DisplayName("setters update fields correctly")
        void setters_updateFields() {
            // TODO
        }
    }
}