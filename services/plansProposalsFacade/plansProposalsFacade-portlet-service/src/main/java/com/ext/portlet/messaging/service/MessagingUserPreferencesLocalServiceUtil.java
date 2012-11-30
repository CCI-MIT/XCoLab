package com.ext.portlet.messaging.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the messaging user preferences local service. This utility wraps {@link com.ext.portlet.messaging.service.impl.MessagingUserPreferencesLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingUserPreferencesLocalService
 * @see com.ext.portlet.messaging.service.base.MessagingUserPreferencesLocalServiceBaseImpl
 * @see com.ext.portlet.messaging.service.impl.MessagingUserPreferencesLocalServiceImpl
 * @generated
 */
public class MessagingUserPreferencesLocalServiceUtil {
    private static MessagingUserPreferencesLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.messaging.service.impl.MessagingUserPreferencesLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the messaging user preferences to the database. Also notifies the appropriate model listeners.
    *
    * @param messagingUserPreferences the messaging user preferences
    * @return the messaging user preferences that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.MessagingUserPreferences addMessagingUserPreferences(
        com.ext.portlet.messaging.model.MessagingUserPreferences messagingUserPreferences)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addMessagingUserPreferences(messagingUserPreferences);
    }

    /**
    * Creates a new messaging user preferences with the primary key. Does not add the messaging user preferences to the database.
    *
    * @param messagingPreferencesId the primary key for the new messaging user preferences
    * @return the new messaging user preferences
    */
    public static com.ext.portlet.messaging.model.MessagingUserPreferences createMessagingUserPreferences(
        java.lang.Long messagingPreferencesId) {
        return getService()
                   .createMessagingUserPreferences(messagingPreferencesId);
    }

    /**
    * Deletes the messaging user preferences with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param messagingPreferencesId the primary key of the messaging user preferences
    * @throws PortalException if a messaging user preferences with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteMessagingUserPreferences(
        java.lang.Long messagingPreferencesId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteMessagingUserPreferences(messagingPreferencesId);
    }

    /**
    * Deletes the messaging user preferences from the database. Also notifies the appropriate model listeners.
    *
    * @param messagingUserPreferences the messaging user preferences
    * @throws SystemException if a system exception occurred
    */
    public static void deleteMessagingUserPreferences(
        com.ext.portlet.messaging.model.MessagingUserPreferences messagingUserPreferences)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteMessagingUserPreferences(messagingUserPreferences);
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

    public static com.ext.portlet.messaging.model.MessagingUserPreferences fetchMessagingUserPreferences(
        java.lang.Long messagingPreferencesId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchMessagingUserPreferences(messagingPreferencesId);
    }

    /**
    * Returns the messaging user preferences with the primary key.
    *
    * @param messagingPreferencesId the primary key of the messaging user preferences
    * @return the messaging user preferences
    * @throws PortalException if a messaging user preferences with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.MessagingUserPreferences getMessagingUserPreferences(
        java.lang.Long messagingPreferencesId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getMessagingUserPreferences(messagingPreferencesId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the messaging user preferenceses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of messaging user preferenceses
    * @param end the upper bound of the range of messaging user preferenceses (not inclusive)
    * @return the range of messaging user preferenceses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.messaging.model.MessagingUserPreferences> getMessagingUserPreferenceses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getMessagingUserPreferenceses(start, end);
    }

    /**
    * Returns the number of messaging user preferenceses.
    *
    * @return the number of messaging user preferenceses
    * @throws SystemException if a system exception occurred
    */
    public static int getMessagingUserPreferencesesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getMessagingUserPreferencesesCount();
    }

    /**
    * Updates the messaging user preferences in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param messagingUserPreferences the messaging user preferences
    * @return the messaging user preferences that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.MessagingUserPreferences updateMessagingUserPreferences(
        com.ext.portlet.messaging.model.MessagingUserPreferences messagingUserPreferences)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .updateMessagingUserPreferences(messagingUserPreferences);
    }

    /**
    * Updates the messaging user preferences in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param messagingUserPreferences the messaging user preferences
    * @param merge whether to merge the messaging user preferences with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the messaging user preferences that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.messaging.model.MessagingUserPreferences updateMessagingUserPreferences(
        com.ext.portlet.messaging.model.MessagingUserPreferences messagingUserPreferences,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .updateMessagingUserPreferences(messagingUserPreferences,
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

    public static com.ext.portlet.messaging.model.MessagingUserPreferences findByUser(
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByUser(userId);
    }

    public static void clearService() {
        _service = null;
    }

    public static MessagingUserPreferencesLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    MessagingUserPreferencesLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    MessagingUserPreferencesLocalService.class.getName(),
                    portletClassLoader);

            _service = new MessagingUserPreferencesLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(MessagingUserPreferencesLocalServiceUtil.class,
                "_service");
            MethodCache.remove(MessagingUserPreferencesLocalService.class);
        }

        return _service;
    }

    public void setService(MessagingUserPreferencesLocalService service) {
        MethodCache.remove(MessagingUserPreferencesLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(MessagingUserPreferencesLocalServiceUtil.class,
            "_service");
        MethodCache.remove(MessagingUserPreferencesLocalService.class);
    }
}
