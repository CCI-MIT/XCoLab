package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchContestPhaseTypeException;
import com.ext.portlet.model.ContestPhaseType;
import com.ext.portlet.model.impl.ContestPhaseTypeImpl;
import com.ext.portlet.model.impl.ContestPhaseTypeModelImpl;
import com.ext.portlet.service.persistence.ActivitySubscriptionPersistence;
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
import com.ext.portlet.service.persistence.PlanAttributeFilterPersistence;
import com.ext.portlet.service.persistence.PlanAttributePersistence;
import com.ext.portlet.service.persistence.PlanColumnSettingsPersistence;
import com.ext.portlet.service.persistence.PlanDescriptionPersistence;
import com.ext.portlet.service.persistence.PlanFanPersistence;
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
            ContestPhaseTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContestPhaseTypeModelImpl.ENTITY_CACHE_ENABLED,
            ContestPhaseTypeModelImpl.FINDER_CACHE_ENABLED,
            ContestPhaseTypeImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
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
            public ContestPhaseType toEntityModel() {
                return _nullContestPhaseType;
            }
        };

    @BeanReference(type = ActivitySubscriptionPersistence.class)
    protected ActivitySubscriptionPersistence activitySubscriptionPersistence;
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
    @BeanReference(type = ResourcePersistence.class)
    protected ResourcePersistence resourcePersistence;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;

    /**
     * Caches the contest phase type in the entity cache if it is enabled.
     *
     * @param contestPhaseType the contest phase type
     */
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
    public ContestPhaseType remove(long id)
        throws NoSuchContestPhaseTypeException, SystemException {
        return remove(Long.valueOf(id));
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

            BatchSessionUtil.delete(session, contestPhaseType);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(contestPhaseType);

        return contestPhaseType;
    }

    @Override
    public ContestPhaseType updateImpl(
        com.ext.portlet.model.ContestPhaseType contestPhaseType, boolean merge)
        throws SystemException {
        contestPhaseType = toUnwrappedModel(contestPhaseType);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, contestPhaseType, merge);

            contestPhaseType.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

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
     * @throws com.liferay.portal.NoSuchModelException if a contest phase type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestPhaseType findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the contest phase type with the primary key or throws a {@link com.ext.portlet.NoSuchContestPhaseTypeException} if it could not be found.
     *
     * @param id the primary key of the contest phase type
     * @return the contest phase type
     * @throws com.ext.portlet.NoSuchContestPhaseTypeException if a contest phase type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ContestPhaseType findByPrimaryKey(long id)
        throws NoSuchContestPhaseTypeException, SystemException {
        ContestPhaseType contestPhaseType = fetchByPrimaryKey(id);

        if (contestPhaseType == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
            }

            throw new NoSuchContestPhaseTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                id);
        }

        return contestPhaseType;
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
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the contest phase type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the contest phase type
     * @return the contest phase type, or <code>null</code> if a contest phase type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ContestPhaseType fetchByPrimaryKey(long id)
        throws SystemException {
        ContestPhaseType contestPhaseType = (ContestPhaseType) EntityCacheUtil.getResult(ContestPhaseTypeModelImpl.ENTITY_CACHE_ENABLED,
                ContestPhaseTypeImpl.class, id);

        if (contestPhaseType == _nullContestPhaseType) {
            return null;
        }

        if (contestPhaseType == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                contestPhaseType = (ContestPhaseType) session.get(ContestPhaseTypeImpl.class,
                        Long.valueOf(id));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (contestPhaseType != null) {
                    cacheResult(contestPhaseType);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(ContestPhaseTypeModelImpl.ENTITY_CACHE_ENABLED,
                        ContestPhaseTypeImpl.class, id, _nullContestPhaseType);
                }

                closeSession(session);
            }
        }

        return contestPhaseType;
    }

    /**
     * Returns all the contest phase types.
     *
     * @return the contest phase types
     * @throws SystemException if a system exception occurred
     */
    public List<ContestPhaseType> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contest phase types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of contest phase types
     * @param end the upper bound of the range of contest phase types (not inclusive)
     * @return the range of contest phase types
     * @throws SystemException if a system exception occurred
     */
    public List<ContestPhaseType> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the contest phase types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of contest phase types
     * @param end the upper bound of the range of contest phase types (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of contest phase types
     * @throws SystemException if a system exception occurred
     */
    public List<ContestPhaseType> findAll(int start, int end,
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
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<ContestPhaseType>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ContestPhaseType>) QueryUtil.list(q,
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
     * Removes all the contest phase types from the database.
     *
     * @throws SystemException if a system exception occurred
     */
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
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_CONTESTPHASETYPE);

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
                            listenerClassName));
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
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
