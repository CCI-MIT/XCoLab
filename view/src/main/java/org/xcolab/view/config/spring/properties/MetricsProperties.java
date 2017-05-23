package org.xcolab.view.config.spring.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties("metrics")
public class MetricsProperties {

    private final ReportingConfig reporting = new ReportingConfig();

    public ReportingConfig getReporting() {
        return reporting;
    }

    public static class ReportingConfig {

        private final LibratoConfig librato = new LibratoConfig();

        private final DatadogConfig datadog = new DatadogConfig();

        /**
         * Included prefixes. By default, all prefixes are reported.
         */
        private final List<String> includes = new ArrayList<>();

        /**
         * Excluded prefixes. If includes is non-empty, this list overrides it.
         */
        private final List<String> excludes = new ArrayList<>();

        public LibratoConfig getLibrato() {
            return librato;
        }

        public DatadogConfig getDatadog() {
            return datadog;
        }

        public List<String> getIncludes() {
            return includes;
        }

        public List<String> getExcludes() {
            return excludes;
        }

        public static class LibratoConfig {

            /**
             * Enable the Librato metric reporter.
             */
            private boolean enabled = false;

            /**
             * Email for Librato metric reporter.
             */
            private String email;

            /**
             * API token to the Librato metric reporter.
             */
            private String apiToken;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getApiToken() {
                return apiToken;
            }

            public void setApiToken(String apiToken) {
                this.apiToken = apiToken;
            }
        }

        public static class DatadogConfig {

            /**
             * Enable the Datadog reporter.
             */
            private boolean enabled = false;

            /**
             * API key for the Datadog metric reporter.
             */
            private String apiKey;

            public boolean isEnabled() {
                return enabled;
            }

            public void setEnabled(boolean enabled) {
                this.enabled = enabled;
            }

            public String getApiKey() {
                return apiKey;
            }

            public void setApiKey(String apiKey) {
                this.apiKey = apiKey;
            }
        }
    }

}
