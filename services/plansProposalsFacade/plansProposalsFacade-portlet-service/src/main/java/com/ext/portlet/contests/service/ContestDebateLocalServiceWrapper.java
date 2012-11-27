package com.ext.portlet.contests.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ContestDebateLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ContestDebateLocalService
 * @generated
 */
public class ContestDebateLocalServiceWrapper
    implements ContestDebateLocalService,
        ServiceWrapper<ContestDebateLocalService> {
    private ContestDebateLocalService _contestDebateLocalService;

    public ContestDebateLocalServiceWrapper(
        ContestDebateLocalService contestDebateLocalService) {
        _contestDebateLocalService = contestDebateLocalService;
    }

    /**
    * Adds the contest debate to the database. Also notifies the appropriate model listeners.
    *
    * @param contestDebate the contest debate
    * @return the contest debate that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.ContestDebate addContestDebate(
        com.ext.portlet.contests.model.ContestDebate contestDebate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestDebateLocalService.addContestDebate(contestDebate);
    }

    /**
    * Creates a new contest debate with the primary key. Does not add the contest debate to the database.
    *
    * @param id the primary key for the new contest debate
    * @return the new contest debate
    */
    public com.ext.portlet.contests.model.ContestDebate createContestDebate(
        java.lang.Long id) {
        return _contestDebateLocalService.createContestDebate(id);
    }

    /**
    * Deletes the contest debate with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest debate
    * @throws PortalException if a contest debate with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteContestDebate(java.lang.Long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _contestDebateLocalService.deleteContestDebate(id);
    }

    /**
    * Deletes the contest debate from the database. Also notifies the appropriate model listeners.
    *
    * @param contestDebate the contest debate
    * @throws SystemException if a system exception occurred
    */
    public void deleteContestDebate(
        com.ext.portlet.contests.model.ContestDebate contestDebate)
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestDebateLocalService.deleteContestDebate(contestDebate);
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
        return _contestDebateLocalService.dynamicQuery(dynamicQuery);
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
        return _contestDebateLocalService.dynamicQuery(dynamicQuery, start, end);
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
        return _contestDebateLocalService.dynamicQuery(dynamicQuery, start,
            end, orderByComparator);
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
        return _contestDebateLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.contests.model.ContestDebate fetchContestDebate(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestDebateLocalService.fetchContestDebate(id);
    }

    /**
    * Returns the contest debate with the primary key.
    *
    * @param id the primary key of the contest debate
    * @return the contest debate
    * @throws PortalException if a contest debate with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.ContestDebate getContestDebate(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestDebateLocalService.getContestDebate(id);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestDebateLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the contest debates.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contest debates
    * @param end the upper bound of the range of contest debates (not inclusive)
    * @return the range of contest debates
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.ContestDebate> getContestDebates(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestDebateLocalService.getContestDebates(start, end);
    }

    /**
    * Returns the number of contest debates.
    *
    * @return the number of contest debates
    * @throws SystemException if a system exception occurred
    */
    public int getContestDebatesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestDebateLocalService.getContestDebatesCount();
    }

    /**
    * Updates the contest debate in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestDebate the contest debate
    * @return the contest debate that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.ContestDebate updateContestDebate(
        com.ext.portlet.contests.model.ContestDebate contestDebate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestDebateLocalService.updateContestDebate(contestDebate);
    }

    /**
    * Updates the contest debate in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestDebate the contest debate
    * @param merge whether to merge the contest debate with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the contest debate that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.ContestDebate updateContestDebate(
        com.ext.portlet.contests.model.ContestDebate contestDebate,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestDebateLocalService.updateContestDebate(contestDebate,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _contestDebateLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contestDebateLocalService.setBeanIdentifier(beanIdentifier);
    }

    public com.ext.portlet.contests.model.ContestDebate createContestDebate(
        java.lang.Long debateId, java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestDebateLocalService.createContestDebate(debateId,
            contestId);
    }

    public java.util.List<com.ext.portlet.contests.model.ContestDebate> getContestDebates(
        java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestDebateLocalService.getContestDebates(contestId);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ContestDebateLocalService getWrappedContestDebateLocalService() {
        return _contestDebateLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedContestDebateLocalService(
        ContestDebateLocalService contestDebateLocalService) {
        _contestDebateLocalService = contestDebateLocalService;
    }

    public ContestDebateLocalService getWrappedService() {
        return _contestDebateLocalService;
    }

    public void setWrappedService(
        ContestDebateLocalService contestDebateLocalService) {
        _contestDebateLocalService = contestDebateLocalService;
    }
}
