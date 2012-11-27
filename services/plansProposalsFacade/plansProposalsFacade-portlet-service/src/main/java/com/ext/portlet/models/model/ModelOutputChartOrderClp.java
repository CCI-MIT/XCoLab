package com.ext.portlet.models.model;

import com.ext.portlet.models.service.ModelOutputChartOrderLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;


public class ModelOutputChartOrderClp extends BaseModelImpl<ModelOutputChartOrder>
    implements ModelOutputChartOrder {
    private Long _modelOutputChartOrderPK;
    private Long _modelId;
    private String _modelOutputLabel;
    private Integer _modelOutputChartOrder;
    private String _modelIndexRangePolicy;
    private String _modelIndexRangeMessage;
    private String _modelIndexErrorPolicy;
    private String _modelIndexErrorMessage;
    private Boolean _modelChartIsVisible;

    public ModelOutputChartOrderClp() {
    }

    public Class<?> getModelClass() {
        return ModelOutputChartOrder.class;
    }

    public String getModelClassName() {
        return ModelOutputChartOrder.class.getName();
    }

    public Long getPrimaryKey() {
        return _modelOutputChartOrderPK;
    }

    public void setPrimaryKey(Long primaryKey) {
        setModelOutputChartOrderPK(primaryKey);
    }

    public Serializable getPrimaryKeyObj() {
        return new Long(_modelOutputChartOrderPK);
    }

    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Long) primaryKeyObj).longValue());
    }

    public Long getModelOutputChartOrderPK() {
        return _modelOutputChartOrderPK;
    }

    public void setModelOutputChartOrderPK(Long modelOutputChartOrderPK) {
        _modelOutputChartOrderPK = modelOutputChartOrderPK;
    }

    public Long getModelId() {
        return _modelId;
    }

    public void setModelId(Long modelId) {
        _modelId = modelId;
    }

    public String getModelOutputLabel() {
        return _modelOutputLabel;
    }

    public void setModelOutputLabel(String modelOutputLabel) {
        _modelOutputLabel = modelOutputLabel;
    }

    public Integer getModelOutputChartOrder() {
        return _modelOutputChartOrder;
    }

    public void setModelOutputChartOrder(Integer modelOutputChartOrder) {
        _modelOutputChartOrder = modelOutputChartOrder;
    }

    public String getModelIndexRangePolicy() {
        return _modelIndexRangePolicy;
    }

    public void setModelIndexRangePolicy(String modelIndexRangePolicy) {
        _modelIndexRangePolicy = modelIndexRangePolicy;
    }

    public String getModelIndexRangeMessage() {
        return _modelIndexRangeMessage;
    }

    public void setModelIndexRangeMessage(String modelIndexRangeMessage) {
        _modelIndexRangeMessage = modelIndexRangeMessage;
    }

    public String getModelIndexErrorPolicy() {
        return _modelIndexErrorPolicy;
    }

    public void setModelIndexErrorPolicy(String modelIndexErrorPolicy) {
        _modelIndexErrorPolicy = modelIndexErrorPolicy;
    }

    public String getModelIndexErrorMessage() {
        return _modelIndexErrorMessage;
    }

    public void setModelIndexErrorMessage(String modelIndexErrorMessage) {
        _modelIndexErrorMessage = modelIndexErrorMessage;
    }

    public Boolean getModelChartIsVisible() {
        return _modelChartIsVisible;
    }

    public void setModelChartIsVisible(Boolean modelChartIsVisible) {
        _modelChartIsVisible = modelChartIsVisible;
    }

    public void persist() throws SystemException {
        if (this.isNew()) {
            ModelOutputChartOrderLocalServiceUtil.addModelOutputChartOrder(this);
        } else {
            ModelOutputChartOrderLocalServiceUtil.updateModelOutputChartOrder(this);
        }
    }

    @Override
    public ModelOutputChartOrder toEscapedModel() {
        return (ModelOutputChartOrder) Proxy.newProxyInstance(ModelOutputChartOrder.class.getClassLoader(),
            new Class[] { ModelOutputChartOrder.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ModelOutputChartOrderClp clone = new ModelOutputChartOrderClp();

        clone.setModelOutputChartOrderPK(getModelOutputChartOrderPK());
        clone.setModelId(getModelId());
        clone.setModelOutputLabel(getModelOutputLabel());
        clone.setModelOutputChartOrder(getModelOutputChartOrder());
        clone.setModelIndexRangePolicy(getModelIndexRangePolicy());
        clone.setModelIndexRangeMessage(getModelIndexRangeMessage());
        clone.setModelIndexErrorPolicy(getModelIndexErrorPolicy());
        clone.setModelIndexErrorMessage(getModelIndexErrorMessage());
        clone.setModelChartIsVisible(getModelChartIsVisible());

        return clone;
    }

    public int compareTo(ModelOutputChartOrder modelOutputChartOrder) {
        Long primaryKey = modelOutputChartOrder.getPrimaryKey();

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

        ModelOutputChartOrderClp modelOutputChartOrder = null;

        try {
            modelOutputChartOrder = (ModelOutputChartOrderClp) obj;
        } catch (ClassCastException cce) {
            return false;
        }

        Long primaryKey = modelOutputChartOrder.getPrimaryKey();

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
        StringBundler sb = new StringBundler(19);

        sb.append("{modelOutputChartOrderPK=");
        sb.append(getModelOutputChartOrderPK());
        sb.append(", modelId=");
        sb.append(getModelId());
        sb.append(", modelOutputLabel=");
        sb.append(getModelOutputLabel());
        sb.append(", modelOutputChartOrder=");
        sb.append(getModelOutputChartOrder());
        sb.append(", modelIndexRangePolicy=");
        sb.append(getModelIndexRangePolicy());
        sb.append(", modelIndexRangeMessage=");
        sb.append(getModelIndexRangeMessage());
        sb.append(", modelIndexErrorPolicy=");
        sb.append(getModelIndexErrorPolicy());
        sb.append(", modelIndexErrorMessage=");
        sb.append(getModelIndexErrorMessage());
        sb.append(", modelChartIsVisible=");
        sb.append(getModelChartIsVisible());
        sb.append("}");

        return sb.toString();
    }

    public String toXmlString() {
        StringBundler sb = new StringBundler(31);

        sb.append("<model><model-name>");
        sb.append("com.ext.portlet.models.model.ModelOutputChartOrder");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>modelOutputChartOrderPK</column-name><column-value><![CDATA[");
        sb.append(getModelOutputChartOrderPK());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelId</column-name><column-value><![CDATA[");
        sb.append(getModelId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelOutputLabel</column-name><column-value><![CDATA[");
        sb.append(getModelOutputLabel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelOutputChartOrder</column-name><column-value><![CDATA[");
        sb.append(getModelOutputChartOrder());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelIndexRangePolicy</column-name><column-value><![CDATA[");
        sb.append(getModelIndexRangePolicy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelIndexRangeMessage</column-name><column-value><![CDATA[");
        sb.append(getModelIndexRangeMessage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelIndexErrorPolicy</column-name><column-value><![CDATA[");
        sb.append(getModelIndexErrorPolicy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelIndexErrorMessage</column-name><column-value><![CDATA[");
        sb.append(getModelIndexErrorMessage());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modelChartIsVisible</column-name><column-value><![CDATA[");
        sb.append(getModelChartIsVisible());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
