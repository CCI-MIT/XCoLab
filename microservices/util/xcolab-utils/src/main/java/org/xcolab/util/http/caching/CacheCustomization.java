package org.xcolab.util.http.caching;

public class CacheCustomization {

    /**
     * Enable this cache.
     */
    private boolean enabled = true;

    /**
     * Maximum number of entities in the cache.
     */
    private long size;

    /**
     * Expiration time (Time to live) of the cache entities.
     */
    private CacheDuration ttl;

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public CacheDuration getTtl() {
        return ttl;
    }

    public void setTtl(CacheDuration ttl) {
        this.ttl = ttl;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
