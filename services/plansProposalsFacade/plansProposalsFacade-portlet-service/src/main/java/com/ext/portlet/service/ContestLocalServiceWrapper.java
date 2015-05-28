package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContestLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContestLocalService
 * @generated
 */
public class ContestLocalServiceWrapper implements ContestLocalService,
    ServiceWrapper<ContestLocalService> {
    private ContestLocalService _contestLocalService;

    public ContestLocalServiceWrapper(ContestLocalService contestLocalService) {
        _contestLocalService = contestLocalService;
    }

    /**
    * Adds the contest to the database. Also notifies the appropriate model listeners.
    *
    * @param contest the contest
    * @return the contest that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.Contest addContest(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.addContest(contest);
    }

    /**
    * Creates a new contest with the primary key. Does not add the contest to the database.
    *
    * @param ContestPK the primary key for the new contest
    * @return the new contest
    */
    @Override
    public com.ext.portlet.model.Contest createContest(long ContestPK) {
        return _contestLocalService.createContest(ContestPK);
    }

    /**
    * Deletes the contest with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ContestPK the primary key of the contest
    * @return the contest that was removed
    * @throws PortalException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.Contest deleteContest(long ContestPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.deleteContest(ContestPK);
    }

    /**
    * Deletes the contest from the database. Also notifies the appropriate model listeners.
    *
    * @param contest the contest
    * @return the contest that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.Contest deleteContest(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.deleteContest(contest);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _contestLocalService.dynamicQuery();
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
        return _contestLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _contestLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _contestLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _contestLocalService.dynamicQueryCount(dynamicQuery);
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
        return _contestLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public com.ext.portlet.model.Contest fetchContest(long ContestPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.fetchContest(ContestPK);
    }

    /**
    * Returns the contest with the primary key.
    *
    * @param ContestPK the primary key of the contest
    * @return the contest
    * @throws PortalException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.Contest getContest(long ContestPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getContest(ContestPK);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the contests.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of contests
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.Contest> getContests(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getContests(start, end);
    }

    /**
    * Returns the number of contests.
    *
    * @return the number of contests
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getContestsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getContestsCount();
    }

    /**
    * Updates the contest in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contest the contest
    * @return the contest that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.Contest updateContest(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.updateContest(contest);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _contestLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contestLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _contestLocalService.invokeMethod(name, parameterTypes, arguments);
    }

    @Override
    public com.ext.portlet.model.Contest getContestByActiveFlag(
        boolean contestActive)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getContestByActiveFlag(contestActive);
    }

    @Override
    public com.ext.portlet.model.Contest createNewContest(
        java.lang.Long userId, java.lang.String name)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.createNewContest(userId, name);
    }

    @Override
    public void updateContestGroupsAndDiscussions()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _contestLocalService.updateContestGroupsAndDiscussions();
    }

    @Override
    public java.util.List<com.ext.portlet.model.Contest> findByActive(
        boolean active)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.findByActive(active);
    }

    @Override
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFeatured(
        boolean active, boolean featured)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.findByActiveFeatured(active, featured);
    }

    @Override
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlag(
        boolean active, int flag)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.findByActiveFlag(active, flag);
    }

    @Override
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlagText(
        boolean active, java.lang.String flagText)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.findByActiveFlagText(active, flagText);
    }

    /**
    * Methods from ContestImpl *
    */
    @Override
    public java.util.List<com.ext.portlet.model.ContestPhase> getAllPhases(
        com.ext.portlet.model.Contest contest) {
        return _contestLocalService.getAllPhases(contest);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ContestPhase> getVisiblePhases(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getVisiblePhases(contest);
    }

    @Override
    public com.ext.portlet.model.PlanType getPlanType(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getPlanType(contest);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ContestPhase> getActivePhases(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getActivePhases(contest);
    }

    @Override
    public com.ext.portlet.model.ContestPhase getActivePhase(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getActivePhase(contest);
    }

    @Override
    public com.ext.portlet.model.ContestPhase getActiveOrLastPhase(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getActiveOrLastPhase(contest);
    }

    @Override
    public boolean isActive(com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.isActive(contest);
    }

    @Override
    public java.util.List<java.lang.Long> getDebatesIds(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getDebatesIds(contest);
    }

    @Override
    public java.lang.Integer getTotalVotes(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getTotalVotes(contest);
    }

    @Override
    public void updateDefaultPlanDescription(
        com.ext.portlet.model.Contest contest, java.lang.String description)
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestLocalService.updateDefaultPlanDescription(contest, description);
    }

    @Override
    public void store(com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.SystemException {
        _contestLocalService.store(contest);
    }

    @Override
    public com.ext.portlet.model.PlanTemplate getPlanTemplate(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getPlanTemplate(contest);
    }

    @Override
    public com.ext.portlet.model.FocusArea getFocusArea(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getFocusArea(contest);
    }

    @Override
    public com.liferay.portal.model.Image getLogo(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getLogo(contest);
    }

    @Override
    public com.liferay.portal.model.Image getSponsorLogo(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getSponsorLogo(contest);
    }

    @Override
    public void setLogo(com.ext.portlet.model.Contest contest,
        java.io.File logoFile)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        _contestLocalService.setLogo(contest, logoFile);
    }

    @Override
    public void setSponsorLogo(com.ext.portlet.model.Contest contest,
        java.io.File logoFile)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        _contestLocalService.setSponsorLogo(contest, logoFile);
    }

    @Override
    public java.lang.String getLogoPath(com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getLogoPath(contest);
    }

    @Override
    public java.lang.String getSponsorLogoPath(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getSponsorLogoPath(contest);
    }

    @Override
    public long getProposalsCount(com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getProposalsCount(contest);
    }

    @Override
    public com.ext.portlet.model.DiscussionCategoryGroup getDiscussionCategoryGroup(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getDiscussionCategoryGroup(contest);
    }

    @Override
    public long getTotalCommentsCount(com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getTotalCommentsCount(contest);
    }

    @Override
    public long getCommentsCount(com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getCommentsCount(contest);
    }

    @Override
    public long getProposalsCommentsCount(com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getProposalsCommentsCount(contest);
    }

    @Override
    public long getVotesCount(com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getVotesCount(contest);
    }

    @Override
    public long getTotalComments(com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getTotalComments(contest);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ContestTeamMember> getTeamMembers(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getTeamMembers(contest);
    }

    /**
    * <p>Returns true if user is subscribed to a contest, false otherwise</p>
    *
    * @param contestPK id of a contest
    * @param userId    id of a user
    * @return true if user is subscribed to a contest, false otherwise
    * @throws PortalException in case of LR error
    * @throws SystemException in case of LR error
    */
    @Override
    public boolean isSubscribed(long contestPK, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.isSubscribed(contestPK, userId);
    }

    /**
    * <p>Subscribes user to contest</p>
    *
    * @param contestPK id of a contest
    * @param userId    id of a user
    * @throws PortalException in case of LR error
    * @throws SystemException in case of LR error
    */
    @Override
    public void subscribe(long contestPK, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _contestLocalService.subscribe(contestPK, userId);
    }

    /**
    * <p>Subscribes user to contest</p>
    *
    * @param contestPK id of a contest
    * @param userId    id of a user
    * @throws PortalException in case of LR error
    * @throws SystemException in case of LR error
    */
    @Override
    public void unsubscribe(long contestPK, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _contestLocalService.unsubscribe(contestPK, userId);
    }

    @Override
    public java.util.List<java.lang.Long> getModelIds(long contestPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getModelIds(contestPK);
    }

    @Override
    public java.util.Map<java.lang.Long, java.lang.String> getModelIdsAndNames(
        long contestPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getModelIdsAndNames(contestPK);
    }

    @Override
    public java.lang.Long getDefaultModelId(long contestPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getDefaultModelId(contestPK);
    }

    @Override
    public java.util.List<com.ext.portlet.model.Contest> getContestsByActivePrivate(
        boolean active, boolean privateContest)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getContestsByActivePrivate(active,
            privateContest);
    }

    @Override
    public java.util.List<com.ext.portlet.model.Contest> getContestsMatchingOntologyTerms(
        java.util.List<com.ext.portlet.model.OntologyTerm> ontologyTerms)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getContestsMatchingOntologyTerms(ontologyTerms);
    }

    /**
    * Returns all contests matching the specified contest tier.
    * Returns all contests in the case of ContestTier.NONE
    *
    * @param contestTierType   The specified contest tier
    * @return A list of all Contests matching the specified contest tier
    * @throws PortalException
    * @throws SystemException
    */
    @Override
    public java.util.List<com.ext.portlet.model.Contest> getContestsMatchingTier(
        long contestTierType)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getContestsMatchingTier(contestTierType);
    }

    /**
    * Returns all contests matching the specified contest tier and ontology terms.
    * Returns all contests in the case of ContestTier.NONE
    *
    * @param contestTierType   The specified contest tier
    * @return A list of all Contests matching the specified contest tier
    * @throws PortalException
    * @throws SystemException
    */
    @Override
    public java.util.List<com.ext.portlet.model.Contest> getContestsMatchingOntologyTermsAndTier(
        java.util.List<com.ext.portlet.model.OntologyTerm> ontologyTerms,
        long contestTierType)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getContestsMatchingOntologyTermsAndTier(ontologyTerms,
            contestTierType);
    }

    /**
    * This method transfers users' supports for proposals in the passed contest to a vote. If a user has only supported
    * one proposal within the passed contest, the support is automatically transferred to a vote and the user is notified
    * about this action. If the user supports more than one proposal in the passed contest, a message is sent to the user
    * which the user to vote for his/her favourite proposal of the list of supported proposals
    *
    * @param contest               The contest object
    * @param serviceContext        The service contest containing the portal base URL
    * @throws SystemException
    * @throws PortalException
    */
    @Override
    public void transferSupportsToVote(com.ext.portlet.model.Contest contest,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _contestLocalService.transferSupportsToVote(contest, serviceContext);
    }

    /**
    * This method generates a CSV string of all judge Reviews of all previous contestPhases of a contest.
    * By using the currentPhase parameter, the output is filtered to only include proposals that are in
    * currentPhase.
    *
    * @param contest           The contest for which the review should be created
    * @param currentPhase      The currently active ContestPhase which should be used for proposal filtering
    * @param serviceContext    A serviceContext which must include the Portal's base URL
    * @return
    * @throws SystemException
    * @throws PortalException
    */
    @Override
    public java.lang.String getProposalJudgeReviewCsv(
        com.ext.portlet.model.Contest contest,
        com.ext.portlet.model.ContestPhase currentPhase,
        com.liferay.portal.service.ServiceContext serviceContext)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getProposalJudgeReviewCsv(contest,
            currentPhase, serviceContext);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getAdvisorsForContest(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getAdvisorsForContest(contest);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getJudgesForContest(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getJudgesForContest(contest);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getFellowsForContest(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getFellowsForContest(contest);
    }

    @Override
    public java.util.List<com.liferay.portal.model.User> getContestManagersForContest(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getContestManagersForContest(contest);
    }

    /**
    * Returns the URL link address for the passed contest
    *
    * @param contest   The contest object
    * @return Contest URL as String
    */
    @Override
    public java.lang.String getContestLinkUrl(
        com.ext.portlet.model.Contest contest) {
        return _contestLocalService.getContestLinkUrl(contest);
    }

    @Override
    public boolean hasContestEnded(long contestPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.hasContestEnded(contestPK);
    }

    @Override
    public boolean hasContestEnded(com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.hasContestEnded(contest);
    }

    @Override
    public com.ext.portlet.model.Proposal getWinnerProposal(long contestPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getWinnerProposal(contestPK);
    }

    @Override
    public java.lang.Integer getPointsAccessibleForActivePhaseOfContest(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getPointsAccessibleForActivePhaseOfContest(contest);
    }

    @Override
    public com.ext.portlet.model.ImpactTemplateSeries getContestImpactTemplateSeries(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getContestImpactTemplateSeries(contest);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ImpactIteration> getContestImpactIterations(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getContestImpactIterations(contest);
    }

    @Override
    public com.ext.portlet.model.ImpactTemplateFocusAreaList getContestImpactFocusAreaList(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getContestImpactFocusAreaList(contest);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ImpactTemplateMaxFocusArea> getContestImpactFocusAreas(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contestLocalService.getContestImpactFocusAreas(contest);
    }

    @Override
    public java.util.List<com.ext.portlet.model.Contest> getContestsByTierLevelAndOntologyTermIds(
        java.lang.Long contestTier,
        java.util.List<java.lang.Long> focusAreaOntologyTermIds)
        throws java.lang.Exception {
        return _contestLocalService.getContestsByTierLevelAndOntologyTermIds(contestTier,
            focusAreaOntologyTermIds);
    }

    @Override
    public java.util.List<com.ext.portlet.model.Contest> getContestsByContestScheduleId(
        java.lang.Long contestScheduleId) throws java.lang.Exception {
        return _contestLocalService.getContestsByContestScheduleId(contestScheduleId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.Contest> getContestsByPlanTemplateId(
        java.lang.Long planTemplateId) throws java.lang.Exception {
        return _contestLocalService.getContestsByPlanTemplateId(planTemplateId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.Contest> getSubContestsByOntologySpaceId(
        com.ext.portlet.model.Contest contest, java.lang.Long ontologySpaceId)
        throws java.lang.Exception {
        return _contestLocalService.getSubContestsByOntologySpaceId(contest,
            ontologySpaceId);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ContestLocalService getWrappedContestLocalService() {
        return _contestLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedContestLocalService(
        ContestLocalService contestLocalService) {
        _contestLocalService = contestLocalService;
    }

    @Override
    public ContestLocalService getWrappedService() {
        return _contestLocalService;
    }

    @Override
    public void setWrappedService(ContestLocalService contestLocalService) {
        _contestLocalService = contestLocalService;
    }
}
