package org.xcolab.util.http.caching;

public enum CacheName {
    NONE(0, null),
    MISC_REQUEST(500L, CacheDuration.REQUEST),
    MISC_SHORT(100L, CacheDuration.SHORT),
    MISC_MEDIUM(200L, CacheDuration.MEDIUM),
    MISC_LONG(100L, CacheDuration.LONG),
    MISC_RUNTIME(50L, CacheDuration.RUNTIME),

    CONTEST_DETAILS(100L, CacheDuration.DAILY),
    CONTEST_LIST(100L, CacheDuration.DAILY),
    CONTEST_ONTOLOGY(50L, CacheDuration.DAILY),
    CONFIGURATION(50L, CacheDuration.DAILY),
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
