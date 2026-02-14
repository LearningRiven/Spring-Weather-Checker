package com.weatherapp.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateTimeUtilsTest {

    @Nested
    @DisplayName("getCurrentSystemTime tests")
    class ToLocalDateTimeStringWithPattern {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy h:mm a");
        Clock fixed = Clock.fixed(Instant.parse("2026-01-15T14:30:00Z"),ZoneOffset.UTC);

        @Test
        @DisplayName("Test current system time overload")
        void test_getCurrentSystemTimeOverload(){
            String result = DateTimeUtils.getCurrentSystemTime("MMMM d, yyyy h:mm a", fixed);
            assertEquals("January 15, 2026 2:30 PM", result);
        }

        @Test
        @DisplayName("Test toLocalDateTimeString with pattern")
        void test_toLocalDateTimeString(){
            long dt = Instant.parse("2026-01-15T14:30:00Z").getEpochSecond();
            int utc = ZoneId.of("UTC").getRules().getOffset(fixed.instant()).getTotalSeconds();
            String result = DateTimeUtils.toLocalDateTimeString(dt, utc, "yyyy-MM-dd h:mm a");
            assertEquals("2026-01-15 2:30 PM", result);
        }

        @Test
        @DisplayName("Test toLocalDateTimeString without pattern")
        void test_toLocalDateTimeStringPattern(){
            long dt = Instant.parse("2026-01-15T14:30:00Z").getEpochSecond();
            int utc = ZoneId.of("UTC").getRules().getOffset(fixed.instant()).getTotalSeconds();
            String result = DateTimeUtils.toLocalDateTimeString(dt, utc);
            assertEquals("January 15, 2026 2:30 PM", result);
        }

        @Test
        @DisplayName("Test timezone Central - Negative offset")
        void test_toLocalDateTimeStringCentral(){
            long dt = Instant.parse("2026-01-15T14:30:00Z").getEpochSecond();
            int cst_central = ZoneId.of("America/Chicago").getRules().getOffset(fixed.instant()).getTotalSeconds();
            String result = DateTimeUtils.toLocalDateTimeString(dt, cst_central);
            assertEquals("January 15, 2026 8:30 AM", result);
        }


        @Test
        @DisplayName("Test timezone Japan - Positive offset")
        void test_toLocalDateTimeStringJapan(){
            long dt = Instant.parse("2026-01-15T14:30:00Z").getEpochSecond();
            int jst_japan = ZoneId.of("Asia/Tokyo").getRules().getOffset(fixed.instant()).getTotalSeconds();
            String result = DateTimeUtils.toLocalDateTimeString(dt, jst_japan);
            assertEquals("January 15, 2026 11:30 PM", result);
        }


        @Test
        @DisplayName("Test timezone India - 30 minute offset")
        void test_toLocalDateTimeStringIndia(){
            long dt = Instant.parse("2026-01-15T14:30:00Z").getEpochSecond();
            int ist_india = ZoneId.of("Asia/Kolkata").getRules().getOffset(fixed.instant()).getTotalSeconds();
            String result = DateTimeUtils.toLocalDateTimeString(dt, ist_india);
            assertEquals("January 15, 2026 8:00 PM", result);
        }

        @Test
        @DisplayName("Test timezone Nepal - 45 minute offset")
        void test_toLocalDateTimeStringNepal(){
            long dt = Instant.parse("2026-01-15T14:30:00Z").getEpochSecond();
            int npt_nepal = ZoneId.of("Asia/Kathmandu").getRules().getOffset(fixed.instant()).getTotalSeconds();
            String result = DateTimeUtils.toLocalDateTimeString(dt, npt_nepal);
            assertEquals("January 15, 2026 8:15 PM", result);
        }

        @Test
        @DisplayName("Test negative offset crosses date boundary to previous day")
        void test_toLocalDateTimeStringDateBoundaryBack(){
            long dt = Instant.parse("2026-01-15T01:00:00Z").getEpochSecond();
            int cst_central = ZoneId.of("America/Chicago").getRules().getOffset(Instant.parse("2026-01-15T01:00:00Z")).getTotalSeconds();
            String result = DateTimeUtils.toLocalDateTimeString(dt, cst_central);
            assertEquals("January 14, 2026 7:00 PM", result);
        }

        @Test
        @DisplayName("Test positive offset crosses date boundary to next day")
        void test_toLocalDateTimeStringDateBoundaryForward(){
            long dt = Instant.parse("2026-01-15T22:00:00Z").getEpochSecond();
            int jst_japan = ZoneId.of("Asia/Tokyo").getRules().getOffset(Instant.parse("2026-01-15T22:00:00Z")).getTotalSeconds();
            String result = DateTimeUtils.toLocalDateTimeString(dt, jst_japan);
            assertEquals("January 16, 2026 7:00 AM", result);
        }
    }
}