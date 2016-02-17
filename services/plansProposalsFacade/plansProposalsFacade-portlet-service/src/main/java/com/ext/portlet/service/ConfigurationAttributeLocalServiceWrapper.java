package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ConfigurationAttributeLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ConfigurationAttributeLocalService
 * @generated
 */
public class ConfigurationAttributeLocalServiceWrapper
    implements ConfigurationAttributeLocalService,
        ServiceWrapper<ConfigurationAttributeLocalService> {
    private ConfigurationAttributeLocalService _configurationAttributeLocalService;

    public ConfigurationAttributeLocalServiceWrapper(
        ConfigurationAttributeLocalService configurationAttributeLocalService) {
        _configurationAttributeLocalService = configurationAttributeLocalService;
    }

    /**
    * Adds the configuration attribute to the database. Also notifies the appropriate model listeners.
    *
    * @param configurationAttribute the configuration attribute
    * @return the configuration attribute that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ConfigurationAttribute addConfigurationAttribute(
        com.ext.portlet.model.ConfigurationAttribute configurationAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _configurationAttributeLocalService.addConfigurationAttribute(configurationAttribute);
    }

    /**
    * Creates a new configuration attribute with the primary key. Does not add the configuration attribute to the database.
    *
    * @param configurationAttributePK the primary key for the new configuration attribute
    * @return the new configuration attribute
    */
    @Override
    public com.ext.portlet.model.ConfigurationAttribute createConfigurationAttribute(
        com.ext.portlet.service.persistence.ConfigurationAttributePK configurationAttributePK) {
        return _configurationAttributeLocalService.createConfigurationAttribute(configurationAttributePK);
    }

    /**
    * Deletes the configuration attribute with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param configurationAttributePK the primary key of the configuration attribute
    * @return the configuration attribute that was removed
    * @throws PortalException if a configuration attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ConfigurationAttribute deleteConfigurationAttribute(
        com.ext.portlet.service.persistence.ConfigurationAttributePK configurationAttributePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _configurationAttributeLocalService.deleteConfigurationAttribute(configurationAttributePK);
    }

    /**
    * Deletes the configuration attribute from the database. Also notifies the appropriate model listeners.
    *
    * @param configurationAttribute the configuration attribute
    * @return the configuration attribute that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ConfigurationAttribute deleteConfigurationAttribute(
        com.ext.portlet.model.ConfigurationAttribute configurationAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _configurationAttributeLocalService.deleteConfigurationAttribute(configurationAttribute);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _configurationAttributeLocalService.dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _configurationAttributeLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ConfigurationAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _configurationAttributeLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ConfigurationAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _configurationAttributeLocalService.dynamicQuery(dynamicQuery,
            start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _configurationAttributeLocalService.dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _configurationAttributeLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.ConfigurationAttribute fetchConfigurationAttribute(
        com.ext.portlet.service.persistence.ConfigurationAttributePK configurationAttributePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _configurationAttributeLocalService.fetchConfigurationAttribute(configurationAttributePK);
    }

    /**
    * Returns the configuration attribute with the primary key.
    *
    * @param configurationAttributePK the primary key of the configuration attribute
    * @return the configuration attribute
    * @throws PortalException if a configuration attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ConfigurationAttribute getConfigurationAttribute(
        com.ext.portlet.service.persistence.ConfigurationAttributePK configurationAttributePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _configurationAttributeLocalService.getConfigurationAttribute(configurationAttributePK);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _configurationAttributeLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the configuration attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ConfigurationAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of configuration attributes
    * @param end the upper bound of the range of configuration attributes (not inclusive)
    * @return the range of configuration attributes
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.ConfigurationAttribute> getConfigurationAttributes(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _configurationAttributeLocalService.getConfigurationAttributes(start,
            end);
    }

    /**
    * Returns the number of configuration attributes.
    *
    * @return the number of configuration attributes
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getConfigurationAttributesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _configurationAttributeLocalService.getConfigurationAttributesCount();
    }

    /**
    * Updates the configuration attribute in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param configurationAttribute the configuration attribute
    * @return the configuration attribute that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ConfigurationAttribute updateConfigurationAttribute(
        com.ext.portlet.model.ConfigurationAttribute configurationAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _configurationAttributeLocalService.updateConfigurationAttribute(configurationAttribute);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _configurationAttributeLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _configurationAttributeLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _configurationAttributeLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public com.ext.portlet.model.ConfigurationAttribute getByAttributeName(
        java.lang.String attributeKeyName)
        throws com.ext.portlet.NoSuchConfigurationAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return _configurationAttributeLocalService.getByAttributeName(attributeKeyName);
    }

    @Override
    public com.ext.portlet.model.ConfigurationAttribute getByAttributeNameAdditionalId(
        java.lang.String attributeKeyName, long additionalId)
        throws com.ext.portlet.NoSuchConfigurationAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return _configurationAttributeLocalService.getByAttributeNameAdditionalId(attributeKeyName,
            additionalId);
    }

    @Override
    public java.lang.String getAttributeStringValue(
        java.lang.String attributeKeyName, long additionalId,
        java.lang.String defaultValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _configurationAttributeLocalService.getAttributeStringValue(attributeKeyName,
            additionalId, defaultValue);
    }

    @Override
    public long getAttributeLongValue(java.lang.String attributeKeyName,
        long additionalId, long defaultValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _configurationAttributeLocalService.getAttributeLongValue(attributeKeyName,
            additionalId, defaultValue);
    }

    @Override
    public double getAttributeDoubleValue(java.lang.String attributeKeyName,
        long additionalId, double defaultValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _configurationAttributeLocalService.getAttributeDoubleValue(attributeKeyName,
            additionalId, defaultValue);
    }

    @Override
    public java.lang.String getAttributeStringValue(
        java.lang.String attributeKeyName, long additionalId)
        throws com.ext.portlet.NoSuchConfigurationAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return _configurationAttributeLocalService.getAttributeStringValue(attributeKeyName,
            additionalId);
    }

    @Override
    public long getAttributeLongValue(java.lang.String attributeKeyName,
        long additionalId)
        throws com.ext.portlet.NoSuchConfigurationAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return _configurationAttributeLocalService.getAttributeLongValue(attributeKeyName,
            additionalId);
    }

    @Override
    public double getAttributeDoubleValue(java.lang.String attributeKeyName,
        long additionalId)
        throws com.ext.portlet.NoSuchConfigurationAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return _configurationAttributeLocalService.getAttributeDoubleValue(attributeKeyName,
            additionalId);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ConfigurationAttributeLocalService getWrappedConfigurationAttributeLocalService() {
        return _configurationAttributeLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedConfigurationAttributeLocalService(
        ConfigurationAttributeLocalService configurationAttributeLocalService) {
        _configurationAttributeLocalService = configurationAttributeLocalService;
    }

    @Override
    public ConfigurationAttributeLocalService getWrappedService() {
        return _configurationAttributeLocalService;
    }

    @Override
    public void setWrappedService(
        ConfigurationAttributeLocalService configurationAttributeLocalService) {
        _configurationAttributeLocalService = configurationAttributeLocalService;
    }
}
