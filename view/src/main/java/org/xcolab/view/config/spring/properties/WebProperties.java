package org.xcolab.view.config.spring.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("web")
public class WebProperties {

    /**
     * Settings related to browser caching of resources.
     */
    public final Cache cache = new Cache();

    /**
     * Configure whether non-members can see content on the site.
     */
    private final GuestAccess guestAccess = new GuestAccess();

    public Cache getCache() {
        return cache;
    }

    public GuestAccess getGuestAccess() {
        return guestAccess;
    }

    public static class GuestAccess {

        /**
         * Allow guest access to all pages.
         */
        private boolean allowAll = true;

        /**
         * Override to allow access to the home page even if guest access is disabled.
         */
        private boolean alwaysAllowHomepage = true;

        /**
         * Override to allow access to the about pages page even if guest access is disabled.
         */
        private boolean alwaysAllowContentPages = false;

        /**
         * If false, the platform does not allow registering new members. Existing members will
         * still be able to log in.
         */
        private boolean allowRegistration = true;

        /**
         * If false, the platform does not allow members to log in.
         */
        private boolean allowLogin = true;

        /**
         * If false, SSO is disabled;
         */
        private boolean allowSso = true;

        public boolean isAllowAll() {
            return allowAll;
        }

        public void setAllowAll(boolean allowAll) {
            this.allowAll = allowAll;
        }

        public boolean isAlwaysAllowHomepage() {
            return alwaysAllowHomepage;
        }

        public void setAlwaysAllowHomepage(boolean alwaysAllowHomepage) {
            this.alwaysAllowHomepage = alwaysAllowHomepage;
        }

        public boolean isAllowRegistration() {
            return allowRegistration;
        }

        public void setAllowRegistration(boolean allowRegistration) {
            this.allowRegistration = allowRegistration;
        }

        public boolean isAllowLogin() {
            return allowLogin;
        }

        public void setAllowLogin(boolean allowLogin) {
            this.allowLogin = allowLogin;
        }

        public boolean isAllowSso() {
            return allowSso;
        }

        public void setAllowSso(boolean allowSso) {
            this.allowSso = allowSso;
        }

        public boolean isAlwaysAllowContentPages() {
            return alwaysAllowContentPages;
        }

        public void setAlwaysAllowContentPages(boolean alwaysAllowContentPages) {
            this.alwaysAllowContentPages = alwaysAllowContentPages;
        }
    }

    public static class Cache {

        /**
         * Browser caching for theme images.
         */
        private final CacheSettings images = new CacheSettings();

        public CacheSettings getImages() {
            return images;
        }
    }

    public static class CacheSettings {

        /**
         * If false, CacheControl.noCache() is returned (validation via ETag).
         */
        private boolean active = true;

        /**
         * Determines the max age of the resource.
         */
        private long maxAgeDays = 7;

        public CacheSettings() {
        }

        public CacheSettings(boolean active, long maxAgeDays) {
            this.active = active;
            this.maxAgeDays = maxAgeDays;
        }

        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        public long getMaxAgeDays() {
            return maxAgeDays;
        }

        public void setMaxAgeDays(long maxAgeDays) {
            this.maxAgeDays = maxAgeDays;
        }
    }
}
