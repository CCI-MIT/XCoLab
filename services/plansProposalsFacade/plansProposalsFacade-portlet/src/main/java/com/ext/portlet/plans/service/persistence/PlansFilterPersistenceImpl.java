package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlansFilterException;
import com.ext.portlet.plans.model.PlansFilter;
import com.ext.portlet.plans.model.impl.PlansFilterImpl;
import com.ext.portlet.plans.model.impl.PlansFilterModelImpl;
import com.ext.portlet.plans.service.persistence.PlanAttributeFilterPersistence;
import com.ext.portlet.plans.service.persistence.PlanAttributePersistence;
import com.ext.portlet.plans.service.persistence.PlanColumnSettingsPersistence;
import com.ext.portlet.plans.service.persistence.PlanDescriptionPersistence;
import com.ext.portlet.plans.service.persistence.PlanFanPersistence;
import com.ext.portlet.plans.service.persistence.PlanItemPersistence;
import com.ext.portlet.plans.service.persistence.PlanMetaPersistence;
import com.ext.portlet.plans.service.persistence.PlanModelRunPersistence;
import com.ext.portlet.plans.service.persistence.PlanPositionItemPersistence;
import com.ext.portlet.plans.service.persistence.PlanPositionPersistence;
import com.ext.portlet.plans.service.persistence.PlanPositionsPersistence;
import com.ext.portlet.plans.service.persistence.PlanPropertyFilterPersistence;
import com.ext.portlet.plans.service.persistence.PlanRelatedPersistence;
import com.ext.portlet.plans.service.persistence.PlanSectionDefinitionPersistence;
import com.ext.portlet.plans.service.persistence.PlanSectionPersistence;
import com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPersistence;
import com.ext.portlet.plans.service.persistence.PlanTeamHistoryPersistence;
import com.ext.portlet.plans.service.persistence.PlanTemplatePersistence;
import com.ext.portlet.plans.service.persistence.PlanTemplateSectionPersistence;
import com.ext.portlet.plans.service.persistence.PlanTypeAttributePersistence;
import com.ext.portlet.plans.service.persistence.PlanTypeColumnPersistence;
import com.ext.portlet.plans.service.persistence.PlanTypePersistence;
import com.ext.portlet.plans.service.persistence.PlanVotePersistence;
import com.ext.portlet.plans.service.persistence.PlansFilterPersistence;
import com.ext.portlet.plans.service.persistence.PlansFilterPositionPersistence;
import com.ext.portlet.plans.service.persistence.PlansUserSettingsPersistence;

import com.liferay.portal.NoSuchModelException;
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
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the plans filter service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlansFilterPersistence
 * @see PlansFilterUtil
 * @generated
 */
