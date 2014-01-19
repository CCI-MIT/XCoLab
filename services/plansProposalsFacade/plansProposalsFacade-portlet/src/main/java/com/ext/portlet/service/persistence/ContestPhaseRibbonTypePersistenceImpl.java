package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchContestPhaseRibbonTypeException;
import com.ext.portlet.model.ContestPhaseRibbonType;
import com.ext.portlet.model.impl.ContestPhaseRibbonTypeImpl;
import com.ext.portlet.model.impl.ContestPhaseRibbonTypeModelImpl;
import com.ext.portlet.service.persistence.ContestPhaseRibbonTypePersistence;

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
import com.liferay.portal.kernel.util.SetUtil;
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
import java.util.Set;

/**
 * The persistence implementation for the contest phase ribbon type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseRibbonTypePersistence
 * @see ContestPhaseRibbonTypeUtil
 * @generated
 */
public class ContestPhaseRibbonTypePersistenceImpl extends BasePersistenceImpl<ContestPhaseRibbonType>
    implements ContestPhaseRibbonTypePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ContestPhaseRibbonTypeUtil} to access the contest phase ribbon type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ContestPhaseRibbonTypeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContestPhaseRibbonTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseRibbonTypeModelImpl.FINDER_CACHE_ENABLED,
            ContestPhaseRibbonTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContestPhaseRibbonTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseRibbonTypeModelImpl.FINDER_CACHE_ENABLED,
            ContestPhaseRibbonTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContestPhaseRibbonTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseRibbonTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CONTESTPHASERIBBONTYPE = "SELECT contestPhaseRibbonType FROM ContestPhaseRibbonType contestPhaseRibbonType";
    private static final String _SQL_COUNT_CONTESTPHASERIBBONTYPE = "SELECT COUNT(contestPhaseRibbonType) FROM ContestPhaseRibbonType contestPhaseRibbonType";
    private static final String _ORDER_BY_ENTITY_ALIAS = "contestPhaseRibbonType.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContestPhaseRibbonType exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ContestPhaseRibbonTypePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static ContestPhaseRibbonType _nullContestPhaseRibbonType = new ContestPhaseRibbonTypeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ContestPhaseRibbonType> toCacheModel() {
                return _nullContestPhaseRibbonTypeCacheModel;
            }
        };

    private static CacheModel<ContestPhaseRibbonType> _nullContestPhaseRibbonTypeCacheModel =
        new CacheModel<ContestPhaseRibbonType>() {
            @Override
            public ContestPhaseRibbonType toEntityModel() {
                return _nullContestPhaseRibbonType;
            }
        };

    public ContestPhaseRibbonTypePersistenceImpl() {
        setModelClass(ContestPhaseRibbonType.class);
    }

    /**
     * Caches the contest phase ribbon type in the entity cache if it is enabled.
     *
     * @param contestPhaseRibbonType the contest phase ribbon type
     */
    @Override
    public void cacheResult(ContestPhaseRibbonType contestPhaseRibbonType) {
        EntityCacheUtil.putResult(ContestPhaseRibbonTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseRibbonTypeImpl.class,
            contestPhaseRibbonType.getPrimaryKey(), contestPhaseRibbonType);

        contestPhaseRibbonType.resetOriginalValues();
    }

    /**
     * Caches the contest phase ribbon types in the entity cache if it is enabled.
     *
     * @param contestPhaseRibbonTypes the contest phase ribbon types
     */
    @Override
    public void cacheResult(
        List<ContestPhaseRibbonType> contestPhaseRibbonTypes) {
        for (ContestPhaseRibbonType contestPhaseRibbonType : contestPhaseRibbonTypes) {
            if (EntityCacheUtil.getResult(
                        ContestPhaseRibbonTypeModelImpl.ENTITY_CACHE_ENABLED,
                        ContestPhaseRibbonTypeImpl.class,
                        contestPhaseRibbonType.getPrimaryKey()) == null) {
                cacheResult(contestPhaseRibbonType);
            } else {
                contestPhaseRibbonType.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all contest phase ribbon types.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ContestPhaseRibbonTypeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ContestPhaseRibbonTypeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the contest phase ribbon type.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ContestPhaseRibbonType contestPhaseRibbonType) {
        EntityCacheUtil.removeResult(ContestPhaseRibbonTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseRibbonTypeImpl.class,
            contestPhaseRibbonType.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ContestPhaseRibbonType> contestPhaseRibbonTypes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ContestPhaseRibbonType contestPhaseRibbonType : contestPhaseRibbonTypes) {
            EntityCacheUtil.removeResult(ContestPhaseRibbonTypeModelImpl.ENTITY_CACHE_ENABLED,
                ContestPhaseRibbonTypeImpl.class,
                contestPhaseRibbonType.getPrimaryKey());
        }
    }

    /**
     * Creates a new contest phase ribbon type with the primary key. Does not add the contest phase ribbon type to the database.
     *
     * @param id the primary key for the new contest phase ribbon type
     * @return the new contest phase ribbon type
     */
    @Override
    public ContestPhaseRibbonType create(long id) {
        ContestPhaseRibbonType contestPhaseRibbonType = new ContestPhaseRibbonTypeImpl();

        contestPhaseRibbonType.setNew(true);
        contestPhaseRibbonType.setPrimaryKey(id);

        return contestPhaseRibbonType;
    }

    /**
     * Removes the contest phase ribbon type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the contest phase ribbon type
     * @return the contest phase ribbon type that was removed
     * @throws com.ext.portlet.NoSuchContestPhaseRibbonTypeException if a contest phase ribbon type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhaseRibbonType remove(long id)
        throws NoSuchContestPhaseRibbonTypeException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the contest phase ribbon type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the contest phase ribbon type
     * @return the contest phase ribbon type that was removed
     * @throws com.ext.portlet.NoSuchContestPhaseRibbonTypeException if a contest phase ribbon type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhaseRibbonType remove(Serializable primaryKey)
        throws NoSuchContestPhaseRibbonTypeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ContestPhaseRibbonType contestPhaseRibbonType = (ContestPhaseRibbonType) session.get(ContestPhaseRibbonTypeImpl.class,
                    primaryKey);

            if (contestPhaseRibbonType == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchContestPhaseRibbonTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(contestPhaseRibbonType);
        } catch (NoSuchContestPhaseRibbonTypeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ContestPhaseRibbonType removeImpl(
        ContestPhaseRibbonType contestPhaseRibbonType)
        throws SystemException {
        contestPhaseRibbonType = toUnwrappedModel(contestPhaseRibbonType);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(contestPhaseRibbonType)) {
                contestPhaseRibbonType = (ContestPhaseRibbonType) session.get(ContestPhaseRibbonTypeImpl.class,
                        contestPhaseRibbonType.getPrimaryKeyObj());
            }

            if (contestPhaseRibbonType != null) {
                session.delete(contestPhaseRibbonType);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (contestPhaseRibbonType != null) {
            clearCache(contestPhaseRibbonType);
        }

        return contestPhaseRibbonType;
    }

    @Override
    public ContestPhaseRibbonType updateImpl(
        com.ext.portlet.model.ContestPhaseRibbonType contestPhaseRibbonType)
        throws SystemException {
        contestPhaseRibbonType = toUnwrappedModel(contestPhaseRibbonType);

        boolean isNew = contestPhaseRibbonType.isNew();

        Session session = null;

        try {
            session = openSession();

            if (contestPhaseRibbonType.isNew()) {
                session.save(contestPhaseRibbonType);

                contestPhaseRibbonType.setNew(false);
            } else {
                session.merge(contestPhaseRibbonType);
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

        EntityCacheUtil.putResult(ContestPhaseRibbonTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseRibbonTypeImpl.class,
            contestPhaseRibbonType.getPrimaryKey(), contestPhaseRibbonType);

        return contestPhaseRibbonType;
    }

    protected ContestPhaseRibbonType toUnwrappedModel(
        ContestPhaseRibbonType contestPhaseRibbonType) {
        if (contestPhaseRibbonType instanceof ContestPhaseRibbonTypeImpl) {
            return contestPhaseRibbonType;
        }

        ContestPhaseRibbonTypeImpl contestPhaseRibbonTypeImpl = new ContestPhaseRibbonTypeImpl();

        contestPhaseRibbonTypeImpl.setNew(contestPhaseRibbonType.isNew());
        contestPhaseRibbonTypeImpl.setPrimaryKey(contestPhaseRibbonType.getPrimaryKey());

        contestPhaseRibbonTypeImpl.setId(contestPhaseRibbonType.getId());
        contestPhaseRibbonTypeImpl.setRibbon(contestPhaseRibbonType.getRibbon());
        contestPhaseRibbonTypeImpl.setHoverText(contestPhaseRibbonType.getHoverText());
        contestPhaseRibbonTypeImpl.setDescription(contestPhaseRibbonType.getDescription());
        contestPhaseRibbonTypeImpl.setCopyOnPromote(contestPhaseRibbonType.isCopyOnPromote());

        return contestPhaseRibbonTypeImpl;
    }

    /**
     * Returns the contest phase ribbon type with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the contest phase ribbon type
     * @return the contest phase ribbon type
     * @throws com.ext.portlet.NoSuchContestPhaseRibbonTypeException if a contest phase ribbon type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhaseRibbonType findByPrimaryKey(Serializable primaryKey)
        throws NoSuchContestPhaseRibbonTypeException, SystemException {
        ContestPhaseRibbonType contestPhaseRibbonType = fetchByPrimaryKey(primaryKey);

        if (contestPhaseRibbonType == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchContestPhaseRibbonTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return contestPhaseRibbonType;
    }

    /**
     * Returns the contest phase ribbon type with the primary key or throws a {@link com.ext.portlet.NoSuchContestPhaseRibbonTypeException} if it could not be found.
     *
     * @param id the primary key of the contest phase ribbon type
     * @return the contest phase ribbon type
     * @throws com.ext.portlet.NoSuchContestPhaseRibbonTypeException if a contest phase ribbon type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhaseRibbonType findByPrimaryKey(long id)
        throws NoSuchContestPhaseRibbonTypeException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the contest phase ribbon type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the contest phase ribbon type
     * @return the contest phase ribbon type, or <code>null</code> if a contest phase ribbon type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhaseRibbonType fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ContestPhaseRibbonType contestPhaseRibbonType = (ContestPhaseRibbonType) EntityCacheUtil.getResult(ContestPhaseRibbonTypeModelImpl.ENTITY_CACHE_ENABLED,
                ContestPhaseRibbonTypeImpl.class, primaryKey);

        if (contestPhaseRibbonType == _nullContestPhaseRibbonType) {
            return null;
        }

        if (contestPhaseRibbonType == null) {
            Session session = null;

            try {
                session = openSession();

                contestPhaseRibbonType = (ContestPhaseRibbonType) session.get(ContestPhaseRibbonTypeImpl.class,
                        primaryKey);

                if (contestPhaseRibbonType != null) {
                    cacheResult(contestPhaseRibbonType);
                } else {
                    EntityCacheUtil.putResult(ContestPhaseRibbonTypeModelImpl.ENTITY_CACHE_ENABLED,
                        ContestPhaseRibbonTypeImpl.class, primaryKey,
                        _nullContestPhaseRibbonType);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ContestPhaseRibbonTypeModelImpl.ENTITY_CACHE_ENABLED,
                    ContestPhaseRibbonTypeImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return contestPhaseRibbonType;
    }

    /**
     * Returns the contest phase ribbon type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the contest phase ribbon type
     * @return the contest phase ribbon type, or <code>null</code> if a contest phase ribbon type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhaseRibbonType fetchByPrimaryKey(long id)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the contest phase ribbon types.
     *
     * @return the contest phase ribbon types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhaseRibbonType> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contest phase ribbon types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseRibbonTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contest phase ribbon types
     * @param end the upper bound of the range of contest phase ribbon types (not inclusive)
     * @return the range of contest phase ribbon types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhaseRibbonType> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the contest phase ribbon types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseRibbonTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contest phase ribbon types
     * @param end the upper bound of the range of contest phase ribbon types (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of contest phase ribbon types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhaseRibbonType> findAll(int start, int end,
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

        List<ContestPhaseRibbonType> list = (List<ContestPhaseRibbonType>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CONTESTPHASERIBBONTYPE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CONTESTPHASERIBBONTYPE;

                if (pagination) {
                    sql = sql.concat(ContestPhaseRibbonTypeModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ContestPhaseRibbonType>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ContestPhaseRibbonType>(list);
                } else {
                    list = (List<ContestPhaseRibbonType>) QueryUtil.list(q,
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
     * Removes all the contest phase ribbon types from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ContestPhaseRibbonType contestPhaseRibbonType : findAll()) {
            remove(contestPhaseRibbonType);
        }
    }

    /**
     * Returns the number of contest phase ribbon types.
     *
     * @return the number of contest phase ribbon types
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

                Query q = session.createQuery(_SQL_COUNT_CONTESTPHASERIBBONTYPE);

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
     * Initializes the contest phase ribbon type persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ContestPhaseRibbonType")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ContestPhaseRibbonType>> listenersList = new ArrayList<ModelListener<ContestPhaseRibbonType>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ContestPhaseRibbonType>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ContestPhaseRibbonTypeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
