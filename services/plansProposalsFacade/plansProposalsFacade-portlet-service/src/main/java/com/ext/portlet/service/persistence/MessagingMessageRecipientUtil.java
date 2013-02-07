package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MessagingMessageRecipient;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the messaging message recipient service. This utility wraps {@link MessagingMessageRecipientPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageRecipientPersistence
 * @see MessagingMessageRecipientPersistenceImpl
 * @generated
 */
public class MessagingMessageRecipientUtil {
    private static MessagingMessageRecipientPersistence _persistence;

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
        MessagingMessageRecipient messagingMessageRecipient) {
        getPersistence().clearCache(messagingMessageRecipient);
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
    public static List<MessagingMessageRecipient> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<MessagingMessageRecipient> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<MessagingMessageRecipient> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static MessagingMessageRecipient update(
        MessagingMessageRecipient messagingMessageRecipient, boolean merge)
        throws SystemException {
        return getPersistence().update(messagingMessageRecipient, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static MessagingMessageRecipient update(
        MessagingMessageRecipient messagingMessageRecipient, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence()
                   .update(messagingMessageRecipient, merge, serviceContext);
    }

    /**
    * Caches the messaging message recipient in the entity cache if it is enabled.
    *
    * @param messagingMessageRecipient the messaging message recipient
    */
    public static void cacheResult(
        com.ext.portlet.model.MessagingMessageRecipient messagingMessageRecipient) {
        getPersistence().cacheResult(messagingMessageRecipient);
    }

    /**
    * Caches the messaging message recipients in the entity cache if it is enabled.
    *
    * @param messagingMessageRecipients the messaging message recipients
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.MessagingMessageRecipient> messagingMessageRecipients) {
        getPersistence().cacheResult(messagingMessageRecipients);
    }

    /**
    * Creates a new messaging message recipient with the primary key. Does not add the messaging message recipient to the database.
    *
    * @param recipientId the primary key for the new messaging message recipient
    * @return the new messaging message recipient
    */
    public static com.ext.portlet.model.MessagingMessageRecipient create(
        long recipientId) {
        return getPersistence().create(recipientId);
    }

    /**
    * Removes the messaging message recipient with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param recipientId the primary key of the messaging message recipient
    * @return the messaging message recipient that was removed
    * @throws com.ext.portlet.NoSuchMessagingMessageRecipientException if a messaging message recipient with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageRecipient remove(
        long recipientId)
        throws com.ext.portlet.NoSuchMessagingMessageRecipientException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(recipientId);
    }

    public static com.ext.portlet.model.MessagingMessageRecipient updateImpl(
        com.ext.portlet.model.MessagingMessageRecipient messagingMessageRecipient,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(messagingMessageRecipient, merge);
    }

    /**
    * Returns the messaging message recipient with the primary key or throws a {@link com.ext.portlet.NoSuchMessagingMessageRecipientException} if it could not be found.
    *
    * @param recipientId the primary key of the messaging message recipient
    * @return the messaging message recipient
    * @throws com.ext.portlet.NoSuchMessagingMessageRecipientException if a messaging message recipient with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageRecipient findByPrimaryKey(
        long recipientId)
        throws com.ext.portlet.NoSuchMessagingMessageRecipientException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(recipientId);
    }

    /**
    * Returns the messaging message recipient with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param recipientId the primary key of the messaging message recipient
    * @return the messaging message recipient, or <code>null</code> if a messaging message recipient with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageRecipient fetchByPrimaryKey(
        long recipientId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(recipientId);
    }

    /**
    * Returns all the messaging message recipients.
    *
    * @return the messaging message recipients
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingMessageRecipient> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the messaging message recipients.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of messaging message recipients
    * @param end the upper bound of the range of messaging message recipients (not inclusive)
    * @return the range of messaging message recipients
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingMessageRecipient> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the messaging message recipients.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of messaging message recipients
    * @param end the upper bound of the range of messaging message recipients (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of messaging message recipients
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingMessageRecipient> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the messaging message recipients from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of messaging message recipients.
    *
    * @return the number of messaging message recipients
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static MessagingMessageRecipientPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (MessagingMessageRecipientPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    MessagingMessageRecipientPersistence.class.getName());

            ReferenceRegistry.registerReference(MessagingMessageRecipientUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(MessagingMessageRecipientPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(MessagingMessageRecipientUtil.class,
            "_persistence");
    }
}
