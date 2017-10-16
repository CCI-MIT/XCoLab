package org.xcolab.util.http.caching;

public enum CacheName {
    NONE(0, null),
    MISC_REQUEST(750, CacheDuration.REQUEST),
    MISC_SHORT(50, CacheDuration.SHORT),
    MISC_MEDIUM(200, CacheDuration.MEDIUM),
    MISC_LONG(100, CacheDuration.LONG),
    MISC_RUNTIME(50, CacheDuration.RUNTIME),

    CONTEST_DETAILS(100, CacheDuration.DAILY),
    CONTEST_LIST(20, CacheDuration.DAILY),
    CONTEST_ONTOLOGY(50, CacheDuration.DAILY),
    PROPOSAL_PHASE(30, CacheDuration.MEDIUM),
    PROPOSAL_DETAILS(100, CacheDuration.MEDIUM),
    PROPOSAL_LIST(20, CacheDuration.REQUEST),
    PROPOSAL_LIST_CLOSED(20, CacheDuration.LONG),
    PROPOSAL_PICKER(20, CacheDuration.SHORT),
    CONFIGURATION(75, CacheDuration.DAILY),
    MEMBER_RATING(100, CacheDuration.SHORT),
    MEMBER(100, CacheDuration.SHORT),
    MEMBER_LIST(20, CacheDuration.MEDIUM),
    ROLES(100, CacheDuration.MEDIUM),
    CONTENT(50, CacheDuration.DAILY),
    PLATFORMTEAM(50, CacheDuration.DAILY);

    private final int numberOfEntries;
    private final CacheDuration duration;

    CacheName(int numberOfEntries, CacheDuration duration) {
        this.numberOfEntries = numberOfEntries;
        this.duration = duration;
    }

    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    public int getNumberOfEntries(CacheCustomization cacheCustomization) {
        if (cacheCustomization != null && cacheCustomization.getEntries() > 0) {
            return cacheCustomization.getEntries();
        }
        return numberOfEntries;
    }

    public CacheDuration getDuration() {
        return duration;
    }

    public CacheDuration getDuration(CacheCustomization cacheCustomization) {
        if (cacheCustomization != null && cacheCustomization.getDuration() != null) {
            return cacheCustomization.getDuration();
        }
        return duration;
    }
}
