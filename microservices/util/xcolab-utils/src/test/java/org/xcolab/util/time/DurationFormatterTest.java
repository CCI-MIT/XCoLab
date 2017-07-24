package org.xcolab.util.time;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

public class DurationFormatterTest {

    private final DurationFormatter durationFormatter = DurationFormatter.forLocale(Locale.ENGLISH);

    private static Instant inTwoHours;
    private static Instant twoHoursAgo;
    private static Instant inTwoDays;
    private static Instant twoDaysAgo;
    private static Instant inTwoMonths;
    private static Instant twoMonthsAgo;

    @BeforeClass
    public static void setUp() {
        final Instant now = Instant.now();
        inTwoHours = now.plus(2, ChronoUnit.HOURS);
        twoHoursAgo = now.minus(2, ChronoUnit.HOURS);
        inTwoDays = now.plus(2, ChronoUnit.DAYS);
        twoDaysAgo = now.minus(2, ChronoUnit.DAYS);
        inTwoMonths = now.plus(60, ChronoUnit.DAYS);
        twoMonthsAgo = now.minus(60, ChronoUnit.DAYS);
    }

    @AfterClass
    public static void tearDown() {
        inTwoHours = null;
        twoHoursAgo = null;
        inTwoDays = null;
        twoDaysAgo = null;
        inTwoMonths = null;
        twoMonthsAgo = null;
    }

    @Test
    public void format__givenDate__shouldFormatInLargestUnit() {
        assertEquals("Future format for hours wrong",
                "2 hours from now", durationFormatter.format(inTwoHours));
        assertEquals("Past format for hours wrong",
                "2 hours ago", durationFormatter.format(twoHoursAgo));
        assertEquals("Future format for days wrong",
                "2 days from now", durationFormatter.format(inTwoDays));
        assertEquals("Past format for days wrong",
                "2 days ago", durationFormatter.format(twoDaysAgo));
        assertEquals("Future format for months wrong",
                "2 months from now", durationFormatter.format(inTwoMonths));
        assertEquals("Past format for months wrong",
                "2 months ago", durationFormatter.format(twoMonthsAgo));
    }

    @Test
    public void formatAsDays__givenMoreThan30Days__shouldFormatInDays() {
        assertEquals("Future format for months wrong",
                "60 days from now", durationFormatter.formatAsDays(inTwoMonths));
        assertEquals("Past format for months wrong",
                "60 days ago", durationFormatter.formatAsDays(twoMonthsAgo));
    }

    @Test
    public void formatDifference__givenDate__shouldFormatInLargestUnit() {
        assertEquals("Future format for hours wrong",
                "2 hours", durationFormatter.formatDifference(inTwoHours));
        assertEquals("Future format for days wrong",
                "2 days", durationFormatter.formatDifference(inTwoDays));
        assertEquals("Future format for months wrong",
                "2 months", durationFormatter.formatDifference(inTwoMonths));
    }

    @Test
    public void formatDifferenceAsDays__givenMoreThan30Days__shouldFormatInDays() {
        assertEquals("Future format for months wrong",
                "60 days", durationFormatter.formatDifferenceAsDays(inTwoMonths));
    }

}
