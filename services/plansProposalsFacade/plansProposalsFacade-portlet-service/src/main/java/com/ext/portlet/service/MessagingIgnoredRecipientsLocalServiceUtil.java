package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the messaging ignored recipients local service. This utility wraps {@link com.ext.portlet.service.impl.MessagingIgnoredRecipientsLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingIgnoredRecipientsLocalService
 * @see com.ext.portlet.service.base.MessagingIgnoredRecipientsLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.MessagingIgnoredRecipientsLocalServiceImpl
 * @generated
 */
public class MessagingIgnoredRecipientsLocalServiceUtil {
    private static MessagingIgnoredRecipientsLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.MessagingIgnoredRecipientsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the messaging ignored recipients to the database. Also notifies the appropriate model listeners.
    *
    * @param messagingIgnoredRecipients the messaging ignored recipients
    * @return the messaging ignored recipients that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingIgnoredRecipients addMessagingIgnoredRecipients(
        com.ext.portlet.model.MessagingIgnoredRecipients messagingIgnoredRecipients)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .addMessagingIgnoredRecipients(messagingIgnoredRecipients);
    }

    /**
    * Creates a new messaging ignored recipients with the primary key. Does not add the messaging ignored recipients to the database.
    *
    * @param ignoredRecipientId the primary key for the new messaging ignored recipients
    * @return the new messaging ignored recipients
    */
    public static com.ext.portlet.model.MessagingIgnoredRecipients createMessagingIgnoredRecipients(
        long ignoredRecipientId) {
        return getService().createMessagingIgnoredRecipients(ignoredRecipientId);
    }

    /**
    * Deletes the messaging ignored recipients with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ignoredRecipientId the primary key of the messaging ignored recipients
    * @throws PortalException if a messaging ignored recipients with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteMessagingIgnoredRecipients(long ignoredRecipientId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteMessagingIgnoredRecipients(ignoredRecipientId);
    }

    /**
    * Deletes the messaging ignored recipients from the database. Also notifies the appropriate model listeners.
    *
    * @param messagingIgnoredRecipients the messaging ignored recipients
    * @throws SystemException if a system exception occurred
    */
    public static void deleteMessagingIgnoredRecipients(
        com.ext.portlet.model.MessagingIgnoredRecipients messagingIgnoredRecipients)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteMessagingIgnoredRecipients(messagingIgnoredRecipients);
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

    public static com.ext.portlet.model.MessagingIgnoredRecipients fetchMessagingIgnoredRecipients(
        long ignoredRecipientId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchMessagingIgnoredRecipients(ignoredRecipientId);
    }

    /**
    * Returns the messaging ignored recipients with the primary key.
    *
    * @param ignoredRecipientId the primary key of the messaging ignored recipients
    * @return the messaging ignored recipients
    * @throws PortalException if a messaging ignored recipients with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingIgnoredRecipients getMessagingIgnoredRecipients(
        long ignoredRecipientId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getMessagingIgnoredRecipients(ignoredRecipientId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the messaging ignored recipientses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of messaging ignored recipientses
    * @param end the upper bound of the range of messaging ignored recipientses (not inclusive)
    * @return the range of messaging ignored recipientses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingIgnoredRecipients> getMessagingIgnoredRecipientses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getMessagingIgnoredRecipientses(start, end);
    }

    /**
    * Returns the number of messaging ignored recipientses.
    *
    * @return the number of messaging ignored recipientses
    * @throws SystemException if a system exception occurred
    */
    public static int getMessagingIgnoredRecipientsesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getMessagingIgnoredRecipientsesCount();
    }

    /**
    * Updates the messaging ignored recipients in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param messagingIgnoredRecipients the messaging ignored recipients
    * @return the messaging ignored recipients that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingIgnoredRecipients updateMessagingIgnoredRecipients(
        com.ext.portlet.model.MessagingIgnoredRecipients messagingIgnoredRecipients)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .updateMessagingIgnoredRecipients(messagingIgnoredRecipients);
    }

    /**
    * Updates the messaging ignored recipients in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param messagingIgnoredRecipients the messaging ignored recipients
    * @param merge whether to merge the messaging ignored recipients with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the messaging ignored recipients that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingIgnoredRecipients updateMessagingIgnoredRecipients(
        com.ext.portlet.model.MessagingIgnoredRecipients messagingIgnoredRecipients,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .updateMessagingIgnoredRecipients(messagingIgnoredRecipients,
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

    public static com.ext.portlet.model.MessagingIgnoredRecipients findByUserId(
        java.lang.Long userId)
        throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().findByUserId(userId);
    }

    public static com.ext.portlet.model.MessagingIgnoredRecipients findByEmail(
        java.lang.String email)
        throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().findByEmail(email);
    }

    public static void clearService() {
        _service = null;
    }

    public static MessagingIgnoredRecipientsLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    MessagingIgnoredRecipientsLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    MessagingIgnoredRecipientsLocalService.class.getName(),
                    portletClassLoader);

            _service = new MessagingIgnoredRecipientsLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(MessagingIgnoredRecipientsLocalServiceUtil.class,
                "_service");
            MethodCache.remove(MessagingIgnoredRecipientsLocalService.class);
        }

        return _service;
    }

    public void setService(MessagingIgnoredRecipientsLocalService service) {
        MethodCache.remove(MessagingIgnoredRecipientsLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(MessagingIgnoredRecipientsLocalServiceUtil.class,
            "_service");
        MethodCache.remove(MessagingIgnoredRecipientsLocalService.class);
    }
}
