package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ConfigurationAttribute;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the configuration attribute service. This utility wraps {@link ConfigurationAttributePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ConfigurationAttributePersistence
 * @see ConfigurationAttributePersistenceImpl
 * @generated
 */
public class ConfigurationAttributeUtil {
    private static ConfigurationAttributePersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
     */
    public static void clearCache(ConfigurationAttribute configurationAttribute) {
        getPersistence().clearCache(configurationAttribute);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<ConfigurationAttribute> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ConfigurationAttribute> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ConfigurationAttribute> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ConfigurationAttribute update(
        ConfigurationAttribute configurationAttribute)
        throws SystemException {
        return getPersistence().update(configurationAttribute);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ConfigurationAttribute update(
        ConfigurationAttribute configurationAttribute,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(configurationAttribute, serviceContext);
    }

    /**
    * Caches the configuration attribute in the entity cache if it is enabled.
    *
    * @param configurationAttribute the configuration attribute
    */
    public static void cacheResult(
        com.ext.portlet.model.ConfigurationAttribute configurationAttribute) {
        getPersistence().cacheResult(configurationAttribute);
    }

    /**
    * Caches the configuration attributes in the entity cache if it is enabled.
    *
    * @param configurationAttributes the configuration attributes
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ConfigurationAttribute> configurationAttributes) {
        getPersistence().cacheResult(configurationAttributes);
    }

    /**
    * Creates a new configuration attribute with the primary key. Does not add the configuration attribute to the database.
    *
    * @param configurationAttributePK the primary key for the new configuration attribute
    * @return the new configuration attribute
    */
    public static com.ext.portlet.model.ConfigurationAttribute create(
        ConfigurationAttributePK configurationAttributePK) {
        return getPersistence().create(configurationAttributePK);
    }

    /**
    * Removes the configuration attribute with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param configurationAttributePK the primary key of the configuration attribute
    * @return the configuration attribute that was removed
    * @throws com.ext.portlet.NoSuchConfigurationAttributeException if a configuration attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ConfigurationAttribute remove(
        ConfigurationAttributePK configurationAttributePK)
        throws com.ext.portlet.NoSuchConfigurationAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(configurationAttributePK);
    }

    public static com.ext.portlet.model.ConfigurationAttribute updateImpl(
        com.ext.portlet.model.ConfigurationAttribute configurationAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(configurationAttribute);
    }

    /**
    * Returns the configuration attribute with the primary key or throws a {@link com.ext.portlet.NoSuchConfigurationAttributeException} if it could not be found.
    *
    * @param configurationAttributePK the primary key of the configuration attribute
    * @return the configuration attribute
    * @throws com.ext.portlet.NoSuchConfigurationAttributeException if a configuration attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ConfigurationAttribute findByPrimaryKey(
        ConfigurationAttributePK configurationAttributePK)
        throws com.ext.portlet.NoSuchConfigurationAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(configurationAttributePK);
    }

    /**
    * Returns the configuration attribute with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param configurationAttributePK the primary key of the configuration attribute
    * @return the configuration attribute, or <code>null</code> if a configuration attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ConfigurationAttribute fetchByPrimaryKey(
        ConfigurationAttributePK configurationAttributePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(configurationAttributePK);
    }

    /**
    * Returns all the configuration attributes.
    *
    * @return the configuration attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ConfigurationAttribute> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
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
    public static java.util.List<com.ext.portlet.model.ConfigurationAttribute> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the configuration attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ConfigurationAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of configuration attributes
    * @param end the upper bound of the range of configuration attributes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of configuration attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ConfigurationAttribute> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the configuration attributes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of configuration attributes.
    *
    * @return the number of configuration attributes
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ConfigurationAttributePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ConfigurationAttributePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ConfigurationAttributePersistence.class.getName());

            ReferenceRegistry.registerReference(ConfigurationAttributeUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ConfigurationAttributePersistence persistence) {
    }
}
