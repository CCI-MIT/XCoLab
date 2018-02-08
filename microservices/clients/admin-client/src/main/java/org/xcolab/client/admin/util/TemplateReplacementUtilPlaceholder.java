package org.xcolab.client.admin.util;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;

import java.util.Optional;

//TODO: move entire TemplateReplacementUtil here
public final class TemplateReplacementUtilPlaceholder {

    private final static String COLAB_NAME_PLACEHOLDER = "<colab-name/>";
    private final static String COLAB_SHORT_NAME_PLACEHOLDER = "<colab-short-name/>";
    private final static String COLAB_URL = "<colab-url/>";

    private final static String PARTNER_COLAB_NAME_PLACEHOLDER = "<partner-colab-name/>";

    private final static String ADMIN_EMAIL_PLACEHOLDER = "<admin-email/>";
    private final static String ADMIN_FROM_EMAIL_PLACEHOLDER = "<admin-from-email/>";

    private TemplateReplacementUtilPlaceholder() {
    }

    public static String replacePlatformConstants(String text) {
        final String colabName = ConfigurationAttributeKey.COLAB_NAME.get();
        final String colabShortName = ConfigurationAttributeKey.COLAB_SHORT_NAME.get();
        final String colabUrl = PlatformAttributeKey.COLAB_URL.get();
        final String adminEmail = ConfigurationAttributeKey.ADMIN_EMAIL.get();
        final String adminFromEmail = ConfigurationAttributeKey.ADMIN_FROM_EMAIL.get();
        final Optional<String> partnerColabName = ConfigurationAttributeKey.PARTNER_COLAB_NAME.getOpt();

        String ret = text.replaceAll(COLAB_NAME_PLACEHOLDER, colabName)
                .replaceAll(COLAB_SHORT_NAME_PLACEHOLDER, colabShortName)
                .replaceAll(COLAB_URL, colabUrl)
                .replaceAll(ADMIN_EMAIL_PLACEHOLDER, adminEmail)
                .replaceAll(ADMIN_FROM_EMAIL_PLACEHOLDER, adminFromEmail);

        if (partnerColabName.isPresent()) {
            ret = ret.replaceAll(PARTNER_COLAB_NAME_PLACEHOLDER, partnerColabName.get());
        }
        return ret;
    }

    public static String getAdminFromEmail() {
        return ConfigurationAttributeKey.ADMIN_FROM_EMAIL.get();
    }
}
