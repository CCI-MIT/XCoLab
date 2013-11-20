package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchEmailListException;
import com.ext.portlet.model.EmailList;
import com.ext.portlet.model.impl.EmailListImpl;
import com.ext.portlet.model.impl.EmailListModelImpl;
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
=======
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)
import com.ext.portlet.service.persistence.EmailListPersistence;

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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the email list service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EmailListPersistence
 * @see EmailListUtil
 * @generated
 */
public class EmailListPersistenceImpl extends BasePersistenceImpl<EmailList>
    implements EmailListPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link EmailListUtil} to access the email list persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = EmailListImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(EmailListModelImpl.ENTITY_CACHE_ENABLED,
            EmailListModelImpl.FINDER_CACHE_ENABLED, EmailListImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(EmailListModelImpl.ENTITY_CACHE_ENABLED,
            EmailListModelImpl.FINDER_CACHE_ENABLED, EmailListImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(EmailListModelImpl.ENTITY_CACHE_ENABLED,
            EmailListModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FINDBYNAME =
        new FinderPath(EmailListModelImpl.ENTITY_CACHE_ENABLED,
            EmailListModelImpl.FINDER_CACHE_ENABLED, EmailListImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByfindByName",
            new String[] {
                String.class.getName(),
                
            Integer.class.getName(), Integer.class.getName(),
                OrderByComparator.class.getName()
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FINDBYNAME =
        new FinderPath(EmailListModelImpl.ENTITY_CACHE_ENABLED,
            EmailListModelImpl.FINDER_CACHE_ENABLED, EmailListImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByfindByName",
            new String[] { String.class.getName() },
            EmailListModelImpl.NAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FINDBYNAME = new FinderPath(EmailListModelImpl.ENTITY_CACHE_ENABLED,
            EmailListModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByfindByName",
            new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_FINDBYNAME_NAME_1 = "emailList.name IS NULL";
    private static final String _FINDER_COLUMN_FINDBYNAME_NAME_2 = "emailList.name = ?";
    private static final String _FINDER_COLUMN_FINDBYNAME_NAME_3 = "(emailList.name IS NULL OR emailList.name = '')";
    public static final FinderPath FINDER_PATH_FETCH_BY_FINDBYNAMEEMAIL = new FinderPath(EmailListModelImpl.ENTITY_CACHE_ENABLED,
            EmailListModelImpl.FINDER_CACHE_ENABLED, EmailListImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchByfindByNameEmail",
            new String[] { String.class.getName(), String.class.getName() },
            EmailListModelImpl.NAME_COLUMN_BITMASK |
            EmailListModelImpl.EMAIL_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FINDBYNAMEEMAIL = new FinderPath(EmailListModelImpl.ENTITY_CACHE_ENABLED,
            EmailListModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByfindByNameEmail",
            new String[] { String.class.getName(), String.class.getName() });
    private static final String _FINDER_COLUMN_FINDBYNAMEEMAIL_NAME_1 = "emailList.name IS NULL AND ";
    private static final String _FINDER_COLUMN_FINDBYNAMEEMAIL_NAME_2 = "emailList.name = ? AND ";
    private static final String _FINDER_COLUMN_FINDBYNAMEEMAIL_NAME_3 = "(emailList.name IS NULL OR emailList.name = '') AND ";
    private static final String _FINDER_COLUMN_FINDBYNAMEEMAIL_EMAIL_1 = "emailList.email IS NULL";
    private static final String _FINDER_COLUMN_FINDBYNAMEEMAIL_EMAIL_2 = "emailList.email = ?";
    private static final String _FINDER_COLUMN_FINDBYNAMEEMAIL_EMAIL_3 = "(emailList.email IS NULL OR emailList.email = '')";
    private static final String _SQL_SELECT_EMAILLIST = "SELECT emailList FROM EmailList emailList";
    private static final String _SQL_SELECT_EMAILLIST_WHERE = "SELECT emailList FROM EmailList emailList WHERE ";
    private static final String _SQL_COUNT_EMAILLIST = "SELECT COUNT(emailList) FROM EmailList emailList";
    private static final String _SQL_COUNT_EMAILLIST_WHERE = "SELECT COUNT(emailList) FROM EmailList emailList WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "emailList.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No EmailList exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No EmailList exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(EmailListPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static EmailList _nullEmailList = new EmailListImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<EmailList> toCacheModel() {
                return _nullEmailListCacheModel;
            }
        };

    private static CacheModel<EmailList> _nullEmailListCacheModel = new CacheModel<EmailList>() {
            @Override
            public EmailList toEntityModel() {
                return _nullEmailList;
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
    public EmailListPersistenceImpl() {
        setModelClass(EmailList.class);
    }
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)

    /**
     * Returns all the email lists where name = &#63;.
     *
     * @param name the name
     * @return the matching email lists
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<EmailList> findByfindByName(String name)
        throws SystemException {
        return findByfindByName(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the email lists where name = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.EmailListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param name the name
     * @param start the lower bound of the range of email lists
     * @param end the upper bound of the range of email lists (not inclusive)
     * @return the range of matching email lists
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<EmailList> findByfindByName(String name, int start, int end)
        throws SystemException {
        return findByfindByName(name, start, end, null);
    }

    /**
     * Returns an ordered range of all the email lists where name = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.EmailListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param name the name
     * @param start the lower bound of the range of email lists
     * @param end the upper bound of the range of email lists (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching email lists
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<EmailList> findByfindByName(String name, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        boolean pagination = true;
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            pagination = false;
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FINDBYNAME;
            finderArgs = new Object[] { name };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FINDBYNAME;
            finderArgs = new Object[] { name, start, end, orderByComparator };
        }

        List<EmailList> list = (List<EmailList>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if ((list != null) && !list.isEmpty()) {
            for (EmailList emailList : list) {
                if (!Validator.equals(name, emailList.getName())) {
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

            query.append(_SQL_SELECT_EMAILLIST_WHERE);

            boolean bindName = false;

            if (name == null) {
                query.append(_FINDER_COLUMN_FINDBYNAME_NAME_1);
            } else if (name.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FINDBYNAME_NAME_3);
            } else {
                bindName = true;

                query.append(_FINDER_COLUMN_FINDBYNAME_NAME_2);
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            } else
             if (pagination) {
                query.append(EmailListModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindName) {
                    qPos.add(name);
                }

                if (!pagination) {
                    list = (List<EmailList>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<EmailList>(list);
                } else {
                    list = (List<EmailList>) QueryUtil.list(q, getDialect(),
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
     * Returns the first email list in the ordered set where name = &#63;.
     *
     * @param name the name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching email list
     * @throws com.ext.portlet.NoSuchEmailListException if a matching email list could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public EmailList findByfindByName_First(String name,
        OrderByComparator orderByComparator)
        throws NoSuchEmailListException, SystemException {
        EmailList emailList = fetchByfindByName_First(name, orderByComparator);

        if (emailList != null) {
            return emailList;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("name=");
        msg.append(name);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchEmailListException(msg.toString());
    }

    /**
     * Returns the first email list in the ordered set where name = &#63;.
     *
     * @param name the name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching email list, or <code>null</code> if a matching email list could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public EmailList fetchByfindByName_First(String name,
        OrderByComparator orderByComparator) throws SystemException {
        List<EmailList> list = findByfindByName(name, 0, 1, orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the last email list in the ordered set where name = &#63;.
     *
     * @param name the name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching email list
     * @throws com.ext.portlet.NoSuchEmailListException if a matching email list could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public EmailList findByfindByName_Last(String name,
        OrderByComparator orderByComparator)
        throws NoSuchEmailListException, SystemException {
        EmailList emailList = fetchByfindByName_Last(name, orderByComparator);

        if (emailList != null) {
            return emailList;
        }

        StringBundler msg = new StringBundler(4);

        msg.append(_NO_SUCH_ENTITY_WITH_KEY);

        msg.append("name=");
        msg.append(name);

        msg.append(StringPool.CLOSE_CURLY_BRACE);

        throw new NoSuchEmailListException(msg.toString());
    }

    /**
     * Returns the last email list in the ordered set where name = &#63;.
     *
     * @param name the name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching email list, or <code>null</code> if a matching email list could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public EmailList fetchByfindByName_Last(String name,
        OrderByComparator orderByComparator) throws SystemException {
        int count = countByfindByName(name);

        if (count == 0) {
            return null;
        }

        List<EmailList> list = findByfindByName(name, count - 1, count,
                orderByComparator);

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }

    /**
     * Returns the email lists before and after the current email list in the ordered set where name = &#63;.
     *
     * @param id the primary key of the current email list
     * @param name the name
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next email list
     * @throws com.ext.portlet.NoSuchEmailListException if a email list with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public EmailList[] findByfindByName_PrevAndNext(long id, String name,
        OrderByComparator orderByComparator)
        throws NoSuchEmailListException, SystemException {
        EmailList emailList = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            EmailList[] array = new EmailListImpl[3];

            array[0] = getByfindByName_PrevAndNext(session, emailList, name,
                    orderByComparator, true);

            array[1] = emailList;

            array[2] = getByfindByName_PrevAndNext(session, emailList, name,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected EmailList getByfindByName_PrevAndNext(Session session,
        EmailList emailList, String name, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_EMAILLIST_WHERE);

        boolean bindName = false;

        if (name == null) {
            query.append(_FINDER_COLUMN_FINDBYNAME_NAME_1);
        } else if (name.equals(StringPool.BLANK)) {
            query.append(_FINDER_COLUMN_FINDBYNAME_NAME_3);
        } else {
            bindName = true;

            query.append(_FINDER_COLUMN_FINDBYNAME_NAME_2);
        }

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
            query.append(EmailListModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        if (bindName) {
            qPos.add(name);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(emailList);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<EmailList> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Removes all the email lists where name = &#63; from the database.
     *
     * @param name the name
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeByfindByName(String name) throws SystemException {
        for (EmailList emailList : findByfindByName(name, QueryUtil.ALL_POS,
                QueryUtil.ALL_POS, null)) {
            remove(emailList);
        }
    }

    /**
     * Returns the number of email lists where name = &#63;.
     *
     * @param name the name
     * @return the number of matching email lists
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByfindByName(String name) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_FINDBYNAME;

        Object[] finderArgs = new Object[] { name };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_EMAILLIST_WHERE);

            boolean bindName = false;

            if (name == null) {
                query.append(_FINDER_COLUMN_FINDBYNAME_NAME_1);
            } else if (name.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FINDBYNAME_NAME_3);
            } else {
                bindName = true;

                query.append(_FINDER_COLUMN_FINDBYNAME_NAME_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindName) {
                    qPos.add(name);
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

    /**
     * Returns the email list where name = &#63; and email = &#63; or throws a {@link com.ext.portlet.NoSuchEmailListException} if it could not be found.
     *
     * @param name the name
     * @param email the email
     * @return the matching email list
     * @throws com.ext.portlet.NoSuchEmailListException if a matching email list could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public EmailList findByfindByNameEmail(String name, String email)
        throws NoSuchEmailListException, SystemException {
        EmailList emailList = fetchByfindByNameEmail(name, email);

        if (emailList == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("name=");
            msg.append(name);

            msg.append(", email=");
            msg.append(email);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchEmailListException(msg.toString());
        }

        return emailList;
    }

    /**
     * Returns the email list where name = &#63; and email = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param name the name
     * @param email the email
     * @return the matching email list, or <code>null</code> if a matching email list could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public EmailList fetchByfindByNameEmail(String name, String email)
        throws SystemException {
        return fetchByfindByNameEmail(name, email, true);
    }

    /**
     * Returns the email list where name = &#63; and email = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param name the name
     * @param email the email
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching email list, or <code>null</code> if a matching email list could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public EmailList fetchByfindByNameEmail(String name, String email,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { name, email };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_FINDBYNAMEEMAIL,
                    finderArgs, this);
        }

        if (result instanceof EmailList) {
            EmailList emailList = (EmailList) result;

            if (!Validator.equals(name, emailList.getName()) ||
                    !Validator.equals(email, emailList.getEmail())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(4);

            query.append(_SQL_SELECT_EMAILLIST_WHERE);

            boolean bindName = false;

            if (name == null) {
                query.append(_FINDER_COLUMN_FINDBYNAMEEMAIL_NAME_1);
            } else if (name.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FINDBYNAMEEMAIL_NAME_3);
            } else {
                bindName = true;

                query.append(_FINDER_COLUMN_FINDBYNAMEEMAIL_NAME_2);
            }

            boolean bindEmail = false;

            if (email == null) {
                query.append(_FINDER_COLUMN_FINDBYNAMEEMAIL_EMAIL_1);
            } else if (email.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FINDBYNAMEEMAIL_EMAIL_3);
            } else {
                bindEmail = true;

                query.append(_FINDER_COLUMN_FINDBYNAMEEMAIL_EMAIL_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindName) {
                    qPos.add(name);
                }

                if (bindEmail) {
                    qPos.add(email);
                }

                List<EmailList> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYNAMEEMAIL,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "EmailListPersistenceImpl.fetchByfindByNameEmail(String, String, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    EmailList emailList = list.get(0);

                    result = emailList;

                    cacheResult(emailList);

                    if ((emailList.getName() == null) ||
                            !emailList.getName().equals(name) ||
                            (emailList.getEmail() == null) ||
                            !emailList.getEmail().equals(email)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYNAMEEMAIL,
                            finderArgs, emailList);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FINDBYNAMEEMAIL,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (EmailList) result;
        }
    }

    /**
     * Removes the email list where name = &#63; and email = &#63; from the database.
     *
     * @param name the name
     * @param email the email
     * @return the email list that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public EmailList removeByfindByNameEmail(String name, String email)
        throws NoSuchEmailListException, SystemException {
        EmailList emailList = findByfindByNameEmail(name, email);

        return remove(emailList);
    }

    /**
     * Returns the number of email lists where name = &#63; and email = &#63;.
     *
     * @param name the name
     * @param email the email
     * @return the number of matching email lists
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByfindByNameEmail(String name, String email)
        throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_FINDBYNAMEEMAIL;

        Object[] finderArgs = new Object[] { name, email };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_EMAILLIST_WHERE);

            boolean bindName = false;

            if (name == null) {
                query.append(_FINDER_COLUMN_FINDBYNAMEEMAIL_NAME_1);
            } else if (name.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FINDBYNAMEEMAIL_NAME_3);
            } else {
                bindName = true;

                query.append(_FINDER_COLUMN_FINDBYNAMEEMAIL_NAME_2);
            }

            boolean bindEmail = false;

            if (email == null) {
                query.append(_FINDER_COLUMN_FINDBYNAMEEMAIL_EMAIL_1);
            } else if (email.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FINDBYNAMEEMAIL_EMAIL_3);
            } else {
                bindEmail = true;

                query.append(_FINDER_COLUMN_FINDBYNAMEEMAIL_EMAIL_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindName) {
                    qPos.add(name);
                }

                if (bindEmail) {
                    qPos.add(email);
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

    /**
     * Caches the email list in the entity cache if it is enabled.
     *
     * @param emailList the email list
     */
    @Override
    public void cacheResult(EmailList emailList) {
        EntityCacheUtil.putResult(EmailListModelImpl.ENTITY_CACHE_ENABLED,
            EmailListImpl.class, emailList.getPrimaryKey(), emailList);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYNAMEEMAIL,
            new Object[] { emailList.getName(), emailList.getEmail() },
            emailList);

        emailList.resetOriginalValues();
    }

    /**
     * Caches the email lists in the entity cache if it is enabled.
     *
     * @param emailLists the email lists
     */
    @Override
    public void cacheResult(List<EmailList> emailLists) {
        for (EmailList emailList : emailLists) {
            if (EntityCacheUtil.getResult(
                        EmailListModelImpl.ENTITY_CACHE_ENABLED,
                        EmailListImpl.class, emailList.getPrimaryKey()) == null) {
                cacheResult(emailList);
            } else {
                emailList.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all email lists.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(EmailListImpl.class.getName());
        }

        EntityCacheUtil.clearCache(EmailListImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the email list.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(EmailList emailList) {
        EntityCacheUtil.removeResult(EmailListModelImpl.ENTITY_CACHE_ENABLED,
            EmailListImpl.class, emailList.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(emailList);
    }

    @Override
    public void clearCache(List<EmailList> emailLists) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (EmailList emailList : emailLists) {
            EntityCacheUtil.removeResult(EmailListModelImpl.ENTITY_CACHE_ENABLED,
                EmailListImpl.class, emailList.getPrimaryKey());

            clearUniqueFindersCache(emailList);
        }
    }

    protected void cacheUniqueFindersCache(EmailList emailList) {
        if (emailList.isNew()) {
            Object[] args = new Object[] {
                    emailList.getName(), emailList.getEmail()
                };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FINDBYNAMEEMAIL,
                args, Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYNAMEEMAIL,
                args, emailList);
        } else {
            EmailListModelImpl emailListModelImpl = (EmailListModelImpl) emailList;

            if ((emailListModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_FINDBYNAMEEMAIL.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        emailList.getName(), emailList.getEmail()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FINDBYNAMEEMAIL,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYNAMEEMAIL,
                    args, emailList);
            }
        }
    }

    protected void clearUniqueFindersCache(EmailList emailList) {
        EmailListModelImpl emailListModelImpl = (EmailListModelImpl) emailList;

        Object[] args = new Object[] { emailList.getName(), emailList.getEmail() };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FINDBYNAMEEMAIL, args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FINDBYNAMEEMAIL, args);

        if ((emailListModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_FINDBYNAMEEMAIL.getColumnBitmask()) != 0) {
            args = new Object[] {
                    emailListModelImpl.getOriginalName(),
                    emailListModelImpl.getOriginalEmail()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FINDBYNAMEEMAIL,
                args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FINDBYNAMEEMAIL,
                args);
        }
    }

    /**
     * Creates a new email list with the primary key. Does not add the email list to the database.
     *
     * @param id the primary key for the new email list
     * @return the new email list
     */
    @Override
    public EmailList create(long id) {
        EmailList emailList = new EmailListImpl();

        emailList.setNew(true);
        emailList.setPrimaryKey(id);

        return emailList;
    }

    /**
     * Removes the email list with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the email list
     * @return the email list that was removed
     * @throws com.ext.portlet.NoSuchEmailListException if a email list with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public EmailList remove(long id)
        throws NoSuchEmailListException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the email list with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the email list
     * @return the email list that was removed
     * @throws com.ext.portlet.NoSuchEmailListException if a email list with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public EmailList remove(Serializable primaryKey)
        throws NoSuchEmailListException, SystemException {
        Session session = null;

        try {
            session = openSession();

            EmailList emailList = (EmailList) session.get(EmailListImpl.class,
                    primaryKey);

            if (emailList == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchEmailListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(emailList);
        } catch (NoSuchEmailListException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected EmailList removeImpl(EmailList emailList)
        throws SystemException {
        emailList = toUnwrappedModel(emailList);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(emailList)) {
                emailList = (EmailList) session.get(EmailListImpl.class,
                        emailList.getPrimaryKeyObj());
            }

            if (emailList != null) {
                session.delete(emailList);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (emailList != null) {
            clearCache(emailList);
        }

        return emailList;
    }

    @Override
    public EmailList updateImpl(com.ext.portlet.model.EmailList emailList)
        throws SystemException {
        emailList = toUnwrappedModel(emailList);

        boolean isNew = emailList.isNew();

        EmailListModelImpl emailListModelImpl = (EmailListModelImpl) emailList;

        Session session = null;

        try {
            session = openSession();

            if (emailList.isNew()) {
                session.save(emailList);

                emailList.setNew(false);
            } else {
                session.merge(emailList);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !EmailListModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((emailListModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FINDBYNAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        emailListModelImpl.getOriginalName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FINDBYNAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FINDBYNAME,
                    args);

                args = new Object[] { emailListModelImpl.getName() };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FINDBYNAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FINDBYNAME,
                    args);
            }
        }

        EntityCacheUtil.putResult(EmailListModelImpl.ENTITY_CACHE_ENABLED,
            EmailListImpl.class, emailList.getPrimaryKey(), emailList);

        clearUniqueFindersCache(emailList);
        cacheUniqueFindersCache(emailList);

        return emailList;
    }

    protected EmailList toUnwrappedModel(EmailList emailList) {
        if (emailList instanceof EmailListImpl) {
            return emailList;
        }

        EmailListImpl emailListImpl = new EmailListImpl();

        emailListImpl.setNew(emailList.isNew());
        emailListImpl.setPrimaryKey(emailList.getPrimaryKey());

        emailListImpl.setId(emailList.getId());
        emailListImpl.setName(emailList.getName());
        emailListImpl.setEmail(emailList.getEmail());

        return emailListImpl;
    }

    /**
     * Returns the email list with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the email list
     * @return the email list
     * @throws com.ext.portlet.NoSuchEmailListException if a email list with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public EmailList findByPrimaryKey(Serializable primaryKey)
        throws NoSuchEmailListException, SystemException {
        EmailList emailList = fetchByPrimaryKey(primaryKey);

        if (emailList == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchEmailListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return emailList;
    }

    /**
     * Returns the email list with the primary key or throws a {@link com.ext.portlet.NoSuchEmailListException} if it could not be found.
     *
     * @param id the primary key of the email list
     * @return the email list
     * @throws com.ext.portlet.NoSuchEmailListException if a email list with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public EmailList findByPrimaryKey(long id)
        throws NoSuchEmailListException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the email list with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the email list
     * @return the email list, or <code>null</code> if a email list with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public EmailList fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        EmailList emailList = (EmailList) EntityCacheUtil.getResult(EmailListModelImpl.ENTITY_CACHE_ENABLED,
                EmailListImpl.class, primaryKey);

        if (emailList == _nullEmailList) {
            return null;
        }

        if (emailList == null) {
            Session session = null;

            try {
                session = openSession();

                emailList = (EmailList) session.get(EmailListImpl.class,
                        primaryKey);

                if (emailList != null) {
                    cacheResult(emailList);
                } else {
                    EntityCacheUtil.putResult(EmailListModelImpl.ENTITY_CACHE_ENABLED,
                        EmailListImpl.class, primaryKey, _nullEmailList);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(EmailListModelImpl.ENTITY_CACHE_ENABLED,
                    EmailListImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return emailList;
    }

    /**
     * Returns the email list with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the email list
     * @return the email list, or <code>null</code> if a email list with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public EmailList fetchByPrimaryKey(long id) throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the email lists.
     *
     * @return the email lists
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<EmailList> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the email lists.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.EmailListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of email lists
     * @param end the upper bound of the range of email lists (not inclusive)
     * @return the range of email lists
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<EmailList> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the email lists.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.EmailListModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of email lists
     * @param end the upper bound of the range of email lists (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of email lists
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<EmailList> findAll(int start, int end,
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

        List<EmailList> list = (List<EmailList>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_EMAILLIST);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_EMAILLIST;

                if (pagination) {
                    sql = sql.concat(EmailListModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<EmailList>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<EmailList>(list);
                } else {
                    list = (List<EmailList>) QueryUtil.list(q, getDialect(),
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
     * Removes all the email lists from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (EmailList emailList : findAll()) {
            remove(emailList);
        }
    }

    /**
     * Returns the number of email lists.
     *
     * @return the number of email lists
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

                Query q = session.createQuery(_SQL_COUNT_EMAILLIST);

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
     * Initializes the email list persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.EmailList")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<EmailList>> listenersList = new ArrayList<ModelListener<EmailList>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<EmailList>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(EmailListImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
