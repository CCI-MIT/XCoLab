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

    /**
     * Optional URL to a CDN that will be prepended to all images and scripts.
     */
    public static final AttributeGetter<String> CDN_URL =
            PlatformAttributes.newStringAttribute("xcolab.platform.cdn")
                    .map(AttributeTransformers.addDefaultScheme())
                    .withCache()
                    .defaultValue("")
                    .build();

    /**
     * Override to provide a specific CDN url for uploaded images.
     *
     * By default, the value from {@link #CDN_URL} will be used.
     */
    public static final AttributeGetter<String> CDN_URL_IMAGES_UPLOADED =
            PlatformAttributes.newStringAttribute("xcolab.platform.cdn.uploaded-images")
                    .map(AttributeTransformers.addDefaultScheme())
                    .withCache()
                    .defaultValue(CDN_URL)
                    .build();

    /**
     * Override to provide a specific CDN url for static images.
     *
     * By default, the value from {@link #CDN_URL} will be used.
     */
    public static final AttributeGetter<String> CDN_URL_IMAGES_STATIC =
            PlatformAttributes.newStringAttribute("xcolab.platform.cdn.static-images")
                    .map(AttributeTransformers.addDefaultScheme())
                    .withCache()
                    .defaultValue(CDN_URL)
                    .build();

    /**
     * Override to provide a specific CDN url for scripts (JS & CSS).
     *
     * By default, the value from {@link #CDN_URL} will be used.
     */
    public static final AttributeGetter<String> CDN_URL_SCRIPTS =
            PlatformAttributes.newStringAttribute("xcolab.platform.cdn.scripts")
                    .map(AttributeTransformers.addDefaultScheme())
                    .withCache()
                    .defaultValue(CDN_URL)
                    .build();

    public static final AttributeGetter<String> ANALYTICS_PRIVATE_KEY_PATH =
            PlatformAttributes.newStringAttribute("analytics.privatekey.path")
                    .withCache()
                    .defaultValue("")
                    .build();

    public static final AttributeGetter<String> FILES_UPLOAD_DIR =
            PlatformAttributes.newStringAttribute("files.upload.dir")
                    .withCache()
                    .defaultValue(System.getProperty("java.io.tmpdir")
                            + File.separator + "xcolab-images" + File.separator)
                    .build();

    //TODO COLAB-2681: Remove once responsive design is finished
    public static final AttributeGetter<Boolean> LAYOUT_IS_RESPONSIVE =
            PlatformAttributes.newBooleanAttribute("layout.is-responsive")
                    .withCache()
                    .defaultValue(false)
                    .build();
}
