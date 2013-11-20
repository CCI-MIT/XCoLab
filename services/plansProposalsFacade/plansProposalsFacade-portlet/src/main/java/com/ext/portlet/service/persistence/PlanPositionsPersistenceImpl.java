package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPlanPositionsException;
import com.ext.portlet.model.PlanPositions;
import com.ext.portlet.model.impl.PlanPositionsImpl;
import com.ext.portlet.model.impl.PlanPositionsModelImpl;
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
=======
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)
import com.ext.portlet.service.persistence.PlanPositionsPersistence;

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
import com.liferay.portal.kernel.util.SetUtil;
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
import java.util.Set;

/**
 * The persistence implementation for the plan positions service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanPositionsPersistence
 * @see PlanPositionsUtil
 * @generated
 */
public class PlanPositionsPersistenceImpl extends BasePersistenceImpl<PlanPositions>
    implements PlanPositionsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanPositionsUtil} to access the plan positions persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanPositionsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionsModelImpl.FINDER_CACHE_ENABLED,
            PlanPositionsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionsModelImpl.FINDER_CACHE_ENABLED,
            PlanPositionsImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_FETCH_BY_CURRENTBYPLANID = new FinderPath(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionsModelImpl.FINDER_CACHE_ENABLED,
            PlanPositionsImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByCurrentByPlanId", new String[] { Long.class.getName() },
            PlanPositionsModelImpl.PLANID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CURRENTBYPLANID = new FinderPath(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByCurrentByPlanId", new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_CURRENTBYPLANID_PLANID_2 = "planPositions.planId = ?";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ALLBYPLANID =
        new FinderPath(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionsModelImpl.FINDER_CACHE_ENABLED,
            PlanPositionsImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByAllByPlanId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALLBYPLANID =
        new FinderPath(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionsModelImpl.FINDER_CACHE_ENABLED,
            PlanPositionsImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByAllByPlanId", new String[] { Long.class.getName() },
            PlanPositionsModelImpl.PLANID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ALLBYPLANID = new FinderPath(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAllByPlanId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_ALLBYPLANID_PLANID_2 = "planPositions.planId = ?";
    private static final String _SQL_SELECT_PLANPOSITIONS = "SELECT planPositions FROM PlanPositions planPositions";
    private static final String _SQL_SELECT_PLANPOSITIONS_WHERE = "SELECT planPositions FROM PlanPositions planPositions WHERE ";
    private static final String _SQL_COUNT_PLANPOSITIONS = "SELECT COUNT(planPositions) FROM PlanPositions planPositions";
    private static final String _SQL_COUNT_PLANPOSITIONS_WHERE = "SELECT COUNT(planPositions) FROM PlanPositions planPositions WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planPositions.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanPositions exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PlanPositions exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanPositionsPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static PlanPositions _nullPlanPositions = new PlanPositionsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanPositions> toCacheModel() {
                return _nullPlanPositionsCacheModel;
            }
        };

    private static CacheModel<PlanPositions> _nullPlanPositionsCacheModel = new CacheModel<PlanPositions>() {
            @Override
            public PlanPositions toEntityModel() {
                return _nullPlanPositions;
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
    public PlanPositionsPersistenceImpl() {
        setModelClass(PlanPositions.class);
    }
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)

    /**
     * Returns the plan positions where planId = &#63; or throws a {@link com.ext.portlet.NoSuchPlanPositionsException} if it could not be found.
     *
     * @param planId the plan ID
     * @return the matching plan positions
     * @throws com.ext.portlet.NoSuchPlanPositionsException if a matching plan positions could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPositions findByCurrentByPlanId(long planId)
        throws NoSuchPlanPositionsException, SystemException {
        PlanPositions planPositions = fetchByCurrentByPlanId(planId);

        if (planPositions == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("planId=");
            msg.append(planId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanPositionsException(msg.toString());
        }

        return planPositions;
    }

    /**
     * Returns the plan positions where planId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param planId the plan ID
     * @return the matching plan positions, or <code>null</code> if a matching plan positions could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPositions fetchByCurrentByPlanId(long planId)
        throws SystemException {
        return fetchByCurrentByPlanId(planId, true);
    }

    /**
     * Returns the plan positions where planId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param planId the plan ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching plan positions, or <code>null</code> if a matching plan positions could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPositions fetchByCurrentByPlanId(long planId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { planId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                    finderArgs, this);
        }

        if (result instanceof PlanPositions) {
            PlanPositions planPositions = (PlanPositions) result;

            if ((planId != planPositions.getPlanId())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_PLANPOSITIONS_WHERE);

            query.append(_FINDER_COLUMN_CURRENTBYPLANID_PLANID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planId);

                List<PlanPositions> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "PlanPositionsPersistenceImpl.fetchByCurrentByPlanId(long, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    PlanPositions planPositions = list.get(0);

                    result = planPositions;

                    cacheResult(planPositions);

                    if ((planPositions.getPlanId() != planId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                            finderArgs, planPositions);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (PlanPositions) result;
        }
    }

    /**
     * Removes the plan positions where planId = &#63; from the database.
     *
     * @param planId the plan ID
     * @return the plan positions that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPositions removeByCurrentByPlanId(long planId)
        throws NoSuchPlanPositionsException, SystemException {
        PlanPositions planPositions = findByCurrentByPlanId(planId);

        return remove(planPositions);
    }

    /**
     * Returns the number of plan positionses where planId = &#63;.
     *
     * @param planId the plan ID
     * @return the number of matching plan positionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCurrentByPlanId(long planId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CURRENTBYPLANID;

        Object[] finderArgs = new Object[] { planId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PLANPOSITIONS_WHERE);

            query.append(_FINDER_COLUMN_CURRENTBYPLANID_PLANID_2);

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
     * Returns all the plan positionses where planId = &#63;.
     *
     * @param planId the plan ID
     * @return the matching plan positionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanPositions> findByAllByPlanId(long planId)
        throws SystemException {
        return findByAllByPlanId(planId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the plan positionses where planId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param planId the plan ID
     * @param start the lower bound of the range of plan positionses
     * @param end the upper bound of the range of plan positionses (not inclusive)
     * @return the range of matching plan positionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanPositions> findByAllByPlanId(long planId, int start, int end)
        throws SystemException {
        return findByAllByPlanId(planId, start, end, null);
    }

    /**
     * Returns an ordered range of all the plan positionses where planId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param planId the plan ID
     * @param start the lower bound of the range of plan positionses
     * @param end the upper bound of the range of plan positionses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching plan positionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanPositions> findByAllByPlanId(long planId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALLBYPLANID;
            finderArgs = new Object[] { planId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ALLBYPLANID;
            finderArgs = new Object[] { planId, start, end, orderByComparator };
        }

        List<PlanPositions> list = (List<PlanPositions>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (PlanPositions planPositions : list) {
                if ((planId != planPositions.getPlanId())) {
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

            query.append(_SQL_SELECT_PLANPOSITIONS_WHERE);

            query.append(_FINDER_COLUMN_ALLBYPLANID_PLANID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(PlanPositionsModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planId);

                if (!pagination) {
                    list = (List<PlanPositions>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PlanPositions>(list);
                } else {
                    list = (List<PlanPositions>) QueryUtil.list(q,
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
     * Returns the first plan positions in the ordered set where planId = &#63;.
     *
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan positions
     * @throws com.ext.portlet.NoSuchPlanPositionsException if a matching plan positions could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPositions findByAllByPlanId_First(long planId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanPositionsException, SystemException {
        PlanPositions planPositions = fetchByAllByPlanId_First(planId,
                orderByComparator);

        if (planPositions != null) {
            return planPositions;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("planId=");
        msg.append(planId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPlanPositionsException(msg.toString());
    }

    /**
     * Returns the first plan positions in the ordered set where planId = &#63;.
     *
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan positions, or <code>null</code> if a matching plan positions could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPositions fetchByAllByPlanId_First(long planId,
        OrderByComparator orderByComparator) throws SystemException {
        List<PlanPositions> list = findByAllByPlanId(planId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last plan positions in the ordered set where planId = &#63;.
     *
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan positions
     * @throws com.ext.portlet.NoSuchPlanPositionsException if a matching plan positions could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPositions findByAllByPlanId_Last(long planId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanPositionsException, SystemException {
        PlanPositions planPositions = fetchByAllByPlanId_Last(planId,
                orderByComparator);

        if (planPositions != null) {
            return planPositions;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("planId=");
        msg.append(planId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchPlanPositionsException(msg.toString());
    }

    /**
     * Returns the last plan positions in the ordered set where planId = &#63;.
     *
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan positions, or <code>null</code> if a matching plan positions could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPositions fetchByAllByPlanId_Last(long planId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByAllByPlanId(planId);

        if (count == 0) {
            return null;
        }

        List<PlanPositions> list = findByAllByPlanId(planId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the plan positionses before and after the current plan positions in the ordered set where planId = &#63;.
     *
     * @param id the primary key of the current plan positions
     * @param planId the plan ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next plan positions
     * @throws com.ext.portlet.NoSuchPlanPositionsException if a plan positions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPositions[] findByAllByPlanId_PrevAndNext(long id, long planId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanPositionsException, SystemException {
        PlanPositions planPositions = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            PlanPositions[] array = new PlanPositionsImpl[3];

            array[0] = getByAllByPlanId_PrevAndNext(session, planPositions,
                    planId, orderByComparator, true);

            array[1] = planPositions;

            array[2] = getByAllByPlanId_PrevAndNext(session, planPositions,
                    planId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PlanPositions getByAllByPlanId_PrevAndNext(Session session,
        PlanPositions planPositions, long planId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PLANPOSITIONS_WHERE);

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
        } else {
            query.append(PlanPositionsModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(planId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(planPositions);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PlanPositions> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the plan positionses where planId = &#63; from the database.
     *
     * @param planId the plan ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByAllByPlanId(long planId) throws SystemException {
        for (PlanPositions planPositions : findByAllByPlanId(planId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(planPositions);
        }
    }

    /**
     * Returns the number of plan positionses where planId = &#63;.
     *
     * @param planId the plan ID
     * @return the number of matching plan positionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByAllByPlanId(long planId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_ALLBYPLANID;

        Object[] finderArgs = new Object[] { planId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PLANPOSITIONS_WHERE);

            query.append(_FINDER_COLUMN_ALLBYPLANID_PLANID_2);

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
     * Caches the plan positions in the entity cache if it is enabled.
     *
     * @param planPositions the plan positions
     */
    @Override
    public void cacheResult(PlanPositions planPositions) {
        EntityCacheUtil.putResult(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionsImpl.class, planPositions.getPrimaryKey(),
            planPositions);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
            new Object[] { planPositions.getPlanId() }, planPositions);

        planPositions.resetOriginalValues();
    }

    /**
     * Caches the plan positionses in the entity cache if it is enabled.
     *
     * @param planPositionses the plan positionses
     */
    @Override
    public void cacheResult(List<PlanPositions> planPositionses) {
        for (PlanPositions planPositions : planPositionses) {
            if (EntityCacheUtil.getResult(
                        PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
                        PlanPositionsImpl.class, planPositions.getPrimaryKey()) == null) {
                cacheResult(planPositions);
            } else {
                planPositions.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan positionses.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanPositionsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanPositionsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan positions.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanPositions planPositions) {
        EntityCacheUtil.removeResult(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionsImpl.class, planPositions.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(planPositions);
    }

    @Override
    public void clearCache(List<PlanPositions> planPositionses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanPositions planPositions : planPositionses) {
            EntityCacheUtil.removeResult(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
                PlanPositionsImpl.class, planPositions.getPrimaryKey());

            clearUniqueFindersCache(planPositions);
        }
    }

    protected void cacheUniqueFindersCache(PlanPositions planPositions) {
        if (planPositions.isNew()) {
            Object[] args = new Object[] { planPositions.getPlanId() };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CURRENTBYPLANID,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                args, planPositions);
        } else {
            PlanPositionsModelImpl planPositionsModelImpl = (PlanPositionsModelImpl) planPositions;

            if ((planPositionsModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_CURRENTBYPLANID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] { planPositions.getPlanId() };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CURRENTBYPLANID,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                    args, planPositions);
            }
        }
    }

    protected void clearUniqueFindersCache(PlanPositions planPositions) {
        PlanPositionsModelImpl planPositionsModelImpl = (PlanPositionsModelImpl) planPositions;

        Object[] args = new Object[] { planPositions.getPlanId() };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CURRENTBYPLANID, args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID, args);

        if ((planPositionsModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_CURRENTBYPLANID.getColumnBitmask()) != 0) {
            args = new Object[] { planPositionsModelImpl.getOriginalPlanId() };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CURRENTBYPLANID,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CURRENTBYPLANID,
                args);
        }
    }

    /**
     * Creates a new plan positions with the primary key. Does not add the plan positions to the database.
     *
     * @param id the primary key for the new plan positions
     * @return the new plan positions
     */
    @Override
    public PlanPositions create(long id) {
        PlanPositions planPositions = new PlanPositionsImpl();

        planPositions.setNew(true);
        planPositions.setPrimaryKey(id);

        return planPositions;
    }

    /**
     * Removes the plan positions with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the plan positions
     * @return the plan positions that was removed
     * @throws com.ext.portlet.NoSuchPlanPositionsException if a plan positions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPositions remove(long id)
        throws NoSuchPlanPositionsException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the plan positions with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan positions
     * @return the plan positions that was removed
     * @throws com.ext.portlet.NoSuchPlanPositionsException if a plan positions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPositions remove(Serializable primaryKey)
        throws NoSuchPlanPositionsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanPositions planPositions = (PlanPositions) session.get(PlanPositionsImpl.class,
                    primaryKey);

            if (planPositions == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanPositionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planPositions);
        } catch (NoSuchPlanPositionsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanPositions removeImpl(PlanPositions planPositions)
        throws SystemException {
        planPositions = toUnwrappedModel(planPositions);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(planPositions)) {
                planPositions = (PlanPositions) session.get(PlanPositionsImpl.class,
                        planPositions.getPrimaryKeyObj());
            }

            if (planPositions != null) {
                session.delete(planPositions);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (planPositions != null) {
            clearCache(planPositions);
        }

        return planPositions;
    }

    @Override
    public PlanPositions updateImpl(
        com.ext.portlet.model.PlanPositions planPositions)
        throws SystemException {
        planPositions = toUnwrappedModel(planPositions);

        boolean isNew = planPositions.isNew();

        PlanPositionsModelImpl planPositionsModelImpl = (PlanPositionsModelImpl) planPositions;

        Session session = null;

        try {
            session = openSession();

            if (planPositions.isNew()) {
                session.save(planPositions);

                planPositions.setNew(false);
            } else {
                session.merge(planPositions);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PlanPositionsModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((planPositionsModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALLBYPLANID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        planPositionsModelImpl.getOriginalPlanId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ALLBYPLANID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALLBYPLANID,
                    args);

                args = new Object[] { planPositionsModelImpl.getPlanId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ALLBYPLANID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALLBYPLANID,
                    args);
            }
        }

        EntityCacheUtil.putResult(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionsImpl.class, planPositions.getPrimaryKey(),
            planPositions);

        clearUniqueFindersCache(planPositions);
        cacheUniqueFindersCache(planPositions);

        return planPositions;
    }

    protected PlanPositions toUnwrappedModel(PlanPositions planPositions) {
        if (planPositions instanceof PlanPositionsImpl) {
            return planPositions;
        }

        PlanPositionsImpl planPositionsImpl = new PlanPositionsImpl();

        planPositionsImpl.setNew(planPositions.isNew());
        planPositionsImpl.setPrimaryKey(planPositions.getPrimaryKey());

        planPositionsImpl.setId(planPositions.getId());
        planPositionsImpl.setPlanId(planPositions.getPlanId());
        planPositionsImpl.setPlanVersion(planPositions.getPlanVersion());
        planPositionsImpl.setVersion(planPositions.getVersion());
        planPositionsImpl.setCreated(planPositions.getCreated());
        planPositionsImpl.setUpdateAuthorId(planPositions.getUpdateAuthorId());

        return planPositionsImpl;
    }

    /**
     * Returns the plan positions with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan positions
     * @return the plan positions
     * @throws com.ext.portlet.NoSuchPlanPositionsException if a plan positions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPositions findByPrimaryKey(Serializable primaryKey)
        throws NoSuchPlanPositionsException, SystemException {
        PlanPositions planPositions = fetchByPrimaryKey(primaryKey);

        if (planPositions == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchPlanPositionsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return planPositions;
    }

    /**
     * Returns the plan positions with the primary key or throws a {@link com.ext.portlet.NoSuchPlanPositionsException} if it could not be found.
     *
     * @param id the primary key of the plan positions
     * @return the plan positions
     * @throws com.ext.portlet.NoSuchPlanPositionsException if a plan positions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPositions findByPrimaryKey(long id)
        throws NoSuchPlanPositionsException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the plan positions with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan positions
     * @return the plan positions, or <code>null</code> if a plan positions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPositions fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        PlanPositions planPositions = (PlanPositions) EntityCacheUtil.getResult(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
                PlanPositionsImpl.class, primaryKey);

        if (planPositions == _nullPlanPositions) {
            return null;
        }

        if (planPositions == null) {
            Session session = null;

            try {
                session = openSession();

                planPositions = (PlanPositions) session.get(PlanPositionsImpl.class,
                        primaryKey);

                if (planPositions != null) {
                    cacheResult(planPositions);
                } else {
                    EntityCacheUtil.putResult(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
                        PlanPositionsImpl.class, primaryKey, _nullPlanPositions);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(PlanPositionsModelImpl.ENTITY_CACHE_ENABLED,
                    PlanPositionsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return planPositions;
    }

    /**
     * Returns the plan positions with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the plan positions
     * @return the plan positions, or <code>null</code> if a plan positions with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPositions fetchByPrimaryKey(long id) throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the plan positionses.
     *
     * @return the plan positionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanPositions> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan positionses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan positionses
     * @param end the upper bound of the range of plan positionses (not inclusive)
     * @return the range of plan positionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanPositions> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan positionses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan positionses
     * @param end the upper bound of the range of plan positionses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan positionses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanPositions> findAll(int start, int end,
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

        List<PlanPositions> list = (List<PlanPositions>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANPOSITIONS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANPOSITIONS;

                if (pagination) {
                    sql = sql.concat(PlanPositionsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<PlanPositions>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PlanPositions>(list);
                } else {
                    list = (List<PlanPositions>) QueryUtil.list(q,
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
     * Removes all the plan positionses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (PlanPositions planPositions : findAll()) {
            remove(planPositions);
        }
    }

    /**
     * Returns the number of plan positionses.
     *
     * @return the number of plan positionses
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

                Query q = session.createQuery(_SQL_COUNT_PLANPOSITIONS);

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

    @Override
    protected Set<String> getBadColumnNames() {
        return _badColumnNames;
    }

    /**
     * Initializes the plan positions persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.PlanPositions")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanPositions>> listenersList = new ArrayList<ModelListener<PlanPositions>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanPositions>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanPositionsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
