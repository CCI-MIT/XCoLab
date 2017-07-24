package org.xcolab.util.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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

    private static Instant toInstant(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }

}
