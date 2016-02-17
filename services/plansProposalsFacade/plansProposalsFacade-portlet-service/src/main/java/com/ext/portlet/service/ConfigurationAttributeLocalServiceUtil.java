package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for ConfigurationAttribute. This utility wraps
 * {@link com.ext.portlet.service.impl.ConfigurationAttributeLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ConfigurationAttributeLocalService
 * @see com.ext.portlet.service.base.ConfigurationAttributeLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.ConfigurationAttributeLocalServiceImpl
 * @generated
 */
public class ConfigurationAttributeLocalServiceUtil {
    private static ConfigurationAttributeLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ConfigurationAttributeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the configuration attribute to the database. Also notifies the appropriate model listeners.
    *
    * @param configurationAttribute the configuration attribute
    * @return the configuration attribute that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ConfigurationAttribute addConfigurationAttribute(
        com.ext.portlet.model.ConfigurationAttribute configurationAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addConfigurationAttribute(configurationAttribute);
    }

    /**
    * Creates a new configuration attribute with the primary key. Does not add the configuration attribute to the database.
    *
    * @param configurationAttributePK the primary key for the new configuration attribute
    * @return the new configuration attribute
    */
    public static com.ext.portlet.model.ConfigurationAttribute createConfigurationAttribute(
        com.ext.portlet.service.persistence.ConfigurationAttributePK configurationAttributePK) {
        return getService()
                   .createConfigurationAttribute(configurationAttributePK);
    }

    /**
    * Deletes the configuration attribute with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param configurationAttributePK the primary key of the configuration attribute
    * @return the configuration attribute that was removed
    * @throws PortalException if a configuration attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ConfigurationAttribute deleteConfigurationAttribute(
        com.ext.portlet.service.persistence.ConfigurationAttributePK configurationAttributePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .deleteConfigurationAttribute(configurationAttributePK);
    }

    /**
    * Deletes the configuration attribute from the database. Also notifies the appropriate model listeners.
    *
    * @param configurationAttribute the configuration attribute
    * @return the configuration attribute that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ConfigurationAttribute deleteConfigurationAttribute(
        com.ext.portlet.model.ConfigurationAttribute configurationAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteConfigurationAttribute(configurationAttribute);
    }

    public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return getService().dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
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
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
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
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery, projection);
    }

    public static com.ext.portlet.model.ConfigurationAttribute fetchConfigurationAttribute(
        com.ext.portlet.service.persistence.ConfigurationAttributePK configurationAttributePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchConfigurationAttribute(configurationAttributePK);
    }

    /**
    * Returns the configuration attribute with the primary key.
    *
    * @param configurationAttributePK the primary key of the configuration attribute
    * @return the configuration attribute
    * @throws PortalException if a configuration attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ConfigurationAttribute getConfigurationAttribute(
        com.ext.portlet.service.persistence.ConfigurationAttributePK configurationAttributePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getConfigurationAttribute(configurationAttributePK);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
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
    public static java.util.List<com.ext.portlet.model.ConfigurationAttribute> getConfigurationAttributes(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getConfigurationAttributes(start, end);
    }

    /**
    * Returns the number of configuration attributes.
    *
    * @return the number of configuration attributes
    * @throws SystemException if a system exception occurred
    */
    public static int getConfigurationAttributesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getConfigurationAttributesCount();
    }

    /**
    * Updates the configuration attribute in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param configurationAttribute the configuration attribute
    * @return the configuration attribute that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ConfigurationAttribute updateConfigurationAttribute(
        com.ext.portlet.model.ConfigurationAttribute configurationAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateConfigurationAttribute(configurationAttribute);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static com.ext.portlet.model.ConfigurationAttribute getByAttributeName(
        java.lang.String attributeKeyName)
        throws com.ext.portlet.NoSuchConfigurationAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getByAttributeName(attributeKeyName);
    }

    public static com.ext.portlet.model.ConfigurationAttribute getByAttributeNameAdditionalId(
        java.lang.String attributeKeyName, long additionalId)
        throws com.ext.portlet.NoSuchConfigurationAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getByAttributeNameAdditionalId(attributeKeyName,
            additionalId);
    }

    public static java.lang.String getAttributeStringValue(
        java.lang.String attributeKeyName, long additionalId,
        java.lang.String defaultValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getAttributeStringValue(attributeKeyName, additionalId,
            defaultValue);
    }

    public static long getAttributeLongValue(
        java.lang.String attributeKeyName, long additionalId, long defaultValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getAttributeLongValue(attributeKeyName, additionalId,
            defaultValue);
    }

    public static double getAttributeDoubleValue(
        java.lang.String attributeKeyName, long additionalId,
        double defaultValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getAttributeDoubleValue(attributeKeyName, additionalId,
            defaultValue);
    }

    public static java.lang.String getAttributeStringValue(
        java.lang.String attributeKeyName, long additionalId)
        throws com.ext.portlet.NoSuchConfigurationAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getAttributeStringValue(attributeKeyName, additionalId);
    }

    public static long getAttributeLongValue(
        java.lang.String attributeKeyName, long additionalId)
        throws com.ext.portlet.NoSuchConfigurationAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getAttributeLongValue(attributeKeyName, additionalId);
    }

    public static double getAttributeDoubleValue(
        java.lang.String attributeKeyName, long additionalId)
        throws com.ext.portlet.NoSuchConfigurationAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getAttributeDoubleValue(attributeKeyName, additionalId);
    }

    public static void clearService() {
        _service = null;
    }

    public static ConfigurationAttributeLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ConfigurationAttributeLocalService.class.getName());

            if (invokableLocalService instanceof ConfigurationAttributeLocalService) {
                _service = (ConfigurationAttributeLocalService) invokableLocalService;
            } else {
                _service = new ConfigurationAttributeLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(ConfigurationAttributeLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(ConfigurationAttributeLocalService service) {
    }
}
