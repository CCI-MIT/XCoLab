package com.ext.portlet.models.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ModelInputGroup}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ModelInputGroup
 * @generated
 */
public class ModelInputGroupWrapper implements ModelInputGroup,
    ModelWrapper<ModelInputGroup> {
    private ModelInputGroup _modelInputGroup;

    public ModelInputGroupWrapper(ModelInputGroup modelInputGroup) {
        _modelInputGroup = modelInputGroup;
    }

    public Class<?> getModelClass() {
        return ModelInputGroup.class;
    }

    public String getModelClassName() {
        return ModelInputGroup.class.getName();
    }

    /**
    * Returns the primary key of this model input group.
    *
    * @return the primary key of this model input group
    */
    public java.lang.Long getPrimaryKey() {
        return _modelInputGroup.getPrimaryKey();
    }

    /**
    * Sets the primary key of this model input group.
    *
    * @param primaryKey the primary key of this model input group
    */
    public void setPrimaryKey(java.lang.Long primaryKey) {
        _modelInputGroup.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the model input group p k of this model input group.
    *
    * @return the model input group p k of this model input group
    */
    public java.lang.Long getModelInputGroupPK() {
        return _modelInputGroup.getModelInputGroupPK();
    }

    /**
    * Sets the model input group p k of this model input group.
    *
    * @param modelInputGroupPK the model input group p k of this model input group
    */
    public void setModelInputGroupPK(java.lang.Long modelInputGroupPK) {
        _modelInputGroup.setModelInputGroupPK(modelInputGroupPK);
    }

    /**
    * Returns the model ID of this model input group.
    *
    * @return the model ID of this model input group
    */
    public java.lang.Long getModelId() {
        return _modelInputGroup.getModelId();
    }

    /**
    * Sets the model ID of this model input group.
    *
    * @param modelId the model ID of this model input group
    */
    public void setModelId(java.lang.Long modelId) {
        _modelInputGroup.setModelId(modelId);
    }

    /**
    * Returns the name and description meta data ID of this model input group.
    *
    * @return the name and description meta data ID of this model input group
    */
    public java.lang.Long getNameAndDescriptionMetaDataId() {
        return _modelInputGroup.getNameAndDescriptionMetaDataId();
    }

    /**
    * Sets the name and description meta data ID of this model input group.
    *
    * @param nameAndDescriptionMetaDataId the name and description meta data ID of this model input group
    */
    public void setNameAndDescriptionMetaDataId(
        java.lang.Long nameAndDescriptionMetaDataId) {
        _modelInputGroup.setNameAndDescriptionMetaDataId(nameAndDescriptionMetaDataId);
    }

    /**
    * Returns the name of this model input group.
    *
    * @return the name of this model input group
    */
    public java.lang.String getName() {
        return _modelInputGroup.getName();
    }

    /**
    * Sets the name of this model input group.
    *
    * @param name the name of this model input group
    */
    public void setName(java.lang.String name) {
        _modelInputGroup.setName(name);
    }

    /**
    * Returns the description of this model input group.
    *
    * @return the description of this model input group
    */
    public java.lang.String getDescription() {
        return _modelInputGroup.getDescription();
    }

    /**
    * Sets the description of this model input group.
    *
    * @param description the description of this model input group
    */
    public void setDescription(java.lang.String description) {
        _modelInputGroup.setDescription(description);
    }

    /**
    * Returns the display item order of this model input group.
    *
    * @return the display item order of this model input group
    */
    public java.lang.Integer getDisplayItemOrder() {
        return _modelInputGroup.getDisplayItemOrder();
    }

    /**
    * Sets the display item order of this model input group.
    *
    * @param displayItemOrder the display item order of this model input group
    */
    public void setDisplayItemOrder(java.lang.Integer displayItemOrder) {
        _modelInputGroup.setDisplayItemOrder(displayItemOrder);
    }

    /**
    * Returns the group type of this model input group.
    *
    * @return the group type of this model input group
    */
    public java.lang.String getGroupType() {
        return _modelInputGroup.getGroupType();
    }

    /**
    * Sets the group type of this model input group.
    *
    * @param groupType the group type of this model input group
    */
    public void setGroupType(java.lang.String groupType) {
        _modelInputGroup.setGroupType(groupType);
    }

    /**
    * Returns the parent group p k of this model input group.
    *
    * @return the parent group p k of this model input group
    */
    public java.lang.Long getParentGroupPK() {
        return _modelInputGroup.getParentGroupPK();
    }

    /**
    * Sets the parent group p k of this model input group.
    *
    * @param parentGroupPK the parent group p k of this model input group
    */
    public void setParentGroupPK(java.lang.Long parentGroupPK) {
        _modelInputGroup.setParentGroupPK(parentGroupPK);
    }

    public boolean isNew() {
        return _modelInputGroup.isNew();
    }

    public void setNew(boolean n) {
        _modelInputGroup.setNew(n);
    }

    public boolean isCachedModel() {
        return _modelInputGroup.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _modelInputGroup.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _modelInputGroup.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _modelInputGroup.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _modelInputGroup.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _modelInputGroup.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _modelInputGroup.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ModelInputGroupWrapper((ModelInputGroup) _modelInputGroup.clone());
    }

    public int compareTo(ModelInputGroup modelInputGroup) {
        return _modelInputGroup.compareTo(modelInputGroup);
    }

    @Override
    public int hashCode() {
        return _modelInputGroup.hashCode();
    }

    public com.liferay.portal.model.CacheModel<ModelInputGroup> toCacheModel() {
        return _modelInputGroup.toCacheModel();
    }

    public ModelInputGroup toEscapedModel() {
        return new ModelInputGroupWrapper(_modelInputGroup.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _modelInputGroup.toString();
    }

    public java.lang.String toXmlString() {
        return _modelInputGroup.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelInputGroup.persist();
    }

    public java.util.List<com.ext.portlet.models.model.ModelInputItem> getInputItems() {
        return _modelInputGroup.getInputItems();
    }

    public java.util.List<com.ext.portlet.models.model.ModelInputGroup> getChildGroups() {
        return _modelInputGroup.getChildGroups();
    }

    public com.ext.portlet.models.model.ModelInputGroup getParent() {
        return _modelInputGroup.getParent();
    }

    public edu.mit.cci.simulation.client.Simulation getModel()
        throws com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        return _modelInputGroup.getModel();
    }

    public edu.mit.cci.simulation.client.MetaData getMetaData()
        throws com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        return _modelInputGroup.getMetaData();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public ModelInputGroup getWrappedModelInputGroup() {
        return _modelInputGroup;
    }

    public ModelInputGroup getWrappedModel() {
        return _modelInputGroup;
    }

    public void resetOriginalValues() {
        _modelInputGroup.resetOriginalValues();
    }
}
