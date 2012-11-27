package com.ext.portlet.discussions.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the discussion message local service. This utility wraps {@link com.ext.portlet.discussions.service.impl.DiscussionMessageLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionMessageLocalService
 * @see com.ext.portlet.discussions.service.base.DiscussionMessageLocalServiceBaseImpl
 * @see com.ext.portlet.discussions.service.impl.DiscussionMessageLocalServiceImpl
 * @generated
 */
public class DiscussionMessageLocalServiceUtil {
    private static DiscussionMessageLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.discussions.service.impl.DiscussionMessageLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the discussion message to the database. Also notifies the appropriate model listeners.
    *
    * @param discussionMessage the discussion message
    * @return the discussion message that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.discussions.model.DiscussionMessage addDiscussionMessage(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addDiscussionMessage(discussionMessage);
    }

    /**
    * Creates a new discussion message with the primary key. Does not add the discussion message to the database.
    *
    * @param pk the primary key for the new discussion message
    * @return the new discussion message
    */
    public static com.ext.portlet.discussions.model.DiscussionMessage createDiscussionMessage(
        java.lang.Long pk) {
        return getService().createDiscussionMessage(pk);
    }

    /**
    * Deletes the discussion message with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param pk the primary key of the discussion message
    * @throws PortalException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteDiscussionMessage(java.lang.Long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteDiscussionMessage(pk);
    }

    /**
    * Deletes the discussion message from the database. Also notifies the appropriate model listeners.
    *
    * @param discussionMessage the discussion message
    * @throws SystemException if a system exception occurred
    */
    public static void deleteDiscussionMessage(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteDiscussionMessage(discussionMessage);
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
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
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
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
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage fetchDiscussionMessage(
        java.lang.Long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchDiscussionMessage(pk);
    }

    /**
    * Returns the discussion message with the primary key.
    *
    * @param pk the primary key of the discussion message
    * @return the discussion message
    * @throws PortalException if a discussion message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.discussions.model.DiscussionMessage getDiscussionMessage(
        java.lang.Long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getDiscussionMessage(pk);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the discussion messages.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of discussion messages
    * @param end the upper bound of the range of discussion messages (not inclusive)
    * @return the range of discussion messages
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> getDiscussionMessages(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getDiscussionMessages(start, end);
    }

    /**
    * Returns the number of discussion messages.
    *
    * @return the number of discussion messages
    * @throws SystemException if a system exception occurred
    */
    public static int getDiscussionMessagesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getDiscussionMessagesCount();
    }

    /**
    * Updates the discussion message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param discussionMessage the discussion message
    * @return the discussion message that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.discussions.model.DiscussionMessage updateDiscussionMessage(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateDiscussionMessage(discussionMessage);
    }

    /**
    * Updates the discussion message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param discussionMessage the discussion message
    * @param merge whether to merge the discussion message with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the discussion message that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.discussions.model.DiscussionMessage updateDiscussionMessage(
        com.ext.portlet.discussions.model.DiscussionMessage discussionMessage,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateDiscussionMessage(discussionMessage, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> getThreadsByCategory(
        java.lang.Long categoryId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getThreadsByCategory(categoryId);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> getThreadMessages(
        java.lang.Long threadId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getThreadMessages(threadId);
    }

    public static int getThreadMessagesCount(java.lang.Long threadId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getThreadMessagesCount(threadId);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage getThreadByThreadId(
        java.lang.Long threadId)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getThreadByThreadId(threadId);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage addThread(
        java.lang.Long categoryGroupId, java.lang.Long categoryId,
        java.lang.String subject, java.lang.String body,
        com.liferay.portal.model.User author)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .addThread(categoryGroupId, categoryId, subject, body, author);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage addMessage(
        java.lang.Long categoryGroupId, java.lang.Long categoryId,
        java.lang.Long threadId, java.lang.String subject,
        java.lang.String body, com.liferay.portal.model.User author)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .addMessage(categoryGroupId, categoryId, threadId, subject,
            body, author);
    }

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionMessage> search(
        java.lang.String query, java.lang.Long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().search(query, categoryGroupId);
    }

    public static com.ext.portlet.discussions.model.DiscussionMessage getMessageByMessageId(
        java.lang.Long messageId)
        throws com.ext.portlet.discussions.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getMessageByMessageId(messageId);
    }

    public static void reIndex()
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().reIndex();
    }

    public static void reIndex(long messageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().reIndex(messageId);
    }

    public static void clearService() {
        _service = null;
    }

    public static DiscussionMessageLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    DiscussionMessageLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    DiscussionMessageLocalService.class.getName(),
                    portletClassLoader);

            _service = new DiscussionMessageLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(DiscussionMessageLocalServiceUtil.class,
                "_service");
            MethodCache.remove(DiscussionMessageLocalService.class);
        }

        return _service;
    }

    public void setService(DiscussionMessageLocalService service) {
        MethodCache.remove(DiscussionMessageLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(DiscussionMessageLocalServiceUtil.class,
            "_service");
        MethodCache.remove(DiscussionMessageLocalService.class);
    }
}
