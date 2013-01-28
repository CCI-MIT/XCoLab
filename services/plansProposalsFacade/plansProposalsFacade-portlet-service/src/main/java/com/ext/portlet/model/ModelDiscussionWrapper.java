package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ModelDiscussion}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ModelDiscussion
 * @generated
 */
public class ModelDiscussionWrapper implements ModelDiscussion,
    ModelWrapper<ModelDiscussion> {
    private ModelDiscussion _modelDiscussion;

    public ModelDiscussionWrapper(ModelDiscussion modelDiscussion) {
        _modelDiscussion = modelDiscussion;
    }

    public Class<?> getModelClass() {
        return ModelDiscussion.class;
    }

    public String getModelClassName() {
        return ModelDiscussion.class.getName();
    }

    /**
    * Returns the primary key of this model discussion.
    *
    * @return the primary key of this model discussion
    */
    public long getPrimaryKey() {
        return _modelDiscussion.getPrimaryKey();
    }

    /**
    * Sets the primary key of this model discussion.
    *
    * @param primaryKey the primary key of this model discussion
    */
    public void setPrimaryKey(long primaryKey) {
        _modelDiscussion.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the model discussion ID of this model discussion.
    *
    * @return the model discussion ID of this model discussion
    */
    public long getModelDiscussionId() {
        return _modelDiscussion.getModelDiscussionId();
    }

    /**
    * Sets the model discussion ID of this model discussion.
    *
    * @param modelDiscussionId the model discussion ID of this model discussion
    */
    public void setModelDiscussionId(long modelDiscussionId) {
        _modelDiscussion.setModelDiscussionId(modelDiscussionId);
    }

    /**
    * Returns the model ID of this model discussion.
    *
    * @return the model ID of this model discussion
    */
    public long getModelId() {
        return _modelDiscussion.getModelId();
    }

    /**
    * Sets the model ID of this model discussion.
    *
    * @param modelId the model ID of this model discussion
    */
    public void setModelId(long modelId) {
        _modelDiscussion.setModelId(modelId);
    }

    /**
    * Returns the category ID of this model discussion.
    *
    * @return the category ID of this model discussion
    */
    public long getCategoryId() {
        return _modelDiscussion.getCategoryId();
    }

    /**
    * Sets the category ID of this model discussion.
    *
    * @param categoryId the category ID of this model discussion
    */
    public void setCategoryId(long categoryId) {
        _modelDiscussion.setCategoryId(categoryId);
    }

    public boolean isNew() {
        return _modelDiscussion.isNew();
    }

    public void setNew(boolean n) {
        _modelDiscussion.setNew(n);
    }

    public boolean isCachedModel() {
        return _modelDiscussion.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _modelDiscussion.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _modelDiscussion.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _modelDiscussion.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _modelDiscussion.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _modelDiscussion.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _modelDiscussion.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ModelDiscussionWrapper((ModelDiscussion) _modelDiscussion.clone());
    }

    public int compareTo(ModelDiscussion modelDiscussion) {
        return _modelDiscussion.compareTo(modelDiscussion);
    }

    @Override
    public int hashCode() {
        return _modelDiscussion.hashCode();
    }

    public com.liferay.portal.model.CacheModel<ModelDiscussion> toCacheModel() {
        return _modelDiscussion.toCacheModel();
    }

    public ModelDiscussion toEscapedModel() {
        return new ModelDiscussionWrapper(_modelDiscussion.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _modelDiscussion.toString();
    }

    public java.lang.String toXmlString() {
        return _modelDiscussion.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelDiscussion.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public ModelDiscussion getWrappedModelDiscussion() {
        return _modelDiscussion;
    }

    public ModelDiscussion getWrappedModel() {
        return _modelDiscussion;
    }

    public void resetOriginalValues() {
        _modelDiscussion.resetOriginalValues();
    }
}
