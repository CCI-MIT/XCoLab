package org.xcolab.view.theme;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;

public class SharedColabVariables {
    public boolean isSharedColab;

    public String partnerColabName; // TODO: check if these are unsued and remove
    public String partnerColabImgsAndClasses;
    public String partnerColabLogo;

    public SharedColabVariables() {
        this.isSharedColab = ConfigurationAttributeKey.IS_SHARED_COLAB.get();
        if (isSharedColab) {
            this.partnerColabName = ConfigurationAttributeKey.PARTNER_COLAB_NAME.get();
            this.partnerColabImgsAndClasses = partnerColabName.replace(" ", "") + "-sketchy";
            this.partnerColabLogo = partnerColabImgsAndClasses + "PartnerLogo.png";
        }
    }
}
