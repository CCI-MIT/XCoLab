package com.ext.portlet.service.persistence;

import com.ext.portlet.model.Points;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the points service. This utility wraps {@link PointsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PointsPersistence
 * @see PointsPersistenceImpl
 * @generated
 */
public class PointsUtil {
    private static PointsPersistence _persistence;

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
    public static void clearCache(Points points) {
        getPersistence().clearCache(points);
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
    public static List<Points> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<Points> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<Points> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static Points update(Points points) throws SystemException {
        return getPersistence().update(points);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static Points update(Points points, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(points, serviceContext);
    }

    /**
    * Returns all the pointses where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the matching pointses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Points> findByProposalId(
        long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByProposalId(proposalId);
    }

    /**
    * Returns a range of all the pointses where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of pointses
    * @param end the upper bound of the range of pointses (not inclusive)
    * @return the range of matching pointses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Points> findByProposalId(
        long proposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByProposalId(proposalId, start, end);
    }

    /**
    * Returns an ordered range of all the pointses where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of pointses
    * @param end the upper bound of the range of pointses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching pointses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Points> findByProposalId(
        long proposalId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId(proposalId, start, end, orderByComparator);
    }

    /**
    * Returns the first points in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points
    * @throws com.ext.portlet.NoSuchPointsException if a matching points could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Points findByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_First(proposalId, orderByComparator);
    }

    /**
    * Returns the first points in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points, or <code>null</code> if a matching points could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Points fetchByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByProposalId_First(proposalId, orderByComparator);
    }

    /**
    * Returns the last points in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points
    * @throws com.ext.portlet.NoSuchPointsException if a matching points could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Points findByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_Last(proposalId, orderByComparator);
    }

    /**
    * Returns the last points in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points, or <code>null</code> if a matching points could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Points fetchByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByProposalId_Last(proposalId, orderByComparator);
    }

    /**
    * Returns the pointses before and after the current points in the ordered set where proposalId = &#63;.
    *
    * @param id the primary key of the current points
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next points
    * @throws com.ext.portlet.NoSuchPointsException if a points with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Points[] findByProposalId_PrevAndNext(
        long id, long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_PrevAndNext(id, proposalId,
            orderByComparator);
    }

    /**
    * Removes all the pointses where proposalId = &#63; from the database.
    *
    * @param proposalId the proposal ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByProposalId(proposalId);
    }

    /**
    * Returns the number of pointses where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the number of matching pointses
    * @throws SystemException if a system exception occurred
    */
    public static int countByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByProposalId(proposalId);
    }

    /**
    * Returns all the pointses where userId = &#63;.
    *
    * @param userId the user ID
    * @return the matching pointses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Points> findByUserId(
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId(userId);
    }

    /**
    * Returns a range of all the pointses where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of pointses
    * @param end the upper bound of the range of pointses (not inclusive)
    * @return the range of matching pointses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Points> findByUserId(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId(userId, start, end);
    }

    /**
    * Returns an ordered range of all the pointses where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of pointses
    * @param end the upper bound of the range of pointses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching pointses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Points> findByUserId(
        long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByUserId(userId, start, end, orderByComparator);
    }

    /**
    * Returns the first points in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points
    * @throws com.ext.portlet.NoSuchPointsException if a matching points could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Points findByUserId_First(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId_First(userId, orderByComparator);
    }

    /**
    * Returns the first points in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points, or <code>null</code> if a matching points could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Points fetchByUserId_First(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUserId_First(userId, orderByComparator);
    }

    /**
    * Returns the last points in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points
    * @throws com.ext.portlet.NoSuchPointsException if a matching points could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Points findByUserId_Last(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByUserId_Last(userId, orderByComparator);
    }

    /**
    * Returns the last points in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points, or <code>null</code> if a matching points could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Points fetchByUserId_Last(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByUserId_Last(userId, orderByComparator);
    }

    /**
    * Returns the pointses before and after the current points in the ordered set where userId = &#63;.
    *
    * @param id the primary key of the current points
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next points
    * @throws com.ext.portlet.NoSuchPointsException if a points with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Points[] findByUserId_PrevAndNext(
        long id, long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByUserId_PrevAndNext(id, userId, orderByComparator);
    }

    /**
    * Removes all the pointses where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByUserId(userId);
    }

    /**
    * Returns the number of pointses where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching pointses
    * @throws SystemException if a system exception occurred
    */
    public static int countByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByUserId(userId);
    }

    /**
    * Returns all the pointses where pointsSourceId = &#63;.
    *
    * @param pointsSourceId the points source ID
    * @return the matching pointses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Points> findByPointsSourceId(
        long pointsSourceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPointsSourceId(pointsSourceId);
    }

    /**
    * Returns a range of all the pointses where pointsSourceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param pointsSourceId the points source ID
    * @param start the lower bound of the range of pointses
    * @param end the upper bound of the range of pointses (not inclusive)
    * @return the range of matching pointses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Points> findByPointsSourceId(
        long pointsSourceId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPointsSourceId(pointsSourceId, start, end);
    }

    /**
    * Returns an ordered range of all the pointses where pointsSourceId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param pointsSourceId the points source ID
    * @param start the lower bound of the range of pointses
    * @param end the upper bound of the range of pointses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching pointses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Points> findByPointsSourceId(
        long pointsSourceId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPointsSourceId(pointsSourceId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first points in the ordered set where pointsSourceId = &#63;.
    *
    * @param pointsSourceId the points source ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points
    * @throws com.ext.portlet.NoSuchPointsException if a matching points could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Points findByPointsSourceId_First(
        long pointsSourceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPointsSourceId_First(pointsSourceId, orderByComparator);
    }

    /**
    * Returns the first points in the ordered set where pointsSourceId = &#63;.
    *
    * @param pointsSourceId the points source ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points, or <code>null</code> if a matching points could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Points fetchByPointsSourceId_First(
        long pointsSourceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPointsSourceId_First(pointsSourceId,
            orderByComparator);
    }

    /**
    * Returns the last points in the ordered set where pointsSourceId = &#63;.
    *
    * @param pointsSourceId the points source ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points
    * @throws com.ext.portlet.NoSuchPointsException if a matching points could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Points findByPointsSourceId_Last(
        long pointsSourceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPointsSourceId_Last(pointsSourceId, orderByComparator);
    }

    /**
    * Returns the last points in the ordered set where pointsSourceId = &#63;.
    *
    * @param pointsSourceId the points source ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points, or <code>null</code> if a matching points could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Points fetchByPointsSourceId_Last(
        long pointsSourceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPointsSourceId_Last(pointsSourceId, orderByComparator);
    }

    /**
    * Returns the pointses before and after the current points in the ordered set where pointsSourceId = &#63;.
    *
    * @param id the primary key of the current points
    * @param pointsSourceId the points source ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next points
    * @throws com.ext.portlet.NoSuchPointsException if a points with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Points[] findByPointsSourceId_PrevAndNext(
        long id, long pointsSourceId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPointsSourceId_PrevAndNext(id, pointsSourceId,
            orderByComparator);
    }

    /**
    * Removes all the pointses where pointsSourceId = &#63; from the database.
    *
    * @param pointsSourceId the points source ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByPointsSourceId(long pointsSourceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByPointsSourceId(pointsSourceId);
    }

    /**
    * Returns the number of pointses where pointsSourceId = &#63;.
    *
    * @param pointsSourceId the points source ID
    * @return the number of matching pointses
    * @throws SystemException if a system exception occurred
    */
    public static int countByPointsSourceId(long pointsSourceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByPointsSourceId(pointsSourceId);
    }

    /**
    * Returns all the pointses where originatingContestPK = &#63;.
    *
    * @param originatingContestPK the originating contest p k
    * @return the matching pointses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Points> findByOriginatingContestPK(
        long originatingContestPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByOriginatingContestPK(originatingContestPK);
    }

    /**
    * Returns a range of all the pointses where originatingContestPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param originatingContestPK the originating contest p k
    * @param start the lower bound of the range of pointses
    * @param end the upper bound of the range of pointses (not inclusive)
    * @return the range of matching pointses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Points> findByOriginatingContestPK(
        long originatingContestPK, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByOriginatingContestPK(originatingContestPK, start, end);
    }

    /**
    * Returns an ordered range of all the pointses where originatingContestPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param originatingContestPK the originating contest p k
    * @param start the lower bound of the range of pointses
    * @param end the upper bound of the range of pointses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching pointses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Points> findByOriginatingContestPK(
        long originatingContestPK, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByOriginatingContestPK(originatingContestPK, start,
            end, orderByComparator);
    }

    /**
    * Returns the first points in the ordered set where originatingContestPK = &#63;.
    *
    * @param originatingContestPK the originating contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points
    * @throws com.ext.portlet.NoSuchPointsException if a matching points could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Points findByOriginatingContestPK_First(
        long originatingContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByOriginatingContestPK_First(originatingContestPK,
            orderByComparator);
    }

    /**
    * Returns the first points in the ordered set where originatingContestPK = &#63;.
    *
    * @param originatingContestPK the originating contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching points, or <code>null</code> if a matching points could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Points fetchByOriginatingContestPK_First(
        long originatingContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByOriginatingContestPK_First(originatingContestPK,
            orderByComparator);
    }

    /**
    * Returns the last points in the ordered set where originatingContestPK = &#63;.
    *
    * @param originatingContestPK the originating contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points
    * @throws com.ext.portlet.NoSuchPointsException if a matching points could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Points findByOriginatingContestPK_Last(
        long originatingContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByOriginatingContestPK_Last(originatingContestPK,
            orderByComparator);
    }

    /**
    * Returns the last points in the ordered set where originatingContestPK = &#63;.
    *
    * @param originatingContestPK the originating contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching points, or <code>null</code> if a matching points could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Points fetchByOriginatingContestPK_Last(
        long originatingContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByOriginatingContestPK_Last(originatingContestPK,
            orderByComparator);
    }

    /**
    * Returns the pointses before and after the current points in the ordered set where originatingContestPK = &#63;.
    *
    * @param id the primary key of the current points
    * @param originatingContestPK the originating contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next points
    * @throws com.ext.portlet.NoSuchPointsException if a points with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Points[] findByOriginatingContestPK_PrevAndNext(
        long id, long originatingContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByOriginatingContestPK_PrevAndNext(id,
            originatingContestPK, orderByComparator);
    }

    /**
    * Removes all the pointses where originatingContestPK = &#63; from the database.
    *
    * @param originatingContestPK the originating contest p k
    * @throws SystemException if a system exception occurred
    */
    public static void removeByOriginatingContestPK(long originatingContestPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByOriginatingContestPK(originatingContestPK);
    }

    /**
    * Returns the number of pointses where originatingContestPK = &#63;.
    *
    * @param originatingContestPK the originating contest p k
    * @return the number of matching pointses
    * @throws SystemException if a system exception occurred
    */
    public static int countByOriginatingContestPK(long originatingContestPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByOriginatingContestPK(originatingContestPK);
    }

    /**
    * Caches the points in the entity cache if it is enabled.
    *
    * @param points the points
    */
    public static void cacheResult(com.ext.portlet.model.Points points) {
        getPersistence().cacheResult(points);
    }

    /**
    * Caches the pointses in the entity cache if it is enabled.
    *
    * @param pointses the pointses
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.Points> pointses) {
        getPersistence().cacheResult(pointses);
    }

    /**
    * Creates a new points with the primary key. Does not add the points to the database.
    *
    * @param id the primary key for the new points
    * @return the new points
    */
    public static com.ext.portlet.model.Points create(long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the points with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the points
    * @return the points that was removed
    * @throws com.ext.portlet.NoSuchPointsException if a points with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Points remove(long id)
        throws com.ext.portlet.NoSuchPointsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.Points updateImpl(
        com.ext.portlet.model.Points points)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(points);
    }

    /**
    * Returns the points with the primary key or throws a {@link com.ext.portlet.NoSuchPointsException} if it could not be found.
    *
    * @param id the primary key of the points
    * @return the points
    * @throws com.ext.portlet.NoSuchPointsException if a points with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Points findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchPointsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the points with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the points
    * @return the points, or <code>null</code> if a points with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Points fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the pointses.
    *
    * @return the pointses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Points> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the pointses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of pointses
    * @param end the upper bound of the range of pointses (not inclusive)
    * @return the range of pointses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Points> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the pointses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of pointses
    * @param end the upper bound of the range of pointses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of pointses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Points> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the pointses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of pointses.
    *
    * @return the number of pointses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PointsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PointsPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    PointsPersistence.class.getName());

            ReferenceRegistry.registerReference(PointsUtil.class, "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(PointsPersistence persistence) {
    }
}
