package com.weatherapp.utils;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public final class DateTimeUtils {
    private DateTimeUtils() {}  // Private constructor for utility class

    public static String toLocalDateTimeString(long dt, int timezoneOffsetSeconds, String pattern) {
        Instant utcInstant = Instant.ofEpochSecond(dt);
        ZoneOffset offset = ZoneOffset.ofTotalSeconds(timezoneOffsetSeconds);
        LocalDateTime localDateTime = utcInstant.atOffset(offset).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

    // Overload for default pattern
    public static String toLocalDateTimeString(long dt, int timezoneOffsetSeconds) {
        return toLocalDateTimeString(dt, timezoneOffsetSeconds, "MMMM d, yyyy h:mm a");
    }

    // Add more utils as needed, e.g., for sunrise/sunset or current system time
    public static String getCurrentSystemTime(String pattern) {
        return getCurrentSystemTime(pattern, Clock.systemUTC());
    }

    //Overloaded method to used a fixed clock, keeps things more consistent
    public static String getCurrentSystemTime(String pattern, Clock clock) {
        LocalDateTime now = clock.instant().atZone(clock.getZone()).toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return now.format(formatter);
    }
}
