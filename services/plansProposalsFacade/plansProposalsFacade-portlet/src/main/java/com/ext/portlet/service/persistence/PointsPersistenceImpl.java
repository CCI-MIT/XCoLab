package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPointsException;
import com.ext.portlet.model.Points;
import com.ext.portlet.model.impl.PointsImpl;
import com.ext.portlet.model.impl.PointsModelImpl;
import com.ext.portlet.service.persistence.PointsPersistence;

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
import com.liferay.portal.kernel.util.SetUtil;
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
import java.util.Set;

/**
 * The persistence implementation for the points service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PointsPersistence
 * @see PointsUtil
 * @generated
 */
public class PointsPersistenceImpl extends BasePersistenceImpl<Points>
    implements PointsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PointsUtil} to access the points persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PointsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PointsModelImpl.ENTITY_CACHE_ENABLED,
            PointsModelImpl.FINDER_CACHE_ENABLED, PointsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PointsModelImpl.ENTITY_CACHE_ENABLED,
            PointsModelImpl.FINDER_CACHE_ENABLED, PointsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PointsModelImpl.ENTITY_CACHE_ENABLED,
            PointsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALID =
        new FinderPath(PointsModelImpl.ENTITY_CACHE_ENABLED,
            PointsModelImpl.FINDER_CACHE_ENABLED, PointsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProposalId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID =
        new FinderPath(PointsModelImpl.ENTITY_CACHE_ENABLED,
            PointsModelImpl.FINDER_CACHE_ENABLED, PointsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProposalId",
            new String[] { Long.class.getName() },
            PointsModelImpl.PROPOSALID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PROPOSALID = new FinderPath(PointsModelImpl.ENTITY_CACHE_ENABLED,
            PointsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProposalId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_PROPOSALID_PROPOSALID_2 = "points.proposalId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(PointsModelImpl.ENTITY_CACHE_ENABLED,
            PointsModelImpl.FINDER_CACHE_ENABLED, PointsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
        new FinderPath(PointsModelImpl.ENTITY_CACHE_ENABLED,
            PointsModelImpl.FINDER_CACHE_ENABLED, PointsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
            new String[] { Long.class.getName() },
            PointsModelImpl.USERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(PointsModelImpl.ENTITY_CACHE_ENABLED,
            PointsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_USERID_USERID_2 = "points.userId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_POINTSSOURCEID =
        new FinderPath(PointsModelImpl.ENTITY_CACHE_ENABLED,
            PointsModelImpl.FINDER_CACHE_ENABLED, PointsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPointsSourceId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POINTSSOURCEID =
        new FinderPath(PointsModelImpl.ENTITY_CACHE_ENABLED,
            PointsModelImpl.FINDER_CACHE_ENABLED, PointsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPointsSourceId",
            new String[] { Long.class.getName() },
            PointsModelImpl.POINTSSOURCEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_POINTSSOURCEID = new FinderPath(PointsModelImpl.ENTITY_CACHE_ENABLED,
            PointsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPointsSourceId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_POINTSSOURCEID_POINTSSOURCEID_2 = "points.pointsSourceId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ORIGINATINGCONTESTPK =
        new FinderPath(PointsModelImpl.ENTITY_CACHE_ENABLED,
            PointsModelImpl.FINDER_CACHE_ENABLED, PointsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByOriginatingContestPK",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORIGINATINGCONTESTPK =
        new FinderPath(PointsModelImpl.ENTITY_CACHE_ENABLED,
            PointsModelImpl.FINDER_CACHE_ENABLED, PointsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByOriginatingContestPK",
            new String[] { Long.class.getName() },
            PointsModelImpl.ORIGINATINGCONTESTPK_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ORIGINATINGCONTESTPK = new FinderPath(PointsModelImpl.ENTITY_CACHE_ENABLED,
            PointsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByOriginatingContestPK", new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_ORIGINATINGCONTESTPK_ORIGINATINGCONTESTPK_2 =
        "points.originatingContestPK = ?";
    private static final String _SQL_SELECT_POINTS = "SELECT points FROM Points points";
    private static final String _SQL_SELECT_POINTS_WHERE = "SELECT points FROM Points points WHERE ";
    private static final String _SQL_COUNT_POINTS = "SELECT COUNT(points) FROM Points points";
    private static final String _SQL_COUNT_POINTS_WHERE = "SELECT COUNT(points) FROM Points points WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "points.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Points exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Points exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PointsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static Points _nullPoints = new PointsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<Points> toCacheModel() {
                return _nullPointsCacheModel;
            }
        };

    private static CacheModel<Points> _nullPointsCacheModel = new CacheModel<Points>() {
            @Override
            public Points toEntityModel() {
                return _nullPoints;
            }
        };

    public PointsPersistenceImpl() {
        setModelClass(Points.class);
    }

    /**
     * Returns all the pointses where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @return the matching pointses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Points> findByProposalId(long proposalId)
        throws SystemException {
        return findByProposalId(proposalId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
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
    @Override
    public List<Points> findByProposalId(long proposalId, int start, int end)
        throws SystemException {
        return findByProposalId(proposalId, start, end, null);
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
    @Override
    public List<Points> findByProposalId(long proposalId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID;
            finderArgs = new Object[] { proposalId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALID;
            finderArgs = new Object[] { proposalId, start, end, orderByComparator };
        }

        List<Points> list = (List<Points>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Points points : list) {
                if ((proposalId != points.getProposalId())) {
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

            query.append(_SQL_SELECT_POINTS_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(PointsModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                if (!pagination) {
                    list = (List<Points>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Points>(list);
                } else {
                    list = (List<Points>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Returns the first points in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching points
     * @throws com.ext.portlet.NoSuchPointsException if a matching points could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Points findByProposalId_First(long proposalId,
        OrderByComparator orderByComparator)
        throws NoSuchPointsException, SystemException {
        Points points = fetchByProposalId_First(proposalId, orderByComparator);

        if (points != null) {
            return points;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("proposalId=");
        msg.append(proposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointsException(msg.toString());
    }

    /**
     * Returns the first points in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching points, or <code>null</code> if a matching points could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Points fetchByProposalId_First(long proposalId,
        OrderByComparator orderByComparator) throws SystemException {
        List<Points> list = findByProposalId(proposalId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public Points findByProposalId_Last(long proposalId,
        OrderByComparator orderByComparator)
        throws NoSuchPointsException, SystemException {
        Points points = fetchByProposalId_Last(proposalId, orderByComparator);

        if (points != null) {
            return points;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("proposalId=");
        msg.append(proposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointsException(msg.toString());
    }

    /**
     * Returns the last points in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching points, or <code>null</code> if a matching points could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Points fetchByProposalId_Last(long proposalId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByProposalId(proposalId);

        if (count == 0) {
            return null;
        }

        List<Points> list = findByProposalId(proposalId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public Points[] findByProposalId_PrevAndNext(long id, long proposalId,
        OrderByComparator orderByComparator)
        throws NoSuchPointsException, SystemException {
        Points points = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            Points[] array = new PointsImpl[3];

            array[0] = getByProposalId_PrevAndNext(session, points, proposalId,
                    orderByComparator, true);

            array[1] = points;

            array[2] = getByProposalId_PrevAndNext(session, points, proposalId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Points getByProposalId_PrevAndNext(Session session,
        Points points, long proposalId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_POINTS_WHERE);

        query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALID_2);

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
            query.append(PointsModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(proposalId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(points);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Points> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the pointses where proposalId = &#63; from the database.
     *
     * @param proposalId the proposal ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByProposalId(long proposalId) throws SystemException {
        for (Points points : findByProposalId(proposalId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(points);
        }
    }

    /**
     * Returns the number of pointses where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @return the number of matching pointses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByProposalId(long proposalId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PROPOSALID;

        Object[] finderArgs = new Object[] { proposalId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_POINTS_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

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
     * Returns all the pointses where userId = &#63;.
     *
     * @param userId the user ID
     * @return the matching pointses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Points> findByUserId(long userId) throws SystemException {
        return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
    @Override
    public List<Points> findByUserId(long userId, int start, int end)
        throws SystemException {
        return findByUserId(userId, start, end, null);
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
    @Override
    public List<Points> findByUserId(long userId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
            finderArgs = new Object[] { userId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
            finderArgs = new Object[] { userId, start, end, orderByComparator };
        }

        List<Points> list = (List<Points>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Points points : list) {
                if ((userId != points.getUserId())) {
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

            query.append(_SQL_SELECT_POINTS_WHERE);

            query.append(_FINDER_COLUMN_USERID_USERID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(PointsModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                if (!pagination) {
                    list = (List<Points>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Points>(list);
                } else {
                    list = (List<Points>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Returns the first points in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching points
     * @throws com.ext.portlet.NoSuchPointsException if a matching points could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Points findByUserId_First(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchPointsException, SystemException {
        Points points = fetchByUserId_First(userId, orderByComparator);

        if (points != null) {
            return points;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("userId=");
        msg.append(userId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointsException(msg.toString());
    }

    /**
     * Returns the first points in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching points, or <code>null</code> if a matching points could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Points fetchByUserId_First(long userId,
        OrderByComparator orderByComparator) throws SystemException {
        List<Points> list = findByUserId(userId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public Points findByUserId_Last(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchPointsException, SystemException {
        Points points = fetchByUserId_Last(userId, orderByComparator);

        if (points != null) {
            return points;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("userId=");
        msg.append(userId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointsException(msg.toString());
    }

    /**
     * Returns the last points in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching points, or <code>null</code> if a matching points could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Points fetchByUserId_Last(long userId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByUserId(userId);

        if (count == 0) {
            return null;
        }

        List<Points> list = findByUserId(userId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public Points[] findByUserId_PrevAndNext(long id, long userId,
        OrderByComparator orderByComparator)
        throws NoSuchPointsException, SystemException {
        Points points = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            Points[] array = new PointsImpl[3];

            array[0] = getByUserId_PrevAndNext(session, points, userId,
                    orderByComparator, true);

            array[1] = points;

            array[2] = getByUserId_PrevAndNext(session, points, userId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Points getByUserId_PrevAndNext(Session session, Points points,
        long userId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_POINTS_WHERE);

        query.append(_FINDER_COLUMN_USERID_USERID_2);

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
            query.append(PointsModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(userId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(points);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Points> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the pointses where userId = &#63; from the database.
     *
     * @param userId the user ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByUserId(long userId) throws SystemException {
        for (Points points : findByUserId(userId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(points);
        }
    }

    /**
     * Returns the number of pointses where userId = &#63;.
     *
     * @param userId the user ID
     * @return the number of matching pointses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByUserId(long userId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

        Object[] finderArgs = new Object[] { userId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_POINTS_WHERE);

            query.append(_FINDER_COLUMN_USERID_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

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
     * Returns all the pointses where pointsSourceId = &#63;.
     *
     * @param pointsSourceId the points source ID
     * @return the matching pointses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Points> findByPointsSourceId(long pointsSourceId)
        throws SystemException {
        return findByPointsSourceId(pointsSourceId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
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
    @Override
    public List<Points> findByPointsSourceId(long pointsSourceId, int start,
        int end) throws SystemException {
        return findByPointsSourceId(pointsSourceId, start, end, null);
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
    @Override
    public List<Points> findByPointsSourceId(long pointsSourceId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POINTSSOURCEID;
            finderArgs = new Object[] { pointsSourceId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_POINTSSOURCEID;
            finderArgs = new Object[] {
                    pointsSourceId,
                    
                    start, end, orderByComparator
                };
        }

        List<Points> list = (List<Points>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Points points : list) {
                if ((pointsSourceId != points.getPointsSourceId())) {
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

            query.append(_SQL_SELECT_POINTS_WHERE);

            query.append(_FINDER_COLUMN_POINTSSOURCEID_POINTSSOURCEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(PointsModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pointsSourceId);

                if (!pagination) {
                    list = (List<Points>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Points>(list);
                } else {
                    list = (List<Points>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Returns the first points in the ordered set where pointsSourceId = &#63;.
     *
     * @param pointsSourceId the points source ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching points
     * @throws com.ext.portlet.NoSuchPointsException if a matching points could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Points findByPointsSourceId_First(long pointsSourceId,
        OrderByComparator orderByComparator)
        throws NoSuchPointsException, SystemException {
        Points points = fetchByPointsSourceId_First(pointsSourceId,
                orderByComparator);

        if (points != null) {
            return points;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("pointsSourceId=");
        msg.append(pointsSourceId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointsException(msg.toString());
    }

    /**
     * Returns the first points in the ordered set where pointsSourceId = &#63;.
     *
     * @param pointsSourceId the points source ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching points, or <code>null</code> if a matching points could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Points fetchByPointsSourceId_First(long pointsSourceId,
        OrderByComparator orderByComparator) throws SystemException {
        List<Points> list = findByPointsSourceId(pointsSourceId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public Points findByPointsSourceId_Last(long pointsSourceId,
        OrderByComparator orderByComparator)
        throws NoSuchPointsException, SystemException {
        Points points = fetchByPointsSourceId_Last(pointsSourceId,
                orderByComparator);

        if (points != null) {
            return points;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("pointsSourceId=");
        msg.append(pointsSourceId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointsException(msg.toString());
    }

    /**
     * Returns the last points in the ordered set where pointsSourceId = &#63;.
     *
     * @param pointsSourceId the points source ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching points, or <code>null</code> if a matching points could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Points fetchByPointsSourceId_Last(long pointsSourceId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByPointsSourceId(pointsSourceId);

        if (count == 0) {
            return null;
        }

        List<Points> list = findByPointsSourceId(pointsSourceId, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public Points[] findByPointsSourceId_PrevAndNext(long id,
        long pointsSourceId, OrderByComparator orderByComparator)
        throws NoSuchPointsException, SystemException {
        Points points = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            Points[] array = new PointsImpl[3];

            array[0] = getByPointsSourceId_PrevAndNext(session, points,
                    pointsSourceId, orderByComparator, true);

            array[1] = points;

            array[2] = getByPointsSourceId_PrevAndNext(session, points,
                    pointsSourceId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Points getByPointsSourceId_PrevAndNext(Session session,
        Points points, long pointsSourceId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_POINTS_WHERE);

        query.append(_FINDER_COLUMN_POINTSSOURCEID_POINTSSOURCEID_2);

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
            query.append(PointsModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(pointsSourceId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(points);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Points> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the pointses where pointsSourceId = &#63; from the database.
     *
     * @param pointsSourceId the points source ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByPointsSourceId(long pointsSourceId)
        throws SystemException {
        for (Points points : findByPointsSourceId(pointsSourceId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(points);
        }
    }

    /**
     * Returns the number of pointses where pointsSourceId = &#63;.
     *
     * @param pointsSourceId the points source ID
     * @return the number of matching pointses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByPointsSourceId(long pointsSourceId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_POINTSSOURCEID;

        Object[] finderArgs = new Object[] { pointsSourceId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_POINTS_WHERE);

            query.append(_FINDER_COLUMN_POINTSSOURCEID_POINTSSOURCEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pointsSourceId);

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
     * Returns all the pointses where originatingContestPK = &#63;.
     *
     * @param originatingContestPK the originating contest p k
     * @return the matching pointses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Points> findByOriginatingContestPK(long originatingContestPK)
        throws SystemException {
        return findByOriginatingContestPK(originatingContestPK,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
    @Override
    public List<Points> findByOriginatingContestPK(long originatingContestPK,
        int start, int end) throws SystemException {
        return findByOriginatingContestPK(originatingContestPK, start, end, null);
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
    @Override
    public List<Points> findByOriginatingContestPK(long originatingContestPK,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORIGINATINGCONTESTPK;
            finderArgs = new Object[] { originatingContestPK };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ORIGINATINGCONTESTPK;
            finderArgs = new Object[] {
                    originatingContestPK,
                    
                    start, end, orderByComparator
                };
        }

        List<Points> list = (List<Points>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Points points : list) {
                if ((originatingContestPK != points.getOriginatingContestPK())) {
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

            query.append(_SQL_SELECT_POINTS_WHERE);

            query.append(_FINDER_COLUMN_ORIGINATINGCONTESTPK_ORIGINATINGCONTESTPK_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(PointsModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(originatingContestPK);

                if (!pagination) {
                    list = (List<Points>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Points>(list);
                } else {
                    list = (List<Points>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Returns the first points in the ordered set where originatingContestPK = &#63;.
     *
     * @param originatingContestPK the originating contest p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching points
     * @throws com.ext.portlet.NoSuchPointsException if a matching points could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Points findByOriginatingContestPK_First(long originatingContestPK,
        OrderByComparator orderByComparator)
        throws NoSuchPointsException, SystemException {
        Points points = fetchByOriginatingContestPK_First(originatingContestPK,
                orderByComparator);

        if (points != null) {
            return points;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("originatingContestPK=");
        msg.append(originatingContestPK);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointsException(msg.toString());
    }

    /**
     * Returns the first points in the ordered set where originatingContestPK = &#63;.
     *
     * @param originatingContestPK the originating contest p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching points, or <code>null</code> if a matching points could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Points fetchByOriginatingContestPK_First(long originatingContestPK,
        OrderByComparator orderByComparator) throws SystemException {
        List<Points> list = findByOriginatingContestPK(originatingContestPK, 0,
                1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public Points findByOriginatingContestPK_Last(long originatingContestPK,
        OrderByComparator orderByComparator)
        throws NoSuchPointsException, SystemException {
        Points points = fetchByOriginatingContestPK_Last(originatingContestPK,
                orderByComparator);

        if (points != null) {
            return points;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("originatingContestPK=");
        msg.append(originatingContestPK);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPointsException(msg.toString());
    }

    /**
     * Returns the last points in the ordered set where originatingContestPK = &#63;.
     *
     * @param originatingContestPK the originating contest p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching points, or <code>null</code> if a matching points could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Points fetchByOriginatingContestPK_Last(long originatingContestPK,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByOriginatingContestPK(originatingContestPK);

        if (count == 0) {
            return null;
        }

        List<Points> list = findByOriginatingContestPK(originatingContestPK,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
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
    @Override
    public Points[] findByOriginatingContestPK_PrevAndNext(long id,
        long originatingContestPK, OrderByComparator orderByComparator)
        throws NoSuchPointsException, SystemException {
        Points points = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            Points[] array = new PointsImpl[3];

            array[0] = getByOriginatingContestPK_PrevAndNext(session, points,
                    originatingContestPK, orderByComparator, true);

            array[1] = points;

            array[2] = getByOriginatingContestPK_PrevAndNext(session, points,
                    originatingContestPK, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Points getByOriginatingContestPK_PrevAndNext(Session session,
        Points points, long originatingContestPK,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_POINTS_WHERE);

        query.append(_FINDER_COLUMN_ORIGINATINGCONTESTPK_ORIGINATINGCONTESTPK_2);

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
            query.append(PointsModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(originatingContestPK);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(points);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Points> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the pointses where originatingContestPK = &#63; from the database.
     *
     * @param originatingContestPK the originating contest p k
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByOriginatingContestPK(long originatingContestPK)
        throws SystemException {
        for (Points points : findByOriginatingContestPK(originatingContestPK,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(points);
        }
    }

    /**
     * Returns the number of pointses where originatingContestPK = &#63;.
     *
     * @param originatingContestPK the originating contest p k
     * @return the number of matching pointses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByOriginatingContestPK(long originatingContestPK)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ORIGINATINGCONTESTPK;

        Object[] finderArgs = new Object[] { originatingContestPK };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_POINTS_WHERE);

            query.append(_FINDER_COLUMN_ORIGINATINGCONTESTPK_ORIGINATINGCONTESTPK_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(originatingContestPK);

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
     * Caches the points in the entity cache if it is enabled.
     *
     * @param points the points
     */
    @Override
    public void cacheResult(Points points) {
        EntityCacheUtil.putResult(PointsModelImpl.ENTITY_CACHE_ENABLED,
            PointsImpl.class, points.getPrimaryKey(), points);

        points.resetOriginalValues();
    }

    /**
     * Caches the pointses in the entity cache if it is enabled.
     *
     * @param pointses the pointses
     */
    @Override
    public void cacheResult(List<Points> pointses) {
        for (Points points : pointses) {
            if (EntityCacheUtil.getResult(
                        PointsModelImpl.ENTITY_CACHE_ENABLED, PointsImpl.class,
                        points.getPrimaryKey()) == null) {
                cacheResult(points);
            } else {
                points.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all pointses.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PointsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PointsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the points.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(Points points) {
        EntityCacheUtil.removeResult(PointsModelImpl.ENTITY_CACHE_ENABLED,
            PointsImpl.class, points.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<Points> pointses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (Points points : pointses) {
            EntityCacheUtil.removeResult(PointsModelImpl.ENTITY_CACHE_ENABLED,
                PointsImpl.class, points.getPrimaryKey());
        }
    }

    /**
     * Creates a new points with the primary key. Does not add the points to the database.
     *
     * @param id the primary key for the new points
     * @return the new points
     */
    @Override
    public Points create(long id) {
        Points points = new PointsImpl();

        points.setNew(true);
        points.setPrimaryKey(id);

        return points;
    }

    /**
     * Removes the points with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the points
     * @return the points that was removed
     * @throws com.ext.portlet.NoSuchPointsException if a points with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Points remove(long id) throws NoSuchPointsException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the points with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the points
     * @return the points that was removed
     * @throws com.ext.portlet.NoSuchPointsException if a points with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Points remove(Serializable primaryKey)
        throws NoSuchPointsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            Points points = (Points) session.get(PointsImpl.class, primaryKey);

            if (points == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPointsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(points);
        } catch (NoSuchPointsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected Points removeImpl(Points points) throws SystemException {
        points = toUnwrappedModel(points);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(points)) {
                points = (Points) session.get(PointsImpl.class,
                        points.getPrimaryKeyObj());
            }

            if (points != null) {
                session.delete(points);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (points != null) {
            clearCache(points);
        }

        return points;
    }

    @Override
    public Points updateImpl(com.ext.portlet.model.Points points)
        throws SystemException {
        points = toUnwrappedModel(points);

        boolean isNew = points.isNew();

        PointsModelImpl pointsModelImpl = (PointsModelImpl) points;

        Session session = null;

        try {
            session = openSession();

            if (points.isNew()) {
                session.save(points);

                points.setNew(false);
            } else {
                session.merge(points);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PointsModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((pointsModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        pointsModelImpl.getOriginalProposalId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID,
                    args);

                args = new Object[] { pointsModelImpl.getProposalId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID,
                    args);
            }

            if ((pointsModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] { pointsModelImpl.getOriginalUserId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
                    args);

                args = new Object[] { pointsModelImpl.getUserId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
                    args);
            }

            if ((pointsModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POINTSSOURCEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        pointsModelImpl.getOriginalPointsSourceId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_POINTSSOURCEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POINTSSOURCEID,
                    args);

                args = new Object[] { pointsModelImpl.getPointsSourceId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_POINTSSOURCEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_POINTSSOURCEID,
                    args);
            }

            if ((pointsModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORIGINATINGCONTESTPK.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        pointsModelImpl.getOriginalOriginatingContestPK()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ORIGINATINGCONTESTPK,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORIGINATINGCONTESTPK,
                    args);

                args = new Object[] { pointsModelImpl.getOriginatingContestPK() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ORIGINATINGCONTESTPK,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ORIGINATINGCONTESTPK,
                    args);
            }
        }

        EntityCacheUtil.putResult(PointsModelImpl.ENTITY_CACHE_ENABLED,
            PointsImpl.class, points.getPrimaryKey(), points);

        return points;
    }

    protected Points toUnwrappedModel(Points points) {
        if (points instanceof PointsImpl) {
            return points;
        }

        PointsImpl pointsImpl = new PointsImpl();

        pointsImpl.setNew(points.isNew());
        pointsImpl.setPrimaryKey(points.getPrimaryKey());

        pointsImpl.setId(points.getId());
        pointsImpl.setProposalId(points.getProposalId());
        pointsImpl.setUserId(points.getUserId());
        pointsImpl.setMaterializedPoints(points.getMaterializedPoints());
        pointsImpl.setHypotheticalPoints(points.getHypotheticalPoints());
        pointsImpl.setPointsSourceId(points.getPointsSourceId());
        pointsImpl.setOriginatingContestPK(points.getOriginatingContestPK());
        pointsImpl.setOriginatingProposalId(points.getOriginatingProposalId());

        return pointsImpl;
    }

    /**
     * Returns the points with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the points
     * @return the points
     * @throws com.ext.portlet.NoSuchPointsException if a points with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Points findByPrimaryKey(Serializable primaryKey)
        throws NoSuchPointsException, SystemException {
        Points points = fetchByPrimaryKey(primaryKey);

        if (points == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchPointsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return points;
    }

    /**
     * Returns the points with the primary key or throws a {@link com.ext.portlet.NoSuchPointsException} if it could not be found.
     *
     * @param id the primary key of the points
     * @return the points
     * @throws com.ext.portlet.NoSuchPointsException if a points with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Points findByPrimaryKey(long id)
        throws NoSuchPointsException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the points with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the points
     * @return the points, or <code>null</code> if a points with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Points fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        Points points = (Points) EntityCacheUtil.getResult(PointsModelImpl.ENTITY_CACHE_ENABLED,
                PointsImpl.class, primaryKey);

        if (points == _nullPoints) {
            return null;
        }

        if (points == null) {
            Session session = null;

            try {
                session = openSession();

                points = (Points) session.get(PointsImpl.class, primaryKey);

                if (points != null) {
                    cacheResult(points);
                } else {
                    EntityCacheUtil.putResult(PointsModelImpl.ENTITY_CACHE_ENABLED,
                        PointsImpl.class, primaryKey, _nullPoints);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(PointsModelImpl.ENTITY_CACHE_ENABLED,
                    PointsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return points;
    }

    /**
     * Returns the points with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the points
     * @return the points, or <code>null</code> if a points with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Points fetchByPrimaryKey(long id) throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the pointses.
     *
     * @return the pointses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Points> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
    @Override
    public List<Points> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
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
    @Override
    public List<Points> findAll(int start, int end,
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

        List<Points> list = (List<Points>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_POINTS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_POINTS;

                if (pagination) {
                    sql = sql.concat(PointsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<Points>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Points>(list);
                } else {
                    list = (List<Points>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Removes all the pointses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (Points points : findAll()) {
            remove(points);
        }
    }

    /**
     * Returns the number of pointses.
     *
     * @return the number of pointses
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

                Query q = session.createQuery(_SQL_COUNT_POINTS);

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

    @Override
    protected Set<String> getBadColumnNames() {
        return _badColumnNames;
    }

    /**
     * Initializes the points persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.Points")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<Points>> listenersList = new ArrayList<ModelListener<Points>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<Points>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PointsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
