package com.ext.portlet.model;

import com.ext.portlet.service.ModelOutputItemLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class ModelOutputItemClp extends BaseModelImpl<ModelOutputItem>
    implements ModelOutputItem {
    private long _modelOutputItemModifierPK;
    private long _modelId;
    private long _modelOutputItemId;
    private int _modelOutputItemOrder;
    private String _modelItemRangePolicy;
    private String _modelItemRangeMessage;
    private String _modelItemErrorPolicy;
    private String _modelItemErrorMessage;
    private String _modelItemLabelFormat;
    private boolean _modelItemIsVisible;
    private String _itemType;
    private long _relatedOutputItem;

    public ModelOutputItemClp() {
    }

    public Class<?> getModelClass() {
        return ModelOutputItem.class;
    }

    public String getModelClassName() {
        return ModelOutputItem.class.getName();
    }

    public long getPrimaryKey() {
        return _modelOutputItemModifierPK;
    }

    public void setPrimaryKey(long primaryKey) {
        setModelOutputItemModifierPK(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_modelOutputItemModifierPK);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public long getModelOutputItemModifierPK() {
        return _modelOutputItemModifierPK;
    }

    public void setModelOutputItemModifierPK(long modelOutputItemModifierPK) {
        _modelOutputItemModifierPK = modelOutputItemModifierPK;
    }

    public long getModelId() {
        return _modelId;
    }

    public void setModelId(long modelId) {
        _modelId = modelId;
    }

    public long getModelOutputItemId() {
        return _modelOutputItemId;
    }

    public void setModelOutputItemId(long modelOutputItemId) {
        _modelOutputItemId = modelOutputItemId;
    }

    public int getModelOutputItemOrder() {
        return _modelOutputItemOrder;
    }

    public void setModelOutputItemOrder(int modelOutputItemOrder) {
        _modelOutputItemOrder = modelOutputItemOrder;
    }

    public String getModelItemRangePolicy() {
        return _modelItemRangePolicy;
    }

    public void setModelItemRangePolicy(String modelItemRangePolicy) {
        _modelItemRangePolicy = modelItemRangePolicy;
    }

    public String getModelItemRangeMessage() {
        return _modelItemRangeMessage;
    }

    public void setModelItemRangeMessage(String modelItemRangeMessage) {
        _modelItemRangeMessage = modelItemRangeMessage;
    }

    public String getModelItemErrorPolicy() {
        return _modelItemErrorPolicy;
    }

    public void setModelItemErrorPolicy(String modelItemErrorPolicy) {
        _modelItemErrorPolicy = modelItemErrorPolicy;
    }

    public String getModelItemErrorMessage() {
        return _modelItemErrorMessage;
    }

    public void setModelItemErrorMessage(String modelItemErrorMessage) {
        _modelItemErrorMessage = modelItemErrorMessage;
    }

    public String getModelItemLabelFormat() {
        return _modelItemLabelFormat;
    }

    public void setModelItemLabelFormat(String modelItemLabelFormat) {
        _modelItemLabelFormat = modelItemLabelFormat;
    }

    public boolean getModelItemIsVisible() {
        return _modelItemIsVisible;
    }

    public boolean isModelItemIsVisible() {
        return _modelItemIsVisible;
    }

    public void setModelItemIsVisible(boolean modelItemIsVisible) {
        _modelItemIsVisible = modelItemIsVisible;
    }

    public String getItemType() {
        return _itemType;
    }

    public void setItemType(String itemType) {
        _itemType = itemType;
    }

    public long getRelatedOutputItem() {
        return _relatedOutputItem;
    }

    public void setRelatedOutputItem(long relatedOutputItem) {
        _relatedOutputItem = relatedOutputItem;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ModelOutputItemLocalServiceUtil.addModelOutputItem(this);
        } else {
            ModelOutputItemLocalServiceUtil.updateModelOutputItem(this);
        }
    }

    @Override
    public ModelOutputItem toEscapedModel() {
        return (ModelOutputItem) Proxy.newProxyInstance(ModelOutputItem.class.getClassLoader(),
            new Class[] { ModelOutputItem.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ModelOutputItemClp clone = new ModelOutputItemClp();

        clone.setModelOutputItemModifierPK(getModelOutputItemModifierPK());
        clone.setModelId(getModelId());
        clone.setModelOutputItemId(getModelOutputItemId());
        clone.setModelOutputItemOrder(getModelOutputItemOrder());
        clone.setModelItemRangePolicy(getModelItemRangePolicy());
        clone.setModelItemRangeMessage(getModelItemRangeMessage());
        clone.setModelItemErrorPolicy(getModelItemErrorPolicy());
        clone.setModelItemErrorMessage(getModelItemErrorMessage());
        clone.setModelItemLabelFormat(getModelItemLabelFormat());
        clone.setModelItemIsVisible(getModelItemIsVisible());
        clone.setItemType(getItemType());
        clone.setRelatedOutputItem(getRelatedOutputItem());

        return clone;
    }

    public int compareTo(ModelOutputItem modelOutputItem) {
        long primaryKey = modelOutputItem.getPrimaryKey();

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

        ModelOutputItemClp modelOutputItem = null;

        try {
            modelOutputItem = (ModelOutputItemClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        long primaryKey = modelOutputItem.getPrimaryKey();

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
        StringBundler sb = new StringBundler(25);

        sb.append("{modelOutputItemModifierPK=");
        sb.append(getModelOutputItemModifierPK());
        sb.append(", modelId=");
        sb.append(getModelId());
        sb.append(", modelOutputItemId=");
        sb.append(getModelOutputItemId());
        sb.append(", modelOutputItemOrder=");
        sb.append(getModelOutputItemOrder());
        sb.append(", modelItemRangePolicy=");
        sb.append(getModelItemRangePolicy());
        sb.append(", modelItemRangeMessage=");
        sb.append(getModelItemRangeMessage());
        sb.append(", modelItemErrorPolicy=");
        sb.append(getModelItemErrorPolicy());
        sb.append(", modelItemErrorMessage=");
        sb.append(getModelItemErrorMessage());
        sb.append(", modelItemLabelFormat=");
        sb.append(getModelItemLabelFormat());
        sb.append(", modelItemIsVisible=");
        sb.append(getModelItemIsVisible());
        sb.append(", itemType=");
        sb.append(getItemType());
        sb.append(", relatedOutputItem=");
        sb.append(getRelatedOutputItem());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.model.ModelOutputItem");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>modelOutputItemModifierPK</column-name><column-value><![CDATA[");
        sb.append(getModelOutputItemModifierPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelOutputItemId</column-name><column-value><![CDATA[");
        sb.append(getModelOutputItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelOutputItemOrder</column-name><column-value><![CDATA[");
        sb.append(getModelOutputItemOrder());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelItemRangePolicy</column-name><column-value><![CDATA[");
        sb.append(getModelItemRangePolicy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelItemRangeMessage</column-name><column-value><![CDATA[");
        sb.append(getModelItemRangeMessage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelItemErrorPolicy</column-name><column-value><![CDATA[");
        sb.append(getModelItemErrorPolicy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelItemErrorMessage</column-name><column-value><![CDATA[");
        sb.append(getModelItemErrorMessage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelItemLabelFormat</column-name><column-value><![CDATA[");
        sb.append(getModelItemLabelFormat());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelItemIsVisible</column-name><column-value><![CDATA[");
        sb.append(getModelItemIsVisible());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemType</column-name><column-value><![CDATA[");
        sb.append(getItemType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>relatedOutputItem</column-name><column-value><![CDATA[");
        sb.append(getRelatedOutputItem());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
