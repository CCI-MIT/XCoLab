package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.PlanMetaLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class PlanMetaClp extends BaseModelImpl<PlanMeta> implements PlanMeta {
    private Long _id;
    private Long _planId;
    private Long _planTypeId;
    private Long _planCreated;
    private Long _authorId;
    private Integer _votes;
    private Long _planGroupId;
    private Boolean _open;
    private String _status;
    private Long _mbCategoryId;
    private Long _categoryGroupId;
    private Long _version;
    private Long _planVersion;
    private Date _created;
    private Long _updateAuthorId;
    private Long _modelId;
    private Boolean _promoted;
    private Long _previousContestPhase;
    private Long _contestPhase;

    public PlanMetaClp() {
    }

    public Class<?> getModelClass() {
        return PlanMeta.class;
    }

    public String getModelClassName() {
        return PlanMeta.class.getName();
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

    public Long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(Long planTypeId) {
        _planTypeId = planTypeId;
    }

    public Long getPlanCreated() {
        return _planCreated;
    }

    public void setPlanCreated(Long planCreated) {
        _planCreated = planCreated;
    }

    public Long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(Long authorId) {
        _authorId = authorId;
    }

    public Integer getVotes() {
        return _votes;
    }

    public void setVotes(Integer votes) {
        _votes = votes;
    }

    public Long getPlanGroupId() {
        return _planGroupId;
    }

    public void setPlanGroupId(Long planGroupId) {
        _planGroupId = planGroupId;
    }

    public Boolean getOpen() {
        return _open;
    }

    public void setOpen(Boolean open) {
        _open = open;
    }

    public String getStatus() {
        return _status;
    }

    public void setStatus(String status) {
        _status = status;
    }

    public Long getMbCategoryId() {
        return _mbCategoryId;
    }

    public void setMbCategoryId(Long mbCategoryId) {
        _mbCategoryId = mbCategoryId;
    }

    public Long getCategoryGroupId() {
        return _categoryGroupId;
    }

    public void setCategoryGroupId(Long categoryGroupId) {
        _categoryGroupId = categoryGroupId;
    }

    public Long getVersion() {
        return _version;
    }

    public void setVersion(Long version) {
        _version = version;
    }

    public Long getPlanVersion() {
        return _planVersion;
    }

    public void setPlanVersion(Long planVersion) {
        _planVersion = planVersion;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public Long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    public void setUpdateAuthorId(Long updateAuthorId) {
        _updateAuthorId = updateAuthorId;
    }

    public Long getModelId() {
        return _modelId;
    }

    public void setModelId(Long modelId) {
        _modelId = modelId;
    }

    public Boolean getPromoted() {
        return _promoted;
    }

    public void setPromoted(Boolean promoted) {
        _promoted = promoted;
    }

    public Long getPreviousContestPhase() {
        return _previousContestPhase;
    }

    public void setPreviousContestPhase(Long previousContestPhase) {
        _previousContestPhase = previousContestPhase;
    }

    public Long getContestPhase() {
        return _contestPhase;
    }

    public void setContestPhase(Long contestPhase) {
        _contestPhase = contestPhase;
    }

    public void store() {
        throw new UnsupportedOperationException();
    }

    public void vote() {
        throw new UnsupportedOperationException();
    }

    public void unvote() {
        throw new UnsupportedOperationException();
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanMetaLocalServiceUtil.addPlanMeta(this);
        } else {
            PlanMetaLocalServiceUtil.updatePlanMeta(this);
        }
    }

    @Override
    public PlanMeta toEscapedModel() {
        return (PlanMeta) Proxy.newProxyInstance(PlanMeta.class.getClassLoader(),
            new Class[] { PlanMeta.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanMetaClp clone = new PlanMetaClp();

        clone.setId(getId());
        clone.setPlanId(getPlanId());
        clone.setPlanTypeId(getPlanTypeId());
        clone.setPlanCreated(getPlanCreated());
        clone.setAuthorId(getAuthorId());
        clone.setVotes(getVotes());
        clone.setPlanGroupId(getPlanGroupId());
        clone.setOpen(getOpen());
        clone.setStatus(getStatus());
        clone.setMbCategoryId(getMbCategoryId());
        clone.setCategoryGroupId(getCategoryGroupId());
        clone.setVersion(getVersion());
        clone.setPlanVersion(getPlanVersion());
        clone.setCreated(getCreated());
        clone.setUpdateAuthorId(getUpdateAuthorId());
        clone.setModelId(getModelId());
        clone.setPromoted(getPromoted());
        clone.setPreviousContestPhase(getPreviousContestPhase());
        clone.setContestPhase(getContestPhase());

        return clone;
    }

    public int compareTo(PlanMeta planMeta) {
        int value = 0;

        if (getId() < planMeta.getId()) {
            value = -1;
        } else if (getId() > planMeta.getId()) {
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

        PlanMetaClp planMeta = null;

        try {
            planMeta = (PlanMetaClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long primaryKey = planMeta.getPrimaryKey();

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
        StringBundler sb = new StringBundler(39);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", planId=");
        sb.append(getPlanId());
        sb.append(", planTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", planCreated=");
        sb.append(getPlanCreated());
        sb.append(", authorId=");
        sb.append(getAuthorId());
        sb.append(", votes=");
        sb.append(getVotes());
        sb.append(", planGroupId=");
        sb.append(getPlanGroupId());
        sb.append(", open=");
        sb.append(getOpen());
        sb.append(", status=");
        sb.append(getStatus());
        sb.append(", mbCategoryId=");
        sb.append(getMbCategoryId());
        sb.append(", categoryGroupId=");
        sb.append(getCategoryGroupId());
        sb.append(", version=");
        sb.append(getVersion());
        sb.append(", planVersion=");
        sb.append(getPlanVersion());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append(", updateAuthorId=");
        sb.append(getUpdateAuthorId());
        sb.append(", modelId=");
        sb.append(getModelId());
        sb.append(", promoted=");
        sb.append(getPromoted());
        sb.append(", previousContestPhase=");
        sb.append(getPreviousContestPhase());
        sb.append(", contestPhase=");
        sb.append(getContestPhase());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(61);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanMeta");
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
            "<column><column-name>planTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planCreated</column-name><column-value><![CDATA[");
        sb.append(getPlanCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>authorId</column-name><column-value><![CDATA[");
        sb.append(getAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>votes</column-name><column-value><![CDATA[");
        sb.append(getVotes());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planGroupId</column-name><column-value><![CDATA[");
        sb.append(getPlanGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>open</column-name><column-value><![CDATA[");
        sb.append(getOpen());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>status</column-name><column-value><![CDATA[");
        sb.append(getStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>mbCategoryId</column-name><column-value><![CDATA[");
        sb.append(getMbCategoryId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>categoryGroupId</column-name><column-value><![CDATA[");
        sb.append(getCategoryGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>version</column-name><column-value><![CDATA[");
        sb.append(getVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planVersion</column-name><column-value><![CDATA[");
        sb.append(getPlanVersion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>updateAuthorId</column-name><column-value><![CDATA[");
        sb.append(getUpdateAuthorId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>promoted</column-name><column-value><![CDATA[");
        sb.append(getPromoted());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>previousContestPhase</column-name><column-value><![CDATA[");
        sb.append(getPreviousContestPhase());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contestPhase</column-name><column-value><![CDATA[");
        sb.append(getContestPhase());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
