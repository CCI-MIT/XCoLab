package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for ActivitySubscription. This utility wraps
 * {@link com.ext.portlet.service.impl.ActivitySubscriptionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ActivitySubscriptionLocalService
 * @see com.ext.portlet.service.base.ActivitySubscriptionLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.ActivitySubscriptionLocalServiceImpl
 * @generated
 */
public class ActivitySubscriptionLocalServiceUtil {
    private static ActivitySubscriptionLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ActivitySubscriptionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the activity subscription to the database. Also notifies the appropriate model listeners.
    *
    * @param activitySubscription the activity subscription
    * @return the activity subscription that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ActivitySubscription addActivitySubscription(
        com.ext.portlet.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addActivitySubscription(activitySubscription);
    }

    /**
    * Creates a new activity subscription with the primary key. Does not add the activity subscription to the database.
    *
    * @param pk the primary key for the new activity subscription
    * @return the new activity subscription
    */
    public static com.ext.portlet.model.ActivitySubscription createActivitySubscription(
        long pk) {
        return getService().createActivitySubscription(pk);
    }

    /**
    * Deletes the activity subscription with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param pk the primary key of the activity subscription
    * @return the activity subscription that was removed
    * @throws PortalException if a activity subscription with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ActivitySubscription deleteActivitySubscription(
        long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteActivitySubscription(pk);
    }

    /**
    * Deletes the activity subscription from the database. Also notifies the appropriate model listeners.
    *
    * @param activitySubscription the activity subscription
    * @return the activity subscription that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ActivitySubscription deleteActivitySubscription(
        com.ext.portlet.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteActivitySubscription(activitySubscription);
    }

    public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return getService().dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ActivitySubscriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ActivitySubscriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery, projection);
    }

    public static com.ext.portlet.model.ActivitySubscription fetchActivitySubscription(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchActivitySubscription(pk);
    }

    /**
    * Returns the activity subscription with the primary key.
    *
    * @param pk the primary key of the activity subscription
    * @return the activity subscription
    * @throws PortalException if a activity subscription with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ActivitySubscription getActivitySubscription(
        long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getActivitySubscription(pk);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

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
    public static java.util.List<com.ext.portlet.model.ActivitySubscription> getActivitySubscriptions(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getActivitySubscriptions(start, end);
    }

    /**
    * Returns the number of activity subscriptions.
    *
    * @return the number of activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    public static int getActivitySubscriptionsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getActivitySubscriptionsCount();
    }

    /**
    * Updates the activity subscription in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param activitySubscription the activity subscription
    * @return the activity subscription that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ActivitySubscription updateActivitySubscription(
        com.ext.portlet.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateActivitySubscription(activitySubscription);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static java.util.List<com.ext.portlet.model.ActivitySubscription> getActivitySubscriptions(
        java.lang.Class clasz, java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getActivitySubscriptions(clasz, classPK, type, extraData);
    }

    public static java.util.List<com.ext.portlet.model.ActivitySubscription> findByUser(
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByUser(userId);
    }

    public static boolean isSubscribed(java.lang.Long userId,
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .isSubscribed(userId, classNameId, classPK, type, extraData);
    }

    public static boolean isSubscribed(java.lang.Long userId,
        java.lang.Class clasz, java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().isSubscribed(userId, clasz, classPK, type, extraData);
    }

    public static void deleteSubscription(java.lang.Long userId,
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService()
            .deleteSubscription(userId, classNameId, classPK, type, extraData);
    }

    public static void deleteSubscription(java.lang.Long userId,
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData, boolean automatic)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService()
            .deleteSubscription(userId, classNameId, classPK, type, extraData,
            automatic);
    }

    public static void deleteSubscription(java.lang.Long userId,
        java.lang.Class clasz, java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData, boolean automatic)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService()
            .deleteSubscription(userId, clasz, classPK, type, extraData,
            automatic);
    }

    public static void deleteSubscription(java.lang.Long userId,
        java.lang.Class clasz, java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteSubscription(userId, clasz, classPK, type, extraData);
    }

    public static void addSubscription(java.lang.Long classNameId,
        java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService()
            .addSubscription(classNameId, classPK, type, extraData, userId);
    }

    public static void addSubscription(java.lang.Long classNameId,
        java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData, java.lang.Long userId, boolean automatic)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService()
            .addSubscription(classNameId, classPK, type, extraData, userId,
            automatic);
    }

    public static void addSubscription(java.lang.Class clasz,
        java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().addSubscription(clasz, classPK, type, extraData, userId);
    }

    public static void addSubscription(java.lang.Class clasz,
        java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData, java.lang.Long userId, boolean automatic)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService()
            .addSubscription(clasz, classPK, type, extraData, userId, automatic);
    }

    public static java.util.List<com.liferay.portlet.social.model.SocialActivity> getActivities(
        java.lang.Long userId, int start, int count)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getActivities(userId, start, count);
    }

    public static void store(
        com.ext.portlet.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().store(activitySubscription);
    }

    public static java.lang.String getName(
        com.ext.portlet.model.ActivitySubscription activitySubscription) {
        return getService().getName(activitySubscription);
    }

    public static com.ext.portlet.Activity.SubscriptionType getSubscriptionType(
        com.ext.portlet.model.ActivitySubscription activitySubscription) {
        return getService().getSubscriptionType(activitySubscription);
    }

    public static void delete(
        com.ext.portlet.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().delete(activitySubscription);
    }

    public static void sendEmailNotifications()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().sendEmailNotifications();
    }

    public static java.util.List<com.liferay.portal.model.User> getSubscribedUsers(
        java.lang.Class clasz, long classPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getSubscribedUsers(clasz, classPK);
    }

    public static java.util.List<com.liferay.portal.model.User> getSubscribedUsers(
        long classNameId, long classPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getSubscribedUsers(classNameId, classPK);
    }

    public static void clearService() {
        _service = null;
    }

    public static ActivitySubscriptionLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ActivitySubscriptionLocalService.class.getName());

            if (invokableLocalService instanceof ActivitySubscriptionLocalService) {
                _service = (ActivitySubscriptionLocalService) invokableLocalService;
            } else {
                _service = new ActivitySubscriptionLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(ActivitySubscriptionLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(ActivitySubscriptionLocalService service) {
    }
}
