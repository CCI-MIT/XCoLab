package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchRolesCategoryException;
import com.ext.portlet.model.RolesCategory;
import com.ext.portlet.model.impl.RolesCategoryImpl;
import com.ext.portlet.model.impl.RolesCategoryModelImpl;
import com.ext.portlet.service.persistence.RolesCategoryPersistence;

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
 * The persistence implementation for the roles category service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see RolesCategoryPersistence
 * @see RolesCategoryUtil
 * @generated
 */
public class RolesCategoryPersistenceImpl extends BasePersistenceImpl<RolesCategory>
    implements RolesCategoryPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link RolesCategoryUtil} to access the roles category persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = RolesCategoryImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(RolesCategoryModelImpl.ENTITY_CACHE_ENABLED,
            RolesCategoryModelImpl.FINDER_CACHE_ENABLED,
            RolesCategoryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(RolesCategoryModelImpl.ENTITY_CACHE_ENABLED,
            RolesCategoryModelImpl.FINDER_CACHE_ENABLED,
            RolesCategoryImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(RolesCategoryModelImpl.ENTITY_CACHE_ENABLED,
            RolesCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_ROLESCATEGORY = "SELECT rolesCategory FROM RolesCategory rolesCategory";
    private static final String _SQL_COUNT_ROLESCATEGORY = "SELECT COUNT(rolesCategory) FROM RolesCategory rolesCategory";
    private static final String _ORDER_BY_ENTITY_ALIAS = "rolesCategory.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RolesCategory exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(RolesCategoryPersistenceImpl.class);
    private static RolesCategory _nullRolesCategory = new RolesCategoryImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<RolesCategory> toCacheModel() {
                return _nullRolesCategoryCacheModel;
            }
        };

    private static CacheModel<RolesCategory> _nullRolesCategoryCacheModel = new CacheModel<RolesCategory>() {
            @Override
            public RolesCategory toEntityModel() {
                return _nullRolesCategory;
            }
        };

    public RolesCategoryPersistenceImpl() {
        setModelClass(RolesCategory.class);
    }

    /**
     * Caches the roles category in the entity cache if it is enabled.
     *
     * @param rolesCategory the roles category
     */
    @Override
    public void cacheResult(RolesCategory rolesCategory) {
        EntityCacheUtil.putResult(RolesCategoryModelImpl.ENTITY_CACHE_ENABLED,
            RolesCategoryImpl.class, rolesCategory.getPrimaryKey(),
            rolesCategory);

        rolesCategory.resetOriginalValues();
    }

    /**
     * Caches the roles categories in the entity cache if it is enabled.
     *
     * @param rolesCategories the roles categories
     */
    @Override
    public void cacheResult(List<RolesCategory> rolesCategories) {
        for (RolesCategory rolesCategory : rolesCategories) {
            if (EntityCacheUtil.getResult(
                        RolesCategoryModelImpl.ENTITY_CACHE_ENABLED,
                        RolesCategoryImpl.class, rolesCategory.getPrimaryKey()) == null) {
                cacheResult(rolesCategory);
            } else {
                rolesCategory.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all roles categories.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(RolesCategoryImpl.class.getName());
        }

        EntityCacheUtil.clearCache(RolesCategoryImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the roles category.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(RolesCategory rolesCategory) {
        EntityCacheUtil.removeResult(RolesCategoryModelImpl.ENTITY_CACHE_ENABLED,
            RolesCategoryImpl.class, rolesCategory.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<RolesCategory> rolesCategories) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (RolesCategory rolesCategory : rolesCategories) {
            EntityCacheUtil.removeResult(RolesCategoryModelImpl.ENTITY_CACHE_ENABLED,
                RolesCategoryImpl.class, rolesCategory.getPrimaryKey());
        }
    }

    /**
     * Creates a new roles category with the primary key. Does not add the roles category to the database.
     *
     * @param roleId the primary key for the new roles category
     * @return the new roles category
     */
    @Override
    public RolesCategory create(long roleId) {
        RolesCategory rolesCategory = new RolesCategoryImpl();

        rolesCategory.setNew(true);
        rolesCategory.setPrimaryKey(roleId);

        return rolesCategory;
    }

    /**
     * Removes the roles category with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param roleId the primary key of the roles category
     * @return the roles category that was removed
     * @throws com.ext.portlet.NoSuchRolesCategoryException if a roles category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RolesCategory remove(long roleId)
        throws NoSuchRolesCategoryException, SystemException {
        return remove((Serializable) roleId);
    }

    /**
     * Removes the roles category with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the roles category
     * @return the roles category that was removed
     * @throws com.ext.portlet.NoSuchRolesCategoryException if a roles category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RolesCategory remove(Serializable primaryKey)
        throws NoSuchRolesCategoryException, SystemException {
        Session session = null;

        try {
            session = openSession();

            RolesCategory rolesCategory = (RolesCategory) session.get(RolesCategoryImpl.class,
                    primaryKey);

            if (rolesCategory == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchRolesCategoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(rolesCategory);
        } catch (NoSuchRolesCategoryException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected RolesCategory removeImpl(RolesCategory rolesCategory)
        throws SystemException {
        rolesCategory = toUnwrappedModel(rolesCategory);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(rolesCategory)) {
                rolesCategory = (RolesCategory) session.get(RolesCategoryImpl.class,
                        rolesCategory.getPrimaryKeyObj());
            }

            if (rolesCategory != null) {
                session.delete(rolesCategory);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (rolesCategory != null) {
            clearCache(rolesCategory);
        }

        return rolesCategory;
    }

    @Override
    public RolesCategory updateImpl(
        com.ext.portlet.model.RolesCategory rolesCategory)
        throws SystemException {
        rolesCategory = toUnwrappedModel(rolesCategory);

        boolean isNew = rolesCategory.isNew();

        Session session = null;

        try {
            session = openSession();

            if (rolesCategory.isNew()) {
                session.save(rolesCategory);

                rolesCategory.setNew(false);
            } else {
                session.merge(rolesCategory);
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

        EntityCacheUtil.putResult(RolesCategoryModelImpl.ENTITY_CACHE_ENABLED,
            RolesCategoryImpl.class, rolesCategory.getPrimaryKey(),
            rolesCategory);

        return rolesCategory;
    }

    protected RolesCategory toUnwrappedModel(RolesCategory rolesCategory) {
        if (rolesCategory instanceof RolesCategoryImpl) {
            return rolesCategory;
        }

        RolesCategoryImpl rolesCategoryImpl = new RolesCategoryImpl();

        rolesCategoryImpl.setNew(rolesCategory.isNew());
        rolesCategoryImpl.setPrimaryKey(rolesCategory.getPrimaryKey());

        rolesCategoryImpl.setRoleId(rolesCategory.getRoleId());
        rolesCategoryImpl.setCategoryName(rolesCategory.getCategoryName());
        rolesCategoryImpl.setRoleOrdinal(rolesCategory.getRoleOrdinal());

        return rolesCategoryImpl;
    }

    /**
     * Returns the roles category with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the roles category
     * @return the roles category
     * @throws com.ext.portlet.NoSuchRolesCategoryException if a roles category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RolesCategory findByPrimaryKey(Serializable primaryKey)
        throws NoSuchRolesCategoryException, SystemException {
        RolesCategory rolesCategory = fetchByPrimaryKey(primaryKey);

        if (rolesCategory == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchRolesCategoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return rolesCategory;
    }

    /**
     * Returns the roles category with the primary key or throws a {@link com.ext.portlet.NoSuchRolesCategoryException} if it could not be found.
     *
     * @param roleId the primary key of the roles category
     * @return the roles category
     * @throws com.ext.portlet.NoSuchRolesCategoryException if a roles category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RolesCategory findByPrimaryKey(long roleId)
        throws NoSuchRolesCategoryException, SystemException {
        return findByPrimaryKey((Serializable) roleId);
    }

    /**
     * Returns the roles category with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the roles category
     * @return the roles category, or <code>null</code> if a roles category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RolesCategory fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        RolesCategory rolesCategory = (RolesCategory) EntityCacheUtil.getResult(RolesCategoryModelImpl.ENTITY_CACHE_ENABLED,
                RolesCategoryImpl.class, primaryKey);

        if (rolesCategory == _nullRolesCategory) {
            return null;
        }

        if (rolesCategory == null) {
            Session session = null;

            try {
                session = openSession();

                rolesCategory = (RolesCategory) session.get(RolesCategoryImpl.class,
                        primaryKey);

                if (rolesCategory != null) {
                    cacheResult(rolesCategory);
                } else {
                    EntityCacheUtil.putResult(RolesCategoryModelImpl.ENTITY_CACHE_ENABLED,
                        RolesCategoryImpl.class, primaryKey, _nullRolesCategory);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(RolesCategoryModelImpl.ENTITY_CACHE_ENABLED,
                    RolesCategoryImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return rolesCategory;
    }

    /**
     * Returns the roles category with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param roleId the primary key of the roles category
     * @return the roles category, or <code>null</code> if a roles category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public RolesCategory fetchByPrimaryKey(long roleId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) roleId);
    }

    /**
     * Returns all the roles categories.
     *
     * @return the roles categories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RolesCategory> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the roles categories.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.RolesCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of roles categories
     * @param end the upper bound of the range of roles categories (not inclusive)
     * @return the range of roles categories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RolesCategory> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the roles categories.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.RolesCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of roles categories
     * @param end the upper bound of the range of roles categories (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of roles categories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<RolesCategory> findAll(int start, int end,
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

        List<RolesCategory> list = (List<RolesCategory>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ROLESCATEGORY);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ROLESCATEGORY;

                if (pagination) {
                    sql = sql.concat(RolesCategoryModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<RolesCategory>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<RolesCategory>(list);
                } else {
                    list = (List<RolesCategory>) QueryUtil.list(q,
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
     * Removes all the roles categories from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (RolesCategory rolesCategory : findAll()) {
            remove(rolesCategory);
        }
    }

    /**
     * Returns the number of roles categories.
     *
     * @return the number of roles categories
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

                Query q = session.createQuery(_SQL_COUNT_ROLESCATEGORY);

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
     * Initializes the roles category persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.RolesCategory")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<RolesCategory>> listenersList = new ArrayList<ModelListener<RolesCategory>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<RolesCategory>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(RolesCategoryImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
