package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchModelPositionException;
import com.ext.portlet.model.ModelPosition;
import com.ext.portlet.model.impl.ModelPositionImpl;
import com.ext.portlet.model.impl.ModelPositionModelImpl;
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
=======
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)
import com.ext.portlet.service.persistence.ModelPositionPersistence;

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
 * The persistence implementation for the model position service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelPositionPersistence
 * @see ModelPositionUtil
 * @generated
 */
public class ModelPositionPersistenceImpl extends BasePersistenceImpl<ModelPosition>
    implements ModelPositionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ModelPositionUtil} to access the model position persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ModelPositionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
            ModelPositionModelImpl.FINDER_CACHE_ENABLED,
            ModelPositionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
            ModelPositionModelImpl.FINDER_CACHE_ENABLED,
            ModelPositionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
            ModelPositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MODELID = new FinderPath(ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
            ModelPositionModelImpl.FINDER_CACHE_ENABLED,
            ModelPositionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByModelId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELID =
        new FinderPath(ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
            ModelPositionModelImpl.FINDER_CACHE_ENABLED,
            ModelPositionImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByModelId", new String[] { Long.class.getName() },
            ModelPositionModelImpl.MODELID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_MODELID = new FinderPath(ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
            ModelPositionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByModelId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_MODELID_MODELID_2 = "modelPosition.modelId = ?";
    private static final String _SQL_SELECT_MODELPOSITION = "SELECT modelPosition FROM ModelPosition modelPosition";
    private static final String _SQL_SELECT_MODELPOSITION_WHERE = "SELECT modelPosition FROM ModelPosition modelPosition WHERE ";
    private static final String _SQL_COUNT_MODELPOSITION = "SELECT COUNT(modelPosition) FROM ModelPosition modelPosition";
    private static final String _SQL_COUNT_MODELPOSITION_WHERE = "SELECT COUNT(modelPosition) FROM ModelPosition modelPosition WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "modelPosition.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ModelPosition exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ModelPosition exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ModelPositionPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static ModelPosition _nullModelPosition = new ModelPositionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ModelPosition> toCacheModel() {
                return _nullModelPositionCacheModel;
            }
        };

    private static CacheModel<ModelPosition> _nullModelPositionCacheModel = new CacheModel<ModelPosition>() {
            @Override
            public ModelPosition toEntityModel() {
                return _nullModelPosition;
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
    public ModelPositionPersistenceImpl() {
        setModelClass(ModelPosition.class);
    }
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)

    /**
     * Returns all the model positions where modelId = &#63;.
     *
     * @param modelId the model ID
     * @return the matching model positions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModelPosition> findByModelId(long modelId)
        throws SystemException {
        return findByModelId(modelId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the model positions where modelId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelPositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param modelId the model ID
     * @param start the lower bound of the range of model positions
     * @param end the upper bound of the range of model positions (not inclusive)
     * @return the range of matching model positions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModelPosition> findByModelId(long modelId, int start, int end)
        throws SystemException {
        return findByModelId(modelId, start, end, null);
    }

    /**
     * Returns an ordered range of all the model positions where modelId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelPositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param modelId the model ID
     * @param start the lower bound of the range of model positions
     * @param end the upper bound of the range of model positions (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching model positions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModelPosition> findByModelId(long modelId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELID;
            finderArgs = new Object[] { modelId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MODELID;
            finderArgs = new Object[] { modelId, start, end, orderByComparator };
        }

        List<ModelPosition> list = (List<ModelPosition>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (ModelPosition modelPosition : list) {
                if ((modelId != modelPosition.getModelId())) {
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

            query.append(_SQL_SELECT_MODELPOSITION_WHERE);

            query.append(_FINDER_COLUMN_MODELID_MODELID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(ModelPositionModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(modelId);

                if (!pagination) {
                    list = (List<ModelPosition>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ModelPosition>(list);
                } else {
                    list = (List<ModelPosition>) QueryUtil.list(q,
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
     * Returns the first model position in the ordered set where modelId = &#63;.
     *
     * @param modelId the model ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching model position
     * @throws com.ext.portlet.NoSuchModelPositionException if a matching model position could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelPosition findByModelId_First(long modelId,
        OrderByComparator orderByComparator)
        throws NoSuchModelPositionException, SystemException {
        ModelPosition modelPosition = fetchByModelId_First(modelId,
                orderByComparator);

        if (modelPosition != null) {
            return modelPosition;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("modelId=");
        msg.append(modelId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchModelPositionException(msg.toString());
    }

    /**
     * Returns the first model position in the ordered set where modelId = &#63;.
     *
     * @param modelId the model ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching model position, or <code>null</code> if a matching model position could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelPosition fetchByModelId_First(long modelId,
        OrderByComparator orderByComparator) throws SystemException {
        List<ModelPosition> list = findByModelId(modelId, 0, 1,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last model position in the ordered set where modelId = &#63;.
     *
     * @param modelId the model ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching model position
     * @throws com.ext.portlet.NoSuchModelPositionException if a matching model position could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelPosition findByModelId_Last(long modelId,
        OrderByComparator orderByComparator)
        throws NoSuchModelPositionException, SystemException {
        ModelPosition modelPosition = fetchByModelId_Last(modelId,
                orderByComparator);

        if (modelPosition != null) {
            return modelPosition;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("modelId=");
        msg.append(modelId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchModelPositionException(msg.toString());
    }

    /**
     * Returns the last model position in the ordered set where modelId = &#63;.
     *
     * @param modelId the model ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching model position, or <code>null</code> if a matching model position could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelPosition fetchByModelId_Last(long modelId,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByModelId(modelId);

        if (count == 0) {
            return null;
        }

        List<ModelPosition> list = findByModelId(modelId, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the model positions before and after the current model position in the ordered set where modelId = &#63;.
     *
     * @param id the primary key of the current model position
     * @param modelId the model ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next model position
     * @throws com.ext.portlet.NoSuchModelPositionException if a model position with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelPosition[] findByModelId_PrevAndNext(long id, long modelId,
        OrderByComparator orderByComparator)
        throws NoSuchModelPositionException, SystemException {
        ModelPosition modelPosition = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            ModelPosition[] array = new ModelPositionImpl[3];

            array[0] = getByModelId_PrevAndNext(session, modelPosition,
                    modelId, orderByComparator, true);

            array[1] = modelPosition;

            array[2] = getByModelId_PrevAndNext(session, modelPosition,
                    modelId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ModelPosition getByModelId_PrevAndNext(Session session,
        ModelPosition modelPosition, long modelId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_MODELPOSITION_WHERE);

        query.append(_FINDER_COLUMN_MODELID_MODELID_2);

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
            query.append(ModelPositionModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(modelId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(modelPosition);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ModelPosition> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the model positions where modelId = &#63; from the database.
     *
     * @param modelId the model ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByModelId(long modelId) throws SystemException {
        for (ModelPosition modelPosition : findByModelId(modelId,
                QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(modelPosition);
        }
    }

    /**
     * Returns the number of model positions where modelId = &#63;.
     *
     * @param modelId the model ID
     * @return the number of matching model positions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByModelId(long modelId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_MODELID;

        Object[] finderArgs = new Object[] { modelId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MODELPOSITION_WHERE);

            query.append(_FINDER_COLUMN_MODELID_MODELID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(modelId);

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
     * Caches the model position in the entity cache if it is enabled.
     *
     * @param modelPosition the model position
     */
    @Override
    public void cacheResult(ModelPosition modelPosition) {
        EntityCacheUtil.putResult(ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
            ModelPositionImpl.class, modelPosition.getPrimaryKey(),
            modelPosition);

        modelPosition.resetOriginalValues();
    }

    /**
     * Caches the model positions in the entity cache if it is enabled.
     *
     * @param modelPositions the model positions
     */
    @Override
    public void cacheResult(List<ModelPosition> modelPositions) {
        for (ModelPosition modelPosition : modelPositions) {
            if (EntityCacheUtil.getResult(
                        ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
                        ModelPositionImpl.class, modelPosition.getPrimaryKey()) == null) {
                cacheResult(modelPosition);
            } else {
                modelPosition.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all model positions.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ModelPositionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ModelPositionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the model position.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ModelPosition modelPosition) {
        EntityCacheUtil.removeResult(ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
            ModelPositionImpl.class, modelPosition.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ModelPosition> modelPositions) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ModelPosition modelPosition : modelPositions) {
            EntityCacheUtil.removeResult(ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
                ModelPositionImpl.class, modelPosition.getPrimaryKey());
        }
    }

    /**
     * Creates a new model position with the primary key. Does not add the model position to the database.
     *
     * @param id the primary key for the new model position
     * @return the new model position
     */
    @Override
    public ModelPosition create(long id) {
        ModelPosition modelPosition = new ModelPositionImpl();

        modelPosition.setNew(true);
        modelPosition.setPrimaryKey(id);

        return modelPosition;
    }

    /**
     * Removes the model position with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the model position
     * @return the model position that was removed
     * @throws com.ext.portlet.NoSuchModelPositionException if a model position with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelPosition remove(long id)
        throws NoSuchModelPositionException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the model position with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the model position
     * @return the model position that was removed
     * @throws com.ext.portlet.NoSuchModelPositionException if a model position with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelPosition remove(Serializable primaryKey)
        throws NoSuchModelPositionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ModelPosition modelPosition = (ModelPosition) session.get(ModelPositionImpl.class,
                    primaryKey);

            if (modelPosition == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchModelPositionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(modelPosition);
        } catch (NoSuchModelPositionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ModelPosition removeImpl(ModelPosition modelPosition)
        throws SystemException {
        modelPosition = toUnwrappedModel(modelPosition);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(modelPosition)) {
                modelPosition = (ModelPosition) session.get(ModelPositionImpl.class,
                        modelPosition.getPrimaryKeyObj());
            }

            if (modelPosition != null) {
                session.delete(modelPosition);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (modelPosition != null) {
            clearCache(modelPosition);
        }

        return modelPosition;
    }

    @Override
    public ModelPosition updateImpl(
        com.ext.portlet.model.ModelPosition modelPosition)
        throws SystemException {
        modelPosition = toUnwrappedModel(modelPosition);

        boolean isNew = modelPosition.isNew();

        ModelPositionModelImpl modelPositionModelImpl = (ModelPositionModelImpl) modelPosition;

        Session session = null;

        try {
            session = openSession();

            if (modelPosition.isNew()) {
                session.save(modelPosition);

                modelPosition.setNew(false);
            } else {
                session.merge(modelPosition);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ModelPositionModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((modelPositionModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        modelPositionModelImpl.getOriginalModelId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODELID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELID,
                    args);

                args = new Object[] { modelPositionModelImpl.getModelId() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODELID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELID,
                    args);
            }
        }

        EntityCacheUtil.putResult(ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
            ModelPositionImpl.class, modelPosition.getPrimaryKey(),
            modelPosition);

        return modelPosition;
    }

    protected ModelPosition toUnwrappedModel(ModelPosition modelPosition) {
        if (modelPosition instanceof ModelPositionImpl) {
            return modelPosition;
        }

        ModelPositionImpl modelPositionImpl = new ModelPositionImpl();

        modelPositionImpl.setNew(modelPosition.isNew());
        modelPositionImpl.setPrimaryKey(modelPosition.getPrimaryKey());

        modelPositionImpl.setId(modelPosition.getId());
        modelPositionImpl.setPositionId(modelPosition.getPositionId());
        modelPositionImpl.setModelId(modelPosition.getModelId());

        return modelPositionImpl;
    }

    /**
     * Returns the model position with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the model position
     * @return the model position
     * @throws com.ext.portlet.NoSuchModelPositionException if a model position with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelPosition findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelPositionException, SystemException {
        ModelPosition modelPosition = fetchByPrimaryKey(primaryKey);

        if (modelPosition == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchModelPositionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return modelPosition;
    }

    /**
     * Returns the model position with the primary key or throws a {@link com.ext.portlet.NoSuchModelPositionException} if it could not be found.
     *
     * @param id the primary key of the model position
     * @return the model position
     * @throws com.ext.portlet.NoSuchModelPositionException if a model position with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelPosition findByPrimaryKey(long id)
        throws NoSuchModelPositionException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the model position with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the model position
     * @return the model position, or <code>null</code> if a model position with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelPosition fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ModelPosition modelPosition = (ModelPosition) EntityCacheUtil.getResult(ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
                ModelPositionImpl.class, primaryKey);

        if (modelPosition == _nullModelPosition) {
            return null;
        }

        if (modelPosition == null) {
            Session session = null;

            try {
                session = openSession();

                modelPosition = (ModelPosition) session.get(ModelPositionImpl.class,
                        primaryKey);

                if (modelPosition != null) {
                    cacheResult(modelPosition);
                } else {
                    EntityCacheUtil.putResult(ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
                        ModelPositionImpl.class, primaryKey, _nullModelPosition);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ModelPositionModelImpl.ENTITY_CACHE_ENABLED,
                    ModelPositionImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return modelPosition;
    }

    /**
     * Returns the model position with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the model position
     * @return the model position, or <code>null</code> if a model position with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelPosition fetchByPrimaryKey(long id) throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the model positions.
     *
     * @return the model positions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModelPosition> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the model positions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelPositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of model positions
     * @param end the upper bound of the range of model positions (not inclusive)
     * @return the range of model positions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModelPosition> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the model positions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelPositionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of model positions
     * @param end the upper bound of the range of model positions (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of model positions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ModelPosition> findAll(int start, int end,
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

        List<ModelPosition> list = (List<ModelPosition>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MODELPOSITION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MODELPOSITION;

                if (pagination) {
                    sql = sql.concat(ModelPositionModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ModelPosition>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ModelPosition>(list);
                } else {
                    list = (List<ModelPosition>) QueryUtil.list(q,
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
     * Removes all the model positions from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ModelPosition modelPosition : findAll()) {
            remove(modelPosition);
        }
    }

    /**
     * Returns the number of model positions.
     *
     * @return the number of model positions
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

                Query q = session.createQuery(_SQL_COUNT_MODELPOSITION);

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
     * Initializes the model position persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ModelPosition")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ModelPosition>> listenersList = new ArrayList<ModelListener<ModelPosition>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ModelPosition>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ModelPositionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
