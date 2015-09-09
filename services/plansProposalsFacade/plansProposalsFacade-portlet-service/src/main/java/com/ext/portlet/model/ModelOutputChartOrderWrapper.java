package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ModelOutputChartOrder}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelOutputChartOrder
 * @generated
 */
public class ModelOutputChartOrderWrapper implements ModelOutputChartOrder,
    ModelWrapper<ModelOutputChartOrder> {
    private ModelOutputChartOrder _modelOutputChartOrder;

    public ModelOutputChartOrderWrapper(
        ModelOutputChartOrder modelOutputChartOrder) {
        _modelOutputChartOrder = modelOutputChartOrder;
    }

    @Override
    public Class<?> getModelClass() {
        return ModelOutputChartOrder.class;
    }

    @Override
    public String getModelClassName() {
        return ModelOutputChartOrder.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("modelOutputChartOrderPK", getModelOutputChartOrderPK());
        attributes.put("modelId", getModelId());
        attributes.put("modelOutputLabel", getModelOutputLabel());
        attributes.put("modelOutputChartOrder", getModelOutputChartOrder());
        attributes.put("modelIndexRangePolicy", getModelIndexRangePolicy());
        attributes.put("modelIndexRangeMessage", getModelIndexRangeMessage());
        attributes.put("modelIndexErrorPolicy", getModelIndexErrorPolicy());
        attributes.put("modelIndexErrorMessage", getModelIndexErrorMessage());
        attributes.put("modelChartIsVisible", getModelChartIsVisible());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long modelOutputChartOrderPK = (Long) attributes.get(
                "modelOutputChartOrderPK");

        if (modelOutputChartOrderPK != null) {
            setModelOutputChartOrderPK(modelOutputChartOrderPK);
        }

        Long modelId = (Long) attributes.get("modelId");

        if (modelId != null) {
            setModelId(modelId);
        }

        String modelOutputLabel = (String) attributes.get("modelOutputLabel");

        if (modelOutputLabel != null) {
            setModelOutputLabel(modelOutputLabel);
        }

        Integer modelOutputChartOrder = (Integer) attributes.get(
                "modelOutputChartOrder");

        if (modelOutputChartOrder != null) {
            setModelOutputChartOrder(modelOutputChartOrder);
        }

        String modelIndexRangePolicy = (String) attributes.get(
                "modelIndexRangePolicy");

        if (modelIndexRangePolicy != null) {
            setModelIndexRangePolicy(modelIndexRangePolicy);
        }

        String modelIndexRangeMessage = (String) attributes.get(
                "modelIndexRangeMessage");

        if (modelIndexRangeMessage != null) {
            setModelIndexRangeMessage(modelIndexRangeMessage);
        }

        String modelIndexErrorPolicy = (String) attributes.get(
                "modelIndexErrorPolicy");

        if (modelIndexErrorPolicy != null) {
            setModelIndexErrorPolicy(modelIndexErrorPolicy);
        }

        String modelIndexErrorMessage = (String) attributes.get(
                "modelIndexErrorMessage");

        if (modelIndexErrorMessage != null) {
            setModelIndexErrorMessage(modelIndexErrorMessage);
        }

        Boolean modelChartIsVisible = (Boolean) attributes.get(
                "modelChartIsVisible");

        if (modelChartIsVisible != null) {
            setModelChartIsVisible(modelChartIsVisible);
        }
    }

    /**
    * Returns the primary key of this model output chart order.
    *
    * @return the primary key of this model output chart order
    */
    @Override
    public long getPrimaryKey() {
        return _modelOutputChartOrder.getPrimaryKey();
    }

    /**
    * Sets the primary key of this model output chart order.
    *
    * @param primaryKey the primary key of this model output chart order
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _modelOutputChartOrder.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the model output chart order p k of this model output chart order.
    *
    * @return the model output chart order p k of this model output chart order
    */
    @Override
    public long getModelOutputChartOrderPK() {
        return _modelOutputChartOrder.getModelOutputChartOrderPK();
    }

    /**
    * Sets the model output chart order p k of this model output chart order.
    *
    * @param modelOutputChartOrderPK the model output chart order p k of this model output chart order
    */
    @Override
    public void setModelOutputChartOrderPK(long modelOutputChartOrderPK) {
        _modelOutputChartOrder.setModelOutputChartOrderPK(modelOutputChartOrderPK);
    }

    /**
    * Returns the model ID of this model output chart order.
    *
    * @return the model ID of this model output chart order
    */
    @Override
    public long getModelId() {
        return _modelOutputChartOrder.getModelId();
    }

    /**
    * Sets the model ID of this model output chart order.
    *
    * @param modelId the model ID of this model output chart order
    */
    @Override
    public void setModelId(long modelId) {
        _modelOutputChartOrder.setModelId(modelId);
    }

    /**
    * Returns the model output label of this model output chart order.
    *
    * @return the model output label of this model output chart order
    */
    @Override
    public java.lang.String getModelOutputLabel() {
        return _modelOutputChartOrder.getModelOutputLabel();
    }

    /**
    * Sets the model output label of this model output chart order.
    *
    * @param modelOutputLabel the model output label of this model output chart order
    */
    @Override
    public void setModelOutputLabel(java.lang.String modelOutputLabel) {
        _modelOutputChartOrder.setModelOutputLabel(modelOutputLabel);
    }

    /**
    * Returns the model output chart order of this model output chart order.
    *
    * @return the model output chart order of this model output chart order
    */
    @Override
    public int getModelOutputChartOrder() {
        return _modelOutputChartOrder.getModelOutputChartOrder();
    }

    /**
    * Sets the model output chart order of this model output chart order.
    *
    * @param modelOutputChartOrder the model output chart order of this model output chart order
    */
    @Override
    public void setModelOutputChartOrder(int modelOutputChartOrder) {
        _modelOutputChartOrder.setModelOutputChartOrder(modelOutputChartOrder);
    }

    /**
    * Returns the model index range policy of this model output chart order.
    *
    * @return the model index range policy of this model output chart order
    */
    @Override
    public java.lang.String getModelIndexRangePolicy() {
        return _modelOutputChartOrder.getModelIndexRangePolicy();
    }

    /**
    * Sets the model index range policy of this model output chart order.
    *
    * @param modelIndexRangePolicy the model index range policy of this model output chart order
    */
    @Override
    public void setModelIndexRangePolicy(java.lang.String modelIndexRangePolicy) {
        _modelOutputChartOrder.setModelIndexRangePolicy(modelIndexRangePolicy);
    }

    /**
    * Returns the model index range message of this model output chart order.
    *
    * @return the model index range message of this model output chart order
    */
    @Override
    public java.lang.String getModelIndexRangeMessage() {
        return _modelOutputChartOrder.getModelIndexRangeMessage();
    }

    /**
    * Sets the model index range message of this model output chart order.
    *
    * @param modelIndexRangeMessage the model index range message of this model output chart order
    */
    @Override
    public void setModelIndexRangeMessage(
        java.lang.String modelIndexRangeMessage) {
        _modelOutputChartOrder.setModelIndexRangeMessage(modelIndexRangeMessage);
    }

    /**
    * Returns the model index error policy of this model output chart order.
    *
    * @return the model index error policy of this model output chart order
    */
    @Override
    public java.lang.String getModelIndexErrorPolicy() {
        return _modelOutputChartOrder.getModelIndexErrorPolicy();
    }

    /**
    * Sets the model index error policy of this model output chart order.
    *
    * @param modelIndexErrorPolicy the model index error policy of this model output chart order
    */
    @Override
    public void setModelIndexErrorPolicy(java.lang.String modelIndexErrorPolicy) {
        _modelOutputChartOrder.setModelIndexErrorPolicy(modelIndexErrorPolicy);
    }

    /**
    * Returns the model index error message of this model output chart order.
    *
    * @return the model index error message of this model output chart order
    */
    @Override
    public java.lang.String getModelIndexErrorMessage() {
        return _modelOutputChartOrder.getModelIndexErrorMessage();
    }

    /**
    * Sets the model index error message of this model output chart order.
    *
    * @param modelIndexErrorMessage the model index error message of this model output chart order
    */
    @Override
    public void setModelIndexErrorMessage(
        java.lang.String modelIndexErrorMessage) {
        _modelOutputChartOrder.setModelIndexErrorMessage(modelIndexErrorMessage);
    }

    /**
    * Returns the model chart is visible of this model output chart order.
    *
    * @return the model chart is visible of this model output chart order
    */
    @Override
    public boolean getModelChartIsVisible() {
        return _modelOutputChartOrder.getModelChartIsVisible();
    }

    /**
    * Returns <code>true</code> if this model output chart order is model chart is visible.
    *
    * @return <code>true</code> if this model output chart order is model chart is visible; <code>false</code> otherwise
    */
    @Override
    public boolean isModelChartIsVisible() {
        return _modelOutputChartOrder.isModelChartIsVisible();
    }

    /**
    * Sets whether this model output chart order is model chart is visible.
    *
    * @param modelChartIsVisible the model chart is visible of this model output chart order
    */
    @Override
    public void setModelChartIsVisible(boolean modelChartIsVisible) {
        _modelOutputChartOrder.setModelChartIsVisible(modelChartIsVisible);
    }

    @Override
    public boolean isNew() {
        return _modelOutputChartOrder.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _modelOutputChartOrder.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _modelOutputChartOrder.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _modelOutputChartOrder.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _modelOutputChartOrder.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _modelOutputChartOrder.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _modelOutputChartOrder.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _modelOutputChartOrder.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _modelOutputChartOrder.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _modelOutputChartOrder.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _modelOutputChartOrder.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ModelOutputChartOrderWrapper((ModelOutputChartOrder) _modelOutputChartOrder.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.ModelOutputChartOrder modelOutputChartOrder) {
        return _modelOutputChartOrder.compareTo(modelOutputChartOrder);
    }

    @Override
    public int hashCode() {
        return _modelOutputChartOrder.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.ModelOutputChartOrder> toCacheModel() {
        return _modelOutputChartOrder.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.ModelOutputChartOrder toEscapedModel() {
        return new ModelOutputChartOrderWrapper(_modelOutputChartOrder.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.ModelOutputChartOrder toUnescapedModel() {
        return new ModelOutputChartOrderWrapper(_modelOutputChartOrder.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _modelOutputChartOrder.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _modelOutputChartOrder.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelOutputChartOrder.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ModelOutputChartOrderWrapper)) {
            return false;
        }

        ModelOutputChartOrderWrapper modelOutputChartOrderWrapper = (ModelOutputChartOrderWrapper) obj;

        if (Validator.equals(_modelOutputChartOrder,
                    modelOutputChartOrderWrapper._modelOutputChartOrder)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ModelOutputChartOrder getWrappedModelOutputChartOrder() {
        return _modelOutputChartOrder;
    }

    @Override
    public ModelOutputChartOrder getWrappedModel() {
        return _modelOutputChartOrder;
    }

    @Override
    public void resetOriginalValues() {
        _modelOutputChartOrder.resetOriginalValues();
    }
}
