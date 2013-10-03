package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ActivitySubscriptionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ActivitySubscriptionLocalService
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
    public com.ext.portlet.model.ActivitySubscription createActivitySubscription(
        long pk) {
        return _activitySubscriptionLocalService.createActivitySubscription(pk);
    }

    /**
    * Deletes the activity subscription with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param pk the primary key of the activity subscription
    * @throws PortalException if a activity subscription with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteActivitySubscription(long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.deleteActivitySubscription(pk);
    }

    /**
    * Deletes the activity subscription from the database. Also notifies the appropriate model listeners.
    *
    * @param activitySubscription the activity subscription
    * @throws SystemException if a system exception occurred
    */
    public void deleteActivitySubscription(
        com.ext.portlet.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.deleteActivitySubscription(activitySubscription);
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.dynamicQueryCount(dynamicQuery);
    }

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
    public com.ext.portlet.model.ActivitySubscription getActivitySubscription(
        long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.getActivitySubscription(pk);
    }

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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of activity subscriptions
    * @param end the upper bound of the range of activity subscriptions (not inclusive)
    * @return the range of activity subscriptions
    * @throws SystemException if a system exception occurred
    */
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
    public com.ext.portlet.model.ActivitySubscription updateActivitySubscription(
        com.ext.portlet.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.updateActivitySubscription(activitySubscription);
    }

    /**
    * Updates the activity subscription in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param activitySubscription the activity subscription
    * @param merge whether to merge the activity subscription with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the activity subscription that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ActivitySubscription updateActivitySubscription(
        com.ext.portlet.model.ActivitySubscription activitySubscription,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.updateActivitySubscription(activitySubscription,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _activitySubscriptionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _activitySubscriptionLocalService.setBeanIdentifier(beanIdentifier);
    }

    public java.util.List<com.ext.portlet.model.ActivitySubscription> getActivitySubscriptions(
        java.lang.Class clasz, java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.getActivitySubscriptions(clasz,
            classPK, type, extraData);
    }

    public java.util.List<com.ext.portlet.model.ActivitySubscription> findByUser(
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.findByUser(userId);
    }

    public boolean isSubscribed(java.lang.Long userId,
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.isSubscribed(userId,
            classNameId, classPK, type, extraData);
    }

    public boolean isSubscribed(java.lang.Long userId, java.lang.Class clasz,
        java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.isSubscribed(userId, clasz,
            classPK, type, extraData);
    }

    public void deleteSubscription(java.lang.Long userId,
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.deleteSubscription(userId,
            classNameId, classPK, type, extraData);
    }

    public void deleteSubscription(java.lang.Long userId,
        java.lang.Long classNameId, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData, boolean automatic)
        throws com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.deleteSubscription(userId,
            classNameId, classPK, type, extraData, automatic);
    }

    public void deleteSubscription(java.lang.Long userId,
        java.lang.Class clasz, java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData, boolean automatic)
        throws com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.deleteSubscription(userId, clasz,
            classPK, type, extraData, automatic);
    }

    public void deleteSubscription(java.lang.Long userId,
        java.lang.Class clasz, java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData)
        throws com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.deleteSubscription(userId, clasz,
            classPK, type, extraData);
    }

    public void addSubscription(java.lang.Long classNameId,
        java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.addSubscription(classNameId, classPK,
            type, extraData, userId);
    }

    public void addSubscription(java.lang.Long classNameId,
        java.lang.Long classPK, java.lang.Integer type,
        java.lang.String extraData, java.lang.Long userId, boolean automatic)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.addSubscription(classNameId, classPK,
            type, extraData, userId, automatic);
    }

    public void addSubscription(java.lang.Class clasz, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.addSubscription(clasz, classPK, type,
            extraData, userId);
    }

    public void addSubscription(java.lang.Class clasz, java.lang.Long classPK,
        java.lang.Integer type, java.lang.String extraData,
        java.lang.Long userId, boolean automatic)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.addSubscription(clasz, classPK, type,
            extraData, userId, automatic);
    }

    public java.util.List<com.liferay.portlet.social.model.SocialActivity> getActivities(
        java.lang.Long userId, int start, int count)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.getActivities(userId, start,
            count);
    }

    public void store(
        com.ext.portlet.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.store(activitySubscription);
    }

    public java.lang.String getName(
        com.ext.portlet.model.ActivitySubscription activitySubscription) {
        return _activitySubscriptionLocalService.getName(activitySubscription);
    }

    public com.ext.portlet.Activity.SubscriptionType getSubscriptionType(
        com.ext.portlet.model.ActivitySubscription activitySubscription) {
        return _activitySubscriptionLocalService.getSubscriptionType(activitySubscription);
    }

    public void delete(
        com.ext.portlet.model.ActivitySubscription activitySubscription)
        throws com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.delete(activitySubscription);
    }

    public void sendEmailNotifications()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _activitySubscriptionLocalService.sendEmailNotifications();
    }

    public java.util.List<com.liferay.portal.model.User> getSubscribedUsers(
        java.lang.Class clasz, long classPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.getSubscribedUsers(clasz,
            classPK);
    }

    public java.util.List<com.liferay.portal.model.User> getSubscribedUsers(
        long classNameId, long classPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _activitySubscriptionLocalService.getSubscribedUsers(classNameId,
            classPK);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ActivitySubscriptionLocalService getWrappedActivitySubscriptionLocalService() {
        return _activitySubscriptionLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedActivitySubscriptionLocalService(
        ActivitySubscriptionLocalService activitySubscriptionLocalService) {
        _activitySubscriptionLocalService = activitySubscriptionLocalService;
    }

    public ActivitySubscriptionLocalService getWrappedService() {
        return _activitySubscriptionLocalService;
    }

    public void setWrappedService(
        ActivitySubscriptionLocalService activitySubscriptionLocalService) {
        _activitySubscriptionLocalService = activitySubscriptionLocalService;
    }
}
