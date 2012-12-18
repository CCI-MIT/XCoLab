package com.ext.portlet.plans.model;

import com.ext.portlet.plans.service.PlanSectionLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;


public class PlanSectionClp extends BaseModelImpl<PlanSection>
    implements PlanSection {
    private long _id;
    private long _planSectionDefinitionId;
    private long _planId;
    private String _content;
    private Date _created;
    private long _version;
    private long _planVersion;
    private long _updateAuthorId;

    public PlanSectionClp() {
    }

    public Class<?> getModelClass() {
        return PlanSection.class;
    }

    public String getModelClassName() {
        return PlanSection.class.getName();
    }

    public long getPrimaryKey() {
        return _id;
    }

    public void setPrimaryKey(long primaryKey) {
        setId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_id);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getId() {
        return _id;
    }

    public void setId(long id) {
        _id = id;
    }

    public long getPlanSectionDefinitionId() {
        return _planSectionDefinitionId;
    }

    public void setPlanSectionDefinitionId(long planSectionDefinitionId) {
        _planSectionDefinitionId = planSectionDefinitionId;
    }

    public long getPlanId() {
        return _planId;
    }

    public void setPlanId(long planId) {
        _planId = planId;
    }

    public String getContent() {
        return _content;
    }

    public void setContent(String content) {
        _content = content;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public long getVersion() {
        return _version;
    }

    public void setVersion(long version) {
        _version = version;
    }

    public long getPlanVersion() {
        return _planVersion;
    }

    public void setPlanVersion(long planVersion) {
        _planVersion = planVersion;
    }

    public long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    public void setUpdateAuthorId(long updateAuthorId) {
        _updateAuthorId = updateAuthorId;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanSectionLocalServiceUtil.addPlanSection(this);
        } else {
            PlanSectionLocalServiceUtil.updatePlanSection(this);
        }
    }

    @Override
    public PlanSection toEscapedModel() {
        return (PlanSection) Proxy.newProxyInstance(PlanSection.class.getClassLoader(),
            new Class[] { PlanSection.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanSectionClp clone = new PlanSectionClp();

        clone.setId(getId());
        clone.setPlanSectionDefinitionId(getPlanSectionDefinitionId());
        clone.setPlanId(getPlanId());
        clone.setContent(getContent());
        clone.setCreated(getCreated());
        clone.setVersion(getVersion());
        clone.setPlanVersion(getPlanVersion());
        clone.setUpdateAuthorId(getUpdateAuthorId());

        return clone;
    }

    public int compareTo(PlanSection planSection) {
        int value = 0;

        if (getId() < planSection.getId()) {
            value = -1;
        } else if (getId() > planSection.getId()) {
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

        PlanSectionClp planSection = null;

        try {
            planSection = (PlanSectionClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = planSection.getPrimaryKey();

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
        StringBundler sb = new StringBundler(17);

        sb.append("{id=");
        sb.append(getId());
        sb.append(", planSectionDefinitionId=");
        sb.append(getPlanSectionDefinitionId());
        sb.append(", planId=");
        sb.append(getPlanId());
        sb.append(", content=");
        sb.append(getContent());
        sb.append(", created=");
        sb.append(getCreated());
        sb.append(", version=");
        sb.append(getVersion());
        sb.append(", planVersion=");
        sb.append(getPlanVersion());
        sb.append(", updateAuthorId=");
        sb.append(getUpdateAuthorId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.plans.model.PlanSection");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>id</column-name><column-value><![CDATA[");
        sb.append(getId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planSectionDefinitionId</column-name><column-value><![CDATA[");
        sb.append(getPlanSectionDefinitionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>planId</column-name><column-value><![CDATA[");
        sb.append(getPlanId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>content</column-name><column-value><![CDATA[");
        sb.append(getContent());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>created</column-name><column-value><![CDATA[");
        sb.append(getCreated());
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
            "<column><column-name>updateAuthorId</column-name><column-value><![CDATA[");
        sb.append(getUpdateAuthorId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
