package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ModelInputGroup}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelInputGroup
 * @generated
 */
public class ModelInputGroupWrapper implements ModelInputGroup,
    ModelWrapper<ModelInputGroup> {
    private ModelInputGroup _modelInputGroup;

    public ModelInputGroupWrapper(ModelInputGroup modelInputGroup) {
        _modelInputGroup = modelInputGroup;
    }

    @Override
    public Class<?> getModelClass() {
        return ModelInputGroup.class;
    }

    @Override
    public String getModelClassName() {
        return ModelInputGroup.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("modelInputGroupPK", getModelInputGroupPK());
        attributes.put("modelId", getModelId());
        attributes.put("nameAndDescriptionMetaDataId",
            getNameAndDescriptionMetaDataId());
        attributes.put("name", getName());
        attributes.put("description", getDescription());
        attributes.put("displayItemOrder", getDisplayItemOrder());
        attributes.put("groupType", getGroupType());
        attributes.put("parentGroupPK", getParentGroupPK());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long modelInputGroupPK = (Long) attributes.get("modelInputGroupPK");

        if (modelInputGroupPK != null) {
            setModelInputGroupPK(modelInputGroupPK);
        }

        Long modelId = (Long) attributes.get("modelId");

        if (modelId != null) {
            setModelId(modelId);
        }

        Long nameAndDescriptionMetaDataId = (Long) attributes.get(
                "nameAndDescriptionMetaDataId");

        if (nameAndDescriptionMetaDataId != null) {
            setNameAndDescriptionMetaDataId(nameAndDescriptionMetaDataId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Integer displayItemOrder = (Integer) attributes.get("displayItemOrder");

        if (displayItemOrder != null) {
            setDisplayItemOrder(displayItemOrder);
        }

        String groupType = (String) attributes.get("groupType");

        if (groupType != null) {
            setGroupType(groupType);
        }

        Long parentGroupPK = (Long) attributes.get("parentGroupPK");

        if (parentGroupPK != null) {
            setParentGroupPK(parentGroupPK);
        }
    }

    /**
    * Returns the primary key of this model input group.
    *
    * @return the primary key of this model input group
    */
    @Override
    public long getPrimaryKey() {
        return _modelInputGroup.getPrimaryKey();
    }

    /**
    * Sets the primary key of this model input group.
    *
    * @param primaryKey the primary key of this model input group
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _modelInputGroup.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the model input group p k of this model input group.
    *
    * @return the model input group p k of this model input group
    */
    @Override
    public long getModelInputGroupPK() {
        return _modelInputGroup.getModelInputGroupPK();
    }

    /**
    * Sets the model input group p k of this model input group.
    *
    * @param modelInputGroupPK the model input group p k of this model input group
    */
    @Override
    public void setModelInputGroupPK(long modelInputGroupPK) {
        _modelInputGroup.setModelInputGroupPK(modelInputGroupPK);
    }

    /**
    * Returns the model ID of this model input group.
    *
    * @return the model ID of this model input group
    */
    @Override
    public long getModelId() {
        return _modelInputGroup.getModelId();
    }

    /**
    * Sets the model ID of this model input group.
    *
    * @param modelId the model ID of this model input group
    */
    @Override
    public void setModelId(long modelId) {
        _modelInputGroup.setModelId(modelId);
    }

    /**
    * Returns the name and description meta data ID of this model input group.
    *
    * @return the name and description meta data ID of this model input group
    */
    @Override
    public long getNameAndDescriptionMetaDataId() {
        return _modelInputGroup.getNameAndDescriptionMetaDataId();
    }

    /**
    * Sets the name and description meta data ID of this model input group.
    *
    * @param nameAndDescriptionMetaDataId the name and description meta data ID of this model input group
    */
    @Override
    public void setNameAndDescriptionMetaDataId(
        long nameAndDescriptionMetaDataId) {
        _modelInputGroup.setNameAndDescriptionMetaDataId(nameAndDescriptionMetaDataId);
    }

    /**
    * Returns the name of this model input group.
    *
    * @return the name of this model input group
    */
    @Override
    public java.lang.String getName() {
        return _modelInputGroup.getName();
    }

    /**
    * Sets the name of this model input group.
    *
    * @param name the name of this model input group
    */
    @Override
    public void setName(java.lang.String name) {
        _modelInputGroup.setName(name);
    }

    /**
    * Returns the description of this model input group.
    *
    * @return the description of this model input group
    */
    @Override
    public java.lang.String getDescription() {
        return _modelInputGroup.getDescription();
    }

    /**
    * Sets the description of this model input group.
    *
    * @param description the description of this model input group
    */
    @Override
    public void setDescription(java.lang.String description) {
        _modelInputGroup.setDescription(description);
    }

    /**
    * Returns the display item order of this model input group.
    *
    * @return the display item order of this model input group
    */
    @Override
    public int getDisplayItemOrder() {
        return _modelInputGroup.getDisplayItemOrder();
    }

    /**
    * Sets the display item order of this model input group.
    *
    * @param displayItemOrder the display item order of this model input group
    */
    @Override
    public void setDisplayItemOrder(int displayItemOrder) {
        _modelInputGroup.setDisplayItemOrder(displayItemOrder);
    }

    /**
    * Returns the group type of this model input group.
    *
    * @return the group type of this model input group
    */
    @Override
    public java.lang.String getGroupType() {
        return _modelInputGroup.getGroupType();
    }

    /**
    * Sets the group type of this model input group.
    *
    * @param groupType the group type of this model input group
    */
    @Override
    public void setGroupType(java.lang.String groupType) {
        _modelInputGroup.setGroupType(groupType);
    }

    /**
    * Returns the parent group p k of this model input group.
    *
    * @return the parent group p k of this model input group
    */
    @Override
    public long getParentGroupPK() {
        return _modelInputGroup.getParentGroupPK();
    }

    /**
    * Sets the parent group p k of this model input group.
    *
    * @param parentGroupPK the parent group p k of this model input group
    */
    @Override
    public void setParentGroupPK(long parentGroupPK) {
        _modelInputGroup.setParentGroupPK(parentGroupPK);
    }

    @Override
    public boolean isNew() {
        return _modelInputGroup.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _modelInputGroup.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _modelInputGroup.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _modelInputGroup.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _modelInputGroup.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _modelInputGroup.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _modelInputGroup.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _modelInputGroup.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _modelInputGroup.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _modelInputGroup.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _modelInputGroup.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ModelInputGroupWrapper((ModelInputGroup) _modelInputGroup.clone());
    }

    @Override
    public int compareTo(ModelInputGroup modelInputGroup) {
        return _modelInputGroup.compareTo(modelInputGroup);
    }

    @Override
    public int hashCode() {
        return _modelInputGroup.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ModelInputGroup> toCacheModel() {
        return _modelInputGroup.toCacheModel();
    }

    @Override
    public ModelInputGroup toEscapedModel() {
        return new ModelInputGroupWrapper(_modelInputGroup.toEscapedModel());
    }

    @Override
    public ModelInputGroup toUnescapedModel() {
        return new ModelInputGroupWrapper(_modelInputGroup.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _modelInputGroup.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _modelInputGroup.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelInputGroup.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ModelInputGroupWrapper)) {
            return false;
        }

        ModelInputGroupWrapper modelInputGroupWrapper = (ModelInputGroupWrapper) obj;

        if (Validator.equals(_modelInputGroup,
                    modelInputGroupWrapper._modelInputGroup)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ModelInputGroup getWrappedModelInputGroup() {
        return _modelInputGroup;
    }

    @Override
    public ModelInputGroup getWrappedModel() {
        return _modelInputGroup;
    }

    @Override
    public void resetOriginalValues() {
        _modelInputGroup.resetOriginalValues();
    }
}
