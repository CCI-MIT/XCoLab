package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPlanTeamHistoryException;
import com.ext.portlet.model.PlanTeamHistory;
import com.ext.portlet.model.impl.PlanTeamHistoryImpl;
import com.ext.portlet.model.impl.PlanTeamHistoryModelImpl;
import com.ext.portlet.service.persistence.ActivitySubscriptionPersistence;
import com.ext.portlet.service.persistence.BalloonStatsEntryPersistence;
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
import com.ext.portlet.service.persistence.EmailListPersistence;
import com.ext.portlet.service.persistence.FocusAreaOntologyTermPersistence;
import com.ext.portlet.service.persistence.FocusAreaPersistence;
import com.ext.portlet.service.persistence.LandingPagePersistence;
import com.ext.portlet.service.persistence.MessagePersistence;
import com.ext.portlet.service.persistence.MessageRecipientStatusPersistence;
import com.ext.portlet.service.persistence.MessagingIgnoredRecipientsPersistence;
import com.ext.portlet.service.persistence.MessagingMessageConversionPersistence;
import com.ext.portlet.service.persistence.MessagingMessageConversionTypePersistence;
import com.ext.portlet.service.persistence.MessagingMessagePersistence;
import com.ext.portlet.service.persistence.MessagingMessageRecipientPersistence;
import com.ext.portlet.service.persistence.MessagingRedirectLinkPersistence;
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
import com.ext.portlet.service.persistence.PlanItemGroupPersistence;
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
import com.ext.portlet.service.persistence.Proposal2PhasePersistence;
import com.ext.portlet.service.persistence.ProposalAttributePersistence;
import com.ext.portlet.service.persistence.ProposalAttributeTypePersistence;
import com.ext.portlet.service.persistence.ProposalPersistence;
import com.ext.portlet.service.persistence.ProposalVersionPersistence;

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
 * The persistence implementation for the plan team history service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTeamHistoryPersistence
 * @see PlanTeamHistoryUtil
 * @generated
 */
