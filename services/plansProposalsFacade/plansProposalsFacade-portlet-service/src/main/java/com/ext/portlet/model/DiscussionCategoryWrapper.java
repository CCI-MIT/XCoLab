package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link DiscussionCategory}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionCategory
 * @generated
 */
public class DiscussionCategoryWrapper implements DiscussionCategory,
    ModelWrapper<DiscussionCategory> {
    private DiscussionCategory _discussionCategory;

    public DiscussionCategoryWrapper(DiscussionCategory discussionCategory) {
        _discussionCategory = discussionCategory;
    }

    @Override
    public Class<?> getModelClass() {
        return DiscussionCategory.class;
    }

    @Override
    public String getModelClassName() {
        return DiscussionCategory.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("pk", getPk());
        attributes.put("categoryId", getCategoryId());
        attributes.put("categoryGroupId", getCategoryGroupId());
        attributes.put("authorId", getAuthorId());
        attributes.put("name", getName());
        attributes.put("description", getDescription());
        attributes.put("createDate", getCreateDate());
        attributes.put("deleted", getDeleted());
        attributes.put("threadsCount", getThreadsCount());
        attributes.put("lastActivityDate", getLastActivityDate());
        attributes.put("lastActivityAuthorId", getLastActivityAuthorId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long pk = (Long) attributes.get("pk");

        if (pk != null) {
            setPk(pk);
        }

        Long categoryId = (Long) attributes.get("categoryId");

        if (categoryId != null) {
            setCategoryId(categoryId);
        }

        Long categoryGroupId = (Long) attributes.get("categoryGroupId");

        if (categoryGroupId != null) {
            setCategoryGroupId(categoryGroupId);
        }

        Long authorId = (Long) attributes.get("authorId");

        if (authorId != null) {
            setAuthorId(authorId);
        }

        String name = (String) attributes.get("name");

        if (name != null) {
            setName(name);
        }

        String description = (String) attributes.get("description");

        if (description != null) {
            setDescription(description);
        }

        Date createDate = (Date) attributes.get("createDate");

        if (createDate != null) {
            setCreateDate(createDate);
        }

        Date deleted = (Date) attributes.get("deleted");

        if (deleted != null) {
            setDeleted(deleted);
        }

        Integer threadsCount = (Integer) attributes.get("threadsCount");

        if (threadsCount != null) {
            setThreadsCount(threadsCount);
        }

        Date lastActivityDate = (Date) attributes.get("lastActivityDate");

        if (lastActivityDate != null) {
            setLastActivityDate(lastActivityDate);
        }

        Long lastActivityAuthorId = (Long) attributes.get(
                "lastActivityAuthorId");

        if (lastActivityAuthorId != null) {
            setLastActivityAuthorId(lastActivityAuthorId);
        }
    }

    /**
    * Returns the primary key of this discussion category.
    *
    * @return the primary key of this discussion category
    */
    @Override
    public long getPrimaryKey() {
        return _discussionCategory.getPrimaryKey();
    }

    /**
    * Sets the primary key of this discussion category.
    *
    * @param primaryKey the primary key of this discussion category
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _discussionCategory.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the pk of this discussion category.
    *
    * @return the pk of this discussion category
    */
    @Override
    public long getPk() {
        return _discussionCategory.getPk();
    }

    /**
    * Sets the pk of this discussion category.
    *
    * @param pk the pk of this discussion category
    */
    @Override
    public void setPk(long pk) {
        _discussionCategory.setPk(pk);
    }

    /**
    * Returns the category ID of this discussion category.
    *
    * @return the category ID of this discussion category
    */
    @Override
    public long getCategoryId() {
        return _discussionCategory.getCategoryId();
    }

    /**
    * Sets the category ID of this discussion category.
    *
    * @param categoryId the category ID of this discussion category
    */
    @Override
    public void setCategoryId(long categoryId) {
        _discussionCategory.setCategoryId(categoryId);
    }

    /**
    * Returns the category group ID of this discussion category.
    *
    * @return the category group ID of this discussion category
    */
    @Override
    public long getCategoryGroupId() {
        return _discussionCategory.getCategoryGroupId();
    }

    /**
    * Sets the category group ID of this discussion category.
    *
    * @param categoryGroupId the category group ID of this discussion category
    */
    @Override
    public void setCategoryGroupId(long categoryGroupId) {
        _discussionCategory.setCategoryGroupId(categoryGroupId);
    }

    /**
    * Returns the author ID of this discussion category.
    *
    * @return the author ID of this discussion category
    */
    @Override
    public long getAuthorId() {
        return _discussionCategory.getAuthorId();
    }

    /**
    * Sets the author ID of this discussion category.
    *
    * @param authorId the author ID of this discussion category
    */
    @Override
    public void setAuthorId(long authorId) {
        _discussionCategory.setAuthorId(authorId);
    }

    /**
    * Returns the name of this discussion category.
    *
    * @return the name of this discussion category
    */
    @Override
    public java.lang.String getName() {
        return _discussionCategory.getName();
    }

    /**
    * Sets the name of this discussion category.
    *
    * @param name the name of this discussion category
    */
    @Override
    public void setName(java.lang.String name) {
        _discussionCategory.setName(name);
    }

    /**
    * Returns the description of this discussion category.
    *
    * @return the description of this discussion category
    */
    @Override
    public java.lang.String getDescription() {
        return _discussionCategory.getDescription();
    }

    /**
    * Sets the description of this discussion category.
    *
    * @param description the description of this discussion category
    */
    @Override
    public void setDescription(java.lang.String description) {
        _discussionCategory.setDescription(description);
    }

    /**
    * Returns the create date of this discussion category.
    *
    * @return the create date of this discussion category
    */
    @Override
    public java.util.Date getCreateDate() {
        return _discussionCategory.getCreateDate();
    }

    /**
    * Sets the create date of this discussion category.
    *
    * @param createDate the create date of this discussion category
    */
    @Override
    public void setCreateDate(java.util.Date createDate) {
        _discussionCategory.setCreateDate(createDate);
    }

    /**
    * Returns the deleted of this discussion category.
    *
    * @return the deleted of this discussion category
    */
    @Override
    public java.util.Date getDeleted() {
        return _discussionCategory.getDeleted();
    }

    /**
    * Sets the deleted of this discussion category.
    *
    * @param deleted the deleted of this discussion category
    */
    @Override
    public void setDeleted(java.util.Date deleted) {
        _discussionCategory.setDeleted(deleted);
    }

    /**
    * Returns the threads count of this discussion category.
    *
    * @return the threads count of this discussion category
    */
    @Override
    public int getThreadsCount() {
        return _discussionCategory.getThreadsCount();
    }

    /**
    * Sets the threads count of this discussion category.
    *
    * @param threadsCount the threads count of this discussion category
    */
    @Override
    public void setThreadsCount(int threadsCount) {
        _discussionCategory.setThreadsCount(threadsCount);
    }

    /**
    * Returns the last activity date of this discussion category.
    *
    * @return the last activity date of this discussion category
    */
    @Override
    public java.util.Date getLastActivityDate() {
        return _discussionCategory.getLastActivityDate();
    }

    /**
    * Sets the last activity date of this discussion category.
    *
    * @param lastActivityDate the last activity date of this discussion category
    */
    @Override
    public void setLastActivityDate(java.util.Date lastActivityDate) {
        _discussionCategory.setLastActivityDate(lastActivityDate);
    }

    /**
    * Returns the last activity author ID of this discussion category.
    *
    * @return the last activity author ID of this discussion category
    */
    @Override
    public long getLastActivityAuthorId() {
        return _discussionCategory.getLastActivityAuthorId();
    }

    /**
    * Sets the last activity author ID of this discussion category.
    *
    * @param lastActivityAuthorId the last activity author ID of this discussion category
    */
    @Override
    public void setLastActivityAuthorId(long lastActivityAuthorId) {
        _discussionCategory.setLastActivityAuthorId(lastActivityAuthorId);
    }

    @Override
    public boolean isNew() {
        return _discussionCategory.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _discussionCategory.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _discussionCategory.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _discussionCategory.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _discussionCategory.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _discussionCategory.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _discussionCategory.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _discussionCategory.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _discussionCategory.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _discussionCategory.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _discussionCategory.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new DiscussionCategoryWrapper((DiscussionCategory) _discussionCategory.clone());
    }

    @Override
    public int compareTo(
        com.ext.portlet.model.DiscussionCategory discussionCategory) {
        return _discussionCategory.compareTo(discussionCategory);
    }

    @Override
    public int hashCode() {
        return _discussionCategory.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<com.ext.portlet.model.DiscussionCategory> toCacheModel() {
        return _discussionCategory.toCacheModel();
    }

    @Override
    public com.ext.portlet.model.DiscussionCategory toEscapedModel() {
        return new DiscussionCategoryWrapper(_discussionCategory.toEscapedModel());
    }

    @Override
    public com.ext.portlet.model.DiscussionCategory toUnescapedModel() {
        return new DiscussionCategoryWrapper(_discussionCategory.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _discussionCategory.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _discussionCategory.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionCategory.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof DiscussionCategoryWrapper)) {
            return false;
        }

        DiscussionCategoryWrapper discussionCategoryWrapper = (DiscussionCategoryWrapper) obj;

        if (Validator.equals(_discussionCategory,
                    discussionCategoryWrapper._discussionCategory)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public DiscussionCategory getWrappedDiscussionCategory() {
        return _discussionCategory;
    }

    @Override
    public DiscussionCategory getWrappedModel() {
        return _discussionCategory;
    }

    @Override
    public void resetOriginalValues() {
        _discussionCategory.resetOriginalValues();
    }
}
