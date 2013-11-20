package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanVoteLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanVoteLocalService
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
    @Override
    public com.ext.portlet.model.PlanVote addPlanVote(
        com.ext.portlet.model.PlanVote planVote)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.addPlanVote(planVote);
    }

    /**
    * Creates a new plan vote with the primary key. Does not add the plan vote to the database.
    *
    * @param planVotePK the primary key for the new plan vote
    * @return the new plan vote
    */
    @Override
    public com.ext.portlet.model.PlanVote createPlanVote(
        com.ext.portlet.service.persistence.PlanVotePK planVotePK) {
        return _planVoteLocalService.createPlanVote(planVotePK);
    }

    /**
    * Deletes the plan vote with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planVotePK the primary key of the plan vote
    * @return the plan vote that was removed
    * @throws PortalException if a plan vote with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanVote deletePlanVote(
        com.ext.portlet.service.persistence.PlanVotePK planVotePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.deletePlanVote(planVotePK);
    }

    /**
    * Deletes the plan vote from the database. Also notifies the appropriate model listeners.
    *
    * @param planVote the plan vote
    * @return the plan vote that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PlanVote deletePlanVote(
        com.ext.portlet.model.PlanVote planVote)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.deletePlanVote(planVote);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _planVoteLocalService.dynamicQuery();
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
        return _planVoteLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _planVoteLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.dynamicQueryCount(dynamicQuery);
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
        return _planVoteLocalService.dynamicQueryCount(dynamicQuery, projection);
    }

    @Override
    public com.ext.portlet.model.PlanVote fetchPlanVote(
        com.ext.portlet.service.persistence.PlanVotePK planVotePK)
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
    @Override
    public com.ext.portlet.model.PlanVote getPlanVote(
        com.ext.portlet.service.persistence.PlanVotePK planVotePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.getPlanVote(planVotePK);
    }

    @Override
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan votes
    * @param end the upper bound of the range of plan votes (not inclusive)
    * @return the range of plan votes
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.PlanVote> getPlanVotes(
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
    @Override
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
    @Override
    public com.ext.portlet.model.PlanVote updatePlanVote(
        com.ext.portlet.model.PlanVote planVote)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.updatePlanVote(planVote);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planVoteLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planVoteLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planVoteLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public boolean voteForPlan(java.lang.Long planId, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.voteForPlan(planId, userId);
    }

    @Override
    public boolean unvote(java.lang.Long userId, java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.unvote(userId, contestId);
    }

    @Override
    public com.ext.portlet.model.PlanVote getPlanVote(java.lang.Long userId,
        java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.getPlanVote(userId, contestId);
    }

    @Override
    public int coutPlanVotes(java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.coutPlanVotes(planId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.PlanVote> getPlanVotes(
        java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.getPlanVotes(planId);
    }

    @Override
    public int countPlanVotes(com.ext.portlet.model.PlanType type)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.countPlanVotes(type);
    }

    @Override
    public int countPlanVotes(com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.countPlanVotes(contest);
    }

    @Override
    public int countPlanVotesByPlanId(java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planVoteLocalService.countPlanVotesByPlanId(planId);
    }

    @Override
    public void store(com.ext.portlet.model.PlanVote planVote)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planVoteLocalService.store(planVote);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanVoteLocalService getWrappedPlanVoteLocalService() {
        return _planVoteLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanVoteLocalService(
        PlanVoteLocalService planVoteLocalService) {
        _planVoteLocalService = planVoteLocalService;
    }

    @Override
    public PlanVoteLocalService getWrappedService() {
        return _planVoteLocalService;
    }

    @Override
    public void setWrappedService(PlanVoteLocalService planVoteLocalService) {
        _planVoteLocalService = planVoteLocalService;
    }
}
