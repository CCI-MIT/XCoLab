package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchUser_Exception;
import com.ext.portlet.model.User_;
import com.ext.portlet.model.impl.User_Impl;
import com.ext.portlet.model.impl.User_ModelImpl;
import com.ext.portlet.service.persistence.Role_Persistence;
import com.ext.portlet.service.persistence.User_Persistence;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
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
import com.liferay.portal.kernel.util.StringPool;
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
 * The persistence implementation for the user_ service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see User_Persistence
 * @see User_Util
 * @generated
 */
public class User_PersistenceImpl extends BasePersistenceImpl<User_>
    implements User_Persistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link User_Util} to access the user_ persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = User_Impl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(User_ModelImpl.ENTITY_CACHE_ENABLED,
            User_ModelImpl.FINDER_CACHE_ENABLED, User_Impl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(User_ModelImpl.ENTITY_CACHE_ENABLED,
            User_ModelImpl.FINDER_CACHE_ENABLED, User_Impl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(User_ModelImpl.ENTITY_CACHE_ENABLED,
            User_ModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(User_ModelImpl.ENTITY_CACHE_ENABLED,
            User_ModelImpl.FINDER_CACHE_ENABLED, User_Impl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
        new FinderPath(User_ModelImpl.ENTITY_CACHE_ENABLED,
            User_ModelImpl.FINDER_CACHE_ENABLED, User_Impl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
            new String[] { Long.class.getName() },
            User_ModelImpl.USERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(User_ModelImpl.ENTITY_CACHE_ENABLED,
            User_ModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_USERID_USERID_2 = "user_.userId = ?";
    private static final String _SQL_SELECT_USER_ = "SELECT user_ FROM User_ user_";
    private static final String _SQL_SELECT_USER__WHERE = "SELECT user_ FROM User_ user_ WHERE ";
    private static final String _SQL_COUNT_USER_ = "SELECT COUNT(user_) FROM User_ user_";
    private static final String _SQL_COUNT_USER__WHERE = "SELECT COUNT(user_) FROM User_ user_ WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "user_.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No User_ exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No User_ exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(User_PersistenceImpl.class);
    private static User_ _nullUser_ = new User_Impl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<User_> toCacheModel() {
                return _nullUser_CacheModel;
            }
        };

    private static CacheModel<User_> _nullUser_CacheModel = new CacheModel<User_>() {
            @Override
            public User_ toEntityModel() {
                return _nullUser_;
            }
        };

    @BeanReference(type = Role_Persistence.class)
    protected Role_Persistence role_Persistence;
    protected TableMapper<User_, com.ext.portlet.model.Role_> user_ToRole_TableMapper;

    public User_PersistenceImpl() {
        setModelClass(User_.class);
    }

    /**
     * Returns all the user_s where userId = &#63;.
     *
     * @param userId the user ID
     * @return the matching user_s
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<User_> findByUserId(long userId) throws SystemException {
        return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the user_s where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.User_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param userId the user ID
     * @param start the lower bound of the range of user_s
     * @param end the upper bound of the range of user_s (not inclusive)
     * @return the range of matching user_s
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<User_> findByUserId(long userId, int start, int end)
        throws SystemException {
        return findByUserId(userId, start, end, null);
    }

    /**
     * Returns an ordered range of all the user_s where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.User_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param userId the user ID
     * @param start the lower bound of the range of user_s
     * @param end the upper bound of the range of user_s (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching user_s
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<User_> findByUserId(long userId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
            finderArgs = new Object[] { userId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
            finderArgs = new Object[] { userId, start, end, orderByComparator };
        }

        List<User_> list = (List<User_>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (User_ user_ : list) {
                if ((userId != user_.getUserId())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_USER__WHERE);

            query.append(_FINDER_COLUMN_USERID_USERID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(User_ModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                if (!pagination) {
                    list = (List<User_>) QueryUtil.list(q, getDialect(), start,
                            end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<User_>(list);
                } else {
                    list = (List<User_>) QueryUtil.list(q, getDialect(), start,
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
     * Returns the first user_ in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching user_
     * @throws com.ext.portlet.NoSuchUser_Exception if a matching user_ could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public User_ findByUserId_First(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchUser_Exception, SystemException {
        User_ user_ = fetchByUserId_First(userId, orderByComparator);

        if (user_ != null) {
            return user_;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("userId=");
        msg.append(userId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchUser_Exception(msg.toString());
    }

    /**
     * Returns the first user_ in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching user_, or <code>null</code> if a matching user_ could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public User_ fetchByUserId_First(long userId,
        OrderByComparator orderByComparator) throws SystemException {
        List<User_> list = findByUserId(userId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last user_ in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching user_
     * @throws com.ext.portlet.NoSuchUser_Exception if a matching user_ could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public User_ findByUserId_Last(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchUser_Exception, SystemException {
        User_ user_ = fetchByUserId_Last(userId, orderByComparator);

        if (user_ != null) {
            return user_;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("userId=");
        msg.append(userId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchUser_Exception(msg.toString());
    }

    /**
     * Returns the last user_ in the ordered set where userId = &#63;.
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching user_, or <code>null</code> if a matching user_ could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public User_ fetchByUserId_Last(long userId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByUserId(userId);

        if (count == 0) {
            return null;
        }

        List<User_> list = findByUserId(userId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Removes all the user_s where userId = &#63; from the database.
     *
     * @param userId the user ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByUserId(long userId) throws SystemException {
        for (User_ user_ : findByUserId(userId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(user_);
        }
    }

    /**
     * Returns the number of user_s where userId = &#63;.
     *
     * @param userId the user ID
     * @return the number of matching user_s
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByUserId(long userId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_USERID;

        Object[] finderArgs = new Object[] { userId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_USER__WHERE);

            query.append(_FINDER_COLUMN_USERID_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                count = (Long) q.uniqueResult();

                FinderCacheUtil.putResult(finderPath, finderArgs, count);
            } catch (Exception e) {
                FinderCacheUtil.removeResult(finderPath, finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Caches the user_ in the entity cache if it is enabled.
     *
     * @param user_ the user_
     */
    @Override
    public void cacheResult(User_ user_) {
        EntityCacheUtil.putResult(User_ModelImpl.ENTITY_CACHE_ENABLED,
            User_Impl.class, user_.getPrimaryKey(), user_);

        user_.resetOriginalValues();
    }

    /**
     * Caches the user_s in the entity cache if it is enabled.
     *
     * @param user_s the user_s
     */
    @Override
    public void cacheResult(List<User_> user_s) {
        for (User_ user_ : user_s) {
            if (EntityCacheUtil.getResult(User_ModelImpl.ENTITY_CACHE_ENABLED,
                        User_Impl.class, user_.getPrimaryKey()) == null) {
                cacheResult(user_);
            } else {
                user_.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all user_s.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(User_Impl.class.getName());
        }

        EntityCacheUtil.clearCache(User_Impl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the user_.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(User_ user_) {
        EntityCacheUtil.removeResult(User_ModelImpl.ENTITY_CACHE_ENABLED,
            User_Impl.class, user_.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<User_> user_s) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (User_ user_ : user_s) {
            EntityCacheUtil.removeResult(User_ModelImpl.ENTITY_CACHE_ENABLED,
                User_Impl.class, user_.getPrimaryKey());
        }
    }

    /**
     * Creates a new user_ with the primary key. Does not add the user_ to the database.
     *
     * @param userId the primary key for the new user_
     * @return the new user_
     */
    @Override
    public User_ create(long userId) {
        User_ user_ = new User_Impl();

        user_.setNew(true);
        user_.setPrimaryKey(userId);

        return user_;
    }

    /**
     * Removes the user_ with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param userId the primary key of the user_
     * @return the user_ that was removed
     * @throws com.ext.portlet.NoSuchUser_Exception if a user_ with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public User_ remove(long userId)
        throws NoSuchUser_Exception, SystemException {
        return remove((Serializable) userId);
    }

    /**
     * Removes the user_ with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the user_
     * @return the user_ that was removed
     * @throws com.ext.portlet.NoSuchUser_Exception if a user_ with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public User_ remove(Serializable primaryKey)
        throws NoSuchUser_Exception, SystemException {
        Session session = null;

        try {
            session = openSession();

            User_ user_ = (User_) session.get(User_Impl.class, primaryKey);

            if (user_ == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchUser_Exception(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(user_);
        } catch (NoSuchUser_Exception nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected User_ removeImpl(User_ user_) throws SystemException {
        user_ = toUnwrappedModel(user_);

        user_ToRole_TableMapper.deleteLeftPrimaryKeyTableMappings(user_.getPrimaryKey());

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(user_)) {
                user_ = (User_) session.get(User_Impl.class,
                        user_.getPrimaryKeyObj());
            }

            if (user_ != null) {
                session.delete(user_);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (user_ != null) {
            clearCache(user_);
        }

        return user_;
    }

    @Override
    public User_ updateImpl(com.ext.portlet.model.User_ user_)
        throws SystemException {
        user_ = toUnwrappedModel(user_);

        boolean isNew = user_.isNew();

        User_ModelImpl user_ModelImpl = (User_ModelImpl) user_;

        Session session = null;

        try {
            session = openSession();

            if (user_.isNew()) {
                session.save(user_);

                user_.setNew(false);
            } else {
                session.merge(user_);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !User_ModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((user_ModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] { user_ModelImpl.getOriginalUserId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
                    args);

                args = new Object[] { user_ModelImpl.getUserId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
                    args);
            }
        }

        EntityCacheUtil.putResult(User_ModelImpl.ENTITY_CACHE_ENABLED,
            User_Impl.class, user_.getPrimaryKey(), user_);

        return user_;
    }

    protected User_ toUnwrappedModel(User_ user_) {
        if (user_ instanceof User_Impl) {
            return user_;
        }

        User_Impl user_Impl = new User_Impl();

        user_Impl.setNew(user_.isNew());
        user_Impl.setPrimaryKey(user_.getPrimaryKey());

        user_Impl.setUserId(user_.getUserId());
        user_Impl.setCreateDate(user_.getCreateDate());
        user_Impl.setScreenName(user_.getScreenName());

        return user_Impl;
    }

    /**
     * Returns the user_ with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the user_
     * @return the user_
     * @throws com.ext.portlet.NoSuchUser_Exception if a user_ with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public User_ findByPrimaryKey(Serializable primaryKey)
        throws NoSuchUser_Exception, SystemException {
        User_ user_ = fetchByPrimaryKey(primaryKey);

        if (user_ == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchUser_Exception(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return user_;
    }

    /**
     * Returns the user_ with the primary key or throws a {@link com.ext.portlet.NoSuchUser_Exception} if it could not be found.
     *
     * @param userId the primary key of the user_
     * @return the user_
     * @throws com.ext.portlet.NoSuchUser_Exception if a user_ with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public User_ findByPrimaryKey(long userId)
        throws NoSuchUser_Exception, SystemException {
        return findByPrimaryKey((Serializable) userId);
    }

    /**
     * Returns the user_ with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the user_
     * @return the user_, or <code>null</code> if a user_ with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public User_ fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        User_ user_ = (User_) EntityCacheUtil.getResult(User_ModelImpl.ENTITY_CACHE_ENABLED,
                User_Impl.class, primaryKey);

        if (user_ == _nullUser_) {
            return null;
        }

        if (user_ == null) {
            Session session = null;

            try {
                session = openSession();

                user_ = (User_) session.get(User_Impl.class, primaryKey);

                if (user_ != null) {
                    cacheResult(user_);
                } else {
                    EntityCacheUtil.putResult(User_ModelImpl.ENTITY_CACHE_ENABLED,
                        User_Impl.class, primaryKey, _nullUser_);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(User_ModelImpl.ENTITY_CACHE_ENABLED,
                    User_Impl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return user_;
    }

    /**
     * Returns the user_ with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param userId the primary key of the user_
     * @return the user_, or <code>null</code> if a user_ with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public User_ fetchByPrimaryKey(long userId) throws SystemException {
        return fetchByPrimaryKey((Serializable) userId);
    }

    /**
     * Returns all the user_s.
     *
     * @return the user_s
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<User_> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the user_s.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.User_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of user_s
     * @param end the upper bound of the range of user_s (not inclusive)
     * @return the range of user_s
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<User_> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the user_s.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.User_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of user_s
     * @param end the upper bound of the range of user_s (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of user_s
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<User_> findAll(int start, int end,
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

        List<User_> list = (List<User_>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_USER_);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_USER_;

                if (pagination) {
                    sql = sql.concat(User_ModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<User_>) QueryUtil.list(q, getDialect(), start,
                            end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<User_>(list);
                } else {
                    list = (List<User_>) QueryUtil.list(q, getDialect(), start,
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
     * Removes all the user_s from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (User_ user_ : findAll()) {
            remove(user_);
        }
    }

    /**
     * Returns the number of user_s.
     *
     * @return the number of user_s
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

                Query q = session.createQuery(_SQL_COUNT_USER_);

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
     * Returns all the role_s associated with the user_.
     *
     * @param pk the primary key of the user_
     * @return the role_s associated with the user_
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<com.ext.portlet.model.Role_> getRole_s(long pk)
        throws SystemException {
        return getRole_s(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    /**
     * Returns a range of all the role_s associated with the user_.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.User_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param pk the primary key of the user_
     * @param start the lower bound of the range of user_s
     * @param end the upper bound of the range of user_s (not inclusive)
     * @return the range of role_s associated with the user_
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<com.ext.portlet.model.Role_> getRole_s(long pk, int start,
        int end) throws SystemException {
        return getRole_s(pk, start, end, null);
    }

    /**
     * Returns an ordered range of all the role_s associated with the user_.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.User_ModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param pk the primary key of the user_
     * @param start the lower bound of the range of user_s
     * @param end the upper bound of the range of user_s (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of role_s associated with the user_
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<com.ext.portlet.model.Role_> getRole_s(long pk, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        return user_ToRole_TableMapper.getRightBaseModels(pk, start, end,
            orderByComparator);
    }

    /**
     * Returns the number of role_s associated with the user_.
     *
     * @param pk the primary key of the user_
     * @return the number of role_s associated with the user_
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int getRole_sSize(long pk) throws SystemException {
        long[] pks = user_ToRole_TableMapper.getRightPrimaryKeys(pk);

        return pks.length;
    }

    /**
     * Returns <code>true</code> if the role_ is associated with the user_.
     *
     * @param pk the primary key of the user_
     * @param role_PK the primary key of the role_
     * @return <code>true</code> if the role_ is associated with the user_; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    @Override
    public boolean containsRole_(long pk, long role_PK)
        throws SystemException {
        return user_ToRole_TableMapper.containsTableMapping(pk, role_PK);
    }

    /**
     * Returns <code>true</code> if the user_ has any role_s associated with it.
     *
     * @param pk the primary key of the user_ to check for associations with role_s
     * @return <code>true</code> if the user_ has any role_s associated with it; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    @Override
    public boolean containsRole_s(long pk) throws SystemException {
        if (getRole_sSize(pk) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds an association between the user_ and the role_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
     *
     * @param pk the primary key of the user_
     * @param role_PK the primary key of the role_
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void addRole_(long pk, long role_PK) throws SystemException {
        user_ToRole_TableMapper.addTableMapping(pk, role_PK);
    }

    /**
     * Adds an association between the user_ and the role_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
     *
     * @param pk the primary key of the user_
     * @param role_ the role_
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void addRole_(long pk, com.ext.portlet.model.Role_ role_)
        throws SystemException {
        user_ToRole_TableMapper.addTableMapping(pk, role_.getPrimaryKey());
    }

    /**
     * Adds an association between the user_ and the role_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
     *
     * @param pk the primary key of the user_
     * @param role_PKs the primary keys of the role_s
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void addRole_s(long pk, long[] role_PKs) throws SystemException {
        for (long role_PK : role_PKs) {
            user_ToRole_TableMapper.addTableMapping(pk, role_PK);
        }
    }

    /**
     * Adds an association between the user_ and the role_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
     *
     * @param pk the primary key of the user_
     * @param role_s the role_s
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void addRole_s(long pk, List<com.ext.portlet.model.Role_> role_s)
        throws SystemException {
        for (com.ext.portlet.model.Role_ role_ : role_s) {
            user_ToRole_TableMapper.addTableMapping(pk, role_.getPrimaryKey());
        }
    }

    /**
     * Clears all associations between the user_ and its role_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
     *
     * @param pk the primary key of the user_ to clear the associated role_s from
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void clearRole_s(long pk) throws SystemException {
        user_ToRole_TableMapper.deleteLeftPrimaryKeyTableMappings(pk);
    }

    /**
     * Removes the association between the user_ and the role_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
     *
     * @param pk the primary key of the user_
     * @param role_PK the primary key of the role_
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeRole_(long pk, long role_PK) throws SystemException {
        user_ToRole_TableMapper.deleteTableMapping(pk, role_PK);
    }

    /**
     * Removes the association between the user_ and the role_. Also notifies the appropriate model listeners and clears the mapping table finder cache.
     *
     * @param pk the primary key of the user_
     * @param role_ the role_
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeRole_(long pk, com.ext.portlet.model.Role_ role_)
        throws SystemException {
        user_ToRole_TableMapper.deleteTableMapping(pk, role_.getPrimaryKey());
    }

    /**
     * Removes the association between the user_ and the role_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
     *
     * @param pk the primary key of the user_
     * @param role_PKs the primary keys of the role_s
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeRole_s(long pk, long[] role_PKs)
        throws SystemException {
        for (long role_PK : role_PKs) {
            user_ToRole_TableMapper.deleteTableMapping(pk, role_PK);
        }
    }

    /**
     * Removes the association between the user_ and the role_s. Also notifies the appropriate model listeners and clears the mapping table finder cache.
     *
     * @param pk the primary key of the user_
     * @param role_s the role_s
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeRole_s(long pk, List<com.ext.portlet.model.Role_> role_s)
        throws SystemException {
        for (com.ext.portlet.model.Role_ role_ : role_s) {
            user_ToRole_TableMapper.deleteTableMapping(pk, role_.getPrimaryKey());
        }
    }

    /**
     * Sets the role_s associated with the user_, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
     *
     * @param pk the primary key of the user_
     * @param role_PKs the primary keys of the role_s to be associated with the user_
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void setRole_s(long pk, long[] role_PKs) throws SystemException {
        Set<Long> newRole_PKsSet = SetUtil.fromArray(role_PKs);
        Set<Long> oldRole_PKsSet = SetUtil.fromArray(user_ToRole_TableMapper.getRightPrimaryKeys(
                    pk));

        Set<Long> removeRole_PKsSet = new HashSet<Long>(oldRole_PKsSet);

        removeRole_PKsSet.removeAll(newRole_PKsSet);

        for (long removeRole_PK : removeRole_PKsSet) {
            user_ToRole_TableMapper.deleteTableMapping(pk, removeRole_PK);
        }

        newRole_PKsSet.removeAll(oldRole_PKsSet);

        for (long newRole_PK : newRole_PKsSet) {
            user_ToRole_TableMapper.addTableMapping(pk, newRole_PK);
        }
    }

    /**
     * Sets the role_s associated with the user_, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
     *
     * @param pk the primary key of the user_
     * @param role_s the role_s to be associated with the user_
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void setRole_s(long pk, List<com.ext.portlet.model.Role_> role_s)
        throws SystemException {
        try {
            long[] role_PKs = new long[role_s.size()];

            for (int i = 0; i < role_s.size(); i++) {
                com.ext.portlet.model.Role_ role_ = role_s.get(i);

                role_PKs[i] = role_.getPrimaryKey();
            }

            setRole_s(pk, role_PKs);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            FinderCacheUtil.clearCache(User_ModelImpl.MAPPING_TABLE_XCOLAB_USERS_ROLES_NAME);
        }
    }

    /**
     * Initializes the user_ persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.User_")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<User_>> listenersList = new ArrayList<ModelListener<User_>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<User_>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }

        user_ToRole_TableMapper = TableMapperFactory.getTableMapper("xcolab_Users_Roles",
                "userId", "roleId", this, role_Persistence);
    }

    public void destroy() {
        EntityCacheUtil.removeCache(User_Impl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
