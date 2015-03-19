package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchContestScheduleException;
import com.ext.portlet.model.ContestSchedule;
import com.ext.portlet.model.impl.ContestScheduleImpl;
import com.ext.portlet.model.impl.ContestScheduleModelImpl;
import com.ext.portlet.service.persistence.ContestSchedulePersistence;

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
 * The persistence implementation for the contest schedule service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestSchedulePersistence
 * @see ContestScheduleUtil
 * @generated
 */
public class ContestSchedulePersistenceImpl extends BasePersistenceImpl<ContestSchedule>
    implements ContestSchedulePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ContestScheduleUtil} to access the contest schedule persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ContestScheduleImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContestScheduleModelImpl.ENTITY_CACHE_ENABLED,
            ContestScheduleModelImpl.FINDER_CACHE_ENABLED,
            ContestScheduleImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContestScheduleModelImpl.ENTITY_CACHE_ENABLED,
            ContestScheduleModelImpl.FINDER_CACHE_ENABLED,
            ContestScheduleImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContestScheduleModelImpl.ENTITY_CACHE_ENABLED,
            ContestScheduleModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CONTESTSCHEDULE = "SELECT contestSchedule FROM ContestSchedule contestSchedule";
    private static final String _SQL_COUNT_CONTESTSCHEDULE = "SELECT COUNT(contestSchedule) FROM ContestSchedule contestSchedule";
    private static final String _ORDER_BY_ENTITY_ALIAS = "contestSchedule.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContestSchedule exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ContestSchedulePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static ContestSchedule _nullContestSchedule = new ContestScheduleImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ContestSchedule> toCacheModel() {
                return _nullContestScheduleCacheModel;
            }
        };

    private static CacheModel<ContestSchedule> _nullContestScheduleCacheModel = new CacheModel<ContestSchedule>() {
            @Override
            public ContestSchedule toEntityModel() {
                return _nullContestSchedule;
            }
        };

    public ContestSchedulePersistenceImpl() {
        setModelClass(ContestSchedule.class);
    }

    /**
     * Caches the contest schedule in the entity cache if it is enabled.
     *
     * @param contestSchedule the contest schedule
     */
    @Override
    public void cacheResult(ContestSchedule contestSchedule) {
        EntityCacheUtil.putResult(ContestScheduleModelImpl.ENTITY_CACHE_ENABLED,
            ContestScheduleImpl.class, contestSchedule.getPrimaryKey(),
            contestSchedule);

        contestSchedule.resetOriginalValues();
    }

    /**
     * Caches the contest schedules in the entity cache if it is enabled.
     *
     * @param contestSchedules the contest schedules
     */
    @Override
    public void cacheResult(List<ContestSchedule> contestSchedules) {
        for (ContestSchedule contestSchedule : contestSchedules) {
            if (EntityCacheUtil.getResult(
                        ContestScheduleModelImpl.ENTITY_CACHE_ENABLED,
                        ContestScheduleImpl.class,
                        contestSchedule.getPrimaryKey()) == null) {
                cacheResult(contestSchedule);
            } else {
                contestSchedule.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all contest schedules.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ContestScheduleImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ContestScheduleImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the contest schedule.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ContestSchedule contestSchedule) {
        EntityCacheUtil.removeResult(ContestScheduleModelImpl.ENTITY_CACHE_ENABLED,
            ContestScheduleImpl.class, contestSchedule.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ContestSchedule> contestSchedules) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ContestSchedule contestSchedule : contestSchedules) {
            EntityCacheUtil.removeResult(ContestScheduleModelImpl.ENTITY_CACHE_ENABLED,
                ContestScheduleImpl.class, contestSchedule.getPrimaryKey());
        }
    }

    /**
     * Creates a new contest schedule with the primary key. Does not add the contest schedule to the database.
     *
     * @param id the primary key for the new contest schedule
     * @return the new contest schedule
     */
    @Override
    public ContestSchedule create(long id) {
        ContestSchedule contestSchedule = new ContestScheduleImpl();

        contestSchedule.setNew(true);
        contestSchedule.setPrimaryKey(id);

        return contestSchedule;
    }

    /**
     * Removes the contest schedule with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the contest schedule
     * @return the contest schedule that was removed
     * @throws com.ext.portlet.NoSuchContestScheduleException if a contest schedule with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestSchedule remove(long id)
        throws NoSuchContestScheduleException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the contest schedule with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the contest schedule
     * @return the contest schedule that was removed
     * @throws com.ext.portlet.NoSuchContestScheduleException if a contest schedule with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestSchedule remove(Serializable primaryKey)
        throws NoSuchContestScheduleException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ContestSchedule contestSchedule = (ContestSchedule) session.get(ContestScheduleImpl.class,
                    primaryKey);

            if (contestSchedule == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchContestScheduleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(contestSchedule);
        } catch (NoSuchContestScheduleException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ContestSchedule removeImpl(ContestSchedule contestSchedule)
        throws SystemException {
        contestSchedule = toUnwrappedModel(contestSchedule);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(contestSchedule)) {
                contestSchedule = (ContestSchedule) session.get(ContestScheduleImpl.class,
                        contestSchedule.getPrimaryKeyObj());
            }

            if (contestSchedule != null) {
                session.delete(contestSchedule);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (contestSchedule != null) {
            clearCache(contestSchedule);
        }

        return contestSchedule;
    }

    @Override
    public ContestSchedule updateImpl(
        com.ext.portlet.model.ContestSchedule contestSchedule)
        throws SystemException {
        contestSchedule = toUnwrappedModel(contestSchedule);

        boolean isNew = contestSchedule.isNew();

        Session session = null;

        try {
            session = openSession();

            if (contestSchedule.isNew()) {
                session.save(contestSchedule);

                contestSchedule.setNew(false);
            } else {
                session.merge(contestSchedule);
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

        EntityCacheUtil.putResult(ContestScheduleModelImpl.ENTITY_CACHE_ENABLED,
            ContestScheduleImpl.class, contestSchedule.getPrimaryKey(),
            contestSchedule);

        return contestSchedule;
    }

    protected ContestSchedule toUnwrappedModel(ContestSchedule contestSchedule) {
        if (contestSchedule instanceof ContestScheduleImpl) {
            return contestSchedule;
        }

        ContestScheduleImpl contestScheduleImpl = new ContestScheduleImpl();

        contestScheduleImpl.setNew(contestSchedule.isNew());
        contestScheduleImpl.setPrimaryKey(contestSchedule.getPrimaryKey());

        contestScheduleImpl.setId(contestSchedule.getId());
        contestScheduleImpl.setName(contestSchedule.getName());
        contestScheduleImpl.setDescription(contestSchedule.getDescription());
        contestScheduleImpl.setStatus(contestSchedule.getStatus());
        contestScheduleImpl.setBaseScheduleId(contestSchedule.getBaseScheduleId());

        return contestScheduleImpl;
    }

    /**
     * Returns the contest schedule with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the contest schedule
     * @return the contest schedule
     * @throws com.ext.portlet.NoSuchContestScheduleException if a contest schedule with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestSchedule findByPrimaryKey(Serializable primaryKey)
        throws NoSuchContestScheduleException, SystemException {
        ContestSchedule contestSchedule = fetchByPrimaryKey(primaryKey);

        if (contestSchedule == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchContestScheduleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return contestSchedule;
    }

    /**
     * Returns the contest schedule with the primary key or throws a {@link com.ext.portlet.NoSuchContestScheduleException} if it could not be found.
     *
     * @param id the primary key of the contest schedule
     * @return the contest schedule
     * @throws com.ext.portlet.NoSuchContestScheduleException if a contest schedule with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestSchedule findByPrimaryKey(long id)
        throws NoSuchContestScheduleException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the contest schedule with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the contest schedule
     * @return the contest schedule, or <code>null</code> if a contest schedule with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestSchedule fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ContestSchedule contestSchedule = (ContestSchedule) EntityCacheUtil.getResult(ContestScheduleModelImpl.ENTITY_CACHE_ENABLED,
                ContestScheduleImpl.class, primaryKey);

        if (contestSchedule == _nullContestSchedule) {
            return null;
        }

        if (contestSchedule == null) {
            Session session = null;

            try {
                session = openSession();

                contestSchedule = (ContestSchedule) session.get(ContestScheduleImpl.class,
                        primaryKey);

                if (contestSchedule != null) {
                    cacheResult(contestSchedule);
                } else {
                    EntityCacheUtil.putResult(ContestScheduleModelImpl.ENTITY_CACHE_ENABLED,
                        ContestScheduleImpl.class, primaryKey,
                        _nullContestSchedule);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ContestScheduleModelImpl.ENTITY_CACHE_ENABLED,
                    ContestScheduleImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return contestSchedule;
    }

    /**
     * Returns the contest schedule with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the contest schedule
     * @return the contest schedule, or <code>null</code> if a contest schedule with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestSchedule fetchByPrimaryKey(long id) throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the contest schedules.
     *
     * @return the contest schedules
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestSchedule> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contest schedules.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contest schedules
     * @param end the upper bound of the range of contest schedules (not inclusive)
     * @return the range of contest schedules
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestSchedule> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the contest schedules.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contest schedules
     * @param end the upper bound of the range of contest schedules (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of contest schedules
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestSchedule> findAll(int start, int end,
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

        List<ContestSchedule> list = (List<ContestSchedule>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CONTESTSCHEDULE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CONTESTSCHEDULE;

                if (pagination) {
                    sql = sql.concat(ContestScheduleModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ContestSchedule>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ContestSchedule>(list);
                } else {
                    list = (List<ContestSchedule>) QueryUtil.list(q,
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
     * Removes all the contest schedules from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ContestSchedule contestSchedule : findAll()) {
            remove(contestSchedule);
        }
    }

    /**
     * Returns the number of contest schedules.
     *
     * @return the number of contest schedules
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

                Query q = session.createQuery(_SQL_COUNT_CONTESTSCHEDULE);

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
     * Initializes the contest schedule persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ContestSchedule")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ContestSchedule>> listenersList = new ArrayList<ModelListener<ContestSchedule>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ContestSchedule>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ContestScheduleImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
