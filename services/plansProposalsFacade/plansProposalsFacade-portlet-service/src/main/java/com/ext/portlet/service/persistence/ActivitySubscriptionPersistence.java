package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ActivitySubscription;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the activity subscription service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ActivitySubscriptionPersistenceImpl
 * @see ActivitySubscriptionUtil
 * @generated
 */
public interface ActivitySubscriptionPersistence extends BasePersistence<ActivitySubscription> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ActivitySubscriptionUtil} to access the activity subscription persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the activity subscriptions where receiverId = &#63;.
    *
    * @param receiverId the receiver ID
    * @return the matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ActivitySubscription> findByreceiverId(
        long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the activity subscriptions where receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ActivitySubscriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param receiverId the receiver ID
    * @param start the lower bound of the range of activity subscriptions
    * @param end the upper bound of the range of activity subscriptions (not inclusive)
    * @return the range of matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ActivitySubscription> findByreceiverId(
        long receiverId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the activity subscriptions where receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ActivitySubscriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param receiverId the receiver ID
    * @param start the lower bound of the range of activity subscriptions
    * @param end the upper bound of the range of activity subscriptions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ActivitySubscription> findByreceiverId(
        long receiverId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first activity subscription in the ordered set where receiverId = &#63;.
    *
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching activity subscription
    * @throws com.ext.portlet.NoSuchActivitySubscriptionException if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription findByreceiverId_First(
        long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchActivitySubscriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first activity subscription in the ordered set where receiverId = &#63;.
    *
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching activity subscription, or <code>null</code> if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription fetchByreceiverId_First(
        long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last activity subscription in the ordered set where receiverId = &#63;.
    *
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching activity subscription
    * @throws com.ext.portlet.NoSuchActivitySubscriptionException if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription findByreceiverId_Last(
        long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchActivitySubscriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last activity subscription in the ordered set where receiverId = &#63;.
    *
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching activity subscription, or <code>null</code> if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription fetchByreceiverId_Last(
        long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the activity subscriptions before and after the current activity subscription in the ordered set where receiverId = &#63;.
    *
    * @param pk the primary key of the current activity subscription
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next activity subscription
    * @throws com.ext.portlet.NoSuchActivitySubscriptionException if a activity subscription with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription[] findByreceiverId_PrevAndNext(
        long pk, long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchActivitySubscriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the activity subscriptions where receiverId = &#63; from the database.
    *
    * @param receiverId the receiver ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByreceiverId(long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of activity subscriptions where receiverId = &#63;.
    *
    * @param receiverId the receiver ID
    * @return the number of matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public int countByreceiverId(long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the activity subscriptions where classNameId = &#63; and classPK = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @return the matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ActivitySubscription> findByClassNameIdClassPK(
        long classNameId, long classPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the activity subscriptions where classNameId = &#63; and classPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ActivitySubscriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param start the lower bound of the range of activity subscriptions
    * @param end the upper bound of the range of activity subscriptions (not inclusive)
    * @return the range of matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ActivitySubscription> findByClassNameIdClassPK(
        long classNameId, long classPK, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the activity subscriptions where classNameId = &#63; and classPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ActivitySubscriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param start the lower bound of the range of activity subscriptions
    * @param end the upper bound of the range of activity subscriptions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ActivitySubscription> findByClassNameIdClassPK(
        long classNameId, long classPK, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first activity subscription in the ordered set where classNameId = &#63; and classPK = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching activity subscription
    * @throws com.ext.portlet.NoSuchActivitySubscriptionException if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription findByClassNameIdClassPK_First(
        long classNameId, long classPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchActivitySubscriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first activity subscription in the ordered set where classNameId = &#63; and classPK = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching activity subscription, or <code>null</code> if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription fetchByClassNameIdClassPK_First(
        long classNameId, long classPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last activity subscription in the ordered set where classNameId = &#63; and classPK = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching activity subscription
    * @throws com.ext.portlet.NoSuchActivitySubscriptionException if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription findByClassNameIdClassPK_Last(
        long classNameId, long classPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchActivitySubscriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last activity subscription in the ordered set where classNameId = &#63; and classPK = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching activity subscription, or <code>null</code> if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription fetchByClassNameIdClassPK_Last(
        long classNameId, long classPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the activity subscriptions before and after the current activity subscription in the ordered set where classNameId = &#63; and classPK = &#63;.
    *
    * @param pk the primary key of the current activity subscription
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next activity subscription
    * @throws com.ext.portlet.NoSuchActivitySubscriptionException if a activity subscription with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription[] findByClassNameIdClassPK_PrevAndNext(
        long pk, long classNameId, long classPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchActivitySubscriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the activity subscriptions where classNameId = &#63; and classPK = &#63; from the database.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @throws SystemException if a system exception occurred
    */
    public void removeByClassNameIdClassPK(long classNameId, long classPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of activity subscriptions where classNameId = &#63; and classPK = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @return the number of matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public int countByClassNameIdClassPK(long classNameId, long classPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the activity subscriptions where classNameId = &#63; and classPK = &#63; and receiverId = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param receiverId the receiver ID
    * @return the matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ActivitySubscription> findByClassNameIdClassPKReceiverId(
        long classNameId, long classPK, long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the activity subscriptions where classNameId = &#63; and classPK = &#63; and receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ActivitySubscriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param receiverId the receiver ID
    * @param start the lower bound of the range of activity subscriptions
    * @param end the upper bound of the range of activity subscriptions (not inclusive)
    * @return the range of matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ActivitySubscription> findByClassNameIdClassPKReceiverId(
        long classNameId, long classPK, long receiverId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the activity subscriptions where classNameId = &#63; and classPK = &#63; and receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ActivitySubscriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param receiverId the receiver ID
    * @param start the lower bound of the range of activity subscriptions
    * @param end the upper bound of the range of activity subscriptions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ActivitySubscription> findByClassNameIdClassPKReceiverId(
        long classNameId, long classPK, long receiverId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and receiverId = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching activity subscription
    * @throws com.ext.portlet.NoSuchActivitySubscriptionException if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription findByClassNameIdClassPKReceiverId_First(
        long classNameId, long classPK, long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchActivitySubscriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and receiverId = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching activity subscription, or <code>null</code> if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription fetchByClassNameIdClassPKReceiverId_First(
        long classNameId, long classPK, long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and receiverId = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching activity subscription
    * @throws com.ext.portlet.NoSuchActivitySubscriptionException if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription findByClassNameIdClassPKReceiverId_Last(
        long classNameId, long classPK, long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchActivitySubscriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and receiverId = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching activity subscription, or <code>null</code> if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription fetchByClassNameIdClassPKReceiverId_Last(
        long classNameId, long classPK, long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the activity subscriptions before and after the current activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and receiverId = &#63;.
    *
    * @param pk the primary key of the current activity subscription
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next activity subscription
    * @throws com.ext.portlet.NoSuchActivitySubscriptionException if a activity subscription with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription[] findByClassNameIdClassPKReceiverId_PrevAndNext(
        long pk, long classNameId, long classPK, long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchActivitySubscriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the activity subscriptions where classNameId = &#63; and classPK = &#63; and receiverId = &#63; from the database.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param receiverId the receiver ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByClassNameIdClassPKReceiverId(long classNameId,
        long classPK, long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of activity subscriptions where classNameId = &#63; and classPK = &#63; and receiverId = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param receiverId the receiver ID
    * @return the number of matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public int countByClassNameIdClassPKReceiverId(long classNameId,
        long classPK, long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the activity subscriptions where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverId = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param receiverId the receiver ID
    * @return the matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ActivitySubscription> findByClassNameIdClassPKTypeReceiverId(
        long classNameId, long classPK, int type, long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the activity subscriptions where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ActivitySubscriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param receiverId the receiver ID
    * @param start the lower bound of the range of activity subscriptions
    * @param end the upper bound of the range of activity subscriptions (not inclusive)
    * @return the range of matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ActivitySubscription> findByClassNameIdClassPKTypeReceiverId(
        long classNameId, long classPK, int type, long receiverId, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the activity subscriptions where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ActivitySubscriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param receiverId the receiver ID
    * @param start the lower bound of the range of activity subscriptions
    * @param end the upper bound of the range of activity subscriptions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ActivitySubscription> findByClassNameIdClassPKTypeReceiverId(
        long classNameId, long classPK, int type, long receiverId, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverId = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching activity subscription
    * @throws com.ext.portlet.NoSuchActivitySubscriptionException if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription findByClassNameIdClassPKTypeReceiverId_First(
        long classNameId, long classPK, int type, long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchActivitySubscriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverId = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching activity subscription, or <code>null</code> if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription fetchByClassNameIdClassPKTypeReceiverId_First(
        long classNameId, long classPK, int type, long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverId = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching activity subscription
    * @throws com.ext.portlet.NoSuchActivitySubscriptionException if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription findByClassNameIdClassPKTypeReceiverId_Last(
        long classNameId, long classPK, int type, long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchActivitySubscriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverId = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching activity subscription, or <code>null</code> if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription fetchByClassNameIdClassPKTypeReceiverId_Last(
        long classNameId, long classPK, int type, long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the activity subscriptions before and after the current activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverId = &#63;.
    *
    * @param pk the primary key of the current activity subscription
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next activity subscription
    * @throws com.ext.portlet.NoSuchActivitySubscriptionException if a activity subscription with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription[] findByClassNameIdClassPKTypeReceiverId_PrevAndNext(
        long pk, long classNameId, long classPK, int type, long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchActivitySubscriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the activity subscriptions where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverId = &#63; from the database.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param receiverId the receiver ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByClassNameIdClassPKTypeReceiverId(long classNameId,
        long classPK, int type, long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of activity subscriptions where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverId = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param receiverId the receiver ID
    * @return the number of matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public int countByClassNameIdClassPKTypeReceiverId(long classNameId,
        long classPK, int type, long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the activity subscriptions where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63; and receiverId = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @param receiverId the receiver ID
    * @return the matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraDataReceiverId(
        long classNameId, long classPK, int type, java.lang.String extraData,
        long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the activity subscriptions where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63; and receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ActivitySubscriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @param receiverId the receiver ID
    * @param start the lower bound of the range of activity subscriptions
    * @param end the upper bound of the range of activity subscriptions (not inclusive)
    * @return the range of matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraDataReceiverId(
        long classNameId, long classPK, int type, java.lang.String extraData,
        long receiverId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the activity subscriptions where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63; and receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ActivitySubscriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @param receiverId the receiver ID
    * @param start the lower bound of the range of activity subscriptions
    * @param end the upper bound of the range of activity subscriptions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraDataReceiverId(
        long classNameId, long classPK, int type, java.lang.String extraData,
        long receiverId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63; and receiverId = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching activity subscription
    * @throws com.ext.portlet.NoSuchActivitySubscriptionException if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription findByClassNameIdClassPKTypeExtraDataReceiverId_First(
        long classNameId, long classPK, int type, java.lang.String extraData,
        long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchActivitySubscriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63; and receiverId = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching activity subscription, or <code>null</code> if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription fetchByClassNameIdClassPKTypeExtraDataReceiverId_First(
        long classNameId, long classPK, int type, java.lang.String extraData,
        long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63; and receiverId = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching activity subscription
    * @throws com.ext.portlet.NoSuchActivitySubscriptionException if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription findByClassNameIdClassPKTypeExtraDataReceiverId_Last(
        long classNameId, long classPK, int type, java.lang.String extraData,
        long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchActivitySubscriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63; and receiverId = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching activity subscription, or <code>null</code> if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription fetchByClassNameIdClassPKTypeExtraDataReceiverId_Last(
        long classNameId, long classPK, int type, java.lang.String extraData,
        long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the activity subscriptions before and after the current activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63; and receiverId = &#63;.
    *
    * @param pk the primary key of the current activity subscription
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next activity subscription
    * @throws com.ext.portlet.NoSuchActivitySubscriptionException if a activity subscription with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription[] findByClassNameIdClassPKTypeExtraDataReceiverId_PrevAndNext(
        long pk, long classNameId, long classPK, int type,
        java.lang.String extraData, long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchActivitySubscriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the activity subscriptions where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63; and receiverId = &#63; from the database.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @param receiverId the receiver ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByClassNameIdClassPKTypeExtraDataReceiverId(
        long classNameId, long classPK, int type, java.lang.String extraData,
        long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of activity subscriptions where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63; and receiverId = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @param receiverId the receiver ID
    * @return the number of matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public int countByClassNameIdClassPKTypeExtraDataReceiverId(
        long classNameId, long classPK, int type, java.lang.String extraData,
        long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the activity subscriptions where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @return the matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraData(
        long classNameId, long classPK, int type, java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the activity subscriptions where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ActivitySubscriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @param start the lower bound of the range of activity subscriptions
    * @param end the upper bound of the range of activity subscriptions (not inclusive)
    * @return the range of matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraData(
        long classNameId, long classPK, int type, java.lang.String extraData,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the activity subscriptions where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ActivitySubscriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @param start the lower bound of the range of activity subscriptions
    * @param end the upper bound of the range of activity subscriptions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraData(
        long classNameId, long classPK, int type, java.lang.String extraData,
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching activity subscription
    * @throws com.ext.portlet.NoSuchActivitySubscriptionException if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription findByClassNameIdClassPKTypeExtraData_First(
        long classNameId, long classPK, int type, java.lang.String extraData,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchActivitySubscriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching activity subscription, or <code>null</code> if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription fetchByClassNameIdClassPKTypeExtraData_First(
        long classNameId, long classPK, int type, java.lang.String extraData,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching activity subscription
    * @throws com.ext.portlet.NoSuchActivitySubscriptionException if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription findByClassNameIdClassPKTypeExtraData_Last(
        long classNameId, long classPK, int type, java.lang.String extraData,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchActivitySubscriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching activity subscription, or <code>null</code> if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription fetchByClassNameIdClassPKTypeExtraData_Last(
        long classNameId, long classPK, int type, java.lang.String extraData,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the activity subscriptions before and after the current activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63;.
    *
    * @param pk the primary key of the current activity subscription
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next activity subscription
    * @throws com.ext.portlet.NoSuchActivitySubscriptionException if a activity subscription with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription[] findByClassNameIdClassPKTypeExtraData_PrevAndNext(
        long pk, long classNameId, long classPK, int type,
        java.lang.String extraData,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchActivitySubscriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the activity subscriptions where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63; from the database.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @throws SystemException if a system exception occurred
    */
    public void removeByClassNameIdClassPKTypeExtraData(long classNameId,
        long classPK, int type, java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of activity subscriptions where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @return the number of matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public int countByClassNameIdClassPKTypeExtraData(long classNameId,
        long classPK, int type, java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the activity subscription in the entity cache if it is enabled.
    *
    * @param activitySubscription the activity subscription
    */
    public void cacheResult(
        com.ext.portlet.model.ActivitySubscription activitySubscription);

    /**
    * Caches the activity subscriptions in the entity cache if it is enabled.
    *
    * @param activitySubscriptions the activity subscriptions
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ActivitySubscription> activitySubscriptions);

    /**
    * Creates a new activity subscription with the primary key. Does not add the activity subscription to the database.
    *
    * @param pk the primary key for the new activity subscription
    * @return the new activity subscription
    */
    public com.ext.portlet.model.ActivitySubscription create(long pk);

    /**
    * Removes the activity subscription with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param pk the primary key of the activity subscription
    * @return the activity subscription that was removed
    * @throws com.ext.portlet.NoSuchActivitySubscriptionException if a activity subscription with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription remove(long pk)
        throws com.ext.portlet.NoSuchActivitySubscriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ActivitySubscription updateImpl(
        com.ext.portlet.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the activity subscription with the primary key or throws a {@link com.ext.portlet.NoSuchActivitySubscriptionException} if it could not be found.
    *
    * @param pk the primary key of the activity subscription
    * @return the activity subscription
    * @throws com.ext.portlet.NoSuchActivitySubscriptionException if a activity subscription with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription findByPrimaryKey(long pk)
        throws com.ext.portlet.NoSuchActivitySubscriptionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the activity subscription with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param pk the primary key of the activity subscription
    * @return the activity subscription, or <code>null</code> if a activity subscription with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription fetchByPrimaryKey(long pk)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the activity subscriptions.
    *
    * @return the activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ActivitySubscription> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the activity subscriptions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ActivitySubscriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of activity subscriptions
    * @param end the upper bound of the range of activity subscriptions (not inclusive)
    * @return the range of activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ActivitySubscription> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the activity subscriptions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ActivitySubscriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of activity subscriptions
    * @param end the upper bound of the range of activity subscriptions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ActivitySubscription> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the activity subscriptions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of activity subscriptions.
    *
    * @return the number of activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
