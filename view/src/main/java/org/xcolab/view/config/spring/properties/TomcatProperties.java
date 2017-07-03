package org.xcolab.view.config.spring.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("tomcat")
public class TomcatProperties {

    /**
     * Enable tomcat AJP connector.
     */
    private boolean tomcatAjpEnabled = true;

    /**
     * Change port for the AJP connector.
     */
    private int tomcatAjpPort = 18010;

    public boolean isTomcatAjpEnabled() {
        return tomcatAjpEnabled;
    }

    public int getTomcatAjpPort() {
        return tomcatAjpPort;
    }

    public void setTomcatAjpEnabled(boolean tomcatAjpEnabled) {
        this.tomcatAjpEnabled = tomcatAjpEnabled;
    }

    public void setTomcatAjpPort(int tomcatAjpPort) {
        this.tomcatAjpPort = tomcatAjpPort;
    }
}
