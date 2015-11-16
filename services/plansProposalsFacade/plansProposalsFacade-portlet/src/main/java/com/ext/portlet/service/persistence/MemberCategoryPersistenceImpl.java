package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchMemberCategoryException;
import com.ext.portlet.model.MemberCategory;
import com.ext.portlet.model.impl.MemberCategoryImpl;
import com.ext.portlet.model.impl.MemberCategoryModelImpl;
import com.ext.portlet.service.persistence.MemberCategoryPersistence;

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
 * The persistence implementation for the member category service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MemberCategoryPersistence
 * @see MemberCategoryUtil
 * @generated
 */
public class MemberCategoryPersistenceImpl extends BasePersistenceImpl<MemberCategory>
    implements MemberCategoryPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MemberCategoryUtil} to access the member category persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MemberCategoryImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
            MemberCategoryModelImpl.FINDER_CACHE_ENABLED,
            MemberCategoryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
            MemberCategoryModelImpl.FINDER_CACHE_ENABLED,
            MemberCategoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
            MemberCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_MEMBERCATEGORY = "SELECT memberCategory FROM MemberCategory memberCategory";
    private static final String _SQL_COUNT_MEMBERCATEGORY = "SELECT COUNT(memberCategory) FROM MemberCategory memberCategory";
    private static final String _ORDER_BY_ENTITY_ALIAS = "memberCategory.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MemberCategory exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MemberCategoryPersistenceImpl.class);
    private static MemberCategory _nullMemberCategory = new MemberCategoryImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MemberCategory> toCacheModel() {
                return _nullMemberCategoryCacheModel;
            }
        };

    private static CacheModel<MemberCategory> _nullMemberCategoryCacheModel = new CacheModel<MemberCategory>() {
            @Override
            public MemberCategory toEntityModel() {
                return _nullMemberCategory;
            }
        };

    public MemberCategoryPersistenceImpl() {
        setModelClass(MemberCategory.class);
    }

    /**
     * Caches the member category in the entity cache if it is enabled.
     *
     * @param memberCategory the member category
     */
    @Override
    public void cacheResult(MemberCategory memberCategory) {
        EntityCacheUtil.putResult(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
            MemberCategoryImpl.class, memberCategory.getPrimaryKey(),
            memberCategory);

        memberCategory.resetOriginalValues();
    }

    /**
     * Caches the member categories in the entity cache if it is enabled.
     *
     * @param memberCategories the member categories
     */
    @Override
    public void cacheResult(List<MemberCategory> memberCategories) {
        for (MemberCategory memberCategory : memberCategories) {
            if (EntityCacheUtil.getResult(
                        MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
                        MemberCategoryImpl.class, memberCategory.getPrimaryKey()) == null) {
                cacheResult(memberCategory);
            } else {
                memberCategory.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all member categories.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MemberCategoryImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MemberCategoryImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the member category.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(MemberCategory memberCategory) {
        EntityCacheUtil.removeResult(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
            MemberCategoryImpl.class, memberCategory.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<MemberCategory> memberCategories) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MemberCategory memberCategory : memberCategories) {
            EntityCacheUtil.removeResult(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
                MemberCategoryImpl.class, memberCategory.getPrimaryKey());
        }
    }

    /**
     * Creates a new member category with the primary key. Does not add the member category to the database.
     *
     * @param roleId the primary key for the new member category
     * @return the new member category
     */
    @Override
    public MemberCategory create(long roleId) {
        MemberCategory memberCategory = new MemberCategoryImpl();

        memberCategory.setNew(true);
        memberCategory.setPrimaryKey(roleId);

        return memberCategory;
    }

    /**
     * Removes the member category with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param roleId the primary key of the member category
     * @return the member category that was removed
     * @throws com.ext.portlet.NoSuchMemberCategoryException if a member category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MemberCategory remove(long roleId)
        throws NoSuchMemberCategoryException, SystemException {
        return remove((Serializable) roleId);
    }

    /**
     * Removes the member category with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the member category
     * @return the member category that was removed
     * @throws com.ext.portlet.NoSuchMemberCategoryException if a member category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MemberCategory remove(Serializable primaryKey)
        throws NoSuchMemberCategoryException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MemberCategory memberCategory = (MemberCategory) session.get(MemberCategoryImpl.class,
                    primaryKey);

            if (memberCategory == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMemberCategoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(memberCategory);
        } catch (NoSuchMemberCategoryException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MemberCategory removeImpl(MemberCategory memberCategory)
        throws SystemException {
        memberCategory = toUnwrappedModel(memberCategory);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(memberCategory)) {
                memberCategory = (MemberCategory) session.get(MemberCategoryImpl.class,
                        memberCategory.getPrimaryKeyObj());
            }

            if (memberCategory != null) {
                session.delete(memberCategory);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (memberCategory != null) {
            clearCache(memberCategory);
        }

        return memberCategory;
    }

    @Override
    public MemberCategory updateImpl(
        com.ext.portlet.model.MemberCategory memberCategory)
        throws SystemException {
        memberCategory = toUnwrappedModel(memberCategory);

        boolean isNew = memberCategory.isNew();

        Session session = null;

        try {
            session = openSession();

            if (memberCategory.isNew()) {
                session.save(memberCategory);

                memberCategory.setNew(false);
            } else {
                session.merge(memberCategory);
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

        EntityCacheUtil.putResult(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
            MemberCategoryImpl.class, memberCategory.getPrimaryKey(),
            memberCategory);

        return memberCategory;
    }

    protected MemberCategory toUnwrappedModel(MemberCategory memberCategory) {
        if (memberCategory instanceof MemberCategoryImpl) {
            return memberCategory;
        }

        MemberCategoryImpl memberCategoryImpl = new MemberCategoryImpl();

        memberCategoryImpl.setNew(memberCategory.isNew());
        memberCategoryImpl.setPrimaryKey(memberCategory.getPrimaryKey());

        memberCategoryImpl.setRoleId(memberCategory.getRoleId());
        memberCategoryImpl.setDisplayName(memberCategory.getDisplayName());
        memberCategoryImpl.setCategoryName(memberCategory.getCategoryName());
        memberCategoryImpl.setSortOrder(memberCategory.getSortOrder());
        memberCategoryImpl.setShowInList(memberCategory.isShowInList());
        memberCategoryImpl.setImageName(memberCategory.getImageName());

        return memberCategoryImpl;
    }

    /**
     * Returns the member category with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the member category
     * @return the member category
     * @throws com.ext.portlet.NoSuchMemberCategoryException if a member category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MemberCategory findByPrimaryKey(Serializable primaryKey)
        throws NoSuchMemberCategoryException, SystemException {
        MemberCategory memberCategory = fetchByPrimaryKey(primaryKey);

        if (memberCategory == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchMemberCategoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return memberCategory;
    }

    /**
     * Returns the member category with the primary key or throws a {@link com.ext.portlet.NoSuchMemberCategoryException} if it could not be found.
     *
     * @param roleId the primary key of the member category
     * @return the member category
     * @throws com.ext.portlet.NoSuchMemberCategoryException if a member category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MemberCategory findByPrimaryKey(long roleId)
        throws NoSuchMemberCategoryException, SystemException {
        return findByPrimaryKey((Serializable) roleId);
    }

    /**
     * Returns the member category with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the member category
     * @return the member category, or <code>null</code> if a member category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MemberCategory fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        MemberCategory memberCategory = (MemberCategory) EntityCacheUtil.getResult(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
                MemberCategoryImpl.class, primaryKey);

        if (memberCategory == _nullMemberCategory) {
            return null;
        }

        if (memberCategory == null) {
            Session session = null;

            try {
                session = openSession();

                memberCategory = (MemberCategory) session.get(MemberCategoryImpl.class,
                        primaryKey);

                if (memberCategory != null) {
                    cacheResult(memberCategory);
                } else {
                    EntityCacheUtil.putResult(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
                        MemberCategoryImpl.class, primaryKey,
                        _nullMemberCategory);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(MemberCategoryModelImpl.ENTITY_CACHE_ENABLED,
                    MemberCategoryImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return memberCategory;
    }

    /**
     * Returns the member category with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param roleId the primary key of the member category
     * @return the member category, or <code>null</code> if a member category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MemberCategory fetchByPrimaryKey(long roleId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) roleId);
    }

    /**
     * Returns all the member categories.
     *
     * @return the member categories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MemberCategory> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the member categories.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MemberCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of member categories
     * @param end the upper bound of the range of member categories (not inclusive)
     * @return the range of member categories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MemberCategory> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the member categories.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MemberCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of member categories
     * @param end the upper bound of the range of member categories (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of member categories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MemberCategory> findAll(int start, int end,
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

        List<MemberCategory> list = (List<MemberCategory>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MEMBERCATEGORY);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MEMBERCATEGORY;

                if (pagination) {
                    sql = sql.concat(MemberCategoryModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<MemberCategory>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MemberCategory>(list);
                } else {
                    list = (List<MemberCategory>) QueryUtil.list(q,
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
     * Removes all the member categories from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (MemberCategory memberCategory : findAll()) {
            remove(memberCategory);
        }
    }

    /**
     * Returns the number of member categories.
     *
     * @return the number of member categories
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

                Query q = session.createQuery(_SQL_COUNT_MEMBERCATEGORY);

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
     * Initializes the member category persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.MemberCategory")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MemberCategory>> listenersList = new ArrayList<ModelListener<MemberCategory>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MemberCategory>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MemberCategoryImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
