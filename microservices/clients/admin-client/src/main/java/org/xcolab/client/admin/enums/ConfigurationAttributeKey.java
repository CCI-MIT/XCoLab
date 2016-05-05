package org.xcolab.client.admin.enums;

import org.xcolab.client.admin.AdminClient;

public enum ConfigurationAttributeKey {
    ADMIN_EMAIL(AttributeType.STRING),
    ADMIN_FROM_EMAIL(AttributeType.STRING),
    COLAB_NAME(AttributeType.STRING),
    COLAB_SHORT_NAME(AttributeType.STRING),
    COLAB_URL(AttributeType.STRING),

    IS_PRODUCTION_ENVIRONMENT(AttributeType.BOOLEAN),

    DEFAULT_CONTEST_TYPE_ID(AttributeType.NUMERIC),
    GENERATE_SCREEN_NAME(AttributeType.BOOLEAN),
    DEFAULT_TIME_ZONE_ID(AttributeType.STRING),
    SHOW_CONTESTS_DISPLAY_OPTIONS(AttributeType.BOOLEAN),

    GOOGLE_AUTH_CLIENT_ID(AttributeType.STRING),
    GOOGLE_AUTH_CLIENT_SECRET(AttributeType.STRING),

    MY_EMMA_ACCOUNT_ID(AttributeType.STRING),
    MY_EMMA_GROUP_ID(AttributeType.STRING),
    MY_EMMA_PUBLIC_API_KEY(AttributeType.STRING),
    MY_EMMA_PRIVATE_API_KEY(AttributeType.STRING),

    IMAGE_UPLOAD_EXTERNAL_SERVICE_URL(AttributeType.STRING),
    IMAGE_UPLOAD_HELP_TEXT(AttributeType.STRING),

    MIT_HEADER_BAR_SHOW(AttributeType.BOOLEAN),
    MIT_HEADER_BAR_LINK_TEXT(AttributeType.STRING),
    MIT_HEADER_BAR_LINK_URL(AttributeType.STRING);

    private final AttributeType type;

    ConfigurationAttributeKey(AttributeType type) {
        this.type = type;
    }

    public AttributeType getType() {
        return type;
    }

    public String getStringValue() {
        if (type != AttributeType.STRING) {
            throw new UnsupportedOperationException("Cannot retrieve String value from non-string attribute; "
                    + "attribute type = " + type.name());
        }
        return AdminClient.getConfigurationAttribute(this).getStringValue();
    }

    public long getLongValue() {
        if (type != AttributeType.NUMERIC) {
            throw new UnsupportedOperationException("Cannot retrieve numeric value from non-numeric attribute; "
                    + "attribute type = " + type.name());
        }
        return AdminClient.getConfigurationAttribute(this).getNumericValue();
    }

    public boolean getBooleanValue() {
        if (type != AttributeType.BOOLEAN) {
            throw new UnsupportedOperationException("Cannot retrieve Boolean value from non-boolean attribute; "
                    + "attribute type = " + type.name());
        }
        return AdminClient.getConfigurationAttribute(this).getNumericValue() > 0;
    }

    public double getDoubleValue() {
        if (type != AttributeType.REAL) {
            throw new UnsupportedOperationException("Cannot retrieve Double value from non-real attribute; "
                    + "attribute type = " + type.name());
        }
        return AdminClient.getConfigurationAttribute(this).getRealValue();
    }
}
