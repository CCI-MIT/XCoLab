package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchProposalContestPhaseAttributeTypeException;
import com.ext.portlet.model.ProposalContestPhaseAttributeType;
import com.ext.portlet.model.impl.ProposalContestPhaseAttributeTypeImpl;
import com.ext.portlet.model.impl.ProposalContestPhaseAttributeTypeModelImpl;
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
 * The persistence implementation for the proposal contest phase attribute type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalContestPhaseAttributeTypePersistence
 * @see ProposalContestPhaseAttributeTypeUtil
 * @generated
 */
public class ProposalContestPhaseAttributeTypePersistenceImpl
    extends BasePersistenceImpl<ProposalContestPhaseAttributeType>
    implements ProposalContestPhaseAttributeTypePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProposalContestPhaseAttributeTypeUtil} to access the proposal contest phase attribute type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProposalContestPhaseAttributeTypeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProposalContestPhaseAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeTypeModelImpl.FINDER_CACHE_ENABLED,
            ProposalContestPhaseAttributeTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProposalContestPhaseAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeTypeModelImpl.FINDER_CACHE_ENABLED,
            ProposalContestPhaseAttributeTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProposalContestPhaseAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeTypeModelImpl.FINDER_CACHE_ENABLED,
            Long.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
            new String[0]);
    private static final String _SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTETYPE = "SELECT proposalContestPhaseAttributeType FROM ProposalContestPhaseAttributeType proposalContestPhaseAttributeType";
    private static final String _SQL_COUNT_PROPOSALCONTESTPHASEATTRIBUTETYPE = "SELECT COUNT(proposalContestPhaseAttributeType) FROM ProposalContestPhaseAttributeType proposalContestPhaseAttributeType";
    private static final String _ORDER_BY_ENTITY_ALIAS = "proposalContestPhaseAttributeType.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProposalContestPhaseAttributeType exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ProposalContestPhaseAttributeTypePersistenceImpl.class);
    private static ProposalContestPhaseAttributeType _nullProposalContestPhaseAttributeType =
        new ProposalContestPhaseAttributeTypeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ProposalContestPhaseAttributeType> toCacheModel() {
                return _nullProposalContestPhaseAttributeTypeCacheModel;
            }
        };

    private static CacheModel<ProposalContestPhaseAttributeType> _nullProposalContestPhaseAttributeTypeCacheModel =
        new CacheModel<ProposalContestPhaseAttributeType>() {
            public ProposalContestPhaseAttributeType toEntityModel() {
                return _nullProposalContestPhaseAttributeType;
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
     * Caches the proposal contest phase attribute type in the entity cache if it is enabled.
     *
     * @param proposalContestPhaseAttributeType the proposal contest phase attribute type
     */
    public void cacheResult(
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType) {
        EntityCacheUtil.putResult(ProposalContestPhaseAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeTypeImpl.class,
            proposalContestPhaseAttributeType.getPrimaryKey(),
            proposalContestPhaseAttributeType);

        proposalContestPhaseAttributeType.resetOriginalValues();
    }

    /**
     * Caches the proposal contest phase attribute types in the entity cache if it is enabled.
     *
     * @param proposalContestPhaseAttributeTypes the proposal contest phase attribute types
     */
    public void cacheResult(
        List<ProposalContestPhaseAttributeType> proposalContestPhaseAttributeTypes) {
        for (ProposalContestPhaseAttributeType proposalContestPhaseAttributeType : proposalContestPhaseAttributeTypes) {
            if (EntityCacheUtil.getResult(
                        ProposalContestPhaseAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalContestPhaseAttributeTypeImpl.class,
                        proposalContestPhaseAttributeType.getPrimaryKey()) == null) {
                cacheResult(proposalContestPhaseAttributeType);
            } else {
                proposalContestPhaseAttributeType.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all proposal contest phase attribute types.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProposalContestPhaseAttributeTypeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProposalContestPhaseAttributeTypeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the proposal contest phase attribute type.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType) {
        EntityCacheUtil.removeResult(ProposalContestPhaseAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeTypeImpl.class,
            proposalContestPhaseAttributeType.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(
        List<ProposalContestPhaseAttributeType> proposalContestPhaseAttributeTypes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ProposalContestPhaseAttributeType proposalContestPhaseAttributeType : proposalContestPhaseAttributeTypes) {
            EntityCacheUtil.removeResult(ProposalContestPhaseAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
                ProposalContestPhaseAttributeTypeImpl.class,
                proposalContestPhaseAttributeType.getPrimaryKey());
        }
    }

    /**
     * Creates a new proposal contest phase attribute type with the primary key. Does not add the proposal contest phase attribute type to the database.
     *
     * @param id the primary key for the new proposal contest phase attribute type
     * @return the new proposal contest phase attribute type
     */
    public ProposalContestPhaseAttributeType create(long id) {
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType = new ProposalContestPhaseAttributeTypeImpl();

        proposalContestPhaseAttributeType.setNew(true);
        proposalContestPhaseAttributeType.setPrimaryKey(id);

        return proposalContestPhaseAttributeType;
    }

    /**
     * Removes the proposal contest phase attribute type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param id the primary key of the proposal contest phase attribute type
     * @return the proposal contest phase attribute type that was removed
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeTypeException if a proposal contest phase attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalContestPhaseAttributeType remove(long id)
        throws NoSuchProposalContestPhaseAttributeTypeException, SystemException {
        return remove(Long.valueOf(id));
    }

    /**
     * Removes the proposal contest phase attribute type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the proposal contest phase attribute type
     * @return the proposal contest phase attribute type that was removed
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeTypeException if a proposal contest phase attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttributeType remove(Serializable primaryKey)
        throws NoSuchProposalContestPhaseAttributeTypeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ProposalContestPhaseAttributeType proposalContestPhaseAttributeType = (ProposalContestPhaseAttributeType) session.get(ProposalContestPhaseAttributeTypeImpl.class,
                    primaryKey);

            if (proposalContestPhaseAttributeType == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProposalContestPhaseAttributeTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(proposalContestPhaseAttributeType);
        } catch (NoSuchProposalContestPhaseAttributeTypeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ProposalContestPhaseAttributeType removeImpl(
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType)
        throws SystemException {
        proposalContestPhaseAttributeType = toUnwrappedModel(proposalContestPhaseAttributeType);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, proposalContestPhaseAttributeType);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(proposalContestPhaseAttributeType);

        return proposalContestPhaseAttributeType;
    }

    @Override
    public ProposalContestPhaseAttributeType updateImpl(
        com.ext.portlet.model.ProposalContestPhaseAttributeType proposalContestPhaseAttributeType,
        boolean merge) throws SystemException {
        proposalContestPhaseAttributeType = toUnwrappedModel(proposalContestPhaseAttributeType);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, proposalContestPhaseAttributeType,
                merge);

            proposalContestPhaseAttributeType.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        EntityCacheUtil.putResult(ProposalContestPhaseAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalContestPhaseAttributeTypeImpl.class,
            proposalContestPhaseAttributeType.getPrimaryKey(),
            proposalContestPhaseAttributeType);

        return proposalContestPhaseAttributeType;
    }

    protected ProposalContestPhaseAttributeType toUnwrappedModel(
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType) {
        if (proposalContestPhaseAttributeType instanceof ProposalContestPhaseAttributeTypeImpl) {
            return proposalContestPhaseAttributeType;
        }

        ProposalContestPhaseAttributeTypeImpl proposalContestPhaseAttributeTypeImpl =
            new ProposalContestPhaseAttributeTypeImpl();

        proposalContestPhaseAttributeTypeImpl.setNew(proposalContestPhaseAttributeType.isNew());
        proposalContestPhaseAttributeTypeImpl.setPrimaryKey(proposalContestPhaseAttributeType.getPrimaryKey());

        proposalContestPhaseAttributeTypeImpl.setId(proposalContestPhaseAttributeType.getId());
        proposalContestPhaseAttributeTypeImpl.setRibbon(proposalContestPhaseAttributeType.getRibbon());
        proposalContestPhaseAttributeTypeImpl.setHoverText(proposalContestPhaseAttributeType.getHoverText());
        proposalContestPhaseAttributeTypeImpl.setDescription(proposalContestPhaseAttributeType.getDescription());
        proposalContestPhaseAttributeTypeImpl.setCopyOnPromote(proposalContestPhaseAttributeType.isCopyOnPromote());

        return proposalContestPhaseAttributeTypeImpl;
    }

    /**
     * Returns the proposal contest phase attribute type with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the proposal contest phase attribute type
     * @return the proposal contest phase attribute type
     * @throws com.liferay.portal.NoSuchModelException if a proposal contest phase attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttributeType findByPrimaryKey(
        Serializable primaryKey) throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the proposal contest phase attribute type with the primary key or throws a {@link com.ext.portlet.NoSuchProposalContestPhaseAttributeTypeException} if it could not be found.
     *
     * @param id the primary key of the proposal contest phase attribute type
     * @return the proposal contest phase attribute type
     * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeTypeException if a proposal contest phase attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalContestPhaseAttributeType findByPrimaryKey(long id)
        throws NoSuchProposalContestPhaseAttributeTypeException, SystemException {
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType = fetchByPrimaryKey(id);

        if (proposalContestPhaseAttributeType == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
            }

            throw new NoSuchProposalContestPhaseAttributeTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                id);
        }

        return proposalContestPhaseAttributeType;
    }

    /**
     * Returns the proposal contest phase attribute type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the proposal contest phase attribute type
     * @return the proposal contest phase attribute type, or <code>null</code> if a proposal contest phase attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalContestPhaseAttributeType fetchByPrimaryKey(
        Serializable primaryKey) throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the proposal contest phase attribute type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param id the primary key of the proposal contest phase attribute type
     * @return the proposal contest phase attribute type, or <code>null</code> if a proposal contest phase attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalContestPhaseAttributeType fetchByPrimaryKey(long id)
        throws SystemException {
        ProposalContestPhaseAttributeType proposalContestPhaseAttributeType = (ProposalContestPhaseAttributeType) EntityCacheUtil.getResult(ProposalContestPhaseAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
                ProposalContestPhaseAttributeTypeImpl.class, id);

        if (proposalContestPhaseAttributeType == _nullProposalContestPhaseAttributeType) {
            return null;
        }

        if (proposalContestPhaseAttributeType == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                proposalContestPhaseAttributeType = (ProposalContestPhaseAttributeType) session.get(ProposalContestPhaseAttributeTypeImpl.class,
                        Long.valueOf(id));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (proposalContestPhaseAttributeType != null) {
                    cacheResult(proposalContestPhaseAttributeType);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(ProposalContestPhaseAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalContestPhaseAttributeTypeImpl.class, id,
                        _nullProposalContestPhaseAttributeType);
                }

                closeSession(session);
            }
        }

        return proposalContestPhaseAttributeType;
    }

    /**
     * Returns all the proposal contest phase attribute types.
     *
     * @return the proposal contest phase attribute types
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalContestPhaseAttributeType> findAll()
        throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal contest phase attribute types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of proposal contest phase attribute types
     * @param end the upper bound of the range of proposal contest phase attribute types (not inclusive)
     * @return the range of proposal contest phase attribute types
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalContestPhaseAttributeType> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal contest phase attribute types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of proposal contest phase attribute types
     * @param end the upper bound of the range of proposal contest phase attribute types (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of proposal contest phase attribute types
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalContestPhaseAttributeType> findAll(int start, int end,
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

        List<ProposalContestPhaseAttributeType> list = (List<ProposalContestPhaseAttributeType>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTETYPE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PROPOSALCONTESTPHASEATTRIBUTETYPE;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<ProposalContestPhaseAttributeType>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ProposalContestPhaseAttributeType>) QueryUtil.list(q,
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
     * Removes all the proposal contest phase attribute types from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (ProposalContestPhaseAttributeType proposalContestPhaseAttributeType : findAll()) {
            remove(proposalContestPhaseAttributeType);
        }
    }

    /**
     * Returns the number of proposal contest phase attribute types.
     *
     * @return the number of proposal contest phase attribute types
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_PROPOSALCONTESTPHASEATTRIBUTETYPE);

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
     * Initializes the proposal contest phase attribute type persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ProposalContestPhaseAttributeType")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ProposalContestPhaseAttributeType>> listenersList =
                    new ArrayList<ModelListener<ProposalContestPhaseAttributeType>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ProposalContestPhaseAttributeType>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProposalContestPhaseAttributeTypeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
