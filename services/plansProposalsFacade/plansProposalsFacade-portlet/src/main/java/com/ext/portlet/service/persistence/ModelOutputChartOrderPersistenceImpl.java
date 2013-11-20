package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchModelOutputChartOrderException;
import com.ext.portlet.model.ModelOutputChartOrder;
import com.ext.portlet.model.impl.ModelOutputChartOrderImpl;
import com.ext.portlet.model.impl.ModelOutputChartOrderModelImpl;
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
=======
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)
import com.ext.portlet.service.persistence.ModelOutputChartOrderPersistence;

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
 * The persistence implementation for the model output chart order service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelOutputChartOrderPersistence
 * @see ModelOutputChartOrderUtil
 * @generated
 */
public class ModelOutputChartOrderPersistenceImpl extends BasePersistenceImpl<ModelOutputChartOrder>
    implements ModelOutputChartOrderPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ModelOutputChartOrderUtil} to access the model output chart order persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ModelOutputChartOrderImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ModelOutputChartOrderModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputChartOrderModelImpl.FINDER_CACHE_ENABLED,
            ModelOutputChartOrderImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ModelOutputChartOrderModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputChartOrderModelImpl.FINDER_CACHE_ENABLED,
            ModelOutputChartOrderImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ModelOutputChartOrderModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputChartOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_FETCH_BY_MODELIDANDLABEL = new FinderPath(ModelOutputChartOrderModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputChartOrderModelImpl.FINDER_CACHE_ENABLED,
            ModelOutputChartOrderImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByModelIdAndLabel",
            new String[] { Long.class.getName(), String.class.getName() },
            ModelOutputChartOrderModelImpl.MODELID_COLUMN_BITMASK |
            ModelOutputChartOrderModelImpl.MODELOUTPUTLABEL_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_MODELIDANDLABEL = new FinderPath(ModelOutputChartOrderModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputChartOrderModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByModelIdAndLabel",
            new String[] { Long.class.getName(), String.class.getName() });
    private static final String _FINDER_COLUMN_MODELIDANDLABEL_MODELID_2 = "modelOutputChartOrder.modelId = ? AND ";
    private static final String _FINDER_COLUMN_MODELIDANDLABEL_MODELOUTPUTLABEL_1 =
        "modelOutputChartOrder.modelOutputLabel IS NULL";
    private static final String _FINDER_COLUMN_MODELIDANDLABEL_MODELOUTPUTLABEL_2 =
        "modelOutputChartOrder.modelOutputLabel = ?";
    private static final String _FINDER_COLUMN_MODELIDANDLABEL_MODELOUTPUTLABEL_3 =
        "(modelOutputChartOrder.modelOutputLabel IS NULL OR modelOutputChartOrder.modelOutputLabel = '')";
    private static final String _SQL_SELECT_MODELOUTPUTCHARTORDER = "SELECT modelOutputChartOrder FROM ModelOutputChartOrder modelOutputChartOrder";
    private static final String _SQL_SELECT_MODELOUTPUTCHARTORDER_WHERE = "SELECT modelOutputChartOrder FROM ModelOutputChartOrder modelOutputChartOrder WHERE ";
    private static final String _SQL_COUNT_MODELOUTPUTCHARTORDER = "SELECT COUNT(modelOutputChartOrder) FROM ModelOutputChartOrder modelOutputChartOrder";
    private static final String _SQL_COUNT_MODELOUTPUTCHARTORDER_WHERE = "SELECT COUNT(modelOutputChartOrder) FROM ModelOutputChartOrder modelOutputChartOrder WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "modelOutputChartOrder.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ModelOutputChartOrder exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ModelOutputChartOrder exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ModelOutputChartOrderPersistenceImpl.class);
    private static ModelOutputChartOrder _nullModelOutputChartOrder = new ModelOutputChartOrderImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ModelOutputChartOrder> toCacheModel() {
                return _nullModelOutputChartOrderCacheModel;
            }
        };

    private static CacheModel<ModelOutputChartOrder> _nullModelOutputChartOrderCacheModel =
        new CacheModel<ModelOutputChartOrder>() {
            @Override
            public ModelOutputChartOrder toEntityModel() {
                return _nullModelOutputChartOrder;
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
    public ModelOutputChartOrderPersistenceImpl() {
        setModelClass(ModelOutputChartOrder.class);
    }

    /**
     * Returns the model output chart order where modelId = &#63; and modelOutputLabel = &#63; or throws a {@link com.ext.portlet.NoSuchModelOutputChartOrderException} if it could not be found.
     *
     * @param modelId the model ID
     * @param modelOutputLabel the model output label
     * @return the matching model output chart order
     * @throws com.ext.portlet.NoSuchModelOutputChartOrderException if a matching model output chart order could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelOutputChartOrder findByModelIdAndLabel(long modelId,
        String modelOutputLabel)
        throws NoSuchModelOutputChartOrderException, SystemException {
        ModelOutputChartOrder modelOutputChartOrder = fetchByModelIdAndLabel(modelId,
                modelOutputLabel);

        if (modelOutputChartOrder == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("modelId=");
            msg.append(modelId);

            msg.append(", modelOutputLabel=");
            msg.append(modelOutputLabel);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchModelOutputChartOrderException(msg.toString());
        }

        return modelOutputChartOrder;
    }

    /**
     * Returns the model output chart order where modelId = &#63; and modelOutputLabel = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param modelId the model ID
     * @param modelOutputLabel the model output label
     * @return the matching model output chart order, or <code>null</code> if a matching model output chart order could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelOutputChartOrder fetchByModelIdAndLabel(long modelId,
        String modelOutputLabel) throws SystemException {
        return fetchByModelIdAndLabel(modelId, modelOutputLabel, true);
    }

    /**
     * Returns the model output chart order where modelId = &#63; and modelOutputLabel = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param modelId the model ID
     * @param modelOutputLabel the model output label
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching model output chart order, or <code>null</code> if a matching model output chart order could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelOutputChartOrder fetchByModelIdAndLabel(long modelId,
        String modelOutputLabel, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { modelId, modelOutputLabel };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_MODELIDANDLABEL,
                    finderArgs, this);
        }

        if (result instanceof ModelOutputChartOrder) {
            ModelOutputChartOrder modelOutputChartOrder = (ModelOutputChartOrder) result;

            if ((modelId != modelOutputChartOrder.getModelId()) ||
                    !Validator.equals(modelOutputLabel,
                        modelOutputChartOrder.getModelOutputLabel())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_SELECT_MODELOUTPUTCHARTORDER_WHERE);

            query.append(_FINDER_COLUMN_MODELIDANDLABEL_MODELID_2);

            boolean bindModelOutputLabel = false;

            if (modelOutputLabel == null) {
                query.append(_FINDER_COLUMN_MODELIDANDLABEL_MODELOUTPUTLABEL_1);
            } else if (modelOutputLabel.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_MODELIDANDLABEL_MODELOUTPUTLABEL_3);
            } else {
                bindModelOutputLabel = true;

                query.append(_FINDER_COLUMN_MODELIDANDLABEL_MODELOUTPUTLABEL_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(modelId);

                if (bindModelOutputLabel) {
                    qPos.add(modelOutputLabel);
                }

                List<ModelOutputChartOrder> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELIDANDLABEL,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "ModelOutputChartOrderPersistenceImpl.fetchByModelIdAndLabel(long, String, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    ModelOutputChartOrder modelOutputChartOrder = list.get(0);

                    result = modelOutputChartOrder;

                    cacheResult(modelOutputChartOrder);

                    if ((modelOutputChartOrder.getModelId() != modelId) ||
                            (modelOutputChartOrder.getModelOutputLabel() == null) ||
                            !modelOutputChartOrder.getModelOutputLabel()
                                                      .equals(modelOutputLabel)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELIDANDLABEL,
                            finderArgs, modelOutputChartOrder);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MODELIDANDLABEL,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (ModelOutputChartOrder) result;
        }
    }

    /**
     * Removes the model output chart order where modelId = &#63; and modelOutputLabel = &#63; from the database.
     *
     * @param modelId the model ID
     * @param modelOutputLabel the model output label
     * @return the model output chart order that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelOutputChartOrder removeByModelIdAndLabel(long modelId,
        String modelOutputLabel)
        throws NoSuchModelOutputChartOrderException, SystemException {
        ModelOutputChartOrder modelOutputChartOrder = findByModelIdAndLabel(modelId,
                modelOutputLabel);

        return remove(modelOutputChartOrder);
    }

    /**
     * Returns the number of model output chart orders where modelId = &#63; and modelOutputLabel = &#63;.
     *
     * @param modelId the model ID
     * @param modelOutputLabel the model output label
     * @return the number of matching model output chart orders
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByModelIdAndLabel(long modelId, String modelOutputLabel)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_MODELIDANDLABEL;

        Object[] finderArgs = new Object[] { modelId, modelOutputLabel };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_MODELOUTPUTCHARTORDER_WHERE);

            query.append(_FINDER_COLUMN_MODELIDANDLABEL_MODELID_2);

            boolean bindModelOutputLabel = false;

            if (modelOutputLabel == null) {
                query.append(_FINDER_COLUMN_MODELIDANDLABEL_MODELOUTPUTLABEL_1);
            } else if (modelOutputLabel.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_MODELIDANDLABEL_MODELOUTPUTLABEL_3);
            } else {
                bindModelOutputLabel = true;

                query.append(_FINDER_COLUMN_MODELIDANDLABEL_MODELOUTPUTLABEL_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(modelId);

                if (bindModelOutputLabel) {
                    qPos.add(modelOutputLabel);
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
     * Caches the model output chart order in the entity cache if it is enabled.
     *
     * @param modelOutputChartOrder the model output chart order
     */
    @Override
    public void cacheResult(ModelOutputChartOrder modelOutputChartOrder) {
        EntityCacheUtil.putResult(ModelOutputChartOrderModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputChartOrderImpl.class,
            modelOutputChartOrder.getPrimaryKey(), modelOutputChartOrder);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELIDANDLABEL,
            new Object[] {
                modelOutputChartOrder.getModelId(),
                modelOutputChartOrder.getModelOutputLabel()
            }, modelOutputChartOrder);

        modelOutputChartOrder.resetOriginalValues();
    }

    /**
     * Caches the model output chart orders in the entity cache if it is enabled.
     *
     * @param modelOutputChartOrders the model output chart orders
     */
    @Override
    public void cacheResult(List<ModelOutputChartOrder> modelOutputChartOrders) {
        for (ModelOutputChartOrder modelOutputChartOrder : modelOutputChartOrders) {
            if (EntityCacheUtil.getResult(
                        ModelOutputChartOrderModelImpl.ENTITY_CACHE_ENABLED,
                        ModelOutputChartOrderImpl.class,
                        modelOutputChartOrder.getPrimaryKey()) == null) {
                cacheResult(modelOutputChartOrder);
            } else {
                modelOutputChartOrder.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all model output chart orders.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ModelOutputChartOrderImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ModelOutputChartOrderImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the model output chart order.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ModelOutputChartOrder modelOutputChartOrder) {
        EntityCacheUtil.removeResult(ModelOutputChartOrderModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputChartOrderImpl.class,
            modelOutputChartOrder.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(modelOutputChartOrder);
    }

    @Override
    public void clearCache(List<ModelOutputChartOrder> modelOutputChartOrders) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ModelOutputChartOrder modelOutputChartOrder : modelOutputChartOrders) {
            EntityCacheUtil.removeResult(ModelOutputChartOrderModelImpl.ENTITY_CACHE_ENABLED,
                ModelOutputChartOrderImpl.class,
                modelOutputChartOrder.getPrimaryKey());

            clearUniqueFindersCache(modelOutputChartOrder);
        }
    }

    protected void cacheUniqueFindersCache(
        ModelOutputChartOrder modelOutputChartOrder) {
        if (modelOutputChartOrder.isNew()) {
            Object[] args = new Object[] {
                    modelOutputChartOrder.getModelId(),
                    modelOutputChartOrder.getModelOutputLabel()
                };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MODELIDANDLABEL,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELIDANDLABEL,
                args, modelOutputChartOrder);
        } else {
            ModelOutputChartOrderModelImpl modelOutputChartOrderModelImpl = (ModelOutputChartOrderModelImpl) modelOutputChartOrder;

            if ((modelOutputChartOrderModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_MODELIDANDLABEL.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        modelOutputChartOrder.getModelId(),
                        modelOutputChartOrder.getModelOutputLabel()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MODELIDANDLABEL,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELIDANDLABEL,
                    args, modelOutputChartOrder);
            }
        }
    }

    protected void clearUniqueFindersCache(
        ModelOutputChartOrder modelOutputChartOrder) {
        ModelOutputChartOrderModelImpl modelOutputChartOrderModelImpl = (ModelOutputChartOrderModelImpl) modelOutputChartOrder;

        Object[] args = new Object[] {
                modelOutputChartOrder.getModelId(),
                modelOutputChartOrder.getModelOutputLabel()
            };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODELIDANDLABEL, args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MODELIDANDLABEL, args);

        if ((modelOutputChartOrderModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_MODELIDANDLABEL.getColumnBitmask()) != 0) {
            args = new Object[] {
                    modelOutputChartOrderModelImpl.getOriginalModelId(),
                    modelOutputChartOrderModelImpl.getOriginalModelOutputLabel()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODELIDANDLABEL,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MODELIDANDLABEL,
                args);
        }
    }

    /**
     * Creates a new model output chart order with the primary key. Does not add the model output chart order to the database.
     *
     * @param modelOutputChartOrderPK the primary key for the new model output chart order
     * @return the new model output chart order
     */
    @Override
    public ModelOutputChartOrder create(long modelOutputChartOrderPK) {
        ModelOutputChartOrder modelOutputChartOrder = new ModelOutputChartOrderImpl();

        modelOutputChartOrder.setNew(true);
        modelOutputChartOrder.setPrimaryKey(modelOutputChartOrderPK);

        return modelOutputChartOrder;
    }

    /**
     * Removes the model output chart order with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param modelOutputChartOrderPK the primary key of the model output chart order
     * @return the model output chart order that was removed
     * @throws com.ext.portlet.NoSuchModelOutputChartOrderException if a model output chart order with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelOutputChartOrder remove(long modelOutputChartOrderPK)
        throws NoSuchModelOutputChartOrderException, SystemException {
        return remove((Serializable) modelOutputChartOrderPK);
    }

    /**
     * Removes the model output chart order with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the model output chart order
     * @return the model output chart order that was removed
     * @throws com.ext.portlet.NoSuchModelOutputChartOrderException if a model output chart order with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelOutputChartOrder remove(Serializable primaryKey)
        throws NoSuchModelOutputChartOrderException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ModelOutputChartOrder modelOutputChartOrder = (ModelOutputChartOrder) session.get(ModelOutputChartOrderImpl.class,
                    primaryKey);

            if (modelOutputChartOrder == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchModelOutputChartOrderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(modelOutputChartOrder);
        } catch (NoSuchModelOutputChartOrderException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ModelOutputChartOrder removeImpl(
        ModelOutputChartOrder modelOutputChartOrder) throws SystemException {
        modelOutputChartOrder = toUnwrappedModel(modelOutputChartOrder);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(modelOutputChartOrder)) {
                modelOutputChartOrder = (ModelOutputChartOrder) session.get(ModelOutputChartOrderImpl.class,
                        modelOutputChartOrder.getPrimaryKeyObj());
            }

            if (modelOutputChartOrder != null) {
                session.delete(modelOutputChartOrder);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (modelOutputChartOrder != null) {
            clearCache(modelOutputChartOrder);
        }

        return modelOutputChartOrder;
    }

    @Override
    public ModelOutputChartOrder updateImpl(
        com.ext.portlet.model.ModelOutputChartOrder modelOutputChartOrder)
        throws SystemException {
        modelOutputChartOrder = toUnwrappedModel(modelOutputChartOrder);

        boolean isNew = modelOutputChartOrder.isNew();

        Session session = null;

        try {
            session = openSession();

            if (modelOutputChartOrder.isNew()) {
                session.save(modelOutputChartOrder);

                modelOutputChartOrder.setNew(false);
            } else {
                session.merge(modelOutputChartOrder);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ModelOutputChartOrderModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(ModelOutputChartOrderModelImpl.ENTITY_CACHE_ENABLED,
            ModelOutputChartOrderImpl.class,
            modelOutputChartOrder.getPrimaryKey(), modelOutputChartOrder);

        clearUniqueFindersCache(modelOutputChartOrder);
        cacheUniqueFindersCache(modelOutputChartOrder);

        return modelOutputChartOrder;
    }

    protected ModelOutputChartOrder toUnwrappedModel(
        ModelOutputChartOrder modelOutputChartOrder) {
        if (modelOutputChartOrder instanceof ModelOutputChartOrderImpl) {
            return modelOutputChartOrder;
        }

        ModelOutputChartOrderImpl modelOutputChartOrderImpl = new ModelOutputChartOrderImpl();

        modelOutputChartOrderImpl.setNew(modelOutputChartOrder.isNew());
        modelOutputChartOrderImpl.setPrimaryKey(modelOutputChartOrder.getPrimaryKey());

        modelOutputChartOrderImpl.setModelOutputChartOrderPK(modelOutputChartOrder.getModelOutputChartOrderPK());
        modelOutputChartOrderImpl.setModelId(modelOutputChartOrder.getModelId());
        modelOutputChartOrderImpl.setModelOutputLabel(modelOutputChartOrder.getModelOutputLabel());
        modelOutputChartOrderImpl.setModelOutputChartOrder(modelOutputChartOrder.getModelOutputChartOrder());
        modelOutputChartOrderImpl.setModelIndexRangePolicy(modelOutputChartOrder.getModelIndexRangePolicy());
        modelOutputChartOrderImpl.setModelIndexRangeMessage(modelOutputChartOrder.getModelIndexRangeMessage());
        modelOutputChartOrderImpl.setModelIndexErrorPolicy(modelOutputChartOrder.getModelIndexErrorPolicy());
        modelOutputChartOrderImpl.setModelIndexErrorMessage(modelOutputChartOrder.getModelIndexErrorMessage());
        modelOutputChartOrderImpl.setModelChartIsVisible(modelOutputChartOrder.isModelChartIsVisible());

        return modelOutputChartOrderImpl;
    }

    /**
     * Returns the model output chart order with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the model output chart order
     * @return the model output chart order
     * @throws com.ext.portlet.NoSuchModelOutputChartOrderException if a model output chart order with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelOutputChartOrder findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelOutputChartOrderException, SystemException {
        ModelOutputChartOrder modelOutputChartOrder = fetchByPrimaryKey(primaryKey);

        if (modelOutputChartOrder == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchModelOutputChartOrderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return modelOutputChartOrder;
    }

    /**
     * Returns the model output chart order with the primary key or throws a {@link com.ext.portlet.NoSuchModelOutputChartOrderException} if it could not be found.
     *
     * @param modelOutputChartOrderPK the primary key of the model output chart order
     * @return the model output chart order
     * @throws com.ext.portlet.NoSuchModelOutputChartOrderException if a model output chart order with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelOutputChartOrder findByPrimaryKey(long modelOutputChartOrderPK)
        throws NoSuchModelOutputChartOrderException, SystemException {
        return findByPrimaryKey((Serializable) modelOutputChartOrderPK);
    }

    /**
     * Returns the model output chart order with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the model output chart order
     * @return the model output chart order, or <code>null</code> if a model output chart order with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelOutputChartOrder fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ModelOutputChartOrder modelOutputChartOrder = (ModelOutputChartOrder) EntityCacheUtil.getResult(ModelOutputChartOrderModelImpl.ENTITY_CACHE_ENABLED,
                ModelOutputChartOrderImpl.class, primaryKey);

        if (modelOutputChartOrder == _nullModelOutputChartOrder) {
            return null;
        }

        if (modelOutputChartOrder == null) {
            Session session = null;

            try {
                session = openSession();

                modelOutputChartOrder = (ModelOutputChartOrder) session.get(ModelOutputChartOrderImpl.class,
                        primaryKey);

                if (modelOutputChartOrder != null) {
                    cacheResult(modelOutputChartOrder);
                } else {
                    EntityCacheUtil.putResult(ModelOutputChartOrderModelImpl.ENTITY_CACHE_ENABLED,
                        ModelOutputChartOrderImpl.class, primaryKey,
                        _nullModelOutputChartOrder);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ModelOutputChartOrderModelImpl.ENTITY_CACHE_ENABLED,
                    ModelOutputChartOrderImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return modelOutputChartOrder;
    }

    /**
     * Returns the model output chart order with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param modelOutputChartOrderPK the primary key of the model output chart order
     * @return the model output chart order, or <code>null</code> if a model output chart order with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelOutputChartOrder fetchByPrimaryKey(long modelOutputChartOrderPK)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) modelOutputChartOrderPK);
    }

    /**
     * Returns all the model output chart orders.
     *
     * @return the model output chart orders
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModelOutputChartOrder> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the model output chart orders.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelOutputChartOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of model output chart orders
     * @param end the upper bound of the range of model output chart orders (not inclusive)
     * @return the range of model output chart orders
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModelOutputChartOrder> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the model output chart orders.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelOutputChartOrderModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of model output chart orders
     * @param end the upper bound of the range of model output chart orders (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of model output chart orders
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModelOutputChartOrder> findAll(int start, int end,
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

        List<ModelOutputChartOrder> list = (List<ModelOutputChartOrder>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MODELOUTPUTCHARTORDER);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MODELOUTPUTCHARTORDER;

                if (pagination) {
                    sql = sql.concat(ModelOutputChartOrderModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ModelOutputChartOrder>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ModelOutputChartOrder>(list);
                } else {
                    list = (List<ModelOutputChartOrder>) QueryUtil.list(q,
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
     * Removes all the model output chart orders from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ModelOutputChartOrder modelOutputChartOrder : findAll()) {
            remove(modelOutputChartOrder);
        }
    }

    /**
     * Returns the number of model output chart orders.
     *
     * @return the number of model output chart orders
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

                Query q = session.createQuery(_SQL_COUNT_MODELOUTPUTCHARTORDER);

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
     * Initializes the model output chart order persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ModelOutputChartOrder")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ModelOutputChartOrder>> listenersList = new ArrayList<ModelListener<ModelOutputChartOrder>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ModelOutputChartOrder>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ModelOutputChartOrderImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
