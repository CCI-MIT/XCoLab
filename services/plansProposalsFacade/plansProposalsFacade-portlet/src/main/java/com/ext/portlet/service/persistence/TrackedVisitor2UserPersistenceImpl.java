package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchTrackedVisitor2UserException;
import com.ext.portlet.model.TrackedVisitor2User;
import com.ext.portlet.model.impl.TrackedVisitor2UserImpl;
import com.ext.portlet.model.impl.TrackedVisitor2UserModelImpl;
import com.ext.portlet.service.persistence.TrackedVisitor2UserPersistence;

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
 * The persistence implementation for the tracked visitor2 user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrackedVisitor2UserPersistence
 * @see TrackedVisitor2UserUtil
 * @generated
 */
public class TrackedVisitor2UserPersistenceImpl extends BasePersistenceImpl<TrackedVisitor2User>
    implements TrackedVisitor2UserPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link TrackedVisitor2UserUtil} to access the tracked visitor2 user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = TrackedVisitor2UserImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(TrackedVisitor2UserModelImpl.ENTITY_CACHE_ENABLED,
            TrackedVisitor2UserModelImpl.FINDER_CACHE_ENABLED,
            TrackedVisitor2UserImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(TrackedVisitor2UserModelImpl.ENTITY_CACHE_ENABLED,
            TrackedVisitor2UserModelImpl.FINDER_CACHE_ENABLED,
            TrackedVisitor2UserImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(TrackedVisitor2UserModelImpl.ENTITY_CACHE_ENABLED,
            TrackedVisitor2UserModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_TRACKEDVISITOR2USER = "SELECT trackedVisitor2User FROM TrackedVisitor2User trackedVisitor2User";
    private static final String _SQL_COUNT_TRACKEDVISITOR2USER = "SELECT COUNT(trackedVisitor2User) FROM TrackedVisitor2User trackedVisitor2User";
    private static final String _ORDER_BY_ENTITY_ALIAS = "trackedVisitor2User.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TrackedVisitor2User exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(TrackedVisitor2UserPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id", "uuid"
            });
    private static TrackedVisitor2User _nullTrackedVisitor2User = new TrackedVisitor2UserImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<TrackedVisitor2User> toCacheModel() {
                return _nullTrackedVisitor2UserCacheModel;
            }
        };

    private static CacheModel<TrackedVisitor2User> _nullTrackedVisitor2UserCacheModel =
        new CacheModel<TrackedVisitor2User>() {
            @Override
            public TrackedVisitor2User toEntityModel() {
                return _nullTrackedVisitor2User;
            }
        };

    public TrackedVisitor2UserPersistenceImpl() {
        setModelClass(TrackedVisitor2User.class);
    }

    /**
     * Caches the tracked visitor2 user in the entity cache if it is enabled.
     *
     * @param trackedVisitor2User the tracked visitor2 user
     */
    @Override
    public void cacheResult(TrackedVisitor2User trackedVisitor2User) {
        EntityCacheUtil.putResult(TrackedVisitor2UserModelImpl.ENTITY_CACHE_ENABLED,
            TrackedVisitor2UserImpl.class, trackedVisitor2User.getPrimaryKey(),
            trackedVisitor2User);

        trackedVisitor2User.resetOriginalValues();
    }

    /**
     * Caches the tracked visitor2 users in the entity cache if it is enabled.
     *
     * @param trackedVisitor2Users the tracked visitor2 users
     */
    @Override
    public void cacheResult(List<TrackedVisitor2User> trackedVisitor2Users) {
        for (TrackedVisitor2User trackedVisitor2User : trackedVisitor2Users) {
            if (EntityCacheUtil.getResult(
                        TrackedVisitor2UserModelImpl.ENTITY_CACHE_ENABLED,
                        TrackedVisitor2UserImpl.class,
                        trackedVisitor2User.getPrimaryKey()) == null) {
                cacheResult(trackedVisitor2User);
            } else {
                trackedVisitor2User.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all tracked visitor2 users.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(TrackedVisitor2UserImpl.class.getName());
        }

        EntityCacheUtil.clearCache(TrackedVisitor2UserImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the tracked visitor2 user.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(TrackedVisitor2User trackedVisitor2User) {
        EntityCacheUtil.removeResult(TrackedVisitor2UserModelImpl.ENTITY_CACHE_ENABLED,
            TrackedVisitor2UserImpl.class, trackedVisitor2User.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<TrackedVisitor2User> trackedVisitor2Users) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (TrackedVisitor2User trackedVisitor2User : trackedVisitor2Users) {
            EntityCacheUtil.removeResult(TrackedVisitor2UserModelImpl.ENTITY_CACHE_ENABLED,
                TrackedVisitor2UserImpl.class,
                trackedVisitor2User.getPrimaryKey());
        }
    }

    /**
     * Creates a new tracked visitor2 user with the primary key. Does not add the tracked visitor2 user to the database.
     *
     * @param id the primary key for the new tracked visitor2 user
     * @return the new tracked visitor2 user
     */
    @Override
    public TrackedVisitor2User create(long id) {
        TrackedVisitor2User trackedVisitor2User = new TrackedVisitor2UserImpl();

        trackedVisitor2User.setNew(true);
        trackedVisitor2User.setPrimaryKey(id);

        return trackedVisitor2User;
    }

    /**
     * Removes the tracked visitor2 user with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the tracked visitor2 user
     * @return the tracked visitor2 user that was removed
     * @throws com.ext.portlet.NoSuchTrackedVisitor2UserException if a tracked visitor2 user with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TrackedVisitor2User remove(long id)
        throws NoSuchTrackedVisitor2UserException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the tracked visitor2 user with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the tracked visitor2 user
     * @return the tracked visitor2 user that was removed
     * @throws com.ext.portlet.NoSuchTrackedVisitor2UserException if a tracked visitor2 user with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TrackedVisitor2User remove(Serializable primaryKey)
        throws NoSuchTrackedVisitor2UserException, SystemException {
        Session session = null;

        try {
            session = openSession();

            TrackedVisitor2User trackedVisitor2User = (TrackedVisitor2User) session.get(TrackedVisitor2UserImpl.class,
                    primaryKey);

            if (trackedVisitor2User == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchTrackedVisitor2UserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(trackedVisitor2User);
        } catch (NoSuchTrackedVisitor2UserException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected TrackedVisitor2User removeImpl(
        TrackedVisitor2User trackedVisitor2User) throws SystemException {
        trackedVisitor2User = toUnwrappedModel(trackedVisitor2User);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(trackedVisitor2User)) {
                trackedVisitor2User = (TrackedVisitor2User) session.get(TrackedVisitor2UserImpl.class,
                        trackedVisitor2User.getPrimaryKeyObj());
            }

            if (trackedVisitor2User != null) {
                session.delete(trackedVisitor2User);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (trackedVisitor2User != null) {
            clearCache(trackedVisitor2User);
        }

        return trackedVisitor2User;
    }

    @Override
    public TrackedVisitor2User updateImpl(
        com.ext.portlet.model.TrackedVisitor2User trackedVisitor2User)
        throws SystemException {
        trackedVisitor2User = toUnwrappedModel(trackedVisitor2User);

        boolean isNew = trackedVisitor2User.isNew();

        Session session = null;

        try {
            session = openSession();

            if (trackedVisitor2User.isNew()) {
                session.save(trackedVisitor2User);

                trackedVisitor2User.setNew(false);
            } else {
                session.merge(trackedVisitor2User);
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

        EntityCacheUtil.putResult(TrackedVisitor2UserModelImpl.ENTITY_CACHE_ENABLED,
            TrackedVisitor2UserImpl.class, trackedVisitor2User.getPrimaryKey(),
            trackedVisitor2User);

        return trackedVisitor2User;
    }

    protected TrackedVisitor2User toUnwrappedModel(
        TrackedVisitor2User trackedVisitor2User) {
        if (trackedVisitor2User instanceof TrackedVisitor2UserImpl) {
            return trackedVisitor2User;
        }

        TrackedVisitor2UserImpl trackedVisitor2UserImpl = new TrackedVisitor2UserImpl();

        trackedVisitor2UserImpl.setNew(trackedVisitor2User.isNew());
        trackedVisitor2UserImpl.setPrimaryKey(trackedVisitor2User.getPrimaryKey());

        trackedVisitor2UserImpl.setId(trackedVisitor2User.getId());
        trackedVisitor2UserImpl.setUuid(trackedVisitor2User.getUuid());
        trackedVisitor2UserImpl.setUserId(trackedVisitor2User.getUserId());
        trackedVisitor2UserImpl.setCreateDate(trackedVisitor2User.getCreateDate());

        return trackedVisitor2UserImpl;
    }

    /**
     * Returns the tracked visitor2 user with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the tracked visitor2 user
     * @return the tracked visitor2 user
     * @throws com.ext.portlet.NoSuchTrackedVisitor2UserException if a tracked visitor2 user with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TrackedVisitor2User findByPrimaryKey(Serializable primaryKey)
        throws NoSuchTrackedVisitor2UserException, SystemException {
        TrackedVisitor2User trackedVisitor2User = fetchByPrimaryKey(primaryKey);

        if (trackedVisitor2User == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchTrackedVisitor2UserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return trackedVisitor2User;
    }

    /**
     * Returns the tracked visitor2 user with the primary key or throws a {@link com.ext.portlet.NoSuchTrackedVisitor2UserException} if it could not be found.
     *
     * @param id the primary key of the tracked visitor2 user
     * @return the tracked visitor2 user
     * @throws com.ext.portlet.NoSuchTrackedVisitor2UserException if a tracked visitor2 user with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TrackedVisitor2User findByPrimaryKey(long id)
        throws NoSuchTrackedVisitor2UserException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the tracked visitor2 user with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the tracked visitor2 user
     * @return the tracked visitor2 user, or <code>null</code> if a tracked visitor2 user with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TrackedVisitor2User fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        TrackedVisitor2User trackedVisitor2User = (TrackedVisitor2User) EntityCacheUtil.getResult(TrackedVisitor2UserModelImpl.ENTITY_CACHE_ENABLED,
                TrackedVisitor2UserImpl.class, primaryKey);

        if (trackedVisitor2User == _nullTrackedVisitor2User) {
            return null;
        }

        if (trackedVisitor2User == null) {
            Session session = null;

            try {
                session = openSession();

                trackedVisitor2User = (TrackedVisitor2User) session.get(TrackedVisitor2UserImpl.class,
                        primaryKey);

                if (trackedVisitor2User != null) {
                    cacheResult(trackedVisitor2User);
                } else {
                    EntityCacheUtil.putResult(TrackedVisitor2UserModelImpl.ENTITY_CACHE_ENABLED,
                        TrackedVisitor2UserImpl.class, primaryKey,
                        _nullTrackedVisitor2User);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(TrackedVisitor2UserModelImpl.ENTITY_CACHE_ENABLED,
                    TrackedVisitor2UserImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return trackedVisitor2User;
    }

    /**
     * Returns the tracked visitor2 user with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the tracked visitor2 user
     * @return the tracked visitor2 user, or <code>null</code> if a tracked visitor2 user with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public TrackedVisitor2User fetchByPrimaryKey(long id)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the tracked visitor2 users.
     *
     * @return the tracked visitor2 users
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<TrackedVisitor2User> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the tracked visitor2 users.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.TrackedVisitor2UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of tracked visitor2 users
     * @param end the upper bound of the range of tracked visitor2 users (not inclusive)
     * @return the range of tracked visitor2 users
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<TrackedVisitor2User> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the tracked visitor2 users.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.TrackedVisitor2UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of tracked visitor2 users
     * @param end the upper bound of the range of tracked visitor2 users (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of tracked visitor2 users
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<TrackedVisitor2User> findAll(int start, int end,
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

        List<TrackedVisitor2User> list = (List<TrackedVisitor2User>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_TRACKEDVISITOR2USER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_TRACKEDVISITOR2USER;

                if (pagination) {
                    sql = sql.concat(TrackedVisitor2UserModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<TrackedVisitor2User>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<TrackedVisitor2User>(list);
                } else {
                    list = (List<TrackedVisitor2User>) QueryUtil.list(q,
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
     * Removes all the tracked visitor2 users from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (TrackedVisitor2User trackedVisitor2User : findAll()) {
            remove(trackedVisitor2User);
        }
    }

    /**
     * Returns the number of tracked visitor2 users.
     *
     * @return the number of tracked visitor2 users
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

                Query q = session.createQuery(_SQL_COUNT_TRACKEDVISITOR2USER);

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
     * Initializes the tracked visitor2 user persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.TrackedVisitor2User")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<TrackedVisitor2User>> listenersList = new ArrayList<ModelListener<TrackedVisitor2User>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<TrackedVisitor2User>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(TrackedVisitor2UserImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
