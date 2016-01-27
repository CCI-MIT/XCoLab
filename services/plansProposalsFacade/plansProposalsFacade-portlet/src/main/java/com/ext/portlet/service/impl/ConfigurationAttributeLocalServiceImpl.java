package com.ext.portlet.service.impl;

import com.ext.portlet.NoSuchConfigurationAttributeException;
import com.ext.portlet.model.ConfigurationAttribute;
import com.ext.portlet.service.base.ConfigurationAttributeLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.ConfigurationAttributePK;
import com.liferay.portal.kernel.exception.SystemException;
import org.xcolab.enums.AttributeType;
import org.xcolab.enums.ConfigurationAttributeKey;

/**
 * The implementation of the configuration attribute local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ConfigurationAttributeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ConfigurationAttributeLocalServiceBaseImpl
 * @see com.ext.portlet.service.ConfigurationAttributeLocalServiceUtil
 */
public class ConfigurationAttributeLocalServiceImpl
    extends ConfigurationAttributeLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ConfigurationAttributeLocalServiceUtil} to access the configuration attribute local service.
     */

    @Override
    public ConfigurationAttribute getByAttributeName(ConfigurationAttributeKey attributeKey) throws SystemException, NoSuchConfigurationAttributeException {
        return configurationAttributePersistence.findByPrimaryKey(new ConfigurationAttributePK(attributeKey.name(), 0L));
    }

    @Override
    public ConfigurationAttribute getByAttributeNameAdditionalId(ConfigurationAttributeKey attributeKey, long additionalId) throws SystemException, NoSuchConfigurationAttributeException {
        return configurationAttributePersistence.findByPrimaryKey(new ConfigurationAttributePK(attributeKey.name(), additionalId));
    }

    @Override
    public String getAttributeStringValue(ConfigurationAttributeKey attributeKey, long additionalId, String defaultValue) throws SystemException {
        try {
            return getAttributeStringValue(attributeKey, additionalId);
        } catch (NoSuchConfigurationAttributeException e) {
            return defaultValue;
        }
    }

    @Override
    public long getAttributeLongValue(ConfigurationAttributeKey attributeKey, long additionalId, long defaultValue) throws SystemException {
        try {
            return getAttributeLongValue(attributeKey, additionalId);
        } catch (NoSuchConfigurationAttributeException e) {
            return defaultValue;
        }
    }

    @Override
    public double getAttributeDoubleValue(ConfigurationAttributeKey attributeKey, long additionalId, double defaultValue) throws SystemException {
        try {
            return getAttributeDoubleValue(attributeKey, additionalId);
        } catch (NoSuchConfigurationAttributeException e) {
            return defaultValue;
        }
    }


    public String getAttributeStringValue(ConfigurationAttributeKey attributeKey, long additionalId) throws SystemException, NoSuchConfigurationAttributeException {
        if (attributeKey.getType() != AttributeType.STRING) {
            throw new UnsupportedOperationException("Cannot retrieve String value from non-string attribute; attribute type = " + attributeKey.getType().name());
        }
        return getByAttributeNameAdditionalId(attributeKey, additionalId).getStringValue();
    }


    public long getAttributeLongValue(ConfigurationAttributeKey attributeKey, long additionalId) throws SystemException, NoSuchConfigurationAttributeException {
        if (attributeKey.getType() != AttributeType.NUMERIC) {
            throw new UnsupportedOperationException("Cannot retrieve long value from non-long attribute; attribute type = " + attributeKey.getType().name());
        }
        return getByAttributeNameAdditionalId(attributeKey, additionalId).getNumericValue();
    }


    public double getAttributeDoubleValue(ConfigurationAttributeKey attributeKey, long additionalId) throws SystemException, NoSuchConfigurationAttributeException {
        if (attributeKey.getType() != AttributeType.REAL) {
            throw new UnsupportedOperationException("Cannot retrieve double value from non-double attribute; attribute type = " + attributeKey.getType().name());
        }
        return getByAttributeNameAdditionalId(attributeKey, additionalId).getRealValue();
    }

}
