package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link DiscussionCategoryGroupLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DiscussionCategoryGroupLocalService
 * @generated
 */
public class DiscussionCategoryGroupLocalServiceWrapper
    implements DiscussionCategoryGroupLocalService,
        ServiceWrapper<DiscussionCategoryGroupLocalService> {
    private DiscussionCategoryGroupLocalService _discussionCategoryGroupLocalService;

    public DiscussionCategoryGroupLocalServiceWrapper(
        DiscussionCategoryGroupLocalService discussionCategoryGroupLocalService) {
        _discussionCategoryGroupLocalService = discussionCategoryGroupLocalService;
    }

    /**
    * Adds the discussion category group to the database. Also notifies the appropriate model listeners.
    *
    * @param discussionCategoryGroup the discussion category group
    * @return the discussion category group that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionCategoryGroup addDiscussionCategoryGroup(
        com.ext.portlet.model.DiscussionCategoryGroup discussionCategoryGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.addDiscussionCategoryGroup(discussionCategoryGroup);
    }

    /**
    * Creates a new discussion category group with the primary key. Does not add the discussion category group to the database.
    *
    * @param id the primary key for the new discussion category group
    * @return the new discussion category group
    */
    public com.ext.portlet.model.DiscussionCategoryGroup createDiscussionCategoryGroup(
        long id) {
        return _discussionCategoryGroupLocalService.createDiscussionCategoryGroup(id);
    }

    /**
    * Deletes the discussion category group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the discussion category group
    * @throws PortalException if a discussion category group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteDiscussionCategoryGroup(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _discussionCategoryGroupLocalService.deleteDiscussionCategoryGroup(id);
    }

    /**
    * Deletes the discussion category group from the database. Also notifies the appropriate model listeners.
    *
    * @param discussionCategoryGroup the discussion category group
    * @throws SystemException if a system exception occurred
    */
    public void deleteDiscussionCategoryGroup(
        com.ext.portlet.model.DiscussionCategoryGroup discussionCategoryGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionCategoryGroupLocalService.deleteDiscussionCategoryGroup(discussionCategoryGroup);
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.dynamicQuery(dynamicQuery,
            start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.model.DiscussionCategoryGroup fetchDiscussionCategoryGroup(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.fetchDiscussionCategoryGroup(id);
    }

    /**
    * Returns the discussion category group with the primary key.
    *
    * @param id the primary key of the discussion category group
    * @return the discussion category group
    * @throws PortalException if a discussion category group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionCategoryGroup getDiscussionCategoryGroup(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.getDiscussionCategoryGroup(id);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the discussion category groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of discussion category groups
    * @param end the upper bound of the range of discussion category groups (not inclusive)
    * @return the range of discussion category groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.DiscussionCategoryGroup> getDiscussionCategoryGroups(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.getDiscussionCategoryGroups(start,
            end);
    }

    /**
    * Returns the number of discussion category groups.
    *
    * @return the number of discussion category groups
    * @throws SystemException if a system exception occurred
    */
    public int getDiscussionCategoryGroupsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.getDiscussionCategoryGroupsCount();
    }

    /**
    * Updates the discussion category group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param discussionCategoryGroup the discussion category group
    * @return the discussion category group that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionCategoryGroup updateDiscussionCategoryGroup(
        com.ext.portlet.model.DiscussionCategoryGroup discussionCategoryGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.updateDiscussionCategoryGroup(discussionCategoryGroup);
    }

    /**
    * Updates the discussion category group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param discussionCategoryGroup the discussion category group
    * @param merge whether to merge the discussion category group with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the discussion category group that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionCategoryGroup updateDiscussionCategoryGroup(
        com.ext.portlet.model.DiscussionCategoryGroup discussionCategoryGroup,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.updateDiscussionCategoryGroup(discussionCategoryGroup,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _discussionCategoryGroupLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _discussionCategoryGroupLocalService.setBeanIdentifier(beanIdentifier);
    }

    public com.ext.portlet.model.DiscussionCategoryGroup createDiscussionCategoryGroup(
        java.lang.String description)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.createDiscussionCategoryGroup(description);
    }

    public com.ext.portlet.model.DiscussionCategory getCategoryById(
        long categoryId)
        throws com.ext.portlet.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.getCategoryById(categoryId);
    }

    public com.ext.portlet.model.DiscussionMessage getThreadById(long threadId)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.getThreadById(threadId);
    }

    public java.util.List<com.ext.portlet.model.DiscussionCategory> getCategories(
        com.ext.portlet.model.DiscussionCategoryGroup dcg)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.getCategories(dcg);
    }

    public com.ext.portlet.model.DiscussionCategory addCategory(
        com.ext.portlet.model.DiscussionCategoryGroup dcg,
        java.lang.String name, java.lang.String description,
        com.liferay.portal.model.User creator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.addCategory(dcg, name,
            description, creator);
    }

    public void store(com.ext.portlet.model.DiscussionCategoryGroup dcg)
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionCategoryGroupLocalService.store(dcg);
    }

    public com.ext.portlet.model.DiscussionMessage getCommentThread(
        com.ext.portlet.model.DiscussionCategoryGroup dcg)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.getCommentThread(dcg);
    }

    public com.ext.portlet.model.DiscussionMessage addComment(
        com.ext.portlet.model.DiscussionCategoryGroup dcg,
        java.lang.String title, java.lang.String description,
        com.liferay.portal.model.User author)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.addComment(dcg, title,
            description, author);
    }

    public int getCommentsCount(long discussionId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.getCommentsCount(discussionId);
    }

    public int getCommentsCount(
        com.ext.portlet.model.DiscussionCategoryGroup dcg)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.getCommentsCount(dcg);
    }

    public void copyEverything(
        com.ext.portlet.model.DiscussionCategoryGroup dcg,
        com.ext.portlet.model.DiscussionCategoryGroup source)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _discussionCategoryGroupLocalService.copyEverything(dcg, source);
    }

    public int getUserMessages(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.getUserMessages(userId);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public DiscussionCategoryGroupLocalService getWrappedDiscussionCategoryGroupLocalService() {
        return _discussionCategoryGroupLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedDiscussionCategoryGroupLocalService(
        DiscussionCategoryGroupLocalService discussionCategoryGroupLocalService) {
        _discussionCategoryGroupLocalService = discussionCategoryGroupLocalService;
    }

    public DiscussionCategoryGroupLocalService getWrappedService() {
        return _discussionCategoryGroupLocalService;
    }

    public void setWrappedService(
        DiscussionCategoryGroupLocalService discussionCategoryGroupLocalService) {
        _discussionCategoryGroupLocalService = discussionCategoryGroupLocalService;
    }
}
