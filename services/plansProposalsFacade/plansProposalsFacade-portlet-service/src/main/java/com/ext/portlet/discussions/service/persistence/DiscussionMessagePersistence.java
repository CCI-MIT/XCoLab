package com.ext.portlet.discussions.service.persistence;

import com.ext.portlet.discussions.model.DiscussionMessage;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the discussion message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionMessagePersistenceImpl
 * @see DiscussionMessageUtil
 * @generated
 */
public interface DiscussionMessagePersistence extends BasePersistence<DiscussionMessage> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link DiscussionMessageUtil} to access the discussion message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the discussion message in the entity cache if it is enabled.
    *
    * @param discussionMessage the discussion message
    */
    public void cacheResult(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage);

    /**
    * Caches the discussion messages in the entity cache if it is enabled.
    *
    * @param discussionMessages the discussion messages
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> discussionMessages);

    /**
    * Creates a new discussion message with the primary key. Does not add the discussion message to the database.
    *
    * @param pk the primary key for the new discussion message
    * @return the new discussion message
    */
    public com.ext.portlet.discussions.model.DiscussionMessage create(
        java.lang.Long pk);

    /**
    * Removes the discussion message with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param pk the primary key of the discussion message
    * @return the discussion message that was removed
    * @throws com.ext.portlet.discussions.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessage remove(
        java.lang.Long pk)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.discussions.model.DiscussionMessage updateImpl(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message with the primary key or throws a {@link com.ext.portlet.discussions.NoSuchDiscussionMessageException} if it could not be found.
    *
    * @param pk the primary key of the discussion message
    * @return the discussion message
    * @throws com.ext.portlet.discussions.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessage findByPrimaryKey(
        java.lang.Long pk)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param pk the primary key of the discussion message
    * @return the discussion message, or <code>null</code> if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessage fetchByPrimaryKey(
        java.lang.Long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the discussion messages where categoryId = &#63; and threadId = &#63;.
    *
    * @param categoryId the category ID
    * @param threadId the thread ID
    * @return the matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByCategoryIdThreadId(
        java.lang.Long categoryId, java.lang.Long threadId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the discussion messages where categoryId = &#63; and threadId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param categoryId the category ID
    * @param threadId the thread ID
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @return the range of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByCategoryIdThreadId(
        java.lang.Long categoryId, java.lang.Long threadId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the discussion messages where categoryId = &#63; and threadId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param categoryId the category ID
    * @param threadId the thread ID
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByCategoryIdThreadId(
        java.lang.Long categoryId, java.lang.Long threadId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first discussion message in the ordered set where categoryId = &#63; and threadId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param categoryId the category ID
    * @param threadId the thread ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching discussion message
    * @throws com.ext.portlet.discussions.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessage findByCategoryIdThreadId_First(
        java.lang.Long categoryId, java.lang.Long threadId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last discussion message in the ordered set where categoryId = &#63; and threadId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param categoryId the category ID
    * @param threadId the thread ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching discussion message
    * @throws com.ext.portlet.discussions.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessage findByCategoryIdThreadId_Last(
        java.lang.Long categoryId, java.lang.Long threadId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion messages before and after the current discussion message in the ordered set where categoryId = &#63; and threadId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the current discussion message
    * @param categoryId the category ID
    * @param threadId the thread ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next discussion message
    * @throws com.ext.portlet.discussions.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessage[] findByCategoryIdThreadId_PrevAndNext(
        java.lang.Long pk, java.lang.Long categoryId, java.lang.Long threadId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the discussion messages where threadId = &#63;.
    *
    * @param threadId the thread ID
    * @return the matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByThreadId(
        java.lang.Long threadId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the discussion messages where threadId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param threadId the thread ID
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @return the range of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByThreadId(
        java.lang.Long threadId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the discussion messages where threadId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param threadId the thread ID
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByThreadId(
        java.lang.Long threadId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first discussion message in the ordered set where threadId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param threadId the thread ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching discussion message
    * @throws com.ext.portlet.discussions.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessage findByThreadId_First(
        java.lang.Long threadId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last discussion message in the ordered set where threadId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param threadId the thread ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching discussion message
    * @throws com.ext.portlet.discussions.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessage findByThreadId_Last(
        java.lang.Long threadId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion messages before and after the current discussion message in the ordered set where threadId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the current discussion message
    * @param threadId the thread ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next discussion message
    * @throws com.ext.portlet.discussions.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessage[] findByThreadId_PrevAndNext(
        java.lang.Long pk, java.lang.Long threadId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message where messageId = &#63; or throws a {@link com.ext.portlet.discussions.NoSuchDiscussionMessageException} if it could not be found.
    *
    * @param messageId the message ID
    * @return the matching discussion message
    * @throws com.ext.portlet.discussions.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessage findBySingleThreadId(
        java.lang.Long messageId)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message where messageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param messageId the message ID
    * @return the matching discussion message, or <code>null</code> if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessage fetchBySingleThreadId(
        java.lang.Long messageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message where messageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param messageId the message ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching discussion message, or <code>null</code> if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessage fetchBySingleThreadId(
        java.lang.Long messageId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the discussion messages where subject LIKE &#63; and categoryGroupId = &#63;.
    *
    * @param subject the subject
    * @param categoryGroupId the category group ID
    * @return the matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findBySubjectLike(
        java.lang.String subject, java.lang.Long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the discussion messages where subject LIKE &#63; and categoryGroupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param subject the subject
    * @param categoryGroupId the category group ID
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @return the range of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findBySubjectLike(
        java.lang.String subject, java.lang.Long categoryGroupId, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the discussion messages where subject LIKE &#63; and categoryGroupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param subject the subject
    * @param categoryGroupId the category group ID
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findBySubjectLike(
        java.lang.String subject, java.lang.Long categoryGroupId, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first discussion message in the ordered set where subject LIKE &#63; and categoryGroupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param subject the subject
    * @param categoryGroupId the category group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching discussion message
    * @throws com.ext.portlet.discussions.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessage findBySubjectLike_First(
        java.lang.String subject, java.lang.Long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last discussion message in the ordered set where subject LIKE &#63; and categoryGroupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param subject the subject
    * @param categoryGroupId the category group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching discussion message
    * @throws com.ext.portlet.discussions.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessage findBySubjectLike_Last(
        java.lang.String subject, java.lang.Long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion messages before and after the current discussion message in the ordered set where subject LIKE &#63; and categoryGroupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the current discussion message
    * @param subject the subject
    * @param categoryGroupId the category group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next discussion message
    * @throws com.ext.portlet.discussions.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessage[] findBySubjectLike_PrevAndNext(
        java.lang.Long pk, java.lang.String subject,
        java.lang.Long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the discussion messages where body LIKE &#63; and categoryGroupId = &#63;.
    *
    * @param body the body
    * @param categoryGroupId the category group ID
    * @return the matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByBodyLike(
        java.lang.String body, java.lang.Long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the discussion messages where body LIKE &#63; and categoryGroupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param body the body
    * @param categoryGroupId the category group ID
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @return the range of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByBodyLike(
        java.lang.String body, java.lang.Long categoryGroupId, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the discussion messages where body LIKE &#63; and categoryGroupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param body the body
    * @param categoryGroupId the category group ID
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findByBodyLike(
        java.lang.String body, java.lang.Long categoryGroupId, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first discussion message in the ordered set where body LIKE &#63; and categoryGroupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param body the body
    * @param categoryGroupId the category group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching discussion message
    * @throws com.ext.portlet.discussions.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessage findByBodyLike_First(
        java.lang.String body, java.lang.Long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last discussion message in the ordered set where body LIKE &#63; and categoryGroupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param body the body
    * @param categoryGroupId the category group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching discussion message
    * @throws com.ext.portlet.discussions.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessage findByBodyLike_Last(
        java.lang.String body, java.lang.Long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion messages before and after the current discussion message in the ordered set where body LIKE &#63; and categoryGroupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the current discussion message
    * @param body the body
    * @param categoryGroupId the category group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next discussion message
    * @throws com.ext.portlet.discussions.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessage[] findByBodyLike_PrevAndNext(
        java.lang.Long pk, java.lang.String body,
        java.lang.Long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message where messageId = &#63; or throws a {@link com.ext.portlet.discussions.NoSuchDiscussionMessageException} if it could not be found.
    *
    * @param messageId the message ID
    * @return the matching discussion message
    * @throws com.ext.portlet.discussions.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessage findByMessageId(
        java.lang.Long messageId)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message where messageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param messageId the message ID
    * @return the matching discussion message, or <code>null</code> if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessage fetchByMessageId(
        java.lang.Long messageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message where messageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param messageId the message ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching discussion message, or <code>null</code> if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.discussions.model.DiscussionMessage fetchByMessageId(
        java.lang.Long messageId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the discussion messages.
    *
    * @return the discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the discussion messages.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @return the range of discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the discussion messages.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the discussion messages where categoryId = &#63; and threadId = &#63; from the database.
    *
    * @param categoryId the category ID
    * @param threadId the thread ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByCategoryIdThreadId(java.lang.Long categoryId,
        java.lang.Long threadId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the discussion messages where threadId = &#63; from the database.
    *
    * @param threadId the thread ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByThreadId(java.lang.Long threadId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the discussion message where messageId = &#63; from the database.
    *
    * @param messageId the message ID
    * @throws SystemException if a system exception occurred
    */
    public void removeBySingleThreadId(java.lang.Long messageId)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the discussion messages where subject LIKE &#63; and categoryGroupId = &#63; from the database.
    *
    * @param subject the subject
    * @param categoryGroupId the category group ID
    * @throws SystemException if a system exception occurred
    */
    public void removeBySubjectLike(java.lang.String subject,
        java.lang.Long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the discussion messages where body LIKE &#63; and categoryGroupId = &#63; from the database.
    *
    * @param body the body
    * @param categoryGroupId the category group ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByBodyLike(java.lang.String body,
        java.lang.Long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the discussion message where messageId = &#63; from the database.
    *
    * @param messageId the message ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByMessageId(java.lang.Long messageId)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the discussion messages from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of discussion messages where categoryId = &#63; and threadId = &#63;.
    *
    * @param categoryId the category ID
    * @param threadId the thread ID
    * @return the number of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public int countByCategoryIdThreadId(java.lang.Long categoryId,
        java.lang.Long threadId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of discussion messages where threadId = &#63;.
    *
    * @param threadId the thread ID
    * @return the number of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public int countByThreadId(java.lang.Long threadId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of discussion messages where messageId = &#63;.
    *
    * @param messageId the message ID
    * @return the number of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public int countBySingleThreadId(java.lang.Long messageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of discussion messages where subject LIKE &#63; and categoryGroupId = &#63;.
    *
    * @param subject the subject
    * @param categoryGroupId the category group ID
    * @return the number of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public int countBySubjectLike(java.lang.String subject,
        java.lang.Long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of discussion messages where body LIKE &#63; and categoryGroupId = &#63;.
    *
    * @param body the body
    * @param categoryGroupId the category group ID
    * @return the number of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public int countByBodyLike(java.lang.String body,
        java.lang.Long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of discussion messages where messageId = &#63;.
    *
    * @param messageId the message ID
    * @return the number of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public int countByMessageId(java.lang.Long messageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of discussion messages.
    *
    * @return the number of discussion messages
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
