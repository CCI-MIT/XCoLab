package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ActivitySubscriptionLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ActivitySubscriptionLocalService
 * @generated
 */
public class ActivitySubscriptionLocalServiceWrapper
    implements ActivitySubscriptionLocalService,
        ServiceWrapper<ActivitySubscriptionLocalService> {
    private ActivitySubscriptionLocalService _activitySubscriptionLocalService;

    public ActivitySubscriptionLocalServiceWrapper(
        ActivitySubscriptionLocalService activitySubscriptionLocalService) {
        _activitySubscriptionLocalService = activitySubscriptionLocalService;
    }

    /**
    * Adds the activity subscription to the database. Also notifies the appropriate model listeners.
    *
    * @param activitySubscription the activity subscription
    * @return the activity subscription that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ActivitySubscription addActivitySubscription(
        com.ext.portlet.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.addActivitySubscription(activitySubscription);
    }

    /**
    * Creates a new activity subscription with the primary key. Does not add the activity subscription to the database.
    *
    * @param pk the primary key for the new activity subscription
    * @return the new activity subscription
    */
    @Override
    public com.ext.portlet.model.ActivitySubscription createActivitySubscription(
        long pk) {
        return _activitySubscriptionLocalService.createActivitySubscription(pk);
    }

    /**
    * Deletes the activity subscription with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param pk the primary key of the activity subscription
    * @return the activity subscription that was removed
    * @throws PortalException if a activity subscription with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ActivitySubscription deleteActivitySubscription(
        long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.deleteActivitySubscription(pk);
    }

    /**
    * Deletes the activity subscription from the database. Also notifies the appropriate model listeners.
    *
    * @param activitySubscription the activity subscription
    * @return the activity subscription that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ActivitySubscription deleteActivitySubscription(
        com.ext.portlet.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.deleteActivitySubscription(activitySubscription);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _activitySubscriptionLocalService.dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.dynamicQuery(dynamicQuery);
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
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.dynamicQuery(dynamicQuery,
            start, end);
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
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.dynamicQuery(dynamicQuery,
            start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.ActivitySubscription fetchActivitySubscription(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.fetchActivitySubscription(pk);
    }

    /**
    * Returns the activity subscription with the primary key.
    *
    * @param pk the primary key of the activity subscription
    * @return the activity subscription
    * @throws PortalException if a activity subscription with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ActivitySubscription getActivitySubscription(
        long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.getActivitySubscription(pk);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.getPersistedModel(primaryKeyObj);
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
    @Override
    public java.util.List<com.ext.portlet.model.ActivitySubscription> getActivitySubscriptions(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.getActivitySubscriptions(start,
            end);
    }

    /**
    * Returns the number of activity subscriptions.
    *
    * @return the number of activity subscriptions
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getActivitySubscriptionsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.getActivitySubscriptionsCount();
    }

    /**
    * Updates the activity subscription in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param activitySubscription the activity subscription
    * @return the activity subscription that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ActivitySubscription updateActivitySubscription(
        com.ext.portlet.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.updateActivitySubscription(activitySubscription);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _activitySubscriptionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _activitySubscriptionLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _activitySubscriptionLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ActivitySubscription> getActivitySubscriptions(
        java.lang.Class clasz, java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.getActivitySubscriptions(clasz,
            classPK, type, extraData);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ActivitySubscription> findByUser(
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.findByUser(userId);
    }

    @Override
    public boolean isSubscribed(java.lang.Long userId,
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.isSubscribed(userId,
            classNameId, classPK, type, extraData);
    }

    @Override
    public boolean isSubscribed(java.lang.Long userId, java.lang.Class clasz,
        java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.isSubscribed(userId, clasz,
            classPK, type, extraData);
    }

    @Override
    public void deleteSubscription(java.lang.Long userId,
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.deleteSubscription(userId,
            classNameId, classPK, type, extraData);
    }

    @Override
    public void deleteSubscription(java.lang.Long userId,
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData, boolean automatic)
        throws com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.deleteSubscription(userId,
            classNameId, classPK, type, extraData, automatic);
    }

    @Override
    public void deleteSubscription(java.lang.Long userId,
        java.lang.Class clasz, java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData, boolean automatic)
        throws com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.deleteSubscription(userId, clasz,
            classPK, type, extraData, automatic);
    }

    @Override
    public void deleteSubscription(java.lang.Long userId,
        java.lang.Class clasz, java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.deleteSubscription(userId, clasz,
            classPK, type, extraData);
    }

    @Override
    public void addSubscription(java.lang.Long classNameId,
        java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.addSubscription(classNameId, classPK,
            type, extraData, userId);
    }

    @Override
    public void addSubscription(java.lang.Long classNameId,
        java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData, java.lang.Long userId, boolean automatic)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.addSubscription(classNameId, classPK,
            type, extraData, userId, automatic);
    }

    @Override
    public void addSubscription(java.lang.Class clasz, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.addSubscription(clasz, classPK, type,
            extraData, userId);
    }

    @Override
    public void addSubscription(java.lang.Class clasz, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long userId, boolean automatic)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.addSubscription(clasz, classPK, type,
            extraData, userId, automatic);
    }

    @Override
    public java.util.List<com.liferay.portlet.social.model.SocialActivity> getActivities(
        java.lang.Long userId, int start, int count)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.getActivities(userId, start,
            count);
    }

    @Override
    public void store(
        com.ext.portlet.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.store(activitySubscription);
    }

    @Override
    public java.lang.String getName(
        com.ext.portlet.model.ActivitySubscription activitySubscription) {
        return _activitySubscriptionLocalService.getName(activitySubscription);
    }

    @Override
    public com.ext.portlet.Activity.SubscriptionType getSubscriptionType(
        com.ext.portlet.model.ActivitySubscription activitySubscription) {
        return _activitySubscriptionLocalService.getSubscriptionType(activitySubscription);
    }

    @Override
    public void delete(
        com.ext.portlet.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.delete(activitySubscription);
    }

    @Override
    public void sendEmailNotifications(
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.sendEmailNotifications(serviceContext);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getSubscribedUsers(
        java.lang.Class clasz, long classPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.getSubscribedUsers(clasz,
            classPK);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getSubscribedUsers(
        long classNameId, long classPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.getSubscribedUsers(classNameId,
            classPK);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ActivitySubscriptionLocalService getWrappedActivitySubscriptionLocalService() {
        return _activitySubscriptionLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedActivitySubscriptionLocalService(
        ActivitySubscriptionLocalService activitySubscriptionLocalService) {
        _activitySubscriptionLocalService = activitySubscriptionLocalService;
    }

    @Override
    public ActivitySubscriptionLocalService getWrappedService() {
        return _activitySubscriptionLocalService;
    }

    @Override
    public void setWrappedService(
        ActivitySubscriptionLocalService activitySubscriptionLocalService) {
        _activitySubscriptionLocalService = activitySubscriptionLocalService;
    }
}
