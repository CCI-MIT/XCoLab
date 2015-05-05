package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchUsers_RolesException;
import com.ext.portlet.model.Users_Roles;
import com.ext.portlet.model.impl.Users_RolesImpl;
import com.ext.portlet.model.impl.Users_RolesModelImpl;
import com.ext.portlet.service.persistence.Users_RolesPersistence;

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
 * The persistence implementation for the users_ roles service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Users_RolesPersistence
 * @see Users_RolesUtil
 * @generated
 */
public class Users_RolesPersistenceImpl extends BasePersistenceImpl<Users_Roles>
    implements Users_RolesPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link Users_RolesUtil} to access the users_ roles persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = Users_RolesImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(Users_RolesModelImpl.ENTITY_CACHE_ENABLED,
            Users_RolesModelImpl.FINDER_CACHE_ENABLED, Users_RolesImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(Users_RolesModelImpl.ENTITY_CACHE_ENABLED,
            Users_RolesModelImpl.FINDER_CACHE_ENABLED, Users_RolesImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(Users_RolesModelImpl.ENTITY_CACHE_ENABLED,
            Users_RolesModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_USERS_ROLES = "SELECT users_Roles FROM Users_Roles users_Roles";
    private static final String _SQL_COUNT_USERS_ROLES = "SELECT COUNT(users_Roles) FROM Users_Roles users_Roles";
    private static final String _ORDER_BY_ENTITY_ALIAS = "users_Roles.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Users_Roles exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(Users_RolesPersistenceImpl.class);
    private static Users_Roles _nullUsers_Roles = new Users_RolesImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<Users_Roles> toCacheModel() {
                return _nullUsers_RolesCacheModel;
            }
        };

    private static CacheModel<Users_Roles> _nullUsers_RolesCacheModel = new CacheModel<Users_Roles>() {
            @Override
            public Users_Roles toEntityModel() {
                return _nullUsers_Roles;
            }
        };

    public Users_RolesPersistenceImpl() {
        setModelClass(Users_Roles.class);
    }

    /**
     * Caches the users_ roles in the entity cache if it is enabled.
     *
     * @param users_Roles the users_ roles
     */
    @Override
    public void cacheResult(Users_Roles users_Roles) {
        EntityCacheUtil.putResult(Users_RolesModelImpl.ENTITY_CACHE_ENABLED,
            Users_RolesImpl.class, users_Roles.getPrimaryKey(), users_Roles);

        users_Roles.resetOriginalValues();
    }

    /**
     * Caches the users_ roleses in the entity cache if it is enabled.
     *
     * @param users_Roleses the users_ roleses
     */
    @Override
    public void cacheResult(List<Users_Roles> users_Roleses) {
        for (Users_Roles users_Roles : users_Roleses) {
            if (EntityCacheUtil.getResult(
                        Users_RolesModelImpl.ENTITY_CACHE_ENABLED,
                        Users_RolesImpl.class, users_Roles.getPrimaryKey()) == null) {
                cacheResult(users_Roles);
            } else {
                users_Roles.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all users_ roleses.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(Users_RolesImpl.class.getName());
        }

        EntityCacheUtil.clearCache(Users_RolesImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the users_ roles.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(Users_Roles users_Roles) {
        EntityCacheUtil.removeResult(Users_RolesModelImpl.ENTITY_CACHE_ENABLED,
            Users_RolesImpl.class, users_Roles.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<Users_Roles> users_Roleses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (Users_Roles users_Roles : users_Roleses) {
            EntityCacheUtil.removeResult(Users_RolesModelImpl.ENTITY_CACHE_ENABLED,
                Users_RolesImpl.class, users_Roles.getPrimaryKey());
        }
    }

    /**
     * Creates a new users_ roles with the primary key. Does not add the users_ roles to the database.
     *
     * @param users_RolesPK the primary key for the new users_ roles
     * @return the new users_ roles
     */
    @Override
    public Users_Roles create(Users_RolesPK users_RolesPK) {
        Users_Roles users_Roles = new Users_RolesImpl();

        users_Roles.setNew(true);
        users_Roles.setPrimaryKey(users_RolesPK);

        return users_Roles;
    }

    /**
     * Removes the users_ roles with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param users_RolesPK the primary key of the users_ roles
     * @return the users_ roles that was removed
     * @throws com.ext.portlet.NoSuchUsers_RolesException if a users_ roles with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Users_Roles remove(Users_RolesPK users_RolesPK)
        throws NoSuchUsers_RolesException, SystemException {
        return remove((Serializable) users_RolesPK);
    }

    /**
     * Removes the users_ roles with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the users_ roles
     * @return the users_ roles that was removed
     * @throws com.ext.portlet.NoSuchUsers_RolesException if a users_ roles with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Users_Roles remove(Serializable primaryKey)
        throws NoSuchUsers_RolesException, SystemException {
        Session session = null;

        try {
            session = openSession();

            Users_Roles users_Roles = (Users_Roles) session.get(Users_RolesImpl.class,
                    primaryKey);

            if (users_Roles == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchUsers_RolesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(users_Roles);
        } catch (NoSuchUsers_RolesException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected Users_Roles removeImpl(Users_Roles users_Roles)
        throws SystemException {
        users_Roles = toUnwrappedModel(users_Roles);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(users_Roles)) {
                users_Roles = (Users_Roles) session.get(Users_RolesImpl.class,
                        users_Roles.getPrimaryKeyObj());
            }

            if (users_Roles != null) {
                session.delete(users_Roles);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (users_Roles != null) {
            clearCache(users_Roles);
        }

        return users_Roles;
    }

    @Override
    public Users_Roles updateImpl(com.ext.portlet.model.Users_Roles users_Roles)
        throws SystemException {
        users_Roles = toUnwrappedModel(users_Roles);

        boolean isNew = users_Roles.isNew();

        Session session = null;

        try {
            session = openSession();

            if (users_Roles.isNew()) {
                session.save(users_Roles);

                users_Roles.setNew(false);
            } else {
                session.merge(users_Roles);
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

        EntityCacheUtil.putResult(Users_RolesModelImpl.ENTITY_CACHE_ENABLED,
            Users_RolesImpl.class, users_Roles.getPrimaryKey(), users_Roles);

        return users_Roles;
    }

    protected Users_Roles toUnwrappedModel(Users_Roles users_Roles) {
        if (users_Roles instanceof Users_RolesImpl) {
            return users_Roles;
        }

        Users_RolesImpl users_RolesImpl = new Users_RolesImpl();

        users_RolesImpl.setNew(users_Roles.isNew());
        users_RolesImpl.setPrimaryKey(users_Roles.getPrimaryKey());

        users_RolesImpl.setUserId(users_Roles.getUserId());
        users_RolesImpl.setRoleId(users_Roles.getRoleId());

        return users_RolesImpl;
    }

    /**
     * Returns the users_ roles with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the users_ roles
     * @return the users_ roles
     * @throws com.ext.portlet.NoSuchUsers_RolesException if a users_ roles with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Users_Roles findByPrimaryKey(Serializable primaryKey)
        throws NoSuchUsers_RolesException, SystemException {
        Users_Roles users_Roles = fetchByPrimaryKey(primaryKey);

        if (users_Roles == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchUsers_RolesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return users_Roles;
    }

    /**
     * Returns the users_ roles with the primary key or throws a {@link com.ext.portlet.NoSuchUsers_RolesException} if it could not be found.
     *
     * @param users_RolesPK the primary key of the users_ roles
     * @return the users_ roles
     * @throws com.ext.portlet.NoSuchUsers_RolesException if a users_ roles with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Users_Roles findByPrimaryKey(Users_RolesPK users_RolesPK)
        throws NoSuchUsers_RolesException, SystemException {
        return findByPrimaryKey((Serializable) users_RolesPK);
    }

    /**
     * Returns the users_ roles with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the users_ roles
     * @return the users_ roles, or <code>null</code> if a users_ roles with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Users_Roles fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        Users_Roles users_Roles = (Users_Roles) EntityCacheUtil.getResult(Users_RolesModelImpl.ENTITY_CACHE_ENABLED,
                Users_RolesImpl.class, primaryKey);

        if (users_Roles == _nullUsers_Roles) {
            return null;
        }

        if (users_Roles == null) {
            Session session = null;

            try {
                session = openSession();

                users_Roles = (Users_Roles) session.get(Users_RolesImpl.class,
                        primaryKey);

                if (users_Roles != null) {
                    cacheResult(users_Roles);
                } else {
                    EntityCacheUtil.putResult(Users_RolesModelImpl.ENTITY_CACHE_ENABLED,
                        Users_RolesImpl.class, primaryKey, _nullUsers_Roles);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(Users_RolesModelImpl.ENTITY_CACHE_ENABLED,
                    Users_RolesImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return users_Roles;
    }

    /**
     * Returns the users_ roles with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param users_RolesPK the primary key of the users_ roles
     * @return the users_ roles, or <code>null</code> if a users_ roles with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Users_Roles fetchByPrimaryKey(Users_RolesPK users_RolesPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) users_RolesPK);
    }

    /**
     * Returns all the users_ roleses.
     *
     * @return the users_ roleses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Users_Roles> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the users_ roleses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Users_RolesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of users_ roleses
     * @param end the upper bound of the range of users_ roleses (not inclusive)
     * @return the range of users_ roleses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Users_Roles> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the users_ roleses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Users_RolesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of users_ roleses
     * @param end the upper bound of the range of users_ roleses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of users_ roleses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Users_Roles> findAll(int start, int end,
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

        List<Users_Roles> list = (List<Users_Roles>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_USERS_ROLES);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_USERS_ROLES;

                if (pagination) {
                    sql = sql.concat(Users_RolesModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<Users_Roles>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Users_Roles>(list);
                } else {
                    list = (List<Users_Roles>) QueryUtil.list(q, getDialect(),
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
     * Removes all the users_ roleses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (Users_Roles users_Roles : findAll()) {
            remove(users_Roles);
        }
    }

    /**
     * Returns the number of users_ roleses.
     *
     * @return the number of users_ roleses
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

                Query q = session.createQuery(_SQL_COUNT_USERS_ROLES);

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
     * Initializes the users_ roles persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.Users_Roles")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<Users_Roles>> listenersList = new ArrayList<ModelListener<Users_Roles>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<Users_Roles>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(Users_RolesImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
