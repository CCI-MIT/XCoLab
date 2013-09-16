package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchProposalAttributeTypeException;
import com.ext.portlet.model.ProposalAttributeType;
import com.ext.portlet.model.impl.ProposalAttributeTypeImpl;
import com.ext.portlet.model.impl.ProposalAttributeTypeModelImpl;
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
 * The persistence implementation for the proposal attribute type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalAttributeTypePersistence
 * @see ProposalAttributeTypeUtil
 * @generated
 */
public class ProposalAttributeTypePersistenceImpl extends BasePersistenceImpl<ProposalAttributeType>
    implements ProposalAttributeTypePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ProposalAttributeTypeUtil} to access the proposal attribute type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ProposalAttributeTypeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ProposalAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeTypeModelImpl.FINDER_CACHE_ENABLED,
            ProposalAttributeTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ProposalAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeTypeModelImpl.FINDER_CACHE_ENABLED,
            ProposalAttributeTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ProposalAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PROPOSALATTRIBUTETYPE = "SELECT proposalAttributeType FROM ProposalAttributeType proposalAttributeType";
    private static final String _SQL_COUNT_PROPOSALATTRIBUTETYPE = "SELECT COUNT(proposalAttributeType) FROM ProposalAttributeType proposalAttributeType";
    private static final String _ORDER_BY_ENTITY_ALIAS = "proposalAttributeType.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ProposalAttributeType exists with the primary key ";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ProposalAttributeTypePersistenceImpl.class);
    private static ProposalAttributeType _nullProposalAttributeType = new ProposalAttributeTypeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ProposalAttributeType> toCacheModel() {
                return _nullProposalAttributeTypeCacheModel;
            }
        };

    private static CacheModel<ProposalAttributeType> _nullProposalAttributeTypeCacheModel =
        new CacheModel<ProposalAttributeType>() {
            public ProposalAttributeType toEntityModel() {
                return _nullProposalAttributeType;
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
     * Caches the proposal attribute type in the entity cache if it is enabled.
     *
     * @param proposalAttributeType the proposal attribute type
     */
    public void cacheResult(ProposalAttributeType proposalAttributeType) {
        EntityCacheUtil.putResult(ProposalAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeTypeImpl.class,
            proposalAttributeType.getPrimaryKey(), proposalAttributeType);

        proposalAttributeType.resetOriginalValues();
    }

    /**
     * Caches the proposal attribute types in the entity cache if it is enabled.
     *
     * @param proposalAttributeTypes the proposal attribute types
     */
    public void cacheResult(List<ProposalAttributeType> proposalAttributeTypes) {
        for (ProposalAttributeType proposalAttributeType : proposalAttributeTypes) {
            if (EntityCacheUtil.getResult(
                        ProposalAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalAttributeTypeImpl.class,
                        proposalAttributeType.getPrimaryKey()) == null) {
                cacheResult(proposalAttributeType);
            } else {
                proposalAttributeType.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all proposal attribute types.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ProposalAttributeTypeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ProposalAttributeTypeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the proposal attribute type.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ProposalAttributeType proposalAttributeType) {
        EntityCacheUtil.removeResult(ProposalAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeTypeImpl.class,
            proposalAttributeType.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ProposalAttributeType> proposalAttributeTypes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ProposalAttributeType proposalAttributeType : proposalAttributeTypes) {
            EntityCacheUtil.removeResult(ProposalAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
                ProposalAttributeTypeImpl.class,
                proposalAttributeType.getPrimaryKey());
        }
    }

    /**
     * Creates a new proposal attribute type with the primary key. Does not add the proposal attribute type to the database.
     *
     * @param name the primary key for the new proposal attribute type
     * @return the new proposal attribute type
     */
    public ProposalAttributeType create(String name) {
        ProposalAttributeType proposalAttributeType = new ProposalAttributeTypeImpl();

        proposalAttributeType.setNew(true);
        proposalAttributeType.setPrimaryKey(name);

        return proposalAttributeType;
    }

    /**
     * Removes the proposal attribute type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param name the primary key of the proposal attribute type
     * @return the proposal attribute type that was removed
     * @throws com.ext.portlet.NoSuchProposalAttributeTypeException if a proposal attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalAttributeType remove(String name)
        throws NoSuchProposalAttributeTypeException, SystemException {
        return remove((Serializable) name);
    }

    /**
     * Removes the proposal attribute type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the proposal attribute type
     * @return the proposal attribute type that was removed
     * @throws com.ext.portlet.NoSuchProposalAttributeTypeException if a proposal attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalAttributeType remove(Serializable primaryKey)
        throws NoSuchProposalAttributeTypeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ProposalAttributeType proposalAttributeType = (ProposalAttributeType) session.get(ProposalAttributeTypeImpl.class,
                    primaryKey);

            if (proposalAttributeType == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchProposalAttributeTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(proposalAttributeType);
        } catch (NoSuchProposalAttributeTypeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ProposalAttributeType removeImpl(
        ProposalAttributeType proposalAttributeType) throws SystemException {
        proposalAttributeType = toUnwrappedModel(proposalAttributeType);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, proposalAttributeType);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(proposalAttributeType);

        return proposalAttributeType;
    }

    @Override
    public ProposalAttributeType updateImpl(
        com.ext.portlet.model.ProposalAttributeType proposalAttributeType,
        boolean merge) throws SystemException {
        proposalAttributeType = toUnwrappedModel(proposalAttributeType);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, proposalAttributeType, merge);

            proposalAttributeType.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        EntityCacheUtil.putResult(ProposalAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
            ProposalAttributeTypeImpl.class,
            proposalAttributeType.getPrimaryKey(), proposalAttributeType);

        return proposalAttributeType;
    }

    protected ProposalAttributeType toUnwrappedModel(
        ProposalAttributeType proposalAttributeType) {
        if (proposalAttributeType instanceof ProposalAttributeTypeImpl) {
            return proposalAttributeType;
        }

        ProposalAttributeTypeImpl proposalAttributeTypeImpl = new ProposalAttributeTypeImpl();

        proposalAttributeTypeImpl.setNew(proposalAttributeType.isNew());
        proposalAttributeTypeImpl.setPrimaryKey(proposalAttributeType.getPrimaryKey());

        proposalAttributeTypeImpl.setName(proposalAttributeType.getName());
        proposalAttributeTypeImpl.setVisibleInVersionHistory(proposalAttributeType.isVisibleInVersionHistory());
        proposalAttributeTypeImpl.setCopyOnPromote(proposalAttributeType.isCopyOnPromote());

        return proposalAttributeTypeImpl;
    }

    /**
     * Returns the proposal attribute type with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the proposal attribute type
     * @return the proposal attribute type
     * @throws com.liferay.portal.NoSuchModelException if a proposal attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalAttributeType findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey((String) primaryKey);
    }

    /**
     * Returns the proposal attribute type with the primary key or throws a {@link com.ext.portlet.NoSuchProposalAttributeTypeException} if it could not be found.
     *
     * @param name the primary key of the proposal attribute type
     * @return the proposal attribute type
     * @throws com.ext.portlet.NoSuchProposalAttributeTypeException if a proposal attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalAttributeType findByPrimaryKey(String name)
        throws NoSuchProposalAttributeTypeException, SystemException {
        ProposalAttributeType proposalAttributeType = fetchByPrimaryKey(name);

        if (proposalAttributeType == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + name);
            }

            throw new NoSuchProposalAttributeTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                name);
        }

        return proposalAttributeType;
    }

    /**
     * Returns the proposal attribute type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the proposal attribute type
     * @return the proposal attribute type, or <code>null</code> if a proposal attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ProposalAttributeType fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey((String) primaryKey);
    }

    /**
     * Returns the proposal attribute type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param name the primary key of the proposal attribute type
     * @return the proposal attribute type, or <code>null</code> if a proposal attribute type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ProposalAttributeType fetchByPrimaryKey(String name)
        throws SystemException {
        ProposalAttributeType proposalAttributeType = (ProposalAttributeType) EntityCacheUtil.getResult(ProposalAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
                ProposalAttributeTypeImpl.class, name);

        if (proposalAttributeType == _nullProposalAttributeType) {
            return null;
        }

        if (proposalAttributeType == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                proposalAttributeType = (ProposalAttributeType) session.get(ProposalAttributeTypeImpl.class,
                        name);
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (proposalAttributeType != null) {
                    cacheResult(proposalAttributeType);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(ProposalAttributeTypeModelImpl.ENTITY_CACHE_ENABLED,
                        ProposalAttributeTypeImpl.class, name,
                        _nullProposalAttributeType);
                }

                closeSession(session);
            }
        }

        return proposalAttributeType;
    }

    /**
     * Returns all the proposal attribute types.
     *
     * @return the proposal attribute types
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalAttributeType> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the proposal attribute types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of proposal attribute types
     * @param end the upper bound of the range of proposal attribute types (not inclusive)
     * @return the range of proposal attribute types
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalAttributeType> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the proposal attribute types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of proposal attribute types
     * @param end the upper bound of the range of proposal attribute types (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of proposal attribute types
     * @throws SystemException if a system exception occurred
     */
    public List<ProposalAttributeType> findAll(int start, int end,
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

        List<ProposalAttributeType> list = (List<ProposalAttributeType>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PROPOSALATTRIBUTETYPE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PROPOSALATTRIBUTETYPE;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<ProposalAttributeType>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ProposalAttributeType>) QueryUtil.list(q,
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
     * Removes all the proposal attribute types from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (ProposalAttributeType proposalAttributeType : findAll()) {
            remove(proposalAttributeType);
        }
    }

    /**
     * Returns the number of proposal attribute types.
     *
     * @return the number of proposal attribute types
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_PROPOSALATTRIBUTETYPE);

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
     * Initializes the proposal attribute type persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ProposalAttributeType")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ProposalAttributeType>> listenersList = new ArrayList<ModelListener<ProposalAttributeType>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ProposalAttributeType>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ProposalAttributeTypeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
