package org.xcolab.view.theme;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;

public class SharedColabVariables {
    final private boolean isSharedColab;

    private String partnerColabName; // TODO: check if these are unsued and remove
    private String partnerColabImgsAndClasses;
    private String partnerColabLogo;

    public SharedColabVariables() {
        this.isSharedColab = ConfigurationAttributeKey.IS_SHARED_COLAB.get();
        if (isSharedColab()) {
            this.partnerColabName = ConfigurationAttributeKey.PARTNER_COLAB_NAME.get();
            this.partnerColabImgsAndClasses = getPartnerColabName().replace(" ", "") + "-sketchy";
            this.partnerColabLogo = getPartnerColabImgsAndClasses() + "PartnerLogo.png";
        }
    }

    public boolean isSharedColab() {
        return isSharedColab;
    }

    public String getPartnerColabName() {
        return partnerColabName;
    }

    public String getPartnerColabImgsAndClasses() {
        return partnerColabImgsAndClasses;
    }

    public String getPartnerColabLogo() {
        return partnerColabLogo;
    }
}
