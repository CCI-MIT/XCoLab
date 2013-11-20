package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPlanAttributeFilterException;
import com.ext.portlet.model.PlanAttributeFilter;
import com.ext.portlet.model.impl.PlanAttributeFilterImpl;
import com.ext.portlet.model.impl.PlanAttributeFilterModelImpl;
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
=======
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)
import com.ext.portlet.service.persistence.PlanAttributeFilterPersistence;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the plan attribute filter service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanAttributeFilterPersistence
 * @see PlanAttributeFilterUtil
 * @generated
 */
public class PlanAttributeFilterPersistenceImpl extends BasePersistenceImpl<PlanAttributeFilter>
    implements PlanAttributeFilterPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanAttributeFilterUtil} to access the plan attribute filter persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanAttributeFilterImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeFilterModelImpl.FINDER_CACHE_ENABLED,
            PlanAttributeFilterImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeFilterModelImpl.FINDER_CACHE_ENABLED,
            PlanAttributeFilterImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeFilterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDATTRIBUTENAME =
        new FinderPath(PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeFilterModelImpl.FINDER_CACHE_ENABLED,
            PlanAttributeFilterImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByPlanUserSettingsIdAttributeName",
            new String[] { Long.class.getName(), String.class.getName() },
            PlanAttributeFilterModelImpl.PLANUSERSETTINGSID_COLUMN_BITMASK |
            PlanAttributeFilterModelImpl.ATTRIBUTENAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDATTRIBUTENAME =
        new FinderPath(PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeFilterModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByPlanUserSettingsIdAttributeName",
            new String[] { Long.class.getName(), String.class.getName() });
    private static final String _FINDER_COLUMN_PLANUSERSETTINGSIDATTRIBUTENAME_PLANUSERSETTINGSID_2 =
        "planAttributeFilter.planUserSettingsId = ? AND ";
    private static final String _FINDER_COLUMN_PLANUSERSETTINGSIDATTRIBUTENAME_ATTRIBUTENAME_1 =
        "planAttributeFilter.attributeName IS NULL";
    private static final String _FINDER_COLUMN_PLANUSERSETTINGSIDATTRIBUTENAME_ATTRIBUTENAME_2 =
        "planAttributeFilter.attributeName = ?";
    private static final String _FINDER_COLUMN_PLANUSERSETTINGSIDATTRIBUTENAME_ATTRIBUTENAME_3 =
        "(planAttributeFilter.attributeName IS NULL OR planAttributeFilter.attributeName = '')";
    private static final String _SQL_SELECT_PLANATTRIBUTEFILTER = "SELECT planAttributeFilter FROM PlanAttributeFilter planAttributeFilter";
    private static final String _SQL_SELECT_PLANATTRIBUTEFILTER_WHERE = "SELECT planAttributeFilter FROM PlanAttributeFilter planAttributeFilter WHERE ";
    private static final String _SQL_COUNT_PLANATTRIBUTEFILTER = "SELECT COUNT(planAttributeFilter) FROM PlanAttributeFilter planAttributeFilter";
    private static final String _SQL_COUNT_PLANATTRIBUTEFILTER_WHERE = "SELECT COUNT(planAttributeFilter) FROM PlanAttributeFilter planAttributeFilter WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planAttributeFilter.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanAttributeFilter exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PlanAttributeFilter exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanAttributeFilterPersistenceImpl.class);
    private static PlanAttributeFilter _nullPlanAttributeFilter = new PlanAttributeFilterImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanAttributeFilter> toCacheModel() {
                return _nullPlanAttributeFilterCacheModel;
            }
        };

    private static CacheModel<PlanAttributeFilter> _nullPlanAttributeFilterCacheModel =
        new CacheModel<PlanAttributeFilter>() {
            @Override
            public PlanAttributeFilter toEntityModel() {
                return _nullPlanAttributeFilter;
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
    public PlanAttributeFilterPersistenceImpl() {
        setModelClass(PlanAttributeFilter.class);
    }

    /**
     * Returns the plan attribute filter where planUserSettingsId = &#63; and attributeName = &#63; or throws a {@link com.ext.portlet.NoSuchPlanAttributeFilterException} if it could not be found.
     *
     * @param planUserSettingsId the plan user settings ID
     * @param attributeName the attribute name
     * @return the matching plan attribute filter
     * @throws com.ext.portlet.NoSuchPlanAttributeFilterException if a matching plan attribute filter could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanAttributeFilter findByPlanUserSettingsIdAttributeName(
        long planUserSettingsId, String attributeName)
        throws NoSuchPlanAttributeFilterException, SystemException {
        PlanAttributeFilter planAttributeFilter = fetchByPlanUserSettingsIdAttributeName(planUserSettingsId,
                attributeName);

        if (planAttributeFilter == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("planUserSettingsId=");
            msg.append(planUserSettingsId);

            msg.append(", attributeName=");
            msg.append(attributeName);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanAttributeFilterException(msg.toString());
        }

        return planAttributeFilter;
    }

    /**
     * Returns the plan attribute filter where planUserSettingsId = &#63; and attributeName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param planUserSettingsId the plan user settings ID
     * @param attributeName the attribute name
     * @return the matching plan attribute filter, or <code>null</code> if a matching plan attribute filter could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanAttributeFilter fetchByPlanUserSettingsIdAttributeName(
        long planUserSettingsId, String attributeName)
        throws SystemException {
        return fetchByPlanUserSettingsIdAttributeName(planUserSettingsId,
            attributeName, true);
    }

    /**
     * Returns the plan attribute filter where planUserSettingsId = &#63; and attributeName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param planUserSettingsId the plan user settings ID
     * @param attributeName the attribute name
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching plan attribute filter, or <code>null</code> if a matching plan attribute filter could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanAttributeFilter fetchByPlanUserSettingsIdAttributeName(
        long planUserSettingsId, String attributeName, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { planUserSettingsId, attributeName };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDATTRIBUTENAME,
                    finderArgs, this);
        }

        if (result instanceof PlanAttributeFilter) {
            PlanAttributeFilter planAttributeFilter = (PlanAttributeFilter) result;

            if ((planUserSettingsId != planAttributeFilter.getPlanUserSettingsId()) ||
                    !Validator.equals(attributeName,
                        planAttributeFilter.getAttributeName())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_SELECT_PLANATTRIBUTEFILTER_WHERE);

            query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDATTRIBUTENAME_PLANUSERSETTINGSID_2);

            boolean bindAttributeName = false;

            if (attributeName == null) {
                query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDATTRIBUTENAME_ATTRIBUTENAME_1);
            } else if (attributeName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDATTRIBUTENAME_ATTRIBUTENAME_3);
            } else {
                bindAttributeName = true;

                query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDATTRIBUTENAME_ATTRIBUTENAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planUserSettingsId);

                if (bindAttributeName) {
                    qPos.add(attributeName);
                }

                List<PlanAttributeFilter> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDATTRIBUTENAME,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "PlanAttributeFilterPersistenceImpl.fetchByPlanUserSettingsIdAttributeName(long, String, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    PlanAttributeFilter planAttributeFilter = list.get(0);

                    result = planAttributeFilter;

                    cacheResult(planAttributeFilter);

                    if ((planAttributeFilter.getPlanUserSettingsId() != planUserSettingsId) ||
                            (planAttributeFilter.getAttributeName() == null) ||
                            !planAttributeFilter.getAttributeName()
                                                    .equals(attributeName)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDATTRIBUTENAME,
                            finderArgs, planAttributeFilter);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDATTRIBUTENAME,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (PlanAttributeFilter) result;
        }
    }

    /**
     * Removes the plan attribute filter where planUserSettingsId = &#63; and attributeName = &#63; from the database.
     *
     * @param planUserSettingsId the plan user settings ID
     * @param attributeName the attribute name
     * @return the plan attribute filter that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanAttributeFilter removeByPlanUserSettingsIdAttributeName(
        long planUserSettingsId, String attributeName)
        throws NoSuchPlanAttributeFilterException, SystemException {
        PlanAttributeFilter planAttributeFilter = findByPlanUserSettingsIdAttributeName(planUserSettingsId,
                attributeName);

        return remove(planAttributeFilter);
    }

    /**
     * Returns the number of plan attribute filters where planUserSettingsId = &#63; and attributeName = &#63;.
     *
     * @param planUserSettingsId the plan user settings ID
     * @param attributeName the attribute name
     * @return the number of matching plan attribute filters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByPlanUserSettingsIdAttributeName(long planUserSettingsId,
        String attributeName) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDATTRIBUTENAME;

        Object[] finderArgs = new Object[] { planUserSettingsId, attributeName };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PLANATTRIBUTEFILTER_WHERE);

            query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDATTRIBUTENAME_PLANUSERSETTINGSID_2);

            boolean bindAttributeName = false;

            if (attributeName == null) {
                query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDATTRIBUTENAME_ATTRIBUTENAME_1);
            } else if (attributeName.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDATTRIBUTENAME_ATTRIBUTENAME_3);
            } else {
                bindAttributeName = true;

                query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDATTRIBUTENAME_ATTRIBUTENAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planUserSettingsId);

                if (bindAttributeName) {
                    qPos.add(attributeName);
                }

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
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)

    /**
     * Caches the plan attribute filter in the entity cache if it is enabled.
     *
     * @param planAttributeFilter the plan attribute filter
     */
    @Override
    public void cacheResult(PlanAttributeFilter planAttributeFilter) {
        EntityCacheUtil.putResult(PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeFilterImpl.class, planAttributeFilter.getPrimaryKey(),
            planAttributeFilter);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDATTRIBUTENAME,
            new Object[] {
                planAttributeFilter.getPlanUserSettingsId(),
                planAttributeFilter.getAttributeName()
            }, planAttributeFilter);

        planAttributeFilter.resetOriginalValues();
    }

    /**
     * Caches the plan attribute filters in the entity cache if it is enabled.
     *
     * @param planAttributeFilters the plan attribute filters
     */
    @Override
    public void cacheResult(List<PlanAttributeFilter> planAttributeFilters) {
        for (PlanAttributeFilter planAttributeFilter : planAttributeFilters) {
            if (EntityCacheUtil.getResult(
                        PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
                        PlanAttributeFilterImpl.class,
                        planAttributeFilter.getPrimaryKey()) == null) {
                cacheResult(planAttributeFilter);
            } else {
                planAttributeFilter.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan attribute filters.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanAttributeFilterImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanAttributeFilterImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan attribute filter.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanAttributeFilter planAttributeFilter) {
        EntityCacheUtil.removeResult(PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeFilterImpl.class, planAttributeFilter.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(planAttributeFilter);
    }

    @Override
    public void clearCache(List<PlanAttributeFilter> planAttributeFilters) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanAttributeFilter planAttributeFilter : planAttributeFilters) {
            EntityCacheUtil.removeResult(PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
                PlanAttributeFilterImpl.class,
                planAttributeFilter.getPrimaryKey());

            clearUniqueFindersCache(planAttributeFilter);
        }
    }

    protected void cacheUniqueFindersCache(
        PlanAttributeFilter planAttributeFilter) {
        if (planAttributeFilter.isNew()) {
            Object[] args = new Object[] {
                    planAttributeFilter.getPlanUserSettingsId(),
                    planAttributeFilter.getAttributeName()
                };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDATTRIBUTENAME,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDATTRIBUTENAME,
                args, planAttributeFilter);
        } else {
            PlanAttributeFilterModelImpl planAttributeFilterModelImpl = (PlanAttributeFilterModelImpl) planAttributeFilter;

            if ((planAttributeFilterModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDATTRIBUTENAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        planAttributeFilter.getPlanUserSettingsId(),
                        planAttributeFilter.getAttributeName()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDATTRIBUTENAME,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDATTRIBUTENAME,
                    args, planAttributeFilter);
            }
        }
    }

    protected void clearUniqueFindersCache(
        PlanAttributeFilter planAttributeFilter) {
        PlanAttributeFilterModelImpl planAttributeFilterModelImpl = (PlanAttributeFilterModelImpl) planAttributeFilter;

        Object[] args = new Object[] {
                planAttributeFilter.getPlanUserSettingsId(),
                planAttributeFilter.getAttributeName()
            };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDATTRIBUTENAME,
            args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDATTRIBUTENAME,
            args);

        if ((planAttributeFilterModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDATTRIBUTENAME.getColumnBitmask()) != 0) {
            args = new Object[] {
                    planAttributeFilterModelImpl.getOriginalPlanUserSettingsId(),
                    planAttributeFilterModelImpl.getOriginalAttributeName()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDATTRIBUTENAME,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDATTRIBUTENAME,
                args);
        }
    }

    /**
     * Creates a new plan attribute filter with the primary key. Does not add the plan attribute filter to the database.
     *
     * @param planAttributeFilterId the primary key for the new plan attribute filter
     * @return the new plan attribute filter
     */
    @Override
    public PlanAttributeFilter create(long planAttributeFilterId) {
        PlanAttributeFilter planAttributeFilter = new PlanAttributeFilterImpl();

        planAttributeFilter.setNew(true);
        planAttributeFilter.setPrimaryKey(planAttributeFilterId);

        return planAttributeFilter;
    }

    /**
     * Removes the plan attribute filter with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param planAttributeFilterId the primary key of the plan attribute filter
     * @return the plan attribute filter that was removed
     * @throws com.ext.portlet.NoSuchPlanAttributeFilterException if a plan attribute filter with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanAttributeFilter remove(long planAttributeFilterId)
        throws NoSuchPlanAttributeFilterException, SystemException {
        return remove((Serializable) planAttributeFilterId);
    }

    /**
     * Removes the plan attribute filter with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan attribute filter
     * @return the plan attribute filter that was removed
     * @throws com.ext.portlet.NoSuchPlanAttributeFilterException if a plan attribute filter with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanAttributeFilter remove(Serializable primaryKey)
        throws NoSuchPlanAttributeFilterException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanAttributeFilter planAttributeFilter = (PlanAttributeFilter) session.get(PlanAttributeFilterImpl.class,
                    primaryKey);

            if (planAttributeFilter == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanAttributeFilterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planAttributeFilter);
        } catch (NoSuchPlanAttributeFilterException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanAttributeFilter removeImpl(
        PlanAttributeFilter planAttributeFilter) throws SystemException {
        planAttributeFilter = toUnwrappedModel(planAttributeFilter);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(planAttributeFilter)) {
                planAttributeFilter = (PlanAttributeFilter) session.get(PlanAttributeFilterImpl.class,
                        planAttributeFilter.getPrimaryKeyObj());
            }

            if (planAttributeFilter != null) {
                session.delete(planAttributeFilter);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (planAttributeFilter != null) {
            clearCache(planAttributeFilter);
        }

        return planAttributeFilter;
    }

    @Override
    public PlanAttributeFilter updateImpl(
        com.ext.portlet.model.PlanAttributeFilter planAttributeFilter)
        throws SystemException {
        planAttributeFilter = toUnwrappedModel(planAttributeFilter);

        boolean isNew = planAttributeFilter.isNew();

        Session session = null;

        try {
            session = openSession();

            if (planAttributeFilter.isNew()) {
                session.save(planAttributeFilter);

                planAttributeFilter.setNew(false);
            } else {
                session.merge(planAttributeFilter);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PlanAttributeFilterModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
            PlanAttributeFilterImpl.class, planAttributeFilter.getPrimaryKey(),
            planAttributeFilter);

        clearUniqueFindersCache(planAttributeFilter);
        cacheUniqueFindersCache(planAttributeFilter);

        return planAttributeFilter;
    }

    protected PlanAttributeFilter toUnwrappedModel(
        PlanAttributeFilter planAttributeFilter) {
        if (planAttributeFilter instanceof PlanAttributeFilterImpl) {
            return planAttributeFilter;
        }

        PlanAttributeFilterImpl planAttributeFilterImpl = new PlanAttributeFilterImpl();

        planAttributeFilterImpl.setNew(planAttributeFilter.isNew());
        planAttributeFilterImpl.setPrimaryKey(planAttributeFilter.getPrimaryKey());

        planAttributeFilterImpl.setPlanAttributeFilterId(planAttributeFilter.getPlanAttributeFilterId());
        planAttributeFilterImpl.setAttributeName(planAttributeFilter.getAttributeName());
        planAttributeFilterImpl.setPlanUserSettingsId(planAttributeFilter.getPlanUserSettingsId());
        planAttributeFilterImpl.setMax(planAttributeFilter.getMax());
        planAttributeFilterImpl.setMin(planAttributeFilter.getMin());
        planAttributeFilterImpl.setStringVal(planAttributeFilter.getStringVal());

        return planAttributeFilterImpl;
    }

    /**
     * Returns the plan attribute filter with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan attribute filter
     * @return the plan attribute filter
     * @throws com.ext.portlet.NoSuchPlanAttributeFilterException if a plan attribute filter with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanAttributeFilter findByPrimaryKey(Serializable primaryKey)
        throws NoSuchPlanAttributeFilterException, SystemException {
        PlanAttributeFilter planAttributeFilter = fetchByPrimaryKey(primaryKey);

        if (planAttributeFilter == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchPlanAttributeFilterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return planAttributeFilter;
    }

    /**
     * Returns the plan attribute filter with the primary key or throws a {@link com.ext.portlet.NoSuchPlanAttributeFilterException} if it could not be found.
     *
     * @param planAttributeFilterId the primary key of the plan attribute filter
     * @return the plan attribute filter
     * @throws com.ext.portlet.NoSuchPlanAttributeFilterException if a plan attribute filter with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanAttributeFilter findByPrimaryKey(long planAttributeFilterId)
        throws NoSuchPlanAttributeFilterException, SystemException {
        return findByPrimaryKey((Serializable) planAttributeFilterId);
    }

    /**
     * Returns the plan attribute filter with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan attribute filter
     * @return the plan attribute filter, or <code>null</code> if a plan attribute filter with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanAttributeFilter fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        PlanAttributeFilter planAttributeFilter = (PlanAttributeFilter) EntityCacheUtil.getResult(PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
                PlanAttributeFilterImpl.class, primaryKey);

        if (planAttributeFilter == _nullPlanAttributeFilter) {
            return null;
        }

        if (planAttributeFilter == null) {
            Session session = null;

            try {
                session = openSession();

                planAttributeFilter = (PlanAttributeFilter) session.get(PlanAttributeFilterImpl.class,
                        primaryKey);

                if (planAttributeFilter != null) {
                    cacheResult(planAttributeFilter);
                } else {
                    EntityCacheUtil.putResult(PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
                        PlanAttributeFilterImpl.class, primaryKey,
                        _nullPlanAttributeFilter);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
                    PlanAttributeFilterImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return planAttributeFilter;
    }

    /**
     * Returns the plan attribute filter with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param planAttributeFilterId the primary key of the plan attribute filter
     * @return the plan attribute filter, or <code>null</code> if a plan attribute filter with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanAttributeFilter fetchByPrimaryKey(long planAttributeFilterId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) planAttributeFilterId);
    }

    /**
     * Returns all the plan attribute filters.
     *
     * @return the plan attribute filters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanAttributeFilter> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan attribute filters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanAttributeFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan attribute filters
     * @param end the upper bound of the range of plan attribute filters (not inclusive)
     * @return the range of plan attribute filters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanAttributeFilter> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan attribute filters.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanAttributeFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of plan attribute filters
     * @param end the upper bound of the range of plan attribute filters (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan attribute filters
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<PlanAttributeFilter> findAll(int start, int end,
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

        List<PlanAttributeFilter> list = (List<PlanAttributeFilter>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANATTRIBUTEFILTER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANATTRIBUTEFILTER;

                if (pagination) {
                    sql = sql.concat(PlanAttributeFilterModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<PlanAttributeFilter>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<PlanAttributeFilter>(list);
                } else {
                    list = (List<PlanAttributeFilter>) QueryUtil.list(q,
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
     * Removes all the plan attribute filters from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (PlanAttributeFilter planAttributeFilter : findAll()) {
            remove(planAttributeFilter);
        }
    }

    /**
     * Returns the number of plan attribute filters.
     *
     * @return the number of plan attribute filters
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

                Query q = session.createQuery(_SQL_COUNT_PLANATTRIBUTEFILTER);

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
     * Initializes the plan attribute filter persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.PlanAttributeFilter")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanAttributeFilter>> listenersList = new ArrayList<ModelListener<PlanAttributeFilter>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanAttributeFilter>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanAttributeFilterImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
