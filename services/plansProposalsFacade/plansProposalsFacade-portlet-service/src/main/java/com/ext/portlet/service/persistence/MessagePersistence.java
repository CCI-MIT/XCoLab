package com.ext.portlet.service.persistence;

import com.ext.portlet.model.Message;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagePersistenceImpl
 * @see MessageUtil
 * @generated
 */
public interface MessagePersistence extends BasePersistence<Message> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link MessageUtil} to access the message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the message in the entity cache if it is enabled.
    *
    * @param message the message
    */
    public void cacheResult(com.ext.portlet.model.Message message);

    /**
    * Caches the messages in the entity cache if it is enabled.
    *
    * @param messages the messages
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.Message> messages);

    /**
    * Creates a new message with the primary key. Does not add the message to the database.
    *
    * @param messageId the primary key for the new message
    * @return the new message
    */
    public com.ext.portlet.model.Message create(long messageId);

    /**
    * Removes the message with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param messageId the primary key of the message
    * @return the message that was removed
    * @throws com.ext.portlet.NoSuchMessageException if a message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Message remove(long messageId)
        throws com.ext.portlet.NoSuchMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.Message updateImpl(
        com.ext.portlet.model.Message message, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the message with the primary key or throws a {@link com.ext.portlet.NoSuchMessageException} if it could not be found.
    *
    * @param messageId the primary key of the message
    * @return the message
    * @throws com.ext.portlet.NoSuchMessageException if a message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Message findByPrimaryKey(long messageId)
        throws com.ext.portlet.NoSuchMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the message with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param messageId the primary key of the message
    * @return the message, or <code>null</code> if a message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Message fetchByPrimaryKey(long messageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the messages where fromId = &#63;.
    *
    * @param fromId the from ID
    * @return the matching messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Message> findBySendingUser(
        long fromId) throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.Message> findBySendingUser(
        long fromId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.Message> findBySendingUser(
        long fromId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    * @throws com.ext.portlet.NoSuchMessageException if a matching message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Message findBySendingUser_First(long fromId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMessageException,
            com.liferay.portal.kernel.exception.SystemException;

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
    * @throws com.ext.portlet.NoSuchMessageException if a matching message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Message findBySendingUser_Last(long fromId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMessageException,
            com.liferay.portal.kernel.exception.SystemException;

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
    * @throws com.ext.portlet.NoSuchMessageException if a message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Message[] findBySendingUser_PrevAndNext(
        long messageId, long fromId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the messages.
    *
    * @return the messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Message> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.Message> findAll(int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.Message> findAll(int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the messages where fromId = &#63; from the database.
    *
    * @param fromId the from ID
    * @throws SystemException if a system exception occurred
    */
    public void removeBySendingUser(long fromId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the messages from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of messages where fromId = &#63;.
    *
    * @param fromId the from ID
    * @return the number of matching messages
    * @throws SystemException if a system exception occurred
    */
    public int countBySendingUser(long fromId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of messages.
    *
    * @return the number of messages
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
