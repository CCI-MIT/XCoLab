package org.xcolab.mailhandler.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("mail")
public class MailProperties {

    /**
     * A list of domains with their respective mappings.
     *
     * One mailhandler instance can handle several domains. All emails received at the user name
     * for a certain domain will be forwarded to all recipients. For example:
     *
     * mail:
     *  domains:
     *    - domain: example.com
     *      mappings:
     *      - admin:
     *      - admin1@example.com
     *      - admin2@example.com
     *
     */
    private List<Domain> domains;

    private final SmtpProperties smtp = new SmtpProperties();

    private final SpamSettings spam = new SpamSettings();

    public SmtpProperties getSmtp() {
        return smtp;
    }

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }

    public SpamSettings getSpam() {
        return spam;
    }

    public static class SpamSettings {

        /**
         * Show warnings when a message is considered spam by the filter.
         */
        private boolean showWarning = false;

        /**
         * Show spam score when sending a message with a warning.
         */
        private boolean showScoreOnWarning = true;

        /**
         * Show spam report when sending a message with a warning.
         */
        private boolean showReportOnWarning = false;

        /**
         * Don't forward messages messages above the filter threshold.
         */
        private boolean filter = false;

        /**
         * Threshold for showing warnings, if active.
         */
        private float warningThreshold = 4.0f;

        /**
         * Threshold for filtering messages, if active.
         */
        private float filterThreshold = 5.0f;

        public boolean isShowWarning() {
            return showWarning;
        }

        public void setShowWarning(boolean showWarning) {
            this.showWarning = showWarning;
        }

        public boolean isShowReportOnWarning() {
            return showReportOnWarning;
        }

        public void setShowReportOnWarning(boolean showReportOnWarning) {
            this.showReportOnWarning = showReportOnWarning;
        }

        public boolean isFilter() {
            return filter;
        }

        public void setFilter(boolean filter) {
            this.filter = filter;
        }

        public boolean isShowScoreOnWarning() {
            return showScoreOnWarning;
        }

        public void setShowScoreOnWarning(boolean showScoreOnWarning) {
            this.showScoreOnWarning = showScoreOnWarning;
        }

        public float getWarningThreshold() {
            return warningThreshold;
        }

        public void setWarningThreshold(float warningThreshold) {
            this.warningThreshold = warningThreshold;
        }

        public float getFilterThreshold() {
            return filterThreshold;
        }

        public void setFilterThreshold(float filterThreshold) {
            this.filterThreshold = filterThreshold;
        }
    }

    public static class Domain {

        private String domain;
        private List<Mapping> mappings;

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public List<Mapping> getMappings() {
            return mappings;
        }

        public void setMappings(List<Mapping> mappings) {
            this.mappings = mappings;
        }
    }

    public static class Mapping {

        /**
         * Username for this mapping (in relation to the parent domain).
         */
        private String username;

        /**
         * Recipients for emails addressed to this username.
         */
        private List<String> recipients;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<String> getRecipients() {
            return recipients;
        }

        public void setRecipients(List<String> recipients) {
            this.recipients = recipients;
        }
    }

    public static class SmtpProperties {

        /**
         * Hostname of the SMTP server.
         */
        private String host = "localhost";

        /**
         * Port of the SMTP server.
         */
        private int port = 25;

        /**
         * User on the SMTP server, if any.
         */
        private String user;

        /**
         * Password for the user, if any.
         */
        private String pass;

        /**
         * Transport strategy for reaching the SMTP server.
         *
         * Default is plain SMTP, use TLS or SSL for more secure transport.
         */
        private String transport = "SMTP";

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }

        public String getTransport() {
            return transport;
        }

        public void setTransport(String transport) {
            this.transport = transport;
        }
    }
}
