package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPlanFanException;
import com.ext.portlet.model.PlanFan;
import com.ext.portlet.model.impl.PlanFanImpl;
import com.ext.portlet.model.impl.PlanFanModelImpl;
import com.ext.portlet.service.persistence.ActivitySubscriptionPersistence;
import com.ext.portlet.service.persistence.ContestDebatePersistence;
import com.ext.portlet.service.persistence.ContestPersistence;
import com.ext.portlet.service.persistence.ContestPhaseColumnPersistence;
import com.ext.portlet.service.persistence.ContestPhasePersistence;
import com.ext.portlet.service.persistence.ContestPhaseTypePersistence;
import com.ext.portlet.service.persistence.ContestTeamMemberPersistence;
import com.ext.portlet.service.persistence.DiscussionCategoryGroupPersistence;
import com.ext.portlet.service.persistence.DiscussionCategoryPersistence;
import com.ext.portlet.service.persistence.DiscussionMessageFlagPersistence;
import com.ext.portlet.service.persistence.DiscussionMessagePersistence;
import com.ext.portlet.service.persistence.FocusAreaOntologyTermPersistence;
import com.ext.portlet.service.persistence.FocusAreaPersistence;
import com.ext.portlet.service.persistence.MessagePersistence;
import com.ext.portlet.service.persistence.MessageRecipientStatusPersistence;
import com.ext.portlet.service.persistence.MessagingUserPreferencesPersistence;
import com.ext.portlet.service.persistence.ModelCategoryPersistence;
import com.ext.portlet.service.persistence.ModelDiscussionPersistence;
import com.ext.portlet.service.persistence.ModelGlobalPreferencePersistence;
import com.ext.portlet.service.persistence.ModelInputGroupPersistence;
import com.ext.portlet.service.persistence.ModelInputItemPersistence;
import com.ext.portlet.service.persistence.ModelOutputChartOrderPersistence;
import com.ext.portlet.service.persistence.ModelOutputItemPersistence;
import com.ext.portlet.service.persistence.ModelPositionPersistence;
import com.ext.portlet.service.persistence.OntologySpacePersistence;
import com.ext.portlet.service.persistence.OntologyTermEntityPersistence;
import com.ext.portlet.service.persistence.OntologyTermPersistence;
import com.ext.portlet.service.persistence.PlanAttributeFilterPersistence;
import com.ext.portlet.service.persistence.PlanAttributePersistence;
import com.ext.portlet.service.persistence.PlanColumnSettingsPersistence;
import com.ext.portlet.service.persistence.PlanDescriptionPersistence;
import com.ext.portlet.service.persistence.PlanFanPersistence;
import com.ext.portlet.service.persistence.PlanItemPersistence;
import com.ext.portlet.service.persistence.PlanMetaPersistence;
import com.ext.portlet.service.persistence.PlanModelRunPersistence;
import com.ext.portlet.service.persistence.PlanPositionItemPersistence;
import com.ext.portlet.service.persistence.PlanPositionPersistence;
import com.ext.portlet.service.persistence.PlanPositionsPersistence;
import com.ext.portlet.service.persistence.PlanPropertyFilterPersistence;
import com.ext.portlet.service.persistence.PlanRelatedPersistence;
import com.ext.portlet.service.persistence.PlanSectionDefinitionPersistence;
import com.ext.portlet.service.persistence.PlanSectionPersistence;
import com.ext.portlet.service.persistence.PlanSectionPlanMapPersistence;
import com.ext.portlet.service.persistence.PlanTeamHistoryPersistence;
import com.ext.portlet.service.persistence.PlanTemplatePersistence;
import com.ext.portlet.service.persistence.PlanTemplateSectionPersistence;
import com.ext.portlet.service.persistence.PlanTypeAttributePersistence;
import com.ext.portlet.service.persistence.PlanTypeColumnPersistence;
import com.ext.portlet.service.persistence.PlanTypePersistence;
import com.ext.portlet.service.persistence.PlanVotePersistence;
import com.ext.portlet.service.persistence.PlansFilterPersistence;
import com.ext.portlet.service.persistence.PlansFilterPositionPersistence;
import com.ext.portlet.service.persistence.PlansUserSettingsPersistence;

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
 * The persistence implementation for the plan fan service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanFanPersistence
 * @see PlanFanUtil
 * @generated
 */
