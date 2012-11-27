package com.ext.portlet.discussions.model;

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
    public java.lang.Long getPrimaryKey() {
        return _discussionCategory.getPrimaryKey();
    }

    /**
    * Sets the primary key of this discussion category.
    *
    * @param primaryKey the primary key of this discussion category
    */
    public void setPrimaryKey(java.lang.Long primaryKey) {
        _discussionCategory.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the pk of this discussion category.
    *
    * @return the pk of this discussion category
    */
    public java.lang.Long getPk() {
        return _discussionCategory.getPk();
    }

    /**
    * Sets the pk of this discussion category.
    *
    * @param pk the pk of this discussion category
    */
    public void setPk(java.lang.Long pk) {
        _discussionCategory.setPk(pk);
    }

    /**
    * Returns the category ID of this discussion category.
    *
    * @return the category ID of this discussion category
    */
    public java.lang.Long getCategoryId() {
        return _discussionCategory.getCategoryId();
    }

    /**
    * Sets the category ID of this discussion category.
    *
    * @param categoryId the category ID of this discussion category
    */
    public void setCategoryId(java.lang.Long categoryId) {
        _discussionCategory.setCategoryId(categoryId);
    }

    /**
    * Returns the category group ID of this discussion category.
    *
    * @return the category group ID of this discussion category
    */
    public java.lang.Long getCategoryGroupId() {
        return _discussionCategory.getCategoryGroupId();
    }

    /**
    * Sets the category group ID of this discussion category.
    *
    * @param categoryGroupId the category group ID of this discussion category
    */
    public void setCategoryGroupId(java.lang.Long categoryGroupId) {
        _discussionCategory.setCategoryGroupId(categoryGroupId);
    }

    /**
    * Returns the author ID of this discussion category.
    *
    * @return the author ID of this discussion category
    */
    public java.lang.Long getAuthorId() {
        return _discussionCategory.getAuthorId();
    }

    /**
    * Sets the author ID of this discussion category.
    *
    * @param authorId the author ID of this discussion category
    */
    public void setAuthorId(java.lang.Long authorId) {
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
    public java.lang.Integer getThreadsCount() {
        return _discussionCategory.getThreadsCount();
    }

    /**
    * Sets the threads count of this discussion category.
    *
    * @param threadsCount the threads count of this discussion category
    */
    public void setThreadsCount(java.lang.Integer threadsCount) {
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
    public java.lang.Long getLastActivityAuthorId() {
        return _discussionCategory.getLastActivityAuthorId();
    }

    /**
    * Sets the last activity author ID of this discussion category.
    *
    * @param lastActivityAuthorId the last activity author ID of this discussion category
    */
    public void setLastActivityAuthorId(java.lang.Long lastActivityAuthorId) {
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

    public java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> getThreads()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategory.getThreads();
    }

    public com.ext.portlet.discussions.model.DiscussionMessage addThread(
        java.lang.String subject, java.lang.String body,
        com.liferay.portal.model.User author)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategory.addThread(subject, body, author);
    }

    public void store()
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionCategory.store();
    }

    public com.liferay.portal.model.User getAuthor()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategory.getAuthor();
    }

    public com.liferay.portal.model.User getLastActivityAuthor()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategory.getLastActivityAuthor();
    }

    public void delete()
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionCategory.delete();
    }

    public void update(java.lang.String name, java.lang.String description)
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionCategory.update(name, description);
    }

    public com.ext.portlet.discussions.model.DiscussionCategoryGroup getCategoryGroup()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategory.getCategoryGroup();
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
