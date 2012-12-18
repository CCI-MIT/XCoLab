package com.ext.portlet.messaging.service.persistence;

import com.ext.portlet.messaging.model.Message;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the message service. This utility wraps {@link MessagePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagePersistence
 * @see MessagePersistenceImpl
 * @generated
 */
public class MessageUtil {
    private static MessagePersistence _persistence;

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
    public static void clearCache(Message message) {
        getPersistence().clearCache(message);
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
    public static List<Message> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<Message> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<Message> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static Message update(Message message, boolean merge)
        throws SystemException {
        return getPersistence().update(message, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static Message update(Message message, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(message, merge, serviceContext);
    }

    /**
    * Caches the message in the entity cache if it is enabled.
    *
    * @param message the message
    */
    public static void cacheResult(
        com.ext.portlet.messaging.model.Message message) {
        getPersistence().cacheResult(message);
    }

    /**
    * Caches the messages in the entity cache if it is enabled.
    *
    * @param messages the messages
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.messaging.model.Message> messages) {
        getPersistence().cacheResult(messages);
    }

    /**
    * Creates a new message with the primary key. Does not add the message to the database.
    *
    * @param messageId the primary key for the new message
    * @return the new message
    */
    public static com.ext.portlet.messaging.model.Message create(long messageId) {
        return getPersistence().create(messageId);
    }

    /**
    * Removes the message with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param messageId the primary key of the message
    * @return the message that was removed
    * @throws com.ext.portlet.messaging.NoSuchMessageException if a message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.Message remove(long messageId)
        throws com.ext.portlet.messaging.NoSuchMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(messageId);
    }

    public static com.ext.portlet.messaging.model.Message updateImpl(
        com.ext.portlet.messaging.model.Message message, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(message, merge);
    }

    /**
    * Returns the message with the primary key or throws a {@link com.ext.portlet.messaging.NoSuchMessageException} if it could not be found.
    *
    * @param messageId the primary key of the message
    * @return the message
    * @throws com.ext.portlet.messaging.NoSuchMessageException if a message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.Message findByPrimaryKey(
        long messageId)
        throws com.ext.portlet.messaging.NoSuchMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(messageId);
    }

    /**
    * Returns the message with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param messageId the primary key of the message
    * @return the message, or <code>null</code> if a message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.Message fetchByPrimaryKey(
        long messageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(messageId);
    }

    /**
    * Returns all the messages where fromId = &#63;.
    *
    * @param fromId the from ID
    * @return the matching messages
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.messaging.model.Message> findBySendingUser(
        long fromId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySendingUser(fromId);
    }

    /**
    * Returns a range of all the messages where fromId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param fromId the from ID
    * @param start the lower bound of the range of messages
    * @param end the upper bound of the range of messages (not inclusive)
    * @return the range of matching messages
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.messaging.model.Message> findBySendingUser(
        long fromId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySendingUser(fromId, start, end);
    }

    /**
    * Returns an ordered range of all the messages where fromId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param fromId the from ID
    * @param start the lower bound of the range of messages
    * @param end the upper bound of the range of messages (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching messages
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.messaging.model.Message> findBySendingUser(
        long fromId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySendingUser(fromId, start, end, orderByComparator);
    }

    /**
    * Returns the first message in the ordered set where fromId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param fromId the from ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching message
    * @throws com.ext.portlet.messaging.NoSuchMessageException if a matching message could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.Message findBySendingUser_First(
        long fromId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.messaging.NoSuchMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySendingUser_First(fromId, orderByComparator);
    }

    /**
    * Returns the last message in the ordered set where fromId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param fromId the from ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching message
    * @throws com.ext.portlet.messaging.NoSuchMessageException if a matching message could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.Message findBySendingUser_Last(
        long fromId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.messaging.NoSuchMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySendingUser_Last(fromId, orderByComparator);
    }

    /**
    * Returns the messages before and after the current message in the ordered set where fromId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param messageId the primary key of the current message
    * @param fromId the from ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next message
    * @throws com.ext.portlet.messaging.NoSuchMessageException if a message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.Message[] findBySendingUser_PrevAndNext(
        long messageId, long fromId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.messaging.NoSuchMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySendingUser_PrevAndNext(messageId, fromId,
            orderByComparator);
    }

    /**
    * Returns all the messages.
    *
    * @return the messages
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.messaging.model.Message> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the messages.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of messages
    * @param end the upper bound of the range of messages (not inclusive)
    * @return the range of messages
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.messaging.model.Message> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the messages.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of messages
    * @param end the upper bound of the range of messages (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of messages
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.messaging.model.Message> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the messages where fromId = &#63; from the database.
    *
    * @param fromId the from ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeBySendingUser(long fromId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeBySendingUser(fromId);
    }

    /**
    * Removes all the messages from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of messages where fromId = &#63;.
    *
    * @param fromId the from ID
    * @return the number of matching messages
    * @throws SystemException if a system exception occurred
    */
    public static int countBySendingUser(long fromId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countBySendingUser(fromId);
    }

    /**
    * Returns the number of messages.
    *
    * @return the number of messages
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static MessagePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (MessagePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.messaging.service.ClpSerializer.getServletContextName(),
                    MessagePersistence.class.getName());

            ReferenceRegistry.registerReference(MessageUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(MessagePersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(MessageUtil.class, "_persistence");
    }
}
