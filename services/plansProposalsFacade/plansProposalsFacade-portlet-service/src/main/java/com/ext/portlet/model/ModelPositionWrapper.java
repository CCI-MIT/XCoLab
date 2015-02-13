package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ModelPosition}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelPosition
 * @generated
 */
public class ModelPositionWrapper implements ModelPosition,
    ModelWrapper<ModelPosition> {
    private ModelPosition _modelPosition;

    public ModelPositionWrapper(ModelPosition modelPosition) {
        _modelPosition = modelPosition;
    }

    @Override
    public Class<?> getModelClass() {
        return ModelPosition.class;
    }

    @Override
    public String getModelClassName() {
        return ModelPosition.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("positionId", getPositionId());
        attributes.put("modelId", getModelId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        Long positionId = (Long) attributes.get("positionId");

        if (positionId != null) {
            setPositionId(positionId);
        }

        Long modelId = (Long) attributes.get("modelId");

        if (modelId != null) {
            setModelId(modelId);
        }
    }

    /**
    * Returns the primary key of this model position.
    *
    * @return the primary key of this model position
    */
    @Override
    public long getPrimaryKey() {
        return _modelPosition.getPrimaryKey();
    }

    /**
    * Sets the primary key of this model position.
    *
    * @param primaryKey the primary key of this model position
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _modelPosition.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this model position.
    *
    * @return the ID of this model position
    */
    @Override
    public long getId() {
        return _modelPosition.getId();
    }

    /**
    * Sets the ID of this model position.
    *
    * @param id the ID of this model position
    */
    @Override
    public void setId(long id) {
        _modelPosition.setId(id);
    }

    /**
    * Returns the position ID of this model position.
    *
    * @return the position ID of this model position
    */
    @Override
    public long getPositionId() {
        return _modelPosition.getPositionId();
    }

    /**
    * Sets the position ID of this model position.
    *
    * @param positionId the position ID of this model position
    */
    @Override
    public void setPositionId(long positionId) {
        _modelPosition.setPositionId(positionId);
    }

    /**
    * Returns the model ID of this model position.
    *
    * @return the model ID of this model position
    */
    @Override
    public long getModelId() {
        return _modelPosition.getModelId();
    }

    /**
    * Sets the model ID of this model position.
    *
    * @param modelId the model ID of this model position
    */
    @Override
    public void setModelId(long modelId) {
        _modelPosition.setModelId(modelId);
    }

    @Override
    public boolean isNew() {
        return _modelPosition.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _modelPosition.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _modelPosition.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _modelPosition.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _modelPosition.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _modelPosition.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _modelPosition.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _modelPosition.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _modelPosition.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _modelPosition.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _modelPosition.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ModelPositionWrapper((ModelPosition) _modelPosition.clone());
    }

    @Override
    public int compareTo(ModelPosition modelPosition) {
        return _modelPosition.compareTo(modelPosition);
    }

    @Override
    public int hashCode() {
        return _modelPosition.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ModelPosition> toCacheModel() {
        return _modelPosition.toCacheModel();
    }

    @Override
    public ModelPosition toEscapedModel() {
        return new ModelPositionWrapper(_modelPosition.toEscapedModel());
    }

    @Override
    public ModelPosition toUnescapedModel() {
        return new ModelPositionWrapper(_modelPosition.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _modelPosition.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _modelPosition.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelPosition.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ModelPositionWrapper)) {
            return false;
        }

        ModelPositionWrapper modelPositionWrapper = (ModelPositionWrapper) obj;

        if (Validator.equals(_modelPosition, modelPositionWrapper._modelPosition)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ModelPosition getWrappedModelPosition() {
        return _modelPosition;
    }

    @Override
    public ModelPosition getWrappedModel() {
        return _modelPosition;
    }

    @Override
    public void resetOriginalValues() {
        _modelPosition.resetOriginalValues();
    }
}
