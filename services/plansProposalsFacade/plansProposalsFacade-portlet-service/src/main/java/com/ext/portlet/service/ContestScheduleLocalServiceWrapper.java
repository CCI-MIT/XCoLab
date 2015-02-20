package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContestScheduleLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContestScheduleLocalService
 * @generated
 */
public class ContestScheduleLocalServiceWrapper
    implements ContestScheduleLocalService,
        ServiceWrapper<ContestScheduleLocalService> {
    private ContestScheduleLocalService _contestScheduleLocalService;

    public ContestScheduleLocalServiceWrapper(
        ContestScheduleLocalService contestScheduleLocalService) {
        _contestScheduleLocalService = contestScheduleLocalService;
    }

    /**
    * Adds the contest schedule to the database. Also notifies the appropriate model listeners.
    *
    * @param contestSchedule the contest schedule
    * @return the contest schedule that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestSchedule addContestSchedule(
        com.ext.portlet.model.ContestSchedule contestSchedule)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestScheduleLocalService.addContestSchedule(contestSchedule);
    }

    /**
    * Creates a new contest schedule with the primary key. Does not add the contest schedule to the database.
    *
    * @param id the primary key for the new contest schedule
    * @return the new contest schedule
    */
    @Override
    public com.ext.portlet.model.ContestSchedule createContestSchedule(long id) {
        return _contestScheduleLocalService.createContestSchedule(id);
    }

    /**
    * Deletes the contest schedule with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest schedule
    * @return the contest schedule that was removed
    * @throws PortalException if a contest schedule with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestSchedule deleteContestSchedule(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestScheduleLocalService.deleteContestSchedule(id);
    }

    /**
    * Deletes the contest schedule from the database. Also notifies the appropriate model listeners.
    *
    * @param contestSchedule the contest schedule
    * @return the contest schedule that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestSchedule deleteContestSchedule(
        com.ext.portlet.model.ContestSchedule contestSchedule)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestScheduleLocalService.deleteContestSchedule(contestSchedule);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _contestScheduleLocalService.dynamicQuery();
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
        return _contestScheduleLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _contestScheduleLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _contestScheduleLocalService.dynamicQuery(dynamicQuery, start,
            end, orderByComparator);
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
        return _contestScheduleLocalService.dynamicQueryCount(dynamicQuery);
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
        return _contestScheduleLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.ContestSchedule fetchContestSchedule(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestScheduleLocalService.fetchContestSchedule(id);
    }

    /**
    * Returns the contest schedule with the primary key.
    *
    * @param id the primary key of the contest schedule
    * @return the contest schedule
    * @throws PortalException if a contest schedule with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestSchedule getContestSchedule(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestScheduleLocalService.getContestSchedule(id);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestScheduleLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the contest schedules.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest schedules
    * @param end the upper bound of the range of contest schedules (not inclusive)
    * @return the range of contest schedules
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.ContestSchedule> getContestSchedules(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestScheduleLocalService.getContestSchedules(start, end);
    }

    /**
    * Returns the number of contest schedules.
    *
    * @return the number of contest schedules
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getContestSchedulesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestScheduleLocalService.getContestSchedulesCount();
    }

    /**
    * Updates the contest schedule in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestSchedule the contest schedule
    * @return the contest schedule that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestSchedule updateContestSchedule(
        com.ext.portlet.model.ContestSchedule contestSchedule)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestScheduleLocalService.updateContestSchedule(contestSchedule);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _contestScheduleLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contestScheduleLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _contestScheduleLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ContestScheduleLocalService getWrappedContestScheduleLocalService() {
        return _contestScheduleLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedContestScheduleLocalService(
        ContestScheduleLocalService contestScheduleLocalService) {
        _contestScheduleLocalService = contestScheduleLocalService;
    }

    @Override
    public ContestScheduleLocalService getWrappedService() {
        return _contestScheduleLocalService;
    }

    @Override
    public void setWrappedService(
        ContestScheduleLocalService contestScheduleLocalService) {
        _contestScheduleLocalService = contestScheduleLocalService;
    }
}
