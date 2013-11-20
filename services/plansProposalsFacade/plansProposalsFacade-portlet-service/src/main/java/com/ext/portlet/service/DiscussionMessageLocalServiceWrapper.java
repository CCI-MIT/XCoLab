package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DiscussionMessageLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionMessageLocalService
 * @generated
 */
public class DiscussionMessageLocalServiceWrapper
    implements DiscussionMessageLocalService,
        ServiceWrapper<DiscussionMessageLocalService> {
    private DiscussionMessageLocalService _discussionMessageLocalService;

    public DiscussionMessageLocalServiceWrapper(
        DiscussionMessageLocalService discussionMessageLocalService) {
        _discussionMessageLocalService = discussionMessageLocalService;
    }

    /**
    * Adds the discussion message to the database. Also notifies the appropriate model listeners.
    *
    * @param discussionMessage the discussion message
    * @return the discussion message that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.DiscussionMessage addDiscussionMessage(
        com.ext.portlet.model.DiscussionMessage discussionMessage)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.addDiscussionMessage(discussionMessage);
    }

    /**
    * Creates a new discussion message with the primary key. Does not add the discussion message to the database.
    *
    * @param pk the primary key for the new discussion message
    * @return the new discussion message
    */
    @Override
    public com.ext.portlet.model.DiscussionMessage createDiscussionMessage(
        long pk) {
        return _discussionMessageLocalService.createDiscussionMessage(pk);
    }

    /**
    * Deletes the discussion message with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param pk the primary key of the discussion message
    * @return the discussion message that was removed
    * @throws PortalException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.DiscussionMessage deleteDiscussionMessage(
        long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.deleteDiscussionMessage(pk);
    }

    /**
    * Deletes the discussion message from the database. Also notifies the appropriate model listeners.
    *
    * @param discussionMessage the discussion message
    * @return the discussion message that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.DiscussionMessage deleteDiscussionMessage(
        com.ext.portlet.model.DiscussionMessage discussionMessage)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.deleteDiscussionMessage(discussionMessage);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _discussionMessageLocalService.dynamicQuery();
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
        return _discussionMessageLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _discussionMessageLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _discussionMessageLocalService.dynamicQuery(dynamicQuery, start,
            end, orderByComparator);
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
        return _discussionMessageLocalService.dynamicQueryCount(dynamicQuery);
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
        return _discussionMessageLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.DiscussionMessage fetchDiscussionMessage(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.fetchDiscussionMessage(pk);
    }

    /**
    * Returns the discussion message with the primary key.
    *
    * @param pk the primary key of the discussion message
    * @return the discussion message
    * @throws PortalException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.DiscussionMessage getDiscussionMessage(long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.getDiscussionMessage(pk);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the discussion messages.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @return the range of discussion messages
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.DiscussionMessage> getDiscussionMessages(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.getDiscussionMessages(start, end);
    }

    /**
    * Returns the number of discussion messages.
    *
    * @return the number of discussion messages
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getDiscussionMessagesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.getDiscussionMessagesCount();
    }

    /**
    * Updates the discussion message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param discussionMessage the discussion message
    * @return the discussion message that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.DiscussionMessage updateDiscussionMessage(
        com.ext.portlet.model.DiscussionMessage discussionMessage)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.updateDiscussionMessage(discussionMessage);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _discussionMessageLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _discussionMessageLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _discussionMessageLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.util.List<com.ext.portlet.model.DiscussionMessage> getThreadsByCategory(
        long categoryId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.getThreadsByCategory(categoryId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.DiscussionMessage> getThreadMessages(
        long threadId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.getThreadMessages(threadId);
    }

    @Override
    public int getThreadMessagesCount(long threadId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.getThreadMessagesCount(threadId);
    }

    @Override
    public com.ext.portlet.model.DiscussionMessage getThreadByThreadId(
        long threadId)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.getThreadByThreadId(threadId);
    }

    @Override
    public com.ext.portlet.model.DiscussionMessage addThread(
        long categoryGroupId, long categoryId, java.lang.String subject,
        java.lang.String body, com.liferay.portal.model.User author)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.addThread(categoryGroupId,
            categoryId, subject, body, author);
    }

    @Override
    public com.ext.portlet.model.DiscussionMessage addMessage(
        long categoryGroupId, long categoryId, long threadId,
        java.lang.String subject, java.lang.String body,
        com.liferay.portal.model.User author)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.addMessage(categoryGroupId,
            categoryId, threadId, subject, body, author);
    }

    @Override
    public java.util.List<com.ext.portlet.model.DiscussionMessage> search(
        java.lang.String query, long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.search(query, categoryGroupId);
    }

    @Override
    public com.ext.portlet.model.DiscussionMessage getMessageByMessageId(
        long messageId)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.getMessageByMessageId(messageId);
    }

    @Override
    public void reIndex()
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionMessageLocalService.reIndex();
    }

    @Override
    public void reIndex(long messageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionMessageLocalService.reIndex(messageId);
    }

    @Override
    public java.util.List<com.ext.portlet.model.DiscussionMessage> getThreadMessages(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.getThreadMessages(dMessage);
    }

    @Override
    public int getThreadMessagesCount(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.getThreadMessagesCount(dMessage);
    }

    @Override
    public void store(com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionMessageLocalService.store(dMessage);
    }

    @Override
    public com.ext.portlet.model.DiscussionMessage addThreadMessage(
        com.ext.portlet.model.DiscussionMessage dMessage,
        java.lang.String subject, java.lang.String body,
        com.liferay.portal.model.User author)
        throws com.ext.portlet.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.addThreadMessage(dMessage,
            subject, body, author);
    }

    @Override
    public com.liferay.portal.model.User getAuthor(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.getAuthor(dMessage);
    }

    @Override
    public com.liferay.portal.model.User getLastActivityAuthor(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.getLastActivityAuthor(dMessage);
    }

    @Override
    public void delete(com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _discussionMessageLocalService.delete(dMessage);
    }

    @Override
    public void update(com.ext.portlet.model.DiscussionMessage dMessage,
        java.lang.String subject, java.lang.String body)
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionMessageLocalService.update(dMessage, subject, body);
    }

    @Override
    public com.ext.portlet.model.DiscussionCategory getCategory(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.ext.portlet.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.getCategory(dMessage);
    }

    @Override
    public com.ext.portlet.model.DiscussionCategoryGroup getCategoryGroup(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.getCategoryGroup(dMessage);
    }

    @Override
    public com.ext.portlet.model.DiscussionMessage getThread(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.getThread(dMessage);
    }

    @Override
    public java.util.List<com.ext.portlet.model.DiscussionMessageFlag> getFlags(
        com.ext.portlet.model.DiscussionMessage dMessage)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.getFlags(dMessage);
    }

    @Override
    public void addFlag(com.ext.portlet.model.DiscussionMessage dMessage,
        java.lang.String flagType, java.lang.String data,
        com.liferay.portal.model.User user)
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionMessageLocalService.addFlag(dMessage, flagType, data, user);
    }

    @Override
    public void removeFlag(com.ext.portlet.model.DiscussionMessage dMessage,
        java.lang.String flagType)
        throws com.liferay.portal.kernel.exception.SystemException {
        _discussionMessageLocalService.removeFlag(dMessage, flagType);
    }

    @Override
    public boolean hasFlag(long messageId, java.lang.String flag)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _discussionMessageLocalService.hasFlag(messageId, flag);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public DiscussionMessageLocalService getWrappedDiscussionMessageLocalService() {
        return _discussionMessageLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedDiscussionMessageLocalService(
        DiscussionMessageLocalService discussionMessageLocalService) {
        _discussionMessageLocalService = discussionMessageLocalService;
    }

    @Override
    public DiscussionMessageLocalService getWrappedService() {
        return _discussionMessageLocalService;
    }

    @Override
    public void setWrappedService(
        DiscussionMessageLocalService discussionMessageLocalService) {
        _discussionMessageLocalService = discussionMessageLocalService;
    }
}
