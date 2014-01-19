package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPlanTypeException;
import com.ext.portlet.model.PlanType;
import com.ext.portlet.model.impl.PlanTypeImpl;
import com.ext.portlet.model.impl.PlanTypeModelImpl;
import com.ext.portlet.service.persistence.PlanTypePersistence;

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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
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
 * The persistence implementation for the plan type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypePersistence
 * @see PlanTypeUtil
 * @generated
 */
public class PlanTypePersistenceImpl extends BasePersistenceImpl<PlanType>
    implements PlanTypePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanTypeUtil} to access the plan type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanTypeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeModelImpl.FINDER_CACHE_ENABLED, PlanTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeModelImpl.FINDER_CACHE_ENABLED, PlanTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_FETCH_BY_DEFAULT = new FinderPath(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeModelImpl.FINDER_CACHE_ENABLED, PlanTypeImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchBydefault",
            new String[] { Boolean.class.getName() },
            PlanTypeModelImpl.ISDEFAULT_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_DEFAULT = new FinderPath(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBydefault",
            new String[] { Boolean.class.getName() });
    private static final String _FINDER_COLUMN_DEFAULT_ISDEFAULT_2 = "planType.isDefault = ?";
    private static final String _SQL_SELECT_PLANTYPE = "SELECT planType FROM PlanType planType";
    private static final String _SQL_SELECT_PLANTYPE_WHERE = "SELECT planType FROM PlanType planType WHERE ";
    private static final String _SQL_COUNT_PLANTYPE = "SELECT COUNT(planType) FROM PlanType planType";
    private static final String _SQL_COUNT_PLANTYPE_WHERE = "SELECT COUNT(planType) FROM PlanType planType WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planType.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanType exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PlanType exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanTypePersistenceImpl.class);
    private static PlanType _nullPlanType = new PlanTypeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanType> toCacheModel() {
                return _nullPlanTypeCacheModel;
            }
        };

    private static CacheModel<PlanType> _nullPlanTypeCacheModel = new CacheModel<PlanType>() {
            @Override
            public PlanType toEntityModel() {
                return _nullPlanType;
            }
        };

    public PlanTypePersistenceImpl() {
        setModelClass(PlanType.class);
    }

    /**
     * Returns the plan type where isDefault = &#63; or throws a {@link com.ext.portlet.NoSuchPlanTypeException} if it could not be found.
     *
     * @param isDefault the is default
     * @return the matching plan type
     * @throws com.ext.portlet.NoSuchPlanTypeException if a matching plan type could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanType findBydefault(boolean isDefault)
        throws NoSuchPlanTypeException, SystemException {
        PlanType planType = fetchBydefault(isDefault);

        if (planType == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("isDefault=");
            msg.append(isDefault);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanTypeException(msg.toString());
        }

        return planType;
    }

    /**
     * Returns the plan type where isDefault = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param isDefault the is default
     * @return the matching plan type, or <code>null</code> if a matching plan type could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanType fetchBydefault(boolean isDefault) throws SystemException {
        return fetchBydefault(isDefault, true);
    }

    /**
     * Returns the plan type where isDefault = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param isDefault the is default
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching plan type, or <code>null</code> if a matching plan type could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanType fetchBydefault(boolean isDefault, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { isDefault };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_DEFAULT,
                    finderArgs, this);
        }

        if (result instanceof PlanType) {
            PlanType planType = (PlanType) result;

            if ((isDefault != planType.getIsDefault())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_PLANTYPE_WHERE);

            query.append(_FINDER_COLUMN_DEFAULT_ISDEFAULT_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(isDefault);

                List<PlanType> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEFAULT,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "PlanTypePersistenceImpl.fetchBydefault(boolean, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    PlanType planType = list.get(0);

                    result = planType;

                    cacheResult(planType);

                    if ((planType.getIsDefault() != isDefault)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEFAULT,
                            finderArgs, planType);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DEFAULT,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (PlanType) result;
        }
    }

    /**
     * Removes the plan type where isDefault = &#63; from the database.
     *
     * @param isDefault the is default
     * @return the plan type that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanType removeBydefault(boolean isDefault)
        throws NoSuchPlanTypeException, SystemException {
        PlanType planType = findBydefault(isDefault);

        return remove(planType);
    }

    /**
     * Returns the number of plan types where isDefault = &#63;.
     *
     * @param isDefault the is default
     * @return the number of matching plan types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBydefault(boolean isDefault) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_DEFAULT;

        Object[] finderArgs = new Object[] { isDefault };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PLANTYPE_WHERE);

            query.append(_FINDER_COLUMN_DEFAULT_ISDEFAULT_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(isDefault);

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
     * Caches the plan type in the entity cache if it is enabled.
     *
     * @param planType the plan type
     */
    @Override
    public void cacheResult(PlanType planType) {
        EntityCacheUtil.putResult(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeImpl.class, planType.getPrimaryKey(), planType);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEFAULT,
            new Object[] { planType.getIsDefault() }, planType);

        planType.resetOriginalValues();
    }

    /**
     * Caches the plan types in the entity cache if it is enabled.
     *
     * @param planTypes the plan types
     */
    @Override
    public void cacheResult(List<PlanType> planTypes) {
        for (PlanType planType : planTypes) {
            if (EntityCacheUtil.getResult(
                        PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
                        PlanTypeImpl.class, planType.getPrimaryKey()) == null) {
                cacheResult(planType);
            } else {
                planType.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan types.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanTypeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanTypeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan type.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanType planType) {
        EntityCacheUtil.removeResult(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeImpl.class, planType.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(planType);
    }

    @Override
    public void clearCache(List<PlanType> planTypes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanType planType : planTypes) {
            EntityCacheUtil.removeResult(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
                PlanTypeImpl.class, planType.getPrimaryKey());

            clearUniqueFindersCache(planType);
        }
    }

    protected void cacheUniqueFindersCache(PlanType planType) {
        if (planType.isNew()) {
            Object[] args = new Object[] { planType.getIsDefault() };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DEFAULT, args,
                Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEFAULT, args,
                planType);
        } else {
            PlanTypeModelImpl planTypeModelImpl = (PlanTypeModelImpl) planType;

            if ((planTypeModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_DEFAULT.getColumnBitmask()) != 0) {
                Object[] args = new Object[] { planType.getIsDefault() };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DEFAULT, args,
                    Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEFAULT, args,
                    planType);
            }
        }
    }

    protected void clearUniqueFindersCache(PlanType planType) {
        PlanTypeModelImpl planTypeModelImpl = (PlanTypeModelImpl) planType;

        Object[] args = new Object[] { planType.getIsDefault() };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DEFAULT, args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DEFAULT, args);

        if ((planTypeModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_DEFAULT.getColumnBitmask()) != 0) {
            args = new Object[] { planTypeModelImpl.getOriginalIsDefault() };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DEFAULT, args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DEFAULT, args);
        }
    }

    /**
     * Creates a new plan type with the primary key. Does not add the plan type to the database.
     *
     * @param planTypeId the primary key for the new plan type
     * @return the new plan type
     */
    @Override
    public PlanType create(long planTypeId) {
        PlanType planType = new PlanTypeImpl();

        planType.setNew(true);
        planType.setPrimaryKey(planTypeId);

        return planType;
    }

    /**
     * Removes the plan type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param planTypeId the primary key of the plan type
     * @return the plan type that was removed
     * @throws com.ext.portlet.NoSuchPlanTypeException if a plan type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanType remove(long planTypeId)
        throws NoSuchPlanTypeException, SystemException {
        return remove((Serializable) planTypeId);
    }

    /**
     * Removes the plan type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan type
     * @return the plan type that was removed
     * @throws com.ext.portlet.NoSuchPlanTypeException if a plan type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanType remove(Serializable primaryKey)
        throws NoSuchPlanTypeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanType planType = (PlanType) session.get(PlanTypeImpl.class,
                    primaryKey);

            if (planType == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planType);
        } catch (NoSuchPlanTypeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanType removeImpl(PlanType planType) throws SystemException {
        planType = toUnwrappedModel(planType);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(planType)) {
                planType = (PlanType) session.get(PlanTypeImpl.class,
                        planType.getPrimaryKeyObj());
            }

            if (planType != null) {
                session.delete(planType);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (planType != null) {
            clearCache(planType);
        }

        return planType;
    }

    @Override
    public PlanType updateImpl(com.ext.portlet.model.PlanType planType)
        throws SystemException {
        planType = toUnwrappedModel(planType);

        boolean isNew = planType.isNew();

        Session session = null;

        try {
            session = openSession();

            if (planType.isNew()) {
                session.save(planType);

                planType.setNew(false);
            } else {
                session.merge(planType);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PlanTypeModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeImpl.class, planType.getPrimaryKey(), planType);

        clearUniqueFindersCache(planType);
        cacheUniqueFindersCache(planType);

        return planType;
    }

    protected PlanType toUnwrappedModel(PlanType planType) {
        if (planType instanceof PlanTypeImpl) {
            return planType;
        }

        PlanTypeImpl planTypeImpl = new PlanTypeImpl();

        planTypeImpl.setNew(planType.isNew());
        planTypeImpl.setPrimaryKey(planType.getPrimaryKey());

        planTypeImpl.setPlanTypeId(planType.getPlanTypeId());
        planTypeImpl.setName(planType.getName());
        planTypeImpl.setDescription(planType.getDescription());
        planTypeImpl.setModelId(planType.getModelId());
        planTypeImpl.setModelTypeName(planType.getModelTypeName());
        planTypeImpl.setPublished(planType.isPublished());
        planTypeImpl.setPublishedCounterpartId(planType.getPublishedCounterpartId());
        planTypeImpl.setIsDefault(planType.isIsDefault());
        planTypeImpl.setDefaultModelId(planType.getDefaultModelId());
        planTypeImpl.setDefaultScenarioId(planType.getDefaultScenarioId());

        return planTypeImpl;
    }

    /**
     * Returns the plan type with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan type
     * @return the plan type
     * @throws com.ext.portlet.NoSuchPlanTypeException if a plan type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanType findByPrimaryKey(Serializable primaryKey)
        throws NoSuchPlanTypeException, SystemException {
        PlanType planType = fetchByPrimaryKey(primaryKey);

        if (planType == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchPlanTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return planType;
    }

    /**
     * Returns the plan type with the primary key or throws a {@link com.ext.portlet.NoSuchPlanTypeException} if it could not be found.
     *
     * @param planTypeId the primary key of the plan type
     * @return the plan type
     * @throws com.ext.portlet.NoSuchPlanTypeException if a plan type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanType findByPrimaryKey(long planTypeId)
        throws NoSuchPlanTypeException, SystemException {
        return findByPrimaryKey((Serializable) planTypeId);
    }

    /**
     * Returns the plan type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan type
     * @return the plan type, or <code>null</code> if a plan type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanType fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        PlanType planType = (PlanType) EntityCacheUtil.getResult(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
                PlanTypeImpl.class, primaryKey);

        if (planType == _nullPlanType) {
            return null;
        }

        if (planType == null) {
            Session session = null;

            try {
                session = openSession();

                planType = (PlanType) session.get(PlanTypeImpl.class, primaryKey);

                if (planType != null) {
                    cacheResult(planType);
                } else {
                    EntityCacheUtil.putResult(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
                        PlanTypeImpl.class, primaryKey, _nullPlanType);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
                    PlanTypeImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return planType;
    }

    /**
     * Returns the plan type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param planTypeId the primary key of the plan type
     * @return the plan type, or <code>null</code> if a plan type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanType fetchByPrimaryKey(long planTypeId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) planTypeId);
    }

    /**
     * Returns all the plan types.
     *
     * @return the plan types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanType> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan types
     * @param end the upper bound of the range of plan types (not inclusive)
     * @return the range of plan types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanType> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan types
     * @param end the upper bound of the range of plan types (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanType> findAll(int start, int end,
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

        List<PlanType> list = (List<PlanType>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANTYPE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANTYPE;

                if (pagination) {
                    sql = sql.concat(PlanTypeModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<PlanType>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PlanType>(list);
                } else {
                    list = (List<PlanType>) QueryUtil.list(q, getDialect(),
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
     * Removes all the plan types from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (PlanType planType : findAll()) {
            remove(planType);
        }
    }

    /**
     * Returns the number of plan types.
     *
     * @return the number of plan types
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

                Query q = session.createQuery(_SQL_COUNT_PLANTYPE);

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
     * Initializes the plan type persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.PlanType")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanType>> listenersList = new ArrayList<ModelListener<PlanType>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanType>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanTypeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
