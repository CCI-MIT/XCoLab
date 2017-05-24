package org.xcolab.client.admin.enums;

import org.xcolab.util.attributes.AttributeGetter;

public final class PlatformAttributeKey {

    private PlatformAttributeKey() {
    }

    public static final AttributeGetter<String> PLATFORM_COLAB_URL =
            PlatformAttributes.newStringAttribute("xcolab.platform.url")
                    .withCache()
                    .defaultValue(ConfigurationAttributeKey.COLAB_URL)
                    .build();

    public static final AttributeGetter<String> PLATFORM_USER_IMAGE_DOMAIN =
            PlatformAttributes.newStringAttribute("xcolab.platform.images.user.domain")
                    .withCache()
                    .defaultValue("")
                    .build();

    public static final AttributeGetter<String> PLATFORM_THEME_IMAGE_DOMAIN =
            PlatformAttributes.newStringAttribute("xcolab.platform.images.theme.domain")
                    .withCache()
                    .defaultValue("")
                    .build();
}
