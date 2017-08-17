package org.xcolab.client.admin.attributes.platform;

import org.xcolab.client.admin.enums.ServerEnvironment;
import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.attributes.transformers.AttributeTransformers;

import java.io.File;

public final class PlatformAttributeKey {

    private PlatformAttributeKey() {
    }

    public static final AttributeGetter<String> COLAB_URL =
            PlatformAttributes.newStringAttribute("xcolab.platform.url")
                    .map(AttributeTransformers.addDefaultScheme())
                    .withCache()
                    .build();

    public static final AttributeGetter<ServerEnvironment> SERVER_ENVIRONMENT =
            PlatformAttributes.newEnumAttribute("environment", ServerEnvironment.class)
                    .withCache()
                    .defaultValue(ServerEnvironment.UNKNOWN)
                    .build();

    public static final AttributeGetter<String> IMAGES_UPLOADED_DOMAIN =
            PlatformAttributes.newStringAttribute("xcolab.platform.images.uploaded.domain")
                    .map(AttributeTransformers.addDefaultScheme())
                    .withCache()
                    .defaultValue("")
                    .build();

    public static final AttributeGetter<String> IMAGES_STATIC_DOMAIN =
            PlatformAttributes.newStringAttribute("xcolab.platform.images.static.domain")
                    .map(AttributeTransformers.addDefaultScheme())
                    .withCache()
                    .defaultValue("")
                    .build();

    public static final AttributeGetter<String> SCRIPTS_DOMAIN =
            PlatformAttributes.newStringAttribute("xcolab.platform.scripts.domain")
                    .map(AttributeTransformers.addDefaultScheme())
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
