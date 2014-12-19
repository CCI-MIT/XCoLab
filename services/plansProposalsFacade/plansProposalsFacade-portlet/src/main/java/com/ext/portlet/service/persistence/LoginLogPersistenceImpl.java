package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchLoginLogException;
import com.ext.portlet.model.LoginLog;
import com.ext.portlet.model.impl.LoginLogImpl;
import com.ext.portlet.model.impl.LoginLogModelImpl;
import com.ext.portlet.service.persistence.LoginLogPersistence;

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
 * The persistence implementation for the login log service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LoginLogPersistence
 * @see LoginLogUtil
 * @generated
 */
public class LoginLogPersistenceImpl extends BasePersistenceImpl<LoginLog>
    implements LoginLogPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link LoginLogUtil} to access the login log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = LoginLogImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LoginLogModelImpl.ENTITY_CACHE_ENABLED,
            LoginLogModelImpl.FINDER_CACHE_ENABLED, LoginLogImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LoginLogModelImpl.ENTITY_CACHE_ENABLED,
            LoginLogModelImpl.FINDER_CACHE_ENABLED, LoginLogImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LoginLogModelImpl.ENTITY_CACHE_ENABLED,
            LoginLogModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_LOGINLOG = "SELECT loginLog FROM LoginLog loginLog";
    private static final String _SQL_COUNT_LOGINLOG = "SELECT COUNT(loginLog) FROM LoginLog loginLog";
    private static final String _ORDER_BY_ENTITY_ALIAS = "loginLog.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LoginLog exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(LoginLogPersistenceImpl.class);
    private static LoginLog _nullLoginLog = new LoginLogImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<LoginLog> toCacheModel() {
                return _nullLoginLogCacheModel;
            }
        };

    private static CacheModel<LoginLog> _nullLoginLogCacheModel = new CacheModel<LoginLog>() {
            @Override
            public LoginLog toEntityModel() {
                return _nullLoginLog;
            }
        };

    public LoginLogPersistenceImpl() {
        setModelClass(LoginLog.class);
    }

    /**
     * Caches the login log in the entity cache if it is enabled.
     *
     * @param loginLog the login log
     */
    @Override
    public void cacheResult(LoginLog loginLog) {
        EntityCacheUtil.putResult(LoginLogModelImpl.ENTITY_CACHE_ENABLED,
            LoginLogImpl.class, loginLog.getPrimaryKey(), loginLog);

        loginLog.resetOriginalValues();
    }

    /**
     * Caches the login logs in the entity cache if it is enabled.
     *
     * @param loginLogs the login logs
     */
    @Override
    public void cacheResult(List<LoginLog> loginLogs) {
        for (LoginLog loginLog : loginLogs) {
            if (EntityCacheUtil.getResult(
                        LoginLogModelImpl.ENTITY_CACHE_ENABLED,
                        LoginLogImpl.class, loginLog.getPrimaryKey()) == null) {
                cacheResult(loginLog);
            } else {
                loginLog.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all login logs.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(LoginLogImpl.class.getName());
        }

        EntityCacheUtil.clearCache(LoginLogImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the login log.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(LoginLog loginLog) {
        EntityCacheUtil.removeResult(LoginLogModelImpl.ENTITY_CACHE_ENABLED,
            LoginLogImpl.class, loginLog.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<LoginLog> loginLogs) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (LoginLog loginLog : loginLogs) {
            EntityCacheUtil.removeResult(LoginLogModelImpl.ENTITY_CACHE_ENABLED,
                LoginLogImpl.class, loginLog.getPrimaryKey());
        }
    }

    /**
     * Creates a new login log with the primary key. Does not add the login log to the database.
     *
     * @param pk the primary key for the new login log
     * @return the new login log
     */
    @Override
    public LoginLog create(long pk) {
        LoginLog loginLog = new LoginLogImpl();

        loginLog.setNew(true);
        loginLog.setPrimaryKey(pk);

        return loginLog;
    }

    /**
     * Removes the login log with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param pk the primary key of the login log
     * @return the login log that was removed
     * @throws com.ext.portlet.NoSuchLoginLogException if a login log with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LoginLog remove(long pk)
        throws NoSuchLoginLogException, SystemException {
        return remove((Serializable) pk);
    }

    /**
     * Removes the login log with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the login log
     * @return the login log that was removed
     * @throws com.ext.portlet.NoSuchLoginLogException if a login log with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LoginLog remove(Serializable primaryKey)
        throws NoSuchLoginLogException, SystemException {
        Session session = null;

        try {
            session = openSession();

            LoginLog loginLog = (LoginLog) session.get(LoginLogImpl.class,
                    primaryKey);

            if (loginLog == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchLoginLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(loginLog);
        } catch (NoSuchLoginLogException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected LoginLog removeImpl(LoginLog loginLog) throws SystemException {
        loginLog = toUnwrappedModel(loginLog);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(loginLog)) {
                loginLog = (LoginLog) session.get(LoginLogImpl.class,
                        loginLog.getPrimaryKeyObj());
            }

            if (loginLog != null) {
                session.delete(loginLog);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (loginLog != null) {
            clearCache(loginLog);
        }

        return loginLog;
    }

    @Override
    public LoginLog updateImpl(com.ext.portlet.model.LoginLog loginLog)
        throws SystemException {
        loginLog = toUnwrappedModel(loginLog);

        boolean isNew = loginLog.isNew();

        Session session = null;

        try {
            session = openSession();

            if (loginLog.isNew()) {
                session.save(loginLog);

                loginLog.setNew(false);
            } else {
                session.merge(loginLog);
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

        EntityCacheUtil.putResult(LoginLogModelImpl.ENTITY_CACHE_ENABLED,
            LoginLogImpl.class, loginLog.getPrimaryKey(), loginLog);

        return loginLog;
    }

    protected LoginLog toUnwrappedModel(LoginLog loginLog) {
        if (loginLog instanceof LoginLogImpl) {
            return loginLog;
        }

        LoginLogImpl loginLogImpl = new LoginLogImpl();

        loginLogImpl.setNew(loginLog.isNew());
        loginLogImpl.setPrimaryKey(loginLog.getPrimaryKey());

        loginLogImpl.setPk(loginLog.getPk());
        loginLogImpl.setUserId(loginLog.getUserId());
        loginLogImpl.setCreateDate(loginLog.getCreateDate());
        loginLogImpl.setIpAddress(loginLog.getIpAddress());
        loginLogImpl.setCity(loginLog.getCity());
        loginLogImpl.setCountry(loginLog.getCountry());
        loginLogImpl.setEntryUrl(loginLog.getEntryUrl());

        return loginLogImpl;
    }

    /**
     * Returns the login log with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the login log
     * @return the login log
     * @throws com.ext.portlet.NoSuchLoginLogException if a login log with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LoginLog findByPrimaryKey(Serializable primaryKey)
        throws NoSuchLoginLogException, SystemException {
        LoginLog loginLog = fetchByPrimaryKey(primaryKey);

        if (loginLog == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchLoginLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return loginLog;
    }

    /**
     * Returns the login log with the primary key or throws a {@link com.ext.portlet.NoSuchLoginLogException} if it could not be found.
     *
     * @param pk the primary key of the login log
     * @return the login log
     * @throws com.ext.portlet.NoSuchLoginLogException if a login log with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LoginLog findByPrimaryKey(long pk)
        throws NoSuchLoginLogException, SystemException {
        return findByPrimaryKey((Serializable) pk);
    }

    /**
     * Returns the login log with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the login log
     * @return the login log, or <code>null</code> if a login log with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LoginLog fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        LoginLog loginLog = (LoginLog) EntityCacheUtil.getResult(LoginLogModelImpl.ENTITY_CACHE_ENABLED,
                LoginLogImpl.class, primaryKey);

        if (loginLog == _nullLoginLog) {
            return null;
        }

        if (loginLog == null) {
            Session session = null;

            try {
                session = openSession();

                loginLog = (LoginLog) session.get(LoginLogImpl.class, primaryKey);

                if (loginLog != null) {
                    cacheResult(loginLog);
                } else {
                    EntityCacheUtil.putResult(LoginLogModelImpl.ENTITY_CACHE_ENABLED,
                        LoginLogImpl.class, primaryKey, _nullLoginLog);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(LoginLogModelImpl.ENTITY_CACHE_ENABLED,
                    LoginLogImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return loginLog;
    }

    /**
     * Returns the login log with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param pk the primary key of the login log
     * @return the login log, or <code>null</code> if a login log with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public LoginLog fetchByPrimaryKey(long pk) throws SystemException {
        return fetchByPrimaryKey((Serializable) pk);
    }

    /**
     * Returns all the login logs.
     *
     * @return the login logs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LoginLog> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the login logs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.LoginLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of login logs
     * @param end the upper bound of the range of login logs (not inclusive)
     * @return the range of login logs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LoginLog> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the login logs.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.LoginLogModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of login logs
     * @param end the upper bound of the range of login logs (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of login logs
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<LoginLog> findAll(int start, int end,
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

        List<LoginLog> list = (List<LoginLog>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_LOGINLOG);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_LOGINLOG;

                if (pagination) {
                    sql = sql.concat(LoginLogModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<LoginLog>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<LoginLog>(list);
                } else {
                    list = (List<LoginLog>) QueryUtil.list(q, getDialect(),
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
     * Removes all the login logs from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (LoginLog loginLog : findAll()) {
            remove(loginLog);
        }
    }

    /**
     * Returns the number of login logs.
     *
     * @return the number of login logs
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

                Query q = session.createQuery(_SQL_COUNT_LOGINLOG);

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
     * Initializes the login log persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.LoginLog")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<LoginLog>> listenersList = new ArrayList<ModelListener<LoginLog>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<LoginLog>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(LoginLogImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
