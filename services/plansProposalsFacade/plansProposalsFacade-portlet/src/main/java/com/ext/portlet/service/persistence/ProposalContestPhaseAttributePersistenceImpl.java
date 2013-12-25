package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.model.impl.ProposalContestPhaseAttributeImpl;
import com.ext.portlet.model.impl.ProposalContestPhaseAttributeModelImpl;
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
import com.ext.portlet.service.persistence.PlanVotePersistence;
import com.ext.portlet.service.persistence.PlansFilterPersistence;
import com.ext.portlet.service.persistence.PlansFilterPositionPersistence;
import com.ext.portlet.service.persistence.PlansUserSettingsPersistence;
import com.ext.portlet.service.persistence.Proposal2PhasePersistence;
import com.ext.portlet.service.persistence.ProposalAttributePersistence;
import com.ext.portlet.service.persistence.ProposalAttributeTypePersistence;
import com.ext.portlet.service.persistence.ProposalContestPhaseAttributePersistence;
import com.ext.portlet.service.persistence.ProposalContestPhaseAttributeTypePersistence;
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
 * The persistence implementation for the proposal contest phase attribute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalContestPhaseAttributePersistence
 * @see ProposalContestPhaseAttributeUtil
 * @generated
 */
public class ProposalContestPhaseAttributePersistenceImpl
    extends BasePersistenceImpl<ProposalContestPhaseAttribute>
    implements ProposalContestPhaseAttributePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProposalContestPhaseAttributeUtil} to access the proposal contest phase attribute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProposalContestPhaseAttributeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_FETCH_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID =
        new FinderPath(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalContestPhaseAttributeImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByProposalIdContestPhaseIdNameAdditionalId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                String.class.getName(), Long.class.getName()
            },
            ProposalContestPhaseAttributeModelImpl.PROPOSALID_COLUMN_BITMASK |
            ProposalContestPhaseAttributeModelImpl.CONTESTPHASEID_COLUMN_BITMASK |
            ProposalContestPhaseAttributeModelImpl.NAME_COLUMN_BITMASK |
            ProposalContestPhaseAttributeModelImpl.ADDITIONALID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID =
        new FinderPath(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByProposalIdContestPhaseIdNameAdditionalId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                String.class.getName(), Long.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID =
        new FinderPath(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalContestPhaseAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByProposalIdContestPhaseId",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID =
        new FinderPath(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalContestPhaseAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByProposalIdContestPhaseId",
            new String[] { Long.class.getName(), Long.class.getName() },
            ProposalContestPhaseAttributeModelImpl.PROPOSALID_COLUMN_BITMASK |
            ProposalContestPhaseAttributeModelImpl.CONTESTPHASEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEID =
        new FinderPath(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByProposalIdContestPhaseId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalContestPhaseAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeModelImpl.FINDER_CACHE_ENABLED,
            ProposalContestPhaseAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    private static final String _SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTE = "SELECT proposalContestPhaseAttribute FROM ProposalContestPhaseAttribute proposalContestPhaseAttribute";
    private static final String _SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTE_WHERE = "SELECT proposalContestPhaseAttribute FROM ProposalContestPhaseAttribute proposalContestPhaseAttribute WHERE ";
    private static final String _SQL_COUNT_PROPOSALCONTESTPHASEATTRIBUTE = "SELECT COUNT(proposalContestPhaseAttribute) FROM ProposalContestPhaseAttribute proposalContestPhaseAttribute";
    private static final String _SQL_COUNT_PROPOSALCONTESTPHASEATTRIBUTE_WHERE = "SELECT COUNT(proposalContestPhaseAttribute) FROM ProposalContestPhaseAttribute proposalContestPhaseAttribute WHERE ";
    private static final String _FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_PROPOSALID_2 =
        "proposalContestPhaseAttribute.proposalId = ? AND ";
    private static final String _FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_CONTESTPHASEID_2 =
        "proposalContestPhaseAttribute.contestPhaseId = ? AND ";
    private static final String _FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_NAME_1 =
        "proposalContestPhaseAttribute.name IS NULL AND ";
    private static final String _FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_NAME_2 =
        "proposalContestPhaseAttribute.name = ? AND ";
    private static final String _FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_NAME_3 =
        "(proposalContestPhaseAttribute.name IS NULL OR proposalContestPhaseAttribute.name = ?) AND ";
    private static final String _FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_ADDITIONALID_2 =
        "proposalContestPhaseAttribute.additionalId = ?";
    private static final String _FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_PROPOSALID_2 =
        "proposalContestPhaseAttribute.proposalId = ? AND ";
    private static final String _FINDER_COLUMN_PROPOSALIDCONTESTPHASEID_CONTESTPHASEID_2 =
        "proposalContestPhaseAttribute.contestPhaseId = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "proposalContestPhaseAttribute.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProposalContestPhaseAttribute exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ProposalContestPhaseAttribute exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ProposalContestPhaseAttributePersistenceImpl.class);
    private static ProposalContestPhaseAttribute _nullProposalContestPhaseAttribute =
        new ProposalContestPhaseAttributeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ProposalContestPhaseAttribute> toCacheModel() {
                return _nullProposalContestPhaseAttributeCacheModel;
            }
        };

    private static CacheModel<ProposalContestPhaseAttribute> _nullProposalContestPhaseAttributeCacheModel =
        new CacheModel<ProposalContestPhaseAttribute>() {
            public ProposalContestPhaseAttribute toEntityModel() {
                return _nullProposalContestPhaseAttribute;
            }
        };

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
     * Caches the proposal contest phase attribute in the entity cache if it is enabled.
     *
     * @param proposalContestPhaseAttribute the proposal contest phase attribute
     */
    public void cacheResult(
        ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        EntityCacheUtil.putResult(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeImpl.class,
            proposalContestPhaseAttribute.getPrimaryKey(),
            proposalContestPhaseAttribute);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
            new Object[] {
                Long.valueOf(proposalContestPhaseAttribute.getProposalId()),
                Long.valueOf(proposalContestPhaseAttribute.getContestPhaseId()),
                
            proposalContestPhaseAttribute.getName(),
                Long.valueOf(proposalContestPhaseAttribute.getAdditionalId())
            }, proposalContestPhaseAttribute);

        proposalContestPhaseAttribute.resetOriginalValues();
    }

    /**
     * Caches the proposal contest phase attributes in the entity cache if it is enabled.
     *
     * @param proposalContestPhaseAttributes the proposal contest phase attributes
     */
    public void cacheResult(
        List<ProposalContestPhaseAttribute> proposalContestPhaseAttributes) {
        for (ProposalContestPhaseAttribute proposalContestPhaseAttribute : proposalContestPhaseAttributes) {
            if (EntityCacheUtil.getResult(
                        ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalContestPhaseAttributeImpl.class,
                        proposalContestPhaseAttribute.getPrimaryKey()) == null) {
                cacheResult(proposalContestPhaseAttribute);
            } else {
                proposalContestPhaseAttribute.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all proposal contest phase attributes.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProposalContestPhaseAttributeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProposalContestPhaseAttributeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the proposal contest phase attribute.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(
        ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        EntityCacheUtil.removeResult(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeImpl.class,
            proposalContestPhaseAttribute.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(proposalContestPhaseAttribute);
    }

    @Override
    public void clearCache(
        List<ProposalContestPhaseAttribute> proposalContestPhaseAttributes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ProposalContestPhaseAttribute proposalContestPhaseAttribute : proposalContestPhaseAttributes) {
            EntityCacheUtil.removeResult(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
                ProposalContestPhaseAttributeImpl.class,
                proposalContestPhaseAttribute.getPrimaryKey());

            clearUniqueFindersCache(proposalContestPhaseAttribute);
        }
    }

    protected void clearUniqueFindersCache(
        ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
            new Object[] {
                Long.valueOf(proposalContestPhaseAttribute.getProposalId()),
                Long.valueOf(proposalContestPhaseAttribute.getContestPhaseId()),
                
            proposalContestPhaseAttribute.getName(),
                Long.valueOf(proposalContestPhaseAttribute.getAdditionalId())
            });
    }

    /**
     * Creates a new proposal contest phase attribute with the primary key. Does not add the proposal contest phase attribute to the database.
     *
     * @param id the primary key for the new proposal contest phase attribute
     * @return the new proposal contest phase attribute
     */
    public ProposalContestPhaseAttribute create(long id) {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = new ProposalContestPhaseAttributeImpl();

        proposalContestPhaseAttribute.setNew(true);
        proposalContestPhaseAttribute.setPrimaryKey(id);

        return proposalContestPhaseAttribute;
    }

    /**
     * Removes the proposal contest phase attribute with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the proposal contest phase attribute
     * @return the proposal contest phase attribute that was removed
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a proposal contest phase attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalContestPhaseAttribute remove(long id)
        throws NoSuchProposalContestPhaseAttributeException, SystemException {
        return remove(Long.valueOf(id));
    }

    /**
     * Removes the proposal contest phase attribute with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the proposal contest phase attribute
     * @return the proposal contest phase attribute that was removed
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a proposal contest phase attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute remove(Serializable primaryKey)
        throws NoSuchProposalContestPhaseAttributeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ProposalContestPhaseAttribute proposalContestPhaseAttribute = (ProposalContestPhaseAttribute) session.get(ProposalContestPhaseAttributeImpl.class,
                    primaryKey);

            if (proposalContestPhaseAttribute == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProposalContestPhaseAttributeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(proposalContestPhaseAttribute);
        } catch (NoSuchProposalContestPhaseAttributeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ProposalContestPhaseAttribute removeImpl(
        ProposalContestPhaseAttribute proposalContestPhaseAttribute)
        throws SystemException {
        proposalContestPhaseAttribute = toUnwrappedModel(proposalContestPhaseAttribute);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, proposalContestPhaseAttribute);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(proposalContestPhaseAttribute);

        return proposalContestPhaseAttribute;
    }

    @Override
    public ProposalContestPhaseAttribute updateImpl(
        com.ext.portlet.model.ProposalContestPhaseAttribute proposalContestPhaseAttribute,
        boolean merge) throws SystemException {
        proposalContestPhaseAttribute = toUnwrappedModel(proposalContestPhaseAttribute);

        boolean isNew = proposalContestPhaseAttribute.isNew();

        ProposalContestPhaseAttributeModelImpl proposalContestPhaseAttributeModelImpl =
            (ProposalContestPhaseAttributeModelImpl) proposalContestPhaseAttribute;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, proposalContestPhaseAttribute,
                merge);

            proposalContestPhaseAttribute.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew ||
                !ProposalContestPhaseAttributeModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((proposalContestPhaseAttributeModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(proposalContestPhaseAttributeModelImpl.getOriginalProposalId()),
                        Long.valueOf(proposalContestPhaseAttributeModelImpl.getOriginalContestPhaseId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID,
                    args);

                args = new Object[] {
                        Long.valueOf(proposalContestPhaseAttributeModelImpl.getProposalId()),
                        Long.valueOf(proposalContestPhaseAttributeModelImpl.getContestPhaseId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALIDCONTESTPHASEID,
                    args);
            }
        }

        EntityCacheUtil.putResult(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeImpl.class,
            proposalContestPhaseAttribute.getPrimaryKey(),
            proposalContestPhaseAttribute);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
                new Object[] {
                    Long.valueOf(proposalContestPhaseAttribute.getProposalId()),
                    Long.valueOf(
                        proposalContestPhaseAttribute.getContestPhaseId()),
                    
                proposalContestPhaseAttribute.getName(),
                    Long.valueOf(
                        proposalContestPhaseAttribute.getAdditionalId())
                }, proposalContestPhaseAttribute);
        } else {
            if ((proposalContestPhaseAttributeModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(proposalContestPhaseAttributeModelImpl.getOriginalProposalId()),
                        Long.valueOf(proposalContestPhaseAttributeModelImpl.getOriginalContestPhaseId()),
                        
                        proposalContestPhaseAttributeModelImpl.getOriginalName(),
                        Long.valueOf(proposalContestPhaseAttributeModelImpl.getOriginalAdditionalId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
                    new Object[] {
                        Long.valueOf(
                            proposalContestPhaseAttribute.getProposalId()),
                        Long.valueOf(
                            proposalContestPhaseAttribute.getContestPhaseId()),
                        
                    proposalContestPhaseAttribute.getName(),
                        Long.valueOf(
                            proposalContestPhaseAttribute.getAdditionalId())
                    }, proposalContestPhaseAttribute);
            }
        }

        return proposalContestPhaseAttribute;
    }

    protected ProposalContestPhaseAttribute toUnwrappedModel(
        ProposalContestPhaseAttribute proposalContestPhaseAttribute) {
        if (proposalContestPhaseAttribute instanceof ProposalContestPhaseAttributeImpl) {
            return proposalContestPhaseAttribute;
        }

        ProposalContestPhaseAttributeImpl proposalContestPhaseAttributeImpl = new ProposalContestPhaseAttributeImpl();

        proposalContestPhaseAttributeImpl.setNew(proposalContestPhaseAttribute.isNew());
        proposalContestPhaseAttributeImpl.setPrimaryKey(proposalContestPhaseAttribute.getPrimaryKey());

        proposalContestPhaseAttributeImpl.setId(proposalContestPhaseAttribute.getId());
        proposalContestPhaseAttributeImpl.setProposalId(proposalContestPhaseAttribute.getProposalId());
        proposalContestPhaseAttributeImpl.setContestPhaseId(proposalContestPhaseAttribute.getContestPhaseId());
        proposalContestPhaseAttributeImpl.setName(proposalContestPhaseAttribute.getName());
        proposalContestPhaseAttributeImpl.setAdditionalId(proposalContestPhaseAttribute.getAdditionalId());
        proposalContestPhaseAttributeImpl.setNumericValue(proposalContestPhaseAttribute.getNumericValue());
        proposalContestPhaseAttributeImpl.setStringValue(proposalContestPhaseAttribute.getStringValue());
        proposalContestPhaseAttributeImpl.setRealValue(proposalContestPhaseAttribute.getRealValue());

        return proposalContestPhaseAttributeImpl;
    }

    /**
     * Returns the proposal contest phase attribute with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the proposal contest phase attribute
     * @return the proposal contest phase attribute
     * @throws com.liferay.portal.NoSuchModelException if a proposal contest phase attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute findByPrimaryKey(
        Serializable primaryKey) throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the proposal contest phase attribute with the primary key or throws a {@link com.ext.portlet.NoSuchProposalContestPhaseAttributeException} if it could not be found.
     *
     * @param id the primary key of the proposal contest phase attribute
     * @return the proposal contest phase attribute
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a proposal contest phase attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalContestPhaseAttribute findByPrimaryKey(long id)
        throws NoSuchProposalContestPhaseAttributeException, SystemException {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = fetchByPrimaryKey(id);

        if (proposalContestPhaseAttribute == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
            }

            throw new NoSuchProposalContestPhaseAttributeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                id);
        }

        return proposalContestPhaseAttribute;
    }

    /**
     * Returns the proposal contest phase attribute with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the proposal contest phase attribute
     * @return the proposal contest phase attribute, or <code>null</code> if a proposal contest phase attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttribute fetchByPrimaryKey(
        Serializable primaryKey) throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the proposal contest phase attribute with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the proposal contest phase attribute
     * @return the proposal contest phase attribute, or <code>null</code> if a proposal contest phase attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalContestPhaseAttribute fetchByPrimaryKey(long id)
        throws SystemException {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = (ProposalContestPhaseAttribute) EntityCacheUtil.getResult(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
                ProposalContestPhaseAttributeImpl.class, id);

        if (proposalContestPhaseAttribute == _nullProposalContestPhaseAttribute) {
            return null;
        }

        if (proposalContestPhaseAttribute == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                proposalContestPhaseAttribute = (ProposalContestPhaseAttribute) session.get(ProposalContestPhaseAttributeImpl.class,
                        Long.valueOf(id));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (proposalContestPhaseAttribute != null) {
                    cacheResult(proposalContestPhaseAttribute);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(ProposalContestPhaseAttributeModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalContestPhaseAttributeImpl.class, id,
                        _nullProposalContestPhaseAttribute);
                }

                closeSession(session);
            }
        }

        return proposalContestPhaseAttribute;
    }

    /**
     * Returns the proposal contest phase attribute where proposalId = &#63; and contestPhaseId = &#63; and name = &#63; and additionalId = &#63; or throws a {@link com.ext.portlet.NoSuchProposalContestPhaseAttributeException} if it could not be found.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param name the name
     * @param additionalId the additional ID
     * @return the matching proposal contest phase attribute
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a matching proposal contest phase attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalContestPhaseAttribute findByProposalIdContestPhaseIdNameAdditionalId(
        long proposalId, long contestPhaseId, String name, long additionalId)
        throws NoSuchProposalContestPhaseAttributeException, SystemException {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = fetchByProposalIdContestPhaseIdNameAdditionalId(proposalId,
                contestPhaseId, name, additionalId);

        if (proposalContestPhaseAttribute == null) {
            StringBundler msg = new StringBundler(10);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("proposalId=");
            msg.append(proposalId);

            msg.append(", contestPhaseId=");
            msg.append(contestPhaseId);

            msg.append(", name=");
            msg.append(name);

            msg.append(", additionalId=");
            msg.append(additionalId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchProposalContestPhaseAttributeException(msg.toString());
        }

        return proposalContestPhaseAttribute;
    }

    /**
     * Returns the proposal contest phase attribute where proposalId = &#63; and contestPhaseId = &#63; and name = &#63; and additionalId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param name the name
     * @param additionalId the additional ID
     * @return the matching proposal contest phase attribute, or <code>null</code> if a matching proposal contest phase attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalContestPhaseAttribute fetchByProposalIdContestPhaseIdNameAdditionalId(
        long proposalId, long contestPhaseId, String name, long additionalId)
        throws SystemException {
        return fetchByProposalIdContestPhaseIdNameAdditionalId(proposalId,
            contestPhaseId, name, additionalId, true);
    }

    /**
     * Returns the proposal contest phase attribute where proposalId = &#63; and contestPhaseId = &#63; and name = &#63; and additionalId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param name the name
     * @param additionalId the additional ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching proposal contest phase attribute, or <code>null</code> if a matching proposal contest phase attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalContestPhaseAttribute fetchByProposalIdContestPhaseIdNameAdditionalId(
        long proposalId, long contestPhaseId, String name, long additionalId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] {
                proposalId, contestPhaseId, name, additionalId
            };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(5);

            query.append(_SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_PROPOSALID_2);

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_CONTESTPHASEID_2);

            if (name == null) {
                query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_NAME_1);
            } else {
                if (name.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_NAME_3);
                } else {
                    query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_NAME_2);
                }
            }

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_ADDITIONALID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                qPos.add(contestPhaseId);

                if (name != null) {
                    qPos.add(name);
                }

                qPos.add(additionalId);

                List<ProposalContestPhaseAttribute> list = q.list();

                result = list;

                ProposalContestPhaseAttribute proposalContestPhaseAttribute = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
                        finderArgs, list);
                } else {
                    proposalContestPhaseAttribute = list.get(0);

                    cacheResult(proposalContestPhaseAttribute);

                    if ((proposalContestPhaseAttribute.getProposalId() != proposalId) ||
                            (proposalContestPhaseAttribute.getContestPhaseId() != contestPhaseId) ||
                            (proposalContestPhaseAttribute.getName() == null) ||
                            !proposalContestPhaseAttribute.getName().equals(name) ||
                            (proposalContestPhaseAttribute.getAdditionalId() != additionalId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
                            finderArgs, proposalContestPhaseAttribute);
                    }
                }

                return proposalContestPhaseAttribute;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (ProposalContestPhaseAttribute) result;
            }
        }
    }

    /**
     * Returns all the proposal contest phase attributes where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @return the matching proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalContestPhaseAttribute> findByProposalIdContestPhaseId(
        long proposalId, long contestPhaseId) throws SystemException {
        return findByProposalIdContestPhaseId(proposalId, contestPhaseId,
            QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal contest phase attributes where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param start the lower bound of the range of proposal contest phase attributes
     * @param end the upper bound of the range of proposal contest phase attributes (not inclusive)
     * @return the range of matching proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalContestPhaseAttribute> findByProposalIdContestPhaseId(
        long proposalId, long contestPhaseId, int start, int end)
        throws SystemException {
        return findByProposalIdContestPhaseId(proposalId, contestPhaseId,
            start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal contest phase attributes where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param start the lower bound of the range of proposal contest phase attributes
     * @param end the upper bound of the range of proposal contest phase attributes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalContestPhaseAttribute> findByProposalIdContestPhaseId(
        long proposalId, long contestPhaseId, int start, int end,
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

        List<ProposalContestPhaseAttribute> list = (List<ProposalContestPhaseAttribute>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTE_WHERE);

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

                list = (List<ProposalContestPhaseAttribute>) QueryUtil.list(q,
                        getDialect(), start, end);
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
     * Returns the first proposal contest phase attribute in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching proposal contest phase attribute
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a matching proposal contest phase attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalContestPhaseAttribute findByProposalIdContestPhaseId_First(
        long proposalId, long contestPhaseId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalContestPhaseAttributeException, SystemException {
        List<ProposalContestPhaseAttribute> list = findByProposalIdContestPhaseId(proposalId,
                contestPhaseId, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("proposalId=");
            msg.append(proposalId);

            msg.append(", contestPhaseId=");
            msg.append(contestPhaseId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchProposalContestPhaseAttributeException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last proposal contest phase attribute in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching proposal contest phase attribute
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a matching proposal contest phase attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalContestPhaseAttribute findByProposalIdContestPhaseId_Last(
        long proposalId, long contestPhaseId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalContestPhaseAttributeException, SystemException {
        int count = countByProposalIdContestPhaseId(proposalId, contestPhaseId);

        List<ProposalContestPhaseAttribute> list = findByProposalIdContestPhaseId(proposalId,
                contestPhaseId, count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("proposalId=");
            msg.append(proposalId);

            msg.append(", contestPhaseId=");
            msg.append(contestPhaseId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchProposalContestPhaseAttributeException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the proposal contest phase attributes before and after the current proposal contest phase attribute in the ordered set where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param id the primary key of the current proposal contest phase attribute
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next proposal contest phase attribute
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a proposal contest phase attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalContestPhaseAttribute[] findByProposalIdContestPhaseId_PrevAndNext(
        long id, long proposalId, long contestPhaseId,
        OrderByComparator orderByComparator)
        throws NoSuchProposalContestPhaseAttributeException, SystemException {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            ProposalContestPhaseAttribute[] array = new ProposalContestPhaseAttributeImpl[3];

            array[0] = getByProposalIdContestPhaseId_PrevAndNext(session,
                    proposalContestPhaseAttribute, proposalId, contestPhaseId,
                    orderByComparator, true);

            array[1] = proposalContestPhaseAttribute;

            array[2] = getByProposalIdContestPhaseId_PrevAndNext(session,
                    proposalContestPhaseAttribute, proposalId, contestPhaseId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ProposalContestPhaseAttribute getByProposalIdContestPhaseId_PrevAndNext(
        Session session,
        ProposalContestPhaseAttribute proposalContestPhaseAttribute,
        long proposalId, long contestPhaseId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTE_WHERE);

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
            Object[] values = orderByComparator.getOrderByConditionValues(proposalContestPhaseAttribute);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ProposalContestPhaseAttribute> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the proposal contest phase attributes.
     *
     * @return the proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalContestPhaseAttribute> findAll()
        throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal contest phase attributes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of proposal contest phase attributes
     * @param end the upper bound of the range of proposal contest phase attributes (not inclusive)
     * @return the range of proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalContestPhaseAttribute> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal contest phase attributes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of proposal contest phase attributes
     * @param end the upper bound of the range of proposal contest phase attributes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalContestPhaseAttribute> findAll(int start, int end,
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

        List<ProposalContestPhaseAttribute> list = (List<ProposalContestPhaseAttribute>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTE;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<ProposalContestPhaseAttribute>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ProposalContestPhaseAttribute>) QueryUtil.list(q,
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
     * Removes the proposal contest phase attribute where proposalId = &#63; and contestPhaseId = &#63; and name = &#63; and additionalId = &#63; from the database.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param name the name
     * @param additionalId the additional ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByProposalIdContestPhaseIdNameAdditionalId(
        long proposalId, long contestPhaseId, String name, long additionalId)
        throws NoSuchProposalContestPhaseAttributeException, SystemException {
        ProposalContestPhaseAttribute proposalContestPhaseAttribute = findByProposalIdContestPhaseIdNameAdditionalId(proposalId,
                contestPhaseId, name, additionalId);

        remove(proposalContestPhaseAttribute);
    }

    /**
     * Removes all the proposal contest phase attributes where proposalId = &#63; and contestPhaseId = &#63; from the database.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByProposalIdContestPhaseId(long proposalId,
        long contestPhaseId) throws SystemException {
        for (ProposalContestPhaseAttribute proposalContestPhaseAttribute : findByProposalIdContestPhaseId(
                proposalId, contestPhaseId)) {
            remove(proposalContestPhaseAttribute);
        }
    }

    /**
     * Removes all the proposal contest phase attributes from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (ProposalContestPhaseAttribute proposalContestPhaseAttribute : findAll()) {
            remove(proposalContestPhaseAttribute);
        }
    }

    /**
     * Returns the number of proposal contest phase attributes where proposalId = &#63; and contestPhaseId = &#63; and name = &#63; and additionalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @param name the name
     * @param additionalId the additional ID
     * @return the number of matching proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    public int countByProposalIdContestPhaseIdNameAdditionalId(
        long proposalId, long contestPhaseId, String name, long additionalId)
        throws SystemException {
        Object[] finderArgs = new Object[] {
                proposalId, contestPhaseId, name, additionalId
            };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(5);

            query.append(_SQL_COUNT_PROPOSALCONTESTPHASEATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_PROPOSALID_2);

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_CONTESTPHASEID_2);

            if (name == null) {
                query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_NAME_1);
            } else {
                if (name.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_NAME_3);
                } else {
                    query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_NAME_2);
                }
            }

            query.append(_FINDER_COLUMN_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID_ADDITIONALID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                qPos.add(contestPhaseId);

                if (name != null) {
                    qPos.add(name);
                }

                qPos.add(additionalId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEIDNAMEADDITIONALID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of proposal contest phase attributes where proposalId = &#63; and contestPhaseId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param contestPhaseId the contest phase ID
     * @return the number of matching proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    public int countByProposalIdContestPhaseId(long proposalId,
        long contestPhaseId) throws SystemException {
        Object[] finderArgs = new Object[] { proposalId, contestPhaseId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PROPOSALIDCONTESTPHASEID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PROPOSALCONTESTPHASEATTRIBUTE_WHERE);

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
     * Returns the number of proposal contest phase attributes.
     *
     * @return the number of proposal contest phase attributes
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_PROPOSALCONTESTPHASEATTRIBUTE);

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
     * Initializes the proposal contest phase attribute persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ProposalContestPhaseAttribute")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ProposalContestPhaseAttribute>> listenersList =
                    new ArrayList<ModelListener<ProposalContestPhaseAttribute>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ProposalContestPhaseAttribute>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProposalContestPhaseAttributeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
