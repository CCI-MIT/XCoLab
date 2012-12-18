package com.ext.portlet.models.model;

import com.ext.portlet.models.service.ModelInputItemLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class ModelInputItemClp extends BaseModelImpl<ModelInputItem>
    implements ModelInputItem {
    private long _modelInputItemPK;
    private long _modelId;
    private long _modelInputItemID;
    private long _modelGroupId;
    private int _displayItemOrder;
    private String _type;
    private String _properties;

    public ModelInputItemClp() {
    }

    public Class<?> getModelClass() {
        return ModelInputItem.class;
    }

    public String getModelClassName() {
        return ModelInputItem.class.getName();
    }

    public long getPrimaryKey() {
        return _modelInputItemPK;
    }

    public void setPrimaryKey(long primaryKey) {
        setModelInputItemPK(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_modelInputItemPK);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getModelInputItemPK() {
        return _modelInputItemPK;
    }

    public void setModelInputItemPK(long modelInputItemPK) {
        _modelInputItemPK = modelInputItemPK;
    }

    public long getModelId() {
        return _modelId;
    }

    public void setModelId(long modelId) {
        _modelId = modelId;
    }

    public long getModelInputItemID() {
        return _modelInputItemID;
    }

    public void setModelInputItemID(long modelInputItemID) {
        _modelInputItemID = modelInputItemID;
    }

    public long getModelGroupId() {
        return _modelGroupId;
    }

    public void setModelGroupId(long modelGroupId) {
        _modelGroupId = modelGroupId;
    }

    public int getDisplayItemOrder() {
        return _displayItemOrder;
    }

    public void setDisplayItemOrder(int displayItemOrder) {
        _displayItemOrder = displayItemOrder;
    }

    public String getType() {
        return _type;
    }

    public void setType(String type) {
        _type = type;
    }

    public String getProperties() {
        return _properties;
    }

    public void setProperties(String properties) {
        _properties = properties;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ModelInputItemLocalServiceUtil.addModelInputItem(this);
        } else {
            ModelInputItemLocalServiceUtil.updateModelInputItem(this);
        }
    }

    @Override
    public ModelInputItem toEscapedModel() {
        return (ModelInputItem) Proxy.newProxyInstance(ModelInputItem.class.getClassLoader(),
            new Class[] { ModelInputItem.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ModelInputItemClp clone = new ModelInputItemClp();

        clone.setModelInputItemPK(getModelInputItemPK());
        clone.setModelId(getModelId());
        clone.setModelInputItemID(getModelInputItemID());
        clone.setModelGroupId(getModelGroupId());
        clone.setDisplayItemOrder(getDisplayItemOrder());
        clone.setType(getType());
        clone.setProperties(getProperties());

        return clone;
    }

    public int compareTo(ModelInputItem modelInputItem) {
        long primaryKey = modelInputItem.getPrimaryKey();

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

        ModelInputItemClp modelInputItem = null;

        try {
            modelInputItem = (ModelInputItemClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = modelInputItem.getPrimaryKey();

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
        StringBundler sb = new StringBundler(15);

        sb.append("{modelInputItemPK=");
        sb.append(getModelInputItemPK());
        sb.append(", modelId=");
        sb.append(getModelId());
        sb.append(", modelInputItemID=");
        sb.append(getModelInputItemID());
        sb.append(", modelGroupId=");
        sb.append(getModelGroupId());
        sb.append(", displayItemOrder=");
        sb.append(getDisplayItemOrder());
        sb.append(", type=");
        sb.append(getType());
        sb.append(", properties=");
        sb.append(getProperties());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(25);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.models.model.ModelInputItem");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>modelInputItemPK</column-name><column-value><![CDATA[");
        sb.append(getModelInputItemPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelInputItemID</column-name><column-value><![CDATA[");
        sb.append(getModelInputItemID());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelGroupId</column-name><column-value><![CDATA[");
        sb.append(getModelGroupId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>displayItemOrder</column-name><column-value><![CDATA[");
        sb.append(getDisplayItemOrder());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>type</column-name><column-value><![CDATA[");
        sb.append(getType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>properties</column-name><column-value><![CDATA[");
        sb.append(getProperties());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
