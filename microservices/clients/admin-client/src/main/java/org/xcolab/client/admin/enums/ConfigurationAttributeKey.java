package org.xcolab.client.admin.enums;

import org.xcolab.client.admin.AdminClient;
import org.xcolab.client.admin.exceptions.ConfigurationAttributeNotFoundException;

public enum ConfigurationAttributeKey {
    ADMIN_EMAIL(AttributeType.STRING, Optionality.REQUIRED),
    ADMIN_FROM_EMAIL(AttributeType.STRING, Optionality.REQUIRED),
    COLAB_NAME(AttributeType.STRING, Optionality.REQUIRED),
    COLAB_SHORT_NAME(AttributeType.STRING, Optionality.REQUIRED),
    COLAB_URL(AttributeType.STRING, Optionality.REQUIRED),
    IS_PRODUCTION_ENVIRONMENT(AttributeType.BOOLEAN, Optionality.REQUIRED),

    DEFAULT_CONTEST_TYPE_ID(AttributeType.NUMERIC, Optionality.REQUIRED),
    GENERATE_SCREEN_NAME(AttributeType.BOOLEAN, Optionality.REQUIRED),
    DEFAULT_TIME_ZONE_ID(AttributeType.STRING, Optionality.REQUIRED),
    SHOW_CONTESTS_DISPLAY_OPTIONS(AttributeType.BOOLEAN, Optionality.REQUIRED),

    GOOGLE_ANALYTICS_KEY(AttributeType.STRING, Optionality.REQUIRED),

    GOOGLE_AUTH_CLIENT_ID(AttributeType.STRING, Optionality.REQUIRED),
    GOOGLE_AUTH_CLIENT_SECRET(AttributeType.STRING, Optionality.REQUIRED),
    FACEBOOK_APPLICATION_ID(AttributeType.STRING, Optionality.REQUIRED),
    FACEBOOK_APPLICATION_SECRET(AttributeType.STRING, Optionality.REQUIRED),
    FACEBOOK_VERIFIED_REQUIRED(AttributeType.BOOLEAN, Optionality.REQUIRED),

    IS_MY_EMMA_ACTIVE(AttributeType.BOOLEAN, Optionality.REQUIRED),
    MY_EMMA_ACCOUNT_ID(AttributeType.STRING, Optionality.REQUIRED),
    MY_EMMA_GROUP_ID(AttributeType.STRING, Optionality.REQUIRED),
    MY_EMMA_PUBLIC_API_KEY(AttributeType.STRING, Optionality.REQUIRED),
    MY_EMMA_PRIVATE_API_KEY(AttributeType.STRING, Optionality.REQUIRED),

    IMAGE_UPLOAD_EXTERNAL_SERVICE_URL(AttributeType.STRING, Optionality.REQUIRED),
    IMAGE_UPLOAD_HELP_TEXT(AttributeType.STRING, Optionality.REQUIRED),

    BETA_RIBBON_SHOW(AttributeType.BOOLEAN, Optionality.REQUIRED),

    PUBLISH_JUDGING_RESULTS(AttributeType.BOOLEAN, Optionality.REQUIRED),
    IS_POINTS_ACTIVE(AttributeType.BOOLEAN, Optionality.REQUIRED),

    FLAGGING_ALLOW_MEMBERS(AttributeType.BOOLEAN, Optionality.REQUIRED),

    MIT_HEADER_BAR_SHOW(AttributeType.BOOLEAN, Optionality.REQUIRED),
    MIT_HEADER_BAR_LINK_TEXT(AttributeType.STRING, Optionality.REQUIRED),
    MIT_HEADER_BAR_LINK_URL(AttributeType.STRING, Optionality.REQUIRED),

    SHOW_CONTEST_COUNTDOWN(AttributeType.BOOLEAN, Optionality.REQUIRED),

    FILTER_PROFANITY(AttributeType.BOOLEAN, Optionality.REQUIRED),
    SHARED_COLAB_PORT(AttributeType.STRING, Optionality.REQUIRED),
    SHARED_COLAB_LOCATION(AttributeType.STRING, Optionality.REQUIRED),
    PARTNER_COLAB_NAME(AttributeType.STRING, Optionality.REQUIRED),
    PARTNER_COLAB_LOCATION(AttributeType.STRING, Optionality.REQUIRED),
    PARTNER_COLAB_PORT(AttributeType.STRING, Optionality.REQUIRED),
    IS_SHARED_COLAB(AttributeType.BOOLEAN, Optionality.REQUIRED),

    OPEN_GRAPH_SHARE_TITLE(AttributeType.STRING, Optionality.REQUIRED),
    OPEN_GRAPH_SHARE_DESCRIPTION(AttributeType.STRING, Optionality.REQUIRED),

    LOGIN_INFO_MESSAGE(AttributeType.STRING, Optionality.OPTIONAL);

    private final AttributeType type;
    private final Optionality optionality;

    ConfigurationAttributeKey(AttributeType type, Optionality optionality) {
        this.type = type;
        this.optionality = optionality;
    }

    public AttributeType getType() {
        return type;
    }

    public String getStringValue() {
        if (type != AttributeType.STRING) {
            throw new UnsupportedOperationException("Cannot retrieve String value from non-string attribute; "
                    + "attribute type = " + type.name());
        }
        try {
            return AdminClient.getConfigurationAttribute(this).getStringValue();
        } catch (ConfigurationAttributeNotFoundException e) {
            if (optionality == Optionality.OPTIONAL) {
                return "";
            }
            throw e;
        }
    }

    public long getLongValue() {
        if (type != AttributeType.NUMERIC) {
            throw new UnsupportedOperationException("Cannot retrieve numeric value from non-numeric attribute; "
                    + "attribute type = " + type.name());
        }
        try {
            return AdminClient.getConfigurationAttribute(this).getNumericValue();
        } catch (ConfigurationAttributeNotFoundException e) {
            if (optionality == Optionality.OPTIONAL) {
                return 0L;
            }
            throw e;
        }
    }

    public boolean getBooleanValue() {
        if (type != AttributeType.BOOLEAN) {
            throw new UnsupportedOperationException("Cannot retrieve Boolean value from non-boolean attribute; "
                    + "attribute type = " + type.name());
        }
        try {
            return AdminClient.getConfigurationAttribute(this).getNumericValue() > 0;
        } catch (ConfigurationAttributeNotFoundException e) {
            if (optionality == Optionality.OPTIONAL) {
                return false;
            }
            throw e;
        }
    }

    public double getDoubleValue() {
        if (type != AttributeType.REAL) {
            throw new UnsupportedOperationException("Cannot retrieve Double value from non-real attribute; "
                    + "attribute type = " + type.name());
        }
        try {
            return AdminClient.getConfigurationAttribute(this).getRealValue();
        } catch (ConfigurationAttributeNotFoundException e) {
            if (optionality == Optionality.OPTIONAL) {
                return 0.0;
            }
            throw e;
        }
    }

    private enum AttributeType {
        NUMERIC,
        BOOLEAN,
        STRING,
        REAL
    }

    private enum Optionality {
        REQUIRED,
        OPTIONAL
    }
}
