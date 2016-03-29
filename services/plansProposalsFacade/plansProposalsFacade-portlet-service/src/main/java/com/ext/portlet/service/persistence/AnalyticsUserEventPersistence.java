package com.ext.portlet.service.persistence;

import com.ext.portlet.model.AnalyticsUserEvent;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the analytics user event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnalyticsUserEventPersistenceImpl
 * @see AnalyticsUserEventUtil
 * @generated
 */
public interface AnalyticsUserEventPersistence extends BasePersistence<AnalyticsUserEvent> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link AnalyticsUserEventUtil} to access the analytics user event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the analytics user event in the entity cache if it is enabled.
    *
    * @param analyticsUserEvent the analytics user event
    */
    public void cacheResult(
        com.ext.portlet.model.AnalyticsUserEvent analyticsUserEvent);

    /**
    * Caches the analytics user events in the entity cache if it is enabled.
    *
    * @param analyticsUserEvents the analytics user events
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.AnalyticsUserEvent> analyticsUserEvents);

    /**
    * Creates a new analytics user event with the primary key. Does not add the analytics user event to the database.
    *
    * @param analyticsUserEventPK the primary key for the new analytics user event
    * @return the new analytics user event
    */
    public com.ext.portlet.model.AnalyticsUserEvent create(
        com.ext.portlet.service.persistence.AnalyticsUserEventPK analyticsUserEventPK);

    /**
    * Removes the analytics user event with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param analyticsUserEventPK the primary key of the analytics user event
    * @return the analytics user event that was removed
    * @throws com.ext.portlet.NoSuchAnalyticsUserEventException if a analytics user event with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.AnalyticsUserEvent remove(
        com.ext.portlet.service.persistence.AnalyticsUserEventPK analyticsUserEventPK)
        throws com.ext.portlet.NoSuchAnalyticsUserEventException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.AnalyticsUserEvent updateImpl(
        com.ext.portlet.model.AnalyticsUserEvent analyticsUserEvent)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the analytics user event with the primary key or throws a {@link com.ext.portlet.NoSuchAnalyticsUserEventException} if it could not be found.
    *
    * @param analyticsUserEventPK the primary key of the analytics user event
    * @return the analytics user event
    * @throws com.ext.portlet.NoSuchAnalyticsUserEventException if a analytics user event with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.AnalyticsUserEvent findByPrimaryKey(
        com.ext.portlet.service.persistence.AnalyticsUserEventPK analyticsUserEventPK)
        throws com.ext.portlet.NoSuchAnalyticsUserEventException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the analytics user event with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param analyticsUserEventPK the primary key of the analytics user event
    * @return the analytics user event, or <code>null</code> if a analytics user event with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.AnalyticsUserEvent fetchByPrimaryKey(
        com.ext.portlet.service.persistence.AnalyticsUserEventPK analyticsUserEventPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the analytics user events.
    *
    * @return the analytics user events
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.AnalyticsUserEvent> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.AnalyticsUserEvent> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.AnalyticsUserEvent> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the analytics user events from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of analytics user events.
    *
    * @return the number of analytics user events
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
