package com.weatherapp.dto.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class GeocodingDirectRequestDTOTest {

    @Nested
    @DisplayName("toQueryParam")
    class ToQueryParam {

        @Test
        @DisplayName("formats city, state, country correctly")
        void formatsAllFields() {
            // TODO
        }

        @Test
        @DisplayName("omits state when null")
        void nullState_omitsState() {
            // TODO
        }

        @Test
        @DisplayName("omits state when blank")
        void blankState_omitsState() {
            // TODO
        }

        @Test
        @DisplayName("omits country when null")
        void nullCountry_omitsCountry() {
            // TODO
        }

        @Test
        @DisplayName("omits country when blank")
        void blankCountry_omitsCountry() {
            // TODO
        }

        @Test
        @DisplayName("returns only city when state and country are blank")
        void onlyCity_returnsOnlyCity() {
            // TODO
        }

        @Test
        @DisplayName("includes country even when state is blank")
        void blankState_stillIncludesCountry() {
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