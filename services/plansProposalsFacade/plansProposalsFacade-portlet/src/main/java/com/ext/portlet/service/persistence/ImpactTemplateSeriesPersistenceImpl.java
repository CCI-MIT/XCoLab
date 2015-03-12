package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchImpactTemplateSeriesException;
import com.ext.portlet.model.ImpactTemplateSeries;
import com.ext.portlet.model.impl.ImpactTemplateSeriesImpl;
import com.ext.portlet.model.impl.ImpactTemplateSeriesModelImpl;
import com.ext.portlet.service.persistence.ImpactTemplateSeriesPersistence;

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
 * The persistence implementation for the impact template series service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactTemplateSeriesPersistence
 * @see ImpactTemplateSeriesUtil
 * @generated
 */
public class ImpactTemplateSeriesPersistenceImpl extends BasePersistenceImpl<ImpactTemplateSeries>
    implements ImpactTemplateSeriesPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ImpactTemplateSeriesUtil} to access the impact template series persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ImpactTemplateSeriesImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImpactTemplateSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactTemplateSeriesModelImpl.FINDER_CACHE_ENABLED,
            ImpactTemplateSeriesImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImpactTemplateSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactTemplateSeriesModelImpl.FINDER_CACHE_ENABLED,
            ImpactTemplateSeriesImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImpactTemplateSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactTemplateSeriesModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_IMPACTTEMPLATESERIES = "SELECT impactTemplateSeries FROM ImpactTemplateSeries impactTemplateSeries";
    private static final String _SQL_COUNT_IMPACTTEMPLATESERIES = "SELECT COUNT(impactTemplateSeries) FROM ImpactTemplateSeries impactTemplateSeries";
    private static final String _ORDER_BY_ENTITY_ALIAS = "impactTemplateSeries.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImpactTemplateSeries exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ImpactTemplateSeriesPersistenceImpl.class);
    private static ImpactTemplateSeries _nullImpactTemplateSeries = new ImpactTemplateSeriesImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ImpactTemplateSeries> toCacheModel() {
                return _nullImpactTemplateSeriesCacheModel;
            }
        };

    private static CacheModel<ImpactTemplateSeries> _nullImpactTemplateSeriesCacheModel =
        new CacheModel<ImpactTemplateSeries>() {
            @Override
            public ImpactTemplateSeries toEntityModel() {
                return _nullImpactTemplateSeries;
            }
        };

    public ImpactTemplateSeriesPersistenceImpl() {
        setModelClass(ImpactTemplateSeries.class);
    }

    /**
     * Caches the impact template series in the entity cache if it is enabled.
     *
     * @param impactTemplateSeries the impact template series
     */
    @Override
    public void cacheResult(ImpactTemplateSeries impactTemplateSeries) {
        EntityCacheUtil.putResult(ImpactTemplateSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactTemplateSeriesImpl.class,
            impactTemplateSeries.getPrimaryKey(), impactTemplateSeries);

        impactTemplateSeries.resetOriginalValues();
    }

    /**
     * Caches the impact template serieses in the entity cache if it is enabled.
     *
     * @param impactTemplateSerieses the impact template serieses
     */
    @Override
    public void cacheResult(List<ImpactTemplateSeries> impactTemplateSerieses) {
        for (ImpactTemplateSeries impactTemplateSeries : impactTemplateSerieses) {
            if (EntityCacheUtil.getResult(
                        ImpactTemplateSeriesModelImpl.ENTITY_CACHE_ENABLED,
                        ImpactTemplateSeriesImpl.class,
                        impactTemplateSeries.getPrimaryKey()) == null) {
                cacheResult(impactTemplateSeries);
            } else {
                impactTemplateSeries.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all impact template serieses.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ImpactTemplateSeriesImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ImpactTemplateSeriesImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the impact template series.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ImpactTemplateSeries impactTemplateSeries) {
        EntityCacheUtil.removeResult(ImpactTemplateSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactTemplateSeriesImpl.class, impactTemplateSeries.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ImpactTemplateSeries> impactTemplateSerieses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ImpactTemplateSeries impactTemplateSeries : impactTemplateSerieses) {
            EntityCacheUtil.removeResult(ImpactTemplateSeriesModelImpl.ENTITY_CACHE_ENABLED,
                ImpactTemplateSeriesImpl.class,
                impactTemplateSeries.getPrimaryKey());
        }
    }

    /**
     * Creates a new impact template series with the primary key. Does not add the impact template series to the database.
     *
     * @param seriesId the primary key for the new impact template series
     * @return the new impact template series
     */
    @Override
    public ImpactTemplateSeries create(long seriesId) {
        ImpactTemplateSeries impactTemplateSeries = new ImpactTemplateSeriesImpl();

        impactTemplateSeries.setNew(true);
        impactTemplateSeries.setPrimaryKey(seriesId);

        return impactTemplateSeries;
    }

    /**
     * Removes the impact template series with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param seriesId the primary key of the impact template series
     * @return the impact template series that was removed
     * @throws com.ext.portlet.NoSuchImpactTemplateSeriesException if a impact template series with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactTemplateSeries remove(long seriesId)
        throws NoSuchImpactTemplateSeriesException, SystemException {
        return remove((Serializable) seriesId);
    }

    /**
     * Removes the impact template series with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the impact template series
     * @return the impact template series that was removed
     * @throws com.ext.portlet.NoSuchImpactTemplateSeriesException if a impact template series with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactTemplateSeries remove(Serializable primaryKey)
        throws NoSuchImpactTemplateSeriesException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ImpactTemplateSeries impactTemplateSeries = (ImpactTemplateSeries) session.get(ImpactTemplateSeriesImpl.class,
                    primaryKey);

            if (impactTemplateSeries == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchImpactTemplateSeriesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(impactTemplateSeries);
        } catch (NoSuchImpactTemplateSeriesException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ImpactTemplateSeries removeImpl(
        ImpactTemplateSeries impactTemplateSeries) throws SystemException {
        impactTemplateSeries = toUnwrappedModel(impactTemplateSeries);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(impactTemplateSeries)) {
                impactTemplateSeries = (ImpactTemplateSeries) session.get(ImpactTemplateSeriesImpl.class,
                        impactTemplateSeries.getPrimaryKeyObj());
            }

            if (impactTemplateSeries != null) {
                session.delete(impactTemplateSeries);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (impactTemplateSeries != null) {
            clearCache(impactTemplateSeries);
        }

        return impactTemplateSeries;
    }

    @Override
    public ImpactTemplateSeries updateImpl(
        com.ext.portlet.model.ImpactTemplateSeries impactTemplateSeries)
        throws SystemException {
        impactTemplateSeries = toUnwrappedModel(impactTemplateSeries);

        boolean isNew = impactTemplateSeries.isNew();

        Session session = null;

        try {
            session = openSession();

            if (impactTemplateSeries.isNew()) {
                session.save(impactTemplateSeries);

                impactTemplateSeries.setNew(false);
            } else {
                session.merge(impactTemplateSeries);
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

        EntityCacheUtil.putResult(ImpactTemplateSeriesModelImpl.ENTITY_CACHE_ENABLED,
            ImpactTemplateSeriesImpl.class,
            impactTemplateSeries.getPrimaryKey(), impactTemplateSeries);

        return impactTemplateSeries;
    }

    protected ImpactTemplateSeries toUnwrappedModel(
        ImpactTemplateSeries impactTemplateSeries) {
        if (impactTemplateSeries instanceof ImpactTemplateSeriesImpl) {
            return impactTemplateSeries;
        }

        ImpactTemplateSeriesImpl impactTemplateSeriesImpl = new ImpactTemplateSeriesImpl();

        impactTemplateSeriesImpl.setNew(impactTemplateSeries.isNew());
        impactTemplateSeriesImpl.setPrimaryKey(impactTemplateSeries.getPrimaryKey());

        impactTemplateSeriesImpl.setSeriesId(impactTemplateSeries.getSeriesId());
        impactTemplateSeriesImpl.setIterationId(impactTemplateSeries.getIterationId());
        impactTemplateSeriesImpl.setName(impactTemplateSeries.getName());

        return impactTemplateSeriesImpl;
    }

    /**
     * Returns the impact template series with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the impact template series
     * @return the impact template series
     * @throws com.ext.portlet.NoSuchImpactTemplateSeriesException if a impact template series with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactTemplateSeries findByPrimaryKey(Serializable primaryKey)
        throws NoSuchImpactTemplateSeriesException, SystemException {
        ImpactTemplateSeries impactTemplateSeries = fetchByPrimaryKey(primaryKey);

        if (impactTemplateSeries == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchImpactTemplateSeriesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return impactTemplateSeries;
    }

    /**
     * Returns the impact template series with the primary key or throws a {@link com.ext.portlet.NoSuchImpactTemplateSeriesException} if it could not be found.
     *
     * @param seriesId the primary key of the impact template series
     * @return the impact template series
     * @throws com.ext.portlet.NoSuchImpactTemplateSeriesException if a impact template series with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactTemplateSeries findByPrimaryKey(long seriesId)
        throws NoSuchImpactTemplateSeriesException, SystemException {
        return findByPrimaryKey((Serializable) seriesId);
    }

    /**
     * Returns the impact template series with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the impact template series
     * @return the impact template series, or <code>null</code> if a impact template series with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactTemplateSeries fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ImpactTemplateSeries impactTemplateSeries = (ImpactTemplateSeries) EntityCacheUtil.getResult(ImpactTemplateSeriesModelImpl.ENTITY_CACHE_ENABLED,
                ImpactTemplateSeriesImpl.class, primaryKey);

        if (impactTemplateSeries == _nullImpactTemplateSeries) {
            return null;
        }

        if (impactTemplateSeries == null) {
            Session session = null;

            try {
                session = openSession();

                impactTemplateSeries = (ImpactTemplateSeries) session.get(ImpactTemplateSeriesImpl.class,
                        primaryKey);

                if (impactTemplateSeries != null) {
                    cacheResult(impactTemplateSeries);
                } else {
                    EntityCacheUtil.putResult(ImpactTemplateSeriesModelImpl.ENTITY_CACHE_ENABLED,
                        ImpactTemplateSeriesImpl.class, primaryKey,
                        _nullImpactTemplateSeries);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ImpactTemplateSeriesModelImpl.ENTITY_CACHE_ENABLED,
                    ImpactTemplateSeriesImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return impactTemplateSeries;
    }

    /**
     * Returns the impact template series with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param seriesId the primary key of the impact template series
     * @return the impact template series, or <code>null</code> if a impact template series with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactTemplateSeries fetchByPrimaryKey(long seriesId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) seriesId);
    }

    /**
     * Returns all the impact template serieses.
     *
     * @return the impact template serieses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImpactTemplateSeries> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the impact template serieses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateSeriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of impact template serieses
     * @param end the upper bound of the range of impact template serieses (not inclusive)
     * @return the range of impact template serieses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImpactTemplateSeries> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the impact template serieses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateSeriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of impact template serieses
     * @param end the upper bound of the range of impact template serieses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of impact template serieses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImpactTemplateSeries> findAll(int start, int end,
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

        List<ImpactTemplateSeries> list = (List<ImpactTemplateSeries>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IMPACTTEMPLATESERIES);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IMPACTTEMPLATESERIES;

                if (pagination) {
                    sql = sql.concat(ImpactTemplateSeriesModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ImpactTemplateSeries>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ImpactTemplateSeries>(list);
                } else {
                    list = (List<ImpactTemplateSeries>) QueryUtil.list(q,
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
     * Removes all the impact template serieses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ImpactTemplateSeries impactTemplateSeries : findAll()) {
            remove(impactTemplateSeries);
        }
    }

    /**
     * Returns the number of impact template serieses.
     *
     * @return the number of impact template serieses
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

                Query q = session.createQuery(_SQL_COUNT_IMPACTTEMPLATESERIES);

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
     * Initializes the impact template series persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ImpactTemplateSeries")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ImpactTemplateSeries>> listenersList = new ArrayList<ModelListener<ImpactTemplateSeries>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ImpactTemplateSeries>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ImpactTemplateSeriesImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
