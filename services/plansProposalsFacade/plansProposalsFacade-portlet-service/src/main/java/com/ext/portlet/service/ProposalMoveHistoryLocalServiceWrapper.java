package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProposalMoveHistoryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalMoveHistoryLocalService
 * @generated
 */
public class ProposalMoveHistoryLocalServiceWrapper
    implements ProposalMoveHistoryLocalService,
        ServiceWrapper<ProposalMoveHistoryLocalService> {
    private ProposalMoveHistoryLocalService _proposalMoveHistoryLocalService;

    public ProposalMoveHistoryLocalServiceWrapper(
        ProposalMoveHistoryLocalService proposalMoveHistoryLocalService) {
        _proposalMoveHistoryLocalService = proposalMoveHistoryLocalService;
    }

    /**
    * Adds the proposal move history to the database. Also notifies the appropriate model listeners.
    *
    * @param proposalMoveHistory the proposal move history
    * @return the proposal move history that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalMoveHistory addProposalMoveHistory(
        com.ext.portlet.model.ProposalMoveHistory proposalMoveHistory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalMoveHistoryLocalService.addProposalMoveHistory(proposalMoveHistory);
    }

    /**
    * Creates a new proposal move history with the primary key. Does not add the proposal move history to the database.
    *
    * @param id_ the primary key for the new proposal move history
    * @return the new proposal move history
    */
    @Override
    public com.ext.portlet.model.ProposalMoveHistory createProposalMoveHistory(
        long id_) {
        return _proposalMoveHistoryLocalService.createProposalMoveHistory(id_);
    }

    /**
    * Deletes the proposal move history with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id_ the primary key of the proposal move history
    * @return the proposal move history that was removed
    * @throws PortalException if a proposal move history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalMoveHistory deleteProposalMoveHistory(
        long id_)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalMoveHistoryLocalService.deleteProposalMoveHistory(id_);
    }

    /**
    * Deletes the proposal move history from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalMoveHistory the proposal move history
    * @return the proposal move history that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalMoveHistory deleteProposalMoveHistory(
        com.ext.portlet.model.ProposalMoveHistory proposalMoveHistory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalMoveHistoryLocalService.deleteProposalMoveHistory(proposalMoveHistory);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _proposalMoveHistoryLocalService.dynamicQuery();
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
        return _proposalMoveHistoryLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _proposalMoveHistoryLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _proposalMoveHistoryLocalService.dynamicQuery(dynamicQuery,
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
        return _proposalMoveHistoryLocalService.dynamicQueryCount(dynamicQuery);
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
        return _proposalMoveHistoryLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.ProposalMoveHistory fetchProposalMoveHistory(
        long id_) throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalMoveHistoryLocalService.fetchProposalMoveHistory(id_);
    }

    /**
    * Returns the proposal move history with the primary key.
    *
    * @param id_ the primary key of the proposal move history
    * @return the proposal move history
    * @throws PortalException if a proposal move history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalMoveHistory getProposalMoveHistory(
        long id_)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalMoveHistoryLocalService.getProposalMoveHistory(id_);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalMoveHistoryLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the proposal move histories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @return the range of proposal move histories
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> getProposalMoveHistories(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalMoveHistoryLocalService.getProposalMoveHistories(start,
            end);
    }

    /**
    * Returns the number of proposal move histories.
    *
    * @return the number of proposal move histories
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getProposalMoveHistoriesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalMoveHistoryLocalService.getProposalMoveHistoriesCount();
    }

    /**
    * Updates the proposal move history in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param proposalMoveHistory the proposal move history
    * @return the proposal move history that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalMoveHistory updateProposalMoveHistory(
        com.ext.portlet.model.ProposalMoveHistory proposalMoveHistory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalMoveHistoryLocalService.updateProposalMoveHistory(proposalMoveHistory);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _proposalMoveHistoryLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _proposalMoveHistoryLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _proposalMoveHistoryLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public com.ext.portlet.model.ProposalMoveHistory create(
        long srcProposalId, long targetProposalId, long srcContestId,
        long targetContestId, long sourcePhaseId, long targetPhaseId,
        java.lang.String moveType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalMoveHistoryLocalService.create(srcProposalId,
            targetProposalId, srcContestId, targetContestId, sourcePhaseId,
            targetPhaseId, moveType);
    }

    @Override
    public com.ext.portlet.model.ProposalMoveHistory createMoveHistory(
        long proposalId, long srcContestId, long targetContestId,
        long srcPhaseId, long targetPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalMoveHistoryLocalService.createMoveHistory(proposalId,
            srcContestId, targetContestId, srcPhaseId, targetPhaseId);
    }

    @Override
    public com.ext.portlet.model.ProposalMoveHistory createCopyHistory(
        long proposalId, long srcContestId, long targetContestId,
        long srcPhaseId, long targetPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalMoveHistoryLocalService.createCopyHistory(proposalId,
            srcContestId, targetContestId, srcPhaseId, targetPhaseId);
    }

    @Override
    public com.ext.portlet.model.ProposalMoveHistory createForkHistory(
        long srcProposalId, long targetProposalId, long srcContestId,
        long targetContestId, long srcPhaseId, long targetPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalMoveHistoryLocalService.createForkHistory(srcProposalId,
            targetProposalId, srcContestId, targetContestId, srcPhaseId,
            targetPhaseId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> getBySourceProposalId(
        long srcProposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalMoveHistoryLocalService.getBySourceProposalId(srcProposalId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> getBySourceContestId(
        long srcContestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalMoveHistoryLocalService.getBySourceContestId(srcContestId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> getBySourcePhaseId(
        long srcPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalMoveHistoryLocalService.getBySourcePhaseId(srcPhaseId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> getByTargetProposalId(
        long targetProposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalMoveHistoryLocalService.getByTargetProposalId(targetProposalId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> getByTargetContestId(
        long targetContestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalMoveHistoryLocalService.getByTargetContestId(targetContestId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> getByTargetPhaseId(
        long targetPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalMoveHistoryLocalService.getByTargetPhaseId(targetPhaseId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> getBySourceProposalIdContestId(
        long srcProposalId, long srcContestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalMoveHistoryLocalService.getBySourceProposalIdContestId(srcProposalId,
            srcContestId);
    }

    @Override
    public com.ext.portlet.model.ProposalMoveHistory getByTargetProposalIdContestId(
        long targetProposalId, long targetContestId)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalMoveHistoryLocalService.getByTargetProposalIdContestId(targetProposalId,
            targetContestId);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProposalMoveHistoryLocalService getWrappedProposalMoveHistoryLocalService() {
        return _proposalMoveHistoryLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProposalMoveHistoryLocalService(
        ProposalMoveHistoryLocalService proposalMoveHistoryLocalService) {
        _proposalMoveHistoryLocalService = proposalMoveHistoryLocalService;
    }

    @Override
    public ProposalMoveHistoryLocalService getWrappedService() {
        return _proposalMoveHistoryLocalService;
    }

    @Override
    public void setWrappedService(
        ProposalMoveHistoryLocalService proposalMoveHistoryLocalService) {
        _proposalMoveHistoryLocalService = proposalMoveHistoryLocalService;
    }
}
