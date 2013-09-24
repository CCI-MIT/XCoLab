package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchMessagingUserPreferencesException;
import com.ext.portlet.model.MessagingUserPreferences;
import com.ext.portlet.model.impl.MessagingUserPreferencesImpl;
import com.ext.portlet.model.impl.MessagingUserPreferencesModelImpl;
import com.ext.portlet.service.persistence.ActivitySubscriptionPersistence;
import com.ext.portlet.service.persistence.BalloonStatsEntryPersistence;
import com.ext.portlet.service.persistence.ContestDebatePersistence;
import com.ext.portlet.service.persistence.ContestPersistence;
import com.ext.portlet.service.persistence.ContestPhaseColumnPersistence;
import com.ext.portlet.service.persistence.ContestPhasePersistence;
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
import com.ext.portlet.service.persistence.PlanPositionsPersistence;
import com.ext.portlet.service.persistence.PlanPropertyFilterPersistence;
import com.ext.portlet.service.persistence.PlanRelatedPersistence;
import com.ext.portlet.service.persistence.PlanSectionDefinitionPersistence;
import com.ext.portlet.service.persistence.PlanSectionPersistence;
import com.ext.portlet.service.persistence.PlanSectionPlanMapPersistence;
import com.ext.portlet.service.persistence.PlanTeamHistoryPersistence;
import com.ext.portlet.service.persistence.PlanTemplatePersistence;
import com.ext.portlet.service.persistence.PlanTemplateSectionPersistence;
import com.ext.portlet.service.persistence.PlanTypeAttributePersistence;
import com.ext.portlet.service.persistence.PlanTypeColumnPersistence;
import com.ext.portlet.service.persistence.PlanTypePersistence;
import com.ext.portlet.service.persistence.PlanVotePersistence;
import com.ext.portlet.service.persistence.PlansFilterPersistence;
import com.ext.portlet.service.persistence.PlansFilterPositionPersistence;
import com.ext.portlet.service.persistence.PlansUserSettingsPersistence;
import com.ext.portlet.service.persistence.Proposal2PhasePersistence;
import com.ext.portlet.service.persistence.ProposalAttributePersistence;
import com.ext.portlet.service.persistence.ProposalAttributeTypePersistence;
import com.ext.portlet.service.persistence.ProposalPersistence;
import com.ext.portlet.service.persistence.ProposalVersionPersistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the messaging user preferences service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingUserPreferencesPersistence
 * @see MessagingUserPreferencesUtil
 * @generated
 */
