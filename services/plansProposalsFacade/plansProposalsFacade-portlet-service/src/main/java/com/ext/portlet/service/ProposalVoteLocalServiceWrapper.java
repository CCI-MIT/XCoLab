package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProposalVoteLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalVoteLocalService
 * @generated
 */
public class ProposalVoteLocalServiceWrapper implements ProposalVoteLocalService,
    ServiceWrapper<ProposalVoteLocalService> {
    private ProposalVoteLocalService _proposalVoteLocalService;

    public ProposalVoteLocalServiceWrapper(
        ProposalVoteLocalService proposalVoteLocalService) {
        _proposalVoteLocalService = proposalVoteLocalService;
    }

    /**
    * Adds the proposal vote to the database. Also notifies the appropriate model listeners.
    *
    * @param proposalVote the proposal vote
    * @return the proposal vote that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalVote addProposalVote(
        com.ext.portlet.model.ProposalVote proposalVote)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalVoteLocalService.addProposalVote(proposalVote);
    }

    /**
    * Creates a new proposal vote with the primary key. Does not add the proposal vote to the database.
    *
    * @param proposalVotePK the primary key for the new proposal vote
    * @return the new proposal vote
    */
    @Override
    public com.ext.portlet.model.ProposalVote createProposalVote(
        com.ext.portlet.service.persistence.ProposalVotePK proposalVotePK) {
        return _proposalVoteLocalService.createProposalVote(proposalVotePK);
    }

    /**
    * Deletes the proposal vote with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalVotePK the primary key of the proposal vote
    * @return the proposal vote that was removed
    * @throws PortalException if a proposal vote with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalVote deleteProposalVote(
        com.ext.portlet.service.persistence.ProposalVotePK proposalVotePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalVoteLocalService.deleteProposalVote(proposalVotePK);
    }

    /**
    * Deletes the proposal vote from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalVote the proposal vote
    * @return the proposal vote that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalVote deleteProposalVote(
        com.ext.portlet.model.ProposalVote proposalVote)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalVoteLocalService.deleteProposalVote(proposalVote);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _proposalVoteLocalService.dynamicQuery();
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
        return _proposalVoteLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _proposalVoteLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _proposalVoteLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _proposalVoteLocalService.dynamicQueryCount(dynamicQuery);
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
        return _proposalVoteLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.ProposalVote fetchProposalVote(
        com.ext.portlet.service.persistence.ProposalVotePK proposalVotePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalVoteLocalService.fetchProposalVote(proposalVotePK);
    }

    /**
    * Returns the proposal vote with the primary key.
    *
    * @param proposalVotePK the primary key of the proposal vote
    * @return the proposal vote
    * @throws PortalException if a proposal vote with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalVote getProposalVote(
        com.ext.portlet.service.persistence.ProposalVotePK proposalVotePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalVoteLocalService.getProposalVote(proposalVotePK);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalVoteLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the proposal votes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal votes
    * @param end the upper bound of the range of proposal votes (not inclusive)
    * @return the range of proposal votes
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.ProposalVote> getProposalVotes(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalVoteLocalService.getProposalVotes(start, end);
    }

    /**
    * Returns the number of proposal votes.
    *
    * @return the number of proposal votes
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getProposalVotesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalVoteLocalService.getProposalVotesCount();
    }

    /**
    * Updates the proposal vote in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param proposalVote the proposal vote
    * @return the proposal vote that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalVote updateProposalVote(
        com.ext.portlet.model.ProposalVote proposalVote)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalVoteLocalService.updateProposalVote(proposalVote);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _proposalVoteLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _proposalVoteLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _proposalVoteLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public com.ext.portlet.model.ProposalVote create(long contestPhaseId,
        long userID) {
        return _proposalVoteLocalService.create(contestPhaseId, userID);
    }

    @Override
    public com.ext.portlet.model.ProposalVote findByProposalIdContestPhaseIdUserId(
        long contestPhaseId, long userId)
        throws com.ext.portlet.NoSuchProposalVoteException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalVoteLocalService.findByProposalIdContestPhaseIdUserId(contestPhaseId,
            userId);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProposalVoteLocalService getWrappedProposalVoteLocalService() {
        return _proposalVoteLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProposalVoteLocalService(
        ProposalVoteLocalService proposalVoteLocalService) {
        _proposalVoteLocalService = proposalVoteLocalService;
    }

    @Override
    public ProposalVoteLocalService getWrappedService() {
        return _proposalVoteLocalService;
    }

    @Override
    public void setWrappedService(
        ProposalVoteLocalService proposalVoteLocalService) {
        _proposalVoteLocalService = proposalVoteLocalService;
    }
}
