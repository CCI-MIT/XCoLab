package org.xcolab.util.time;

import org.ocpsoft.prettytime.PrettyTime;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DurationFormatter {

    private static final Map<Locale, DurationFormatter> instances = new HashMap<>();

    private final PrettyTime prettyTime;

    private DurationFormatter(Locale locale) {
        prettyTime = new PrettyTime(locale);
    }

    public static DurationFormatter forLocale(Locale locale) {
        return instances.computeIfAbsent(locale, DurationFormatter::new);
    }

    public static DurationFormatter forRequestLocale() {
        return forLocale(LocaleContextHolder.getLocale());
    }

    /**
     * Prints the duration since or until a certain point in time
     * and decorates for past/future tense.
     *
     * E.g. "1 hour ago" or "in 1 month"
     *
     * @param instant reference date
     */
    public String format(Instant instant) {
        return format(toDate(instant));
    }

    public String format(Date date) {
        return prettyTime.format(date);
    }

    public String format(Calendar calendar) {
        return prettyTime.format(calendar);
    }

    public String format(LocalDate localDate) {
        return format(toDate(localDate));
    }

    public String format(LocalDateTime localDateTime) {
        return format(toDate(localDateTime));
    }

    /**
     * Prints the time difference between now and the given point in time.
     *
     * E.g. "1 hour" or "1 month"
     *
     * @param instant reference date
     */
    public String formatDifference(Instant instant) {
        return formatDifference(toDate(instant));
    }

    public String formatDifference(Date date) {
        return prettyTime.formatApproximateDuration(date);
    }

    public String formatDifference(LocalDate localDate) {
        return formatDifference(toDate(localDate));
    }

    public String formatDifference(LocalDateTime localDateTime) {
        return formatDifference(toDate(localDateTime));
    }

    private Date toDate(Instant instant) {
        return Date.from(instant);
    }

    private Date toDate(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return toDate(instant);
    }

    private Date toDate(LocalDateTime localDateTime) {
        final Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return toDate(instant);
    }

}
