package org.xcolab.util.http.caching;

import org.ehcache.expiry.Duration;

import java.util.concurrent.TimeUnit;

public enum CacheRetention {
    NONE(0, Duration.ZERO),
    REQUEST(600L, Duration.of(3, TimeUnit.SECONDS)),
    SHORT(200L, Duration.of(5, TimeUnit.MINUTES)),
    MEDIUM(400L, Duration.of(20, TimeUnit.MINUTES)),
    LONG(200L, Duration.of(2, TimeUnit.HOURS)),
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
