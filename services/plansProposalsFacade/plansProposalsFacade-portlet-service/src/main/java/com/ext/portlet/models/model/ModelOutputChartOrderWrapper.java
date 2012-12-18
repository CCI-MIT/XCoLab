package com.ext.portlet.models.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ModelOutputChartOrder}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ModelOutputChartOrder
 * @generated
 */
public class ModelOutputChartOrderWrapper implements ModelOutputChartOrder,
    ModelWrapper<ModelOutputChartOrder> {
    private ModelOutputChartOrder _modelOutputChartOrder;

    public ModelOutputChartOrderWrapper(
        ModelOutputChartOrder modelOutputChartOrder) {
        _modelOutputChartOrder = modelOutputChartOrder;
    }

    public Class<?> getModelClass() {
        return ModelOutputChartOrder.class;
    }

    public String getModelClassName() {
        return ModelOutputChartOrder.class.getName();
    }

    /**
    * Returns the primary key of this model output chart order.
    *
    * @return the primary key of this model output chart order
    */
    public long getPrimaryKey() {
        return _modelOutputChartOrder.getPrimaryKey();
    }

    /**
    * Sets the primary key of this model output chart order.
    *
    * @param primaryKey the primary key of this model output chart order
    */
    public void setPrimaryKey(long primaryKey) {
        _modelOutputChartOrder.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the model output chart order p k of this model output chart order.
    *
    * @return the model output chart order p k of this model output chart order
    */
    public long getModelOutputChartOrderPK() {
        return _modelOutputChartOrder.getModelOutputChartOrderPK();
    }

    /**
    * Sets the model output chart order p k of this model output chart order.
    *
    * @param modelOutputChartOrderPK the model output chart order p k of this model output chart order
    */
    public void setModelOutputChartOrderPK(long modelOutputChartOrderPK) {
        _modelOutputChartOrder.setModelOutputChartOrderPK(modelOutputChartOrderPK);
    }

    /**
    * Returns the model ID of this model output chart order.
    *
    * @return the model ID of this model output chart order
    */
    public long getModelId() {
        return _modelOutputChartOrder.getModelId();
    }

    /**
    * Sets the model ID of this model output chart order.
    *
    * @param modelId the model ID of this model output chart order
    */
    public void setModelId(long modelId) {
        _modelOutputChartOrder.setModelId(modelId);
    }

    /**
    * Returns the model output label of this model output chart order.
    *
    * @return the model output label of this model output chart order
    */
    public java.lang.String getModelOutputLabel() {
        return _modelOutputChartOrder.getModelOutputLabel();
    }

    /**
    * Sets the model output label of this model output chart order.
    *
    * @param modelOutputLabel the model output label of this model output chart order
    */
    public void setModelOutputLabel(java.lang.String modelOutputLabel) {
        _modelOutputChartOrder.setModelOutputLabel(modelOutputLabel);
    }

    /**
    * Returns the model output chart order of this model output chart order.
    *
    * @return the model output chart order of this model output chart order
    */
    public int getModelOutputChartOrder() {
        return _modelOutputChartOrder.getModelOutputChartOrder();
    }

    /**
    * Sets the model output chart order of this model output chart order.
    *
    * @param modelOutputChartOrder the model output chart order of this model output chart order
    */
    public void setModelOutputChartOrder(int modelOutputChartOrder) {
        _modelOutputChartOrder.setModelOutputChartOrder(modelOutputChartOrder);
    }

    /**
    * Returns the model index range policy of this model output chart order.
    *
    * @return the model index range policy of this model output chart order
    */
    public java.lang.String getModelIndexRangePolicy() {
        return _modelOutputChartOrder.getModelIndexRangePolicy();
    }

    /**
    * Sets the model index range policy of this model output chart order.
    *
    * @param modelIndexRangePolicy the model index range policy of this model output chart order
    */
    public void setModelIndexRangePolicy(java.lang.String modelIndexRangePolicy) {
        _modelOutputChartOrder.setModelIndexRangePolicy(modelIndexRangePolicy);
    }

    /**
    * Returns the model index range message of this model output chart order.
    *
    * @return the model index range message of this model output chart order
    */
    public java.lang.String getModelIndexRangeMessage() {
        return _modelOutputChartOrder.getModelIndexRangeMessage();
    }

    /**
    * Sets the model index range message of this model output chart order.
    *
    * @param modelIndexRangeMessage the model index range message of this model output chart order
    */
    public void setModelIndexRangeMessage(
        java.lang.String modelIndexRangeMessage) {
        _modelOutputChartOrder.setModelIndexRangeMessage(modelIndexRangeMessage);
    }

    /**
    * Returns the model index error policy of this model output chart order.
    *
    * @return the model index error policy of this model output chart order
    */
    public java.lang.String getModelIndexErrorPolicy() {
        return _modelOutputChartOrder.getModelIndexErrorPolicy();
    }

    /**
    * Sets the model index error policy of this model output chart order.
    *
    * @param modelIndexErrorPolicy the model index error policy of this model output chart order
    */
    public void setModelIndexErrorPolicy(java.lang.String modelIndexErrorPolicy) {
        _modelOutputChartOrder.setModelIndexErrorPolicy(modelIndexErrorPolicy);
    }

    /**
    * Returns the model index error message of this model output chart order.
    *
    * @return the model index error message of this model output chart order
    */
    public java.lang.String getModelIndexErrorMessage() {
        return _modelOutputChartOrder.getModelIndexErrorMessage();
    }

    /**
    * Sets the model index error message of this model output chart order.
    *
    * @param modelIndexErrorMessage the model index error message of this model output chart order
    */
    public void setModelIndexErrorMessage(
        java.lang.String modelIndexErrorMessage) {
        _modelOutputChartOrder.setModelIndexErrorMessage(modelIndexErrorMessage);
    }

    /**
    * Returns the model chart is visible of this model output chart order.
    *
    * @return the model chart is visible of this model output chart order
    */
    public boolean getModelChartIsVisible() {
        return _modelOutputChartOrder.getModelChartIsVisible();
    }

    /**
    * Returns <code>true</code> if this model output chart order is model chart is visible.
    *
    * @return <code>true</code> if this model output chart order is model chart is visible; <code>false</code> otherwise
    */
    public boolean isModelChartIsVisible() {
        return _modelOutputChartOrder.isModelChartIsVisible();
    }

    /**
    * Sets whether this model output chart order is model chart is visible.
    *
    * @param modelChartIsVisible the model chart is visible of this model output chart order
    */
    public void setModelChartIsVisible(boolean modelChartIsVisible) {
        _modelOutputChartOrder.setModelChartIsVisible(modelChartIsVisible);
    }

    public boolean isNew() {
        return _modelOutputChartOrder.isNew();
    }

    public void setNew(boolean n) {
        _modelOutputChartOrder.setNew(n);
    }

    public boolean isCachedModel() {
        return _modelOutputChartOrder.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _modelOutputChartOrder.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _modelOutputChartOrder.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _modelOutputChartOrder.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _modelOutputChartOrder.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _modelOutputChartOrder.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _modelOutputChartOrder.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ModelOutputChartOrderWrapper((ModelOutputChartOrder) _modelOutputChartOrder.clone());
    }

    public int compareTo(ModelOutputChartOrder modelOutputChartOrder) {
        return _modelOutputChartOrder.compareTo(modelOutputChartOrder);
    }

    @Override
    public int hashCode() {
        return _modelOutputChartOrder.hashCode();
    }

    public com.liferay.portal.model.CacheModel<ModelOutputChartOrder> toCacheModel() {
        return _modelOutputChartOrder.toCacheModel();
    }

    public ModelOutputChartOrder toEscapedModel() {
        return new ModelOutputChartOrderWrapper(_modelOutputChartOrder.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _modelOutputChartOrder.toString();
    }

    public java.lang.String toXmlString() {
        return _modelOutputChartOrder.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelOutputChartOrder.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public ModelOutputChartOrder getWrappedModelOutputChartOrder() {
        return _modelOutputChartOrder;
    }

    public ModelOutputChartOrder getWrappedModel() {
        return _modelOutputChartOrder;
    }

    public void resetOriginalValues() {
        _modelOutputChartOrder.resetOriginalValues();
    }
}
