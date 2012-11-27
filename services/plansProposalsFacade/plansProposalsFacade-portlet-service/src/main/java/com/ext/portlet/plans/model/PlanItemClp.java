package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class PlanItemClp extends BaseModelImpl<PlanItem> implements PlanItem {
    private Long _id;
    private Long _planId;
    private String _state;
    private Date _updated;
    private Long _updateAuthorId;
    private String _updateType;
    private Long _version;

    public PlanItemClp() {
    }

    public Class<?> getModelClass() {
        return PlanItem.class;
    }

    public String getModelClassName() {
        return PlanItem.class.getName();
    }

    public Long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(Long primaryKey) {
        setId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_id);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long id) {
        _id = id;
    }

    public Long getPlanId() {
        return _planId;
    }

    public void setPlanId(Long planId) {
        _planId = planId;
    }

    public String getState() {
        return _state;
    }

    public void setState(String state) {
        _state = state;
    }

    public Date getUpdated() {
        return _updated;
    }

    public void setUpdated(Date updated) {
        _updated = updated;
    }

    public Long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    public void setUpdateAuthorId(Long updateAuthorId) {
        _updateAuthorId = updateAuthorId;
    }

    public String getUpdateType() {
        return _updateType;
    }

    public void setUpdateType(String updateType) {
        _updateType = updateType;
    }

    public Long getVersion() {
        return _version;
    }

    public void setVersion(Long version) {
        _version = version;
    }

    public java.lang.String getDescription() {
        throw new UnsupportedOperationException();
    }

    public java.lang.String getName() {
        throw new UnsupportedOperationException();
    }

    public java.lang.Long getImageId() {
        throw new UnsupportedOperationException();
    }

    public java.lang.String getPitch() {
        throw new UnsupportedOperationException();
    }

    public com.liferay.portal.model.Image getImage() {
        throw new UnsupportedOperationException();
    }

    public void setDescription(java.lang.String description,
        java.lang.Long updateAuthorId) {
        throw new UnsupportedOperationException();
    }

    public void setName(java.lang.String name, java.lang.Long updateAuthorId) {
        throw new UnsupportedOperationException();
    }

    public void setImage(java.lang.Long imageId, java.lang.Long updateAuthorId) {
        throw new UnsupportedOperationException();
    }

    public void setPitch(java.lang.String pitch, java.lang.Long updateAuthorId) {
        throw new UnsupportedOperationException();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanDescription> getAllDescriptionVersions() {
        throw new UnsupportedOperationException();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanDescription> getPlanDescriptions() {
        throw new UnsupportedOperationException();
    }

    public java.lang.Long getScenarioId() {
        throw new UnsupportedOperationException();
    }

    public void setScenarioId(java.lang.Long scenarioId, java.lang.Long authorId) {
        throw new UnsupportedOperationException();
    }

    public void setModelId(java.lang.Long simulationId, java.lang.Long authorId) {
        throw new UnsupportedOperationException();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanModelRun> getAllPlanModelRuns() {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.plans.model.PlanMeta getPlanMeta() {
        throw new UnsupportedOperationException();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanMeta> getAllPlanMetas() {
        throw new UnsupportedOperationException();
    }

    public java.lang.Long getPlanTypeId() {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.plans.model.PlanType getPlanType() {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.contests.model.Contest getContest() {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.contests.model.ContestPhase getContestPhase() {
        throw new UnsupportedOperationException();
    }

    public void setContestPhase(
        com.ext.portlet.contests.model.ContestPhase phase,
        java.lang.Long updateAuthorId) {
        throw new UnsupportedOperationException();
    }

    public void setPlanTypeId(java.lang.Long planTypeId,
        java.lang.Long updateAuthorId) {
        throw new UnsupportedOperationException();
    }

    public java.lang.Long getMBCategoryId() {
        throw new UnsupportedOperationException();
    }

    public void setMBCategoryId(java.lang.Long mbCategoryId,
        java.lang.Long updateAuthorId) {
        throw new UnsupportedOperationException();
    }

    public java.lang.Long getCategoryGroupId() {
        throw new UnsupportedOperationException();
    }

    public void setCategoryGroupId(java.lang.Long categoryGroupId,
        java.lang.Long updateAuthorId) {
        throw new UnsupportedOperationException();
    }

    public java.lang.Long getPlanGroupId() {
        throw new UnsupportedOperationException();
    }

    public void setPlanGroupId(java.lang.Long groupId,
        java.lang.Long updateAuthorId) {
        throw new UnsupportedOperationException();
    }

    public java.lang.Long getAuthorId() {
        throw new UnsupportedOperationException();
    }

    public com.liferay.portal.model.User getAuthor() {
        throw new UnsupportedOperationException();
    }

    public void setAuthorId(java.lang.Long authorId,
        java.lang.Long updateAuthorId) {
        throw new UnsupportedOperationException();
    }

    public java.util.Date getCreateDate() {
        throw new UnsupportedOperationException();
    }

    public java.util.Date getPublishDate() {
        throw new UnsupportedOperationException();
    }

    public java.lang.String getCreator() {
        throw new UnsupportedOperationException();
    }

    public java.lang.Integer getVotes() {
        throw new UnsupportedOperationException();
    }

    public boolean getOpen() {
        throw new UnsupportedOperationException();
    }

    public void setOpen(boolean open, java.lang.Long updateAuthorId) {
        throw new UnsupportedOperationException();
    }

    public void setOpen(boolean open) {
        throw new UnsupportedOperationException();
    }

    public java.lang.String getStatus() {
        throw new UnsupportedOperationException();
    }

    public void setStatus(java.lang.String status, java.lang.Long updateAuthorId) {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.plans.model.PlanPositions getPlanPositions() {
        throw new UnsupportedOperationException();
    }

    public java.util.List<java.lang.Long> getPositionsIds() {
        throw new UnsupportedOperationException();
    }

    public java.lang.Long[] getPositionsIdsArray() {
        throw new UnsupportedOperationException();
    }

    public void setPositions(java.util.List<java.lang.Long> positionsIds,
        java.lang.Long updateAuthorId) {
        throw new UnsupportedOperationException();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanPositions> getAllPositionsVersions() {
        throw new UnsupportedOperationException();
    }

    public boolean hasUserVoted(java.lang.Long userId) {
        throw new UnsupportedOperationException();
    }

    public void vote(java.lang.Long userId) {
        throw new UnsupportedOperationException();
    }

    public void unvote(java.lang.Long userId) {
        throw new UnsupportedOperationException();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getAllVersions() {
        throw new UnsupportedOperationException();
    }

    public void store() {
        throw new UnsupportedOperationException();
    }

    public void updateAllAttributes() {
        throw new UnsupportedOperationException();
    }

    public void updateAttribute(java.lang.String attributeName) {
        throw new UnsupportedOperationException();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributes() {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.plans.model.PlanAttribute getPlanAttribute(
        java.lang.String name) {
        throw new UnsupportedOperationException();
    }

    public java.util.List<com.liferay.portal.model.User> getMembers() {
        throw new UnsupportedOperationException();
    }

    public java.util.List<com.liferay.portal.model.MembershipRequest> getMembershipRequests() {
        throw new UnsupportedOperationException();
    }

    public void addMembershipRequest(java.lang.Long userId,
        java.lang.String comments) {
        throw new UnsupportedOperationException();
    }

    public void dennyMembershipRequest(java.lang.Long userId,
        com.liferay.portal.model.MembershipRequest request,
        java.lang.String reply, java.lang.Long updateAuthorId) {
        throw new UnsupportedOperationException();
    }

    public void approveMembershipRequest(java.lang.Long userId,
        com.liferay.portal.model.MembershipRequest request,
        java.lang.String reply, java.lang.Long updateAuthorId) {
        throw new UnsupportedOperationException();
    }

    public void publish(java.lang.Long updateAuthorId) {
        throw new UnsupportedOperationException();
    }

    public void delete(java.lang.Long updateAuthorId) {
        throw new UnsupportedOperationException();
    }

    public com.liferay.portal.model.User getUpdateAuthor() {
        throw new UnsupportedOperationException();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanFan> getFans() {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.plans.model.PlanFan addFan(java.lang.Long userId) {
        throw new UnsupportedOperationException();
    }

    public void removeFan(java.lang.Long userId) {
        throw new UnsupportedOperationException();
    }

    public boolean isUserAFan(java.lang.Long userId) {
        throw new UnsupportedOperationException();
    }

    public boolean isUserAMember(java.lang.Long userId) {
        throw new UnsupportedOperationException();
    }

    public boolean hasUserRequestedMembership(java.lang.Long userId) {
        throw new UnsupportedOperationException();
    }

    public boolean isAdmin(java.lang.Long userId) {
        throw new UnsupportedOperationException();
    }

    public boolean isOwner(java.lang.Long userId) {
        throw new UnsupportedOperationException();
    }

    public void setUserPermission(java.lang.Long userId,
        java.lang.String userPermission, java.lang.Long updateAuthorId) {
        throw new UnsupportedOperationException();
    }

    public void removeMember(java.lang.Long userId,
        java.lang.Long updateAuthorId) {
        throw new UnsupportedOperationException();
    }

    public void joinIfNotAMember(java.lang.Long userId) {
        throw new UnsupportedOperationException();
    }

    public void setSeekingAssistance(boolean seekingAssistance) {
        throw new UnsupportedOperationException();
    }

    public boolean isSeekingAssistance() {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.discussions.model.DiscussionCategoryGroup getDiscussionCategoryGroup() {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.plans.model.PlanItem promote(
        com.liferay.portal.model.User user) {
        throw new UnsupportedOperationException();
    }

    public boolean getPromoted() {
        throw new UnsupportedOperationException();
    }

    public int getCommentsCount() {
        throw new UnsupportedOperationException();
    }

    public void setPlace(int place) {
        throw new UnsupportedOperationException();
    }

    public void removePlace() {
        throw new UnsupportedOperationException();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanVote> getPlanVotes() {
        throw new UnsupportedOperationException();
    }

    public void setRibbon(java.lang.Integer ribbon) {
        throw new UnsupportedOperationException();
    }

    public void setRibbonText(java.lang.String ribbonText) {
        throw new UnsupportedOperationException();
    }

    public void setAttribute(java.lang.String attributeName,
        java.lang.String value) {
        throw new UnsupportedOperationException();
    }

    public void removeAttribute(java.lang.String attributeName) {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.plans.model.PlanTemplate getPlanTemplate() {
        throw new UnsupportedOperationException();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanSection> getPlanSections() {
        throw new UnsupportedOperationException();
    }

    public void setSectionContent(
        com.ext.portlet.plans.model.PlanSectionDefinition psd,
        java.lang.String content,
        java.util.List<java.lang.Long> referencedPlans,
        java.lang.Long updateAuthorId) {
        throw new UnsupportedOperationException();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanSection> getAllPlanSections(
        com.ext.portlet.plans.model.PlanSectionDefinition psd) {
        throw new UnsupportedOperationException();
    }

    public java.lang.Integer getRibbon() {
        throw new UnsupportedOperationException();
    }

    public void setTeam(java.lang.String team) {
        throw new UnsupportedOperationException();
    }

    public java.lang.String getTeam() {
        throw new UnsupportedOperationException();
    }

    public void revertTo(java.lang.Long updateAuthorId) {
        throw new UnsupportedOperationException();
    }

    public java.lang.String getTags() {
        throw new UnsupportedOperationException();
    }

    public void setTags(java.lang.String tags) {
        throw new UnsupportedOperationException();
    }

    public java.lang.String getTagsHover() {
        throw new UnsupportedOperationException();
    }

    public void setTagsHover(java.lang.String tagsHover) {
        throw new UnsupportedOperationException();
    }

    public java.lang.Integer getTagsOrder() {
        throw new UnsupportedOperationException();
    }

    public void setTagsOrder(int tagsOrder) {
        throw new UnsupportedOperationException();
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanItemLocalServiceUtil.addPlanItem(this);
        } else {
            PlanItemLocalServiceUtil.updatePlanItem(this);
        }
    }

    @Override
    public PlanItem toEscapedModel() {
        return (PlanItem) Proxy.newProxyInstance(PlanItem.class.getClassLoader(),
            new Class[] { PlanItem.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanItemClp clone = new PlanItemClp();

        clone.setId(getId());
        clone.setPlanId(getPlanId());
        clone.setState(getState());
        clone.setUpdated(getUpdated());
        clone.setUpdateAuthorId(getUpdateAuthorId());
        clone.setUpdateType(getUpdateType());
        clone.setVersion(getVersion());

        return clone;
    }

    public int compareTo(PlanItem planItem) {
        int value = 0;

        if (getId() < planItem.getId()) {
            value = -1;
        } else if (getId() > planItem.getId()) {
            value = 1;
        } else {
            value = 0;
        }

        value = value * -1;

        if (value != 0) {
            return value;
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanItemClp planItem = null;

        try {
            planItem = (PlanItemClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long primaryKey = planItem.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey().hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(15);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", planId=");
        sb.append(getPlanId());
        sb.append(", state=");
        sb.append(getState());
        sb.append(", updated=");
        sb.append(getUpdated());
        sb.append(", updateAuthorId=");
        sb.append(getUpdateAuthorId());
        sb.append(", updateType=");
        sb.append(getUpdateType());
        sb.append(", version=");
        sb.append(getVersion());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanItem");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>state</column-name><column-value><![CDATA[");
        sb.append(getState());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updated</column-name><column-value><![CDATA[");
        sb.append(getUpdated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updateAuthorId</column-name><column-value><![CDATA[");
        sb.append(getUpdateAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updateType</column-name><column-value><![CDATA[");
        sb.append(getUpdateType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>version</column-name><column-value><![CDATA[");
        sb.append(getVersion());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
