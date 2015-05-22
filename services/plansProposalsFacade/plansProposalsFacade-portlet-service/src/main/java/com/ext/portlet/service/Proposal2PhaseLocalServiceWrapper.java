package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link Proposal2PhaseLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see Proposal2PhaseLocalService
 * @generated
 */
public class Proposal2PhaseLocalServiceWrapper
    implements Proposal2PhaseLocalService,
        ServiceWrapper<Proposal2PhaseLocalService> {
    private Proposal2PhaseLocalService _proposal2PhaseLocalService;

    public Proposal2PhaseLocalServiceWrapper(
        Proposal2PhaseLocalService proposal2PhaseLocalService) {
        _proposal2PhaseLocalService = proposal2PhaseLocalService;
    }

    /**
    * Adds the proposal2 phase to the database. Also notifies the appropriate model listeners.
    *
    * @param proposal2Phase the proposal2 phase
    * @return the proposal2 phase that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.Proposal2Phase addProposal2Phase(
        com.ext.portlet.model.Proposal2Phase proposal2Phase)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposal2PhaseLocalService.addProposal2Phase(proposal2Phase);
    }

    /**
    * Creates a new proposal2 phase with the primary key. Does not add the proposal2 phase to the database.
    *
    * @param proposal2PhasePK the primary key for the new proposal2 phase
    * @return the new proposal2 phase
    */
    @Override
    public com.ext.portlet.model.Proposal2Phase createProposal2Phase(
        com.ext.portlet.service.persistence.Proposal2PhasePK proposal2PhasePK) {
        return _proposal2PhaseLocalService.createProposal2Phase(proposal2PhasePK);
    }

    /**
    * Deletes the proposal2 phase with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param proposal2PhasePK the primary key of the proposal2 phase
    * @return the proposal2 phase that was removed
    * @throws PortalException if a proposal2 phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.Proposal2Phase deleteProposal2Phase(
        com.ext.portlet.service.persistence.Proposal2PhasePK proposal2PhasePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposal2PhaseLocalService.deleteProposal2Phase(proposal2PhasePK);
    }

    /**
    * Deletes the proposal2 phase from the database. Also notifies the appropriate model listeners.
    *
    * @param proposal2Phase the proposal2 phase
    * @return the proposal2 phase that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.Proposal2Phase deleteProposal2Phase(
        com.ext.portlet.model.Proposal2Phase proposal2Phase)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposal2PhaseLocalService.deleteProposal2Phase(proposal2Phase);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _proposal2PhaseLocalService.dynamicQuery();
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
        return _proposal2PhaseLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Proposal2PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _proposal2PhaseLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Proposal2PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _proposal2PhaseLocalService.dynamicQuery(dynamicQuery, start,
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
        return _proposal2PhaseLocalService.dynamicQueryCount(dynamicQuery);
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
        return _proposal2PhaseLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.Proposal2Phase fetchProposal2Phase(
        com.ext.portlet.service.persistence.Proposal2PhasePK proposal2PhasePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposal2PhaseLocalService.fetchProposal2Phase(proposal2PhasePK);
    }

    /**
    * Returns the proposal2 phase with the primary key.
    *
    * @param proposal2PhasePK the primary key of the proposal2 phase
    * @return the proposal2 phase
    * @throws PortalException if a proposal2 phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.Proposal2Phase getProposal2Phase(
        com.ext.portlet.service.persistence.Proposal2PhasePK proposal2PhasePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposal2PhaseLocalService.getProposal2Phase(proposal2PhasePK);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposal2PhaseLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the proposal2 phases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Proposal2PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal2 phases
    * @param end the upper bound of the range of proposal2 phases (not inclusive)
    * @return the range of proposal2 phases
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.Proposal2Phase> getProposal2Phases(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposal2PhaseLocalService.getProposal2Phases(start, end);
    }

    /**
    * Returns the number of proposal2 phases.
    *
    * @return the number of proposal2 phases
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getProposal2PhasesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposal2PhaseLocalService.getProposal2PhasesCount();
    }

    /**
    * Updates the proposal2 phase in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param proposal2Phase the proposal2 phase
    * @return the proposal2 phase that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.Proposal2Phase updateProposal2Phase(
        com.ext.portlet.model.Proposal2Phase proposal2Phase)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposal2PhaseLocalService.updateProposal2Phase(proposal2Phase);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _proposal2PhaseLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _proposal2PhaseLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _proposal2PhaseLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public com.ext.portlet.model.Proposal2Phase create(long proposalId,
        long contestPhaseId) {
        return _proposal2PhaseLocalService.create(proposalId, contestPhaseId);
    }

    @Override
    public com.ext.portlet.model.Proposal2Phase create(long proposalId,
        long contestPhaseId, int versionFrom, int versionTo)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposal2PhaseLocalService.create(proposalId, contestPhaseId,
            versionFrom, versionTo);
    }

    @Override
    public com.ext.portlet.model.Proposal2Phase getByProposalIdContestPhaseId(
        long proposalId, long contestPhaseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposal2PhaseLocalService.getByProposalIdContestPhaseId(proposalId,
            contestPhaseId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.Proposal2Phase> getByProposalId(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposal2PhaseLocalService.getByProposalId(proposalId);
    }

    @Override
    public int getLatestProposalVersionInActiveContest(
        java.lang.Long proposalId) throws java.lang.Exception {
        return _proposal2PhaseLocalService.getLatestProposalVersionInActiveContest(proposalId);
    }

    @Override
    public com.ext.portlet.model.ContestPhase getLatestContestPhaseInContest(
        java.lang.Long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposal2PhaseLocalService.getLatestContestPhaseInContest(proposalId);
    }

    @Override
    public com.ext.portlet.model.Contest getCurrentContestForProposal(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposal2PhaseLocalService.getCurrentContestForProposal(proposalId);
    }

    @Override
    public com.ext.portlet.model.Proposal2Phase getForVersion(
        java.lang.Long proposalId, int proposalVersionId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposal2PhaseLocalService.getForVersion(proposalId,
            proposalVersionId);
    }

    @Override
    public com.ext.portlet.model.Proposal2Phase getForVersion(
        com.ext.portlet.model.ProposalVersion proposalVersion)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposal2PhaseLocalService.getForVersion(proposalVersion);
    }

    @Override
    public java.util.List<java.lang.Long> getContestPhasesForProposal(
        long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposal2PhaseLocalService.getContestPhasesForProposal(proposalId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ContestPhase> getActiveContestPhasesForProposal(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposal2PhaseLocalService.getActiveContestPhasesForProposal(proposalId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.Proposal2Phase> getByContestPhaseId(
        long contestPhaseId) throws java.lang.Exception {
        return _proposal2PhaseLocalService.getByContestPhaseId(contestPhaseId);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public Proposal2PhaseLocalService getWrappedProposal2PhaseLocalService() {
        return _proposal2PhaseLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProposal2PhaseLocalService(
        Proposal2PhaseLocalService proposal2PhaseLocalService) {
        _proposal2PhaseLocalService = proposal2PhaseLocalService;
    }

    @Override
    public Proposal2PhaseLocalService getWrappedService() {
        return _proposal2PhaseLocalService;
    }

    @Override
    public void setWrappedService(
        Proposal2PhaseLocalService proposal2PhaseLocalService) {
        _proposal2PhaseLocalService = proposal2PhaseLocalService;
    }
}
