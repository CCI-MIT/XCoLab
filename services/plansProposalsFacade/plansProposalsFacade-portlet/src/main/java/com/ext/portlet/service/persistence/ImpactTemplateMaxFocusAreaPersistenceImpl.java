package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchImpactTemplateMaxFocusAreaException;
import com.ext.portlet.model.ImpactTemplateMaxFocusArea;
import com.ext.portlet.model.impl.ImpactTemplateMaxFocusAreaImpl;
import com.ext.portlet.model.impl.ImpactTemplateMaxFocusAreaModelImpl;
import com.ext.portlet.service.persistence.ImpactTemplateMaxFocusAreaPersistence;

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
 * The persistence implementation for the impact template max focus area service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactTemplateMaxFocusAreaPersistence
 * @see ImpactTemplateMaxFocusAreaUtil
 * @generated
 */
public class ImpactTemplateMaxFocusAreaPersistenceImpl
    extends BasePersistenceImpl<ImpactTemplateMaxFocusArea>
    implements ImpactTemplateMaxFocusAreaPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ImpactTemplateMaxFocusAreaUtil} to access the impact template max focus area persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ImpactTemplateMaxFocusAreaImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImpactTemplateMaxFocusAreaModelImpl.ENTITY_CACHE_ENABLED,
            ImpactTemplateMaxFocusAreaModelImpl.FINDER_CACHE_ENABLED,
            ImpactTemplateMaxFocusAreaImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImpactTemplateMaxFocusAreaModelImpl.ENTITY_CACHE_ENABLED,
            ImpactTemplateMaxFocusAreaModelImpl.FINDER_CACHE_ENABLED,
            ImpactTemplateMaxFocusAreaImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImpactTemplateMaxFocusAreaModelImpl.ENTITY_CACHE_ENABLED,
            ImpactTemplateMaxFocusAreaModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    private static final String _SQL_SELECT_IMPACTTEMPLATEMAXFOCUSAREA = "SELECT impactTemplateMaxFocusArea FROM ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea";
    private static final String _SQL_COUNT_IMPACTTEMPLATEMAXFOCUSAREA = "SELECT COUNT(impactTemplateMaxFocusArea) FROM ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea";
    private static final String _ORDER_BY_ENTITY_ALIAS = "impactTemplateMaxFocusArea.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImpactTemplateMaxFocusArea exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ImpactTemplateMaxFocusAreaPersistenceImpl.class);
    private static ImpactTemplateMaxFocusArea _nullImpactTemplateMaxFocusArea = new ImpactTemplateMaxFocusAreaImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ImpactTemplateMaxFocusArea> toCacheModel() {
                return _nullImpactTemplateMaxFocusAreaCacheModel;
            }
        };

    private static CacheModel<ImpactTemplateMaxFocusArea> _nullImpactTemplateMaxFocusAreaCacheModel =
        new CacheModel<ImpactTemplateMaxFocusArea>() {
            @Override
            public ImpactTemplateMaxFocusArea toEntityModel() {
                return _nullImpactTemplateMaxFocusArea;
            }
        };

    public ImpactTemplateMaxFocusAreaPersistenceImpl() {
        setModelClass(ImpactTemplateMaxFocusArea.class);
    }

    /**
     * Caches the impact template max focus area in the entity cache if it is enabled.
     *
     * @param impactTemplateMaxFocusArea the impact template max focus area
     */
    @Override
    public void cacheResult(
        ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea) {
        EntityCacheUtil.putResult(ImpactTemplateMaxFocusAreaModelImpl.ENTITY_CACHE_ENABLED,
            ImpactTemplateMaxFocusAreaImpl.class,
            impactTemplateMaxFocusArea.getPrimaryKey(),
            impactTemplateMaxFocusArea);

        impactTemplateMaxFocusArea.resetOriginalValues();
    }

    /**
     * Caches the impact template max focus areas in the entity cache if it is enabled.
     *
     * @param impactTemplateMaxFocusAreas the impact template max focus areas
     */
    @Override
    public void cacheResult(
        List<ImpactTemplateMaxFocusArea> impactTemplateMaxFocusAreas) {
        for (ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea : impactTemplateMaxFocusAreas) {
            if (EntityCacheUtil.getResult(
                        ImpactTemplateMaxFocusAreaModelImpl.ENTITY_CACHE_ENABLED,
                        ImpactTemplateMaxFocusAreaImpl.class,
                        impactTemplateMaxFocusArea.getPrimaryKey()) == null) {
                cacheResult(impactTemplateMaxFocusArea);
            } else {
                impactTemplateMaxFocusArea.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all impact template max focus areas.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ImpactTemplateMaxFocusAreaImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ImpactTemplateMaxFocusAreaImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the impact template max focus area.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(
        ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea) {
        EntityCacheUtil.removeResult(ImpactTemplateMaxFocusAreaModelImpl.ENTITY_CACHE_ENABLED,
            ImpactTemplateMaxFocusAreaImpl.class,
            impactTemplateMaxFocusArea.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<ImpactTemplateMaxFocusArea> impactTemplateMaxFocusAreas) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea : impactTemplateMaxFocusAreas) {
            EntityCacheUtil.removeResult(ImpactTemplateMaxFocusAreaModelImpl.ENTITY_CACHE_ENABLED,
                ImpactTemplateMaxFocusAreaImpl.class,
                impactTemplateMaxFocusArea.getPrimaryKey());
        }
    }

    /**
     * Creates a new impact template max focus area with the primary key. Does not add the impact template max focus area to the database.
     *
     * @param impactTemplateMaxFocusAreaPK the primary key for the new impact template max focus area
     * @return the new impact template max focus area
     */
    @Override
    public ImpactTemplateMaxFocusArea create(
        ImpactTemplateMaxFocusAreaPK impactTemplateMaxFocusAreaPK) {
        ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea = new ImpactTemplateMaxFocusAreaImpl();

        impactTemplateMaxFocusArea.setNew(true);
        impactTemplateMaxFocusArea.setPrimaryKey(impactTemplateMaxFocusAreaPK);

        return impactTemplateMaxFocusArea;
    }

    /**
     * Removes the impact template max focus area with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param impactTemplateMaxFocusAreaPK the primary key of the impact template max focus area
     * @return the impact template max focus area that was removed
     * @throws com.ext.portlet.NoSuchImpactTemplateMaxFocusAreaException if a impact template max focus area with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactTemplateMaxFocusArea remove(
        ImpactTemplateMaxFocusAreaPK impactTemplateMaxFocusAreaPK)
        throws NoSuchImpactTemplateMaxFocusAreaException, SystemException {
        return remove((Serializable) impactTemplateMaxFocusAreaPK);
    }

    /**
     * Removes the impact template max focus area with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the impact template max focus area
     * @return the impact template max focus area that was removed
     * @throws com.ext.portlet.NoSuchImpactTemplateMaxFocusAreaException if a impact template max focus area with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactTemplateMaxFocusArea remove(Serializable primaryKey)
        throws NoSuchImpactTemplateMaxFocusAreaException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea = (ImpactTemplateMaxFocusArea) session.get(ImpactTemplateMaxFocusAreaImpl.class,
                    primaryKey);

            if (impactTemplateMaxFocusArea == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchImpactTemplateMaxFocusAreaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(impactTemplateMaxFocusArea);
        } catch (NoSuchImpactTemplateMaxFocusAreaException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ImpactTemplateMaxFocusArea removeImpl(
        ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea)
        throws SystemException {
        impactTemplateMaxFocusArea = toUnwrappedModel(impactTemplateMaxFocusArea);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(impactTemplateMaxFocusArea)) {
                impactTemplateMaxFocusArea = (ImpactTemplateMaxFocusArea) session.get(ImpactTemplateMaxFocusAreaImpl.class,
                        impactTemplateMaxFocusArea.getPrimaryKeyObj());
            }

            if (impactTemplateMaxFocusArea != null) {
                session.delete(impactTemplateMaxFocusArea);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (impactTemplateMaxFocusArea != null) {
            clearCache(impactTemplateMaxFocusArea);
        }

        return impactTemplateMaxFocusArea;
    }

    @Override
    public ImpactTemplateMaxFocusArea updateImpl(
        com.ext.portlet.model.ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea)
        throws SystemException {
        impactTemplateMaxFocusArea = toUnwrappedModel(impactTemplateMaxFocusArea);

        boolean isNew = impactTemplateMaxFocusArea.isNew();

        Session session = null;

        try {
            session = openSession();

            if (impactTemplateMaxFocusArea.isNew()) {
                session.save(impactTemplateMaxFocusArea);

                impactTemplateMaxFocusArea.setNew(false);
            } else {
                session.merge(impactTemplateMaxFocusArea);
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

        EntityCacheUtil.putResult(ImpactTemplateMaxFocusAreaModelImpl.ENTITY_CACHE_ENABLED,
            ImpactTemplateMaxFocusAreaImpl.class,
            impactTemplateMaxFocusArea.getPrimaryKey(),
            impactTemplateMaxFocusArea);

        return impactTemplateMaxFocusArea;
    }

    protected ImpactTemplateMaxFocusArea toUnwrappedModel(
        ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea) {
        if (impactTemplateMaxFocusArea instanceof ImpactTemplateMaxFocusAreaImpl) {
            return impactTemplateMaxFocusArea;
        }

        ImpactTemplateMaxFocusAreaImpl impactTemplateMaxFocusAreaImpl = new ImpactTemplateMaxFocusAreaImpl();

        impactTemplateMaxFocusAreaImpl.setNew(impactTemplateMaxFocusArea.isNew());
        impactTemplateMaxFocusAreaImpl.setPrimaryKey(impactTemplateMaxFocusArea.getPrimaryKey());

        impactTemplateMaxFocusAreaImpl.setFocusAreaListId(impactTemplateMaxFocusArea.getFocusAreaListId());
        impactTemplateMaxFocusAreaImpl.setFocusAreaId(impactTemplateMaxFocusArea.getFocusAreaId());

        return impactTemplateMaxFocusAreaImpl;
    }

    /**
     * Returns the impact template max focus area with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the impact template max focus area
     * @return the impact template max focus area
     * @throws com.ext.portlet.NoSuchImpactTemplateMaxFocusAreaException if a impact template max focus area with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactTemplateMaxFocusArea findByPrimaryKey(Serializable primaryKey)
        throws NoSuchImpactTemplateMaxFocusAreaException, SystemException {
        ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea = fetchByPrimaryKey(primaryKey);

        if (impactTemplateMaxFocusArea == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchImpactTemplateMaxFocusAreaException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return impactTemplateMaxFocusArea;
    }

    /**
     * Returns the impact template max focus area with the primary key or throws a {@link com.ext.portlet.NoSuchImpactTemplateMaxFocusAreaException} if it could not be found.
     *
     * @param impactTemplateMaxFocusAreaPK the primary key of the impact template max focus area
     * @return the impact template max focus area
     * @throws com.ext.portlet.NoSuchImpactTemplateMaxFocusAreaException if a impact template max focus area with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactTemplateMaxFocusArea findByPrimaryKey(
        ImpactTemplateMaxFocusAreaPK impactTemplateMaxFocusAreaPK)
        throws NoSuchImpactTemplateMaxFocusAreaException, SystemException {
        return findByPrimaryKey((Serializable) impactTemplateMaxFocusAreaPK);
    }

    /**
     * Returns the impact template max focus area with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the impact template max focus area
     * @return the impact template max focus area, or <code>null</code> if a impact template max focus area with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactTemplateMaxFocusArea fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea = (ImpactTemplateMaxFocusArea) EntityCacheUtil.getResult(ImpactTemplateMaxFocusAreaModelImpl.ENTITY_CACHE_ENABLED,
                ImpactTemplateMaxFocusAreaImpl.class, primaryKey);

        if (impactTemplateMaxFocusArea == _nullImpactTemplateMaxFocusArea) {
            return null;
        }

        if (impactTemplateMaxFocusArea == null) {
            Session session = null;

            try {
                session = openSession();

                impactTemplateMaxFocusArea = (ImpactTemplateMaxFocusArea) session.get(ImpactTemplateMaxFocusAreaImpl.class,
                        primaryKey);

                if (impactTemplateMaxFocusArea != null) {
                    cacheResult(impactTemplateMaxFocusArea);
                } else {
                    EntityCacheUtil.putResult(ImpactTemplateMaxFocusAreaModelImpl.ENTITY_CACHE_ENABLED,
                        ImpactTemplateMaxFocusAreaImpl.class, primaryKey,
                        _nullImpactTemplateMaxFocusArea);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ImpactTemplateMaxFocusAreaModelImpl.ENTITY_CACHE_ENABLED,
                    ImpactTemplateMaxFocusAreaImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return impactTemplateMaxFocusArea;
    }

    /**
     * Returns the impact template max focus area with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param impactTemplateMaxFocusAreaPK the primary key of the impact template max focus area
     * @return the impact template max focus area, or <code>null</code> if a impact template max focus area with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ImpactTemplateMaxFocusArea fetchByPrimaryKey(
        ImpactTemplateMaxFocusAreaPK impactTemplateMaxFocusAreaPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) impactTemplateMaxFocusAreaPK);
    }

    /**
     * Returns all the impact template max focus areas.
     *
     * @return the impact template max focus areas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImpactTemplateMaxFocusArea> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the impact template max focus areas.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateMaxFocusAreaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of impact template max focus areas
     * @param end the upper bound of the range of impact template max focus areas (not inclusive)
     * @return the range of impact template max focus areas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImpactTemplateMaxFocusArea> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the impact template max focus areas.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateMaxFocusAreaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of impact template max focus areas
     * @param end the upper bound of the range of impact template max focus areas (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of impact template max focus areas
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ImpactTemplateMaxFocusArea> findAll(int start, int end,
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

        List<ImpactTemplateMaxFocusArea> list = (List<ImpactTemplateMaxFocusArea>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_IMPACTTEMPLATEMAXFOCUSAREA);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_IMPACTTEMPLATEMAXFOCUSAREA;

                if (pagination) {
                    sql = sql.concat(ImpactTemplateMaxFocusAreaModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ImpactTemplateMaxFocusArea>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ImpactTemplateMaxFocusArea>(list);
                } else {
                    list = (List<ImpactTemplateMaxFocusArea>) QueryUtil.list(q,
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
     * Removes all the impact template max focus areas from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea : findAll()) {
            remove(impactTemplateMaxFocusArea);
        }
    }

    /**
     * Returns the number of impact template max focus areas.
     *
     * @return the number of impact template max focus areas
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

                Query q = session.createQuery(_SQL_COUNT_IMPACTTEMPLATEMAXFOCUSAREA);

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
     * Initializes the impact template max focus area persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ImpactTemplateMaxFocusArea")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ImpactTemplateMaxFocusArea>> listenersList = new ArrayList<ModelListener<ImpactTemplateMaxFocusArea>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ImpactTemplateMaxFocusArea>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ImpactTemplateMaxFocusAreaImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
