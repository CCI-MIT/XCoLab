package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContestPhaseColumnLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseColumnLocalService
 * @generated
 */
public class ContestPhaseColumnLocalServiceWrapper
    implements ContestPhaseColumnLocalService,
        ServiceWrapper<ContestPhaseColumnLocalService> {
    private ContestPhaseColumnLocalService _contestPhaseColumnLocalService;

    public ContestPhaseColumnLocalServiceWrapper(
        ContestPhaseColumnLocalService contestPhaseColumnLocalService) {
        _contestPhaseColumnLocalService = contestPhaseColumnLocalService;
    }

    /**
    * Adds the contest phase column to the database. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseColumn the contest phase column
    * @return the contest phase column that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestPhaseColumn addContestPhaseColumn(
        com.ext.portlet.model.ContestPhaseColumn contestPhaseColumn)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseColumnLocalService.addContestPhaseColumn(contestPhaseColumn);
    }

    /**
    * Creates a new contest phase column with the primary key. Does not add the contest phase column to the database.
    *
    * @param id the primary key for the new contest phase column
    * @return the new contest phase column
    */
    @Override
    public com.ext.portlet.model.ContestPhaseColumn createContestPhaseColumn(
        long id) {
        return _contestPhaseColumnLocalService.createContestPhaseColumn(id);
    }

    /**
    * Deletes the contest phase column with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest phase column
    * @return the contest phase column that was removed
    * @throws PortalException if a contest phase column with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestPhaseColumn deleteContestPhaseColumn(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseColumnLocalService.deleteContestPhaseColumn(id);
    }

    /**
    * Deletes the contest phase column from the database. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseColumn the contest phase column
    * @return the contest phase column that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestPhaseColumn deleteContestPhaseColumn(
        com.ext.portlet.model.ContestPhaseColumn contestPhaseColumn)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseColumnLocalService.deleteContestPhaseColumn(contestPhaseColumn);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _contestPhaseColumnLocalService.dynamicQuery();
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
        return _contestPhaseColumnLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseColumnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _contestPhaseColumnLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseColumnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _contestPhaseColumnLocalService.dynamicQuery(dynamicQuery,
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
        return _contestPhaseColumnLocalService.dynamicQueryCount(dynamicQuery);
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
        return _contestPhaseColumnLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.ContestPhaseColumn fetchContestPhaseColumn(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseColumnLocalService.fetchContestPhaseColumn(id);
    }

    /**
    * Returns the contest phase column with the primary key.
    *
    * @param id the primary key of the contest phase column
    * @return the contest phase column
    * @throws PortalException if a contest phase column with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestPhaseColumn getContestPhaseColumn(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseColumnLocalService.getContestPhaseColumn(id);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseColumnLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the contest phase columns.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseColumnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest phase columns
    * @param end the upper bound of the range of contest phase columns (not inclusive)
    * @return the range of contest phase columns
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.ContestPhaseColumn> getContestPhaseColumns(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseColumnLocalService.getContestPhaseColumns(start, end);
    }

    /**
    * Returns the number of contest phase columns.
    *
    * @return the number of contest phase columns
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getContestPhaseColumnsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseColumnLocalService.getContestPhaseColumnsCount();
    }

    /**
    * Updates the contest phase column in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseColumn the contest phase column
    * @return the contest phase column that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestPhaseColumn updateContestPhaseColumn(
        com.ext.portlet.model.ContestPhaseColumn contestPhaseColumn)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseColumnLocalService.updateContestPhaseColumn(contestPhaseColumn);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _contestPhaseColumnLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contestPhaseColumnLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _contestPhaseColumnLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ContestPhaseColumn> getPhaseColumns(
        java.lang.Long contestPhasePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseColumnLocalService.getPhaseColumns(contestPhasePK);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ContestPhaseColumnLocalService getWrappedContestPhaseColumnLocalService() {
        return _contestPhaseColumnLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedContestPhaseColumnLocalService(
        ContestPhaseColumnLocalService contestPhaseColumnLocalService) {
        _contestPhaseColumnLocalService = contestPhaseColumnLocalService;
    }

    @Override
    public ContestPhaseColumnLocalService getWrappedService() {
        return _contestPhaseColumnLocalService;
    }

    @Override
    public void setWrappedService(
        ContestPhaseColumnLocalService contestPhaseColumnLocalService) {
        _contestPhaseColumnLocalService = contestPhaseColumnLocalService;
    }
}
