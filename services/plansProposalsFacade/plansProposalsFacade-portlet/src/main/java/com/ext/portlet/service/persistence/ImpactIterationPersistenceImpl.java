package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchImpactIterationException;
import com.ext.portlet.model.ImpactIteration;
import com.ext.portlet.model.impl.ImpactIterationImpl;
import com.ext.portlet.model.impl.ImpactIterationModelImpl;
import com.ext.portlet.service.persistence.ImpactIterationPersistence;

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
 * The persistence implementation for the impact iteration service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactIterationPersistence
 * @see ImpactIterationUtil
 * @generated
 */
public class ImpactIterationPersistenceImpl extends BasePersistenceImpl<ImpactIteration>
    implements ImpactIterationPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ImpactIterationUtil} to access the impact iteration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ImpactIterationImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImpactIterationModelImpl.ENTITY_CACHE_ENABLED,
            ImpactIterationModelImpl.FINDER_CACHE_ENABLED,
            ImpactIterationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImpactIterationModelImpl.ENTITY_CACHE_ENABLED,
            ImpactIterationModelImpl.FINDER_CACHE_ENABLED,
            ImpactIterationImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImpactIterationModelImpl.ENTITY_CACHE_ENABLED,
            ImpactIterationModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ITERATIONID =
        new FinderPath(ImpactIterationModelImpl.ENTITY_CACHE_ENABLED,
            ImpactIterationModelImpl.FINDER_CACHE_ENABLED,
            ImpactIterationImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByIterationId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITERATIONID =
        new FinderPath(ImpactIterationModelImpl.ENTITY_CACHE_ENABLED,
            ImpactIterationModelImpl.FINDER_CACHE_ENABLED,
            ImpactIterationImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByIterationId",
            new String[] { Long.class.getName() },
            ImpactIterationModelImpl.ITERATIONID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ITERATIONID = new FinderPath(ImpactIterationModelImpl.ENTITY_CACHE_ENABLED,
            ImpactIterationModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByIterationId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_ITERATIONID_ITERATIONID_2 = "impactIteration.id.iterationId = ?";
    private static final String _SQL_SELECT_IMPACTITERATION = "SELECT impactIteration FROM ImpactIteration impactIteration";
    private static final String _SQL_SELECT_IMPACTITERATION_WHERE = "SELECT impactIteration FROM ImpactIteration impactIteration WHERE ";
    private static final String _SQL_COUNT_IMPACTITERATION = "SELECT COUNT(impactIteration) FROM ImpactIteration impactIteration";
    private static final String _SQL_COUNT_IMPACTITERATION_WHERE = "SELECT COUNT(impactIteration) FROM ImpactIteration impactIteration WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "impactIteration.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImpactIteration exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ImpactIteration exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ImpactIterationPersistenceImpl.class);
    private static ImpactIteration _nullImpactIteration = new ImpactIterationImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ImpactIteration> toCacheModel() {
                return _nullImpactIterationCacheModel;
            }
        };

    private static CacheModel<ImpactIteration> _nullImpactIterationCacheModel = new CacheModel<ImpactIteration>() {
            @Override
            public ImpactIteration toEntityModel() {
                return _nullImpactIteration;
            }
        };

    public ImpactIterationPersistenceImpl() {
        setModelClass(ImpactIteration.class);
    }

    /**
     * Returns all the impact iterations where iterationId = &#63;.
     *
     * @param iterationId the iteration ID
     * @return the matching impact iterations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImpactIteration> findByIterationId(long iterationId)
        throws SystemException {
        return findByIterationId(iterationId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the impact iterations where iterationId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactIterationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param iterationId the iteration ID
     * @param start the lower bound of the range of impact iterations
     * @param end the upper bound of the range of impact iterations (not inclusive)
     * @return the range of matching impact iterations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImpactIteration> findByIterationId(long iterationId, int start,
        int end) throws SystemException {
        return findByIterationId(iterationId, start, end, null);
    }

    /**
     * Returns an ordered range of all the impact iterations where iterationId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactIterationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param iterationId the iteration ID
     * @param start the lower bound of the range of impact iterations
     * @param end the upper bound of the range of impact iterations (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching impact iterations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImpactIteration> findByIterationId(long iterationId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITERATIONID;
            finderArgs = new Object[] { iterationId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ITERATIONID;
            finderArgs = new Object[] { iterationId, start, end, orderByComparator };
        }

        List<ImpactIteration> list = (List<ImpactIteration>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ImpactIteration impactIteration : list) {
                if ((iterationId != impactIteration.getIterationId())) {
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

            query.append(_SQL_SELECT_IMPACTITERATION_WHERE);

            query.append(_FINDER_COLUMN_ITERATIONID_ITERATIONID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ImpactIterationModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(iterationId);

                if (!pagination) {
                    list = (List<ImpactIteration>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ImpactIteration>(list);
                } else {
                    list = (List<ImpactIteration>) QueryUtil.list(q,
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
     * Returns the first impact iteration in the ordered set where iterationId = &#63;.
     *
     * @param iterationId the iteration ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching impact iteration
     * @throws com.ext.portlet.NoSuchImpactIterationException if a matching impact iteration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactIteration findByIterationId_First(long iterationId,
        OrderByComparator orderByComparator)
        throws NoSuchImpactIterationException, SystemException {
        ImpactIteration impactIteration = fetchByIterationId_First(iterationId,
                orderByComparator);

        if (impactIteration != null) {
            return impactIteration;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("iterationId=");
        msg.append(iterationId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchImpactIterationException(msg.toString());
    }

    /**
     * Returns the first impact iteration in the ordered set where iterationId = &#63;.
     *
     * @param iterationId the iteration ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching impact iteration, or <code>null</code> if a matching impact iteration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactIteration fetchByIterationId_First(long iterationId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ImpactIteration> list = findByIterationId(iterationId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last impact iteration in the ordered set where iterationId = &#63;.
     *
     * @param iterationId the iteration ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching impact iteration
     * @throws com.ext.portlet.NoSuchImpactIterationException if a matching impact iteration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactIteration findByIterationId_Last(long iterationId,
        OrderByComparator orderByComparator)
        throws NoSuchImpactIterationException, SystemException {
        ImpactIteration impactIteration = fetchByIterationId_Last(iterationId,
                orderByComparator);

        if (impactIteration != null) {
            return impactIteration;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("iterationId=");
        msg.append(iterationId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchImpactIterationException(msg.toString());
    }

    /**
     * Returns the last impact iteration in the ordered set where iterationId = &#63;.
     *
     * @param iterationId the iteration ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching impact iteration, or <code>null</code> if a matching impact iteration could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactIteration fetchByIterationId_Last(long iterationId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByIterationId(iterationId);

        if (count == 0) {
            return null;
        }

        List<ImpactIteration> list = findByIterationId(iterationId, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the impact iterations before and after the current impact iteration in the ordered set where iterationId = &#63;.
     *
     * @param impactIterationPK the primary key of the current impact iteration
     * @param iterationId the iteration ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next impact iteration
     * @throws com.ext.portlet.NoSuchImpactIterationException if a impact iteration with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactIteration[] findByIterationId_PrevAndNext(
        ImpactIterationPK impactIterationPK, long iterationId,
        OrderByComparator orderByComparator)
        throws NoSuchImpactIterationException, SystemException {
        ImpactIteration impactIteration = findByPrimaryKey(impactIterationPK);

        Session session = null;

        try {
            session = openSession();

            ImpactIteration[] array = new ImpactIterationImpl[3];

            array[0] = getByIterationId_PrevAndNext(session, impactIteration,
                    iterationId, orderByComparator, true);

            array[1] = impactIteration;

            array[2] = getByIterationId_PrevAndNext(session, impactIteration,
                    iterationId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ImpactIteration getByIterationId_PrevAndNext(Session session,
        ImpactIteration impactIteration, long iterationId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_IMPACTITERATION_WHERE);

        query.append(_FINDER_COLUMN_ITERATIONID_ITERATIONID_2);

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
            query.append(ImpactIterationModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(iterationId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(impactIteration);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ImpactIteration> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the impact iterations where iterationId = &#63; from the database.
     *
     * @param iterationId the iteration ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByIterationId(long iterationId) throws SystemException {
        for (ImpactIteration impactIteration : findByIterationId(iterationId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(impactIteration);
        }
    }

    /**
     * Returns the number of impact iterations where iterationId = &#63;.
     *
     * @param iterationId the iteration ID
     * @return the number of matching impact iterations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByIterationId(long iterationId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ITERATIONID;

        Object[] finderArgs = new Object[] { iterationId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_IMPACTITERATION_WHERE);

            query.append(_FINDER_COLUMN_ITERATIONID_ITERATIONID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(iterationId);

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
     * Caches the impact iteration in the entity cache if it is enabled.
     *
     * @param impactIteration the impact iteration
     */
    @Override
    public void cacheResult(ImpactIteration impactIteration) {
        EntityCacheUtil.putResult(ImpactIterationModelImpl.ENTITY_CACHE_ENABLED,
            ImpactIterationImpl.class, impactIteration.getPrimaryKey(),
            impactIteration);

        impactIteration.resetOriginalValues();
    }

    /**
     * Caches the impact iterations in the entity cache if it is enabled.
     *
     * @param impactIterations the impact iterations
     */
    @Override
    public void cacheResult(List<ImpactIteration> impactIterations) {
        for (ImpactIteration impactIteration : impactIterations) {
            if (EntityCacheUtil.getResult(
                        ImpactIterationModelImpl.ENTITY_CACHE_ENABLED,
                        ImpactIterationImpl.class,
                        impactIteration.getPrimaryKey()) == null) {
                cacheResult(impactIteration);
            } else {
                impactIteration.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all impact iterations.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ImpactIterationImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ImpactIterationImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the impact iteration.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ImpactIteration impactIteration) {
        EntityCacheUtil.removeResult(ImpactIterationModelImpl.ENTITY_CACHE_ENABLED,
            ImpactIterationImpl.class, impactIteration.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ImpactIteration> impactIterations) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ImpactIteration impactIteration : impactIterations) {
            EntityCacheUtil.removeResult(ImpactIterationModelImpl.ENTITY_CACHE_ENABLED,
                ImpactIterationImpl.class, impactIteration.getPrimaryKey());
        }
    }

    /**
     * Creates a new impact iteration with the primary key. Does not add the impact iteration to the database.
     *
     * @param impactIterationPK the primary key for the new impact iteration
     * @return the new impact iteration
     */
    @Override
    public ImpactIteration create(ImpactIterationPK impactIterationPK) {
        ImpactIteration impactIteration = new ImpactIterationImpl();

        impactIteration.setNew(true);
        impactIteration.setPrimaryKey(impactIterationPK);

        return impactIteration;
    }

    /**
     * Removes the impact iteration with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param impactIterationPK the primary key of the impact iteration
     * @return the impact iteration that was removed
     * @throws com.ext.portlet.NoSuchImpactIterationException if a impact iteration with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactIteration remove(ImpactIterationPK impactIterationPK)
        throws NoSuchImpactIterationException, SystemException {
        return remove((Serializable) impactIterationPK);
    }

    /**
     * Removes the impact iteration with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the impact iteration
     * @return the impact iteration that was removed
     * @throws com.ext.portlet.NoSuchImpactIterationException if a impact iteration with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactIteration remove(Serializable primaryKey)
        throws NoSuchImpactIterationException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ImpactIteration impactIteration = (ImpactIteration) session.get(ImpactIterationImpl.class,
                    primaryKey);

            if (impactIteration == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchImpactIterationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(impactIteration);
        } catch (NoSuchImpactIterationException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ImpactIteration removeImpl(ImpactIteration impactIteration)
        throws SystemException {
        impactIteration = toUnwrappedModel(impactIteration);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(impactIteration)) {
                impactIteration = (ImpactIteration) session.get(ImpactIterationImpl.class,
                        impactIteration.getPrimaryKeyObj());
            }

            if (impactIteration != null) {
                session.delete(impactIteration);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (impactIteration != null) {
            clearCache(impactIteration);
        }

        return impactIteration;
    }

    @Override
    public ImpactIteration updateImpl(
        com.ext.portlet.model.ImpactIteration impactIteration)
        throws SystemException {
        impactIteration = toUnwrappedModel(impactIteration);

        boolean isNew = impactIteration.isNew();

        ImpactIterationModelImpl impactIterationModelImpl = (ImpactIterationModelImpl) impactIteration;

        Session session = null;

        try {
            session = openSession();

            if (impactIteration.isNew()) {
                session.save(impactIteration);

                impactIteration.setNew(false);
            } else {
                session.merge(impactIteration);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ImpactIterationModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((impactIterationModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITERATIONID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        impactIterationModelImpl.getOriginalIterationId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITERATIONID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITERATIONID,
                    args);

                args = new Object[] { impactIterationModelImpl.getIterationId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ITERATIONID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ITERATIONID,
                    args);
            }
        }

        EntityCacheUtil.putResult(ImpactIterationModelImpl.ENTITY_CACHE_ENABLED,
            ImpactIterationImpl.class, impactIteration.getPrimaryKey(),
            impactIteration);

        return impactIteration;
    }

    protected ImpactIteration toUnwrappedModel(ImpactIteration impactIteration) {
        if (impactIteration instanceof ImpactIterationImpl) {
            return impactIteration;
        }

        ImpactIterationImpl impactIterationImpl = new ImpactIterationImpl();

        impactIterationImpl.setNew(impactIteration.isNew());
        impactIterationImpl.setPrimaryKey(impactIteration.getPrimaryKey());

        impactIterationImpl.setIterationId(impactIteration.getIterationId());
        impactIterationImpl.setYear(impactIteration.getYear());

        return impactIterationImpl;
    }

    /**
     * Returns the impact iteration with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the impact iteration
     * @return the impact iteration
     * @throws com.ext.portlet.NoSuchImpactIterationException if a impact iteration with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactIteration findByPrimaryKey(Serializable primaryKey)
        throws NoSuchImpactIterationException, SystemException {
        ImpactIteration impactIteration = fetchByPrimaryKey(primaryKey);

        if (impactIteration == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchImpactIterationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return impactIteration;
    }

    /**
     * Returns the impact iteration with the primary key or throws a {@link com.ext.portlet.NoSuchImpactIterationException} if it could not be found.
     *
     * @param impactIterationPK the primary key of the impact iteration
     * @return the impact iteration
     * @throws com.ext.portlet.NoSuchImpactIterationException if a impact iteration with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactIteration findByPrimaryKey(ImpactIterationPK impactIterationPK)
        throws NoSuchImpactIterationException, SystemException {
        return findByPrimaryKey((Serializable) impactIterationPK);
    }

    /**
     * Returns the impact iteration with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the impact iteration
     * @return the impact iteration, or <code>null</code> if a impact iteration with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactIteration fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ImpactIteration impactIteration = (ImpactIteration) EntityCacheUtil.getResult(ImpactIterationModelImpl.ENTITY_CACHE_ENABLED,
                ImpactIterationImpl.class, primaryKey);

        if (impactIteration == _nullImpactIteration) {
            return null;
        }

        if (impactIteration == null) {
            Session session = null;

            try {
                session = openSession();

                impactIteration = (ImpactIteration) session.get(ImpactIterationImpl.class,
                        primaryKey);

                if (impactIteration != null) {
                    cacheResult(impactIteration);
                } else {
                    EntityCacheUtil.putResult(ImpactIterationModelImpl.ENTITY_CACHE_ENABLED,
                        ImpactIterationImpl.class, primaryKey,
                        _nullImpactIteration);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ImpactIterationModelImpl.ENTITY_CACHE_ENABLED,
                    ImpactIterationImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return impactIteration;
    }

    /**
     * Returns the impact iteration with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param impactIterationPK the primary key of the impact iteration
     * @return the impact iteration, or <code>null</code> if a impact iteration with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactIteration fetchByPrimaryKey(
        ImpactIterationPK impactIterationPK) throws SystemException {
        return fetchByPrimaryKey((Serializable) impactIterationPK);
    }

    /**
     * Returns all the impact iterations.
     *
     * @return the impact iterations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImpactIteration> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the impact iterations.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactIterationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of impact iterations
     * @param end the upper bound of the range of impact iterations (not inclusive)
     * @return the range of impact iterations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImpactIteration> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the impact iterations.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactIterationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of impact iterations
     * @param end the upper bound of the range of impact iterations (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of impact iterations
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImpactIteration> findAll(int start, int end,
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

        List<ImpactIteration> list = (List<ImpactIteration>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IMPACTITERATION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IMPACTITERATION;

                if (pagination) {
                    sql = sql.concat(ImpactIterationModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ImpactIteration>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ImpactIteration>(list);
                } else {
                    list = (List<ImpactIteration>) QueryUtil.list(q,
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
     * Removes all the impact iterations from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ImpactIteration impactIteration : findAll()) {
            remove(impactIteration);
        }
    }

    /**
     * Returns the number of impact iterations.
     *
     * @return the number of impact iterations
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

                Query q = session.createQuery(_SQL_COUNT_IMPACTITERATION);

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
     * Initializes the impact iteration persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ImpactIteration")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ImpactIteration>> listenersList = new ArrayList<ModelListener<ImpactIteration>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ImpactIteration>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ImpactIterationImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
