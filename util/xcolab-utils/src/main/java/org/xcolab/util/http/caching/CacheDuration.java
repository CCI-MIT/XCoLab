package org.xcolab.util.http.caching;

import org.ehcache.expiry.Duration;

import java.util.concurrent.TimeUnit;

public enum CacheDuration {
    REQUEST(3, TimeUnit.SECONDS),
    SHORT(5, TimeUnit.MINUTES),
    MEDIUM(30, TimeUnit.MINUTES),
    LONG(2, TimeUnit.HOURS),
    DAILY(1, TimeUnit.DAYS),
    RUNTIME(0, null);

    private final long value;
    private final TimeUnit unit;

    CacheDuration(long value, TimeUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public Duration getDuration() {
        if (value == 0) {
            return Duration.INFINITE;
        }
        return Duration.of(value, unit);
    }

    public long toSeconds() {
        if (value == 0 || unit == null) {
            return TimeUnit.DAYS.toSeconds(14);
        }
        return unit.toSeconds(value);
    }
}
