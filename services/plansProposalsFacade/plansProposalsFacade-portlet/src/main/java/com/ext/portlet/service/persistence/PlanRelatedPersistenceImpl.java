package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPlanRelatedException;
import com.ext.portlet.model.PlanRelated;
import com.ext.portlet.model.impl.PlanRelatedImpl;
import com.ext.portlet.model.impl.PlanRelatedModelImpl;
import com.ext.portlet.service.persistence.PlanRelatedPersistence;

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
 * The persistence implementation for the plan related service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanRelatedPersistence
 * @see PlanRelatedUtil
 * @generated
 */
public class PlanRelatedPersistenceImpl extends BasePersistenceImpl<PlanRelated>
    implements PlanRelatedPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanRelatedUtil} to access the plan related persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanRelatedImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedModelImpl.FINDER_CACHE_ENABLED, PlanRelatedImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedModelImpl.FINDER_CACHE_ENABLED, PlanRelatedImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PLANID = new FinderPath(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedModelImpl.FINDER_CACHE_ENABLED, PlanRelatedImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPlanId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID =
        new FinderPath(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedModelImpl.FINDER_CACHE_ENABLED, PlanRelatedImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPlanId",
            new String[] { Long.class.getName() },
            PlanRelatedModelImpl.RELATEDPLANID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANID = new FinderPath(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPlanId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_PLANID_RELATEDPLANID_2 = "planRelated.id.relatedPlanId = ?";
    private static final String _SQL_SELECT_PLANRELATED = "SELECT planRelated FROM PlanRelated planRelated";
    private static final String _SQL_SELECT_PLANRELATED_WHERE = "SELECT planRelated FROM PlanRelated planRelated WHERE ";
    private static final String _SQL_COUNT_PLANRELATED = "SELECT COUNT(planRelated) FROM PlanRelated planRelated";
    private static final String _SQL_COUNT_PLANRELATED_WHERE = "SELECT COUNT(planRelated) FROM PlanRelated planRelated WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planRelated.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanRelated exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PlanRelated exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanRelatedPersistenceImpl.class);
    private static PlanRelated _nullPlanRelated = new PlanRelatedImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanRelated> toCacheModel() {
                return _nullPlanRelatedCacheModel;
            }
        };

    private static CacheModel<PlanRelated> _nullPlanRelatedCacheModel = new CacheModel<PlanRelated>() {
            @Override
            public PlanRelated toEntityModel() {
                return _nullPlanRelated;
            }
        };

    public PlanRelatedPersistenceImpl() {
        setModelClass(PlanRelated.class);
    }

    /**
     * Returns all the plan relateds where relatedPlanId = &#63;.
     *
     * @param relatedPlanId the related plan ID
     * @return the matching plan relateds
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanRelated> findByPlanId(long relatedPlanId)
        throws SystemException {
        return findByPlanId(relatedPlanId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan relateds where relatedPlanId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param relatedPlanId the related plan ID
     * @param start the lower bound of the range of plan relateds
     * @param end the upper bound of the range of plan relateds (not inclusive)
     * @return the range of matching plan relateds
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanRelated> findByPlanId(long relatedPlanId, int start, int end)
        throws SystemException {
        return findByPlanId(relatedPlanId, start, end, null);
    }

    /**
     * Returns an ordered range of all the plan relateds where relatedPlanId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param relatedPlanId the related plan ID
     * @param start the lower bound of the range of plan relateds
     * @param end the upper bound of the range of plan relateds (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching plan relateds
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanRelated> findByPlanId(long relatedPlanId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID;
            finderArgs = new Object[] { relatedPlanId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PLANID;
            finderArgs = new Object[] {
                    relatedPlanId,
                    
                    start, end, orderByComparator
                };
        }

        List<PlanRelated> list = (List<PlanRelated>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (PlanRelated planRelated : list) {
                if ((relatedPlanId != planRelated.getRelatedPlanId())) {
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

            query.append(_SQL_SELECT_PLANRELATED_WHERE);

            query.append(_FINDER_COLUMN_PLANID_RELATEDPLANID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(PlanRelatedModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(relatedPlanId);

                if (!pagination) {
                    list = (List<PlanRelated>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PlanRelated>(list);
                } else {
                    list = (List<PlanRelated>) QueryUtil.list(q, getDialect(),
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
     * Returns the first plan related in the ordered set where relatedPlanId = &#63;.
     *
     * @param relatedPlanId the related plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan related
     * @throws com.ext.portlet.NoSuchPlanRelatedException if a matching plan related could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanRelated findByPlanId_First(long relatedPlanId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanRelatedException, SystemException {
        PlanRelated planRelated = fetchByPlanId_First(relatedPlanId,
                orderByComparator);

        if (planRelated != null) {
            return planRelated;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("relatedPlanId=");
        msg.append(relatedPlanId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPlanRelatedException(msg.toString());
    }

    /**
     * Returns the first plan related in the ordered set where relatedPlanId = &#63;.
     *
     * @param relatedPlanId the related plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan related, or <code>null</code> if a matching plan related could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanRelated fetchByPlanId_First(long relatedPlanId,
        OrderByComparator orderByComparator) throws SystemException {
        List<PlanRelated> list = findByPlanId(relatedPlanId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last plan related in the ordered set where relatedPlanId = &#63;.
     *
     * @param relatedPlanId the related plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan related
     * @throws com.ext.portlet.NoSuchPlanRelatedException if a matching plan related could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanRelated findByPlanId_Last(long relatedPlanId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanRelatedException, SystemException {
        PlanRelated planRelated = fetchByPlanId_Last(relatedPlanId,
                orderByComparator);

        if (planRelated != null) {
            return planRelated;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("relatedPlanId=");
        msg.append(relatedPlanId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPlanRelatedException(msg.toString());
    }

    /**
     * Returns the last plan related in the ordered set where relatedPlanId = &#63;.
     *
     * @param relatedPlanId the related plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan related, or <code>null</code> if a matching plan related could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanRelated fetchByPlanId_Last(long relatedPlanId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByPlanId(relatedPlanId);

        if (count == 0) {
            return null;
        }

        List<PlanRelated> list = findByPlanId(relatedPlanId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the plan relateds before and after the current plan related in the ordered set where relatedPlanId = &#63;.
     *
     * @param planRelatedPK the primary key of the current plan related
     * @param relatedPlanId the related plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next plan related
     * @throws com.ext.portlet.NoSuchPlanRelatedException if a plan related with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanRelated[] findByPlanId_PrevAndNext(PlanRelatedPK planRelatedPK,
        long relatedPlanId, OrderByComparator orderByComparator)
        throws NoSuchPlanRelatedException, SystemException {
        PlanRelated planRelated = findByPrimaryKey(planRelatedPK);

        Session session = null;

        try {
            session = openSession();

            PlanRelated[] array = new PlanRelatedImpl[3];

            array[0] = getByPlanId_PrevAndNext(session, planRelated,
                    relatedPlanId, orderByComparator, true);

            array[1] = planRelated;

            array[2] = getByPlanId_PrevAndNext(session, planRelated,
                    relatedPlanId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PlanRelated getByPlanId_PrevAndNext(Session session,
        PlanRelated planRelated, long relatedPlanId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PLANRELATED_WHERE);

        query.append(_FINDER_COLUMN_PLANID_RELATEDPLANID_2);

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
            query.append(PlanRelatedModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(relatedPlanId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(planRelated);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PlanRelated> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the plan relateds where relatedPlanId = &#63; from the database.
     *
     * @param relatedPlanId the related plan ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByPlanId(long relatedPlanId) throws SystemException {
        for (PlanRelated planRelated : findByPlanId(relatedPlanId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(planRelated);
        }
    }

    /**
     * Returns the number of plan relateds where relatedPlanId = &#63;.
     *
     * @param relatedPlanId the related plan ID
     * @return the number of matching plan relateds
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByPlanId(long relatedPlanId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PLANID;

        Object[] finderArgs = new Object[] { relatedPlanId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PLANRELATED_WHERE);

            query.append(_FINDER_COLUMN_PLANID_RELATEDPLANID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(relatedPlanId);

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
     * Caches the plan related in the entity cache if it is enabled.
     *
     * @param planRelated the plan related
     */
    @Override
    public void cacheResult(PlanRelated planRelated) {
        EntityCacheUtil.putResult(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedImpl.class, planRelated.getPrimaryKey(), planRelated);

        planRelated.resetOriginalValues();
    }

    /**
     * Caches the plan relateds in the entity cache if it is enabled.
     *
     * @param planRelateds the plan relateds
     */
    @Override
    public void cacheResult(List<PlanRelated> planRelateds) {
        for (PlanRelated planRelated : planRelateds) {
            if (EntityCacheUtil.getResult(
                        PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
                        PlanRelatedImpl.class, planRelated.getPrimaryKey()) == null) {
                cacheResult(planRelated);
            } else {
                planRelated.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan relateds.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanRelatedImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanRelatedImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan related.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanRelated planRelated) {
        EntityCacheUtil.removeResult(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedImpl.class, planRelated.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<PlanRelated> planRelateds) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanRelated planRelated : planRelateds) {
            EntityCacheUtil.removeResult(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
                PlanRelatedImpl.class, planRelated.getPrimaryKey());
        }
    }

    /**
     * Creates a new plan related with the primary key. Does not add the plan related to the database.
     *
     * @param planRelatedPK the primary key for the new plan related
     * @return the new plan related
     */
    @Override
    public PlanRelated create(PlanRelatedPK planRelatedPK) {
        PlanRelated planRelated = new PlanRelatedImpl();

        planRelated.setNew(true);
        planRelated.setPrimaryKey(planRelatedPK);

        return planRelated;
    }

    /**
     * Removes the plan related with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param planRelatedPK the primary key of the plan related
     * @return the plan related that was removed
     * @throws com.ext.portlet.NoSuchPlanRelatedException if a plan related with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanRelated remove(PlanRelatedPK planRelatedPK)
        throws NoSuchPlanRelatedException, SystemException {
        return remove((Serializable) planRelatedPK);
    }

    /**
     * Removes the plan related with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan related
     * @return the plan related that was removed
     * @throws com.ext.portlet.NoSuchPlanRelatedException if a plan related with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanRelated remove(Serializable primaryKey)
        throws NoSuchPlanRelatedException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanRelated planRelated = (PlanRelated) session.get(PlanRelatedImpl.class,
                    primaryKey);

            if (planRelated == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanRelatedException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planRelated);
        } catch (NoSuchPlanRelatedException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanRelated removeImpl(PlanRelated planRelated)
        throws SystemException {
        planRelated = toUnwrappedModel(planRelated);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(planRelated)) {
                planRelated = (PlanRelated) session.get(PlanRelatedImpl.class,
                        planRelated.getPrimaryKeyObj());
            }

            if (planRelated != null) {
                session.delete(planRelated);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (planRelated != null) {
            clearCache(planRelated);
        }

        return planRelated;
    }

    @Override
    public PlanRelated updateImpl(com.ext.portlet.model.PlanRelated planRelated)
        throws SystemException {
        planRelated = toUnwrappedModel(planRelated);

        boolean isNew = planRelated.isNew();

        PlanRelatedModelImpl planRelatedModelImpl = (PlanRelatedModelImpl) planRelated;

        Session session = null;

        try {
            session = openSession();

            if (planRelated.isNew()) {
                session.save(planRelated);

                planRelated.setNew(false);
            } else {
                session.merge(planRelated);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PlanRelatedModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((planRelatedModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        planRelatedModelImpl.getOriginalRelatedPlanId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID,
                    args);

                args = new Object[] { planRelatedModelImpl.getRelatedPlanId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID,
                    args);
            }
        }

        EntityCacheUtil.putResult(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedImpl.class, planRelated.getPrimaryKey(), planRelated);

        return planRelated;
    }

    protected PlanRelated toUnwrappedModel(PlanRelated planRelated) {
        if (planRelated instanceof PlanRelatedImpl) {
            return planRelated;
        }

        PlanRelatedImpl planRelatedImpl = new PlanRelatedImpl();

        planRelatedImpl.setNew(planRelated.isNew());
        planRelatedImpl.setPrimaryKey(planRelated.getPrimaryKey());

        planRelatedImpl.setSectionId(planRelated.getSectionId());
        planRelatedImpl.setRelatedPlanId(planRelated.getRelatedPlanId());

        return planRelatedImpl;
    }

    /**
     * Returns the plan related with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan related
     * @return the plan related
     * @throws com.ext.portlet.NoSuchPlanRelatedException if a plan related with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanRelated findByPrimaryKey(Serializable primaryKey)
        throws NoSuchPlanRelatedException, SystemException {
        PlanRelated planRelated = fetchByPrimaryKey(primaryKey);

        if (planRelated == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchPlanRelatedException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return planRelated;
    }

    /**
     * Returns the plan related with the primary key or throws a {@link com.ext.portlet.NoSuchPlanRelatedException} if it could not be found.
     *
     * @param planRelatedPK the primary key of the plan related
     * @return the plan related
     * @throws com.ext.portlet.NoSuchPlanRelatedException if a plan related with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanRelated findByPrimaryKey(PlanRelatedPK planRelatedPK)
        throws NoSuchPlanRelatedException, SystemException {
        return findByPrimaryKey((Serializable) planRelatedPK);
    }

    /**
     * Returns the plan related with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan related
     * @return the plan related, or <code>null</code> if a plan related with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanRelated fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        PlanRelated planRelated = (PlanRelated) EntityCacheUtil.getResult(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
                PlanRelatedImpl.class, primaryKey);

        if (planRelated == _nullPlanRelated) {
            return null;
        }

        if (planRelated == null) {
            Session session = null;

            try {
                session = openSession();

                planRelated = (PlanRelated) session.get(PlanRelatedImpl.class,
                        primaryKey);

                if (planRelated != null) {
                    cacheResult(planRelated);
                } else {
                    EntityCacheUtil.putResult(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
                        PlanRelatedImpl.class, primaryKey, _nullPlanRelated);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
                    PlanRelatedImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return planRelated;
    }

    /**
     * Returns the plan related with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param planRelatedPK the primary key of the plan related
     * @return the plan related, or <code>null</code> if a plan related with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanRelated fetchByPrimaryKey(PlanRelatedPK planRelatedPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) planRelatedPK);
    }

    /**
     * Returns all the plan relateds.
     *
     * @return the plan relateds
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanRelated> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan relateds.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan relateds
     * @param end the upper bound of the range of plan relateds (not inclusive)
     * @return the range of plan relateds
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanRelated> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan relateds.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan relateds
     * @param end the upper bound of the range of plan relateds (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan relateds
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanRelated> findAll(int start, int end,
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

        List<PlanRelated> list = (List<PlanRelated>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANRELATED);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANRELATED;

                if (pagination) {
                    sql = sql.concat(PlanRelatedModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<PlanRelated>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PlanRelated>(list);
                } else {
                    list = (List<PlanRelated>) QueryUtil.list(q, getDialect(),
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
     * Removes all the plan relateds from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (PlanRelated planRelated : findAll()) {
            remove(planRelated);
        }
    }

    /**
     * Returns the number of plan relateds.
     *
     * @return the number of plan relateds
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

                Query q = session.createQuery(_SQL_COUNT_PLANRELATED);

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
     * Initializes the plan related persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.PlanRelated")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanRelated>> listenersList = new ArrayList<ModelListener<PlanRelated>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanRelated>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanRelatedImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