public class PlanFanPersistenceImpl extends BasePersistenceImpl<PlanFan>
    implements PlanFanPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanFanUtil} to access the plan fan persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanFanImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PLANID = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, PlanFanImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPlanId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID =
        new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, PlanFanImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPlanId",
            new String[] { Long.class.getName() },
            PlanFanModelImpl.PLANID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANID = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPlanId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, PlanFanImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
        new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, PlanFanImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
            new String[] { Long.class.getName() },
            PlanFanModelImpl.USERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_PLANIDUSERID = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, PlanFanImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchByPlanIdUserId",
            new String[] { Long.class.getName(), Long.class.getName() },
            PlanFanModelImpl.PLANID_COLUMN_BITMASK |
            PlanFanModelImpl.USERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANIDUSERID = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPlanIdUserId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, PlanFanImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, PlanFanImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PLANFAN = "SELECT planFan FROM PlanFan planFan";
    private static final String _SQL_SELECT_PLANFAN_WHERE = "SELECT planFan FROM PlanFan planFan WHERE ";
    private static final String _SQL_COUNT_PLANFAN = "SELECT COUNT(planFan) FROM PlanFan planFan";
    private static final String _SQL_COUNT_PLANFAN_WHERE = "SELECT COUNT(planFan) FROM PlanFan planFan WHERE ";
    private static final String _FINDER_COLUMN_PLANID_PLANID_2 = "planFan.planId = ? AND planFan.deleted is null";
    private static final String _FINDER_COLUMN_USERID_USERID_2 = "planFan.userId = ? AND planFan.deleted is null";
    private static final String _FINDER_COLUMN_PLANIDUSERID_PLANID_2 = "planFan.planId = ? AND ";
    private static final String _FINDER_COLUMN_PLANIDUSERID_USERID_2 = "planFan.userId = ? AND planFan.deleted is null";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planFan.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanFan exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PlanFan exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanFanPersistenceImpl.class);
    private static PlanFan _nullPlanFan = new PlanFanImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanFan> toCacheModel() {
                return _nullPlanFanCacheModel;
            }
        };

    private static CacheModel<PlanFan> _nullPlanFanCacheModel = new CacheModel<PlanFan>() {
            public PlanFan toEntityModel() {
                return _nullPlanFan;
            }
        };

    @BeanReference(type = ActivitySubscriptionPersistence.class)
    protected ActivitySubscriptionPersistence activitySubscriptionPersistence;
    @BeanReference(type = ContestPersistence.class)
    protected ContestPersistence contestPersistence;
    @BeanReference(type = ContestDebatePersistence.class)
    protected ContestDebatePersistence contestDebatePersistence;
    @BeanReference(type = ContestPhasePersistence.class)
    protected ContestPhasePersistence contestPhasePersistence;
    @BeanReference(type = ContestPhaseColumnPersistence.class)
    protected ContestPhaseColumnPersistence contestPhaseColumnPersistence;
    @BeanReference(type = ContestPhaseTypePersistence.class)
    protected ContestPhaseTypePersistence contestPhaseTypePersistence;
    @BeanReference(type = ContestTeamMemberPersistence.class)
    protected ContestTeamMemberPersistence contestTeamMemberPersistence;
    @BeanReference(type = DiscussionCategoryPersistence.class)
    protected DiscussionCategoryPersistence discussionCategoryPersistence;
    @BeanReference(type = DiscussionCategoryGroupPersistence.class)
    protected DiscussionCategoryGroupPersistence discussionCategoryGroupPersistence;
    @BeanReference(type = DiscussionMessagePersistence.class)
    protected DiscussionMessagePersistence discussionMessagePersistence;
    @BeanReference(type = DiscussionMessageFlagPersistence.class)
    protected DiscussionMessageFlagPersistence discussionMessageFlagPersistence;
    @BeanReference(type = FocusAreaPersistence.class)
    protected FocusAreaPersistence focusAreaPersistence;
    @BeanReference(type = FocusAreaOntologyTermPersistence.class)
    protected FocusAreaOntologyTermPersistence focusAreaOntologyTermPersistence;
    @BeanReference(type = MessagePersistence.class)
    protected MessagePersistence messagePersistence;
    @BeanReference(type = MessageRecipientStatusPersistence.class)
    protected MessageRecipientStatusPersistence messageRecipientStatusPersistence;
    @BeanReference(type = MessagingUserPreferencesPersistence.class)
    protected MessagingUserPreferencesPersistence messagingUserPreferencesPersistence;
    @BeanReference(type = ModelCategoryPersistence.class)
    protected ModelCategoryPersistence modelCategoryPersistence;
    @BeanReference(type = ModelDiscussionPersistence.class)
    protected ModelDiscussionPersistence modelDiscussionPersistence;
    @BeanReference(type = ModelGlobalPreferencePersistence.class)
    protected ModelGlobalPreferencePersistence modelGlobalPreferencePersistence;
    @BeanReference(type = ModelInputGroupPersistence.class)
    protected ModelInputGroupPersistence modelInputGroupPersistence;
    @BeanReference(type = ModelInputItemPersistence.class)
    protected ModelInputItemPersistence modelInputItemPersistence;
    @BeanReference(type = ModelOutputChartOrderPersistence.class)
    protected ModelOutputChartOrderPersistence modelOutputChartOrderPersistence;
    @BeanReference(type = ModelOutputItemPersistence.class)
    protected ModelOutputItemPersistence modelOutputItemPersistence;
    @BeanReference(type = ModelPositionPersistence.class)
    protected ModelPositionPersistence modelPositionPersistence;
    @BeanReference(type = OntologySpacePersistence.class)
    protected OntologySpacePersistence ontologySpacePersistence;
    @BeanReference(type = OntologyTermPersistence.class)
    protected OntologyTermPersistence ontologyTermPersistence;
    @BeanReference(type = OntologyTermEntityPersistence.class)
    protected OntologyTermEntityPersistence ontologyTermEntityPersistence;
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
     * Caches the plan fan in the entity cache if it is enabled.
     *
     * @param planFan the plan fan
     */
    public void cacheResult(PlanFan planFan) {
        EntityCacheUtil.putResult(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanImpl.class, planFan.getPrimaryKey(), planFan);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANIDUSERID,
            new Object[] {
                Long.valueOf(planFan.getPlanId()),
                Long.valueOf(planFan.getUserId())
            }, planFan);

        planFan.resetOriginalValues();
    }

    /**
     * Caches the plan fans in the entity cache if it is enabled.
     *
     * @param planFans the plan fans
     */
    public void cacheResult(List<PlanFan> planFans) {
        for (PlanFan planFan : planFans) {
            if (EntityCacheUtil.getResult(
                        PlanFanModelImpl.ENTITY_CACHE_ENABLED,
                        PlanFanImpl.class, planFan.getPrimaryKey()) == null) {
                cacheResult(planFan);
            } else {
                planFan.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan fans.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanFanImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanFanImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan fan.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanFan planFan) {
        EntityCacheUtil.removeResult(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanImpl.class, planFan.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(planFan);
    }

    @Override
    public void clearCache(List<PlanFan> planFans) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanFan planFan : planFans) {
            EntityCacheUtil.removeResult(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
                PlanFanImpl.class, planFan.getPrimaryKey());

            clearUniqueFindersCache(planFan);
        }
    }

    protected void clearUniqueFindersCache(PlanFan planFan) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANIDUSERID,
            new Object[] {
                Long.valueOf(planFan.getPlanId()),
                Long.valueOf(planFan.getUserId())
            });
    }

    /**
     * Creates a new plan fan with the primary key. Does not add the plan fan to the database.
     *
     * @param id the primary key for the new plan fan
     * @return the new plan fan
     */
    public PlanFan create(long id) {
        PlanFan planFan = new PlanFanImpl();

        planFan.setNew(true);
        planFan.setPrimaryKey(id);

        return planFan;
    }

    /**
     * Removes the plan fan with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the plan fan
     * @return the plan fan that was removed
     * @throws com.ext.portlet.NoSuchPlanFanException if a plan fan with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanFan remove(long id)
        throws NoSuchPlanFanException, SystemException {
        return remove(Long.valueOf(id));
    }

    /**
     * Removes the plan fan with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan fan
     * @return the plan fan that was removed
     * @throws com.ext.portlet.NoSuchPlanFanException if a plan fan with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanFan remove(Serializable primaryKey)
        throws NoSuchPlanFanException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanFan planFan = (PlanFan) session.get(PlanFanImpl.class,
                    primaryKey);

            if (planFan == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanFanException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planFan);
        } catch (NoSuchPlanFanException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanFan removeImpl(PlanFan planFan) throws SystemException {
        planFan = toUnwrappedModel(planFan);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, planFan);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(planFan);

        return planFan;
    }

    @Override
    public PlanFan updateImpl(com.ext.portlet.model.PlanFan planFan,
        boolean merge) throws SystemException {
        planFan = toUnwrappedModel(planFan);

        boolean isNew = planFan.isNew();

        PlanFanModelImpl planFanModelImpl = (PlanFanModelImpl) planFan;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planFan, merge);

            planFan.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PlanFanModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((planFanModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(planFanModelImpl.getOriginalPlanId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID,
                    args);

                args = new Object[] { Long.valueOf(planFanModelImpl.getPlanId()) };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID,
                    args);
            }

            if ((planFanModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(planFanModelImpl.getOriginalUserId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
                    args);

                args = new Object[] { Long.valueOf(planFanModelImpl.getUserId()) };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
                    args);
            }
        }

        EntityCacheUtil.putResult(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
            PlanFanImpl.class, planFan.getPrimaryKey(), planFan);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANIDUSERID,
                new Object[] {
                    Long.valueOf(planFan.getPlanId()),
                    Long.valueOf(planFan.getUserId())
                }, planFan);
        } else {
            if ((planFanModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_PLANIDUSERID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(planFanModelImpl.getOriginalPlanId()),
                        Long.valueOf(planFanModelImpl.getOriginalUserId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANIDUSERID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANIDUSERID,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANIDUSERID,
                    new Object[] {
                        Long.valueOf(planFan.getPlanId()),
                        Long.valueOf(planFan.getUserId())
                    }, planFan);
            }
        }

        return planFan;
    }

    protected PlanFan toUnwrappedModel(PlanFan planFan) {
        if (planFan instanceof PlanFanImpl) {
            return planFan;
        }

        PlanFanImpl planFanImpl = new PlanFanImpl();

        planFanImpl.setNew(planFan.isNew());
        planFanImpl.setPrimaryKey(planFan.getPrimaryKey());

        planFanImpl.setId(planFan.getId());
        planFanImpl.setUserId(planFan.getUserId());
        planFanImpl.setPlanId(planFan.getPlanId());
        planFanImpl.setCreated(planFan.getCreated());
        planFanImpl.setDeleted(planFan.getDeleted());

        return planFanImpl;
    }

    /**
     * Returns the plan fan with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan fan
     * @return the plan fan
     * @throws com.liferay.portal.NoSuchModelException if a plan fan with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanFan findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the plan fan with the primary key or throws a {@link com.ext.portlet.NoSuchPlanFanException} if it could not be found.
     *
     * @param id the primary key of the plan fan
     * @return the plan fan
     * @throws com.ext.portlet.NoSuchPlanFanException if a plan fan with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanFan findByPrimaryKey(long id)
        throws NoSuchPlanFanException, SystemException {
        PlanFan planFan = fetchByPrimaryKey(id);

        if (planFan == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
            }

            throw new NoSuchPlanFanException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                id);
        }

        return planFan;
    }

    /**
     * Returns the plan fan with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan fan
     * @return the plan fan, or <code>null</code> if a plan fan with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanFan fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the plan fan with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the plan fan
     * @return the plan fan, or <code>null</code> if a plan fan with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanFan fetchByPrimaryKey(long id) throws SystemException {
        PlanFan planFan = (PlanFan) EntityCacheUtil.getResult(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
                PlanFanImpl.class, id);

        if (planFan == _nullPlanFan) {
            return null;
        }

        if (planFan == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                planFan = (PlanFan) session.get(PlanFanImpl.class,
                        Long.valueOf(id));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (planFan != null) {
                    cacheResult(planFan);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(PlanFanModelImpl.ENTITY_CACHE_ENABLED,
                        PlanFanImpl.class, id, _nullPlanFan);
                }

                closeSession(session);
            }
        }

        return planFan;
    }

    /**
     * Returns all the plan fans where planId = &#63;.
     *
     * @param planId the plan ID
     * @return the matching plan fans
     * @throws SystemException if a system exception occurred
     */
    public List<PlanFan> findByPlanId(long planId) throws SystemException {
        return findByPlanId(planId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan fans where planId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param planId the plan ID
     * @param start the lower bound of the range of plan fans
     * @param end the upper bound of the range of plan fans (not inclusive)
     * @return the range of matching plan fans
     * @throws SystemException if a system exception occurred
     */
    public List<PlanFan> findByPlanId(long planId, int start, int end)
        throws SystemException {
        return findByPlanId(planId, start, end, null);
    }

    /**
     * Returns an ordered range of all the plan fans where planId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param planId the plan ID
     * @param start the lower bound of the range of plan fans
     * @param end the upper bound of the range of plan fans (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching plan fans
     * @throws SystemException if a system exception occurred
     */
    public List<PlanFan> findByPlanId(long planId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID;
            finderArgs = new Object[] { planId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PLANID;
            finderArgs = new Object[] { planId, start, end, orderByComparator };
        }

        List<PlanFan> list = (List<PlanFan>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_PLANFAN_WHERE);

            query.append(_FINDER_COLUMN_PLANID_PLANID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(PlanFanModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planId);

                list = (List<PlanFan>) QueryUtil.list(q, getDialect(), start,
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
     * Returns the first plan fan in the ordered set where planId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan fan
     * @throws com.ext.portlet.NoSuchPlanFanException if a matching plan fan could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanFan findByPlanId_First(long planId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanFanException, SystemException {
        List<PlanFan> list = findByPlanId(planId, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("planId=");
            msg.append(planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanFanException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last plan fan in the ordered set where planId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan fan
     * @throws com.ext.portlet.NoSuchPlanFanException if a matching plan fan could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanFan findByPlanId_Last(long planId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanFanException, SystemException {
        int count = countByPlanId(planId);

        List<PlanFan> list = findByPlanId(planId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("planId=");
            msg.append(planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanFanException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the plan fans before and after the current plan fan in the ordered set where planId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param id the primary key of the current plan fan
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next plan fan
     * @throws com.ext.portlet.NoSuchPlanFanException if a plan fan with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanFan[] findByPlanId_PrevAndNext(long id, long planId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanFanException, SystemException {
        PlanFan planFan = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            PlanFan[] array = new PlanFanImpl[3];

            array[0] = getByPlanId_PrevAndNext(session, planFan, planId,
                    orderByComparator, true);

            array[1] = planFan;

            array[2] = getByPlanId_PrevAndNext(session, planFan, planId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PlanFan getByPlanId_PrevAndNext(Session session, PlanFan planFan,
        long planId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PLANFAN_WHERE);

        query.append(_FINDER_COLUMN_PLANID_PLANID_2);

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
            query.append(PlanFanModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(planId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(planFan);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PlanFan> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the plan fans where userId = &#63;.
     *
     * @param userId the user ID
     * @return the matching plan fans
     * @throws SystemException if a system exception occurred
     */
    public List<PlanFan> findByUserId(long userId) throws SystemException {
        return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan fans where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param start the lower bound of the range of plan fans
     * @param end the upper bound of the range of plan fans (not inclusive)
     * @return the range of matching plan fans
     * @throws SystemException if a system exception occurred
     */
    public List<PlanFan> findByUserId(long userId, int start, int end)
        throws SystemException {
        return findByUserId(userId, start, end, null);
    }

    /**
     * Returns an ordered range of all the plan fans where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param start the lower bound of the range of plan fans
     * @param end the upper bound of the range of plan fans (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching plan fans
     * @throws SystemException if a system exception occurred
     */
    public List<PlanFan> findByUserId(long userId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
            finderArgs = new Object[] { userId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
            finderArgs = new Object[] { userId, start, end, orderByComparator };
        }

        List<PlanFan> list = (List<PlanFan>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_PLANFAN_WHERE);

            query.append(_FINDER_COLUMN_USERID_USERID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(PlanFanModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                list = (List<PlanFan>) QueryUtil.list(q, getDialect(), start,
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
     * Returns the first plan fan in the ordered set where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan fan
     * @throws com.ext.portlet.NoSuchPlanFanException if a matching plan fan could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanFan findByUserId_First(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanFanException, SystemException {
        List<PlanFan> list = findByUserId(userId, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("userId=");
            msg.append(userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanFanException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last plan fan in the ordered set where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan fan
     * @throws com.ext.portlet.NoSuchPlanFanException if a matching plan fan could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanFan findByUserId_Last(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanFanException, SystemException {
        int count = countByUserId(userId);

        List<PlanFan> list = findByUserId(userId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("userId=");
            msg.append(userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanFanException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the plan fans before and after the current plan fan in the ordered set where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param id the primary key of the current plan fan
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next plan fan
     * @throws com.ext.portlet.NoSuchPlanFanException if a plan fan with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanFan[] findByUserId_PrevAndNext(long id, long userId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanFanException, SystemException {
        PlanFan planFan = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            PlanFan[] array = new PlanFanImpl[3];

            array[0] = getByUserId_PrevAndNext(session, planFan, userId,
                    orderByComparator, true);

            array[1] = planFan;

            array[2] = getByUserId_PrevAndNext(session, planFan, userId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PlanFan getByUserId_PrevAndNext(Session session, PlanFan planFan,
        long userId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PLANFAN_WHERE);

        query.append(_FINDER_COLUMN_USERID_USERID_2);

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
            query.append(PlanFanModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(userId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(planFan);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PlanFan> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns the plan fan where planId = &#63; and userId = &#63; or throws a {@link com.ext.portlet.NoSuchPlanFanException} if it could not be found.
     *
     * @param planId the plan ID
     * @param userId the user ID
     * @return the matching plan fan
     * @throws com.ext.portlet.NoSuchPlanFanException if a matching plan fan could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanFan findByPlanIdUserId(long planId, long userId)
        throws NoSuchPlanFanException, SystemException {
        PlanFan planFan = fetchByPlanIdUserId(planId, userId);

        if (planFan == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("planId=");
            msg.append(planId);

            msg.append(", userId=");
            msg.append(userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanFanException(msg.toString());
        }

        return planFan;
    }

    /**
     * Returns the plan fan where planId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param planId the plan ID
     * @param userId the user ID
     * @return the matching plan fan, or <code>null</code> if a matching plan fan could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanFan fetchByPlanIdUserId(long planId, long userId)
        throws SystemException {
        return fetchByPlanIdUserId(planId, userId, true);
    }

    /**
     * Returns the plan fan where planId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param planId the plan ID
     * @param userId the user ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching plan fan, or <code>null</code> if a matching plan fan could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanFan fetchByPlanIdUserId(long planId, long userId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { planId, userId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PLANIDUSERID,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_SELECT_PLANFAN_WHERE);

            query.append(_FINDER_COLUMN_PLANIDUSERID_PLANID_2);

            query.append(_FINDER_COLUMN_PLANIDUSERID_USERID_2);

            query.append(PlanFanModelImpl.ORDER_BY_JPQL);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planId);

                qPos.add(userId);

                List<PlanFan> list = q.list();

                result = list;

                PlanFan planFan = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANIDUSERID,
                        finderArgs, list);
                } else {
                    planFan = list.get(0);

                    cacheResult(planFan);

                    if ((planFan.getPlanId() != planId) ||
                            (planFan.getUserId() != userId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANIDUSERID,
                            finderArgs, planFan);
                    }
                }

                return planFan;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANIDUSERID,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (PlanFan) result;
            }
        }
    }

    /**
     * Returns all the plan fans.
     *
     * @return the plan fans
     * @throws SystemException if a system exception occurred
     */
    public List<PlanFan> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan fans.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plan fans
     * @param end the upper bound of the range of plan fans (not inclusive)
     * @return the range of plan fans
     * @throws SystemException if a system exception occurred
     */
    public List<PlanFan> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan fans.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plan fans
     * @param end the upper bound of the range of plan fans (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan fans
     * @throws SystemException if a system exception occurred
     */
    public List<PlanFan> findAll(int start, int end,
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

        List<PlanFan> list = (List<PlanFan>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANFAN);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANFAN.concat(PlanFanModelImpl.ORDER_BY_JPQL);
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<PlanFan>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanFan>) QueryUtil.list(q, getDialect(),
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
     * Removes all the plan fans where planId = &#63; from the database.
     *
     * @param planId the plan ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByPlanId(long planId) throws SystemException {
        for (PlanFan planFan : findByPlanId(planId)) {
            remove(planFan);
        }
    }

    /**
     * Removes all the plan fans where userId = &#63; from the database.
     *
     * @param userId the user ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByUserId(long userId) throws SystemException {
        for (PlanFan planFan : findByUserId(userId)) {
            remove(planFan);
        }
    }

    /**
     * Removes the plan fan where planId = &#63; and userId = &#63; from the database.
     *
     * @param planId the plan ID
     * @param userId the user ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByPlanIdUserId(long planId, long userId)
        throws NoSuchPlanFanException, SystemException {
        PlanFan planFan = findByPlanIdUserId(planId, userId);

        remove(planFan);
    }

    /**
     * Removes all the plan fans from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (PlanFan planFan : findAll()) {
            remove(planFan);
        }
    }

    /**
     * Returns the number of plan fans where planId = &#63;.
     *
     * @param planId the plan ID
     * @return the number of matching plan fans
     * @throws SystemException if a system exception occurred
     */
    public int countByPlanId(long planId) throws SystemException {
        Object[] finderArgs = new Object[] { planId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PLANID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PLANFAN_WHERE);

            query.append(_FINDER_COLUMN_PLANID_PLANID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planId);

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
     * Returns the number of plan fans where userId = &#63;.
     *
     * @param userId the user ID
     * @return the number of matching plan fans
     * @throws SystemException if a system exception occurred
     */
    public int countByUserId(long userId) throws SystemException {
        Object[] finderArgs = new Object[] { userId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PLANFAN_WHERE);

            query.append(_FINDER_COLUMN_USERID_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of plan fans where planId = &#63; and userId = &#63;.
     *
     * @param planId the plan ID
     * @param userId the user ID
     * @return the number of matching plan fans
     * @throws SystemException if a system exception occurred
     */
    public int countByPlanIdUserId(long planId, long userId)
        throws SystemException {
        Object[] finderArgs = new Object[] { planId, userId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PLANIDUSERID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PLANFAN_WHERE);

            query.append(_FINDER_COLUMN_PLANIDUSERID_PLANID_2);

            query.append(_FINDER_COLUMN_PLANIDUSERID_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planId);

                qPos.add(userId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLANIDUSERID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of plan fans.
     *
     * @return the number of plan fans
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_PLANFAN);

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
     * Initializes the plan fan persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.PlanFan")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanFan>> listenersList = new ArrayList<ModelListener<PlanFan>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanFan>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanFanImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
