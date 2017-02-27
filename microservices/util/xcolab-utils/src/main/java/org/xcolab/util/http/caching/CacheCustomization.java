package org.xcolab.util.http.caching;

public class CacheCustomization {

    /**
     * Enable this cache.
     */
    private boolean enabled = true;

    /**
     * Maximum number of entities in the cache.
     */
    private int entries;

    /**
     * Expiration time (Time to live) of the cache entities.
     */
    private CacheDuration duration;

    /**
     * Eviction policy, should be one of LFU, LRU, FIFO.
     */
    private String evictionPolicy;

    private final DiskStorage diskStorage = new DiskStorage();

    public int getEntries() {
        return entries;
    }

    public void setEntries(int entries) {
        this.entries = entries;
    }

    public CacheDuration getDuration() {
        return duration;
    }

    public void setDuration(CacheDuration duration) {
        this.duration = duration;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public DiskStorage getDiskStorage() {
        return diskStorage;
    }

    public String getEvictionPolicy() {
        return evictionPolicy;
    }

    public void setEvictionPolicy(String evictionPolicy) {
        this.evictionPolicy = evictionPolicy;
    }

    public static class DiskStorage {

        /**
         * Enable disk storage tier.
         */
        private boolean enabled = false;

        /**
         * Maximum size of disk cache (unit specified in separate property).
         */
        private int maxMegabytes = 1;

        /**
         * Path to store disk persistence file.
         *
         * Can use one of the following system properties:
         * user.home, user.dir, java.io.tmpdir
         *
         * Note: cannot be configured for individual caches
         */
        private String path = "java.io.tmpdir";

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public int getMaxMegabytes() {
            return maxMegabytes;
        }

        public void setMaxMegabytes(int maxMegabytes) {
            this.maxMegabytes = maxMegabytes;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
