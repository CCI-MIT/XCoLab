package org.xcolab.view.theme;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;

public class CredentialVariables {
    public String googleAnalyticsKey;
    public String pingdomRumId;
    public String typekitId;
    public String typekitIdLocal;

    public CredentialVariables() {
        this.googleAnalyticsKey = ConfigurationAttributeKey.GOOGLE_ANALYTICS_KEY.get();
        this.pingdomRumId = ConfigurationAttributeKey.PINGDOM_RUM_ID.get();
        this.typekitId = ConfigurationAttributeKey.TYPEKIT_KIT_ID.get();
        this.typekitIdLocal = ConfigurationAttributeKey.TYPEKIT_KIT_ID_LOCALHOST.get();
    }
}
