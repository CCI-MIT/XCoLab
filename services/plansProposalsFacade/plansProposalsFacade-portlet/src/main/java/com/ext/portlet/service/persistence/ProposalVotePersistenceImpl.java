package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchProposalVoteException;
import com.ext.portlet.model.ProposalVote;
import com.ext.portlet.model.impl.ProposalVoteImpl;
import com.ext.portlet.model.impl.ProposalVoteModelImpl;
import com.ext.portlet.service.persistence.ActivitySubscriptionPersistence;
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
import com.ext.portlet.service.persistence.PlanVotePersistence;
import com.ext.portlet.service.persistence.PlansFilterPersistence;
import com.ext.portlet.service.persistence.PlansFilterPositionPersistence;
import com.ext.portlet.service.persistence.PlansUserSettingsPersistence;
import com.ext.portlet.service.persistence.Proposal2PhasePersistence;
import com.ext.portlet.service.persistence.ProposalAttributePersistence;
import com.ext.portlet.service.persistence.ProposalAttributeTypePersistence;
import com.ext.portlet.service.persistence.ProposalPersistence;
import com.ext.portlet.service.persistence.ProposalSupporterPersistence;
import com.ext.portlet.service.persistence.ProposalVersionPersistence;
import com.ext.portlet.service.persistence.ProposalVotePersistence;

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
 * The persistence implementation for the proposal vote service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalVotePersistence
 * @see ProposalVoteUtil
 * @generated
 */
