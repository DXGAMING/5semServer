package com.server.util.date_converter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * The type Date converter.
 *
 */
public class DateConverter {
    private DateConverter() {
    }

    /**
     * Convert to long long.
     *
     * @param date the date
     * @return the long
     */
    public static long convertToLong(LocalDate date) {
        ZonedDateTime zonedDateTime = date.atStartOfDay().atZone(ZoneId.systemDefault());

        return zonedDateTime.toInstant().toEpochMilli();
    }

    /**
     * Convert to date local date.
     *
     * @param milliseconds the milliseconds
     * @return the local date
     */
    public static LocalDate convertToDate(long milliseconds) {
        return Instant.ofEpochMilli(milliseconds).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
