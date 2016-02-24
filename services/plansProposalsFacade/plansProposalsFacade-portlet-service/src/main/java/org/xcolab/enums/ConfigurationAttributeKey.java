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
    COLAB_URL(AttributeType.STRING);

    private final AttributeType type;

    ConfigurationAttributeKey(AttributeType type) {
        this.type = type;
    }

    public AttributeType getType() {
        return type;
    }
}
