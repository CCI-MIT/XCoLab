package com.ext.portlet.model;

import com.ext.portlet.service.PlanTypeLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class PlanTypeClp extends BaseModelImpl<PlanType> implements PlanType {
    private long _planTypeId;
    private String _name;
    private String _description;
    private long _modelId;
    private String _modelTypeName;
    private boolean _published;
    private long _publishedCounterpartId;
    private boolean _isDefault;
    private long _defaultModelId;
    private long _defaultScenarioId;

    public PlanTypeClp() {
    }

    public Class<?> getModelClass() {
        return PlanType.class;
    }

    public String getModelClassName() {
        return PlanType.class.getName();
    }

    public long getPrimaryKey() {
        return _planTypeId;
    }

    public void setPrimaryKey(long primaryKey) {
        setPlanTypeId(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_planTypeId);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getPlanTypeId() {
        return _planTypeId;
    }

    public void setPlanTypeId(long planTypeId) {
        _planTypeId = planTypeId;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        _description = description;
    }

    public long getModelId() {
        return _modelId;
    }

    public void setModelId(long modelId) {
        _modelId = modelId;
    }

    public String getModelTypeName() {
        return _modelTypeName;
    }

    public void setModelTypeName(String modelTypeName) {
        _modelTypeName = modelTypeName;
    }

    public boolean getPublished() {
        return _published;
    }

    public boolean isPublished() {
        return _published;
    }

    public void setPublished(boolean published) {
        _published = published;
    }

    public long getPublishedCounterpartId() {
        return _publishedCounterpartId;
    }

    public void setPublishedCounterpartId(long publishedCounterpartId) {
        _publishedCounterpartId = publishedCounterpartId;
    }

    public boolean getIsDefault() {
        return _isDefault;
    }

    public boolean isIsDefault() {
        return _isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        _isDefault = isDefault;
    }

    public long getDefaultModelId() {
        return _defaultModelId;
    }

    public void setDefaultModelId(long defaultModelId) {
        _defaultModelId = defaultModelId;
    }

    public long getDefaultScenarioId() {
        return _defaultScenarioId;
    }

    public void setDefaultScenarioId(long defaultScenarioId) {
        _defaultScenarioId = defaultScenarioId;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            PlanTypeLocalServiceUtil.addPlanType(this);
        } else {
            PlanTypeLocalServiceUtil.updatePlanType(this);
        }
    }

    @Override
    public PlanType toEscapedModel() {
        return (PlanType) Proxy.newProxyInstance(PlanType.class.getClassLoader(),
            new Class[] { PlanType.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PlanTypeClp clone = new PlanTypeClp();

        clone.setPlanTypeId(getPlanTypeId());
        clone.setName(getName());
        clone.setDescription(getDescription());
        clone.setModelId(getModelId());
        clone.setModelTypeName(getModelTypeName());
        clone.setPublished(getPublished());
        clone.setPublishedCounterpartId(getPublishedCounterpartId());
        clone.setIsDefault(getIsDefault());
        clone.setDefaultModelId(getDefaultModelId());
        clone.setDefaultScenarioId(getDefaultScenarioId());

        return clone;
    }

    public int compareTo(PlanType planType) {
        long primaryKey = planType.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        PlanTypeClp planType = null;

        try {
            planType = (PlanTypeClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = planType.getPrimaryKey();

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
        StringBundler sb = new StringBundler(21);

        sb.append("{planTypeId=");
        sb.append(getPlanTypeId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", modelId=");
        sb.append(getModelId());
        sb.append(", modelTypeName=");
        sb.append(getModelTypeName());
        sb.append(", published=");
        sb.append(getPublished());
        sb.append(", publishedCounterpartId=");
        sb.append(getPublishedCounterpartId());
        sb.append(", isDefault=");
        sb.append(getIsDefault());
        sb.append(", defaultModelId=");
        sb.append(getDefaultModelId());
        sb.append(", defaultScenarioId=");
        sb.append(getDefaultScenarioId());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(34);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.PlanType");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>planTypeId</column-name><column-value><![CDATA[");
        sb.append(getPlanTypeId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>name</column-name><column-value><![CDATA[");
        sb.append(getName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>description</column-name><column-value><![CDATA[");
        sb.append(getDescription());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelTypeName</column-name><column-value><![CDATA[");
        sb.append(getModelTypeName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>published</column-name><column-value><![CDATA[");
        sb.append(getPublished());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>publishedCounterpartId</column-name><column-value><![CDATA[");
        sb.append(getPublishedCounterpartId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>isDefault</column-name><column-value><![CDATA[");
        sb.append(getIsDefault());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>defaultModelId</column-name><column-value><![CDATA[");
        sb.append(getDefaultModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>defaultScenarioId</column-name><column-value><![CDATA[");
        sb.append(getDefaultScenarioId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