public class PlansFilterPersistenceImpl extends BasePersistenceImpl<PlansFilter>
    implements PlansFilterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlansFilterUtil} to access the plans filter persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlansFilterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlansFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterModelImpl.FINDER_CACHE_ENABLED, PlansFilterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlansFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterModelImpl.FINDER_CACHE_ENABLED, PlansFilterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlansFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PLANSFILTER = "SELECT plansFilter FROM PlansFilter plansFilter";
    private static final String _SQL_COUNT_PLANSFILTER = "SELECT COUNT(plansFilter) FROM PlansFilter plansFilter";
    private static final String _ORDER_BY_ENTITY_ALIAS = "plansFilter.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlansFilter exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlansFilterPersistenceImpl.class);
    private static PlansFilter _nullPlansFilter = new PlansFilterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlansFilter> toCacheModel() {
                return _nullPlansFilterCacheModel;
            }
        };

    private static CacheModel<PlansFilter> _nullPlansFilterCacheModel = new CacheModel<PlansFilter>() {
            public PlansFilter toEntityModel() {
                return _nullPlansFilter;
            }
        };

    @BeanReference(type = PlanAttributePersistence.class)
    protected PlanAttributePersistence planAttributePersistence;
    @BeanReference(type = PlanAttributeFilterPersistence.class)
    protected PlanAttributeFilterPersistence planAttributeFilterPersistence;
    @BeanReference(type = PlanColumnSettingsPersistence.class)
    protected PlanColumnSettingsPersistence planColumnSettingsPersistence;
    @BeanReference(type = PlanDescriptionPersistence.class)
    protected PlanDescriptionPersistence planDescriptionPersistence;
    @BeanReference(type = PlanFanPersistence.class)
    protected PlanFanPersistence planFanPersistence;
    @BeanReference(type = PlanItemPersistence.class)
    protected PlanItemPersistence planItemPersistence;
    @BeanReference(type = PlanMetaPersistence.class)
    protected PlanMetaPersistence planMetaPersistence;
    @BeanReference(type = PlanModelRunPersistence.class)
    protected PlanModelRunPersistence planModelRunPersistence;
    @BeanReference(type = PlanPositionPersistence.class)
    protected PlanPositionPersistence planPositionPersistence;
    @BeanReference(type = PlanPositionItemPersistence.class)
    protected PlanPositionItemPersistence planPositionItemPersistence;
    @BeanReference(type = PlanPositionsPersistence.class)
    protected PlanPositionsPersistence planPositionsPersistence;
    @BeanReference(type = PlanPropertyFilterPersistence.class)
    protected PlanPropertyFilterPersistence planPropertyFilterPersistence;
    @BeanReference(type = PlanRelatedPersistence.class)
    protected PlanRelatedPersistence planRelatedPersistence;
    @BeanReference(type = PlanSectionPersistence.class)
    protected PlanSectionPersistence planSectionPersistence;
    @BeanReference(type = PlanSectionDefinitionPersistence.class)
    protected PlanSectionDefinitionPersistence planSectionDefinitionPersistence;
    @BeanReference(type = PlanSectionPlanMapPersistence.class)
    protected PlanSectionPlanMapPersistence planSectionPlanMapPersistence;
    @BeanReference(type = PlansFilterPersistence.class)
    protected PlansFilterPersistence plansFilterPersistence;
    @BeanReference(type = PlansFilterPositionPersistence.class)
    protected PlansFilterPositionPersistence plansFilterPositionPersistence;
    @BeanReference(type = PlansUserSettingsPersistence.class)
    protected PlansUserSettingsPersistence plansUserSettingsPersistence;
    @BeanReference(type = PlanTeamHistoryPersistence.class)
    protected PlanTeamHistoryPersistence planTeamHistoryPersistence;
    @BeanReference(type = PlanTemplatePersistence.class)
    protected PlanTemplatePersistence planTemplatePersistence;
    @BeanReference(type = PlanTemplateSectionPersistence.class)
    protected PlanTemplateSectionPersistence planTemplateSectionPersistence;
    @BeanReference(type = PlanTypePersistence.class)
    protected PlanTypePersistence planTypePersistence;
    @BeanReference(type = PlanTypeAttributePersistence.class)
    protected PlanTypeAttributePersistence planTypeAttributePersistence;
    @BeanReference(type = PlanTypeColumnPersistence.class)
    protected PlanTypeColumnPersistence planTypeColumnPersistence;
    @BeanReference(type = PlanVotePersistence.class)
    protected PlanVotePersistence planVotePersistence;
    @BeanReference(type = ResourcePersistence.class)
    protected ResourcePersistence resourcePersistence;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;

    /**
     * Caches the plans filter in the entity cache if it is enabled.
     *
     * @param plansFilter the plans filter
     */
    public void cacheResult(PlansFilter plansFilter) {
        EntityCacheUtil.putResult(PlansFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterImpl.class, plansFilter.getPrimaryKey(), plansFilter);

        plansFilter.resetOriginalValues();
    }

    /**
     * Caches the plans filters in the entity cache if it is enabled.
     *
     * @param plansFilters the plans filters
     */
    public void cacheResult(List<PlansFilter> plansFilters) {
        for (PlansFilter plansFilter : plansFilters) {
            if (EntityCacheUtil.getResult(
                        PlansFilterModelImpl.ENTITY_CACHE_ENABLED,
                        PlansFilterImpl.class, plansFilter.getPrimaryKey()) == null) {
                cacheResult(plansFilter);
            } else {
                plansFilter.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plans filters.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlansFilterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlansFilterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plans filter.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlansFilter plansFilter) {
        EntityCacheUtil.removeResult(PlansFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterImpl.class, plansFilter.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<PlansFilter> plansFilters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlansFilter plansFilter : plansFilters) {
            EntityCacheUtil.removeResult(PlansFilterModelImpl.ENTITY_CACHE_ENABLED,
                PlansFilterImpl.class, plansFilter.getPrimaryKey());
        }
    }

    /**
     * Creates a new plans filter with the primary key. Does not add the plans filter to the database.
     *
     * @param plansFilterPK the primary key for the new plans filter
     * @return the new plans filter
     */
    public PlansFilter create(PlansFilterPK plansFilterPK) {
        PlansFilter plansFilter = new PlansFilterImpl();

        plansFilter.setNew(true);
        plansFilter.setPrimaryKey(plansFilterPK);

        return plansFilter;
    }

    /**
     * Removes the plans filter with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param plansFilterPK the primary key of the plans filter
     * @return the plans filter that was removed
     * @throws com.ext.portlet.plans.NoSuchPlansFilterException if a plans filter with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlansFilter remove(PlansFilterPK plansFilterPK)
        throws NoSuchPlansFilterException, SystemException {
        return remove((Serializable) plansFilterPK);
    }

    /**
     * Removes the plans filter with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plans filter
     * @return the plans filter that was removed
     * @throws com.ext.portlet.plans.NoSuchPlansFilterException if a plans filter with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlansFilter remove(Serializable primaryKey)
        throws NoSuchPlansFilterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlansFilter plansFilter = (PlansFilter) session.get(PlansFilterImpl.class,
                    primaryKey);

            if (plansFilter == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlansFilterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(plansFilter);
        } catch (NoSuchPlansFilterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlansFilter removeImpl(PlansFilter plansFilter)
        throws SystemException {
        plansFilter = toUnwrappedModel(plansFilter);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, plansFilter);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(plansFilter);

        return plansFilter;
    }

    @Override
    public PlansFilter updateImpl(
        com.ext.portlet.plans.model.PlansFilter plansFilter, boolean merge)
        throws SystemException {
        plansFilter = toUnwrappedModel(plansFilter);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, plansFilter, merge);

            plansFilter.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        EntityCacheUtil.putResult(PlansFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlansFilterImpl.class, plansFilter.getPrimaryKey(), plansFilter);

        return plansFilter;
    }

    protected PlansFilter toUnwrappedModel(PlansFilter plansFilter) {
        if (plansFilter instanceof PlansFilterImpl) {
            return plansFilter;
        }

        PlansFilterImpl plansFilterImpl = new PlansFilterImpl();

        plansFilterImpl.setNew(plansFilter.isNew());
        plansFilterImpl.setPrimaryKey(plansFilter.getPrimaryKey());

        plansFilterImpl.setUserId(plansFilter.getUserId());
        plansFilterImpl.setPlanTypeId(plansFilter.getPlanTypeId());
        plansFilterImpl.setName(plansFilter.getName());
        plansFilterImpl.setCreator(plansFilter.getCreator());
        plansFilterImpl.setDescription(plansFilter.getDescription());
        plansFilterImpl.setCO2From(plansFilter.getCO2From());
        plansFilterImpl.setCO2To(plansFilter.getCO2To());
        plansFilterImpl.setVotesFrom(plansFilter.getVotesFrom());
        plansFilterImpl.setVotesTo(plansFilter.getVotesTo());
        plansFilterImpl.setDamageFrom(plansFilter.getDamageFrom());
        plansFilterImpl.setDamageTo(plansFilter.getDamageTo());
        plansFilterImpl.setMitigationFrom(plansFilter.getMitigationFrom());
        plansFilterImpl.setMitigationTo(plansFilter.getMitigationTo());
        plansFilterImpl.setDateFrom(plansFilter.getDateFrom());
        plansFilterImpl.setDateTo(plansFilter.getDateTo());
        plansFilterImpl.setFilterPositionsAll(plansFilter.isFilterPositionsAll());
        plansFilterImpl.setEnabled(plansFilter.isEnabled());

        return plansFilterImpl;
    }

    /**
     * Returns the plans filter with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plans filter
     * @return the plans filter
     * @throws com.liferay.portal.NoSuchModelException if a plans filter with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlansFilter findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey((PlansFilterPK) primaryKey);
    }

    /**
     * Returns the plans filter with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlansFilterException} if it could not be found.
     *
     * @param plansFilterPK the primary key of the plans filter
     * @return the plans filter
     * @throws com.ext.portlet.plans.NoSuchPlansFilterException if a plans filter with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlansFilter findByPrimaryKey(PlansFilterPK plansFilterPK)
        throws NoSuchPlansFilterException, SystemException {
        PlansFilter plansFilter = fetchByPrimaryKey(plansFilterPK);

        if (plansFilter == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + plansFilterPK);
            }

            throw new NoSuchPlansFilterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                plansFilterPK);
        }

        return plansFilter;
    }

    /**
     * Returns the plans filter with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plans filter
     * @return the plans filter, or <code>null</code> if a plans filter with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlansFilter fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey((PlansFilterPK) primaryKey);
    }

    /**
     * Returns the plans filter with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param plansFilterPK the primary key of the plans filter
     * @return the plans filter, or <code>null</code> if a plans filter with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlansFilter fetchByPrimaryKey(PlansFilterPK plansFilterPK)
        throws SystemException {
        PlansFilter plansFilter = (PlansFilter) EntityCacheUtil.getResult(PlansFilterModelImpl.ENTITY_CACHE_ENABLED,
                PlansFilterImpl.class, plansFilterPK);

        if (plansFilter == _nullPlansFilter) {
            return null;
        }

        if (plansFilter == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                plansFilter = (PlansFilter) session.get(PlansFilterImpl.class,
                        plansFilterPK);
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (plansFilter != null) {
                    cacheResult(plansFilter);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(PlansFilterModelImpl.ENTITY_CACHE_ENABLED,
                        PlansFilterImpl.class, plansFilterPK, _nullPlansFilter);
                }

                closeSession(session);
            }
        }

        return plansFilter;
    }

    /**
     * Returns all the plans filters.
     *
     * @return the plans filters
     * @throws SystemException if a system exception occurred
     */
    public List<PlansFilter> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plans filters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plans filters
     * @param end the upper bound of the range of plans filters (not inclusive)
     * @return the range of plans filters
     * @throws SystemException if a system exception occurred
     */
    public List<PlansFilter> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plans filters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plans filters
     * @param end the upper bound of the range of plans filters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plans filters
     * @throws SystemException if a system exception occurred
     */
    public List<PlansFilter> findAll(int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = new Object[] { start, end, orderByComparator };

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
            finderArgs = FINDER_ARGS_EMPTY;
        } else {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
            finderArgs = new Object[] { start, end, orderByComparator };
        }

        List<PlansFilter> list = (List<PlansFilter>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANSFILTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANSFILTER;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<PlansFilter>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlansFilter>) QueryUtil.list(q, getDialect(),
                            start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(finderPath, finderArgs);
                } else {
                    cacheResult(list);

                    FinderCacheUtil.putResult(finderPath, finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes all the plans filters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (PlansFilter plansFilter : findAll()) {
            remove(plansFilter);
        }
    }

    /**
     * Returns the number of plans filters.
     *
     * @return the number of plans filters
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_PLANSFILTER);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Initializes the plans filter persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.plans.model.PlansFilter")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlansFilter>> listenersList = new ArrayList<ModelListener<PlansFilter>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlansFilter>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlansFilterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
