package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanVote}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanVote
 * @generated
 */
public class PlanVoteWrapper implements PlanVote, ModelWrapper<PlanVote> {
    private PlanVote _planVote;

    public PlanVoteWrapper(PlanVote planVote) {
        _planVote = planVote;
    }

    public Class<?> getModelClass() {
        return PlanVote.class;
    }

    public String getModelClassName() {
        return PlanVote.class.getName();
    }

    /**
    * Returns the primary key of this plan vote.
    *
    * @return the primary key of this plan vote
    */
    public com.ext.portlet.service.persistence.PlanVotePK getPrimaryKey() {
        return _planVote.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan vote.
    *
    * @param primaryKey the primary key of this plan vote
    */
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.PlanVotePK primaryKey) {
        _planVote.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the user ID of this plan vote.
    *
    * @return the user ID of this plan vote
    */
    public long getUserId() {
        return _planVote.getUserId();
    }

    /**
    * Sets the user ID of this plan vote.
    *
    * @param userId the user ID of this plan vote
    */
    public void setUserId(long userId) {
        _planVote.setUserId(userId);
    }

    /**
    * Returns the user uuid of this plan vote.
    *
    * @return the user uuid of this plan vote
    * @throws SystemException if a system exception occurred
    */
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planVote.getUserUuid();
    }

    /**
    * Sets the user uuid of this plan vote.
    *
    * @param userUuid the user uuid of this plan vote
    */
    public void setUserUuid(java.lang.String userUuid) {
        _planVote.setUserUuid(userUuid);
    }

    /**
    * Returns the contest ID of this plan vote.
    *
    * @return the contest ID of this plan vote
    */
    public long getContestId() {
        return _planVote.getContestId();
    }

    /**
    * Sets the contest ID of this plan vote.
    *
    * @param contestId the contest ID of this plan vote
    */
    public void setContestId(long contestId) {
        _planVote.setContestId(contestId);
    }

    /**
    * Returns the plan ID of this plan vote.
    *
    * @return the plan ID of this plan vote
    */
    public long getPlanId() {
        return _planVote.getPlanId();
    }

    /**
    * Sets the plan ID of this plan vote.
    *
    * @param planId the plan ID of this plan vote
    */
    public void setPlanId(long planId) {
        _planVote.setPlanId(planId);
    }

    /**
    * Returns the create date of this plan vote.
    *
    * @return the create date of this plan vote
    */
    public java.util.Date getCreateDate() {
        return _planVote.getCreateDate();
    }

    /**
    * Sets the create date of this plan vote.
    *
    * @param createDate the create date of this plan vote
    */
    public void setCreateDate(java.util.Date createDate) {
        _planVote.setCreateDate(createDate);
    }

    public boolean isNew() {
        return _planVote.isNew();
    }

    public void setNew(boolean n) {
        _planVote.setNew(n);
    }

    public boolean isCachedModel() {
        return _planVote.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planVote.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planVote.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planVote.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planVote.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planVote.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planVote.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanVoteWrapper((PlanVote) _planVote.clone());
    }

    public int compareTo(PlanVote planVote) {
        return _planVote.compareTo(planVote);
    }

    @Override
    public int hashCode() {
        return _planVote.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanVote> toCacheModel() {
        return _planVote.toCacheModel();
    }

    public PlanVote toEscapedModel() {
        return new PlanVoteWrapper(_planVote.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planVote.toString();
    }

    public java.lang.String toXmlString() {
        return _planVote.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planVote.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanVote getWrappedPlanVote() {
        return _planVote;
    }

    public PlanVote getWrappedModel() {
        return _planVote;
    }

    public void resetOriginalValues() {
        _planVote.resetOriginalValues();
    }
}
