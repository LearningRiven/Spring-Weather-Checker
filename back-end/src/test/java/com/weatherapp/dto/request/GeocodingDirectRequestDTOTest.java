package com.weatherapp.dto.request;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class GeocodingDirectRequestDTOTest {

    @Nested
    @DisplayName("toQueryParam")
    class ToQueryParam {

        @Test
        @DisplayName("formats city, state, country correctly")
        void formatsAllFields() {
            GeocodingDirectRequestDTO direct = new GeocodingDirectRequestDTO("Austin","TX","US",5);
            String toQuery = direct.toQueryParam();
            assertEquals("Austin,TX,US",toQuery);
        }

        @Test
        @DisplayName("formats state and country correctly when city is blank/null")
        void nullBlankNull_city(){
            GeocodingDirectRequestDTO directBlank = new GeocodingDirectRequestDTO("","TX","US",5);
            String toQueryBlank = directBlank.toQueryParam();
            assertEquals("TX,US",toQueryBlank);

            GeocodingDirectRequestDTO directNull = new GeocodingDirectRequestDTO(null,"TX","US",5);
            String toQueryNull = directNull.toQueryParam();
            assertEquals("TX,US",toQueryNull);
        }

        @Test
        @DisplayName("formats city and country correctly when state is blank/null")
        void nullBlankNull_state(){
            GeocodingDirectRequestDTO directBlank = new GeocodingDirectRequestDTO("Austin","","US",5);
            String toQueryBlank = directBlank.toQueryParam();
            assertEquals("Austin,US",toQueryBlank);

            GeocodingDirectRequestDTO directNull = new GeocodingDirectRequestDTO("Austin",null,"US",5);
            String toQueryNull = directNull.toQueryParam();
            assertEquals("Austin,US",toQueryNull);
        }

        @Test
        @DisplayName("formats city and state correctly when country is blank/null")
        void nullBlankNull_country(){
            GeocodingDirectRequestDTO directBlank = new GeocodingDirectRequestDTO("Austin","TX","",5);
            String toQueryBlank = directBlank.toQueryParam();
            assertEquals("Austin",toQueryBlank);

            GeocodingDirectRequestDTO directNull = new GeocodingDirectRequestDTO("Austin","TX",null,5);
            String toQueryNull = directNull.toQueryParam();
            assertEquals("Austin",toQueryNull);
        }

        @Test
        @DisplayName("includes state only when the country is US (case exclusive)")
        void countryNotUS(){
            GeocodingDirectRequestDTO direct = new GeocodingDirectRequestDTO("Worcester","MA","GB",5);
            String toQuery = direct.toQueryParam();
            assertEquals("Worcester,GB",toQuery);
        }

        @Test
        @DisplayName("Nothing inside of the object")
        void objectIsEmptyNull(){
            GeocodingDirectRequestDTO directBlank = new GeocodingDirectRequestDTO("","","",5);
            String toQueryBlank = directBlank.toQueryParam();
            assertEquals("",toQueryBlank);

            GeocodingDirectRequestDTO directNull = new GeocodingDirectRequestDTO(null,null,null,5);
            String toQueryNull = directNull.toQueryParam();
            assertEquals("",toQueryNull);
        }

    }

    @Nested
    @DisplayName("constructor and getters")
    class ConstructorAndGetters {

        @Test
        @DisplayName("all-args constructor sets fields correctly")
        void allArgsConstructor_setsFields() {
            GeocodingDirectRequestDTO direct = new GeocodingDirectRequestDTO("Austin","TX","US",5);
            assertEquals("Austin", direct.getCityName());
            assertEquals("TX", direct.getStateCode());
            assertEquals("US", direct.getCountryCode());
            assertEquals(5, direct.getLimit());
        }

        @Test
        @DisplayName("no-args constructor creates empty object")
        void noArgsConstructor_createsEmptyObject() {
            GeocodingDirectRequestDTO direct = new GeocodingDirectRequestDTO();
            assertNull(direct.getCityName());
            assertNull(direct.getStateCode());
            assertNull(direct.getCountryCode());
            assertNull(direct.getLimit());
        }

        @Test
        @DisplayName("setters update fields correctly")
        void setters_updateFields() {
            GeocodingDirectRequestDTO direct = new GeocodingDirectRequestDTO();

            direct.setCityName("Chicopee");
            assertEquals("Chicopee", direct.getCityName());

            direct.setStateCode("MA");
            assertEquals("MA", direct.getStateCode());

            direct.setCountryCode("US");
            assertEquals("US", direct.getCountryCode());

            direct.setLimit(3);
            assertEquals(3, direct.getLimit());
        }
    }
}