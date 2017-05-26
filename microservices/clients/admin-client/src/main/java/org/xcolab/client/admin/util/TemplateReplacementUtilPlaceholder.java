package org.xcolab.client.admin.util;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.admin.enums.PlatformAttributeKey;

//TODO: move entire TemplateReplacementUtil here
public final class TemplateReplacementUtilPlaceholder {

    private final static String COLAB_NAME_PLACEHOLDER = "<colab-name/>";
    private final static String COLAB_SHORT_NAME_PLACEHOLDER = "<colab-short-name/>";
    private final static String COLAB_URL = "<colab-url/>";

    private final static String ADMIN_EMAIL_PLACEHOLDER = "<admin-email/>";
    private final static String ADMIN_FROM_EMAIL_PLACEHOLDER = "<admin-from-email/>";

    private TemplateReplacementUtilPlaceholder() {
    }

    public static String replacePlatformConstants(String text) {
        final String colabName = ConfigurationAttributeKey.COLAB_NAME.get();
        final String colabShortName = ConfigurationAttributeKey.COLAB_SHORT_NAME.get();
        final String colabUrl = PlatformAttributeKey.PLATFORM_COLAB_URL.get();
        final String adminEmail = ConfigurationAttributeKey.ADMIN_EMAIL.get();
        final String adminFromEmail = ConfigurationAttributeKey.ADMIN_FROM_EMAIL.get();

        return text.replaceAll(COLAB_NAME_PLACEHOLDER, colabName)
                .replaceAll(COLAB_SHORT_NAME_PLACEHOLDER, colabShortName)
                .replaceAll(COLAB_URL, colabUrl)
                .replaceAll(ADMIN_EMAIL_PLACEHOLDER, adminEmail)
                .replaceAll(ADMIN_FROM_EMAIL_PLACEHOLDER, adminFromEmail);
    }

    public static String getAdminFromEmail() {
        return ConfigurationAttributeKey.ADMIN_FROM_EMAIL.get();
    }
}
