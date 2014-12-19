package com.ext.portlet.service.persistence;

import com.ext.portlet.model.TrackedVisit;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the tracked visit service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrackedVisitPersistenceImpl
 * @see TrackedVisitUtil
 * @generated
 */
public interface TrackedVisitPersistence extends BasePersistence<TrackedVisit> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link TrackedVisitUtil} to access the tracked visit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the tracked visits where uuid = &#63;.
    *
    * @param uuid the uuid
    * @return the matching tracked visits
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.TrackedVisit> findByUuid(
        java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the tracked visits where uuid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.TrackedVisitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param uuid the uuid
    * @param start the lower bound of the range of tracked visits
    * @param end the upper bound of the range of tracked visits (not inclusive)
    * @return the range of matching tracked visits
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.TrackedVisit> findByUuid(
        java.lang.String uuid, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the tracked visits where uuid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.TrackedVisitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param uuid the uuid
    * @param start the lower bound of the range of tracked visits
    * @param end the upper bound of the range of tracked visits (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching tracked visits
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.TrackedVisit> findByUuid(
        java.lang.String uuid, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first tracked visit in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching tracked visit
    * @throws com.ext.portlet.NoSuchTrackedVisitException if a matching tracked visit could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.TrackedVisit findByUuid_First(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchTrackedVisitException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first tracked visit in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching tracked visit, or <code>null</code> if a matching tracked visit could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.TrackedVisit fetchByUuid_First(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last tracked visit in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching tracked visit
    * @throws com.ext.portlet.NoSuchTrackedVisitException if a matching tracked visit could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.TrackedVisit findByUuid_Last(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchTrackedVisitException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last tracked visit in the ordered set where uuid = &#63;.
    *
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching tracked visit, or <code>null</code> if a matching tracked visit could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.TrackedVisit fetchByUuid_Last(
        java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the tracked visits before and after the current tracked visit in the ordered set where uuid = &#63;.
    *
    * @param id the primary key of the current tracked visit
    * @param uuid the uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next tracked visit
    * @throws com.ext.portlet.NoSuchTrackedVisitException if a tracked visit with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.TrackedVisit[] findByUuid_PrevAndNext(
        long id, java.lang.String uuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchTrackedVisitException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the tracked visits where uuid = &#63; from the database.
    *
    * @param uuid the uuid
    * @throws SystemException if a system exception occurred
    */
    public void removeByUuid(java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of tracked visits where uuid = &#63;.
    *
    * @param uuid the uuid
    * @return the number of matching tracked visits
    * @throws SystemException if a system exception occurred
    */
    public int countByUuid(java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the tracked visit in the entity cache if it is enabled.
    *
    * @param trackedVisit the tracked visit
    */
    public void cacheResult(com.ext.portlet.model.TrackedVisit trackedVisit);

    /**
    * Caches the tracked visits in the entity cache if it is enabled.
    *
    * @param trackedVisits the tracked visits
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.TrackedVisit> trackedVisits);

    /**
    * Creates a new tracked visit with the primary key. Does not add the tracked visit to the database.
    *
    * @param id the primary key for the new tracked visit
    * @return the new tracked visit
    */
    public com.ext.portlet.model.TrackedVisit create(long id);

    /**
    * Removes the tracked visit with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the tracked visit
    * @return the tracked visit that was removed
    * @throws com.ext.portlet.NoSuchTrackedVisitException if a tracked visit with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.TrackedVisit remove(long id)
        throws com.ext.portlet.NoSuchTrackedVisitException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.TrackedVisit updateImpl(
        com.ext.portlet.model.TrackedVisit trackedVisit)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the tracked visit with the primary key or throws a {@link com.ext.portlet.NoSuchTrackedVisitException} if it could not be found.
    *
    * @param id the primary key of the tracked visit
    * @return the tracked visit
    * @throws com.ext.portlet.NoSuchTrackedVisitException if a tracked visit with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.TrackedVisit findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchTrackedVisitException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the tracked visit with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the tracked visit
    * @return the tracked visit, or <code>null</code> if a tracked visit with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.TrackedVisit fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the tracked visits.
    *
    * @return the tracked visits
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.TrackedVisit> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the tracked visits.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.TrackedVisitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of tracked visits
    * @param end the upper bound of the range of tracked visits (not inclusive)
    * @return the range of tracked visits
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.TrackedVisit> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the tracked visits.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.TrackedVisitModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of tracked visits
    * @param end the upper bound of the range of tracked visits (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of tracked visits
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.TrackedVisit> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the tracked visits from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of tracked visits.
    *
    * @return the number of tracked visits
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
