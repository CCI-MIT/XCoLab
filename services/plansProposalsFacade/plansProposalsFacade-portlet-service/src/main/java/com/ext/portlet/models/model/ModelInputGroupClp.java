package com.ext.portlet.models.model;

import com.ext.portlet.models.service.ModelInputGroupLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class ModelInputGroupClp extends BaseModelImpl<ModelInputGroup>
    implements ModelInputGroup {
    private Long _modelInputGroupPK;
    private Long _modelId;
    private Long _nameAndDescriptionMetaDataId;
    private String _name;
    private String _description;
    private Integer _displayItemOrder;
    private String _groupType;
    private Long _parentGroupPK;

    public ModelInputGroupClp() {
    }

    public Class<?> getModelClass() {
        return ModelInputGroup.class;
    }

    public String getModelClassName() {
        return ModelInputGroup.class.getName();
    }

    public Long getPrimaryKey() {
        return _modelInputGroupPK;
    }

    public void setPrimaryKey(Long primaryKey) {
        setModelInputGroupPK(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_modelInputGroupPK);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public Long getModelInputGroupPK() {
        return _modelInputGroupPK;
    }

    public void setModelInputGroupPK(Long modelInputGroupPK) {
        _modelInputGroupPK = modelInputGroupPK;
    }

    public Long getModelId() {
        return _modelId;
    }

    public void setModelId(Long modelId) {
        _modelId = modelId;
    }

    public Long getNameAndDescriptionMetaDataId() {
        return _nameAndDescriptionMetaDataId;
    }

    public void setNameAndDescriptionMetaDataId(
        Long nameAndDescriptionMetaDataId) {
        _nameAndDescriptionMetaDataId = nameAndDescriptionMetaDataId;
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

    public Integer getDisplayItemOrder() {
        return _displayItemOrder;
    }

    public void setDisplayItemOrder(Integer displayItemOrder) {
        _displayItemOrder = displayItemOrder;
    }

    public String getGroupType() {
        return _groupType;
    }

    public void setGroupType(String groupType) {
        _groupType = groupType;
    }

    public Long getParentGroupPK() {
        return _parentGroupPK;
    }

    public void setParentGroupPK(Long parentGroupPK) {
        _parentGroupPK = parentGroupPK;
    }

    public java.util.List<com.ext.portlet.models.model.ModelInputItem> getInputItems() {
        throw new UnsupportedOperationException();
    }

    public java.util.List<com.ext.portlet.models.model.ModelInputGroup> getChildGroups() {
        throw new UnsupportedOperationException();
    }

    public com.ext.portlet.models.model.ModelInputGroup getParent() {
        throw new UnsupportedOperationException();
    }

    public edu.mit.cci.simulation.client.Simulation getModel() {
        throw new UnsupportedOperationException();
    }

    public edu.mit.cci.simulation.client.MetaData getMetaData() {
        throw new UnsupportedOperationException();
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ModelInputGroupLocalServiceUtil.addModelInputGroup(this);
        } else {
            ModelInputGroupLocalServiceUtil.updateModelInputGroup(this);
        }
    }

    @Override
    public ModelInputGroup toEscapedModel() {
        return (ModelInputGroup) Proxy.newProxyInstance(ModelInputGroup.class.getClassLoader(),
            new Class[] { ModelInputGroup.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ModelInputGroupClp clone = new ModelInputGroupClp();

        clone.setModelInputGroupPK(getModelInputGroupPK());
        clone.setModelId(getModelId());
        clone.setNameAndDescriptionMetaDataId(getNameAndDescriptionMetaDataId());
        clone.setName(getName());
        clone.setDescription(getDescription());
        clone.setDisplayItemOrder(getDisplayItemOrder());
        clone.setGroupType(getGroupType());
        clone.setParentGroupPK(getParentGroupPK());

        return clone;
    }

    public int compareTo(ModelInputGroup modelInputGroup) {
        Long primaryKey = modelInputGroup.getPrimaryKey();

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

        ModelInputGroupClp modelInputGroup = null;

        try {
            modelInputGroup = (ModelInputGroupClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long primaryKey = modelInputGroup.getPrimaryKey();

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

        sb.append("{modelInputGroupPK=");
        sb.append(getModelInputGroupPK());
        sb.append(", modelId=");
        sb.append(getModelId());
        sb.append(", nameAndDescriptionMetaDataId=");
        sb.append(getNameAndDescriptionMetaDataId());
        sb.append(", name=");
        sb.append(getName());
        sb.append(", description=");
        sb.append(getDescription());
        sb.append(", displayItemOrder=");
        sb.append(getDisplayItemOrder());
        sb.append(", groupType=");
        sb.append(getGroupType());
        sb.append(", parentGroupPK=");
        sb.append(getParentGroupPK());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(28);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.models.model.ModelInputGroup");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>modelInputGroupPK</column-name><column-value><![CDATA[");
        sb.append(getModelInputGroupPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>nameAndDescriptionMetaDataId</column-name><column-value><![CDATA[");
        sb.append(getNameAndDescriptionMetaDataId());
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
            "<column><column-name>displayItemOrder</column-name><column-value><![CDATA[");
        sb.append(getDisplayItemOrder());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>groupType</column-name><column-value><![CDATA[");
        sb.append(getGroupType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentGroupPK</column-name><column-value><![CDATA[");
        sb.append(getParentGroupPK());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