public class MessagingUserPreferencesPersistenceImpl extends BasePersistenceImpl<MessagingUserPreferences>
    implements MessagingUserPreferencesPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MessagingUserPreferencesUtil} to access the messaging user preferences persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MessagingUserPreferencesImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_FETCH_BY_MESSAGINGPREFERENCES = new FinderPath(MessagingUserPreferencesModelImpl.ENTITY_CACHE_ENABLED,
            MessagingUserPreferencesModelImpl.FINDER_CACHE_ENABLED,
            MessagingUserPreferencesImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchBymessagingPreferences",
            new String[] { Long.class.getName() },
            MessagingUserPreferencesModelImpl.USERID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_MESSAGINGPREFERENCES = new FinderPath(MessagingUserPreferencesModelImpl.ENTITY_CACHE_ENABLED,
            MessagingUserPreferencesModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countBymessagingPreferences", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MessagingUserPreferencesModelImpl.ENTITY_CACHE_ENABLED,
            MessagingUserPreferencesModelImpl.FINDER_CACHE_ENABLED,
            MessagingUserPreferencesImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MessagingUserPreferencesModelImpl.ENTITY_CACHE_ENABLED,
            MessagingUserPreferencesModelImpl.FINDER_CACHE_ENABLED,
            MessagingUserPreferencesImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MessagingUserPreferencesModelImpl.ENTITY_CACHE_ENABLED,
            MessagingUserPreferencesModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_MESSAGINGUSERPREFERENCES = "SELECT messagingUserPreferences FROM MessagingUserPreferences messagingUserPreferences";
    private static final String _SQL_SELECT_MESSAGINGUSERPREFERENCES_WHERE = "SELECT messagingUserPreferences FROM MessagingUserPreferences messagingUserPreferences WHERE ";
    private static final String _SQL_COUNT_MESSAGINGUSERPREFERENCES = "SELECT COUNT(messagingUserPreferences) FROM MessagingUserPreferences messagingUserPreferences";
    private static final String _SQL_COUNT_MESSAGINGUSERPREFERENCES_WHERE = "SELECT COUNT(messagingUserPreferences) FROM MessagingUserPreferences messagingUserPreferences WHERE ";
    private static final String _FINDER_COLUMN_MESSAGINGPREFERENCES_USERID_2 = "messagingUserPreferences.userId = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "messagingUserPreferences.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MessagingUserPreferences exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MessagingUserPreferences exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MessagingUserPreferencesPersistenceImpl.class);
    private static MessagingUserPreferences _nullMessagingUserPreferences = new MessagingUserPreferencesImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MessagingUserPreferences> toCacheModel() {
                return _nullMessagingUserPreferencesCacheModel;
            }
        };

    private static CacheModel<MessagingUserPreferences> _nullMessagingUserPreferencesCacheModel =
        new CacheModel<MessagingUserPreferences>() {
            public MessagingUserPreferences toEntityModel() {
                return _nullMessagingUserPreferences;
            }
        };

    @BeanReference(type = ActivitySubscriptionPersistence.class)
    protected ActivitySubscriptionPersistence activitySubscriptionPersistence;
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
    @BeanReference(type = ProposalVersionPersistence.class)
    protected ProposalVersionPersistence proposalVersionPersistence;
    @BeanReference(type = ResourcePersistence.class)
    protected ResourcePersistence resourcePersistence;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;

    /**
     * Caches the messaging user preferences in the entity cache if it is enabled.
     *
     * @param messagingUserPreferences the messaging user preferences
     */
    public void cacheResult(MessagingUserPreferences messagingUserPreferences) {
        EntityCacheUtil.putResult(MessagingUserPreferencesModelImpl.ENTITY_CACHE_ENABLED,
            MessagingUserPreferencesImpl.class,
            messagingUserPreferences.getPrimaryKey(), messagingUserPreferences);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGINGPREFERENCES,
            new Object[] { Long.valueOf(messagingUserPreferences.getUserId()) },
            messagingUserPreferences);

        messagingUserPreferences.resetOriginalValues();
    }

    /**
     * Caches the messaging user preferenceses in the entity cache if it is enabled.
     *
     * @param messagingUserPreferenceses the messaging user preferenceses
     */
    public void cacheResult(
        List<MessagingUserPreferences> messagingUserPreferenceses) {
        for (MessagingUserPreferences messagingUserPreferences : messagingUserPreferenceses) {
            if (EntityCacheUtil.getResult(
                        MessagingUserPreferencesModelImpl.ENTITY_CACHE_ENABLED,
                        MessagingUserPreferencesImpl.class,
                        messagingUserPreferences.getPrimaryKey()) == null) {
                cacheResult(messagingUserPreferences);
            } else {
                messagingUserPreferences.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all messaging user preferenceses.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MessagingUserPreferencesImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MessagingUserPreferencesImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the messaging user preferences.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(MessagingUserPreferences messagingUserPreferences) {
        EntityCacheUtil.removeResult(MessagingUserPreferencesModelImpl.ENTITY_CACHE_ENABLED,
            MessagingUserPreferencesImpl.class,
            messagingUserPreferences.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(messagingUserPreferences);
    }

    @Override
    public void clearCache(
        List<MessagingUserPreferences> messagingUserPreferenceses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MessagingUserPreferences messagingUserPreferences : messagingUserPreferenceses) {
            EntityCacheUtil.removeResult(MessagingUserPreferencesModelImpl.ENTITY_CACHE_ENABLED,
                MessagingUserPreferencesImpl.class,
                messagingUserPreferences.getPrimaryKey());

            clearUniqueFindersCache(messagingUserPreferences);
        }
    }

    protected void clearUniqueFindersCache(
        MessagingUserPreferences messagingUserPreferences) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MESSAGINGPREFERENCES,
            new Object[] { Long.valueOf(messagingUserPreferences.getUserId()) });
    }

    /**
     * Creates a new messaging user preferences with the primary key. Does not add the messaging user preferences to the database.
     *
     * @param messagingPreferencesId the primary key for the new messaging user preferences
     * @return the new messaging user preferences
     */
    public MessagingUserPreferences create(long messagingPreferencesId) {
        MessagingUserPreferences messagingUserPreferences = new MessagingUserPreferencesImpl();

        messagingUserPreferences.setNew(true);
        messagingUserPreferences.setPrimaryKey(messagingPreferencesId);

        return messagingUserPreferences;
    }

    /**
     * Removes the messaging user preferences with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param messagingPreferencesId the primary key of the messaging user preferences
     * @return the messaging user preferences that was removed
     * @throws com.ext.portlet.NoSuchMessagingUserPreferencesException if a messaging user preferences with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessagingUserPreferences remove(long messagingPreferencesId)
        throws NoSuchMessagingUserPreferencesException, SystemException {
        return remove(Long.valueOf(messagingPreferencesId));
    }

    /**
     * Removes the messaging user preferences with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the messaging user preferences
     * @return the messaging user preferences that was removed
     * @throws com.ext.portlet.NoSuchMessagingUserPreferencesException if a messaging user preferences with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingUserPreferences remove(Serializable primaryKey)
        throws NoSuchMessagingUserPreferencesException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MessagingUserPreferences messagingUserPreferences = (MessagingUserPreferences) session.get(MessagingUserPreferencesImpl.class,
                    primaryKey);

            if (messagingUserPreferences == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMessagingUserPreferencesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(messagingUserPreferences);
        } catch (NoSuchMessagingUserPreferencesException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MessagingUserPreferences removeImpl(
        MessagingUserPreferences messagingUserPreferences)
        throws SystemException {
        messagingUserPreferences = toUnwrappedModel(messagingUserPreferences);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, messagingUserPreferences);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(messagingUserPreferences);

        return messagingUserPreferences;
    }

    @Override
    public MessagingUserPreferences updateImpl(
        com.ext.portlet.model.MessagingUserPreferences messagingUserPreferences,
        boolean merge) throws SystemException {
        messagingUserPreferences = toUnwrappedModel(messagingUserPreferences);

        boolean isNew = messagingUserPreferences.isNew();

        MessagingUserPreferencesModelImpl messagingUserPreferencesModelImpl = (MessagingUserPreferencesModelImpl) messagingUserPreferences;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, messagingUserPreferences, merge);

            messagingUserPreferences.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !MessagingUserPreferencesModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(MessagingUserPreferencesModelImpl.ENTITY_CACHE_ENABLED,
            MessagingUserPreferencesImpl.class,
            messagingUserPreferences.getPrimaryKey(), messagingUserPreferences);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGINGPREFERENCES,
                new Object[] { Long.valueOf(
                        messagingUserPreferences.getUserId()) },
                messagingUserPreferences);
        } else {
            if ((messagingUserPreferencesModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_MESSAGINGPREFERENCES.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(messagingUserPreferencesModelImpl.getOriginalUserId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MESSAGINGPREFERENCES,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MESSAGINGPREFERENCES,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGINGPREFERENCES,
                    new Object[] {
                        Long.valueOf(messagingUserPreferences.getUserId())
                    }, messagingUserPreferences);
            }
        }

        return messagingUserPreferences;
    }

    protected MessagingUserPreferences toUnwrappedModel(
        MessagingUserPreferences messagingUserPreferences) {
        if (messagingUserPreferences instanceof MessagingUserPreferencesImpl) {
            return messagingUserPreferences;
        }

        MessagingUserPreferencesImpl messagingUserPreferencesImpl = new MessagingUserPreferencesImpl();

        messagingUserPreferencesImpl.setNew(messagingUserPreferences.isNew());
        messagingUserPreferencesImpl.setPrimaryKey(messagingUserPreferences.getPrimaryKey());

        messagingUserPreferencesImpl.setMessagingPreferencesId(messagingUserPreferences.getMessagingPreferencesId());
        messagingUserPreferencesImpl.setUserId(messagingUserPreferences.getUserId());
        messagingUserPreferencesImpl.setEmailOnSend(messagingUserPreferences.isEmailOnSend());
        messagingUserPreferencesImpl.setEmailOnReceipt(messagingUserPreferences.isEmailOnReceipt());
        messagingUserPreferencesImpl.setEmailOnActivity(messagingUserPreferences.isEmailOnActivity());

        return messagingUserPreferencesImpl;
    }

    /**
     * Returns the messaging user preferences with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the messaging user preferences
     * @return the messaging user preferences
     * @throws com.liferay.portal.NoSuchModelException if a messaging user preferences with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingUserPreferences findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the messaging user preferences with the primary key or throws a {@link com.ext.portlet.NoSuchMessagingUserPreferencesException} if it could not be found.
     *
     * @param messagingPreferencesId the primary key of the messaging user preferences
     * @return the messaging user preferences
     * @throws com.ext.portlet.NoSuchMessagingUserPreferencesException if a messaging user preferences with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessagingUserPreferences findByPrimaryKey(
        long messagingPreferencesId)
        throws NoSuchMessagingUserPreferencesException, SystemException {
        MessagingUserPreferences messagingUserPreferences = fetchByPrimaryKey(messagingPreferencesId);

        if (messagingUserPreferences == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    messagingPreferencesId);
            }

            throw new NoSuchMessagingUserPreferencesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                messagingPreferencesId);
        }

        return messagingUserPreferences;
    }

    /**
     * Returns the messaging user preferences with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the messaging user preferences
     * @return the messaging user preferences, or <code>null</code> if a messaging user preferences with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingUserPreferences fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the messaging user preferences with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param messagingPreferencesId the primary key of the messaging user preferences
     * @return the messaging user preferences, or <code>null</code> if a messaging user preferences with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessagingUserPreferences fetchByPrimaryKey(
        long messagingPreferencesId) throws SystemException {
        MessagingUserPreferences messagingUserPreferences = (MessagingUserPreferences) EntityCacheUtil.getResult(MessagingUserPreferencesModelImpl.ENTITY_CACHE_ENABLED,
                MessagingUserPreferencesImpl.class, messagingPreferencesId);

        if (messagingUserPreferences == _nullMessagingUserPreferences) {
            return null;
        }

        if (messagingUserPreferences == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                messagingUserPreferences = (MessagingUserPreferences) session.get(MessagingUserPreferencesImpl.class,
                        Long.valueOf(messagingPreferencesId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (messagingUserPreferences != null) {
                    cacheResult(messagingUserPreferences);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(MessagingUserPreferencesModelImpl.ENTITY_CACHE_ENABLED,
                        MessagingUserPreferencesImpl.class,
                        messagingPreferencesId, _nullMessagingUserPreferences);
                }

                closeSession(session);
            }
        }

        return messagingUserPreferences;
    }

    /**
     * Returns the messaging user preferences where userId = &#63; or throws a {@link com.ext.portlet.NoSuchMessagingUserPreferencesException} if it could not be found.
     *
     * @param userId the user ID
     * @return the matching messaging user preferences
     * @throws com.ext.portlet.NoSuchMessagingUserPreferencesException if a matching messaging user preferences could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessagingUserPreferences findBymessagingPreferences(long userId)
        throws NoSuchMessagingUserPreferencesException, SystemException {
        MessagingUserPreferences messagingUserPreferences = fetchBymessagingPreferences(userId);

        if (messagingUserPreferences == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("userId=");
            msg.append(userId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchMessagingUserPreferencesException(msg.toString());
        }

        return messagingUserPreferences;
    }

    /**
     * Returns the messaging user preferences where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param userId the user ID
     * @return the matching messaging user preferences, or <code>null</code> if a matching messaging user preferences could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessagingUserPreferences fetchBymessagingPreferences(long userId)
        throws SystemException {
        return fetchBymessagingPreferences(userId, true);
    }

    /**
     * Returns the messaging user preferences where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param userId the user ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching messaging user preferences, or <code>null</code> if a matching messaging user preferences could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessagingUserPreferences fetchBymessagingPreferences(long userId,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { userId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_MESSAGINGPREFERENCES,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_SELECT_MESSAGINGUSERPREFERENCES_WHERE);

            query.append(_FINDER_COLUMN_MESSAGINGPREFERENCES_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                List<MessagingUserPreferences> list = q.list();

                result = list;

                MessagingUserPreferences messagingUserPreferences = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGINGPREFERENCES,
                        finderArgs, list);
                } else {
                    messagingUserPreferences = list.get(0);

                    cacheResult(messagingUserPreferences);

                    if ((messagingUserPreferences.getUserId() != userId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MESSAGINGPREFERENCES,
                            finderArgs, messagingUserPreferences);
                    }
                }

                return messagingUserPreferences;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MESSAGINGPREFERENCES,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (MessagingUserPreferences) result;
            }
        }
    }

    /**
     * Returns all the messaging user preferenceses.
     *
     * @return the messaging user preferenceses
     * @throws SystemException if a system exception occurred
     */
    public List<MessagingUserPreferences> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the messaging user preferenceses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of messaging user preferenceses
     * @param end the upper bound of the range of messaging user preferenceses (not inclusive)
     * @return the range of messaging user preferenceses
     * @throws SystemException if a system exception occurred
     */
    public List<MessagingUserPreferences> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the messaging user preferenceses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of messaging user preferenceses
     * @param end the upper bound of the range of messaging user preferenceses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of messaging user preferenceses
     * @throws SystemException if a system exception occurred
     */
    public List<MessagingUserPreferences> findAll(int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = new Object[] { start, end, orderByComparator };

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
            finderArgs = FINDER_ARGS_EMPTY;
        } else {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
            finderArgs = new Object[] { start, end, orderByComparator };
        }

        List<MessagingUserPreferences> list = (List<MessagingUserPreferences>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MESSAGINGUSERPREFERENCES);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MESSAGINGUSERPREFERENCES;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<MessagingUserPreferences>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<MessagingUserPreferences>) QueryUtil.list(q,
                            getDialect(), start, end);
                }
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(finderPath, finderArgs);
                } else {
                    cacheResult(list);

                    FinderCacheUtil.putResult(finderPath, finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Removes the messaging user preferences where userId = &#63; from the database.
     *
     * @param userId the user ID
     * @throws SystemException if a system exception occurred
     */
    public void removeBymessagingPreferences(long userId)
        throws NoSuchMessagingUserPreferencesException, SystemException {
        MessagingUserPreferences messagingUserPreferences = findBymessagingPreferences(userId);

        remove(messagingUserPreferences);
    }

    /**
     * Removes all the messaging user preferenceses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (MessagingUserPreferences messagingUserPreferences : findAll()) {
            remove(messagingUserPreferences);
        }
    }

    /**
     * Returns the number of messaging user preferenceses where userId = &#63;.
     *
     * @param userId the user ID
     * @return the number of matching messaging user preferenceses
     * @throws SystemException if a system exception occurred
     */
    public int countBymessagingPreferences(long userId)
        throws SystemException {
        Object[] finderArgs = new Object[] { userId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MESSAGINGPREFERENCES,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MESSAGINGUSERPREFERENCES_WHERE);

            query.append(_FINDER_COLUMN_MESSAGINGPREFERENCES_USERID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MESSAGINGPREFERENCES,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of messaging user preferenceses.
     *
     * @return the number of messaging user preferenceses
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_MESSAGINGUSERPREFERENCES);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
                    FINDER_ARGS_EMPTY, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Initializes the messaging user preferences persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.MessagingUserPreferences")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MessagingUserPreferences>> listenersList = new ArrayList<ModelListener<MessagingUserPreferences>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MessagingUserPreferences>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MessagingUserPreferencesImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
