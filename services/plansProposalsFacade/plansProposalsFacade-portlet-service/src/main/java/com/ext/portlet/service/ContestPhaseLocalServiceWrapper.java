package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContestPhaseLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseLocalService
 * @generated
 */
public class ContestPhaseLocalServiceWrapper implements ContestPhaseLocalService,
    ServiceWrapper<ContestPhaseLocalService> {
    private ContestPhaseLocalService _contestPhaseLocalService;

    public ContestPhaseLocalServiceWrapper(
        ContestPhaseLocalService contestPhaseLocalService) {
        _contestPhaseLocalService = contestPhaseLocalService;
    }

    /**
    * Adds the contest phase to the database. Also notifies the appropriate model listeners.
    *
    * @param contestPhase the contest phase
    * @return the contest phase that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestPhase addContestPhase(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseLocalService.addContestPhase(contestPhase);
    }

    /**
    * Creates a new contest phase with the primary key. Does not add the contest phase to the database.
    *
    * @param ContestPhasePK the primary key for the new contest phase
    * @return the new contest phase
    */
    @Override
    public com.ext.portlet.model.ContestPhase createContestPhase(
        long ContestPhasePK) {
        return _contestPhaseLocalService.createContestPhase(ContestPhasePK);
    }

    /**
    * Deletes the contest phase with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ContestPhasePK the primary key of the contest phase
    * @return the contest phase that was removed
    * @throws PortalException if a contest phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestPhase deleteContestPhase(
        long ContestPhasePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseLocalService.deleteContestPhase(ContestPhasePK);
    }

    /**
    * Deletes the contest phase from the database. Also notifies the appropriate model listeners.
    *
    * @param contestPhase the contest phase
    * @return the contest phase that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestPhase deleteContestPhase(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseLocalService.deleteContestPhase(contestPhase);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _contestPhaseLocalService.dynamicQuery();
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
        return _contestPhaseLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _contestPhaseLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _contestPhaseLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _contestPhaseLocalService.dynamicQueryCount(dynamicQuery);
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
        return _contestPhaseLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.ContestPhase fetchContestPhase(
        long ContestPhasePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseLocalService.fetchContestPhase(ContestPhasePK);
    }

    /**
    * Returns the contest phase with the primary key.
    *
    * @param ContestPhasePK the primary key of the contest phase
    * @return the contest phase
    * @throws PortalException if a contest phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestPhase getContestPhase(
        long ContestPhasePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseLocalService.getContestPhase(ContestPhasePK);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the contest phases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @return the range of contest phases
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.ContestPhase> getContestPhases(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseLocalService.getContestPhases(start, end);
    }

    /**
    * Returns the number of contest phases.
    *
    * @return the number of contest phases
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getContestPhasesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseLocalService.getContestPhasesCount();
    }

    /**
    * Updates the contest phase in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestPhase the contest phase
    * @return the contest phase that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ContestPhase updateContestPhase(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseLocalService.updateContestPhase(contestPhase);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _contestPhaseLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contestPhaseLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _contestPhaseLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
    * This can be used by unit tests to set a different clock than the standard one
    */
    @Override
    public void overrideClock(org.xcolab.utils.Clock clock) {
        _contestPhaseLocalService.overrideClock(clock);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanItem> getPlans(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseLocalService.getPlans(contestPhase);
    }

    @Override
    public com.ext.portlet.contests.ContestStatus getContestStatus(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseLocalService.getContestStatus(contestPhase);
    }

    @Override
    public java.lang.String getContestStatusStr(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseLocalService.getContestStatusStr(contestPhase);
    }

    @Override
    public java.util.List<java.lang.String> getPhaseColumns(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseLocalService.getPhaseColumns(contestPhase);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ContestPhaseColumn> getPhaseColumnsRaw(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseLocalService.getPhaseColumnsRaw(contestPhase);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ContestPhase> getPreviousPhases(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseLocalService.getPreviousPhases(contestPhase);
    }

    @Override
    public com.ext.portlet.model.ContestPhase getNextContestPhase(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseLocalService.getNextContestPhase(contestPhase);
    }

    @Override
    public boolean getPhaseActive(
        com.ext.portlet.model.ContestPhase contestPhase) {
        return _contestPhaseLocalService.getPhaseActive(contestPhase);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ContestPhase> getPhasesForContest(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseLocalService.getPhasesForContest(contest);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ContestPhase> getPhasesForContest(
        long contestPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseLocalService.getPhasesForContest(contestPK);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ContestPhase> getPhasesForContestSchedule(
        long contestScheduleId, long contestPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseLocalService.getPhasesForContestSchedule(contestScheduleId,
            contestPK);
    }

    @Override
    public com.ext.portlet.model.ContestPhase getActivePhaseForContest(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseLocalService.getActivePhaseForContest(contest);
    }

    /**
    * from ContestPhaseImpl *
    */
    @Override
    public com.ext.portlet.model.Contest getContest(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseLocalService.getContest(contestPhase);
    }

    @Override
    public java.lang.String getName(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseLocalService.getName(contestPhase);
    }

    @Override
    public void promoteProposal(long proposalId, long nextPhaseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _contestPhaseLocalService.promoteProposal(proposalId, nextPhaseId);
    }

    @Override
    public void promoteProposal(long proposalId, long nextPhaseId,
        long currentPhaseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _contestPhaseLocalService.promoteProposal(proposalId, nextPhaseId,
            currentPhaseId);
    }

    /**
    * Method responsible for autopromotion of proposals between phases.
    *
    * @throws SystemException
    * @throws PortalException
    */
    @Override
    public void autoPromoteProposals()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _contestPhaseLocalService.autoPromoteProposals();
    }

    @Override
    public void forcePromotionOfProposalInPhase(
        com.ext.portlet.model.Proposal p,
        com.ext.portlet.model.ContestPhase phase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _contestPhaseLocalService.forcePromotionOfProposalInPhase(p, phase);
    }

    @Override
    public int getNumberOfProposalsForJudge(
        com.liferay.portal.model.User judge,
        com.ext.portlet.model.ContestPhase phase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestPhaseLocalService.getNumberOfProposalsForJudge(judge,
            phase);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ContestPhaseLocalService getWrappedContestPhaseLocalService() {
        return _contestPhaseLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedContestPhaseLocalService(
        ContestPhaseLocalService contestPhaseLocalService) {
        _contestPhaseLocalService = contestPhaseLocalService;
    }

    @Override
    public ContestPhaseLocalService getWrappedService() {
        return _contestPhaseLocalService;
    }

    @Override
    public void setWrappedService(
        ContestPhaseLocalService contestPhaseLocalService) {
        _contestPhaseLocalService = contestPhaseLocalService;
    }
}
