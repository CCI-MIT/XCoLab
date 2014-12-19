package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link PlanVote}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanVote
 * @generated
 */
public class PlanVoteWrapper implements PlanVote, ModelWrapper<PlanVote> {
    private PlanVote _planVote;

    public PlanVoteWrapper(PlanVote planVote) {
        _planVote = planVote;
    }

    @Override
    public Class<?> getModelClass() {
        return PlanVote.class;
    }

    @Override
    public String getModelClassName() {
        return PlanVote.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("userId", getUserId());
        attributes.put("contestId", getContestId());
        attributes.put("planId", getPlanId());
        attributes.put("createDate", getCreateDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long userId = (Long) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        Long contestId = (Long) attributes.get("contestId");

        if (contestId != null) {
            setContestId(contestId);
        }

        Long planId = (Long) attributes.get("planId");

        if (planId != null) {
            setPlanId(planId);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }
    }

    /**
    * Returns the primary key of this plan vote.
    *
    * @return the primary key of this plan vote
    */
    @Override
    public com.ext.portlet.service.persistence.PlanVotePK getPrimaryKey() {
        return _planVote.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan vote.
    *
    * @param primaryKey the primary key of this plan vote
    */
    @Override
    public void setPrimaryKey(
        com.ext.portlet.service.persistence.PlanVotePK primaryKey) {
        _planVote.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the user ID of this plan vote.
    *
    * @return the user ID of this plan vote
    */
    @Override
    public long getUserId() {
        return _planVote.getUserId();
    }

    /**
    * Sets the user ID of this plan vote.
    *
    * @param userId the user ID of this plan vote
    */
    @Override
    public void setUserId(long userId) {
        _planVote.setUserId(userId);
    }

    /**
    * Returns the user uuid of this plan vote.
    *
    * @return the user uuid of this plan vote
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.lang.String getUserUuid()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planVote.getUserUuid();
    }

    /**
    * Sets the user uuid of this plan vote.
    *
    * @param userUuid the user uuid of this plan vote
    */
    @Override
    public void setUserUuid(java.lang.String userUuid) {
        _planVote.setUserUuid(userUuid);
    }

    /**
    * Returns the contest ID of this plan vote.
    *
    * @return the contest ID of this plan vote
    */
    @Override
    public long getContestId() {
        return _planVote.getContestId();
    }

    /**
    * Sets the contest ID of this plan vote.
    *
    * @param contestId the contest ID of this plan vote
    */
    @Override
    public void setContestId(long contestId) {
        _planVote.setContestId(contestId);
    }

    /**
    * Returns the plan ID of this plan vote.
    *
    * @return the plan ID of this plan vote
    */
    @Override
    public long getPlanId() {
        return _planVote.getPlanId();
    }

    /**
    * Sets the plan ID of this plan vote.
    *
    * @param planId the plan ID of this plan vote
    */
    @Override
    public void setPlanId(long planId) {
        _planVote.setPlanId(planId);
    }

    /**
    * Returns the create date of this plan vote.
    *
    * @return the create date of this plan vote
    */
    @Override
    public java.util.Date getCreateDate() {
        return _planVote.getCreateDate();
    }

    /**
    * Sets the create date of this plan vote.
    *
    * @param createDate the create date of this plan vote
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _planVote.setCreateDate(createDate);
    }

    @Override
    public boolean isNew() {
        return _planVote.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _planVote.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _planVote.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _planVote.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _planVote.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _planVote.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planVote.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planVote.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _planVote.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _planVote.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planVote.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanVoteWrapper((PlanVote) _planVote.clone());
    }

    @Override
    public int compareTo(PlanVote planVote) {
        return _planVote.compareTo(planVote);
    }

    @Override
    public int hashCode() {
        return _planVote.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<PlanVote> toCacheModel() {
        return _planVote.toCacheModel();
    }

    @Override
    public PlanVote toEscapedModel() {
        return new PlanVoteWrapper(_planVote.toEscapedModel());
    }

    @Override
    public PlanVote toUnescapedModel() {
        return new PlanVoteWrapper(_planVote.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planVote.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _planVote.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planVote.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PlanVoteWrapper)) {
            return false;
        }

        PlanVoteWrapper planVoteWrapper = (PlanVoteWrapper) obj;

        if (Validator.equals(_planVote, planVoteWrapper._planVote)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public PlanVote getWrappedPlanVote() {
        return _planVote;
    }

    @Override
    public PlanVote getWrappedModel() {
        return _planVote;
    }

    @Override
    public void resetOriginalValues() {
        _planVote.resetOriginalValues();
    }
}
