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
    private Long _id;
    private Long _planSectionDefinitionId;
    private Long _planId;
    private String _content;
    private Date _created;
    private Long _version;
    private Long _planVersion;
    private Long _updateAuthorId;

    public PlanSectionClp() {
    }

    public Class<?> getModelClass() {
        return PlanSection.class;
    }

    public String getModelClassName() {
        return PlanSection.class.getName();
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

    public Long getPlanSectionDefinitionId() {
        return _planSectionDefinitionId;
    }

    public void setPlanSectionDefinitionId(Long planSectionDefinitionId) {
        _planSectionDefinitionId = planSectionDefinitionId;
    }

    public Long getPlanId() {
        return _planId;
    }

    public void setPlanId(Long planId) {
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

    public Long getUpdateAuthorId() {
        return _updateAuthorId;
    }

    public void setUpdateAuthorId(Long updateAuthorId) {
        _updateAuthorId = updateAuthorId;
    }

    public void store() {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.plans.model.PlanSectionDefinition getDefinition() {
        throw new UnsupportedOperationException();
    }

    public void addPlanReference(java.lang.Long planId) {
        throw new UnsupportedOperationException();
    }

    public java.util.List<com.ext.portlet.plans.model.PlanItem> getReferencedPlans() {
        throw new UnsupportedOperationException();
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

        Long primaryKey = planSection.getPrimaryKey();

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
