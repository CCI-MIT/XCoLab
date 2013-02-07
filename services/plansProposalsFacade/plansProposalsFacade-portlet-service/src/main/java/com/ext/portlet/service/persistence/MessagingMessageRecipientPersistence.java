package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MessagingMessageRecipient;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the messaging message recipient service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageRecipientPersistenceImpl
 * @see MessagingMessageRecipientUtil
 * @generated
 */
public interface MessagingMessageRecipientPersistence extends BasePersistence<MessagingMessageRecipient> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link MessagingMessageRecipientUtil} to access the messaging message recipient persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the messaging message recipient in the entity cache if it is enabled.
    *
    * @param messagingMessageRecipient the messaging message recipient
    */
    public void cacheResult(
        com.ext.portlet.model.MessagingMessageRecipient messagingMessageRecipient);

    /**
    * Caches the messaging message recipients in the entity cache if it is enabled.
    *
    * @param messagingMessageRecipients the messaging message recipients
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.MessagingMessageRecipient> messagingMessageRecipients);

    /**
    * Creates a new messaging message recipient with the primary key. Does not add the messaging message recipient to the database.
    *
    * @param recipientId the primary key for the new messaging message recipient
    * @return the new messaging message recipient
    */
    public com.ext.portlet.model.MessagingMessageRecipient create(
        long recipientId);

    /**
    * Removes the messaging message recipient with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param recipientId the primary key of the messaging message recipient
    * @return the messaging message recipient that was removed
    * @throws com.ext.portlet.NoSuchMessagingMessageRecipientException if a messaging message recipient with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessageRecipient remove(
        long recipientId)
        throws com.ext.portlet.NoSuchMessagingMessageRecipientException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.MessagingMessageRecipient updateImpl(
        com.ext.portlet.model.MessagingMessageRecipient messagingMessageRecipient,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the messaging message recipient with the primary key or throws a {@link com.ext.portlet.NoSuchMessagingMessageRecipientException} if it could not be found.
    *
    * @param recipientId the primary key of the messaging message recipient
    * @return the messaging message recipient
    * @throws com.ext.portlet.NoSuchMessagingMessageRecipientException if a messaging message recipient with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessageRecipient findByPrimaryKey(
        long recipientId)
        throws com.ext.portlet.NoSuchMessagingMessageRecipientException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the messaging message recipient with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param recipientId the primary key of the messaging message recipient
    * @return the messaging message recipient, or <code>null</code> if a messaging message recipient with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingMessageRecipient fetchByPrimaryKey(
        long recipientId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the messaging message recipients.
    *
    * @return the messaging message recipients
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessagingMessageRecipient> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.MessagingMessageRecipient> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.MessagingMessageRecipient> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the messaging message recipients from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of messaging message recipients.
    *
    * @return the number of messaging message recipients
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
