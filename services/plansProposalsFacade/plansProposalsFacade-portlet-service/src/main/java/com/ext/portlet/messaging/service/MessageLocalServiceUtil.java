package com.ext.portlet.messaging.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the message local service. This utility wraps {@link com.ext.portlet.messaging.service.impl.MessageLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessageLocalService
 * @see com.ext.portlet.messaging.service.base.MessageLocalServiceBaseImpl
 * @see com.ext.portlet.messaging.service.impl.MessageLocalServiceImpl
 * @generated
 */
public class MessageLocalServiceUtil {
    private static MessageLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.messaging.service.impl.MessageLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the message to the database. Also notifies the appropriate model listeners.
    *
    * @param message the message
    * @return the message that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.Message addMessage(
        com.ext.portlet.messaging.model.Message message)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addMessage(message);
    }

    /**
    * Creates a new message with the primary key. Does not add the message to the database.
    *
    * @param messageId the primary key for the new message
    * @return the new message
    */
    public static com.ext.portlet.messaging.model.Message createMessage(
        long messageId) {
        return getService().createMessage(messageId);
    }

    /**
    * Deletes the message with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param messageId the primary key of the message
    * @throws PortalException if a message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteMessage(long messageId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteMessage(messageId);
    }

    /**
    * Deletes the message from the database. Also notifies the appropriate model listeners.
    *
    * @param message the message
    * @throws SystemException if a system exception occurred
    */
    public static void deleteMessage(
        com.ext.portlet.messaging.model.Message message)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteMessage(message);
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

    public static com.ext.portlet.messaging.model.Message fetchMessage(
        long messageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchMessage(messageId);
    }

    /**
    * Returns the message with the primary key.
    *
    * @param messageId the primary key of the message
    * @return the message
    * @throws PortalException if a message with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.Message getMessage(
        long messageId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getMessage(messageId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the messages.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of messages
    * @param end the upper bound of the range of messages (not inclusive)
    * @return the range of messages
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.messaging.model.Message> getMessages(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getMessages(start, end);
    }

    /**
    * Returns the number of messages.
    *
    * @return the number of messages
    * @throws SystemException if a system exception occurred
    */
    public static int getMessagesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getMessagesCount();
    }

    /**
    * Updates the message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param message the message
    * @return the message that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.Message updateMessage(
        com.ext.portlet.messaging.model.Message message)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateMessage(message);
    }

    /**
    * Updates the message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param message the message
    * @param merge whether to merge the message with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the message that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.Message updateMessage(
        com.ext.portlet.messaging.model.Message message, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateMessage(message, merge);
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

    public static int countSentMessage(long userid)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().countSentMessage(userid);
    }

    public static java.util.List<com.ext.portlet.messaging.model.Message> findSentMessages(
        long userid, int pagerstart, int pagerend)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findSentMessages(userid, pagerstart, pagerend);
    }

    public static java.util.List<com.ext.portlet.messaging.model.MessageRecipientStatus> getRecipients(
        com.ext.portlet.messaging.model.Message msg)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getRecipients(msg);
    }

    public static boolean hasReciever(
        com.ext.portlet.messaging.model.Message msg, long userid)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().hasReciever(msg, userid);
    }

    public static boolean isOpened(
        com.ext.portlet.messaging.model.Message msg, long userid)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().isOpened(msg, userid);
    }

    public static void setOpened(com.ext.portlet.messaging.model.Message msg,
        long userid)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().setOpened(msg, userid);
    }

    public static boolean isArchived(
        com.ext.portlet.messaging.model.Message msg, long userid)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().isArchived(msg, userid);
    }

    public static void setArchived(
        com.ext.portlet.messaging.model.Message msg, long userid)
        throws com.ext.portlet.messaging.NoSuchMessageRecipientStatusException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().setArchived(msg, userid);
    }

    public static void clearService() {
        _service = null;
    }

    public static MessageLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    MessageLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    MessageLocalService.class.getName(), portletClassLoader);

            _service = new MessageLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(MessageLocalServiceUtil.class,
                "_service");
            MethodCache.remove(MessageLocalService.class);
        }

        return _service;
    }

    public void setService(MessageLocalService service) {
        MethodCache.remove(MessageLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(MessageLocalServiceUtil.class,
            "_service");
        MethodCache.remove(MessageLocalService.class);
    }
}
