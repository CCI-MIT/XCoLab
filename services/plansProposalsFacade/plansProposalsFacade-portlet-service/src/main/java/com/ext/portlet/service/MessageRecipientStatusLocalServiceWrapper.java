package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MessageRecipientStatusLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessageRecipientStatusLocalService
 * @generated
 */
public class MessageRecipientStatusLocalServiceWrapper
    implements MessageRecipientStatusLocalService,
        ServiceWrapper<MessageRecipientStatusLocalService> {
    private MessageRecipientStatusLocalService _messageRecipientStatusLocalService;

    public MessageRecipientStatusLocalServiceWrapper(
        MessageRecipientStatusLocalService messageRecipientStatusLocalService) {
        _messageRecipientStatusLocalService = messageRecipientStatusLocalService;
    }

    /**
    * Adds the message recipient status to the database. Also notifies the appropriate model listeners.
    *
    * @param messageRecipientStatus the message recipient status
    * @return the message recipient status that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus addMessageRecipientStatus(
        com.ext.portlet.model.MessageRecipientStatus messageRecipientStatus)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messageRecipientStatusLocalService.addMessageRecipientStatus(messageRecipientStatus);
    }

    /**
    * Creates a new message recipient status with the primary key. Does not add the message recipient status to the database.
    *
    * @param messageRecipientId the primary key for the new message recipient status
    * @return the new message recipient status
    */
    public com.ext.portlet.model.MessageRecipientStatus createMessageRecipientStatus(
        long messageRecipientId) {
        return _messageRecipientStatusLocalService.createMessageRecipientStatus(messageRecipientId);
    }

    /**
    * Deletes the message recipient status with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param messageRecipientId the primary key of the message recipient status
    * @throws PortalException if a message recipient status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteMessageRecipientStatus(long messageRecipientId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _messageRecipientStatusLocalService.deleteMessageRecipientStatus(messageRecipientId);
    }

    /**
    * Deletes the message recipient status from the database. Also notifies the appropriate model listeners.
    *
    * @param messageRecipientStatus the message recipient status
    * @throws SystemException if a system exception occurred
    */
    public void deleteMessageRecipientStatus(
        com.ext.portlet.model.MessageRecipientStatus messageRecipientStatus)
        throws com.liferay.portal.kernel.exception.SystemException {
        _messageRecipientStatusLocalService.deleteMessageRecipientStatus(messageRecipientStatus);
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
        return _messageRecipientStatusLocalService.dynamicQuery(dynamicQuery);
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
        return _messageRecipientStatusLocalService.dynamicQuery(dynamicQuery,
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
        return _messageRecipientStatusLocalService.dynamicQuery(dynamicQuery,
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
        return _messageRecipientStatusLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.model.MessageRecipientStatus fetchMessageRecipientStatus(
        long messageRecipientId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messageRecipientStatusLocalService.fetchMessageRecipientStatus(messageRecipientId);
    }

    /**
    * Returns the message recipient status with the primary key.
    *
    * @param messageRecipientId the primary key of the message recipient status
    * @return the message recipient status
    * @throws PortalException if a message recipient status with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus getMessageRecipientStatus(
        long messageRecipientId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _messageRecipientStatusLocalService.getMessageRecipientStatus(messageRecipientId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _messageRecipientStatusLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the message recipient statuses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of message recipient statuses
    * @param end the upper bound of the range of message recipient statuses (not inclusive)
    * @return the range of message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MessageRecipientStatus> getMessageRecipientStatuses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messageRecipientStatusLocalService.getMessageRecipientStatuses(start,
            end);
    }

    /**
    * Returns the number of message recipient statuses.
    *
    * @return the number of message recipient statuses
    * @throws SystemException if a system exception occurred
    */
    public int getMessageRecipientStatusesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messageRecipientStatusLocalService.getMessageRecipientStatusesCount();
    }

    /**
    * Updates the message recipient status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param messageRecipientStatus the message recipient status
    * @return the message recipient status that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus updateMessageRecipientStatus(
        com.ext.portlet.model.MessageRecipientStatus messageRecipientStatus)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messageRecipientStatusLocalService.updateMessageRecipientStatus(messageRecipientStatus);
    }

    /**
    * Updates the message recipient status in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param messageRecipientStatus the message recipient status
    * @param merge whether to merge the message recipient status with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the message recipient status that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MessageRecipientStatus updateMessageRecipientStatus(
        com.ext.portlet.model.MessageRecipientStatus messageRecipientStatus,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messageRecipientStatusLocalService.updateMessageRecipientStatus(messageRecipientStatus,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _messageRecipientStatusLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _messageRecipientStatusLocalService.setBeanIdentifier(beanIdentifier);
    }

    public int countByMessageId(long messageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messageRecipientStatusLocalService.countByMessageId(messageId);
    }

    public java.util.List<com.ext.portlet.model.MessageRecipientStatus> findByMessageId(
        long messageId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messageRecipientStatusLocalService.findByMessageId(messageId,
            start, end);
    }

    public int countArchivedMessagesForUser(long userid)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messageRecipientStatusLocalService.countArchivedMessagesForUser(userid);
    }

    public java.util.List<com.ext.portlet.model.MessageRecipientStatus> findArchivedMessagesForUser(
        long userid, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messageRecipientStatusLocalService.findArchivedMessagesForUser(userid,
            start, end);
    }

    public int countInboxMessagesForUser(long userid)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messageRecipientStatusLocalService.countInboxMessagesForUser(userid);
    }

    public java.util.List<com.ext.portlet.model.MessageRecipientStatus> findInboxMessagesForUser(
        long userid, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _messageRecipientStatusLocalService.findInboxMessagesForUser(userid,
            start, end);
    }

    public com.ext.portlet.model.MessageRecipientStatus findByMessageRecipient(
        long userid, long messageid)
        throws com.ext.portlet.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        return _messageRecipientStatusLocalService.findByMessageRecipient(userid,
            messageid);
    }

    public int countUnreadMessages(long userId)
        throws com.ext.portlet.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        return _messageRecipientStatusLocalService.countUnreadMessages(userId);
    }

    public boolean didReceiveJudgeCommentForProposal(
        com.ext.portlet.model.Proposal p, com.liferay.portal.model.User judge)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _messageRecipientStatusLocalService.didReceiveJudgeCommentForProposal(p,
            judge);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public MessageRecipientStatusLocalService getWrappedMessageRecipientStatusLocalService() {
        return _messageRecipientStatusLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedMessageRecipientStatusLocalService(
        MessageRecipientStatusLocalService messageRecipientStatusLocalService) {
        _messageRecipientStatusLocalService = messageRecipientStatusLocalService;
    }

    public MessageRecipientStatusLocalService getWrappedService() {
        return _messageRecipientStatusLocalService;
    }

    public void setWrappedService(
        MessageRecipientStatusLocalService messageRecipientStatusLocalService) {
        _messageRecipientStatusLocalService = messageRecipientStatusLocalService;
    }
}
