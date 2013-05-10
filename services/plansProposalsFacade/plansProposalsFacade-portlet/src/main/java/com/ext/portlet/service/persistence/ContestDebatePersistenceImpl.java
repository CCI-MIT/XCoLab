package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchContestDebateException;
import com.ext.portlet.model.ContestDebate;
import com.ext.portlet.model.impl.ContestDebateImpl;
import com.ext.portlet.model.impl.ContestDebateModelImpl;
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
 * The persistence implementation for the contest debate service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestDebatePersistence
 * @see ContestDebateUtil
 * @generated
 */
public class ContestDebatePersistenceImpl extends BasePersistenceImpl<ContestDebate>
    implements ContestDebatePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ContestDebateUtil} to access the contest debate persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ContestDebateImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTPK =
        new FinderPath(ContestDebateModelImpl.ENTITY_CACHE_ENABLED,
            ContestDebateModelImpl.FINDER_CACHE_ENABLED,
            ContestDebateImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByContestPK",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTPK =
        new FinderPath(ContestDebateModelImpl.ENTITY_CACHE_ENABLED,
            ContestDebateModelImpl.FINDER_CACHE_ENABLED,
            ContestDebateImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByContestPK", new String[] { Long.class.getName() },
            ContestDebateModelImpl.CONTESTPK_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTPK = new FinderPath(ContestDebateModelImpl.ENTITY_CACHE_ENABLED,
            ContestDebateModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByContestPK",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContestDebateModelImpl.ENTITY_CACHE_ENABLED,
            ContestDebateModelImpl.FINDER_CACHE_ENABLED,
            ContestDebateImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContestDebateModelImpl.ENTITY_CACHE_ENABLED,
            ContestDebateModelImpl.FINDER_CACHE_ENABLED,
            ContestDebateImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContestDebateModelImpl.ENTITY_CACHE_ENABLED,
            ContestDebateModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CONTESTDEBATE = "SELECT contestDebate FROM ContestDebate contestDebate";
    private static final String _SQL_SELECT_CONTESTDEBATE_WHERE = "SELECT contestDebate FROM ContestDebate contestDebate WHERE ";
    private static final String _SQL_COUNT_CONTESTDEBATE = "SELECT COUNT(contestDebate) FROM ContestDebate contestDebate";
    private static final String _SQL_COUNT_CONTESTDEBATE_WHERE = "SELECT COUNT(contestDebate) FROM ContestDebate contestDebate WHERE ";
    private static final String _FINDER_COLUMN_CONTESTPK_CONTESTPK_2 = "contestDebate.ContestPK = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "contestDebate.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ContestDebate exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ContestDebate exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ContestDebatePersistenceImpl.class);
    private static ContestDebate _nullContestDebate = new ContestDebateImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ContestDebate> toCacheModel() {
                return _nullContestDebateCacheModel;
            }
        };

    private static CacheModel<ContestDebate> _nullContestDebateCacheModel = new CacheModel<ContestDebate>() {
            public ContestDebate toEntityModel() {
                return _nullContestDebate;
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
     * Caches the contest debate in the entity cache if it is enabled.
     *
     * @param contestDebate the contest debate
     */
    public void cacheResult(ContestDebate contestDebate) {
        EntityCacheUtil.putResult(ContestDebateModelImpl.ENTITY_CACHE_ENABLED,
            ContestDebateImpl.class, contestDebate.getPrimaryKey(),
            contestDebate);

        contestDebate.resetOriginalValues();
    }

    /**
     * Caches the contest debates in the entity cache if it is enabled.
     *
     * @param contestDebates the contest debates
     */
    public void cacheResult(List<ContestDebate> contestDebates) {
        for (ContestDebate contestDebate : contestDebates) {
            if (EntityCacheUtil.getResult(
                        ContestDebateModelImpl.ENTITY_CACHE_ENABLED,
                        ContestDebateImpl.class, contestDebate.getPrimaryKey()) == null) {
                cacheResult(contestDebate);
            } else {
                contestDebate.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all contest debates.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ContestDebateImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ContestDebateImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the contest debate.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ContestDebate contestDebate) {
        EntityCacheUtil.removeResult(ContestDebateModelImpl.ENTITY_CACHE_ENABLED,
            ContestDebateImpl.class, contestDebate.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ContestDebate> contestDebates) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ContestDebate contestDebate : contestDebates) {
            EntityCacheUtil.removeResult(ContestDebateModelImpl.ENTITY_CACHE_ENABLED,
                ContestDebateImpl.class, contestDebate.getPrimaryKey());
        }
    }

    /**
     * Creates a new contest debate with the primary key. Does not add the contest debate to the database.
     *
     * @param id the primary key for the new contest debate
     * @return the new contest debate
     */
    public ContestDebate create(long id) {
        ContestDebate contestDebate = new ContestDebateImpl();

        contestDebate.setNew(true);
        contestDebate.setPrimaryKey(id);

        return contestDebate;
    }

    /**
     * Removes the contest debate with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the contest debate
     * @return the contest debate that was removed
     * @throws com.ext.portlet.NoSuchContestDebateException if a contest debate with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ContestDebate remove(long id)
        throws NoSuchContestDebateException, SystemException {
        return remove(Long.valueOf(id));
    }

    /**
     * Removes the contest debate with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the contest debate
     * @return the contest debate that was removed
     * @throws com.ext.portlet.NoSuchContestDebateException if a contest debate with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestDebate remove(Serializable primaryKey)
        throws NoSuchContestDebateException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ContestDebate contestDebate = (ContestDebate) session.get(ContestDebateImpl.class,
                    primaryKey);

            if (contestDebate == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchContestDebateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(contestDebate);
        } catch (NoSuchContestDebateException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ContestDebate removeImpl(ContestDebate contestDebate)
        throws SystemException {
        contestDebate = toUnwrappedModel(contestDebate);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, contestDebate);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(contestDebate);

        return contestDebate;
    }

    @Override
    public ContestDebate updateImpl(
        com.ext.portlet.model.ContestDebate contestDebate, boolean merge)
        throws SystemException {
        contestDebate = toUnwrappedModel(contestDebate);

        boolean isNew = contestDebate.isNew();

        ContestDebateModelImpl contestDebateModelImpl = (ContestDebateModelImpl) contestDebate;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, contestDebate, merge);

            contestDebate.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ContestDebateModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((contestDebateModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTPK.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(contestDebateModelImpl.getOriginalContestPK())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTPK,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTPK,
                    args);

                args = new Object[] {
                        Long.valueOf(contestDebateModelImpl.getContestPK())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTPK,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTPK,
                    args);
            }
        }

        EntityCacheUtil.putResult(ContestDebateModelImpl.ENTITY_CACHE_ENABLED,
            ContestDebateImpl.class, contestDebate.getPrimaryKey(),
            contestDebate);

        return contestDebate;
    }

    protected ContestDebate toUnwrappedModel(ContestDebate contestDebate) {
        if (contestDebate instanceof ContestDebateImpl) {
            return contestDebate;
        }

        ContestDebateImpl contestDebateImpl = new ContestDebateImpl();

        contestDebateImpl.setNew(contestDebate.isNew());
        contestDebateImpl.setPrimaryKey(contestDebate.getPrimaryKey());

        contestDebateImpl.setId(contestDebate.getId());
        contestDebateImpl.setDebateId(contestDebate.getDebateId());
        contestDebateImpl.setContestPK(contestDebate.getContestPK());

        return contestDebateImpl;
    }

    /**
     * Returns the contest debate with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the contest debate
     * @return the contest debate
     * @throws com.liferay.portal.NoSuchModelException if a contest debate with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestDebate findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the contest debate with the primary key or throws a {@link com.ext.portlet.NoSuchContestDebateException} if it could not be found.
     *
     * @param id the primary key of the contest debate
     * @return the contest debate
     * @throws com.ext.portlet.NoSuchContestDebateException if a contest debate with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ContestDebate findByPrimaryKey(long id)
        throws NoSuchContestDebateException, SystemException {
        ContestDebate contestDebate = fetchByPrimaryKey(id);

        if (contestDebate == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
            }

            throw new NoSuchContestDebateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                id);
        }

        return contestDebate;
    }

    /**
     * Returns the contest debate with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the contest debate
     * @return the contest debate, or <code>null</code> if a contest debate with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ContestDebate fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the contest debate with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the contest debate
     * @return the contest debate, or <code>null</code> if a contest debate with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ContestDebate fetchByPrimaryKey(long id) throws SystemException {
        ContestDebate contestDebate = (ContestDebate) EntityCacheUtil.getResult(ContestDebateModelImpl.ENTITY_CACHE_ENABLED,
                ContestDebateImpl.class, id);

        if (contestDebate == _nullContestDebate) {
            return null;
        }

        if (contestDebate == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                contestDebate = (ContestDebate) session.get(ContestDebateImpl.class,
                        Long.valueOf(id));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (contestDebate != null) {
                    cacheResult(contestDebate);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(ContestDebateModelImpl.ENTITY_CACHE_ENABLED,
                        ContestDebateImpl.class, id, _nullContestDebate);
                }

                closeSession(session);
            }
        }

        return contestDebate;
    }

    /**
     * Returns all the contest debates where ContestPK = &#63;.
     *
     * @param ContestPK the contest p k
     * @return the matching contest debates
     * @throws SystemException if a system exception occurred
     */
    public List<ContestDebate> findByContestPK(long ContestPK)
        throws SystemException {
        return findByContestPK(ContestPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
            null);
    }

    /**
     * Returns a range of all the contest debates where ContestPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param ContestPK the contest p k
     * @param start the lower bound of the range of contest debates
     * @param end the upper bound of the range of contest debates (not inclusive)
     * @return the range of matching contest debates
     * @throws SystemException if a system exception occurred
     */
    public List<ContestDebate> findByContestPK(long ContestPK, int start,
        int end) throws SystemException {
        return findByContestPK(ContestPK, start, end, null);
    }

    /**
     * Returns an ordered range of all the contest debates where ContestPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param ContestPK the contest p k
     * @param start the lower bound of the range of contest debates
     * @param end the upper bound of the range of contest debates (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contest debates
     * @throws SystemException if a system exception occurred
     */
    public List<ContestDebate> findByContestPK(long ContestPK, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CONTESTPK;
            finderArgs = new Object[] { ContestPK };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CONTESTPK;
            finderArgs = new Object[] { ContestPK, start, end, orderByComparator };
        }

        List<ContestDebate> list = (List<ContestDebate>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_CONTESTDEBATE_WHERE);

            query.append(_FINDER_COLUMN_CONTESTPK_CONTESTPK_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ContestPK);

                list = (List<ContestDebate>) QueryUtil.list(q, getDialect(),
                        start, end);
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
     * Returns the first contest debate in the ordered set where ContestPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param ContestPK the contest p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest debate
     * @throws com.ext.portlet.NoSuchContestDebateException if a matching contest debate could not be found
     * @throws SystemException if a system exception occurred
     */
    public ContestDebate findByContestPK_First(long ContestPK,
        OrderByComparator orderByComparator)
        throws NoSuchContestDebateException, SystemException {
        List<ContestDebate> list = findByContestPK(ContestPK, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("ContestPK=");
            msg.append(ContestPK);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestDebateException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last contest debate in the ordered set where ContestPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param ContestPK the contest p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest debate
     * @throws com.ext.portlet.NoSuchContestDebateException if a matching contest debate could not be found
     * @throws SystemException if a system exception occurred
     */
    public ContestDebate findByContestPK_Last(long ContestPK,
        OrderByComparator orderByComparator)
        throws NoSuchContestDebateException, SystemException {
        int count = countByContestPK(ContestPK);

        List<ContestDebate> list = findByContestPK(ContestPK, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("ContestPK=");
            msg.append(ContestPK);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestDebateException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the contest debates before and after the current contest debate in the ordered set where ContestPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param id the primary key of the current contest debate
     * @param ContestPK the contest p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest debate
     * @throws com.ext.portlet.NoSuchContestDebateException if a contest debate with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ContestDebate[] findByContestPK_PrevAndNext(long id, long ContestPK,
        OrderByComparator orderByComparator)
        throws NoSuchContestDebateException, SystemException {
        ContestDebate contestDebate = findByPrimaryKey(id);

        Session session = null;

        try {
            session = openSession();

            ContestDebate[] array = new ContestDebateImpl[3];

            array[0] = getByContestPK_PrevAndNext(session, contestDebate,
                    ContestPK, orderByComparator, true);

            array[1] = contestDebate;

            array[2] = getByContestPK_PrevAndNext(session, contestDebate,
                    ContestPK, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ContestDebate getByContestPK_PrevAndNext(Session session,
        ContestDebate contestDebate, long ContestPK,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTESTDEBATE_WHERE);

        query.append(_FINDER_COLUMN_CONTESTPK_CONTESTPK_2);

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
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(ContestPK);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contestDebate);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ContestDebate> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the contest debates.
     *
     * @return the contest debates
     * @throws SystemException if a system exception occurred
     */
    public List<ContestDebate> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contest debates.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of contest debates
     * @param end the upper bound of the range of contest debates (not inclusive)
     * @return the range of contest debates
     * @throws SystemException if a system exception occurred
     */
    public List<ContestDebate> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the contest debates.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of contest debates
     * @param end the upper bound of the range of contest debates (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of contest debates
     * @throws SystemException if a system exception occurred
     */
    public List<ContestDebate> findAll(int start, int end,
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

        List<ContestDebate> list = (List<ContestDebate>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CONTESTDEBATE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CONTESTDEBATE;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<ContestDebate>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ContestDebate>) QueryUtil.list(q,
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
     * Removes all the contest debates where ContestPK = &#63; from the database.
     *
     * @param ContestPK the contest p k
     * @throws SystemException if a system exception occurred
     */
    public void removeByContestPK(long ContestPK) throws SystemException {
        for (ContestDebate contestDebate : findByContestPK(ContestPK)) {
            remove(contestDebate);
        }
    }

    /**
     * Removes all the contest debates from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (ContestDebate contestDebate : findAll()) {
            remove(contestDebate);
        }
    }

    /**
     * Returns the number of contest debates where ContestPK = &#63;.
     *
     * @param ContestPK the contest p k
     * @return the number of matching contest debates
     * @throws SystemException if a system exception occurred
     */
    public int countByContestPK(long ContestPK) throws SystemException {
        Object[] finderArgs = new Object[] { ContestPK };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CONTESTPK,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_CONTESTDEBATE_WHERE);

            query.append(_FINDER_COLUMN_CONTESTPK_CONTESTPK_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(ContestPK);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTESTPK,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of contest debates.
     *
     * @return the number of contest debates
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_CONTESTDEBATE);

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
     * Initializes the contest debate persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ContestDebate")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ContestDebate>> listenersList = new ArrayList<ModelListener<ContestDebate>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ContestDebate>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ContestDebateImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
