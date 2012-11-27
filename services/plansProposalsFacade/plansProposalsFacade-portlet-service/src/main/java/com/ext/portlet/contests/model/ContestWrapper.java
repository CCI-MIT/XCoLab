package com.ext.portlet.contests.model;

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
    public java.lang.Long getPrimaryKey() {
        return _contest.getPrimaryKey();
    }

    /**
    * Sets the primary key of this contest.
    *
    * @param primaryKey the primary key of this contest
    */
    public void setPrimaryKey(java.lang.Long primaryKey) {
        _contest.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the contest p k of this contest.
    *
    * @return the contest p k of this contest
    */
    public java.lang.Long getContestPK() {
        return _contest.getContestPK();
    }

    /**
    * Sets the contest p k of this contest.
    *
    * @param ContestPK the contest p k of this contest
    */
    public void setContestPK(java.lang.Long ContestPK) {
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
    public java.lang.Long getPlanTypeId() {
        return _contest.getPlanTypeId();
    }

    /**
    * Sets the plan type ID of this contest.
    *
    * @param PlanTypeId the plan type ID of this contest
    */
    public void setPlanTypeId(java.lang.Long PlanTypeId) {
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
    public java.lang.Long getAuthorId() {
        return _contest.getAuthorId();
    }

    /**
    * Sets the author ID of this contest.
    *
    * @param authorId the author ID of this contest
    */
    public void setAuthorId(java.lang.Long authorId) {
        _contest.setAuthorId(authorId);
    }

    /**
    * Returns the contest active of this contest.
    *
    * @return the contest active of this contest
    */
    public java.lang.Boolean getContestActive() {
        return _contest.getContestActive();
    }

    /**
    * Sets the contest active of this contest.
    *
    * @param contestActive the contest active of this contest
    */
    public void setContestActive(java.lang.Boolean contestActive) {
        _contest.setContestActive(contestActive);
    }

    /**
    * Returns the plan template ID of this contest.
    *
    * @return the plan template ID of this contest
    */
    public java.lang.Long getPlanTemplateId() {
        return _contest.getPlanTemplateId();
    }

    /**
    * Sets the plan template ID of this contest.
    *
    * @param planTemplateId the plan template ID of this contest
    */
    public void setPlanTemplateId(java.lang.Long planTemplateId) {
        _contest.setPlanTemplateId(planTemplateId);
    }

    /**
    * Returns the focus area ID of this contest.
    *
    * @return the focus area ID of this contest
    */
    public java.lang.Long getFocusAreaId() {
        return _contest.getFocusAreaId();
    }

    /**
    * Sets the focus area ID of this contest.
    *
    * @param focusAreaId the focus area ID of this contest
    */
    public void setFocusAreaId(java.lang.Long focusAreaId) {
        _contest.setFocusAreaId(focusAreaId);
    }

    /**
    * Returns the contest logo ID of this contest.
    *
    * @return the contest logo ID of this contest
    */
    public java.lang.Long getContestLogoId() {
        return _contest.getContestLogoId();
    }

    /**
    * Sets the contest logo ID of this contest.
    *
    * @param contestLogoId the contest logo ID of this contest
    */
    public void setContestLogoId(java.lang.Long contestLogoId) {
        _contest.setContestLogoId(contestLogoId);
    }

    /**
    * Returns the featured of this contest.
    *
    * @return the featured of this contest
    */
    public java.lang.Boolean getFeatured() {
        return _contest.getFeatured();
    }

    /**
    * Sets the featured of this contest.
    *
    * @param featured the featured of this contest
    */
    public void setFeatured(java.lang.Boolean featured) {
        _contest.setFeatured(featured);
    }

    /**
    * Returns the plans open by default of this contest.
    *
    * @return the plans open by default of this contest
    */
    public java.lang.Boolean getPlansOpenByDefault() {
        return _contest.getPlansOpenByDefault();
    }

    /**
    * Sets the plans open by default of this contest.
    *
    * @param plansOpenByDefault the plans open by default of this contest
    */
    public void setPlansOpenByDefault(java.lang.Boolean plansOpenByDefault) {
        _contest.setPlansOpenByDefault(plansOpenByDefault);
    }

    /**
    * Returns the flag of this contest.
    *
    * @return the flag of this contest
    */
    public java.lang.Integer getFlag() {
        return _contest.getFlag();
    }

    /**
    * Sets the flag of this contest.
    *
    * @param flag the flag of this contest
    */
    public void setFlag(java.lang.Integer flag) {
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
    * Returns the group ID of this contest.
    *
    * @return the group ID of this contest
    */
    public java.lang.Long getGroupId() {
        return _contest.getGroupId();
    }

    /**
    * Sets the group ID of this contest.
    *
    * @param groupId the group ID of this contest
    */
    public void setGroupId(java.lang.Long groupId) {
        _contest.setGroupId(groupId);
    }

    /**
    * Returns the discussion group ID of this contest.
    *
    * @return the discussion group ID of this contest
    */
    public java.lang.Long getDiscussionGroupId() {
        return _contest.getDiscussionGroupId();
    }

    /**
    * Sets the discussion group ID of this contest.
    *
    * @param discussionGroupId the discussion group ID of this contest
    */
    public void setDiscussionGroupId(java.lang.Long discussionGroupId) {
        _contest.setDiscussionGroupId(discussionGroupId);
    }

    /**
    * Returns the weight of this contest.
    *
    * @return the weight of this contest
    */
    public java.lang.Integer getWeight() {
        return _contest.getWeight();
    }

    /**
    * Sets the weight of this contest.
    *
    * @param weight the weight of this contest
    */
    public void setWeight(java.lang.Integer weight) {
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

    public java.util.List<com.ext.portlet.contests.model.ContestPhase> getPhases()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contest.getPhases();
    }

    public com.ext.portlet.plans.model.PlanType getPlanType()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contest.getPlanType();
    }

    public java.util.List<com.ext.portlet.contests.model.ContestPhase> getActivePhases()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contest.getActivePhases();
    }

    public com.ext.portlet.contests.model.ContestPhase getActivePhase()
        throws com.ext.portlet.contests.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contest.getActivePhase();
    }

    public boolean isActive()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contest.isActive();
    }

    public java.util.List<java.lang.Long> getDebatesIds()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contest.getDebatesIds();
    }

    public java.lang.Integer getTotalVotes()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contest.getTotalVotes();
    }

    public void updateDefaultPlanDescription(java.lang.String description)
        throws com.liferay.portal.kernel.exception.SystemException {
        _contest.updateDefaultPlanDescription(description);
    }

    public void store()
        throws com.liferay.portal.kernel.exception.SystemException {
        _contest.store();
    }

    public com.ext.portlet.plans.model.PlanTemplate getPlanTemplate()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contest.getPlanTemplate();
    }

    public com.ext.portlet.ontology.model.FocusArea getFocusArea()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contest.getFocusArea();
    }

    public com.liferay.portal.model.Image getLogo()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contest.getLogo();
    }

    public void setLogo(java.io.File logoFile)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        _contest.setLogo(logoFile);
    }

    public java.lang.String getLogoPath()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contest.getLogoPath();
    }

    public long getProposalsCount()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contest.getProposalsCount();
    }

    public com.ext.portlet.discussions.model.DiscussionCategoryGroup getDiscussionCategoryGroup()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contest.getDiscussionCategoryGroup();
    }

    public long getCommentsCount()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contest.getCommentsCount();
    }

    public long getProposalsCommentsCount()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contest.getProposalsCommentsCount();
    }

    public long getTotalComments()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _contest.getTotalComments();
    }

    public java.util.List<com.ext.portlet.contests.model.ContestTeamMember> getTeamMembers()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _contest.getTeamMembers();
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
