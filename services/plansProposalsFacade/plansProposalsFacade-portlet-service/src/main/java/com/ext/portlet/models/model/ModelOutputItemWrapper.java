package com.ext.portlet.models.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ModelOutputItem}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ModelOutputItem
 * @generated
 */
public class ModelOutputItemWrapper implements ModelOutputItem,
    ModelWrapper<ModelOutputItem> {
    private ModelOutputItem _modelOutputItem;

    public ModelOutputItemWrapper(ModelOutputItem modelOutputItem) {
        _modelOutputItem = modelOutputItem;
    }

    public Class<?> getModelClass() {
        return ModelOutputItem.class;
    }

    public String getModelClassName() {
        return ModelOutputItem.class.getName();
    }

    /**
    * Returns the primary key of this model output item.
    *
    * @return the primary key of this model output item
    */
    public long getPrimaryKey() {
        return _modelOutputItem.getPrimaryKey();
    }

    /**
    * Sets the primary key of this model output item.
    *
    * @param primaryKey the primary key of this model output item
    */
    public void setPrimaryKey(long primaryKey) {
        _modelOutputItem.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the model output item modifier p k of this model output item.
    *
    * @return the model output item modifier p k of this model output item
    */
    public long getModelOutputItemModifierPK() {
        return _modelOutputItem.getModelOutputItemModifierPK();
    }

    /**
    * Sets the model output item modifier p k of this model output item.
    *
    * @param modelOutputItemModifierPK the model output item modifier p k of this model output item
    */
    public void setModelOutputItemModifierPK(long modelOutputItemModifierPK) {
        _modelOutputItem.setModelOutputItemModifierPK(modelOutputItemModifierPK);
    }

    /**
    * Returns the model ID of this model output item.
    *
    * @return the model ID of this model output item
    */
    public long getModelId() {
        return _modelOutputItem.getModelId();
    }

    /**
    * Sets the model ID of this model output item.
    *
    * @param modelId the model ID of this model output item
    */
    public void setModelId(long modelId) {
        _modelOutputItem.setModelId(modelId);
    }

    /**
    * Returns the model output item ID of this model output item.
    *
    * @return the model output item ID of this model output item
    */
    public long getModelOutputItemId() {
        return _modelOutputItem.getModelOutputItemId();
    }

    /**
    * Sets the model output item ID of this model output item.
    *
    * @param modelOutputItemId the model output item ID of this model output item
    */
    public void setModelOutputItemId(long modelOutputItemId) {
        _modelOutputItem.setModelOutputItemId(modelOutputItemId);
    }

    /**
    * Returns the model output item order of this model output item.
    *
    * @return the model output item order of this model output item
    */
    public int getModelOutputItemOrder() {
        return _modelOutputItem.getModelOutputItemOrder();
    }

    /**
    * Sets the model output item order of this model output item.
    *
    * @param modelOutputItemOrder the model output item order of this model output item
    */
    public void setModelOutputItemOrder(int modelOutputItemOrder) {
        _modelOutputItem.setModelOutputItemOrder(modelOutputItemOrder);
    }

    /**
    * Returns the model item range policy of this model output item.
    *
    * @return the model item range policy of this model output item
    */
    public java.lang.String getModelItemRangePolicy() {
        return _modelOutputItem.getModelItemRangePolicy();
    }

    /**
    * Sets the model item range policy of this model output item.
    *
    * @param modelItemRangePolicy the model item range policy of this model output item
    */
    public void setModelItemRangePolicy(java.lang.String modelItemRangePolicy) {
        _modelOutputItem.setModelItemRangePolicy(modelItemRangePolicy);
    }

    /**
    * Returns the model item range message of this model output item.
    *
    * @return the model item range message of this model output item
    */
    public java.lang.String getModelItemRangeMessage() {
        return _modelOutputItem.getModelItemRangeMessage();
    }

    /**
    * Sets the model item range message of this model output item.
    *
    * @param modelItemRangeMessage the model item range message of this model output item
    */
    public void setModelItemRangeMessage(java.lang.String modelItemRangeMessage) {
        _modelOutputItem.setModelItemRangeMessage(modelItemRangeMessage);
    }

    /**
    * Returns the model item error policy of this model output item.
    *
    * @return the model item error policy of this model output item
    */
    public java.lang.String getModelItemErrorPolicy() {
        return _modelOutputItem.getModelItemErrorPolicy();
    }

    /**
    * Sets the model item error policy of this model output item.
    *
    * @param modelItemErrorPolicy the model item error policy of this model output item
    */
    public void setModelItemErrorPolicy(java.lang.String modelItemErrorPolicy) {
        _modelOutputItem.setModelItemErrorPolicy(modelItemErrorPolicy);
    }

    /**
    * Returns the model item error message of this model output item.
    *
    * @return the model item error message of this model output item
    */
    public java.lang.String getModelItemErrorMessage() {
        return _modelOutputItem.getModelItemErrorMessage();
    }

    /**
    * Sets the model item error message of this model output item.
    *
    * @param modelItemErrorMessage the model item error message of this model output item
    */
    public void setModelItemErrorMessage(java.lang.String modelItemErrorMessage) {
        _modelOutputItem.setModelItemErrorMessage(modelItemErrorMessage);
    }

    /**
    * Returns the model item label format of this model output item.
    *
    * @return the model item label format of this model output item
    */
    public java.lang.String getModelItemLabelFormat() {
        return _modelOutputItem.getModelItemLabelFormat();
    }

    /**
    * Sets the model item label format of this model output item.
    *
    * @param modelItemLabelFormat the model item label format of this model output item
    */
    public void setModelItemLabelFormat(java.lang.String modelItemLabelFormat) {
        _modelOutputItem.setModelItemLabelFormat(modelItemLabelFormat);
    }

    /**
    * Returns the model item is visible of this model output item.
    *
    * @return the model item is visible of this model output item
    */
    public boolean getModelItemIsVisible() {
        return _modelOutputItem.getModelItemIsVisible();
    }

    /**
    * Returns <code>true</code> if this model output item is model item is visible.
    *
    * @return <code>true</code> if this model output item is model item is visible; <code>false</code> otherwise
    */
    public boolean isModelItemIsVisible() {
        return _modelOutputItem.isModelItemIsVisible();
    }

    /**
    * Sets whether this model output item is model item is visible.
    *
    * @param modelItemIsVisible the model item is visible of this model output item
    */
    public void setModelItemIsVisible(boolean modelItemIsVisible) {
        _modelOutputItem.setModelItemIsVisible(modelItemIsVisible);
    }

    /**
    * Returns the item type of this model output item.
    *
    * @return the item type of this model output item
    */
    public java.lang.String getItemType() {
        return _modelOutputItem.getItemType();
    }

    /**
    * Sets the item type of this model output item.
    *
    * @param itemType the item type of this model output item
    */
    public void setItemType(java.lang.String itemType) {
        _modelOutputItem.setItemType(itemType);
    }

    /**
    * Returns the related output item of this model output item.
    *
    * @return the related output item of this model output item
    */
    public long getRelatedOutputItem() {
        return _modelOutputItem.getRelatedOutputItem();
    }

    /**
    * Sets the related output item of this model output item.
    *
    * @param relatedOutputItem the related output item of this model output item
    */
    public void setRelatedOutputItem(long relatedOutputItem) {
        _modelOutputItem.setRelatedOutputItem(relatedOutputItem);
    }

    public boolean isNew() {
        return _modelOutputItem.isNew();
    }

    public void setNew(boolean n) {
        _modelOutputItem.setNew(n);
    }

    public boolean isCachedModel() {
        return _modelOutputItem.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _modelOutputItem.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _modelOutputItem.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _modelOutputItem.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _modelOutputItem.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _modelOutputItem.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _modelOutputItem.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ModelOutputItemWrapper((ModelOutputItem) _modelOutputItem.clone());
    }

    public int compareTo(ModelOutputItem modelOutputItem) {
        return _modelOutputItem.compareTo(modelOutputItem);
    }

    @Override
    public int hashCode() {
        return _modelOutputItem.hashCode();
    }

    public com.liferay.portal.model.CacheModel<ModelOutputItem> toCacheModel() {
        return _modelOutputItem.toCacheModel();
    }

    public ModelOutputItem toEscapedModel() {
        return new ModelOutputItemWrapper(_modelOutputItem.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _modelOutputItem.toString();
    }

    public java.lang.String toXmlString() {
        return _modelOutputItem.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelOutputItem.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public ModelOutputItem getWrappedModelOutputItem() {
        return _modelOutputItem;
    }

    public ModelOutputItem getWrappedModel() {
        return _modelOutputItem;
    }

    public void resetOriginalValues() {
        _modelOutputItem.resetOriginalValues();
    }
}
