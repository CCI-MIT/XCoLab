package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanItemException;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.impl.PlanItemImpl;
import com.ext.portlet.plans.model.impl.PlanItemModelImpl;
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
 * The persistence implementation for the plan item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanItemPersistence
 * @see PlanItemUtil
 * @generated
 */
public class PlanItemPersistenceImpl extends BasePersistenceImpl<PlanItem>
    implements PlanItemPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanItemUtil} to access the plan item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanItemImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ALLBYPLANID =
        new FinderPath(PlanItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemModelImpl.FINDER_CACHE_ENABLED, PlanItemImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByAllByPlanId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALLBYPLANID =
        new FinderPath(PlanItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemModelImpl.FINDER_CACHE_ENABLED, PlanItemImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByAllByPlanId",
            new String[] { Long.class.getName() },
            PlanItemModelImpl.PLANID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ALLBYPLANID = new FinderPath(PlanItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAllByPlanId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_PLANID = new FinderPath(PlanItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemModelImpl.FINDER_CACHE_ENABLED, PlanItemImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchByPlanId",
            new String[] { Long.class.getName() },
            PlanItemModelImpl.PLANID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANID = new FinderPath(PlanItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPlanId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemModelImpl.FINDER_CACHE_ENABLED, PlanItemImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemModelImpl.FINDER_CACHE_ENABLED, PlanItemImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PLANITEM = "SELECT planItem FROM PlanItem planItem";
    private static final String _SQL_SELECT_PLANITEM_WHERE = "SELECT planItem FROM PlanItem planItem WHERE ";
    private static final String _SQL_COUNT_PLANITEM = "SELECT COUNT(planItem) FROM PlanItem planItem";
    private static final String _SQL_COUNT_PLANITEM_WHERE = "SELECT COUNT(planItem) FROM PlanItem planItem WHERE ";
    private static final String _FINDER_COLUMN_ALLBYPLANID_PLANID_2 = "planItem.planId = ?";
    private static final String _FINDER_COLUMN_PLANID_PLANID_2 = "planItem.planId = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planItem.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanItem exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PlanItem exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanItemPersistenceImpl.class);
    private static PlanItem _nullPlanItem = new PlanItemImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanItem> toCacheModel() {
                return _nullPlanItemCacheModel;
            }
        };

    private static CacheModel<PlanItem> _nullPlanItemCacheModel = new CacheModel<PlanItem>() {
            public PlanItem toEntityModel() {
                return _nullPlanItem;
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
     * Caches the plan item in the entity cache if it is enabled.
     *
     * @param planItem the plan item
     */
    public void cacheResult(PlanItem planItem) {
        EntityCacheUtil.putResult(PlanItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemImpl.class, planItem.getPrimaryKey(), planItem);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANID,
            new Object[] { Long.valueOf(planItem.getPlanId()) }, planItem);

        planItem.resetOriginalValues();
    }

    /**
     * Caches the plan items in the entity cache if it is enabled.
     *
     * @param planItems the plan items
     */
    public void cacheResult(List<PlanItem> planItems) {
        for (PlanItem planItem : planItems) {
            if (EntityCacheUtil.getResult(
                        PlanItemModelImpl.ENTITY_CACHE_ENABLED,
                        PlanItemImpl.class, planItem.getPrimaryKey()) == null) {
                cacheResult(planItem);
            } else {
                planItem.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan items.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanItemImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanItemImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan item.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanItem planItem) {
        EntityCacheUtil.removeResult(PlanItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemImpl.class, planItem.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(planItem);
    }

    @Override
    public void clearCache(List<PlanItem> planItems) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanItem planItem : planItems) {
            EntityCacheUtil.removeResult(PlanItemModelImpl.ENTITY_CACHE_ENABLED,
                PlanItemImpl.class, planItem.getPrimaryKey());

            clearUniqueFindersCache(planItem);
        }
    }

    protected void clearUniqueFindersCache(PlanItem planItem) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANID,
            new Object[] { Long.valueOf(planItem.getPlanId()) });
    }

    /**
     * Creates a new plan item with the primary key. Does not add the plan item to the database.
     *
     * @param id the primary key for the new plan item
     * @return the new plan item
     */
    public PlanItem create(Long id) {
        PlanItem planItem = new PlanItemImpl();

        planItem.setNew(true);
        planItem.setPrimaryKey(id);

        return planItem;
    }

    /**
     * Removes the plan item with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the plan item
     * @return the plan item that was removed
     * @throws com.ext.portlet.plans.NoSuchPlanItemException if a plan item with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanItem remove(Long id)
        throws NoSuchPlanItemException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the plan item with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan item
     * @return the plan item that was removed
     * @throws com.ext.portlet.plans.NoSuchPlanItemException if a plan item with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanItem remove(Serializable primaryKey)
        throws NoSuchPlanItemException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanItem planItem = (PlanItem) session.get(PlanItemImpl.class,
                    primaryKey);

            if (planItem == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planItem);
        } catch (NoSuchPlanItemException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanItem removeImpl(PlanItem planItem) throws SystemException {
        planItem = toUnwrappedModel(planItem);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, planItem);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(planItem);

        return planItem;
    }

    @Override
    public PlanItem updateImpl(com.ext.portlet.plans.model.PlanItem planItem,
        boolean merge) throws SystemException {
        planItem = toUnwrappedModel(planItem);

        boolean isNew = planItem.isNew();

        PlanItemModelImpl planItemModelImpl = (PlanItemModelImpl) planItem;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planItem, merge);

            planItem.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PlanItemModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((planItemModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALLBYPLANID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(planItemModelImpl.getOriginalPlanId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ALLBYPLANID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALLBYPLANID,
                    args);

                args = new Object[] { Long.valueOf(planItemModelImpl.getPlanId()) };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ALLBYPLANID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALLBYPLANID,
                    args);
            }
        }

        EntityCacheUtil.putResult(PlanItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanItemImpl.class, planItem.getPrimaryKey(), planItem);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANID,
                new Object[] { Long.valueOf(planItem.getPlanId()) }, planItem);
        } else {
            if ((planItemModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_PLANID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(planItemModelImpl.getOriginalPlanId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANID, args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANID,
                    new Object[] { Long.valueOf(planItem.getPlanId()) },
                    planItem);
            }
        }

        return planItem;
    }

    protected PlanItem toUnwrappedModel(PlanItem planItem) {
        if (planItem instanceof PlanItemImpl) {
            return planItem;
        }

        PlanItemImpl planItemImpl = new PlanItemImpl();

        planItemImpl.setNew(planItem.isNew());
        planItemImpl.setPrimaryKey(planItem.getPrimaryKey());

        planItemImpl.setId(planItem.getId());
        planItemImpl.setPlanId(planItem.getPlanId());
        planItemImpl.setState(planItem.getState());
        planItemImpl.setUpdated(planItem.getUpdated());
        planItemImpl.setUpdateAuthorId(planItem.getUpdateAuthorId());
        planItemImpl.setUpdateType(planItem.getUpdateType());
        planItemImpl.setVersion(planItem.getVersion());

        return planItemImpl;
    }

    /**
     * Returns the plan item with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan item
     * @return the plan item
     * @throws com.liferay.portal.NoSuchModelException if a plan item with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanItem findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey((Long) primaryKey);
    }

    /**
     * Returns the plan item with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanItemException} if it could not be found.
     *
     * @param id the primary key of the plan item
     * @return the plan item
     * @throws com.ext.portlet.plans.NoSuchPlanItemException if a plan item with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanItem findByPrimaryKey(Long id)
        throws NoSuchPlanItemException, SystemException {
        PlanItem planItem = fetchByPrimaryKey(id);

        if (planItem == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
            }

            throw new NoSuchPlanItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                id);
        }

        return planItem;
    }

    /**
     * Returns the plan item with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan item
     * @return the plan item, or <code>null</code> if a plan item with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanItem fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey((Long) primaryKey);
    }

    /**
     * Returns the plan item with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the plan item
     * @return the plan item, or <code>null</code> if a plan item with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanItem fetchByPrimaryKey(Long id) throws SystemException {
        PlanItem planItem = (PlanItem) EntityCacheUtil.getResult(PlanItemModelImpl.ENTITY_CACHE_ENABLED,
                PlanItemImpl.class, id);

        if (planItem == _nullPlanItem) {
            return null;
        }

        if (planItem == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                planItem = (PlanItem) session.get(PlanItemImpl.class,
                        Long.valueOf(id));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (planItem != null) {
                    cacheResult(planItem);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(PlanItemModelImpl.ENTITY_CACHE_ENABLED,
                        PlanItemImpl.class, id, _nullPlanItem);
                }

                closeSession(session);
            }
        }

        return planItem;
    }

    /**
     * Returns all the plan items where planId = &#63;.
     *
     * @param planId the plan ID
     * @return the matching plan items
     * @throws SystemException if a system exception occurred
     */
    public List<PlanItem> findByAllByPlanId(Long planId)
        throws SystemException {
        return findByAllByPlanId(planId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the plan items where planId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param planId the plan ID
     * @param start the lower bound of the range of plan items
     * @param end the upper bound of the range of plan items (not inclusive)
     * @return the range of matching plan items
     * @throws SystemException if a system exception occurred
     */
    public List<PlanItem> findByAllByPlanId(Long planId, int start, int end)
        throws SystemException {
        return findByAllByPlanId(planId, start, end, null);
    }

    /**
     * Returns an ordered range of all the plan items where planId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param planId the plan ID
     * @param start the lower bound of the range of plan items
     * @param end the upper bound of the range of plan items (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching plan items
     * @throws SystemException if a system exception occurred
     */
    public List<PlanItem> findByAllByPlanId(Long planId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALLBYPLANID;
            finderArgs = new Object[] { planId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ALLBYPLANID;
            finderArgs = new Object[] { planId, start, end, orderByComparator };
        }

        List<PlanItem> list = (List<PlanItem>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_PLANITEM_WHERE);

            query.append(_FINDER_COLUMN_ALLBYPLANID_PLANID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(PlanItemModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planId.longValue());

                list = (List<PlanItem>) QueryUtil.list(q, getDialect(), start,
                        end);
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
     * Returns the first plan item in the ordered set where planId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan item
     * @throws com.ext.portlet.plans.NoSuchPlanItemException if a matching plan item could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanItem findByAllByPlanId_First(Long planId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanItemException, SystemException {
        List<PlanItem> list = findByAllByPlanId(planId, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("planId=");
            msg.append(planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanItemException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last plan item in the ordered set where planId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan item
     * @throws com.ext.portlet.plans.NoSuchPlanItemException if a matching plan item could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanItem findByAllByPlanId_Last(Long planId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanItemException, SystemException {
        int count = countByAllByPlanId(planId);

        List<PlanItem> list = findByAllByPlanId(planId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("planId=");
            msg.append(planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanItemException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the plan items before and after the current plan item in the ordered set where planId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param id the primary key of the current plan item
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next plan item
     * @throws com.ext.portlet.plans.NoSuchPlanItemException if a plan item with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanItem[] findByAllByPlanId_PrevAndNext(Long id, Long planId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanItemException, SystemException {
        PlanItem planItem = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            PlanItem[] array = new PlanItemImpl[3];

            array[0] = getByAllByPlanId_PrevAndNext(session, planItem, planId,
                    orderByComparator, true);

            array[1] = planItem;

            array[2] = getByAllByPlanId_PrevAndNext(session, planItem, planId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PlanItem getByAllByPlanId_PrevAndNext(Session session,
        PlanItem planItem, Long planId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PLANITEM_WHERE);

        query.append(_FINDER_COLUMN_ALLBYPLANID_PLANID_2);

        if (orderByComparator != null) {
            String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

            if (orderByConditionFields.length > 0) {
                query.append(WHERE_AND);
            }

            for (int i = 0; i < orderByConditionFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByConditionFields[i]);

                if ((i + 1) < orderByConditionFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN_HAS_NEXT);
                    } else {
                        query.append(WHERE_LESSER_THAN_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(WHERE_GREATER_THAN);
                    } else {
                        query.append(WHERE_LESSER_THAN);
                    }
                }
            }

            query.append(ORDER_BY_CLAUSE);

            String[] orderByFields = orderByComparator.getOrderByFields();

            for (int i = 0; i < orderByFields.length; i++) {
                query.append(_ORDER_BY_ENTITY_ALIAS);
                query.append(orderByFields[i]);

                if ((i + 1) < orderByFields.length) {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC_HAS_NEXT);
                    } else {
                        query.append(ORDER_BY_DESC_HAS_NEXT);
                    }
                } else {
                    if (orderByComparator.isAscending() ^ previous) {
                        query.append(ORDER_BY_ASC);
                    } else {
                        query.append(ORDER_BY_DESC);
                    }
                }
            }
        }
        else {
            query.append(PlanItemModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(planId.longValue());

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(planItem);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PlanItem> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns the plan item where planId = &#63; or throws a {@link com.ext.portlet.plans.NoSuchPlanItemException} if it could not be found.
     *
     * @param planId the plan ID
     * @return the matching plan item
     * @throws com.ext.portlet.plans.NoSuchPlanItemException if a matching plan item could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanItem findByPlanId(Long planId)
        throws NoSuchPlanItemException, SystemException {
        PlanItem planItem = fetchByPlanId(planId);

        if (planItem == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("planId=");
            msg.append(planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanItemException(msg.toString());
        }

        return planItem;
    }

    /**
     * Returns the plan item where planId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param planId the plan ID
     * @return the matching plan item, or <code>null</code> if a matching plan item could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanItem fetchByPlanId(Long planId) throws SystemException {
        return fetchByPlanId(planId, true);
    }

    /**
     * Returns the plan item where planId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param planId the plan ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching plan item, or <code>null</code> if a matching plan item could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanItem fetchByPlanId(Long planId, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { planId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PLANID,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_PLANITEM_WHERE);

            query.append(_FINDER_COLUMN_PLANID_PLANID_2);

            query.append(PlanItemModelImpl.ORDER_BY_JPQL);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planId.longValue());

                List<PlanItem> list = q.list();

                result = list;

                PlanItem planItem = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANID,
                        finderArgs, list);
                } else {
                    planItem = list.get(0);

                    cacheResult(planItem);

                    if ((planItem.getPlanId() != planId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANID,
                            finderArgs, planItem);
                    }
                }

                return planItem;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANID,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (PlanItem) result;
            }
        }
    }

    /**
     * Returns all the plan items.
     *
     * @return the plan items
     * @throws SystemException if a system exception occurred
     */
    public List<PlanItem> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan items.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plan items
     * @param end the upper bound of the range of plan items (not inclusive)
     * @return the range of plan items
     * @throws SystemException if a system exception occurred
     */
    public List<PlanItem> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan items.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plan items
     * @param end the upper bound of the range of plan items (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan items
     * @throws SystemException if a system exception occurred
     */
    public List<PlanItem> findAll(int start, int end,
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

        List<PlanItem> list = (List<PlanItem>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANITEM);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANITEM.concat(PlanItemModelImpl.ORDER_BY_JPQL);
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<PlanItem>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanItem>) QueryUtil.list(q, getDialect(),
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
     * Removes all the plan items where planId = &#63; from the database.
     *
     * @param planId the plan ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByAllByPlanId(Long planId) throws SystemException {
        for (PlanItem planItem : findByAllByPlanId(planId)) {
            remove(planItem);
        }
    }

    /**
     * Removes the plan item where planId = &#63; from the database.
     *
     * @param planId the plan ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByPlanId(Long planId)
        throws NoSuchPlanItemException, SystemException {
        PlanItem planItem = findByPlanId(planId);

        remove(planItem);
    }

    /**
     * Removes all the plan items from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (PlanItem planItem : findAll()) {
            remove(planItem);
        }
    }

    /**
     * Returns the number of plan items where planId = &#63;.
     *
     * @param planId the plan ID
     * @return the number of matching plan items
     * @throws SystemException if a system exception occurred
     */
    public int countByAllByPlanId(Long planId) throws SystemException {
        Object[] finderArgs = new Object[] { planId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ALLBYPLANID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PLANITEM_WHERE);

            query.append(_FINDER_COLUMN_ALLBYPLANID_PLANID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planId.longValue());

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ALLBYPLANID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of plan items where planId = &#63;.
     *
     * @param planId the plan ID
     * @return the number of matching plan items
     * @throws SystemException if a system exception occurred
     */
    public int countByPlanId(Long planId) throws SystemException {
        Object[] finderArgs = new Object[] { planId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PLANID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PLANITEM_WHERE);

            query.append(_FINDER_COLUMN_PLANID_PLANID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planId.longValue());

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLANID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of plan items.
     *
     * @return the number of plan items
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_PLANITEM);

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
     * Initializes the plan item persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.plans.model.PlanItem")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanItem>> listenersList = new ArrayList<ModelListener<PlanItem>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanItem>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanItemImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
