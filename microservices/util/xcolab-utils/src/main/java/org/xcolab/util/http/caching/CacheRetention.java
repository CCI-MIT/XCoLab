package org.xcolab.util.http.caching;

import org.ehcache.expiry.Duration;

import java.util.concurrent.TimeUnit;

public enum CacheRetention {
    NONE(0, Duration.ZERO),
    REQUEST(100, Duration.of(3, TimeUnit.SECONDS)),
    SHORT(100L, Duration.of(30, TimeUnit.SECONDS)),
    MEDIUM(100L, Duration.of(10, TimeUnit.MINUTES)),
    LONG(100L, Duration.of(1, TimeUnit.HOURS)),
    RUNTIME(50L, Duration.INFINITE);

    private final long numberOfEntries;
    private final Duration duration;

    CacheRetention(long numberOfEntries, Duration duration) {
        this.numberOfEntries = numberOfEntries;
        this.duration = duration;
    }

    public long getNumberOfEntries() {
        return numberOfEntries;
    }

    public Duration getDuration() {
        return duration;
    }
}
