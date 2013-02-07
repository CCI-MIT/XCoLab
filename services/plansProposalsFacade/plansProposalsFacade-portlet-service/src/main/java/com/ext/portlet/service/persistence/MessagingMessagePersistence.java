package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MessagingMessage;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the messaging message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessagePersistenceImpl
 * @see MessagingMessageUtil
 * @generated
 */
public interface MessagingMessagePersistence extends BasePersistence<MessagingMessage> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link MessagingMessageUtil} to access the messaging message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the messaging message in the entity cache if it is enabled.
    *
    * @param messagingMessage the messaging message
    */
    public void cacheResult(
        com.ext.portlet.model.MessagingMessage messagingMessage);

    /**
    * Caches the messaging messages in the entity cache if it is enabled.
    *
    * @param messagingMessages the messaging messages
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.MessagingMessage> messagingMessages);

    /**
    * Creates a new messaging message with the primary key. Does not add the messaging message to the database.
    *
    * @param messageId the primary key for the new messaging message
    * @return the new messaging message
    */
    public com.ext.portlet.model.MessagingMessage create(long messageId);

    /**
    * Removes the messaging message with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param messageId the primary key of the messaging message
    * @return the messaging message that was removed
    * @throws com.ext.portlet.NoSuchMessagingMessageException if a messaging message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessage remove(long messageId)
        throws com.ext.portlet.NoSuchMessagingMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.MessagingMessage updateImpl(
        com.ext.portlet.model.MessagingMessage messagingMessage, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the messaging message with the primary key or throws a {@link com.ext.portlet.NoSuchMessagingMessageException} if it could not be found.
    *
    * @param messageId the primary key of the messaging message
    * @return the messaging message
    * @throws com.ext.portlet.NoSuchMessagingMessageException if a messaging message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessage findByPrimaryKey(
        long messageId)
        throws com.ext.portlet.NoSuchMessagingMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the messaging message with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param messageId the primary key of the messaging message
    * @return the messaging message, or <code>null</code> if a messaging message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessage fetchByPrimaryKey(
        long messageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the messaging messages.
    *
    * @return the messaging messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessagingMessage> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.MessagingMessage> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.MessagingMessage> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the messaging messages from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of messaging messages.
    *
    * @return the number of messaging messages
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
