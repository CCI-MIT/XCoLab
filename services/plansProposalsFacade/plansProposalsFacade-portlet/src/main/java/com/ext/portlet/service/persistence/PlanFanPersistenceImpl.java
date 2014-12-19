package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPlanFanException;
import com.ext.portlet.model.PlanFan;
import com.ext.portlet.model.impl.PlanFanImpl;
import com.ext.portlet.model.impl.PlanFanModelImpl;
import com.ext.portlet.service.persistence.PlanFanPersistence;

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
 * The persistence implementation for the plan fan service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanFanPersistence
 * @see PlanFanUtil
 * @generated
 */
public class PlanFanPersistenceImpl extends BasePersistenceImpl<PlanFan>
    implements PlanFanPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanFanUtil} to access the plan fan persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanFanImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, PlanFanImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, PlanFanImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PLANID = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, PlanFanImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPlanId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID =
        new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, PlanFanImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPlanId",
            new String[] { Long.class.getName() },
            PlanFanModelImpl.PLANID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANID = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPlanId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_PLANID_PLANID_2 = "planFan.planId = ? AND planFan.deleted is null";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, PlanFanImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
        new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, PlanFanImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
            new String[] { Long.class.getName() },
            PlanFanModelImpl.USERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_USERID_USERID_2 = "planFan.userId = ? AND planFan.deleted is null";
    public static final FinderPath FINDER_PATH_FETCH_BY_PLANIDUSERID = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, PlanFanImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchByPlanIdUserId",
            new String[] { Long.class.getName(), Long.class.getName() },
            PlanFanModelImpl.PLANID_COLUMN_BITMASK |
            PlanFanModelImpl.USERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANIDUSERID = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPlanIdUserId",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _FINDER_COLUMN_PLANIDUSERID_PLANID_2 = "planFan.planId = ? AND ";
    private static final String _FINDER_COLUMN_PLANIDUSERID_USERID_2 = "planFan.userId = ? AND planFan.deleted is null";
    private static final String _SQL_SELECT_PLANFAN = "SELECT planFan FROM PlanFan planFan";
    private static final String _SQL_SELECT_PLANFAN_WHERE = "SELECT planFan FROM PlanFan planFan WHERE ";
    private static final String _SQL_COUNT_PLANFAN = "SELECT COUNT(planFan) FROM PlanFan planFan";
    private static final String _SQL_COUNT_PLANFAN_WHERE = "SELECT COUNT(planFan) FROM PlanFan planFan WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planFan.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanFan exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PlanFan exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanFanPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static PlanFan _nullPlanFan = new PlanFanImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanFan> toCacheModel() {
                return _nullPlanFanCacheModel;
            }
        };

    private static CacheModel<PlanFan> _nullPlanFanCacheModel = new CacheModel<PlanFan>() {
            @Override
            public PlanFan toEntityModel() {
                return _nullPlanFan;
            }
        };

    public PlanFanPersistenceImpl() {
        setModelClass(PlanFan.class);
    }

    /**
     * Returns all the plan fans where planId = &#63;.
     *
     * @param planId the plan ID
     * @return the matching plan fans
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanFan> findByPlanId(long planId) throws SystemException {
        return findByPlanId(planId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan fans where planId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanFanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param planId the plan ID
     * @param start the lower bound of the range of plan fans
     * @param end the upper bound of the range of plan fans (not inclusive)
     * @return the range of matching plan fans
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanFan> findByPlanId(long planId, int start, int end)
        throws SystemException {
        return findByPlanId(planId, start, end, null);
    }

    /**
     * Returns an ordered range of all the plan fans where planId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanFanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param planId the plan ID
     * @param start the lower bound of the range of plan fans
     * @param end the upper bound of the range of plan fans (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching plan fans
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanFan> findByPlanId(long planId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID;
            finderArgs = new Object[] { planId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PLANID;
            finderArgs = new Object[] { planId, start, end, orderByComparator };
        }

        List<PlanFan> list = (List<PlanFan>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (PlanFan planFan : list) {
                if ((planId != planFan.getPlanId())) {
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

            query.append(_SQL_SELECT_PLANFAN_WHERE);

            query.append(_FINDER_COLUMN_PLANID_PLANID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(PlanFanModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planId);

                if (!pagination) {
                    list = (List<PlanFan>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PlanFan>(list);
                } else {
                    list = (List<PlanFan>) QueryUtil.list(q, getDialect(),
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
     * Returns the first plan fan in the ordered set where planId = &#63;.
     *
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan fan
     * @throws com.ext.portlet.NoSuchPlanFanException if a matching plan fan could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanFan findByPlanId_First(long planId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanFanException, SystemException {
        PlanFan planFan = fetchByPlanId_First(planId, orderByComparator);

        if (planFan != null) {
            return planFan;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("planId=");
        msg.append(planId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPlanFanException(msg.toString());
    }

    /**
     * Returns the first plan fan in the ordered set where planId = &#63;.
     *
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan fan, or <code>null</code> if a matching plan fan could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanFan fetchByPlanId_First(long planId,
        OrderByComparator orderByComparator) throws SystemException {
        List<PlanFan> list = findByPlanId(planId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last plan fan in the ordered set where planId = &#63;.
     *
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan fan
     * @throws com.ext.portlet.NoSuchPlanFanException if a matching plan fan could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanFan findByPlanId_Last(long planId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanFanException, SystemException {
        PlanFan planFan = fetchByPlanId_Last(planId, orderByComparator);

        if (planFan != null) {
            return planFan;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("planId=");
        msg.append(planId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPlanFanException(msg.toString());
    }

    /**
     * Returns the last plan fan in the ordered set where planId = &#63;.
     *
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan fan, or <code>null</code> if a matching plan fan could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanFan fetchByPlanId_Last(long planId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByPlanId(planId);

        if (count == 0) {
            return null;
        }

        List<PlanFan> list = findByPlanId(planId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the plan fans before and after the current plan fan in the ordered set where planId = &#63;.
     *
     * @param id the primary key of the current plan fan
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next plan fan
     * @throws com.ext.portlet.NoSuchPlanFanException if a plan fan with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanFan[] findByPlanId_PrevAndNext(long id, long planId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanFanException, SystemException {
        PlanFan planFan = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            PlanFan[] array = new PlanFanImpl[3];

            array[0] = getByPlanId_PrevAndNext(session, planFan, planId,
                    orderByComparator, true);

            array[1] = planFan;

            array[2] = getByPlanId_PrevAndNext(session, planFan, planId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PlanFan getByPlanId_PrevAndNext(Session session, PlanFan planFan,
        long planId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PLANFAN_WHERE);

        query.append(_FINDER_COLUMN_PLANID_PLANID_2);

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
            query.append(PlanFanModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(planId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(planFan);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PlanFan> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the plan fans where planId = &#63; from the database.
     *
     * @param planId the plan ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByPlanId(long planId) throws SystemException {
        for (PlanFan planFan : findByPlanId(planId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(planFan);
        }
    }

    /**
     * Returns the number of plan fans where planId = &#63;.
     *
     * @param planId the plan ID
     * @return the number of matching plan fans
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByPlanId(long planId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PLANID;

        Object[] finderArgs = new Object[] { planId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PLANFAN_WHERE);

            query.append(_FINDER_COLUMN_PLANID_PLANID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planId);

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
     * Returns all the plan fans where userId = &#63;.
     *
     * @param userId the user ID
     * @return the matching plan fans
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanFan> findByUserId(long userId) throws SystemException {
        return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan fans where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanFanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param userId the user ID
     * @param start the lower bound of the range of plan fans
     * @param end the upper bound of the range of plan fans (not inclusive)
     * @return the range of matching plan fans
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanFan> findByUserId(long userId, int start, int end)
        throws SystemException {
        return findByUserId(userId, start, end, null);
    }

    /**
     * Returns an ordered range of all the plan fans where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanFanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param userId the user ID
     * @param start the lower bound of the range of plan fans
     * @param end the upper bound of the range of plan fans (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching plan fans
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanFan> findByUserId(long userId, int start, int end,
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

        List<PlanFan> list = (List<PlanFan>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (PlanFan planFan : list) {
                if ((userId != planFan.getUserId())) {
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

            query.append(_SQL_SELECT_PLANFAN_WHERE);

            query.append(_FINDER_COLUMN_USERID_USERID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(PlanFanModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                if (!pagination) {
                    list = (List<PlanFan>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PlanFan>(list);
                } else {
                    list = (List<PlanFan>) QueryUtil.list(q, getDialect(),
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
     * Returns the first plan fan in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan fan
     * @throws com.ext.portlet.NoSuchPlanFanException if a matching plan fan could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanFan findByUserId_First(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanFanException, SystemException {
        PlanFan planFan = fetchByUserId_First(userId, orderByComparator);

        if (planFan != null) {
            return planFan;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("userId=");
        msg.append(userId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPlanFanException(msg.toString());
    }

    /**
     * Returns the first plan fan in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan fan, or <code>null</code> if a matching plan fan could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanFan fetchByUserId_First(long userId,
        OrderByComparator orderByComparator) throws SystemException {
        List<PlanFan> list = findByUserId(userId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last plan fan in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan fan
     * @throws com.ext.portlet.NoSuchPlanFanException if a matching plan fan could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanFan findByUserId_Last(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanFanException, SystemException {
        PlanFan planFan = fetchByUserId_Last(userId, orderByComparator);

        if (planFan != null) {
            return planFan;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("userId=");
        msg.append(userId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPlanFanException(msg.toString());
    }

    /**
     * Returns the last plan fan in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan fan, or <code>null</code> if a matching plan fan could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanFan fetchByUserId_Last(long userId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByUserId(userId);

        if (count == 0) {
            return null;
        }

        List<PlanFan> list = findByUserId(userId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the plan fans before and after the current plan fan in the ordered set where userId = &#63;.
     *
     * @param id the primary key of the current plan fan
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next plan fan
     * @throws com.ext.portlet.NoSuchPlanFanException if a plan fan with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanFan[] findByUserId_PrevAndNext(long id, long userId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanFanException, SystemException {
        PlanFan planFan = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            PlanFan[] array = new PlanFanImpl[3];

            array[0] = getByUserId_PrevAndNext(session, planFan, userId,
                    orderByComparator, true);

            array[1] = planFan;

            array[2] = getByUserId_PrevAndNext(session, planFan, userId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PlanFan getByUserId_PrevAndNext(Session session, PlanFan planFan,
        long userId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PLANFAN_WHERE);

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
            query.append(PlanFanModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(userId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(planFan);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PlanFan> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the plan fans where userId = &#63; from the database.
     *
     * @param userId the user ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByUserId(long userId) throws SystemException {
        for (PlanFan planFan : findByUserId(userId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(planFan);
        }
    }

    /**
     * Returns the number of plan fans where userId = &#63;.
     *
     * @param userId the user ID
     * @return the number of matching plan fans
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

            query.append(_SQL_COUNT_PLANFAN_WHERE);

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
     * Returns the plan fan where planId = &#63; and userId = &#63; or throws a {@link com.ext.portlet.NoSuchPlanFanException} if it could not be found.
     *
     * @param planId the plan ID
     * @param userId the user ID
     * @return the matching plan fan
     * @throws com.ext.portlet.NoSuchPlanFanException if a matching plan fan could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanFan findByPlanIdUserId(long planId, long userId)
        throws NoSuchPlanFanException, SystemException {
        PlanFan planFan = fetchByPlanIdUserId(planId, userId);

        if (planFan == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("planId=");
            msg.append(planId);

            msg.append(", userId=");
            msg.append(userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanFanException(msg.toString());
        }

        return planFan;
    }

    /**
     * Returns the plan fan where planId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param planId the plan ID
     * @param userId the user ID
     * @return the matching plan fan, or <code>null</code> if a matching plan fan could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanFan fetchByPlanIdUserId(long planId, long userId)
        throws SystemException {
        return fetchByPlanIdUserId(planId, userId, true);
    }

    /**
     * Returns the plan fan where planId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param planId the plan ID
     * @param userId the user ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching plan fan, or <code>null</code> if a matching plan fan could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanFan fetchByPlanIdUserId(long planId, long userId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { planId, userId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PLANIDUSERID,
                    finderArgs, this);
        }

        if (result instanceof PlanFan) {
            PlanFan planFan = (PlanFan) result;

            if ((planId != planFan.getPlanId()) ||
                    (userId != planFan.getUserId())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_SELECT_PLANFAN_WHERE);

            query.append(_FINDER_COLUMN_PLANIDUSERID_PLANID_2);

            query.append(_FINDER_COLUMN_PLANIDUSERID_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planId);

                qPos.add(userId);

                List<PlanFan> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANIDUSERID,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "PlanFanPersistenceImpl.fetchByPlanIdUserId(long, long, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    PlanFan planFan = list.get(0);

                    result = planFan;

                    cacheResult(planFan);

                    if ((planFan.getPlanId() != planId) ||
                            (planFan.getUserId() != userId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANIDUSERID,
                            finderArgs, planFan);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANIDUSERID,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (PlanFan) result;
        }
    }

    /**
     * Removes the plan fan where planId = &#63; and userId = &#63; from the database.
     *
     * @param planId the plan ID
     * @param userId the user ID
     * @return the plan fan that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanFan removeByPlanIdUserId(long planId, long userId)
        throws NoSuchPlanFanException, SystemException {
        PlanFan planFan = findByPlanIdUserId(planId, userId);

        return remove(planFan);
    }

    /**
     * Returns the number of plan fans where planId = &#63; and userId = &#63;.
     *
     * @param planId the plan ID
     * @param userId the user ID
     * @return the number of matching plan fans
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByPlanIdUserId(long planId, long userId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PLANIDUSERID;

        Object[] finderArgs = new Object[] { planId, userId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PLANFAN_WHERE);

            query.append(_FINDER_COLUMN_PLANIDUSERID_PLANID_2);

            query.append(_FINDER_COLUMN_PLANIDUSERID_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planId);

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
     * Caches the plan fan in the entity cache if it is enabled.
     *
     * @param planFan the plan fan
     */
    @Override
    public void cacheResult(PlanFan planFan) {
        EntityCacheUtil.putResult(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanImpl.class, planFan.getPrimaryKey(), planFan);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANIDUSERID,
            new Object[] { planFan.getPlanId(), planFan.getUserId() }, planFan);

        planFan.resetOriginalValues();
    }

    /**
     * Caches the plan fans in the entity cache if it is enabled.
     *
     * @param planFans the plan fans
     */
    @Override
    public void cacheResult(List<PlanFan> planFans) {
        for (PlanFan planFan : planFans) {
            if (EntityCacheUtil.getResult(
                        PlanFanModelImpl.ENTITY_CACHE_ENABLED,
                        PlanFanImpl.class, planFan.getPrimaryKey()) == null) {
                cacheResult(planFan);
            } else {
                planFan.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan fans.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanFanImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanFanImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan fan.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanFan planFan) {
        EntityCacheUtil.removeResult(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanImpl.class, planFan.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(planFan);
    }

    @Override
    public void clearCache(List<PlanFan> planFans) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanFan planFan : planFans) {
            EntityCacheUtil.removeResult(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
                PlanFanImpl.class, planFan.getPrimaryKey());

            clearUniqueFindersCache(planFan);
        }
    }

    protected void cacheUniqueFindersCache(PlanFan planFan) {
        if (planFan.isNew()) {
            Object[] args = new Object[] {
                    planFan.getPlanId(), planFan.getUserId()
                };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLANIDUSERID, args,
                Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANIDUSERID, args,
                planFan);
        } else {
            PlanFanModelImpl planFanModelImpl = (PlanFanModelImpl) planFan;

            if ((planFanModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_PLANIDUSERID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        planFan.getPlanId(), planFan.getUserId()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLANIDUSERID,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANIDUSERID,
                    args, planFan);
            }
        }
    }

    protected void clearUniqueFindersCache(PlanFan planFan) {
        PlanFanModelImpl planFanModelImpl = (PlanFanModelImpl) planFan;

        Object[] args = new Object[] { planFan.getPlanId(), planFan.getUserId() };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANIDUSERID, args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANIDUSERID, args);

        if ((planFanModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_PLANIDUSERID.getColumnBitmask()) != 0) {
            args = new Object[] {
                    planFanModelImpl.getOriginalPlanId(),
                    planFanModelImpl.getOriginalUserId()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANIDUSERID, args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANIDUSERID, args);
        }
    }

    /**
     * Creates a new plan fan with the primary key. Does not add the plan fan to the database.
     *
     * @param id the primary key for the new plan fan
     * @return the new plan fan
     */
    @Override
    public PlanFan create(long id) {
        PlanFan planFan = new PlanFanImpl();

        planFan.setNew(true);
        planFan.setPrimaryKey(id);

        return planFan;
    }

    /**
     * Removes the plan fan with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the plan fan
     * @return the plan fan that was removed
     * @throws com.ext.portlet.NoSuchPlanFanException if a plan fan with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanFan remove(long id)
        throws NoSuchPlanFanException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the plan fan with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan fan
     * @return the plan fan that was removed
     * @throws com.ext.portlet.NoSuchPlanFanException if a plan fan with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanFan remove(Serializable primaryKey)
        throws NoSuchPlanFanException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanFan planFan = (PlanFan) session.get(PlanFanImpl.class,
                    primaryKey);

            if (planFan == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanFanException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planFan);
        } catch (NoSuchPlanFanException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanFan removeImpl(PlanFan planFan) throws SystemException {
        planFan = toUnwrappedModel(planFan);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(planFan)) {
                planFan = (PlanFan) session.get(PlanFanImpl.class,
                        planFan.getPrimaryKeyObj());
            }

            if (planFan != null) {
                session.delete(planFan);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (planFan != null) {
            clearCache(planFan);
        }

        return planFan;
    }

    @Override
    public PlanFan updateImpl(com.ext.portlet.model.PlanFan planFan)
        throws SystemException {
        planFan = toUnwrappedModel(planFan);

        boolean isNew = planFan.isNew();

        PlanFanModelImpl planFanModelImpl = (PlanFanModelImpl) planFan;

        Session session = null;

        try {
            session = openSession();

            if (planFan.isNew()) {
                session.save(planFan);

                planFan.setNew(false);
            } else {
                session.merge(planFan);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PlanFanModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((planFanModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        planFanModelImpl.getOriginalPlanId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID,
                    args);

                args = new Object[] { planFanModelImpl.getPlanId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID,
                    args);
            }

            if ((planFanModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        planFanModelImpl.getOriginalUserId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
                    args);

                args = new Object[] { planFanModelImpl.getUserId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
                    args);
            }
        }

        EntityCacheUtil.putResult(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanImpl.class, planFan.getPrimaryKey(), planFan);

        clearUniqueFindersCache(planFan);
        cacheUniqueFindersCache(planFan);

        return planFan;
    }

    protected PlanFan toUnwrappedModel(PlanFan planFan) {
        if (planFan instanceof PlanFanImpl) {
            return planFan;
        }

        PlanFanImpl planFanImpl = new PlanFanImpl();

        planFanImpl.setNew(planFan.isNew());
        planFanImpl.setPrimaryKey(planFan.getPrimaryKey());

        planFanImpl.setId(planFan.getId());
        planFanImpl.setUserId(planFan.getUserId());
        planFanImpl.setPlanId(planFan.getPlanId());
        planFanImpl.setCreated(planFan.getCreated());
        planFanImpl.setDeleted(planFan.getDeleted());

        return planFanImpl;
    }

    /**
     * Returns the plan fan with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan fan
     * @return the plan fan
     * @throws com.ext.portlet.NoSuchPlanFanException if a plan fan with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanFan findByPrimaryKey(Serializable primaryKey)
        throws NoSuchPlanFanException, SystemException {
        PlanFan planFan = fetchByPrimaryKey(primaryKey);

        if (planFan == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchPlanFanException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return planFan;
    }

    /**
     * Returns the plan fan with the primary key or throws a {@link com.ext.portlet.NoSuchPlanFanException} if it could not be found.
     *
     * @param id the primary key of the plan fan
     * @return the plan fan
     * @throws com.ext.portlet.NoSuchPlanFanException if a plan fan with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanFan findByPrimaryKey(long id)
        throws NoSuchPlanFanException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the plan fan with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan fan
     * @return the plan fan, or <code>null</code> if a plan fan with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanFan fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        PlanFan planFan = (PlanFan) EntityCacheUtil.getResult(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
                PlanFanImpl.class, primaryKey);

        if (planFan == _nullPlanFan) {
            return null;
        }

        if (planFan == null) {
            Session session = null;

            try {
                session = openSession();

                planFan = (PlanFan) session.get(PlanFanImpl.class, primaryKey);

                if (planFan != null) {
                    cacheResult(planFan);
                } else {
                    EntityCacheUtil.putResult(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
                        PlanFanImpl.class, primaryKey, _nullPlanFan);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
                    PlanFanImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return planFan;
    }

    /**
     * Returns the plan fan with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the plan fan
     * @return the plan fan, or <code>null</code> if a plan fan with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanFan fetchByPrimaryKey(long id) throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the plan fans.
     *
     * @return the plan fans
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanFan> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan fans.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanFanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan fans
     * @param end the upper bound of the range of plan fans (not inclusive)
     * @return the range of plan fans
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanFan> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan fans.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanFanModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan fans
     * @param end the upper bound of the range of plan fans (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan fans
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanFan> findAll(int start, int end,
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

        List<PlanFan> list = (List<PlanFan>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANFAN);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANFAN;

                if (pagination) {
                    sql = sql.concat(PlanFanModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<PlanFan>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PlanFan>(list);
                } else {
                    list = (List<PlanFan>) QueryUtil.list(q, getDialect(),
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
     * Removes all the plan fans from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (PlanFan planFan : findAll()) {
            remove(planFan);
        }
    }

    /**
     * Returns the number of plan fans.
     *
     * @return the number of plan fans
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

                Query q = session.createQuery(_SQL_COUNT_PLANFAN);

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
     * Initializes the plan fan persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.PlanFan")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanFan>> listenersList = new ArrayList<ModelListener<PlanFan>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanFan>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanFanImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
