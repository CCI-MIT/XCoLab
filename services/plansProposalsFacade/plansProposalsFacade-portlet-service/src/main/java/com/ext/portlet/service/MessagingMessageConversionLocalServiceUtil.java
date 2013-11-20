package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for MessagingMessageConversion. This utility wraps
 * {@link com.ext.portlet.service.impl.MessagingMessageConversionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageConversionLocalService
 * @see com.ext.portlet.service.base.MessagingMessageConversionLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.MessagingMessageConversionLocalServiceImpl
 * @generated
 */
public class MessagingMessageConversionLocalServiceUtil {
    private static MessagingMessageConversionLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.MessagingMessageConversionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the messaging message conversion to the database. Also notifies the appropriate model listeners.
    *
    * @param messagingMessageConversion the messaging message conversion
    * @return the messaging message conversion that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageConversion addMessagingMessageConversion(
        com.ext.portlet.model.MessagingMessageConversion messagingMessageConversion)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .addMessagingMessageConversion(messagingMessageConversion);
    }

    /**
    * Creates a new messaging message conversion with the primary key. Does not add the messaging message conversion to the database.
    *
    * @param conversionId the primary key for the new messaging message conversion
    * @return the new messaging message conversion
    */
    public static com.ext.portlet.model.MessagingMessageConversion createMessagingMessageConversion(
        long conversionId) {
        return getService().createMessagingMessageConversion(conversionId);
    }

    /**
    * Deletes the messaging message conversion with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param conversionId the primary key of the messaging message conversion
    * @return the messaging message conversion that was removed
    * @throws PortalException if a messaging message conversion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageConversion deleteMessagingMessageConversion(
        long conversionId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteMessagingMessageConversion(conversionId);
    }

    /**
    * Deletes the messaging message conversion from the database. Also notifies the appropriate model listeners.
    *
    * @param messagingMessageConversion the messaging message conversion
    * @return the messaging message conversion that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageConversion deleteMessagingMessageConversion(
        com.ext.portlet.model.MessagingMessageConversion messagingMessageConversion)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .deleteMessagingMessageConversion(messagingMessageConversion);
    }

    public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return getService().dynamicQuery();
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingMessageConversionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingMessageConversionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery, projection);
    }

    public static com.ext.portlet.model.MessagingMessageConversion fetchMessagingMessageConversion(
        long conversionId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchMessagingMessageConversion(conversionId);
    }

    /**
    * Returns the messaging message conversion with the primary key.
    *
    * @param conversionId the primary key of the messaging message conversion
    * @return the messaging message conversion
    * @throws PortalException if a messaging message conversion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageConversion getMessagingMessageConversion(
        long conversionId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getMessagingMessageConversion(conversionId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the messaging message conversions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingMessageConversionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of messaging message conversions
    * @param end the upper bound of the range of messaging message conversions (not inclusive)
    * @return the range of messaging message conversions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingMessageConversion> getMessagingMessageConversions(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getMessagingMessageConversions(start, end);
    }

    /**
    * Returns the number of messaging message conversions.
    *
    * @return the number of messaging message conversions
    * @throws SystemException if a system exception occurred
    */
    public static int getMessagingMessageConversionsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getMessagingMessageConversionsCount();
    }

    /**
    * Updates the messaging message conversion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param messagingMessageConversion the messaging message conversion
    * @return the messaging message conversion that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingMessageConversion updateMessagingMessageConversion(
        com.ext.portlet.model.MessagingMessageConversion messagingMessageConversion)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .updateMessagingMessageConversion(messagingMessageConversion);
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

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static int countByType(java.lang.Long messageId,
        com.ext.portlet.model.MessagingMessageConversionType type)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().countByType(messageId, type);
    }

    public static int countByType(java.lang.Long messageId,
        java.lang.String typeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().countByType(messageId, typeName);
    }

    public static void addConversion(java.lang.Long messageId,
        java.lang.String typeName, java.lang.String clientIP,
        java.lang.Object extraData, java.lang.Object extraData2)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService()
            .addConversion(messageId, typeName, clientIP, extraData, extraData2);
    }

    public static int countConversionsByIP(java.lang.Long messageId,
        java.lang.String typeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().countConversionsByIP(messageId, typeName);
    }

    public static int countConversionsByRecipient(java.lang.Long messageId,
        java.lang.String typeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().countConversionsByRecipient(messageId, typeName);
    }

    public static void clearService() {
        _service = null;
    }

    public static MessagingMessageConversionLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    MessagingMessageConversionLocalService.class.getName());

            if (invokableLocalService instanceof MessagingMessageConversionLocalService) {
                _service = (MessagingMessageConversionLocalService) invokableLocalService;
            } else {
                _service = new MessagingMessageConversionLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(MessagingMessageConversionLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(MessagingMessageConversionLocalService service) {
    }
}
