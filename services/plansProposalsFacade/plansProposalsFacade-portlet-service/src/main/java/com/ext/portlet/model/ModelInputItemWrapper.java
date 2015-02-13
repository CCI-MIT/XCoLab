package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ModelInputItem}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelInputItem
 * @generated
 */
public class ModelInputItemWrapper implements ModelInputItem,
    ModelWrapper<ModelInputItem> {
    private ModelInputItem _modelInputItem;

    public ModelInputItemWrapper(ModelInputItem modelInputItem) {
        _modelInputItem = modelInputItem;
    }

    @Override
    public Class<?> getModelClass() {
        return ModelInputItem.class;
    }

    @Override
    public String getModelClassName() {
        return ModelInputItem.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("modelInputItemPK", getModelInputItemPK());
        attributes.put("modelId", getModelId());
        attributes.put("modelInputItemID", getModelInputItemID());
        attributes.put("modelGroupId", getModelGroupId());
        attributes.put("displayItemOrder", getDisplayItemOrder());
        attributes.put("type", getType());
        attributes.put("properties", getProperties());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long modelInputItemPK = (Long) attributes.get("modelInputItemPK");

        if (modelInputItemPK != null) {
            setModelInputItemPK(modelInputItemPK);
        }

        Long modelId = (Long) attributes.get("modelId");

        if (modelId != null) {
            setModelId(modelId);
        }

        Long modelInputItemID = (Long) attributes.get("modelInputItemID");

        if (modelInputItemID != null) {
            setModelInputItemID(modelInputItemID);
        }

        Long modelGroupId = (Long) attributes.get("modelGroupId");

        if (modelGroupId != null) {
            setModelGroupId(modelGroupId);
        }

        Integer displayItemOrder = (Integer) attributes.get("displayItemOrder");

        if (displayItemOrder != null) {
            setDisplayItemOrder(displayItemOrder);
        }

        String type = (String) attributes.get("type");

        if (type != null) {
            setType(type);
        }

        String properties = (String) attributes.get("properties");

        if (properties != null) {
            setProperties(properties);
        }
    }

    /**
    * Returns the primary key of this model input item.
    *
    * @return the primary key of this model input item
    */
    @Override
    public long getPrimaryKey() {
        return _modelInputItem.getPrimaryKey();
    }

    /**
    * Sets the primary key of this model input item.
    *
    * @param primaryKey the primary key of this model input item
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _modelInputItem.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the model input item p k of this model input item.
    *
    * @return the model input item p k of this model input item
    */
    @Override
    public long getModelInputItemPK() {
        return _modelInputItem.getModelInputItemPK();
    }

    /**
    * Sets the model input item p k of this model input item.
    *
    * @param modelInputItemPK the model input item p k of this model input item
    */
    @Override
    public void setModelInputItemPK(long modelInputItemPK) {
        _modelInputItem.setModelInputItemPK(modelInputItemPK);
    }

    /**
    * Returns the model ID of this model input item.
    *
    * @return the model ID of this model input item
    */
    @Override
    public long getModelId() {
        return _modelInputItem.getModelId();
    }

    /**
    * Sets the model ID of this model input item.
    *
    * @param modelId the model ID of this model input item
    */
    @Override
    public void setModelId(long modelId) {
        _modelInputItem.setModelId(modelId);
    }

    /**
    * Returns the model input item i d of this model input item.
    *
    * @return the model input item i d of this model input item
    */
    @Override
    public long getModelInputItemID() {
        return _modelInputItem.getModelInputItemID();
    }

    /**
    * Sets the model input item i d of this model input item.
    *
    * @param modelInputItemID the model input item i d of this model input item
    */
    @Override
    public void setModelInputItemID(long modelInputItemID) {
        _modelInputItem.setModelInputItemID(modelInputItemID);
    }

    /**
    * Returns the model group ID of this model input item.
    *
    * @return the model group ID of this model input item
    */
    @Override
    public long getModelGroupId() {
        return _modelInputItem.getModelGroupId();
    }

    /**
    * Sets the model group ID of this model input item.
    *
    * @param modelGroupId the model group ID of this model input item
    */
    @Override
    public void setModelGroupId(long modelGroupId) {
        _modelInputItem.setModelGroupId(modelGroupId);
    }

    /**
    * Returns the display item order of this model input item.
    *
    * @return the display item order of this model input item
    */
    @Override
    public int getDisplayItemOrder() {
        return _modelInputItem.getDisplayItemOrder();
    }

    /**
    * Sets the display item order of this model input item.
    *
    * @param displayItemOrder the display item order of this model input item
    */
    @Override
    public void setDisplayItemOrder(int displayItemOrder) {
        _modelInputItem.setDisplayItemOrder(displayItemOrder);
    }

    /**
    * Returns the type of this model input item.
    *
    * @return the type of this model input item
    */
    @Override
    public java.lang.String getType() {
        return _modelInputItem.getType();
    }

    /**
    * Sets the type of this model input item.
    *
    * @param type the type of this model input item
    */
    @Override
    public void setType(java.lang.String type) {
        _modelInputItem.setType(type);
    }

    /**
    * Returns the properties of this model input item.
    *
    * @return the properties of this model input item
    */
    @Override
    public java.lang.String getProperties() {
        return _modelInputItem.getProperties();
    }

    /**
    * Sets the properties of this model input item.
    *
    * @param properties the properties of this model input item
    */
    @Override
    public void setProperties(java.lang.String properties) {
        _modelInputItem.setProperties(properties);
    }

    @Override
    public boolean isNew() {
        return _modelInputItem.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _modelInputItem.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _modelInputItem.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _modelInputItem.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _modelInputItem.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _modelInputItem.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _modelInputItem.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _modelInputItem.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _modelInputItem.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _modelInputItem.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _modelInputItem.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ModelInputItemWrapper((ModelInputItem) _modelInputItem.clone());
    }

    @Override
    public int compareTo(ModelInputItem modelInputItem) {
        return _modelInputItem.compareTo(modelInputItem);
    }

    @Override
    public int hashCode() {
        return _modelInputItem.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ModelInputItem> toCacheModel() {
        return _modelInputItem.toCacheModel();
    }

    @Override
    public ModelInputItem toEscapedModel() {
        return new ModelInputItemWrapper(_modelInputItem.toEscapedModel());
    }

    @Override
    public ModelInputItem toUnescapedModel() {
        return new ModelInputItemWrapper(_modelInputItem.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _modelInputItem.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _modelInputItem.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelInputItem.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ModelInputItemWrapper)) {
            return false;
        }

        ModelInputItemWrapper modelInputItemWrapper = (ModelInputItemWrapper) obj;

        if (Validator.equals(_modelInputItem,
                    modelInputItemWrapper._modelInputItem)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ModelInputItem getWrappedModelInputItem() {
        return _modelInputItem;
    }

    @Override
    public ModelInputItem getWrappedModel() {
        return _modelInputItem;
    }

    @Override
    public void resetOriginalValues() {
        _modelInputItem.resetOriginalValues();
    }
}
