package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link Contest}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Contest
 * @generated
 */
public class ContestWrapper implements Contest, ModelWrapper<Contest> {
    private Contest _contest;

    public ContestWrapper(Contest contest) {
        _contest = contest;
    }

    public Class<?> getModelClass() {
        return Contest.class;
    }

    public String getModelClassName() {
        return Contest.class.getName();
    }

    /**
    * Returns the primary key of this contest.
    *
    * @return the primary key of this contest
    */
    public long getPrimaryKey() {
        return _contest.getPrimaryKey();
    }

    /**
    * Sets the primary key of this contest.
    *
    * @param primaryKey the primary key of this contest
    */
    public void setPrimaryKey(long primaryKey) {
        _contest.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the contest p k of this contest.
    *
    * @return the contest p k of this contest
    */
    public long getContestPK() {
        return _contest.getContestPK();
    }

    /**
    * Sets the contest p k of this contest.
    *
    * @param ContestPK the contest p k of this contest
    */
    public void setContestPK(long ContestPK) {
        _contest.setContestPK(ContestPK);
    }

    /**
    * Returns the contest name of this contest.
    *
    * @return the contest name of this contest
    */
    public java.lang.String getContestName() {
        return _contest.getContestName();
    }

    /**
    * Sets the contest name of this contest.
    *
    * @param ContestName the contest name of this contest
    */
    public void setContestName(java.lang.String ContestName) {
        _contest.setContestName(ContestName);
    }

    /**
    * Returns the contest short name of this contest.
    *
    * @return the contest short name of this contest
    */
    public java.lang.String getContestShortName() {
        return _contest.getContestShortName();
    }

    /**
    * Sets the contest short name of this contest.
    *
    * @param ContestShortName the contest short name of this contest
    */
    public void setContestShortName(java.lang.String ContestShortName) {
        _contest.setContestShortName(ContestShortName);
    }

    /**
    * Returns the contest description of this contest.
    *
    * @return the contest description of this contest
    */
    public java.lang.String getContestDescription() {
        return _contest.getContestDescription();
    }

    /**
    * Sets the contest description of this contest.
    *
    * @param ContestDescription the contest description of this contest
    */
    public void setContestDescription(java.lang.String ContestDescription) {
        _contest.setContestDescription(ContestDescription);
    }

    /**
    * Returns the contest model description of this contest.
    *
    * @return the contest model description of this contest
    */
    public java.lang.String getContestModelDescription() {
        return _contest.getContestModelDescription();
    }

    /**
    * Sets the contest model description of this contest.
    *
    * @param ContestModelDescription the contest model description of this contest
    */
    public void setContestModelDescription(
        java.lang.String ContestModelDescription) {
        _contest.setContestModelDescription(ContestModelDescription);
    }

    /**
    * Returns the contest positions description of this contest.
    *
    * @return the contest positions description of this contest
    */
    public java.lang.String getContestPositionsDescription() {
        return _contest.getContestPositionsDescription();
    }

    /**
    * Sets the contest positions description of this contest.
    *
    * @param ContestPositionsDescription the contest positions description of this contest
    */
    public void setContestPositionsDescription(
        java.lang.String ContestPositionsDescription) {
        _contest.setContestPositionsDescription(ContestPositionsDescription);
    }

    /**
    * Returns the default plan description of this contest.
    *
    * @return the default plan description of this contest
    */
    public java.lang.String getDefaultPlanDescription() {
        return _contest.getDefaultPlanDescription();
    }

    /**
    * Sets the default plan description of this contest.
    *
    * @param defaultPlanDescription the default plan description of this contest
    */
    public void setDefaultPlanDescription(
        java.lang.String defaultPlanDescription) {
        _contest.setDefaultPlanDescription(defaultPlanDescription);
    }

    /**
    * Returns the plan type ID of this contest.
    *
    * @return the plan type ID of this contest
    */
    public long getPlanTypeId() {
        return _contest.getPlanTypeId();
    }

    /**
    * Sets the plan type ID of this contest.
    *
    * @param PlanTypeId the plan type ID of this contest
    */
    public void setPlanTypeId(long PlanTypeId) {
        _contest.setPlanTypeId(PlanTypeId);
    }

    /**
    * Returns the created of this contest.
    *
    * @return the created of this contest
    */
    public java.util.Date getCreated() {
        return _contest.getCreated();
    }

    /**
    * Sets the created of this contest.
    *
    * @param created the created of this contest
    */
    public void setCreated(java.util.Date created) {
        _contest.setCreated(created);
    }

    /**
    * Returns the updated of this contest.
    *
    * @return the updated of this contest
    */
    public java.util.Date getUpdated() {
        return _contest.getUpdated();
    }

    /**
    * Sets the updated of this contest.
    *
    * @param updated the updated of this contest
    */
    public void setUpdated(java.util.Date updated) {
        _contest.setUpdated(updated);
    }

    /**
    * Returns the author ID of this contest.
    *
    * @return the author ID of this contest
    */
    public long getAuthorId() {
        return _contest.getAuthorId();
    }

    /**
    * Sets the author ID of this contest.
    *
    * @param authorId the author ID of this contest
    */
    public void setAuthorId(long authorId) {
        _contest.setAuthorId(authorId);
    }

    /**
    * Returns the contest active of this contest.
    *
    * @return the contest active of this contest
    */
    public boolean getContestActive() {
        return _contest.getContestActive();
    }

    /**
    * Returns <code>true</code> if this contest is contest active.
    *
    * @return <code>true</code> if this contest is contest active; <code>false</code> otherwise
    */
    public boolean isContestActive() {
        return _contest.isContestActive();
    }

    /**
    * Sets whether this contest is contest active.
    *
    * @param contestActive the contest active of this contest
    */
    public void setContestActive(boolean contestActive) {
        _contest.setContestActive(contestActive);
    }

    /**
    * Returns the plan template ID of this contest.
    *
    * @return the plan template ID of this contest
    */
    public long getPlanTemplateId() {
        return _contest.getPlanTemplateId();
    }

    /**
    * Sets the plan template ID of this contest.
    *
    * @param planTemplateId the plan template ID of this contest
    */
    public void setPlanTemplateId(long planTemplateId) {
        _contest.setPlanTemplateId(planTemplateId);
    }

    /**
    * Returns the focus area ID of this contest.
    *
    * @return the focus area ID of this contest
    */
    public long getFocusAreaId() {
        return _contest.getFocusAreaId();
    }

    /**
    * Sets the focus area ID of this contest.
    *
    * @param focusAreaId the focus area ID of this contest
    */
    public void setFocusAreaId(long focusAreaId) {
        _contest.setFocusAreaId(focusAreaId);
    }

    /**
    * Returns the contest logo ID of this contest.
    *
    * @return the contest logo ID of this contest
    */
    public long getContestLogoId() {
        return _contest.getContestLogoId();
    }

    /**
    * Sets the contest logo ID of this contest.
    *
    * @param contestLogoId the contest logo ID of this contest
    */
    public void setContestLogoId(long contestLogoId) {
        _contest.setContestLogoId(contestLogoId);
    }

    /**
    * Returns the featured of this contest.
    *
    * @return the featured of this contest
    */
    public boolean getFeatured() {
        return _contest.getFeatured();
    }

    /**
    * Returns <code>true</code> if this contest is featured.
    *
    * @return <code>true</code> if this contest is featured; <code>false</code> otherwise
    */
    public boolean isFeatured() {
        return _contest.isFeatured();
    }

    /**
    * Sets whether this contest is featured.
    *
    * @param featured the featured of this contest
    */
    public void setFeatured(boolean featured) {
        _contest.setFeatured(featured);
    }

    /**
    * Returns the plans open by default of this contest.
    *
    * @return the plans open by default of this contest
    */
    public boolean getPlansOpenByDefault() {
        return _contest.getPlansOpenByDefault();
    }

    /**
    * Returns <code>true</code> if this contest is plans open by default.
    *
    * @return <code>true</code> if this contest is plans open by default; <code>false</code> otherwise
    */
    public boolean isPlansOpenByDefault() {
        return _contest.isPlansOpenByDefault();
    }

    /**
    * Sets whether this contest is plans open by default.
    *
    * @param plansOpenByDefault the plans open by default of this contest
    */
    public void setPlansOpenByDefault(boolean plansOpenByDefault) {
        _contest.setPlansOpenByDefault(plansOpenByDefault);
    }

    /**
    * Returns the flag of this contest.
    *
    * @return the flag of this contest
    */
    public int getFlag() {
        return _contest.getFlag();
    }

    /**
    * Sets the flag of this contest.
    *
    * @param flag the flag of this contest
    */
    public void setFlag(int flag) {
        _contest.setFlag(flag);
    }

    /**
    * Returns the flag text of this contest.
    *
    * @return the flag text of this contest
    */
    public java.lang.String getFlagText() {
        return _contest.getFlagText();
    }

    /**
    * Sets the flag text of this contest.
    *
    * @param flagText the flag text of this contest
    */
    public void setFlagText(java.lang.String flagText) {
        _contest.setFlagText(flagText);
    }

    /**
    * Returns the flag tooltip of this contest.
    *
    * @return the flag tooltip of this contest
    */
    public java.lang.String getFlagTooltip() {
        return _contest.getFlagTooltip();
    }

    /**
    * Sets the flag tooltip of this contest.
    *
    * @param flagTooltip the flag tooltip of this contest
    */
    public void setFlagTooltip(java.lang.String flagTooltip) {
        _contest.setFlagTooltip(flagTooltip);
    }

    /**
    * Returns the group ID of this contest.
    *
    * @return the group ID of this contest
    */
    public long getGroupId() {
        return _contest.getGroupId();
    }

    /**
    * Sets the group ID of this contest.
    *
    * @param groupId the group ID of this contest
    */
    public void setGroupId(long groupId) {
        _contest.setGroupId(groupId);
    }

    /**
    * Returns the discussion group ID of this contest.
    *
    * @return the discussion group ID of this contest
    */
    public long getDiscussionGroupId() {
        return _contest.getDiscussionGroupId();
    }

    /**
    * Sets the discussion group ID of this contest.
    *
    * @param discussionGroupId the discussion group ID of this contest
    */
    public void setDiscussionGroupId(long discussionGroupId) {
        _contest.setDiscussionGroupId(discussionGroupId);
    }

    /**
    * Returns the weight of this contest.
    *
    * @return the weight of this contest
    */
    public int getWeight() {
        return _contest.getWeight();
    }

    /**
    * Sets the weight of this contest.
    *
    * @param weight the weight of this contest
    */
    public void setWeight(int weight) {
        _contest.setWeight(weight);
    }

    /**
    * Returns the resources url of this contest.
    *
    * @return the resources url of this contest
    */
    public java.lang.String getResourcesUrl() {
        return _contest.getResourcesUrl();
    }

    /**
    * Sets the resources url of this contest.
    *
    * @param resourcesUrl the resources url of this contest
    */
    public void setResourcesUrl(java.lang.String resourcesUrl) {
        _contest.setResourcesUrl(resourcesUrl);
    }

    public boolean isNew() {
        return _contest.isNew();
    }

    public void setNew(boolean n) {
        _contest.setNew(n);
    }

    public boolean isCachedModel() {
        return _contest.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _contest.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _contest.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _contest.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _contest.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _contest.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _contest.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ContestWrapper((Contest) _contest.clone());
    }

    public int compareTo(Contest contest) {
        return _contest.compareTo(contest);
    }

    @Override
    public int hashCode() {
        return _contest.hashCode();
    }

    public com.liferay.portal.model.CacheModel<Contest> toCacheModel() {
        return _contest.toCacheModel();
    }

    public Contest toEscapedModel() {
        return new ContestWrapper(_contest.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _contest.toString();
    }

    public java.lang.String toXmlString() {
        return _contest.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _contest.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public Contest getWrappedContest() {
        return _contest;
    }

    public Contest getWrappedModel() {
        return _contest;
    }

    public void resetOriginalValues() {
        _contest.resetOriginalValues();
    }
}
