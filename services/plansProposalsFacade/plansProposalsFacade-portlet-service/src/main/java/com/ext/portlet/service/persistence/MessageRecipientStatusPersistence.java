package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MessageRecipientStatus;

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
    * Returns all the message recipient statuses where userId = &#63;.
    *
    * @param userId the user ID
    * @return the matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessageRecipientStatus> findByReceivingUser(
        long userId) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the message recipient statuses where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessageRecipientStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of message recipient statuses
    * @param end the upper bound of the range of message recipient statuses (not inclusive)
    * @return the range of matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessageRecipientStatus> findByReceivingUser(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the message recipient statuses where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessageRecipientStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of message recipient statuses
    * @param end the upper bound of the range of message recipient statuses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessageRecipientStatus> findByReceivingUser(
        long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first message recipient status in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching message recipient status
    * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus findByReceivingUser_First(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first message recipient status in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching message recipient status, or <code>null</code> if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus fetchByReceivingUser_First(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last message recipient status in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching message recipient status
    * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus findByReceivingUser_Last(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last message recipient status in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching message recipient status, or <code>null</code> if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus fetchByReceivingUser_Last(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the message recipient statuses before and after the current message recipient status in the ordered set where userId = &#63;.
    *
    * @param messageRecipientId the primary key of the current message recipient status
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next message recipient status
    * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a message recipient status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus[] findByReceivingUser_PrevAndNext(
        long messageRecipientId, long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the message recipient statuses where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByReceivingUser(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of message recipient statuses where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public int countByReceivingUser(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the message recipient statuses where userId = &#63; and archived = &#63;.
    *
    * @param userId the user ID
    * @param archived the archived
    * @return the matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessageRecipientStatus> findByReceivingUserArchived(
        long userId, boolean archived)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the message recipient statuses where userId = &#63; and archived = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessageRecipientStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userId the user ID
    * @param archived the archived
    * @param start the lower bound of the range of message recipient statuses
    * @param end the upper bound of the range of message recipient statuses (not inclusive)
    * @return the range of matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessageRecipientStatus> findByReceivingUserArchived(
        long userId, boolean archived, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the message recipient statuses where userId = &#63; and archived = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessageRecipientStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    public java.util.List<com.ext.portlet.model.MessageRecipientStatus> findByReceivingUserArchived(
        long userId, boolean archived, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first message recipient status in the ordered set where userId = &#63; and archived = &#63;.
    *
    * @param userId the user ID
    * @param archived the archived
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching message recipient status
    * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus findByReceivingUserArchived_First(
        long userId, boolean archived,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first message recipient status in the ordered set where userId = &#63; and archived = &#63;.
    *
    * @param userId the user ID
    * @param archived the archived
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching message recipient status, or <code>null</code> if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus fetchByReceivingUserArchived_First(
        long userId, boolean archived,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last message recipient status in the ordered set where userId = &#63; and archived = &#63;.
    *
    * @param userId the user ID
    * @param archived the archived
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching message recipient status
    * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus findByReceivingUserArchived_Last(
        long userId, boolean archived,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last message recipient status in the ordered set where userId = &#63; and archived = &#63;.
    *
    * @param userId the user ID
    * @param archived the archived
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching message recipient status, or <code>null</code> if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus fetchByReceivingUserArchived_Last(
        long userId, boolean archived,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the message recipient statuses before and after the current message recipient status in the ordered set where userId = &#63; and archived = &#63;.
    *
    * @param messageRecipientId the primary key of the current message recipient status
    * @param userId the user ID
    * @param archived the archived
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next message recipient status
    * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a message recipient status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus[] findByReceivingUserArchived_PrevAndNext(
        long messageRecipientId, long userId, boolean archived,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the message recipient statuses where userId = &#63; and archived = &#63; from the database.
    *
    * @param userId the user ID
    * @param archived the archived
    * @throws SystemException if a system exception occurred
    */
    public void removeByReceivingUserArchived(long userId, boolean archived)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of message recipient statuses where userId = &#63; and archived = &#63;.
    *
    * @param userId the user ID
    * @param archived the archived
    * @return the number of matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public int countByReceivingUserArchived(long userId, boolean archived)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the message recipient statuses where messageId = &#63;.
    *
    * @param messageId the message ID
    * @return the matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessageRecipientStatus> findByMessageId(
        long messageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the message recipient statuses where messageId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessageRecipientStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param messageId the message ID
    * @param start the lower bound of the range of message recipient statuses
    * @param end the upper bound of the range of message recipient statuses (not inclusive)
    * @return the range of matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessageRecipientStatus> findByMessageId(
        long messageId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the message recipient statuses where messageId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessageRecipientStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param messageId the message ID
    * @param start the lower bound of the range of message recipient statuses
    * @param end the upper bound of the range of message recipient statuses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessageRecipientStatus> findByMessageId(
        long messageId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first message recipient status in the ordered set where messageId = &#63;.
    *
    * @param messageId the message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching message recipient status
    * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus findByMessageId_First(
        long messageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first message recipient status in the ordered set where messageId = &#63;.
    *
    * @param messageId the message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching message recipient status, or <code>null</code> if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus fetchByMessageId_First(
        long messageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last message recipient status in the ordered set where messageId = &#63;.
    *
    * @param messageId the message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching message recipient status
    * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus findByMessageId_Last(
        long messageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last message recipient status in the ordered set where messageId = &#63;.
    *
    * @param messageId the message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching message recipient status, or <code>null</code> if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus fetchByMessageId_Last(
        long messageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the message recipient statuses before and after the current message recipient status in the ordered set where messageId = &#63;.
    *
    * @param messageRecipientId the primary key of the current message recipient status
    * @param messageId the message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next message recipient status
    * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a message recipient status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus[] findByMessageId_PrevAndNext(
        long messageRecipientId, long messageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the message recipient statuses where messageId = &#63; from the database.
    *
    * @param messageId the message ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByMessageId(long messageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of message recipient statuses where messageId = &#63;.
    *
    * @param messageId the message ID
    * @return the number of matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public int countByMessageId(long messageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the message recipient status where messageId = &#63; and userId = &#63; or throws a {@link com.ext.portlet.NoSuchMessageRecipientStatusException} if it could not be found.
    *
    * @param messageId the message ID
    * @param userId the user ID
    * @return the matching message recipient status
    * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus findByMessageReciever(
        long messageId, long userId)
        throws com.ext.portlet.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the message recipient status where messageId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param messageId the message ID
    * @param userId the user ID
    * @return the matching message recipient status, or <code>null</code> if a matching message recipient status could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus fetchByMessageReciever(
        long messageId, long userId)
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
    public com.ext.portlet.model.MessageRecipientStatus fetchByMessageReciever(
        long messageId, long userId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the message recipient status where messageId = &#63; and userId = &#63; from the database.
    *
    * @param messageId the message ID
    * @param userId the user ID
    * @return the message recipient status that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus removeByMessageReciever(
        long messageId, long userId)
        throws com.ext.portlet.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of message recipient statuses where messageId = &#63; and userId = &#63;.
    *
    * @param messageId the message ID
    * @param userId the user ID
    * @return the number of matching message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public int countByMessageReciever(long messageId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the message recipient status in the entity cache if it is enabled.
    *
    * @param messageRecipientStatus the message recipient status
    */
    public void cacheResult(
        com.ext.portlet.model.MessageRecipientStatus messageRecipientStatus);

    /**
    * Caches the message recipient statuses in the entity cache if it is enabled.
    *
    * @param messageRecipientStatuses the message recipient statuses
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.MessageRecipientStatus> messageRecipientStatuses);

    /**
    * Creates a new message recipient status with the primary key. Does not add the message recipient status to the database.
    *
    * @param messageRecipientId the primary key for the new message recipient status
    * @return the new message recipient status
    */
    public com.ext.portlet.model.MessageRecipientStatus create(
        long messageRecipientId);

    /**
    * Removes the message recipient status with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param messageRecipientId the primary key of the message recipient status
    * @return the message recipient status that was removed
    * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a message recipient status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus remove(
        long messageRecipientId)
        throws com.ext.portlet.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.MessageRecipientStatus updateImpl(
        com.ext.portlet.model.MessageRecipientStatus messageRecipientStatus)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the message recipient status with the primary key or throws a {@link com.ext.portlet.NoSuchMessageRecipientStatusException} if it could not be found.
    *
    * @param messageRecipientId the primary key of the message recipient status
    * @return the message recipient status
    * @throws com.ext.portlet.NoSuchMessageRecipientStatusException if a message recipient status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus findByPrimaryKey(
        long messageRecipientId)
        throws com.ext.portlet.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the message recipient status with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param messageRecipientId the primary key of the message recipient status
    * @return the message recipient status, or <code>null</code> if a message recipient status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus fetchByPrimaryKey(
        long messageRecipientId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the message recipient statuses.
    *
    * @return the message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessageRecipientStatus> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the message recipient statuses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessageRecipientStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of message recipient statuses
    * @param end the upper bound of the range of message recipient statuses (not inclusive)
    * @return the range of message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessageRecipientStatus> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the message recipient statuses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessageRecipientStatusModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of message recipient statuses
    * @param end the upper bound of the range of message recipient statuses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessageRecipientStatus> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the message recipient statuses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
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
