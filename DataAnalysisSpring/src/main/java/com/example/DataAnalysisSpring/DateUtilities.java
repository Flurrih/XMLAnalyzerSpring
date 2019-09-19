package com.example.DataAnalysisSpring;

import org.graalvm.compiler.replacements.nodes.CStringConstant;

import javax.swing.text.Element;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public final class DateUtilities {

    public enum Zone {
        POLAND("Europe/Warsaw");

        public final String zone;

        private Zone(String zone) {
            this.zone = zone;
        }
    }

    public static LocalDateTime ParseStringToLocalDateTime(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return LocalDateTime.parse(date, formatter);
    }

    public static ZonedDateTime ConvertLocalToZonedDateTime(LocalDateTime date, Zone zone) {
        ZoneId zoneId = ZoneId.of(zone.toString());
        return date.atZone(zoneId);
    }

    public static ZonedDateTime ParseStringToZonedDateTime(String date, Zone zone) {
        LocalDateTime localDate = ParseStringToLocalDateTime(date);
        return ConvertLocalToZonedDateTime(localDate, zone);
    }
}
