package org.xcolab.view.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {

    @Value("${server.use-forward-headers:false}")
    private boolean useForwardHeaders;

    @Value("${tomcat.ajp.enabled:false}")
    private boolean tomcatAjpEnabled;

    @Value("${tomcat.ajp.port:18010}")
    private int tomcatAjpPort;

    public boolean isUseForwardHeaders() {
        return useForwardHeaders;
    }

    public boolean isTomcatAjpEnabled() {
        return tomcatAjpEnabled;
    }

    public int getTomcatAjpPort() {
        return tomcatAjpPort;
    }
}
