package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchImpactDefaultSeriesDataException;
import com.ext.portlet.model.ImpactDefaultSeriesData;
import com.ext.portlet.model.impl.ImpactDefaultSeriesDataImpl;
import com.ext.portlet.model.impl.ImpactDefaultSeriesDataModelImpl;
import com.ext.portlet.service.persistence.ImpactDefaultSeriesDataPersistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the impact default series data service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactDefaultSeriesDataPersistence
 * @see ImpactDefaultSeriesDataUtil
 * @generated
 */
public class ImpactDefaultSeriesDataPersistenceImpl extends BasePersistenceImpl<ImpactDefaultSeriesData>
    implements ImpactDefaultSeriesDataPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ImpactDefaultSeriesDataUtil} to access the impact default series data persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ImpactDefaultSeriesDataImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImpactDefaultSeriesDataModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesDataModelImpl.FINDER_CACHE_ENABLED,
            ImpactDefaultSeriesDataImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImpactDefaultSeriesDataModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesDataModelImpl.FINDER_CACHE_ENABLED,
            ImpactDefaultSeriesDataImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImpactDefaultSeriesDataModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesDataModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SERIESID = new FinderPath(ImpactDefaultSeriesDataModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesDataModelImpl.FINDER_CACHE_ENABLED,
            ImpactDefaultSeriesDataImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySeriesId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERIESID =
        new FinderPath(ImpactDefaultSeriesDataModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesDataModelImpl.FINDER_CACHE_ENABLED,
            ImpactDefaultSeriesDataImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySeriesId",
            new String[] { Long.class.getName() },
            ImpactDefaultSeriesDataModelImpl.SERIESID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_SERIESID = new FinderPath(ImpactDefaultSeriesDataModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesDataModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySeriesId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_SERIESID_SERIESID_2 = "impactDefaultSeriesData.id.seriesId = ?";
    public static final FinderPath FINDER_PATH_FETCH_BY_SERIESIDANDYEAR = new FinderPath(ImpactDefaultSeriesDataModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesDataModelImpl.FINDER_CACHE_ENABLED,
            ImpactDefaultSeriesDataImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchBySeriesIdAndYear",
            new String[] { Long.class.getName(), Integer.class.getName() },
            ImpactDefaultSeriesDataModelImpl.SERIESID_COLUMN_BITMASK |
            ImpactDefaultSeriesDataModelImpl.YEAR_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_SERIESIDANDYEAR = new FinderPath(ImpactDefaultSeriesDataModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesDataModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countBySeriesIdAndYear",
            new String[] { Long.class.getName(), Integer.class.getName() });
    private static final String _FINDER_COLUMN_SERIESIDANDYEAR_SERIESID_2 = "impactDefaultSeriesData.id.seriesId = ? AND ";
    private static final String _FINDER_COLUMN_SERIESIDANDYEAR_YEAR_2 = "impactDefaultSeriesData.id.year = ?";
    private static final String _SQL_SELECT_IMPACTDEFAULTSERIESDATA = "SELECT impactDefaultSeriesData FROM ImpactDefaultSeriesData impactDefaultSeriesData";
    private static final String _SQL_SELECT_IMPACTDEFAULTSERIESDATA_WHERE = "SELECT impactDefaultSeriesData FROM ImpactDefaultSeriesData impactDefaultSeriesData WHERE ";
    private static final String _SQL_COUNT_IMPACTDEFAULTSERIESDATA = "SELECT COUNT(impactDefaultSeriesData) FROM ImpactDefaultSeriesData impactDefaultSeriesData";
    private static final String _SQL_COUNT_IMPACTDEFAULTSERIESDATA_WHERE = "SELECT COUNT(impactDefaultSeriesData) FROM ImpactDefaultSeriesData impactDefaultSeriesData WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "impactDefaultSeriesData.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImpactDefaultSeriesData exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ImpactDefaultSeriesData exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ImpactDefaultSeriesDataPersistenceImpl.class);
    private static ImpactDefaultSeriesData _nullImpactDefaultSeriesData = new ImpactDefaultSeriesDataImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ImpactDefaultSeriesData> toCacheModel() {
                return _nullImpactDefaultSeriesDataCacheModel;
            }
        };

    private static CacheModel<ImpactDefaultSeriesData> _nullImpactDefaultSeriesDataCacheModel =
        new CacheModel<ImpactDefaultSeriesData>() {
            @Override
            public ImpactDefaultSeriesData toEntityModel() {
                return _nullImpactDefaultSeriesData;
            }
        };

    public ImpactDefaultSeriesDataPersistenceImpl() {
        setModelClass(ImpactDefaultSeriesData.class);
    }

    /**
     * Returns all the impact default series datas where seriesId = &#63;.
     *
     * @param seriesId the series ID
     * @return the matching impact default series datas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImpactDefaultSeriesData> findBySeriesId(long seriesId)
        throws SystemException {
        return findBySeriesId(seriesId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
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
    @Override
    public List<ImpactDefaultSeriesData> findBySeriesId(long seriesId,
        int start, int end) throws SystemException {
        return findBySeriesId(seriesId, start, end, null);
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
    @Override
    public List<ImpactDefaultSeriesData> findBySeriesId(long seriesId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERIESID;
            finderArgs = new Object[] { seriesId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SERIESID;
            finderArgs = new Object[] { seriesId, start, end, orderByComparator };
        }

        List<ImpactDefaultSeriesData> list = (List<ImpactDefaultSeriesData>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ImpactDefaultSeriesData impactDefaultSeriesData : list) {
                if ((seriesId != impactDefaultSeriesData.getSeriesId())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_IMPACTDEFAULTSERIESDATA_WHERE);

            query.append(_FINDER_COLUMN_SERIESID_SERIESID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ImpactDefaultSeriesDataModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(seriesId);

                if (!pagination) {
                    list = (List<ImpactDefaultSeriesData>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ImpactDefaultSeriesData>(list);
                } else {
                    list = (List<ImpactDefaultSeriesData>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
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
    @Override
    public ImpactDefaultSeriesData findBySeriesId_First(long seriesId,
        OrderByComparator orderByComparator)
        throws NoSuchImpactDefaultSeriesDataException, SystemException {
        ImpactDefaultSeriesData impactDefaultSeriesData = fetchBySeriesId_First(seriesId,
                orderByComparator);

        if (impactDefaultSeriesData != null) {
            return impactDefaultSeriesData;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("seriesId=");
        msg.append(seriesId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchImpactDefaultSeriesDataException(msg.toString());
    }

    /**
     * Returns the first impact default series data in the ordered set where seriesId = &#63;.
     *
     * @param seriesId the series ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching impact default series data, or <code>null</code> if a matching impact default series data could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeriesData fetchBySeriesId_First(long seriesId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ImpactDefaultSeriesData> list = findBySeriesId(seriesId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public ImpactDefaultSeriesData findBySeriesId_Last(long seriesId,
        OrderByComparator orderByComparator)
        throws NoSuchImpactDefaultSeriesDataException, SystemException {
        ImpactDefaultSeriesData impactDefaultSeriesData = fetchBySeriesId_Last(seriesId,
                orderByComparator);

        if (impactDefaultSeriesData != null) {
            return impactDefaultSeriesData;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("seriesId=");
        msg.append(seriesId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchImpactDefaultSeriesDataException(msg.toString());
    }

    /**
     * Returns the last impact default series data in the ordered set where seriesId = &#63;.
     *
     * @param seriesId the series ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching impact default series data, or <code>null</code> if a matching impact default series data could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeriesData fetchBySeriesId_Last(long seriesId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countBySeriesId(seriesId);

        if (count == 0) {
            return null;
        }

        List<ImpactDefaultSeriesData> list = findBySeriesId(seriesId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public ImpactDefaultSeriesData[] findBySeriesId_PrevAndNext(
        ImpactDefaultSeriesDataPK impactDefaultSeriesDataPK, long seriesId,
        OrderByComparator orderByComparator)
        throws NoSuchImpactDefaultSeriesDataException, SystemException {
        ImpactDefaultSeriesData impactDefaultSeriesData = findByPrimaryKey(impactDefaultSeriesDataPK);

        Session session = null;

        try {
            session = openSession();

            ImpactDefaultSeriesData[] array = new ImpactDefaultSeriesDataImpl[3];

            array[0] = getBySeriesId_PrevAndNext(session,
                    impactDefaultSeriesData, seriesId, orderByComparator, true);

            array[1] = impactDefaultSeriesData;

            array[2] = getBySeriesId_PrevAndNext(session,
                    impactDefaultSeriesData, seriesId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ImpactDefaultSeriesData getBySeriesId_PrevAndNext(
        Session session, ImpactDefaultSeriesData impactDefaultSeriesData,
        long seriesId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_IMPACTDEFAULTSERIESDATA_WHERE);

        query.append(_FINDER_COLUMN_SERIESID_SERIESID_2);

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        } else {
            query.append(ImpactDefaultSeriesDataModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(seriesId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(impactDefaultSeriesData);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ImpactDefaultSeriesData> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the impact default series datas where seriesId = &#63; from the database.
     *
     * @param seriesId the series ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeBySeriesId(long seriesId) throws SystemException {
        for (ImpactDefaultSeriesData impactDefaultSeriesData : findBySeriesId(
                seriesId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(impactDefaultSeriesData);
        }
    }

    /**
     * Returns the number of impact default series datas where seriesId = &#63;.
     *
     * @param seriesId the series ID
     * @return the number of matching impact default series datas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBySeriesId(long seriesId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_SERIESID;

        Object[] finderArgs = new Object[] { seriesId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_IMPACTDEFAULTSERIESDATA_WHERE);

            query.append(_FINDER_COLUMN_SERIESID_SERIESID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(seriesId);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
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
    @Override
    public ImpactDefaultSeriesData findBySeriesIdAndYear(long seriesId, int year)
        throws NoSuchImpactDefaultSeriesDataException, SystemException {
        ImpactDefaultSeriesData impactDefaultSeriesData = fetchBySeriesIdAndYear(seriesId,
                year);

        if (impactDefaultSeriesData == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("seriesId=");
            msg.append(seriesId);

            msg.append(", year=");
            msg.append(year);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchImpactDefaultSeriesDataException(msg.toString());
        }

        return impactDefaultSeriesData;
    }

    /**
     * Returns the impact default series data where seriesId = &#63; and year = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param seriesId the series ID
     * @param year the year
     * @return the matching impact default series data, or <code>null</code> if a matching impact default series data could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeriesData fetchBySeriesIdAndYear(long seriesId,
        int year) throws SystemException {
        return fetchBySeriesIdAndYear(seriesId, year, true);
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
    @Override
    public ImpactDefaultSeriesData fetchBySeriesIdAndYear(long seriesId,
        int year, boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { seriesId, year };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_SERIESIDANDYEAR,
                    finderArgs, this);
        }

        if (result instanceof ImpactDefaultSeriesData) {
            ImpactDefaultSeriesData impactDefaultSeriesData = (ImpactDefaultSeriesData) result;

            if ((seriesId != impactDefaultSeriesData.getSeriesId()) ||
                    (year != impactDefaultSeriesData.getYear())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_SELECT_IMPACTDEFAULTSERIESDATA_WHERE);

            query.append(_FINDER_COLUMN_SERIESIDANDYEAR_SERIESID_2);

            query.append(_FINDER_COLUMN_SERIESIDANDYEAR_YEAR_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(seriesId);

                qPos.add(year);

                List<ImpactDefaultSeriesData> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SERIESIDANDYEAR,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "ImpactDefaultSeriesDataPersistenceImpl.fetchBySeriesIdAndYear(long, int, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    ImpactDefaultSeriesData impactDefaultSeriesData = list.get(0);

                    result = impactDefaultSeriesData;

                    cacheResult(impactDefaultSeriesData);

                    if ((impactDefaultSeriesData.getSeriesId() != seriesId) ||
                            (impactDefaultSeriesData.getYear() != year)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SERIESIDANDYEAR,
                            finderArgs, impactDefaultSeriesData);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SERIESIDANDYEAR,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (ImpactDefaultSeriesData) result;
        }
    }

    /**
     * Removes the impact default series data where seriesId = &#63; and year = &#63; from the database.
     *
     * @param seriesId the series ID
     * @param year the year
     * @return the impact default series data that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeriesData removeBySeriesIdAndYear(long seriesId,
        int year)
        throws NoSuchImpactDefaultSeriesDataException, SystemException {
        ImpactDefaultSeriesData impactDefaultSeriesData = findBySeriesIdAndYear(seriesId,
                year);

        return remove(impactDefaultSeriesData);
    }

    /**
     * Returns the number of impact default series datas where seriesId = &#63; and year = &#63;.
     *
     * @param seriesId the series ID
     * @param year the year
     * @return the number of matching impact default series datas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBySeriesIdAndYear(long seriesId, int year)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_SERIESIDANDYEAR;

        Object[] finderArgs = new Object[] { seriesId, year };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_IMPACTDEFAULTSERIESDATA_WHERE);

            query.append(_FINDER_COLUMN_SERIESIDANDYEAR_SERIESID_2);

            query.append(_FINDER_COLUMN_SERIESIDANDYEAR_YEAR_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(seriesId);

                qPos.add(year);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Caches the impact default series data in the entity cache if it is enabled.
     *
     * @param impactDefaultSeriesData the impact default series data
     */
    @Override
    public void cacheResult(ImpactDefaultSeriesData impactDefaultSeriesData) {
        EntityCacheUtil.putResult(ImpactDefaultSeriesDataModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesDataImpl.class,
            impactDefaultSeriesData.getPrimaryKey(), impactDefaultSeriesData);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SERIESIDANDYEAR,
            new Object[] {
                impactDefaultSeriesData.getSeriesId(),
                impactDefaultSeriesData.getYear()
            }, impactDefaultSeriesData);

        impactDefaultSeriesData.resetOriginalValues();
    }

    /**
     * Caches the impact default series datas in the entity cache if it is enabled.
     *
     * @param impactDefaultSeriesDatas the impact default series datas
     */
    @Override
    public void cacheResult(
        List<ImpactDefaultSeriesData> impactDefaultSeriesDatas) {
        for (ImpactDefaultSeriesData impactDefaultSeriesData : impactDefaultSeriesDatas) {
            if (EntityCacheUtil.getResult(
                        ImpactDefaultSeriesDataModelImpl.ENTITY_CACHE_ENABLED,
                        ImpactDefaultSeriesDataImpl.class,
                        impactDefaultSeriesData.getPrimaryKey()) == null) {
                cacheResult(impactDefaultSeriesData);
            } else {
                impactDefaultSeriesData.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all impact default series datas.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ImpactDefaultSeriesDataImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ImpactDefaultSeriesDataImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the impact default series data.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ImpactDefaultSeriesData impactDefaultSeriesData) {
        EntityCacheUtil.removeResult(ImpactDefaultSeriesDataModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesDataImpl.class,
            impactDefaultSeriesData.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(impactDefaultSeriesData);
    }

    @Override
    public void clearCache(
        List<ImpactDefaultSeriesData> impactDefaultSeriesDatas) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ImpactDefaultSeriesData impactDefaultSeriesData : impactDefaultSeriesDatas) {
            EntityCacheUtil.removeResult(ImpactDefaultSeriesDataModelImpl.ENTITY_CACHE_ENABLED,
                ImpactDefaultSeriesDataImpl.class,
                impactDefaultSeriesData.getPrimaryKey());

            clearUniqueFindersCache(impactDefaultSeriesData);
        }
    }

    protected void cacheUniqueFindersCache(
        ImpactDefaultSeriesData impactDefaultSeriesData) {
        if (impactDefaultSeriesData.isNew()) {
            Object[] args = new Object[] {
                    impactDefaultSeriesData.getSeriesId(),
                    impactDefaultSeriesData.getYear()
                };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SERIESIDANDYEAR,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SERIESIDANDYEAR,
                args, impactDefaultSeriesData);
        } else {
            ImpactDefaultSeriesDataModelImpl impactDefaultSeriesDataModelImpl = (ImpactDefaultSeriesDataModelImpl) impactDefaultSeriesData;

            if ((impactDefaultSeriesDataModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_SERIESIDANDYEAR.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        impactDefaultSeriesData.getSeriesId(),
                        impactDefaultSeriesData.getYear()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_SERIESIDANDYEAR,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_SERIESIDANDYEAR,
                    args, impactDefaultSeriesData);
            }
        }
    }

    protected void clearUniqueFindersCache(
        ImpactDefaultSeriesData impactDefaultSeriesData) {
        ImpactDefaultSeriesDataModelImpl impactDefaultSeriesDataModelImpl = (ImpactDefaultSeriesDataModelImpl) impactDefaultSeriesData;

        Object[] args = new Object[] {
                impactDefaultSeriesData.getSeriesId(),
                impactDefaultSeriesData.getYear()
            };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SERIESIDANDYEAR, args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SERIESIDANDYEAR, args);

        if ((impactDefaultSeriesDataModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_SERIESIDANDYEAR.getColumnBitmask()) != 0) {
            args = new Object[] {
                    impactDefaultSeriesDataModelImpl.getOriginalSeriesId(),
                    impactDefaultSeriesDataModelImpl.getOriginalYear()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SERIESIDANDYEAR,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_SERIESIDANDYEAR,
                args);
        }
    }

    /**
     * Creates a new impact default series data with the primary key. Does not add the impact default series data to the database.
     *
     * @param impactDefaultSeriesDataPK the primary key for the new impact default series data
     * @return the new impact default series data
     */
    @Override
    public ImpactDefaultSeriesData create(
        ImpactDefaultSeriesDataPK impactDefaultSeriesDataPK) {
        ImpactDefaultSeriesData impactDefaultSeriesData = new ImpactDefaultSeriesDataImpl();

        impactDefaultSeriesData.setNew(true);
        impactDefaultSeriesData.setPrimaryKey(impactDefaultSeriesDataPK);

        return impactDefaultSeriesData;
    }

    /**
     * Removes the impact default series data with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param impactDefaultSeriesDataPK the primary key of the impact default series data
     * @return the impact default series data that was removed
     * @throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException if a impact default series data with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeriesData remove(
        ImpactDefaultSeriesDataPK impactDefaultSeriesDataPK)
        throws NoSuchImpactDefaultSeriesDataException, SystemException {
        return remove((Serializable) impactDefaultSeriesDataPK);
    }

    /**
     * Removes the impact default series data with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the impact default series data
     * @return the impact default series data that was removed
     * @throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException if a impact default series data with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeriesData remove(Serializable primaryKey)
        throws NoSuchImpactDefaultSeriesDataException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ImpactDefaultSeriesData impactDefaultSeriesData = (ImpactDefaultSeriesData) session.get(ImpactDefaultSeriesDataImpl.class,
                    primaryKey);

            if (impactDefaultSeriesData == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchImpactDefaultSeriesDataException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(impactDefaultSeriesData);
        } catch (NoSuchImpactDefaultSeriesDataException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ImpactDefaultSeriesData removeImpl(
        ImpactDefaultSeriesData impactDefaultSeriesData)
        throws SystemException {
        impactDefaultSeriesData = toUnwrappedModel(impactDefaultSeriesData);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(impactDefaultSeriesData)) {
                impactDefaultSeriesData = (ImpactDefaultSeriesData) session.get(ImpactDefaultSeriesDataImpl.class,
                        impactDefaultSeriesData.getPrimaryKeyObj());
            }

            if (impactDefaultSeriesData != null) {
                session.delete(impactDefaultSeriesData);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (impactDefaultSeriesData != null) {
            clearCache(impactDefaultSeriesData);
        }

        return impactDefaultSeriesData;
    }

    @Override
    public ImpactDefaultSeriesData updateImpl(
        com.ext.portlet.model.ImpactDefaultSeriesData impactDefaultSeriesData)
        throws SystemException {
        impactDefaultSeriesData = toUnwrappedModel(impactDefaultSeriesData);

        boolean isNew = impactDefaultSeriesData.isNew();

        ImpactDefaultSeriesDataModelImpl impactDefaultSeriesDataModelImpl = (ImpactDefaultSeriesDataModelImpl) impactDefaultSeriesData;

        Session session = null;

        try {
            session = openSession();

            if (impactDefaultSeriesData.isNew()) {
                session.save(impactDefaultSeriesData);

                impactDefaultSeriesData.setNew(false);
            } else {
                session.merge(impactDefaultSeriesData);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ImpactDefaultSeriesDataModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((impactDefaultSeriesDataModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERIESID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        impactDefaultSeriesDataModelImpl.getOriginalSeriesId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SERIESID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERIESID,
                    args);

                args = new Object[] {
                        impactDefaultSeriesDataModelImpl.getSeriesId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SERIESID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERIESID,
                    args);
            }
        }

        EntityCacheUtil.putResult(ImpactDefaultSeriesDataModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesDataImpl.class,
            impactDefaultSeriesData.getPrimaryKey(), impactDefaultSeriesData);

        clearUniqueFindersCache(impactDefaultSeriesData);
        cacheUniqueFindersCache(impactDefaultSeriesData);

        return impactDefaultSeriesData;
    }

    protected ImpactDefaultSeriesData toUnwrappedModel(
        ImpactDefaultSeriesData impactDefaultSeriesData) {
        if (impactDefaultSeriesData instanceof ImpactDefaultSeriesDataImpl) {
            return impactDefaultSeriesData;
        }

        ImpactDefaultSeriesDataImpl impactDefaultSeriesDataImpl = new ImpactDefaultSeriesDataImpl();

        impactDefaultSeriesDataImpl.setNew(impactDefaultSeriesData.isNew());
        impactDefaultSeriesDataImpl.setPrimaryKey(impactDefaultSeriesData.getPrimaryKey());

        impactDefaultSeriesDataImpl.setSeriesId(impactDefaultSeriesData.getSeriesId());
        impactDefaultSeriesDataImpl.setYear(impactDefaultSeriesData.getYear());
        impactDefaultSeriesDataImpl.setValue(impactDefaultSeriesData.getValue());

        return impactDefaultSeriesDataImpl;
    }

    /**
     * Returns the impact default series data with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the impact default series data
     * @return the impact default series data
     * @throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException if a impact default series data with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeriesData findByPrimaryKey(Serializable primaryKey)
        throws NoSuchImpactDefaultSeriesDataException, SystemException {
        ImpactDefaultSeriesData impactDefaultSeriesData = fetchByPrimaryKey(primaryKey);

        if (impactDefaultSeriesData == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchImpactDefaultSeriesDataException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return impactDefaultSeriesData;
    }

    /**
     * Returns the impact default series data with the primary key or throws a {@link com.ext.portlet.NoSuchImpactDefaultSeriesDataException} if it could not be found.
     *
     * @param impactDefaultSeriesDataPK the primary key of the impact default series data
     * @return the impact default series data
     * @throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException if a impact default series data with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeriesData findByPrimaryKey(
        ImpactDefaultSeriesDataPK impactDefaultSeriesDataPK)
        throws NoSuchImpactDefaultSeriesDataException, SystemException {
        return findByPrimaryKey((Serializable) impactDefaultSeriesDataPK);
    }

    /**
     * Returns the impact default series data with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the impact default series data
     * @return the impact default series data, or <code>null</code> if a impact default series data with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeriesData fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ImpactDefaultSeriesData impactDefaultSeriesData = (ImpactDefaultSeriesData) EntityCacheUtil.getResult(ImpactDefaultSeriesDataModelImpl.ENTITY_CACHE_ENABLED,
                ImpactDefaultSeriesDataImpl.class, primaryKey);

        if (impactDefaultSeriesData == _nullImpactDefaultSeriesData) {
            return null;
        }

        if (impactDefaultSeriesData == null) {
            Session session = null;

            try {
                session = openSession();

                impactDefaultSeriesData = (ImpactDefaultSeriesData) session.get(ImpactDefaultSeriesDataImpl.class,
                        primaryKey);

                if (impactDefaultSeriesData != null) {
                    cacheResult(impactDefaultSeriesData);
                } else {
                    EntityCacheUtil.putResult(ImpactDefaultSeriesDataModelImpl.ENTITY_CACHE_ENABLED,
                        ImpactDefaultSeriesDataImpl.class, primaryKey,
                        _nullImpactDefaultSeriesData);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ImpactDefaultSeriesDataModelImpl.ENTITY_CACHE_ENABLED,
                    ImpactDefaultSeriesDataImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return impactDefaultSeriesData;
    }

    /**
     * Returns the impact default series data with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param impactDefaultSeriesDataPK the primary key of the impact default series data
     * @return the impact default series data, or <code>null</code> if a impact default series data with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeriesData fetchByPrimaryKey(
        ImpactDefaultSeriesDataPK impactDefaultSeriesDataPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) impactDefaultSeriesDataPK);
    }

    /**
     * Returns all the impact default series datas.
     *
     * @return the impact default series datas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImpactDefaultSeriesData> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
    @Override
    public List<ImpactDefaultSeriesData> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
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
    @Override
    public List<ImpactDefaultSeriesData> findAll(int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
            finderArgs = FINDER_ARGS_EMPTY;
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
            finderArgs = new Object[] { start, end, orderByComparator };
        }

        List<ImpactDefaultSeriesData> list = (List<ImpactDefaultSeriesData>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IMPACTDEFAULTSERIESDATA);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IMPACTDEFAULTSERIESDATA;

                if (pagination) {
                    sql = sql.concat(ImpactDefaultSeriesDataModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ImpactDefaultSeriesData>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ImpactDefaultSeriesData>(list);
                } else {
                    list = (List<ImpactDefaultSeriesData>) QueryUtil.list(q,
                            getDialect(), start, end);
                }

                cacheResult(list);

                FinderCacheUtil.putResult(finderPath, finderArgs, list);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes all the impact default series datas from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ImpactDefaultSeriesData impactDefaultSeriesData : findAll()) {
            remove(impactDefaultSeriesData);
        }
    }

    /**
     * Returns the number of impact default series datas.
     *
     * @return the number of impact default series datas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_IMPACTDEFAULTSERIESDATA);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Initializes the impact default series data persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ImpactDefaultSeriesData")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ImpactDefaultSeriesData>> listenersList = new ArrayList<ModelListener<ImpactDefaultSeriesData>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ImpactDefaultSeriesData>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ImpactDefaultSeriesDataImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
