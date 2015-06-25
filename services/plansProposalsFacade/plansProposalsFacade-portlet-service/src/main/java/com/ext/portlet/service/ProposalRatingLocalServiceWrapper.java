package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProposalRatingLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalRatingLocalService
 * @generated
 */
public class ProposalRatingLocalServiceWrapper
    implements ProposalRatingLocalService,
        ServiceWrapper<ProposalRatingLocalService> {
    private ProposalRatingLocalService _proposalRatingLocalService;

    public ProposalRatingLocalServiceWrapper(
        ProposalRatingLocalService proposalRatingLocalService) {
        _proposalRatingLocalService = proposalRatingLocalService;
    }

    /**
    * Adds the proposal rating to the database. Also notifies the appropriate model listeners.
    *
    * @param proposalRating the proposal rating
    * @return the proposal rating that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalRating addProposalRating(
        com.ext.portlet.model.ProposalRating proposalRating)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalRatingLocalService.addProposalRating(proposalRating);
    }

    /**
    * Creates a new proposal rating with the primary key. Does not add the proposal rating to the database.
    *
    * @param id the primary key for the new proposal rating
    * @return the new proposal rating
    */
    @Override
    public com.ext.portlet.model.ProposalRating createProposalRating(long id) {
        return _proposalRatingLocalService.createProposalRating(id);
    }

    /**
    * Deletes the proposal rating with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the proposal rating
    * @return the proposal rating that was removed
    * @throws PortalException if a proposal rating with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalRating deleteProposalRating(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalRatingLocalService.deleteProposalRating(id);
    }

    /**
    * Deletes the proposal rating from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalRating the proposal rating
    * @return the proposal rating that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalRating deleteProposalRating(
        com.ext.portlet.model.ProposalRating proposalRating)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalRatingLocalService.deleteProposalRating(proposalRating);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _proposalRatingLocalService.dynamicQuery();
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
        return _proposalRatingLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalRatingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _proposalRatingLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalRatingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _proposalRatingLocalService.dynamicQuery(dynamicQuery, start,
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
        return _proposalRatingLocalService.dynamicQueryCount(dynamicQuery);
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
        return _proposalRatingLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.ProposalRating fetchProposalRating(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalRatingLocalService.fetchProposalRating(id);
    }

    /**
    * Returns the proposal rating with the primary key.
    *
    * @param id the primary key of the proposal rating
    * @return the proposal rating
    * @throws PortalException if a proposal rating with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalRating getProposalRating(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalRatingLocalService.getProposalRating(id);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalRatingLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the proposal ratings.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalRatingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal ratings
    * @param end the upper bound of the range of proposal ratings (not inclusive)
    * @return the range of proposal ratings
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.ProposalRating> getProposalRatings(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalRatingLocalService.getProposalRatings(start, end);
    }

    /**
    * Returns the number of proposal ratings.
    *
    * @return the number of proposal ratings
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getProposalRatingsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalRatingLocalService.getProposalRatingsCount();
    }

    /**
    * Updates the proposal rating in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param proposalRating the proposal rating
    * @return the proposal rating that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalRating updateProposalRating(
        com.ext.portlet.model.ProposalRating proposalRating)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalRatingLocalService.updateProposalRating(proposalRating);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _proposalRatingLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _proposalRatingLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _proposalRatingLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ProposalRating> getFellowRatingsForProposal(
        long proposalId, long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalRatingLocalService.getFellowRatingsForProposal(proposalId,
            contestPhaseId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ProposalRating> getJudgeRatingsForProposal(
        long proposalId, long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalRatingLocalService.getJudgeRatingsForProposal(proposalId,
            contestPhaseId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ProposalRating> getJudgeRatingsForProposalAndUser(
        long userId, long proposalId, long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalRatingLocalService.getJudgeRatingsForProposalAndUser(userId,
            proposalId, contestPhaseId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ProposalRating> getFellowRatingForProposalAndUser(
        long userId, long proposalId, long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalRatingLocalService.getFellowRatingForProposalAndUser(userId,
            proposalId, contestPhaseId);
    }

    @Override
    public com.ext.portlet.model.ProposalRating updateRating(
        long proposalRatingId, long ratingValueId, java.lang.String comment,
        java.lang.String otherDataString)
        throws com.liferay.portal.NoSuchUserException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalRatingLocalService.updateRating(proposalRatingId,
            ratingValueId, comment, otherDataString);
    }

    @Override
    public com.ext.portlet.model.ProposalRating addRating(long proposalId,
        long contestPhaseId, long userId, long ratingValueId,
        java.lang.String comment, java.lang.String otherDataString)
        throws com.liferay.portal.NoSuchUserException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalRatingLocalService.addRating(proposalId,
            contestPhaseId, userId, ratingValueId, comment, otherDataString);
    }

    @Override
    public com.ext.portlet.model.ProposalRating addRating(long proposalId,
        long contestPhaseId, long userId, long ratingValueId,
        java.lang.String comment, java.lang.String otherDataString,
        boolean onlyForInternalUsage)
        throws com.liferay.portal.NoSuchUserException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalRatingLocalService.addRating(proposalId,
            contestPhaseId, userId, ratingValueId, comment, otherDataString,
            onlyForInternalUsage);
    }

    @Override
    public com.ext.portlet.model.ProposalRating updateRating(
        com.ext.portlet.model.ProposalRating proposalRating)
        throws com.liferay.portal.NoSuchUserException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalRatingLocalService.updateRating(proposalRating);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProposalRatingLocalService getWrappedProposalRatingLocalService() {
        return _proposalRatingLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProposalRatingLocalService(
        ProposalRatingLocalService proposalRatingLocalService) {
        _proposalRatingLocalService = proposalRatingLocalService;
    }

    @Override
    public ProposalRatingLocalService getWrappedService() {
        return _proposalRatingLocalService;
    }

    @Override
    public void setWrappedService(
        ProposalRatingLocalService proposalRatingLocalService) {
        _proposalRatingLocalService = proposalRatingLocalService;
    }
}
