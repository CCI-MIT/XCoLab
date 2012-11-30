package com.ext.portlet.messaging.service.persistence;

import com.ext.portlet.messaging.model.MessageRecipientStatus;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the message recipient status service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageRecipientStatusPersistenceImpl
 * @see MessageRecipientStatusUtil
 * @generated
 */
public interface MessageRecipientStatusPersistence extends BasePersistence<MessageRecipientStatus> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link MessageRecipientStatusUtil} to access the message recipient status persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the message recipient status in the entity cache if it is enabled.
    *
    * @param messageRecipientStatus the message recipient status
    */
    public void cacheResult(
        com.ext.portlet.messaging.model.MessageRecipientStatus messageRecipientStatus);

    /**
    * Caches the message recipient statuses in the entity cache if it is enabled.
    *
    * @param messageRecipientStatuses the message recipient statuses
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> messageRecipientStatuses);

    /**
    * Creates a new message recipient status with the primary key. Does not add the message recipient status to the database.
    *
    * @param messageRecipientId the primary key for the new message recipient status
    * @return the new message recipient status
    */
    public com.ext.portlet.messaging.model.MessageRecipientStatus create(
        java.lang.Long messageRecipientId);

    /**
    * Removes the message recipient status with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param messageRecipientId the primary key of the message recipient status
    * @return the message recipient status that was removed
    * @throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException if a message recipient status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.messaging.model.MessageRecipientStatus remove(
        java.lang.Long messageRecipientId)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.messaging.model.MessageRecipientStatus updateImpl(
        com.ext.portlet.messaging.model.MessageRecipientStatus messageRecipientStatus,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the message recipient status with the primary key or throws a {@link com.ext.portlet.messaging.NoSuchMessageRecipientStatusException} if it could not be found.
    *
    * @param messageRecipientId the primary key of the message recipient status
    * @return the message recipient status
    * @throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException if a message recipient status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.messaging.model.MessageRecipientStatus findByPrimaryKey(
        java.lang.Long messageRecipientId)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the message recipient status with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param messageRecipientId the primary key of the message recipient status
    * @return the message recipient status, or <code>null</code> if a message recipient status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.messaging.model.MessageRecipientStatus fetchByPrimaryKey(
        java.lang.Long messageRecipientId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the message recipient statuses where userId = &#63;.
    *
    * @param userId the user ID
    * @return the matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUser(
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUser(
        java.lang.Long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUser(
        java.lang.Long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.messaging.model.MessageRecipientStatus findByReceivingUser_First(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.messaging.model.MessageRecipientStatus findByReceivingUser_Last(
        java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.messaging.model.MessageRecipientStatus[] findByReceivingUser_PrevAndNext(
        java.lang.Long messageRecipientId, java.lang.Long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the message recipient statuses where userId = &#63; and archived = &#63;.
    *
    * @param userId the user ID
    * @param archived the archived
    * @return the matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUserArchived(
        java.lang.Long userId, java.lang.Boolean archived)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUserArchived(
        java.lang.Long userId, java.lang.Boolean archived, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByReceivingUserArchived(
        java.lang.Long userId, java.lang.Boolean archived, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.messaging.model.MessageRecipientStatus findByReceivingUserArchived_First(
        java.lang.Long userId, java.lang.Boolean archived,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.messaging.model.MessageRecipientStatus findByReceivingUserArchived_Last(
        java.lang.Long userId, java.lang.Boolean archived,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.messaging.model.MessageRecipientStatus[] findByReceivingUserArchived_PrevAndNext(
        java.lang.Long messageRecipientId, java.lang.Long userId,
        java.lang.Boolean archived,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the message recipient statuses where messageId = &#63;.
    *
    * @param messageId the message ID
    * @return the matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByMessageId(
        java.lang.Long messageId)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByMessageId(
        java.lang.Long messageId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findByMessageId(
        java.lang.Long messageId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.messaging.model.MessageRecipientStatus findByMessageId_First(
        java.lang.Long messageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.messaging.model.MessageRecipientStatus findByMessageId_Last(
        java.lang.Long messageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.messaging.model.MessageRecipientStatus[] findByMessageId_PrevAndNext(
        java.lang.Long messageRecipientId, java.lang.Long messageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the message recipient status where messageId = &#63; and userId = &#63; or throws a {@link com.ext.portlet.messaging.NoSuchMessageRecipientStatusException} if it could not be found.
    *
    * @param messageId the message ID
    * @param userId the user ID
    * @return the matching message recipient status
    * @throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.messaging.model.MessageRecipientStatus findByMessageReciever(
        java.lang.Long messageId, java.lang.Long userId)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the message recipient status where messageId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param messageId the message ID
    * @param userId the user ID
    * @return the matching message recipient status, or <code>null</code> if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.messaging.model.MessageRecipientStatus fetchByMessageReciever(
        java.lang.Long messageId, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the message recipient status where messageId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param messageId the message ID
    * @param userId the user ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching message recipient status, or <code>null</code> if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.messaging.model.MessageRecipientStatus fetchByMessageReciever(
        java.lang.Long messageId, java.lang.Long userId,
        boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the message recipient statuses.
    *
    * @return the message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the message recipient statuses where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByReceivingUser(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the message recipient statuses where userId = &#63; and archived = &#63; from the database.
    *
    * @param userId the user ID
    * @param archived the archived
    * @throws SystemException if a system exception occurred
    */
    public void removeByReceivingUserArchived(java.lang.Long userId,
        java.lang.Boolean archived)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the message recipient statuses where messageId = &#63; from the database.
    *
    * @param messageId the message ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByMessageId(java.lang.Long messageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the message recipient status where messageId = &#63; and userId = &#63; from the database.
    *
    * @param messageId the message ID
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByMessageReciever(java.lang.Long messageId,
        java.lang.Long userId)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the message recipient statuses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of message recipient statuses where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public int countByReceivingUser(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of message recipient statuses where userId = &#63; and archived = &#63;.
    *
    * @param userId the user ID
    * @param archived the archived
    * @return the number of matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public int countByReceivingUserArchived(java.lang.Long userId,
        java.lang.Boolean archived)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of message recipient statuses where messageId = &#63;.
    *
    * @param messageId the message ID
    * @return the number of matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public int countByMessageId(java.lang.Long messageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of message recipient statuses where messageId = &#63; and userId = &#63;.
    *
    * @param messageId the message ID
    * @param userId the user ID
    * @return the number of matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public int countByMessageReciever(java.lang.Long messageId,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of message recipient statuses.
    *
    * @return the number of message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
