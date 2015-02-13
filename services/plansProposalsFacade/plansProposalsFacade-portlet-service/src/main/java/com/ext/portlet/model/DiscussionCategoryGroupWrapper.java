package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DiscussionCategoryGroup}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionCategoryGroup
 * @generated
 */
public class DiscussionCategoryGroupWrapper implements DiscussionCategoryGroup,
    ModelWrapper<DiscussionCategoryGroup> {
    private DiscussionCategoryGroup _discussionCategoryGroup;

    public DiscussionCategoryGroupWrapper(
        DiscussionCategoryGroup discussionCategoryGroup) {
        _discussionCategoryGroup = discussionCategoryGroup;
    }

    @Override
    public Class<?> getModelClass() {
        return DiscussionCategoryGroup.class;
    }

    @Override
    public String getModelClassName() {
        return DiscussionCategoryGroup.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("id", getId());
        attributes.put("description", getDescription());
        attributes.put("url", getUrl());
        attributes.put("commentsThread", getCommentsThread());
        attributes.put("isQuiet", getIsQuiet());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long id = (Long) attributes.get("id");

        if (id != null) {
            setId(id);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        String url = (String) attributes.get("url");

        if (url != null) {
            setUrl(url);
        }

        Long commentsThread = (Long) attributes.get("commentsThread");

        if (commentsThread != null) {
            setCommentsThread(commentsThread);
        }

        Boolean isQuiet = (Boolean) attributes.get("isQuiet");

        if (isQuiet != null) {
            setIsQuiet(isQuiet);
        }
    }

    /**
    * Returns the primary key of this discussion category group.
    *
    * @return the primary key of this discussion category group
    */
    @Override
    public long getPrimaryKey() {
        return _discussionCategoryGroup.getPrimaryKey();
    }

    /**
    * Sets the primary key of this discussion category group.
    *
    * @param primaryKey the primary key of this discussion category group
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _discussionCategoryGroup.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this discussion category group.
    *
    * @return the ID of this discussion category group
    */
    @Override
    public long getId() {
        return _discussionCategoryGroup.getId();
    }

    /**
    * Sets the ID of this discussion category group.
    *
    * @param id the ID of this discussion category group
    */
    @Override
    public void setId(long id) {
        _discussionCategoryGroup.setId(id);
    }

    /**
    * Returns the description of this discussion category group.
    *
    * @return the description of this discussion category group
    */
    @Override
    public java.lang.String getDescription() {
        return _discussionCategoryGroup.getDescription();
    }

    /**
    * Sets the description of this discussion category group.
    *
    * @param description the description of this discussion category group
    */
    @Override
    public void setDescription(java.lang.String description) {
        _discussionCategoryGroup.setDescription(description);
    }

    /**
    * Returns the url of this discussion category group.
    *
    * @return the url of this discussion category group
    */
    @Override
    public java.lang.String getUrl() {
        return _discussionCategoryGroup.getUrl();
    }

    /**
    * Sets the url of this discussion category group.
    *
    * @param url the url of this discussion category group
    */
    @Override
    public void setUrl(java.lang.String url) {
        _discussionCategoryGroup.setUrl(url);
    }

    /**
    * Returns the comments thread of this discussion category group.
    *
    * @return the comments thread of this discussion category group
    */
    @Override
    public long getCommentsThread() {
        return _discussionCategoryGroup.getCommentsThread();
    }

    /**
    * Sets the comments thread of this discussion category group.
    *
    * @param commentsThread the comments thread of this discussion category group
    */
    @Override
    public void setCommentsThread(long commentsThread) {
        _discussionCategoryGroup.setCommentsThread(commentsThread);
    }

    /**
    * Returns the is quiet of this discussion category group.
    *
    * @return the is quiet of this discussion category group
    */
    @Override
    public boolean getIsQuiet() {
        return _discussionCategoryGroup.getIsQuiet();
    }

    /**
    * Returns <code>true</code> if this discussion category group is is quiet.
    *
    * @return <code>true</code> if this discussion category group is is quiet; <code>false</code> otherwise
    */
    @Override
    public boolean isIsQuiet() {
        return _discussionCategoryGroup.isIsQuiet();
    }

    /**
    * Sets whether this discussion category group is is quiet.
    *
    * @param isQuiet the is quiet of this discussion category group
    */
    @Override
    public void setIsQuiet(boolean isQuiet) {
        _discussionCategoryGroup.setIsQuiet(isQuiet);
    }

    @Override
    public boolean isNew() {
        return _discussionCategoryGroup.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _discussionCategoryGroup.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _discussionCategoryGroup.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _discussionCategoryGroup.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _discussionCategoryGroup.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _discussionCategoryGroup.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _discussionCategoryGroup.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _discussionCategoryGroup.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _discussionCategoryGroup.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _discussionCategoryGroup.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _discussionCategoryGroup.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new DiscussionCategoryGroupWrapper((DiscussionCategoryGroup) _discussionCategoryGroup.clone());
    }

    @Override
    public int compareTo(DiscussionCategoryGroup discussionCategoryGroup) {
        return _discussionCategoryGroup.compareTo(discussionCategoryGroup);
    }

    @Override
    public int hashCode() {
        return _discussionCategoryGroup.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<DiscussionCategoryGroup> toCacheModel() {
        return _discussionCategoryGroup.toCacheModel();
    }

    @Override
    public DiscussionCategoryGroup toEscapedModel() {
        return new DiscussionCategoryGroupWrapper(_discussionCategoryGroup.toEscapedModel());
    }

    @Override
    public DiscussionCategoryGroup toUnescapedModel() {
        return new DiscussionCategoryGroupWrapper(_discussionCategoryGroup.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _discussionCategoryGroup.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _discussionCategoryGroup.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionCategoryGroup.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DiscussionCategoryGroupWrapper)) {
            return false;
        }

        DiscussionCategoryGroupWrapper discussionCategoryGroupWrapper = (DiscussionCategoryGroupWrapper) obj;

        if (Validator.equals(_discussionCategoryGroup,
                    discussionCategoryGroupWrapper._discussionCategoryGroup)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public DiscussionCategoryGroup getWrappedDiscussionCategoryGroup() {
        return _discussionCategoryGroup;
    }

    @Override
    public DiscussionCategoryGroup getWrappedModel() {
        return _discussionCategoryGroup;
    }

    @Override
    public void resetOriginalValues() {
        _discussionCategoryGroup.resetOriginalValues();
    }
}
