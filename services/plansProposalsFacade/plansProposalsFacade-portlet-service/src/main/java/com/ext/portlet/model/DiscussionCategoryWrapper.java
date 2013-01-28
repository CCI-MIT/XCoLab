package com.ext.portlet.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link DiscussionCategory}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DiscussionCategory
 * @generated
 */
public class DiscussionCategoryWrapper implements DiscussionCategory,
    ModelWrapper<DiscussionCategory> {
    private DiscussionCategory _discussionCategory;

    public DiscussionCategoryWrapper(DiscussionCategory discussionCategory) {
        _discussionCategory = discussionCategory;
    }

    public Class<?> getModelClass() {
        return DiscussionCategory.class;
    }

    public String getModelClassName() {
        return DiscussionCategory.class.getName();
    }

    /**
    * Returns the primary key of this discussion category.
    *
    * @return the primary key of this discussion category
    */
    public long getPrimaryKey() {
        return _discussionCategory.getPrimaryKey();
    }

    /**
    * Sets the primary key of this discussion category.
    *
    * @param primaryKey the primary key of this discussion category
    */
    public void setPrimaryKey(long primaryKey) {
        _discussionCategory.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the pk of this discussion category.
    *
    * @return the pk of this discussion category
    */
    public long getPk() {
        return _discussionCategory.getPk();
    }

    /**
    * Sets the pk of this discussion category.
    *
    * @param pk the pk of this discussion category
    */
    public void setPk(long pk) {
        _discussionCategory.setPk(pk);
    }

    /**
    * Returns the category ID of this discussion category.
    *
    * @return the category ID of this discussion category
    */
    public long getCategoryId() {
        return _discussionCategory.getCategoryId();
    }

    /**
    * Sets the category ID of this discussion category.
    *
    * @param categoryId the category ID of this discussion category
    */
    public void setCategoryId(long categoryId) {
        _discussionCategory.setCategoryId(categoryId);
    }

    /**
    * Returns the category group ID of this discussion category.
    *
    * @return the category group ID of this discussion category
    */
    public long getCategoryGroupId() {
        return _discussionCategory.getCategoryGroupId();
    }

    /**
    * Sets the category group ID of this discussion category.
    *
    * @param categoryGroupId the category group ID of this discussion category
    */
    public void setCategoryGroupId(long categoryGroupId) {
        _discussionCategory.setCategoryGroupId(categoryGroupId);
    }

    /**
    * Returns the author ID of this discussion category.
    *
    * @return the author ID of this discussion category
    */
    public long getAuthorId() {
        return _discussionCategory.getAuthorId();
    }

    /**
    * Sets the author ID of this discussion category.
    *
    * @param authorId the author ID of this discussion category
    */
    public void setAuthorId(long authorId) {
        _discussionCategory.setAuthorId(authorId);
    }

    /**
    * Returns the name of this discussion category.
    *
    * @return the name of this discussion category
    */
    public java.lang.String getName() {
        return _discussionCategory.getName();
    }

    /**
    * Sets the name of this discussion category.
    *
    * @param name the name of this discussion category
    */
    public void setName(java.lang.String name) {
        _discussionCategory.setName(name);
    }

    /**
    * Returns the description of this discussion category.
    *
    * @return the description of this discussion category
    */
    public java.lang.String getDescription() {
        return _discussionCategory.getDescription();
    }

    /**
    * Sets the description of this discussion category.
    *
    * @param description the description of this discussion category
    */
    public void setDescription(java.lang.String description) {
        _discussionCategory.setDescription(description);
    }

    /**
    * Returns the create date of this discussion category.
    *
    * @return the create date of this discussion category
    */
    public java.util.Date getCreateDate() {
        return _discussionCategory.getCreateDate();
    }

    /**
    * Sets the create date of this discussion category.
    *
    * @param createDate the create date of this discussion category
    */
    public void setCreateDate(java.util.Date createDate) {
        _discussionCategory.setCreateDate(createDate);
    }

    /**
    * Returns the deleted of this discussion category.
    *
    * @return the deleted of this discussion category
    */
    public java.util.Date getDeleted() {
        return _discussionCategory.getDeleted();
    }

    /**
    * Sets the deleted of this discussion category.
    *
    * @param deleted the deleted of this discussion category
    */
    public void setDeleted(java.util.Date deleted) {
        _discussionCategory.setDeleted(deleted);
    }

    /**
    * Returns the threads count of this discussion category.
    *
    * @return the threads count of this discussion category
    */
    public int getThreadsCount() {
        return _discussionCategory.getThreadsCount();
    }

    /**
    * Sets the threads count of this discussion category.
    *
    * @param threadsCount the threads count of this discussion category
    */
    public void setThreadsCount(int threadsCount) {
        _discussionCategory.setThreadsCount(threadsCount);
    }

    /**
    * Returns the last activity date of this discussion category.
    *
    * @return the last activity date of this discussion category
    */
    public java.util.Date getLastActivityDate() {
        return _discussionCategory.getLastActivityDate();
    }

    /**
    * Sets the last activity date of this discussion category.
    *
    * @param lastActivityDate the last activity date of this discussion category
    */
    public void setLastActivityDate(java.util.Date lastActivityDate) {
        _discussionCategory.setLastActivityDate(lastActivityDate);
    }

    /**
    * Returns the last activity author ID of this discussion category.
    *
    * @return the last activity author ID of this discussion category
    */
    public long getLastActivityAuthorId() {
        return _discussionCategory.getLastActivityAuthorId();
    }

    /**
    * Sets the last activity author ID of this discussion category.
    *
    * @param lastActivityAuthorId the last activity author ID of this discussion category
    */
    public void setLastActivityAuthorId(long lastActivityAuthorId) {
        _discussionCategory.setLastActivityAuthorId(lastActivityAuthorId);
    }

    public boolean isNew() {
        return _discussionCategory.isNew();
    }

    public void setNew(boolean n) {
        _discussionCategory.setNew(n);
    }

    public boolean isCachedModel() {
        return _discussionCategory.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _discussionCategory.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _discussionCategory.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _discussionCategory.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _discussionCategory.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _discussionCategory.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _discussionCategory.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new DiscussionCategoryWrapper((DiscussionCategory) _discussionCategory.clone());
    }

    public int compareTo(DiscussionCategory discussionCategory) {
        return _discussionCategory.compareTo(discussionCategory);
    }

    @Override
    public int hashCode() {
        return _discussionCategory.hashCode();
    }

    public com.liferay.portal.model.CacheModel<DiscussionCategory> toCacheModel() {
        return _discussionCategory.toCacheModel();
    }

    public DiscussionCategory toEscapedModel() {
        return new DiscussionCategoryWrapper(_discussionCategory.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _discussionCategory.toString();
    }

    public java.lang.String toXmlString() {
        return _discussionCategory.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionCategory.persist();
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public DiscussionCategory getWrappedDiscussionCategory() {
        return _discussionCategory;
    }

    public DiscussionCategory getWrappedModel() {
        return _discussionCategory;
    }

    public void resetOriginalValues() {
        _discussionCategory.resetOriginalValues();
    }
}
