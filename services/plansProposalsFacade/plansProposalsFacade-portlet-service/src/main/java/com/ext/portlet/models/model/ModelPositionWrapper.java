package com.ext.portlet.models.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ModelPosition}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ModelPosition
 * @generated
 */
public class ModelPositionWrapper implements ModelPosition,
    ModelWrapper<ModelPosition> {
    private ModelPosition _modelPosition;

    public ModelPositionWrapper(ModelPosition modelPosition) {
        _modelPosition = modelPosition;
    }

    public Class<?> getModelClass() {
        return ModelPosition.class;
    }

    public String getModelClassName() {
        return ModelPosition.class.getName();
    }

    /**
    * Returns the primary key of this model position.
    *
    * @return the primary key of this model position
    */
    public long getPrimaryKey() {
        return _modelPosition.getPrimaryKey();
    }

    /**
    * Sets the primary key of this model position.
    *
    * @param primaryKey the primary key of this model position
    */
    public void setPrimaryKey(long primaryKey) {
        _modelPosition.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this model position.
    *
    * @return the ID of this model position
    */
    public long getId() {
        return _modelPosition.getId();
    }

    /**
    * Sets the ID of this model position.
    *
    * @param id the ID of this model position
    */
    public void setId(long id) {
        _modelPosition.setId(id);
    }

    /**
    * Returns the position ID of this model position.
    *
    * @return the position ID of this model position
    */
    public long getPositionId() {
        return _modelPosition.getPositionId();
    }

    /**
    * Sets the position ID of this model position.
    *
    * @param positionId the position ID of this model position
    */
    public void setPositionId(long positionId) {
        _modelPosition.setPositionId(positionId);
    }

    /**
    * Returns the model ID of this model position.
    *
    * @return the model ID of this model position
    */
    public long getModelId() {
        return _modelPosition.getModelId();
    }

    /**
    * Sets the model ID of this model position.
    *
    * @param modelId the model ID of this model position
    */
    public void setModelId(long modelId) {
        _modelPosition.setModelId(modelId);
    }

    public boolean isNew() {
        return _modelPosition.isNew();
    }

    public void setNew(boolean n) {
        _modelPosition.setNew(n);
    }

    public boolean isCachedModel() {
        return _modelPosition.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _modelPosition.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _modelPosition.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _modelPosition.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _modelPosition.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _modelPosition.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _modelPosition.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ModelPositionWrapper((ModelPosition) _modelPosition.clone());
    }

    public int compareTo(ModelPosition modelPosition) {
        return _modelPosition.compareTo(modelPosition);
    }

    @Override
    public int hashCode() {
        return _modelPosition.hashCode();
    }

    public com.liferay.portal.model.CacheModel<ModelPosition> toCacheModel() {
        return _modelPosition.toCacheModel();
    }

    public ModelPosition toEscapedModel() {
        return new ModelPositionWrapper(_modelPosition.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _modelPosition.toString();
    }

    public java.lang.String toXmlString() {
        return _modelPosition.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelPosition.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public ModelPosition getWrappedModelPosition() {
        return _modelPosition;
    }

    public ModelPosition getWrappedModel() {
        return _modelPosition;
    }

    public void resetOriginalValues() {
        _modelPosition.resetOriginalValues();
    }
}
