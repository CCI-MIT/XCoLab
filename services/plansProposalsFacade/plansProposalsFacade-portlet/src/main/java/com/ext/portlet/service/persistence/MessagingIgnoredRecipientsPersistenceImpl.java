package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchMessagingIgnoredRecipientsException;
import com.ext.portlet.model.MessagingIgnoredRecipients;
import com.ext.portlet.model.impl.MessagingIgnoredRecipientsImpl;
import com.ext.portlet.model.impl.MessagingIgnoredRecipientsModelImpl;
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
=======
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)
import com.ext.portlet.service.persistence.MessagingIgnoredRecipientsPersistence;

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
 * The persistence implementation for the messaging ignored recipients service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingIgnoredRecipientsPersistence
 * @see MessagingIgnoredRecipientsUtil
 * @generated
 */
public class MessagingIgnoredRecipientsPersistenceImpl
    extends BasePersistenceImpl<MessagingIgnoredRecipients>
    implements MessagingIgnoredRecipientsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MessagingIgnoredRecipientsUtil} to access the messaging ignored recipients persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MessagingIgnoredRecipientsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
            MessagingIgnoredRecipientsModelImpl.FINDER_CACHE_ENABLED,
            MessagingIgnoredRecipientsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
            MessagingIgnoredRecipientsModelImpl.FINDER_CACHE_ENABLED,
            MessagingIgnoredRecipientsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
            MessagingIgnoredRecipientsModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    public static final FinderPath FINDER_PATH_FETCH_BY_FINDBYEMAIL = new FinderPath(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
            MessagingIgnoredRecipientsModelImpl.FINDER_CACHE_ENABLED,
            MessagingIgnoredRecipientsImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByfindByEmail", new String[] { String.class.getName() },
            MessagingIgnoredRecipientsModelImpl.EMAIL_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FINDBYEMAIL = new FinderPath(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
            MessagingIgnoredRecipientsModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByfindByEmail", new String[] { String.class.getName() });
    private static final String _FINDER_COLUMN_FINDBYEMAIL_EMAIL_1 = "messagingIgnoredRecipients.email IS NULL";
    private static final String _FINDER_COLUMN_FINDBYEMAIL_EMAIL_2 = "messagingIgnoredRecipients.email = ?";
    private static final String _FINDER_COLUMN_FINDBYEMAIL_EMAIL_3 = "(messagingIgnoredRecipients.email IS NULL OR messagingIgnoredRecipients.email = '')";
    public static final FinderPath FINDER_PATH_FETCH_BY_FINDBYUSERID = new FinderPath(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
            MessagingIgnoredRecipientsModelImpl.FINDER_CACHE_ENABLED,
            MessagingIgnoredRecipientsImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByfindByUserId", new String[] { Long.class.getName() },
            MessagingIgnoredRecipientsModelImpl.USERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FINDBYUSERID = new FinderPath(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
            MessagingIgnoredRecipientsModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByfindByUserId", new String[] { Long.class.getName() });
    private static final String _FINDER_COLUMN_FINDBYUSERID_USERID_2 = "messagingIgnoredRecipients.userId = ?";
    private static final String _SQL_SELECT_MESSAGINGIGNOREDRECIPIENTS = "SELECT messagingIgnoredRecipients FROM MessagingIgnoredRecipients messagingIgnoredRecipients";
    private static final String _SQL_SELECT_MESSAGINGIGNOREDRECIPIENTS_WHERE = "SELECT messagingIgnoredRecipients FROM MessagingIgnoredRecipients messagingIgnoredRecipients WHERE ";
    private static final String _SQL_COUNT_MESSAGINGIGNOREDRECIPIENTS = "SELECT COUNT(messagingIgnoredRecipients) FROM MessagingIgnoredRecipients messagingIgnoredRecipients";
    private static final String _SQL_COUNT_MESSAGINGIGNOREDRECIPIENTS_WHERE = "SELECT COUNT(messagingIgnoredRecipients) FROM MessagingIgnoredRecipients messagingIgnoredRecipients WHERE ";
    private static final String _ORDER_BY_ENTITY_ALIAS = "messagingIgnoredRecipients.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MessagingIgnoredRecipients exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MessagingIgnoredRecipients exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MessagingIgnoredRecipientsPersistenceImpl.class);
    private static MessagingIgnoredRecipients _nullMessagingIgnoredRecipients = new MessagingIgnoredRecipientsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MessagingIgnoredRecipients> toCacheModel() {
                return _nullMessagingIgnoredRecipientsCacheModel;
            }
        };

    private static CacheModel<MessagingIgnoredRecipients> _nullMessagingIgnoredRecipientsCacheModel =
        new CacheModel<MessagingIgnoredRecipients>() {
            @Override
            public MessagingIgnoredRecipients toEntityModel() {
                return _nullMessagingIgnoredRecipients;
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
    public MessagingIgnoredRecipientsPersistenceImpl() {
        setModelClass(MessagingIgnoredRecipients.class);
    }
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)

    /**
     * Returns the messaging ignored recipients where email = &#63; or throws a {@link com.ext.portlet.NoSuchMessagingIgnoredRecipientsException} if it could not be found.
     *
     * @param email the email
     * @return the matching messaging ignored recipients
     * @throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException if a matching messaging ignored recipients could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingIgnoredRecipients findByfindByEmail(String email)
        throws NoSuchMessagingIgnoredRecipientsException, SystemException {
        MessagingIgnoredRecipients messagingIgnoredRecipients = fetchByfindByEmail(email);

        if (messagingIgnoredRecipients == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("email=");
            msg.append(email);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchMessagingIgnoredRecipientsException(msg.toString());
        }

        return messagingIgnoredRecipients;
    }

    /**
     * Returns the messaging ignored recipients where email = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param email the email
     * @return the matching messaging ignored recipients, or <code>null</code> if a matching messaging ignored recipients could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingIgnoredRecipients fetchByfindByEmail(String email)
        throws SystemException {
        return fetchByfindByEmail(email, true);
    }

    /**
     * Returns the messaging ignored recipients where email = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param email the email
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching messaging ignored recipients, or <code>null</code> if a matching messaging ignored recipients could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingIgnoredRecipients fetchByfindByEmail(String email,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { email };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_FINDBYEMAIL,
                    finderArgs, this);
        }

        if (result instanceof MessagingIgnoredRecipients) {
            MessagingIgnoredRecipients messagingIgnoredRecipients = (MessagingIgnoredRecipients) result;

            if (!Validator.equals(email, messagingIgnoredRecipients.getEmail())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_MESSAGINGIGNOREDRECIPIENTS_WHERE);

            boolean bindEmail = false;

            if (email == null) {
                query.append(_FINDER_COLUMN_FINDBYEMAIL_EMAIL_1);
            } else if (email.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FINDBYEMAIL_EMAIL_3);
            } else {
                bindEmail = true;

                query.append(_FINDER_COLUMN_FINDBYEMAIL_EMAIL_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (bindEmail) {
                    qPos.add(email);
                }

                List<MessagingIgnoredRecipients> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYEMAIL,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "MessagingIgnoredRecipientsPersistenceImpl.fetchByfindByEmail(String, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    MessagingIgnoredRecipients messagingIgnoredRecipients = list.get(0);

                    result = messagingIgnoredRecipients;

                    cacheResult(messagingIgnoredRecipients);

                    if ((messagingIgnoredRecipients.getEmail() == null) ||
                            !messagingIgnoredRecipients.getEmail().equals(email)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYEMAIL,
                            finderArgs, messagingIgnoredRecipients);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FINDBYEMAIL,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (MessagingIgnoredRecipients) result;
        }
    }

    /**
     * Removes the messaging ignored recipients where email = &#63; from the database.
     *
     * @param email the email
     * @return the messaging ignored recipients that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingIgnoredRecipients removeByfindByEmail(String email)
        throws NoSuchMessagingIgnoredRecipientsException, SystemException {
        MessagingIgnoredRecipients messagingIgnoredRecipients = findByfindByEmail(email);

        return remove(messagingIgnoredRecipients);
    }

    /**
     * Returns the number of messaging ignored recipientses where email = &#63;.
     *
     * @param email the email
     * @return the number of matching messaging ignored recipientses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByfindByEmail(String email) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_FINDBYEMAIL;

        Object[] finderArgs = new Object[] { email };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MESSAGINGIGNOREDRECIPIENTS_WHERE);

            boolean bindEmail = false;

            if (email == null) {
                query.append(_FINDER_COLUMN_FINDBYEMAIL_EMAIL_1);
            } else if (email.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_FINDBYEMAIL_EMAIL_3);
            } else {
                bindEmail = true;

                query.append(_FINDER_COLUMN_FINDBYEMAIL_EMAIL_2);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

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
     * Returns the messaging ignored recipients where userId = &#63; or throws a {@link com.ext.portlet.NoSuchMessagingIgnoredRecipientsException} if it could not be found.
     *
     * @param userId the user ID
     * @return the matching messaging ignored recipients
     * @throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException if a matching messaging ignored recipients could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingIgnoredRecipients findByfindByUserId(long userId)
        throws NoSuchMessagingIgnoredRecipientsException, SystemException {
        MessagingIgnoredRecipients messagingIgnoredRecipients = fetchByfindByUserId(userId);

        if (messagingIgnoredRecipients == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("userId=");
            msg.append(userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchMessagingIgnoredRecipientsException(msg.toString());
        }

        return messagingIgnoredRecipients;
    }

    /**
     * Returns the messaging ignored recipients where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param userId the user ID
     * @return the matching messaging ignored recipients, or <code>null</code> if a matching messaging ignored recipients could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingIgnoredRecipients fetchByfindByUserId(long userId)
        throws SystemException {
        return fetchByfindByUserId(userId, true);
    }

    /**
     * Returns the messaging ignored recipients where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param userId the user ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching messaging ignored recipients, or <code>null</code> if a matching messaging ignored recipients could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingIgnoredRecipients fetchByfindByUserId(long userId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { userId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_FINDBYUSERID,
                    finderArgs, this);
        }

        if (result instanceof MessagingIgnoredRecipients) {
            MessagingIgnoredRecipients messagingIgnoredRecipients = (MessagingIgnoredRecipients) result;

            if ((userId != messagingIgnoredRecipients.getUserId())) {
                result = null;
            }
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_MESSAGINGIGNOREDRECIPIENTS_WHERE);

            query.append(_FINDER_COLUMN_FINDBYUSERID_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                List<MessagingIgnoredRecipients> list = q.list();

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYUSERID,
                        finderArgs, list);
                } else {
                    if ((list.size() > 1) && _log.isWarnEnabled()) {
                        _log.warn(
                            "MessagingIgnoredRecipientsPersistenceImpl.fetchByfindByUserId(long, boolean) with parameters (" +
                            StringUtil.merge(finderArgs) +
                            ") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
                    }

                    MessagingIgnoredRecipients messagingIgnoredRecipients = list.get(0);

                    result = messagingIgnoredRecipients;

                    cacheResult(messagingIgnoredRecipients);

                    if ((messagingIgnoredRecipients.getUserId() != userId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYUSERID,
                            finderArgs, messagingIgnoredRecipients);
                    }
                }
            } catch (Exception e) {
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FINDBYUSERID,
                    finderArgs);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        if (result instanceof List<?>) {
            return null;
        } else {
            return (MessagingIgnoredRecipients) result;
        }
    }

    /**
     * Removes the messaging ignored recipients where userId = &#63; from the database.
     *
     * @param userId the user ID
     * @return the messaging ignored recipients that was removed
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingIgnoredRecipients removeByfindByUserId(long userId)
        throws NoSuchMessagingIgnoredRecipientsException, SystemException {
        MessagingIgnoredRecipients messagingIgnoredRecipients = findByfindByUserId(userId);

        return remove(messagingIgnoredRecipients);
    }

    /**
     * Returns the number of messaging ignored recipientses where userId = &#63;.
     *
     * @param userId the user ID
     * @return the number of matching messaging ignored recipientses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public int countByfindByUserId(long userId) throws SystemException {
        FinderPath finderPath = FINDER_PATH_COUNT_BY_FINDBYUSERID;

        Object[] finderArgs = new Object[] { userId };

        Long count = (Long) FinderCacheUtil.getResult(finderPath, finderArgs,
                this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MESSAGINGIGNOREDRECIPIENTS_WHERE);

            query.append(_FINDER_COLUMN_FINDBYUSERID_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

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
     * Caches the messaging ignored recipients in the entity cache if it is enabled.
     *
     * @param messagingIgnoredRecipients the messaging ignored recipients
     */
    @Override
    public void cacheResult(
        MessagingIgnoredRecipients messagingIgnoredRecipients) {
        EntityCacheUtil.putResult(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
            MessagingIgnoredRecipientsImpl.class,
            messagingIgnoredRecipients.getPrimaryKey(),
            messagingIgnoredRecipients);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYEMAIL,
            new Object[] { messagingIgnoredRecipients.getEmail() },
            messagingIgnoredRecipients);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYUSERID,
            new Object[] { messagingIgnoredRecipients.getUserId() },
            messagingIgnoredRecipients);

        messagingIgnoredRecipients.resetOriginalValues();
    }

    /**
     * Caches the messaging ignored recipientses in the entity cache if it is enabled.
     *
     * @param messagingIgnoredRecipientses the messaging ignored recipientses
     */
    @Override
    public void cacheResult(
        List<MessagingIgnoredRecipients> messagingIgnoredRecipientses) {
        for (MessagingIgnoredRecipients messagingIgnoredRecipients : messagingIgnoredRecipientses) {
            if (EntityCacheUtil.getResult(
                        MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
                        MessagingIgnoredRecipientsImpl.class,
                        messagingIgnoredRecipients.getPrimaryKey()) == null) {
                cacheResult(messagingIgnoredRecipients);
            } else {
                messagingIgnoredRecipients.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all messaging ignored recipientses.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MessagingIgnoredRecipientsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MessagingIgnoredRecipientsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the messaging ignored recipients.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(
        MessagingIgnoredRecipients messagingIgnoredRecipients) {
        EntityCacheUtil.removeResult(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
            MessagingIgnoredRecipientsImpl.class,
            messagingIgnoredRecipients.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(messagingIgnoredRecipients);
    }

    @Override
    public void clearCache(
        List<MessagingIgnoredRecipients> messagingIgnoredRecipientses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MessagingIgnoredRecipients messagingIgnoredRecipients : messagingIgnoredRecipientses) {
            EntityCacheUtil.removeResult(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
                MessagingIgnoredRecipientsImpl.class,
                messagingIgnoredRecipients.getPrimaryKey());

            clearUniqueFindersCache(messagingIgnoredRecipients);
        }
    }

    protected void cacheUniqueFindersCache(
        MessagingIgnoredRecipients messagingIgnoredRecipients) {
        if (messagingIgnoredRecipients.isNew()) {
            Object[] args = new Object[] { messagingIgnoredRecipients.getEmail() };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FINDBYEMAIL, args,
                Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYEMAIL, args,
                messagingIgnoredRecipients);

            args = new Object[] { messagingIgnoredRecipients.getUserId() };

            FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FINDBYUSERID, args,
                Long.valueOf(1));
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYUSERID, args,
                messagingIgnoredRecipients);
        } else {
            MessagingIgnoredRecipientsModelImpl messagingIgnoredRecipientsModelImpl =
                (MessagingIgnoredRecipientsModelImpl) messagingIgnoredRecipients;

            if ((messagingIgnoredRecipientsModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_FINDBYEMAIL.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        messagingIgnoredRecipients.getEmail()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FINDBYEMAIL,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYEMAIL,
                    args, messagingIgnoredRecipients);
            }

            if ((messagingIgnoredRecipientsModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_FINDBYUSERID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        messagingIgnoredRecipients.getUserId()
                    };

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FINDBYUSERID,
                    args, Long.valueOf(1));
                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYUSERID,
                    args, messagingIgnoredRecipients);
            }
        }
    }

    protected void clearUniqueFindersCache(
        MessagingIgnoredRecipients messagingIgnoredRecipients) {
        MessagingIgnoredRecipientsModelImpl messagingIgnoredRecipientsModelImpl = (MessagingIgnoredRecipientsModelImpl) messagingIgnoredRecipients;

        Object[] args = new Object[] { messagingIgnoredRecipients.getEmail() };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FINDBYEMAIL, args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FINDBYEMAIL, args);

        if ((messagingIgnoredRecipientsModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_FINDBYEMAIL.getColumnBitmask()) != 0) {
            args = new Object[] {
                    messagingIgnoredRecipientsModelImpl.getOriginalEmail()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FINDBYEMAIL, args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FINDBYEMAIL, args);
        }

        args = new Object[] { messagingIgnoredRecipients.getUserId() };

        FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FINDBYUSERID, args);
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FINDBYUSERID, args);

        if ((messagingIgnoredRecipientsModelImpl.getColumnBitmask() &
                FINDER_PATH_FETCH_BY_FINDBYUSERID.getColumnBitmask()) != 0) {
            args = new Object[] {
                    messagingIgnoredRecipientsModelImpl.getOriginalUserId()
                };

            FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FINDBYUSERID, args);
            FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FINDBYUSERID, args);
        }
    }

    /**
     * Creates a new messaging ignored recipients with the primary key. Does not add the messaging ignored recipients to the database.
     *
     * @param ignoredRecipientId the primary key for the new messaging ignored recipients
     * @return the new messaging ignored recipients
     */
    @Override
    public MessagingIgnoredRecipients create(long ignoredRecipientId) {
        MessagingIgnoredRecipients messagingIgnoredRecipients = new MessagingIgnoredRecipientsImpl();

        messagingIgnoredRecipients.setNew(true);
        messagingIgnoredRecipients.setPrimaryKey(ignoredRecipientId);

        return messagingIgnoredRecipients;
    }

    /**
     * Removes the messaging ignored recipients with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ignoredRecipientId the primary key of the messaging ignored recipients
     * @return the messaging ignored recipients that was removed
     * @throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException if a messaging ignored recipients with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingIgnoredRecipients remove(long ignoredRecipientId)
        throws NoSuchMessagingIgnoredRecipientsException, SystemException {
        return remove((Serializable) ignoredRecipientId);
    }

    /**
     * Removes the messaging ignored recipients with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the messaging ignored recipients
     * @return the messaging ignored recipients that was removed
     * @throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException if a messaging ignored recipients with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingIgnoredRecipients remove(Serializable primaryKey)
        throws NoSuchMessagingIgnoredRecipientsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MessagingIgnoredRecipients messagingIgnoredRecipients = (MessagingIgnoredRecipients) session.get(MessagingIgnoredRecipientsImpl.class,
                    primaryKey);

            if (messagingIgnoredRecipients == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMessagingIgnoredRecipientsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(messagingIgnoredRecipients);
        } catch (NoSuchMessagingIgnoredRecipientsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MessagingIgnoredRecipients removeImpl(
        MessagingIgnoredRecipients messagingIgnoredRecipients)
        throws SystemException {
        messagingIgnoredRecipients = toUnwrappedModel(messagingIgnoredRecipients);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(messagingIgnoredRecipients)) {
                messagingIgnoredRecipients = (MessagingIgnoredRecipients) session.get(MessagingIgnoredRecipientsImpl.class,
                        messagingIgnoredRecipients.getPrimaryKeyObj());
            }

            if (messagingIgnoredRecipients != null) {
                session.delete(messagingIgnoredRecipients);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (messagingIgnoredRecipients != null) {
            clearCache(messagingIgnoredRecipients);
        }

        return messagingIgnoredRecipients;
    }

    @Override
    public MessagingIgnoredRecipients updateImpl(
        com.ext.portlet.model.MessagingIgnoredRecipients messagingIgnoredRecipients)
        throws SystemException {
        messagingIgnoredRecipients = toUnwrappedModel(messagingIgnoredRecipients);

        boolean isNew = messagingIgnoredRecipients.isNew();

        Session session = null;

        try {
            session = openSession();

            if (messagingIgnoredRecipients.isNew()) {
                session.save(messagingIgnoredRecipients);

                messagingIgnoredRecipients.setNew(false);
            } else {
                session.merge(messagingIgnoredRecipients);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew ||
                !MessagingIgnoredRecipientsModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
            MessagingIgnoredRecipientsImpl.class,
            messagingIgnoredRecipients.getPrimaryKey(),
            messagingIgnoredRecipients);

        clearUniqueFindersCache(messagingIgnoredRecipients);
        cacheUniqueFindersCache(messagingIgnoredRecipients);

        return messagingIgnoredRecipients;
    }

    protected MessagingIgnoredRecipients toUnwrappedModel(
        MessagingIgnoredRecipients messagingIgnoredRecipients) {
        if (messagingIgnoredRecipients instanceof MessagingIgnoredRecipientsImpl) {
            return messagingIgnoredRecipients;
        }

        MessagingIgnoredRecipientsImpl messagingIgnoredRecipientsImpl = new MessagingIgnoredRecipientsImpl();

        messagingIgnoredRecipientsImpl.setNew(messagingIgnoredRecipients.isNew());
        messagingIgnoredRecipientsImpl.setPrimaryKey(messagingIgnoredRecipients.getPrimaryKey());

        messagingIgnoredRecipientsImpl.setIgnoredRecipientId(messagingIgnoredRecipients.getIgnoredRecipientId());
        messagingIgnoredRecipientsImpl.setEmail(messagingIgnoredRecipients.getEmail());
        messagingIgnoredRecipientsImpl.setName(messagingIgnoredRecipients.getName());
        messagingIgnoredRecipientsImpl.setUserId(messagingIgnoredRecipients.getUserId());
        messagingIgnoredRecipientsImpl.setCreateDate(messagingIgnoredRecipients.getCreateDate());

        return messagingIgnoredRecipientsImpl;
    }

    /**
     * Returns the messaging ignored recipients with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the messaging ignored recipients
     * @return the messaging ignored recipients
     * @throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException if a messaging ignored recipients with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingIgnoredRecipients findByPrimaryKey(Serializable primaryKey)
        throws NoSuchMessagingIgnoredRecipientsException, SystemException {
        MessagingIgnoredRecipients messagingIgnoredRecipients = fetchByPrimaryKey(primaryKey);

        if (messagingIgnoredRecipients == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchMessagingIgnoredRecipientsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return messagingIgnoredRecipients;
    }

    /**
     * Returns the messaging ignored recipients with the primary key or throws a {@link com.ext.portlet.NoSuchMessagingIgnoredRecipientsException} if it could not be found.
     *
     * @param ignoredRecipientId the primary key of the messaging ignored recipients
     * @return the messaging ignored recipients
     * @throws com.ext.portlet.NoSuchMessagingIgnoredRecipientsException if a messaging ignored recipients with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingIgnoredRecipients findByPrimaryKey(long ignoredRecipientId)
        throws NoSuchMessagingIgnoredRecipientsException, SystemException {
        return findByPrimaryKey((Serializable) ignoredRecipientId);
    }

    /**
     * Returns the messaging ignored recipients with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the messaging ignored recipients
     * @return the messaging ignored recipients, or <code>null</code> if a messaging ignored recipients with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingIgnoredRecipients fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        MessagingIgnoredRecipients messagingIgnoredRecipients = (MessagingIgnoredRecipients) EntityCacheUtil.getResult(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
                MessagingIgnoredRecipientsImpl.class, primaryKey);

        if (messagingIgnoredRecipients == _nullMessagingIgnoredRecipients) {
            return null;
        }

        if (messagingIgnoredRecipients == null) {
            Session session = null;

            try {
                session = openSession();

                messagingIgnoredRecipients = (MessagingIgnoredRecipients) session.get(MessagingIgnoredRecipientsImpl.class,
                        primaryKey);

                if (messagingIgnoredRecipients != null) {
                    cacheResult(messagingIgnoredRecipients);
                } else {
                    EntityCacheUtil.putResult(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
                        MessagingIgnoredRecipientsImpl.class, primaryKey,
                        _nullMessagingIgnoredRecipients);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(MessagingIgnoredRecipientsModelImpl.ENTITY_CACHE_ENABLED,
                    MessagingIgnoredRecipientsImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return messagingIgnoredRecipients;
    }

    /**
     * Returns the messaging ignored recipients with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ignoredRecipientId the primary key of the messaging ignored recipients
     * @return the messaging ignored recipients, or <code>null</code> if a messaging ignored recipients with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingIgnoredRecipients fetchByPrimaryKey(long ignoredRecipientId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) ignoredRecipientId);
    }

    /**
     * Returns all the messaging ignored recipientses.
     *
     * @return the messaging ignored recipientses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MessagingIgnoredRecipients> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the messaging ignored recipientses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingIgnoredRecipientsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of messaging ignored recipientses
     * @param end the upper bound of the range of messaging ignored recipientses (not inclusive)
     * @return the range of messaging ignored recipientses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MessagingIgnoredRecipients> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the messaging ignored recipientses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingIgnoredRecipientsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of messaging ignored recipientses
     * @param end the upper bound of the range of messaging ignored recipientses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of messaging ignored recipientses
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MessagingIgnoredRecipients> findAll(int start, int end,
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

        List<MessagingIgnoredRecipients> list = (List<MessagingIgnoredRecipients>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MESSAGINGIGNOREDRECIPIENTS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MESSAGINGIGNOREDRECIPIENTS;

                if (pagination) {
                    sql = sql.concat(MessagingIgnoredRecipientsModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<MessagingIgnoredRecipients>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MessagingIgnoredRecipients>(list);
                } else {
                    list = (List<MessagingIgnoredRecipients>) QueryUtil.list(q,
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
     * Removes all the messaging ignored recipientses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (MessagingIgnoredRecipients messagingIgnoredRecipients : findAll()) {
            remove(messagingIgnoredRecipients);
        }
    }

    /**
     * Returns the number of messaging ignored recipientses.
     *
     * @return the number of messaging ignored recipientses
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

                Query q = session.createQuery(_SQL_COUNT_MESSAGINGIGNOREDRECIPIENTS);

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
     * Initializes the messaging ignored recipients persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.MessagingIgnoredRecipients")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MessagingIgnoredRecipients>> listenersList = new ArrayList<ModelListener<MessagingIgnoredRecipients>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MessagingIgnoredRecipients>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MessagingIgnoredRecipientsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
