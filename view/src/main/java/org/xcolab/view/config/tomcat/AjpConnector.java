package org.xcolab.view.config.tomcat;

import org.apache.catalina.connector.Connector;

public class AjpConnector extends Connector {

    private static final String AJP_PROTOCOL = "AJP/1.3";

    public AjpConnector(int port) {
        super(AJP_PROTOCOL);
        setPort(port);
        setSecure(false);
        setAllowTrace(false);
        setScheme("http");
    }
}
