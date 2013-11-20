package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MessagingIgnoredRecipients;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the messaging ignored recipients service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingIgnoredRecipientsPersistenceImpl
 * @see MessagingIgnoredRecipientsUtil
 * @generated
 */
public interface MessagingIgnoredRecipientsPersistence extends BasePersistence<MessagingIgnoredRecipients> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link MessagingIgnoredRecipientsUtil} to access the messaging ignored recipients persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns the messaging ignored recipients where email = &#63; or throws a {@link com.ext.portlet.NoSuchMessagingIgnoredRecipientsException} if it could not be found.
    *
    * @param email the email
    * @return the matching messaging ignored recipients
    * @throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException if a matching messaging ignored recipients could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingIgnoredRecipients findByfindByEmail(
        java.lang.String email)
        throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the messaging ignored recipients where email = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param email the email
    * @return the matching messaging ignored recipients, or <code>null</code> if a matching messaging ignored recipients could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingIgnoredRecipients fetchByfindByEmail(
        java.lang.String email)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the messaging ignored recipients where email = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param email the email
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching messaging ignored recipients, or <code>null</code> if a matching messaging ignored recipients could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingIgnoredRecipients fetchByfindByEmail(
        java.lang.String email, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the messaging ignored recipients where email = &#63; from the database.
    *
    * @param email the email
    * @return the messaging ignored recipients that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingIgnoredRecipients removeByfindByEmail(
        java.lang.String email)
        throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of messaging ignored recipientses where email = &#63;.
    *
    * @param email the email
    * @return the number of matching messaging ignored recipientses
    * @throws SystemException if a system exception occurred
    */
    public int countByfindByEmail(java.lang.String email)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the messaging ignored recipients where userId = &#63; or throws a {@link com.ext.portlet.NoSuchMessagingIgnoredRecipientsException} if it could not be found.
    *
    * @param userId the user ID
    * @return the matching messaging ignored recipients
    * @throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException if a matching messaging ignored recipients could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingIgnoredRecipients findByfindByUserId(
        long userId)
        throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the messaging ignored recipients where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param userId the user ID
    * @return the matching messaging ignored recipients, or <code>null</code> if a matching messaging ignored recipients could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingIgnoredRecipients fetchByfindByUserId(
        long userId) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the messaging ignored recipients where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param userId the user ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching messaging ignored recipients, or <code>null</code> if a matching messaging ignored recipients could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingIgnoredRecipients fetchByfindByUserId(
        long userId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the messaging ignored recipients where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @return the messaging ignored recipients that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingIgnoredRecipients removeByfindByUserId(
        long userId)
        throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of messaging ignored recipientses where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching messaging ignored recipientses
    * @throws SystemException if a system exception occurred
    */
    public int countByfindByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the messaging ignored recipients in the entity cache if it is enabled.
    *
    * @param messagingIgnoredRecipients the messaging ignored recipients
    */
    public void cacheResult(
        com.ext.portlet.model.MessagingIgnoredRecipients messagingIgnoredRecipients);

    /**
    * Caches the messaging ignored recipientses in the entity cache if it is enabled.
    *
    * @param messagingIgnoredRecipientses the messaging ignored recipientses
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.MessagingIgnoredRecipients> messagingIgnoredRecipientses);

    /**
    * Creates a new messaging ignored recipients with the primary key. Does not add the messaging ignored recipients to the database.
    *
    * @param ignoredRecipientId the primary key for the new messaging ignored recipients
    * @return the new messaging ignored recipients
    */
    public com.ext.portlet.model.MessagingIgnoredRecipients create(
        long ignoredRecipientId);

    /**
    * Removes the messaging ignored recipients with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ignoredRecipientId the primary key of the messaging ignored recipients
    * @return the messaging ignored recipients that was removed
    * @throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException if a messaging ignored recipients with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingIgnoredRecipients remove(
        long ignoredRecipientId)
        throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.MessagingIgnoredRecipients updateImpl(
        com.ext.portlet.model.MessagingIgnoredRecipients messagingIgnoredRecipients)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the messaging ignored recipients with the primary key or throws a {@link com.ext.portlet.NoSuchMessagingIgnoredRecipientsException} if it could not be found.
    *
    * @param ignoredRecipientId the primary key of the messaging ignored recipients
    * @return the messaging ignored recipients
    * @throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException if a messaging ignored recipients with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingIgnoredRecipients findByPrimaryKey(
        long ignoredRecipientId)
        throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the messaging ignored recipients with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ignoredRecipientId the primary key of the messaging ignored recipients
    * @return the messaging ignored recipients, or <code>null</code> if a messaging ignored recipients with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessagingIgnoredRecipients fetchByPrimaryKey(
        long ignoredRecipientId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the messaging ignored recipientses.
    *
    * @return the messaging ignored recipientses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessagingIgnoredRecipients> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the messaging ignored recipientses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingIgnoredRecipientsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of messaging ignored recipientses
    * @param end the upper bound of the range of messaging ignored recipientses (not inclusive)
    * @return the range of messaging ignored recipientses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessagingIgnoredRecipients> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the messaging ignored recipientses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingIgnoredRecipientsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of messaging ignored recipientses
    * @param end the upper bound of the range of messaging ignored recipientses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of messaging ignored recipientses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessagingIgnoredRecipients> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the messaging ignored recipientses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of messaging ignored recipientses.
    *
    * @return the number of messaging ignored recipientses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
