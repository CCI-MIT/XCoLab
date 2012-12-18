package com.ext.portlet.models.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ModelCategory}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ModelCategory
 * @generated
 */
public class ModelCategoryWrapper implements ModelCategory,
    ModelWrapper<ModelCategory> {
    private ModelCategory _modelCategory;

    public ModelCategoryWrapper(ModelCategory modelCategory) {
        _modelCategory = modelCategory;
    }

    public Class<?> getModelClass() {
        return ModelCategory.class;
    }

    public String getModelClassName() {
        return ModelCategory.class.getName();
    }

    /**
    * Returns the primary key of this model category.
    *
    * @return the primary key of this model category
    */
    public long getPrimaryKey() {
        return _modelCategory.getPrimaryKey();
    }

    /**
    * Sets the primary key of this model category.
    *
    * @param primaryKey the primary key of this model category
    */
    public void setPrimaryKey(long primaryKey) {
        _modelCategory.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the model category p k of this model category.
    *
    * @return the model category p k of this model category
    */
    public long getModelCategoryPK() {
        return _modelCategory.getModelCategoryPK();
    }

    /**
    * Sets the model category p k of this model category.
    *
    * @param modelCategoryPK the model category p k of this model category
    */
    public void setModelCategoryPK(long modelCategoryPK) {
        _modelCategory.setModelCategoryPK(modelCategoryPK);
    }

    /**
    * Returns the model category name of this model category.
    *
    * @return the model category name of this model category
    */
    public java.lang.String getModelCategoryName() {
        return _modelCategory.getModelCategoryName();
    }

    /**
    * Sets the model category name of this model category.
    *
    * @param modelCategoryName the model category name of this model category
    */
    public void setModelCategoryName(java.lang.String modelCategoryName) {
        _modelCategory.setModelCategoryName(modelCategoryName);
    }

    /**
    * Returns the model category description of this model category.
    *
    * @return the model category description of this model category
    */
    public java.lang.String getModelCategoryDescription() {
        return _modelCategory.getModelCategoryDescription();
    }

    /**
    * Sets the model category description of this model category.
    *
    * @param modelCategoryDescription the model category description of this model category
    */
    public void setModelCategoryDescription(
        java.lang.String modelCategoryDescription) {
        _modelCategory.setModelCategoryDescription(modelCategoryDescription);
    }

    /**
    * Returns the model category display weight of this model category.
    *
    * @return the model category display weight of this model category
    */
    public int getModelCategoryDisplayWeight() {
        return _modelCategory.getModelCategoryDisplayWeight();
    }

    /**
    * Sets the model category display weight of this model category.
    *
    * @param modelCategoryDisplayWeight the model category display weight of this model category
    */
    public void setModelCategoryDisplayWeight(int modelCategoryDisplayWeight) {
        _modelCategory.setModelCategoryDisplayWeight(modelCategoryDisplayWeight);
    }

    public boolean isNew() {
        return _modelCategory.isNew();
    }

    public void setNew(boolean n) {
        _modelCategory.setNew(n);
    }

    public boolean isCachedModel() {
        return _modelCategory.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _modelCategory.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _modelCategory.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _modelCategory.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _modelCategory.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _modelCategory.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _modelCategory.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ModelCategoryWrapper((ModelCategory) _modelCategory.clone());
    }

    public int compareTo(ModelCategory modelCategory) {
        return _modelCategory.compareTo(modelCategory);
    }

    @Override
    public int hashCode() {
        return _modelCategory.hashCode();
    }

    public com.liferay.portal.model.CacheModel<ModelCategory> toCacheModel() {
        return _modelCategory.toCacheModel();
    }

    public ModelCategory toEscapedModel() {
        return new ModelCategoryWrapper(_modelCategory.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _modelCategory.toString();
    }

    public java.lang.String toXmlString() {
        return _modelCategory.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelCategory.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public ModelCategory getWrappedModelCategory() {
        return _modelCategory;
    }

    public ModelCategory getWrappedModel() {
        return _modelCategory;
    }

    public void resetOriginalValues() {
        _modelCategory.resetOriginalValues();
    }
}
