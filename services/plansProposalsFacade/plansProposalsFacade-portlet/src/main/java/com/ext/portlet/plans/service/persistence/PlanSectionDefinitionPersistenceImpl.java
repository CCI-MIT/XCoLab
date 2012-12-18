package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanSectionDefinitionException;
import com.ext.portlet.plans.model.PlanSectionDefinition;
import com.ext.portlet.plans.model.impl.PlanSectionDefinitionImpl;
import com.ext.portlet.plans.model.impl.PlanSectionDefinitionModelImpl;
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
 * The persistence implementation for the plan section definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionDefinitionPersistence
 * @see PlanSectionDefinitionUtil
 * @generated
 */
public class PlanSectionDefinitionPersistenceImpl extends BasePersistenceImpl<PlanSectionDefinition>
    implements PlanSectionDefinitionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanSectionDefinitionUtil} to access the plan section definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanSectionDefinitionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionDefinitionModelImpl.FINDER_CACHE_ENABLED,
            PlanSectionDefinitionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionDefinitionModelImpl.FINDER_CACHE_ENABLED,
            PlanSectionDefinitionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionDefinitionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PLANSECTIONDEFINITION = "SELECT planSectionDefinition FROM PlanSectionDefinition planSectionDefinition";
    private static final String _SQL_COUNT_PLANSECTIONDEFINITION = "SELECT COUNT(planSectionDefinition) FROM PlanSectionDefinition planSectionDefinition";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planSectionDefinition.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanSectionDefinition exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanSectionDefinitionPersistenceImpl.class);
    private static PlanSectionDefinition _nullPlanSectionDefinition = new PlanSectionDefinitionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanSectionDefinition> toCacheModel() {
                return _nullPlanSectionDefinitionCacheModel;
            }
        };

    private static CacheModel<PlanSectionDefinition> _nullPlanSectionDefinitionCacheModel =
        new CacheModel<PlanSectionDefinition>() {
            public PlanSectionDefinition toEntityModel() {
                return _nullPlanSectionDefinition;
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
     * Caches the plan section definition in the entity cache if it is enabled.
     *
     * @param planSectionDefinition the plan section definition
     */
    public void cacheResult(PlanSectionDefinition planSectionDefinition) {
        EntityCacheUtil.putResult(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionDefinitionImpl.class,
            planSectionDefinition.getPrimaryKey(), planSectionDefinition);

        planSectionDefinition.resetOriginalValues();
    }

    /**
     * Caches the plan section definitions in the entity cache if it is enabled.
     *
     * @param planSectionDefinitions the plan section definitions
     */
    public void cacheResult(List<PlanSectionDefinition> planSectionDefinitions) {
        for (PlanSectionDefinition planSectionDefinition : planSectionDefinitions) {
            if (EntityCacheUtil.getResult(
                        PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                        PlanSectionDefinitionImpl.class,
                        planSectionDefinition.getPrimaryKey()) == null) {
                cacheResult(planSectionDefinition);
            } else {
                planSectionDefinition.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan section definitions.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanSectionDefinitionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanSectionDefinitionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan section definition.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanSectionDefinition planSectionDefinition) {
        EntityCacheUtil.removeResult(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionDefinitionImpl.class,
            planSectionDefinition.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<PlanSectionDefinition> planSectionDefinitions) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanSectionDefinition planSectionDefinition : planSectionDefinitions) {
            EntityCacheUtil.removeResult(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                PlanSectionDefinitionImpl.class,
                planSectionDefinition.getPrimaryKey());
        }
    }

    /**
     * Creates a new plan section definition with the primary key. Does not add the plan section definition to the database.
     *
     * @param id the primary key for the new plan section definition
     * @return the new plan section definition
     */
    public PlanSectionDefinition create(long id) {
        PlanSectionDefinition planSectionDefinition = new PlanSectionDefinitionImpl();

        planSectionDefinition.setNew(true);
        planSectionDefinition.setPrimaryKey(id);

        return planSectionDefinition;
    }

    /**
     * Removes the plan section definition with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the plan section definition
     * @return the plan section definition that was removed
     * @throws com.ext.portlet.plans.NoSuchPlanSectionDefinitionException if a plan section definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanSectionDefinition remove(long id)
        throws NoSuchPlanSectionDefinitionException, SystemException {
        return remove(Long.valueOf(id));
    }

    /**
     * Removes the plan section definition with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan section definition
     * @return the plan section definition that was removed
     * @throws com.ext.portlet.plans.NoSuchPlanSectionDefinitionException if a plan section definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanSectionDefinition remove(Serializable primaryKey)
        throws NoSuchPlanSectionDefinitionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanSectionDefinition planSectionDefinition = (PlanSectionDefinition) session.get(PlanSectionDefinitionImpl.class,
                    primaryKey);

            if (planSectionDefinition == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanSectionDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planSectionDefinition);
        } catch (NoSuchPlanSectionDefinitionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanSectionDefinition removeImpl(
        PlanSectionDefinition planSectionDefinition) throws SystemException {
        planSectionDefinition = toUnwrappedModel(planSectionDefinition);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, planSectionDefinition);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(planSectionDefinition);

        return planSectionDefinition;
    }

    @Override
    public PlanSectionDefinition updateImpl(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition,
        boolean merge) throws SystemException {
        planSectionDefinition = toUnwrappedModel(planSectionDefinition);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planSectionDefinition, merge);

            planSectionDefinition.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        EntityCacheUtil.putResult(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
            PlanSectionDefinitionImpl.class,
            planSectionDefinition.getPrimaryKey(), planSectionDefinition);

        return planSectionDefinition;
    }

    protected PlanSectionDefinition toUnwrappedModel(
        PlanSectionDefinition planSectionDefinition) {
        if (planSectionDefinition instanceof PlanSectionDefinitionImpl) {
            return planSectionDefinition;
        }

        PlanSectionDefinitionImpl planSectionDefinitionImpl = new PlanSectionDefinitionImpl();

        planSectionDefinitionImpl.setNew(planSectionDefinition.isNew());
        planSectionDefinitionImpl.setPrimaryKey(planSectionDefinition.getPrimaryKey());

        planSectionDefinitionImpl.setId(planSectionDefinition.getId());
        planSectionDefinitionImpl.setAdminTitle(planSectionDefinition.getAdminTitle());
        planSectionDefinitionImpl.setTitle(planSectionDefinition.getTitle());
        planSectionDefinitionImpl.setDefaultText(planSectionDefinition.getDefaultText());
        planSectionDefinitionImpl.setHelpText(planSectionDefinition.getHelpText());
        planSectionDefinitionImpl.setCharacterLimit(planSectionDefinition.getCharacterLimit());
        planSectionDefinitionImpl.setFocusAreaId(planSectionDefinition.getFocusAreaId());
        planSectionDefinitionImpl.setLocked(planSectionDefinition.isLocked());

        return planSectionDefinitionImpl;
    }

    /**
     * Returns the plan section definition with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan section definition
     * @return the plan section definition
     * @throws com.liferay.portal.NoSuchModelException if a plan section definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanSectionDefinition findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the plan section definition with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanSectionDefinitionException} if it could not be found.
     *
     * @param id the primary key of the plan section definition
     * @return the plan section definition
     * @throws com.ext.portlet.plans.NoSuchPlanSectionDefinitionException if a plan section definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanSectionDefinition findByPrimaryKey(long id)
        throws NoSuchPlanSectionDefinitionException, SystemException {
        PlanSectionDefinition planSectionDefinition = fetchByPrimaryKey(id);

        if (planSectionDefinition == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
            }

            throw new NoSuchPlanSectionDefinitionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                id);
        }

        return planSectionDefinition;
    }

    /**
     * Returns the plan section definition with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan section definition
     * @return the plan section definition, or <code>null</code> if a plan section definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanSectionDefinition fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the plan section definition with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the plan section definition
     * @return the plan section definition, or <code>null</code> if a plan section definition with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanSectionDefinition fetchByPrimaryKey(long id)
        throws SystemException {
        PlanSectionDefinition planSectionDefinition = (PlanSectionDefinition) EntityCacheUtil.getResult(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                PlanSectionDefinitionImpl.class, id);

        if (planSectionDefinition == _nullPlanSectionDefinition) {
            return null;
        }

        if (planSectionDefinition == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                planSectionDefinition = (PlanSectionDefinition) session.get(PlanSectionDefinitionImpl.class,
                        Long.valueOf(id));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (planSectionDefinition != null) {
                    cacheResult(planSectionDefinition);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(PlanSectionDefinitionModelImpl.ENTITY_CACHE_ENABLED,
                        PlanSectionDefinitionImpl.class, id,
                        _nullPlanSectionDefinition);
                }

                closeSession(session);
            }
        }

        return planSectionDefinition;
    }

    /**
     * Returns all the plan section definitions.
     *
     * @return the plan section definitions
     * @throws SystemException if a system exception occurred
     */
    public List<PlanSectionDefinition> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan section definitions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plan section definitions
     * @param end the upper bound of the range of plan section definitions (not inclusive)
     * @return the range of plan section definitions
     * @throws SystemException if a system exception occurred
     */
    public List<PlanSectionDefinition> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan section definitions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plan section definitions
     * @param end the upper bound of the range of plan section definitions (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan section definitions
     * @throws SystemException if a system exception occurred
     */
    public List<PlanSectionDefinition> findAll(int start, int end,
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

        List<PlanSectionDefinition> list = (List<PlanSectionDefinition>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANSECTIONDEFINITION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANSECTIONDEFINITION;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<PlanSectionDefinition>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanSectionDefinition>) QueryUtil.list(q,
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
     * Removes all the plan section definitions from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (PlanSectionDefinition planSectionDefinition : findAll()) {
            remove(planSectionDefinition);
        }
    }

    /**
     * Returns the number of plan section definitions.
     *
     * @return the number of plan section definitions
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_PLANSECTIONDEFINITION);

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
     * Initializes the plan section definition persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.plans.model.PlanSectionDefinition")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanSectionDefinition>> listenersList = new ArrayList<ModelListener<PlanSectionDefinition>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanSectionDefinition>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanSectionDefinitionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
