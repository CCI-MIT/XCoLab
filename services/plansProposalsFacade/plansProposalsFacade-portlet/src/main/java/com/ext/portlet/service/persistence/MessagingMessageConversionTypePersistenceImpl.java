package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchMessagingMessageConversionTypeException;
import com.ext.portlet.model.MessagingMessageConversionType;
import com.ext.portlet.model.impl.MessagingMessageConversionTypeImpl;
import com.ext.portlet.model.impl.MessagingMessageConversionTypeModelImpl;
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
 * The persistence implementation for the messaging message conversion type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageConversionTypePersistence
 * @see MessagingMessageConversionTypeUtil
 * @generated
 */
public class MessagingMessageConversionTypePersistenceImpl
    extends BasePersistenceImpl<MessagingMessageConversionType>
    implements MessagingMessageConversionTypePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link MessagingMessageConversionTypeUtil} to access the messaging message conversion type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = MessagingMessageConversionTypeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_FETCH_BY_FINDBYNAME = new FinderPath(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionTypeModelImpl.FINDER_CACHE_ENABLED,
            MessagingMessageConversionTypeImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByfindByName", new String[] { String.class.getName() },
            MessagingMessageConversionTypeModelImpl.NAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_FINDBYNAME = new FinderPath(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionTypeModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByfindByName", new String[] { String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionTypeModelImpl.FINDER_CACHE_ENABLED,
            MessagingMessageConversionTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionTypeModelImpl.FINDER_CACHE_ENABLED,
            MessagingMessageConversionTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionTypeModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    private static final String _SQL_SELECT_MESSAGINGMESSAGECONVERSIONTYPE = "SELECT messagingMessageConversionType FROM MessagingMessageConversionType messagingMessageConversionType";
    private static final String _SQL_SELECT_MESSAGINGMESSAGECONVERSIONTYPE_WHERE =
        "SELECT messagingMessageConversionType FROM MessagingMessageConversionType messagingMessageConversionType WHERE ";
    private static final String _SQL_COUNT_MESSAGINGMESSAGECONVERSIONTYPE = "SELECT COUNT(messagingMessageConversionType) FROM MessagingMessageConversionType messagingMessageConversionType";
    private static final String _SQL_COUNT_MESSAGINGMESSAGECONVERSIONTYPE_WHERE = "SELECT COUNT(messagingMessageConversionType) FROM MessagingMessageConversionType messagingMessageConversionType WHERE ";
    private static final String _FINDER_COLUMN_FINDBYNAME_NAME_1 = "messagingMessageConversionType.name IS NULL";
    private static final String _FINDER_COLUMN_FINDBYNAME_NAME_2 = "messagingMessageConversionType.name = ?";
    private static final String _FINDER_COLUMN_FINDBYNAME_NAME_3 = "(messagingMessageConversionType.name IS NULL OR messagingMessageConversionType.name = ?)";
    private static final String _ORDER_BY_ENTITY_ALIAS = "messagingMessageConversionType.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No MessagingMessageConversionType exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No MessagingMessageConversionType exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(MessagingMessageConversionTypePersistenceImpl.class);
    private static MessagingMessageConversionType _nullMessagingMessageConversionType =
        new MessagingMessageConversionTypeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<MessagingMessageConversionType> toCacheModel() {
                return _nullMessagingMessageConversionTypeCacheModel;
            }
        };

    private static CacheModel<MessagingMessageConversionType> _nullMessagingMessageConversionTypeCacheModel =
        new CacheModel<MessagingMessageConversionType>() {
            public MessagingMessageConversionType toEntityModel() {
                return _nullMessagingMessageConversionType;
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
     * Caches the messaging message conversion type in the entity cache if it is enabled.
     *
     * @param messagingMessageConversionType the messaging message conversion type
     */
    public void cacheResult(
        MessagingMessageConversionType messagingMessageConversionType) {
        EntityCacheUtil.putResult(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionTypeImpl.class,
            messagingMessageConversionType.getPrimaryKey(),
            messagingMessageConversionType);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYNAME,
            new Object[] { messagingMessageConversionType.getName() },
            messagingMessageConversionType);

        messagingMessageConversionType.resetOriginalValues();
    }

    /**
     * Caches the messaging message conversion types in the entity cache if it is enabled.
     *
     * @param messagingMessageConversionTypes the messaging message conversion types
     */
    public void cacheResult(
        List<MessagingMessageConversionType> messagingMessageConversionTypes) {
        for (MessagingMessageConversionType messagingMessageConversionType : messagingMessageConversionTypes) {
            if (EntityCacheUtil.getResult(
                        MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
                        MessagingMessageConversionTypeImpl.class,
                        messagingMessageConversionType.getPrimaryKey()) == null) {
                cacheResult(messagingMessageConversionType);
            } else {
                messagingMessageConversionType.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all messaging message conversion types.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(MessagingMessageConversionTypeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(MessagingMessageConversionTypeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the messaging message conversion type.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(
        MessagingMessageConversionType messagingMessageConversionType) {
        EntityCacheUtil.removeResult(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionTypeImpl.class,
            messagingMessageConversionType.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(messagingMessageConversionType);
    }

    @Override
    public void clearCache(
        List<MessagingMessageConversionType> messagingMessageConversionTypes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (MessagingMessageConversionType messagingMessageConversionType : messagingMessageConversionTypes) {
            EntityCacheUtil.removeResult(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
                MessagingMessageConversionTypeImpl.class,
                messagingMessageConversionType.getPrimaryKey());

            clearUniqueFindersCache(messagingMessageConversionType);
        }
    }

    protected void clearUniqueFindersCache(
        MessagingMessageConversionType messagingMessageConversionType) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FINDBYNAME,
            new Object[] { messagingMessageConversionType.getName() });
    }

    /**
     * Creates a new messaging message conversion type with the primary key. Does not add the messaging message conversion type to the database.
     *
     * @param typeId the primary key for the new messaging message conversion type
     * @return the new messaging message conversion type
     */
    public MessagingMessageConversionType create(long typeId) {
        MessagingMessageConversionType messagingMessageConversionType = new MessagingMessageConversionTypeImpl();

        messagingMessageConversionType.setNew(true);
        messagingMessageConversionType.setPrimaryKey(typeId);

        return messagingMessageConversionType;
    }

    /**
     * Removes the messaging message conversion type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param typeId the primary key of the messaging message conversion type
     * @return the messaging message conversion type that was removed
     * @throws com.ext.portlet.NoSuchMessagingMessageConversionTypeException if a messaging message conversion type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessagingMessageConversionType remove(long typeId)
        throws NoSuchMessagingMessageConversionTypeException, SystemException {
        return remove(Long.valueOf(typeId));
    }

    /**
     * Removes the messaging message conversion type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the messaging message conversion type
     * @return the messaging message conversion type that was removed
     * @throws com.ext.portlet.NoSuchMessagingMessageConversionTypeException if a messaging message conversion type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversionType remove(Serializable primaryKey)
        throws NoSuchMessagingMessageConversionTypeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            MessagingMessageConversionType messagingMessageConversionType = (MessagingMessageConversionType) session.get(MessagingMessageConversionTypeImpl.class,
                    primaryKey);

            if (messagingMessageConversionType == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchMessagingMessageConversionTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(messagingMessageConversionType);
        } catch (NoSuchMessagingMessageConversionTypeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected MessagingMessageConversionType removeImpl(
        MessagingMessageConversionType messagingMessageConversionType)
        throws SystemException {
        messagingMessageConversionType = toUnwrappedModel(messagingMessageConversionType);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, messagingMessageConversionType);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(messagingMessageConversionType);

        return messagingMessageConversionType;
    }

    @Override
    public MessagingMessageConversionType updateImpl(
        com.ext.portlet.model.MessagingMessageConversionType messagingMessageConversionType,
        boolean merge) throws SystemException {
        messagingMessageConversionType = toUnwrappedModel(messagingMessageConversionType);

        boolean isNew = messagingMessageConversionType.isNew();

        MessagingMessageConversionTypeModelImpl messagingMessageConversionTypeModelImpl =
            (MessagingMessageConversionTypeModelImpl) messagingMessageConversionType;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, messagingMessageConversionType,
                merge);

            messagingMessageConversionType.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew ||
                !MessagingMessageConversionTypeModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
            MessagingMessageConversionTypeImpl.class,
            messagingMessageConversionType.getPrimaryKey(),
            messagingMessageConversionType);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYNAME,
                new Object[] { messagingMessageConversionType.getName() },
                messagingMessageConversionType);
        } else {
            if ((messagingMessageConversionTypeModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_FINDBYNAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        messagingMessageConversionTypeModelImpl.getOriginalName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FINDBYNAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FINDBYNAME,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYNAME,
                    new Object[] { messagingMessageConversionType.getName() },
                    messagingMessageConversionType);
            }
        }

        return messagingMessageConversionType;
    }

    protected MessagingMessageConversionType toUnwrappedModel(
        MessagingMessageConversionType messagingMessageConversionType) {
        if (messagingMessageConversionType instanceof MessagingMessageConversionTypeImpl) {
            return messagingMessageConversionType;
        }

        MessagingMessageConversionTypeImpl messagingMessageConversionTypeImpl = new MessagingMessageConversionTypeImpl();

        messagingMessageConversionTypeImpl.setNew(messagingMessageConversionType.isNew());
        messagingMessageConversionTypeImpl.setPrimaryKey(messagingMessageConversionType.getPrimaryKey());

        messagingMessageConversionTypeImpl.setTypeId(messagingMessageConversionType.getTypeId());
        messagingMessageConversionTypeImpl.setName(messagingMessageConversionType.getName());
        messagingMessageConversionTypeImpl.setDescription(messagingMessageConversionType.getDescription());

        return messagingMessageConversionTypeImpl;
    }

    /**
     * Returns the messaging message conversion type with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the messaging message conversion type
     * @return the messaging message conversion type
     * @throws com.liferay.portal.NoSuchModelException if a messaging message conversion type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversionType findByPrimaryKey(
        Serializable primaryKey) throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the messaging message conversion type with the primary key or throws a {@link com.ext.portlet.NoSuchMessagingMessageConversionTypeException} if it could not be found.
     *
     * @param typeId the primary key of the messaging message conversion type
     * @return the messaging message conversion type
     * @throws com.ext.portlet.NoSuchMessagingMessageConversionTypeException if a messaging message conversion type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessagingMessageConversionType findByPrimaryKey(long typeId)
        throws NoSuchMessagingMessageConversionTypeException, SystemException {
        MessagingMessageConversionType messagingMessageConversionType = fetchByPrimaryKey(typeId);

        if (messagingMessageConversionType == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + typeId);
            }

            throw new NoSuchMessagingMessageConversionTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                typeId);
        }

        return messagingMessageConversionType;
    }

    /**
     * Returns the messaging message conversion type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the messaging message conversion type
     * @return the messaging message conversion type, or <code>null</code> if a messaging message conversion type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public MessagingMessageConversionType fetchByPrimaryKey(
        Serializable primaryKey) throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the messaging message conversion type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param typeId the primary key of the messaging message conversion type
     * @return the messaging message conversion type, or <code>null</code> if a messaging message conversion type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessagingMessageConversionType fetchByPrimaryKey(long typeId)
        throws SystemException {
        MessagingMessageConversionType messagingMessageConversionType = (MessagingMessageConversionType) EntityCacheUtil.getResult(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
                MessagingMessageConversionTypeImpl.class, typeId);

        if (messagingMessageConversionType == _nullMessagingMessageConversionType) {
            return null;
        }

        if (messagingMessageConversionType == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                messagingMessageConversionType = (MessagingMessageConversionType) session.get(MessagingMessageConversionTypeImpl.class,
                        Long.valueOf(typeId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (messagingMessageConversionType != null) {
                    cacheResult(messagingMessageConversionType);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(MessagingMessageConversionTypeModelImpl.ENTITY_CACHE_ENABLED,
                        MessagingMessageConversionTypeImpl.class, typeId,
                        _nullMessagingMessageConversionType);
                }

                closeSession(session);
            }
        }

        return messagingMessageConversionType;
    }

    /**
     * Returns the messaging message conversion type where name = &#63; or throws a {@link com.ext.portlet.NoSuchMessagingMessageConversionTypeException} if it could not be found.
     *
     * @param name the name
     * @return the matching messaging message conversion type
     * @throws com.ext.portlet.NoSuchMessagingMessageConversionTypeException if a matching messaging message conversion type could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessagingMessageConversionType findByfindByName(String name)
        throws NoSuchMessagingMessageConversionTypeException, SystemException {
        MessagingMessageConversionType messagingMessageConversionType = fetchByfindByName(name);

        if (messagingMessageConversionType == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("name=");
            msg.append(name);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchMessagingMessageConversionTypeException(msg.toString());
        }

        return messagingMessageConversionType;
    }

    /**
     * Returns the messaging message conversion type where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param name the name
     * @return the matching messaging message conversion type, or <code>null</code> if a matching messaging message conversion type could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessagingMessageConversionType fetchByfindByName(String name)
        throws SystemException {
        return fetchByfindByName(name, true);
    }

    /**
     * Returns the messaging message conversion type where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param name the name
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching messaging message conversion type, or <code>null</code> if a matching messaging message conversion type could not be found
     * @throws SystemException if a system exception occurred
     */
    public MessagingMessageConversionType fetchByfindByName(String name,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { name };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_FINDBYNAME,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_SELECT_MESSAGINGMESSAGECONVERSIONTYPE_WHERE);

            if (name == null) {
                query.append(_FINDER_COLUMN_FINDBYNAME_NAME_1);
            } else {
                if (name.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_FINDBYNAME_NAME_3);
                } else {
                    query.append(_FINDER_COLUMN_FINDBYNAME_NAME_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (name != null) {
                    qPos.add(name);
                }

                List<MessagingMessageConversionType> list = q.list();

                result = list;

                MessagingMessageConversionType messagingMessageConversionType = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYNAME,
                        finderArgs, list);
                } else {
                    messagingMessageConversionType = list.get(0);

                    cacheResult(messagingMessageConversionType);

                    if ((messagingMessageConversionType.getName() == null) ||
                            !messagingMessageConversionType.getName()
                                                               .equals(name)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_FINDBYNAME,
                            finderArgs, messagingMessageConversionType);
                    }
                }

                return messagingMessageConversionType;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_FINDBYNAME,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (MessagingMessageConversionType) result;
            }
        }
    }

    /**
     * Returns all the messaging message conversion types.
     *
     * @return the messaging message conversion types
     * @throws SystemException if a system exception occurred
     */
    public List<MessagingMessageConversionType> findAll()
        throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the messaging message conversion types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of messaging message conversion types
     * @param end the upper bound of the range of messaging message conversion types (not inclusive)
     * @return the range of messaging message conversion types
     * @throws SystemException if a system exception occurred
     */
    public List<MessagingMessageConversionType> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the messaging message conversion types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of messaging message conversion types
     * @param end the upper bound of the range of messaging message conversion types (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of messaging message conversion types
     * @throws SystemException if a system exception occurred
     */
    public List<MessagingMessageConversionType> findAll(int start, int end,
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

        List<MessagingMessageConversionType> list = (List<MessagingMessageConversionType>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MESSAGINGMESSAGECONVERSIONTYPE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MESSAGINGMESSAGECONVERSIONTYPE;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<MessagingMessageConversionType>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<MessagingMessageConversionType>) QueryUtil.list(q,
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
     * Removes the messaging message conversion type where name = &#63; from the database.
     *
     * @param name the name
     * @throws SystemException if a system exception occurred
     */
    public void removeByfindByName(String name)
        throws NoSuchMessagingMessageConversionTypeException, SystemException {
        MessagingMessageConversionType messagingMessageConversionType = findByfindByName(name);

        remove(messagingMessageConversionType);
    }

    /**
     * Removes all the messaging message conversion types from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (MessagingMessageConversionType messagingMessageConversionType : findAll()) {
            remove(messagingMessageConversionType);
        }
    }

    /**
     * Returns the number of messaging message conversion types where name = &#63;.
     *
     * @param name the name
     * @return the number of matching messaging message conversion types
     * @throws SystemException if a system exception occurred
     */
    public int countByfindByName(String name) throws SystemException {
        Object[] finderArgs = new Object[] { name };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FINDBYNAME,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MESSAGINGMESSAGECONVERSIONTYPE_WHERE);

            if (name == null) {
                query.append(_FINDER_COLUMN_FINDBYNAME_NAME_1);
            } else {
                if (name.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_FINDBYNAME_NAME_3);
                } else {
                    query.append(_FINDER_COLUMN_FINDBYNAME_NAME_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                if (name != null) {
                    qPos.add(name);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FINDBYNAME,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of messaging message conversion types.
     *
     * @return the number of messaging message conversion types
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_MESSAGINGMESSAGECONVERSIONTYPE);

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
     * Initializes the messaging message conversion type persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.MessagingMessageConversionType")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<MessagingMessageConversionType>> listenersList =
                    new ArrayList<ModelListener<MessagingMessageConversionType>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<MessagingMessageConversionType>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(MessagingMessageConversionTypeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
