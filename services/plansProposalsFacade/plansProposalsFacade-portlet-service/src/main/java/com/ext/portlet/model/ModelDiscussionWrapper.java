package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ModelDiscussion}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelDiscussion
 * @generated
 */
public class ModelDiscussionWrapper implements ModelDiscussion,
    ModelWrapper<ModelDiscussion> {
    private ModelDiscussion _modelDiscussion;

    public ModelDiscussionWrapper(ModelDiscussion modelDiscussion) {
        _modelDiscussion = modelDiscussion;
    }

    @Override
    public Class<?> getModelClass() {
        return ModelDiscussion.class;
    }

    @Override
    public String getModelClassName() {
        return ModelDiscussion.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("modelDiscussionId", getModelDiscussionId());
        attributes.put("modelId", getModelId());
        attributes.put("categoryId", getCategoryId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long modelDiscussionId = (Long) attributes.get("modelDiscussionId");

        if (modelDiscussionId != null) {
            setModelDiscussionId(modelDiscussionId);
        }

        Long modelId = (Long) attributes.get("modelId");

        if (modelId != null) {
            setModelId(modelId);
        }

        Long categoryId = (Long) attributes.get("categoryId");

        if (categoryId != null) {
            setCategoryId(categoryId);
        }
    }

    /**
    * Returns the primary key of this model discussion.
    *
    * @return the primary key of this model discussion
    */
    @Override
    public long getPrimaryKey() {
        return _modelDiscussion.getPrimaryKey();
    }

    /**
    * Sets the primary key of this model discussion.
    *
    * @param primaryKey the primary key of this model discussion
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _modelDiscussion.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the model discussion ID of this model discussion.
    *
    * @return the model discussion ID of this model discussion
    */
    @Override
    public long getModelDiscussionId() {
        return _modelDiscussion.getModelDiscussionId();
    }

    /**
    * Sets the model discussion ID of this model discussion.
    *
    * @param modelDiscussionId the model discussion ID of this model discussion
    */
    @Override
    public void setModelDiscussionId(long modelDiscussionId) {
        _modelDiscussion.setModelDiscussionId(modelDiscussionId);
    }

    /**
    * Returns the model ID of this model discussion.
    *
    * @return the model ID of this model discussion
    */
    @Override
    public long getModelId() {
        return _modelDiscussion.getModelId();
    }

    /**
    * Sets the model ID of this model discussion.
    *
    * @param modelId the model ID of this model discussion
    */
    @Override
    public void setModelId(long modelId) {
        _modelDiscussion.setModelId(modelId);
    }

    /**
    * Returns the category ID of this model discussion.
    *
    * @return the category ID of this model discussion
    */
    @Override
    public long getCategoryId() {
        return _modelDiscussion.getCategoryId();
    }

    /**
    * Sets the category ID of this model discussion.
    *
    * @param categoryId the category ID of this model discussion
    */
    @Override
    public void setCategoryId(long categoryId) {
        _modelDiscussion.setCategoryId(categoryId);
    }

    @Override
    public boolean isNew() {
        return _modelDiscussion.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _modelDiscussion.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _modelDiscussion.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _modelDiscussion.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _modelDiscussion.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _modelDiscussion.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _modelDiscussion.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _modelDiscussion.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _modelDiscussion.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _modelDiscussion.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _modelDiscussion.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ModelDiscussionWrapper((ModelDiscussion) _modelDiscussion.clone());
    }

    @Override
    public int compareTo(ModelDiscussion modelDiscussion) {
        return _modelDiscussion.compareTo(modelDiscussion);
    }

    @Override
    public int hashCode() {
        return _modelDiscussion.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<ModelDiscussion> toCacheModel() {
        return _modelDiscussion.toCacheModel();
    }

    @Override
    public ModelDiscussion toEscapedModel() {
        return new ModelDiscussionWrapper(_modelDiscussion.toEscapedModel());
    }

    @Override
    public ModelDiscussion toUnescapedModel() {
        return new ModelDiscussionWrapper(_modelDiscussion.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _modelDiscussion.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _modelDiscussion.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelDiscussion.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ModelDiscussionWrapper)) {
            return false;
        }

        ModelDiscussionWrapper modelDiscussionWrapper = (ModelDiscussionWrapper) obj;

        if (Validator.equals(_modelDiscussion,
                    modelDiscussionWrapper._modelDiscussion)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ModelDiscussion getWrappedModelDiscussion() {
        return _modelDiscussion;
    }

    @Override
    public ModelDiscussion getWrappedModel() {
        return _modelDiscussion;
    }

    @Override
    public void resetOriginalValues() {
        _modelDiscussion.resetOriginalValues();
    }
}
