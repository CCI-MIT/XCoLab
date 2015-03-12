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
    private static final String _SQL_SELECT_IMPACTDEFAULTSERIES = "SELECT impactDefaultSeries FROM ImpactDefaultSeries impactDefaultSeries";
    private static final String _SQL_COUNT_IMPACTDEFAULTSERIES = "SELECT COUNT(impactDefaultSeries) FROM ImpactDefaultSeries impactDefaultSeries";
    private static final String _ORDER_BY_ENTITY_ALIAS = "impactDefaultSeries.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImpactDefaultSeries exists with the primary key ";
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
     * Caches the impact default series in the entity cache if it is enabled.
     *
     * @param impactDefaultSeries the impact default series
     */
    @Override
    public void cacheResult(ImpactDefaultSeries impactDefaultSeries) {
        EntityCacheUtil.putResult(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesImpl.class, impactDefaultSeries.getPrimaryKey(),
            impactDefaultSeries);

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
    }

    @Override
    public void clearCache(List<ImpactDefaultSeries> impactDefaultSerieses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ImpactDefaultSeries impactDefaultSeries : impactDefaultSerieses) {
            EntityCacheUtil.removeResult(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
                ImpactDefaultSeriesImpl.class,
                impactDefaultSeries.getPrimaryKey());
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

        if (isNew) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(ImpactDefaultSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactDefaultSeriesImpl.class, impactDefaultSeries.getPrimaryKey(),
            impactDefaultSeries);

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
