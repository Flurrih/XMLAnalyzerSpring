package com.example.DataAnalysisSpring;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public final class DateUtilities {
    public enum Zone {
        POLAND("Europe/Warsaw");

        public final String zone;

        Zone(String zone) {
            this.zone = zone;
        }

        public String toString() {
            return this.zone;
        }
    }

    private static DateTimeFormatter localDateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    private static DateTimeFormatter offsetDateTimeFormatter = new DateTimeFormatterBuilder()
                                                                    .append(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                                                                    .appendPattern("xxx")
                                                                    .toFormatter();

    public static LocalDateTime ParseStringToLocalDateTime(String date) {
        return LocalDateTime.parse(date, localDateTimeFormatter);
    }

    public static ZonedDateTime ConvertLocalToZonedDateTime(LocalDateTime date, Zone zone) {
        ZoneId zoneId = ZoneId.of(zone.toString());
        return date.atZone(zoneId);
    }

    public static ZonedDateTime ParseStringToZonedDateTime(String date, Zone zone) {
        LocalDateTime localDate = ParseStringToLocalDateTime(date);
        return ConvertLocalToZonedDateTime(localDate, zone);
    }

    public static String ConvertZonedDateTimeToString(ZonedDateTime date) {
        return date.format(offsetDateTimeFormatter);
    }
    public static String ConvertLocalDateTimeToString(LocalDateTime date) {
        return date.format(localDateTimeFormatter);
    }
}
