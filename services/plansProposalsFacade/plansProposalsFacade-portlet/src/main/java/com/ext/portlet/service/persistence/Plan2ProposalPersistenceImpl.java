package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPlan2ProposalException;
import com.ext.portlet.model.Plan2Proposal;
import com.ext.portlet.model.impl.Plan2ProposalImpl;
import com.ext.portlet.model.impl.Plan2ProposalModelImpl;
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
=======
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)
import com.ext.portlet.service.persistence.Plan2ProposalPersistence;

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
 * The persistence implementation for the plan2 proposal service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Plan2ProposalPersistence
 * @see Plan2ProposalUtil
 * @generated
 */
public class Plan2ProposalPersistenceImpl extends BasePersistenceImpl<Plan2Proposal>
    implements Plan2ProposalPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link Plan2ProposalUtil} to access the plan2 proposal persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = Plan2ProposalImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(Plan2ProposalModelImpl.ENTITY_CACHE_ENABLED,
            Plan2ProposalModelImpl.FINDER_CACHE_ENABLED,
            Plan2ProposalImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(Plan2ProposalModelImpl.ENTITY_CACHE_ENABLED,
            Plan2ProposalModelImpl.FINDER_CACHE_ENABLED,
            Plan2ProposalImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(Plan2ProposalModelImpl.ENTITY_CACHE_ENABLED,
            Plan2ProposalModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALID =
        new FinderPath(Plan2ProposalModelImpl.ENTITY_CACHE_ENABLED,
            Plan2ProposalModelImpl.FINDER_CACHE_ENABLED,
            Plan2ProposalImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByProposalId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID =
        new FinderPath(Plan2ProposalModelImpl.ENTITY_CACHE_ENABLED,
            Plan2ProposalModelImpl.FINDER_CACHE_ENABLED,
            Plan2ProposalImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByProposalId", new String[] { Long.class.getName() },
            Plan2ProposalModelImpl.PROPOSALID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PROPOSALID = new FinderPath(Plan2ProposalModelImpl.ENTITY_CACHE_ENABLED,
            Plan2ProposalModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByProposalId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_PROPOSALID_PROPOSALID_2 = "plan2Proposal.proposalId = ?";
    private static final String _SQL_SELECT_PLAN2PROPOSAL = "SELECT plan2Proposal FROM Plan2Proposal plan2Proposal";
    private static final String _SQL_SELECT_PLAN2PROPOSAL_WHERE = "SELECT plan2Proposal FROM Plan2Proposal plan2Proposal WHERE ";
    private static final String _SQL_COUNT_PLAN2PROPOSAL = "SELECT COUNT(plan2Proposal) FROM Plan2Proposal plan2Proposal";
    private static final String _SQL_COUNT_PLAN2PROPOSAL_WHERE = "SELECT COUNT(plan2Proposal) FROM Plan2Proposal plan2Proposal WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "plan2Proposal.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Plan2Proposal exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Plan2Proposal exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(Plan2ProposalPersistenceImpl.class);
    private static Plan2Proposal _nullPlan2Proposal = new Plan2ProposalImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<Plan2Proposal> toCacheModel() {
                return _nullPlan2ProposalCacheModel;
            }
        };

    private static CacheModel<Plan2Proposal> _nullPlan2ProposalCacheModel = new CacheModel<Plan2Proposal>() {
            @Override
            public Plan2Proposal toEntityModel() {
                return _nullPlan2Proposal;
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
=======
    public Plan2ProposalPersistenceImpl() {
        setModelClass(Plan2Proposal.class);
    }
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)

    /**
     * Returns all the plan2 proposals where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @return the matching plan2 proposals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Plan2Proposal> findByProposalId(long proposalId)
        throws SystemException {
        return findByProposalId(proposalId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan2 proposals where proposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Plan2ProposalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param start the lower bound of the range of plan2 proposals
     * @param end the upper bound of the range of plan2 proposals (not inclusive)
     * @return the range of matching plan2 proposals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Plan2Proposal> findByProposalId(long proposalId, int start,
        int end) throws SystemException {
        return findByProposalId(proposalId, start, end, null);
    }

    /**
     * Returns an ordered range of all the plan2 proposals where proposalId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Plan2ProposalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param proposalId the proposal ID
     * @param start the lower bound of the range of plan2 proposals
     * @param end the upper bound of the range of plan2 proposals (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching plan2 proposals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Plan2Proposal> findByProposalId(long proposalId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID;
            finderArgs = new Object[] { proposalId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PROPOSALID;
            finderArgs = new Object[] { proposalId, start, end, orderByComparator };
        }

        List<Plan2Proposal> list = (List<Plan2Proposal>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (Plan2Proposal plan2Proposal : list) {
                if ((proposalId != plan2Proposal.getProposalId())) {
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

            query.append(_SQL_SELECT_PLAN2PROPOSAL_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(Plan2ProposalModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

                if (!pagination) {
                    list = (List<Plan2Proposal>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Plan2Proposal>(list);
                } else {
                    list = (List<Plan2Proposal>) QueryUtil.list(q,
                            getDialect(), start, end);
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
     * Returns the first plan2 proposal in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan2 proposal
     * @throws com.ext.portlet.NoSuchPlan2ProposalException if a matching plan2 proposal could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Plan2Proposal findByProposalId_First(long proposalId,
        OrderByComparator orderByComparator)
        throws NoSuchPlan2ProposalException, SystemException {
        Plan2Proposal plan2Proposal = fetchByProposalId_First(proposalId,
                orderByComparator);

        if (plan2Proposal != null) {
            return plan2Proposal;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("proposalId=");
        msg.append(proposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPlan2ProposalException(msg.toString());
    }

    /**
     * Returns the first plan2 proposal in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan2 proposal, or <code>null</code> if a matching plan2 proposal could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Plan2Proposal fetchByProposalId_First(long proposalId,
        OrderByComparator orderByComparator) throws SystemException {
        List<Plan2Proposal> list = findByProposalId(proposalId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last plan2 proposal in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan2 proposal
     * @throws com.ext.portlet.NoSuchPlan2ProposalException if a matching plan2 proposal could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Plan2Proposal findByProposalId_Last(long proposalId,
        OrderByComparator orderByComparator)
        throws NoSuchPlan2ProposalException, SystemException {
        Plan2Proposal plan2Proposal = fetchByProposalId_Last(proposalId,
                orderByComparator);

        if (plan2Proposal != null) {
            return plan2Proposal;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("proposalId=");
        msg.append(proposalId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPlan2ProposalException(msg.toString());
    }

    /**
     * Returns the last plan2 proposal in the ordered set where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan2 proposal, or <code>null</code> if a matching plan2 proposal could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Plan2Proposal fetchByProposalId_Last(long proposalId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByProposalId(proposalId);

        if (count == 0) {
            return null;
        }

        List<Plan2Proposal> list = findByProposalId(proposalId, count - 1,
                count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the plan2 proposals before and after the current plan2 proposal in the ordered set where proposalId = &#63;.
     *
     * @param planId the primary key of the current plan2 proposal
     * @param proposalId the proposal ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next plan2 proposal
     * @throws com.ext.portlet.NoSuchPlan2ProposalException if a plan2 proposal with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Plan2Proposal[] findByProposalId_PrevAndNext(long planId,
        long proposalId, OrderByComparator orderByComparator)
        throws NoSuchPlan2ProposalException, SystemException {
        Plan2Proposal plan2Proposal = findByPrimaryKey(planId);

        Session session = null;

        try {
            session = openSession();

            Plan2Proposal[] array = new Plan2ProposalImpl[3];

            array[0] = getByProposalId_PrevAndNext(session, plan2Proposal,
                    proposalId, orderByComparator, true);

            array[1] = plan2Proposal;

            array[2] = getByProposalId_PrevAndNext(session, plan2Proposal,
                    proposalId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Plan2Proposal getByProposalId_PrevAndNext(Session session,
        Plan2Proposal plan2Proposal, long proposalId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PLAN2PROPOSAL_WHERE);

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
        } else {
            query.append(Plan2ProposalModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(proposalId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(plan2Proposal);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Plan2Proposal> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the plan2 proposals where proposalId = &#63; from the database.
     *
     * @param proposalId the proposal ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByProposalId(long proposalId) throws SystemException {
        for (Plan2Proposal plan2Proposal : findByProposalId(proposalId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(plan2Proposal);
        }
    }

    /**
     * Returns the number of plan2 proposals where proposalId = &#63;.
     *
     * @param proposalId the proposal ID
     * @return the number of matching plan2 proposals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByProposalId(long proposalId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PROPOSALID;

        Object[] finderArgs = new Object[] { proposalId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PLAN2PROPOSAL_WHERE);

            query.append(_FINDER_COLUMN_PROPOSALID_PROPOSALID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(proposalId);

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
     * Caches the plan2 proposal in the entity cache if it is enabled.
     *
     * @param plan2Proposal the plan2 proposal
     */
    @Override
    public void cacheResult(Plan2Proposal plan2Proposal) {
        EntityCacheUtil.putResult(Plan2ProposalModelImpl.ENTITY_CACHE_ENABLED,
            Plan2ProposalImpl.class, plan2Proposal.getPrimaryKey(),
            plan2Proposal);

        plan2Proposal.resetOriginalValues();
    }

    /**
     * Caches the plan2 proposals in the entity cache if it is enabled.
     *
     * @param plan2Proposals the plan2 proposals
     */
    @Override
    public void cacheResult(List<Plan2Proposal> plan2Proposals) {
        for (Plan2Proposal plan2Proposal : plan2Proposals) {
            if (EntityCacheUtil.getResult(
                        Plan2ProposalModelImpl.ENTITY_CACHE_ENABLED,
                        Plan2ProposalImpl.class, plan2Proposal.getPrimaryKey()) == null) {
                cacheResult(plan2Proposal);
            } else {
                plan2Proposal.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan2 proposals.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(Plan2ProposalImpl.class.getName());
        }

        EntityCacheUtil.clearCache(Plan2ProposalImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan2 proposal.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(Plan2Proposal plan2Proposal) {
        EntityCacheUtil.removeResult(Plan2ProposalModelImpl.ENTITY_CACHE_ENABLED,
            Plan2ProposalImpl.class, plan2Proposal.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<Plan2Proposal> plan2Proposals) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (Plan2Proposal plan2Proposal : plan2Proposals) {
            EntityCacheUtil.removeResult(Plan2ProposalModelImpl.ENTITY_CACHE_ENABLED,
                Plan2ProposalImpl.class, plan2Proposal.getPrimaryKey());
        }
    }

    /**
     * Creates a new plan2 proposal with the primary key. Does not add the plan2 proposal to the database.
     *
     * @param planId the primary key for the new plan2 proposal
     * @return the new plan2 proposal
     */
    @Override
    public Plan2Proposal create(long planId) {
        Plan2Proposal plan2Proposal = new Plan2ProposalImpl();

        plan2Proposal.setNew(true);
        plan2Proposal.setPrimaryKey(planId);

        return plan2Proposal;
    }

    /**
     * Removes the plan2 proposal with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param planId the primary key of the plan2 proposal
     * @return the plan2 proposal that was removed
     * @throws com.ext.portlet.NoSuchPlan2ProposalException if a plan2 proposal with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Plan2Proposal remove(long planId)
        throws NoSuchPlan2ProposalException, SystemException {
        return remove((Serializable) planId);
    }

    /**
     * Removes the plan2 proposal with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan2 proposal
     * @return the plan2 proposal that was removed
     * @throws com.ext.portlet.NoSuchPlan2ProposalException if a plan2 proposal with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Plan2Proposal remove(Serializable primaryKey)
        throws NoSuchPlan2ProposalException, SystemException {
        Session session = null;

        try {
            session = openSession();

            Plan2Proposal plan2Proposal = (Plan2Proposal) session.get(Plan2ProposalImpl.class,
                    primaryKey);

            if (plan2Proposal == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlan2ProposalException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(plan2Proposal);
        } catch (NoSuchPlan2ProposalException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected Plan2Proposal removeImpl(Plan2Proposal plan2Proposal)
        throws SystemException {
        plan2Proposal = toUnwrappedModel(plan2Proposal);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(plan2Proposal)) {
                plan2Proposal = (Plan2Proposal) session.get(Plan2ProposalImpl.class,
                        plan2Proposal.getPrimaryKeyObj());
            }

            if (plan2Proposal != null) {
                session.delete(plan2Proposal);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (plan2Proposal != null) {
            clearCache(plan2Proposal);
        }

        return plan2Proposal;
    }

    @Override
    public Plan2Proposal updateImpl(
        com.ext.portlet.model.Plan2Proposal plan2Proposal)
        throws SystemException {
        plan2Proposal = toUnwrappedModel(plan2Proposal);

        boolean isNew = plan2Proposal.isNew();

        Plan2ProposalModelImpl plan2ProposalModelImpl = (Plan2ProposalModelImpl) plan2Proposal;

        Session session = null;

        try {
            session = openSession();

            if (plan2Proposal.isNew()) {
                session.save(plan2Proposal);

                plan2Proposal.setNew(false);
            } else {
                session.merge(plan2Proposal);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !Plan2ProposalModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((plan2ProposalModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        plan2ProposalModelImpl.getOriginalProposalId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID,
                    args);

                args = new Object[] { plan2ProposalModelImpl.getProposalId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PROPOSALID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PROPOSALID,
                    args);
            }
        }

        EntityCacheUtil.putResult(Plan2ProposalModelImpl.ENTITY_CACHE_ENABLED,
            Plan2ProposalImpl.class, plan2Proposal.getPrimaryKey(),
            plan2Proposal);

        return plan2Proposal;
    }

    protected Plan2Proposal toUnwrappedModel(Plan2Proposal plan2Proposal) {
        if (plan2Proposal instanceof Plan2ProposalImpl) {
            return plan2Proposal;
        }

        Plan2ProposalImpl plan2ProposalImpl = new Plan2ProposalImpl();

        plan2ProposalImpl.setNew(plan2Proposal.isNew());
        plan2ProposalImpl.setPrimaryKey(plan2Proposal.getPrimaryKey());

        plan2ProposalImpl.setPlanId(plan2Proposal.getPlanId());
        plan2ProposalImpl.setProposalId(plan2Proposal.getProposalId());

        return plan2ProposalImpl;
    }

    /**
     * Returns the plan2 proposal with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan2 proposal
     * @return the plan2 proposal
     * @throws com.ext.portlet.NoSuchPlan2ProposalException if a plan2 proposal with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Plan2Proposal findByPrimaryKey(Serializable primaryKey)
        throws NoSuchPlan2ProposalException, SystemException {
        Plan2Proposal plan2Proposal = fetchByPrimaryKey(primaryKey);

        if (plan2Proposal == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchPlan2ProposalException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return plan2Proposal;
    }

    /**
     * Returns the plan2 proposal with the primary key or throws a {@link com.ext.portlet.NoSuchPlan2ProposalException} if it could not be found.
     *
     * @param planId the primary key of the plan2 proposal
     * @return the plan2 proposal
     * @throws com.ext.portlet.NoSuchPlan2ProposalException if a plan2 proposal with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Plan2Proposal findByPrimaryKey(long planId)
        throws NoSuchPlan2ProposalException, SystemException {
        return findByPrimaryKey((Serializable) planId);
    }

    /**
     * Returns the plan2 proposal with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan2 proposal
     * @return the plan2 proposal, or <code>null</code> if a plan2 proposal with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Plan2Proposal fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        Plan2Proposal plan2Proposal = (Plan2Proposal) EntityCacheUtil.getResult(Plan2ProposalModelImpl.ENTITY_CACHE_ENABLED,
                Plan2ProposalImpl.class, primaryKey);

        if (plan2Proposal == _nullPlan2Proposal) {
            return null;
        }

        if (plan2Proposal == null) {
            Session session = null;

            try {
                session = openSession();

                plan2Proposal = (Plan2Proposal) session.get(Plan2ProposalImpl.class,
                        primaryKey);

                if (plan2Proposal != null) {
                    cacheResult(plan2Proposal);
                } else {
                    EntityCacheUtil.putResult(Plan2ProposalModelImpl.ENTITY_CACHE_ENABLED,
                        Plan2ProposalImpl.class, primaryKey, _nullPlan2Proposal);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(Plan2ProposalModelImpl.ENTITY_CACHE_ENABLED,
                    Plan2ProposalImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return plan2Proposal;
    }

    /**
     * Returns the plan2 proposal with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param planId the primary key of the plan2 proposal
     * @return the plan2 proposal, or <code>null</code> if a plan2 proposal with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Plan2Proposal fetchByPrimaryKey(long planId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) planId);
    }

    /**
     * Returns all the plan2 proposals.
     *
     * @return the plan2 proposals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Plan2Proposal> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan2 proposals.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Plan2ProposalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan2 proposals
     * @param end the upper bound of the range of plan2 proposals (not inclusive)
     * @return the range of plan2 proposals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Plan2Proposal> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan2 proposals.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Plan2ProposalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan2 proposals
     * @param end the upper bound of the range of plan2 proposals (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan2 proposals
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<Plan2Proposal> findAll(int start, int end,
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

        List<Plan2Proposal> list = (List<Plan2Proposal>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLAN2PROPOSAL);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLAN2PROPOSAL;

                if (pagination) {
                    sql = sql.concat(Plan2ProposalModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<Plan2Proposal>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<Plan2Proposal>(list);
                } else {
                    list = (List<Plan2Proposal>) QueryUtil.list(q,
                            getDialect(), start, end);
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
     * Removes all the plan2 proposals from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (Plan2Proposal plan2Proposal : findAll()) {
            remove(plan2Proposal);
        }
    }

    /**
     * Returns the number of plan2 proposals.
     *
     * @return the number of plan2 proposals
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

                Query q = session.createQuery(_SQL_COUNT_PLAN2PROPOSAL);

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
     * Initializes the plan2 proposal persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.Plan2Proposal")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<Plan2Proposal>> listenersList = new ArrayList<ModelListener<Plan2Proposal>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<Plan2Proposal>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(Plan2ProposalImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
