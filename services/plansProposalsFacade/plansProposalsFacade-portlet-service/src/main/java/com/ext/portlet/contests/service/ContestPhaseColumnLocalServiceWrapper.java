package com.ext.portlet.contests.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ContestPhaseColumnLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContestPhaseColumnLocalService
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
    public com.ext.portlet.contests.model.ContestPhaseColumn addContestPhaseColumn(
        com.ext.portlet.contests.model.ContestPhaseColumn contestPhaseColumn)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseColumnLocalService.addContestPhaseColumn(contestPhaseColumn);
    }

    /**
    * Creates a new contest phase column with the primary key. Does not add the contest phase column to the database.
    *
    * @param id the primary key for the new contest phase column
    * @return the new contest phase column
    */
    public com.ext.portlet.contests.model.ContestPhaseColumn createContestPhaseColumn(
        java.lang.Long id) {
        return _contestPhaseColumnLocalService.createContestPhaseColumn(id);
    }

    /**
    * Deletes the contest phase column with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest phase column
    * @throws PortalException if a contest phase column with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteContestPhaseColumn(java.lang.Long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _contestPhaseColumnLocalService.deleteContestPhaseColumn(id);
    }

    /**
    * Deletes the contest phase column from the database. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseColumn the contest phase column
    * @throws SystemException if a system exception occurred
    */
    public void deleteContestPhaseColumn(
        com.ext.portlet.contests.model.ContestPhaseColumn contestPhaseColumn)
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestPhaseColumnLocalService.deleteContestPhaseColumn(contestPhaseColumn);
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
        return _contestPhaseColumnLocalService.dynamicQuery(dynamicQuery);
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
        return _contestPhaseColumnLocalService.dynamicQuery(dynamicQuery,
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
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseColumnLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.contests.model.ContestPhaseColumn fetchContestPhaseColumn(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException {
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
    public com.ext.portlet.contests.model.ContestPhaseColumn getContestPhaseColumn(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseColumnLocalService.getContestPhaseColumn(id);
    }

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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contest phase columns
    * @param end the upper bound of the range of contest phase columns (not inclusive)
    * @return the range of contest phase columns
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.ContestPhaseColumn> getContestPhaseColumns(
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
    public com.ext.portlet.contests.model.ContestPhaseColumn updateContestPhaseColumn(
        com.ext.portlet.contests.model.ContestPhaseColumn contestPhaseColumn)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseColumnLocalService.updateContestPhaseColumn(contestPhaseColumn);
    }

    /**
    * Updates the contest phase column in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestPhaseColumn the contest phase column
    * @param merge whether to merge the contest phase column with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the contest phase column that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.ContestPhaseColumn updateContestPhaseColumn(
        com.ext.portlet.contests.model.ContestPhaseColumn contestPhaseColumn,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseColumnLocalService.updateContestPhaseColumn(contestPhaseColumn,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _contestPhaseColumnLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contestPhaseColumnLocalService.setBeanIdentifier(beanIdentifier);
    }

    public java.util.List<com.ext.portlet.contests.model.ContestPhaseColumn> getPhaseColumns(
        java.lang.Long contestPhasePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseColumnLocalService.getPhaseColumns(contestPhasePK);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ContestPhaseColumnLocalService getWrappedContestPhaseColumnLocalService() {
        return _contestPhaseColumnLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedContestPhaseColumnLocalService(
        ContestPhaseColumnLocalService contestPhaseColumnLocalService) {
        _contestPhaseColumnLocalService = contestPhaseColumnLocalService;
    }

    public ContestPhaseColumnLocalService getWrappedService() {
        return _contestPhaseColumnLocalService;
    }

    public void setWrappedService(
        ContestPhaseColumnLocalService contestPhaseColumnLocalService) {
        _contestPhaseColumnLocalService = contestPhaseColumnLocalService;
    }
}
