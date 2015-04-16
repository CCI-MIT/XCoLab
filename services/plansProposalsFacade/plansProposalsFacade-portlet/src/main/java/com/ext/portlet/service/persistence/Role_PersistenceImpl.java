package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchRole_Exception;
import com.ext.portlet.model.Role_;
import com.ext.portlet.model.impl.Role_Impl;
import com.ext.portlet.model.impl.Role_ModelImpl;
import com.ext.portlet.service.persistence.Role_Persistence;
import com.ext.portlet.service.persistence.User_Persistence;

import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.service.persistence.impl.TableMapper;
import com.liferay.portal.service.persistence.impl.TableMapperFactory;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the role_ service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Role_Persistence
 * @see Role_Util
 * @generated
 */
public class Role_PersistenceImpl extends BasePersistenceImpl<Role_>
    implements Role_Persistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link Role_Util} to access the role_ persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = Role_Impl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(Role_ModelImpl.ENTITY_CACHE_ENABLED,
            Role_ModelImpl.FINDER_CACHE_ENABLED, Role_Impl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(Role_ModelImpl.ENTITY_CACHE_ENABLED,
            Role_ModelImpl.FINDER_CACHE_ENABLED, Role_Impl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(Role_ModelImpl.ENTITY_CACHE_ENABLED,
            Role_ModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_ROLE_ = "SELECT role_ FROM Role_ role_";
    private static final String _SQL_COUNT_ROLE_ = "SELECT COUNT(role_) FROM Role_ role_";
    private static final String _ORDER_BY_ENTITY_ALIAS = "role_.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Role_ exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(Role_PersistenceImpl.class);
    private static Role_ _nullRole_ = new Role_Impl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<Role_> toCacheModel() {
                return _nullRole_CacheModel;
            }
        };

    private static CacheModel<Role_> _nullRole_CacheModel = new CacheModel<Role_>() {
            @Override
            public Role_ toEntityModel() {
                return _nullRole_;
            }
        };

    @BeanReference(type = User_Persistence.class)
    protected User_Persistence user_Persistence;
    protected TableMapper<Role_, com.ext.portlet.model.User_> role_ToUser_TableMapper;

    public Role_PersistenceImpl() {
        setModelClass(Role_.class);
    }

    /**
     * Caches the role_ in the entity cache if it is enabled.
     *
     * @param role_ the role_
     */
    @Override
    public void cacheResult(Role_ role_) {
        EntityCacheUtil.putResult(Role_ModelImpl.ENTITY_CACHE_ENABLED,
            Role_Impl.class, role_.getPrimaryKey(), role_);

        role_.resetOriginalValues();
    }

    /**
     * Caches the role_s in the entity cache if it is enabled.
     *
     * @param role_s the role_s
     */
    @Override
    public void cacheResult(List<Role_> role_s) {
        for (Role_ role_ : role_s) {
            if (EntityCacheUtil.getResult(Role_ModelImpl.ENTITY_CACHE_ENABLED,
                        Role_Impl.class, role_.getPrimaryKey()) == null) {
                cacheResult(role_);
            } else {
                role_.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all role_s.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(Role_Impl.class.getName());
        }

        EntityCacheUtil.clearCache(Role_Impl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the role_.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(Role_ role_) {
        EntityCacheUtil.removeResult(Role_ModelImpl.ENTITY_CACHE_ENABLED,
            Role_Impl.class, role_.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<Role_> role_s) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (Role_ role_ : role_s) {
            EntityCacheUtil.removeResult(Role_ModelImpl.ENTITY_CACHE_ENABLED,
                Role_Impl.class, role_.getPrimaryKey());
        }
    }

    /**
     * Creates a new role_ with the primary key. Does not add the role_ to the database.
     *
     * @param roleId the primary key for the new role_
     * @return the new role_
     */
    @Override
    public Role_ create(long roleId) {
        Role_ role_ = new Role_Impl();

        role_.setNew(true);
        role_.setPrimaryKey(roleId);

        return role_;
    }

    /**
     * Removes the role_ with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param roleId the primary key of the role_
     * @return the role_ that was removed
     * @throws com.ext.portlet.NoSuchRole_Exception if a role_ with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Role_ remove(long roleId)
        throws NoSuchRole_Exception, SystemException {
        return remove((Serializable) roleId);
    }

    /**
     * Removes the role_ with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the role_
     * @return the role_ that was removed
     * @throws com.ext.portlet.NoSuchRole_Exception if a role_ with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Role_ remove(Serializable primaryKey)
        throws NoSuchRole_Exception, SystemException {
        Session session = null;

        try {
            session = openSession();

            Role_ role_ = (Role_) session.get(Role_Impl.class, primaryKey);

            if (role_ == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchRole_Exception(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(role_);
        } catch (NoSuchRole_Exception nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected Role_ removeImpl(Role_ role_) throws SystemException {
        role_ = toUnwrappedModel(role_);

        role_ToUser_TableMapper.deleteLeftPrimaryKeyTableMappings(role_.getPrimaryKey());

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(role_)) {
                role_ = (Role_) session.get(Role_Impl.class,
                        role_.getPrimaryKeyObj());
            }

            if (role_ != null) {
                session.delete(role_);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (role_ != null) {
            clearCache(role_);
        }

        return role_;
    }

    @Override
    public Role_ updateImpl(com.ext.portlet.model.Role_ role_)
        throws SystemException {
        role_ = toUnwrappedModel(role_);

        boolean isNew = role_.isNew();

        Session session = null;

        try {
            session = openSession();

            if (role_.isNew()) {
                session.save(role_);

                role_.setNew(false);
            } else {
                session.merge(role_);
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

        EntityCacheUtil.putResult(Role_ModelImpl.ENTITY_CACHE_ENABLED,
            Role_Impl.class, role_.getPrimaryKey(), role_);

        return role_;
    }

    protected Role_ toUnwrappedModel(Role_ role_) {
        if (role_ instanceof Role_Impl) {
            return role_;
        }

        Role_Impl role_Impl = new Role_Impl();

        role_Impl.setNew(role_.isNew());
        role_Impl.setPrimaryKey(role_.getPrimaryKey());

        role_Impl.setRoleId(role_.getRoleId());
        role_Impl.setName(role_.getName());

        return role_Impl;
    }

    /**
     * Returns the role_ with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the role_
     * @return the role_
     * @throws com.ext.portlet.NoSuchRole_Exception if a role_ with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Role_ findByPrimaryKey(Serializable primaryKey)
        throws NoSuchRole_Exception, SystemException {
        Role_ role_ = fetchByPrimaryKey(primaryKey);

        if (role_ == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchRole_Exception(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return role_;
    }

    /**
     * Returns the role_ with the primary key or throws a {@link com.ext.portlet.NoSuchRole_Exception} if it could not be found.
     *
     * @param roleId the primary key of the role_
     * @return the role_
     * @throws com.ext.portlet.NoSuchRole_Exception if a role_ with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Role_ findByPrimaryKey(long roleId)
        throws NoSuchRole_Exception, SystemException {
        return findByPrimaryKey((Serializable) roleId);
    }

    /**
     * Returns the role_ with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the role_
     * @return the role_, or <code>null</code> if a role_ with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Role_ fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        Role_ role_ = (Role_) EntityCacheUtil.getResult(Role_ModelImpl.ENTITY_CACHE_ENABLED,
                Role_Impl.class, primaryKey);

        if (role_ == _nullRole_) {
            return null;
        }

        if (role_ == null) {
            Session session = null;

            try {
                session = openSession();

                role_ = (Role_) session.get(Role_Impl.class, primaryKey);

                if (role_ != null) {
                    cacheResult(role_);
                } else {
                    EntityCacheUtil.putResult(Role_ModelImpl.ENTITY_CACHE_ENABLED,
                        Role_Impl.class, primaryKey, _nullRole_);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(Role_ModelImpl.ENTITY_CACHE_ENABLED,
                    Role_Impl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return role_;
    }

    /**
     * Returns the role_ with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param roleId the primary key of the role_
     * @return the role_, or <code>null</code> if a role_ with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Role_ fetchByPrimaryKey(long roleId) throws SystemException {
        return fetchByPrimaryKey((Serializable) roleId);
    }

    /**
     * Returns all the role_s.
     *
     * @return the role_s
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Role_> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the role_s.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Role_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of role_s
     * @param end the upper bound of the range of role_s (not inclusive)
     * @return the range of role_s
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Role_> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the role_s.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Role_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of role_s
     * @param end the upper bound of the range of role_s (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of role_s
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Role_> findAll(int start, int end,
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

        List<Role_> list = (List<Role_>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ROLE_);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ROLE_;

                if (pagination) {
                    sql = sql.concat(Role_ModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<Role_>) QueryUtil.list(q, getDialect(), start,
                            end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Role_>(list);
                } else {
                    list = (List<Role_>) QueryUtil.list(q, getDialect(), start,
                            end);
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
     * Removes all the role_s from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (Role_ role_ : findAll()) {
            remove(role_);
        }
    }

    /**
     * Returns the number of role_s.
     *
     * @return the number of role_s
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

                Query q = session.createQuery(_SQL_COUNT_ROLE_);

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
     * Returns all the user_s associated with the role_.
     *
     * @param pk the primary key of the role_
     * @return the user_s associated with the role_
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<com.ext.portlet.model.User_> getUser_s(long pk)
        throws SystemException {
        return getUser_s(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    /**
     * Returns a range of all the user_s associated with the role_.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Role_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param pk the primary key of the role_
     * @param start the lower bound of the range of role_s
     * @param end the upper bound of the range of role_s (not inclusive)
     * @return the range of user_s associated with the role_
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<com.ext.portlet.model.User_> getUser_s(long pk, int start,
        int end) throws SystemException {
        return getUser_s(pk, start, end, null);
    }

    /**
     * Returns an ordered range of all the user_s associated with the role_.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Role_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param pk the primary key of the role_
     * @param start the lower bound of the range of role_s
     * @param end the upper bound of the range of role_s (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of user_s associated with the role_
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<com.ext.portlet.model.User_> getUser_s(long pk, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        return role_ToUser_TableMapper.getRightBaseModels(pk, start, end,
            orderByComparator);
    }

    /**
     * Returns the number of user_s associated with the role_.
     *
     * @param pk the primary key of the role_
     * @return the number of user_s associated with the role_
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int getUser_sSize(long pk) throws SystemException {
        long[] pks = role_ToUser_TableMapper.getRightPrimaryKeys(pk);

        return pks.length;
    }

    /**
     * Returns <code>true</code> if the user_ is associated with the role_.
     *
     * @param pk the primary key of the role_
     * @param user_PK the primary key of the user_
     * @return <code>true</code> if the user_ is associated with the role_; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    @Override
    public boolean containsUser_(long pk, long user_PK)
        throws SystemException {
        return role_ToUser_TableMapper.containsTableMapping(pk, user_PK);
    }

    /**
     * Returns <code>true</code> if the role_ has any user_s associated with it.
     *
     * @param pk the primary key of the role_ to check for associations with user_s
     * @return <code>true</code> if the role_ has any user_s associated with it; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    @Override
    public boolean containsUser_s(long pk) throws SystemException {
        if (getUser_sSize(pk) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds an association between the role_ and the user_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
     *
     * @param pk the primary key of the role_
     * @param user_PK the primary key of the user_
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void addUser_(long pk, long user_PK) throws SystemException {
        role_ToUser_TableMapper.addTableMapping(pk, user_PK);
    }

    /**
     * Adds an association between the role_ and the user_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
     *
     * @param pk the primary key of the role_
     * @param user_ the user_
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void addUser_(long pk, com.ext.portlet.model.User_ user_)
        throws SystemException {
        role_ToUser_TableMapper.addTableMapping(pk, user_.getPrimaryKey());
    }

    /**
     * Adds an association between the role_ and the user_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
     *
     * @param pk the primary key of the role_
     * @param user_PKs the primary keys of the user_s
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void addUser_s(long pk, long[] user_PKs) throws SystemException {
        for (long user_PK : user_PKs) {
            role_ToUser_TableMapper.addTableMapping(pk, user_PK);
        }
    }

    /**
     * Adds an association between the role_ and the user_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
     *
     * @param pk the primary key of the role_
     * @param user_s the user_s
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void addUser_s(long pk, List<com.ext.portlet.model.User_> user_s)
        throws SystemException {
        for (com.ext.portlet.model.User_ user_ : user_s) {
            role_ToUser_TableMapper.addTableMapping(pk, user_.getPrimaryKey());
        }
    }

    /**
     * Clears all associations between the role_ and its user_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
     *
     * @param pk the primary key of the role_ to clear the associated user_s from
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void clearUser_s(long pk) throws SystemException {
        role_ToUser_TableMapper.deleteLeftPrimaryKeyTableMappings(pk);
    }

    /**
     * Removes the association between the role_ and the user_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
     *
     * @param pk the primary key of the role_
     * @param user_PK the primary key of the user_
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeUser_(long pk, long user_PK) throws SystemException {
        role_ToUser_TableMapper.deleteTableMapping(pk, user_PK);
    }

    /**
     * Removes the association between the role_ and the user_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
     *
     * @param pk the primary key of the role_
     * @param user_ the user_
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeUser_(long pk, com.ext.portlet.model.User_ user_)
        throws SystemException {
        role_ToUser_TableMapper.deleteTableMapping(pk, user_.getPrimaryKey());
    }

    /**
     * Removes the association between the role_ and the user_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
     *
     * @param pk the primary key of the role_
     * @param user_PKs the primary keys of the user_s
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeUser_s(long pk, long[] user_PKs)
        throws SystemException {
        for (long user_PK : user_PKs) {
            role_ToUser_TableMapper.deleteTableMapping(pk, user_PK);
        }
    }

    /**
     * Removes the association between the role_ and the user_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
     *
     * @param pk the primary key of the role_
     * @param user_s the user_s
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeUser_s(long pk, List<com.ext.portlet.model.User_> user_s)
        throws SystemException {
        for (com.ext.portlet.model.User_ user_ : user_s) {
            role_ToUser_TableMapper.deleteTableMapping(pk, user_.getPrimaryKey());
        }
    }

    /**
     * Sets the user_s associated with the role_, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
     *
     * @param pk the primary key of the role_
     * @param user_PKs the primary keys of the user_s to be associated with the role_
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void setUser_s(long pk, long[] user_PKs) throws SystemException {
        Set<Long> newUser_PKsSet = SetUtil.fromArray(user_PKs);
        Set<Long> oldUser_PKsSet = SetUtil.fromArray(role_ToUser_TableMapper.getRightPrimaryKeys(
                    pk));

        Set<Long> removeUser_PKsSet = new HashSet<Long>(oldUser_PKsSet);

        removeUser_PKsSet.removeAll(newUser_PKsSet);

        for (long removeUser_PK : removeUser_PKsSet) {
            role_ToUser_TableMapper.deleteTableMapping(pk, removeUser_PK);
        }

        newUser_PKsSet.removeAll(oldUser_PKsSet);

        for (long newUser_PK : newUser_PKsSet) {
            role_ToUser_TableMapper.addTableMapping(pk, newUser_PK);
        }
    }

    /**
     * Sets the user_s associated with the role_, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
     *
     * @param pk the primary key of the role_
     * @param user_s the user_s to be associated with the role_
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void setUser_s(long pk, List<com.ext.portlet.model.User_> user_s)
        throws SystemException {
        try {
            long[] user_PKs = new long[user_s.size()];

            for (int i = 0; i < user_s.size(); i++) {
                com.ext.portlet.model.User_ user_ = user_s.get(i);

                user_PKs[i] = user_.getPrimaryKey();
            }

            setUser_s(pk, user_PKs);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            FinderCacheUtil.clearCache(Role_ModelImpl.MAPPING_TABLE_XCOLAB_USERS_ROLES_NAME);
        }
    }

    /**
     * Initializes the role_ persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.Role_")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<Role_>> listenersList = new ArrayList<ModelListener<Role_>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<Role_>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }

        role_ToUser_TableMapper = TableMapperFactory.getTableMapper("xcolab_Users_Roles",
                "roleId", "userId", this, user_Persistence);
    }

    public void destroy() {
        EntityCacheUtil.removeCache(Role_Impl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
