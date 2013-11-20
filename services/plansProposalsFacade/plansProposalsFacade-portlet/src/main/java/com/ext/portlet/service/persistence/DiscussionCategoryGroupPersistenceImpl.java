package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchDiscussionCategoryGroupException;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.impl.DiscussionCategoryGroupImpl;
import com.ext.portlet.model.impl.DiscussionCategoryGroupModelImpl;
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
=======
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)
import com.ext.portlet.service.persistence.DiscussionCategoryGroupPersistence;

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
import com.liferay.portal.kernel.util.SetUtil;
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
import java.util.Set;

/**
 * The persistence implementation for the discussion category group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionCategoryGroupPersistence
 * @see DiscussionCategoryGroupUtil
 * @generated
 */
public class DiscussionCategoryGroupPersistenceImpl extends BasePersistenceImpl<DiscussionCategoryGroup>
    implements DiscussionCategoryGroupPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link DiscussionCategoryGroupUtil} to access the discussion category group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = DiscussionCategoryGroupImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(DiscussionCategoryGroupModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryGroupModelImpl.FINDER_CACHE_ENABLED,
            DiscussionCategoryGroupImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(DiscussionCategoryGroupModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryGroupModelImpl.FINDER_CACHE_ENABLED,
            DiscussionCategoryGroupImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(DiscussionCategoryGroupModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_DISCUSSIONCATEGORYGROUP = "SELECT discussionCategoryGroup FROM DiscussionCategoryGroup discussionCategoryGroup";
    private static final String _SQL_COUNT_DISCUSSIONCATEGORYGROUP = "SELECT COUNT(discussionCategoryGroup) FROM DiscussionCategoryGroup discussionCategoryGroup";
    private static final String _ORDER_BY_ENTITY_ALIAS = "discussionCategoryGroup.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DiscussionCategoryGroup exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(DiscussionCategoryGroupPersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static DiscussionCategoryGroup _nullDiscussionCategoryGroup = new DiscussionCategoryGroupImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<DiscussionCategoryGroup> toCacheModel() {
                return _nullDiscussionCategoryGroupCacheModel;
            }
        };

    private static CacheModel<DiscussionCategoryGroup> _nullDiscussionCategoryGroupCacheModel =
        new CacheModel<DiscussionCategoryGroup>() {
            @Override
            public DiscussionCategoryGroup toEntityModel() {
                return _nullDiscussionCategoryGroup;
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
    public DiscussionCategoryGroupPersistenceImpl() {
        setModelClass(DiscussionCategoryGroup.class);
    }
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)

    /**
     * Caches the discussion category group in the entity cache if it is enabled.
     *
     * @param discussionCategoryGroup the discussion category group
     */
    @Override
    public void cacheResult(DiscussionCategoryGroup discussionCategoryGroup) {
        EntityCacheUtil.putResult(DiscussionCategoryGroupModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryGroupImpl.class,
            discussionCategoryGroup.getPrimaryKey(), discussionCategoryGroup);

        discussionCategoryGroup.resetOriginalValues();
    }

    /**
     * Caches the discussion category groups in the entity cache if it is enabled.
     *
     * @param discussionCategoryGroups the discussion category groups
     */
    @Override
    public void cacheResult(
        List<DiscussionCategoryGroup> discussionCategoryGroups) {
        for (DiscussionCategoryGroup discussionCategoryGroup : discussionCategoryGroups) {
            if (EntityCacheUtil.getResult(
                        DiscussionCategoryGroupModelImpl.ENTITY_CACHE_ENABLED,
                        DiscussionCategoryGroupImpl.class,
                        discussionCategoryGroup.getPrimaryKey()) == null) {
                cacheResult(discussionCategoryGroup);
            } else {
                discussionCategoryGroup.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all discussion category groups.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(DiscussionCategoryGroupImpl.class.getName());
        }

        EntityCacheUtil.clearCache(DiscussionCategoryGroupImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the discussion category group.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(DiscussionCategoryGroup discussionCategoryGroup) {
        EntityCacheUtil.removeResult(DiscussionCategoryGroupModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryGroupImpl.class,
            discussionCategoryGroup.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<DiscussionCategoryGroup> discussionCategoryGroups) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (DiscussionCategoryGroup discussionCategoryGroup : discussionCategoryGroups) {
            EntityCacheUtil.removeResult(DiscussionCategoryGroupModelImpl.ENTITY_CACHE_ENABLED,
                DiscussionCategoryGroupImpl.class,
                discussionCategoryGroup.getPrimaryKey());
        }
    }

    /**
     * Creates a new discussion category group with the primary key. Does not add the discussion category group to the database.
     *
     * @param id the primary key for the new discussion category group
     * @return the new discussion category group
     */
    @Override
    public DiscussionCategoryGroup create(long id) {
        DiscussionCategoryGroup discussionCategoryGroup = new DiscussionCategoryGroupImpl();

        discussionCategoryGroup.setNew(true);
        discussionCategoryGroup.setPrimaryKey(id);

        return discussionCategoryGroup;
    }

    /**
     * Removes the discussion category group with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the discussion category group
     * @return the discussion category group that was removed
     * @throws com.ext.portlet.NoSuchDiscussionCategoryGroupException if a discussion category group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionCategoryGroup remove(long id)
        throws NoSuchDiscussionCategoryGroupException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the discussion category group with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the discussion category group
     * @return the discussion category group that was removed
     * @throws com.ext.portlet.NoSuchDiscussionCategoryGroupException if a discussion category group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionCategoryGroup remove(Serializable primaryKey)
        throws NoSuchDiscussionCategoryGroupException, SystemException {
        Session session = null;

        try {
            session = openSession();

            DiscussionCategoryGroup discussionCategoryGroup = (DiscussionCategoryGroup) session.get(DiscussionCategoryGroupImpl.class,
                    primaryKey);

            if (discussionCategoryGroup == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchDiscussionCategoryGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(discussionCategoryGroup);
        } catch (NoSuchDiscussionCategoryGroupException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected DiscussionCategoryGroup removeImpl(
        DiscussionCategoryGroup discussionCategoryGroup)
        throws SystemException {
        discussionCategoryGroup = toUnwrappedModel(discussionCategoryGroup);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(discussionCategoryGroup)) {
                discussionCategoryGroup = (DiscussionCategoryGroup) session.get(DiscussionCategoryGroupImpl.class,
                        discussionCategoryGroup.getPrimaryKeyObj());
            }

            if (discussionCategoryGroup != null) {
                session.delete(discussionCategoryGroup);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (discussionCategoryGroup != null) {
            clearCache(discussionCategoryGroup);
        }

        return discussionCategoryGroup;
    }

    @Override
    public DiscussionCategoryGroup updateImpl(
        com.ext.portlet.model.DiscussionCategoryGroup discussionCategoryGroup)
        throws SystemException {
        discussionCategoryGroup = toUnwrappedModel(discussionCategoryGroup);

        boolean isNew = discussionCategoryGroup.isNew();

        Session session = null;

        try {
            session = openSession();

            if (discussionCategoryGroup.isNew()) {
                session.save(discussionCategoryGroup);

                discussionCategoryGroup.setNew(false);
            } else {
                session.merge(discussionCategoryGroup);
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

        EntityCacheUtil.putResult(DiscussionCategoryGroupModelImpl.ENTITY_CACHE_ENABLED,
            DiscussionCategoryGroupImpl.class,
            discussionCategoryGroup.getPrimaryKey(), discussionCategoryGroup);

        return discussionCategoryGroup;
    }

    protected DiscussionCategoryGroup toUnwrappedModel(
        DiscussionCategoryGroup discussionCategoryGroup) {
        if (discussionCategoryGroup instanceof DiscussionCategoryGroupImpl) {
            return discussionCategoryGroup;
        }

        DiscussionCategoryGroupImpl discussionCategoryGroupImpl = new DiscussionCategoryGroupImpl();

        discussionCategoryGroupImpl.setNew(discussionCategoryGroup.isNew());
        discussionCategoryGroupImpl.setPrimaryKey(discussionCategoryGroup.getPrimaryKey());

        discussionCategoryGroupImpl.setId(discussionCategoryGroup.getId());
        discussionCategoryGroupImpl.setDescription(discussionCategoryGroup.getDescription());
        discussionCategoryGroupImpl.setUrl(discussionCategoryGroup.getUrl());
        discussionCategoryGroupImpl.setCommentsThread(discussionCategoryGroup.getCommentsThread());
        discussionCategoryGroupImpl.setIsQuiet(discussionCategoryGroup.isIsQuiet());

        return discussionCategoryGroupImpl;
    }

    /**
     * Returns the discussion category group with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the discussion category group
     * @return the discussion category group
     * @throws com.ext.portlet.NoSuchDiscussionCategoryGroupException if a discussion category group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionCategoryGroup findByPrimaryKey(Serializable primaryKey)
        throws NoSuchDiscussionCategoryGroupException, SystemException {
        DiscussionCategoryGroup discussionCategoryGroup = fetchByPrimaryKey(primaryKey);

        if (discussionCategoryGroup == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchDiscussionCategoryGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return discussionCategoryGroup;
    }

    /**
     * Returns the discussion category group with the primary key or throws a {@link com.ext.portlet.NoSuchDiscussionCategoryGroupException} if it could not be found.
     *
     * @param id the primary key of the discussion category group
     * @return the discussion category group
     * @throws com.ext.portlet.NoSuchDiscussionCategoryGroupException if a discussion category group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionCategoryGroup findByPrimaryKey(long id)
        throws NoSuchDiscussionCategoryGroupException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the discussion category group with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the discussion category group
     * @return the discussion category group, or <code>null</code> if a discussion category group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionCategoryGroup fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        DiscussionCategoryGroup discussionCategoryGroup = (DiscussionCategoryGroup) EntityCacheUtil.getResult(DiscussionCategoryGroupModelImpl.ENTITY_CACHE_ENABLED,
                DiscussionCategoryGroupImpl.class, primaryKey);

        if (discussionCategoryGroup == _nullDiscussionCategoryGroup) {
            return null;
        }

        if (discussionCategoryGroup == null) {
            Session session = null;

            try {
                session = openSession();

                discussionCategoryGroup = (DiscussionCategoryGroup) session.get(DiscussionCategoryGroupImpl.class,
                        primaryKey);

                if (discussionCategoryGroup != null) {
                    cacheResult(discussionCategoryGroup);
                } else {
                    EntityCacheUtil.putResult(DiscussionCategoryGroupModelImpl.ENTITY_CACHE_ENABLED,
                        DiscussionCategoryGroupImpl.class, primaryKey,
                        _nullDiscussionCategoryGroup);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(DiscussionCategoryGroupModelImpl.ENTITY_CACHE_ENABLED,
                    DiscussionCategoryGroupImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return discussionCategoryGroup;
    }

    /**
     * Returns the discussion category group with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the discussion category group
     * @return the discussion category group, or <code>null</code> if a discussion category group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public DiscussionCategoryGroup fetchByPrimaryKey(long id)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the discussion category groups.
     *
     * @return the discussion category groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DiscussionCategoryGroup> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the discussion category groups.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionCategoryGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of discussion category groups
     * @param end the upper bound of the range of discussion category groups (not inclusive)
     * @return the range of discussion category groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DiscussionCategoryGroup> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the discussion category groups.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionCategoryGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of discussion category groups
     * @param end the upper bound of the range of discussion category groups (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of discussion category groups
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<DiscussionCategoryGroup> findAll(int start, int end,
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

        List<DiscussionCategoryGroup> list = (List<DiscussionCategoryGroup>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_DISCUSSIONCATEGORYGROUP);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_DISCUSSIONCATEGORYGROUP;

                if (pagination) {
                    sql = sql.concat(DiscussionCategoryGroupModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<DiscussionCategoryGroup>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<DiscussionCategoryGroup>(list);
                } else {
                    list = (List<DiscussionCategoryGroup>) QueryUtil.list(q,
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
     * Removes all the discussion category groups from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (DiscussionCategoryGroup discussionCategoryGroup : findAll()) {
            remove(discussionCategoryGroup);
        }
    }

    /**
     * Returns the number of discussion category groups.
     *
     * @return the number of discussion category groups
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

                Query q = session.createQuery(_SQL_COUNT_DISCUSSIONCATEGORYGROUP);

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
     * Initializes the discussion category group persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.DiscussionCategoryGroup")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<DiscussionCategoryGroup>> listenersList = new ArrayList<ModelListener<DiscussionCategoryGroup>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<DiscussionCategoryGroup>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(DiscussionCategoryGroupImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