public class PlanTeamHistoryPersistenceImpl extends BasePersistenceImpl<PlanTeamHistory>
    implements PlanTeamHistoryPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanTeamHistoryUtil} to access the plan team history persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanTeamHistoryImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PLANID = new FinderPath(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
            PlanTeamHistoryModelImpl.FINDER_CACHE_ENABLED,
            PlanTeamHistoryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByPlanId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID =
        new FinderPath(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
            PlanTeamHistoryModelImpl.FINDER_CACHE_ENABLED,
            PlanTeamHistoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPlanId",
            new String[] { Long.class.getName() },
            PlanTeamHistoryModelImpl.PLANID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANID = new FinderPath(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
            PlanTeamHistoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPlanId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_LASTUSERACTIONINPLAN = new FinderPath(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
            PlanTeamHistoryModelImpl.FINDER_CACHE_ENABLED,
            PlanTeamHistoryImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByLastUserActionInPlan",
            new String[] { Long.class.getName(), Long.class.getName() },
            PlanTeamHistoryModelImpl.PLANID_COLUMN_BITMASK |
            PlanTeamHistoryModelImpl.USERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_LASTUSERACTIONINPLAN = new FinderPath(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
            PlanTeamHistoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByLastUserActionInPlan",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
            PlanTeamHistoryModelImpl.FINDER_CACHE_ENABLED,
            PlanTeamHistoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
            PlanTeamHistoryModelImpl.FINDER_CACHE_ENABLED,
            PlanTeamHistoryImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
            PlanTeamHistoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PLANTEAMHISTORY = "SELECT planTeamHistory FROM PlanTeamHistory planTeamHistory";
    private static final String _SQL_SELECT_PLANTEAMHISTORY_WHERE = "SELECT planTeamHistory FROM PlanTeamHistory planTeamHistory WHERE ";
    private static final String _SQL_COUNT_PLANTEAMHISTORY = "SELECT COUNT(planTeamHistory) FROM PlanTeamHistory planTeamHistory";
    private static final String _SQL_COUNT_PLANTEAMHISTORY_WHERE = "SELECT COUNT(planTeamHistory) FROM PlanTeamHistory planTeamHistory WHERE ";
    private static final String _FINDER_COLUMN_PLANID_PLANID_2 = "planTeamHistory.planId = ?";
    private static final String _FINDER_COLUMN_LASTUSERACTIONINPLAN_PLANID_2 = "planTeamHistory.planId = ? AND ";
    private static final String _FINDER_COLUMN_LASTUSERACTIONINPLAN_USERID_2 = "planTeamHistory.userId = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planTeamHistory.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanTeamHistory exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PlanTeamHistory exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanTeamHistoryPersistenceImpl.class);
    private static PlanTeamHistory _nullPlanTeamHistory = new PlanTeamHistoryImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanTeamHistory> toCacheModel() {
                return _nullPlanTeamHistoryCacheModel;
            }
        };

    private static CacheModel<PlanTeamHistory> _nullPlanTeamHistoryCacheModel = new CacheModel<PlanTeamHistory>() {
            public PlanTeamHistory toEntityModel() {
                return _nullPlanTeamHistory;
            }
        };

    @BeanReference(type = ActivitySubscriptionPersistence.class)
    protected ActivitySubscriptionPersistence activitySubscriptionPersistence;
    @BeanReference(type = BalloonStatsEntryPersistence.class)
    protected BalloonStatsEntryPersistence balloonStatsEntryPersistence;
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
    @BeanReference(type = EmailListPersistence.class)
    protected EmailListPersistence emailListPersistence;
    @BeanReference(type = FocusAreaPersistence.class)
    protected FocusAreaPersistence focusAreaPersistence;
    @BeanReference(type = FocusAreaOntologyTermPersistence.class)
    protected FocusAreaOntologyTermPersistence focusAreaOntologyTermPersistence;
    @BeanReference(type = LandingPagePersistence.class)
    protected LandingPagePersistence landingPagePersistence;
    @BeanReference(type = MessagePersistence.class)
    protected MessagePersistence messagePersistence;
    @BeanReference(type = MessageRecipientStatusPersistence.class)
    protected MessageRecipientStatusPersistence messageRecipientStatusPersistence;
    @BeanReference(type = MessagingIgnoredRecipientsPersistence.class)
    protected MessagingIgnoredRecipientsPersistence messagingIgnoredRecipientsPersistence;
    @BeanReference(type = MessagingMessagePersistence.class)
    protected MessagingMessagePersistence messagingMessagePersistence;
    @BeanReference(type = MessagingMessageConversionPersistence.class)
    protected MessagingMessageConversionPersistence messagingMessageConversionPersistence;
    @BeanReference(type = MessagingMessageConversionTypePersistence.class)
    protected MessagingMessageConversionTypePersistence messagingMessageConversionTypePersistence;
    @BeanReference(type = MessagingMessageRecipientPersistence.class)
    protected MessagingMessageRecipientPersistence messagingMessageRecipientPersistence;
    @BeanReference(type = MessagingRedirectLinkPersistence.class)
    protected MessagingRedirectLinkPersistence messagingRedirectLinkPersistence;
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
    @BeanReference(type = PlanItemGroupPersistence.class)
    protected PlanItemGroupPersistence planItemGroupPersistence;
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
    @BeanReference(type = ProposalPersistence.class)
    protected ProposalPersistence proposalPersistence;
    @BeanReference(type = Proposal2PhasePersistence.class)
    protected Proposal2PhasePersistence proposal2PhasePersistence;
    @BeanReference(type = ProposalAttributePersistence.class)
    protected ProposalAttributePersistence proposalAttributePersistence;
    @BeanReference(type = ProposalAttributeTypePersistence.class)
    protected ProposalAttributeTypePersistence proposalAttributeTypePersistence;
    @BeanReference(type = ProposalVersionPersistence.class)
    protected ProposalVersionPersistence proposalVersionPersistence;
    @BeanReference(type = ResourcePersistence.class)
    protected ResourcePersistence resourcePersistence;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;

    /**
     * Caches the plan team history in the entity cache if it is enabled.
     *
     * @param planTeamHistory the plan team history
     */
    public void cacheResult(PlanTeamHistory planTeamHistory) {
        EntityCacheUtil.putResult(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
            PlanTeamHistoryImpl.class, planTeamHistory.getPrimaryKey(),
            planTeamHistory);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_LASTUSERACTIONINPLAN,
            new Object[] {
                Long.valueOf(planTeamHistory.getPlanId()),
                Long.valueOf(planTeamHistory.getUserId())
            }, planTeamHistory);

        planTeamHistory.resetOriginalValues();
    }

    /**
     * Caches the plan team histories in the entity cache if it is enabled.
     *
     * @param planTeamHistories the plan team histories
     */
    public void cacheResult(List<PlanTeamHistory> planTeamHistories) {
        for (PlanTeamHistory planTeamHistory : planTeamHistories) {
            if (EntityCacheUtil.getResult(
                        PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
                        PlanTeamHistoryImpl.class,
                        planTeamHistory.getPrimaryKey()) == null) {
                cacheResult(planTeamHistory);
            } else {
                planTeamHistory.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan team histories.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanTeamHistoryImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanTeamHistoryImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan team history.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanTeamHistory planTeamHistory) {
        EntityCacheUtil.removeResult(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
            PlanTeamHistoryImpl.class, planTeamHistory.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(planTeamHistory);
    }

    @Override
    public void clearCache(List<PlanTeamHistory> planTeamHistories) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanTeamHistory planTeamHistory : planTeamHistories) {
            EntityCacheUtil.removeResult(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
                PlanTeamHistoryImpl.class, planTeamHistory.getPrimaryKey());

            clearUniqueFindersCache(planTeamHistory);
        }
    }

    protected void clearUniqueFindersCache(PlanTeamHistory planTeamHistory) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_LASTUSERACTIONINPLAN,
            new Object[] {
                Long.valueOf(planTeamHistory.getPlanId()),
                Long.valueOf(planTeamHistory.getUserId())
            });
    }

    /**
     * Creates a new plan team history with the primary key. Does not add the plan team history to the database.
     *
     * @param id the primary key for the new plan team history
     * @return the new plan team history
     */
    public PlanTeamHistory create(long id) {
        PlanTeamHistory planTeamHistory = new PlanTeamHistoryImpl();

        planTeamHistory.setNew(true);
        planTeamHistory.setPrimaryKey(id);

        return planTeamHistory;
    }

    /**
     * Removes the plan team history with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the plan team history
     * @return the plan team history that was removed
     * @throws com.ext.portlet.NoSuchPlanTeamHistoryException if a plan team history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTeamHistory remove(long id)
        throws NoSuchPlanTeamHistoryException, SystemException {
        return remove(Long.valueOf(id));
    }

    /**
     * Removes the plan team history with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan team history
     * @return the plan team history that was removed
     * @throws com.ext.portlet.NoSuchPlanTeamHistoryException if a plan team history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTeamHistory remove(Serializable primaryKey)
        throws NoSuchPlanTeamHistoryException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanTeamHistory planTeamHistory = (PlanTeamHistory) session.get(PlanTeamHistoryImpl.class,
                    primaryKey);

            if (planTeamHistory == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanTeamHistoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planTeamHistory);
        } catch (NoSuchPlanTeamHistoryException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanTeamHistory removeImpl(PlanTeamHistory planTeamHistory)
        throws SystemException {
        planTeamHistory = toUnwrappedModel(planTeamHistory);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, planTeamHistory);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(planTeamHistory);

        return planTeamHistory;
    }

    @Override
    public PlanTeamHistory updateImpl(
        com.ext.portlet.model.PlanTeamHistory planTeamHistory, boolean merge)
        throws SystemException {
        planTeamHistory = toUnwrappedModel(planTeamHistory);

        boolean isNew = planTeamHistory.isNew();

        PlanTeamHistoryModelImpl planTeamHistoryModelImpl = (PlanTeamHistoryModelImpl) planTeamHistory;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planTeamHistory, merge);

            planTeamHistory.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PlanTeamHistoryModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((planTeamHistoryModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(planTeamHistoryModelImpl.getOriginalPlanId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID,
                    args);

                args = new Object[] {
                        Long.valueOf(planTeamHistoryModelImpl.getPlanId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID,
                    args);
            }
        }

        EntityCacheUtil.putResult(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
            PlanTeamHistoryImpl.class, planTeamHistory.getPrimaryKey(),
            planTeamHistory);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_LASTUSERACTIONINPLAN,
                new Object[] {
                    Long.valueOf(planTeamHistory.getPlanId()),
                    Long.valueOf(planTeamHistory.getUserId())
                }, planTeamHistory);
        } else {
            if ((planTeamHistoryModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_LASTUSERACTIONINPLAN.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(planTeamHistoryModelImpl.getOriginalPlanId()),
                        Long.valueOf(planTeamHistoryModelImpl.getOriginalUserId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LASTUSERACTIONINPLAN,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_LASTUSERACTIONINPLAN,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_LASTUSERACTIONINPLAN,
                    new Object[] {
                        Long.valueOf(planTeamHistory.getPlanId()),
                        Long.valueOf(planTeamHistory.getUserId())
                    }, planTeamHistory);
            }
        }

        return planTeamHistory;
    }

    protected PlanTeamHistory toUnwrappedModel(PlanTeamHistory planTeamHistory) {
        if (planTeamHistory instanceof PlanTeamHistoryImpl) {
            return planTeamHistory;
        }

        PlanTeamHistoryImpl planTeamHistoryImpl = new PlanTeamHistoryImpl();

        planTeamHistoryImpl.setNew(planTeamHistory.isNew());
        planTeamHistoryImpl.setPrimaryKey(planTeamHistory.getPrimaryKey());

        planTeamHistoryImpl.setId(planTeamHistory.getId());
        planTeamHistoryImpl.setPlanId(planTeamHistory.getPlanId());
        planTeamHistoryImpl.setUserId(planTeamHistory.getUserId());
        planTeamHistoryImpl.setAction(planTeamHistory.getAction());
        planTeamHistoryImpl.setPayload(planTeamHistory.getPayload());
        planTeamHistoryImpl.setCreated(planTeamHistory.getCreated());
        planTeamHistoryImpl.setUpdateAuthorId(planTeamHistory.getUpdateAuthorId());

        return planTeamHistoryImpl;
    }

    /**
     * Returns the plan team history with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan team history
     * @return the plan team history
     * @throws com.liferay.portal.NoSuchModelException if a plan team history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTeamHistory findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the plan team history with the primary key or throws a {@link com.ext.portlet.NoSuchPlanTeamHistoryException} if it could not be found.
     *
     * @param id the primary key of the plan team history
     * @return the plan team history
     * @throws com.ext.portlet.NoSuchPlanTeamHistoryException if a plan team history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTeamHistory findByPrimaryKey(long id)
        throws NoSuchPlanTeamHistoryException, SystemException {
        PlanTeamHistory planTeamHistory = fetchByPrimaryKey(id);

        if (planTeamHistory == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
            }

            throw new NoSuchPlanTeamHistoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                id);
        }

        return planTeamHistory;
    }

    /**
     * Returns the plan team history with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan team history
     * @return the plan team history, or <code>null</code> if a plan team history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTeamHistory fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the plan team history with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the plan team history
     * @return the plan team history, or <code>null</code> if a plan team history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTeamHistory fetchByPrimaryKey(long id) throws SystemException {
        PlanTeamHistory planTeamHistory = (PlanTeamHistory) EntityCacheUtil.getResult(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
                PlanTeamHistoryImpl.class, id);

        if (planTeamHistory == _nullPlanTeamHistory) {
            return null;
        }

        if (planTeamHistory == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                planTeamHistory = (PlanTeamHistory) session.get(PlanTeamHistoryImpl.class,
                        Long.valueOf(id));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (planTeamHistory != null) {
                    cacheResult(planTeamHistory);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(PlanTeamHistoryModelImpl.ENTITY_CACHE_ENABLED,
                        PlanTeamHistoryImpl.class, id, _nullPlanTeamHistory);
                }

                closeSession(session);
            }
        }

        return planTeamHistory;
    }

    /**
     * Returns all the plan team histories where planId = &#63;.
     *
     * @param planId the plan ID
     * @return the matching plan team histories
     * @throws SystemException if a system exception occurred
     */
    public List<PlanTeamHistory> findByPlanId(long planId)
        throws SystemException {
        return findByPlanId(planId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan team histories where planId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param planId the plan ID
     * @param start the lower bound of the range of plan team histories
     * @param end the upper bound of the range of plan team histories (not inclusive)
     * @return the range of matching plan team histories
     * @throws SystemException if a system exception occurred
     */
    public List<PlanTeamHistory> findByPlanId(long planId, int start, int end)
        throws SystemException {
        return findByPlanId(planId, start, end, null);
    }

    /**
     * Returns an ordered range of all the plan team histories where planId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param planId the plan ID
     * @param start the lower bound of the range of plan team histories
     * @param end the upper bound of the range of plan team histories (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching plan team histories
     * @throws SystemException if a system exception occurred
     */
    public List<PlanTeamHistory> findByPlanId(long planId, int start, int end,
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

        List<PlanTeamHistory> list = (List<PlanTeamHistory>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_PLANTEAMHISTORY_WHERE);

            query.append(_FINDER_COLUMN_PLANID_PLANID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(PlanTeamHistoryModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planId);

                list = (List<PlanTeamHistory>) QueryUtil.list(q, getDialect(),
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
     * Returns the first plan team history in the ordered set where planId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan team history
     * @throws com.ext.portlet.NoSuchPlanTeamHistoryException if a matching plan team history could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTeamHistory findByPlanId_First(long planId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanTeamHistoryException, SystemException {
        List<PlanTeamHistory> list = findByPlanId(planId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("planId=");
            msg.append(planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanTeamHistoryException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last plan team history in the ordered set where planId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan team history
     * @throws com.ext.portlet.NoSuchPlanTeamHistoryException if a matching plan team history could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTeamHistory findByPlanId_Last(long planId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanTeamHistoryException, SystemException {
        int count = countByPlanId(planId);

        List<PlanTeamHistory> list = findByPlanId(planId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("planId=");
            msg.append(planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanTeamHistoryException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the plan team histories before and after the current plan team history in the ordered set where planId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param id the primary key of the current plan team history
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next plan team history
     * @throws com.ext.portlet.NoSuchPlanTeamHistoryException if a plan team history with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTeamHistory[] findByPlanId_PrevAndNext(long id, long planId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanTeamHistoryException, SystemException {
        PlanTeamHistory planTeamHistory = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            PlanTeamHistory[] array = new PlanTeamHistoryImpl[3];

            array[0] = getByPlanId_PrevAndNext(session, planTeamHistory,
                    planId, orderByComparator, true);

            array[1] = planTeamHistory;

            array[2] = getByPlanId_PrevAndNext(session, planTeamHistory,
                    planId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PlanTeamHistory getByPlanId_PrevAndNext(Session session,
        PlanTeamHistory planTeamHistory, long planId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PLANTEAMHISTORY_WHERE);

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
            query.append(PlanTeamHistoryModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(planId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(planTeamHistory);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PlanTeamHistory> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns the plan team history where planId = &#63; and userId = &#63; or throws a {@link com.ext.portlet.NoSuchPlanTeamHistoryException} if it could not be found.
     *
     * @param planId the plan ID
     * @param userId the user ID
     * @return the matching plan team history
     * @throws com.ext.portlet.NoSuchPlanTeamHistoryException if a matching plan team history could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTeamHistory findByLastUserActionInPlan(long planId, long userId)
        throws NoSuchPlanTeamHistoryException, SystemException {
        PlanTeamHistory planTeamHistory = fetchByLastUserActionInPlan(planId,
                userId);

        if (planTeamHistory == null) {
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

            throw new NoSuchPlanTeamHistoryException(msg.toString());
        }

        return planTeamHistory;
    }

    /**
     * Returns the plan team history where planId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param planId the plan ID
     * @param userId the user ID
     * @return the matching plan team history, or <code>null</code> if a matching plan team history could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTeamHistory fetchByLastUserActionInPlan(long planId, long userId)
        throws SystemException {
        return fetchByLastUserActionInPlan(planId, userId, true);
    }

    /**
     * Returns the plan team history where planId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param planId the plan ID
     * @param userId the user ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching plan team history, or <code>null</code> if a matching plan team history could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTeamHistory fetchByLastUserActionInPlan(long planId,
        long userId, boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { planId, userId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_LASTUSERACTIONINPLAN,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_SELECT_PLANTEAMHISTORY_WHERE);

            query.append(_FINDER_COLUMN_LASTUSERACTIONINPLAN_PLANID_2);

            query.append(_FINDER_COLUMN_LASTUSERACTIONINPLAN_USERID_2);

            query.append(PlanTeamHistoryModelImpl.ORDER_BY_JPQL);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planId);

                qPos.add(userId);

                List<PlanTeamHistory> list = q.list();

                result = list;

                PlanTeamHistory planTeamHistory = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_LASTUSERACTIONINPLAN,
                        finderArgs, list);
                } else {
                    planTeamHistory = list.get(0);

                    cacheResult(planTeamHistory);

                    if ((planTeamHistory.getPlanId() != planId) ||
                            (planTeamHistory.getUserId() != userId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_LASTUSERACTIONINPLAN,
                            finderArgs, planTeamHistory);
                    }
                }

                return planTeamHistory;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_LASTUSERACTIONINPLAN,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (PlanTeamHistory) result;
            }
        }
    }

    /**
     * Returns all the plan team histories.
     *
     * @return the plan team histories
     * @throws SystemException if a system exception occurred
     */
    public List<PlanTeamHistory> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan team histories.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plan team histories
     * @param end the upper bound of the range of plan team histories (not inclusive)
     * @return the range of plan team histories
     * @throws SystemException if a system exception occurred
     */
    public List<PlanTeamHistory> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan team histories.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plan team histories
     * @param end the upper bound of the range of plan team histories (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan team histories
     * @throws SystemException if a system exception occurred
     */
    public List<PlanTeamHistory> findAll(int start, int end,
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

        List<PlanTeamHistory> list = (List<PlanTeamHistory>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANTEAMHISTORY);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANTEAMHISTORY.concat(PlanTeamHistoryModelImpl.ORDER_BY_JPQL);
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<PlanTeamHistory>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanTeamHistory>) QueryUtil.list(q,
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
     * Removes all the plan team histories where planId = &#63; from the database.
     *
     * @param planId the plan ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByPlanId(long planId) throws SystemException {
        for (PlanTeamHistory planTeamHistory : findByPlanId(planId)) {
            remove(planTeamHistory);
        }
    }

    /**
     * Removes the plan team history where planId = &#63; and userId = &#63; from the database.
     *
     * @param planId the plan ID
     * @param userId the user ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByLastUserActionInPlan(long planId, long userId)
        throws NoSuchPlanTeamHistoryException, SystemException {
        PlanTeamHistory planTeamHistory = findByLastUserActionInPlan(planId,
                userId);

        remove(planTeamHistory);
    }

    /**
     * Removes all the plan team histories from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (PlanTeamHistory planTeamHistory : findAll()) {
            remove(planTeamHistory);
        }
    }

    /**
     * Returns the number of plan team histories where planId = &#63;.
     *
     * @param planId the plan ID
     * @return the number of matching plan team histories
     * @throws SystemException if a system exception occurred
     */
    public int countByPlanId(long planId) throws SystemException {
        Object[] finderArgs = new Object[] { planId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PLANID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PLANTEAMHISTORY_WHERE);

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
     * Returns the number of plan team histories where planId = &#63; and userId = &#63;.
     *
     * @param planId the plan ID
     * @param userId the user ID
     * @return the number of matching plan team histories
     * @throws SystemException if a system exception occurred
     */
    public int countByLastUserActionInPlan(long planId, long userId)
        throws SystemException {
        Object[] finderArgs = new Object[] { planId, userId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_LASTUSERACTIONINPLAN,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PLANTEAMHISTORY_WHERE);

            query.append(_FINDER_COLUMN_LASTUSERACTIONINPLAN_PLANID_2);

            query.append(_FINDER_COLUMN_LASTUSERACTIONINPLAN_USERID_2);

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

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_LASTUSERACTIONINPLAN,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of plan team histories.
     *
     * @return the number of plan team histories
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_PLANTEAMHISTORY);

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
     * Initializes the plan team history persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.PlanTeamHistory")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanTeamHistory>> listenersList = new ArrayList<ModelListener<PlanTeamHistory>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanTeamHistory>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanTeamHistoryImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
