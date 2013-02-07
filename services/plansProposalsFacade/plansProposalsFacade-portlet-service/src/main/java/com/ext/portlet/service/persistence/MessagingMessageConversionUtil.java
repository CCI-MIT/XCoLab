package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MessagingMessageConversion;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the messaging message conversion service. This utility wraps {@link MessagingMessageConversionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageConversionPersistence
 * @see MessagingMessageConversionPersistenceImpl
 * @generated
 */
public class MessagingMessageConversionUtil {
    private static MessagingMessageConversionPersistence _persistence;

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
        MessagingMessageConversion messagingMessageConversion) {
        getPersistence().clearCache(messagingMessageConversion);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<MessagingMessageConversion> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<MessagingMessageConversion> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<MessagingMessageConversion> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static MessagingMessageConversion update(
        MessagingMessageConversion messagingMessageConversion, boolean merge)
        throws SystemException {
        return getPersistence().update(messagingMessageConversion, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static MessagingMessageConversion update(
        MessagingMessageConversion messagingMessageConversion, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence()
                   .update(messagingMessageConversion, merge, serviceContext);
    }

    /**
    * Caches the messaging message conversion in the entity cache if it is enabled.
    *
    * @param messagingMessageConversion the messaging message conversion
    */
    public static void cacheResult(
        com.ext.portlet.model.MessagingMessageConversion messagingMessageConversion) {
        getPersistence().cacheResult(messagingMessageConversion);
    }

    /**
    * Caches the messaging message conversions in the entity cache if it is enabled.
    *
    * @param messagingMessageConversions the messaging message conversions
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.MessagingMessageConversion> messagingMessageConversions) {
        getPersistence().cacheResult(messagingMessageConversions);
    }

    /**
    * Creates a new messaging message conversion with the primary key. Does not add the messaging message conversion to the database.
    *
    * @param conversionId the primary key for the new messaging message conversion
    * @return the new messaging message conversion
    */
    public static com.ext.portlet.model.MessagingMessageConversion create(
        long conversionId) {
        return getPersistence().create(conversionId);
    }

    /**
    * Removes the messaging message conversion with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param conversionId the primary key of the messaging message conversion
    * @return the messaging message conversion that was removed
    * @throws com.ext.portlet.NoSuchMessagingMessageConversionException if a messaging message conversion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageConversion remove(
        long conversionId)
        throws com.ext.portlet.NoSuchMessagingMessageConversionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(conversionId);
    }

    public static com.ext.portlet.model.MessagingMessageConversion updateImpl(
        com.ext.portlet.model.MessagingMessageConversion messagingMessageConversion,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(messagingMessageConversion, merge);
    }

    /**
    * Returns the messaging message conversion with the primary key or throws a {@link com.ext.portlet.NoSuchMessagingMessageConversionException} if it could not be found.
    *
    * @param conversionId the primary key of the messaging message conversion
    * @return the messaging message conversion
    * @throws com.ext.portlet.NoSuchMessagingMessageConversionException if a messaging message conversion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageConversion findByPrimaryKey(
        long conversionId)
        throws com.ext.portlet.NoSuchMessagingMessageConversionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(conversionId);
    }

    /**
    * Returns the messaging message conversion with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param conversionId the primary key of the messaging message conversion
    * @return the messaging message conversion, or <code>null</code> if a messaging message conversion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageConversion fetchByPrimaryKey(
        long conversionId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(conversionId);
    }

    /**
    * Returns all the messaging message conversions where messageId = &#63; and conversionTypeId = &#63;.
    *
    * @param messageId the message ID
    * @param conversionTypeId the conversion type ID
    * @return the matching messaging message conversions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingMessageConversion> findByfindByType(
        long messageId, long conversionTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByfindByType(messageId, conversionTypeId);
    }

    /**
    * Returns a range of all the messaging message conversions where messageId = &#63; and conversionTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param messageId the message ID
    * @param conversionTypeId the conversion type ID
    * @param start the lower bound of the range of messaging message conversions
    * @param end the upper bound of the range of messaging message conversions (not inclusive)
    * @return the range of matching messaging message conversions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingMessageConversion> findByfindByType(
        long messageId, long conversionTypeId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByfindByType(messageId, conversionTypeId, start, end);
    }

    /**
    * Returns an ordered range of all the messaging message conversions where messageId = &#63; and conversionTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param messageId the message ID
    * @param conversionTypeId the conversion type ID
    * @param start the lower bound of the range of messaging message conversions
    * @param end the upper bound of the range of messaging message conversions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching messaging message conversions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingMessageConversion> findByfindByType(
        long messageId, long conversionTypeId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByfindByType(messageId, conversionTypeId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first messaging message conversion in the ordered set where messageId = &#63; and conversionTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param messageId the message ID
    * @param conversionTypeId the conversion type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching messaging message conversion
    * @throws com.ext.portlet.NoSuchMessagingMessageConversionException if a matching messaging message conversion could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageConversion findByfindByType_First(
        long messageId, long conversionTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMessagingMessageConversionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByfindByType_First(messageId, conversionTypeId,
            orderByComparator);
    }

    /**
    * Returns the last messaging message conversion in the ordered set where messageId = &#63; and conversionTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param messageId the message ID
    * @param conversionTypeId the conversion type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching messaging message conversion
    * @throws com.ext.portlet.NoSuchMessagingMessageConversionException if a matching messaging message conversion could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageConversion findByfindByType_Last(
        long messageId, long conversionTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMessagingMessageConversionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByfindByType_Last(messageId, conversionTypeId,
            orderByComparator);
    }

    /**
    * Returns the messaging message conversions before and after the current messaging message conversion in the ordered set where messageId = &#63; and conversionTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param conversionId the primary key of the current messaging message conversion
    * @param messageId the message ID
    * @param conversionTypeId the conversion type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next messaging message conversion
    * @throws com.ext.portlet.NoSuchMessagingMessageConversionException if a messaging message conversion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageConversion[] findByfindByType_PrevAndNext(
        long conversionId, long messageId, long conversionTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMessagingMessageConversionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByfindByType_PrevAndNext(conversionId, messageId,
            conversionTypeId, orderByComparator);
    }

    /**
    * Returns all the messaging message conversions.
    *
    * @return the messaging message conversions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingMessageConversion> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the messaging message conversions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of messaging message conversions
    * @param end the upper bound of the range of messaging message conversions (not inclusive)
    * @return the range of messaging message conversions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingMessageConversion> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the messaging message conversions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of messaging message conversions
    * @param end the upper bound of the range of messaging message conversions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of messaging message conversions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingMessageConversion> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the messaging message conversions where messageId = &#63; and conversionTypeId = &#63; from the database.
    *
    * @param messageId the message ID
    * @param conversionTypeId the conversion type ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByfindByType(long messageId, long conversionTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByfindByType(messageId, conversionTypeId);
    }

    /**
    * Removes all the messaging message conversions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of messaging message conversions where messageId = &#63; and conversionTypeId = &#63;.
    *
    * @param messageId the message ID
    * @param conversionTypeId the conversion type ID
    * @return the number of matching messaging message conversions
    * @throws SystemException if a system exception occurred
    */
    public static int countByfindByType(long messageId, long conversionTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByfindByType(messageId, conversionTypeId);
    }

    /**
    * Returns the number of messaging message conversions.
    *
    * @return the number of messaging message conversions
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static MessagingMessageConversionPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (MessagingMessageConversionPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    MessagingMessageConversionPersistence.class.getName());

            ReferenceRegistry.registerReference(MessagingMessageConversionUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(
        MessagingMessageConversionPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(MessagingMessageConversionUtil.class,
            "_persistence");
    }
}
