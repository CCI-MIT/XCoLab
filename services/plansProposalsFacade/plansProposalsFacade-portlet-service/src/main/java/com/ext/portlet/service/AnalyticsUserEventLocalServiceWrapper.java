package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link AnalyticsUserEventLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AnalyticsUserEventLocalService
 * @generated
 */
public class AnalyticsUserEventLocalServiceWrapper
    implements AnalyticsUserEventLocalService,
        ServiceWrapper<AnalyticsUserEventLocalService> {
    private AnalyticsUserEventLocalService _analyticsUserEventLocalService;

    public AnalyticsUserEventLocalServiceWrapper(
        AnalyticsUserEventLocalService analyticsUserEventLocalService) {
        _analyticsUserEventLocalService = analyticsUserEventLocalService;
    }

    /**
    * Adds the analytics user event to the database. Also notifies the appropriate model listeners.
    *
    * @param analyticsUserEvent the analytics user event
    * @return the analytics user event that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.AnalyticsUserEvent addAnalyticsUserEvent(
        com.ext.portlet.model.AnalyticsUserEvent analyticsUserEvent)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _analyticsUserEventLocalService.addAnalyticsUserEvent(analyticsUserEvent);
    }

    /**
    * Creates a new analytics user event with the primary key. Does not add the analytics user event to the database.
    *
    * @param analyticsUserEventPK the primary key for the new analytics user event
    * @return the new analytics user event
    */
    public com.ext.portlet.model.AnalyticsUserEvent createAnalyticsUserEvent(
        com.ext.portlet.service.persistence.AnalyticsUserEventPK analyticsUserEventPK) {
        return _analyticsUserEventLocalService.createAnalyticsUserEvent(analyticsUserEventPK);
    }

    /**
    * Deletes the analytics user event with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param analyticsUserEventPK the primary key of the analytics user event
    * @throws PortalException if a analytics user event with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteAnalyticsUserEvent(
        com.ext.portlet.service.persistence.AnalyticsUserEventPK analyticsUserEventPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _analyticsUserEventLocalService.deleteAnalyticsUserEvent(analyticsUserEventPK);
    }

    /**
    * Deletes the analytics user event from the database. Also notifies the appropriate model listeners.
    *
    * @param analyticsUserEvent the analytics user event
    * @throws SystemException if a system exception occurred
    */
    public void deleteAnalyticsUserEvent(
        com.ext.portlet.model.AnalyticsUserEvent analyticsUserEvent)
        throws com.liferay.portal.kernel.exception.SystemException {
        _analyticsUserEventLocalService.deleteAnalyticsUserEvent(analyticsUserEvent);
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
        return _analyticsUserEventLocalService.dynamicQuery(dynamicQuery);
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
        return _analyticsUserEventLocalService.dynamicQuery(dynamicQuery,
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
        return _analyticsUserEventLocalService.dynamicQuery(dynamicQuery,
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
        return _analyticsUserEventLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.model.AnalyticsUserEvent fetchAnalyticsUserEvent(
        com.ext.portlet.service.persistence.AnalyticsUserEventPK analyticsUserEventPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _analyticsUserEventLocalService.fetchAnalyticsUserEvent(analyticsUserEventPK);
    }

    /**
    * Returns the analytics user event with the primary key.
    *
    * @param analyticsUserEventPK the primary key of the analytics user event
    * @return the analytics user event
    * @throws PortalException if a analytics user event with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.AnalyticsUserEvent getAnalyticsUserEvent(
        com.ext.portlet.service.persistence.AnalyticsUserEventPK analyticsUserEventPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _analyticsUserEventLocalService.getAnalyticsUserEvent(analyticsUserEventPK);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _analyticsUserEventLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the analytics user events.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of analytics user events
    * @param end the upper bound of the range of analytics user events (not inclusive)
    * @return the range of analytics user events
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.AnalyticsUserEvent> getAnalyticsUserEvents(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _analyticsUserEventLocalService.getAnalyticsUserEvents(start, end);
    }

    /**
    * Returns the number of analytics user events.
    *
    * @return the number of analytics user events
    * @throws SystemException if a system exception occurred
    */
    public int getAnalyticsUserEventsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _analyticsUserEventLocalService.getAnalyticsUserEventsCount();
    }

    /**
    * Updates the analytics user event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param analyticsUserEvent the analytics user event
    * @return the analytics user event that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.AnalyticsUserEvent updateAnalyticsUserEvent(
        com.ext.portlet.model.AnalyticsUserEvent analyticsUserEvent)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _analyticsUserEventLocalService.updateAnalyticsUserEvent(analyticsUserEvent);
    }

    /**
    * Updates the analytics user event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param analyticsUserEvent the analytics user event
    * @param merge whether to merge the analytics user event with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the analytics user event that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.AnalyticsUserEvent updateAnalyticsUserEvent(
        com.ext.portlet.model.AnalyticsUserEvent analyticsUserEvent,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _analyticsUserEventLocalService.updateAnalyticsUserEvent(analyticsUserEvent,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _analyticsUserEventLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _analyticsUserEventLocalService.setBeanIdentifier(beanIdentifier);
    }

    public boolean eventExists(long userId, java.lang.String idString) {
        return _analyticsUserEventLocalService.eventExists(userId, idString);
    }

    public com.ext.portlet.model.AnalyticsUserEvent createEvent(long userId,
        java.lang.String idString, java.lang.String category,
        java.lang.String action, java.lang.String label, int value)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _analyticsUserEventLocalService.createEvent(userId, idString,
            category, action, label, value);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public AnalyticsUserEventLocalService getWrappedAnalyticsUserEventLocalService() {
        return _analyticsUserEventLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedAnalyticsUserEventLocalService(
        AnalyticsUserEventLocalService analyticsUserEventLocalService) {
        _analyticsUserEventLocalService = analyticsUserEventLocalService;
    }

    public AnalyticsUserEventLocalService getWrappedService() {
        return _analyticsUserEventLocalService;
    }

    public void setWrappedService(
        AnalyticsUserEventLocalService analyticsUserEventLocalService) {
        _analyticsUserEventLocalService = analyticsUserEventLocalService;
    }
}
