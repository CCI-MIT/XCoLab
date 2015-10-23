package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchContestTypeException;
import com.ext.portlet.model.ContestType;
import com.ext.portlet.model.impl.ContestTypeImpl;
import com.ext.portlet.model.impl.ContestTypeModelImpl;
import com.ext.portlet.service.persistence.ContestTypePersistence;

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
 * The persistence implementation for the contest type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestTypePersistence
 * @see ContestTypeUtil
 * @generated
 */
public class ContestTypePersistenceImpl extends BasePersistenceImpl<ContestType>
    implements ContestTypePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ContestTypeUtil} to access the contest type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ContestTypeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContestTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestTypeModelImpl.FINDER_CACHE_ENABLED, ContestTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContestTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestTypeModelImpl.FINDER_CACHE_ENABLED, ContestTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContestTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CONTESTTYPE = "SELECT contestType FROM ContestType contestType";
    private static final String _SQL_COUNT_CONTESTTYPE = "SELECT COUNT(contestType) FROM ContestType contestType";
    private static final String _ORDER_BY_ENTITY_ALIAS = "contestType.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContestType exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ContestTypePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static ContestType _nullContestType = new ContestTypeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ContestType> toCacheModel() {
                return _nullContestTypeCacheModel;
            }
        };

    private static CacheModel<ContestType> _nullContestTypeCacheModel = new CacheModel<ContestType>() {
            @Override
            public ContestType toEntityModel() {
                return _nullContestType;
            }
        };

    public ContestTypePersistenceImpl() {
        setModelClass(ContestType.class);
    }

    /**
     * Caches the contest type in the entity cache if it is enabled.
     *
     * @param contestType the contest type
     */
    @Override
    public void cacheResult(ContestType contestType) {
        EntityCacheUtil.putResult(ContestTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestTypeImpl.class, contestType.getPrimaryKey(), contestType);

        contestType.resetOriginalValues();
    }

    /**
     * Caches the contest types in the entity cache if it is enabled.
     *
     * @param contestTypes the contest types
     */
    @Override
    public void cacheResult(List<ContestType> contestTypes) {
        for (ContestType contestType : contestTypes) {
            if (EntityCacheUtil.getResult(
                        ContestTypeModelImpl.ENTITY_CACHE_ENABLED,
                        ContestTypeImpl.class, contestType.getPrimaryKey()) == null) {
                cacheResult(contestType);
            } else {
                contestType.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all contest types.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ContestTypeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ContestTypeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the contest type.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ContestType contestType) {
        EntityCacheUtil.removeResult(ContestTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestTypeImpl.class, contestType.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ContestType> contestTypes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ContestType contestType : contestTypes) {
            EntityCacheUtil.removeResult(ContestTypeModelImpl.ENTITY_CACHE_ENABLED,
                ContestTypeImpl.class, contestType.getPrimaryKey());
        }
    }

    /**
     * Creates a new contest type with the primary key. Does not add the contest type to the database.
     *
     * @param id the primary key for the new contest type
     * @return the new contest type
     */
    @Override
    public ContestType create(long id) {
        ContestType contestType = new ContestTypeImpl();

        contestType.setNew(true);
        contestType.setPrimaryKey(id);

        return contestType;
    }

    /**
     * Removes the contest type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the contest type
     * @return the contest type that was removed
     * @throws com.ext.portlet.NoSuchContestTypeException if a contest type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestType remove(long id)
        throws NoSuchContestTypeException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the contest type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the contest type
     * @return the contest type that was removed
     * @throws com.ext.portlet.NoSuchContestTypeException if a contest type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestType remove(Serializable primaryKey)
        throws NoSuchContestTypeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ContestType contestType = (ContestType) session.get(ContestTypeImpl.class,
                    primaryKey);

            if (contestType == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchContestTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(contestType);
        } catch (NoSuchContestTypeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ContestType removeImpl(ContestType contestType)
        throws SystemException {
        contestType = toUnwrappedModel(contestType);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(contestType)) {
                contestType = (ContestType) session.get(ContestTypeImpl.class,
                        contestType.getPrimaryKeyObj());
            }

            if (contestType != null) {
                session.delete(contestType);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (contestType != null) {
            clearCache(contestType);
        }

        return contestType;
    }

    @Override
    public ContestType updateImpl(com.ext.portlet.model.ContestType contestType)
        throws SystemException {
        contestType = toUnwrappedModel(contestType);

        boolean isNew = contestType.isNew();

        Session session = null;

        try {
            session = openSession();

            if (contestType.isNew()) {
                session.save(contestType);

                contestType.setNew(false);
            } else {
                session.merge(contestType);
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

        EntityCacheUtil.putResult(ContestTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestTypeImpl.class, contestType.getPrimaryKey(), contestType);

        return contestType;
    }

    protected ContestType toUnwrappedModel(ContestType contestType) {
        if (contestType instanceof ContestTypeImpl) {
            return contestType;
        }

        ContestTypeImpl contestTypeImpl = new ContestTypeImpl();

        contestTypeImpl.setNew(contestType.isNew());
        contestTypeImpl.setPrimaryKey(contestType.getPrimaryKey());

        contestTypeImpl.setId(contestType.getId());
        contestTypeImpl.setContestName(contestType.getContestName());
        contestTypeImpl.setContestNamePlural(contestType.getContestNamePlural());
        contestTypeImpl.setProposalName(contestType.getProposalName());
        contestTypeImpl.setProposalNamePlural(contestType.getProposalNamePlural());
        contestTypeImpl.setPortletName(contestType.getPortletName());
        contestTypeImpl.setPortletUrl(contestType.getPortletUrl());
        contestTypeImpl.setMenuItemName(contestType.getMenuItemName());
        contestTypeImpl.setHasDiscussion(contestType.isHasDiscussion());

        return contestTypeImpl;
    }

    /**
     * Returns the contest type with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the contest type
     * @return the contest type
     * @throws com.ext.portlet.NoSuchContestTypeException if a contest type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestType findByPrimaryKey(Serializable primaryKey)
        throws NoSuchContestTypeException, SystemException {
        ContestType contestType = fetchByPrimaryKey(primaryKey);

        if (contestType == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchContestTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return contestType;
    }

    /**
     * Returns the contest type with the primary key or throws a {@link com.ext.portlet.NoSuchContestTypeException} if it could not be found.
     *
     * @param id the primary key of the contest type
     * @return the contest type
     * @throws com.ext.portlet.NoSuchContestTypeException if a contest type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestType findByPrimaryKey(long id)
        throws NoSuchContestTypeException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the contest type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the contest type
     * @return the contest type, or <code>null</code> if a contest type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestType fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ContestType contestType = (ContestType) EntityCacheUtil.getResult(ContestTypeModelImpl.ENTITY_CACHE_ENABLED,
                ContestTypeImpl.class, primaryKey);

        if (contestType == _nullContestType) {
            return null;
        }

        if (contestType == null) {
            Session session = null;

            try {
                session = openSession();

                contestType = (ContestType) session.get(ContestTypeImpl.class,
                        primaryKey);

                if (contestType != null) {
                    cacheResult(contestType);
                } else {
                    EntityCacheUtil.putResult(ContestTypeModelImpl.ENTITY_CACHE_ENABLED,
                        ContestTypeImpl.class, primaryKey, _nullContestType);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ContestTypeModelImpl.ENTITY_CACHE_ENABLED,
                    ContestTypeImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return contestType;
    }

    /**
     * Returns the contest type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the contest type
     * @return the contest type, or <code>null</code> if a contest type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestType fetchByPrimaryKey(long id) throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the contest types.
     *
     * @return the contest types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestType> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contest types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contest types
     * @param end the upper bound of the range of contest types (not inclusive)
     * @return the range of contest types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestType> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the contest types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contest types
     * @param end the upper bound of the range of contest types (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of contest types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestType> findAll(int start, int end,
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

        List<ContestType> list = (List<ContestType>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CONTESTTYPE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CONTESTTYPE;

                if (pagination) {
                    sql = sql.concat(ContestTypeModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ContestType>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ContestType>(list);
                } else {
                    list = (List<ContestType>) QueryUtil.list(q, getDialect(),
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
     * Removes all the contest types from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ContestType contestType : findAll()) {
            remove(contestType);
        }
    }

    /**
     * Returns the number of contest types.
     *
     * @return the number of contest types
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

                Query q = session.createQuery(_SQL_COUNT_CONTESTTYPE);

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
     * Initializes the contest type persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ContestType")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ContestType>> listenersList = new ArrayList<ModelListener<ContestType>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ContestType>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ContestTypeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
