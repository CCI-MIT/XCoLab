package org.xcolab.client.admin.enums;

import org.xcolab.util.attributes.AttributeGetter;

import java.io.File;

public final class PlatformAttributeKey {

    private PlatformAttributeKey() {
    }

    public static final AttributeGetter<String> PLATFORM_COLAB_URL =
            PlatformAttributes.newStringAttribute("xcolab.platform.url")
                    .withCache()
                    .defaultValue(ConfigurationAttributeKey.COLAB_URL)
                    .build();

    public static final AttributeGetter<ServerEnvironment> PLATFORM_SERVER_ENVIRONMENT =
            PlatformAttributes.newEnumAttribute("environment", ServerEnvironment.class)
                    .withCache()
                    .defaultValue(ServerEnvironment.UNKNOWN)
                    .build();

    public static final AttributeGetter<String> PLATFORM_UPLOADED_IMAGE_DOMAIN =
            PlatformAttributes.newStringAttribute("xcolab.platform.images.uploaded.domain")
                    .withCache()
                    .defaultValue("")
                    .build();

    public static final AttributeGetter<String> PLATFORM_STATIC_IMAGE_DOMAIN =
            PlatformAttributes.newStringAttribute("xcolab.platform.images.static.domain")
                    .withCache()
                    .defaultValue("")
                    .build();

    public static final AttributeGetter<String> PLATFORM_SCRIPT_DOMAIN =
            PlatformAttributes.newStringAttribute("xcolab.platform.scripts.domain")
                    .withCache()
                    .defaultValue("")
                    .build();

    public static final AttributeGetter<String> FILES_UPLOAD_DIR =
            PlatformAttributes.newStringAttribute("files.upload.dir")
                    .withCache()
                    .defaultValue(System.getProperty("java.io.tmpdir")
                            + File.separator + "xcolab-images" + File.separator)
                    .build();
}
