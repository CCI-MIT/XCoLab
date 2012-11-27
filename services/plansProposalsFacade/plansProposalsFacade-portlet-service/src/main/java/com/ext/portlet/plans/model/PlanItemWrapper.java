package com.ext.portlet.plans.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanItem}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanItem
 * @generated
 */
public class PlanItemWrapper implements PlanItem, ModelWrapper<PlanItem> {
    private PlanItem _planItem;

    public PlanItemWrapper(PlanItem planItem) {
        _planItem = planItem;
    }

    public Class<?> getModelClass() {
        return PlanItem.class;
    }

    public String getModelClassName() {
        return PlanItem.class.getName();
    }

    /**
    * Returns the primary key of this plan item.
    *
    * @return the primary key of this plan item
    */
    public java.lang.Long getPrimaryKey() {
        return _planItem.getPrimaryKey();
    }

    /**
    * Sets the primary key of this plan item.
    *
    * @param primaryKey the primary key of this plan item
    */
    public void setPrimaryKey(java.lang.Long primaryKey) {
        _planItem.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this plan item.
    *
    * @return the ID of this plan item
    */
    public java.lang.Long getId() {
        return _planItem.getId();
    }

    /**
    * Sets the ID of this plan item.
    *
    * @param id the ID of this plan item
    */
    public void setId(java.lang.Long id) {
        _planItem.setId(id);
    }

    /**
    * Returns the plan ID of this plan item.
    *
    * @return the plan ID of this plan item
    */
    public java.lang.Long getPlanId() {
        return _planItem.getPlanId();
    }

    /**
    * Sets the plan ID of this plan item.
    *
    * @param planId the plan ID of this plan item
    */
    public void setPlanId(java.lang.Long planId) {
        _planItem.setPlanId(planId);
    }

    /**
    * Returns the state of this plan item.
    *
    * @return the state of this plan item
    */
    public java.lang.String getState() {
        return _planItem.getState();
    }

    /**
    * Sets the state of this plan item.
    *
    * @param state the state of this plan item
    */
    public void setState(java.lang.String state) {
        _planItem.setState(state);
    }

    /**
    * Returns the updated of this plan item.
    *
    * @return the updated of this plan item
    */
    public java.util.Date getUpdated() {
        return _planItem.getUpdated();
    }

    /**
    * Sets the updated of this plan item.
    *
    * @param updated the updated of this plan item
    */
    public void setUpdated(java.util.Date updated) {
        _planItem.setUpdated(updated);
    }

    /**
    * Returns the update author ID of this plan item.
    *
    * @return the update author ID of this plan item
    */
    public java.lang.Long getUpdateAuthorId() {
        return _planItem.getUpdateAuthorId();
    }

    /**
    * Sets the update author ID of this plan item.
    *
    * @param updateAuthorId the update author ID of this plan item
    */
    public void setUpdateAuthorId(java.lang.Long updateAuthorId) {
        _planItem.setUpdateAuthorId(updateAuthorId);
    }

    /**
    * Returns the update type of this plan item.
    *
    * @return the update type of this plan item
    */
    public java.lang.String getUpdateType() {
        return _planItem.getUpdateType();
    }

    /**
    * Sets the update type of this plan item.
    *
    * @param updateType the update type of this plan item
    */
    public void setUpdateType(java.lang.String updateType) {
        _planItem.setUpdateType(updateType);
    }

    /**
    * Returns the version of this plan item.
    *
    * @return the version of this plan item
    */
    public java.lang.Long getVersion() {
        return _planItem.getVersion();
    }

    /**
    * Sets the version of this plan item.
    *
    * @param version the version of this plan item
    */
    public void setVersion(java.lang.Long version) {
        _planItem.setVersion(version);
    }

    public boolean isNew() {
        return _planItem.isNew();
    }

    public void setNew(boolean n) {
        _planItem.setNew(n);
    }

    public boolean isCachedModel() {
        return _planItem.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _planItem.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _planItem.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _planItem.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _planItem.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _planItem.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _planItem.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new PlanItemWrapper((PlanItem) _planItem.clone());
    }

    public int compareTo(PlanItem planItem) {
        return _planItem.compareTo(planItem);
    }

    @Override
    public int hashCode() {
        return _planItem.hashCode();
    }

    public com.liferay.portal.model.CacheModel<PlanItem> toCacheModel() {
        return _planItem.toCacheModel();
    }

    public PlanItem toEscapedModel() {
        return new PlanItemWrapper(_planItem.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _planItem.toString();
    }

    public java.lang.String toXmlString() {
        return _planItem.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.persist();
    }

    public java.lang.String getDescription()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getDescription();
    }

    public java.lang.String getName()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getName();
    }