public class ProposalVotePersistenceImpl extends BasePersistenceImpl<ProposalVote>
    implements ProposalVotePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProposalVoteUtil} to access the proposal vote persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProposalVoteImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALID =
        new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, ProposalVoteImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByProposalId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID =
        new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, ProposalVoteImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByProposalId",
            new String[] { Long.class.getName() },
            ProposalVoteModelImpl.PROPOSALID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PROPOSALID = new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProposalId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID =
        new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, ProposalVoteImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByProposalIdContestPhaseId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID =
        new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, ProposalVoteImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByProposalIdContestPhaseId",
            new String[] { Long.class.getName(), Long.class.getName() },
            ProposalVoteModelImpl.PROPOSALID_COLUMN_BITMASK |
            ProposalVoteModelImpl.CONTESTPHASEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEID =
        new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByProposalIdContestPhaseId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, ProposalVoteImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
        new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, ProposalVoteImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
            new String[] { Long.class.getName() },
            ProposalVoteModelImpl.USERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, ProposalVoteImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, ProposalVoteImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PROPOSALVOTE = "SELECT proposalVote FROM ProposalVote proposalVote";
    private static final String _SQL_SELECT_PROPOSALVOTE_WHERE = "SELECT proposalVote FROM ProposalVote proposalVote WHERE ";
    private static final String _SQL_COUNT_PROPOSALVOTE = "SELECT COUNT(proposalVote) FROM ProposalVote proposalVote";
    private static final String _SQL_COUNT_PROPOSALVOTE_WHERE = "SELECT COUNT(proposalVote) FROM ProposalVote proposalVote WHERE ";
    private static final String _FINDER_COLUMN_PROPOSALID_PROPOSALID_2 = "proposalVote.proposalId = ?";
    private static final String _FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_PROPOSALID_2 =
        "proposalVote.proposalId = ? AND ";
    private static final String _FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_CONTESTPHASEID_2 =
        "proposalVote.id.contestPhaseId = ?";
    private static final String _FINDER_COLUMN_USERID_USERID_2 = "proposalVote.id.userId = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "proposalVote.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProposalVote exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ProposalVote exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ProposalVotePersistenceImpl.class);
    private static ProposalVote _nullProposalVote = new ProposalVoteImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ProposalVote> toCacheModel() {
                return _nullProposalVoteCacheModel;
            }
        };

    private static CacheModel<ProposalVote> _nullProposalVoteCacheModel = new CacheModel<ProposalVote>() {
            public ProposalVote toEntityModel() {
                return _nullProposalVote;
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
     * Caches the proposal vote in the entity cache if it is enabled.
     *
     * @param proposalVote the proposal vote
     */
    public void cacheResult(ProposalVote proposalVote) {
        EntityCacheUtil.putResult(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteImpl.class, proposalVote.getPrimaryKey(), proposalVote);

        proposalVote.resetOriginalValues();
    }

    /**
     * Caches the proposal votes in the entity cache if it is enabled.
     *
     * @param proposalVotes the proposal votes
     */
    public void cacheResult(List<ProposalVote> proposalVotes) {
        for (ProposalVote proposalVote : proposalVotes) {
            if (EntityCacheUtil.getResult(
                        ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalVoteImpl.class, proposalVote.getPrimaryKey()) == null) {
                cacheResult(proposalVote);
            } else {
                proposalVote.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all proposal votes.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProposalVoteImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProposalVoteImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the proposal vote.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ProposalVote proposalVote) {
        EntityCacheUtil.removeResult(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteImpl.class, proposalVote.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ProposalVote> proposalVotes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ProposalVote proposalVote : proposalVotes) {
            EntityCacheUtil.removeResult(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
                ProposalVoteImpl.class, proposalVote.getPrimaryKey());
        }
    }

    /**
     * Creates a new proposal vote with the primary key. Does not add the proposal vote to the database.
     *
     * @param proposalVotePK the primary key for the new proposal vote
     * @return the new proposal vote
     */
    public ProposalVote create(ProposalVotePK proposalVotePK) {
        ProposalVote proposalVote = new ProposalVoteImpl();

        proposalVote.setNew(true);
        proposalVote.setPrimaryKey(proposalVotePK);

        return proposalVote;
    }

    /**
     * Removes the proposal vote with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param proposalVotePK the primary key of the proposal vote
     * @return the proposal vote that was removed
     * @throws com.ext.portlet.NoSuchProposalVoteException if a proposal vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalVote remove(ProposalVotePK proposalVotePK)
        throws NoSuchProposalVoteException, SystemException {
        return remove((Serializable) proposalVotePK);
    }

    /**
     * Removes the proposal vote with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the proposal vote
     * @return the proposal vote that was removed
     * @throws com.ext.portlet.NoSuchProposalVoteException if a proposal vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote remove(Serializable primaryKey)
        throws NoSuchProposalVoteException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ProposalVote proposalVote = (ProposalVote) session.get(ProposalVoteImpl.class,
                    primaryKey);

            if (proposalVote == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProposalVoteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(proposalVote);
        } catch (NoSuchProposalVoteException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ProposalVote removeImpl(ProposalVote proposalVote)
        throws SystemException {
        proposalVote = toUnwrappedModel(proposalVote);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, proposalVote);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(proposalVote);

        return proposalVote;
    }

    @Override
    public ProposalVote updateImpl(
        com.ext.portlet.model.ProposalVote proposalVote, boolean merge)
        throws SystemException {
        proposalVote = toUnwrappedModel(proposalVote);

        boolean isNew = proposalVote.isNew();

        ProposalVoteModelImpl proposalVoteModelImpl = (ProposalVoteModelImpl) proposalVote;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, proposalVote, merge);

            proposalVote.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ProposalVoteModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((proposalVoteModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(proposalVoteModelImpl.getOriginalProposalId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID,
                    args);

                args = new Object[] {
                        Long.valueOf(proposalVoteModelImpl.getProposalId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID,
                    args);
            }

            if ((proposalVoteModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(proposalVoteModelImpl.getOriginalProposalId()),
                        Long.valueOf(proposalVoteModelImpl.getOriginalContestPhaseId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID,
                    args);

                args = new Object[] {
                        Long.valueOf(proposalVoteModelImpl.getProposalId()),
                        Long.valueOf(proposalVoteModelImpl.getContestPhaseId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID,
                    args);
            }

            if ((proposalVoteModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(proposalVoteModelImpl.getOriginalUserId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
                    args);

                args = new Object[] {
                        Long.valueOf(proposalVoteModelImpl.getUserId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
                    args);
            }
        }

        EntityCacheUtil.putResult(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
            ProposalVoteImpl.class, proposalVote.getPrimaryKey(), proposalVote);

        return proposalVote;
    }

    protected ProposalVote toUnwrappedModel(ProposalVote proposalVote) {
        if (proposalVote instanceof ProposalVoteImpl) {
            return proposalVote;
        }

        ProposalVoteImpl proposalVoteImpl = new ProposalVoteImpl();

        proposalVoteImpl.setNew(proposalVote.isNew());
        proposalVoteImpl.setPrimaryKey(proposalVote.getPrimaryKey());

        proposalVoteImpl.setProposalId(proposalVote.getProposalId());
        proposalVoteImpl.setContestPhaseId(proposalVote.getContestPhaseId());
        proposalVoteImpl.setUserId(proposalVote.getUserId());
        proposalVoteImpl.setCreateDate(proposalVote.getCreateDate());

        return proposalVoteImpl;
    }

    /**
     * Returns the proposal vote with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the proposal vote
     * @return the proposal vote
     * @throws com.liferay.portal.NoSuchModelException if a proposal vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey((ProposalVotePK) primaryKey);
    }

    /**
     * Returns the proposal vote with the primary key or throws a {@link com.ext.portlet.NoSuchProposalVoteException} if it could not be found.
     *
     * @param proposalVotePK the primary key of the proposal vote
     * @return the proposal vote
     * @throws com.ext.portlet.NoSuchProposalVoteException if a proposal vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalVote findByPrimaryKey(ProposalVotePK proposalVotePK)
        throws NoSuchProposalVoteException, SystemException {
        ProposalVote proposalVote = fetchByPrimaryKey(proposalVotePK);

        if (proposalVote == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + proposalVotePK);
            }

            throw new NoSuchProposalVoteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                proposalVotePK);
        }

        return proposalVote;
    }

    /**
     * Returns the proposal vote with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the proposal vote
     * @return the proposal vote, or <code>null</code> if a proposal vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalVote fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey((ProposalVotePK) primaryKey);
    }

    /**
     * Returns the proposal vote with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param proposalVotePK the primary key of the proposal vote
     * @return the proposal vote, or <code>null</code> if a proposal vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalVote fetchByPrimaryKey(ProposalVotePK proposalVotePK)
        throws SystemException {
        ProposalVote proposalVote = (ProposalVote) EntityCacheUtil.getResult(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
                ProposalVoteImpl.class, proposalVotePK);

        if (proposalVote == _nullProposalVote) {
            return null;
        }

        if (proposalVote == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                proposalVote = (ProposalVote) session.get(ProposalVoteImpl.class,
                        proposalVotePK);
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (proposalVote != null) {
                    cacheResult(proposalVote);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(ProposalVoteModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalVoteImpl.class, proposalVotePK,
                        _nullProposalVote);
                }

                closeSession(session);
            }
        }

        return proposalVote;
    }

    /**
     * Returns all the proposal votes where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @return the matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalVote> findByProposalId(long proposalId)
        throws SystemException {
        return findByProposalId(proposalId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal votes where proposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param start the lower bound of the range of proposal votes
     * @param end the upper bound of the range of proposal votes (not inclusive)
     * @return the range of matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalVote> findByProposalId(long proposalId, int start,
        int end) throws SystemException {
        return findByProposalId(proposalId, start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal votes where proposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param start the lower bound of the range of proposal votes
     * @param end the upper bound of the range of proposal votes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalVote> findByProposalId(long proposalId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID;
            finderArgs = new Object[] { proposalId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALID;
            finderArgs = new Object[] { proposalId, start, end, orderByComparator };
        }

        List<ProposalVote> list = (List<ProposalVote>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_PROPOSALVOTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALID_2);

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

                qPos.add(proposalId);

                list = (List<ProposalVote>) QueryUtil.list(q, getDialect(),
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
     * Returns the first proposal vote in the ordered set where proposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal vote
     * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalVote findByProposalId_First(long proposalId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalVoteException, SystemException {
        List<ProposalVote> list = findByProposalId(proposalId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("proposalId=");
            msg.append(proposalId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchProposalVoteException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last proposal vote in the ordered set where proposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal vote
     * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalVote findByProposalId_Last(long proposalId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalVoteException, SystemException {
        int count = countByProposalId(proposalId);

        List<ProposalVote> list = findByProposalId(proposalId, count - 1,
                count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("proposalId=");
            msg.append(proposalId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchProposalVoteException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the proposal votes before and after the current proposal vote in the ordered set where proposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param proposalVotePK the primary key of the current proposal vote
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next proposal vote
     * @throws com.ext.portlet.NoSuchProposalVoteException if a proposal vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalVote[] findByProposalId_PrevAndNext(
        ProposalVotePK proposalVotePK, long proposalId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalVoteException, SystemException {
        ProposalVote proposalVote = findByPrimaryKey(proposalVotePK);

        Session session = null;

        try {
            session = openSession();

            ProposalVote[] array = new ProposalVoteImpl[3];

            array[0] = getByProposalId_PrevAndNext(session, proposalVote,
                    proposalId, orderByComparator, true);

            array[1] = proposalVote;

            array[2] = getByProposalId_PrevAndNext(session, proposalVote,
                    proposalId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProposalVote getByProposalId_PrevAndNext(Session session,
        ProposalVote proposalVote, long proposalId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PROPOSALVOTE_WHERE);

        query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALID_2);

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

        qPos.add(proposalId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(proposalVote);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProposalVote> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the proposal votes where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @return the matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalVote> findByProposalIdContestPhaseId(long proposalId,
        long contestPhaseId) throws SystemException {
        return findByProposalIdContestPhaseId(proposalId, contestPhaseId,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal votes where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param start the lower bound of the range of proposal votes
     * @param end the upper bound of the range of proposal votes (not inclusive)
     * @return the range of matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalVote> findByProposalIdContestPhaseId(long proposalId,
        long contestPhaseId, int start, int end) throws SystemException {
        return findByProposalIdContestPhaseId(proposalId, contestPhaseId,
            start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal votes where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param start the lower bound of the range of proposal votes
     * @param end the upper bound of the range of proposal votes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalVote> findByProposalIdContestPhaseId(long proposalId,
        long contestPhaseId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID;
            finderArgs = new Object[] { proposalId, contestPhaseId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID;
            finderArgs = new Object[] {
                    proposalId, contestPhaseId,
                    
                    start, end, orderByComparator
                };
        }

        List<ProposalVote> list = (List<ProposalVote>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_PROPOSALVOTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_PROPOSALID_2);

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_CONTESTPHASEID_2);

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

                qPos.add(proposalId);

                qPos.add(contestPhaseId);

                list = (List<ProposalVote>) QueryUtil.list(q, getDialect(),
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
     * Returns the first proposal vote in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal vote
     * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalVote findByProposalIdContestPhaseId_First(long proposalId,
        long contestPhaseId, OrderByComparator orderByComparator)
        throws NoSuchProposalVoteException, SystemException {
        List<ProposalVote> list = findByProposalIdContestPhaseId(proposalId,
                contestPhaseId, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("proposalId=");
            msg.append(proposalId);

            msg.append(", contestPhaseId=");
            msg.append(contestPhaseId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchProposalVoteException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last proposal vote in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal vote
     * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalVote findByProposalIdContestPhaseId_Last(long proposalId,
        long contestPhaseId, OrderByComparator orderByComparator)
        throws NoSuchProposalVoteException, SystemException {
        int count = countByProposalIdContestPhaseId(proposalId, contestPhaseId);

        List<ProposalVote> list = findByProposalIdContestPhaseId(proposalId,
                contestPhaseId, count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("proposalId=");
            msg.append(proposalId);

            msg.append(", contestPhaseId=");
            msg.append(contestPhaseId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchProposalVoteException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the proposal votes before and after the current proposal vote in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param proposalVotePK the primary key of the current proposal vote
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next proposal vote
     * @throws com.ext.portlet.NoSuchProposalVoteException if a proposal vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalVote[] findByProposalIdContestPhaseId_PrevAndNext(
        ProposalVotePK proposalVotePK, long proposalId, long contestPhaseId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalVoteException, SystemException {
        ProposalVote proposalVote = findByPrimaryKey(proposalVotePK);

        Session session = null;

        try {
            session = openSession();

            ProposalVote[] array = new ProposalVoteImpl[3];

            array[0] = getByProposalIdContestPhaseId_PrevAndNext(session,
                    proposalVote, proposalId, contestPhaseId,
                    orderByComparator, true);

            array[1] = proposalVote;

            array[2] = getByProposalIdContestPhaseId_PrevAndNext(session,
                    proposalVote, proposalId, contestPhaseId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProposalVote getByProposalIdContestPhaseId_PrevAndNext(
        Session session, ProposalVote proposalVote, long proposalId,
        long contestPhaseId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PROPOSALVOTE_WHERE);

        query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_PROPOSALID_2);

        query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_CONTESTPHASEID_2);

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

        qPos.add(proposalId);

        qPos.add(contestPhaseId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(proposalVote);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProposalVote> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the proposal votes where userId = &#63;.
     *
     * @param userId the user ID
     * @return the matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalVote> findByUserId(long userId)
        throws SystemException {
        return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal votes where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param start the lower bound of the range of proposal votes
     * @param end the upper bound of the range of proposal votes (not inclusive)
     * @return the range of matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalVote> findByUserId(long userId, int start, int end)
        throws SystemException {
        return findByUserId(userId, start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal votes where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param start the lower bound of the range of proposal votes
     * @param end the upper bound of the range of proposal votes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalVote> findByUserId(long userId, int start, int end,
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

        List<ProposalVote> list = (List<ProposalVote>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_PROPOSALVOTE_WHERE);

            query.append(_FINDER_COLUMN_USERID_USERID_2);

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

                qPos.add(userId);

                list = (List<ProposalVote>) QueryUtil.list(q, getDialect(),
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
     * Returns the first proposal vote in the ordered set where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal vote
     * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalVote findByUserId_First(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalVoteException, SystemException {
        List<ProposalVote> list = findByUserId(userId, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("userId=");
            msg.append(userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchProposalVoteException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last proposal vote in the ordered set where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal vote
     * @throws com.ext.portlet.NoSuchProposalVoteException if a matching proposal vote could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalVote findByUserId_Last(long userId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalVoteException, SystemException {
        int count = countByUserId(userId);

        List<ProposalVote> list = findByUserId(userId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("userId=");
            msg.append(userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchProposalVoteException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the proposal votes before and after the current proposal vote in the ordered set where userId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param proposalVotePK the primary key of the current proposal vote
     * @param userId the user ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next proposal vote
     * @throws com.ext.portlet.NoSuchProposalVoteException if a proposal vote with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalVote[] findByUserId_PrevAndNext(
        ProposalVotePK proposalVotePK, long userId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalVoteException, SystemException {
        ProposalVote proposalVote = findByPrimaryKey(proposalVotePK);

        Session session = null;

        try {
            session = openSession();

            ProposalVote[] array = new ProposalVoteImpl[3];

            array[0] = getByUserId_PrevAndNext(session, proposalVote, userId,
                    orderByComparator, true);

            array[1] = proposalVote;

            array[2] = getByUserId_PrevAndNext(session, proposalVote, userId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProposalVote getByUserId_PrevAndNext(Session session,
        ProposalVote proposalVote, long userId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PROPOSALVOTE_WHERE);

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

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(userId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(proposalVote);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProposalVote> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the proposal votes.
     *
     * @return the proposal votes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalVote> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal votes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of proposal votes
     * @param end the upper bound of the range of proposal votes (not inclusive)
     * @return the range of proposal votes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalVote> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal votes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of proposal votes
     * @param end the upper bound of the range of proposal votes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of proposal votes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalVote> findAll(int start, int end,
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

        List<ProposalVote> list = (List<ProposalVote>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PROPOSALVOTE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PROPOSALVOTE;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<ProposalVote>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ProposalVote>) QueryUtil.list(q, getDialect(),
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
     * Removes all the proposal votes where proposalId = &#63; from the database.
     *
     * @param proposalId the proposal ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByProposalId(long proposalId) throws SystemException {
        for (ProposalVote proposalVote : findByProposalId(proposalId)) {
            remove(proposalVote);
        }
    }

    /**
     * Removes all the proposal votes where proposalId = &#63; and contestPhaseId = &#63; from the database.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByProposalIdContestPhaseId(long proposalId,
        long contestPhaseId) throws SystemException {
        for (ProposalVote proposalVote : findByProposalIdContestPhaseId(
                proposalId, contestPhaseId)) {
            remove(proposalVote);
        }
    }

    /**
     * Removes all the proposal votes where userId = &#63; from the database.
     *
     * @param userId the user ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByUserId(long userId) throws SystemException {
        for (ProposalVote proposalVote : findByUserId(userId)) {
            remove(proposalVote);
        }
    }

    /**
     * Removes all the proposal votes from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (ProposalVote proposalVote : findAll()) {
            remove(proposalVote);
        }
    }

    /**
     * Returns the number of proposal votes where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @return the number of matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    public int countByProposalId(long proposalId) throws SystemException {
        Object[] finderArgs = new Object[] { proposalId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PROPOSALID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PROPOSALVOTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PROPOSALID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of proposal votes where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @return the number of matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    public int countByProposalIdContestPhaseId(long proposalId,
        long contestPhaseId) throws SystemException {
        Object[] finderArgs = new Object[] { proposalId, contestPhaseId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PROPOSALVOTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_PROPOSALID_2);

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_CONTESTPHASEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                qPos.add(contestPhaseId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of proposal votes where userId = &#63;.
     *
     * @param userId the user ID
     * @return the number of matching proposal votes
     * @throws SystemException if a system exception occurred
     */
    public int countByUserId(long userId) throws SystemException {
        Object[] finderArgs = new Object[] { userId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PROPOSALVOTE_WHERE);

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
     * Returns the number of proposal votes.
     *
     * @return the number of proposal votes
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_PROPOSALVOTE);

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
     * Initializes the proposal vote persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ProposalVote")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ProposalVote>> listenersList = new ArrayList<ModelListener<ProposalVote>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ProposalVote>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProposalVoteImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
