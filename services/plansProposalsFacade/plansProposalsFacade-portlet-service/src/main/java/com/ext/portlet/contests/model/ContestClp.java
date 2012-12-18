package com.ext.portlet.contests.model;

import com.ext.portlet.contests.service.ContestLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class ContestClp extends BaseModelImpl<Contest> implements Contest {
    private long _ContestPK;
    private String _ContestName;
    private String _ContestShortName;
    private String _ContestDescription;
    private String _ContestModelDescription;
    private String _ContestPositionsDescription;
    private String _defaultPlanDescription;
    private long _PlanTypeId;
    private Date _created;
    private Date _updated;
    private long _authorId;
    private boolean _contestActive;
    private long _planTemplateId;
    private long _focusAreaId;
    private long _contestLogoId;
    private boolean _featured;
    private boolean _plansOpenByDefault;
    private int _flag;
    private String _flagText;
    private long _groupId;
    private long _discussionGroupId;
    private int _weight;
    private String _resourcesUrl;

    public ContestClp() {
    }

    public Class<?> getModelClass() {
        return Contest.class;
    }

    public String getModelClassName() {
        return Contest.class.getName();
    }

    public long getPrimaryKey() {
        return _ContestPK;
    }

    public void setPrimaryKey(long primaryKey) {
        setContestPK(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_ContestPK);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getContestPK() {
        return _ContestPK;
    }

    public void setContestPK(long ContestPK) {
        _ContestPK = ContestPK;
    }

    public String getContestName() {
        return _ContestName;
    }

    public void setContestName(String ContestName) {
        _ContestName = ContestName;
    }

    public String getContestShortName() {
        return _ContestShortName;
    }

    public void setContestShortName(String ContestShortName) {
        _ContestShortName = ContestShortName;
    }

    public String getContestDescription() {
        return _ContestDescription;
    }

    public void setContestDescription(String ContestDescription) {
        _ContestDescription = ContestDescription;
    }

    public String getContestModelDescription() {
        return _ContestModelDescription;
    }

    public void setContestModelDescription(String ContestModelDescription) {
        _ContestModelDescription = ContestModelDescription;
    }

    public String getContestPositionsDescription() {
        return _ContestPositionsDescription;
    }

    public void setContestPositionsDescription(
        String ContestPositionsDescription) {
        _ContestPositionsDescription = ContestPositionsDescription;
    }

    public String getDefaultPlanDescription() {
        return _defaultPlanDescription;
    }

    public void setDefaultPlanDescription(String defaultPlanDescription) {
        _defaultPlanDescription = defaultPlanDescription;
    }

    public long getPlanTypeId() {
        return _PlanTypeId;
    }

    public void setPlanTypeId(long PlanTypeId) {
        _PlanTypeId = PlanTypeId;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public Date getUpdated() {
        return _updated;
    }

    public void setUpdated(Date updated) {
        _updated = updated;
    }

    public long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(long authorId) {
        _authorId = authorId;
    }

    public boolean getContestActive() {
        return _contestActive;
    }

    public boolean isContestActive() {
        return _contestActive;
    }

    public void setContestActive(boolean contestActive) {
        _contestActive = contestActive;
    }

    public long getPlanTemplateId() {
        return _planTemplateId;
    }

    public void setPlanTemplateId(long planTemplateId) {
        _planTemplateId = planTemplateId;
    }

    public long getFocusAreaId() {
        return _focusAreaId;
    }

    public void setFocusAreaId(long focusAreaId) {
        _focusAreaId = focusAreaId;
    }

    public long getContestLogoId() {
        return _contestLogoId;
    }

    public void setContestLogoId(long contestLogoId) {
        _contestLogoId = contestLogoId;
    }

    public boolean getFeatured() {
        return _featured;
    }

    public boolean isFeatured() {
        return _featured;
    }

    public void setFeatured(boolean featured) {
        _featured = featured;
    }

    public boolean getPlansOpenByDefault() {
        return _plansOpenByDefault;
    }

    public boolean isPlansOpenByDefault() {
        return _plansOpenByDefault;
    }

    public void setPlansOpenByDefault(boolean plansOpenByDefault) {
        _plansOpenByDefault = plansOpenByDefault;
    }

    public int getFlag() {
        return _flag;
    }

    public void setFlag(int flag) {
        _flag = flag;
    }

    public String getFlagText() {
        return _flagText;
    }

    public void setFlagText(String flagText) {
        _flagText = flagText;
    }

    public long getGroupId() {
        return _groupId;
    }

    public void setGroupId(long groupId) {
        _groupId = groupId;
    }

    public long getDiscussionGroupId() {
        return _discussionGroupId;
    }

    public void setDiscussionGroupId(long discussionGroupId) {
        _discussionGroupId = discussionGroupId;
    }

    public int getWeight() {
        return _weight;
    }

    public void setWeight(int weight) {
        _weight = weight;
    }

    public String getResourcesUrl() {
        return _resourcesUrl;
    }

    public void setResourcesUrl(String resourcesUrl) {
        _resourcesUrl = resourcesUrl;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ContestLocalServiceUtil.addContest(this);
        } else {
            ContestLocalServiceUtil.updateContest(this);
        }
    }

    @Override
    public Contest toEscapedModel() {
        return (Contest) Proxy.newProxyInstance(Contest.class.getClassLoader(),
            new Class[] { Contest.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ContestClp clone = new ContestClp();

        clone.setContestPK(getContestPK());
        clone.setContestName(getContestName());
        clone.setContestShortName(getContestShortName());
        clone.setContestDescription(getContestDescription());
        clone.setContestModelDescription(getContestModelDescription());
        clone.setContestPositionsDescription(getContestPositionsDescription());
        clone.setDefaultPlanDescription(getDefaultPlanDescription());
        clone.setPlanTypeId(getPlanTypeId());
        clone.setCreated(getCreated());
        clone.setUpdated(getUpdated());
        clone.setAuthorId(getAuthorId());
        clone.setContestActive(getContestActive());
        clone.setPlanTemplateId(getPlanTemplateId());
        clone.setFocusAreaId(getFocusAreaId());
        clone.setContestLogoId(getContestLogoId());
        clone.setFeatured(getFeatured());
        clone.setPlansOpenByDefault(getPlansOpenByDefault());
        clone.setFlag(getFlag());
        clone.setFlagText(getFlagText());
        clone.setGroupId(getGroupId());
        clone.setDiscussionGroupId(getDiscussionGroupId());
        clone.setWeight(getWeight());
        clone.setResourcesUrl(getResourcesUrl());

        return clone;
    }

    public int compareTo(Contest contest) {
        int value = 0;

        if (getWeight() < contest.getWeight()) {
            value = -1;
        } else if (getWeight() > contest.getWeight()) {
            value = 1;
        } else {
            value = 0;
        }

        if (value != 0) {
            return value;
        }

        value = DateUtil.compareTo(getCreated(), contest.getCreated());

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

        ContestClp contest = null;

        try {
            contest = (ContestClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = contest.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (int) getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(47);

        sb.append("{ContestPK=");
        sb.append(getContestPK());
        sb.append(", ContestName=");
        sb.append(getContestName());
        sb.append(", ContestShortName=");
        sb.append(getContestShortName());
        sb.append(", ContestDescription=");
        sb.append(getContestDescription());
        sb.append(", ContestModelDescription=");
        sb.append(getContestModelDescription());
        sb.append(", ContestPositionsDescription=");
        sb.append(getContestPositionsDescription());
        sb.append(", defaultPlanDescription=");
        sb.append(getDefaultPlanDescription());
        sb.append(", PlanTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append(", updated=");
        sb.append(getUpdated());
        sb.append(", authorId=");
        sb.append(getAuthorId());
        sb.append(", contestActive=");
        sb.append(getContestActive());
        sb.append(", planTemplateId=");
        sb.append(getPlanTemplateId());
        sb.append(", focusAreaId=");
        sb.append(getFocusAreaId());
        sb.append(", contestLogoId=");
        sb.append(getContestLogoId());
        sb.append(", featured=");
        sb.append(getFeatured());
        sb.append(", plansOpenByDefault=");
        sb.append(getPlansOpenByDefault());
        sb.append(", flag=");
        sb.append(getFlag());
        sb.append(", flagText=");
        sb.append(getFlagText());
        sb.append(", groupId=");
        sb.append(getGroupId());
        sb.append(", discussionGroupId=");
        sb.append(getDiscussionGroupId());
        sb.append(", weight=");
        sb.append(getWeight());
        sb.append(", resourcesUrl=");
        sb.append(getResourcesUrl());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(73);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.contests.model.Contest");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>ContestPK</column-name><column-value><![CDATA[");
        sb.append(getContestPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestName</column-name><column-value><![CDATA[");
        sb.append(getContestName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestShortName</column-name><column-value><![CDATA[");
        sb.append(getContestShortName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestDescription</column-name><column-value><![CDATA[");
        sb.append(getContestDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestModelDescription</column-name><column-value><![CDATA[");
        sb.append(getContestModelDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ContestPositionsDescription</column-name><column-value><![CDATA[");
        sb.append(getContestPositionsDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>defaultPlanDescription</column-name><column-value><![CDATA[");
        sb.append(getDefaultPlanDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>PlanTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updated</column-name><column-value><![CDATA[");
        sb.append(getUpdated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorId</column-name><column-value><![CDATA[");
        sb.append(getAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contestActive</column-name><column-value><![CDATA[");
        sb.append(getContestActive());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planTemplateId</column-name><column-value><![CDATA[");
        sb.append(getPlanTemplateId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>focusAreaId</column-name><column-value><![CDATA[");
        sb.append(getFocusAreaId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contestLogoId</column-name><column-value><![CDATA[");
        sb.append(getContestLogoId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>featured</column-name><column-value><![CDATA[");
        sb.append(getFeatured());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>plansOpenByDefault</column-name><column-value><![CDATA[");
        sb.append(getPlansOpenByDefault());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>flag</column-name><column-value><![CDATA[");
        sb.append(getFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>flagText</column-name><column-value><![CDATA[");
        sb.append(getFlagText());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>groupId</column-name><column-value><![CDATA[");
        sb.append(getGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>discussionGroupId</column-name><column-value><![CDATA[");
        sb.append(getDiscussionGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>weight</column-name><column-value><![CDATA[");
        sb.append(getWeight());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>resourcesUrl</column-name><column-value><![CDATA[");
        sb.append(getResourcesUrl());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
