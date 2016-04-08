package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DiscussionCategoryLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionCategoryLocalService
 * @generated
 */
public class DiscussionCategoryLocalServiceWrapper
    implements DiscussionCategoryLocalService,
        ServiceWrapper<DiscussionCategoryLocalService> {
    private DiscussionCategoryLocalService _discussionCategoryLocalService;

    public DiscussionCategoryLocalServiceWrapper(
        DiscussionCategoryLocalService discussionCategoryLocalService) {
        _discussionCategoryLocalService = discussionCategoryLocalService;
    }

    /**
    * Adds the discussion category to the database. Also notifies the appropriate model listeners.
    *
    * @param discussionCategory the discussion category
    * @return the discussion category that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.DiscussionCategory addDiscussionCategory(
        com.ext.portlet.model.DiscussionCategory discussionCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryLocalService.addDiscussionCategory(discussionCategory);
    }

    /**
    * Creates a new discussion category with the primary key. Does not add the discussion category to the database.
    *
    * @param pk the primary key for the new discussion category
    * @return the new discussion category
    */
    @Override
    public com.ext.portlet.model.DiscussionCategory createDiscussionCategory(
        long pk) {
        return _discussionCategoryLocalService.createDiscussionCategory(pk);
    }

    /**
    * Deletes the discussion category with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param pk the primary key of the discussion category
    * @return the discussion category that was removed
    * @throws PortalException if a discussion category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.DiscussionCategory deleteDiscussionCategory(
        long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryLocalService.deleteDiscussionCategory(pk);
    }

    /**
    * Deletes the discussion category from the database. Also notifies the appropriate model listeners.
    *
    * @param discussionCategory the discussion category
    * @return the discussion category that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.DiscussionCategory deleteDiscussionCategory(
        com.ext.portlet.model.DiscussionCategory discussionCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryLocalService.deleteDiscussionCategory(discussionCategory);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _discussionCategoryLocalService.dynamicQuery();
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
        return _discussionCategoryLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _discussionCategoryLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _discussionCategoryLocalService.dynamicQuery(dynamicQuery,
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
        return _discussionCategoryLocalService.dynamicQueryCount(dynamicQuery);
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
        return _discussionCategoryLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.DiscussionCategory fetchDiscussionCategory(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryLocalService.fetchDiscussionCategory(pk);
    }

    /**
    * Returns the discussion category with the primary key.
    *
    * @param pk the primary key of the discussion category
    * @return the discussion category
    * @throws PortalException if a discussion category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.DiscussionCategory getDiscussionCategory(
        long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryLocalService.getDiscussionCategory(pk);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the discussion categories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of discussion categories
    * @param end the upper bound of the range of discussion categories (not inclusive)
    * @return the range of discussion categories
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.DiscussionCategory> getDiscussionCategories(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryLocalService.getDiscussionCategories(start,
            end);
    }

    /**
    * Returns the number of discussion categories.
    *
    * @return the number of discussion categories
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getDiscussionCategoriesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryLocalService.getDiscussionCategoriesCount();
    }

    /**
    * Updates the discussion category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param discussionCategory the discussion category
    * @return the discussion category that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.DiscussionCategory updateDiscussionCategory(
        com.ext.portlet.model.DiscussionCategory discussionCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryLocalService.updateDiscussionCategory(discussionCategory);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _discussionCategoryLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _discussionCategoryLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _discussionCategoryLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.util.List<com.ext.portlet.model.DiscussionCategory> getCategoriesByCategoryGroupId(
        long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryLocalService.getCategoriesByCategoryGroupId(categoryGroupId);
    }

    @Override
    public com.ext.portlet.model.DiscussionCategory getDiscussionCategoryById(
        long categoryId)
        throws com.ext.portlet.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryLocalService.getDiscussionCategoryById(categoryId);
    }

    @Override
    public com.ext.portlet.model.DiscussionCategory createDiscussionCategory(
        long categoryGroupId, java.lang.String name,
        java.lang.String description, com.liferay.portal.model.User author)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryLocalService.createDiscussionCategory(categoryGroupId,
            name, description, author);
    }

    @Override
    public java.util.List<com.ext.portlet.model.DiscussionMessage> getThreads(
        com.ext.portlet.model.DiscussionCategory dCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryLocalService.getThreads(dCategory);
    }

    @Override
    public com.ext.portlet.model.DiscussionMessage addThread(
        com.ext.portlet.model.DiscussionCategory dCategory,
        java.lang.String subject, java.lang.String body,
        com.liferay.portal.model.User author)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryLocalService.addThread(dCategory, subject,
            body, author);
    }

    @Override
    public void store(com.ext.portlet.model.DiscussionCategory dCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionCategoryLocalService.store(dCategory);
    }

    @Override
    public com.liferay.portal.model.User getAuthor(
        com.ext.portlet.model.DiscussionCategory dCategory)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryLocalService.getAuthor(dCategory);
    }

    @Override
    public com.liferay.portal.model.User getLastActivityAuthor(
        com.ext.portlet.model.DiscussionCategory dCategory)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryLocalService.getLastActivityAuthor(dCategory);
    }

    @Override
    public void delete(com.ext.portlet.model.DiscussionCategory dCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionCategoryLocalService.delete(dCategory);
    }

    @Override
    public void update(com.ext.portlet.model.DiscussionCategory dCategory,
        java.lang.String name, java.lang.String description)
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionCategoryLocalService.update(dCategory, name, description);
    }

    @Override
    public com.ext.portlet.model.DiscussionCategoryGroup getCategoryGroup(
        com.ext.portlet.model.DiscussionCategory dCategory)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryLocalService.getCategoryGroup(dCategory);
    }

    @Override
    public void subscribe(long userId, long categoryGroupId, long categoryId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _discussionCategoryLocalService.subscribe(userId, categoryGroupId,
            categoryId);
    }

    @Override
    public void unsubscribe(long userId, long discussionCategoryGroupId,
        long categoryId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionCategoryLocalService.unsubscribe(userId,
            discussionCategoryGroupId, categoryId);
    }

    @Override
    public boolean isSubscribed(long userId, long discussionCategoryGroupId,
        long categoryId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionCategoryLocalService.isSubscribed(userId,
            discussionCategoryGroupId, categoryId);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public DiscussionCategoryLocalService getWrappedDiscussionCategoryLocalService() {
        return _discussionCategoryLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedDiscussionCategoryLocalService(
        DiscussionCategoryLocalService discussionCategoryLocalService) {
        _discussionCategoryLocalService = discussionCategoryLocalService;
    }

    @Override
    public DiscussionCategoryLocalService getWrappedService() {
        return _discussionCategoryLocalService;
    }

    @Override
    public void setWrappedService(
        DiscussionCategoryLocalService discussionCategoryLocalService) {
        _discussionCategoryLocalService = discussionCategoryLocalService;
    }
}
