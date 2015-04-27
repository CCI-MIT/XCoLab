package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchImpactDefaultSeriesException;
import com.ext.portlet.model.ImpactDefaultSeries;
import com.ext.portlet.model.impl.ImpactDefaultSeriesImpl;
import com.ext.portlet.model.impl.ImpactDefaultSeriesModelImpl;
import com.ext.portlet.service.persistence.ImpactDefaultSeriesPersistence;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the impact default series service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactDefaultSeriesPersistence
 * @see ImpactDefaultSeriesUtil
 * @generated
 */
public class ImpactDefaultSeriesPersistenceImpl extends BasePersistenceImpl<ImpactDefaultSeries>
    implements ImpactDefaultSeriesPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ImpactDefaultSeriesUtil} to access the impact default series persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ImpactDefaultSeriesImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesModelImpl.FINDER_CACHE_ENABLED,
            ImpactDefaultSeriesImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesModelImpl.FINDER_CACHE_ENABLED,
            ImpactDefaultSeriesImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SERIESID = new FinderPath(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesModelImpl.FINDER_CACHE_ENABLED,
            ImpactDefaultSeriesImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBySeriesId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERIESID =
        new FinderPath(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesModelImpl.FINDER_CACHE_ENABLED,
            ImpactDefaultSeriesImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBySeriesId",
            new String[] { Long.class.getName() },
            ImpactDefaultSeriesModelImpl.SERIESID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_SERIESID = new FinderPath(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySeriesId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_SERIESID_SERIESID_2 = "impactDefaultSeries.id.seriesId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SERIESIDANDEDITABLE =
        new FinderPath(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesModelImpl.FINDER_CACHE_ENABLED,
            ImpactDefaultSeriesImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findBySeriesIdAndEditable",
            new String[] {
                Long.class.getName(), Boolean.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERIESIDANDEDITABLE =
        new FinderPath(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesModelImpl.FINDER_CACHE_ENABLED,
            ImpactDefaultSeriesImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findBySeriesIdAndEditable",
            new String[] { Long.class.getName(), Boolean.class.getName() },
            ImpactDefaultSeriesModelImpl.SERIESID_COLUMN_BITMASK |
            ImpactDefaultSeriesModelImpl.EDITABLE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_SERIESIDANDEDITABLE = new FinderPath(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countBySeriesIdAndEditable",
            new String[] { Long.class.getName(), Boolean.class.getName() });
    private static final String _FINDER_COLUMN_SERIESIDANDEDITABLE_SERIESID_2 = "impactDefaultSeries.id.seriesId = ? AND ";
    private static final String _FINDER_COLUMN_SERIESIDANDEDITABLE_EDITABLE_2 = "impactDefaultSeries.editable = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FOCUSAREAID =
        new FinderPath(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesModelImpl.FINDER_CACHE_ENABLED,
            ImpactDefaultSeriesImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFocusAreaId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FOCUSAREAID =
        new FinderPath(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesModelImpl.FINDER_CACHE_ENABLED,
            ImpactDefaultSeriesImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFocusAreaId",
            new String[] { Long.class.getName() },
            ImpactDefaultSeriesModelImpl.FOCUSAREAID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FOCUSAREAID = new FinderPath(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFocusAreaId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_FOCUSAREAID_FOCUSAREAID_2 = "impactDefaultSeries.focusAreaId = ?";
    public static final FinderPath FINDER_PATH_FETCH_BY_FOCUSAREAIDANDNAME = new FinderPath(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesModelImpl.FINDER_CACHE_ENABLED,
            ImpactDefaultSeriesImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByFocusAreaIdAndName",
            new String[] { Long.class.getName(), String.class.getName() },
            ImpactDefaultSeriesModelImpl.FOCUSAREAID_COLUMN_BITMASK |
            ImpactDefaultSeriesModelImpl.NAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FOCUSAREAIDANDNAME = new FinderPath(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByFocusAreaIdAndName",
            new String[] { Long.class.getName(), String.class.getName() });
    private static final String _FINDER_COLUMN_FOCUSAREAIDANDNAME_FOCUSAREAID_2 = "impactDefaultSeries.focusAreaId = ? AND ";
    private static final String _FINDER_COLUMN_FOCUSAREAIDANDNAME_NAME_1 = "impactDefaultSeries.id.name IS NULL";
    private static final String _FINDER_COLUMN_FOCUSAREAIDANDNAME_NAME_2 = "impactDefaultSeries.id.name = ?";
    private static final String _FINDER_COLUMN_FOCUSAREAIDANDNAME_NAME_3 = "(impactDefaultSeries.id.name IS NULL OR impactDefaultSeries.id.name = '')";
    private static final String _SQL_SELECT_IMPACTDEFAULTSERIES = "SELECT impactDefaultSeries FROM ImpactDefaultSeries impactDefaultSeries";
    private static final String _SQL_SELECT_IMPACTDEFAULTSERIES_WHERE = "SELECT impactDefaultSeries FROM ImpactDefaultSeries impactDefaultSeries WHERE ";
    private static final String _SQL_COUNT_IMPACTDEFAULTSERIES = "SELECT COUNT(impactDefaultSeries) FROM ImpactDefaultSeries impactDefaultSeries";
    private static final String _SQL_COUNT_IMPACTDEFAULTSERIES_WHERE = "SELECT COUNT(impactDefaultSeries) FROM ImpactDefaultSeries impactDefaultSeries WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "impactDefaultSeries.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImpactDefaultSeries exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ImpactDefaultSeries exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ImpactDefaultSeriesPersistenceImpl.class);
    private static ImpactDefaultSeries _nullImpactDefaultSeries = new ImpactDefaultSeriesImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ImpactDefaultSeries> toCacheModel() {
                return _nullImpactDefaultSeriesCacheModel;
            }
        };

    private static CacheModel<ImpactDefaultSeries> _nullImpactDefaultSeriesCacheModel =
        new CacheModel<ImpactDefaultSeries>() {
            @Override
            public ImpactDefaultSeries toEntityModel() {
                return _nullImpactDefaultSeries;
            }
        };

    public ImpactDefaultSeriesPersistenceImpl() {
        setModelClass(ImpactDefaultSeries.class);
    }

    /**
     * Returns all the impact default serieses where seriesId = &#63;.
     *
     * @param seriesId the series ID
     * @return the matching impact default serieses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImpactDefaultSeries> findBySeriesId(long seriesId)
        throws SystemException {
        return findBySeriesId(seriesId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
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
    @Override
    public List<ImpactDefaultSeries> findBySeriesId(long seriesId, int start,
        int end) throws SystemException {
        return findBySeriesId(seriesId, start, end, null);
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
    @Override
    public List<ImpactDefaultSeries> findBySeriesId(long seriesId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
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

        List<ImpactDefaultSeries> list = (List<ImpactDefaultSeries>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ImpactDefaultSeries impactDefaultSeries : list) {
                if ((seriesId != impactDefaultSeries.getSeriesId())) {
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

            query.append(_SQL_SELECT_IMPACTDEFAULTSERIES_WHERE);

            query.append(_FINDER_COLUMN_SERIESID_SERIESID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ImpactDefaultSeriesModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(seriesId);

                if (!pagination) {
                    list = (List<ImpactDefaultSeries>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ImpactDefaultSeries>(list);
                } else {
                    list = (List<ImpactDefaultSeries>) QueryUtil.list(q,
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
     * Returns the first impact default series in the ordered set where seriesId = &#63;.
     *
     * @param seriesId the series ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching impact default series
     * @throws com.ext.portlet.NoSuchImpactDefaultSeriesException if a matching impact default series could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeries findBySeriesId_First(long seriesId,
        OrderByComparator orderByComparator)
        throws NoSuchImpactDefaultSeriesException, SystemException {
        ImpactDefaultSeries impactDefaultSeries = fetchBySeriesId_First(seriesId,
                orderByComparator);

        if (impactDefaultSeries != null) {
            return impactDefaultSeries;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("seriesId=");
        msg.append(seriesId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchImpactDefaultSeriesException(msg.toString());
    }

    /**
     * Returns the first impact default series in the ordered set where seriesId = &#63;.
     *
     * @param seriesId the series ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching impact default series, or <code>null</code> if a matching impact default series could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeries fetchBySeriesId_First(long seriesId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ImpactDefaultSeries> list = findBySeriesId(seriesId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public ImpactDefaultSeries findBySeriesId_Last(long seriesId,
        OrderByComparator orderByComparator)
        throws NoSuchImpactDefaultSeriesException, SystemException {
        ImpactDefaultSeries impactDefaultSeries = fetchBySeriesId_Last(seriesId,
                orderByComparator);

        if (impactDefaultSeries != null) {
            return impactDefaultSeries;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("seriesId=");
        msg.append(seriesId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchImpactDefaultSeriesException(msg.toString());
    }

    /**
     * Returns the last impact default series in the ordered set where seriesId = &#63;.
     *
     * @param seriesId the series ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching impact default series, or <code>null</code> if a matching impact default series could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeries fetchBySeriesId_Last(long seriesId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countBySeriesId(seriesId);

        if (count == 0) {
            return null;
        }

        List<ImpactDefaultSeries> list = findBySeriesId(seriesId, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public ImpactDefaultSeries[] findBySeriesId_PrevAndNext(
        ImpactDefaultSeriesPK impactDefaultSeriesPK, long seriesId,
        OrderByComparator orderByComparator)
        throws NoSuchImpactDefaultSeriesException, SystemException {
        ImpactDefaultSeries impactDefaultSeries = findByPrimaryKey(impactDefaultSeriesPK);

        Session session = null;

        try {
            session = openSession();

            ImpactDefaultSeries[] array = new ImpactDefaultSeriesImpl[3];

            array[0] = getBySeriesId_PrevAndNext(session, impactDefaultSeries,
                    seriesId, orderByComparator, true);

            array[1] = impactDefaultSeries;

            array[2] = getBySeriesId_PrevAndNext(session, impactDefaultSeries,
                    seriesId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ImpactDefaultSeries getBySeriesId_PrevAndNext(Session session,
        ImpactDefaultSeries impactDefaultSeries, long seriesId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_IMPACTDEFAULTSERIES_WHERE);

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
            query.append(ImpactDefaultSeriesModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(seriesId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(impactDefaultSeries);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ImpactDefaultSeries> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the impact default serieses where seriesId = &#63; from the database.
     *
     * @param seriesId the series ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeBySeriesId(long seriesId) throws SystemException {
        for (ImpactDefaultSeries impactDefaultSeries : findBySeriesId(
                seriesId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(impactDefaultSeries);
        }
    }

    /**
     * Returns the number of impact default serieses where seriesId = &#63;.
     *
     * @param seriesId the series ID
     * @return the number of matching impact default serieses
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

            query.append(_SQL_COUNT_IMPACTDEFAULTSERIES_WHERE);

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
     * Returns all the impact default serieses where seriesId = &#63; and editable = &#63;.
     *
     * @param seriesId the series ID
     * @param editable the editable
     * @return the matching impact default serieses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImpactDefaultSeries> findBySeriesIdAndEditable(long seriesId,
        boolean editable) throws SystemException {
        return findBySeriesIdAndEditable(seriesId, editable, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
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
    @Override
    public List<ImpactDefaultSeries> findBySeriesIdAndEditable(long seriesId,
        boolean editable, int start, int end) throws SystemException {
        return findBySeriesIdAndEditable(seriesId, editable, start, end, null);
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
    @Override
    public List<ImpactDefaultSeries> findBySeriesIdAndEditable(long seriesId,
        boolean editable, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERIESIDANDEDITABLE;
            finderArgs = new Object[] { seriesId, editable };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SERIESIDANDEDITABLE;
            finderArgs = new Object[] {
                    seriesId, editable,
                    
                    start, end, orderByComparator
                };
        }

        List<ImpactDefaultSeries> list = (List<ImpactDefaultSeries>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ImpactDefaultSeries impactDefaultSeries : list) {
                if ((seriesId != impactDefaultSeries.getSeriesId()) ||
                        (editable != impactDefaultSeries.getEditable())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_IMPACTDEFAULTSERIES_WHERE);

            query.append(_FINDER_COLUMN_SERIESIDANDEDITABLE_SERIESID_2);

            query.append(_FINDER_COLUMN_SERIESIDANDEDITABLE_EDITABLE_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ImpactDefaultSeriesModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(seriesId);

                qPos.add(editable);

                if (!pagination) {
                    list = (List<ImpactDefaultSeries>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ImpactDefaultSeries>(list);
                } else {
                    list = (List<ImpactDefaultSeries>) QueryUtil.list(q,
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
     * Returns the first impact default series in the ordered set where seriesId = &#63; and editable = &#63;.
     *
     * @param seriesId the series ID
     * @param editable the editable
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching impact default series
     * @throws com.ext.portlet.NoSuchImpactDefaultSeriesException if a matching impact default series could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeries findBySeriesIdAndEditable_First(long seriesId,
        boolean editable, OrderByComparator orderByComparator)
        throws NoSuchImpactDefaultSeriesException, SystemException {
        ImpactDefaultSeries impactDefaultSeries = fetchBySeriesIdAndEditable_First(seriesId,
                editable, orderByComparator);

        if (impactDefaultSeries != null) {
            return impactDefaultSeries;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("seriesId=");
        msg.append(seriesId);

        msg.append(", editable=");
        msg.append(editable);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchImpactDefaultSeriesException(msg.toString());
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
    @Override
    public ImpactDefaultSeries fetchBySeriesIdAndEditable_First(long seriesId,
        boolean editable, OrderByComparator orderByComparator)
        throws SystemException {
        List<ImpactDefaultSeries> list = findBySeriesIdAndEditable(seriesId,
                editable, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public ImpactDefaultSeries findBySeriesIdAndEditable_Last(long seriesId,
        boolean editable, OrderByComparator orderByComparator)
        throws NoSuchImpactDefaultSeriesException, SystemException {
        ImpactDefaultSeries impactDefaultSeries = fetchBySeriesIdAndEditable_Last(seriesId,
                editable, orderByComparator);

        if (impactDefaultSeries != null) {
            return impactDefaultSeries;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("seriesId=");
        msg.append(seriesId);

        msg.append(", editable=");
        msg.append(editable);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchImpactDefaultSeriesException(msg.toString());
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
    @Override
    public ImpactDefaultSeries fetchBySeriesIdAndEditable_Last(long seriesId,
        boolean editable, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countBySeriesIdAndEditable(seriesId, editable);

        if (count == 0) {
            return null;
        }

        List<ImpactDefaultSeries> list = findBySeriesIdAndEditable(seriesId,
                editable, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public ImpactDefaultSeries[] findBySeriesIdAndEditable_PrevAndNext(
        ImpactDefaultSeriesPK impactDefaultSeriesPK, long seriesId,
        boolean editable, OrderByComparator orderByComparator)
        throws NoSuchImpactDefaultSeriesException, SystemException {
        ImpactDefaultSeries impactDefaultSeries = findByPrimaryKey(impactDefaultSeriesPK);

        Session session = null;

        try {
            session = openSession();

            ImpactDefaultSeries[] array = new ImpactDefaultSeriesImpl[3];

            array[0] = getBySeriesIdAndEditable_PrevAndNext(session,
                    impactDefaultSeries, seriesId, editable, orderByComparator,
                    true);

            array[1] = impactDefaultSeries;

            array[2] = getBySeriesIdAndEditable_PrevAndNext(session,
                    impactDefaultSeries, seriesId, editable, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ImpactDefaultSeries getBySeriesIdAndEditable_PrevAndNext(
        Session session, ImpactDefaultSeries impactDefaultSeries,
        long seriesId, boolean editable, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_IMPACTDEFAULTSERIES_WHERE);

        query.append(_FINDER_COLUMN_SERIESIDANDEDITABLE_SERIESID_2);

        query.append(_FINDER_COLUMN_SERIESIDANDEDITABLE_EDITABLE_2);

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
            query.append(ImpactDefaultSeriesModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(seriesId);

        qPos.add(editable);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(impactDefaultSeries);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ImpactDefaultSeries> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the impact default serieses where seriesId = &#63; and editable = &#63; from the database.
     *
     * @param seriesId the series ID
     * @param editable the editable
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeBySeriesIdAndEditable(long seriesId, boolean editable)
        throws SystemException {
        for (ImpactDefaultSeries impactDefaultSeries : findBySeriesIdAndEditable(
                seriesId, editable, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(impactDefaultSeries);
        }
    }

    /**
     * Returns the number of impact default serieses where seriesId = &#63; and editable = &#63;.
     *
     * @param seriesId the series ID
     * @param editable the editable
     * @return the number of matching impact default serieses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBySeriesIdAndEditable(long seriesId, boolean editable)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_SERIESIDANDEDITABLE;

        Object[] finderArgs = new Object[] { seriesId, editable };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_IMPACTDEFAULTSERIES_WHERE);

            query.append(_FINDER_COLUMN_SERIESIDANDEDITABLE_SERIESID_2);

            query.append(_FINDER_COLUMN_SERIESIDANDEDITABLE_EDITABLE_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(seriesId);

                qPos.add(editable);

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
     * Returns all the impact default serieses where focusAreaId = &#63;.
     *
     * @param focusAreaId the focus area ID
     * @return the matching impact default serieses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImpactDefaultSeries> findByFocusAreaId(long focusAreaId)
        throws SystemException {
        return findByFocusAreaId(focusAreaId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
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
    @Override
    public List<ImpactDefaultSeries> findByFocusAreaId(long focusAreaId,
        int start, int end) throws SystemException {
        return findByFocusAreaId(focusAreaId, start, end, null);
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
    @Override
    public List<ImpactDefaultSeries> findByFocusAreaId(long focusAreaId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FOCUSAREAID;
            finderArgs = new Object[] { focusAreaId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FOCUSAREAID;
            finderArgs = new Object[] { focusAreaId, start, end, orderByComparator };
        }

        List<ImpactDefaultSeries> list = (List<ImpactDefaultSeries>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ImpactDefaultSeries impactDefaultSeries : list) {
                if ((focusAreaId != impactDefaultSeries.getFocusAreaId())) {
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

            query.append(_SQL_SELECT_IMPACTDEFAULTSERIES_WHERE);

            query.append(_FINDER_COLUMN_FOCUSAREAID_FOCUSAREAID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ImpactDefaultSeriesModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(focusAreaId);

                if (!pagination) {
                    list = (List<ImpactDefaultSeries>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ImpactDefaultSeries>(list);
                } else {
                    list = (List<ImpactDefaultSeries>) QueryUtil.list(q,
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
     * Returns the first impact default series in the ordered set where focusAreaId = &#63;.
     *
     * @param focusAreaId the focus area ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching impact default series
     * @throws com.ext.portlet.NoSuchImpactDefaultSeriesException if a matching impact default series could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeries findByFocusAreaId_First(long focusAreaId,
        OrderByComparator orderByComparator)
        throws NoSuchImpactDefaultSeriesException, SystemException {
        ImpactDefaultSeries impactDefaultSeries = fetchByFocusAreaId_First(focusAreaId,
                orderByComparator);

        if (impactDefaultSeries != null) {
            return impactDefaultSeries;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("focusAreaId=");
        msg.append(focusAreaId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchImpactDefaultSeriesException(msg.toString());
    }

    /**
     * Returns the first impact default series in the ordered set where focusAreaId = &#63;.
     *
     * @param focusAreaId the focus area ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching impact default series, or <code>null</code> if a matching impact default series could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeries fetchByFocusAreaId_First(long focusAreaId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ImpactDefaultSeries> list = findByFocusAreaId(focusAreaId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public ImpactDefaultSeries findByFocusAreaId_Last(long focusAreaId,
        OrderByComparator orderByComparator)
        throws NoSuchImpactDefaultSeriesException, SystemException {
        ImpactDefaultSeries impactDefaultSeries = fetchByFocusAreaId_Last(focusAreaId,
                orderByComparator);

        if (impactDefaultSeries != null) {
            return impactDefaultSeries;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("focusAreaId=");
        msg.append(focusAreaId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchImpactDefaultSeriesException(msg.toString());
    }

    /**
     * Returns the last impact default series in the ordered set where focusAreaId = &#63;.
     *
     * @param focusAreaId the focus area ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching impact default series, or <code>null</code> if a matching impact default series could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeries fetchByFocusAreaId_Last(long focusAreaId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByFocusAreaId(focusAreaId);

        if (count == 0) {
            return null;
        }

        List<ImpactDefaultSeries> list = findByFocusAreaId(focusAreaId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public ImpactDefaultSeries[] findByFocusAreaId_PrevAndNext(
        ImpactDefaultSeriesPK impactDefaultSeriesPK, long focusAreaId,
        OrderByComparator orderByComparator)
        throws NoSuchImpactDefaultSeriesException, SystemException {
        ImpactDefaultSeries impactDefaultSeries = findByPrimaryKey(impactDefaultSeriesPK);

        Session session = null;

        try {
            session = openSession();

            ImpactDefaultSeries[] array = new ImpactDefaultSeriesImpl[3];

            array[0] = getByFocusAreaId_PrevAndNext(session,
                    impactDefaultSeries, focusAreaId, orderByComparator, true);

            array[1] = impactDefaultSeries;

            array[2] = getByFocusAreaId_PrevAndNext(session,
                    impactDefaultSeries, focusAreaId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ImpactDefaultSeries getByFocusAreaId_PrevAndNext(
        Session session, ImpactDefaultSeries impactDefaultSeries,
        long focusAreaId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_IMPACTDEFAULTSERIES_WHERE);

        query.append(_FINDER_COLUMN_FOCUSAREAID_FOCUSAREAID_2);

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
            query.append(ImpactDefaultSeriesModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(focusAreaId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(impactDefaultSeries);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ImpactDefaultSeries> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the impact default serieses where focusAreaId = &#63; from the database.
     *
     * @param focusAreaId the focus area ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByFocusAreaId(long focusAreaId) throws SystemException {
        for (ImpactDefaultSeries impactDefaultSeries : findByFocusAreaId(
                focusAreaId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(impactDefaultSeries);
        }
    }

    /**
     * Returns the number of impact default serieses where focusAreaId = &#63;.
     *
     * @param focusAreaId the focus area ID
     * @return the number of matching impact default serieses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByFocusAreaId(long focusAreaId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_FOCUSAREAID;

        Object[] finderArgs = new Object[] { focusAreaId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_IMPACTDEFAULTSERIES_WHERE);

            query.append(_FINDER_COLUMN_FOCUSAREAID_FOCUSAREAID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(focusAreaId);

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
     * Returns the impact default series where focusAreaId = &#63; and name = &#63; or throws a {@link com.ext.portlet.NoSuchImpactDefaultSeriesException} if it could not be found.
     *
     * @param focusAreaId the focus area ID
     * @param name the name
     * @return the matching impact default series
     * @throws com.ext.portlet.NoSuchImpactDefaultSeriesException if a matching impact default series could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeries findByFocusAreaIdAndName(long focusAreaId,
        String name) throws NoSuchImpactDefaultSeriesException, SystemException {
        ImpactDefaultSeries impactDefaultSeries = fetchByFocusAreaIdAndName(focusAreaId,
                name);

        if (impactDefaultSeries == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("focusAreaId=");
            msg.append(focusAreaId);

            msg.append(", name=");
            msg.append(name);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchImpactDefaultSeriesException(msg.toString());
        }

        return impactDefaultSeries;
    }

    /**
     * Returns the impact default series where focusAreaId = &#63; and name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param focusAreaId the focus area ID
     * @param name the name
     * @return the matching impact default series, or <code>null</code> if a matching impact default series could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeries fetchByFocusAreaIdAndName(long focusAreaId,
        String name) throws SystemException {
        return fetchByFocusAreaIdAndName(focusAreaId, name, true);
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
    @Override
    public ImpactDefaultSeries fetchByFocusAreaIdAndName(long focusAreaId,
        String name, boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { focusAreaId, name };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_FOCUSAREAIDANDNAME,
                    finderArgs, this);
        }

        if (result instanceof ImpactDefaultSeries) {
            ImpactDefaultSeries impactDefaultSeries = (ImpactDefaultSeries) result;

            if ((focusAreaId != impactDefaultSeries.getFocusAreaId()) ||
                    !Validator.equals(name, impactDefaultSeries.getName())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_SELECT_IMPACTDEFAULTSERIES_WHERE);

            query.append(_FINDER_COLUMN_FOCUSAREAIDANDNAME_FOCUSAREAID_2);

            boolean bindName = false;

            if (name == null) {
                query.append(_FINDER_COLUMN_FOCUSAREAIDANDNAME_NAME_1);
            } else if (name.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FOCUSAREAIDANDNAME_NAME_3);
            } else {
                bindName = true;

                query.append(_FINDER_COLUMN_FOCUSAREAIDANDNAME_NAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(focusAreaId);

                if (bindName) {
                    qPos.add(name);
                }

                List<ImpactDefaultSeries> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FOCUSAREAIDANDNAME,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "ImpactDefaultSeriesPersistenceImpl.fetchByFocusAreaIdAndName(long, String, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    ImpactDefaultSeries impactDefaultSeries = list.get(0);

                    result = impactDefaultSeries;

                    cacheResult(impactDefaultSeries);

                    if ((impactDefaultSeries.getFocusAreaId() != focusAreaId) ||
                            (impactDefaultSeries.getName() == null) ||
                            !impactDefaultSeries.getName().equals(name)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FOCUSAREAIDANDNAME,
                            finderArgs, impactDefaultSeries);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FOCUSAREAIDANDNAME,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (ImpactDefaultSeries) result;
        }
    }

    /**
     * Removes the impact default series where focusAreaId = &#63; and name = &#63; from the database.
     *
     * @param focusAreaId the focus area ID
     * @param name the name
     * @return the impact default series that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeries removeByFocusAreaIdAndName(long focusAreaId,
        String name) throws NoSuchImpactDefaultSeriesException, SystemException {
        ImpactDefaultSeries impactDefaultSeries = findByFocusAreaIdAndName(focusAreaId,
                name);

        return remove(impactDefaultSeries);
    }

    /**
     * Returns the number of impact default serieses where focusAreaId = &#63; and name = &#63;.
     *
     * @param focusAreaId the focus area ID
     * @param name the name
     * @return the number of matching impact default serieses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByFocusAreaIdAndName(long focusAreaId, String name)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_FOCUSAREAIDANDNAME;

        Object[] finderArgs = new Object[] { focusAreaId, name };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_IMPACTDEFAULTSERIES_WHERE);

            query.append(_FINDER_COLUMN_FOCUSAREAIDANDNAME_FOCUSAREAID_2);

            boolean bindName = false;

            if (name == null) {
                query.append(_FINDER_COLUMN_FOCUSAREAIDANDNAME_NAME_1);
            } else if (name.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FOCUSAREAIDANDNAME_NAME_3);
            } else {
                bindName = true;

                query.append(_FINDER_COLUMN_FOCUSAREAIDANDNAME_NAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(focusAreaId);

                if (bindName) {
                    qPos.add(name);
                }

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
     * Caches the impact default series in the entity cache if it is enabled.
     *
     * @param impactDefaultSeries the impact default series
     */
    @Override
    public void cacheResult(ImpactDefaultSeries impactDefaultSeries) {
        EntityCacheUtil.putResult(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesImpl.class, impactDefaultSeries.getPrimaryKey(),
            impactDefaultSeries);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FOCUSAREAIDANDNAME,
            new Object[] {
                impactDefaultSeries.getFocusAreaId(),
                impactDefaultSeries.getName()
            }, impactDefaultSeries);

        impactDefaultSeries.resetOriginalValues();
    }

    /**
     * Caches the impact default serieses in the entity cache if it is enabled.
     *
     * @param impactDefaultSerieses the impact default serieses
     */
    @Override
    public void cacheResult(List<ImpactDefaultSeries> impactDefaultSerieses) {
        for (ImpactDefaultSeries impactDefaultSeries : impactDefaultSerieses) {
            if (EntityCacheUtil.getResult(
                        ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
                        ImpactDefaultSeriesImpl.class,
                        impactDefaultSeries.getPrimaryKey()) == null) {
                cacheResult(impactDefaultSeries);
            } else {
                impactDefaultSeries.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all impact default serieses.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ImpactDefaultSeriesImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ImpactDefaultSeriesImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the impact default series.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ImpactDefaultSeries impactDefaultSeries) {
        EntityCacheUtil.removeResult(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesImpl.class, impactDefaultSeries.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(impactDefaultSeries);
    }

    @Override
    public void clearCache(List<ImpactDefaultSeries> impactDefaultSerieses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ImpactDefaultSeries impactDefaultSeries : impactDefaultSerieses) {
            EntityCacheUtil.removeResult(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
                ImpactDefaultSeriesImpl.class,
                impactDefaultSeries.getPrimaryKey());

            clearUniqueFindersCache(impactDefaultSeries);
        }
    }

    protected void cacheUniqueFindersCache(
        ImpactDefaultSeries impactDefaultSeries) {
        if (impactDefaultSeries.isNew()) {
            Object[] args = new Object[] {
                    impactDefaultSeries.getFocusAreaId(),
                    impactDefaultSeries.getName()
                };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FOCUSAREAIDANDNAME,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FOCUSAREAIDANDNAME,
                args, impactDefaultSeries);
        } else {
            ImpactDefaultSeriesModelImpl impactDefaultSeriesModelImpl = (ImpactDefaultSeriesModelImpl) impactDefaultSeries;

            if ((impactDefaultSeriesModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_FOCUSAREAIDANDNAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        impactDefaultSeries.getFocusAreaId(),
                        impactDefaultSeries.getName()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FOCUSAREAIDANDNAME,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FOCUSAREAIDANDNAME,
                    args, impactDefaultSeries);
            }
        }
    }

    protected void clearUniqueFindersCache(
        ImpactDefaultSeries impactDefaultSeries) {
        ImpactDefaultSeriesModelImpl impactDefaultSeriesModelImpl = (ImpactDefaultSeriesModelImpl) impactDefaultSeries;

        Object[] args = new Object[] {
                impactDefaultSeries.getFocusAreaId(),
                impactDefaultSeries.getName()
            };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FOCUSAREAIDANDNAME,
            args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FOCUSAREAIDANDNAME,
            args);

        if ((impactDefaultSeriesModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_FOCUSAREAIDANDNAME.getColumnBitmask()) != 0) {
            args = new Object[] {
                    impactDefaultSeriesModelImpl.getOriginalFocusAreaId(),
                    impactDefaultSeriesModelImpl.getOriginalName()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FOCUSAREAIDANDNAME,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FOCUSAREAIDANDNAME,
                args);
        }
    }

    /**
     * Creates a new impact default series with the primary key. Does not add the impact default series to the database.
     *
     * @param impactDefaultSeriesPK the primary key for the new impact default series
     * @return the new impact default series
     */
    @Override
    public ImpactDefaultSeries create(
        ImpactDefaultSeriesPK impactDefaultSeriesPK) {
        ImpactDefaultSeries impactDefaultSeries = new ImpactDefaultSeriesImpl();

        impactDefaultSeries.setNew(true);
        impactDefaultSeries.setPrimaryKey(impactDefaultSeriesPK);

        return impactDefaultSeries;
    }

    /**
     * Removes the impact default series with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param impactDefaultSeriesPK the primary key of the impact default series
     * @return the impact default series that was removed
     * @throws com.ext.portlet.NoSuchImpactDefaultSeriesException if a impact default series with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeries remove(
        ImpactDefaultSeriesPK impactDefaultSeriesPK)
        throws NoSuchImpactDefaultSeriesException, SystemException {
        return remove((Serializable) impactDefaultSeriesPK);
    }

    /**
     * Removes the impact default series with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the impact default series
     * @return the impact default series that was removed
     * @throws com.ext.portlet.NoSuchImpactDefaultSeriesException if a impact default series with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeries remove(Serializable primaryKey)
        throws NoSuchImpactDefaultSeriesException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ImpactDefaultSeries impactDefaultSeries = (ImpactDefaultSeries) session.get(ImpactDefaultSeriesImpl.class,
                    primaryKey);

            if (impactDefaultSeries == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchImpactDefaultSeriesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(impactDefaultSeries);
        } catch (NoSuchImpactDefaultSeriesException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ImpactDefaultSeries removeImpl(
        ImpactDefaultSeries impactDefaultSeries) throws SystemException {
        impactDefaultSeries = toUnwrappedModel(impactDefaultSeries);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(impactDefaultSeries)) {
                impactDefaultSeries = (ImpactDefaultSeries) session.get(ImpactDefaultSeriesImpl.class,
                        impactDefaultSeries.getPrimaryKeyObj());
            }

            if (impactDefaultSeries != null) {
                session.delete(impactDefaultSeries);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (impactDefaultSeries != null) {
            clearCache(impactDefaultSeries);
        }

        return impactDefaultSeries;
    }

    @Override
    public ImpactDefaultSeries updateImpl(
        com.ext.portlet.model.ImpactDefaultSeries impactDefaultSeries)
        throws SystemException {
        impactDefaultSeries = toUnwrappedModel(impactDefaultSeries);

        boolean isNew = impactDefaultSeries.isNew();

        ImpactDefaultSeriesModelImpl impactDefaultSeriesModelImpl = (ImpactDefaultSeriesModelImpl) impactDefaultSeries;

        Session session = null;

        try {
            session = openSession();

            if (impactDefaultSeries.isNew()) {
                session.save(impactDefaultSeries);

                impactDefaultSeries.setNew(false);
            } else {
                session.merge(impactDefaultSeries);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ImpactDefaultSeriesModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((impactDefaultSeriesModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERIESID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        impactDefaultSeriesModelImpl.getOriginalSeriesId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SERIESID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERIESID,
                    args);

                args = new Object[] { impactDefaultSeriesModelImpl.getSeriesId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SERIESID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERIESID,
                    args);
            }

            if ((impactDefaultSeriesModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERIESIDANDEDITABLE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        impactDefaultSeriesModelImpl.getOriginalSeriesId(),
                        impactDefaultSeriesModelImpl.getOriginalEditable()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SERIESIDANDEDITABLE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERIESIDANDEDITABLE,
                    args);

                args = new Object[] {
                        impactDefaultSeriesModelImpl.getSeriesId(),
                        impactDefaultSeriesModelImpl.getEditable()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SERIESIDANDEDITABLE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SERIESIDANDEDITABLE,
                    args);
            }

            if ((impactDefaultSeriesModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FOCUSAREAID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        impactDefaultSeriesModelImpl.getOriginalFocusAreaId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FOCUSAREAID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FOCUSAREAID,
                    args);

                args = new Object[] {
                        impactDefaultSeriesModelImpl.getFocusAreaId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FOCUSAREAID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FOCUSAREAID,
                    args);
            }
        }

        EntityCacheUtil.putResult(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesImpl.class, impactDefaultSeries.getPrimaryKey(),
            impactDefaultSeries);

        clearUniqueFindersCache(impactDefaultSeries);
        cacheUniqueFindersCache(impactDefaultSeries);

        return impactDefaultSeries;
    }

    protected ImpactDefaultSeries toUnwrappedModel(
        ImpactDefaultSeries impactDefaultSeries) {
        if (impactDefaultSeries instanceof ImpactDefaultSeriesImpl) {
            return impactDefaultSeries;
        }

        ImpactDefaultSeriesImpl impactDefaultSeriesImpl = new ImpactDefaultSeriesImpl();

        impactDefaultSeriesImpl.setNew(impactDefaultSeries.isNew());
        impactDefaultSeriesImpl.setPrimaryKey(impactDefaultSeries.getPrimaryKey());

        impactDefaultSeriesImpl.setSeriesId(impactDefaultSeries.getSeriesId());
        impactDefaultSeriesImpl.setName(impactDefaultSeries.getName());
        impactDefaultSeriesImpl.setDescription(impactDefaultSeries.getDescription());
        impactDefaultSeriesImpl.setFocusAreaId(impactDefaultSeries.getFocusAreaId());
        impactDefaultSeriesImpl.setVisible(impactDefaultSeries.isVisible());
        impactDefaultSeriesImpl.setEditable(impactDefaultSeries.isEditable());

        return impactDefaultSeriesImpl;
    }

    /**
     * Returns the impact default series with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the impact default series
     * @return the impact default series
     * @throws com.ext.portlet.NoSuchImpactDefaultSeriesException if a impact default series with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeries findByPrimaryKey(Serializable primaryKey)
        throws NoSuchImpactDefaultSeriesException, SystemException {
        ImpactDefaultSeries impactDefaultSeries = fetchByPrimaryKey(primaryKey);

        if (impactDefaultSeries == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchImpactDefaultSeriesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return impactDefaultSeries;
    }

    /**
     * Returns the impact default series with the primary key or throws a {@link com.ext.portlet.NoSuchImpactDefaultSeriesException} if it could not be found.
     *
     * @param impactDefaultSeriesPK the primary key of the impact default series
     * @return the impact default series
     * @throws com.ext.portlet.NoSuchImpactDefaultSeriesException if a impact default series with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeries findByPrimaryKey(
        ImpactDefaultSeriesPK impactDefaultSeriesPK)
        throws NoSuchImpactDefaultSeriesException, SystemException {
        return findByPrimaryKey((Serializable) impactDefaultSeriesPK);
    }

    /**
     * Returns the impact default series with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the impact default series
     * @return the impact default series, or <code>null</code> if a impact default series with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeries fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ImpactDefaultSeries impactDefaultSeries = (ImpactDefaultSeries) EntityCacheUtil.getResult(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
                ImpactDefaultSeriesImpl.class, primaryKey);

        if (impactDefaultSeries == _nullImpactDefaultSeries) {
            return null;
        }

        if (impactDefaultSeries == null) {
            Session session = null;

            try {
                session = openSession();

                impactDefaultSeries = (ImpactDefaultSeries) session.get(ImpactDefaultSeriesImpl.class,
                        primaryKey);

                if (impactDefaultSeries != null) {
                    cacheResult(impactDefaultSeries);
                } else {
                    EntityCacheUtil.putResult(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
                        ImpactDefaultSeriesImpl.class, primaryKey,
                        _nullImpactDefaultSeries);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
                    ImpactDefaultSeriesImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return impactDefaultSeries;
    }

    /**
     * Returns the impact default series with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param impactDefaultSeriesPK the primary key of the impact default series
     * @return the impact default series, or <code>null</code> if a impact default series with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactDefaultSeries fetchByPrimaryKey(
        ImpactDefaultSeriesPK impactDefaultSeriesPK) throws SystemException {
        return fetchByPrimaryKey((Serializable) impactDefaultSeriesPK);
    }

    /**
     * Returns all the impact default serieses.
     *
     * @return the impact default serieses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImpactDefaultSeries> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
    @Override
    public List<ImpactDefaultSeries> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
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
    @Override
    public List<ImpactDefaultSeries> findAll(int start, int end,
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

        List<ImpactDefaultSeries> list = (List<ImpactDefaultSeries>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IMPACTDEFAULTSERIES);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IMPACTDEFAULTSERIES;

                if (pagination) {
                    sql = sql.concat(ImpactDefaultSeriesModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ImpactDefaultSeries>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ImpactDefaultSeries>(list);
                } else {
                    list = (List<ImpactDefaultSeries>) QueryUtil.list(q,
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
     * Removes all the impact default serieses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ImpactDefaultSeries impactDefaultSeries : findAll()) {
            remove(impactDefaultSeries);
        }
    }

    /**
     * Returns the number of impact default serieses.
     *
     * @return the number of impact default serieses
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

                Query q = session.createQuery(_SQL_COUNT_IMPACTDEFAULTSERIES);

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
     * Initializes the impact default series persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ImpactDefaultSeries")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ImpactDefaultSeries>> listenersList = new ArrayList<ModelListener<ImpactDefaultSeries>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ImpactDefaultSeries>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ImpactDefaultSeriesImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
