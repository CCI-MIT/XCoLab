package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPlanVoteException;
import com.ext.portlet.model.PlanVote;
import com.ext.portlet.model.impl.PlanVoteImpl;
import com.ext.portlet.model.impl.PlanVoteModelImpl;
<<<<<<< HEAD
import com.ext.portlet.service.persistence.ActivitySubscriptionPersistence;
import com.ext.portlet.service.persistence.AnalyticsUserEventPersistence;
import com.ext.portlet.service.persistence.BalloonStatsEntryPersistence;
import com.ext.portlet.service.persistence.ContestDebatePersistence;
import com.ext.portlet.service.persistence.ContestPersistence;
import com.ext.portlet.service.persistence.ContestPhaseColumnPersistence;
import com.ext.portlet.service.persistence.ContestPhasePersistence;
import com.ext.portlet.service.persistence.ContestPhaseRibbonTypePersistence;
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
import com.ext.portlet.service.persistence.Plan2ProposalPersistence;
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
=======
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)
import com.ext.portlet.service.persistence.PlanVotePersistence;

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
 * The persistence implementation for the plan vote service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanVotePersistence
 * @see PlanVoteUtil
 * @generated
 */
public class PlanVotePersistenceImpl extends BasePersistenceImpl<PlanVote>
    implements PlanVotePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanVoteUtil} to access the plan vote persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanVoteImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteModelImpl.FINDER_CACHE_ENABLED, PlanVoteImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteModelImpl.FINDER_CACHE_ENABLED, PlanVoteImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTID =
        new FinderPath(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteModelImpl.FINDER_CACHE_ENABLED, PlanVoteImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findBycontestId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID =
        new FinderPath(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteModelImpl.FINDER_CACHE_ENABLED, PlanVoteImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findBycontestId",
            new String[] { Long.class.getName() },
            PlanVoteModelImpl.CONTESTID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTID = new FinderPath(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBycontestId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_CONTESTID_CONTESTID_2 = "planVote.id.contestId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PLANID = new FinderPath(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteModelImpl.FINDER_CACHE_ENABLED, PlanVoteImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPlanId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID =
        new FinderPath(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteModelImpl.FINDER_CACHE_ENABLED, PlanVoteImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPlanId",
            new String[] { Long.class.getName() },
            PlanVoteModelImpl.PLANID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANID = new FinderPath(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPlanId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_PLANID_PLANID_2 = "planVote.planId = ?";
    public static final FinderPath FINDER_PATH_FETCH_BY_CONTESTIDUSERID = new FinderPath(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteModelImpl.FINDER_CACHE_ENABLED, PlanVoteImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchByContestIdUserId",
            new String[] { Long.class.getName(), Long.class.getName() },
            PlanVoteModelImpl.CONTESTID_COLUMN_BITMASK |
            PlanVoteModelImpl.USERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTIDUSERID = new FinderPath(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByContestIdUserId",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _FINDER_COLUMN_CONTESTIDUSERID_CONTESTID_2 = "planVote.id.contestId = ? AND ";
    private static final String _FINDER_COLUMN_CONTESTIDUSERID_USERID_2 = "planVote.id.userId = ?";
    private static final String _SQL_SELECT_PLANVOTE = "SELECT planVote FROM PlanVote planVote";
    private static final String _SQL_SELECT_PLANVOTE_WHERE = "SELECT planVote FROM PlanVote planVote WHERE ";
    private static final String _SQL_COUNT_PLANVOTE = "SELECT COUNT(planVote) FROM PlanVote planVote";
    private static final String _SQL_COUNT_PLANVOTE_WHERE = "SELECT COUNT(planVote) FROM PlanVote planVote WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planVote.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanVote exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PlanVote exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanVotePersistenceImpl.class);
    private static PlanVote _nullPlanVote = new PlanVoteImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanVote> toCacheModel() {
                return _nullPlanVoteCacheModel;
            }
        };

    private static CacheModel<PlanVote> _nullPlanVoteCacheModel = new CacheModel<PlanVote>() {
            @Override
            public PlanVote toEntityModel() {
                return _nullPlanVote;
            }
        };

<<<<<<< HEAD
    @BeanReference(type = ActivitySubscriptionPersistence.class)
    protected ActivitySubscriptionPersistence activitySubscriptionPersistence;
    @BeanReference(type = AnalyticsUserEventPersistence.class)
    protected AnalyticsUserEventPersistence analyticsUserEventPersistence;
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
    @BeanReference(type = ContestPhaseRibbonTypePersistence.class)
    protected ContestPhaseRibbonTypePersistence contestPhaseRibbonTypePersistence;
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
    @BeanReference(type = Plan2ProposalPersistence.class)
    protected Plan2ProposalPersistence plan2ProposalPersistence;
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
    @BeanReference(type = ProposalContestPhaseAttributePersistence.class)
    protected ProposalContestPhaseAttributePersistence proposalContestPhaseAttributePersistence;
    @BeanReference(type = ProposalContestPhaseAttributeTypePersistence.class)
    protected ProposalContestPhaseAttributeTypePersistence proposalContestPhaseAttributeTypePersistence;
    @BeanReference(type = ProposalSupporterPersistence.class)
    protected ProposalSupporterPersistence proposalSupporterPersistence;
    @BeanReference(type = ProposalVersionPersistence.class)
    protected ProposalVersionPersistence proposalVersionPersistence;
    @BeanReference(type = ProposalVotePersistence.class)
    protected ProposalVotePersistence proposalVotePersistence;
    @BeanReference(type = ResourcePersistence.class)
    protected ResourcePersistence resourcePersistence;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;

    /**
     * Caches the plan vote in the entity cache if it is enabled.
     *
     * @param planVote the plan vote
     */
    public void cacheResult(PlanVote planVote) {
        EntityCacheUtil.putResult(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteImpl.class, planVote.getPrimaryKey(), planVote);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDUSERID,
            new Object[] {
                Long.valueOf(planVote.getContestId()),
                Long.valueOf(planVote.getUserId())
            }, planVote);

        planVote.resetOriginalValues();
=======
    public PlanVotePersistenceImpl() {
        setModelClass(PlanVote.class);
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)
    }

    /**
     * Returns all the plan votes where contestId = &#63;.
     *
     * @param contestId the contest ID
     * @return the matching plan votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanVote> findBycontestId(long contestId)
        throws SystemException {
        return findBycontestId(contestId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the plan votes where contestId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestId the contest ID
     * @param start the lower bound of the range of plan votes
     * @param end the upper bound of the range of plan votes (not inclusive)
     * @return the range of matching plan votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanVote> findBycontestId(long contestId, int start, int end)
        throws SystemException {
        return findBycontestId(contestId, start, end, null);
    }

    /**
     * Returns an ordered range of all the plan votes where contestId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param contestId the contest ID
     * @param start the lower bound of the range of plan votes
     * @param end the upper bound of the range of plan votes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching plan votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanVote> findBycontestId(long contestId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID;
            finderArgs = new Object[] { contestId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTID;
            finderArgs = new Object[] { contestId, start, end, orderByComparator };
        }

        List<PlanVote> list = (List<PlanVote>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (PlanVote planVote : list) {
                if ((contestId != planVote.getContestId())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_PLANVOTE_WHERE);

            query.append(_FINDER_COLUMN_CONTESTID_CONTESTID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(PlanVoteModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestId);

                if (!pagination) {
                    list = (List<PlanVote>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PlanVote>(list);
                } else {
                    list = (List<PlanVote>) QueryUtil.list(q, getDialect(),
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
     * Returns the first plan vote in the ordered set where contestId = &#63;.
     *
     * @param contestId the contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan vote
     * @throws com.ext.portlet.NoSuchPlanVoteException if a matching plan vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanVote findBycontestId_First(long contestId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanVoteException, SystemException {
        PlanVote planVote = fetchBycontestId_First(contestId, orderByComparator);

        if (planVote != null) {
            return planVote;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestId=");
        msg.append(contestId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPlanVoteException(msg.toString());
    }

    /**
     * Returns the first plan vote in the ordered set where contestId = &#63;.
     *
     * @param contestId the contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan vote, or <code>null</code> if a matching plan vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanVote fetchBycontestId_First(long contestId,
        OrderByComparator orderByComparator) throws SystemException {
        List<PlanVote> list = findBycontestId(contestId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last plan vote in the ordered set where contestId = &#63;.
     *
     * @param contestId the contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan vote
     * @throws com.ext.portlet.NoSuchPlanVoteException if a matching plan vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanVote findBycontestId_Last(long contestId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanVoteException, SystemException {
        PlanVote planVote = fetchBycontestId_Last(contestId, orderByComparator);

        if (planVote != null) {
            return planVote;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("contestId=");
        msg.append(contestId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPlanVoteException(msg.toString());
    }

    /**
     * Returns the last plan vote in the ordered set where contestId = &#63;.
     *
     * @param contestId the contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan vote, or <code>null</code> if a matching plan vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanVote fetchBycontestId_Last(long contestId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countBycontestId(contestId);

        if (count == 0) {
            return null;
        }

        List<PlanVote> list = findBycontestId(contestId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the plan votes before and after the current plan vote in the ordered set where contestId = &#63;.
     *
     * @param planVotePK the primary key of the current plan vote
     * @param contestId the contest ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next plan vote
     * @throws com.ext.portlet.NoSuchPlanVoteException if a plan vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanVote[] findBycontestId_PrevAndNext(PlanVotePK planVotePK,
        long contestId, OrderByComparator orderByComparator)
        throws NoSuchPlanVoteException, SystemException {
        PlanVote planVote = findByPrimaryKey(planVotePK);

        Session session = null;

        try {
            session = openSession();

            PlanVote[] array = new PlanVoteImpl[3];

            array[0] = getBycontestId_PrevAndNext(session, planVote, contestId,
                    orderByComparator, true);

            array[1] = planVote;

            array[2] = getBycontestId_PrevAndNext(session, planVote, contestId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PlanVote getBycontestId_PrevAndNext(Session session,
        PlanVote planVote, long contestId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PLANVOTE_WHERE);

        query.append(_FINDER_COLUMN_CONTESTID_CONTESTID_2);

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
        } else {
            query.append(PlanVoteModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(contestId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(planVote);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PlanVote> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the plan votes where contestId = &#63; from the database.
     *
     * @param contestId the contest ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeBycontestId(long contestId) throws SystemException {
        for (PlanVote planVote : findBycontestId(contestId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(planVote);
        }
    }

    /**
     * Returns the number of plan votes where contestId = &#63;.
     *
     * @param contestId the contest ID
     * @return the number of matching plan votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countBycontestId(long contestId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTESTID;

        Object[] finderArgs = new Object[] { contestId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PLANVOTE_WHERE);

            query.append(_FINDER_COLUMN_CONTESTID_CONTESTID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestId);

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
     * Returns all the plan votes where planId = &#63;.
     *
     * @param planId the plan ID
     * @return the matching plan votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanVote> findByPlanId(long planId) throws SystemException {
        return findByPlanId(planId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan votes where planId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param planId the plan ID
     * @param start the lower bound of the range of plan votes
     * @param end the upper bound of the range of plan votes (not inclusive)
     * @return the range of matching plan votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanVote> findByPlanId(long planId, int start, int end)
        throws SystemException {
        return findByPlanId(planId, start, end, null);
    }

    /**
     * Returns an ordered range of all the plan votes where planId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param planId the plan ID
     * @param start the lower bound of the range of plan votes
     * @param end the upper bound of the range of plan votes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching plan votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanVote> findByPlanId(long planId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID;
            finderArgs = new Object[] { planId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PLANID;
            finderArgs = new Object[] { planId, start, end, orderByComparator };
        }

        List<PlanVote> list = (List<PlanVote>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (PlanVote planVote : list) {
                if ((planId != planVote.getPlanId())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_PLANVOTE_WHERE);

            query.append(_FINDER_COLUMN_PLANID_PLANID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(PlanVoteModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planId);

                if (!pagination) {
                    list = (List<PlanVote>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PlanVote>(list);
                } else {
                    list = (List<PlanVote>) QueryUtil.list(q, getDialect(),
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
     * Returns the first plan vote in the ordered set where planId = &#63;.
     *
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan vote
     * @throws com.ext.portlet.NoSuchPlanVoteException if a matching plan vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanVote findByPlanId_First(long planId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanVoteException, SystemException {
        PlanVote planVote = fetchByPlanId_First(planId, orderByComparator);

        if (planVote != null) {
            return planVote;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("planId=");
        msg.append(planId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPlanVoteException(msg.toString());
    }

    /**
     * Returns the first plan vote in the ordered set where planId = &#63;.
     *
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan vote, or <code>null</code> if a matching plan vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanVote fetchByPlanId_First(long planId,
        OrderByComparator orderByComparator) throws SystemException {
        List<PlanVote> list = findByPlanId(planId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last plan vote in the ordered set where planId = &#63;.
     *
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan vote
     * @throws com.ext.portlet.NoSuchPlanVoteException if a matching plan vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanVote findByPlanId_Last(long planId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanVoteException, SystemException {
        PlanVote planVote = fetchByPlanId_Last(planId, orderByComparator);

        if (planVote != null) {
            return planVote;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("planId=");
        msg.append(planId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPlanVoteException(msg.toString());
    }

    /**
     * Returns the last plan vote in the ordered set where planId = &#63;.
     *
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan vote, or <code>null</code> if a matching plan vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanVote fetchByPlanId_Last(long planId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByPlanId(planId);

        if (count == 0) {
            return null;
        }

        List<PlanVote> list = findByPlanId(planId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the plan votes before and after the current plan vote in the ordered set where planId = &#63;.
     *
     * @param planVotePK the primary key of the current plan vote
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next plan vote
     * @throws com.ext.portlet.NoSuchPlanVoteException if a plan vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanVote[] findByPlanId_PrevAndNext(PlanVotePK planVotePK,
        long planId, OrderByComparator orderByComparator)
        throws NoSuchPlanVoteException, SystemException {
        PlanVote planVote = findByPrimaryKey(planVotePK);

        Session session = null;

        try {
            session = openSession();

            PlanVote[] array = new PlanVoteImpl[3];

            array[0] = getByPlanId_PrevAndNext(session, planVote, planId,
                    orderByComparator, true);

            array[1] = planVote;

            array[2] = getByPlanId_PrevAndNext(session, planVote, planId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PlanVote getByPlanId_PrevAndNext(Session session,
        PlanVote planVote, long planId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PLANVOTE_WHERE);

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
        } else {
            query.append(PlanVoteModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(planId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(planVote);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PlanVote> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the plan votes where planId = &#63; from the database.
     *
     * @param planId the plan ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByPlanId(long planId) throws SystemException {
        for (PlanVote planVote : findByPlanId(planId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(planVote);
        }
    }

    /**
     * Returns the number of plan votes where planId = &#63;.
     *
     * @param planId the plan ID
     * @return the number of matching plan votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByPlanId(long planId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PLANID;

        Object[] finderArgs = new Object[] { planId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PLANVOTE_WHERE);

            query.append(_FINDER_COLUMN_PLANID_PLANID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planId);

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
     * Returns the plan vote where contestId = &#63; and userId = &#63; or throws a {@link com.ext.portlet.NoSuchPlanVoteException} if it could not be found.
     *
     * @param contestId the contest ID
     * @param userId the user ID
     * @return the matching plan vote
     * @throws com.ext.portlet.NoSuchPlanVoteException if a matching plan vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanVote findByContestIdUserId(long contestId, long userId)
        throws NoSuchPlanVoteException, SystemException {
        PlanVote planVote = fetchByContestIdUserId(contestId, userId);

        if (planVote == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("contestId=");
            msg.append(contestId);

            msg.append(", userId=");
            msg.append(userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanVoteException(msg.toString());
        }

        return planVote;
    }

    /**
     * Returns the plan vote where contestId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param contestId the contest ID
     * @param userId the user ID
     * @return the matching plan vote, or <code>null</code> if a matching plan vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanVote fetchByContestIdUserId(long contestId, long userId)
        throws SystemException {
        return fetchByContestIdUserId(contestId, userId, true);
    }

    /**
     * Returns the plan vote where contestId = &#63; and userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param contestId the contest ID
     * @param userId the user ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching plan vote, or <code>null</code> if a matching plan vote could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanVote fetchByContestIdUserId(long contestId, long userId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { contestId, userId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CONTESTIDUSERID,
                    finderArgs, this);
        }

        if (result instanceof PlanVote) {
            PlanVote planVote = (PlanVote) result;

            if ((contestId != planVote.getContestId()) ||
                    (userId != planVote.getUserId())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_SELECT_PLANVOTE_WHERE);

            query.append(_FINDER_COLUMN_CONTESTIDUSERID_CONTESTID_2);

            query.append(_FINDER_COLUMN_CONTESTIDUSERID_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestId);

                qPos.add(userId);

                List<PlanVote> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDUSERID,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "PlanVotePersistenceImpl.fetchByContestIdUserId(long, long, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    PlanVote planVote = list.get(0);

                    result = planVote;

                    cacheResult(planVote);

                    if ((planVote.getContestId() != contestId) ||
                            (planVote.getUserId() != userId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDUSERID,
                            finderArgs, planVote);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTESTIDUSERID,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (PlanVote) result;
        }
    }

    /**
     * Removes the plan vote where contestId = &#63; and userId = &#63; from the database.
     *
     * @param contestId the contest ID
     * @param userId the user ID
     * @return the plan vote that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanVote removeByContestIdUserId(long contestId, long userId)
        throws NoSuchPlanVoteException, SystemException {
        PlanVote planVote = findByContestIdUserId(contestId, userId);

        return remove(planVote);
    }

    /**
     * Returns the number of plan votes where contestId = &#63; and userId = &#63;.
     *
     * @param contestId the contest ID
     * @param userId the user ID
     * @return the number of matching plan votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByContestIdUserId(long contestId, long userId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CONTESTIDUSERID;

        Object[] finderArgs = new Object[] { contestId, userId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PLANVOTE_WHERE);

            query.append(_FINDER_COLUMN_CONTESTIDUSERID_CONTESTID_2);

            query.append(_FINDER_COLUMN_CONTESTIDUSERID_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestId);

                qPos.add(userId);

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
     * Caches the plan vote in the entity cache if it is enabled.
     *
     * @param planVote the plan vote
     */
    @Override
    public void cacheResult(PlanVote planVote) {
        EntityCacheUtil.putResult(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteImpl.class, planVote.getPrimaryKey(), planVote);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDUSERID,
            new Object[] { planVote.getContestId(), planVote.getUserId() },
            planVote);

        planVote.resetOriginalValues();
    }

    /**
     * Caches the plan votes in the entity cache if it is enabled.
     *
     * @param planVotes the plan votes
     */
    @Override
    public void cacheResult(List<PlanVote> planVotes) {
        for (PlanVote planVote : planVotes) {
            if (EntityCacheUtil.getResult(
                        PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
                        PlanVoteImpl.class, planVote.getPrimaryKey()) == null) {
                cacheResult(planVote);
            } else {
                planVote.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan votes.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanVoteImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanVoteImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan vote.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanVote planVote) {
        EntityCacheUtil.removeResult(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteImpl.class, planVote.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(planVote);
    }

    @Override
    public void clearCache(List<PlanVote> planVotes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanVote planVote : planVotes) {
            EntityCacheUtil.removeResult(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
                PlanVoteImpl.class, planVote.getPrimaryKey());

            clearUniqueFindersCache(planVote);
        }
    }

    protected void cacheUniqueFindersCache(PlanVote planVote) {
        if (planVote.isNew()) {
            Object[] args = new Object[] {
                    planVote.getContestId(), planVote.getUserId()
                };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTESTIDUSERID,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDUSERID,
                args, planVote);
        } else {
            PlanVoteModelImpl planVoteModelImpl = (PlanVoteModelImpl) planVote;

            if ((planVoteModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_CONTESTIDUSERID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        planVote.getContestId(), planVote.getUserId()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTESTIDUSERID,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTIDUSERID,
                    args, planVote);
            }
        }
    }

    protected void clearUniqueFindersCache(PlanVote planVote) {
        PlanVoteModelImpl planVoteModelImpl = (PlanVoteModelImpl) planVote;

        Object[] args = new Object[] {
                planVote.getContestId(), planVote.getUserId()
            };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTIDUSERID, args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTESTIDUSERID, args);

        if ((planVoteModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_CONTESTIDUSERID.getColumnBitmask()) != 0) {
            args = new Object[] {
                    planVoteModelImpl.getOriginalContestId(),
                    planVoteModelImpl.getOriginalUserId()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTIDUSERID,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTESTIDUSERID,
                args);
        }
    }

    /**
     * Creates a new plan vote with the primary key. Does not add the plan vote to the database.
     *
     * @param planVotePK the primary key for the new plan vote
     * @return the new plan vote
     */
    @Override
    public PlanVote create(PlanVotePK planVotePK) {
        PlanVote planVote = new PlanVoteImpl();

        planVote.setNew(true);
        planVote.setPrimaryKey(planVotePK);

        return planVote;
    }

    /**
     * Removes the plan vote with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param planVotePK the primary key of the plan vote
     * @return the plan vote that was removed
     * @throws com.ext.portlet.NoSuchPlanVoteException if a plan vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanVote remove(PlanVotePK planVotePK)
        throws NoSuchPlanVoteException, SystemException {
        return remove((Serializable) planVotePK);
    }

    /**
     * Removes the plan vote with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan vote
     * @return the plan vote that was removed
     * @throws com.ext.portlet.NoSuchPlanVoteException if a plan vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanVote remove(Serializable primaryKey)
        throws NoSuchPlanVoteException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanVote planVote = (PlanVote) session.get(PlanVoteImpl.class,
                    primaryKey);

            if (planVote == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanVoteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planVote);
        } catch (NoSuchPlanVoteException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanVote removeImpl(PlanVote planVote) throws SystemException {
        planVote = toUnwrappedModel(planVote);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(planVote)) {
                planVote = (PlanVote) session.get(PlanVoteImpl.class,
                        planVote.getPrimaryKeyObj());
            }

            if (planVote != null) {
                session.delete(planVote);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (planVote != null) {
            clearCache(planVote);
        }

        return planVote;
    }

    @Override
    public PlanVote updateImpl(com.ext.portlet.model.PlanVote planVote)
        throws SystemException {
        planVote = toUnwrappedModel(planVote);

        boolean isNew = planVote.isNew();

        PlanVoteModelImpl planVoteModelImpl = (PlanVoteModelImpl) planVote;

        Session session = null;

        try {
            session = openSession();

            if (planVote.isNew()) {
                session.save(planVote);

                planVote.setNew(false);
            } else {
                session.merge(planVote);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PlanVoteModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((planVoteModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        planVoteModelImpl.getOriginalContestId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID,
                    args);

                args = new Object[] { planVoteModelImpl.getContestId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTID,
                    args);
            }

            if ((planVoteModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        planVoteModelImpl.getOriginalPlanId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID,
                    args);

                args = new Object[] { planVoteModelImpl.getPlanId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANID,
                    args);
            }
        }

        EntityCacheUtil.putResult(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
            PlanVoteImpl.class, planVote.getPrimaryKey(), planVote);

        clearUniqueFindersCache(planVote);
        cacheUniqueFindersCache(planVote);

        return planVote;
    }

    protected PlanVote toUnwrappedModel(PlanVote planVote) {
        if (planVote instanceof PlanVoteImpl) {
            return planVote;
        }

        PlanVoteImpl planVoteImpl = new PlanVoteImpl();

        planVoteImpl.setNew(planVote.isNew());
        planVoteImpl.setPrimaryKey(planVote.getPrimaryKey());

        planVoteImpl.setUserId(planVote.getUserId());
        planVoteImpl.setContestId(planVote.getContestId());
        planVoteImpl.setPlanId(planVote.getPlanId());
        planVoteImpl.setCreateDate(planVote.getCreateDate());

        return planVoteImpl;
    }

    /**
     * Returns the plan vote with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan vote
     * @return the plan vote
     * @throws com.ext.portlet.NoSuchPlanVoteException if a plan vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanVote findByPrimaryKey(Serializable primaryKey)
        throws NoSuchPlanVoteException, SystemException {
        PlanVote planVote = fetchByPrimaryKey(primaryKey);

        if (planVote == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchPlanVoteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return planVote;
    }

    /**
     * Returns the plan vote with the primary key or throws a {@link com.ext.portlet.NoSuchPlanVoteException} if it could not be found.
     *
     * @param planVotePK the primary key of the plan vote
     * @return the plan vote
     * @throws com.ext.portlet.NoSuchPlanVoteException if a plan vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanVote findByPrimaryKey(PlanVotePK planVotePK)
        throws NoSuchPlanVoteException, SystemException {
        return findByPrimaryKey((Serializable) planVotePK);
    }

    /**
     * Returns the plan vote with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan vote
     * @return the plan vote, or <code>null</code> if a plan vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanVote fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        PlanVote planVote = (PlanVote) EntityCacheUtil.getResult(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
                PlanVoteImpl.class, primaryKey);

        if (planVote == _nullPlanVote) {
            return null;
        }

        if (planVote == null) {
            Session session = null;

            try {
                session = openSession();

                planVote = (PlanVote) session.get(PlanVoteImpl.class, primaryKey);

                if (planVote != null) {
                    cacheResult(planVote);
                } else {
                    EntityCacheUtil.putResult(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
                        PlanVoteImpl.class, primaryKey, _nullPlanVote);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(PlanVoteModelImpl.ENTITY_CACHE_ENABLED,
                    PlanVoteImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return planVote;
    }

    /**
     * Returns the plan vote with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param planVotePK the primary key of the plan vote
     * @return the plan vote, or <code>null</code> if a plan vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanVote fetchByPrimaryKey(PlanVotePK planVotePK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) planVotePK);
    }

    /**
     * Returns all the plan votes.
     *
     * @return the plan votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanVote> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan votes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan votes
     * @param end the upper bound of the range of plan votes (not inclusive)
     * @return the range of plan votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanVote> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan votes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanVoteModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan votes
     * @param end the upper bound of the range of plan votes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan votes
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanVote> findAll(int start, int end,
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

        List<PlanVote> list = (List<PlanVote>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANVOTE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANVOTE;

                if (pagination) {
                    sql = sql.concat(PlanVoteModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<PlanVote>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PlanVote>(list);
                } else {
                    list = (List<PlanVote>) QueryUtil.list(q, getDialect(),
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
     * Removes all the plan votes from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (PlanVote planVote : findAll()) {
            remove(planVote);
        }
    }

    /**
     * Returns the number of plan votes.
     *
     * @return the number of plan votes
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

                Query q = session.createQuery(_SQL_COUNT_PLANVOTE);

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
     * Initializes the plan vote persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.PlanVote")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanVote>> listenersList = new ArrayList<ModelListener<PlanVote>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanVote>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanVoteImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
