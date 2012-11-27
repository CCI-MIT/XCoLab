package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanRelatedException;
import com.ext.portlet.plans.model.PlanRelated;
import com.ext.portlet.plans.model.impl.PlanRelatedImpl;
import com.ext.portlet.plans.model.impl.PlanRelatedModelImpl;
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
 * The persistence implementation for the plan related service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanRelatedPersistence
 * @see PlanRelatedUtil
 * @generated
 */
public class PlanRelatedPersistenceImpl extends BasePersistenceImpl<PlanRelated>
    implements PlanRelatedPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanRelatedUtil} to access the plan related persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanRelatedImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PLANID = new FinderPath(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedModelImpl.FINDER_CACHE_ENABLED, PlanRelatedImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPlanId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID =
        new FinderPath(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedModelImpl.FINDER_CACHE_ENABLED, PlanRelatedImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPlanId",
            new String[] { Long.class.getName() },
            PlanRelatedModelImpl.RELATEDPLANID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANID = new FinderPath(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPlanId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedModelImpl.FINDER_CACHE_ENABLED, PlanRelatedImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedModelImpl.FINDER_CACHE_ENABLED, PlanRelatedImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PLANRELATED = "SELECT planRelated FROM PlanRelated planRelated";
    private static final String _SQL_SELECT_PLANRELATED_WHERE = "SELECT planRelated FROM PlanRelated planRelated WHERE ";
    private static final String _SQL_COUNT_PLANRELATED = "SELECT COUNT(planRelated) FROM PlanRelated planRelated";
    private static final String _SQL_COUNT_PLANRELATED_WHERE = "SELECT COUNT(planRelated) FROM PlanRelated planRelated WHERE ";
    private static final String _FINDER_COLUMN_PLANID_RELATEDPLANID_2 = "planRelated.id.relatedPlanId = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planRelated.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanRelated exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PlanRelated exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanRelatedPersistenceImpl.class);
    private static PlanRelated _nullPlanRelated = new PlanRelatedImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanRelated> toCacheModel() {
                return _nullPlanRelatedCacheModel;
            }
        };

    private static CacheModel<PlanRelated> _nullPlanRelatedCacheModel = new CacheModel<PlanRelated>() {
            public PlanRelated toEntityModel() {
                return _nullPlanRelated;
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
     * Caches the plan related in the entity cache if it is enabled.
     *
     * @param planRelated the plan related
     */
    public void cacheResult(PlanRelated planRelated) {
        EntityCacheUtil.putResult(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedImpl.class, planRelated.getPrimaryKey(), planRelated);

        planRelated.resetOriginalValues();
    }

    /**
     * Caches the plan relateds in the entity cache if it is enabled.
     *
     * @param planRelateds the plan relateds
     */
    public void cacheResult(List<PlanRelated> planRelateds) {
        for (PlanRelated planRelated : planRelateds) {
            if (EntityCacheUtil.getResult(
                        PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
                        PlanRelatedImpl.class, planRelated.getPrimaryKey()) == null) {
                cacheResult(planRelated);
            } else {
                planRelated.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan relateds.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanRelatedImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanRelatedImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan related.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanRelated planRelated) {
        EntityCacheUtil.removeResult(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedImpl.class, planRelated.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<PlanRelated> planRelateds) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanRelated planRelated : planRelateds) {
            EntityCacheUtil.removeResult(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
                PlanRelatedImpl.class, planRelated.getPrimaryKey());
        }
    }

    /**
     * Creates a new plan related with the primary key. Does not add the plan related to the database.
     *
     * @param planRelatedPK the primary key for the new plan related
     * @return the new plan related
     */
    public PlanRelated create(PlanRelatedPK planRelatedPK) {
        PlanRelated planRelated = new PlanRelatedImpl();

        planRelated.setNew(true);
        planRelated.setPrimaryKey(planRelatedPK);

        return planRelated;
    }

    /**
     * Removes the plan related with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param planRelatedPK the primary key of the plan related
     * @return the plan related that was removed
     * @throws com.ext.portlet.plans.NoSuchPlanRelatedException if a plan related with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanRelated remove(PlanRelatedPK planRelatedPK)
        throws NoSuchPlanRelatedException, SystemException {
        return remove((Serializable) planRelatedPK);
    }

    /**
     * Removes the plan related with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan related
     * @return the plan related that was removed
     * @throws com.ext.portlet.plans.NoSuchPlanRelatedException if a plan related with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanRelated remove(Serializable primaryKey)
        throws NoSuchPlanRelatedException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanRelated planRelated = (PlanRelated) session.get(PlanRelatedImpl.class,
                    primaryKey);

            if (planRelated == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanRelatedException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planRelated);
        } catch (NoSuchPlanRelatedException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanRelated removeImpl(PlanRelated planRelated)
        throws SystemException {
        planRelated = toUnwrappedModel(planRelated);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, planRelated);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(planRelated);

        return planRelated;
    }

    @Override
    public PlanRelated updateImpl(
        com.ext.portlet.plans.model.PlanRelated planRelated, boolean merge)
        throws SystemException {
        planRelated = toUnwrappedModel(planRelated);

        boolean isNew = planRelated.isNew();

        PlanRelatedModelImpl planRelatedModelImpl = (PlanRelatedModelImpl) planRelated;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planRelated, merge);

            planRelated.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PlanRelatedModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((planRelatedModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(planRelatedModelImpl.getOriginalRelatedPlanId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID,
                    args);

                args = new Object[] {
                        Long.valueOf(planRelatedModelImpl.getRelatedPlanId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID,
                    args);
            }
        }

        EntityCacheUtil.putResult(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
            PlanRelatedImpl.class, planRelated.getPrimaryKey(), planRelated);

        return planRelated;
    }

    protected PlanRelated toUnwrappedModel(PlanRelated planRelated) {
        if (planRelated instanceof PlanRelatedImpl) {
            return planRelated;
        }

        PlanRelatedImpl planRelatedImpl = new PlanRelatedImpl();

        planRelatedImpl.setNew(planRelated.isNew());
        planRelatedImpl.setPrimaryKey(planRelated.getPrimaryKey());

        planRelatedImpl.setSectionId(planRelated.getSectionId());
        planRelatedImpl.setRelatedPlanId(planRelated.getRelatedPlanId());

        return planRelatedImpl;
    }

    /**
     * Returns the plan related with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan related
     * @return the plan related
     * @throws com.liferay.portal.NoSuchModelException if a plan related with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanRelated findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey((PlanRelatedPK) primaryKey);
    }

    /**
     * Returns the plan related with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanRelatedException} if it could not be found.
     *
     * @param planRelatedPK the primary key of the plan related
     * @return the plan related
     * @throws com.ext.portlet.plans.NoSuchPlanRelatedException if a plan related with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanRelated findByPrimaryKey(PlanRelatedPK planRelatedPK)
        throws NoSuchPlanRelatedException, SystemException {
        PlanRelated planRelated = fetchByPrimaryKey(planRelatedPK);

        if (planRelated == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + planRelatedPK);
            }

            throw new NoSuchPlanRelatedException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                planRelatedPK);
        }

        return planRelated;
    }

    /**
     * Returns the plan related with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan related
     * @return the plan related, or <code>null</code> if a plan related with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanRelated fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey((PlanRelatedPK) primaryKey);
    }

    /**
     * Returns the plan related with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param planRelatedPK the primary key of the plan related
     * @return the plan related, or <code>null</code> if a plan related with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanRelated fetchByPrimaryKey(PlanRelatedPK planRelatedPK)
        throws SystemException {
        PlanRelated planRelated = (PlanRelated) EntityCacheUtil.getResult(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
                PlanRelatedImpl.class, planRelatedPK);

        if (planRelated == _nullPlanRelated) {
            return null;
        }

        if (planRelated == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                planRelated = (PlanRelated) session.get(PlanRelatedImpl.class,
                        planRelatedPK);
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (planRelated != null) {
                    cacheResult(planRelated);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(PlanRelatedModelImpl.ENTITY_CACHE_ENABLED,
                        PlanRelatedImpl.class, planRelatedPK, _nullPlanRelated);
                }

                closeSession(session);
            }
        }

        return planRelated;
    }

    /**
     * Returns all the plan relateds where relatedPlanId = &#63;.
     *
     * @param relatedPlanId the related plan ID
     * @return the matching plan relateds
     * @throws SystemException if a system exception occurred
     */
    public List<PlanRelated> findByPlanId(Long relatedPlanId)
        throws SystemException {
        return findByPlanId(relatedPlanId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan relateds where relatedPlanId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param relatedPlanId the related plan ID
     * @param start the lower bound of the range of plan relateds
     * @param end the upper bound of the range of plan relateds (not inclusive)
     * @return the range of matching plan relateds
     * @throws SystemException if a system exception occurred
     */
    public List<PlanRelated> findByPlanId(Long relatedPlanId, int start, int end)
        throws SystemException {
        return findByPlanId(relatedPlanId, start, end, null);
    }

    /**
     * Returns an ordered range of all the plan relateds where relatedPlanId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param relatedPlanId the related plan ID
     * @param start the lower bound of the range of plan relateds
     * @param end the upper bound of the range of plan relateds (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching plan relateds
     * @throws SystemException if a system exception occurred
     */
    public List<PlanRelated> findByPlanId(Long relatedPlanId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID;
            finderArgs = new Object[] { relatedPlanId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PLANID;
            finderArgs = new Object[] {
                    relatedPlanId,
                    
                    start, end, orderByComparator
                };
        }

        List<PlanRelated> list = (List<PlanRelated>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_PLANRELATED_WHERE);

            query.append(_FINDER_COLUMN_PLANID_RELATEDPLANID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(relatedPlanId.longValue());

                list = (List<PlanRelated>) QueryUtil.list(q, getDialect(),
                        start, end);
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
     * Returns the first plan related in the ordered set where relatedPlanId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param relatedPlanId the related plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan related
     * @throws com.ext.portlet.plans.NoSuchPlanRelatedException if a matching plan related could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanRelated findByPlanId_First(Long relatedPlanId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanRelatedException, SystemException {
        List<PlanRelated> list = findByPlanId(relatedPlanId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("relatedPlanId=");
            msg.append(relatedPlanId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanRelatedException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last plan related in the ordered set where relatedPlanId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param relatedPlanId the related plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan related
     * @throws com.ext.portlet.plans.NoSuchPlanRelatedException if a matching plan related could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanRelated findByPlanId_Last(Long relatedPlanId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanRelatedException, SystemException {
        int count = countByPlanId(relatedPlanId);

        List<PlanRelated> list = findByPlanId(relatedPlanId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("relatedPlanId=");
            msg.append(relatedPlanId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanRelatedException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the plan relateds before and after the current plan related in the ordered set where relatedPlanId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param planRelatedPK the primary key of the current plan related
     * @param relatedPlanId the related plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next plan related
     * @throws com.ext.portlet.plans.NoSuchPlanRelatedException if a plan related with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanRelated[] findByPlanId_PrevAndNext(PlanRelatedPK planRelatedPK,
        Long relatedPlanId, OrderByComparator orderByComparator)
        throws NoSuchPlanRelatedException, SystemException {
        PlanRelated planRelated = findByPrimaryKey(planRelatedPK);

        Session session = null;

        try {
            session = openSession();

            PlanRelated[] array = new PlanRelatedImpl[3];

            array[0] = getByPlanId_PrevAndNext(session, planRelated,
                    relatedPlanId, orderByComparator, true);

            array[1] = planRelated;

            array[2] = getByPlanId_PrevAndNext(session, planRelated,
                    relatedPlanId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PlanRelated getByPlanId_PrevAndNext(Session session,
        PlanRelated planRelated, Long relatedPlanId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PLANRELATED_WHERE);

        query.append(_FINDER_COLUMN_PLANID_RELATEDPLANID_2);

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

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(relatedPlanId.longValue());

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(planRelated);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PlanRelated> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the plan relateds.
     *
     * @return the plan relateds
     * @throws SystemException if a system exception occurred
     */
    public List<PlanRelated> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan relateds.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plan relateds
     * @param end the upper bound of the range of plan relateds (not inclusive)
     * @return the range of plan relateds
     * @throws SystemException if a system exception occurred
     */
    public List<PlanRelated> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan relateds.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plan relateds
     * @param end the upper bound of the range of plan relateds (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan relateds
     * @throws SystemException if a system exception occurred
     */
    public List<PlanRelated> findAll(int start, int end,
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

        List<PlanRelated> list = (List<PlanRelated>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANRELATED);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANRELATED;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<PlanRelated>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanRelated>) QueryUtil.list(q, getDialect(),
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
     * Removes all the plan relateds where relatedPlanId = &#63; from the database.
     *
     * @param relatedPlanId the related plan ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByPlanId(Long relatedPlanId) throws SystemException {
        for (PlanRelated planRelated : findByPlanId(relatedPlanId)) {
            remove(planRelated);
        }
    }

    /**
     * Removes all the plan relateds from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (PlanRelated planRelated : findAll()) {
            remove(planRelated);
        }
    }

    /**
     * Returns the number of plan relateds where relatedPlanId = &#63;.
     *
     * @param relatedPlanId the related plan ID
     * @return the number of matching plan relateds
     * @throws SystemException if a system exception occurred
     */
    public int countByPlanId(Long relatedPlanId) throws SystemException {
        Object[] finderArgs = new Object[] { relatedPlanId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PLANID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PLANRELATED_WHERE);

            query.append(_FINDER_COLUMN_PLANID_RELATEDPLANID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(relatedPlanId.longValue());

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
     * Returns the number of plan relateds.
     *
     * @return the number of plan relateds
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_PLANRELATED);

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
     * Initializes the plan related persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.plans.model.PlanRelated")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanRelated>> listenersList = new ArrayList<ModelListener<PlanRelated>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanRelated>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanRelatedImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
