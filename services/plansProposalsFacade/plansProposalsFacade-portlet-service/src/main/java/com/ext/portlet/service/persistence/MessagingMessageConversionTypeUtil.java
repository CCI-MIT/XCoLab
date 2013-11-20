package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MessagingMessageConversionType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the messaging message conversion type service. This utility wraps {@link MessagingMessageConversionTypePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageConversionTypePersistence
 * @see MessagingMessageConversionTypePersistenceImpl
 * @generated
 */
public class MessagingMessageConversionTypeUtil {
    private static MessagingMessageConversionTypePersistence _persistence;

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
    public static void clearCache(
        MessagingMessageConversionType messagingMessageConversionType) {
        getPersistence().clearCache(messagingMessageConversionType);
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
    public static List<MessagingMessageConversionType> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<MessagingMessageConversionType> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<MessagingMessageConversionType> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static MessagingMessageConversionType update(
        MessagingMessageConversionType messagingMessageConversionType)
        throws SystemException {
        return getPersistence().update(messagingMessageConversionType);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static MessagingMessageConversionType update(
        MessagingMessageConversionType messagingMessageConversionType,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence()
                   .update(messagingMessageConversionType, serviceContext);
    }

    /**
    * Returns the messaging message conversion type where name = &#63; or throws a {@link com.ext.portlet.NoSuchMessagingMessageConversionTypeException} if it could not be found.
    *
    * @param name the name
    * @return the matching messaging message conversion type
    * @throws com.ext.portlet.NoSuchMessagingMessageConversionTypeException if a matching messaging message conversion type could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageConversionType findByfindByName(
        java.lang.String name)
        throws com.ext.portlet.NoSuchMessagingMessageConversionTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByfindByName(name);
    }

    /**
    * Returns the messaging message conversion type where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param name the name
    * @return the matching messaging message conversion type, or <code>null</code> if a matching messaging message conversion type could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageConversionType fetchByfindByName(
        java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByfindByName(name);
    }

    /**
    * Returns the messaging message conversion type where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param name the name
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching messaging message conversion type, or <code>null</code> if a matching messaging message conversion type could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageConversionType fetchByfindByName(
        java.lang.String name, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByfindByName(name, retrieveFromCache);
    }

    /**
    * Removes the messaging message conversion type where name = &#63; from the database.
    *
    * @param name the name
    * @return the messaging message conversion type that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageConversionType removeByfindByName(
        java.lang.String name)
        throws com.ext.portlet.NoSuchMessagingMessageConversionTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().removeByfindByName(name);
    }

    /**
    * Returns the number of messaging message conversion types where name = &#63;.
    *
    * @param name the name
    * @return the number of matching messaging message conversion types
    * @throws SystemException if a system exception occurred
    */
    public static int countByfindByName(java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByfindByName(name);
    }

    /**
    * Caches the messaging message conversion type in the entity cache if it is enabled.
    *
    * @param messagingMessageConversionType the messaging message conversion type
    */
    public static void cacheResult(
        com.ext.portlet.model.MessagingMessageConversionType messagingMessageConversionType) {
        getPersistence().cacheResult(messagingMessageConversionType);
    }

    /**
    * Caches the messaging message conversion types in the entity cache if it is enabled.
    *
    * @param messagingMessageConversionTypes the messaging message conversion types
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.MessagingMessageConversionType> messagingMessageConversionTypes) {
        getPersistence().cacheResult(messagingMessageConversionTypes);
    }

    /**
    * Creates a new messaging message conversion type with the primary key. Does not add the messaging message conversion type to the database.
    *
    * @param typeId the primary key for the new messaging message conversion type
    * @return the new messaging message conversion type
    */
    public static com.ext.portlet.model.MessagingMessageConversionType create(
        long typeId) {
        return getPersistence().create(typeId);
    }

    /**
    * Removes the messaging message conversion type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param typeId the primary key of the messaging message conversion type
    * @return the messaging message conversion type that was removed
    * @throws com.ext.portlet.NoSuchMessagingMessageConversionTypeException if a messaging message conversion type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageConversionType remove(
        long typeId)
        throws com.ext.portlet.NoSuchMessagingMessageConversionTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(typeId);
    }

    public static com.ext.portlet.model.MessagingMessageConversionType updateImpl(
        com.ext.portlet.model.MessagingMessageConversionType messagingMessageConversionType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(messagingMessageConversionType);
    }

    /**
    * Returns the messaging message conversion type with the primary key or throws a {@link com.ext.portlet.NoSuchMessagingMessageConversionTypeException} if it could not be found.
    *
    * @param typeId the primary key of the messaging message conversion type
    * @return the messaging message conversion type
    * @throws com.ext.portlet.NoSuchMessagingMessageConversionTypeException if a messaging message conversion type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageConversionType findByPrimaryKey(
        long typeId)
        throws com.ext.portlet.NoSuchMessagingMessageConversionTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(typeId);
    }

    /**
    * Returns the messaging message conversion type with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param typeId the primary key of the messaging message conversion type
    * @return the messaging message conversion type, or <code>null</code> if a messaging message conversion type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageConversionType fetchByPrimaryKey(
        long typeId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(typeId);
    }

    /**
    * Returns all the messaging message conversion types.
    *
    * @return the messaging message conversion types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingMessageConversionType> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the messaging message conversion types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingMessageConversionTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of messaging message conversion types
    * @param end the upper bound of the range of messaging message conversion types (not inclusive)
    * @return the range of messaging message conversion types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingMessageConversionType> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the messaging message conversion types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingMessageConversionTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of messaging message conversion types
    * @param end the upper bound of the range of messaging message conversion types (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of messaging message conversion types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingMessageConversionType> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the messaging message conversion types from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of messaging message conversion types.
    *
    * @return the number of messaging message conversion types
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static MessagingMessageConversionTypePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (MessagingMessageConversionTypePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    MessagingMessageConversionTypePersistence.class.getName());

            ReferenceRegistry.registerReference(MessagingMessageConversionTypeUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(
        MessagingMessageConversionTypePersistence persistence) {
    }
}
