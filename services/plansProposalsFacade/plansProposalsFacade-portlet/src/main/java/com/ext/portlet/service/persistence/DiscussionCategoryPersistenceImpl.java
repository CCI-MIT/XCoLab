package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchDiscussionCategoryException;
import com.ext.portlet.model.DiscussionCategory;
import com.ext.portlet.model.impl.DiscussionCategoryImpl;
import com.ext.portlet.model.impl.DiscussionCategoryModelImpl;
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
=======
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)
import com.ext.portlet.service.persistence.DiscussionCategoryPersistence;

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
 * The persistence implementation for the discussion category service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionCategoryPersistence
 * @see DiscussionCategoryUtil
 * @generated
 */
public class DiscussionCategoryPersistenceImpl extends BasePersistenceImpl<DiscussionCategory>
    implements DiscussionCategoryPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link DiscussionCategoryUtil} to access the discussion category persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = DiscussionCategoryImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryModelImpl.FINDER_CACHE_ENABLED,
            DiscussionCategoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryModelImpl.FINDER_CACHE_ENABLED,
            DiscussionCategoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CATEGORYGROUPID =
        new FinderPath(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryModelImpl.FINDER_CACHE_ENABLED,
            DiscussionCategoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCategoryGroupId",
            new String[] {
                Long.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CATEGORYGROUPID =
        new FinderPath(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryModelImpl.FINDER_CACHE_ENABLED,
            DiscussionCategoryImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCategoryGroupId",
            new String[] { Long.class.getName() },
            DiscussionCategoryModelImpl.CATEGORYGROUPID_COLUMN_BITMASK |
            DiscussionCategoryModelImpl.CREATEDATE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CATEGORYGROUPID = new FinderPath(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByCategoryGroupId", new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_CATEGORYGROUPID_CATEGORYGROUPID_2 =
        "discussionCategory.categoryGroupId = ? AND discussionCategory.deleted is null";
    public static final FinderPath FINDER_PATH_FETCH_BY_CATEGORYID = new FinderPath(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryModelImpl.FINDER_CACHE_ENABLED,
            DiscussionCategoryImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByCategoryId", new String[] { Long.class.getName() },
            DiscussionCategoryModelImpl.CATEGORYID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CATEGORYID = new FinderPath(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCategoryId",
            new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_CATEGORYID_CATEGORYID_2 = "discussionCategory.categoryId = ? AND discussionCategory.deleted is null";
    private static final String _SQL_SELECT_DISCUSSIONCATEGORY = "SELECT discussionCategory FROM DiscussionCategory discussionCategory";
    private static final String _SQL_SELECT_DISCUSSIONCATEGORY_WHERE = "SELECT discussionCategory FROM DiscussionCategory discussionCategory WHERE ";
    private static final String _SQL_COUNT_DISCUSSIONCATEGORY = "SELECT COUNT(discussionCategory) FROM DiscussionCategory discussionCategory";
    private static final String _SQL_COUNT_DISCUSSIONCATEGORY_WHERE = "SELECT COUNT(discussionCategory) FROM DiscussionCategory discussionCategory WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "discussionCategory.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DiscussionCategory exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DiscussionCategory exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(DiscussionCategoryPersistenceImpl.class);
    private static DiscussionCategory _nullDiscussionCategory = new DiscussionCategoryImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<DiscussionCategory> toCacheModel() {
                return _nullDiscussionCategoryCacheModel;
            }
        };

    private static CacheModel<DiscussionCategory> _nullDiscussionCategoryCacheModel =
        new CacheModel<DiscussionCategory>() {
            @Override
            public DiscussionCategory toEntityModel() {
                return _nullDiscussionCategory;
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
     * Caches the discussion category in the entity cache if it is enabled.
     *
     * @param discussionCategory the discussion category
     */
    public void cacheResult(DiscussionCategory discussionCategory) {
        EntityCacheUtil.putResult(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryImpl.class, discussionCategory.getPrimaryKey(),
            discussionCategory);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CATEGORYID,
            new Object[] { Long.valueOf(discussionCategory.getCategoryId()) },
            discussionCategory);

        discussionCategory.resetOriginalValues();
=======
    public DiscussionCategoryPersistenceImpl() {
        setModelClass(DiscussionCategory.class);
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)
    }

    /**
     * Returns all the discussion categories where categoryGroupId = &#63;.
     *
     * @param categoryGroupId the category group ID
     * @return the matching discussion categories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DiscussionCategory> findByCategoryGroupId(long categoryGroupId)
        throws SystemException {
        return findByCategoryGroupId(categoryGroupId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the discussion categories where categoryGroupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param categoryGroupId the category group ID
     * @param start the lower bound of the range of discussion categories
     * @param end the upper bound of the range of discussion categories (not inclusive)
     * @return the range of matching discussion categories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DiscussionCategory> findByCategoryGroupId(
        long categoryGroupId, int start, int end) throws SystemException {
        return findByCategoryGroupId(categoryGroupId, start, end, null);
    }

    /**
     * Returns an ordered range of all the discussion categories where categoryGroupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param categoryGroupId the category group ID
     * @param start the lower bound of the range of discussion categories
     * @param end the upper bound of the range of discussion categories (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching discussion categories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DiscussionCategory> findByCategoryGroupId(
        long categoryGroupId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CATEGORYGROUPID;
            finderArgs = new Object[] { categoryGroupId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CATEGORYGROUPID;
            finderArgs = new Object[] {
                    categoryGroupId,
                    
                    start, end, orderByComparator
                };
        }

        List<DiscussionCategory> list = (List<DiscussionCategory>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (DiscussionCategory discussionCategory : list) {
                if ((categoryGroupId != discussionCategory.getCategoryGroupId())) {
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

            query.append(_SQL_SELECT_DISCUSSIONCATEGORY_WHERE);

            query.append(_FINDER_COLUMN_CATEGORYGROUPID_CATEGORYGROUPID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(DiscussionCategoryModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(categoryGroupId);

                if (!pagination) {
                    list = (List<DiscussionCategory>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<DiscussionCategory>(list);
                } else {
                    list = (List<DiscussionCategory>) QueryUtil.list(q,
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
     * Returns the first discussion category in the ordered set where categoryGroupId = &#63;.
     *
     * @param categoryGroupId the category group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching discussion category
     * @throws com.ext.portlet.NoSuchDiscussionCategoryException if a matching discussion category could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionCategory findByCategoryGroupId_First(
        long categoryGroupId, OrderByComparator orderByComparator)
        throws NoSuchDiscussionCategoryException, SystemException {
        DiscussionCategory discussionCategory = fetchByCategoryGroupId_First(categoryGroupId,
                orderByComparator);

        if (discussionCategory != null) {
            return discussionCategory;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("categoryGroupId=");
        msg.append(categoryGroupId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchDiscussionCategoryException(msg.toString());
    }

    /**
     * Returns the first discussion category in the ordered set where categoryGroupId = &#63;.
     *
     * @param categoryGroupId the category group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching discussion category, or <code>null</code> if a matching discussion category could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionCategory fetchByCategoryGroupId_First(
        long categoryGroupId, OrderByComparator orderByComparator)
        throws SystemException {
        List<DiscussionCategory> list = findByCategoryGroupId(categoryGroupId,
                0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last discussion category in the ordered set where categoryGroupId = &#63;.
     *
     * @param categoryGroupId the category group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching discussion category
     * @throws com.ext.portlet.NoSuchDiscussionCategoryException if a matching discussion category could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionCategory findByCategoryGroupId_Last(long categoryGroupId,
        OrderByComparator orderByComparator)
        throws NoSuchDiscussionCategoryException, SystemException {
        DiscussionCategory discussionCategory = fetchByCategoryGroupId_Last(categoryGroupId,
                orderByComparator);

        if (discussionCategory != null) {
            return discussionCategory;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("categoryGroupId=");
        msg.append(categoryGroupId);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchDiscussionCategoryException(msg.toString());
    }

    /**
     * Returns the last discussion category in the ordered set where categoryGroupId = &#63;.
     *
     * @param categoryGroupId the category group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching discussion category, or <code>null</code> if a matching discussion category could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionCategory fetchByCategoryGroupId_Last(
        long categoryGroupId, OrderByComparator orderByComparator)
        throws SystemException {
        int count = countByCategoryGroupId(categoryGroupId);

        if (count == 0) {
            return null;
        }

        List<DiscussionCategory> list = findByCategoryGroupId(categoryGroupId,
                count - 1, count, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the discussion categories before and after the current discussion category in the ordered set where categoryGroupId = &#63;.
     *
     * @param pk the primary key of the current discussion category
     * @param categoryGroupId the category group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next discussion category
     * @throws com.ext.portlet.NoSuchDiscussionCategoryException if a discussion category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionCategory[] findByCategoryGroupId_PrevAndNext(long pk,
        long categoryGroupId, OrderByComparator orderByComparator)
        throws NoSuchDiscussionCategoryException, SystemException {
        DiscussionCategory discussionCategory = findByPrimaryKey(pk);

        Session session = null;

        try {
            session = openSession();

            DiscussionCategory[] array = new DiscussionCategoryImpl[3];

            array[0] = getByCategoryGroupId_PrevAndNext(session,
                    discussionCategory, categoryGroupId, orderByComparator, true);

            array[1] = discussionCategory;

            array[2] = getByCategoryGroupId_PrevAndNext(session,
                    discussionCategory, categoryGroupId, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected DiscussionCategory getByCategoryGroupId_PrevAndNext(
        Session session, DiscussionCategory discussionCategory,
        long categoryGroupId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_DISCUSSIONCATEGORY_WHERE);

        query.append(_FINDER_COLUMN_CATEGORYGROUPID_CATEGORYGROUPID_2);

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
            query.append(DiscussionCategoryModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(categoryGroupId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(discussionCategory);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<DiscussionCategory> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the discussion categories where categoryGroupId = &#63; from the database.
     *
     * @param categoryGroupId the category group ID
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByCategoryGroupId(long categoryGroupId)
        throws SystemException {
        for (DiscussionCategory discussionCategory : findByCategoryGroupId(
                categoryGroupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
            remove(discussionCategory);
        }
    }

    /**
     * Returns the number of discussion categories where categoryGroupId = &#63;.
     *
     * @param categoryGroupId the category group ID
     * @return the number of matching discussion categories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCategoryGroupId(long categoryGroupId)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CATEGORYGROUPID;

        Object[] finderArgs = new Object[] { categoryGroupId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_DISCUSSIONCATEGORY_WHERE);

            query.append(_FINDER_COLUMN_CATEGORYGROUPID_CATEGORYGROUPID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(categoryGroupId);

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
     * Returns the discussion category where categoryId = &#63; or throws a {@link com.ext.portlet.NoSuchDiscussionCategoryException} if it could not be found.
     *
     * @param categoryId the category ID
     * @return the matching discussion category
     * @throws com.ext.portlet.NoSuchDiscussionCategoryException if a matching discussion category could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionCategory findByCategoryId(long categoryId)
        throws NoSuchDiscussionCategoryException, SystemException {
        DiscussionCategory discussionCategory = fetchByCategoryId(categoryId);

        if (discussionCategory == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("categoryId=");
            msg.append(categoryId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchDiscussionCategoryException(msg.toString());
        }

        return discussionCategory;
    }

    /**
     * Returns the discussion category where categoryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param categoryId the category ID
     * @return the matching discussion category, or <code>null</code> if a matching discussion category could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionCategory fetchByCategoryId(long categoryId)
        throws SystemException {
        return fetchByCategoryId(categoryId, true);
    }

    /**
     * Returns the discussion category where categoryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param categoryId the category ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching discussion category, or <code>null</code> if a matching discussion category could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionCategory fetchByCategoryId(long categoryId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { categoryId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CATEGORYID,
                    finderArgs, this);
        }

        if (result instanceof DiscussionCategory) {
            DiscussionCategory discussionCategory = (DiscussionCategory) result;

            if ((categoryId != discussionCategory.getCategoryId())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_DISCUSSIONCATEGORY_WHERE);

            query.append(_FINDER_COLUMN_CATEGORYID_CATEGORYID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(categoryId);

                List<DiscussionCategory> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CATEGORYID,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "DiscussionCategoryPersistenceImpl.fetchByCategoryId(long, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    DiscussionCategory discussionCategory = list.get(0);

                    result = discussionCategory;

                    cacheResult(discussionCategory);

                    if ((discussionCategory.getCategoryId() != categoryId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CATEGORYID,
                            finderArgs, discussionCategory);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CATEGORYID,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (DiscussionCategory) result;
        }
    }

    /**
     * Removes the discussion category where categoryId = &#63; from the database.
     *
     * @param categoryId the category ID
     * @return the discussion category that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionCategory removeByCategoryId(long categoryId)
        throws NoSuchDiscussionCategoryException, SystemException {
        DiscussionCategory discussionCategory = findByCategoryId(categoryId);

        return remove(discussionCategory);
    }

    /**
     * Returns the number of discussion categories where categoryId = &#63;.
     *
     * @param categoryId the category ID
     * @return the number of matching discussion categories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByCategoryId(long categoryId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_CATEGORYID;

        Object[] finderArgs = new Object[] { categoryId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_DISCUSSIONCATEGORY_WHERE);

            query.append(_FINDER_COLUMN_CATEGORYID_CATEGORYID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(categoryId);

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
     * Caches the discussion category in the entity cache if it is enabled.
     *
     * @param discussionCategory the discussion category
     */
    @Override
    public void cacheResult(DiscussionCategory discussionCategory) {
        EntityCacheUtil.putResult(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryImpl.class, discussionCategory.getPrimaryKey(),
            discussionCategory);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CATEGORYID,
            new Object[] { discussionCategory.getCategoryId() },
            discussionCategory);

        discussionCategory.resetOriginalValues();
    }

    /**
     * Caches the discussion categories in the entity cache if it is enabled.
     *
     * @param discussionCategories the discussion categories
     */
    @Override
    public void cacheResult(List<DiscussionCategory> discussionCategories) {
        for (DiscussionCategory discussionCategory : discussionCategories) {
            if (EntityCacheUtil.getResult(
                        DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
                        DiscussionCategoryImpl.class,
                        discussionCategory.getPrimaryKey()) == null) {
                cacheResult(discussionCategory);
            } else {
                discussionCategory.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all discussion categories.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(DiscussionCategoryImpl.class.getName());
        }

        EntityCacheUtil.clearCache(DiscussionCategoryImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the discussion category.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(DiscussionCategory discussionCategory) {
        EntityCacheUtil.removeResult(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryImpl.class, discussionCategory.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(discussionCategory);
    }

    @Override
    public void clearCache(List<DiscussionCategory> discussionCategories) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (DiscussionCategory discussionCategory : discussionCategories) {
            EntityCacheUtil.removeResult(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
                DiscussionCategoryImpl.class, discussionCategory.getPrimaryKey());

            clearUniqueFindersCache(discussionCategory);
        }
    }

    protected void cacheUniqueFindersCache(
        DiscussionCategory discussionCategory) {
        if (discussionCategory.isNew()) {
            Object[] args = new Object[] { discussionCategory.getCategoryId() };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CATEGORYID, args,
                Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CATEGORYID, args,
                discussionCategory);
        } else {
            DiscussionCategoryModelImpl discussionCategoryModelImpl = (DiscussionCategoryModelImpl) discussionCategory;

            if ((discussionCategoryModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_CATEGORYID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] { discussionCategory.getCategoryId() };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CATEGORYID,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CATEGORYID,
                    args, discussionCategory);
            }
        }
    }

    protected void clearUniqueFindersCache(
        DiscussionCategory discussionCategory) {
        DiscussionCategoryModelImpl discussionCategoryModelImpl = (DiscussionCategoryModelImpl) discussionCategory;

        Object[] args = new Object[] { discussionCategory.getCategoryId() };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CATEGORYID, args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CATEGORYID, args);

        if ((discussionCategoryModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_CATEGORYID.getColumnBitmask()) != 0) {
            args = new Object[] {
                    discussionCategoryModelImpl.getOriginalCategoryId()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CATEGORYID, args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CATEGORYID, args);
        }
    }

    /**
     * Creates a new discussion category with the primary key. Does not add the discussion category to the database.
     *
     * @param pk the primary key for the new discussion category
     * @return the new discussion category
     */
    @Override
    public DiscussionCategory create(long pk) {
        DiscussionCategory discussionCategory = new DiscussionCategoryImpl();

        discussionCategory.setNew(true);
        discussionCategory.setPrimaryKey(pk);

        return discussionCategory;
    }

    /**
     * Removes the discussion category with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param pk the primary key of the discussion category
     * @return the discussion category that was removed
     * @throws com.ext.portlet.NoSuchDiscussionCategoryException if a discussion category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionCategory remove(long pk)
        throws NoSuchDiscussionCategoryException, SystemException {
        return remove((Serializable) pk);
    }

    /**
     * Removes the discussion category with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the discussion category
     * @return the discussion category that was removed
     * @throws com.ext.portlet.NoSuchDiscussionCategoryException if a discussion category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionCategory remove(Serializable primaryKey)
        throws NoSuchDiscussionCategoryException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DiscussionCategory discussionCategory = (DiscussionCategory) session.get(DiscussionCategoryImpl.class,
                    primaryKey);

            if (discussionCategory == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchDiscussionCategoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(discussionCategory);
        } catch (NoSuchDiscussionCategoryException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected DiscussionCategory removeImpl(
        DiscussionCategory discussionCategory) throws SystemException {
        discussionCategory = toUnwrappedModel(discussionCategory);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(discussionCategory)) {
                discussionCategory = (DiscussionCategory) session.get(DiscussionCategoryImpl.class,
                        discussionCategory.getPrimaryKeyObj());
            }

            if (discussionCategory != null) {
                session.delete(discussionCategory);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (discussionCategory != null) {
            clearCache(discussionCategory);
        }

        return discussionCategory;
    }

    @Override
    public DiscussionCategory updateImpl(
        com.ext.portlet.model.DiscussionCategory discussionCategory)
        throws SystemException {
        discussionCategory = toUnwrappedModel(discussionCategory);

        boolean isNew = discussionCategory.isNew();

        DiscussionCategoryModelImpl discussionCategoryModelImpl = (DiscussionCategoryModelImpl) discussionCategory;

        Session session = null;

        try {
            session = openSession();

            if (discussionCategory.isNew()) {
                session.save(discussionCategory);

                discussionCategory.setNew(false);
            } else {
                session.merge(discussionCategory);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !DiscussionCategoryModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((discussionCategoryModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CATEGORYGROUPID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        discussionCategoryModelImpl.getOriginalCategoryGroupId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CATEGORYGROUPID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CATEGORYGROUPID,
                    args);

                args = new Object[] {
                        discussionCategoryModelImpl.getCategoryGroupId()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CATEGORYGROUPID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CATEGORYGROUPID,
                    args);
            }
        }

        EntityCacheUtil.putResult(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryImpl.class, discussionCategory.getPrimaryKey(),
            discussionCategory);

        clearUniqueFindersCache(discussionCategory);
        cacheUniqueFindersCache(discussionCategory);

        return discussionCategory;
    }

    protected DiscussionCategory toUnwrappedModel(
        DiscussionCategory discussionCategory) {
        if (discussionCategory instanceof DiscussionCategoryImpl) {
            return discussionCategory;
        }

        DiscussionCategoryImpl discussionCategoryImpl = new DiscussionCategoryImpl();

        discussionCategoryImpl.setNew(discussionCategory.isNew());
        discussionCategoryImpl.setPrimaryKey(discussionCategory.getPrimaryKey());

        discussionCategoryImpl.setPk(discussionCategory.getPk());
        discussionCategoryImpl.setCategoryId(discussionCategory.getCategoryId());
        discussionCategoryImpl.setCategoryGroupId(discussionCategory.getCategoryGroupId());
        discussionCategoryImpl.setAuthorId(discussionCategory.getAuthorId());
        discussionCategoryImpl.setName(discussionCategory.getName());
        discussionCategoryImpl.setDescription(discussionCategory.getDescription());
        discussionCategoryImpl.setCreateDate(discussionCategory.getCreateDate());
        discussionCategoryImpl.setDeleted(discussionCategory.getDeleted());
        discussionCategoryImpl.setThreadsCount(discussionCategory.getThreadsCount());
        discussionCategoryImpl.setLastActivityDate(discussionCategory.getLastActivityDate());
        discussionCategoryImpl.setLastActivityAuthorId(discussionCategory.getLastActivityAuthorId());

        return discussionCategoryImpl;
    }

    /**
     * Returns the discussion category with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the discussion category
     * @return the discussion category
     * @throws com.ext.portlet.NoSuchDiscussionCategoryException if a discussion category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionCategory findByPrimaryKey(Serializable primaryKey)
        throws NoSuchDiscussionCategoryException, SystemException {
        DiscussionCategory discussionCategory = fetchByPrimaryKey(primaryKey);

        if (discussionCategory == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchDiscussionCategoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return discussionCategory;
    }

    /**
     * Returns the discussion category with the primary key or throws a {@link com.ext.portlet.NoSuchDiscussionCategoryException} if it could not be found.
     *
     * @param pk the primary key of the discussion category
     * @return the discussion category
     * @throws com.ext.portlet.NoSuchDiscussionCategoryException if a discussion category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionCategory findByPrimaryKey(long pk)
        throws NoSuchDiscussionCategoryException, SystemException {
        return findByPrimaryKey((Serializable) pk);
    }

    /**
     * Returns the discussion category with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the discussion category
     * @return the discussion category, or <code>null</code> if a discussion category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionCategory fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        DiscussionCategory discussionCategory = (DiscussionCategory) EntityCacheUtil.getResult(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
                DiscussionCategoryImpl.class, primaryKey);

        if (discussionCategory == _nullDiscussionCategory) {
            return null;
        }

        if (discussionCategory == null) {
            Session session = null;

            try {
                session = openSession();

                discussionCategory = (DiscussionCategory) session.get(DiscussionCategoryImpl.class,
                        primaryKey);

                if (discussionCategory != null) {
                    cacheResult(discussionCategory);
                } else {
                    EntityCacheUtil.putResult(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
                        DiscussionCategoryImpl.class, primaryKey,
                        _nullDiscussionCategory);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(DiscussionCategoryModelImpl.ENTITY_CACHE_ENABLED,
                    DiscussionCategoryImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return discussionCategory;
    }

    /**
     * Returns the discussion category with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param pk the primary key of the discussion category
     * @return the discussion category, or <code>null</code> if a discussion category with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionCategory fetchByPrimaryKey(long pk)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) pk);
    }

    /**
     * Returns all the discussion categories.
     *
     * @return the discussion categories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DiscussionCategory> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the discussion categories.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of discussion categories
     * @param end the upper bound of the range of discussion categories (not inclusive)
     * @return the range of discussion categories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DiscussionCategory> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the discussion categories.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of discussion categories
     * @param end the upper bound of the range of discussion categories (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of discussion categories
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DiscussionCategory> findAll(int start, int end,
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

        List<DiscussionCategory> list = (List<DiscussionCategory>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_DISCUSSIONCATEGORY);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_DISCUSSIONCATEGORY;

                if (pagination) {
                    sql = sql.concat(DiscussionCategoryModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<DiscussionCategory>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<DiscussionCategory>(list);
                } else {
                    list = (List<DiscussionCategory>) QueryUtil.list(q,
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
     * Removes all the discussion categories from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (DiscussionCategory discussionCategory : findAll()) {
            remove(discussionCategory);
        }
    }

    /**
     * Returns the number of discussion categories.
     *
     * @return the number of discussion categories
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

                Query q = session.createQuery(_SQL_COUNT_DISCUSSIONCATEGORY);

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
     * Initializes the discussion category persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.DiscussionCategory")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DiscussionCategory>> listenersList = new ArrayList<ModelListener<DiscussionCategory>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DiscussionCategory>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(DiscussionCategoryImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
