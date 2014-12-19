package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPlanTypeColumnException;
import com.ext.portlet.model.PlanTypeColumn;
import com.ext.portlet.model.impl.PlanTypeColumnImpl;
import com.ext.portlet.model.impl.PlanTypeColumnModelImpl;
import com.ext.portlet.service.persistence.PlanTypeColumnPersistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
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
 * The persistence implementation for the plan type column service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypeColumnPersistence
 * @see PlanTypeColumnUtil
 * @generated
 */
public class PlanTypeColumnPersistenceImpl extends BasePersistenceImpl<PlanTypeColumn>
    implements PlanTypeColumnPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanTypeColumnUtil} to access the plan type column persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanTypeColumnImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeColumnModelImpl.FINDER_CACHE_ENABLED,
            PlanTypeColumnImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeColumnModelImpl.FINDER_CACHE_ENABLED,
            PlanTypeColumnImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeColumnModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PLANTYPECOLUMN = "SELECT planTypeColumn FROM PlanTypeColumn planTypeColumn";
    private static final String _SQL_COUNT_PLANTYPECOLUMN = "SELECT COUNT(planTypeColumn) FROM PlanTypeColumn planTypeColumn";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planTypeColumn.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanTypeColumn exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanTypeColumnPersistenceImpl.class);
    private static PlanTypeColumn _nullPlanTypeColumn = new PlanTypeColumnImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanTypeColumn> toCacheModel() {
                return _nullPlanTypeColumnCacheModel;
            }
        };

    private static CacheModel<PlanTypeColumn> _nullPlanTypeColumnCacheModel = new CacheModel<PlanTypeColumn>() {
            @Override
            public PlanTypeColumn toEntityModel() {
                return _nullPlanTypeColumn;
            }
        };

    public PlanTypeColumnPersistenceImpl() {
        setModelClass(PlanTypeColumn.class);
    }

    /**
     * Caches the plan type column in the entity cache if it is enabled.
     *
     * @param planTypeColumn the plan type column
     */
    @Override
    public void cacheResult(PlanTypeColumn planTypeColumn) {
        EntityCacheUtil.putResult(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeColumnImpl.class, planTypeColumn.getPrimaryKey(),
            planTypeColumn);

        planTypeColumn.resetOriginalValues();
    }

    /**
     * Caches the plan type columns in the entity cache if it is enabled.
     *
     * @param planTypeColumns the plan type columns
     */
    @Override
    public void cacheResult(List<PlanTypeColumn> planTypeColumns) {
        for (PlanTypeColumn planTypeColumn : planTypeColumns) {
            if (EntityCacheUtil.getResult(
                        PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
                        PlanTypeColumnImpl.class, planTypeColumn.getPrimaryKey()) == null) {
                cacheResult(planTypeColumn);
            } else {
                planTypeColumn.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan type columns.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanTypeColumnImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanTypeColumnImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan type column.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanTypeColumn planTypeColumn) {
        EntityCacheUtil.removeResult(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeColumnImpl.class, planTypeColumn.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<PlanTypeColumn> planTypeColumns) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanTypeColumn planTypeColumn : planTypeColumns) {
            EntityCacheUtil.removeResult(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
                PlanTypeColumnImpl.class, planTypeColumn.getPrimaryKey());
        }
    }

    /**
     * Creates a new plan type column with the primary key. Does not add the plan type column to the database.
     *
     * @param planTypeColumnId the primary key for the new plan type column
     * @return the new plan type column
     */
    @Override
    public PlanTypeColumn create(long planTypeColumnId) {
        PlanTypeColumn planTypeColumn = new PlanTypeColumnImpl();

        planTypeColumn.setNew(true);
        planTypeColumn.setPrimaryKey(planTypeColumnId);

        return planTypeColumn;
    }

    /**
     * Removes the plan type column with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param planTypeColumnId the primary key of the plan type column
     * @return the plan type column that was removed
     * @throws com.ext.portlet.NoSuchPlanTypeColumnException if a plan type column with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTypeColumn remove(long planTypeColumnId)
        throws NoSuchPlanTypeColumnException, SystemException {
        return remove((Serializable) planTypeColumnId);
    }

    /**
     * Removes the plan type column with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan type column
     * @return the plan type column that was removed
     * @throws com.ext.portlet.NoSuchPlanTypeColumnException if a plan type column with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTypeColumn remove(Serializable primaryKey)
        throws NoSuchPlanTypeColumnException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanTypeColumn planTypeColumn = (PlanTypeColumn) session.get(PlanTypeColumnImpl.class,
                    primaryKey);

            if (planTypeColumn == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanTypeColumnException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planTypeColumn);
        } catch (NoSuchPlanTypeColumnException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanTypeColumn removeImpl(PlanTypeColumn planTypeColumn)
        throws SystemException {
        planTypeColumn = toUnwrappedModel(planTypeColumn);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(planTypeColumn)) {
                planTypeColumn = (PlanTypeColumn) session.get(PlanTypeColumnImpl.class,
                        planTypeColumn.getPrimaryKeyObj());
            }

            if (planTypeColumn != null) {
                session.delete(planTypeColumn);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (planTypeColumn != null) {
            clearCache(planTypeColumn);
        }

        return planTypeColumn;
    }

    @Override
    public PlanTypeColumn updateImpl(
        com.ext.portlet.model.PlanTypeColumn planTypeColumn)
        throws SystemException {
        planTypeColumn = toUnwrappedModel(planTypeColumn);

        boolean isNew = planTypeColumn.isNew();

        Session session = null;

        try {
            session = openSession();

            if (planTypeColumn.isNew()) {
                session.save(planTypeColumn);

                planTypeColumn.setNew(false);
            } else {
                session.merge(planTypeColumn);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeColumnImpl.class, planTypeColumn.getPrimaryKey(),
            planTypeColumn);

        return planTypeColumn;
    }

    protected PlanTypeColumn toUnwrappedModel(PlanTypeColumn planTypeColumn) {
        if (planTypeColumn instanceof PlanTypeColumnImpl) {
            return planTypeColumn;
        }

        PlanTypeColumnImpl planTypeColumnImpl = new PlanTypeColumnImpl();

        planTypeColumnImpl.setNew(planTypeColumn.isNew());
        planTypeColumnImpl.setPrimaryKey(planTypeColumn.getPrimaryKey());

        planTypeColumnImpl.setPlanTypeColumnId(planTypeColumn.getPlanTypeColumnId());
        planTypeColumnImpl.setPlanTypeId(planTypeColumn.getPlanTypeId());
        planTypeColumnImpl.setWeight(planTypeColumn.getWeight());
        planTypeColumnImpl.setColumnName(planTypeColumn.getColumnName());
        planTypeColumnImpl.setVisibleByDefault(planTypeColumn.isVisibleByDefault());

        return planTypeColumnImpl;
    }

    /**
     * Returns the plan type column with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan type column
     * @return the plan type column
     * @throws com.ext.portlet.NoSuchPlanTypeColumnException if a plan type column with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTypeColumn findByPrimaryKey(Serializable primaryKey)
        throws NoSuchPlanTypeColumnException, SystemException {
        PlanTypeColumn planTypeColumn = fetchByPrimaryKey(primaryKey);

        if (planTypeColumn == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchPlanTypeColumnException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return planTypeColumn;
    }

    /**
     * Returns the plan type column with the primary key or throws a {@link com.ext.portlet.NoSuchPlanTypeColumnException} if it could not be found.
     *
     * @param planTypeColumnId the primary key of the plan type column
     * @return the plan type column
     * @throws com.ext.portlet.NoSuchPlanTypeColumnException if a plan type column with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTypeColumn findByPrimaryKey(long planTypeColumnId)
        throws NoSuchPlanTypeColumnException, SystemException {
        return findByPrimaryKey((Serializable) planTypeColumnId);
    }

    /**
     * Returns the plan type column with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan type column
     * @return the plan type column, or <code>null</code> if a plan type column with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTypeColumn fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        PlanTypeColumn planTypeColumn = (PlanTypeColumn) EntityCacheUtil.getResult(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
                PlanTypeColumnImpl.class, primaryKey);

        if (planTypeColumn == _nullPlanTypeColumn) {
            return null;
        }

        if (planTypeColumn == null) {
            Session session = null;

            try {
                session = openSession();

                planTypeColumn = (PlanTypeColumn) session.get(PlanTypeColumnImpl.class,
                        primaryKey);

                if (planTypeColumn != null) {
                    cacheResult(planTypeColumn);
                } else {
                    EntityCacheUtil.putResult(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
                        PlanTypeColumnImpl.class, primaryKey,
                        _nullPlanTypeColumn);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
                    PlanTypeColumnImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return planTypeColumn;
    }

    /**
     * Returns the plan type column with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param planTypeColumnId the primary key of the plan type column
     * @return the plan type column, or <code>null</code> if a plan type column with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTypeColumn fetchByPrimaryKey(long planTypeColumnId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) planTypeColumnId);
    }

    /**
     * Returns all the plan type columns.
     *
     * @return the plan type columns
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanTypeColumn> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan type columns.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanTypeColumnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan type columns
     * @param end the upper bound of the range of plan type columns (not inclusive)
     * @return the range of plan type columns
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanTypeColumn> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan type columns.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanTypeColumnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan type columns
     * @param end the upper bound of the range of plan type columns (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan type columns
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanTypeColumn> findAll(int start, int end,
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

        List<PlanTypeColumn> list = (List<PlanTypeColumn>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANTYPECOLUMN);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANTYPECOLUMN;

                if (pagination) {
                    sql = sql.concat(PlanTypeColumnModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<PlanTypeColumn>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PlanTypeColumn>(list);
                } else {
                    list = (List<PlanTypeColumn>) QueryUtil.list(q,
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
     * Removes all the plan type columns from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (PlanTypeColumn planTypeColumn : findAll()) {
            remove(planTypeColumn);
        }
    }

    /**
     * Returns the number of plan type columns.
     *
     * @return the number of plan type columns
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

                Query q = session.createQuery(_SQL_COUNT_PLANTYPECOLUMN);

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
     * Initializes the plan type column persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.PlanTypeColumn")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanTypeColumn>> listenersList = new ArrayList<ModelListener<PlanTypeColumn>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanTypeColumn>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanTypeColumnImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
