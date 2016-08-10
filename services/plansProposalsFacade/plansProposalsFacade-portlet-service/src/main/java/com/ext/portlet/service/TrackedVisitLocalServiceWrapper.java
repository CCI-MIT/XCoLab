package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link TrackedVisitLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see TrackedVisitLocalService
 * @generated
 */
public class TrackedVisitLocalServiceWrapper implements TrackedVisitLocalService,
    ServiceWrapper<TrackedVisitLocalService> {
    private TrackedVisitLocalService _trackedVisitLocalService;

    public TrackedVisitLocalServiceWrapper(
        TrackedVisitLocalService trackedVisitLocalService) {
        _trackedVisitLocalService = trackedVisitLocalService;
    }

    /**
    * Adds the tracked visit to the database. Also notifies the appropriate model listeners.
    *
    * @param trackedVisit the tracked visit
    * @return the tracked visit that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.TrackedVisit addTrackedVisit(
        com.ext.portlet.model.TrackedVisit trackedVisit)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _trackedVisitLocalService.addTrackedVisit(trackedVisit);
    }

    /**
    * Creates a new tracked visit with the primary key. Does not add the tracked visit to the database.
    *
    * @param id the primary key for the new tracked visit
    * @return the new tracked visit
    */
    @Override
    public com.ext.portlet.model.TrackedVisit createTrackedVisit(long id) {
        return _trackedVisitLocalService.createTrackedVisit(id);
    }

    /**
    * Deletes the tracked visit with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the tracked visit
    * @return the tracked visit that was removed
    * @throws PortalException if a tracked visit with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.TrackedVisit deleteTrackedVisit(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _trackedVisitLocalService.deleteTrackedVisit(id);
    }

    /**
    * Deletes the tracked visit from the database. Also notifies the appropriate model listeners.
    *
    * @param trackedVisit the tracked visit
    * @return the tracked visit that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.TrackedVisit deleteTrackedVisit(
        com.ext.portlet.model.TrackedVisit trackedVisit)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _trackedVisitLocalService.deleteTrackedVisit(trackedVisit);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _trackedVisitLocalService.dynamicQuery();
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
        return _trackedVisitLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.TrackedVisitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _trackedVisitLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.TrackedVisitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _trackedVisitLocalService.dynamicQuery(dynamicQuery, start, end,
            orderByComparator);
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
        return _trackedVisitLocalService.dynamicQueryCount(dynamicQuery);
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
        return _trackedVisitLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.TrackedVisit fetchTrackedVisit(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _trackedVisitLocalService.fetchTrackedVisit(id);
    }

    /**
    * Returns the tracked visit with the primary key.
    *
    * @param id the primary key of the tracked visit
    * @return the tracked visit
    * @throws PortalException if a tracked visit with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.TrackedVisit getTrackedVisit(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _trackedVisitLocalService.getTrackedVisit(id);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _trackedVisitLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the tracked visits.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.TrackedVisitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of tracked visits
    * @param end the upper bound of the range of tracked visits (not inclusive)
    * @return the range of tracked visits
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.TrackedVisit> getTrackedVisits(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _trackedVisitLocalService.getTrackedVisits(start, end);
    }

    /**
    * Returns the number of tracked visits.
    *
    * @return the number of tracked visits
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getTrackedVisitsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _trackedVisitLocalService.getTrackedVisitsCount();
    }

    /**
    * Updates the tracked visit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param trackedVisit the tracked visit
    * @return the tracked visit that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.TrackedVisit updateTrackedVisit(
        com.ext.portlet.model.TrackedVisit trackedVisit)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _trackedVisitLocalService.updateTrackedVisit(trackedVisit);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _trackedVisitLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _trackedVisitLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _trackedVisitLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public TrackedVisitLocalService getWrappedTrackedVisitLocalService() {
        return _trackedVisitLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedTrackedVisitLocalService(
        TrackedVisitLocalService trackedVisitLocalService) {
        _trackedVisitLocalService = trackedVisitLocalService;
    }

    @Override
    public TrackedVisitLocalService getWrappedService() {
        return _trackedVisitLocalService;
    }

    @Override
    public void setWrappedService(
        TrackedVisitLocalService trackedVisitLocalService) {
        _trackedVisitLocalService = trackedVisitLocalService;
    }
}
