package com.ext.portlet.Activity.service.persistence;

import com.ext.portlet.Activity.model.ActivitySubscription;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the activity subscription service. This utility wraps {@link ActivitySubscriptionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ActivitySubscriptionPersistence
 * @see ActivitySubscriptionPersistenceImpl
 * @generated
 */
public class ActivitySubscriptionUtil {
    private static ActivitySubscriptionPersistence _persistence;

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
    public static void clearCache(ActivitySubscription activitySubscription) {
        getPersistence().clearCache(activitySubscription);
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
    public static List<ActivitySubscription> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ActivitySubscription> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ActivitySubscription> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static ActivitySubscription update(
        ActivitySubscription activitySubscription, boolean merge)
        throws SystemException {
        return getPersistence().update(activitySubscription, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static ActivitySubscription update(
        ActivitySubscription activitySubscription, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence()
                   .update(activitySubscription, merge, serviceContext);
    }

    /**
    * Caches the activity subscription in the entity cache if it is enabled.
    *
    * @param activitySubscription the activity subscription
    */
    public static void cacheResult(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription) {
        getPersistence().cacheResult(activitySubscription);
    }

    /**
    * Caches the activity subscriptions in the entity cache if it is enabled.
    *
    * @param activitySubscriptions the activity subscriptions
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> activitySubscriptions) {
        getPersistence().cacheResult(activitySubscriptions);
    }

    /**
    * Creates a new activity subscription with the primary key. Does not add the activity subscription to the database.
    *
    * @param pk the primary key for the new activity subscription
    * @return the new activity subscription
    */
    public static com.ext.portlet.Activity.model.ActivitySubscription create(
        java.lang.Long pk) {
        return getPersistence().create(pk);
    }

    /**
    * Removes the activity subscription with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param pk the primary key of the activity subscription
    * @return the activity subscription that was removed
    * @throws com.ext.portlet.Activity.NoSuchSubscriptionException if a activity subscription with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.Activity.model.ActivitySubscription remove(
        java.lang.Long pk)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(pk);
    }

    public static com.ext.portlet.Activity.model.ActivitySubscription updateImpl(
        com.ext.portlet.Activity.model.ActivitySubscription activitySubscription,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(activitySubscription, merge);
    }

    /**
    * Returns the activity subscription with the primary key or throws a {@link com.ext.portlet.Activity.NoSuchSubscriptionException} if it could not be found.
    *
    * @param pk the primary key of the activity subscription
    * @return the activity subscription
    * @throws com.ext.portlet.Activity.NoSuchSubscriptionException if a activity subscription with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.Activity.model.ActivitySubscription findByPrimaryKey(
        java.lang.Long pk)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(pk);
    }

    /**
    * Returns the activity subscription with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param pk the primary key of the activity subscription
    * @return the activity subscription, or <code>null</code> if a activity subscription with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.Activity.model.ActivitySubscription fetchByPrimaryKey(
        java.lang.Long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(pk);
    }

    /**
    * Returns all the activity subscriptions where receiverId = &#63;.
    *
    * @param receiverId the receiver ID
    * @return the matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByreceiverId(
        java.lang.Long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByreceiverId(receiverId);
    }

    /**
    * Returns a range of all the activity subscriptions where receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param receiverId the receiver ID
    * @param start the lower bound of the range of activity subscriptions
    * @param end the upper bound of the range of activity subscriptions (not inclusive)
    * @return the range of matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByreceiverId(
        java.lang.Long receiverId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByreceiverId(receiverId, start, end);
    }

    /**
    * Returns an ordered range of all the activity subscriptions where receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param receiverId the receiver ID
    * @param start the lower bound of the range of activity subscriptions
    * @param end the upper bound of the range of activity subscriptions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByreceiverId(
        java.lang.Long receiverId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByreceiverId(receiverId, start, end, orderByComparator);
    }

    /**
    * Returns the first activity subscription in the ordered set where receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching activity subscription
    * @throws com.ext.portlet.Activity.NoSuchSubscriptionException if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.Activity.model.ActivitySubscription findByreceiverId_First(
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByreceiverId_First(receiverId, orderByComparator);
    }

    /**
    * Returns the last activity subscription in the ordered set where receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching activity subscription
    * @throws com.ext.portlet.Activity.NoSuchSubscriptionException if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.Activity.model.ActivitySubscription findByreceiverId_Last(
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByreceiverId_Last(receiverId, orderByComparator);
    }

    /**
    * Returns the activity subscriptions before and after the current activity subscription in the ordered set where receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the current activity subscription
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next activity subscription
    * @throws com.ext.portlet.Activity.NoSuchSubscriptionException if a activity subscription with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.Activity.model.ActivitySubscription[] findByreceiverId_PrevAndNext(
        java.lang.Long pk, java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByreceiverId_PrevAndNext(pk, receiverId,
            orderByComparator);
    }

    /**
    * Returns all the activity subscriptions where classNameId = &#63; and classPK = &#63; and receiverId = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param receiverId the receiver ID
    * @return the matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKReceiverId(classNameId, classPK,
            receiverId);
    }

    /**
    * Returns a range of all the activity subscriptions where classNameId = &#63; and classPK = &#63; and receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Long receiverId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKReceiverId(classNameId, classPK,
            receiverId, start, end);
    }

    /**
    * Returns an ordered range of all the activity subscriptions where classNameId = &#63; and classPK = &#63; and receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Long receiverId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKReceiverId(classNameId, classPK,
            receiverId, start, end, orderByComparator);
    }

    /**
    * Returns the first activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching activity subscription
    * @throws com.ext.portlet.Activity.NoSuchSubscriptionException if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKReceiverId_First(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKReceiverId_First(classNameId,
            classPK, receiverId, orderByComparator);
    }

    /**
    * Returns the last activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching activity subscription
    * @throws com.ext.portlet.Activity.NoSuchSubscriptionException if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKReceiverId_Last(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKReceiverId_Last(classNameId,
            classPK, receiverId, orderByComparator);
    }

    /**
    * Returns the activity subscriptions before and after the current activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the current activity subscription
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next activity subscription
    * @throws com.ext.portlet.Activity.NoSuchSubscriptionException if a activity subscription with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.Activity.model.ActivitySubscription[] findByClassNameIdClassPKReceiverId_PrevAndNext(
        java.lang.Long pk, java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKReceiverId_PrevAndNext(pk,
            classNameId, classPK, receiverId, orderByComparator);
    }

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
    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeReceiverId(classNameId,
            classPK, type, receiverId);
    }

    /**
    * Returns a range of all the activity subscriptions where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeReceiverId(classNameId,
            classPK, type, receiverId, start, end);
    }

    /**
    * Returns an ordered range of all the activity subscriptions where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeReceiverId(classNameId,
            classPK, type, receiverId, start, end, orderByComparator);
    }

    /**
    * Returns the first activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching activity subscription
    * @throws com.ext.portlet.Activity.NoSuchSubscriptionException if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKTypeReceiverId_First(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeReceiverId_First(classNameId,
            classPK, type, receiverId, orderByComparator);
    }

    /**
    * Returns the last activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching activity subscription
    * @throws com.ext.portlet.Activity.NoSuchSubscriptionException if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKTypeReceiverId_Last(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeReceiverId_Last(classNameId,
            classPK, type, receiverId, orderByComparator);
    }

    /**
    * Returns the activity subscriptions before and after the current activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the current activity subscription
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next activity subscription
    * @throws com.ext.portlet.Activity.NoSuchSubscriptionException if a activity subscription with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.Activity.model.ActivitySubscription[] findByClassNameIdClassPKTypeReceiverId_PrevAndNext(
        java.lang.Long pk, java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeReceiverId_PrevAndNext(pk,
            classNameId, classPK, type, receiverId, orderByComparator);
    }

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
    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraDataReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraDataReceiverId(classNameId,
            classPK, type, extraData, receiverId);
    }

    /**
    * Returns a range of all the activity subscriptions where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63; and receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraDataReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraDataReceiverId(classNameId,
            classPK, type, extraData, receiverId, start, end);
    }

    /**
    * Returns an ordered range of all the activity subscriptions where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63; and receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraDataReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraDataReceiverId(classNameId,
            classPK, type, extraData, receiverId, start, end, orderByComparator);
    }

    /**
    * Returns the first activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63; and receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching activity subscription
    * @throws com.ext.portlet.Activity.NoSuchSubscriptionException if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKTypeExtraDataReceiverId_First(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraDataReceiverId_First(classNameId,
            classPK, type, extraData, receiverId, orderByComparator);
    }

    /**
    * Returns the last activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63; and receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching activity subscription
    * @throws com.ext.portlet.Activity.NoSuchSubscriptionException if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKTypeExtraDataReceiverId_Last(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraDataReceiverId_Last(classNameId,
            classPK, type, extraData, receiverId, orderByComparator);
    }

    /**
    * Returns the activity subscriptions before and after the current activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63; and receiverId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the current activity subscription
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @param receiverId the receiver ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next activity subscription
    * @throws com.ext.portlet.Activity.NoSuchSubscriptionException if a activity subscription with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.Activity.model.ActivitySubscription[] findByClassNameIdClassPKTypeExtraDataReceiverId_PrevAndNext(
        java.lang.Long pk, java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraDataReceiverId_PrevAndNext(pk,
            classNameId, classPK, type, extraData, receiverId, orderByComparator);
    }

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
    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraData(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraData(classNameId, classPK,
            type, extraData);
    }

    /**
    * Returns a range of all the activity subscriptions where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraData(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraData(classNameId, classPK,
            type, extraData, start, end);
    }

    /**
    * Returns an ordered range of all the activity subscriptions where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findByClassNameIdClassPKTypeExtraData(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraData(classNameId, classPK,
            type, extraData, start, end, orderByComparator);
    }

    /**
    * Returns the first activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching activity subscription
    * @throws com.ext.portlet.Activity.NoSuchSubscriptionException if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKTypeExtraData_First(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraData_First(classNameId,
            classPK, type, extraData, orderByComparator);
    }

    /**
    * Returns the last activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching activity subscription
    * @throws com.ext.portlet.Activity.NoSuchSubscriptionException if a matching activity subscription could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.Activity.model.ActivitySubscription findByClassNameIdClassPKTypeExtraData_Last(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraData_Last(classNameId,
            classPK, type, extraData, orderByComparator);
    }

    /**
    * Returns the activity subscriptions before and after the current activity subscription in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the current activity subscription
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next activity subscription
    * @throws com.ext.portlet.Activity.NoSuchSubscriptionException if a activity subscription with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.Activity.model.ActivitySubscription[] findByClassNameIdClassPKTypeExtraData_PrevAndNext(
        java.lang.Long pk, java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.Activity.NoSuchSubscriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByClassNameIdClassPKTypeExtraData_PrevAndNext(pk,
            classNameId, classPK, type, extraData, orderByComparator);
    }

    /**
    * Returns all the activity subscriptions.
    *
    * @return the activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the activity subscriptions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of activity subscriptions
    * @param end the upper bound of the range of activity subscriptions (not inclusive)
    * @return the range of activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the activity subscriptions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of activity subscriptions
    * @param end the upper bound of the range of activity subscriptions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.Activity.model.ActivitySubscription> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the activity subscriptions where receiverId = &#63; from the database.
    *
    * @param receiverId the receiver ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByreceiverId(java.lang.Long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByreceiverId(receiverId);
    }

    /**
    * Removes all the activity subscriptions where classNameId = &#63; and classPK = &#63; and receiverId = &#63; from the database.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param receiverId the receiver ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByClassNameIdClassPKReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByClassNameIdClassPKReceiverId(classNameId, classPK,
            receiverId);
    }

    /**
    * Removes all the activity subscriptions where classNameId = &#63; and classPK = &#63; and type = &#63; and receiverId = &#63; from the database.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param receiverId the receiver ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByClassNameIdClassPKTypeReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByClassNameIdClassPKTypeReceiverId(classNameId, classPK,
            type, receiverId);
    }

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
    public static void removeByClassNameIdClassPKTypeExtraDataReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByClassNameIdClassPKTypeExtraDataReceiverId(classNameId,
            classPK, type, extraData, receiverId);
    }

    /**
    * Removes all the activity subscriptions where classNameId = &#63; and classPK = &#63; and type = &#63; and extraData = &#63; from the database.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param type the type
    * @param extraData the extra data
    * @throws SystemException if a system exception occurred
    */
    public static void removeByClassNameIdClassPKTypeExtraData(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByClassNameIdClassPKTypeExtraData(classNameId, classPK,
            type, extraData);
    }

    /**
    * Removes all the activity subscriptions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of activity subscriptions where receiverId = &#63;.
    *
    * @param receiverId the receiver ID
    * @return the number of matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public static int countByreceiverId(java.lang.Long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByreceiverId(receiverId);
    }

    /**
    * Returns the number of activity subscriptions where classNameId = &#63; and classPK = &#63; and receiverId = &#63;.
    *
    * @param classNameId the class name ID
    * @param classPK the class p k
    * @param receiverId the receiver ID
    * @return the number of matching activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public static int countByClassNameIdClassPKReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByClassNameIdClassPKReceiverId(classNameId, classPK,
            receiverId);
    }

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
    public static int countByClassNameIdClassPKTypeReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.Long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByClassNameIdClassPKTypeReceiverId(classNameId,
            classPK, type, receiverId);
    }

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
    public static int countByClassNameIdClassPKTypeExtraDataReceiverId(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long receiverId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByClassNameIdClassPKTypeExtraDataReceiverId(classNameId,
            classPK, type, extraData, receiverId);
    }

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
    public static int countByClassNameIdClassPKTypeExtraData(
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByClassNameIdClassPKTypeExtraData(classNameId,
            classPK, type, extraData);
    }

    /**
    * Returns the number of activity subscriptions.
    *
    * @return the number of activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ActivitySubscriptionPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ActivitySubscriptionPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.Activity.service.ClpSerializer.getServletContextName(),
                    ActivitySubscriptionPersistence.class.getName());

            ReferenceRegistry.registerReference(ActivitySubscriptionUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(ActivitySubscriptionPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(ActivitySubscriptionUtil.class,
            "_persistence");
    }
}
