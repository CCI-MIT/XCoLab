package org.xcolab.enums;

import com.ext.portlet.NoSuchConfigurationAttributeException;
import com.ext.portlet.service.ConfigurationAttributeLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

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

    public String getStringValue() throws SystemException {
        try {
            return ConfigurationAttributeLocalServiceUtil.getAttributeStringValue(name(), 0L);
        } catch (NoSuchConfigurationAttributeException e) {
            throw new SystemException("ConfigurationAttribute " + name() + " does not exist.");
        }
    }

    public long getLongValue() throws SystemException {
        try {
            return ConfigurationAttributeLocalServiceUtil.getAttributeLongValue(name(), 0L);
        } catch (NoSuchConfigurationAttributeException e) {
            throw new SystemException("ConfigurationAttribute " + name() + " does not exist.");
        }
    }

    public boolean getBooleanValue() throws SystemException {
        try {
            return ConfigurationAttributeLocalServiceUtil.getAttributeBooleanValue(name(), 0L);
        } catch (NoSuchConfigurationAttributeException e) {
            throw new SystemException("ConfigurationAttribute " + name() + " does not exist.");
        }
    }

    public double getDoubleValue() throws SystemException {
        try {
            return ConfigurationAttributeLocalServiceUtil.getAttributeDoubleValue(name(), 0L);
        } catch (NoSuchConfigurationAttributeException e) {
            throw new SystemException("ConfigurationAttribute " + name() + " does not exist.");
        }
    }
}
