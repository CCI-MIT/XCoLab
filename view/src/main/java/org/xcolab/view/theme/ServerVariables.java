package org.xcolab.view.theme;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.admin.enums.ServerEnvironment;
import org.xcolab.commons.servlet.ManifestUtil;

import javax.servlet.ServletContext;

public class ServerVariables {

    private final ServerEnvironment environment;
    private final String buildCommit;

    public ServerVariables(ServletContext servletContext) {
        environment = PlatformAttributeKey.SERVER_ENVIRONMENT.get();
        buildCommit = ManifestUtil.getBuildCommit(servletContext).orElse("unknown");
    }

    public ServerEnvironment getEnvironment() {
        return environment;
    }

    public String getBuildCommit() {
        return buildCommit;
    }
}
