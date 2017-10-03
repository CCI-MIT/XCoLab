package org.xcolab.view.config.spring.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("tomcat")
public class TomcatProperties {

    private final Ajp ajp = new Ajp();

    public Ajp getAjp() {
        return ajp;
    }

    public static class Ajp {

        /**
         * Enable tomcat AJP connector.
         */
        private boolean enabled = true;
        /**
         * Change port for the AJP connector.
         */
        private int port = 18010;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }
}
