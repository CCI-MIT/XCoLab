package com.ext.portlet.service.persistence;

import com.ext.portlet.model.DiscussionMessage;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the discussion message service. This utility wraps {@link DiscussionMessagePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionMessagePersistence
 * @see DiscussionMessagePersistenceImpl
 * @generated
 */
public class DiscussionMessageUtil {
    private static DiscussionMessagePersistence _persistence;

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
    public static void clearCache(DiscussionMessage discussionMessage) {
        getPersistence().clearCache(discussionMessage);
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
    public static List<DiscussionMessage> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<DiscussionMessage> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<DiscussionMessage> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static DiscussionMessage update(
        DiscussionMessage discussionMessage, boolean merge)
        throws SystemException {
        return getPersistence().update(discussionMessage, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static DiscussionMessage update(
        DiscussionMessage discussionMessage, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(discussionMessage, merge, serviceContext);
    }

    /**
    * Caches the discussion message in the entity cache if it is enabled.
    *
    * @param discussionMessage the discussion message
    */
    public static void cacheResult(
        com.ext.portlet.model.DiscussionMessage discussionMessage) {
        getPersistence().cacheResult(discussionMessage);
    }

    /**
    * Caches the discussion messages in the entity cache if it is enabled.
    *
    * @param discussionMessages the discussion messages
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.DiscussionMessage> discussionMessages) {
        getPersistence().cacheResult(discussionMessages);
    }

    /**
    * Creates a new discussion message with the primary key. Does not add the discussion message to the database.
    *
    * @param pk the primary key for the new discussion message
    * @return the new discussion message
    */
    public static com.ext.portlet.model.DiscussionMessage create(long pk) {
        return getPersistence().create(pk);
    }

    /**
    * Removes the discussion message with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param pk the primary key of the discussion message
    * @return the discussion message that was removed
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage remove(long pk)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(pk);
    }

    public static com.ext.portlet.model.DiscussionMessage updateImpl(
        com.ext.portlet.model.DiscussionMessage discussionMessage, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(discussionMessage, merge);
    }

    /**
    * Returns the discussion message with the primary key or throws a {@link com.ext.portlet.NoSuchDiscussionMessageException} if it could not be found.
    *
    * @param pk the primary key of the discussion message
    * @return the discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage findByPrimaryKey(
        long pk)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(pk);
    }

    /**
    * Returns the discussion message with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param pk the primary key of the discussion message
    * @return the discussion message, or <code>null</code> if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage fetchByPrimaryKey(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(pk);
    }

    /**
    * Returns all the discussion messages where categoryId = &#63; and threadId = &#63;.
    *
    * @param categoryId the category ID
    * @param threadId the thread ID
    * @return the matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionMessage> findByCategoryIdThreadId(
        long categoryId, long threadId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCategoryIdThreadId(categoryId, threadId);
    }

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
    public static java.util.List<com.ext.portlet.model.DiscussionMessage> findByCategoryIdThreadId(
        long categoryId, long threadId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCategoryIdThreadId(categoryId, threadId, start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.DiscussionMessage> findByCategoryIdThreadId(
        long categoryId, long threadId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCategoryIdThreadId(categoryId, threadId, start, end,
            orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage findByCategoryIdThreadId_First(
        long categoryId, long threadId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCategoryIdThreadId_First(categoryId, threadId,
            orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage findByCategoryIdThreadId_Last(
        long categoryId, long threadId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCategoryIdThreadId_Last(categoryId, threadId,
            orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage[] findByCategoryIdThreadId_PrevAndNext(
        long pk, long categoryId, long threadId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCategoryIdThreadId_PrevAndNext(pk, categoryId,
            threadId, orderByComparator);
    }

    /**
    * Returns all the discussion messages where threadId = &#63;.
    *
    * @param threadId the thread ID
    * @return the matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionMessage> findByThreadId(
        long threadId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByThreadId(threadId);
    }

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
    public static java.util.List<com.ext.portlet.model.DiscussionMessage> findByThreadId(
        long threadId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByThreadId(threadId, start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.DiscussionMessage> findByThreadId(
        long threadId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByThreadId(threadId, start, end, orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage findByThreadId_First(
        long threadId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByThreadId_First(threadId, orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage findByThreadId_Last(
        long threadId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByThreadId_Last(threadId, orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage[] findByThreadId_PrevAndNext(
        long pk, long threadId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByThreadId_PrevAndNext(pk, threadId, orderByComparator);
    }

    /**
    * Returns the discussion message where messageId = &#63; or throws a {@link com.ext.portlet.NoSuchDiscussionMessageException} if it could not be found.
    *
    * @param messageId the message ID
    * @return the matching discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage findBySingleThreadId(
        long messageId)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySingleThreadId(messageId);
    }

    /**
    * Returns the discussion message where messageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param messageId the message ID
    * @return the matching discussion message, or <code>null</code> if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage fetchBySingleThreadId(
        long messageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchBySingleThreadId(messageId);
    }

    /**
    * Returns the discussion message where messageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param messageId the message ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching discussion message, or <code>null</code> if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage fetchBySingleThreadId(
        long messageId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySingleThreadId(messageId, retrieveFromCache);
    }

    /**
    * Returns all the discussion messages where subject LIKE &#63; and categoryGroupId = &#63;.
    *
    * @param subject the subject
    * @param categoryGroupId the category group ID
    * @return the matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionMessage> findBySubjectLike(
        java.lang.String subject, long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySubjectLike(subject, categoryGroupId);
    }

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
    public static java.util.List<com.ext.portlet.model.DiscussionMessage> findBySubjectLike(
        java.lang.String subject, long categoryGroupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySubjectLike(subject, categoryGroupId, start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.DiscussionMessage> findBySubjectLike(
        java.lang.String subject, long categoryGroupId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySubjectLike(subject, categoryGroupId, start, end,
            orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage findBySubjectLike_First(
        java.lang.String subject, long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySubjectLike_First(subject, categoryGroupId,
            orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage findBySubjectLike_Last(
        java.lang.String subject, long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySubjectLike_Last(subject, categoryGroupId,
            orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage[] findBySubjectLike_PrevAndNext(
        long pk, java.lang.String subject, long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySubjectLike_PrevAndNext(pk, subject, categoryGroupId,
            orderByComparator);
    }

    /**
    * Returns all the discussion messages where body LIKE &#63; and categoryGroupId = &#63;.
    *
    * @param body the body
    * @param categoryGroupId the category group ID
    * @return the matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionMessage> findByBodyLike(
        java.lang.String body, long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByBodyLike(body, categoryGroupId);
    }

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
    public static java.util.List<com.ext.portlet.model.DiscussionMessage> findByBodyLike(
        java.lang.String body, long categoryGroupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByBodyLike(body, categoryGroupId, start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.DiscussionMessage> findByBodyLike(
        java.lang.String body, long categoryGroupId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBodyLike(body, categoryGroupId, start, end,
            orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage findByBodyLike_First(
        java.lang.String body, long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBodyLike_First(body, categoryGroupId,
            orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage findByBodyLike_Last(
        java.lang.String body, long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBodyLike_Last(body, categoryGroupId, orderByComparator);
    }

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
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage[] findByBodyLike_PrevAndNext(
        long pk, java.lang.String body, long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBodyLike_PrevAndNext(pk, body, categoryGroupId,
            orderByComparator);
    }

    /**
    * Returns the discussion message where messageId = &#63; or throws a {@link com.ext.portlet.NoSuchDiscussionMessageException} if it could not be found.
    *
    * @param messageId the message ID
    * @return the matching discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage findByMessageId(
        long messageId)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByMessageId(messageId);
    }

    /**
    * Returns the discussion message where messageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param messageId the message ID
    * @return the matching discussion message, or <code>null</code> if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage fetchByMessageId(
        long messageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByMessageId(messageId);
    }

    /**
    * Returns the discussion message where messageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param messageId the message ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching discussion message, or <code>null</code> if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage fetchByMessageId(
        long messageId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByMessageId(messageId, retrieveFromCache);
    }

    /**
    * Returns all the discussion messages where authorId = &#63;.
    *
    * @param authorId the author ID
    * @return the matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionMessage> findByAuthorId(
        long authorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByAuthorId(authorId);
    }

    /**
    * Returns a range of all the discussion messages where authorId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param authorId the author ID
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @return the range of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionMessage> findByAuthorId(
        long authorId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByAuthorId(authorId, start, end);
    }

    /**
    * Returns an ordered range of all the discussion messages where authorId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param authorId the author ID
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionMessage> findByAuthorId(
        long authorId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAuthorId(authorId, start, end, orderByComparator);
    }

    /**
    * Returns the first discussion message in the ordered set where authorId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param authorId the author ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage findByAuthorId_First(
        long authorId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByAuthorId_First(authorId, orderByComparator);
    }

    /**
    * Returns the last discussion message in the ordered set where authorId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param authorId the author ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a matching discussion message could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage findByAuthorId_Last(
        long authorId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByAuthorId_Last(authorId, orderByComparator);
    }

    /**
    * Returns the discussion messages before and after the current discussion message in the ordered set where authorId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the current discussion message
    * @param authorId the author ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next discussion message
    * @throws com.ext.portlet.NoSuchDiscussionMessageException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessage[] findByAuthorId_PrevAndNext(
        long pk, long authorId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAuthorId_PrevAndNext(pk, authorId, orderByComparator);
    }

    /**
    * Returns all the discussion messages.
    *
    * @return the discussion messages
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionMessage> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<com.ext.portlet.model.DiscussionMessage> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.DiscussionMessage> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the discussion messages where categoryId = &#63; and threadId = &#63; from the database.
    *
    * @param categoryId the category ID
    * @param threadId the thread ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCategoryIdThreadId(long categoryId, long threadId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCategoryIdThreadId(categoryId, threadId);
    }

    /**
    * Removes all the discussion messages where threadId = &#63; from the database.
    *
    * @param threadId the thread ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByThreadId(long threadId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByThreadId(threadId);
    }

    /**
    * Removes the discussion message where messageId = &#63; from the database.
    *
    * @param messageId the message ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeBySingleThreadId(long messageId)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeBySingleThreadId(messageId);
    }

    /**
    * Removes all the discussion messages where subject LIKE &#63; and categoryGroupId = &#63; from the database.
    *
    * @param subject the subject
    * @param categoryGroupId the category group ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeBySubjectLike(java.lang.String subject,
        long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeBySubjectLike(subject, categoryGroupId);
    }

    /**
    * Removes all the discussion messages where body LIKE &#63; and categoryGroupId = &#63; from the database.
    *
    * @param body the body
    * @param categoryGroupId the category group ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByBodyLike(java.lang.String body,
        long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByBodyLike(body, categoryGroupId);
    }

    /**
    * Removes the discussion message where messageId = &#63; from the database.
    *
    * @param messageId the message ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByMessageId(long messageId)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByMessageId(messageId);
    }

    /**
    * Removes all the discussion messages where authorId = &#63; from the database.
    *
    * @param authorId the author ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByAuthorId(long authorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByAuthorId(authorId);
    }

    /**
    * Removes all the discussion messages from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of discussion messages where categoryId = &#63; and threadId = &#63;.
    *
    * @param categoryId the category ID
    * @param threadId the thread ID
    * @return the number of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public static int countByCategoryIdThreadId(long categoryId, long threadId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCategoryIdThreadId(categoryId, threadId);
    }

    /**
    * Returns the number of discussion messages where threadId = &#63;.
    *
    * @param threadId the thread ID
    * @return the number of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public static int countByThreadId(long threadId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByThreadId(threadId);
    }

    /**
    * Returns the number of discussion messages where messageId = &#63;.
    *
    * @param messageId the message ID
    * @return the number of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public static int countBySingleThreadId(long messageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countBySingleThreadId(messageId);
    }

    /**
    * Returns the number of discussion messages where subject LIKE &#63; and categoryGroupId = &#63;.
    *
    * @param subject the subject
    * @param categoryGroupId the category group ID
    * @return the number of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public static int countBySubjectLike(java.lang.String subject,
        long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countBySubjectLike(subject, categoryGroupId);
    }

    /**
    * Returns the number of discussion messages where body LIKE &#63; and categoryGroupId = &#63;.
    *
    * @param body the body
    * @param categoryGroupId the category group ID
    * @return the number of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public static int countByBodyLike(java.lang.String body,
        long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByBodyLike(body, categoryGroupId);
    }

    /**
    * Returns the number of discussion messages where messageId = &#63;.
    *
    * @param messageId the message ID
    * @return the number of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public static int countByMessageId(long messageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByMessageId(messageId);
    }

    /**
    * Returns the number of discussion messages where authorId = &#63;.
    *
    * @param authorId the author ID
    * @return the number of matching discussion messages
    * @throws SystemException if a system exception occurred
    */
    public static int countByAuthorId(long authorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByAuthorId(authorId);
    }

    /**
    * Returns the number of discussion messages.
    *
    * @return the number of discussion messages
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static DiscussionMessagePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (DiscussionMessagePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    DiscussionMessagePersistence.class.getName());

            ReferenceRegistry.registerReference(DiscussionMessageUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(DiscussionMessagePersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(DiscussionMessageUtil.class,
            "_persistence");
    }
}
