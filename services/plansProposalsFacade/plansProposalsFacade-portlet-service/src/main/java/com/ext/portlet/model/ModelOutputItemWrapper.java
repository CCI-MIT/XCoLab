package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ModelOutputItem}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelOutputItem
 * @generated
 */
public class ModelOutputItemWrapper implements ModelOutputItem,
    ModelWrapper<ModelOutputItem> {
    private ModelOutputItem _modelOutputItem;

    public ModelOutputItemWrapper(ModelOutputItem modelOutputItem) {
        _modelOutputItem = modelOutputItem;
    }

    @Override
    public Class<?> getModelClass() {
        return ModelOutputItem.class;
    }

    @Override
    public String getModelClassName() {
        return ModelOutputItem.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("modelOutputItemModifierPK",
            getModelOutputItemModifierPK());
        attributes.put("modelId", getModelId());
        attributes.put("modelOutputItemId", getModelOutputItemId());
        attributes.put("modelOutputItemOrder", getModelOutputItemOrder());
        attributes.put("modelItemRangePolicy", getModelItemRangePolicy());
        attributes.put("modelItemRangeMessage", getModelItemRangeMessage());
        attributes.put("modelItemErrorPolicy", getModelItemErrorPolicy());
        attributes.put("modelItemErrorMessage", getModelItemErrorMessage());
        attributes.put("modelItemLabelFormat", getModelItemLabelFormat());
        attributes.put("modelItemIsVisible", getModelItemIsVisible());
        attributes.put("itemType", getItemType());
        attributes.put("relatedOutputItem", getRelatedOutputItem());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long modelOutputItemModifierPK = (Long) attributes.get(
                "modelOutputItemModifierPK");

        if (modelOutputItemModifierPK != null) {
            setModelOutputItemModifierPK(modelOutputItemModifierPK);
        }

        Long modelId = (Long) attributes.get("modelId");

        if (modelId != null) {
            setModelId(modelId);
        }

        Long modelOutputItemId = (Long) attributes.get("modelOutputItemId");

        if (modelOutputItemId != null) {
            setModelOutputItemId(modelOutputItemId);
        }

        Integer modelOutputItemOrder = (Integer) attributes.get(
                "modelOutputItemOrder");

        if (modelOutputItemOrder != null) {
            setModelOutputItemOrder(modelOutputItemOrder);
        }

        String modelItemRangePolicy = (String) attributes.get(
                "modelItemRangePolicy");

        if (modelItemRangePolicy != null) {
            setModelItemRangePolicy(modelItemRangePolicy);
        }

        String modelItemRangeMessage = (String) attributes.get(
                "modelItemRangeMessage");

        if (modelItemRangeMessage != null) {
            setModelItemRangeMessage(modelItemRangeMessage);
        }

        String modelItemErrorPolicy = (String) attributes.get(
                "modelItemErrorPolicy");

        if (modelItemErrorPolicy != null) {
            setModelItemErrorPolicy(modelItemErrorPolicy);
        }

        String modelItemErrorMessage = (String) attributes.get(
                "modelItemErrorMessage");

        if (modelItemErrorMessage != null) {
            setModelItemErrorMessage(modelItemErrorMessage);
        }

        String modelItemLabelFormat = (String) attributes.get(
                "modelItemLabelFormat");

        if (modelItemLabelFormat != null) {
            setModelItemLabelFormat(modelItemLabelFormat);
        }

        Boolean modelItemIsVisible = (Boolean) attributes.get(
                "modelItemIsVisible");

        if (modelItemIsVisible != null) {
            setModelItemIsVisible(modelItemIsVisible);
        }

        String itemType = (String) attributes.get("itemType");

        if (itemType != null) {
            setItemType(itemType);
        }

        Long relatedOutputItem = (Long) attributes.get("relatedOutputItem");

        if (relatedOutputItem != null) {
            setRelatedOutputItem(relatedOutputItem);
        }
    }

    /**
    * Returns the primary key of this model output item.
    *
    * @return the primary key of this model output item
    */
    @Override
    public long getPrimaryKey() {
        return _modelOutputItem.getPrimaryKey();
    }

    /**
    * Sets the primary key of this model output item.
    *
    * @param primaryKey the primary key of this model output item
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _modelOutputItem.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the model output item modifier p k of this model output item.
    *
    * @return the model output item modifier p k of this model output item
    */
    @Override
    public long getModelOutputItemModifierPK() {
        return _modelOutputItem.getModelOutputItemModifierPK();
    }

    /**
    * Sets the model output item modifier p k of this model output item.
    *
    * @param modelOutputItemModifierPK the model output item modifier p k of this model output item
    */
    @Override
    public void setModelOutputItemModifierPK(long modelOutputItemModifierPK) {
        _modelOutputItem.setModelOutputItemModifierPK(modelOutputItemModifierPK);
    }

    /**
    * Returns the model ID of this model output item.
    *
    * @return the model ID of this model output item
    */
    @Override
    public long getModelId() {
        return _modelOutputItem.getModelId();
    }

    /**
    * Sets the model ID of this model output item.
    *
    * @param modelId the model ID of this model output item
    */
    @Override
    public void setModelId(long modelId) {
        _modelOutputItem.setModelId(modelId);
    }

    /**
    * Returns the model output item ID of this model output item.
    *
    * @return the model output item ID of this model output item
    */
    @Override
    public long getModelOutputItemId() {
        return _modelOutputItem.getModelOutputItemId();
    }

    /**
    * Sets the model output item ID of this model output item.
    *
    * @param modelOutputItemId the model output item ID of this model output item
    */
    @Override
    public void setModelOutputItemId(long modelOutputItemId) {
        _modelOutputItem.setModelOutputItemId(modelOutputItemId);
    }

    /**
    * Returns the model output item order of this model output item.
    *
    * @return the model output item order of this model output item
    */
    @Override
    public int getModelOutputItemOrder() {
        return _modelOutputItem.getModelOutputItemOrder();
    }

    /**
    * Sets the model output item order of this model output item.
    *
    * @param modelOutputItemOrder the model output item order of this model output item
    */
    @Override
    public void setModelOutputItemOrder(int modelOutputItemOrder) {
        _modelOutputItem.setModelOutputItemOrder(modelOutputItemOrder);
    }

    /**
    * Returns the model item range policy of this model output item.
    *
    * @return the model item range policy of this model output item
    */
    @Override
    public java.lang.String getModelItemRangePolicy() {
        return _modelOutputItem.getModelItemRangePolicy();
    }

    /**
    * Sets the model item range policy of this model output item.
    *
    * @param modelItemRangePolicy the model item range policy of this model output item
    */
    @Override
    public void setModelItemRangePolicy(java.lang.String modelItemRangePolicy) {
        _modelOutputItem.setModelItemRangePolicy(modelItemRangePolicy);
    }

    /**
    * Returns the model item range message of this model output item.
    *
    * @return the model item range message of this model output item
    */
    @Override
    public java.lang.String getModelItemRangeMessage() {
        return _modelOutputItem.getModelItemRangeMessage();
    }

    /**
    * Sets the model item range message of this model output item.
    *
    * @param modelItemRangeMessage the model item range message of this model output item
    */
    @Override
    public void setModelItemRangeMessage(java.lang.String modelItemRangeMessage) {
        _modelOutputItem.setModelItemRangeMessage(modelItemRangeMessage);
    }

    /**
    * Returns the model item error policy of this model output item.
    *
    * @return the model item error policy of this model output item
    */
    @Override
    public java.lang.String getModelItemErrorPolicy() {
        return _modelOutputItem.getModelItemErrorPolicy();
    }

    /**
    * Sets the model item error policy of this model output item.
    *
    * @param modelItemErrorPolicy the model item error policy of this model output item
    */
    @Override
    public void setModelItemErrorPolicy(java.lang.String modelItemErrorPolicy) {
        _modelOutputItem.setModelItemErrorPolicy(modelItemErrorPolicy);
    }

    /**
    * Returns the model item error message of this model output item.
    *
    * @return the model item error message of this model output item
    */
    @Override
    public java.lang.String getModelItemErrorMessage() {
        return _modelOutputItem.getModelItemErrorMessage();
    }

    /**
    * Sets the model item error message of this model output item.
    *
    * @param modelItemErrorMessage the model item error message of this model output item
    */
    @Override
    public void setModelItemErrorMessage(java.lang.String modelItemErrorMessage) {
        _modelOutputItem.setModelItemErrorMessage(modelItemErrorMessage);
    }

    /**
    * Returns the model item label format of this model output item.
    *
    * @return the model item label format of this model output item
    */
    @Override
    public java.lang.String getModelItemLabelFormat() {
        return _modelOutputItem.getModelItemLabelFormat();
    }

    /**
    * Sets the model item label format of this model output item.
    *
    * @param modelItemLabelFormat the model item label format of this model output item
    */
    @Override
    public void setModelItemLabelFormat(java.lang.String modelItemLabelFormat) {
        _modelOutputItem.setModelItemLabelFormat(modelItemLabelFormat);
    }

    /**
    * Returns the model item is visible of this model output item.
    *
    * @return the model item is visible of this model output item
    */
    @Override
    public boolean getModelItemIsVisible() {
        return _modelOutputItem.getModelItemIsVisible();
    }

    /**
    * Returns <code>true</code> if this model output item is model item is visible.
    *
    * @return <code>true</code> if this model output item is model item is visible; <code>false</code> otherwise
    */
    @Override
    public boolean isModelItemIsVisible() {
        return _modelOutputItem.isModelItemIsVisible();
    }

    /**
    * Sets whether this model output item is model item is visible.
    *
    * @param modelItemIsVisible the model item is visible of this model output item
    */
    @Override
    public void setModelItemIsVisible(boolean modelItemIsVisible) {
        _modelOutputItem.setModelItemIsVisible(modelItemIsVisible);
    }

    /**
    * Returns the item type of this model output item.
    *
    * @return the item type of this model output item
    */
    @Override
    public java.lang.String getItemType() {
        return _modelOutputItem.getItemType();
    }

    /**
    * Sets the item type of this model output item.
    *
    * @param itemType the item type of this model output item
    */
    @Override
    public void setItemType(java.lang.String itemType) {
        _modelOutputItem.setItemType(itemType);
    }

    /**
    * Returns the related output item of this model output item.
    *
    * @return the related output item of this model output item
    */
    @Override
    public long getRelatedOutputItem() {
        return _modelOutputItem.getRelatedOutputItem();
    }

    /**
    * Sets the related output item of this model output item.
    *
    * @param relatedOutputItem the related output item of this model output item
    */
    @Override
    public void setRelatedOutputItem(long relatedOutputItem) {
        _modelOutputItem.setRelatedOutputItem(relatedOutputItem);
    }

    @Override
    public boolean isNew() {
        return _modelOutputItem.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _modelOutputItem.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _modelOutputItem.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _modelOutputItem.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _modelOutputItem.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _modelOutputItem.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _modelOutputItem.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _modelOutputItem.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _modelOutputItem.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _modelOutputItem.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _modelOutputItem.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ModelOutputItemWrapper((ModelOutputItem) _modelOutputItem.clone());
    }

    @Override
    public int compareTo(com.ext.portlet.model.ModelOutputItem modelOutputItem) {
        return _modelOutputItem.compareTo(modelOutputItem);
    }

    @Override
    public int hashCode() {
        return _modelOutputItem.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.ModelOutputItem> toCacheModel() {
        return _modelOutputItem.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.ModelOutputItem toEscapedModel() {
        return new ModelOutputItemWrapper(_modelOutputItem.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.ModelOutputItem toUnescapedModel() {
        return new ModelOutputItemWrapper(_modelOutputItem.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _modelOutputItem.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _modelOutputItem.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelOutputItem.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ModelOutputItemWrapper)) {
            return false;
        }

        ModelOutputItemWrapper modelOutputItemWrapper = (ModelOutputItemWrapper) obj;

        if (Validator.equals(_modelOutputItem,
                    modelOutputItemWrapper._modelOutputItem)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ModelOutputItem getWrappedModelOutputItem() {
        return _modelOutputItem;
    }

    @Override
    public ModelOutputItem getWrappedModel() {
        return _modelOutputItem;
    }

    @Override
    public void resetOriginalValues() {
        _modelOutputItem.resetOriginalValues();
    }
}
