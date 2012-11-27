package com.ext.portlet.discussions.model;

import com.liferay.portal.model.ModelWrapper;

/**
 * <p>
 * This class is a wrapper for {@link DiscussionCategoryGroup}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DiscussionCategoryGroup
 * @generated
 */
public class DiscussionCategoryGroupWrapper implements DiscussionCategoryGroup,
    ModelWrapper<DiscussionCategoryGroup> {
    private DiscussionCategoryGroup _discussionCategoryGroup;

    public DiscussionCategoryGroupWrapper(
        DiscussionCategoryGroup discussionCategoryGroup) {
        _discussionCategoryGroup = discussionCategoryGroup;
    }

    public Class<?> getModelClass() {
        return DiscussionCategoryGroup.class;
    }

    public String getModelClassName() {
        return DiscussionCategoryGroup.class.getName();
    }

    /**
    * Returns the primary key of this discussion category group.
    *
    * @return the primary key of this discussion category group
    */
    public java.lang.Long getPrimaryKey() {
        return _discussionCategoryGroup.getPrimaryKey();
    }

    /**
    * Sets the primary key of this discussion category group.
    *
    * @param primaryKey the primary key of this discussion category group
    */
    public void setPrimaryKey(java.lang.Long primaryKey) {
        _discussionCategoryGroup.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the ID of this discussion category group.
    *
    * @return the ID of this discussion category group
    */
    public java.lang.Long getId() {
        return _discussionCategoryGroup.getId();
    }

    /**
    * Sets the ID of this discussion category group.
    *
    * @param id the ID of this discussion category group
    */
    public void setId(java.lang.Long id) {
        _discussionCategoryGroup.setId(id);
    }

    /**
    * Returns the description of this discussion category group.
    *
    * @return the description of this discussion category group
    */
    public java.lang.String getDescription() {
        return _discussionCategoryGroup.getDescription();
    }

    /**
    * Sets the description of this discussion category group.
    *
    * @param description the description of this discussion category group
    */
    public void setDescription(java.lang.String description) {
        _discussionCategoryGroup.setDescription(description);
    }

    /**
    * Returns the url of this discussion category group.
    *
    * @return the url of this discussion category group
    */
    public java.lang.String getUrl() {
        return _discussionCategoryGroup.getUrl();
    }

    /**
    * Sets the url of this discussion category group.
    *
    * @param url the url of this discussion category group
    */
    public void setUrl(java.lang.String url) {
        _discussionCategoryGroup.setUrl(url);
    }

    /**
    * Returns the comments thread of this discussion category group.
    *
    * @return the comments thread of this discussion category group
    */
    public java.lang.Long getCommentsThread() {
        return _discussionCategoryGroup.getCommentsThread();
    }

    /**
    * Sets the comments thread of this discussion category group.
    *
    * @param commentsThread the comments thread of this discussion category group
    */
    public void setCommentsThread(java.lang.Long commentsThread) {
        _discussionCategoryGroup.setCommentsThread(commentsThread);
    }

    public boolean isNew() {
        return _discussionCategoryGroup.isNew();
    }

    public void setNew(boolean n) {
        _discussionCategoryGroup.setNew(n);
    }

    public boolean isCachedModel() {
        return _discussionCategoryGroup.isCachedModel();
    }

    public void setCachedModel(boolean cachedModel) {
        _discussionCategoryGroup.setCachedModel(cachedModel);
    }

    public boolean isEscapedModel() {
        return _discussionCategoryGroup.isEscapedModel();
    }

    public java.io.Serializable getPrimaryKeyObj() {
        return _discussionCategoryGroup.getPrimaryKeyObj();
    }

    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _discussionCategoryGroup.setPrimaryKeyObj(primaryKeyObj);
    }

    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _discussionCategoryGroup.getExpandoBridge();
    }

    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _discussionCategoryGroup.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new DiscussionCategoryGroupWrapper((DiscussionCategoryGroup) _discussionCategoryGroup.clone());
    }

    public int compareTo(DiscussionCategoryGroup discussionCategoryGroup) {
        return _discussionCategoryGroup.compareTo(discussionCategoryGroup);
    }

    @Override
    public int hashCode() {
        return _discussionCategoryGroup.hashCode();
    }

    public com.liferay.portal.model.CacheModel<DiscussionCategoryGroup> toCacheModel() {
        return _discussionCategoryGroup.toCacheModel();
    }

    public DiscussionCategoryGroup toEscapedModel() {
        return new DiscussionCategoryGroupWrapper(_discussionCategoryGroup.toEscapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _discussionCategoryGroup.toString();
    }

    public java.lang.String toXmlString() {
        return _discussionCategoryGroup.toXmlString();
    }

    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionCategoryGroup.persist();
    }

    public com.ext.portlet.discussions.model.DiscussionCategory getCategoryById(
        java.lang.Long categoryId)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroup.getCategoryById(categoryId);
    }

    public com.ext.portlet.discussions.model.DiscussionMessage getThreadById(
        java.lang.Long threadId)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroup.getThreadById(threadId);
    }

    public java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> getCategories()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroup.getCategories();
    }

    public com.ext.portlet.discussions.model.DiscussionCategory addCategory(
        java.lang.String name, java.lang.String description,
        com.liferay.portal.model.User creator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroup.addCategory(name, description, creator);
    }

    public void store()
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionCategoryGroup.store();
    }

    public com.ext.portlet.discussions.model.DiscussionMessage getCommentThread()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroup.getCommentThread();
    }

    public com.ext.portlet.discussions.model.DiscussionMessage addComment(
        java.lang.String title, java.lang.String description,
        com.liferay.portal.model.User author)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroup.addComment(title, description, author);
    }

    public int getCommentsCount()
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroup.getCommentsCount();
    }

    public void copyEverything(
        com.ext.portlet.discussions.model.DiscussionCategoryGroup source)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _discussionCategoryGroup.copyEverything(source);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedModel}
     */
    public DiscussionCategoryGroup getWrappedDiscussionCategoryGroup() {
        return _discussionCategoryGroup;
    }

    public DiscussionCategoryGroup getWrappedModel() {
        return _discussionCategoryGroup;
    }

    public void resetOriginalValues() {
        _discussionCategoryGroup.resetOriginalValues();
    }
}
