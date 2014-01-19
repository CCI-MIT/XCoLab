package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPlanPropertyFilterException;
import com.ext.portlet.model.PlanPropertyFilter;
import com.ext.portlet.model.impl.PlanPropertyFilterImpl;
import com.ext.portlet.model.impl.PlanPropertyFilterModelImpl;
import com.ext.portlet.service.persistence.PlanPropertyFilterPersistence;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the plan property filter service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanPropertyFilterPersistence
 * @see PlanPropertyFilterUtil
 * @generated
 */
public class PlanPropertyFilterPersistenceImpl extends BasePersistenceImpl<PlanPropertyFilter>
    implements PlanPropertyFilterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanPropertyFilterUtil} to access the plan property filter persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanPropertyFilterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanPropertyFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanPropertyFilterModelImpl.FINDER_CACHE_ENABLED,
            PlanPropertyFilterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanPropertyFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanPropertyFilterModelImpl.FINDER_CACHE_ENABLED,
            PlanPropertyFilterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanPropertyFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanPropertyFilterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDPROPERTYNAME =
        new FinderPath(PlanPropertyFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanPropertyFilterModelImpl.FINDER_CACHE_ENABLED,
            PlanPropertyFilterImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByPlanUserSettingsIdPropertyName",
            new String[] { Long.class.getName(), String.class.getName() },
            PlanPropertyFilterModelImpl.PLANUSERSETTINGSID_COLUMN_BITMASK |
            PlanPropertyFilterModelImpl.PROPERTYNAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDPROPERTYNAME =
        new FinderPath(PlanPropertyFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanPropertyFilterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByPlanUserSettingsIdPropertyName",
            new String[] { Long.class.getName(), String.class.getName() });
    private static final String _FINDER_COLUMN_PLANUSERSETTINGSIDPROPERTYNAME_PLANUSERSETTINGSID_2 =
        "planPropertyFilter.planUserSettingsId = ? AND ";
    private static final String _FINDER_COLUMN_PLANUSERSETTINGSIDPROPERTYNAME_PROPERTYNAME_1 =
        "planPropertyFilter.propertyName IS NULL";
    private static final String _FINDER_COLUMN_PLANUSERSETTINGSIDPROPERTYNAME_PROPERTYNAME_2 =
        "planPropertyFilter.propertyName = ?";
    private static final String _FINDER_COLUMN_PLANUSERSETTINGSIDPROPERTYNAME_PROPERTYNAME_3 =
        "(planPropertyFilter.propertyName IS NULL OR planPropertyFilter.propertyName = '')";
    private static final String _SQL_SELECT_PLANPROPERTYFILTER = "SELECT planPropertyFilter FROM PlanPropertyFilter planPropertyFilter";
    private static final String _SQL_SELECT_PLANPROPERTYFILTER_WHERE = "SELECT planPropertyFilter FROM PlanPropertyFilter planPropertyFilter WHERE ";
    private static final String _SQL_COUNT_PLANPROPERTYFILTER = "SELECT COUNT(planPropertyFilter) FROM PlanPropertyFilter planPropertyFilter";
    private static final String _SQL_COUNT_PLANPROPERTYFILTER_WHERE = "SELECT COUNT(planPropertyFilter) FROM PlanPropertyFilter planPropertyFilter WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planPropertyFilter.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanPropertyFilter exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PlanPropertyFilter exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanPropertyFilterPersistenceImpl.class);
    private static PlanPropertyFilter _nullPlanPropertyFilter = new PlanPropertyFilterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanPropertyFilter> toCacheModel() {
                return _nullPlanPropertyFilterCacheModel;
            }
        };

    private static CacheModel<PlanPropertyFilter> _nullPlanPropertyFilterCacheModel =
        new CacheModel<PlanPropertyFilter>() {
            @Override
            public PlanPropertyFilter toEntityModel() {
                return _nullPlanPropertyFilter;
            }
        };

    public PlanPropertyFilterPersistenceImpl() {
        setModelClass(PlanPropertyFilter.class);
    }

    /**
     * Returns the plan property filter where planUserSettingsId = &#63; and propertyName = &#63; or throws a {@link com.ext.portlet.NoSuchPlanPropertyFilterException} if it could not be found.
     *
     * @param planUserSettingsId the plan user settings ID
     * @param propertyName the property name
     * @return the matching plan property filter
     * @throws com.ext.portlet.NoSuchPlanPropertyFilterException if a matching plan property filter could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPropertyFilter findByPlanUserSettingsIdPropertyName(
        long planUserSettingsId, String propertyName)
        throws NoSuchPlanPropertyFilterException, SystemException {
        PlanPropertyFilter planPropertyFilter = fetchByPlanUserSettingsIdPropertyName(planUserSettingsId,
                propertyName);

        if (planPropertyFilter == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("planUserSettingsId=");
            msg.append(planUserSettingsId);

            msg.append(", propertyName=");
            msg.append(propertyName);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanPropertyFilterException(msg.toString());
        }

        return planPropertyFilter;
    }

    /**
     * Returns the plan property filter where planUserSettingsId = &#63; and propertyName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param planUserSettingsId the plan user settings ID
     * @param propertyName the property name
     * @return the matching plan property filter, or <code>null</code> if a matching plan property filter could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPropertyFilter fetchByPlanUserSettingsIdPropertyName(
        long planUserSettingsId, String propertyName) throws SystemException {
        return fetchByPlanUserSettingsIdPropertyName(planUserSettingsId,
            propertyName, true);
    }

    /**
     * Returns the plan property filter where planUserSettingsId = &#63; and propertyName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param planUserSettingsId the plan user settings ID
     * @param propertyName the property name
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching plan property filter, or <code>null</code> if a matching plan property filter could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPropertyFilter fetchByPlanUserSettingsIdPropertyName(
        long planUserSettingsId, String propertyName, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { planUserSettingsId, propertyName };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDPROPERTYNAME,
                    finderArgs, this);
        }

        if (result instanceof PlanPropertyFilter) {
            PlanPropertyFilter planPropertyFilter = (PlanPropertyFilter) result;

            if ((planUserSettingsId != planPropertyFilter.getPlanUserSettingsId()) ||
                    !Validator.equals(propertyName,
                        planPropertyFilter.getPropertyName())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_SELECT_PLANPROPERTYFILTER_WHERE);

            query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDPROPERTYNAME_PLANUSERSETTINGSID_2);

            boolean bindPropertyName = false;

            if (propertyName == null) {
                query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDPROPERTYNAME_PROPERTYNAME_1);
            } else if (propertyName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDPROPERTYNAME_PROPERTYNAME_3);
            } else {
                bindPropertyName = true;

                query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDPROPERTYNAME_PROPERTYNAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planUserSettingsId);

                if (bindPropertyName) {
                    qPos.add(propertyName);
                }

                List<PlanPropertyFilter> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDPROPERTYNAME,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "PlanPropertyFilterPersistenceImpl.fetchByPlanUserSettingsIdPropertyName(long, String, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    PlanPropertyFilter planPropertyFilter = list.get(0);

                    result = planPropertyFilter;

                    cacheResult(planPropertyFilter);

                    if ((planPropertyFilter.getPlanUserSettingsId() != planUserSettingsId) ||
                            (planPropertyFilter.getPropertyName() == null) ||
                            !planPropertyFilter.getPropertyName()
                                                   .equals(propertyName)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDPROPERTYNAME,
                            finderArgs, planPropertyFilter);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDPROPERTYNAME,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (PlanPropertyFilter) result;
        }
    }

    /**
     * Removes the plan property filter where planUserSettingsId = &#63; and propertyName = &#63; from the database.
     *
     * @param planUserSettingsId the plan user settings ID
     * @param propertyName the property name
     * @return the plan property filter that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPropertyFilter removeByPlanUserSettingsIdPropertyName(
        long planUserSettingsId, String propertyName)
        throws NoSuchPlanPropertyFilterException, SystemException {
        PlanPropertyFilter planPropertyFilter = findByPlanUserSettingsIdPropertyName(planUserSettingsId,
                propertyName);

        return remove(planPropertyFilter);
    }

    /**
     * Returns the number of plan property filters where planUserSettingsId = &#63; and propertyName = &#63;.
     *
     * @param planUserSettingsId the plan user settings ID
     * @param propertyName the property name
     * @return the number of matching plan property filters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByPlanUserSettingsIdPropertyName(long planUserSettingsId,
        String propertyName) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDPROPERTYNAME;

        Object[] finderArgs = new Object[] { planUserSettingsId, propertyName };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PLANPROPERTYFILTER_WHERE);

            query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDPROPERTYNAME_PLANUSERSETTINGSID_2);

            boolean bindPropertyName = false;

            if (propertyName == null) {
                query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDPROPERTYNAME_PROPERTYNAME_1);
            } else if (propertyName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDPROPERTYNAME_PROPERTYNAME_3);
            } else {
                bindPropertyName = true;

                query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDPROPERTYNAME_PROPERTYNAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planUserSettingsId);

                if (bindPropertyName) {
                    qPos.add(propertyName);
                }

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
     * Caches the plan property filter in the entity cache if it is enabled.
     *
     * @param planPropertyFilter the plan property filter
     */
    @Override
    public void cacheResult(PlanPropertyFilter planPropertyFilter) {
        EntityCacheUtil.putResult(PlanPropertyFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanPropertyFilterImpl.class, planPropertyFilter.getPrimaryKey(),
            planPropertyFilter);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDPROPERTYNAME,
            new Object[] {
                planPropertyFilter.getPlanUserSettingsId(),
                planPropertyFilter.getPropertyName()
            }, planPropertyFilter);

        planPropertyFilter.resetOriginalValues();
    }

    /**
     * Caches the plan property filters in the entity cache if it is enabled.
     *
     * @param planPropertyFilters the plan property filters
     */
    @Override
    public void cacheResult(List<PlanPropertyFilter> planPropertyFilters) {
        for (PlanPropertyFilter planPropertyFilter : planPropertyFilters) {
            if (EntityCacheUtil.getResult(
                        PlanPropertyFilterModelImpl.ENTITY_CACHE_ENABLED,
                        PlanPropertyFilterImpl.class,
                        planPropertyFilter.getPrimaryKey()) == null) {
                cacheResult(planPropertyFilter);
            } else {
                planPropertyFilter.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan property filters.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanPropertyFilterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanPropertyFilterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan property filter.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanPropertyFilter planPropertyFilter) {
        EntityCacheUtil.removeResult(PlanPropertyFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanPropertyFilterImpl.class, planPropertyFilter.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(planPropertyFilter);
    }

    @Override
    public void clearCache(List<PlanPropertyFilter> planPropertyFilters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanPropertyFilter planPropertyFilter : planPropertyFilters) {
            EntityCacheUtil.removeResult(PlanPropertyFilterModelImpl.ENTITY_CACHE_ENABLED,
                PlanPropertyFilterImpl.class, planPropertyFilter.getPrimaryKey());

            clearUniqueFindersCache(planPropertyFilter);
        }
    }

    protected void cacheUniqueFindersCache(
        PlanPropertyFilter planPropertyFilter) {
        if (planPropertyFilter.isNew()) {
            Object[] args = new Object[] {
                    planPropertyFilter.getPlanUserSettingsId(),
                    planPropertyFilter.getPropertyName()
                };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDPROPERTYNAME,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDPROPERTYNAME,
                args, planPropertyFilter);
        } else {
            PlanPropertyFilterModelImpl planPropertyFilterModelImpl = (PlanPropertyFilterModelImpl) planPropertyFilter;

            if ((planPropertyFilterModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDPROPERTYNAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        planPropertyFilter.getPlanUserSettingsId(),
                        planPropertyFilter.getPropertyName()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDPROPERTYNAME,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDPROPERTYNAME,
                    args, planPropertyFilter);
            }
        }
    }

    protected void clearUniqueFindersCache(
        PlanPropertyFilter planPropertyFilter) {
        PlanPropertyFilterModelImpl planPropertyFilterModelImpl = (PlanPropertyFilterModelImpl) planPropertyFilter;

        Object[] args = new Object[] {
                planPropertyFilter.getPlanUserSettingsId(),
                planPropertyFilter.getPropertyName()
            };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDPROPERTYNAME,
            args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDPROPERTYNAME,
            args);

        if ((planPropertyFilterModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDPROPERTYNAME.getColumnBitmask()) != 0) {
            args = new Object[] {
                    planPropertyFilterModelImpl.getOriginalPlanUserSettingsId(),
                    planPropertyFilterModelImpl.getOriginalPropertyName()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDPROPERTYNAME,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDPROPERTYNAME,
                args);
        }
    }

    /**
     * Creates a new plan property filter with the primary key. Does not add the plan property filter to the database.
     *
     * @param planPropertyFilterId the primary key for the new plan property filter
     * @return the new plan property filter
     */
    @Override
    public PlanPropertyFilter create(long planPropertyFilterId) {
        PlanPropertyFilter planPropertyFilter = new PlanPropertyFilterImpl();

        planPropertyFilter.setNew(true);
        planPropertyFilter.setPrimaryKey(planPropertyFilterId);

        return planPropertyFilter;
    }

    /**
     * Removes the plan property filter with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param planPropertyFilterId the primary key of the plan property filter
     * @return the plan property filter that was removed
     * @throws com.ext.portlet.NoSuchPlanPropertyFilterException if a plan property filter with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPropertyFilter remove(long planPropertyFilterId)
        throws NoSuchPlanPropertyFilterException, SystemException {
        return remove((Serializable) planPropertyFilterId);
    }

    /**
     * Removes the plan property filter with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan property filter
     * @return the plan property filter that was removed
     * @throws com.ext.portlet.NoSuchPlanPropertyFilterException if a plan property filter with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPropertyFilter remove(Serializable primaryKey)
        throws NoSuchPlanPropertyFilterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanPropertyFilter planPropertyFilter = (PlanPropertyFilter) session.get(PlanPropertyFilterImpl.class,
                    primaryKey);

            if (planPropertyFilter == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanPropertyFilterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planPropertyFilter);
        } catch (NoSuchPlanPropertyFilterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanPropertyFilter removeImpl(
        PlanPropertyFilter planPropertyFilter) throws SystemException {
        planPropertyFilter = toUnwrappedModel(planPropertyFilter);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(planPropertyFilter)) {
                planPropertyFilter = (PlanPropertyFilter) session.get(PlanPropertyFilterImpl.class,
                        planPropertyFilter.getPrimaryKeyObj());
            }

            if (planPropertyFilter != null) {
                session.delete(planPropertyFilter);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (planPropertyFilter != null) {
            clearCache(planPropertyFilter);
        }

        return planPropertyFilter;
    }

    @Override
    public PlanPropertyFilter updateImpl(
        com.ext.portlet.model.PlanPropertyFilter planPropertyFilter)
        throws SystemException {
        planPropertyFilter = toUnwrappedModel(planPropertyFilter);

        boolean isNew = planPropertyFilter.isNew();

        Session session = null;

        try {
            session = openSession();

            if (planPropertyFilter.isNew()) {
                session.save(planPropertyFilter);

                planPropertyFilter.setNew(false);
            } else {
                session.merge(planPropertyFilter);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PlanPropertyFilterModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(PlanPropertyFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanPropertyFilterImpl.class, planPropertyFilter.getPrimaryKey(),
            planPropertyFilter);

        clearUniqueFindersCache(planPropertyFilter);
        cacheUniqueFindersCache(planPropertyFilter);

        return planPropertyFilter;
    }

    protected PlanPropertyFilter toUnwrappedModel(
        PlanPropertyFilter planPropertyFilter) {
        if (planPropertyFilter instanceof PlanPropertyFilterImpl) {
            return planPropertyFilter;
        }

        PlanPropertyFilterImpl planPropertyFilterImpl = new PlanPropertyFilterImpl();

        planPropertyFilterImpl.setNew(planPropertyFilter.isNew());
        planPropertyFilterImpl.setPrimaryKey(planPropertyFilter.getPrimaryKey());

        planPropertyFilterImpl.setPlanPropertyFilterId(planPropertyFilter.getPlanPropertyFilterId());
        planPropertyFilterImpl.setPropertyName(planPropertyFilter.getPropertyName());
        planPropertyFilterImpl.setPlanUserSettingsId(planPropertyFilter.getPlanUserSettingsId());
        planPropertyFilterImpl.setValue(planPropertyFilter.getValue());

        return planPropertyFilterImpl;
    }

    /**
     * Returns the plan property filter with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan property filter
     * @return the plan property filter
     * @throws com.ext.portlet.NoSuchPlanPropertyFilterException if a plan property filter with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPropertyFilter findByPrimaryKey(Serializable primaryKey)
        throws NoSuchPlanPropertyFilterException, SystemException {
        PlanPropertyFilter planPropertyFilter = fetchByPrimaryKey(primaryKey);

        if (planPropertyFilter == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchPlanPropertyFilterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return planPropertyFilter;
    }

    /**
     * Returns the plan property filter with the primary key or throws a {@link com.ext.portlet.NoSuchPlanPropertyFilterException} if it could not be found.
     *
     * @param planPropertyFilterId the primary key of the plan property filter
     * @return the plan property filter
     * @throws com.ext.portlet.NoSuchPlanPropertyFilterException if a plan property filter with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPropertyFilter findByPrimaryKey(long planPropertyFilterId)
        throws NoSuchPlanPropertyFilterException, SystemException {
        return findByPrimaryKey((Serializable) planPropertyFilterId);
    }

    /**
     * Returns the plan property filter with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan property filter
     * @return the plan property filter, or <code>null</code> if a plan property filter with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPropertyFilter fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        PlanPropertyFilter planPropertyFilter = (PlanPropertyFilter) EntityCacheUtil.getResult(PlanPropertyFilterModelImpl.ENTITY_CACHE_ENABLED,
                PlanPropertyFilterImpl.class, primaryKey);

        if (planPropertyFilter == _nullPlanPropertyFilter) {
            return null;
        }

        if (planPropertyFilter == null) {
            Session session = null;

            try {
                session = openSession();

                planPropertyFilter = (PlanPropertyFilter) session.get(PlanPropertyFilterImpl.class,
                        primaryKey);

                if (planPropertyFilter != null) {
                    cacheResult(planPropertyFilter);
                } else {
                    EntityCacheUtil.putResult(PlanPropertyFilterModelImpl.ENTITY_CACHE_ENABLED,
                        PlanPropertyFilterImpl.class, primaryKey,
                        _nullPlanPropertyFilter);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(PlanPropertyFilterModelImpl.ENTITY_CACHE_ENABLED,
                    PlanPropertyFilterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return planPropertyFilter;
    }

    /**
     * Returns the plan property filter with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param planPropertyFilterId the primary key of the plan property filter
     * @return the plan property filter, or <code>null</code> if a plan property filter with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPropertyFilter fetchByPrimaryKey(long planPropertyFilterId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) planPropertyFilterId);
    }

    /**
     * Returns all the plan property filters.
     *
     * @return the plan property filters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanPropertyFilter> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan property filters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPropertyFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan property filters
     * @param end the upper bound of the range of plan property filters (not inclusive)
     * @return the range of plan property filters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanPropertyFilter> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan property filters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPropertyFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan property filters
     * @param end the upper bound of the range of plan property filters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan property filters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanPropertyFilter> findAll(int start, int end,
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

        List<PlanPropertyFilter> list = (List<PlanPropertyFilter>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANPROPERTYFILTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANPROPERTYFILTER;

                if (pagination) {
                    sql = sql.concat(PlanPropertyFilterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<PlanPropertyFilter>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PlanPropertyFilter>(list);
                } else {
                    list = (List<PlanPropertyFilter>) QueryUtil.list(q,
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
     * Removes all the plan property filters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (PlanPropertyFilter planPropertyFilter : findAll()) {
            remove(planPropertyFilter);
        }
    }

    /**
     * Returns the number of plan property filters.
     *
     * @return the number of plan property filters
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

                Query q = session.createQuery(_SQL_COUNT_PLANPROPERTYFILTER);

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
     * Initializes the plan property filter persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.PlanPropertyFilter")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanPropertyFilter>> listenersList = new ArrayList<ModelListener<PlanPropertyFilter>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanPropertyFilter>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanPropertyFilterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
