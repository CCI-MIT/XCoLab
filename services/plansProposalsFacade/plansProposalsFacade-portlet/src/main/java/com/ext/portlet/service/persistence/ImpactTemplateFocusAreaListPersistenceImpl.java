package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchImpactTemplateFocusAreaListException;
import com.ext.portlet.model.ImpactTemplateFocusAreaList;
import com.ext.portlet.model.impl.ImpactTemplateFocusAreaListImpl;
import com.ext.portlet.model.impl.ImpactTemplateFocusAreaListModelImpl;
import com.ext.portlet.service.persistence.ImpactTemplateFocusAreaListPersistence;

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
 * The persistence implementation for the impact template focus area list service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactTemplateFocusAreaListPersistence
 * @see ImpactTemplateFocusAreaListUtil
 * @generated
 */
public class ImpactTemplateFocusAreaListPersistenceImpl
    extends BasePersistenceImpl<ImpactTemplateFocusAreaList>
    implements ImpactTemplateFocusAreaListPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ImpactTemplateFocusAreaListUtil} to access the impact template focus area list persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ImpactTemplateFocusAreaListImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImpactTemplateFocusAreaListModelImpl.ENTITY_CACHE_ENABLED,
            ImpactTemplateFocusAreaListModelImpl.FINDER_CACHE_ENABLED,
            ImpactTemplateFocusAreaListImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImpactTemplateFocusAreaListModelImpl.ENTITY_CACHE_ENABLED,
            ImpactTemplateFocusAreaListModelImpl.FINDER_CACHE_ENABLED,
            ImpactTemplateFocusAreaListImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImpactTemplateFocusAreaListModelImpl.ENTITY_CACHE_ENABLED,
            ImpactTemplateFocusAreaListModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    private static final String _SQL_SELECT_IMPACTTEMPLATEFOCUSAREALIST = "SELECT impactTemplateFocusAreaList FROM ImpactTemplateFocusAreaList impactTemplateFocusAreaList";
    private static final String _SQL_COUNT_IMPACTTEMPLATEFOCUSAREALIST = "SELECT COUNT(impactTemplateFocusAreaList) FROM ImpactTemplateFocusAreaList impactTemplateFocusAreaList";
    private static final String _ORDER_BY_ENTITY_ALIAS = "impactTemplateFocusAreaList.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImpactTemplateFocusAreaList exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ImpactTemplateFocusAreaListPersistenceImpl.class);
    private static ImpactTemplateFocusAreaList _nullImpactTemplateFocusAreaList = new ImpactTemplateFocusAreaListImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ImpactTemplateFocusAreaList> toCacheModel() {
                return _nullImpactTemplateFocusAreaListCacheModel;
            }
        };

    private static CacheModel<ImpactTemplateFocusAreaList> _nullImpactTemplateFocusAreaListCacheModel =
        new CacheModel<ImpactTemplateFocusAreaList>() {
            @Override
            public ImpactTemplateFocusAreaList toEntityModel() {
                return _nullImpactTemplateFocusAreaList;
            }
        };

    public ImpactTemplateFocusAreaListPersistenceImpl() {
        setModelClass(ImpactTemplateFocusAreaList.class);
    }

    /**
     * Caches the impact template focus area list in the entity cache if it is enabled.
     *
     * @param impactTemplateFocusAreaList the impact template focus area list
     */
    @Override
    public void cacheResult(
        ImpactTemplateFocusAreaList impactTemplateFocusAreaList) {
        EntityCacheUtil.putResult(ImpactTemplateFocusAreaListModelImpl.ENTITY_CACHE_ENABLED,
            ImpactTemplateFocusAreaListImpl.class,
            impactTemplateFocusAreaList.getPrimaryKey(),
            impactTemplateFocusAreaList);

        impactTemplateFocusAreaList.resetOriginalValues();
    }

    /**
     * Caches the impact template focus area lists in the entity cache if it is enabled.
     *
     * @param impactTemplateFocusAreaLists the impact template focus area lists
     */
    @Override
    public void cacheResult(
        List<ImpactTemplateFocusAreaList> impactTemplateFocusAreaLists) {
        for (ImpactTemplateFocusAreaList impactTemplateFocusAreaList : impactTemplateFocusAreaLists) {
            if (EntityCacheUtil.getResult(
                        ImpactTemplateFocusAreaListModelImpl.ENTITY_CACHE_ENABLED,
                        ImpactTemplateFocusAreaListImpl.class,
                        impactTemplateFocusAreaList.getPrimaryKey()) == null) {
                cacheResult(impactTemplateFocusAreaList);
            } else {
                impactTemplateFocusAreaList.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all impact template focus area lists.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ImpactTemplateFocusAreaListImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ImpactTemplateFocusAreaListImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the impact template focus area list.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(
        ImpactTemplateFocusAreaList impactTemplateFocusAreaList) {
        EntityCacheUtil.removeResult(ImpactTemplateFocusAreaListModelImpl.ENTITY_CACHE_ENABLED,
            ImpactTemplateFocusAreaListImpl.class,
            impactTemplateFocusAreaList.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<ImpactTemplateFocusAreaList> impactTemplateFocusAreaLists) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ImpactTemplateFocusAreaList impactTemplateFocusAreaList : impactTemplateFocusAreaLists) {
            EntityCacheUtil.removeResult(ImpactTemplateFocusAreaListModelImpl.ENTITY_CACHE_ENABLED,
                ImpactTemplateFocusAreaListImpl.class,
                impactTemplateFocusAreaList.getPrimaryKey());
        }
    }

    /**
     * Creates a new impact template focus area list with the primary key. Does not add the impact template focus area list to the database.
     *
     * @param focusAreaListId the primary key for the new impact template focus area list
     * @return the new impact template focus area list
     */
    @Override
    public ImpactTemplateFocusAreaList create(long focusAreaListId) {
        ImpactTemplateFocusAreaList impactTemplateFocusAreaList = new ImpactTemplateFocusAreaListImpl();

        impactTemplateFocusAreaList.setNew(true);
        impactTemplateFocusAreaList.setPrimaryKey(focusAreaListId);

        return impactTemplateFocusAreaList;
    }

    /**
     * Removes the impact template focus area list with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param focusAreaListId the primary key of the impact template focus area list
     * @return the impact template focus area list that was removed
     * @throws com.ext.portlet.NoSuchImpactTemplateFocusAreaListException if a impact template focus area list with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactTemplateFocusAreaList remove(long focusAreaListId)
        throws NoSuchImpactTemplateFocusAreaListException, SystemException {
        return remove((Serializable) focusAreaListId);
    }

    /**
     * Removes the impact template focus area list with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the impact template focus area list
     * @return the impact template focus area list that was removed
     * @throws com.ext.portlet.NoSuchImpactTemplateFocusAreaListException if a impact template focus area list with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactTemplateFocusAreaList remove(Serializable primaryKey)
        throws NoSuchImpactTemplateFocusAreaListException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ImpactTemplateFocusAreaList impactTemplateFocusAreaList = (ImpactTemplateFocusAreaList) session.get(ImpactTemplateFocusAreaListImpl.class,
                    primaryKey);

            if (impactTemplateFocusAreaList == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchImpactTemplateFocusAreaListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(impactTemplateFocusAreaList);
        } catch (NoSuchImpactTemplateFocusAreaListException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ImpactTemplateFocusAreaList removeImpl(
        ImpactTemplateFocusAreaList impactTemplateFocusAreaList)
        throws SystemException {
        impactTemplateFocusAreaList = toUnwrappedModel(impactTemplateFocusAreaList);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(impactTemplateFocusAreaList)) {
                impactTemplateFocusAreaList = (ImpactTemplateFocusAreaList) session.get(ImpactTemplateFocusAreaListImpl.class,
                        impactTemplateFocusAreaList.getPrimaryKeyObj());
            }

            if (impactTemplateFocusAreaList != null) {
                session.delete(impactTemplateFocusAreaList);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (impactTemplateFocusAreaList != null) {
            clearCache(impactTemplateFocusAreaList);
        }

        return impactTemplateFocusAreaList;
    }

    @Override
    public ImpactTemplateFocusAreaList updateImpl(
        com.ext.portlet.model.ImpactTemplateFocusAreaList impactTemplateFocusAreaList)
        throws SystemException {
        impactTemplateFocusAreaList = toUnwrappedModel(impactTemplateFocusAreaList);

        boolean isNew = impactTemplateFocusAreaList.isNew();

        Session session = null;

        try {
            session = openSession();

            if (impactTemplateFocusAreaList.isNew()) {
                session.save(impactTemplateFocusAreaList);

                impactTemplateFocusAreaList.setNew(false);
            } else {
                session.merge(impactTemplateFocusAreaList);
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

        EntityCacheUtil.putResult(ImpactTemplateFocusAreaListModelImpl.ENTITY_CACHE_ENABLED,
            ImpactTemplateFocusAreaListImpl.class,
            impactTemplateFocusAreaList.getPrimaryKey(),
            impactTemplateFocusAreaList);

        return impactTemplateFocusAreaList;
    }

    protected ImpactTemplateFocusAreaList toUnwrappedModel(
        ImpactTemplateFocusAreaList impactTemplateFocusAreaList) {
        if (impactTemplateFocusAreaList instanceof ImpactTemplateFocusAreaListImpl) {
            return impactTemplateFocusAreaList;
        }

        ImpactTemplateFocusAreaListImpl impactTemplateFocusAreaListImpl = new ImpactTemplateFocusAreaListImpl();

        impactTemplateFocusAreaListImpl.setNew(impactTemplateFocusAreaList.isNew());
        impactTemplateFocusAreaListImpl.setPrimaryKey(impactTemplateFocusAreaList.getPrimaryKey());

        impactTemplateFocusAreaListImpl.setFocusAreaListId(impactTemplateFocusAreaList.getFocusAreaListId());
        impactTemplateFocusAreaListImpl.setName(impactTemplateFocusAreaList.getName());

        return impactTemplateFocusAreaListImpl;
    }

    /**
     * Returns the impact template focus area list with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the impact template focus area list
     * @return the impact template focus area list
     * @throws com.ext.portlet.NoSuchImpactTemplateFocusAreaListException if a impact template focus area list with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactTemplateFocusAreaList findByPrimaryKey(Serializable primaryKey)
        throws NoSuchImpactTemplateFocusAreaListException, SystemException {
        ImpactTemplateFocusAreaList impactTemplateFocusAreaList = fetchByPrimaryKey(primaryKey);

        if (impactTemplateFocusAreaList == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchImpactTemplateFocusAreaListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return impactTemplateFocusAreaList;
    }

    /**
     * Returns the impact template focus area list with the primary key or throws a {@link com.ext.portlet.NoSuchImpactTemplateFocusAreaListException} if it could not be found.
     *
     * @param focusAreaListId the primary key of the impact template focus area list
     * @return the impact template focus area list
     * @throws com.ext.portlet.NoSuchImpactTemplateFocusAreaListException if a impact template focus area list with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactTemplateFocusAreaList findByPrimaryKey(long focusAreaListId)
        throws NoSuchImpactTemplateFocusAreaListException, SystemException {
        return findByPrimaryKey((Serializable) focusAreaListId);
    }

    /**
     * Returns the impact template focus area list with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the impact template focus area list
     * @return the impact template focus area list, or <code>null</code> if a impact template focus area list with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactTemplateFocusAreaList fetchByPrimaryKey(
        Serializable primaryKey) throws SystemException {
        ImpactTemplateFocusAreaList impactTemplateFocusAreaList = (ImpactTemplateFocusAreaList) EntityCacheUtil.getResult(ImpactTemplateFocusAreaListModelImpl.ENTITY_CACHE_ENABLED,
                ImpactTemplateFocusAreaListImpl.class, primaryKey);

        if (impactTemplateFocusAreaList == _nullImpactTemplateFocusAreaList) {
            return null;
        }

        if (impactTemplateFocusAreaList == null) {
            Session session = null;

            try {
                session = openSession();

                impactTemplateFocusAreaList = (ImpactTemplateFocusAreaList) session.get(ImpactTemplateFocusAreaListImpl.class,
                        primaryKey);

                if (impactTemplateFocusAreaList != null) {
                    cacheResult(impactTemplateFocusAreaList);
                } else {
                    EntityCacheUtil.putResult(ImpactTemplateFocusAreaListModelImpl.ENTITY_CACHE_ENABLED,
                        ImpactTemplateFocusAreaListImpl.class, primaryKey,
                        _nullImpactTemplateFocusAreaList);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ImpactTemplateFocusAreaListModelImpl.ENTITY_CACHE_ENABLED,
                    ImpactTemplateFocusAreaListImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return impactTemplateFocusAreaList;
    }

    /**
     * Returns the impact template focus area list with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param focusAreaListId the primary key of the impact template focus area list
     * @return the impact template focus area list, or <code>null</code> if a impact template focus area list with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactTemplateFocusAreaList fetchByPrimaryKey(long focusAreaListId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) focusAreaListId);
    }

    /**
     * Returns all the impact template focus area lists.
     *
     * @return the impact template focus area lists
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImpactTemplateFocusAreaList> findAll()
        throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the impact template focus area lists.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateFocusAreaListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of impact template focus area lists
     * @param end the upper bound of the range of impact template focus area lists (not inclusive)
     * @return the range of impact template focus area lists
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImpactTemplateFocusAreaList> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the impact template focus area lists.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateFocusAreaListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of impact template focus area lists
     * @param end the upper bound of the range of impact template focus area lists (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of impact template focus area lists
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImpactTemplateFocusAreaList> findAll(int start, int end,
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

        List<ImpactTemplateFocusAreaList> list = (List<ImpactTemplateFocusAreaList>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IMPACTTEMPLATEFOCUSAREALIST);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IMPACTTEMPLATEFOCUSAREALIST;

                if (pagination) {
                    sql = sql.concat(ImpactTemplateFocusAreaListModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ImpactTemplateFocusAreaList>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ImpactTemplateFocusAreaList>(list);
                } else {
                    list = (List<ImpactTemplateFocusAreaList>) QueryUtil.list(q,
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
     * Removes all the impact template focus area lists from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ImpactTemplateFocusAreaList impactTemplateFocusAreaList : findAll()) {
            remove(impactTemplateFocusAreaList);
        }
    }

    /**
     * Returns the number of impact template focus area lists.
     *
     * @return the number of impact template focus area lists
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

                Query q = session.createQuery(_SQL_COUNT_IMPACTTEMPLATEFOCUSAREALIST);

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
     * Initializes the impact template focus area list persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ImpactTemplateFocusAreaList")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ImpactTemplateFocusAreaList>> listenersList = new ArrayList<ModelListener<ImpactTemplateFocusAreaList>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ImpactTemplateFocusAreaList>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ImpactTemplateFocusAreaListImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
