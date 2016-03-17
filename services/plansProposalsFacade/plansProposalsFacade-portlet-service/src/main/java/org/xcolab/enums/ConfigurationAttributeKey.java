package org.xcolab.enums;

/**
 * Created by johannes on 1/26/16.
 */
public enum ConfigurationAttributeKey {
    COLAB_NAME(AttributeType.STRING),
    COLAB_SHORT_NAME(AttributeType.STRING),
    ADMIN_EMAIL(AttributeType.STRING),
    ADMIN_FROM_EMAIL(AttributeType.STRING),
    DEFAULT_CONTEST_TYPE_ID(AttributeType.NUMERIC),
    COLAB_URL(AttributeType.STRING),
    GENERATE_SCREEN_NAME(AttributeType.BOOLEAN),
    DEFAULT_TIME_ZONE_ID(AttributeType.STRING),
    GOOGLE_AUTH_CLIENT_ID(AttributeType.STRING),
    GOOGLE_AUTH_CLIENT_SECRET(AttributeType.STRING),
    IMAGE_UPLOAD_EXTERNAL_SERVICE_URL(AttributeType.STRING),
    IMAGE_UPLOAD_HELP_TEXT(AttributeType.STRING),
    SHOW_CONTESTS_DISPLAY_OPTIONS(AttributeType.BOOLEAN);

    private final AttributeType type;

    ConfigurationAttributeKey(AttributeType type) {
        this.type = type;
    }

    public AttributeType getType() {
        return type;
    }
}
