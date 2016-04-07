package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ImpactDefaultSeries;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the impact default series service. This utility wraps {@link ImpactDefaultSeriesPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactDefaultSeriesPersistence
 * @see ImpactDefaultSeriesPersistenceImpl
 * @generated
 */
public class ImpactDefaultSeriesUtil {
    private static ImpactDefaultSeriesPersistence _persistence;

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
    public static void clearCache(ImpactDefaultSeries impactDefaultSeries) {
        getPersistence().clearCache(impactDefaultSeries);
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
    public static List<ImpactDefaultSeries> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ImpactDefaultSeries> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ImpactDefaultSeries> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ImpactDefaultSeries update(
        ImpactDefaultSeries impactDefaultSeries) throws SystemException {
        return getPersistence().update(impactDefaultSeries);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ImpactDefaultSeries update(
        ImpactDefaultSeries impactDefaultSeries, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(impactDefaultSeries, serviceContext);
    }

    /**
    * Returns all the impact default serieses where seriesId = &#63;.
    *
    * @param seriesId the series ID
    * @return the matching impact default serieses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactDefaultSeries> findBySeriesId(
        long seriesId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySeriesId(seriesId);
    }

    /**
    * Returns a range of all the impact default serieses where seriesId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactDefaultSeriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param seriesId the series ID
    * @param start the lower bound of the range of impact default serieses
    * @param end the upper bound of the range of impact default serieses (not inclusive)
    * @return the range of matching impact default serieses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactDefaultSeries> findBySeriesId(
        long seriesId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySeriesId(seriesId, start, end);
    }

    /**
    * Returns an ordered range of all the impact default serieses where seriesId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactDefaultSeriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param seriesId the series ID
    * @param start the lower bound of the range of impact default serieses
    * @param end the upper bound of the range of impact default serieses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching impact default serieses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactDefaultSeries> findBySeriesId(
        long seriesId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySeriesId(seriesId, start, end, orderByComparator);
    }

    /**
    * Returns the first impact default series in the ordered set where seriesId = &#63;.
    *
    * @param seriesId the series ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching impact default series
    * @throws com.ext.portlet.NoSuchImpactDefaultSeriesException if a matching impact default series could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries findBySeriesId_First(
        long seriesId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySeriesId_First(seriesId, orderByComparator);
    }

    /**
    * Returns the first impact default series in the ordered set where seriesId = &#63;.
    *
    * @param seriesId the series ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching impact default series, or <code>null</code> if a matching impact default series could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries fetchBySeriesId_First(
        long seriesId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySeriesId_First(seriesId, orderByComparator);
    }

    /**
    * Returns the last impact default series in the ordered set where seriesId = &#63;.
    *
    * @param seriesId the series ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching impact default series
    * @throws com.ext.portlet.NoSuchImpactDefaultSeriesException if a matching impact default series could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries findBySeriesId_Last(
        long seriesId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySeriesId_Last(seriesId, orderByComparator);
    }

    /**
    * Returns the last impact default series in the ordered set where seriesId = &#63;.
    *
    * @param seriesId the series ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching impact default series, or <code>null</code> if a matching impact default series could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries fetchBySeriesId_Last(
        long seriesId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchBySeriesId_Last(seriesId, orderByComparator);
    }

    /**
    * Returns the impact default serieses before and after the current impact default series in the ordered set where seriesId = &#63;.
    *
    * @param impactDefaultSeriesPK the primary key of the current impact default series
    * @param seriesId the series ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next impact default series
    * @throws com.ext.portlet.NoSuchImpactDefaultSeriesException if a impact default series with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries[] findBySeriesId_PrevAndNext(
        ImpactDefaultSeriesPK impactDefaultSeriesPK, long seriesId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySeriesId_PrevAndNext(impactDefaultSeriesPK, seriesId,
            orderByComparator);
    }

    /**
    * Removes all the impact default serieses where seriesId = &#63; from the database.
    *
    * @param seriesId the series ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeBySeriesId(long seriesId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeBySeriesId(seriesId);
    }

    /**
    * Returns the number of impact default serieses where seriesId = &#63;.
    *
    * @param seriesId the series ID
    * @return the number of matching impact default serieses
    * @throws SystemException if a system exception occurred
    */
    public static int countBySeriesId(long seriesId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countBySeriesId(seriesId);
    }

    /**
    * Returns all the impact default serieses where seriesId = &#63; and editable = &#63;.
    *
    * @param seriesId the series ID
    * @param editable the editable
    * @return the matching impact default serieses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactDefaultSeries> findBySeriesIdAndEditable(
        long seriesId, boolean editable)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySeriesIdAndEditable(seriesId, editable);
    }

    /**
    * Returns a range of all the impact default serieses where seriesId = &#63; and editable = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactDefaultSeriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param seriesId the series ID
    * @param editable the editable
    * @param start the lower bound of the range of impact default serieses
    * @param end the upper bound of the range of impact default serieses (not inclusive)
    * @return the range of matching impact default serieses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactDefaultSeries> findBySeriesIdAndEditable(
        long seriesId, boolean editable, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySeriesIdAndEditable(seriesId, editable, start, end);
    }

    /**
    * Returns an ordered range of all the impact default serieses where seriesId = &#63; and editable = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactDefaultSeriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param seriesId the series ID
    * @param editable the editable
    * @param start the lower bound of the range of impact default serieses
    * @param end the upper bound of the range of impact default serieses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching impact default serieses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactDefaultSeries> findBySeriesIdAndEditable(
        long seriesId, boolean editable, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySeriesIdAndEditable(seriesId, editable, start, end,
            orderByComparator);
    }

    /**
    * Returns the first impact default series in the ordered set where seriesId = &#63; and editable = &#63;.
    *
    * @param seriesId the series ID
    * @param editable the editable
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching impact default series
    * @throws com.ext.portlet.NoSuchImpactDefaultSeriesException if a matching impact default series could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries findBySeriesIdAndEditable_First(
        long seriesId, boolean editable,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySeriesIdAndEditable_First(seriesId, editable,
            orderByComparator);
    }

    /**
    * Returns the first impact default series in the ordered set where seriesId = &#63; and editable = &#63;.
    *
    * @param seriesId the series ID
    * @param editable the editable
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching impact default series, or <code>null</code> if a matching impact default series could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries fetchBySeriesIdAndEditable_First(
        long seriesId, boolean editable,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySeriesIdAndEditable_First(seriesId, editable,
            orderByComparator);
    }

    /**
    * Returns the last impact default series in the ordered set where seriesId = &#63; and editable = &#63;.
    *
    * @param seriesId the series ID
    * @param editable the editable
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching impact default series
    * @throws com.ext.portlet.NoSuchImpactDefaultSeriesException if a matching impact default series could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries findBySeriesIdAndEditable_Last(
        long seriesId, boolean editable,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySeriesIdAndEditable_Last(seriesId, editable,
            orderByComparator);
    }

    /**
    * Returns the last impact default series in the ordered set where seriesId = &#63; and editable = &#63;.
    *
    * @param seriesId the series ID
    * @param editable the editable
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching impact default series, or <code>null</code> if a matching impact default series could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries fetchBySeriesIdAndEditable_Last(
        long seriesId, boolean editable,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySeriesIdAndEditable_Last(seriesId, editable,
            orderByComparator);
    }

    /**
    * Returns the impact default serieses before and after the current impact default series in the ordered set where seriesId = &#63; and editable = &#63;.
    *
    * @param impactDefaultSeriesPK the primary key of the current impact default series
    * @param seriesId the series ID
    * @param editable the editable
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next impact default series
    * @throws com.ext.portlet.NoSuchImpactDefaultSeriesException if a impact default series with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries[] findBySeriesIdAndEditable_PrevAndNext(
        ImpactDefaultSeriesPK impactDefaultSeriesPK, long seriesId,
        boolean editable,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySeriesIdAndEditable_PrevAndNext(impactDefaultSeriesPK,
            seriesId, editable, orderByComparator);
    }

    /**
    * Removes all the impact default serieses where seriesId = &#63; and editable = &#63; from the database.
    *
    * @param seriesId the series ID
    * @param editable the editable
    * @throws SystemException if a system exception occurred
    */
    public static void removeBySeriesIdAndEditable(long seriesId,
        boolean editable)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeBySeriesIdAndEditable(seriesId, editable);
    }

    /**
    * Returns the number of impact default serieses where seriesId = &#63; and editable = &#63;.
    *
    * @param seriesId the series ID
    * @param editable the editable
    * @return the number of matching impact default serieses
    * @throws SystemException if a system exception occurred
    */
    public static int countBySeriesIdAndEditable(long seriesId, boolean editable)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countBySeriesIdAndEditable(seriesId, editable);
    }

    /**
    * Returns all the impact default serieses where focusAreaId = &#63;.
    *
    * @param focusAreaId the focus area ID
    * @return the matching impact default serieses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactDefaultSeries> findByFocusAreaId(
        long focusAreaId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByFocusAreaId(focusAreaId);
    }

    /**
    * Returns a range of all the impact default serieses where focusAreaId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactDefaultSeriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param focusAreaId the focus area ID
    * @param start the lower bound of the range of impact default serieses
    * @param end the upper bound of the range of impact default serieses (not inclusive)
    * @return the range of matching impact default serieses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactDefaultSeries> findByFocusAreaId(
        long focusAreaId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByFocusAreaId(focusAreaId, start, end);
    }

    /**
    * Returns an ordered range of all the impact default serieses where focusAreaId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactDefaultSeriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param focusAreaId the focus area ID
    * @param start the lower bound of the range of impact default serieses
    * @param end the upper bound of the range of impact default serieses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching impact default serieses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactDefaultSeries> findByFocusAreaId(
        long focusAreaId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFocusAreaId(focusAreaId, start, end, orderByComparator);
    }

    /**
    * Returns the first impact default series in the ordered set where focusAreaId = &#63;.
    *
    * @param focusAreaId the focus area ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching impact default series
    * @throws com.ext.portlet.NoSuchImpactDefaultSeriesException if a matching impact default series could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries findByFocusAreaId_First(
        long focusAreaId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFocusAreaId_First(focusAreaId, orderByComparator);
    }

    /**
    * Returns the first impact default series in the ordered set where focusAreaId = &#63;.
    *
    * @param focusAreaId the focus area ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching impact default series, or <code>null</code> if a matching impact default series could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries fetchByFocusAreaId_First(
        long focusAreaId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByFocusAreaId_First(focusAreaId, orderByComparator);
    }

    /**
    * Returns the last impact default series in the ordered set where focusAreaId = &#63;.
    *
    * @param focusAreaId the focus area ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching impact default series
    * @throws com.ext.portlet.NoSuchImpactDefaultSeriesException if a matching impact default series could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries findByFocusAreaId_Last(
        long focusAreaId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFocusAreaId_Last(focusAreaId, orderByComparator);
    }

    /**
    * Returns the last impact default series in the ordered set where focusAreaId = &#63;.
    *
    * @param focusAreaId the focus area ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching impact default series, or <code>null</code> if a matching impact default series could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries fetchByFocusAreaId_Last(
        long focusAreaId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByFocusAreaId_Last(focusAreaId, orderByComparator);
    }

    /**
    * Returns the impact default serieses before and after the current impact default series in the ordered set where focusAreaId = &#63;.
    *
    * @param impactDefaultSeriesPK the primary key of the current impact default series
    * @param focusAreaId the focus area ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next impact default series
    * @throws com.ext.portlet.NoSuchImpactDefaultSeriesException if a impact default series with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries[] findByFocusAreaId_PrevAndNext(
        ImpactDefaultSeriesPK impactDefaultSeriesPK, long focusAreaId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByFocusAreaId_PrevAndNext(impactDefaultSeriesPK,
            focusAreaId, orderByComparator);
    }

    /**
    * Removes all the impact default serieses where focusAreaId = &#63; from the database.
    *
    * @param focusAreaId the focus area ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByFocusAreaId(long focusAreaId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByFocusAreaId(focusAreaId);
    }

    /**
    * Returns the number of impact default serieses where focusAreaId = &#63;.
    *
    * @param focusAreaId the focus area ID
    * @return the number of matching impact default serieses
    * @throws SystemException if a system exception occurred
    */
    public static int countByFocusAreaId(long focusAreaId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByFocusAreaId(focusAreaId);
    }

    /**
    * Returns the impact default series where focusAreaId = &#63; and name = &#63; or throws a {@link com.ext.portlet.NoSuchImpactDefaultSeriesException} if it could not be found.
    *
    * @param focusAreaId the focus area ID
    * @param name the name
    * @return the matching impact default series
    * @throws com.ext.portlet.NoSuchImpactDefaultSeriesException if a matching impact default series could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries findByFocusAreaIdAndName(
        long focusAreaId, java.lang.String name)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByFocusAreaIdAndName(focusAreaId, name);
    }

    /**
    * Returns the impact default series where focusAreaId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param focusAreaId the focus area ID
    * @param name the name
    * @return the matching impact default series, or <code>null</code> if a matching impact default series could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries fetchByFocusAreaIdAndName(
        long focusAreaId, java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByFocusAreaIdAndName(focusAreaId, name);
    }

    /**
    * Returns the impact default series where focusAreaId = &#63; and name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param focusAreaId the focus area ID
    * @param name the name
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching impact default series, or <code>null</code> if a matching impact default series could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries fetchByFocusAreaIdAndName(
        long focusAreaId, java.lang.String name, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByFocusAreaIdAndName(focusAreaId, name,
            retrieveFromCache);
    }

    /**
    * Removes the impact default series where focusAreaId = &#63; and name = &#63; from the database.
    *
    * @param focusAreaId the focus area ID
    * @param name the name
    * @return the impact default series that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries removeByFocusAreaIdAndName(
        long focusAreaId, java.lang.String name)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().removeByFocusAreaIdAndName(focusAreaId, name);
    }

    /**
    * Returns the number of impact default serieses where focusAreaId = &#63; and name = &#63;.
    *
    * @param focusAreaId the focus area ID
    * @param name the name
    * @return the number of matching impact default serieses
    * @throws SystemException if a system exception occurred
    */
    public static int countByFocusAreaIdAndName(long focusAreaId,
        java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByFocusAreaIdAndName(focusAreaId, name);
    }

    /**
    * Caches the impact default series in the entity cache if it is enabled.
    *
    * @param impactDefaultSeries the impact default series
    */
    public static void cacheResult(
        com.ext.portlet.model.ImpactDefaultSeries impactDefaultSeries) {
        getPersistence().cacheResult(impactDefaultSeries);
    }

    /**
    * Caches the impact default serieses in the entity cache if it is enabled.
    *
    * @param impactDefaultSerieses the impact default serieses
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ImpactDefaultSeries> impactDefaultSerieses) {
        getPersistence().cacheResult(impactDefaultSerieses);
    }

    /**
    * Creates a new impact default series with the primary key. Does not add the impact default series to the database.
    *
    * @param impactDefaultSeriesPK the primary key for the new impact default series
    * @return the new impact default series
    */
    public static com.ext.portlet.model.ImpactDefaultSeries create(
        ImpactDefaultSeriesPK impactDefaultSeriesPK) {
        return getPersistence().create(impactDefaultSeriesPK);
    }

    /**
    * Removes the impact default series with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param impactDefaultSeriesPK the primary key of the impact default series
    * @return the impact default series that was removed
    * @throws com.ext.portlet.NoSuchImpactDefaultSeriesException if a impact default series with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries remove(
        ImpactDefaultSeriesPK impactDefaultSeriesPK)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(impactDefaultSeriesPK);
    }

    public static com.ext.portlet.model.ImpactDefaultSeries updateImpl(
        com.ext.portlet.model.ImpactDefaultSeries impactDefaultSeries)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(impactDefaultSeries);
    }

    /**
    * Returns the impact default series with the primary key or throws a {@link com.ext.portlet.NoSuchImpactDefaultSeriesException} if it could not be found.
    *
    * @param impactDefaultSeriesPK the primary key of the impact default series
    * @return the impact default series
    * @throws com.ext.portlet.NoSuchImpactDefaultSeriesException if a impact default series with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries findByPrimaryKey(
        ImpactDefaultSeriesPK impactDefaultSeriesPK)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(impactDefaultSeriesPK);
    }

    /**
    * Returns the impact default series with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param impactDefaultSeriesPK the primary key of the impact default series
    * @return the impact default series, or <code>null</code> if a impact default series with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries fetchByPrimaryKey(
        ImpactDefaultSeriesPK impactDefaultSeriesPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(impactDefaultSeriesPK);
    }

    /**
    * Returns all the impact default serieses.
    *
    * @return the impact default serieses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactDefaultSeries> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the impact default serieses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactDefaultSeriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of impact default serieses
    * @param end the upper bound of the range of impact default serieses (not inclusive)
    * @return the range of impact default serieses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactDefaultSeries> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the impact default serieses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactDefaultSeriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of impact default serieses
    * @param end the upper bound of the range of impact default serieses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of impact default serieses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactDefaultSeries> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the impact default serieses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of impact default serieses.
    *
    * @return the number of impact default serieses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ImpactDefaultSeriesPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ImpactDefaultSeriesPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ImpactDefaultSeriesPersistence.class.getName());

            ReferenceRegistry.registerReference(ImpactDefaultSeriesUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ImpactDefaultSeriesPersistence persistence) {
    }
}
