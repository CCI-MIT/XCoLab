package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the messaging message recipient local service. This utility wraps {@link com.ext.portlet.service.impl.MessagingMessageRecipientLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageRecipientLocalService
 * @see com.ext.portlet.service.base.MessagingMessageRecipientLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.MessagingMessageRecipientLocalServiceImpl
 * @generated
 */
public class MessagingMessageRecipientLocalServiceUtil {
    private static MessagingMessageRecipientLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.MessagingMessageRecipientLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the messaging message recipient to the database. Also notifies the appropriate model listeners.
    *
    * @param messagingMessageRecipient the messaging message recipient
    * @return the messaging message recipient that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageRecipient addMessagingMessageRecipient(
        com.ext.portlet.model.MessagingMessageRecipient messagingMessageRecipient)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .addMessagingMessageRecipient(messagingMessageRecipient);
    }

    /**
    * Creates a new messaging message recipient with the primary key. Does not add the messaging message recipient to the database.
    *
    * @param recipientId the primary key for the new messaging message recipient
    * @return the new messaging message recipient
    */
    public static com.ext.portlet.model.MessagingMessageRecipient createMessagingMessageRecipient(
        long recipientId) {
        return getService().createMessagingMessageRecipient(recipientId);
    }

    /**
    * Deletes the messaging message recipient with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param recipientId the primary key of the messaging message recipient
    * @throws PortalException if a messaging message recipient with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteMessagingMessageRecipient(long recipientId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteMessagingMessageRecipient(recipientId);
    }

    /**
    * Deletes the messaging message recipient from the database. Also notifies the appropriate model listeners.
    *
    * @param messagingMessageRecipient the messaging message recipient
    * @throws SystemException if a system exception occurred
    */
    public static void deleteMessagingMessageRecipient(
        com.ext.portlet.model.MessagingMessageRecipient messagingMessageRecipient)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteMessagingMessageRecipient(messagingMessageRecipient);
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

    public static com.ext.portlet.model.MessagingMessageRecipient fetchMessagingMessageRecipient(
        long recipientId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchMessagingMessageRecipient(recipientId);
    }

    /**
    * Returns the messaging message recipient with the primary key.
    *
    * @param recipientId the primary key of the messaging message recipient
    * @return the messaging message recipient
    * @throws PortalException if a messaging message recipient with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageRecipient getMessagingMessageRecipient(
        long recipientId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getMessagingMessageRecipient(recipientId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the messaging message recipients.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of messaging message recipients
    * @param end the upper bound of the range of messaging message recipients (not inclusive)
    * @return the range of messaging message recipients
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingMessageRecipient> getMessagingMessageRecipients(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getMessagingMessageRecipients(start, end);
    }

    /**
    * Returns the number of messaging message recipients.
    *
    * @return the number of messaging message recipients
    * @throws SystemException if a system exception occurred
    */
    public static int getMessagingMessageRecipientsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getMessagingMessageRecipientsCount();
    }

    /**
    * Updates the messaging message recipient in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param messagingMessageRecipient the messaging message recipient
    * @return the messaging message recipient that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageRecipient updateMessagingMessageRecipient(
        com.ext.portlet.model.MessagingMessageRecipient messagingMessageRecipient)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .updateMessagingMessageRecipient(messagingMessageRecipient);
    }

    /**
    * Updates the messaging message recipient in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param messagingMessageRecipient the messaging message recipient
    * @param merge whether to merge the messaging message recipient with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the messaging message recipient that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageRecipient updateMessagingMessageRecipient(
        com.ext.portlet.model.MessagingMessageRecipient messagingMessageRecipient,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .updateMessagingMessageRecipient(messagingMessageRecipient,
            merge);
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

    public static void clearService() {
        _service = null;
    }

    public static MessagingMessageRecipientLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    MessagingMessageRecipientLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    MessagingMessageRecipientLocalService.class.getName(),
                    portletClassLoader);

            _service = new MessagingMessageRecipientLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(MessagingMessageRecipientLocalServiceUtil.class,
                "_service");
            MethodCache.remove(MessagingMessageRecipientLocalService.class);
        }

        return _service;
    }

    public void setService(MessagingMessageRecipientLocalService service) {
        MethodCache.remove(MessagingMessageRecipientLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(MessagingMessageRecipientLocalServiceUtil.class,
            "_service");
        MethodCache.remove(MessagingMessageRecipientLocalService.class);
    }
}
