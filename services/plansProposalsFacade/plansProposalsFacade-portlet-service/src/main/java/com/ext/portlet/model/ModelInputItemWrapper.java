package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ModelInputItem}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ModelInputItem
 * @generated
 */
public class ModelInputItemWrapper implements ModelInputItem,
    ModelWrapper<ModelInputItem> {
    private ModelInputItem _modelInputItem;

    public ModelInputItemWrapper(ModelInputItem modelInputItem) {
        _modelInputItem = modelInputItem;
    }

    public Class<?> getModelClass() {
        return ModelInputItem.class;
    }

    public String getModelClassName() {
        return ModelInputItem.class.getName();
    }

    /**
    * Returns the primary key of this model input item.
    *
    * @return the primary key of this model input item
    */
    public long getPrimaryKey() {
        return _modelInputItem.getPrimaryKey();
    }

    /**
    * Sets the primary key of this model input item.
    *
    * @param primaryKey the primary key of this model input item
    */
    public void setPrimaryKey(long primaryKey) {
        _modelInputItem.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the model input item p k of this model input item.
    *
    * @return the model input item p k of this model input item
    */
    public long getModelInputItemPK() {
        return _modelInputItem.getModelInputItemPK();
    }

    /**
    * Sets the model input item p k of this model input item.
    *
    * @param modelInputItemPK the model input item p k of this model input item
    */
    public void setModelInputItemPK(long modelInputItemPK) {
        _modelInputItem.setModelInputItemPK(modelInputItemPK);
    }

    /**
    * Returns the model ID of this model input item.
    *
    * @return the model ID of this model input item
    */
    public long getModelId() {
        return _modelInputItem.getModelId();
    }

    /**
    * Sets the model ID of this model input item.
    *
    * @param modelId the model ID of this model input item
    */
    public void setModelId(long modelId) {
        _modelInputItem.setModelId(modelId);
    }

    /**
    * Returns the model input item i d of this model input item.
    *
    * @return the model input item i d of this model input item
    */
    public long getModelInputItemID() {
        return _modelInputItem.getModelInputItemID();
    }

    /**
    * Sets the model input item i d of this model input item.
    *
    * @param modelInputItemID the model input item i d of this model input item
    */
    public void setModelInputItemID(long modelInputItemID) {
        _modelInputItem.setModelInputItemID(modelInputItemID);
    }

    /**
    * Returns the model group ID of this model input item.
    *
    * @return the model group ID of this model input item
    */
    public long getModelGroupId() {
        return _modelInputItem.getModelGroupId();
    }

    /**
    * Sets the model group ID of this model input item.
    *
    * @param modelGroupId the model group ID of this model input item
    */
    public void setModelGroupId(long modelGroupId) {
        _modelInputItem.setModelGroupId(modelGroupId);
    }

    /**
    * Returns the display item order of this model input item.
    *
    * @return the display item order of this model input item
    */
    public int getDisplayItemOrder() {
        return _modelInputItem.getDisplayItemOrder();
    }

    /**
    * Sets the display item order of this model input item.
    *
    * @param displayItemOrder the display item order of this model input item
    */
    public void setDisplayItemOrder(int displayItemOrder) {
        _modelInputItem.setDisplayItemOrder(displayItemOrder);
    }

    /**
    * Returns the type of this model input item.
    *
    * @return the type of this model input item
    */
    public java.lang.String getType() {
        return _modelInputItem.getType();
    }

    /**
    * Sets the type of this model input item.
    *
    * @param type the type of this model input item
    */
    public void setType(java.lang.String type) {
        _modelInputItem.setType(type);
    }

    /**
    * Returns the properties of this model input item.
    *
    * @return the properties of this model input item
    */
    public java.lang.String getProperties() {
        return _modelInputItem.getProperties();
    }

    /**
    * Sets the properties of this model input item.
    *
    * @param properties the properties of this model input item
    */
    public void setProperties(java.lang.String properties) {
        _modelInputItem.setProperties(properties);
    }

    public boolean isNew() {
        return _modelInputItem.isNew();
    }

    public void setNew(boolean n) {
        _modelInputItem.setNew(n);
    }

    public boolean isCachedModel() {
        return _modelInputItem.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _modelInputItem.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _modelInputItem.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _modelInputItem.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _modelInputItem.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _modelInputItem.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _modelInputItem.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ModelInputItemWrapper((ModelInputItem) _modelInputItem.clone());
    }

    public int compareTo(ModelInputItem modelInputItem) {
        return _modelInputItem.compareTo(modelInputItem);
    }

    @Override
    public int hashCode() {
        return _modelInputItem.hashCode();
    }

    public com.liferay.portal.model.CacheModel<ModelInputItem> toCacheModel() {
        return _modelInputItem.toCacheModel();
    }

    public ModelInputItem toEscapedModel() {
        return new ModelInputItemWrapper(_modelInputItem.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _modelInputItem.toString();
    }

    public java.lang.String toXmlString() {
        return _modelInputItem.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelInputItem.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public ModelInputItem getWrappedModelInputItem() {
        return _modelInputItem;
    }

    public ModelInputItem getWrappedModel() {
        return _modelInputItem;
    }

    public void resetOriginalValues() {
        _modelInputItem.resetOriginalValues();
    }
}
