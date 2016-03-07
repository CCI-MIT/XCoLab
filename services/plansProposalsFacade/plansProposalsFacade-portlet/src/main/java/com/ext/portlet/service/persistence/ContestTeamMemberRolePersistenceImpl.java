package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchContestTeamMemberRoleException;
import com.ext.portlet.model.ContestTeamMemberRole;
import com.ext.portlet.model.impl.ContestTeamMemberRoleImpl;
import com.ext.portlet.model.impl.ContestTeamMemberRoleModelImpl;
import com.ext.portlet.service.persistence.ContestTeamMemberRolePersistence;

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
 * The persistence implementation for the contest team member role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestTeamMemberRolePersistence
 * @see ContestTeamMemberRoleUtil
 * @generated
 */
public class ContestTeamMemberRolePersistenceImpl extends BasePersistenceImpl<ContestTeamMemberRole>
    implements ContestTeamMemberRolePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ContestTeamMemberRoleUtil} to access the contest team member role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ContestTeamMemberRoleImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContestTeamMemberRoleModelImpl.ENTITY_CACHE_ENABLED,
            ContestTeamMemberRoleModelImpl.FINDER_CACHE_ENABLED,
            ContestTeamMemberRoleImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContestTeamMemberRoleModelImpl.ENTITY_CACHE_ENABLED,
            ContestTeamMemberRoleModelImpl.FINDER_CACHE_ENABLED,
            ContestTeamMemberRoleImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContestTeamMemberRoleModelImpl.ENTITY_CACHE_ENABLED,
            ContestTeamMemberRoleModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CONTESTTEAMMEMBERROLE = "SELECT contestTeamMemberRole FROM ContestTeamMemberRole contestTeamMemberRole";
    private static final String _SQL_COUNT_CONTESTTEAMMEMBERROLE = "SELECT COUNT(contestTeamMemberRole) FROM ContestTeamMemberRole contestTeamMemberRole";
    private static final String _ORDER_BY_ENTITY_ALIAS = "contestTeamMemberRole.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContestTeamMemberRole exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ContestTeamMemberRolePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static ContestTeamMemberRole _nullContestTeamMemberRole = new ContestTeamMemberRoleImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ContestTeamMemberRole> toCacheModel() {
                return _nullContestTeamMemberRoleCacheModel;
            }
        };

    private static CacheModel<ContestTeamMemberRole> _nullContestTeamMemberRoleCacheModel =
        new CacheModel<ContestTeamMemberRole>() {
            @Override
            public ContestTeamMemberRole toEntityModel() {
                return _nullContestTeamMemberRole;
            }
        };

    public ContestTeamMemberRolePersistenceImpl() {
        setModelClass(ContestTeamMemberRole.class);
    }

    /**
     * Caches the contest team member role in the entity cache if it is enabled.
     *
     * @param contestTeamMemberRole the contest team member role
     */
    @Override
    public void cacheResult(ContestTeamMemberRole contestTeamMemberRole) {
        EntityCacheUtil.putResult(ContestTeamMemberRoleModelImpl.ENTITY_CACHE_ENABLED,
            ContestTeamMemberRoleImpl.class,
            contestTeamMemberRole.getPrimaryKey(), contestTeamMemberRole);

        contestTeamMemberRole.resetOriginalValues();
    }

    /**
     * Caches the contest team member roles in the entity cache if it is enabled.
     *
     * @param contestTeamMemberRoles the contest team member roles
     */
    @Override
    public void cacheResult(List<ContestTeamMemberRole> contestTeamMemberRoles) {
        for (ContestTeamMemberRole contestTeamMemberRole : contestTeamMemberRoles) {
            if (EntityCacheUtil.getResult(
                        ContestTeamMemberRoleModelImpl.ENTITY_CACHE_ENABLED,
                        ContestTeamMemberRoleImpl.class,
                        contestTeamMemberRole.getPrimaryKey()) == null) {
                cacheResult(contestTeamMemberRole);
            } else {
                contestTeamMemberRole.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all contest team member roles.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ContestTeamMemberRoleImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ContestTeamMemberRoleImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the contest team member role.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ContestTeamMemberRole contestTeamMemberRole) {
        EntityCacheUtil.removeResult(ContestTeamMemberRoleModelImpl.ENTITY_CACHE_ENABLED,
            ContestTeamMemberRoleImpl.class,
            contestTeamMemberRole.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ContestTeamMemberRole> contestTeamMemberRoles) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ContestTeamMemberRole contestTeamMemberRole : contestTeamMemberRoles) {
            EntityCacheUtil.removeResult(ContestTeamMemberRoleModelImpl.ENTITY_CACHE_ENABLED,
                ContestTeamMemberRoleImpl.class,
                contestTeamMemberRole.getPrimaryKey());
        }
    }

    /**
     * Creates a new contest team member role with the primary key. Does not add the contest team member role to the database.
     *
     * @param id the primary key for the new contest team member role
     * @return the new contest team member role
     */
    @Override
    public ContestTeamMemberRole create(long id) {
        ContestTeamMemberRole contestTeamMemberRole = new ContestTeamMemberRoleImpl();

        contestTeamMemberRole.setNew(true);
        contestTeamMemberRole.setPrimaryKey(id);

        return contestTeamMemberRole;
    }

    /**
     * Removes the contest team member role with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the contest team member role
     * @return the contest team member role that was removed
     * @throws com.ext.portlet.NoSuchContestTeamMemberRoleException if a contest team member role with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestTeamMemberRole remove(long id)
        throws NoSuchContestTeamMemberRoleException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the contest team member role with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the contest team member role
     * @return the contest team member role that was removed
     * @throws com.ext.portlet.NoSuchContestTeamMemberRoleException if a contest team member role with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestTeamMemberRole remove(Serializable primaryKey)
        throws NoSuchContestTeamMemberRoleException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ContestTeamMemberRole contestTeamMemberRole = (ContestTeamMemberRole) session.get(ContestTeamMemberRoleImpl.class,
                    primaryKey);

            if (contestTeamMemberRole == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchContestTeamMemberRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(contestTeamMemberRole);
        } catch (NoSuchContestTeamMemberRoleException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ContestTeamMemberRole removeImpl(
        ContestTeamMemberRole contestTeamMemberRole) throws SystemException {
        contestTeamMemberRole = toUnwrappedModel(contestTeamMemberRole);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(contestTeamMemberRole)) {
                contestTeamMemberRole = (ContestTeamMemberRole) session.get(ContestTeamMemberRoleImpl.class,
                        contestTeamMemberRole.getPrimaryKeyObj());
            }

            if (contestTeamMemberRole != null) {
                session.delete(contestTeamMemberRole);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (contestTeamMemberRole != null) {
            clearCache(contestTeamMemberRole);
        }

        return contestTeamMemberRole;
    }

    @Override
    public ContestTeamMemberRole updateImpl(
        com.ext.portlet.model.ContestTeamMemberRole contestTeamMemberRole)
        throws SystemException {
        contestTeamMemberRole = toUnwrappedModel(contestTeamMemberRole);

        boolean isNew = contestTeamMemberRole.isNew();

        Session session = null;

        try {
            session = openSession();

            if (contestTeamMemberRole.isNew()) {
                session.save(contestTeamMemberRole);

                contestTeamMemberRole.setNew(false);
            } else {
                session.merge(contestTeamMemberRole);
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

        EntityCacheUtil.putResult(ContestTeamMemberRoleModelImpl.ENTITY_CACHE_ENABLED,
            ContestTeamMemberRoleImpl.class,
            contestTeamMemberRole.getPrimaryKey(), contestTeamMemberRole);

        return contestTeamMemberRole;
    }

    protected ContestTeamMemberRole toUnwrappedModel(
        ContestTeamMemberRole contestTeamMemberRole) {
        if (contestTeamMemberRole instanceof ContestTeamMemberRoleImpl) {
            return contestTeamMemberRole;
        }

        ContestTeamMemberRoleImpl contestTeamMemberRoleImpl = new ContestTeamMemberRoleImpl();

        contestTeamMemberRoleImpl.setNew(contestTeamMemberRole.isNew());
        contestTeamMemberRoleImpl.setPrimaryKey(contestTeamMemberRole.getPrimaryKey());

        contestTeamMemberRoleImpl.setId(contestTeamMemberRole.getId());
        contestTeamMemberRoleImpl.setRole(contestTeamMemberRole.getRole());
        contestTeamMemberRoleImpl.setSort(contestTeamMemberRole.getSort());

        return contestTeamMemberRoleImpl;
    }

    /**
     * Returns the contest team member role with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the contest team member role
     * @return the contest team member role
     * @throws com.ext.portlet.NoSuchContestTeamMemberRoleException if a contest team member role with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestTeamMemberRole findByPrimaryKey(Serializable primaryKey)
        throws NoSuchContestTeamMemberRoleException, SystemException {
        ContestTeamMemberRole contestTeamMemberRole = fetchByPrimaryKey(primaryKey);

        if (contestTeamMemberRole == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchContestTeamMemberRoleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return contestTeamMemberRole;
    }

    /**
     * Returns the contest team member role with the primary key or throws a {@link com.ext.portlet.NoSuchContestTeamMemberRoleException} if it could not be found.
     *
     * @param id the primary key of the contest team member role
     * @return the contest team member role
     * @throws com.ext.portlet.NoSuchContestTeamMemberRoleException if a contest team member role with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestTeamMemberRole findByPrimaryKey(long id)
        throws NoSuchContestTeamMemberRoleException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the contest team member role with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the contest team member role
     * @return the contest team member role, or <code>null</code> if a contest team member role with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestTeamMemberRole fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ContestTeamMemberRole contestTeamMemberRole = (ContestTeamMemberRole) EntityCacheUtil.getResult(ContestTeamMemberRoleModelImpl.ENTITY_CACHE_ENABLED,
                ContestTeamMemberRoleImpl.class, primaryKey);

        if (contestTeamMemberRole == _nullContestTeamMemberRole) {
            return null;
        }

        if (contestTeamMemberRole == null) {
            Session session = null;

            try {
                session = openSession();

                contestTeamMemberRole = (ContestTeamMemberRole) session.get(ContestTeamMemberRoleImpl.class,
                        primaryKey);

                if (contestTeamMemberRole != null) {
                    cacheResult(contestTeamMemberRole);
                } else {
                    EntityCacheUtil.putResult(ContestTeamMemberRoleModelImpl.ENTITY_CACHE_ENABLED,
                        ContestTeamMemberRoleImpl.class, primaryKey,
                        _nullContestTeamMemberRole);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ContestTeamMemberRoleModelImpl.ENTITY_CACHE_ENABLED,
                    ContestTeamMemberRoleImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return contestTeamMemberRole;
    }

    /**
     * Returns the contest team member role with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the contest team member role
     * @return the contest team member role, or <code>null</code> if a contest team member role with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestTeamMemberRole fetchByPrimaryKey(long id)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the contest team member roles.
     *
     * @return the contest team member roles
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestTeamMemberRole> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contest team member roles.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestTeamMemberRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contest team member roles
     * @param end the upper bound of the range of contest team member roles (not inclusive)
     * @return the range of contest team member roles
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestTeamMemberRole> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the contest team member roles.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestTeamMemberRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contest team member roles
     * @param end the upper bound of the range of contest team member roles (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of contest team member roles
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestTeamMemberRole> findAll(int start, int end,
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

        List<ContestTeamMemberRole> list = (List<ContestTeamMemberRole>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CONTESTTEAMMEMBERROLE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CONTESTTEAMMEMBERROLE;

                if (pagination) {
                    sql = sql.concat(ContestTeamMemberRoleModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ContestTeamMemberRole>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ContestTeamMemberRole>(list);
                } else {
                    list = (List<ContestTeamMemberRole>) QueryUtil.list(q,
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
     * Removes all the contest team member roles from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ContestTeamMemberRole contestTeamMemberRole : findAll()) {
            remove(contestTeamMemberRole);
        }
    }

    /**
     * Returns the number of contest team member roles.
     *
     * @return the number of contest team member roles
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

                Query q = session.createQuery(_SQL_COUNT_CONTESTTEAMMEMBERROLE);

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
     * Initializes the contest team member role persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ContestTeamMemberRole")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ContestTeamMemberRole>> listenersList = new ArrayList<ModelListener<ContestTeamMemberRole>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ContestTeamMemberRole>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ContestTeamMemberRoleImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
