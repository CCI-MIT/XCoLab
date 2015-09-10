package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ModelCategory}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelCategory
 * @generated
 */
public class ModelCategoryWrapper implements ModelCategory,
    ModelWrapper<ModelCategory> {
    private ModelCategory _modelCategory;

    public ModelCategoryWrapper(ModelCategory modelCategory) {
        _modelCategory = modelCategory;
    }

    @Override
    public Class<?> getModelClass() {
        return ModelCategory.class;
    }

    @Override
    public String getModelClassName() {
        return ModelCategory.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("modelCategoryPK", getModelCategoryPK());
        attributes.put("modelCategoryName", getModelCategoryName());
        attributes.put("modelCategoryDescription", getModelCategoryDescription());
        attributes.put("modelCategoryDisplayWeight",
            getModelCategoryDisplayWeight());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long modelCategoryPK = (Long) attributes.get("modelCategoryPK");

        if (modelCategoryPK != null) {
            setModelCategoryPK(modelCategoryPK);
        }

        String modelCategoryName = (String) attributes.get("modelCategoryName");

        if (modelCategoryName != null) {
            setModelCategoryName(modelCategoryName);
        }

        String modelCategoryDescription = (String) attributes.get(
                "modelCategoryDescription");

        if (modelCategoryDescription != null) {
            setModelCategoryDescription(modelCategoryDescription);
        }

        Integer modelCategoryDisplayWeight = (Integer) attributes.get(
                "modelCategoryDisplayWeight");

        if (modelCategoryDisplayWeight != null) {
            setModelCategoryDisplayWeight(modelCategoryDisplayWeight);
        }
    }

    /**
    * Returns the primary key of this model category.
    *
    * @return the primary key of this model category
    */
    @Override
    public long getPrimaryKey() {
        return _modelCategory.getPrimaryKey();
    }

    /**
    * Sets the primary key of this model category.
    *
    * @param primaryKey the primary key of this model category
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _modelCategory.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the model category p k of this model category.
    *
    * @return the model category p k of this model category
    */
    @Override
    public long getModelCategoryPK() {
        return _modelCategory.getModelCategoryPK();
    }

    /**
    * Sets the model category p k of this model category.
    *
    * @param modelCategoryPK the model category p k of this model category
    */
    @Override
    public void setModelCategoryPK(long modelCategoryPK) {
        _modelCategory.setModelCategoryPK(modelCategoryPK);
    }

    /**
    * Returns the model category name of this model category.
    *
    * @return the model category name of this model category
    */
    @Override
    public java.lang.String getModelCategoryName() {
        return _modelCategory.getModelCategoryName();
    }

    /**
    * Sets the model category name of this model category.
    *
    * @param modelCategoryName the model category name of this model category
    */
    @Override
    public void setModelCategoryName(java.lang.String modelCategoryName) {
        _modelCategory.setModelCategoryName(modelCategoryName);
    }

    /**
    * Returns the model category description of this model category.
    *
    * @return the model category description of this model category
    */
    @Override
    public java.lang.String getModelCategoryDescription() {
        return _modelCategory.getModelCategoryDescription();
    }

    /**
    * Sets the model category description of this model category.
    *
    * @param modelCategoryDescription the model category description of this model category
    */
    @Override
    public void setModelCategoryDescription(
        java.lang.String modelCategoryDescription) {
        _modelCategory.setModelCategoryDescription(modelCategoryDescription);
    }

    /**
    * Returns the model category display weight of this model category.
    *
    * @return the model category display weight of this model category
    */
    @Override
    public int getModelCategoryDisplayWeight() {
        return _modelCategory.getModelCategoryDisplayWeight();
    }

    /**
    * Sets the model category display weight of this model category.
    *
    * @param modelCategoryDisplayWeight the model category display weight of this model category
    */
    @Override
    public void setModelCategoryDisplayWeight(int modelCategoryDisplayWeight) {
        _modelCategory.setModelCategoryDisplayWeight(modelCategoryDisplayWeight);
    }

    @Override
    public boolean isNew() {
        return _modelCategory.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _modelCategory.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _modelCategory.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _modelCategory.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _modelCategory.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _modelCategory.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _modelCategory.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _modelCategory.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _modelCategory.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _modelCategory.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _modelCategory.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ModelCategoryWrapper((ModelCategory) _modelCategory.clone());
    }

    @Override
    public int compareTo(com.ext.portlet.model.ModelCategory modelCategory) {
        return _modelCategory.compareTo(modelCategory);
    }

    @Override
    public int hashCode() {
        return _modelCategory.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.ModelCategory> toCacheModel() {
        return _modelCategory.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.ModelCategory toEscapedModel() {
        return new ModelCategoryWrapper(_modelCategory.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.ModelCategory toUnescapedModel() {
        return new ModelCategoryWrapper(_modelCategory.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _modelCategory.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _modelCategory.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelCategory.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ModelCategoryWrapper)) {
            return false;
        }

        ModelCategoryWrapper modelCategoryWrapper = (ModelCategoryWrapper) obj;

        if (Validator.equals(_modelCategory, modelCategoryWrapper._modelCategory)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ModelCategory getWrappedModelCategory() {
        return _modelCategory;
    }

    @Override
    public ModelCategory getWrappedModel() {
        return _modelCategory;
    }

    @Override
    public void resetOriginalValues() {
        _modelCategory.resetOriginalValues();
    }
}
