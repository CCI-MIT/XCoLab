package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MessagingIgnoredRecipients;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the messaging ignored recipients service. This utility wraps {@link MessagingIgnoredRecipientsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingIgnoredRecipientsPersistence
 * @see MessagingIgnoredRecipientsPersistenceImpl
 * @generated
 */
public class MessagingIgnoredRecipientsUtil {
    private static MessagingIgnoredRecipientsPersistence _persistence;

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
        MessagingIgnoredRecipients messagingIgnoredRecipients) {
        getPersistence().clearCache(messagingIgnoredRecipients);
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
    public static List<MessagingIgnoredRecipients> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<MessagingIgnoredRecipients> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<MessagingIgnoredRecipients> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static MessagingIgnoredRecipients update(
        MessagingIgnoredRecipients messagingIgnoredRecipients, boolean merge)
        throws SystemException {
        return getPersistence().update(messagingIgnoredRecipients, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static MessagingIgnoredRecipients update(
        MessagingIgnoredRecipients messagingIgnoredRecipients, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence()
                   .update(messagingIgnoredRecipients, merge, serviceContext);
    }

    /**
    * Caches the messaging ignored recipients in the entity cache if it is enabled.
    *
    * @param messagingIgnoredRecipients the messaging ignored recipients
    */
    public static void cacheResult(
        com.ext.portlet.model.MessagingIgnoredRecipients messagingIgnoredRecipients) {
        getPersistence().cacheResult(messagingIgnoredRecipients);
    }

    /**
    * Caches the messaging ignored recipientses in the entity cache if it is enabled.
    *
    * @param messagingIgnoredRecipientses the messaging ignored recipientses
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.MessagingIgnoredRecipients> messagingIgnoredRecipientses) {
        getPersistence().cacheResult(messagingIgnoredRecipientses);
    }

    /**
    * Creates a new messaging ignored recipients with the primary key. Does not add the messaging ignored recipients to the database.
    *
    * @param ignoredRecipientId the primary key for the new messaging ignored recipients
    * @return the new messaging ignored recipients
    */
    public static com.ext.portlet.model.MessagingIgnoredRecipients create(
        long ignoredRecipientId) {
        return getPersistence().create(ignoredRecipientId);
    }

    /**
    * Removes the messaging ignored recipients with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ignoredRecipientId the primary key of the messaging ignored recipients
    * @return the messaging ignored recipients that was removed
    * @throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException if a messaging ignored recipients with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingIgnoredRecipients remove(
        long ignoredRecipientId)
        throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(ignoredRecipientId);
    }

    public static com.ext.portlet.model.MessagingIgnoredRecipients updateImpl(
        com.ext.portlet.model.MessagingIgnoredRecipients messagingIgnoredRecipients,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(messagingIgnoredRecipients, merge);
    }

    /**
    * Returns the messaging ignored recipients with the primary key or throws a {@link com.ext.portlet.NoSuchMessagingIgnoredRecipientsException} if it could not be found.
    *
    * @param ignoredRecipientId the primary key of the messaging ignored recipients
    * @return the messaging ignored recipients
    * @throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException if a messaging ignored recipients with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingIgnoredRecipients findByPrimaryKey(
        long ignoredRecipientId)
        throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ignoredRecipientId);
    }

    /**
    * Returns the messaging ignored recipients with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ignoredRecipientId the primary key of the messaging ignored recipients
    * @return the messaging ignored recipients, or <code>null</code> if a messaging ignored recipients with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingIgnoredRecipients fetchByPrimaryKey(
        long ignoredRecipientId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ignoredRecipientId);
    }

    /**
    * Returns the messaging ignored recipients where email = &#63; or throws a {@link com.ext.portlet.NoSuchMessagingIgnoredRecipientsException} if it could not be found.
    *
    * @param email the email
    * @return the matching messaging ignored recipients
    * @throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException if a matching messaging ignored recipients could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingIgnoredRecipients findByfindByEmail(
        java.lang.String email)
        throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByfindByEmail(email);
    }

    /**
    * Returns the messaging ignored recipients where email = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param email the email
    * @return the matching messaging ignored recipients, or <code>null</code> if a matching messaging ignored recipients could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingIgnoredRecipients fetchByfindByEmail(
        java.lang.String email)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByfindByEmail(email);
    }

    /**
    * Returns the messaging ignored recipients where email = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param email the email
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching messaging ignored recipients, or <code>null</code> if a matching messaging ignored recipients could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingIgnoredRecipients fetchByfindByEmail(
        java.lang.String email, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByfindByEmail(email, retrieveFromCache);
    }

    /**
    * Returns the messaging ignored recipients where userId = &#63; or throws a {@link com.ext.portlet.NoSuchMessagingIgnoredRecipientsException} if it could not be found.
    *
    * @param userId the user ID
    * @return the matching messaging ignored recipients
    * @throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException if a matching messaging ignored recipients could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingIgnoredRecipients findByfindByUserId(
        long userId)
        throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByfindByUserId(userId);
    }

    /**
    * Returns the messaging ignored recipients where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param userId the user ID
    * @return the matching messaging ignored recipients, or <code>null</code> if a matching messaging ignored recipients could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingIgnoredRecipients fetchByfindByUserId(
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByfindByUserId(userId);
    }

    /**
    * Returns the messaging ignored recipients where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param userId the user ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching messaging ignored recipients, or <code>null</code> if a matching messaging ignored recipients could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingIgnoredRecipients fetchByfindByUserId(
        long userId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByfindByUserId(userId, retrieveFromCache);
    }

    /**
    * Returns all the messaging ignored recipientses.
    *
    * @return the messaging ignored recipientses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingIgnoredRecipients> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the messaging ignored recipientses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of messaging ignored recipientses
    * @param end the upper bound of the range of messaging ignored recipientses (not inclusive)
    * @return the range of messaging ignored recipientses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingIgnoredRecipients> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the messaging ignored recipientses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of messaging ignored recipientses
    * @param end the upper bound of the range of messaging ignored recipientses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of messaging ignored recipientses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingIgnoredRecipients> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes the messaging ignored recipients where email = &#63; from the database.
    *
    * @param email the email
    * @throws SystemException if a system exception occurred
    */
    public static void removeByfindByEmail(java.lang.String email)
        throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByfindByEmail(email);
    }

    /**
    * Removes the messaging ignored recipients where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByfindByUserId(long userId)
        throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByfindByUserId(userId);
    }

    /**
    * Removes all the messaging ignored recipientses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of messaging ignored recipientses where email = &#63;.
    *
    * @param email the email
    * @return the number of matching messaging ignored recipientses
    * @throws SystemException if a system exception occurred
    */
    public static int countByfindByEmail(java.lang.String email)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByfindByEmail(email);
    }

    /**
    * Returns the number of messaging ignored recipientses where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching messaging ignored recipientses
    * @throws SystemException if a system exception occurred
    */
    public static int countByfindByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByfindByUserId(userId);
    }

    /**
    * Returns the number of messaging ignored recipientses.
    *
    * @return the number of messaging ignored recipientses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static MessagingIgnoredRecipientsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (MessagingIgnoredRecipientsPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    MessagingIgnoredRecipientsPersistence.class.getName());

            ReferenceRegistry.registerReference(MessagingIgnoredRecipientsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(
        MessagingIgnoredRecipientsPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(MessagingIgnoredRecipientsUtil.class,
            "_persistence");
    }
}
