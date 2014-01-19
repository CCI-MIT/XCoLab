package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPlanDescriptionException;
import com.ext.portlet.model.PlanDescription;
import com.ext.portlet.model.impl.PlanDescriptionImpl;
import com.ext.portlet.model.impl.PlanDescriptionModelImpl;
import com.ext.portlet.service.persistence.PlanDescriptionPersistence;

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
 * The persistence implementation for the plan description service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanDescriptionPersistence
 * @see PlanDescriptionUtil
 * @generated
 */
public class PlanDescriptionPersistenceImpl extends BasePersistenceImpl<PlanDescription>
    implements PlanDescriptionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanDescriptionUtil} to access the plan description persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanDescriptionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
            PlanDescriptionModelImpl.FINDER_CACHE_ENABLED,
            PlanDescriptionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
            PlanDescriptionModelImpl.FINDER_CACHE_ENABLED,
            PlanDescriptionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
            PlanDescriptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_FETCH_BY_CURRENTBYPLANID = new FinderPath(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
            PlanDescriptionModelImpl.FINDER_CACHE_ENABLED,
            PlanDescriptionImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByCurrentByPlanId", new String[] { Long.class.getName() },
            PlanDescriptionModelImpl.PLANID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CURRENTBYPLANID = new FinderPath(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
            PlanDescriptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByCurrentByPlanId", new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_CURRENTBYPLANID_PLANID_2 = "planDescription.planId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PLANIDPLANVERSION =
        new FinderPath(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
            PlanDescriptionModelImpl.FINDER_CACHE_ENABLED,
            PlanDescriptionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByPlanIdPlanVersion",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_COUNT_BY_PLANIDPLANVERSION =
        new FinderPath(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
            PlanDescriptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "countByPlanIdPlanVersion",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _FINDER_COLUMN_PLANIDPLANVERSION_PLANID_2 = "planDescription.planId = ? AND ";
    private static final String _FINDER_COLUMN_PLANIDPLANVERSION_PLANVERSION_2 = "planDescription.planVersion <= ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ALLBYPLANID =
        new FinderPath(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
            PlanDescriptionModelImpl.FINDER_CACHE_ENABLED,
            PlanDescriptionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByAllByPlanId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALLBYPLANID =
        new FinderPath(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
            PlanDescriptionModelImpl.FINDER_CACHE_ENABLED,
            PlanDescriptionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAllByPlanId",
            new String[] { Long.class.getName() },
            PlanDescriptionModelImpl.PLANID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ALLBYPLANID = new FinderPath(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
            PlanDescriptionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAllByPlanId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_ALLBYPLANID_PLANID_2 = "planDescription.planId = ?";
    private static final String _SQL_SELECT_PLANDESCRIPTION = "SELECT planDescription FROM PlanDescription planDescription";
    private static final String _SQL_SELECT_PLANDESCRIPTION_WHERE = "SELECT planDescription FROM PlanDescription planDescription WHERE ";
    private static final String _SQL_COUNT_PLANDESCRIPTION = "SELECT COUNT(planDescription) FROM PlanDescription planDescription";
    private static final String _SQL_COUNT_PLANDESCRIPTION_WHERE = "SELECT COUNT(planDescription) FROM PlanDescription planDescription WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planDescription.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanDescription exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PlanDescription exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanDescriptionPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static PlanDescription _nullPlanDescription = new PlanDescriptionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanDescription> toCacheModel() {
                return _nullPlanDescriptionCacheModel;
            }
        };

    private static CacheModel<PlanDescription> _nullPlanDescriptionCacheModel = new CacheModel<PlanDescription>() {
            @Override
            public PlanDescription toEntityModel() {
                return _nullPlanDescription;
            }
        };

    public PlanDescriptionPersistenceImpl() {
        setModelClass(PlanDescription.class);
    }

    /**
     * Returns the plan description where planId = &#63; or throws a {@link com.ext.portlet.NoSuchPlanDescriptionException} if it could not be found.
     *
     * @param planId the plan ID
     * @return the matching plan description
     * @throws com.ext.portlet.NoSuchPlanDescriptionException if a matching plan description could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanDescription findByCurrentByPlanId(long planId)
        throws NoSuchPlanDescriptionException, SystemException {
        PlanDescription planDescription = fetchByCurrentByPlanId(planId);

        if (planDescription == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("planId=");
            msg.append(planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanDescriptionException(msg.toString());
        }

        return planDescription;
    }

    /**
     * Returns the plan description where planId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param planId the plan ID
     * @return the matching plan description, or <code>null</code> if a matching plan description could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanDescription fetchByCurrentByPlanId(long planId)
        throws SystemException {
        return fetchByCurrentByPlanId(planId, true);
    }

    /**
     * Returns the plan description where planId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param planId the plan ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching plan description, or <code>null</code> if a matching plan description could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanDescription fetchByCurrentByPlanId(long planId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { planId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                    finderArgs, this);
        }

        if (result instanceof PlanDescription) {
            PlanDescription planDescription = (PlanDescription) result;

            if ((planId != planDescription.getPlanId())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_PLANDESCRIPTION_WHERE);

            query.append(_FINDER_COLUMN_CURRENTBYPLANID_PLANID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planId);

                List<PlanDescription> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "PlanDescriptionPersistenceImpl.fetchByCurrentByPlanId(long, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    PlanDescription planDescription = list.get(0);

                    result = planDescription;

                    cacheResult(planDescription);

                    if ((planDescription.getPlanId() != planId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                            finderArgs, planDescription);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (PlanDescription) result;
        }
    }

    /**
     * Removes the plan description where planId = &#63; from the database.
     *
     * @param planId the plan ID
     * @return the plan description that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanDescription removeByCurrentByPlanId(long planId)
        throws NoSuchPlanDescriptionException, SystemException {
        PlanDescription planDescription = findByCurrentByPlanId(planId);

        return remove(planDescription);
    }

    /**
     * Returns the number of plan descriptions where planId = &#63;.
     *
     * @param planId the plan ID
     * @return the number of matching plan descriptions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCurrentByPlanId(long planId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CURRENTBYPLANID;

        Object[] finderArgs = new Object[] { planId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PLANDESCRIPTION_WHERE);

            query.append(_FINDER_COLUMN_CURRENTBYPLANID_PLANID_2);

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
     * Returns all the plan descriptions where planId = &#63; and planVersion &le; &#63;.
     *
     * @param planId the plan ID
     * @param planVersion the plan version
     * @return the matching plan descriptions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanDescription> findByPlanIdPlanVersion(long planId,
        long planVersion) throws SystemException {
        return findByPlanIdPlanVersion(planId, planVersion, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan descriptions where planId = &#63; and planVersion &le; &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanDescriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param planId the plan ID
     * @param planVersion the plan version
     * @param start the lower bound of the range of plan descriptions
     * @param end the upper bound of the range of plan descriptions (not inclusive)
     * @return the range of matching plan descriptions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanDescription> findByPlanIdPlanVersion(long planId,
        long planVersion, int start, int end) throws SystemException {
        return findByPlanIdPlanVersion(planId, planVersion, start, end, null);
    }

    /**
     * Returns an ordered range of all the plan descriptions where planId = &#63; and planVersion &le; &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanDescriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param planId the plan ID
     * @param planVersion the plan version
     * @param start the lower bound of the range of plan descriptions
     * @param end the upper bound of the range of plan descriptions (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching plan descriptions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanDescription> findByPlanIdPlanVersion(long planId,
        long planVersion, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PLANIDPLANVERSION;
        finderArgs = new Object[] {
                planId, planVersion,
                
                start, end, orderByComparator
            };

        List<PlanDescription> list = (List<PlanDescription>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (PlanDescription planDescription : list) {
                if ((planId != planDescription.getPlanId()) ||
                        (planVersion < planDescription.getPlanVersion())) {
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

            query.append(_SQL_SELECT_PLANDESCRIPTION_WHERE);

            query.append(_FINDER_COLUMN_PLANIDPLANVERSION_PLANID_2);

            query.append(_FINDER_COLUMN_PLANIDPLANVERSION_PLANVERSION_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(PlanDescriptionModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planId);

                qPos.add(planVersion);

                if (!pagination) {
                    list = (List<PlanDescription>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PlanDescription>(list);
                } else {
                    list = (List<PlanDescription>) QueryUtil.list(q,
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
     * Returns the first plan description in the ordered set where planId = &#63; and planVersion &le; &#63;.
     *
     * @param planId the plan ID
     * @param planVersion the plan version
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan description
     * @throws com.ext.portlet.NoSuchPlanDescriptionException if a matching plan description could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanDescription findByPlanIdPlanVersion_First(long planId,
        long planVersion, OrderByComparator orderByComparator)
        throws NoSuchPlanDescriptionException, SystemException {
        PlanDescription planDescription = fetchByPlanIdPlanVersion_First(planId,
                planVersion, orderByComparator);

        if (planDescription != null) {
            return planDescription;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("planId=");
        msg.append(planId);

        msg.append(", planVersion=");
        msg.append(planVersion);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPlanDescriptionException(msg.toString());
    }

    /**
     * Returns the first plan description in the ordered set where planId = &#63; and planVersion &le; &#63;.
     *
     * @param planId the plan ID
     * @param planVersion the plan version
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan description, or <code>null</code> if a matching plan description could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanDescription fetchByPlanIdPlanVersion_First(long planId,
        long planVersion, OrderByComparator orderByComparator)
        throws SystemException {
        List<PlanDescription> list = findByPlanIdPlanVersion(planId,
                planVersion, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last plan description in the ordered set where planId = &#63; and planVersion &le; &#63;.
     *
     * @param planId the plan ID
     * @param planVersion the plan version
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan description
     * @throws com.ext.portlet.NoSuchPlanDescriptionException if a matching plan description could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanDescription findByPlanIdPlanVersion_Last(long planId,
        long planVersion, OrderByComparator orderByComparator)
        throws NoSuchPlanDescriptionException, SystemException {
        PlanDescription planDescription = fetchByPlanIdPlanVersion_Last(planId,
                planVersion, orderByComparator);

        if (planDescription != null) {
            return planDescription;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("planId=");
        msg.append(planId);

        msg.append(", planVersion=");
        msg.append(planVersion);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPlanDescriptionException(msg.toString());
    }

    /**
     * Returns the last plan description in the ordered set where planId = &#63; and planVersion &le; &#63;.
     *
     * @param planId the plan ID
     * @param planVersion the plan version
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan description, or <code>null</code> if a matching plan description could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanDescription fetchByPlanIdPlanVersion_Last(long planId,
        long planVersion, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByPlanIdPlanVersion(planId, planVersion);

        if (count == 0) {
            return null;
        }

        List<PlanDescription> list = findByPlanIdPlanVersion(planId,
                planVersion, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the plan descriptions before and after the current plan description in the ordered set where planId = &#63; and planVersion &le; &#63;.
     *
     * @param id the primary key of the current plan description
     * @param planId the plan ID
     * @param planVersion the plan version
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next plan description
     * @throws com.ext.portlet.NoSuchPlanDescriptionException if a plan description with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanDescription[] findByPlanIdPlanVersion_PrevAndNext(long id,
        long planId, long planVersion, OrderByComparator orderByComparator)
        throws NoSuchPlanDescriptionException, SystemException {
        PlanDescription planDescription = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            PlanDescription[] array = new PlanDescriptionImpl[3];

            array[0] = getByPlanIdPlanVersion_PrevAndNext(session,
                    planDescription, planId, planVersion, orderByComparator,
                    true);

            array[1] = planDescription;

            array[2] = getByPlanIdPlanVersion_PrevAndNext(session,
                    planDescription, planId, planVersion, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PlanDescription getByPlanIdPlanVersion_PrevAndNext(
        Session session, PlanDescription planDescription, long planId,
        long planVersion, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PLANDESCRIPTION_WHERE);

        query.append(_FINDER_COLUMN_PLANIDPLANVERSION_PLANID_2);

        query.append(_FINDER_COLUMN_PLANIDPLANVERSION_PLANVERSION_2);

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
            query.append(PlanDescriptionModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(planId);

        qPos.add(planVersion);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(planDescription);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PlanDescription> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the plan descriptions where planId = &#63; and planVersion &le; &#63; from the database.
     *
     * @param planId the plan ID
     * @param planVersion the plan version
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByPlanIdPlanVersion(long planId, long planVersion)
        throws SystemException {
        for (PlanDescription planDescription : findByPlanIdPlanVersion(planId,
                planVersion, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(planDescription);
        }
    }

    /**
     * Returns the number of plan descriptions where planId = &#63; and planVersion &le; &#63;.
     *
     * @param planId the plan ID
     * @param planVersion the plan version
     * @return the number of matching plan descriptions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByPlanIdPlanVersion(long planId, long planVersion)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_WITH_PAGINATION_COUNT_BY_PLANIDPLANVERSION;

        Object[] finderArgs = new Object[] { planId, planVersion };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PLANDESCRIPTION_WHERE);

            query.append(_FINDER_COLUMN_PLANIDPLANVERSION_PLANID_2);

            query.append(_FINDER_COLUMN_PLANIDPLANVERSION_PLANVERSION_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planId);

                qPos.add(planVersion);

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
     * Returns all the plan descriptions where planId = &#63;.
     *
     * @param planId the plan ID
     * @return the matching plan descriptions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanDescription> findByAllByPlanId(long planId)
        throws SystemException {
        return findByAllByPlanId(planId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the plan descriptions where planId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanDescriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param planId the plan ID
     * @param start the lower bound of the range of plan descriptions
     * @param end the upper bound of the range of plan descriptions (not inclusive)
     * @return the range of matching plan descriptions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanDescription> findByAllByPlanId(long planId, int start,
        int end) throws SystemException {
        return findByAllByPlanId(planId, start, end, null);
    }

    /**
     * Returns an ordered range of all the plan descriptions where planId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanDescriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param planId the plan ID
     * @param start the lower bound of the range of plan descriptions
     * @param end the upper bound of the range of plan descriptions (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching plan descriptions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanDescription> findByAllByPlanId(long planId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALLBYPLANID;
            finderArgs = new Object[] { planId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ALLBYPLANID;
            finderArgs = new Object[] { planId, start, end, orderByComparator };
        }

        List<PlanDescription> list = (List<PlanDescription>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (PlanDescription planDescription : list) {
                if ((planId != planDescription.getPlanId())) {
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

            query.append(_SQL_SELECT_PLANDESCRIPTION_WHERE);

            query.append(_FINDER_COLUMN_ALLBYPLANID_PLANID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(PlanDescriptionModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planId);

                if (!pagination) {
                    list = (List<PlanDescription>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PlanDescription>(list);
                } else {
                    list = (List<PlanDescription>) QueryUtil.list(q,
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
     * Returns the first plan description in the ordered set where planId = &#63;.
     *
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan description
     * @throws com.ext.portlet.NoSuchPlanDescriptionException if a matching plan description could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanDescription findByAllByPlanId_First(long planId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanDescriptionException, SystemException {
        PlanDescription planDescription = fetchByAllByPlanId_First(planId,
                orderByComparator);

        if (planDescription != null) {
            return planDescription;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("planId=");
        msg.append(planId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPlanDescriptionException(msg.toString());
    }

    /**
     * Returns the first plan description in the ordered set where planId = &#63;.
     *
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan description, or <code>null</code> if a matching plan description could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanDescription fetchByAllByPlanId_First(long planId,
        OrderByComparator orderByComparator) throws SystemException {
        List<PlanDescription> list = findByAllByPlanId(planId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last plan description in the ordered set where planId = &#63;.
     *
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan description
     * @throws com.ext.portlet.NoSuchPlanDescriptionException if a matching plan description could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanDescription findByAllByPlanId_Last(long planId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanDescriptionException, SystemException {
        PlanDescription planDescription = fetchByAllByPlanId_Last(planId,
                orderByComparator);

        if (planDescription != null) {
            return planDescription;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("planId=");
        msg.append(planId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPlanDescriptionException(msg.toString());
    }

    /**
     * Returns the last plan description in the ordered set where planId = &#63;.
     *
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan description, or <code>null</code> if a matching plan description could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanDescription fetchByAllByPlanId_Last(long planId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByAllByPlanId(planId);

        if (count == 0) {
            return null;
        }

        List<PlanDescription> list = findByAllByPlanId(planId, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the plan descriptions before and after the current plan description in the ordered set where planId = &#63;.
     *
     * @param id the primary key of the current plan description
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next plan description
     * @throws com.ext.portlet.NoSuchPlanDescriptionException if a plan description with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanDescription[] findByAllByPlanId_PrevAndNext(long id,
        long planId, OrderByComparator orderByComparator)
        throws NoSuchPlanDescriptionException, SystemException {
        PlanDescription planDescription = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            PlanDescription[] array = new PlanDescriptionImpl[3];

            array[0] = getByAllByPlanId_PrevAndNext(session, planDescription,
                    planId, orderByComparator, true);

            array[1] = planDescription;

            array[2] = getByAllByPlanId_PrevAndNext(session, planDescription,
                    planId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PlanDescription getByAllByPlanId_PrevAndNext(Session session,
        PlanDescription planDescription, long planId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PLANDESCRIPTION_WHERE);

        query.append(_FINDER_COLUMN_ALLBYPLANID_PLANID_2);

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
            query.append(PlanDescriptionModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(planId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(planDescription);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PlanDescription> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the plan descriptions where planId = &#63; from the database.
     *
     * @param planId the plan ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByAllByPlanId(long planId) throws SystemException {
        for (PlanDescription planDescription : findByAllByPlanId(planId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(planDescription);
        }
    }

    /**
     * Returns the number of plan descriptions where planId = &#63;.
     *
     * @param planId the plan ID
     * @return the number of matching plan descriptions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByAllByPlanId(long planId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ALLBYPLANID;

        Object[] finderArgs = new Object[] { planId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PLANDESCRIPTION_WHERE);

            query.append(_FINDER_COLUMN_ALLBYPLANID_PLANID_2);

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
     * Caches the plan description in the entity cache if it is enabled.
     *
     * @param planDescription the plan description
     */
    @Override
    public void cacheResult(PlanDescription planDescription) {
        EntityCacheUtil.putResult(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
            PlanDescriptionImpl.class, planDescription.getPrimaryKey(),
            planDescription);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
            new Object[] { planDescription.getPlanId() }, planDescription);

        planDescription.resetOriginalValues();
    }

    /**
     * Caches the plan descriptions in the entity cache if it is enabled.
     *
     * @param planDescriptions the plan descriptions
     */
    @Override
    public void cacheResult(List<PlanDescription> planDescriptions) {
        for (PlanDescription planDescription : planDescriptions) {
            if (EntityCacheUtil.getResult(
                        PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
                        PlanDescriptionImpl.class,
                        planDescription.getPrimaryKey()) == null) {
                cacheResult(planDescription);
            } else {
                planDescription.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan descriptions.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanDescriptionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanDescriptionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan description.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanDescription planDescription) {
        EntityCacheUtil.removeResult(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
            PlanDescriptionImpl.class, planDescription.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(planDescription);
    }

    @Override
    public void clearCache(List<PlanDescription> planDescriptions) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanDescription planDescription : planDescriptions) {
            EntityCacheUtil.removeResult(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
                PlanDescriptionImpl.class, planDescription.getPrimaryKey());

            clearUniqueFindersCache(planDescription);
        }
    }

    protected void cacheUniqueFindersCache(PlanDescription planDescription) {
        if (planDescription.isNew()) {
            Object[] args = new Object[] { planDescription.getPlanId() };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CURRENTBYPLANID,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                args, planDescription);
        } else {
            PlanDescriptionModelImpl planDescriptionModelImpl = (PlanDescriptionModelImpl) planDescription;

            if ((planDescriptionModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_CURRENTBYPLANID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] { planDescription.getPlanId() };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CURRENTBYPLANID,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                    args, planDescription);
            }
        }
    }

    protected void clearUniqueFindersCache(PlanDescription planDescription) {
        PlanDescriptionModelImpl planDescriptionModelImpl = (PlanDescriptionModelImpl) planDescription;

        Object[] args = new Object[] { planDescription.getPlanId() };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CURRENTBYPLANID, args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID, args);

        if ((planDescriptionModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_CURRENTBYPLANID.getColumnBitmask()) != 0) {
            args = new Object[] { planDescriptionModelImpl.getOriginalPlanId() };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CURRENTBYPLANID,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                args);
        }
    }

    /**
     * Creates a new plan description with the primary key. Does not add the plan description to the database.
     *
     * @param id the primary key for the new plan description
     * @return the new plan description
     */
    @Override
    public PlanDescription create(long id) {
        PlanDescription planDescription = new PlanDescriptionImpl();

        planDescription.setNew(true);
        planDescription.setPrimaryKey(id);

        return planDescription;
    }

    /**
     * Removes the plan description with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the plan description
     * @return the plan description that was removed
     * @throws com.ext.portlet.NoSuchPlanDescriptionException if a plan description with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanDescription remove(long id)
        throws NoSuchPlanDescriptionException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the plan description with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan description
     * @return the plan description that was removed
     * @throws com.ext.portlet.NoSuchPlanDescriptionException if a plan description with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanDescription remove(Serializable primaryKey)
        throws NoSuchPlanDescriptionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanDescription planDescription = (PlanDescription) session.get(PlanDescriptionImpl.class,
                    primaryKey);

            if (planDescription == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanDescriptionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planDescription);
        } catch (NoSuchPlanDescriptionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanDescription removeImpl(PlanDescription planDescription)
        throws SystemException {
        planDescription = toUnwrappedModel(planDescription);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(planDescription)) {
                planDescription = (PlanDescription) session.get(PlanDescriptionImpl.class,
                        planDescription.getPrimaryKeyObj());
            }

            if (planDescription != null) {
                session.delete(planDescription);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (planDescription != null) {
            clearCache(planDescription);
        }

        return planDescription;
    }

    @Override
    public PlanDescription updateImpl(
        com.ext.portlet.model.PlanDescription planDescription)
        throws SystemException {
        planDescription = toUnwrappedModel(planDescription);

        boolean isNew = planDescription.isNew();

        PlanDescriptionModelImpl planDescriptionModelImpl = (PlanDescriptionModelImpl) planDescription;

        Session session = null;

        try {
            session = openSession();

            if (planDescription.isNew()) {
                session.save(planDescription);

                planDescription.setNew(false);
            } else {
                session.merge(planDescription);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PlanDescriptionModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((planDescriptionModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALLBYPLANID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        planDescriptionModelImpl.getOriginalPlanId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ALLBYPLANID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALLBYPLANID,
                    args);

                args = new Object[] { planDescriptionModelImpl.getPlanId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ALLBYPLANID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALLBYPLANID,
                    args);
            }
        }

        EntityCacheUtil.putResult(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
            PlanDescriptionImpl.class, planDescription.getPrimaryKey(),
            planDescription);

        clearUniqueFindersCache(planDescription);
        cacheUniqueFindersCache(planDescription);

        return planDescription;
    }

    protected PlanDescription toUnwrappedModel(PlanDescription planDescription) {
        if (planDescription instanceof PlanDescriptionImpl) {
            return planDescription;
        }

        PlanDescriptionImpl planDescriptionImpl = new PlanDescriptionImpl();

        planDescriptionImpl.setNew(planDescription.isNew());
        planDescriptionImpl.setPrimaryKey(planDescription.getPrimaryKey());

        planDescriptionImpl.setId(planDescription.getId());
        planDescriptionImpl.setPlanId(planDescription.getPlanId());
        planDescriptionImpl.setName(planDescription.getName());
        planDescriptionImpl.setDescription(planDescription.getDescription());
        planDescriptionImpl.setVersion(planDescription.getVersion());
        planDescriptionImpl.setPlanVersion(planDescription.getPlanVersion());
        planDescriptionImpl.setCreated(planDescription.getCreated());
        planDescriptionImpl.setUpdateAuthorId(planDescription.getUpdateAuthorId());
        planDescriptionImpl.setImage(planDescription.getImage());
        planDescriptionImpl.setPitch(planDescription.getPitch());

        return planDescriptionImpl;
    }

    /**
     * Returns the plan description with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan description
     * @return the plan description
     * @throws com.ext.portlet.NoSuchPlanDescriptionException if a plan description with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanDescription findByPrimaryKey(Serializable primaryKey)
        throws NoSuchPlanDescriptionException, SystemException {
        PlanDescription planDescription = fetchByPrimaryKey(primaryKey);

        if (planDescription == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchPlanDescriptionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return planDescription;
    }

    /**
     * Returns the plan description with the primary key or throws a {@link com.ext.portlet.NoSuchPlanDescriptionException} if it could not be found.
     *
     * @param id the primary key of the plan description
     * @return the plan description
     * @throws com.ext.portlet.NoSuchPlanDescriptionException if a plan description with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanDescription findByPrimaryKey(long id)
        throws NoSuchPlanDescriptionException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the plan description with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan description
     * @return the plan description, or <code>null</code> if a plan description with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanDescription fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        PlanDescription planDescription = (PlanDescription) EntityCacheUtil.getResult(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
                PlanDescriptionImpl.class, primaryKey);

        if (planDescription == _nullPlanDescription) {
            return null;
        }

        if (planDescription == null) {
            Session session = null;

            try {
                session = openSession();

                planDescription = (PlanDescription) session.get(PlanDescriptionImpl.class,
                        primaryKey);

                if (planDescription != null) {
                    cacheResult(planDescription);
                } else {
                    EntityCacheUtil.putResult(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
                        PlanDescriptionImpl.class, primaryKey,
                        _nullPlanDescription);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(PlanDescriptionModelImpl.ENTITY_CACHE_ENABLED,
                    PlanDescriptionImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return planDescription;
    }

    /**
     * Returns the plan description with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the plan description
     * @return the plan description, or <code>null</code> if a plan description with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanDescription fetchByPrimaryKey(long id) throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the plan descriptions.
     *
     * @return the plan descriptions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanDescription> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan descriptions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanDescriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan descriptions
     * @param end the upper bound of the range of plan descriptions (not inclusive)
     * @return the range of plan descriptions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanDescription> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan descriptions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanDescriptionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan descriptions
     * @param end the upper bound of the range of plan descriptions (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan descriptions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanDescription> findAll(int start, int end,
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

        List<PlanDescription> list = (List<PlanDescription>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANDESCRIPTION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANDESCRIPTION;

                if (pagination) {
                    sql = sql.concat(PlanDescriptionModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<PlanDescription>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PlanDescription>(list);
                } else {
                    list = (List<PlanDescription>) QueryUtil.list(q,
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
     * Removes all the plan descriptions from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (PlanDescription planDescription : findAll()) {
            remove(planDescription);
        }
    }

    /**
     * Returns the number of plan descriptions.
     *
     * @return the number of plan descriptions
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

                Query q = session.createQuery(_SQL_COUNT_PLANDESCRIPTION);

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
     * Initializes the plan description persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.PlanDescription")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanDescription>> listenersList = new ArrayList<ModelListener<PlanDescription>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanDescription>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanDescriptionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
