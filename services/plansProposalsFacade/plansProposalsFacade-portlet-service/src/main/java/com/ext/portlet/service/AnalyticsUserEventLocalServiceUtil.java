package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the analytics user event local service. This utility wraps {@link com.ext.portlet.service.impl.AnalyticsUserEventLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnalyticsUserEventLocalService
 * @see com.ext.portlet.service.base.AnalyticsUserEventLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.AnalyticsUserEventLocalServiceImpl
 * @generated
 */
public class AnalyticsUserEventLocalServiceUtil {
    private static AnalyticsUserEventLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.AnalyticsUserEventLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the analytics user event to the database. Also notifies the appropriate model listeners.
    *
    * @param analyticsUserEvent the analytics user event
    * @return the analytics user event that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.AnalyticsUserEvent addAnalyticsUserEvent(
        com.ext.portlet.model.AnalyticsUserEvent analyticsUserEvent)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addAnalyticsUserEvent(analyticsUserEvent);
    }

    /**
    * Creates a new analytics user event with the primary key. Does not add the analytics user event to the database.
    *
    * @param analyticsUserEventPK the primary key for the new analytics user event
    * @return the new analytics user event
    */
    public static com.ext.portlet.model.AnalyticsUserEvent createAnalyticsUserEvent(
        com.ext.portlet.service.persistence.AnalyticsUserEventPK analyticsUserEventPK) {
        return getService().createAnalyticsUserEvent(analyticsUserEventPK);
    }

    /**
    * Deletes the analytics user event with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param analyticsUserEventPK the primary key of the analytics user event
    * @throws PortalException if a analytics user event with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteAnalyticsUserEvent(
        com.ext.portlet.service.persistence.AnalyticsUserEventPK analyticsUserEventPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteAnalyticsUserEvent(analyticsUserEventPK);
    }

    /**
    * Deletes the analytics user event from the database. Also notifies the appropriate model listeners.
    *
    * @param analyticsUserEvent the analytics user event
    * @throws SystemException if a system exception occurred
    */
    public static void deleteAnalyticsUserEvent(
        com.ext.portlet.model.AnalyticsUserEvent analyticsUserEvent)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteAnalyticsUserEvent(analyticsUserEvent);
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

    public static com.ext.portlet.model.AnalyticsUserEvent fetchAnalyticsUserEvent(
        com.ext.portlet.service.persistence.AnalyticsUserEventPK analyticsUserEventPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchAnalyticsUserEvent(analyticsUserEventPK);
    }

    /**
    * Returns the analytics user event with the primary key.
    *
    * @param analyticsUserEventPK the primary key of the analytics user event
    * @return the analytics user event
    * @throws PortalException if a analytics user event with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.AnalyticsUserEvent getAnalyticsUserEvent(
        com.ext.portlet.service.persistence.AnalyticsUserEventPK analyticsUserEventPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getAnalyticsUserEvent(analyticsUserEventPK);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the analytics user events.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of analytics user events
    * @param end the upper bound of the range of analytics user events (not inclusive)
    * @return the range of analytics user events
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.AnalyticsUserEvent> getAnalyticsUserEvents(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getAnalyticsUserEvents(start, end);
    }

    /**
    * Returns the number of analytics user events.
    *
    * @return the number of analytics user events
    * @throws SystemException if a system exception occurred
    */
    public static int getAnalyticsUserEventsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getAnalyticsUserEventsCount();
    }

    /**
    * Updates the analytics user event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param analyticsUserEvent the analytics user event
    * @return the analytics user event that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.AnalyticsUserEvent updateAnalyticsUserEvent(
        com.ext.portlet.model.AnalyticsUserEvent analyticsUserEvent)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateAnalyticsUserEvent(analyticsUserEvent);
    }

    /**
    * Updates the analytics user event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param analyticsUserEvent the analytics user event
    * @param merge whether to merge the analytics user event with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the analytics user event that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.AnalyticsUserEvent updateAnalyticsUserEvent(
        com.ext.portlet.model.AnalyticsUserEvent analyticsUserEvent,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateAnalyticsUserEvent(analyticsUserEvent, merge);
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

    public static boolean eventExists(long userId, java.lang.String idString) {
        return getService().eventExists(userId, idString);
    }

    public static com.ext.portlet.model.AnalyticsUserEvent createEvent(
        long userId, java.lang.String idString, java.lang.String category,
        java.lang.String action, java.lang.String label, int value)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .createEvent(userId, idString, category, action, label, value);
    }

    public static void clearService() {
        _service = null;
    }

    public static AnalyticsUserEventLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    AnalyticsUserEventLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    AnalyticsUserEventLocalService.class.getName(),
                    portletClassLoader);

            _service = new AnalyticsUserEventLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(AnalyticsUserEventLocalServiceUtil.class,
                "_service");
            MethodCache.remove(AnalyticsUserEventLocalService.class);
        }

        return _service;
    }

    public void setService(AnalyticsUserEventLocalService service) {
        MethodCache.remove(AnalyticsUserEventLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(AnalyticsUserEventLocalServiceUtil.class,
            "_service");
        MethodCache.remove(AnalyticsUserEventLocalService.class);
    }
}
