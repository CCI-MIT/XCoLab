package org.xcolab.util.time;

import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.impl.ResourcesTimeFormat;
import org.ocpsoft.prettytime.impl.ResourcesTimeUnit;
import org.ocpsoft.prettytime.units.Day;
import org.ocpsoft.prettytime.units.Hour;
import org.ocpsoft.prettytime.units.Minute;
import org.ocpsoft.prettytime.units.Month;
import org.ocpsoft.prettytime.units.Second;
import org.ocpsoft.prettytime.units.Week;
import org.ocpsoft.prettytime.units.Year;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.xcolab.util.time.DateUtil.toDate;

public class DurationFormatter {

    private static final Map<Locale, DurationFormatter> instances = new HashMap<>();

    private static final List<ResourcesTimeUnit> ALL_UNITS =
            Arrays.asList(new Minute(), new Hour(), new Day(),
                    new Week(), new Month(), new Year());
    private static final List<ResourcesTimeUnit> DAY_UNITS =
            Arrays.asList(new Minute(), new Hour(), new Day());

    private final PrettyTime prettyTime;
    private final PrettyTime prettyTimeDays;

    private DurationFormatter(Locale locale) {
        prettyTime = createPrettyTime(locale, ALL_UNITS);
        prettyTimeDays = createPrettyTime(locale, DAY_UNITS);
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

    public String format(LocalDateTime localDateTime) {
        return format(toDate(localDateTime));
    }

    public String format(Date date) {
        return prettyTime.format(date);
    }


    /**
     * Prints the duration since or until a certain point in time
     * and decorates for past/future tense, using units smaller
     * than or equal to Days (i.e. Days, hours, minutes).
     *
     * E.g. "1 hour ago" or "in 35 days"
     *
     * @param instant reference date
     */
    public String formatAsDays(Instant instant) {
        return formatAsDays(toDate(instant));
    }

    public String formatAsDays(Date date) {
        return prettyTimeDays.format(date);
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

    public String formatDifference(LocalDateTime localDateTime) {
        return formatDifference(toDate(localDateTime));
    }

    public String formatDifference(Date date) {
        return prettyTime.formatApproximateDuration(date);
    }

    /**
     * Prints the time difference between now and the given point in time,
     * using units smaller than or equal to Days (i.e. Days, hours, minutes).
     *
     * E.g. "1 hour" or "35 days"
     *
     * @param instant reference date
     */
    public String formatDifferenceAsDays(Instant instant) {
        return formatDifferenceAsDays(toDate(instant));
    }

    public String formatDifferenceAsDays(Date date) {
        return prettyTimeDays.formatApproximateDuration(date);
    }

    private static PrettyTime createPrettyTime(Locale locale, List<ResourcesTimeUnit> units) {
        PrettyTime prettyTime = new PrettyTime(locale);
        prettyTime.clearUnits();
        for (ResourcesTimeUnit unit : units) {
            prettyTime.registerUnit(unit, new ResourcesTimeFormat(unit));
        }
        return prettyTime;
    }

}
