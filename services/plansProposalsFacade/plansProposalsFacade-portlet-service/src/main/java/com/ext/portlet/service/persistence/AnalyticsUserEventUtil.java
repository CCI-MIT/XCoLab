package com.ext.portlet.service.persistence;

import com.ext.portlet.model.AnalyticsUserEvent;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the analytics user event service. This utility wraps {@link AnalyticsUserEventPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnalyticsUserEventPersistence
 * @see AnalyticsUserEventPersistenceImpl
 * @generated
 */
public class AnalyticsUserEventUtil {
    private static AnalyticsUserEventPersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
     */
    public static void clearCache(AnalyticsUserEvent analyticsUserEvent) {
        getPersistence().clearCache(analyticsUserEvent);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<AnalyticsUserEvent> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<AnalyticsUserEvent> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<AnalyticsUserEvent> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static AnalyticsUserEvent update(
        AnalyticsUserEvent analyticsUserEvent) throws SystemException {
        return getPersistence().update(analyticsUserEvent);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static AnalyticsUserEvent update(
        AnalyticsUserEvent analyticsUserEvent, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(analyticsUserEvent, serviceContext);
    }

    /**
    * Caches the analytics user event in the entity cache if it is enabled.
    *
    * @param analyticsUserEvent the analytics user event
    */
    public static void cacheResult(
        com.ext.portlet.model.AnalyticsUserEvent analyticsUserEvent) {
        getPersistence().cacheResult(analyticsUserEvent);
    }

    /**
    * Caches the analytics user events in the entity cache if it is enabled.
    *
    * @param analyticsUserEvents the analytics user events
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.AnalyticsUserEvent> analyticsUserEvents) {
        getPersistence().cacheResult(analyticsUserEvents);
    }

    /**
    * Creates a new analytics user event with the primary key. Does not add the analytics user event to the database.
    *
    * @param analyticsUserEventPK the primary key for the new analytics user event
    * @return the new analytics user event
    */
    public static com.ext.portlet.model.AnalyticsUserEvent create(
        com.ext.portlet.service.persistence.AnalyticsUserEventPK analyticsUserEventPK) {
        return getPersistence().create(analyticsUserEventPK);
    }

    /**
    * Removes the analytics user event with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param analyticsUserEventPK the primary key of the analytics user event
    * @return the analytics user event that was removed
    * @throws com.ext.portlet.NoSuchAnalyticsUserEventException if a analytics user event with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.AnalyticsUserEvent remove(
        com.ext.portlet.service.persistence.AnalyticsUserEventPK analyticsUserEventPK)
        throws com.ext.portlet.NoSuchAnalyticsUserEventException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(analyticsUserEventPK);
    }

    public static com.ext.portlet.model.AnalyticsUserEvent updateImpl(
        com.ext.portlet.model.AnalyticsUserEvent analyticsUserEvent)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(analyticsUserEvent);
    }

    /**
    * Returns the analytics user event with the primary key or throws a {@link com.ext.portlet.NoSuchAnalyticsUserEventException} if it could not be found.
    *
    * @param analyticsUserEventPK the primary key of the analytics user event
    * @return the analytics user event
    * @throws com.ext.portlet.NoSuchAnalyticsUserEventException if a analytics user event with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.AnalyticsUserEvent findByPrimaryKey(
        com.ext.portlet.service.persistence.AnalyticsUserEventPK analyticsUserEventPK)
        throws com.ext.portlet.NoSuchAnalyticsUserEventException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(analyticsUserEventPK);
    }

    /**
    * Returns the analytics user event with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param analyticsUserEventPK the primary key of the analytics user event
    * @return the analytics user event, or <code>null</code> if a analytics user event with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.AnalyticsUserEvent fetchByPrimaryKey(
        com.ext.portlet.service.persistence.AnalyticsUserEventPK analyticsUserEventPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(analyticsUserEventPK);
    }

    /**
    * Returns all the analytics user events.
    *
    * @return the analytics user events
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.AnalyticsUserEvent> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the analytics user events.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.AnalyticsUserEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of analytics user events
    * @param end the upper bound of the range of analytics user events (not inclusive)
    * @return the range of analytics user events
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.AnalyticsUserEvent> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the analytics user events.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.AnalyticsUserEventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of analytics user events
    * @param end the upper bound of the range of analytics user events (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of analytics user events
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.AnalyticsUserEvent> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the analytics user events from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of analytics user events.
    *
    * @return the number of analytics user events
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static AnalyticsUserEventPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (AnalyticsUserEventPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    AnalyticsUserEventPersistence.class.getName());

            ReferenceRegistry.registerReference(AnalyticsUserEventUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(AnalyticsUserEventPersistence persistence) {
    }
}
