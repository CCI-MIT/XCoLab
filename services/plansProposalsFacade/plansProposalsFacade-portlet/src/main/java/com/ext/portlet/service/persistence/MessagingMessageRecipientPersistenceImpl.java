package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchMessagingMessageRecipientException;
import com.ext.portlet.model.MessagingMessageRecipient;
import com.ext.portlet.model.impl.MessagingMessageRecipientImpl;
import com.ext.portlet.model.impl.MessagingMessageRecipientModelImpl;
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
=======
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)
import com.ext.portlet.service.persistence.MessagingMessageRecipientPersistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
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
 * The persistence implementation for the messaging message recipient service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageRecipientPersistence
 * @see MessagingMessageRecipientUtil
 * @generated
 */
public class MessagingMessageRecipientPersistenceImpl
    extends BasePersistenceImpl<MessagingMessageRecipient>
    implements MessagingMessageRecipientPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MessagingMessageRecipientUtil} to access the messaging message recipient persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MessagingMessageRecipientImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MessagingMessageRecipientModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageRecipientModelImpl.FINDER_CACHE_ENABLED,
            MessagingMessageRecipientImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MessagingMessageRecipientModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageRecipientModelImpl.FINDER_CACHE_ENABLED,
            MessagingMessageRecipientImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MessagingMessageRecipientModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageRecipientModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    private static final String _SQL_SELECT_MESSAGINGMESSAGERECIPIENT = "SELECT messagingMessageRecipient FROM MessagingMessageRecipient messagingMessageRecipient";
    private static final String _SQL_COUNT_MESSAGINGMESSAGERECIPIENT = "SELECT COUNT(messagingMessageRecipient) FROM MessagingMessageRecipient messagingMessageRecipient";
    private static final String _ORDER_BY_ENTITY_ALIAS = "messagingMessageRecipient.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MessagingMessageRecipient exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MessagingMessageRecipientPersistenceImpl.class);
    private static MessagingMessageRecipient _nullMessagingMessageRecipient = new MessagingMessageRecipientImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MessagingMessageRecipient> toCacheModel() {
                return _nullMessagingMessageRecipientCacheModel;
            }
        };

    private static CacheModel<MessagingMessageRecipient> _nullMessagingMessageRecipientCacheModel =
        new CacheModel<MessagingMessageRecipient>() {
            @Override
            public MessagingMessageRecipient toEntityModel() {
                return _nullMessagingMessageRecipient;
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
    public MessagingMessageRecipientPersistenceImpl() {
        setModelClass(MessagingMessageRecipient.class);
    }
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)

    /**
     * Caches the messaging message recipient in the entity cache if it is enabled.
     *
     * @param messagingMessageRecipient the messaging message recipient
     */
    @Override
    public void cacheResult(MessagingMessageRecipient messagingMessageRecipient) {
        EntityCacheUtil.putResult(MessagingMessageRecipientModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageRecipientImpl.class,
            messagingMessageRecipient.getPrimaryKey(), messagingMessageRecipient);

        messagingMessageRecipient.resetOriginalValues();
    }

    /**
     * Caches the messaging message recipients in the entity cache if it is enabled.
     *
     * @param messagingMessageRecipients the messaging message recipients
     */
    @Override
    public void cacheResult(
        List<MessagingMessageRecipient> messagingMessageRecipients) {
        for (MessagingMessageRecipient messagingMessageRecipient : messagingMessageRecipients) {
            if (EntityCacheUtil.getResult(
                        MessagingMessageRecipientModelImpl.ENTITY_CACHE_ENABLED,
                        MessagingMessageRecipientImpl.class,
                        messagingMessageRecipient.getPrimaryKey()) == null) {
                cacheResult(messagingMessageRecipient);
            } else {
                messagingMessageRecipient.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all messaging message recipients.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MessagingMessageRecipientImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MessagingMessageRecipientImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the messaging message recipient.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(MessagingMessageRecipient messagingMessageRecipient) {
        EntityCacheUtil.removeResult(MessagingMessageRecipientModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageRecipientImpl.class,
            messagingMessageRecipient.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<MessagingMessageRecipient> messagingMessageRecipients) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MessagingMessageRecipient messagingMessageRecipient : messagingMessageRecipients) {
            EntityCacheUtil.removeResult(MessagingMessageRecipientModelImpl.ENTITY_CACHE_ENABLED,
                MessagingMessageRecipientImpl.class,
                messagingMessageRecipient.getPrimaryKey());
        }
    }

    /**
     * Creates a new messaging message recipient with the primary key. Does not add the messaging message recipient to the database.
     *
     * @param recipientId the primary key for the new messaging message recipient
     * @return the new messaging message recipient
     */
    @Override
    public MessagingMessageRecipient create(long recipientId) {
        MessagingMessageRecipient messagingMessageRecipient = new MessagingMessageRecipientImpl();

        messagingMessageRecipient.setNew(true);
        messagingMessageRecipient.setPrimaryKey(recipientId);

        return messagingMessageRecipient;
    }

    /**
     * Removes the messaging message recipient with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param recipientId the primary key of the messaging message recipient
     * @return the messaging message recipient that was removed
     * @throws com.ext.portlet.NoSuchMessagingMessageRecipientException if a messaging message recipient with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageRecipient remove(long recipientId)
        throws NoSuchMessagingMessageRecipientException, SystemException {
        return remove((Serializable) recipientId);
    }

    /**
     * Removes the messaging message recipient with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the messaging message recipient
     * @return the messaging message recipient that was removed
     * @throws com.ext.portlet.NoSuchMessagingMessageRecipientException if a messaging message recipient with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageRecipient remove(Serializable primaryKey)
        throws NoSuchMessagingMessageRecipientException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MessagingMessageRecipient messagingMessageRecipient = (MessagingMessageRecipient) session.get(MessagingMessageRecipientImpl.class,
                    primaryKey);

            if (messagingMessageRecipient == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMessagingMessageRecipientException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(messagingMessageRecipient);
        } catch (NoSuchMessagingMessageRecipientException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MessagingMessageRecipient removeImpl(
        MessagingMessageRecipient messagingMessageRecipient)
        throws SystemException {
        messagingMessageRecipient = toUnwrappedModel(messagingMessageRecipient);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(messagingMessageRecipient)) {
                messagingMessageRecipient = (MessagingMessageRecipient) session.get(MessagingMessageRecipientImpl.class,
                        messagingMessageRecipient.getPrimaryKeyObj());
            }

            if (messagingMessageRecipient != null) {
                session.delete(messagingMessageRecipient);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (messagingMessageRecipient != null) {
            clearCache(messagingMessageRecipient);
        }

        return messagingMessageRecipient;
    }

    @Override
    public MessagingMessageRecipient updateImpl(
        com.ext.portlet.model.MessagingMessageRecipient messagingMessageRecipient)
        throws SystemException {
        messagingMessageRecipient = toUnwrappedModel(messagingMessageRecipient);

        boolean isNew = messagingMessageRecipient.isNew();

        Session session = null;

        try {
            session = openSession();

            if (messagingMessageRecipient.isNew()) {
                session.save(messagingMessageRecipient);

                messagingMessageRecipient.setNew(false);
            } else {
                session.merge(messagingMessageRecipient);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(MessagingMessageRecipientModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageRecipientImpl.class,
            messagingMessageRecipient.getPrimaryKey(), messagingMessageRecipient);

        return messagingMessageRecipient;
    }

    protected MessagingMessageRecipient toUnwrappedModel(
        MessagingMessageRecipient messagingMessageRecipient) {
        if (messagingMessageRecipient instanceof MessagingMessageRecipientImpl) {
            return messagingMessageRecipient;
        }

        MessagingMessageRecipientImpl messagingMessageRecipientImpl = new MessagingMessageRecipientImpl();

        messagingMessageRecipientImpl.setNew(messagingMessageRecipient.isNew());
        messagingMessageRecipientImpl.setPrimaryKey(messagingMessageRecipient.getPrimaryKey());

        messagingMessageRecipientImpl.setRecipientId(messagingMessageRecipient.getRecipientId());
        messagingMessageRecipientImpl.setMessageId(messagingMessageRecipient.getMessageId());
        messagingMessageRecipientImpl.setUserId(messagingMessageRecipient.getUserId());
        messagingMessageRecipientImpl.setEmailAddress(messagingMessageRecipient.getEmailAddress());

        return messagingMessageRecipientImpl;
    }

    /**
     * Returns the messaging message recipient with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the messaging message recipient
     * @return the messaging message recipient
     * @throws com.ext.portlet.NoSuchMessagingMessageRecipientException if a messaging message recipient with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageRecipient findByPrimaryKey(Serializable primaryKey)
        throws NoSuchMessagingMessageRecipientException, SystemException {
        MessagingMessageRecipient messagingMessageRecipient = fetchByPrimaryKey(primaryKey);

        if (messagingMessageRecipient == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchMessagingMessageRecipientException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return messagingMessageRecipient;
    }

    /**
     * Returns the messaging message recipient with the primary key or throws a {@link com.ext.portlet.NoSuchMessagingMessageRecipientException} if it could not be found.
     *
     * @param recipientId the primary key of the messaging message recipient
     * @return the messaging message recipient
     * @throws com.ext.portlet.NoSuchMessagingMessageRecipientException if a messaging message recipient with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageRecipient findByPrimaryKey(long recipientId)
        throws NoSuchMessagingMessageRecipientException, SystemException {
        return findByPrimaryKey((Serializable) recipientId);
    }

    /**
     * Returns the messaging message recipient with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the messaging message recipient
     * @return the messaging message recipient, or <code>null</code> if a messaging message recipient with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageRecipient fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        MessagingMessageRecipient messagingMessageRecipient = (MessagingMessageRecipient) EntityCacheUtil.getResult(MessagingMessageRecipientModelImpl.ENTITY_CACHE_ENABLED,
                MessagingMessageRecipientImpl.class, primaryKey);

        if (messagingMessageRecipient == _nullMessagingMessageRecipient) {
            return null;
        }

        if (messagingMessageRecipient == null) {
            Session session = null;

            try {
                session = openSession();

                messagingMessageRecipient = (MessagingMessageRecipient) session.get(MessagingMessageRecipientImpl.class,
                        primaryKey);

                if (messagingMessageRecipient != null) {
                    cacheResult(messagingMessageRecipient);
                } else {
                    EntityCacheUtil.putResult(MessagingMessageRecipientModelImpl.ENTITY_CACHE_ENABLED,
                        MessagingMessageRecipientImpl.class, primaryKey,
                        _nullMessagingMessageRecipient);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(MessagingMessageRecipientModelImpl.ENTITY_CACHE_ENABLED,
                    MessagingMessageRecipientImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return messagingMessageRecipient;
    }

    /**
     * Returns the messaging message recipient with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param recipientId the primary key of the messaging message recipient
     * @return the messaging message recipient, or <code>null</code> if a messaging message recipient with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageRecipient fetchByPrimaryKey(long recipientId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) recipientId);
    }

    /**
     * Returns all the messaging message recipients.
     *
     * @return the messaging message recipients
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MessagingMessageRecipient> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the messaging message recipients.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingMessageRecipientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of messaging message recipients
     * @param end the upper bound of the range of messaging message recipients (not inclusive)
     * @return the range of messaging message recipients
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MessagingMessageRecipient> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the messaging message recipients.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingMessageRecipientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of messaging message recipients
     * @param end the upper bound of the range of messaging message recipients (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of messaging message recipients
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MessagingMessageRecipient> findAll(int start, int end,
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

        List<MessagingMessageRecipient> list = (List<MessagingMessageRecipient>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MESSAGINGMESSAGERECIPIENT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MESSAGINGMESSAGERECIPIENT;

                if (pagination) {
                    sql = sql.concat(MessagingMessageRecipientModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<MessagingMessageRecipient>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MessagingMessageRecipient>(list);
                } else {
                    list = (List<MessagingMessageRecipient>) QueryUtil.list(q,
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
     * Removes all the messaging message recipients from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (MessagingMessageRecipient messagingMessageRecipient : findAll()) {
            remove(messagingMessageRecipient);
        }
    }

    /**
     * Returns the number of messaging message recipients.
     *
     * @return the number of messaging message recipients
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

                Query q = session.createQuery(_SQL_COUNT_MESSAGINGMESSAGERECIPIENT);

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
     * Initializes the messaging message recipient persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.MessagingMessageRecipient")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MessagingMessageRecipient>> listenersList = new ArrayList<ModelListener<MessagingMessageRecipient>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MessagingMessageRecipient>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MessagingMessageRecipientImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
