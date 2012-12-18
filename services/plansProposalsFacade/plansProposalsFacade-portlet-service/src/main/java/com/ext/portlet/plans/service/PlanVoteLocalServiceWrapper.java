package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanVoteLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanVoteLocalService
 * @generated
 */
public class PlanVoteLocalServiceWrapper implements PlanVoteLocalService,
    ServiceWrapper<PlanVoteLocalService> {
    private PlanVoteLocalService _planVoteLocalService;

    public PlanVoteLocalServiceWrapper(
        PlanVoteLocalService planVoteLocalService) {
        _planVoteLocalService = planVoteLocalService;
    }

    /**
    * Adds the plan vote to the database. Also notifies the appropriate model listeners.
    *
    * @param planVote the plan vote
    * @return the plan vote that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanVote addPlanVote(
        com.ext.portlet.plans.model.PlanVote planVote)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.addPlanVote(planVote);
    }

    /**
    * Creates a new plan vote with the primary key. Does not add the plan vote to the database.
    *
    * @param planVotePK the primary key for the new plan vote
    * @return the new plan vote
    */
    public com.ext.portlet.plans.model.PlanVote createPlanVote(
        com.ext.portlet.plans.service.persistence.PlanVotePK planVotePK) {
        return _planVoteLocalService.createPlanVote(planVotePK);
    }

    /**
    * Deletes the plan vote with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planVotePK the primary key of the plan vote
    * @throws PortalException if a plan vote with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanVote(
        com.ext.portlet.plans.service.persistence.PlanVotePK planVotePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planVoteLocalService.deletePlanVote(planVotePK);
    }

    /**
    * Deletes the plan vote from the database. Also notifies the appropriate model listeners.
    *
    * @param planVote the plan vote
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanVote(com.ext.portlet.plans.model.PlanVote planVote)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planVoteLocalService.deletePlanVote(planVote);
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
        return _planVoteLocalService.dynamicQuery(dynamicQuery);
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
        return _planVoteLocalService.dynamicQuery(dynamicQuery, start, end);
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
        return _planVoteLocalService.dynamicQuery(dynamicQuery, start, end,
            orderByComparator);
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
        return _planVoteLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.plans.model.PlanVote fetchPlanVote(
        com.ext.portlet.plans.service.persistence.PlanVotePK planVotePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.fetchPlanVote(planVotePK);
    }

    /**
    * Returns the plan vote with the primary key.
    *
    * @param planVotePK the primary key of the plan vote
    * @return the plan vote
    * @throws PortalException if a plan vote with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanVote getPlanVote(
        com.ext.portlet.plans.service.persistence.PlanVotePK planVotePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.getPlanVote(planVotePK);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan votes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan votes
    * @param end the upper bound of the range of plan votes (not inclusive)
    * @return the range of plan votes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanVote> getPlanVotes(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.getPlanVotes(start, end);
    }

    /**
    * Returns the number of plan votes.
    *
    * @return the number of plan votes
    * @throws SystemException if a system exception occurred
    */
    public int getPlanVotesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.getPlanVotesCount();
    }

    /**
    * Updates the plan vote in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planVote the plan vote
    * @return the plan vote that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanVote updatePlanVote(
        com.ext.portlet.plans.model.PlanVote planVote)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.updatePlanVote(planVote);
    }

    /**
    * Updates the plan vote in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planVote the plan vote
    * @param merge whether to merge the plan vote with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan vote that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanVote updatePlanVote(
        com.ext.portlet.plans.model.PlanVote planVote, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.updatePlanVote(planVote, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _planVoteLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planVoteLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
    * Votes for a plan, if user has already voted for given plan returns false, true otherwise.
    */
    public boolean voteForPlan(java.lang.Long planId, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.voteForPlan(planId, userId);
    }

    public boolean unvote(java.lang.Long userId, java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.unvote(userId, contestId);
    }

    public com.ext.portlet.plans.model.PlanVote getPlanVote(
        java.lang.Long userId, java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.getPlanVote(userId, contestId);
    }

    public int coutPlanVotes(java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.coutPlanVotes(planId);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanVote> getPlanVotes(
        java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.getPlanVotes(planId);
    }

    public int countPlanVotes(com.ext.portlet.plans.model.PlanType type)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.countPlanVotes(type);
    }

    public int countPlanVotes(com.ext.portlet.contests.model.Contest contest)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.countPlanVotes(contest);
    }

    public int countPlanVotesByPlanId(java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.countPlanVotesByPlanId(planId);
    }

    public void store(com.ext.portlet.plans.model.PlanVote planVote)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planVoteLocalService.store(planVote);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanVoteLocalService getWrappedPlanVoteLocalService() {
        return _planVoteLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanVoteLocalService(
        PlanVoteLocalService planVoteLocalService) {
        _planVoteLocalService = planVoteLocalService;
    }

    public PlanVoteLocalService getWrappedService() {
        return _planVoteLocalService;
    }

    public void setWrappedService(PlanVoteLocalService planVoteLocalService) {
        _planVoteLocalService = planVoteLocalService;
    }
}
