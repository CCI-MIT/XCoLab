package com.ext.portlet.service.persistence;

import com.ext.portlet.model.DiscussionMessage;

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
    * Returns all the discussion messages where categoryId = &#63; and threadId = &#63;.
    *
    * @param categoryId the category ID
    * @param threadId the thread ID
    * @return the matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.DiscussionMessage> findByCategoryIdThreadId(
        long categoryId, long threadId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the discussion messages where categoryId = &#63; and threadId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param categoryId the category ID
    * @param threadId the thread ID
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @return the range of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.DiscussionMessage> findByCategoryIdThreadId(
        long categoryId, long threadId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the discussion messages where categoryId = &#63; and threadId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    public java.util.List<com.ext.portlet.model.DiscussionMessage> findByCategoryIdThreadId(
        long categoryId, long threadId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first discussion message in the ordered set where categoryId = &#63; and threadId = &#63;.
    *
    * @param categoryId the category ID
    * @param threadId the thread ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage findByCategoryIdThreadId_First(
        long categoryId, long threadId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first discussion message in the ordered set where categoryId = &#63; and threadId = &#63;.
    *
    * @param categoryId the category ID
    * @param threadId the thread ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching discussion message, or <code>null</code> if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage fetchByCategoryIdThreadId_First(
        long categoryId, long threadId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last discussion message in the ordered set where categoryId = &#63; and threadId = &#63;.
    *
    * @param categoryId the category ID
    * @param threadId the thread ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage findByCategoryIdThreadId_Last(
        long categoryId, long threadId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last discussion message in the ordered set where categoryId = &#63; and threadId = &#63;.
    *
    * @param categoryId the category ID
    * @param threadId the thread ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching discussion message, or <code>null</code> if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage fetchByCategoryIdThreadId_Last(
        long categoryId, long threadId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion messages before and after the current discussion message in the ordered set where categoryId = &#63; and threadId = &#63;.
    *
    * @param pk the primary key of the current discussion message
    * @param categoryId the category ID
    * @param threadId the thread ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage[] findByCategoryIdThreadId_PrevAndNext(
        long pk, long categoryId, long threadId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the discussion messages where categoryId = &#63; and threadId = &#63; from the database.
    *
    * @param categoryId the category ID
    * @param threadId the thread ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByCategoryIdThreadId(long categoryId, long threadId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of discussion messages where categoryId = &#63; and threadId = &#63;.
    *
    * @param categoryId the category ID
    * @param threadId the thread ID
    * @return the number of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public int countByCategoryIdThreadId(long categoryId, long threadId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the discussion messages where threadId = &#63;.
    *
    * @param threadId the thread ID
    * @return the matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.DiscussionMessage> findByThreadId(
        long threadId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the discussion messages where threadId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param threadId the thread ID
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @return the range of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.DiscussionMessage> findByThreadId(
        long threadId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the discussion messages where threadId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param threadId the thread ID
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.DiscussionMessage> findByThreadId(
        long threadId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first discussion message in the ordered set where threadId = &#63;.
    *
    * @param threadId the thread ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage findByThreadId_First(
        long threadId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first discussion message in the ordered set where threadId = &#63;.
    *
    * @param threadId the thread ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching discussion message, or <code>null</code> if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage fetchByThreadId_First(
        long threadId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last discussion message in the ordered set where threadId = &#63;.
    *
    * @param threadId the thread ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage findByThreadId_Last(
        long threadId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last discussion message in the ordered set where threadId = &#63;.
    *
    * @param threadId the thread ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching discussion message, or <code>null</code> if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage fetchByThreadId_Last(
        long threadId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion messages before and after the current discussion message in the ordered set where threadId = &#63;.
    *
    * @param pk the primary key of the current discussion message
    * @param threadId the thread ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage[] findByThreadId_PrevAndNext(
        long pk, long threadId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the discussion messages where threadId = &#63; from the database.
    *
    * @param threadId the thread ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByThreadId(long threadId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of discussion messages where threadId = &#63;.
    *
    * @param threadId the thread ID
    * @return the number of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public int countByThreadId(long threadId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message where messageId = &#63; or throws a {@link com.ext.portlet.NoSuchDiscussionMessageException} if it could not be found.
    *
    * @param messageId the message ID
    * @return the matching discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage findBySingleThreadId(
        long messageId)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message where messageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param messageId the message ID
    * @return the matching discussion message, or <code>null</code> if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage fetchBySingleThreadId(
        long messageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message where messageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param messageId the message ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching discussion message, or <code>null</code> if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage fetchBySingleThreadId(
        long messageId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the discussion message where messageId = &#63; from the database.
    *
    * @param messageId the message ID
    * @return the discussion message that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage removeBySingleThreadId(
        long messageId)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of discussion messages where messageId = &#63;.
    *
    * @param messageId the message ID
    * @return the number of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public int countBySingleThreadId(long messageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the discussion messages where subject LIKE &#63; and categoryGroupId = &#63;.
    *
    * @param subject the subject
    * @param categoryGroupId the category group ID
    * @return the matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.DiscussionMessage> findBySubjectLike(
        java.lang.String subject, long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the discussion messages where subject LIKE &#63; and categoryGroupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param subject the subject
    * @param categoryGroupId the category group ID
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @return the range of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.DiscussionMessage> findBySubjectLike(
        java.lang.String subject, long categoryGroupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the discussion messages where subject LIKE &#63; and categoryGroupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    public java.util.List<com.ext.portlet.model.DiscussionMessage> findBySubjectLike(
        java.lang.String subject, long categoryGroupId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first discussion message in the ordered set where subject LIKE &#63; and categoryGroupId = &#63;.
    *
    * @param subject the subject
    * @param categoryGroupId the category group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage findBySubjectLike_First(
        java.lang.String subject, long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first discussion message in the ordered set where subject LIKE &#63; and categoryGroupId = &#63;.
    *
    * @param subject the subject
    * @param categoryGroupId the category group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching discussion message, or <code>null</code> if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage fetchBySubjectLike_First(
        java.lang.String subject, long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last discussion message in the ordered set where subject LIKE &#63; and categoryGroupId = &#63;.
    *
    * @param subject the subject
    * @param categoryGroupId the category group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage findBySubjectLike_Last(
        java.lang.String subject, long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last discussion message in the ordered set where subject LIKE &#63; and categoryGroupId = &#63;.
    *
    * @param subject the subject
    * @param categoryGroupId the category group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching discussion message, or <code>null</code> if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage fetchBySubjectLike_Last(
        java.lang.String subject, long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion messages before and after the current discussion message in the ordered set where subject LIKE &#63; and categoryGroupId = &#63;.
    *
    * @param pk the primary key of the current discussion message
    * @param subject the subject
    * @param categoryGroupId the category group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage[] findBySubjectLike_PrevAndNext(
        long pk, java.lang.String subject, long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the discussion messages where subject LIKE &#63; and categoryGroupId = &#63; from the database.
    *
    * @param subject the subject
    * @param categoryGroupId the category group ID
    * @throws SystemException if a system exception occurred
    */
    public void removeBySubjectLike(java.lang.String subject,
        long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of discussion messages where subject LIKE &#63; and categoryGroupId = &#63;.
    *
    * @param subject the subject
    * @param categoryGroupId the category group ID
    * @return the number of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public int countBySubjectLike(java.lang.String subject, long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the discussion messages where body LIKE &#63; and categoryGroupId = &#63;.
    *
    * @param body the body
    * @param categoryGroupId the category group ID
    * @return the matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.DiscussionMessage> findByBodyLike(
        java.lang.String body, long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the discussion messages where body LIKE &#63; and categoryGroupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param body the body
    * @param categoryGroupId the category group ID
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @return the range of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.DiscussionMessage> findByBodyLike(
        java.lang.String body, long categoryGroupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the discussion messages where body LIKE &#63; and categoryGroupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    public java.util.List<com.ext.portlet.model.DiscussionMessage> findByBodyLike(
        java.lang.String body, long categoryGroupId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first discussion message in the ordered set where body LIKE &#63; and categoryGroupId = &#63;.
    *
    * @param body the body
    * @param categoryGroupId the category group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage findByBodyLike_First(
        java.lang.String body, long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first discussion message in the ordered set where body LIKE &#63; and categoryGroupId = &#63;.
    *
    * @param body the body
    * @param categoryGroupId the category group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching discussion message, or <code>null</code> if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage fetchByBodyLike_First(
        java.lang.String body, long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last discussion message in the ordered set where body LIKE &#63; and categoryGroupId = &#63;.
    *
    * @param body the body
    * @param categoryGroupId the category group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage findByBodyLike_Last(
        java.lang.String body, long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last discussion message in the ordered set where body LIKE &#63; and categoryGroupId = &#63;.
    *
    * @param body the body
    * @param categoryGroupId the category group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching discussion message, or <code>null</code> if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage fetchByBodyLike_Last(
        java.lang.String body, long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion messages before and after the current discussion message in the ordered set where body LIKE &#63; and categoryGroupId = &#63;.
    *
    * @param pk the primary key of the current discussion message
    * @param body the body
    * @param categoryGroupId the category group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage[] findByBodyLike_PrevAndNext(
        long pk, java.lang.String body, long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the discussion messages where body LIKE &#63; and categoryGroupId = &#63; from the database.
    *
    * @param body the body
    * @param categoryGroupId the category group ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByBodyLike(java.lang.String body, long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of discussion messages where body LIKE &#63; and categoryGroupId = &#63;.
    *
    * @param body the body
    * @param categoryGroupId the category group ID
    * @return the number of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public int countByBodyLike(java.lang.String body, long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message where messageId = &#63; or throws a {@link com.ext.portlet.NoSuchDiscussionMessageException} if it could not be found.
    *
    * @param messageId the message ID
    * @return the matching discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage findByMessageId(
        long messageId)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message where messageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param messageId the message ID
    * @return the matching discussion message, or <code>null</code> if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage fetchByMessageId(
        long messageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message where messageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param messageId the message ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching discussion message, or <code>null</code> if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage fetchByMessageId(
        long messageId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the discussion message where messageId = &#63; from the database.
    *
    * @param messageId the message ID
    * @return the discussion message that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage removeByMessageId(
        long messageId)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of discussion messages where messageId = &#63;.
    *
    * @param messageId the message ID
    * @return the number of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public int countByMessageId(long messageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the discussion messages where authorId = &#63;.
    *
    * @param authorId the author ID
    * @return the matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.DiscussionMessage> findByAuthorId(
        long authorId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the discussion messages where authorId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param authorId the author ID
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @return the range of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.DiscussionMessage> findByAuthorId(
        long authorId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the discussion messages where authorId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param authorId the author ID
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.DiscussionMessage> findByAuthorId(
        long authorId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first discussion message in the ordered set where authorId = &#63;.
    *
    * @param authorId the author ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage findByAuthorId_First(
        long authorId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first discussion message in the ordered set where authorId = &#63;.
    *
    * @param authorId the author ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching discussion message, or <code>null</code> if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage fetchByAuthorId_First(
        long authorId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last discussion message in the ordered set where authorId = &#63;.
    *
    * @param authorId the author ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage findByAuthorId_Last(
        long authorId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last discussion message in the ordered set where authorId = &#63;.
    *
    * @param authorId the author ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching discussion message, or <code>null</code> if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage fetchByAuthorId_Last(
        long authorId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion messages before and after the current discussion message in the ordered set where authorId = &#63;.
    *
    * @param pk the primary key of the current discussion message
    * @param authorId the author ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage[] findByAuthorId_PrevAndNext(
        long pk, long authorId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the discussion messages where authorId = &#63; from the database.
    *
    * @param authorId the author ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByAuthorId(long authorId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of discussion messages where authorId = &#63;.
    *
    * @param authorId the author ID
    * @return the number of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public int countByAuthorId(long authorId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the discussion message in the entity cache if it is enabled.
    *
    * @param discussionMessage the discussion message
    */
    public void cacheResult(
        com.ext.portlet.model.DiscussionMessage discussionMessage);

    /**
    * Caches the discussion messages in the entity cache if it is enabled.
    *
    * @param discussionMessages the discussion messages
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.DiscussionMessage> discussionMessages);

    /**
    * Creates a new discussion message with the primary key. Does not add the discussion message to the database.
    *
    * @param pk the primary key for the new discussion message
    * @return the new discussion message
    */
    public com.ext.portlet.model.DiscussionMessage create(long pk);

    /**
    * Removes the discussion message with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param pk the primary key of the discussion message
    * @return the discussion message that was removed
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage remove(long pk)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.DiscussionMessage updateImpl(
        com.ext.portlet.model.DiscussionMessage discussionMessage)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message with the primary key or throws a {@link com.ext.portlet.NoSuchDiscussionMessageException} if it could not be found.
    *
    * @param pk the primary key of the discussion message
    * @return the discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage findByPrimaryKey(long pk)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param pk the primary key of the discussion message
    * @return the discussion message, or <code>null</code> if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessage fetchByPrimaryKey(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the discussion messages.
    *
    * @return the discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.DiscussionMessage> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the discussion messages.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @return the range of discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.DiscussionMessage> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the discussion messages.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of discussion messages
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.DiscussionMessage> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the discussion messages from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
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
