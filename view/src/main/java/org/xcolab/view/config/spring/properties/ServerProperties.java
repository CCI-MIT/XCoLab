package org.xcolab.view.config.spring.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("server")
public class ServerProperties {

    /**
     *
     */
    private boolean useForwardHeaders = false;

    public boolean isUseForwardHeaders() {
        return useForwardHeaders;
    }

    public void setUseForwardHeaders(boolean useForwardHeaders) {
        this.useForwardHeaders = useForwardHeaders;
    }
}
