package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchContestPhaseTypeException;
import com.ext.portlet.model.ContestPhaseType;
import com.ext.portlet.model.impl.ContestPhaseTypeImpl;
import com.ext.portlet.model.impl.ContestPhaseTypeModelImpl;
<<<<<<< HEAD
import com.ext.portlet.service.persistence.ActivitySubscriptionPersistence;
import com.ext.portlet.service.persistence.AnalyticsUserEventPersistence;
import com.ext.portlet.service.persistence.BalloonStatsEntryPersistence;
import com.ext.portlet.service.persistence.ContestDebatePersistence;
import com.ext.portlet.service.persistence.ContestPersistence;
import com.ext.portlet.service.persistence.ContestPhaseColumnPersistence;
import com.ext.portlet.service.persistence.ContestPhasePersistence;
import com.ext.portlet.service.persistence.ContestPhaseRibbonTypePersistence;
=======
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)
import com.ext.portlet.service.persistence.ContestPhaseTypePersistence;

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
 * The persistence implementation for the contest phase type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseTypePersistence
 * @see ContestPhaseTypeUtil
 * @generated
 */
public class ContestPhaseTypePersistenceImpl extends BasePersistenceImpl<ContestPhaseType>
    implements ContestPhaseTypePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ContestPhaseTypeUtil} to access the contest phase type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ContestPhaseTypeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContestPhaseTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseTypeModelImpl.FINDER_CACHE_ENABLED,
            ContestPhaseTypeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContestPhaseTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseTypeModelImpl.FINDER_CACHE_ENABLED,
            ContestPhaseTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContestPhaseTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CONTESTPHASETYPE = "SELECT contestPhaseType FROM ContestPhaseType contestPhaseType";
    private static final String _SQL_COUNT_CONTESTPHASETYPE = "SELECT COUNT(contestPhaseType) FROM ContestPhaseType contestPhaseType";
    private static final String _ORDER_BY_ENTITY_ALIAS = "contestPhaseType.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContestPhaseType exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ContestPhaseTypePersistenceImpl.class);
    private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
                "id"
            });
    private static ContestPhaseType _nullContestPhaseType = new ContestPhaseTypeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ContestPhaseType> toCacheModel() {
                return _nullContestPhaseTypeCacheModel;
            }
        };

    private static CacheModel<ContestPhaseType> _nullContestPhaseTypeCacheModel = new CacheModel<ContestPhaseType>() {
            @Override
            public ContestPhaseType toEntityModel() {
                return _nullContestPhaseType;
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
    public ContestPhaseTypePersistenceImpl() {
        setModelClass(ContestPhaseType.class);
    }
>>>>>>> First steps toward lr6.2 (proposals/plansProposalFacade deploy and seem to work)

    /**
     * Caches the contest phase type in the entity cache if it is enabled.
     *
     * @param contestPhaseType the contest phase type
     */
    @Override
    public void cacheResult(ContestPhaseType contestPhaseType) {
        EntityCacheUtil.putResult(ContestPhaseTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseTypeImpl.class, contestPhaseType.getPrimaryKey(),
            contestPhaseType);

        contestPhaseType.resetOriginalValues();
    }

    /**
     * Caches the contest phase types in the entity cache if it is enabled.
     *
     * @param contestPhaseTypes the contest phase types
     */
    @Override
    public void cacheResult(List<ContestPhaseType> contestPhaseTypes) {
        for (ContestPhaseType contestPhaseType : contestPhaseTypes) {
            if (EntityCacheUtil.getResult(
                        ContestPhaseTypeModelImpl.ENTITY_CACHE_ENABLED,
                        ContestPhaseTypeImpl.class,
                        contestPhaseType.getPrimaryKey()) == null) {
                cacheResult(contestPhaseType);
            } else {
                contestPhaseType.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all contest phase types.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ContestPhaseTypeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ContestPhaseTypeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the contest phase type.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ContestPhaseType contestPhaseType) {
        EntityCacheUtil.removeResult(ContestPhaseTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseTypeImpl.class, contestPhaseType.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ContestPhaseType> contestPhaseTypes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ContestPhaseType contestPhaseType : contestPhaseTypes) {
            EntityCacheUtil.removeResult(ContestPhaseTypeModelImpl.ENTITY_CACHE_ENABLED,
                ContestPhaseTypeImpl.class, contestPhaseType.getPrimaryKey());
        }
    }

    /**
     * Creates a new contest phase type with the primary key. Does not add the contest phase type to the database.
     *
     * @param id the primary key for the new contest phase type
     * @return the new contest phase type
     */
    @Override
    public ContestPhaseType create(long id) {
        ContestPhaseType contestPhaseType = new ContestPhaseTypeImpl();

        contestPhaseType.setNew(true);
        contestPhaseType.setPrimaryKey(id);

        return contestPhaseType;
    }

    /**
     * Removes the contest phase type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the contest phase type
     * @return the contest phase type that was removed
     * @throws com.ext.portlet.NoSuchContestPhaseTypeException if a contest phase type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhaseType remove(long id)
        throws NoSuchContestPhaseTypeException, SystemException {
        return remove((Serializable) id);
    }

    /**
     * Removes the contest phase type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the contest phase type
     * @return the contest phase type that was removed
     * @throws com.ext.portlet.NoSuchContestPhaseTypeException if a contest phase type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhaseType remove(Serializable primaryKey)
        throws NoSuchContestPhaseTypeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ContestPhaseType contestPhaseType = (ContestPhaseType) session.get(ContestPhaseTypeImpl.class,
                    primaryKey);

            if (contestPhaseType == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchContestPhaseTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(contestPhaseType);
        } catch (NoSuchContestPhaseTypeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ContestPhaseType removeImpl(ContestPhaseType contestPhaseType)
        throws SystemException {
        contestPhaseType = toUnwrappedModel(contestPhaseType);

        Session session = null;

        try {
            session = openSession();

            if (!session.contains(contestPhaseType)) {
                contestPhaseType = (ContestPhaseType) session.get(ContestPhaseTypeImpl.class,
                        contestPhaseType.getPrimaryKeyObj());
            }

            if (contestPhaseType != null) {
                session.delete(contestPhaseType);
            }
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        if (contestPhaseType != null) {
            clearCache(contestPhaseType);
        }

        return contestPhaseType;
    }

    @Override
    public ContestPhaseType updateImpl(
        com.ext.portlet.model.ContestPhaseType contestPhaseType)
        throws SystemException {
        contestPhaseType = toUnwrappedModel(contestPhaseType);

        boolean isNew = contestPhaseType.isNew();

        Session session = null;

        try {
            session = openSession();

            if (contestPhaseType.isNew()) {
                session.save(contestPhaseType);

                contestPhaseType.setNew(false);
            } else {
                session.merge(contestPhaseType);
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

        EntityCacheUtil.putResult(ContestPhaseTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseTypeImpl.class, contestPhaseType.getPrimaryKey(),
            contestPhaseType);

        return contestPhaseType;
    }

    protected ContestPhaseType toUnwrappedModel(
        ContestPhaseType contestPhaseType) {
        if (contestPhaseType instanceof ContestPhaseTypeImpl) {
            return contestPhaseType;
        }

        ContestPhaseTypeImpl contestPhaseTypeImpl = new ContestPhaseTypeImpl();

        contestPhaseTypeImpl.setNew(contestPhaseType.isNew());
        contestPhaseTypeImpl.setPrimaryKey(contestPhaseType.getPrimaryKey());

        contestPhaseTypeImpl.setId(contestPhaseType.getId());
        contestPhaseTypeImpl.setName(contestPhaseType.getName());
        contestPhaseTypeImpl.setDescription(contestPhaseType.getDescription());
        contestPhaseTypeImpl.setStatus(contestPhaseType.getStatus());

        return contestPhaseTypeImpl;
    }

    /**
     * Returns the contest phase type with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the contest phase type
     * @return the contest phase type
     * @throws com.ext.portlet.NoSuchContestPhaseTypeException if a contest phase type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhaseType findByPrimaryKey(Serializable primaryKey)
        throws NoSuchContestPhaseTypeException, SystemException {
        ContestPhaseType contestPhaseType = fetchByPrimaryKey(primaryKey);

        if (contestPhaseType == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
            }

            throw new NoSuchContestPhaseTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                primaryKey);
        }

        return contestPhaseType;
    }

    /**
     * Returns the contest phase type with the primary key or throws a {@link com.ext.portlet.NoSuchContestPhaseTypeException} if it could not be found.
     *
     * @param id the primary key of the contest phase type
     * @return the contest phase type
     * @throws com.ext.portlet.NoSuchContestPhaseTypeException if a contest phase type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhaseType findByPrimaryKey(long id)
        throws NoSuchContestPhaseTypeException, SystemException {
        return findByPrimaryKey((Serializable) id);
    }

    /**
     * Returns the contest phase type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the contest phase type
     * @return the contest phase type, or <code>null</code> if a contest phase type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhaseType fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        ContestPhaseType contestPhaseType = (ContestPhaseType) EntityCacheUtil.getResult(ContestPhaseTypeModelImpl.ENTITY_CACHE_ENABLED,
                ContestPhaseTypeImpl.class, primaryKey);

        if (contestPhaseType == _nullContestPhaseType) {
            return null;
        }

        if (contestPhaseType == null) {
            Session session = null;

            try {
                session = openSession();

                contestPhaseType = (ContestPhaseType) session.get(ContestPhaseTypeImpl.class,
                        primaryKey);

                if (contestPhaseType != null) {
                    cacheResult(contestPhaseType);
                } else {
                    EntityCacheUtil.putResult(ContestPhaseTypeModelImpl.ENTITY_CACHE_ENABLED,
                        ContestPhaseTypeImpl.class, primaryKey,
                        _nullContestPhaseType);
                }
            } catch (Exception e) {
                EntityCacheUtil.removeResult(ContestPhaseTypeModelImpl.ENTITY_CACHE_ENABLED,
                    ContestPhaseTypeImpl.class, primaryKey);

                throw processException(e);
            } finally {
                closeSession(session);
            }
        }

        return contestPhaseType;
    }

    /**
     * Returns the contest phase type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the contest phase type
     * @return the contest phase type, or <code>null</code> if a contest phase type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhaseType fetchByPrimaryKey(long id)
        throws SystemException {
        return fetchByPrimaryKey((Serializable) id);
    }

    /**
     * Returns all the contest phase types.
     *
     * @return the contest phase types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhaseType> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contest phase types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contest phase types
     * @param end the upper bound of the range of contest phase types (not inclusive)
     * @return the range of contest phase types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhaseType> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the contest phase types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
     * </p>
     *
     * @param start the lower bound of the range of contest phase types
     * @param end the upper bound of the range of contest phase types (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of contest phase types
     * @throws SystemException if a system exception occurred
     */
    @Override
    public List<ContestPhaseType> findAll(int start, int end,
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

        List<ContestPhaseType> list = (List<ContestPhaseType>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CONTESTPHASETYPE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CONTESTPHASETYPE;

                if (pagination) {
                    sql = sql.concat(ContestPhaseTypeModelImpl.ORDER_BY_JPQL);
                }
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (!pagination) {
                    list = (List<ContestPhaseType>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);

                    list = new UnmodifiableList<ContestPhaseType>(list);
                } else {
                    list = (List<ContestPhaseType>) QueryUtil.list(q,
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
     * Removes all the contest phase types from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    @Override
    public void removeAll() throws SystemException {
        for (ContestPhaseType contestPhaseType : findAll()) {
            remove(contestPhaseType);
        }
    }

    /**
     * Returns the number of contest phase types.
     *
     * @return the number of contest phase types
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

                Query q = session.createQuery(_SQL_COUNT_CONTESTPHASETYPE);

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
     * Initializes the contest phase type persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ContestPhaseType")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ContestPhaseType>> listenersList = new ArrayList<ModelListener<ContestPhaseType>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ContestPhaseType>) InstanceFactory.newInstance(
                            getClassLoader(), listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ContestPhaseTypeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
