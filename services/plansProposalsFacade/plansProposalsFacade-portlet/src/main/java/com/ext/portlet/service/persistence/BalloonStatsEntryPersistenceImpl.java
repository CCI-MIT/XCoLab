package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchBalloonStatsEntryException;
import com.ext.portlet.model.BalloonStatsEntry;
import com.ext.portlet.model.impl.BalloonStatsEntryImpl;
import com.ext.portlet.model.impl.BalloonStatsEntryModelImpl;
import com.ext.portlet.service.persistence.BalloonStatsEntryPersistence;

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
 * The persistence implementation for the balloon stats entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BalloonStatsEntryPersistence
 * @see BalloonStatsEntryUtil
 * @generated
 */
public class BalloonStatsEntryPersistenceImpl extends BasePersistenceImpl<BalloonStatsEntry>
    implements BalloonStatsEntryPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link BalloonStatsEntryUtil} to access the balloon stats entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = BalloonStatsEntryImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(BalloonStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
            BalloonStatsEntryModelImpl.FINDER_CACHE_ENABLED,
            BalloonStatsEntryImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(BalloonStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
            BalloonStatsEntryModelImpl.FINDER_CACHE_ENABLED,
            BalloonStatsEntryImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(BalloonStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
            BalloonStatsEntryModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_BALLOONSTATSENTRY = "SELECT balloonStatsEntry FROM BalloonStatsEntry balloonStatsEntry";
    private static final String _SQL_COUNT_BALLOONSTATSENTRY = "SELECT COUNT(balloonStatsEntry) FROM BalloonStatsEntry balloonStatsEntry";
    private static final String _ORDER_BY_ENTITY_ALIAS = "balloonStatsEntry.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No BalloonStatsEntry exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(BalloonStatsEntryPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static BalloonStatsEntry _nullBalloonStatsEntry = new BalloonStatsEntryImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<BalloonStatsEntry> toCacheModel() {
                return _nullBalloonStatsEntryCacheModel;
            }
        };

    private static CacheModel<BalloonStatsEntry> _nullBalloonStatsEntryCacheModel =
        new CacheModel<BalloonStatsEntry>() {
            @Override
            public BalloonStatsEntry toEntityModel() {
                return _nullBalloonStatsEntry;
            }
        };

    public BalloonStatsEntryPersistenceImpl() {
        setModelClass(BalloonStatsEntry.class);
    }

    /**
     * Caches the balloon stats entry in the entity cache if it is enabled.
     *
     * @param balloonStatsEntry the balloon stats entry
     */
    @Override
    public void cacheResult(BalloonStatsEntry balloonStatsEntry) {
        EntityCacheUtil.putResult(BalloonStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
            BalloonStatsEntryImpl.class, balloonStatsEntry.getPrimaryKey(),
            balloonStatsEntry);

        balloonStatsEntry.resetOriginalValues();
    }

    /**
     * Caches the balloon stats entries in the entity cache if it is enabled.
     *
     * @param balloonStatsEntries the balloon stats entries
     */
    @Override
    public void cacheResult(List<BalloonStatsEntry> balloonStatsEntries) {
        for (BalloonStatsEntry balloonStatsEntry : balloonStatsEntries) {
            if (EntityCacheUtil.getResult(
                        BalloonStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
                        BalloonStatsEntryImpl.class,
                        balloonStatsEntry.getPrimaryKey()) == null) {
                cacheResult(balloonStatsEntry);
            } else {
                balloonStatsEntry.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all balloon stats entries.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(BalloonStatsEntryImpl.class.getName());
        }

        EntityCacheUtil.clearCache(BalloonStatsEntryImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the balloon stats entry.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(BalloonStatsEntry balloonStatsEntry) {
        EntityCacheUtil.removeResult(BalloonStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
            BalloonStatsEntryImpl.class, balloonStatsEntry.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<BalloonStatsEntry> balloonStatsEntries) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (BalloonStatsEntry balloonStatsEntry : balloonStatsEntries) {
            EntityCacheUtil.removeResult(BalloonStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
                BalloonStatsEntryImpl.class, balloonStatsEntry.getPrimaryKey());
        }
    }

    /**
     * Creates a new balloon stats entry with the primary key. Does not add the balloon stats entry to the database.
     *
     * @param id the primary key for the new balloon stats entry
     * @return the new balloon stats entry
     */
    @Override
    public BalloonStatsEntry create(long id) {
        BalloonStatsEntry balloonStatsEntry = new BalloonStatsEntryImpl();

        balloonStatsEntry.setNew(true);
        balloonStatsEntry.setPrimaryKey(id);

        return balloonStatsEntry;
    }

    /**
     * Removes the balloon stats entry with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the balloon stats entry
     * @return the balloon stats entry that was removed
     * @throws com.ext.portlet.NoSuchBalloonStatsEntryException if a balloon stats entry with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonStatsEntry remove(long id)
        throws NoSuchBalloonStatsEntryException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the balloon stats entry with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the balloon stats entry
     * @return the balloon stats entry that was removed
     * @throws com.ext.portlet.NoSuchBalloonStatsEntryException if a balloon stats entry with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonStatsEntry remove(Serializable primaryKey)
        throws NoSuchBalloonStatsEntryException, SystemException {
        Session session = null;

        try {
            session = openSession();

            BalloonStatsEntry balloonStatsEntry = (BalloonStatsEntry) session.get(BalloonStatsEntryImpl.class,
                    primaryKey);

            if (balloonStatsEntry == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchBalloonStatsEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(balloonStatsEntry);
        } catch (NoSuchBalloonStatsEntryException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected BalloonStatsEntry removeImpl(BalloonStatsEntry balloonStatsEntry)
        throws SystemException {
        balloonStatsEntry = toUnwrappedModel(balloonStatsEntry);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(balloonStatsEntry)) {
                balloonStatsEntry = (BalloonStatsEntry) session.get(BalloonStatsEntryImpl.class,
                        balloonStatsEntry.getPrimaryKeyObj());
            }

            if (balloonStatsEntry != null) {
                session.delete(balloonStatsEntry);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (balloonStatsEntry != null) {
            clearCache(balloonStatsEntry);
        }

        return balloonStatsEntry;
    }

    @Override
    public BalloonStatsEntry updateImpl(
        com.ext.portlet.model.BalloonStatsEntry balloonStatsEntry)
        throws SystemException {
        balloonStatsEntry = toUnwrappedModel(balloonStatsEntry);

        boolean isNew = balloonStatsEntry.isNew();

        Session session = null;

        try {
            session = openSession();

            if (balloonStatsEntry.isNew()) {
                session.save(balloonStatsEntry);

                balloonStatsEntry.setNew(false);
            } else {
                session.merge(balloonStatsEntry);
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

        EntityCacheUtil.putResult(BalloonStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
            BalloonStatsEntryImpl.class, balloonStatsEntry.getPrimaryKey(),
            balloonStatsEntry);

        return balloonStatsEntry;
    }

    protected BalloonStatsEntry toUnwrappedModel(
        BalloonStatsEntry balloonStatsEntry) {
        if (balloonStatsEntry instanceof BalloonStatsEntryImpl) {
            return balloonStatsEntry;
        }

        BalloonStatsEntryImpl balloonStatsEntryImpl = new BalloonStatsEntryImpl();

        balloonStatsEntryImpl.setNew(balloonStatsEntry.isNew());
        balloonStatsEntryImpl.setPrimaryKey(balloonStatsEntry.getPrimaryKey());

        balloonStatsEntryImpl.setId(balloonStatsEntry.getId());
        balloonStatsEntryImpl.setFirstContestId(balloonStatsEntry.getFirstContestId());
        balloonStatsEntryImpl.setSecondContestId(balloonStatsEntry.getSecondContestId());
        balloonStatsEntryImpl.setChoosenContest(balloonStatsEntry.getChoosenContest());
        balloonStatsEntryImpl.setCookie(balloonStatsEntry.getCookie());
        balloonStatsEntryImpl.setIp(balloonStatsEntry.getIp());
        balloonStatsEntryImpl.setExtraData(balloonStatsEntry.getExtraData());

        return balloonStatsEntryImpl;
    }

    /**
     * Returns the balloon stats entry with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the balloon stats entry
     * @return the balloon stats entry
     * @throws com.ext.portlet.NoSuchBalloonStatsEntryException if a balloon stats entry with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonStatsEntry findByPrimaryKey(Serializable primaryKey)
        throws NoSuchBalloonStatsEntryException, SystemException {
        BalloonStatsEntry balloonStatsEntry = fetchByPrimaryKey(primaryKey);

        if (balloonStatsEntry == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchBalloonStatsEntryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return balloonStatsEntry;
    }

    /**
     * Returns the balloon stats entry with the primary key or throws a {@link com.ext.portlet.NoSuchBalloonStatsEntryException} if it could not be found.
     *
     * @param id the primary key of the balloon stats entry
     * @return the balloon stats entry
     * @throws com.ext.portlet.NoSuchBalloonStatsEntryException if a balloon stats entry with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonStatsEntry findByPrimaryKey(long id)
        throws NoSuchBalloonStatsEntryException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the balloon stats entry with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the balloon stats entry
     * @return the balloon stats entry, or <code>null</code> if a balloon stats entry with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonStatsEntry fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        BalloonStatsEntry balloonStatsEntry = (BalloonStatsEntry) EntityCacheUtil.getResult(BalloonStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
                BalloonStatsEntryImpl.class, primaryKey);

        if (balloonStatsEntry == _nullBalloonStatsEntry) {
            return null;
        }

        if (balloonStatsEntry == null) {
            Session session = null;

            try {
                session = openSession();

                balloonStatsEntry = (BalloonStatsEntry) session.get(BalloonStatsEntryImpl.class,
                        primaryKey);

                if (balloonStatsEntry != null) {
                    cacheResult(balloonStatsEntry);
                } else {
                    EntityCacheUtil.putResult(BalloonStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
                        BalloonStatsEntryImpl.class, primaryKey,
                        _nullBalloonStatsEntry);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(BalloonStatsEntryModelImpl.ENTITY_CACHE_ENABLED,
                    BalloonStatsEntryImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return balloonStatsEntry;
    }

    /**
     * Returns the balloon stats entry with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the balloon stats entry
     * @return the balloon stats entry, or <code>null</code> if a balloon stats entry with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public BalloonStatsEntry fetchByPrimaryKey(long id)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the balloon stats entries.
     *
     * @return the balloon stats entries
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BalloonStatsEntry> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the balloon stats entries.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonStatsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of balloon stats entries
     * @param end the upper bound of the range of balloon stats entries (not inclusive)
     * @return the range of balloon stats entries
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BalloonStatsEntry> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the balloon stats entries.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonStatsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of balloon stats entries
     * @param end the upper bound of the range of balloon stats entries (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of balloon stats entries
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<BalloonStatsEntry> findAll(int start, int end,
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

        List<BalloonStatsEntry> list = (List<BalloonStatsEntry>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_BALLOONSTATSENTRY);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_BALLOONSTATSENTRY;

                if (pagination) {
                    sql = sql.concat(BalloonStatsEntryModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<BalloonStatsEntry>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<BalloonStatsEntry>(list);
                } else {
                    list = (List<BalloonStatsEntry>) QueryUtil.list(q,
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
     * Removes all the balloon stats entries from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (BalloonStatsEntry balloonStatsEntry : findAll()) {
            remove(balloonStatsEntry);
        }
    }

    /**
     * Returns the number of balloon stats entries.
     *
     * @return the number of balloon stats entries
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

                Query q = session.createQuery(_SQL_COUNT_BALLOONSTATSENTRY);

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
     * Initializes the balloon stats entry persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.BalloonStatsEntry")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<BalloonStatsEntry>> listenersList = new ArrayList<ModelListener<BalloonStatsEntry>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<BalloonStatsEntry>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(BalloonStatsEntryImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
