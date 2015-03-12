package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ImpactDefaultSeriesData;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the impact default series data service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactDefaultSeriesDataPersistenceImpl
 * @see ImpactDefaultSeriesDataUtil
 * @generated
 */
public interface ImpactDefaultSeriesDataPersistence extends BasePersistence<ImpactDefaultSeriesData> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ImpactDefaultSeriesDataUtil} to access the impact default series data persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the impact default series datas where seriesId = &#63;.
    *
    * @param seriesId the series ID
    * @return the matching impact default series datas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ImpactDefaultSeriesData> findBySeriesId(
        long seriesId)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.ImpactDefaultSeriesData> findBySeriesId(
        long seriesId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.ImpactDefaultSeriesData> findBySeriesId(
        long seriesId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first impact default series data in the ordered set where seriesId = &#63;.
    *
    * @param seriesId the series ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching impact default series data
    * @throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException if a matching impact default series data could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactDefaultSeriesData findBySeriesId_First(
        long seriesId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first impact default series data in the ordered set where seriesId = &#63;.
    *
    * @param seriesId the series ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching impact default series data, or <code>null</code> if a matching impact default series data could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactDefaultSeriesData fetchBySeriesId_First(
        long seriesId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last impact default series data in the ordered set where seriesId = &#63;.
    *
    * @param seriesId the series ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching impact default series data
    * @throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException if a matching impact default series data could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactDefaultSeriesData findBySeriesId_Last(
        long seriesId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last impact default series data in the ordered set where seriesId = &#63;.
    *
    * @param seriesId the series ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching impact default series data, or <code>null</code> if a matching impact default series data could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactDefaultSeriesData fetchBySeriesId_Last(
        long seriesId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.model.ImpactDefaultSeriesData[] findBySeriesId_PrevAndNext(
        ImpactDefaultSeriesDataPK impactDefaultSeriesDataPK, long seriesId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the impact default series datas where seriesId = &#63; from the database.
    *
    * @param seriesId the series ID
    * @throws SystemException if a system exception occurred
    */
    public void removeBySeriesId(long seriesId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of impact default series datas where seriesId = &#63;.
    *
    * @param seriesId the series ID
    * @return the number of matching impact default series datas
    * @throws SystemException if a system exception occurred
    */
    public int countBySeriesId(long seriesId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the impact default series data where seriesId = &#63; and year = &#63; or throws a {@link com.ext.portlet.NoSuchImpactDefaultSeriesDataException} if it could not be found.
    *
    * @param seriesId the series ID
    * @param year the year
    * @return the matching impact default series data
    * @throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException if a matching impact default series data could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactDefaultSeriesData findBySeriesIdAndYear(
        long seriesId, int year)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the impact default series data where seriesId = &#63; and year = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param seriesId the series ID
    * @param year the year
    * @return the matching impact default series data, or <code>null</code> if a matching impact default series data could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactDefaultSeriesData fetchBySeriesIdAndYear(
        long seriesId, int year)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the impact default series data where seriesId = &#63; and year = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param seriesId the series ID
    * @param year the year
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching impact default series data, or <code>null</code> if a matching impact default series data could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactDefaultSeriesData fetchBySeriesIdAndYear(
        long seriesId, int year, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the impact default series data where seriesId = &#63; and year = &#63; from the database.
    *
    * @param seriesId the series ID
    * @param year the year
    * @return the impact default series data that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactDefaultSeriesData removeBySeriesIdAndYear(
        long seriesId, int year)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of impact default series datas where seriesId = &#63; and year = &#63;.
    *
    * @param seriesId the series ID
    * @param year the year
    * @return the number of matching impact default series datas
    * @throws SystemException if a system exception occurred
    */
    public int countBySeriesIdAndYear(long seriesId, int year)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the impact default series data in the entity cache if it is enabled.
    *
    * @param impactDefaultSeriesData the impact default series data
    */
    public void cacheResult(
        com.ext.portlet.model.ImpactDefaultSeriesData impactDefaultSeriesData);

    /**
    * Caches the impact default series datas in the entity cache if it is enabled.
    *
    * @param impactDefaultSeriesDatas the impact default series datas
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ImpactDefaultSeriesData> impactDefaultSeriesDatas);

    /**
    * Creates a new impact default series data with the primary key. Does not add the impact default series data to the database.
    *
    * @param impactDefaultSeriesDataPK the primary key for the new impact default series data
    * @return the new impact default series data
    */
    public com.ext.portlet.model.ImpactDefaultSeriesData create(
        ImpactDefaultSeriesDataPK impactDefaultSeriesDataPK);

    /**
    * Removes the impact default series data with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param impactDefaultSeriesDataPK the primary key of the impact default series data
    * @return the impact default series data that was removed
    * @throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException if a impact default series data with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactDefaultSeriesData remove(
        ImpactDefaultSeriesDataPK impactDefaultSeriesDataPK)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ImpactDefaultSeriesData updateImpl(
        com.ext.portlet.model.ImpactDefaultSeriesData impactDefaultSeriesData)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the impact default series data with the primary key or throws a {@link com.ext.portlet.NoSuchImpactDefaultSeriesDataException} if it could not be found.
    *
    * @param impactDefaultSeriesDataPK the primary key of the impact default series data
    * @return the impact default series data
    * @throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException if a impact default series data with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactDefaultSeriesData findByPrimaryKey(
        ImpactDefaultSeriesDataPK impactDefaultSeriesDataPK)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the impact default series data with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param impactDefaultSeriesDataPK the primary key of the impact default series data
    * @return the impact default series data, or <code>null</code> if a impact default series data with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ImpactDefaultSeriesData fetchByPrimaryKey(
        ImpactDefaultSeriesDataPK impactDefaultSeriesDataPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the impact default series datas.
    *
    * @return the impact default series datas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ImpactDefaultSeriesData> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.ImpactDefaultSeriesData> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.ImpactDefaultSeriesData> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the impact default series datas from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of impact default series datas.
    *
    * @return the number of impact default series datas
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
