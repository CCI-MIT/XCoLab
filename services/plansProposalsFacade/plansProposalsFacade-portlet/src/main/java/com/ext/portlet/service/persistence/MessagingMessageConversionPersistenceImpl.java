package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchMessagingMessageConversionException;
import com.ext.portlet.model.MessagingMessageConversion;
import com.ext.portlet.model.impl.MessagingMessageConversionImpl;
import com.ext.portlet.model.impl.MessagingMessageConversionModelImpl;
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
=======
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)
import com.ext.portlet.service.persistence.MessagingMessageConversionPersistence;

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
 * The persistence implementation for the messaging message conversion service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageConversionPersistence
 * @see MessagingMessageConversionUtil
 * @generated
 */
public class MessagingMessageConversionPersistenceImpl
    extends BasePersistenceImpl<MessagingMessageConversion>
    implements MessagingMessageConversionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MessagingMessageConversionUtil} to access the messaging message conversion persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MessagingMessageConversionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionModelImpl.FINDER_CACHE_ENABLED,
            MessagingMessageConversionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionModelImpl.FINDER_CACHE_ENABLED,
            MessagingMessageConversionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FINDBYTYPE =
        new FinderPath(MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionModelImpl.FINDER_CACHE_ENABLED,
            MessagingMessageConversionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByfindByType",
            new String[] {
                Long.class.getName(), Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FINDBYTYPE =
        new FinderPath(MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionModelImpl.FINDER_CACHE_ENABLED,
            MessagingMessageConversionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByfindByType",
            new String[] { Long.class.getName(), Long.class.getName() },
            MessagingMessageConversionModelImpl.MESSAGEID_COLUMN_BITMASK |
            MessagingMessageConversionModelImpl.CONVERSIONTYPEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FINDBYTYPE = new FinderPath(MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByfindByType",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _FINDER_COLUMN_FINDBYTYPE_MESSAGEID_2 = "messagingMessageConversion.messageId = ? AND ";
    private static final String _FINDER_COLUMN_FINDBYTYPE_CONVERSIONTYPEID_2 = "messagingMessageConversion.conversionTypeId = ?";
    private static final String _SQL_SELECT_MESSAGINGMESSAGECONVERSION = "SELECT messagingMessageConversion FROM MessagingMessageConversion messagingMessageConversion";
    private static final String _SQL_SELECT_MESSAGINGMESSAGECONVERSION_WHERE = "SELECT messagingMessageConversion FROM MessagingMessageConversion messagingMessageConversion WHERE ";
    private static final String _SQL_COUNT_MESSAGINGMESSAGECONVERSION = "SELECT COUNT(messagingMessageConversion) FROM MessagingMessageConversion messagingMessageConversion";
    private static final String _SQL_COUNT_MESSAGINGMESSAGECONVERSION_WHERE = "SELECT COUNT(messagingMessageConversion) FROM MessagingMessageConversion messagingMessageConversion WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "messagingMessageConversion.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MessagingMessageConversion exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MessagingMessageConversion exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MessagingMessageConversionPersistenceImpl.class);
    private static MessagingMessageConversion _nullMessagingMessageConversion = new MessagingMessageConversionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MessagingMessageConversion> toCacheModel() {
                return _nullMessagingMessageConversionCacheModel;
            }
        };

    private static CacheModel<MessagingMessageConversion> _nullMessagingMessageConversionCacheModel =
        new CacheModel<MessagingMessageConversion>() {
            @Override
            public MessagingMessageConversion toEntityModel() {
                return _nullMessagingMessageConversion;
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
     * Caches the messaging message conversion in the entity cache if it is enabled.
     *
     * @param messagingMessageConversion the messaging message conversion
     */
    public void cacheResult(
        MessagingMessageConversion messagingMessageConversion) {
        EntityCacheUtil.putResult(MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionImpl.class,
            messagingMessageConversion.getPrimaryKey(),
            messagingMessageConversion);

        messagingMessageConversion.resetOriginalValues();
=======
    public MessagingMessageConversionPersistenceImpl() {
        setModelClass(MessagingMessageConversion.class);
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)
    }

    /**
     * Returns all the messaging message conversions where messageId = &#63; and conversionTypeId = &#63;.
     *
     * @param messageId the message ID
     * @param conversionTypeId the conversion type ID
     * @return the matching messaging message conversions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MessagingMessageConversion> findByfindByType(long messageId,
        long conversionTypeId) throws SystemException {
        return findByfindByType(messageId, conversionTypeId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the messaging message conversions where messageId = &#63; and conversionTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingMessageConversionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param messageId the message ID
     * @param conversionTypeId the conversion type ID
     * @param start the lower bound of the range of messaging message conversions
     * @param end the upper bound of the range of messaging message conversions (not inclusive)
     * @return the range of matching messaging message conversions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MessagingMessageConversion> findByfindByType(long messageId,
        long conversionTypeId, int start, int end) throws SystemException {
        return findByfindByType(messageId, conversionTypeId, start, end, null);
    }

    /**
     * Returns an ordered range of all the messaging message conversions where messageId = &#63; and conversionTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingMessageConversionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param messageId the message ID
     * @param conversionTypeId the conversion type ID
     * @param start the lower bound of the range of messaging message conversions
     * @param end the upper bound of the range of messaging message conversions (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching messaging message conversions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MessagingMessageConversion> findByfindByType(long messageId,
        long conversionTypeId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FINDBYTYPE;
            finderArgs = new Object[] { messageId, conversionTypeId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FINDBYTYPE;
            finderArgs = new Object[] {
                    messageId, conversionTypeId,
                    
                    start, end, orderByComparator
                };
        }

        List<MessagingMessageConversion> list = (List<MessagingMessageConversion>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (MessagingMessageConversion messagingMessageConversion : list) {
                if ((messageId != messagingMessageConversion.getMessageId()) ||
                        (conversionTypeId != messagingMessageConversion.getConversionTypeId())) {
                    list = null;

                    break;
                }
            }
        }

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_MESSAGINGMESSAGECONVERSION_WHERE);

            query.append(_FINDER_COLUMN_FINDBYTYPE_MESSAGEID_2);

            query.append(_FINDER_COLUMN_FINDBYTYPE_CONVERSIONTYPEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(MessagingMessageConversionModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(messageId);

                qPos.add(conversionTypeId);

                if (!pagination) {
                    list = (List<MessagingMessageConversion>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MessagingMessageConversion>(list);
                } else {
                    list = (List<MessagingMessageConversion>) QueryUtil.list(q,
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
     * Returns the first messaging message conversion in the ordered set where messageId = &#63; and conversionTypeId = &#63;.
     *
     * @param messageId the message ID
     * @param conversionTypeId the conversion type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching messaging message conversion
     * @throws com.ext.portlet.NoSuchMessagingMessageConversionException if a matching messaging message conversion could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversion findByfindByType_First(long messageId,
        long conversionTypeId, OrderByComparator orderByComparator)
        throws NoSuchMessagingMessageConversionException, SystemException {
        MessagingMessageConversion messagingMessageConversion = fetchByfindByType_First(messageId,
                conversionTypeId, orderByComparator);

        if (messagingMessageConversion != null) {
            return messagingMessageConversion;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("messageId=");
        msg.append(messageId);

        msg.append(", conversionTypeId=");
        msg.append(conversionTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchMessagingMessageConversionException(msg.toString());
    }

    /**
     * Returns the first messaging message conversion in the ordered set where messageId = &#63; and conversionTypeId = &#63;.
     *
     * @param messageId the message ID
     * @param conversionTypeId the conversion type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching messaging message conversion, or <code>null</code> if a matching messaging message conversion could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversion fetchByfindByType_First(long messageId,
        long conversionTypeId, OrderByComparator orderByComparator)
        throws SystemException {
        List<MessagingMessageConversion> list = findByfindByType(messageId,
                conversionTypeId, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last messaging message conversion in the ordered set where messageId = &#63; and conversionTypeId = &#63;.
     *
     * @param messageId the message ID
     * @param conversionTypeId the conversion type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching messaging message conversion
     * @throws com.ext.portlet.NoSuchMessagingMessageConversionException if a matching messaging message conversion could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversion findByfindByType_Last(long messageId,
        long conversionTypeId, OrderByComparator orderByComparator)
        throws NoSuchMessagingMessageConversionException, SystemException {
        MessagingMessageConversion messagingMessageConversion = fetchByfindByType_Last(messageId,
                conversionTypeId, orderByComparator);

        if (messagingMessageConversion != null) {
            return messagingMessageConversion;
        }

        StringBundler msg = new StringBundler(6);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("messageId=");
        msg.append(messageId);

        msg.append(", conversionTypeId=");
        msg.append(conversionTypeId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchMessagingMessageConversionException(msg.toString());
    }

    /**
     * Returns the last messaging message conversion in the ordered set where messageId = &#63; and conversionTypeId = &#63;.
     *
     * @param messageId the message ID
     * @param conversionTypeId the conversion type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching messaging message conversion, or <code>null</code> if a matching messaging message conversion could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversion fetchByfindByType_Last(long messageId,
        long conversionTypeId, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByfindByType(messageId, conversionTypeId);

        if (count == 0) {
            return null;
        }

        List<MessagingMessageConversion> list = findByfindByType(messageId,
                conversionTypeId, count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the messaging message conversions before and after the current messaging message conversion in the ordered set where messageId = &#63; and conversionTypeId = &#63;.
     *
     * @param conversionId the primary key of the current messaging message conversion
     * @param messageId the message ID
     * @param conversionTypeId the conversion type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next messaging message conversion
     * @throws com.ext.portlet.NoSuchMessagingMessageConversionException if a messaging message conversion with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversion[] findByfindByType_PrevAndNext(
        long conversionId, long messageId, long conversionTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchMessagingMessageConversionException, SystemException {
        MessagingMessageConversion messagingMessageConversion = findByPrimaryKey(conversionId);

        Session session = null;

        try {
            session = openSession();

            MessagingMessageConversion[] array = new MessagingMessageConversionImpl[3];

            array[0] = getByfindByType_PrevAndNext(session,
                    messagingMessageConversion, messageId, conversionTypeId,
                    orderByComparator, true);

            array[1] = messagingMessageConversion;

            array[2] = getByfindByType_PrevAndNext(session,
                    messagingMessageConversion, messageId, conversionTypeId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected MessagingMessageConversion getByfindByType_PrevAndNext(
        Session session, MessagingMessageConversion messagingMessageConversion,
        long messageId, long conversionTypeId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_MESSAGINGMESSAGECONVERSION_WHERE);

        query.append(_FINDER_COLUMN_FINDBYTYPE_MESSAGEID_2);

        query.append(_FINDER_COLUMN_FINDBYTYPE_CONVERSIONTYPEID_2);

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
            query.append(MessagingMessageConversionModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(messageId);

        qPos.add(conversionTypeId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(messagingMessageConversion);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<MessagingMessageConversion> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the messaging message conversions where messageId = &#63; and conversionTypeId = &#63; from the database.
     *
     * @param messageId the message ID
     * @param conversionTypeId the conversion type ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByfindByType(long messageId, long conversionTypeId)
        throws SystemException {
        for (MessagingMessageConversion messagingMessageConversion : findByfindByType(
                messageId, conversionTypeId, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(messagingMessageConversion);
        }
    }

    /**
     * Returns the number of messaging message conversions where messageId = &#63; and conversionTypeId = &#63;.
     *
     * @param messageId the message ID
     * @param conversionTypeId the conversion type ID
     * @return the number of matching messaging message conversions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByfindByType(long messageId, long conversionTypeId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_FINDBYTYPE;

        Object[] finderArgs = new Object[] { messageId, conversionTypeId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_MESSAGINGMESSAGECONVERSION_WHERE);

            query.append(_FINDER_COLUMN_FINDBYTYPE_MESSAGEID_2);

            query.append(_FINDER_COLUMN_FINDBYTYPE_CONVERSIONTYPEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(messageId);

                qPos.add(conversionTypeId);

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
     * Caches the messaging message conversion in the entity cache if it is enabled.
     *
     * @param messagingMessageConversion the messaging message conversion
     */
    @Override
    public void cacheResult(
        MessagingMessageConversion messagingMessageConversion) {
        EntityCacheUtil.putResult(MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionImpl.class,
            messagingMessageConversion.getPrimaryKey(),
            messagingMessageConversion);

        messagingMessageConversion.resetOriginalValues();
    }

    /**
     * Caches the messaging message conversions in the entity cache if it is enabled.
     *
     * @param messagingMessageConversions the messaging message conversions
     */
    @Override
    public void cacheResult(
        List<MessagingMessageConversion> messagingMessageConversions) {
        for (MessagingMessageConversion messagingMessageConversion : messagingMessageConversions) {
            if (EntityCacheUtil.getResult(
                        MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
                        MessagingMessageConversionImpl.class,
                        messagingMessageConversion.getPrimaryKey()) == null) {
                cacheResult(messagingMessageConversion);
            } else {
                messagingMessageConversion.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all messaging message conversions.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MessagingMessageConversionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MessagingMessageConversionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the messaging message conversion.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(
        MessagingMessageConversion messagingMessageConversion) {
        EntityCacheUtil.removeResult(MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionImpl.class,
            messagingMessageConversion.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<MessagingMessageConversion> messagingMessageConversions) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MessagingMessageConversion messagingMessageConversion : messagingMessageConversions) {
            EntityCacheUtil.removeResult(MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
                MessagingMessageConversionImpl.class,
                messagingMessageConversion.getPrimaryKey());
        }
    }

    /**
     * Creates a new messaging message conversion with the primary key. Does not add the messaging message conversion to the database.
     *
     * @param conversionId the primary key for the new messaging message conversion
     * @return the new messaging message conversion
     */
    @Override
    public MessagingMessageConversion create(long conversionId) {
        MessagingMessageConversion messagingMessageConversion = new MessagingMessageConversionImpl();

        messagingMessageConversion.setNew(true);
        messagingMessageConversion.setPrimaryKey(conversionId);

        return messagingMessageConversion;
    }

    /**
     * Removes the messaging message conversion with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param conversionId the primary key of the messaging message conversion
     * @return the messaging message conversion that was removed
     * @throws com.ext.portlet.NoSuchMessagingMessageConversionException if a messaging message conversion with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversion remove(long conversionId)
        throws NoSuchMessagingMessageConversionException, SystemException {
        return remove((Serializable) conversionId);
    }

    /**
     * Removes the messaging message conversion with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the messaging message conversion
     * @return the messaging message conversion that was removed
     * @throws com.ext.portlet.NoSuchMessagingMessageConversionException if a messaging message conversion with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversion remove(Serializable primaryKey)
        throws NoSuchMessagingMessageConversionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MessagingMessageConversion messagingMessageConversion = (MessagingMessageConversion) session.get(MessagingMessageConversionImpl.class,
                    primaryKey);

            if (messagingMessageConversion == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMessagingMessageConversionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(messagingMessageConversion);
        } catch (NoSuchMessagingMessageConversionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MessagingMessageConversion removeImpl(
        MessagingMessageConversion messagingMessageConversion)
        throws SystemException {
        messagingMessageConversion = toUnwrappedModel(messagingMessageConversion);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(messagingMessageConversion)) {
                messagingMessageConversion = (MessagingMessageConversion) session.get(MessagingMessageConversionImpl.class,
                        messagingMessageConversion.getPrimaryKeyObj());
            }

            if (messagingMessageConversion != null) {
                session.delete(messagingMessageConversion);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (messagingMessageConversion != null) {
            clearCache(messagingMessageConversion);
        }

        return messagingMessageConversion;
    }

    @Override
    public MessagingMessageConversion updateImpl(
        com.ext.portlet.model.MessagingMessageConversion messagingMessageConversion)
        throws SystemException {
        messagingMessageConversion = toUnwrappedModel(messagingMessageConversion);

        boolean isNew = messagingMessageConversion.isNew();

        MessagingMessageConversionModelImpl messagingMessageConversionModelImpl = (MessagingMessageConversionModelImpl) messagingMessageConversion;

        Session session = null;

        try {
            session = openSession();

            if (messagingMessageConversion.isNew()) {
                session.save(messagingMessageConversion);

                messagingMessageConversion.setNew(false);
            } else {
                session.merge(messagingMessageConversion);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew ||
                !MessagingMessageConversionModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((messagingMessageConversionModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FINDBYTYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        messagingMessageConversionModelImpl.getOriginalMessageId(),
                        messagingMessageConversionModelImpl.getOriginalConversionTypeId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FINDBYTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FINDBYTYPE,
                    args);

                args = new Object[] {
                        messagingMessageConversionModelImpl.getMessageId(),
                        messagingMessageConversionModelImpl.getConversionTypeId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FINDBYTYPE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FINDBYTYPE,
                    args);
            }
        }

        EntityCacheUtil.putResult(MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionImpl.class,
            messagingMessageConversion.getPrimaryKey(),
            messagingMessageConversion);

        return messagingMessageConversion;
    }

    protected MessagingMessageConversion toUnwrappedModel(
        MessagingMessageConversion messagingMessageConversion) {
        if (messagingMessageConversion instanceof MessagingMessageConversionImpl) {
            return messagingMessageConversion;
        }

        MessagingMessageConversionImpl messagingMessageConversionImpl = new MessagingMessageConversionImpl();

        messagingMessageConversionImpl.setNew(messagingMessageConversion.isNew());
        messagingMessageConversionImpl.setPrimaryKey(messagingMessageConversion.getPrimaryKey());

        messagingMessageConversionImpl.setConversionId(messagingMessageConversion.getConversionId());
        messagingMessageConversionImpl.setConversionTypeId(messagingMessageConversion.getConversionTypeId());
        messagingMessageConversionImpl.setMessageId(messagingMessageConversion.getMessageId());
        messagingMessageConversionImpl.setIpAddress(messagingMessageConversion.getIpAddress());
        messagingMessageConversionImpl.setExtraData(messagingMessageConversion.getExtraData());
        messagingMessageConversionImpl.setExtraData2(messagingMessageConversion.getExtraData2());
        messagingMessageConversionImpl.setCreateDate(messagingMessageConversion.getCreateDate());

        return messagingMessageConversionImpl;
    }

    /**
     * Returns the messaging message conversion with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the messaging message conversion
     * @return the messaging message conversion
     * @throws com.ext.portlet.NoSuchMessagingMessageConversionException if a messaging message conversion with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversion findByPrimaryKey(Serializable primaryKey)
        throws NoSuchMessagingMessageConversionException, SystemException {
        MessagingMessageConversion messagingMessageConversion = fetchByPrimaryKey(primaryKey);

        if (messagingMessageConversion == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchMessagingMessageConversionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return messagingMessageConversion;
    }

    /**
     * Returns the messaging message conversion with the primary key or throws a {@link com.ext.portlet.NoSuchMessagingMessageConversionException} if it could not be found.
     *
     * @param conversionId the primary key of the messaging message conversion
     * @return the messaging message conversion
     * @throws com.ext.portlet.NoSuchMessagingMessageConversionException if a messaging message conversion with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversion findByPrimaryKey(long conversionId)
        throws NoSuchMessagingMessageConversionException, SystemException {
        return findByPrimaryKey((Serializable) conversionId);
    }

    /**
     * Returns the messaging message conversion with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the messaging message conversion
     * @return the messaging message conversion, or <code>null</code> if a messaging message conversion with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversion fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        MessagingMessageConversion messagingMessageConversion = (MessagingMessageConversion) EntityCacheUtil.getResult(MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
                MessagingMessageConversionImpl.class, primaryKey);

        if (messagingMessageConversion == _nullMessagingMessageConversion) {
            return null;
        }

        if (messagingMessageConversion == null) {
            Session session = null;

            try {
                session = openSession();

                messagingMessageConversion = (MessagingMessageConversion) session.get(MessagingMessageConversionImpl.class,
                        primaryKey);

                if (messagingMessageConversion != null) {
                    cacheResult(messagingMessageConversion);
                } else {
                    EntityCacheUtil.putResult(MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
                        MessagingMessageConversionImpl.class, primaryKey,
                        _nullMessagingMessageConversion);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(MessagingMessageConversionModelImpl.ENTITY_CACHE_ENABLED,
                    MessagingMessageConversionImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return messagingMessageConversion;
    }

    /**
     * Returns the messaging message conversion with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param conversionId the primary key of the messaging message conversion
     * @return the messaging message conversion, or <code>null</code> if a messaging message conversion with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversion fetchByPrimaryKey(long conversionId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) conversionId);
    }

    /**
     * Returns all the messaging message conversions.
     *
     * @return the messaging message conversions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MessagingMessageConversion> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the messaging message conversions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingMessageConversionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of messaging message conversions
     * @param end the upper bound of the range of messaging message conversions (not inclusive)
     * @return the range of messaging message conversions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MessagingMessageConversion> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the messaging message conversions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingMessageConversionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of messaging message conversions
     * @param end the upper bound of the range of messaging message conversions (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of messaging message conversions
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MessagingMessageConversion> findAll(int start, int end,
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

        List<MessagingMessageConversion> list = (List<MessagingMessageConversion>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MESSAGINGMESSAGECONVERSION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MESSAGINGMESSAGECONVERSION;

                if (pagination) {
                    sql = sql.concat(MessagingMessageConversionModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<MessagingMessageConversion>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MessagingMessageConversion>(list);
                } else {
                    list = (List<MessagingMessageConversion>) QueryUtil.list(q,
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
     * Removes all the messaging message conversions from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (MessagingMessageConversion messagingMessageConversion : findAll()) {
            remove(messagingMessageConversion);
        }
    }

    /**
     * Returns the number of messaging message conversions.
     *
     * @return the number of messaging message conversions
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

                Query q = session.createQuery(_SQL_COUNT_MESSAGINGMESSAGECONVERSION);

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
     * Initializes the messaging message conversion persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.MessagingMessageConversion")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MessagingMessageConversion>> listenersList = new ArrayList<ModelListener<MessagingMessageConversion>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MessagingMessageConversion>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MessagingMessageConversionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
