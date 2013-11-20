package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchMessagingMessageException;
import com.ext.portlet.model.MessagingMessage;
import com.ext.portlet.model.impl.MessagingMessageImpl;
import com.ext.portlet.model.impl.MessagingMessageModelImpl;
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
=======
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)
import com.ext.portlet.service.persistence.MessagingMessagePersistence;

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
 * The persistence implementation for the messaging message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessagePersistence
 * @see MessagingMessageUtil
 * @generated
 */
public class MessagingMessagePersistenceImpl extends BasePersistenceImpl<MessagingMessage>
    implements MessagingMessagePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MessagingMessageUtil} to access the messaging message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MessagingMessageImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MessagingMessageModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageModelImpl.FINDER_CACHE_ENABLED,
            MessagingMessageImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MessagingMessageModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageModelImpl.FINDER_CACHE_ENABLED,
            MessagingMessageImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MessagingMessageModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_MESSAGINGMESSAGE = "SELECT messagingMessage FROM MessagingMessage messagingMessage";
    private static final String _SQL_COUNT_MESSAGINGMESSAGE = "SELECT COUNT(messagingMessage) FROM MessagingMessage messagingMessage";
    private static final String _ORDER_BY_ENTITY_ALIAS = "messagingMessage.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MessagingMessage exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MessagingMessagePersistenceImpl.class);
    private static MessagingMessage _nullMessagingMessage = new MessagingMessageImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MessagingMessage> toCacheModel() {
                return _nullMessagingMessageCacheModel;
            }
        };

    private static CacheModel<MessagingMessage> _nullMessagingMessageCacheModel = new CacheModel<MessagingMessage>() {
            @Override
            public MessagingMessage toEntityModel() {
                return _nullMessagingMessage;
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
    public MessagingMessagePersistenceImpl() {
        setModelClass(MessagingMessage.class);
    }
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)

    /**
     * Caches the messaging message in the entity cache if it is enabled.
     *
     * @param messagingMessage the messaging message
     */
    @Override
    public void cacheResult(MessagingMessage messagingMessage) {
        EntityCacheUtil.putResult(MessagingMessageModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageImpl.class, messagingMessage.getPrimaryKey(),
            messagingMessage);

        messagingMessage.resetOriginalValues();
    }

    /**
     * Caches the messaging messages in the entity cache if it is enabled.
     *
     * @param messagingMessages the messaging messages
     */
    @Override
    public void cacheResult(List<MessagingMessage> messagingMessages) {
        for (MessagingMessage messagingMessage : messagingMessages) {
            if (EntityCacheUtil.getResult(
                        MessagingMessageModelImpl.ENTITY_CACHE_ENABLED,
                        MessagingMessageImpl.class,
                        messagingMessage.getPrimaryKey()) == null) {
                cacheResult(messagingMessage);
            } else {
                messagingMessage.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all messaging messages.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MessagingMessageImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MessagingMessageImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the messaging message.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(MessagingMessage messagingMessage) {
        EntityCacheUtil.removeResult(MessagingMessageModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageImpl.class, messagingMessage.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<MessagingMessage> messagingMessages) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MessagingMessage messagingMessage : messagingMessages) {
            EntityCacheUtil.removeResult(MessagingMessageModelImpl.ENTITY_CACHE_ENABLED,
                MessagingMessageImpl.class, messagingMessage.getPrimaryKey());
        }
    }

    /**
     * Creates a new messaging message with the primary key. Does not add the messaging message to the database.
     *
     * @param messageId the primary key for the new messaging message
     * @return the new messaging message
     */
    @Override
    public MessagingMessage create(long messageId) {
        MessagingMessage messagingMessage = new MessagingMessageImpl();

        messagingMessage.setNew(true);
        messagingMessage.setPrimaryKey(messageId);

        return messagingMessage;
    }

    /**
     * Removes the messaging message with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param messageId the primary key of the messaging message
     * @return the messaging message that was removed
     * @throws com.ext.portlet.NoSuchMessagingMessageException if a messaging message with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessage remove(long messageId)
        throws NoSuchMessagingMessageException, SystemException {
        return remove((Serializable) messageId);
    }

    /**
     * Removes the messaging message with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the messaging message
     * @return the messaging message that was removed
     * @throws com.ext.portlet.NoSuchMessagingMessageException if a messaging message with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessage remove(Serializable primaryKey)
        throws NoSuchMessagingMessageException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MessagingMessage messagingMessage = (MessagingMessage) session.get(MessagingMessageImpl.class,
                    primaryKey);

            if (messagingMessage == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMessagingMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(messagingMessage);
        } catch (NoSuchMessagingMessageException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MessagingMessage removeImpl(MessagingMessage messagingMessage)
        throws SystemException {
        messagingMessage = toUnwrappedModel(messagingMessage);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(messagingMessage)) {
                messagingMessage = (MessagingMessage) session.get(MessagingMessageImpl.class,
                        messagingMessage.getPrimaryKeyObj());
            }

            if (messagingMessage != null) {
                session.delete(messagingMessage);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (messagingMessage != null) {
            clearCache(messagingMessage);
        }

        return messagingMessage;
    }

    @Override
    public MessagingMessage updateImpl(
        com.ext.portlet.model.MessagingMessage messagingMessage)
        throws SystemException {
        messagingMessage = toUnwrappedModel(messagingMessage);

        boolean isNew = messagingMessage.isNew();

        Session session = null;

        try {
            session = openSession();

            if (messagingMessage.isNew()) {
                session.save(messagingMessage);

                messagingMessage.setNew(false);
            } else {
                session.merge(messagingMessage);
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

        EntityCacheUtil.putResult(MessagingMessageModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageImpl.class, messagingMessage.getPrimaryKey(),
            messagingMessage);

        return messagingMessage;
    }

    protected MessagingMessage toUnwrappedModel(
        MessagingMessage messagingMessage) {
        if (messagingMessage instanceof MessagingMessageImpl) {
            return messagingMessage;
        }

        MessagingMessageImpl messagingMessageImpl = new MessagingMessageImpl();

        messagingMessageImpl.setNew(messagingMessage.isNew());
        messagingMessageImpl.setPrimaryKey(messagingMessage.getPrimaryKey());

        messagingMessageImpl.setMessageId(messagingMessage.getMessageId());
        messagingMessageImpl.setName(messagingMessage.getName());
        messagingMessageImpl.setDescription(messagingMessage.getDescription());
        messagingMessageImpl.setSubject(messagingMessage.getSubject());
        messagingMessageImpl.setBody(messagingMessage.getBody());
        messagingMessageImpl.setReplyTo(messagingMessage.getReplyTo());
        messagingMessageImpl.setSendToAll(messagingMessage.isSendToAll());
        messagingMessageImpl.setConversionCount(messagingMessage.getConversionCount());
        messagingMessageImpl.setRedirectURL(messagingMessage.getRedirectURL());
        messagingMessageImpl.setCreatorId(messagingMessage.getCreatorId());
        messagingMessageImpl.setCreateDate(messagingMessage.getCreateDate());
        messagingMessageImpl.setModifiedDate(messagingMessage.getModifiedDate());

        return messagingMessageImpl;
    }

    /**
     * Returns the messaging message with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the messaging message
     * @return the messaging message
     * @throws com.ext.portlet.NoSuchMessagingMessageException if a messaging message with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessage findByPrimaryKey(Serializable primaryKey)
        throws NoSuchMessagingMessageException, SystemException {
        MessagingMessage messagingMessage = fetchByPrimaryKey(primaryKey);

        if (messagingMessage == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchMessagingMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return messagingMessage;
    }

    /**
     * Returns the messaging message with the primary key or throws a {@link com.ext.portlet.NoSuchMessagingMessageException} if it could not be found.
     *
     * @param messageId the primary key of the messaging message
     * @return the messaging message
     * @throws com.ext.portlet.NoSuchMessagingMessageException if a messaging message with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessage findByPrimaryKey(long messageId)
        throws NoSuchMessagingMessageException, SystemException {
        return findByPrimaryKey((Serializable) messageId);
    }

    /**
     * Returns the messaging message with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the messaging message
     * @return the messaging message, or <code>null</code> if a messaging message with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessage fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        MessagingMessage messagingMessage = (MessagingMessage) EntityCacheUtil.getResult(MessagingMessageModelImpl.ENTITY_CACHE_ENABLED,
                MessagingMessageImpl.class, primaryKey);

        if (messagingMessage == _nullMessagingMessage) {
            return null;
        }

        if (messagingMessage == null) {
            Session session = null;

            try {
                session = openSession();

                messagingMessage = (MessagingMessage) session.get(MessagingMessageImpl.class,
                        primaryKey);

                if (messagingMessage != null) {
                    cacheResult(messagingMessage);
                } else {
                    EntityCacheUtil.putResult(MessagingMessageModelImpl.ENTITY_CACHE_ENABLED,
                        MessagingMessageImpl.class, primaryKey,
                        _nullMessagingMessage);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(MessagingMessageModelImpl.ENTITY_CACHE_ENABLED,
                    MessagingMessageImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return messagingMessage;
    }

    /**
     * Returns the messaging message with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param messageId the primary key of the messaging message
     * @return the messaging message, or <code>null</code> if a messaging message with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessage fetchByPrimaryKey(long messageId)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) messageId);
    }

    /**
     * Returns all the messaging messages.
     *
     * @return the messaging messages
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MessagingMessage> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the messaging messages.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of messaging messages
     * @param end the upper bound of the range of messaging messages (not inclusive)
     * @return the range of messaging messages
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MessagingMessage> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the messaging messages.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingMessageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of messaging messages
     * @param end the upper bound of the range of messaging messages (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of messaging messages
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<MessagingMessage> findAll(int start, int end,
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

        List<MessagingMessage> list = (List<MessagingMessage>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MESSAGINGMESSAGE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MESSAGINGMESSAGE;

                if (pagination) {
                    sql = sql.concat(MessagingMessageModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<MessagingMessage>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<MessagingMessage>(list);
                } else {
                    list = (List<MessagingMessage>) QueryUtil.list(q,
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
     * Removes all the messaging messages from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (MessagingMessage messagingMessage : findAll()) {
            remove(messagingMessage);
        }
    }

    /**
     * Returns the number of messaging messages.
     *
     * @return the number of messaging messages
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

                Query q = session.createQuery(_SQL_COUNT_MESSAGINGMESSAGE);

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
     * Initializes the messaging message persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.MessagingMessage")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MessagingMessage>> listenersList = new ArrayList<ModelListener<MessagingMessage>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MessagingMessage>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MessagingMessageImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
