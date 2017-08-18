package org.xcolab.util.time;

import org.springframework.util.Assert;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public final class DateUtil {

    private DateUtil() {
    }

    public static Date toDate(Instant instant) {
        return Date.from(instant);
    }

    public static Date toDate(LocalDateTime localDateTime) {
        final Instant instant = toInstant(localDateTime);
        return toDate(instant);
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return toLocalDateTime(date.toInstant());
    }

    public static LocalDateTime toLocalDateTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static Instant toInstant(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }


    public static Integer getYearFromDate(Date date) {
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("US/Eastern"));
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static LocalDateTime earliest(LocalDateTime... dateTimes) {
        Assert.notEmpty(dateTimes, "Need to specify at least one LocalDateTime");
        return Arrays.stream(dateTimes)
                .min(Comparator.naturalOrder())
                .orElseThrow(IllegalStateException::new);
    }

    public static LocalDateTime latest(LocalDateTime... dateTimes) {
        Assert.notEmpty(dateTimes, "Need to specify at least one LocalDateTime");
        return Arrays.stream(dateTimes)
                .max(Comparator.naturalOrder())
                .orElseThrow(IllegalStateException::new);
    }

}
