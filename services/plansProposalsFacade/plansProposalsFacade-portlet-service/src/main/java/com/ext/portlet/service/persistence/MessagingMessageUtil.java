package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MessagingMessage;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the messaging message service. This utility wraps {@link MessagingMessagePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessagePersistence
 * @see MessagingMessagePersistenceImpl
 * @generated
 */
public class MessagingMessageUtil {
    private static MessagingMessagePersistence _persistence;

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
    public static void clearCache(MessagingMessage messagingMessage) {
        getPersistence().clearCache(messagingMessage);
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
    public static List<MessagingMessage> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<MessagingMessage> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<MessagingMessage> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static MessagingMessage update(MessagingMessage messagingMessage,
        boolean merge) throws SystemException {
        return getPersistence().update(messagingMessage, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static MessagingMessage update(MessagingMessage messagingMessage,
        boolean merge, ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(messagingMessage, merge, serviceContext);
    }

    /**
    * Caches the messaging message in the entity cache if it is enabled.
    *
    * @param messagingMessage the messaging message
    */
    public static void cacheResult(
        com.ext.portlet.model.MessagingMessage messagingMessage) {
        getPersistence().cacheResult(messagingMessage);
    }

    /**
    * Caches the messaging messages in the entity cache if it is enabled.
    *
    * @param messagingMessages the messaging messages
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.MessagingMessage> messagingMessages) {
        getPersistence().cacheResult(messagingMessages);
    }

    /**
    * Creates a new messaging message with the primary key. Does not add the messaging message to the database.
    *
    * @param messageId the primary key for the new messaging message
    * @return the new messaging message
    */
    public static com.ext.portlet.model.MessagingMessage create(long messageId) {
        return getPersistence().create(messageId);
    }

    /**
    * Removes the messaging message with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param messageId the primary key of the messaging message
    * @return the messaging message that was removed
    * @throws com.ext.portlet.NoSuchMessagingMessageException if a messaging message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessage remove(long messageId)
        throws com.ext.portlet.NoSuchMessagingMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(messageId);
    }

    public static com.ext.portlet.model.MessagingMessage updateImpl(
        com.ext.portlet.model.MessagingMessage messagingMessage, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(messagingMessage, merge);
    }

    /**
    * Returns the messaging message with the primary key or throws a {@link com.ext.portlet.NoSuchMessagingMessageException} if it could not be found.
    *
    * @param messageId the primary key of the messaging message
    * @return the messaging message
    * @throws com.ext.portlet.NoSuchMessagingMessageException if a messaging message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessage findByPrimaryKey(
        long messageId)
        throws com.ext.portlet.NoSuchMessagingMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(messageId);
    }

    /**
    * Returns the messaging message with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param messageId the primary key of the messaging message
    * @return the messaging message, or <code>null</code> if a messaging message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessage fetchByPrimaryKey(
        long messageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(messageId);
    }

    /**
    * Returns all the messaging messages.
    *
    * @return the messaging messages
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingMessage> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the messaging messages.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of messaging messages
    * @param end the upper bound of the range of messaging messages (not inclusive)
    * @return the range of messaging messages
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingMessage> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the messaging messages.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of messaging messages
    * @param end the upper bound of the range of messaging messages (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of messaging messages
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingMessage> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the messaging messages from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of messaging messages.
    *
    * @return the number of messaging messages
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static MessagingMessagePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (MessagingMessagePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    MessagingMessagePersistence.class.getName());

            ReferenceRegistry.registerReference(MessagingMessageUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(MessagingMessagePersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(MessagingMessageUtil.class,
            "_persistence");
    }
}
