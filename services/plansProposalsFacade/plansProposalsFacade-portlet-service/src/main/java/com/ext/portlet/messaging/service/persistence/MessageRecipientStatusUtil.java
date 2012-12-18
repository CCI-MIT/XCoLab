package com.ext.portlet.messaging.service.persistence;

import com.ext.portlet.messaging.model.MessageRecipientStatus;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the message recipient status service. This utility wraps {@link MessageRecipientStatusPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageRecipientStatusPersistence
 * @see MessageRecipientStatusPersistenceImpl
 * @generated
 */
public class MessageRecipientStatusUtil {
    private static MessageRecipientStatusPersistence _persistence;

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
    public static void clearCache(MessageRecipientStatus messageRecipientStatus) {
        getPersistence().clearCache(messageRecipientStatus);
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
    public static List<MessageRecipientStatus> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<MessageRecipientStatus> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<MessageRecipientStatus> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static MessageRecipientStatus update(
        MessageRecipientStatus messageRecipientStatus, boolean merge)
        throws SystemException {
        return getPersistence().update(messageRecipientStatus, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static MessageRecipientStatus update(
        MessageRecipientStatus messageRecipientStatus, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence()
                   .update(messageRecipientStatus, merge, serviceContext);
    }

    /**
    * Caches the message recipient status in the entity cache if it is enabled.
    *
    * @param messageRecipientStatus the message recipient status
    */
    public static void cacheResult(
        com.ext.portlet.messaging.model.MessageRecipientStatus messageRecipientStatus) {
        getPersistence().cacheResult(messageRecipientStatus);
    }

    /**
    * Caches the message recipient statuses in the entity cache if it is enabled.
    *
    * @param messageRecipientStatuses the message recipient statuses
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> messageRecipientStatuses) {
        getPersistence().cacheResult(messageRecipientStatuses);
    }

    /**
    * Creates a new message recipient status with the primary key. Does not add the message recipient status to the database.
    *
    * @param messageRecipientId the primary key for the new message recipient status
    * @return the new message recipient status
    */
    public static com.ext.portlet.messaging.model.MessageRecipientStatus create(
        long messageRecipientId) {
        return getPersistence().create(messageRecipientId);
    }

    /**
    * Removes the message recipient status with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param messageRecipientId the primary key of the message recipient status
    * @return the message recipient status that was removed
    * @throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException if a message recipient status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.MessageRecipientStatus remove(
        long messageRecipientId)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(messageRecipientId);
    }

    public static com.ext.portlet.messaging.model.MessageRecipientStatus updateImpl(
        com.ext.portlet.messaging.model.MessageRecipientStatus messageRecipientStatus,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(messageRecipientStatus, merge);
    }

    /**
    * Returns the message recipient status with the primary key or throws a {@link com.ext.portlet.messaging.NoSuchMessageRecipientStatusException} if it could not be found.
    *
    * @param messageRecipientId the primary key of the message recipient status
    * @return the message recipient status
    * @throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException if a message recipient status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.MessageRecipientStatus findByPrimaryKey(
        long messageRecipientId)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(messageRecipientId);
    }

    /**
    * Returns the message recipient status with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param messageRecipientId the primary key of the message recipient status
    * @return the message recipient status, or <code>null</code> if a message recipient status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.MessageRecipientStatus fetchByPrimaryKey(
        long messageRecipientId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(messageRecipientId);
    }

    /**
    * Returns all the message recipient statuses where userId = &#63;.
    *
    * @param userId the user ID
    * @return the matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUser(
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByReceivingUser(userId);
    }

    /**
    * Returns a range of all the message recipient statuses where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of message recipient statuses
    * @param end the upper bound of the range of message recipient statuses (not inclusive)
    * @return the range of matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUser(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByReceivingUser(userId, start, end);
    }

    /**
    * Returns an ordered range of all the message recipient statuses where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of message recipient statuses
    * @param end the upper bound of the range of message recipient statuses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUser(
        long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByReceivingUser(userId, start, end, orderByComparator);
    }

    /**
    * Returns the first message recipient status in the ordered set where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching message recipient status
    * @throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.MessageRecipientStatus findByReceivingUser_First(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByReceivingUser_First(userId, orderByComparator);
    }

    /**
    * Returns the last message recipient status in the ordered set where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching message recipient status
    * @throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.MessageRecipientStatus findByReceivingUser_Last(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByReceivingUser_Last(userId, orderByComparator);
    }

    /**
    * Returns the message recipient statuses before and after the current message recipient status in the ordered set where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param messageRecipientId the primary key of the current message recipient status
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next message recipient status
    * @throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException if a message recipient status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.MessageRecipientStatus[] findByReceivingUser_PrevAndNext(
        long messageRecipientId, long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByReceivingUser_PrevAndNext(messageRecipientId, userId,
            orderByComparator);
    }

    /**
    * Returns all the message recipient statuses where userId = &#63; and archived = &#63;.
    *
    * @param userId the user ID
    * @param archived the archived
    * @return the matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUserArchived(
        long userId, boolean archived)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByReceivingUserArchived(userId, archived);
    }

    /**
    * Returns a range of all the message recipient statuses where userId = &#63; and archived = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param userId the user ID
    * @param archived the archived
    * @param start the lower bound of the range of message recipient statuses
    * @param end the upper bound of the range of message recipient statuses (not inclusive)
    * @return the range of matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUserArchived(
        long userId, boolean archived, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByReceivingUserArchived(userId, archived, start, end);
    }

    /**
    * Returns an ordered range of all the message recipient statuses where userId = &#63; and archived = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param userId the user ID
    * @param archived the archived
    * @param start the lower bound of the range of message recipient statuses
    * @param end the upper bound of the range of message recipient statuses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUserArchived(
        long userId, boolean archived, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByReceivingUserArchived(userId, archived, start, end,
            orderByComparator);
    }

    /**
    * Returns the first message recipient status in the ordered set where userId = &#63; and archived = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param userId the user ID
    * @param archived the archived
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching message recipient status
    * @throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.MessageRecipientStatus findByReceivingUserArchived_First(
        long userId, boolean archived,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByReceivingUserArchived_First(userId, archived,
            orderByComparator);
    }

    /**
    * Returns the last message recipient status in the ordered set where userId = &#63; and archived = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param userId the user ID
    * @param archived the archived
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching message recipient status
    * @throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.MessageRecipientStatus findByReceivingUserArchived_Last(
        long userId, boolean archived,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByReceivingUserArchived_Last(userId, archived,
            orderByComparator);
    }

    /**
    * Returns the message recipient statuses before and after the current message recipient status in the ordered set where userId = &#63; and archived = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param messageRecipientId the primary key of the current message recipient status
    * @param userId the user ID
    * @param archived the archived
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next message recipient status
    * @throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException if a message recipient status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.MessageRecipientStatus[] findByReceivingUserArchived_PrevAndNext(
        long messageRecipientId, long userId, boolean archived,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByReceivingUserArchived_PrevAndNext(messageRecipientId,
            userId, archived, orderByComparator);
    }

    /**
    * Returns all the message recipient statuses where messageId = &#63;.
    *
    * @param messageId the message ID
    * @return the matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByMessageId(
        long messageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByMessageId(messageId);
    }

    /**
    * Returns a range of all the message recipient statuses where messageId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param messageId the message ID
    * @param start the lower bound of the range of message recipient statuses
    * @param end the upper bound of the range of message recipient statuses (not inclusive)
    * @return the range of matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByMessageId(
        long messageId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByMessageId(messageId, start, end);
    }

    /**
    * Returns an ordered range of all the message recipient statuses where messageId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param messageId the message ID
    * @param start the lower bound of the range of message recipient statuses
    * @param end the upper bound of the range of message recipient statuses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByMessageId(
        long messageId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMessageId(messageId, start, end, orderByComparator);
    }

    /**
    * Returns the first message recipient status in the ordered set where messageId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param messageId the message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching message recipient status
    * @throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.MessageRecipientStatus findByMessageId_First(
        long messageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMessageId_First(messageId, orderByComparator);
    }

    /**
    * Returns the last message recipient status in the ordered set where messageId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param messageId the message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching message recipient status
    * @throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.MessageRecipientStatus findByMessageId_Last(
        long messageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMessageId_Last(messageId, orderByComparator);
    }

    /**
    * Returns the message recipient statuses before and after the current message recipient status in the ordered set where messageId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param messageRecipientId the primary key of the current message recipient status
    * @param messageId the message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next message recipient status
    * @throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException if a message recipient status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.MessageRecipientStatus[] findByMessageId_PrevAndNext(
        long messageRecipientId, long messageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMessageId_PrevAndNext(messageRecipientId, messageId,
            orderByComparator);
    }

    /**
    * Returns the message recipient status where messageId = &#63; and userId = &#63; or throws a {@link com.ext.portlet.messaging.NoSuchMessageRecipientStatusException} if it could not be found.
    *
    * @param messageId the message ID
    * @param userId the user ID
    * @return the matching message recipient status
    * @throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.MessageRecipientStatus findByMessageReciever(
        long messageId, long userId)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByMessageReciever(messageId, userId);
    }

    /**
    * Returns the message recipient status where messageId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param messageId the message ID
    * @param userId the user ID
    * @return the matching message recipient status, or <code>null</code> if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.MessageRecipientStatus fetchByMessageReciever(
        long messageId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByMessageReciever(messageId, userId);
    }

    /**
    * Returns the message recipient status where messageId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param messageId the message ID
    * @param userId the user ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching message recipient status, or <code>null</code> if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.MessageRecipientStatus fetchByMessageReciever(
        long messageId, long userId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByMessageReciever(messageId, userId, retrieveFromCache);
    }

    /**
    * Returns all the message recipient statuses.
    *
    * @return the message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the message recipient statuses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of message recipient statuses
    * @param end the upper bound of the range of message recipient statuses (not inclusive)
    * @return the range of message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the message recipient statuses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of message recipient statuses
    * @param end the upper bound of the range of message recipient statuses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the message recipient statuses where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByReceivingUser(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByReceivingUser(userId);
    }

    /**
    * Removes all the message recipient statuses where userId = &#63; and archived = &#63; from the database.
    *
    * @param userId the user ID
    * @param archived the archived
    * @throws SystemException if a system exception occurred
    */
    public static void removeByReceivingUserArchived(long userId,
        boolean archived)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByReceivingUserArchived(userId, archived);
    }

    /**
    * Removes all the message recipient statuses where messageId = &#63; from the database.
    *
    * @param messageId the message ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByMessageId(long messageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByMessageId(messageId);
    }

    /**
    * Removes the message recipient status where messageId = &#63; and userId = &#63; from the database.
    *
    * @param messageId the message ID
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByMessageReciever(long messageId, long userId)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByMessageReciever(messageId, userId);
    }

    /**
    * Removes all the message recipient statuses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of message recipient statuses where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public static int countByReceivingUser(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByReceivingUser(userId);
    }

    /**
    * Returns the number of message recipient statuses where userId = &#63; and archived = &#63;.
    *
    * @param userId the user ID
    * @param archived the archived
    * @return the number of matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public static int countByReceivingUserArchived(long userId, boolean archived)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByReceivingUserArchived(userId, archived);
    }

    /**
    * Returns the number of message recipient statuses where messageId = &#63;.
    *
    * @param messageId the message ID
    * @return the number of matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public static int countByMessageId(long messageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByMessageId(messageId);
    }

    /**
    * Returns the number of message recipient statuses where messageId = &#63; and userId = &#63;.
    *
    * @param messageId the message ID
    * @param userId the user ID
    * @return the number of matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public static int countByMessageReciever(long messageId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByMessageReciever(messageId, userId);
    }

    /**
    * Returns the number of message recipient statuses.
    *
    * @return the number of message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static MessageRecipientStatusPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (MessageRecipientStatusPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.messaging.service.ClpSerializer.getServletContextName(),
                    MessageRecipientStatusPersistence.class.getName());

            ReferenceRegistry.registerReference(MessageRecipientStatusUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(MessageRecipientStatusPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(MessageRecipientStatusUtil.class,
            "_persistence");
    }
}
