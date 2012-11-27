package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanTypeColumnException;
import com.ext.portlet.plans.model.PlanTypeColumn;
import com.ext.portlet.plans.model.impl.PlanTypeColumnImpl;
import com.ext.portlet.plans.model.impl.PlanTypeColumnModelImpl;
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
 * The persistence implementation for the plan type column service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypeColumnPersistence
 * @see PlanTypeColumnUtil
 * @generated
 */
public class PlanTypeColumnPersistenceImpl extends BasePersistenceImpl<PlanTypeColumn>
    implements PlanTypeColumnPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanTypeColumnUtil} to access the plan type column persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanTypeColumnImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeColumnModelImpl.FINDER_CACHE_ENABLED,
            PlanTypeColumnImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeColumnModelImpl.FINDER_CACHE_ENABLED,
            PlanTypeColumnImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeColumnModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PLANTYPECOLUMN = "SELECT planTypeColumn FROM PlanTypeColumn planTypeColumn";
    private static final String _SQL_COUNT_PLANTYPECOLUMN = "SELECT COUNT(planTypeColumn) FROM PlanTypeColumn planTypeColumn";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planTypeColumn.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanTypeColumn exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanTypeColumnPersistenceImpl.class);
    private static PlanTypeColumn _nullPlanTypeColumn = new PlanTypeColumnImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanTypeColumn> toCacheModel() {
                return _nullPlanTypeColumnCacheModel;
            }
        };

    private static CacheModel<PlanTypeColumn> _nullPlanTypeColumnCacheModel = new CacheModel<PlanTypeColumn>() {
            public PlanTypeColumn toEntityModel() {
                return _nullPlanTypeColumn;
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
     * Caches the plan type column in the entity cache if it is enabled.
     *
     * @param planTypeColumn the plan type column
     */
    public void cacheResult(PlanTypeColumn planTypeColumn) {
        EntityCacheUtil.putResult(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeColumnImpl.class, planTypeColumn.getPrimaryKey(),
            planTypeColumn);

        planTypeColumn.resetOriginalValues();
    }

    /**
     * Caches the plan type columns in the entity cache if it is enabled.
     *
     * @param planTypeColumns the plan type columns
     */
    public void cacheResult(List<PlanTypeColumn> planTypeColumns) {
        for (PlanTypeColumn planTypeColumn : planTypeColumns) {
            if (EntityCacheUtil.getResult(
                        PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
                        PlanTypeColumnImpl.class, planTypeColumn.getPrimaryKey()) == null) {
                cacheResult(planTypeColumn);
            } else {
                planTypeColumn.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan type columns.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanTypeColumnImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanTypeColumnImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan type column.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanTypeColumn planTypeColumn) {
        EntityCacheUtil.removeResult(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeColumnImpl.class, planTypeColumn.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<PlanTypeColumn> planTypeColumns) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanTypeColumn planTypeColumn : planTypeColumns) {
            EntityCacheUtil.removeResult(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
                PlanTypeColumnImpl.class, planTypeColumn.getPrimaryKey());
        }
    }

    /**
     * Creates a new plan type column with the primary key. Does not add the plan type column to the database.
     *
     * @param planTypeColumnId the primary key for the new plan type column
     * @return the new plan type column
     */
    public PlanTypeColumn create(Long planTypeColumnId) {
        PlanTypeColumn planTypeColumn = new PlanTypeColumnImpl();

        planTypeColumn.setNew(true);
        planTypeColumn.setPrimaryKey(planTypeColumnId);

        return planTypeColumn;
    }

    /**
     * Removes the plan type column with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param planTypeColumnId the primary key of the plan type column
     * @return the plan type column that was removed
     * @throws com.ext.portlet.plans.NoSuchPlanTypeColumnException if a plan type column with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTypeColumn remove(Long planTypeColumnId)
        throws NoSuchPlanTypeColumnException, SystemException {
        return remove((Serializable) planTypeColumnId);
    }

    /**
     * Removes the plan type column with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan type column
     * @return the plan type column that was removed
     * @throws com.ext.portlet.plans.NoSuchPlanTypeColumnException if a plan type column with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTypeColumn remove(Serializable primaryKey)
        throws NoSuchPlanTypeColumnException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanTypeColumn planTypeColumn = (PlanTypeColumn) session.get(PlanTypeColumnImpl.class,
                    primaryKey);

            if (planTypeColumn == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanTypeColumnException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planTypeColumn);
        } catch (NoSuchPlanTypeColumnException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanTypeColumn removeImpl(PlanTypeColumn planTypeColumn)
        throws SystemException {
        planTypeColumn = toUnwrappedModel(planTypeColumn);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, planTypeColumn);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(planTypeColumn);

        return planTypeColumn;
    }

    @Override
    public PlanTypeColumn updateImpl(
        com.ext.portlet.plans.model.PlanTypeColumn planTypeColumn, boolean merge)
        throws SystemException {
        planTypeColumn = toUnwrappedModel(planTypeColumn);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planTypeColumn, merge);

            planTypeColumn.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        EntityCacheUtil.putResult(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeColumnImpl.class, planTypeColumn.getPrimaryKey(),
            planTypeColumn);

        return planTypeColumn;
    }

    protected PlanTypeColumn toUnwrappedModel(PlanTypeColumn planTypeColumn) {
        if (planTypeColumn instanceof PlanTypeColumnImpl) {
            return planTypeColumn;
        }

        PlanTypeColumnImpl planTypeColumnImpl = new PlanTypeColumnImpl();

        planTypeColumnImpl.setNew(planTypeColumn.isNew());
        planTypeColumnImpl.setPrimaryKey(planTypeColumn.getPrimaryKey());

        planTypeColumnImpl.setPlanTypeColumnId(planTypeColumn.getPlanTypeColumnId());
        planTypeColumnImpl.setPlanTypeId(planTypeColumn.getPlanTypeId());
        planTypeColumnImpl.setWeight(planTypeColumn.getWeight());
        planTypeColumnImpl.setColumnName(planTypeColumn.getColumnName());
        planTypeColumnImpl.setVisibleByDefault(planTypeColumn.getVisibleByDefault());

        return planTypeColumnImpl;
    }

    /**
     * Returns the plan type column with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan type column
     * @return the plan type column
     * @throws com.liferay.portal.NoSuchModelException if a plan type column with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTypeColumn findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey((Long) primaryKey);
    }

    /**
     * Returns the plan type column with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanTypeColumnException} if it could not be found.
     *
     * @param planTypeColumnId the primary key of the plan type column
     * @return the plan type column
     * @throws com.ext.portlet.plans.NoSuchPlanTypeColumnException if a plan type column with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTypeColumn findByPrimaryKey(Long planTypeColumnId)
        throws NoSuchPlanTypeColumnException, SystemException {
        PlanTypeColumn planTypeColumn = fetchByPrimaryKey(planTypeColumnId);

        if (planTypeColumn == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + planTypeColumnId);
            }

            throw new NoSuchPlanTypeColumnException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                planTypeColumnId);
        }

        return planTypeColumn;
    }

    /**
     * Returns the plan type column with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan type column
     * @return the plan type column, or <code>null</code> if a plan type column with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTypeColumn fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey((Long) primaryKey);
    }

    /**
     * Returns the plan type column with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param planTypeColumnId the primary key of the plan type column
     * @return the plan type column, or <code>null</code> if a plan type column with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTypeColumn fetchByPrimaryKey(Long planTypeColumnId)
        throws SystemException {
        PlanTypeColumn planTypeColumn = (PlanTypeColumn) EntityCacheUtil.getResult(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
                PlanTypeColumnImpl.class, planTypeColumnId);

        if (planTypeColumn == _nullPlanTypeColumn) {
            return null;
        }

        if (planTypeColumn == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                planTypeColumn = (PlanTypeColumn) session.get(PlanTypeColumnImpl.class,
                        Long.valueOf(planTypeColumnId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (planTypeColumn != null) {
                    cacheResult(planTypeColumn);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
                        PlanTypeColumnImpl.class, planTypeColumnId,
                        _nullPlanTypeColumn);
                }

                closeSession(session);
            }
        }

        return planTypeColumn;
    }

    /**
     * Returns all the plan type columns.
     *
     * @return the plan type columns
     * @throws SystemException if a system exception occurred
     */
    public List<PlanTypeColumn> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan type columns.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plan type columns
     * @param end the upper bound of the range of plan type columns (not inclusive)
     * @return the range of plan type columns
     * @throws SystemException if a system exception occurred
     */
    public List<PlanTypeColumn> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan type columns.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plan type columns
     * @param end the upper bound of the range of plan type columns (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan type columns
     * @throws SystemException if a system exception occurred
     */
    public List<PlanTypeColumn> findAll(int start, int end,
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

        List<PlanTypeColumn> list = (List<PlanTypeColumn>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANTYPECOLUMN);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANTYPECOLUMN.concat(PlanTypeColumnModelImpl.ORDER_BY_JPQL);
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<PlanTypeColumn>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanTypeColumn>) QueryUtil.list(q,
                            getDialect(), start, end);
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
     * Removes all the plan type columns from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (PlanTypeColumn planTypeColumn : findAll()) {
            remove(planTypeColumn);
        }
    }

    /**
     * Returns the number of plan type columns.
     *
     * @return the number of plan type columns
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_PLANTYPECOLUMN);

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
     * Initializes the plan type column persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.plans.model.PlanTypeColumn")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanTypeColumn>> listenersList = new ArrayList<ModelListener<PlanTypeColumn>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanTypeColumn>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanTypeColumnImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
