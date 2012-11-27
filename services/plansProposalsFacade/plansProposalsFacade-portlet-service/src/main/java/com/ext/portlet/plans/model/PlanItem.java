package com.ext.portlet.plans.model;

import com.liferay.portal.model.PersistedModel;

/**
 * The extended model interface for the PlanItem service. Represents a row in the &quot;Plans_PlanItem&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see PlanItemModel
 * @see com.ext.portlet.plans.model.impl.PlanItemImpl
 * @see com.ext.portlet.plans.model.impl.PlanItemModelImpl
 * @generated
 */
public interface PlanItem extends PlanItemModel, PersistedModel {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this interface directly. Add methods to {@link com.ext.portlet.plans.model.impl.PlanItemImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */
    public java.lang.String getDescription()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.lang.String getName()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.lang.Long getImageId()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.lang.String getPitch()
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.liferay.portal.model.Image getImage()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setDescription(java.lang.String description,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setName(java.lang.String name, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setImage(java.lang.Long imageId, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setPitch(java.lang.String pitch, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException,
            com.liferay.portal.kernel.search.SearchException;

    public java.util.List<com.ext.portlet.plans.model.PlanDescription> getAllDescriptionVersions()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * List of all versions of PlanDescription objects related to given plan
    *
    * @see com.ext.portlet.plans.model.PlanItem#getPlanDescriptions()
    */
    public java.util.List<com.ext.portlet.plans.model.PlanDescription> getPlanDescriptions()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.lang.Long getScenarioId()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setScenarioId(java.lang.Long scenarioId, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setModelId(java.lang.Long simulationId, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanModelRun> getAllPlanModelRuns()
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.plans.model.PlanMeta getPlanMeta()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanMeta> getAllPlanMetas()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.lang.Long getPlanTypeId()
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.plans.model.PlanType getPlanType()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.contests.model.Contest getContest()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.contests.model.ContestPhase getContestPhase()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setContestPhase(
        com.ext.portlet.contests.model.ContestPhase phase,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setPlanTypeId(java.lang.Long planTypeId,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.lang.Long getMBCategoryId()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setMBCategoryId(java.lang.Long mbCategoryId,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.lang.Long getCategoryGroupId()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setCategoryGroupId(java.lang.Long categoryGroupId,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.lang.Long getPlanGroupId()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setPlanGroupId(java.lang.Long groupId,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.lang.Long getAuthorId()
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.liferay.portal.model.User getAuthor()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setAuthorId(java.lang.Long authorId,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.Date getCreateDate()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.Date getPublishDate()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.lang.String getCreator()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.lang.Integer getVotes()
        throws com.liferay.portal.kernel.exception.SystemException;

    public boolean getOpen()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setOpen(boolean open, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setOpen(boolean open)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.lang.String getStatus()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setStatus(java.lang.String status, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.plans.model.PlanPositions getPlanPositions()
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<java.lang.Long> getPositionsIds()
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.lang.Long[] getPositionsIdsArray()
        throws com.ext.portlet.plans.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setPositions(java.util.List<java.lang.Long> positionsIds,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanPositions> getAllPositionsVersions()
        throws com.liferay.portal.kernel.exception.SystemException;

    public boolean hasUserVoted(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void vote(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void unvote(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getAllVersions()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void store()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates values of all available attributes.
    *
    * @throws SystemException
    */
    public void updateAllAttributes()
        throws com.liferay.portal.kernel.exception.SystemException;

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
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributes()
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.plans.model.PlanAttribute getPlanAttribute(
        java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns list of plan members.
    */
    public java.util.List<com.liferay.portal.model.User> getMembers()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.liferay.portal.model.MembershipRequest> getMembershipRequests()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void addMembershipRequest(java.lang.Long userId,
        java.lang.String comments)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void dennyMembershipRequest(java.lang.Long userId,
        com.liferay.portal.model.MembershipRequest request,
        java.lang.String reply, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void approveMembershipRequest(java.lang.Long userId,
        com.liferay.portal.model.MembershipRequest request,
        java.lang.String reply, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void publish(java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void delete(java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.liferay.portal.model.User getUpdateAuthor()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanFan> getFans()
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.plans.model.PlanFan addFan(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void removeFan(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public boolean isUserAFan(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public boolean isUserAMember(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public boolean hasUserRequestedMembership(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    public boolean isAdmin(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public boolean isOwner(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setUserPermission(java.lang.Long userId,
        java.lang.String userPermission, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void removeMember(java.lang.Long userId,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void joinIfNotAMember(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setSeekingAssistance(boolean seekingAssistance)
        throws com.liferay.portal.kernel.exception.SystemException;

    public boolean isSeekingAssistance()
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.discussions.model.DiscussionCategoryGroup getDiscussionCategoryGroup()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.plans.model.PlanItem promote(
        com.liferay.portal.model.User user)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public boolean getPromoted()
        throws com.liferay.portal.kernel.exception.SystemException;

    public int getCommentsCount()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setPlace(int place)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void removePlace()
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanVote> getPlanVotes()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setRibbon(java.lang.Integer ribbon)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setRibbonText(java.lang.String ribbonText)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setAttribute(java.lang.String attributeName,
        java.lang.String value)
        throws com.liferay.portal.kernel.exception.SystemException;

    public void removeAttribute(java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.plans.model.PlanTemplate getPlanTemplate()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanSection> getPlanSections()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setSectionContent(
        com.ext.portlet.plans.model.PlanSectionDefinition psd,
        java.lang.String content,
        java.util.List<java.lang.Long> referencedPlans,
        java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.util.List<com.ext.portlet.plans.model.PlanSection> getAllPlanSections(
        com.ext.portlet.plans.model.PlanSectionDefinition psd)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.lang.Integer getRibbon()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setTeam(java.lang.String team)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.lang.String getTeam()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void revertTo(java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public java.lang.String getTags()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setTags(java.lang.String tags)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.lang.String getTagsHover()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setTagsHover(java.lang.String tagsHover)
        throws com.liferay.portal.kernel.exception.SystemException;

    public java.lang.Integer getTagsOrder()
        throws com.liferay.portal.kernel.exception.SystemException;

    public void setTagsOrder(int tagsOrder)
        throws com.liferay.portal.kernel.exception.SystemException;
}
