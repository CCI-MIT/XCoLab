package org.xcolab.mailhandler;

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

    private final EmailAddress from = new EmailAddress();

    private final SmtpProperties smtp = new SmtpProperties();

    public SmtpProperties getSmtp() {
        return smtp;
    }

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }

    public EmailAddress getFrom() {
        return from;
    }

    public static class EmailAddress {

        /**
         * Name of the owner of this email address.
         */
        private String name;

        /**
         * The email address.
         */
        private String email;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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
