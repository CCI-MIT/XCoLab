package org.xcolab.client.admin.attributes.platform;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.admin.enums.ServerEnvironment;
import org.xcolab.commons.attributes.AttributeGetter;
import org.xcolab.commons.attributes.transformers.AttributeTransformers;
import org.xcolab.commons.attributes.wrappers.TransformedAttribute;

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


    //= Credentials

    public static final AttributeGetter<String> GOOGLE_RECAPTCHA_SITE_KEY =
            PlatformAttributes.newStringAttribute("google.recaptcha.site-key")
                    .defaultValue("")
                    .build();
    public static final AttributeGetter<String> GOOGLE_RECAPTCHA_SITE_SECRET_KEY =
            PlatformAttributes.newStringAttribute("google.recaptcha.secret-key")
                    .defaultValue("")
                    .build();
    public static final AttributeGetter<Boolean> GOOGLE_RECAPTCHA_IS_ACTIVE =
            PlatformAttributes.newBooleanAttribute("google.recaptcha.enabled")
                    .defaultValue(TransformedAttribute.of(GOOGLE_RECAPTCHA_SITE_KEY,
                            StringUtils::isNotEmpty))
                    .build();

    public static final AttributeGetter<Boolean> GOOGLE_RECAPTCHA_IS_ACTIVE_FOR_COMMENTS =
            PlatformAttributes.newBooleanAttribute("google.recaptcha.comments.enabled")
                    .defaultValue(false)
                    .build();

    public static final AttributeGetter<String> SENTRY_BACKEND_DSN =
            PlatformAttributes.newStringAttribute("sentry.backend.dsn")
                    .defaultValue("")
                    .build();

    public static final AttributeGetter<String> SENTRY_FRONTEND_DSN_PUBLIC =
            PlatformAttributes.newStringAttribute("sentry.frontend.dsn-public")
                    .defaultValue("")
                    .build();
}
