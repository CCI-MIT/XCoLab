package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ImpactDefaultSeriesData;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the impact default series data service. This utility wraps {@link ImpactDefaultSeriesDataPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactDefaultSeriesDataPersistence
 * @see ImpactDefaultSeriesDataPersistenceImpl
 * @generated
 */
public class ImpactDefaultSeriesDataUtil {
    private static ImpactDefaultSeriesDataPersistence _persistence;

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
    public static void clearCache(
        ImpactDefaultSeriesData impactDefaultSeriesData) {
        getPersistence().clearCache(impactDefaultSeriesData);
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
    public static List<ImpactDefaultSeriesData> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ImpactDefaultSeriesData> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ImpactDefaultSeriesData> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ImpactDefaultSeriesData update(
        ImpactDefaultSeriesData impactDefaultSeriesData)
        throws SystemException {
        return getPersistence().update(impactDefaultSeriesData);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ImpactDefaultSeriesData update(
        ImpactDefaultSeriesData impactDefaultSeriesData,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(impactDefaultSeriesData, serviceContext);
    }

    /**
    * Returns all the impact default series datas where seriesId = &#63;.
    *
    * @param seriesId the series ID
    * @return the matching impact default series datas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactDefaultSeriesData> findBySeriesId(
        long seriesId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySeriesId(seriesId);
    }

    /**
    * Returns a range of all the impact default series datas where seriesId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactDefaultSeriesDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param seriesId the series ID
    * @param start the lower bound of the range of impact default series datas
    * @param end the upper bound of the range of impact default series datas (not inclusive)
    * @return the range of matching impact default series datas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactDefaultSeriesData> findBySeriesId(
        long seriesId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySeriesId(seriesId, start, end);
    }

    /**
    * Returns an ordered range of all the impact default series datas where seriesId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactDefaultSeriesDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param seriesId the series ID
    * @param start the lower bound of the range of impact default series datas
    * @param end the upper bound of the range of impact default series datas (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching impact default series datas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactDefaultSeriesData> findBySeriesId(
        long seriesId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySeriesId(seriesId, start, end, orderByComparator);
    }

    /**
    * Returns the first impact default series data in the ordered set where seriesId = &#63;.
    *
    * @param seriesId the series ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching impact default series data
    * @throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException if a matching impact default series data could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeriesData findBySeriesId_First(
        long seriesId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySeriesId_First(seriesId, orderByComparator);
    }

    /**
    * Returns the first impact default series data in the ordered set where seriesId = &#63;.
    *
    * @param seriesId the series ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching impact default series data, or <code>null</code> if a matching impact default series data could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeriesData fetchBySeriesId_First(
        long seriesId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySeriesId_First(seriesId, orderByComparator);
    }

    /**
    * Returns the last impact default series data in the ordered set where seriesId = &#63;.
    *
    * @param seriesId the series ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching impact default series data
    * @throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException if a matching impact default series data could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeriesData findBySeriesId_Last(
        long seriesId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySeriesId_Last(seriesId, orderByComparator);
    }

    /**
    * Returns the last impact default series data in the ordered set where seriesId = &#63;.
    *
    * @param seriesId the series ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching impact default series data, or <code>null</code> if a matching impact default series data could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeriesData fetchBySeriesId_Last(
        long seriesId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchBySeriesId_Last(seriesId, orderByComparator);
    }

    /**
    * Returns the impact default series datas before and after the current impact default series data in the ordered set where seriesId = &#63;.
    *
    * @param impactDefaultSeriesDataPK the primary key of the current impact default series data
    * @param seriesId the series ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next impact default series data
    * @throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException if a impact default series data with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeriesData[] findBySeriesId_PrevAndNext(
        ImpactDefaultSeriesDataPK impactDefaultSeriesDataPK, long seriesId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySeriesId_PrevAndNext(impactDefaultSeriesDataPK,
            seriesId, orderByComparator);
    }

    /**
    * Removes all the impact default series datas where seriesId = &#63; from the database.
    *
    * @param seriesId the series ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeBySeriesId(long seriesId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeBySeriesId(seriesId);
    }

    /**
    * Returns the number of impact default series datas where seriesId = &#63;.
    *
    * @param seriesId the series ID
    * @return the number of matching impact default series datas
    * @throws SystemException if a system exception occurred
    */
    public static int countBySeriesId(long seriesId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countBySeriesId(seriesId);
    }

    /**
    * Returns the impact default series data where seriesId = &#63; and year = &#63; or throws a {@link com.ext.portlet.NoSuchImpactDefaultSeriesDataException} if it could not be found.
    *
    * @param seriesId the series ID
    * @param year the year
    * @return the matching impact default series data
    * @throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException if a matching impact default series data could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeriesData findBySeriesIdAndYear(
        long seriesId, int year)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySeriesIdAndYear(seriesId, year);
    }

    /**
    * Returns the impact default series data where seriesId = &#63; and year = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param seriesId the series ID
    * @param year the year
    * @return the matching impact default series data, or <code>null</code> if a matching impact default series data could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeriesData fetchBySeriesIdAndYear(
        long seriesId, int year)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchBySeriesIdAndYear(seriesId, year);
    }

    /**
    * Returns the impact default series data where seriesId = &#63; and year = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param seriesId the series ID
    * @param year the year
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching impact default series data, or <code>null</code> if a matching impact default series data could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeriesData fetchBySeriesIdAndYear(
        long seriesId, int year, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySeriesIdAndYear(seriesId, year, retrieveFromCache);
    }

    /**
    * Removes the impact default series data where seriesId = &#63; and year = &#63; from the database.
    *
    * @param seriesId the series ID
    * @param year the year
    * @return the impact default series data that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeriesData removeBySeriesIdAndYear(
        long seriesId, int year)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().removeBySeriesIdAndYear(seriesId, year);
    }

    /**
    * Returns the number of impact default series datas where seriesId = &#63; and year = &#63;.
    *
    * @param seriesId the series ID
    * @param year the year
    * @return the number of matching impact default series datas
    * @throws SystemException if a system exception occurred
    */
    public static int countBySeriesIdAndYear(long seriesId, int year)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countBySeriesIdAndYear(seriesId, year);
    }

    /**
    * Caches the impact default series data in the entity cache if it is enabled.
    *
    * @param impactDefaultSeriesData the impact default series data
    */
    public static void cacheResult(
        com.ext.portlet.model.ImpactDefaultSeriesData impactDefaultSeriesData) {
        getPersistence().cacheResult(impactDefaultSeriesData);
    }

    /**
    * Caches the impact default series datas in the entity cache if it is enabled.
    *
    * @param impactDefaultSeriesDatas the impact default series datas
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ImpactDefaultSeriesData> impactDefaultSeriesDatas) {
        getPersistence().cacheResult(impactDefaultSeriesDatas);
    }

    /**
    * Creates a new impact default series data with the primary key. Does not add the impact default series data to the database.
    *
    * @param impactDefaultSeriesDataPK the primary key for the new impact default series data
    * @return the new impact default series data
    */
    public static com.ext.portlet.model.ImpactDefaultSeriesData create(
        ImpactDefaultSeriesDataPK impactDefaultSeriesDataPK) {
        return getPersistence().create(impactDefaultSeriesDataPK);
    }

    /**
    * Removes the impact default series data with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param impactDefaultSeriesDataPK the primary key of the impact default series data
    * @return the impact default series data that was removed
    * @throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException if a impact default series data with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeriesData remove(
        ImpactDefaultSeriesDataPK impactDefaultSeriesDataPK)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(impactDefaultSeriesDataPK);
    }

    public static com.ext.portlet.model.ImpactDefaultSeriesData updateImpl(
        com.ext.portlet.model.ImpactDefaultSeriesData impactDefaultSeriesData)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(impactDefaultSeriesData);
    }

    /**
    * Returns the impact default series data with the primary key or throws a {@link com.ext.portlet.NoSuchImpactDefaultSeriesDataException} if it could not be found.
    *
    * @param impactDefaultSeriesDataPK the primary key of the impact default series data
    * @return the impact default series data
    * @throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException if a impact default series data with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeriesData findByPrimaryKey(
        ImpactDefaultSeriesDataPK impactDefaultSeriesDataPK)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(impactDefaultSeriesDataPK);
    }

    /**
    * Returns the impact default series data with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param impactDefaultSeriesDataPK the primary key of the impact default series data
    * @return the impact default series data, or <code>null</code> if a impact default series data with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeriesData fetchByPrimaryKey(
        ImpactDefaultSeriesDataPK impactDefaultSeriesDataPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(impactDefaultSeriesDataPK);
    }

    /**
    * Returns all the impact default series datas.
    *
    * @return the impact default series datas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactDefaultSeriesData> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the impact default series datas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactDefaultSeriesDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of impact default series datas
    * @param end the upper bound of the range of impact default series datas (not inclusive)
    * @return the range of impact default series datas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactDefaultSeriesData> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the impact default series datas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactDefaultSeriesDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of impact default series datas
    * @param end the upper bound of the range of impact default series datas (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of impact default series datas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactDefaultSeriesData> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the impact default series datas from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of impact default series datas.
    *
    * @return the number of impact default series datas
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ImpactDefaultSeriesDataPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ImpactDefaultSeriesDataPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ImpactDefaultSeriesDataPersistence.class.getName());

            ReferenceRegistry.registerReference(ImpactDefaultSeriesDataUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ImpactDefaultSeriesDataPersistence persistence) {
    }
}