    public java.lang.Long getImageId()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getImageId();
    }

    public java.lang.String getPitch()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getPitch();
    }

    public com.liferay.portal.model.Image getImage()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getImage();
    }

    public void setDescription(java.lang.String description,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItem.setDescription(description, updateAuthorId);
    }

    public void setName(java.lang.String name, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItem.setName(name, updateAuthorId);
    }

    public void setImage(java.lang.Long imageId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItem.setImage(imageId, updateAuthorId);
    }

    public void setPitch(java.lang.String pitch, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portal.kernel.search.SearchException {
        _planItem.setPitch(pitch, updateAuthorId);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanDescription> getAllDescriptionVersions()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getAllDescriptionVersions();
    }

    /**
    * List of all versions of PlanDescription objects related to given plan
    *
    * @see com.ext.portlet.plans.model.PlanItem#getPlanDescriptions()
    */
    public java.util.List<com.ext.portlet.plans.model.PlanDescription> getPlanDescriptions()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getPlanDescriptions();
    }

    public java.lang.Long getScenarioId()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getScenarioId();
    }

    public void setScenarioId(java.lang.Long scenarioId, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItem.setScenarioId(scenarioId, authorId);
    }

    public void setModelId(java.lang.Long simulationId, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItem.setModelId(simulationId, authorId);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanModelRun> getAllPlanModelRuns()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getAllPlanModelRuns();
    }

    public com.ext.portlet.plans.model.PlanMeta getPlanMeta()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getPlanMeta();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanMeta> getAllPlanMetas()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getAllPlanMetas();
    }

    public java.lang.Long getPlanTypeId()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getPlanTypeId();
    }

    public com.ext.portlet.plans.model.PlanType getPlanType()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getPlanType();
    }

    public com.ext.portlet.contests.model.Contest getContest()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getContest();
    }

    public com.ext.portlet.contests.model.ContestPhase getContestPhase()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getContestPhase();
    }

    public void setContestPhase(
        com.ext.portlet.contests.model.ContestPhase phase,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.setContestPhase(phase, updateAuthorId);
    }

    public void setPlanTypeId(java.lang.Long planTypeId,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItem.setPlanTypeId(planTypeId, updateAuthorId);
    }

    public java.lang.Long getMBCategoryId()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getMBCategoryId();
    }

    public void setMBCategoryId(java.lang.Long mbCategoryId,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.setMBCategoryId(mbCategoryId, updateAuthorId);
    }

    public java.lang.Long getCategoryGroupId()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getCategoryGroupId();
    }

    public void setCategoryGroupId(java.lang.Long categoryGroupId,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.setCategoryGroupId(categoryGroupId, updateAuthorId);
    }

    public java.lang.Long getPlanGroupId()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getPlanGroupId();
    }

    public void setPlanGroupId(java.lang.Long groupId,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.setPlanGroupId(groupId, updateAuthorId);
    }

    public java.lang.Long getAuthorId()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getAuthorId();
    }

    public com.liferay.portal.model.User getAuthor()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getAuthor();
    }

    public void setAuthorId(java.lang.Long authorId,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.setAuthorId(authorId, updateAuthorId);
    }

    public java.util.Date getCreateDate()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getCreateDate();
    }

    public java.util.Date getPublishDate()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getPublishDate();
    }

    public java.lang.String getCreator()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getCreator();
    }

    public java.lang.Integer getVotes()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getVotes();
    }

    public boolean getOpen()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getOpen();
    }

    public void setOpen(boolean open, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.setOpen(open, updateAuthorId);
    }

    public void setOpen(boolean open)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.setOpen(open);
    }

    public java.lang.String getStatus()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getStatus();
    }

    public void setStatus(java.lang.String status, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.setStatus(status, updateAuthorId);
    }

    public com.ext.portlet.plans.model.PlanPositions getPlanPositions()
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getPlanPositions();
    }

    public java.util.List<java.lang.Long> getPositionsIds()
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getPositionsIds();
    }

    public java.lang.Long[] getPositionsIdsArray()
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getPositionsIdsArray();
    }

    public void setPositions(java.util.List<java.lang.Long> positionsIds,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItem.setPositions(positionsIds, updateAuthorId);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanPositions> getAllPositionsVersions()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getAllPositionsVersions();
    }

    public boolean hasUserVoted(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItem.hasUserVoted(userId);
    }

    public void vote(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItem.vote(userId);
    }

    public void unvote(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItem.unvote(userId);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getAllVersions()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getAllVersions();
    }

    public void store()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.store();
    }

    /**
    * Updates values of all available attributes.
    *
    * @throws SystemException
    */
    public void updateAllAttributes()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.updateAllAttributes();
    }

    /**
    * Updates value of a given attribute, should be used only for property
    * attributes.
    *
    * @param attributeName
    attribute which value should be updated
    * @throws SystemException
    in case of any error
    */
    public void updateAttribute(java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.updateAttribute(attributeName);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributes()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getPlanAttributes();
    }

    public com.ext.portlet.plans.model.PlanAttribute getPlanAttribute(
        java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getPlanAttribute(name);
    }

    /**
    * Returns list of plan members.
    */
    public java.util.List<com.liferay.portal.model.User> getMembers()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getMembers();
    }

    public java.util.List<com.liferay.portal.model.MembershipRequest> getMembershipRequests()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getMembershipRequests();
    }

    public void addMembershipRequest(java.lang.Long userId,
        java.lang.String comments)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItem.addMembershipRequest(userId, comments);
    }

    public void dennyMembershipRequest(java.lang.Long userId,
        com.liferay.portal.model.MembershipRequest request,
        java.lang.String reply, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItem.dennyMembershipRequest(userId, request, reply, updateAuthorId);
    }

    public void approveMembershipRequest(java.lang.Long userId,
        com.liferay.portal.model.MembershipRequest request,
        java.lang.String reply, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItem.approveMembershipRequest(userId, request, reply,
            updateAuthorId);
    }

    public void publish(java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItem.publish(updateAuthorId);
    }

    public void delete(java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItem.delete(updateAuthorId);
    }

    public com.liferay.portal.model.User getUpdateAuthor()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getUpdateAuthor();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanFan> getFans()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getFans();
    }

    public com.ext.portlet.plans.model.PlanFan addFan(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.addFan(userId);
    }

    public void removeFan(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.removeFan(userId);
    }

    public boolean isUserAFan(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.isUserAFan(userId);
    }

    public boolean isUserAMember(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.isUserAMember(userId);
    }

    public boolean hasUserRequestedMembership(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.hasUserRequestedMembership(userId);
    }

    public boolean isAdmin(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItem.isAdmin(userId);
    }

    public boolean isOwner(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItem.isOwner(userId);
    }

    public void setUserPermission(java.lang.Long userId,
        java.lang.String userPermission, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItem.setUserPermission(userId, userPermission, updateAuthorId);
    }

    public void removeMember(java.lang.Long userId,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItem.removeMember(userId, updateAuthorId);
    }

    public void joinIfNotAMember(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItem.joinIfNotAMember(userId);
    }

    public void setSeekingAssistance(boolean seekingAssistance)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.setSeekingAssistance(seekingAssistance);
    }

    public boolean isSeekingAssistance()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.isSeekingAssistance();
    }

    public com.ext.portlet.discussions.model.DiscussionCategoryGroup getDiscussionCategoryGroup()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getDiscussionCategoryGroup();
    }

    public com.ext.portlet.plans.model.PlanItem promote(
        com.liferay.portal.model.User user)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItem.promote(user);
    }

    public boolean getPromoted()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getPromoted();
    }

    public int getCommentsCount()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getCommentsCount();
    }

    public void setPlace(int place)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.setPlace(place);
    }

    public void removePlace()
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.removePlace();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanVote> getPlanVotes()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getPlanVotes();
    }

    public void setRibbon(java.lang.Integer ribbon)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.setRibbon(ribbon);
    }

    public void setRibbonText(java.lang.String ribbonText)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.setRibbonText(ribbonText);
    }

    public void setAttribute(java.lang.String attributeName,
        java.lang.String value)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.setAttribute(attributeName, value);
    }

    public void removeAttribute(java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.removeAttribute(attributeName);
    }

    public com.ext.portlet.plans.model.PlanTemplate getPlanTemplate()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getPlanTemplate();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanSection> getPlanSections()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getPlanSections();
    }

    public void setSectionContent(
        com.ext.portlet.plans.model.PlanSectionDefinition psd,
        java.lang.String content,
        java.util.List<java.lang.Long> referencedPlans,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItem.setSectionContent(psd, content, referencedPlans,
            updateAuthorId);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanSection> getAllPlanSections(
        com.ext.portlet.plans.model.PlanSectionDefinition psd)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getAllPlanSections(psd);
    }

    public java.lang.Integer getRibbon()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getRibbon();
    }

    public void setTeam(java.lang.String team)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.setTeam(team);
    }

    public java.lang.String getTeam()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getTeam();
    }

    public void revertTo(java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItem.revertTo(updateAuthorId);
    }

    public java.lang.String getTags()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getTags();
    }

    public void setTags(java.lang.String tags)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.setTags(tags);
    }

    public java.lang.String getTagsHover()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getTagsHover();
    }

    public void setTagsHover(java.lang.String tagsHover)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.setTagsHover(tagsHover);
    }

    public java.lang.Integer getTagsOrder()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItem.getTagsOrder();
    }

    public void setTagsOrder(int tagsOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItem.setTagsOrder(tagsOrder);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public PlanItem getWrappedPlanItem() {
        return _planItem;
    }

    public PlanItem getWrappedModel() {
        return _planItem;
    }

    public void resetOriginalValues() {
        _planItem.resetOriginalValues();
    }
}
