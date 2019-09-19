package com.example.DataAnalysisSpring;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DateUtilitiesTest {
    @Test
    public void shouldParseStringToLocalDateTime() {
        LocalDateTime localDateTime =
                DateUtilities.ParseStringToLocalDateTime("2019-09-19T11:21:08.3566361");
        assertEquals("2019-09-19T11:21:08.3566361", DateUtilities.ConvertLocalDateTimeToString(localDateTime));
    }

    @Test
    public void shouldConvertLocalToZonedDateTime() {
        LocalDateTime localDateTime =
                DateUtilities.ParseStringToLocalDateTime("2019-09-19T11:21:08.3566361");
        ZonedDateTime zonedDateTime =
                DateUtilities.ConvertLocalToZonedDateTime(localDateTime, DateUtilities.Zone.POLAND);
        assertEquals("2019-09-19T11:21:08.3566361+02:00",
                DateUtilities.ConvertZonedDateTimeToString(zonedDateTime));
    }

    @Test
    public void shouldParseStringToZonedDateTime() {
        ZonedDateTime zonedDateTime =
                DateUtilities.ParseStringToZonedDateTime("2019-09-19T11:21:08.3566361", DateUtilities.Zone.POLAND);
        assertEquals("2019-09-19T11:21:08.3566361+02:00", DateUtilities.ConvertZonedDateTimeToString(zonedDateTime));
    }
}
