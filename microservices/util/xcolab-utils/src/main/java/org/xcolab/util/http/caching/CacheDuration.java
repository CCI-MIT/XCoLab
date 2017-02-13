package org.xcolab.util.http.caching;

import org.ehcache.expiry.Duration;

import java.util.concurrent.TimeUnit;

public enum CacheDuration {
    REQUEST(Duration.of(3, TimeUnit.SECONDS)),
    SHORT(Duration.of(5, TimeUnit.MINUTES)),
    MEDIUM(Duration.of(30, TimeUnit.MINUTES)),
    LONG(Duration.of(2, TimeUnit.HOURS)),
    DAILY(Duration.of(1, TimeUnit.DAYS)),
    RUNTIME(Duration.INFINITE);

    private final Duration duration;

    CacheDuration(Duration duration) {
        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }
}
