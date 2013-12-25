package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchAnalyticsUserEventException;
import com.ext.portlet.model.AnalyticsUserEvent;
import com.ext.portlet.model.impl.AnalyticsUserEventImpl;
import com.ext.portlet.model.impl.AnalyticsUserEventModelImpl;
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
import com.ext.portlet.service.persistence.ProposalContestPhaseAttributePersistence;
import com.ext.portlet.service.persistence.ProposalContestPhaseAttributeTypePersistence;
import com.ext.portlet.service.persistence.ProposalPersistence;
import com.ext.portlet.service.persistence.ProposalSupporterPersistence;
import com.ext.portlet.service.persistence.ProposalVersionPersistence;
import com.ext.portlet.service.persistence.ProposalVotePersistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
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
 * The persistence implementation for the analytics user event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AnalyticsUserEventPersistence
 * @see AnalyticsUserEventUtil
 * @generated
 */
public class AnalyticsUserEventPersistenceImpl extends BasePersistenceImpl<AnalyticsUserEvent>
    implements AnalyticsUserEventPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link AnalyticsUserEventUtil} to access the analytics user event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = AnalyticsUserEventImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(AnalyticsUserEventModelImpl.ENTITY_CACHE_ENABLED,
            AnalyticsUserEventModelImpl.FINDER_CACHE_ENABLED,
            AnalyticsUserEventImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(AnalyticsUserEventModelImpl.ENTITY_CACHE_ENABLED,
            AnalyticsUserEventModelImpl.FINDER_CACHE_ENABLED,
            AnalyticsUserEventImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(AnalyticsUserEventModelImpl.ENTITY_CACHE_ENABLED,
            AnalyticsUserEventModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_ANALYTICSUSEREVENT = "SELECT analyticsUserEvent FROM AnalyticsUserEvent analyticsUserEvent";
    private static final String _SQL_COUNT_ANALYTICSUSEREVENT = "SELECT COUNT(analyticsUserEvent) FROM AnalyticsUserEvent analyticsUserEvent";
    private static final String _ORDER_BY_ENTITY_ALIAS = "analyticsUserEvent.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AnalyticsUserEvent exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(AnalyticsUserEventPersistenceImpl.class);
    private static AnalyticsUserEvent _nullAnalyticsUserEvent = new AnalyticsUserEventImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<AnalyticsUserEvent> toCacheModel() {
                return _nullAnalyticsUserEventCacheModel;
            }
        };

    private static CacheModel<AnalyticsUserEvent> _nullAnalyticsUserEventCacheModel =
        new CacheModel<AnalyticsUserEvent>() {
            public AnalyticsUserEvent toEntityModel() {
                return _nullAnalyticsUserEvent;
            }
        };

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
     * Caches the analytics user event in the entity cache if it is enabled.
     *
     * @param analyticsUserEvent the analytics user event
     */
    public void cacheResult(AnalyticsUserEvent analyticsUserEvent) {
        EntityCacheUtil.putResult(AnalyticsUserEventModelImpl.ENTITY_CACHE_ENABLED,
            AnalyticsUserEventImpl.class, analyticsUserEvent.getPrimaryKey(),
            analyticsUserEvent);

        analyticsUserEvent.resetOriginalValues();
    }

    /**
     * Caches the analytics user events in the entity cache if it is enabled.
     *
     * @param analyticsUserEvents the analytics user events
     */
    public void cacheResult(List<AnalyticsUserEvent> analyticsUserEvents) {
        for (AnalyticsUserEvent analyticsUserEvent : analyticsUserEvents) {
            if (EntityCacheUtil.getResult(
                        AnalyticsUserEventModelImpl.ENTITY_CACHE_ENABLED,
                        AnalyticsUserEventImpl.class,
                        analyticsUserEvent.getPrimaryKey()) == null) {
                cacheResult(analyticsUserEvent);
            } else {
                analyticsUserEvent.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all analytics user events.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(AnalyticsUserEventImpl.class.getName());
        }

        EntityCacheUtil.clearCache(AnalyticsUserEventImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the analytics user event.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(AnalyticsUserEvent analyticsUserEvent) {
        EntityCacheUtil.removeResult(AnalyticsUserEventModelImpl.ENTITY_CACHE_ENABLED,
            AnalyticsUserEventImpl.class, analyticsUserEvent.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<AnalyticsUserEvent> analyticsUserEvents) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (AnalyticsUserEvent analyticsUserEvent : analyticsUserEvents) {
            EntityCacheUtil.removeResult(AnalyticsUserEventModelImpl.ENTITY_CACHE_ENABLED,
                AnalyticsUserEventImpl.class, analyticsUserEvent.getPrimaryKey());
        }
    }

    /**
     * Creates a new analytics user event with the primary key. Does not add the analytics user event to the database.
     *
     * @param analyticsUserEventPK the primary key for the new analytics user event
     * @return the new analytics user event
     */
    public AnalyticsUserEvent create(AnalyticsUserEventPK analyticsUserEventPK) {
        AnalyticsUserEvent analyticsUserEvent = new AnalyticsUserEventImpl();

        analyticsUserEvent.setNew(true);
        analyticsUserEvent.setPrimaryKey(analyticsUserEventPK);

        return analyticsUserEvent;
    }

    /**
     * Removes the analytics user event with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param analyticsUserEventPK the primary key of the analytics user event
     * @return the analytics user event that was removed
     * @throws com.ext.portlet.NoSuchAnalyticsUserEventException if a analytics user event with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public AnalyticsUserEvent remove(AnalyticsUserEventPK analyticsUserEventPK)
        throws NoSuchAnalyticsUserEventException, SystemException {
        return remove((Serializable) analyticsUserEventPK);
    }

    /**
     * Removes the analytics user event with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the analytics user event
     * @return the analytics user event that was removed
     * @throws com.ext.portlet.NoSuchAnalyticsUserEventException if a analytics user event with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AnalyticsUserEvent remove(Serializable primaryKey)
        throws NoSuchAnalyticsUserEventException, SystemException {
        Session session = null;

        try {
            session = openSession();

            AnalyticsUserEvent analyticsUserEvent = (AnalyticsUserEvent) session.get(AnalyticsUserEventImpl.class,
                    primaryKey);

            if (analyticsUserEvent == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchAnalyticsUserEventException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(analyticsUserEvent);
        } catch (NoSuchAnalyticsUserEventException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected AnalyticsUserEvent removeImpl(
        AnalyticsUserEvent analyticsUserEvent) throws SystemException {
        analyticsUserEvent = toUnwrappedModel(analyticsUserEvent);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, analyticsUserEvent);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(analyticsUserEvent);

        return analyticsUserEvent;
    }

    @Override
    public AnalyticsUserEvent updateImpl(
        com.ext.portlet.model.AnalyticsUserEvent analyticsUserEvent,
        boolean merge) throws SystemException {
        analyticsUserEvent = toUnwrappedModel(analyticsUserEvent);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, analyticsUserEvent, merge);

            analyticsUserEvent.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        EntityCacheUtil.putResult(AnalyticsUserEventModelImpl.ENTITY_CACHE_ENABLED,
            AnalyticsUserEventImpl.class, analyticsUserEvent.getPrimaryKey(),
            analyticsUserEvent);

        return analyticsUserEvent;
    }

    protected AnalyticsUserEvent toUnwrappedModel(
        AnalyticsUserEvent analyticsUserEvent) {
        if (analyticsUserEvent instanceof AnalyticsUserEventImpl) {
            return analyticsUserEvent;
        }

        AnalyticsUserEventImpl analyticsUserEventImpl = new AnalyticsUserEventImpl();

        analyticsUserEventImpl.setNew(analyticsUserEvent.isNew());
        analyticsUserEventImpl.setPrimaryKey(analyticsUserEvent.getPrimaryKey());

        analyticsUserEventImpl.setUserId(analyticsUserEvent.getUserId());
        analyticsUserEventImpl.setIdString(analyticsUserEvent.getIdString());
        analyticsUserEventImpl.setCategory(analyticsUserEvent.getCategory());
        analyticsUserEventImpl.setAction(analyticsUserEvent.getAction());
        analyticsUserEventImpl.setLabel(analyticsUserEvent.getLabel());
        analyticsUserEventImpl.setValue(analyticsUserEvent.getValue());
        analyticsUserEventImpl.setCreated(analyticsUserEvent.getCreated());

        return analyticsUserEventImpl;
    }

    /**
     * Returns the analytics user event with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the analytics user event
     * @return the analytics user event
     * @throws com.liferay.portal.NoSuchModelException if a analytics user event with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AnalyticsUserEvent findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey((AnalyticsUserEventPK) primaryKey);
    }

    /**
     * Returns the analytics user event with the primary key or throws a {@link com.ext.portlet.NoSuchAnalyticsUserEventException} if it could not be found.
     *
     * @param analyticsUserEventPK the primary key of the analytics user event
     * @return the analytics user event
     * @throws com.ext.portlet.NoSuchAnalyticsUserEventException if a analytics user event with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public AnalyticsUserEvent findByPrimaryKey(
        AnalyticsUserEventPK analyticsUserEventPK)
        throws NoSuchAnalyticsUserEventException, SystemException {
        AnalyticsUserEvent analyticsUserEvent = fetchByPrimaryKey(analyticsUserEventPK);

        if (analyticsUserEvent == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    analyticsUserEventPK);
            }

            throw new NoSuchAnalyticsUserEventException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                analyticsUserEventPK);
        }

        return analyticsUserEvent;
    }

    /**
     * Returns the analytics user event with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the analytics user event
     * @return the analytics user event, or <code>null</code> if a analytics user event with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public AnalyticsUserEvent fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey((AnalyticsUserEventPK) primaryKey);
    }

    /**
     * Returns the analytics user event with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param analyticsUserEventPK the primary key of the analytics user event
     * @return the analytics user event, or <code>null</code> if a analytics user event with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public AnalyticsUserEvent fetchByPrimaryKey(
        AnalyticsUserEventPK analyticsUserEventPK) throws SystemException {
        AnalyticsUserEvent analyticsUserEvent = (AnalyticsUserEvent) EntityCacheUtil.getResult(AnalyticsUserEventModelImpl.ENTITY_CACHE_ENABLED,
                AnalyticsUserEventImpl.class, analyticsUserEventPK);

        if (analyticsUserEvent == _nullAnalyticsUserEvent) {
            return null;
        }

        if (analyticsUserEvent == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                analyticsUserEvent = (AnalyticsUserEvent) session.get(AnalyticsUserEventImpl.class,
                        analyticsUserEventPK);
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (analyticsUserEvent != null) {
                    cacheResult(analyticsUserEvent);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(AnalyticsUserEventModelImpl.ENTITY_CACHE_ENABLED,
                        AnalyticsUserEventImpl.class, analyticsUserEventPK,
                        _nullAnalyticsUserEvent);
                }

                closeSession(session);
            }
        }

        return analyticsUserEvent;
    }

    /**
     * Returns all the analytics user events.
     *
     * @return the analytics user events
     * @throws SystemException if a system exception occurred
     */
    public List<AnalyticsUserEvent> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the analytics user events.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of analytics user events
     * @param end the upper bound of the range of analytics user events (not inclusive)
     * @return the range of analytics user events
     * @throws SystemException if a system exception occurred
     */
    public List<AnalyticsUserEvent> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the analytics user events.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of analytics user events
     * @param end the upper bound of the range of analytics user events (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of analytics user events
     * @throws SystemException if a system exception occurred
     */
    public List<AnalyticsUserEvent> findAll(int start, int end,
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

        List<AnalyticsUserEvent> list = (List<AnalyticsUserEvent>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_ANALYTICSUSEREVENT);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_ANALYTICSUSEREVENT;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<AnalyticsUserEvent>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<AnalyticsUserEvent>) QueryUtil.list(q,
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
     * Removes all the analytics user events from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (AnalyticsUserEvent analyticsUserEvent : findAll()) {
            remove(analyticsUserEvent);
        }
    }

    /**
     * Returns the number of analytics user events.
     *
     * @return the number of analytics user events
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_ANALYTICSUSEREVENT);

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
     * Initializes the analytics user event persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.AnalyticsUserEvent")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<AnalyticsUserEvent>> listenersList = new ArrayList<ModelListener<AnalyticsUserEvent>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<AnalyticsUserEvent>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(AnalyticsUserEventImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
