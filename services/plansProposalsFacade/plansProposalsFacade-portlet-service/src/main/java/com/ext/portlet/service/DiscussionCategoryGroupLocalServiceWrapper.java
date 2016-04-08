package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DiscussionCategoryGroupLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionCategoryGroupLocalService
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
    @Override
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
    @Override
    public com.ext.portlet.model.DiscussionCategoryGroup createDiscussionCategoryGroup(
        long id) {
        return _discussionCategoryGroupLocalService.createDiscussionCategoryGroup(id);
    }

    /**
    * Deletes the discussion category group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the discussion category group
    * @return the discussion category group that was removed
    * @throws PortalException if a discussion category group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.DiscussionCategoryGroup deleteDiscussionCategoryGroup(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.deleteDiscussionCategoryGroup(id);
    }

    /**
    * Deletes the discussion category group from the database. Also notifies the appropriate model listeners.
    *
    * @param discussionCategoryGroup the discussion category group
    * @return the discussion category group that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.DiscussionCategoryGroup deleteDiscussionCategoryGroup(
        com.ext.portlet.model.DiscussionCategoryGroup discussionCategoryGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.deleteDiscussionCategoryGroup(discussionCategoryGroup);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _discussionCategoryGroupLocalService.dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionCategoryGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionCategoryGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
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
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
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
    @Override
    public com.ext.portlet.model.DiscussionCategoryGroup getDiscussionCategoryGroup(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.getDiscussionCategoryGroup(id);
    }

    @Override
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionCategoryGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of discussion category groups
    * @param end the upper bound of the range of discussion category groups (not inclusive)
    * @return the range of discussion category groups
    * @throws SystemException if a system exception occurred
    */
    @Override
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
    @Override
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
    @Override
    public com.ext.portlet.model.DiscussionCategoryGroup updateDiscussionCategoryGroup(
        com.ext.portlet.model.DiscussionCategoryGroup discussionCategoryGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.updateDiscussionCategoryGroup(discussionCategoryGroup);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _discussionCategoryGroupLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _discussionCategoryGroupLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _discussionCategoryGroupLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public com.ext.portlet.model.DiscussionCategoryGroup createDiscussionCategoryGroup(
        java.lang.String description)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.createDiscussionCategoryGroup(description);
    }

    @Override
    public com.ext.portlet.model.DiscussionCategory getCategoryById(
        long categoryId)
        throws com.ext.portlet.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.getCategoryById(categoryId);
    }

    @Override
    public com.ext.portlet.model.DiscussionMessage getThreadById(long threadId)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.getThreadById(threadId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.DiscussionCategory> getCategories(
        com.ext.portlet.model.DiscussionCategoryGroup dcg)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.getCategories(dcg);
    }

    @Override
    public com.ext.portlet.model.DiscussionCategory addCategory(
        com.ext.portlet.model.DiscussionCategoryGroup dcg,
        java.lang.String name, java.lang.String description,
        com.liferay.portal.model.User creator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.addCategory(dcg, name,
            description, creator);
    }

    @Override
    public void store(com.ext.portlet.model.DiscussionCategoryGroup dcg)
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionCategoryGroupLocalService.store(dcg);
    }

    @Override
    public com.ext.portlet.model.DiscussionMessage getCommentThread(
        com.ext.portlet.model.DiscussionCategoryGroup dcg)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.getCommentThread(dcg);
    }

    @Override
    public com.ext.portlet.model.DiscussionMessage addComment(
        com.ext.portlet.model.DiscussionCategoryGroup dcg,
        java.lang.String title, java.lang.String description,
        com.liferay.portal.model.User author)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.addComment(dcg, title,
            description, author);
    }

    @Override
    public int getCommentsCount(long discussionId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.getCommentsCount(discussionId);
    }

    @Override
    public int getCommentsCount(
        com.ext.portlet.model.DiscussionCategoryGroup dcg)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.getCommentsCount(dcg);
    }

    @Override
    public void copyEverything(
        com.ext.portlet.model.DiscussionCategoryGroup dcg,
        com.ext.portlet.model.DiscussionCategoryGroup source)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _discussionCategoryGroupLocalService.copyEverything(dcg, source);
    }

    @Override
    public int getUserMessages(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.getUserMessages(userId);
    }

    @Override
    public void subscribe(long userId, long discussionCategoryGroupId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _discussionCategoryGroupLocalService.subscribe(userId,
            discussionCategoryGroupId);
    }

    @Override
    public void unsubscribe(long userId, long discussionCategoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionCategoryGroupLocalService.unsubscribe(userId,
            discussionCategoryGroupId);
    }

    @Override
    public boolean isSubscribed(long userId, long discussionCategoryGroupId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryGroupLocalService.isSubscribed(userId,
            discussionCategoryGroupId);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public DiscussionCategoryGroupLocalService getWrappedDiscussionCategoryGroupLocalService() {
        return _discussionCategoryGroupLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedDiscussionCategoryGroupLocalService(
        DiscussionCategoryGroupLocalService discussionCategoryGroupLocalService) {
        _discussionCategoryGroupLocalService = discussionCategoryGroupLocalService;
    }

    @Override
    public DiscussionCategoryGroupLocalService getWrappedService() {
        return _discussionCategoryGroupLocalService;
    }

    @Override
    public void setWrappedService(
        DiscussionCategoryGroupLocalService discussionCategoryGroupLocalService) {
        _discussionCategoryGroupLocalService = discussionCategoryGroupLocalService;
    }
}
