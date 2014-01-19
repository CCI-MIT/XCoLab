package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPlanItemGroupException;
import com.ext.portlet.model.PlanItemGroup;
import com.ext.portlet.model.impl.PlanItemGroupImpl;
import com.ext.portlet.model.impl.PlanItemGroupModelImpl;
import com.ext.portlet.service.persistence.PlanItemGroupPersistence;

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
 * The persistence implementation for the plan item group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanItemGroupPersistence
 * @see PlanItemGroupUtil
 * @generated
 */
public class PlanItemGroupPersistenceImpl extends BasePersistenceImpl<PlanItemGroup>
    implements PlanItemGroupPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanItemGroupUtil} to access the plan item group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanItemGroupImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanItemGroupModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemGroupModelImpl.FINDER_CACHE_ENABLED,
            PlanItemGroupImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanItemGroupModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemGroupModelImpl.FINDER_CACHE_ENABLED,
            PlanItemGroupImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanItemGroupModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID = new FinderPath(PlanItemGroupModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemGroupModelImpl.FINDER_CACHE_ENABLED,
            PlanItemGroupImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByGroupId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID =
        new FinderPath(PlanItemGroupModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemGroupModelImpl.FINDER_CACHE_ENABLED,
            PlanItemGroupImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByGroupId", new String[] { Long.class.getName() },
            PlanItemGroupModelImpl.GROUPID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_GROUPID = new FinderPath(PlanItemGroupModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 = "planItemGroup.groupId = ?";
    private static final String _SQL_SELECT_PLANITEMGROUP = "SELECT planItemGroup FROM PlanItemGroup planItemGroup";
    private static final String _SQL_SELECT_PLANITEMGROUP_WHERE = "SELECT planItemGroup FROM PlanItemGroup planItemGroup WHERE ";
    private static final String _SQL_COUNT_PLANITEMGROUP = "SELECT COUNT(planItemGroup) FROM PlanItemGroup planItemGroup";
    private static final String _SQL_COUNT_PLANITEMGROUP_WHERE = "SELECT COUNT(planItemGroup) FROM PlanItemGroup planItemGroup WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planItemGroup.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanItemGroup exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PlanItemGroup exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanItemGroupPersistenceImpl.class);
    private static PlanItemGroup _nullPlanItemGroup = new PlanItemGroupImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanItemGroup> toCacheModel() {
                return _nullPlanItemGroupCacheModel;
            }
        };

    private static CacheModel<PlanItemGroup> _nullPlanItemGroupCacheModel = new CacheModel<PlanItemGroup>() {
            @Override
            public PlanItemGroup toEntityModel() {
                return _nullPlanItemGroup;
            }
        };

    public PlanItemGroupPersistenceImpl() {
        setModelClass(PlanItemGroup.class);
    }

    /**
     * Returns all the plan item groups where groupId = &#63;.
     *
     * @param groupId the group ID
     * @return the matching plan item groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanItemGroup> findByGroupId(long groupId)
        throws SystemException {
        return findByGroupId(groupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan item groups where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param groupId the group ID
     * @param start the lower bound of the range of plan item groups
     * @param end the upper bound of the range of plan item groups (not inclusive)
     * @return the range of matching plan item groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanItemGroup> findByGroupId(long groupId, int start, int end)
        throws SystemException {
        return findByGroupId(groupId, start, end, null);
    }

    /**
     * Returns an ordered range of all the plan item groups where groupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param groupId the group ID
     * @param start the lower bound of the range of plan item groups
     * @param end the upper bound of the range of plan item groups (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching plan item groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanItemGroup> findByGroupId(long groupId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID;
            finderArgs = new Object[] { groupId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_GROUPID;
            finderArgs = new Object[] { groupId, start, end, orderByComparator };
        }

        List<PlanItemGroup> list = (List<PlanItemGroup>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (PlanItemGroup planItemGroup : list) {
                if ((groupId != planItemGroup.getGroupId())) {
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

            query.append(_SQL_SELECT_PLANITEMGROUP_WHERE);

            query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(PlanItemGroupModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(groupId);

                if (!pagination) {
                    list = (List<PlanItemGroup>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PlanItemGroup>(list);
                } else {
                    list = (List<PlanItemGroup>) QueryUtil.list(q,
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
     * Returns the first plan item group in the ordered set where groupId = &#63;.
     *
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan item group
     * @throws com.ext.portlet.NoSuchPlanItemGroupException if a matching plan item group could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanItemGroup findByGroupId_First(long groupId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanItemGroupException, SystemException {
        PlanItemGroup planItemGroup = fetchByGroupId_First(groupId,
                orderByComparator);

        if (planItemGroup != null) {
            return planItemGroup;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("groupId=");
        msg.append(groupId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPlanItemGroupException(msg.toString());
    }

    /**
     * Returns the first plan item group in the ordered set where groupId = &#63;.
     *
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan item group, or <code>null</code> if a matching plan item group could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanItemGroup fetchByGroupId_First(long groupId,
        OrderByComparator orderByComparator) throws SystemException {
        List<PlanItemGroup> list = findByGroupId(groupId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last plan item group in the ordered set where groupId = &#63;.
     *
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan item group
     * @throws com.ext.portlet.NoSuchPlanItemGroupException if a matching plan item group could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanItemGroup findByGroupId_Last(long groupId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanItemGroupException, SystemException {
        PlanItemGroup planItemGroup = fetchByGroupId_Last(groupId,
                orderByComparator);

        if (planItemGroup != null) {
            return planItemGroup;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("groupId=");
        msg.append(groupId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPlanItemGroupException(msg.toString());
    }

    /**
     * Returns the last plan item group in the ordered set where groupId = &#63;.
     *
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan item group, or <code>null</code> if a matching plan item group could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanItemGroup fetchByGroupId_Last(long groupId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByGroupId(groupId);

        if (count == 0) {
            return null;
        }

        List<PlanItemGroup> list = findByGroupId(groupId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the plan item groups before and after the current plan item group in the ordered set where groupId = &#63;.
     *
     * @param planId the primary key of the current plan item group
     * @param groupId the group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next plan item group
     * @throws com.ext.portlet.NoSuchPlanItemGroupException if a plan item group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanItemGroup[] findByGroupId_PrevAndNext(long planId, long groupId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanItemGroupException, SystemException {
        PlanItemGroup planItemGroup = findByPrimaryKey(planId);

        Session session = null;

        try {
            session = openSession();

            PlanItemGroup[] array = new PlanItemGroupImpl[3];

            array[0] = getByGroupId_PrevAndNext(session, planItemGroup,
                    groupId, orderByComparator, true);

            array[1] = planItemGroup;

            array[2] = getByGroupId_PrevAndNext(session, planItemGroup,
                    groupId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PlanItemGroup getByGroupId_PrevAndNext(Session session,
        PlanItemGroup planItemGroup, long groupId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PLANITEMGROUP_WHERE);

        query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

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
            query.append(PlanItemGroupModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(groupId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(planItemGroup);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PlanItemGroup> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the plan item groups where groupId = &#63; from the database.
     *
     * @param groupId the group ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByGroupId(long groupId) throws SystemException {
        for (PlanItemGroup planItemGroup : findByGroupId(groupId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(planItemGroup);
        }
    }

    /**
     * Returns the number of plan item groups where groupId = &#63;.
     *
     * @param groupId the group ID
     * @return the number of matching plan item groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByGroupId(long groupId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_GROUPID;

        Object[] finderArgs = new Object[] { groupId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PLANITEMGROUP_WHERE);

            query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(groupId);

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
     * Caches the plan item group in the entity cache if it is enabled.
     *
     * @param planItemGroup the plan item group
     */
    @Override
    public void cacheResult(PlanItemGroup planItemGroup) {
        EntityCacheUtil.putResult(PlanItemGroupModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemGroupImpl.class, planItemGroup.getPrimaryKey(),
            planItemGroup);

        planItemGroup.resetOriginalValues();
    }

    /**
     * Caches the plan item groups in the entity cache if it is enabled.
     *
     * @param planItemGroups the plan item groups
     */
    @Override
    public void cacheResult(List<PlanItemGroup> planItemGroups) {
        for (PlanItemGroup planItemGroup : planItemGroups) {
            if (EntityCacheUtil.getResult(
                        PlanItemGroupModelImpl.ENTITY_CACHE_ENABLED,
                        PlanItemGroupImpl.class, planItemGroup.getPrimaryKey()) == null) {
                cacheResult(planItemGroup);
            } else {
                planItemGroup.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan item groups.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanItemGroupImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanItemGroupImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan item group.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanItemGroup planItemGroup) {
        EntityCacheUtil.removeResult(PlanItemGroupModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemGroupImpl.class, planItemGroup.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<PlanItemGroup> planItemGroups) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanItemGroup planItemGroup : planItemGroups) {
            EntityCacheUtil.removeResult(PlanItemGroupModelImpl.ENTITY_CACHE_ENABLED,
                PlanItemGroupImpl.class, planItemGroup.getPrimaryKey());
        }
    }

    /**
     * Creates a new plan item group with the primary key. Does not add the plan item group to the database.
     *
     * @param planId the primary key for the new plan item group
     * @return the new plan item group
     */
    @Override
    public PlanItemGroup create(long planId) {
        PlanItemGroup planItemGroup = new PlanItemGroupImpl();

        planItemGroup.setNew(true);
        planItemGroup.setPrimaryKey(planId);

        return planItemGroup;
    }

    /**
     * Removes the plan item group with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param planId the primary key of the plan item group
     * @return the plan item group that was removed
     * @throws com.ext.portlet.NoSuchPlanItemGroupException if a plan item group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanItemGroup remove(long planId)
        throws NoSuchPlanItemGroupException, SystemException {
        return remove((Serializable) planId);
    }

    /**
     * Removes the plan item group with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan item group
     * @return the plan item group that was removed
     * @throws com.ext.portlet.NoSuchPlanItemGroupException if a plan item group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanItemGroup remove(Serializable primaryKey)
        throws NoSuchPlanItemGroupException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanItemGroup planItemGroup = (PlanItemGroup) session.get(PlanItemGroupImpl.class,
                    primaryKey);

            if (planItemGroup == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanItemGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planItemGroup);
        } catch (NoSuchPlanItemGroupException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanItemGroup removeImpl(PlanItemGroup planItemGroup)
        throws SystemException {
        planItemGroup = toUnwrappedModel(planItemGroup);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(planItemGroup)) {
                planItemGroup = (PlanItemGroup) session.get(PlanItemGroupImpl.class,
                        planItemGroup.getPrimaryKeyObj());
            }

            if (planItemGroup != null) {
                session.delete(planItemGroup);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (planItemGroup != null) {
            clearCache(planItemGroup);
        }

        return planItemGroup;
    }

    @Override
    public PlanItemGroup updateImpl(
        com.ext.portlet.model.PlanItemGroup planItemGroup)
        throws SystemException {
        planItemGroup = toUnwrappedModel(planItemGroup);

        boolean isNew = planItemGroup.isNew();

        PlanItemGroupModelImpl planItemGroupModelImpl = (PlanItemGroupModelImpl) planItemGroup;

        Session session = null;

        try {
            session = openSession();

            if (planItemGroup.isNew()) {
                session.save(planItemGroup);

                planItemGroup.setNew(false);
            } else {
                session.merge(planItemGroup);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PlanItemGroupModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((planItemGroupModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        planItemGroupModelImpl.getOriginalGroupId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
                    args);

                args = new Object[] { planItemGroupModelImpl.getGroupId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_GROUPID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_GROUPID,
                    args);
            }
        }

        EntityCacheUtil.putResult(PlanItemGroupModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemGroupImpl.class, planItemGroup.getPrimaryKey(),
            planItemGroup);

        return planItemGroup;
    }

    protected PlanItemGroup toUnwrappedModel(PlanItemGroup planItemGroup) {
        if (planItemGroup instanceof PlanItemGroupImpl) {
            return planItemGroup;
        }

        PlanItemGroupImpl planItemGroupImpl = new PlanItemGroupImpl();

        planItemGroupImpl.setNew(planItemGroup.isNew());
        planItemGroupImpl.setPrimaryKey(planItemGroup.getPrimaryKey());

        planItemGroupImpl.setPlanId(planItemGroup.getPlanId());
        planItemGroupImpl.setGroupId(planItemGroup.getGroupId());

        return planItemGroupImpl;
    }

    /**
     * Returns the plan item group with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan item group
     * @return the plan item group
     * @throws com.ext.portlet.NoSuchPlanItemGroupException if a plan item group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanItemGroup findByPrimaryKey(Serializable primaryKey)
        throws NoSuchPlanItemGroupException, SystemException {
        PlanItemGroup planItemGroup = fetchByPrimaryKey(primaryKey);

        if (planItemGroup == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchPlanItemGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return planItemGroup;
    }

    /**
     * Returns the plan item group with the primary key or throws a {@link com.ext.portlet.NoSuchPlanItemGroupException} if it could not be found.
     *
     * @param planId the primary key of the plan item group
     * @return the plan item group
     * @throws com.ext.portlet.NoSuchPlanItemGroupException if a plan item group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanItemGroup findByPrimaryKey(long planId)
        throws NoSuchPlanItemGroupException, SystemException {
        return findByPrimaryKey((Serializable) planId);
    }

    /**
     * Returns the plan item group with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan item group
     * @return the plan item group, or <code>null</code> if a plan item group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanItemGroup fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        PlanItemGroup planItemGroup = (PlanItemGroup) EntityCacheUtil.getResult(PlanItemGroupModelImpl.ENTITY_CACHE_ENABLED,
                PlanItemGroupImpl.class, primaryKey);

        if (planItemGroup == _nullPlanItemGroup) {
            return null;
        }

        if (planItemGroup == null) {
            Session session = null;

            try {
                session = openSession();

                planItemGroup = (PlanItemGroup) session.get(PlanItemGroupImpl.class,
                        primaryKey);

                if (planItemGroup != null) {
                    cacheResult(planItemGroup);
                } else {
                    EntityCacheUtil.putResult(PlanItemGroupModelImpl.ENTITY_CACHE_ENABLED,
                        PlanItemGroupImpl.class, primaryKey, _nullPlanItemGroup);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(PlanItemGroupModelImpl.ENTITY_CACHE_ENABLED,
                    PlanItemGroupImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return planItemGroup;
    }

    /**
     * Returns the plan item group with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param planId the primary key of the plan item group
     * @return the plan item group, or <code>null</code> if a plan item group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanItemGroup fetchByPrimaryKey(long planId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) planId);
    }

    /**
     * Returns all the plan item groups.
     *
     * @return the plan item groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanItemGroup> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan item groups.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan item groups
     * @param end the upper bound of the range of plan item groups (not inclusive)
     * @return the range of plan item groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanItemGroup> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan item groups.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan item groups
     * @param end the upper bound of the range of plan item groups (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan item groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanItemGroup> findAll(int start, int end,
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

        List<PlanItemGroup> list = (List<PlanItemGroup>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANITEMGROUP);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANITEMGROUP;

                if (pagination) {
                    sql = sql.concat(PlanItemGroupModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<PlanItemGroup>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PlanItemGroup>(list);
                } else {
                    list = (List<PlanItemGroup>) QueryUtil.list(q,
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
     * Removes all the plan item groups from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (PlanItemGroup planItemGroup : findAll()) {
            remove(planItemGroup);
        }
    }

    /**
     * Returns the number of plan item groups.
     *
     * @return the number of plan item groups
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

                Query q = session.createQuery(_SQL_COUNT_PLANITEMGROUP);

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
     * Initializes the plan item group persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.PlanItemGroup")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanItemGroup>> listenersList = new ArrayList<ModelListener<PlanItemGroup>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanItemGroup>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanItemGroupImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
