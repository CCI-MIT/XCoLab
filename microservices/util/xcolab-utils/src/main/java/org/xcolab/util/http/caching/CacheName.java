package org.xcolab.util.http.caching;

public enum CacheName {
    NONE(0, null),
    MISC_REQUEST(600L, CacheDuration.REQUEST),
    MISC_SHORT(200L, CacheDuration.SHORT),
    MISC_MEDIUM(400L, CacheDuration.MEDIUM),
    MISC_LONG(200L, CacheDuration.LONG),
    MISC_RUNTIME(50L, CacheDuration.RUNTIME)
    ;

    private final long numberOfEntries;
    private final CacheDuration duration;

    CacheName(long numberOfEntries, CacheDuration duration) {
        this.numberOfEntries = numberOfEntries;
        this.duration = duration;
    }

    public long getNumberOfEntries() {
        return numberOfEntries;
    }

    public CacheDuration getDuration() {
        return duration;
    }
}
