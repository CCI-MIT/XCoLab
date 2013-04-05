package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchContestException;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.impl.ContestImpl;
import com.ext.portlet.model.impl.ContestModelImpl;
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
 * The persistence implementation for the contest service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPersistence
 * @see ContestUtil
 * @generated
 */
public class ContestPersistenceImpl extends BasePersistenceImpl<Contest>
    implements ContestPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ContestUtil} to access the contest persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ContestImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByType",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByType",
            new String[] { Long.class.getName() },
            ContestModelImpl.PLANTYPEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_TYPE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByType",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_CONTESTACTIVE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchBycontestActive",
            new String[] { Boolean.class.getName() },
            ContestModelImpl.CONTESTACTIVE_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_CONTESTACTIVE = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBycontestActive",
            new String[] { Boolean.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFEATURED =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActiveFeatured",
            new String[] {
                Boolean.class.getName(), Boolean.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATURED =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActiveFeatured",
            new String[] { Boolean.class.getName(), Boolean.class.getName() },
            ContestModelImpl.CONTESTACTIVE_COLUMN_BITMASK |
            ContestModelImpl.FEATURED_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVEFEATURED = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActiveFeatured",
            new String[] { Boolean.class.getName(), Boolean.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFLAG =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActiveFlag",
            new String[] {
                Boolean.class.getName(), Integer.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAG =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActiveFlag",
            new String[] { Boolean.class.getName(), Integer.class.getName() },
            ContestModelImpl.CONTESTACTIVE_COLUMN_BITMASK |
            ContestModelImpl.FLAG_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVEFLAG = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActiveFlag",
            new String[] { Boolean.class.getName(), Integer.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFLAGTEXT =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByActiveFlagText",
            new String[] {
                Boolean.class.getName(), String.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGTEXT =
        new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByActiveFlagText",
            new String[] { Boolean.class.getName(), String.class.getName() },
            ContestModelImpl.CONTESTACTIVE_COLUMN_BITMASK |
            ContestModelImpl.FLAGTEXT_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ACTIVEFLAGTEXT = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByActiveFlagText",
            new String[] { Boolean.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, ContestImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_CONTEST = "SELECT contest FROM Contest contest";
    private static final String _SQL_SELECT_CONTEST_WHERE = "SELECT contest FROM Contest contest WHERE ";
    private static final String _SQL_COUNT_CONTEST = "SELECT COUNT(contest) FROM Contest contest";
    private static final String _SQL_COUNT_CONTEST_WHERE = "SELECT COUNT(contest) FROM Contest contest WHERE ";
    private static final String _FINDER_COLUMN_TYPE_PLANTYPEID_2 = "contest.PlanTypeId = ?";
    private static final String _FINDER_COLUMN_CONTESTACTIVE_CONTESTACTIVE_2 = "contest.contestActive = ?";
    private static final String _FINDER_COLUMN_ACTIVEFEATURED_CONTESTACTIVE_2 = "contest.contestActive = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFEATURED_FEATURED_2 = "contest.featured = ?";
    private static final String _FINDER_COLUMN_ACTIVEFLAG_CONTESTACTIVE_2 = "contest.contestActive = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFLAG_FLAG_2 = "contest.flag = ?";
    private static final String _FINDER_COLUMN_ACTIVEFLAGTEXT_CONTESTACTIVE_2 = "contest.contestActive = ? AND ";
    private static final String _FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_1 = "contest.flagText IS NULL";
    private static final String _FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_2 = "contest.flagText = ?";
    private static final String _FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_3 = "(contest.flagText IS NULL OR contest.flagText = ?)";
    private static final String _ORDER_BY_ENTITY_ALIAS = "contest.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Contest exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Contest exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ContestPersistenceImpl.class);
    private static Contest _nullContest = new ContestImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<Contest> toCacheModel() {
                return _nullContestCacheModel;
            }
        };

    private static CacheModel<Contest> _nullContestCacheModel = new CacheModel<Contest>() {
            public Contest toEntityModel() {
                return _nullContest;
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
     * Caches the contest in the entity cache if it is enabled.
     *
     * @param contest the contest
     */
    public void cacheResult(Contest contest) {
        EntityCacheUtil.putResult(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestImpl.class, contest.getPrimaryKey(), contest);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTACTIVE,
            new Object[] { Boolean.valueOf(contest.getContestActive()) },
            contest);

        contest.resetOriginalValues();
    }

    /**
     * Caches the contests in the entity cache if it is enabled.
     *
     * @param contests the contests
     */
    public void cacheResult(List<Contest> contests) {
        for (Contest contest : contests) {
            if (EntityCacheUtil.getResult(
                        ContestModelImpl.ENTITY_CACHE_ENABLED,
                        ContestImpl.class, contest.getPrimaryKey()) == null) {
                cacheResult(contest);
            } else {
                contest.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all contests.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ContestImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ContestImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the contest.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(Contest contest) {
        EntityCacheUtil.removeResult(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestImpl.class, contest.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(contest);
    }

    @Override
    public void clearCache(List<Contest> contests) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (Contest contest : contests) {
            EntityCacheUtil.removeResult(ContestModelImpl.ENTITY_CACHE_ENABLED,
                ContestImpl.class, contest.getPrimaryKey());

            clearUniqueFindersCache(contest);
        }
    }

    protected void clearUniqueFindersCache(Contest contest) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTESTACTIVE,
            new Object[] { Boolean.valueOf(contest.getContestActive()) });
    }

    /**
     * Creates a new contest with the primary key. Does not add the contest to the database.
     *
     * @param ContestPK the primary key for the new contest
     * @return the new contest
     */
    public Contest create(long ContestPK) {
        Contest contest = new ContestImpl();

        contest.setNew(true);
        contest.setPrimaryKey(ContestPK);

        return contest;
    }

    /**
     * Removes the contest with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param ContestPK the primary key of the contest
     * @return the contest that was removed
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contest remove(long ContestPK)
        throws NoSuchContestException, SystemException {
        return remove(Long.valueOf(ContestPK));
    }

    /**
     * Removes the contest with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the contest
     * @return the contest that was removed
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest remove(Serializable primaryKey)
        throws NoSuchContestException, SystemException {
        Session session = null;

        try {
            session = openSession();

            Contest contest = (Contest) session.get(ContestImpl.class,
                    primaryKey);

            if (contest == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchContestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(contest);
        } catch (NoSuchContestException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected Contest removeImpl(Contest contest) throws SystemException {
        contest = toUnwrappedModel(contest);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, contest);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(contest);

        return contest;
    }

    @Override
    public Contest updateImpl(com.ext.portlet.model.Contest contest,
        boolean merge) throws SystemException {
        contest = toUnwrappedModel(contest);

        boolean isNew = contest.isNew();

        ContestModelImpl contestModelImpl = (ContestModelImpl) contest;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, contest, merge);

            contest.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ContestModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(contestModelImpl.getOriginalPlanTypeId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
                    args);

                args = new Object[] {
                        Long.valueOf(contestModelImpl.getPlanTypeId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
                    args);
            }

            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATURED.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Boolean.valueOf(contestModelImpl.getOriginalContestActive()),
                        Boolean.valueOf(contestModelImpl.getOriginalFeatured())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFEATURED,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATURED,
                    args);

                args = new Object[] {
                        Boolean.valueOf(contestModelImpl.getContestActive()),
                        Boolean.valueOf(contestModelImpl.getFeatured())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFEATURED,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATURED,
                    args);
            }

            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAG.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Boolean.valueOf(contestModelImpl.getOriginalContestActive()),
                        Integer.valueOf(contestModelImpl.getOriginalFlag())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFLAG,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAG,
                    args);

                args = new Object[] {
                        Boolean.valueOf(contestModelImpl.getContestActive()),
                        Integer.valueOf(contestModelImpl.getFlag())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFLAG,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAG,
                    args);
            }

            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGTEXT.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Boolean.valueOf(contestModelImpl.getOriginalContestActive()),
                        
                        contestModelImpl.getOriginalFlagText()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFLAGTEXT,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGTEXT,
                    args);

                args = new Object[] {
                        Boolean.valueOf(contestModelImpl.getContestActive()),
                        
                        contestModelImpl.getFlagText()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ACTIVEFLAGTEXT,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGTEXT,
                    args);
            }
        }

        EntityCacheUtil.putResult(ContestModelImpl.ENTITY_CACHE_ENABLED,
            ContestImpl.class, contest.getPrimaryKey(), contest);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTACTIVE,
                new Object[] { Boolean.valueOf(contest.getContestActive()) },
                contest);
        } else {
            if ((contestModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_CONTESTACTIVE.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Boolean.valueOf(contestModelImpl.getOriginalContestActive())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CONTESTACTIVE,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTESTACTIVE,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTACTIVE,
                    new Object[] { Boolean.valueOf(contest.getContestActive()) },
                    contest);
            }
        }

        return contest;
    }

    protected Contest toUnwrappedModel(Contest contest) {
        if (contest instanceof ContestImpl) {
            return contest;
        }

        ContestImpl contestImpl = new ContestImpl();

        contestImpl.setNew(contest.isNew());
        contestImpl.setPrimaryKey(contest.getPrimaryKey());

        contestImpl.setContestPK(contest.getContestPK());
        contestImpl.setContestName(contest.getContestName());
        contestImpl.setContestShortName(contest.getContestShortName());
        contestImpl.setContestDescription(contest.getContestDescription());
        contestImpl.setContestModelDescription(contest.getContestModelDescription());
        contestImpl.setContestPositionsDescription(contest.getContestPositionsDescription());
        contestImpl.setDefaultPlanDescription(contest.getDefaultPlanDescription());
        contestImpl.setPlanTypeId(contest.getPlanTypeId());
        contestImpl.setCreated(contest.getCreated());
        contestImpl.setUpdated(contest.getUpdated());
        contestImpl.setAuthorId(contest.getAuthorId());
        contestImpl.setContestActive(contest.isContestActive());
        contestImpl.setPlanTemplateId(contest.getPlanTemplateId());
        contestImpl.setFocusAreaId(contest.getFocusAreaId());
        contestImpl.setContestLogoId(contest.getContestLogoId());
        contestImpl.setFeatured(contest.isFeatured());
        contestImpl.setPlansOpenByDefault(contest.isPlansOpenByDefault());
        contestImpl.setSponsorLogoId(contest.getSponsorLogoId());
        contestImpl.setSponsorText(contest.getSponsorText());
        contestImpl.setFlag(contest.getFlag());
        contestImpl.setFlagText(contest.getFlagText());
        contestImpl.setFlagTooltip(contest.getFlagTooltip());
        contestImpl.setGroupId(contest.getGroupId());
        contestImpl.setDiscussionGroupId(contest.getDiscussionGroupId());
        contestImpl.setWeight(contest.getWeight());
        contestImpl.setResourcesUrl(contest.getResourcesUrl());

        return contestImpl;
    }

    /**
     * Returns the contest with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the contest
     * @return the contest
     * @throws com.liferay.portal.NoSuchModelException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the contest with the primary key or throws a {@link com.ext.portlet.NoSuchContestException} if it could not be found.
     *
     * @param ContestPK the primary key of the contest
     * @return the contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contest findByPrimaryKey(long ContestPK)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchByPrimaryKey(ContestPK);

        if (contest == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + ContestPK);
            }

            throw new NoSuchContestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                ContestPK);
        }

        return contest;
    }

    /**
     * Returns the contest with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the contest
     * @return the contest, or <code>null</code> if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public Contest fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the contest with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param ContestPK the primary key of the contest
     * @return the contest, or <code>null</code> if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contest fetchByPrimaryKey(long ContestPK) throws SystemException {
        Contest contest = (Contest) EntityCacheUtil.getResult(ContestModelImpl.ENTITY_CACHE_ENABLED,
                ContestImpl.class, ContestPK);

        if (contest == _nullContest) {
            return null;
        }

        if (contest == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                contest = (Contest) session.get(ContestImpl.class,
                        Long.valueOf(ContestPK));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (contest != null) {
                    cacheResult(contest);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(ContestModelImpl.ENTITY_CACHE_ENABLED,
                        ContestImpl.class, ContestPK, _nullContest);
                }

                closeSession(session);
            }
        }

        return contest;
    }

    /**
     * Returns all the contests where PlanTypeId = &#63;.
     *
     * @param PlanTypeId the plan type ID
     * @return the matching contests
     * @throws SystemException if a system exception occurred
     */
    public List<Contest> findByType(long PlanTypeId) throws SystemException {
        return findByType(PlanTypeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests where PlanTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param PlanTypeId the plan type ID
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of matching contests
     * @throws SystemException if a system exception occurred
     */
    public List<Contest> findByType(long PlanTypeId, int start, int end)
        throws SystemException {
        return findByType(PlanTypeId, start, end, null);
    }

    /**
     * Returns an ordered range of all the contests where PlanTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param PlanTypeId the plan type ID
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contests
     * @throws SystemException if a system exception occurred
     */
    public List<Contest> findByType(long PlanTypeId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE;
            finderArgs = new Object[] { PlanTypeId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE;
            finderArgs = new Object[] { PlanTypeId, start, end, orderByComparator };
        }

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_TYPE_PLANTYPEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(ContestModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(PlanTypeId);

                list = (List<Contest>) QueryUtil.list(q, getDialect(), start,
                        end);
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
     * Returns the first contest in the ordered set where PlanTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param PlanTypeId the plan type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contest findByType_First(long PlanTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        List<Contest> list = findByType(PlanTypeId, 0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("PlanTypeId=");
            msg.append(PlanTypeId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last contest in the ordered set where PlanTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param PlanTypeId the plan type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contest findByType_Last(long PlanTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        int count = countByType(PlanTypeId);

        List<Contest> list = findByType(PlanTypeId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("PlanTypeId=");
            msg.append(PlanTypeId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the contests before and after the current contest in the ordered set where PlanTypeId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param ContestPK the primary key of the current contest
     * @param PlanTypeId the plan type ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contest[] findByType_PrevAndNext(long ContestPK, long PlanTypeId,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByType_PrevAndNext(session, contest, PlanTypeId,
                    orderByComparator, true);

            array[1] = contest;

            array[2] = getByType_PrevAndNext(session, contest, PlanTypeId,
                    orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByType_PrevAndNext(Session session, Contest contest,
        long PlanTypeId, OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_TYPE_PLANTYPEID_2);

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
        else {
            query.append(ContestModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(PlanTypeId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contest);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Contest> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns the contest where contestActive = &#63; or throws a {@link com.ext.portlet.NoSuchContestException} if it could not be found.
     *
     * @param contestActive the contest active
     * @return the matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contest findBycontestActive(boolean contestActive)
        throws NoSuchContestException, SystemException {
        Contest contest = fetchBycontestActive(contestActive);

        if (contest == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("contestActive=");
            msg.append(contestActive);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchContestException(msg.toString());
        }

        return contest;
    }

    /**
     * Returns the contest where contestActive = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param contestActive the contest active
     * @return the matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contest fetchBycontestActive(boolean contestActive)
        throws SystemException {
        return fetchBycontestActive(contestActive, true);
    }

    /**
     * Returns the contest where contestActive = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param contestActive the contest active
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching contest, or <code>null</code> if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contest fetchBycontestActive(boolean contestActive,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { contestActive };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_CONTESTACTIVE,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_CONTESTACTIVE_CONTESTACTIVE_2);

            query.append(ContestModelImpl.ORDER_BY_JPQL);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                List<Contest> list = q.list();

                result = list;

                Contest contest = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTACTIVE,
                        finderArgs, list);
                } else {
                    contest = list.get(0);

                    cacheResult(contest);

                    if ((contest.getContestActive() != contestActive)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_CONTESTACTIVE,
                            finderArgs, contest);
                    }
                }

                return contest;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_CONTESTACTIVE,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (Contest) result;
            }
        }
    }

    /**
     * Returns all the contests where contestActive = &#63; and featured = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @return the matching contests
     * @throws SystemException if a system exception occurred
     */
    public List<Contest> findByActiveFeatured(boolean contestActive,
        boolean featured) throws SystemException {
        return findByActiveFeatured(contestActive, featured, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests where contestActive = &#63; and featured = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of matching contests
     * @throws SystemException if a system exception occurred
     */
    public List<Contest> findByActiveFeatured(boolean contestActive,
        boolean featured, int start, int end) throws SystemException {
        return findByActiveFeatured(contestActive, featured, start, end, null);
    }

    /**
     * Returns an ordered range of all the contests where contestActive = &#63; and featured = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contests
     * @throws SystemException if a system exception occurred
     */
    public List<Contest> findByActiveFeatured(boolean contestActive,
        boolean featured, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFEATURED;
            finderArgs = new Object[] { contestActive, featured };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFEATURED;
            finderArgs = new Object[] {
                    contestActive, featured,
                    
                    start, end, orderByComparator
                };
        }

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFEATURED_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFEATURED_FEATURED_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(ContestModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                qPos.add(featured);

                list = (List<Contest>) QueryUtil.list(q, getDialect(), start,
                        end);
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
     * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contest findByActiveFeatured_First(boolean contestActive,
        boolean featured, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        List<Contest> list = findByActiveFeatured(contestActive, featured, 0,
                1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("contestActive=");
            msg.append(contestActive);

            msg.append(", featured=");
            msg.append(featured);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contest findByActiveFeatured_Last(boolean contestActive,
        boolean featured, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        int count = countByActiveFeatured(contestActive, featured);

        List<Contest> list = findByActiveFeatured(contestActive, featured,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("contestActive=");
            msg.append(contestActive);

            msg.append(", featured=");
            msg.append(featured);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and featured = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param ContestPK the primary key of the current contest
     * @param contestActive the contest active
     * @param featured the featured
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contest[] findByActiveFeatured_PrevAndNext(long ContestPK,
        boolean contestActive, boolean featured,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByActiveFeatured_PrevAndNext(session, contest,
                    contestActive, featured, orderByComparator, true);

            array[1] = contest;

            array[2] = getByActiveFeatured_PrevAndNext(session, contest,
                    contestActive, featured, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByActiveFeatured_PrevAndNext(Session session,
        Contest contest, boolean contestActive, boolean featured,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_ACTIVEFEATURED_CONTESTACTIVE_2);

        query.append(_FINDER_COLUMN_ACTIVEFEATURED_FEATURED_2);

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
        else {
            query.append(ContestModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(contestActive);

        qPos.add(featured);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contest);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Contest> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the contests where contestActive = &#63; and flag = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @return the matching contests
     * @throws SystemException if a system exception occurred
     */
    public List<Contest> findByActiveFlag(boolean contestActive, int flag)
        throws SystemException {
        return findByActiveFlag(contestActive, flag, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests where contestActive = &#63; and flag = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of matching contests
     * @throws SystemException if a system exception occurred
     */
    public List<Contest> findByActiveFlag(boolean contestActive, int flag,
        int start, int end) throws SystemException {
        return findByActiveFlag(contestActive, flag, start, end, null);
    }

    /**
     * Returns an ordered range of all the contests where contestActive = &#63; and flag = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contests
     * @throws SystemException if a system exception occurred
     */
    public List<Contest> findByActiveFlag(boolean contestActive, int flag,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAG;
            finderArgs = new Object[] { contestActive, flag };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFLAG;
            finderArgs = new Object[] {
                    contestActive, flag,
                    
                    start, end, orderByComparator
                };
        }

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFLAG_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFLAG_FLAG_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(ContestModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                qPos.add(flag);

                list = (List<Contest>) QueryUtil.list(q, getDialect(), start,
                        end);
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
     * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contest findByActiveFlag_First(boolean contestActive, int flag,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        List<Contest> list = findByActiveFlag(contestActive, flag, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("contestActive=");
            msg.append(contestActive);

            msg.append(", flag=");
            msg.append(flag);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contest findByActiveFlag_Last(boolean contestActive, int flag,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        int count = countByActiveFlag(contestActive, flag);

        List<Contest> list = findByActiveFlag(contestActive, flag, count - 1,
                count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("contestActive=");
            msg.append(contestActive);

            msg.append(", flag=");
            msg.append(flag);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flag = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param ContestPK the primary key of the current contest
     * @param contestActive the contest active
     * @param flag the flag
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contest[] findByActiveFlag_PrevAndNext(long ContestPK,
        boolean contestActive, int flag, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByActiveFlag_PrevAndNext(session, contest,
                    contestActive, flag, orderByComparator, true);

            array[1] = contest;

            array[2] = getByActiveFlag_PrevAndNext(session, contest,
                    contestActive, flag, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByActiveFlag_PrevAndNext(Session session,
        Contest contest, boolean contestActive, int flag,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_ACTIVEFLAG_CONTESTACTIVE_2);

        query.append(_FINDER_COLUMN_ACTIVEFLAG_FLAG_2);

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
        else {
            query.append(ContestModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(contestActive);

        qPos.add(flag);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contest);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Contest> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the contests where contestActive = &#63; and flagText = &#63;.
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @return the matching contests
     * @throws SystemException if a system exception occurred
     */
    public List<Contest> findByActiveFlagText(boolean contestActive,
        String flagText) throws SystemException {
        return findByActiveFlagText(contestActive, flagText, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests where contestActive = &#63; and flagText = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of matching contests
     * @throws SystemException if a system exception occurred
     */
    public List<Contest> findByActiveFlagText(boolean contestActive,
        String flagText, int start, int end) throws SystemException {
        return findByActiveFlagText(contestActive, flagText, start, end, null);
    }

    /**
     * Returns an ordered range of all the contests where contestActive = &#63; and flagText = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching contests
     * @throws SystemException if a system exception occurred
     */
    public List<Contest> findByActiveFlagText(boolean contestActive,
        String flagText, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ACTIVEFLAGTEXT;
            finderArgs = new Object[] { contestActive, flagText };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ACTIVEFLAGTEXT;
            finderArgs = new Object[] {
                    contestActive, flagText,
                    
                    start, end, orderByComparator
                };
        }

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(4 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(4);
            }

            query.append(_SQL_SELECT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_CONTESTACTIVE_2);

            if (flagText == null) {
                query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_1);
            } else {
                if (flagText.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_3);
                } else {
                    query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_2);
                }
            }

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(ContestModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                if (flagText != null) {
                    qPos.add(flagText);
                }

                list = (List<Contest>) QueryUtil.list(q, getDialect(), start,
                        end);
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
     * Returns the first contest in the ordered set where contestActive = &#63; and flagText = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contest findByActiveFlagText_First(boolean contestActive,
        String flagText, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        List<Contest> list = findByActiveFlagText(contestActive, flagText, 0,
                1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("contestActive=");
            msg.append(contestActive);

            msg.append(", flagText=");
            msg.append(flagText);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last contest in the ordered set where contestActive = &#63; and flagText = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching contest
     * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contest findByActiveFlagText_Last(boolean contestActive,
        String flagText, OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        int count = countByActiveFlagText(contestActive, flagText);

        List<Contest> list = findByActiveFlagText(contestActive, flagText,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("contestActive=");
            msg.append(contestActive);

            msg.append(", flagText=");
            msg.append(flagText);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchContestException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flagText = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param ContestPK the primary key of the current contest
     * @param contestActive the contest active
     * @param flagText the flag text
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next contest
     * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public Contest[] findByActiveFlagText_PrevAndNext(long ContestPK,
        boolean contestActive, String flagText,
        OrderByComparator orderByComparator)
        throws NoSuchContestException, SystemException {
        Contest contest = findByPrimaryKey(ContestPK);

        Session session = null;

        try {
            session = openSession();

            Contest[] array = new ContestImpl[3];

            array[0] = getByActiveFlagText_PrevAndNext(session, contest,
                    contestActive, flagText, orderByComparator, true);

            array[1] = contest;

            array[2] = getByActiveFlagText_PrevAndNext(session, contest,
                    contestActive, flagText, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected Contest getByActiveFlagText_PrevAndNext(Session session,
        Contest contest, boolean contestActive, String flagText,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_CONTEST_WHERE);

        query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_CONTESTACTIVE_2);

        if (flagText == null) {
            query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_1);
        } else {
            if (flagText.equals(StringPool.BLANK)) {
                query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_3);
            } else {
                query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_2);
            }
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
        }
        else {
            query.append(ContestModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(contestActive);

        if (flagText != null) {
            qPos.add(flagText);
        }

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(contest);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<Contest> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the contests.
     *
     * @return the contests
     * @throws SystemException if a system exception occurred
     */
    public List<Contest> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the contests.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @return the range of contests
     * @throws SystemException if a system exception occurred
     */
    public List<Contest> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the contests.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of contests
     * @param end the upper bound of the range of contests (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of contests
     * @throws SystemException if a system exception occurred
     */
    public List<Contest> findAll(int start, int end,
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

        List<Contest> list = (List<Contest>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_CONTEST);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_CONTEST.concat(ContestModelImpl.ORDER_BY_JPQL);
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<Contest>) QueryUtil.list(q, getDialect(),
                            start, end);
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
     * Removes all the contests where PlanTypeId = &#63; from the database.
     *
     * @param PlanTypeId the plan type ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByType(long PlanTypeId) throws SystemException {
        for (Contest contest : findByType(PlanTypeId)) {
            remove(contest);
        }
    }

    /**
     * Removes the contest where contestActive = &#63; from the database.
     *
     * @param contestActive the contest active
     * @throws SystemException if a system exception occurred
     */
    public void removeBycontestActive(boolean contestActive)
        throws NoSuchContestException, SystemException {
        Contest contest = findBycontestActive(contestActive);

        remove(contest);
    }

    /**
     * Removes all the contests where contestActive = &#63; and featured = &#63; from the database.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @throws SystemException if a system exception occurred
     */
    public void removeByActiveFeatured(boolean contestActive, boolean featured)
        throws SystemException {
        for (Contest contest : findByActiveFeatured(contestActive, featured)) {
            remove(contest);
        }
    }

    /**
     * Removes all the contests where contestActive = &#63; and flag = &#63; from the database.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @throws SystemException if a system exception occurred
     */
    public void removeByActiveFlag(boolean contestActive, int flag)
        throws SystemException {
        for (Contest contest : findByActiveFlag(contestActive, flag)) {
            remove(contest);
        }
    }

    /**
     * Removes all the contests where contestActive = &#63; and flagText = &#63; from the database.
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @throws SystemException if a system exception occurred
     */
    public void removeByActiveFlagText(boolean contestActive, String flagText)
        throws SystemException {
        for (Contest contest : findByActiveFlagText(contestActive, flagText)) {
            remove(contest);
        }
    }

    /**
     * Removes all the contests from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (Contest contest : findAll()) {
            remove(contest);
        }
    }

    /**
     * Returns the number of contests where PlanTypeId = &#63;.
     *
     * @param PlanTypeId the plan type ID
     * @return the number of matching contests
     * @throws SystemException if a system exception occurred
     */
    public int countByType(long PlanTypeId) throws SystemException {
        Object[] finderArgs = new Object[] { PlanTypeId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_TYPE,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_TYPE_PLANTYPEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(PlanTypeId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_TYPE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of contests where contestActive = &#63;.
     *
     * @param contestActive the contest active
     * @return the number of matching contests
     * @throws SystemException if a system exception occurred
     */
    public int countBycontestActive(boolean contestActive)
        throws SystemException {
        Object[] finderArgs = new Object[] { contestActive };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_CONTESTACTIVE,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_CONTESTACTIVE_CONTESTACTIVE_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_CONTESTACTIVE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of contests where contestActive = &#63; and featured = &#63;.
     *
     * @param contestActive the contest active
     * @param featured the featured
     * @return the number of matching contests
     * @throws SystemException if a system exception occurred
     */
    public int countByActiveFeatured(boolean contestActive, boolean featured)
        throws SystemException {
        Object[] finderArgs = new Object[] { contestActive, featured };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACTIVEFEATURED,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFEATURED_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFEATURED_FEATURED_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                qPos.add(featured);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ACTIVEFEATURED,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of contests where contestActive = &#63; and flag = &#63;.
     *
     * @param contestActive the contest active
     * @param flag the flag
     * @return the number of matching contests
     * @throws SystemException if a system exception occurred
     */
    public int countByActiveFlag(boolean contestActive, int flag)
        throws SystemException {
        Object[] finderArgs = new Object[] { contestActive, flag };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACTIVEFLAG,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFLAG_CONTESTACTIVE_2);

            query.append(_FINDER_COLUMN_ACTIVEFLAG_FLAG_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                qPos.add(flag);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ACTIVEFLAG,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of contests where contestActive = &#63; and flagText = &#63;.
     *
     * @param contestActive the contest active
     * @param flagText the flag text
     * @return the number of matching contests
     * @throws SystemException if a system exception occurred
     */
    public int countByActiveFlagText(boolean contestActive, String flagText)
        throws SystemException {
        Object[] finderArgs = new Object[] { contestActive, flagText };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ACTIVEFLAGTEXT,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_CONTEST_WHERE);

            query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_CONTESTACTIVE_2);

            if (flagText == null) {
                query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_1);
            } else {
                if (flagText.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_3);
                } else {
                    query.append(_FINDER_COLUMN_ACTIVEFLAGTEXT_FLAGTEXT_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(contestActive);

                if (flagText != null) {
                    qPos.add(flagText);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ACTIVEFLAGTEXT,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of contests.
     *
     * @return the number of contests
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_CONTEST);

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
     * Initializes the contest persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.Contest")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<Contest>> listenersList = new ArrayList<ModelListener<Contest>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<Contest>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ContestImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
