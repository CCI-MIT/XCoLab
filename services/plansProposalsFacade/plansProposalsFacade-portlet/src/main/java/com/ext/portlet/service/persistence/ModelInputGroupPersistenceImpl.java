package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchModelInputGroupException;
import com.ext.portlet.model.ModelInputGroup;
import com.ext.portlet.model.impl.ModelInputGroupImpl;
import com.ext.portlet.model.impl.ModelInputGroupModelImpl;
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
 * The persistence implementation for the model input group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelInputGroupPersistence
 * @see ModelInputGroupUtil
 * @generated
 */
public class ModelInputGroupPersistenceImpl extends BasePersistenceImpl<ModelInputGroup>
    implements ModelInputGroupPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ModelInputGroupUtil} to access the model input group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ModelInputGroupImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTMODELID =
        new FinderPath(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputGroupModelImpl.FINDER_CACHE_ENABLED,
            ModelInputGroupImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByparentModelId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTMODELID =
        new FinderPath(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputGroupModelImpl.FINDER_CACHE_ENABLED,
            ModelInputGroupImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByparentModelId",
            new String[] { Long.class.getName() },
            ModelInputGroupModelImpl.PARENTGROUPPK_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PARENTMODELID = new FinderPath(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByparentModelId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MODELID = new FinderPath(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputGroupModelImpl.FINDER_CACHE_ENABLED,
            ModelInputGroupImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByModelId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELID =
        new FinderPath(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputGroupModelImpl.FINDER_CACHE_ENABLED,
            ModelInputGroupImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByModelId",
            new String[] { Long.class.getName() },
            ModelInputGroupModelImpl.MODELID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_MODELID = new FinderPath(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByModelId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputGroupModelImpl.FINDER_CACHE_ENABLED,
            ModelInputGroupImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputGroupModelImpl.FINDER_CACHE_ENABLED,
            ModelInputGroupImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputGroupModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_MODELINPUTGROUP = "SELECT modelInputGroup FROM ModelInputGroup modelInputGroup";
    private static final String _SQL_SELECT_MODELINPUTGROUP_WHERE = "SELECT modelInputGroup FROM ModelInputGroup modelInputGroup WHERE ";
    private static final String _SQL_COUNT_MODELINPUTGROUP = "SELECT COUNT(modelInputGroup) FROM ModelInputGroup modelInputGroup";
    private static final String _SQL_COUNT_MODELINPUTGROUP_WHERE = "SELECT COUNT(modelInputGroup) FROM ModelInputGroup modelInputGroup WHERE ";
    private static final String _FINDER_COLUMN_PARENTMODELID_PARENTGROUPPK_2 = "modelInputGroup.parentGroupPK = ?";
    private static final String _FINDER_COLUMN_MODELID_MODELID_2 = "modelInputGroup.modelId = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "modelInputGroup.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ModelInputGroup exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ModelInputGroup exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ModelInputGroupPersistenceImpl.class);
    private static ModelInputGroup _nullModelInputGroup = new ModelInputGroupImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ModelInputGroup> toCacheModel() {
                return _nullModelInputGroupCacheModel;
            }
        };

    private static CacheModel<ModelInputGroup> _nullModelInputGroupCacheModel = new CacheModel<ModelInputGroup>() {
            public ModelInputGroup toEntityModel() {
                return _nullModelInputGroup;
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
     * Caches the model input group in the entity cache if it is enabled.
     *
     * @param modelInputGroup the model input group
     */
    public void cacheResult(ModelInputGroup modelInputGroup) {
        EntityCacheUtil.putResult(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputGroupImpl.class, modelInputGroup.getPrimaryKey(),
            modelInputGroup);

        modelInputGroup.resetOriginalValues();
    }

    /**
     * Caches the model input groups in the entity cache if it is enabled.
     *
     * @param modelInputGroups the model input groups
     */
    public void cacheResult(List<ModelInputGroup> modelInputGroups) {
        for (ModelInputGroup modelInputGroup : modelInputGroups) {
            if (EntityCacheUtil.getResult(
                        ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
                        ModelInputGroupImpl.class,
                        modelInputGroup.getPrimaryKey()) == null) {
                cacheResult(modelInputGroup);
            } else {
                modelInputGroup.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all model input groups.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ModelInputGroupImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ModelInputGroupImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the model input group.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ModelInputGroup modelInputGroup) {
        EntityCacheUtil.removeResult(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputGroupImpl.class, modelInputGroup.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<ModelInputGroup> modelInputGroups) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ModelInputGroup modelInputGroup : modelInputGroups) {
            EntityCacheUtil.removeResult(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
                ModelInputGroupImpl.class, modelInputGroup.getPrimaryKey());
        }
    }

    /**
     * Creates a new model input group with the primary key. Does not add the model input group to the database.
     *
     * @param modelInputGroupPK the primary key for the new model input group
     * @return the new model input group
     */
    public ModelInputGroup create(long modelInputGroupPK) {
        ModelInputGroup modelInputGroup = new ModelInputGroupImpl();

        modelInputGroup.setNew(true);
        modelInputGroup.setPrimaryKey(modelInputGroupPK);

        return modelInputGroup;
    }

    /**
     * Removes the model input group with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param modelInputGroupPK the primary key of the model input group
     * @return the model input group that was removed
     * @throws com.ext.portlet.NoSuchModelInputGroupException if a model input group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputGroup remove(long modelInputGroupPK)
        throws NoSuchModelInputGroupException, SystemException {
        return remove(Long.valueOf(modelInputGroupPK));
    }

    /**
     * Removes the model input group with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the model input group
     * @return the model input group that was removed
     * @throws com.ext.portlet.NoSuchModelInputGroupException if a model input group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelInputGroup remove(Serializable primaryKey)
        throws NoSuchModelInputGroupException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ModelInputGroup modelInputGroup = (ModelInputGroup) session.get(ModelInputGroupImpl.class,
                    primaryKey);

            if (modelInputGroup == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchModelInputGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(modelInputGroup);
        } catch (NoSuchModelInputGroupException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ModelInputGroup removeImpl(ModelInputGroup modelInputGroup)
        throws SystemException {
        modelInputGroup = toUnwrappedModel(modelInputGroup);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, modelInputGroup);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(modelInputGroup);

        return modelInputGroup;
    }

    @Override
    public ModelInputGroup updateImpl(
        com.ext.portlet.model.ModelInputGroup modelInputGroup, boolean merge)
        throws SystemException {
        modelInputGroup = toUnwrappedModel(modelInputGroup);

        boolean isNew = modelInputGroup.isNew();

        ModelInputGroupModelImpl modelInputGroupModelImpl = (ModelInputGroupModelImpl) modelInputGroup;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, modelInputGroup, merge);

            modelInputGroup.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ModelInputGroupModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((modelInputGroupModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTMODELID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(modelInputGroupModelImpl.getOriginalParentGroupPK())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTMODELID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTMODELID,
                    args);

                args = new Object[] {
                        Long.valueOf(modelInputGroupModelImpl.getParentGroupPK())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PARENTMODELID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTMODELID,
                    args);
            }

            if ((modelInputGroupModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(modelInputGroupModelImpl.getOriginalModelId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODELID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELID,
                    args);

                args = new Object[] {
                        Long.valueOf(modelInputGroupModelImpl.getModelId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODELID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELID,
                    args);
            }
        }

        EntityCacheUtil.putResult(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputGroupImpl.class, modelInputGroup.getPrimaryKey(),
            modelInputGroup);

        return modelInputGroup;
    }

    protected ModelInputGroup toUnwrappedModel(ModelInputGroup modelInputGroup) {
        if (modelInputGroup instanceof ModelInputGroupImpl) {
            return modelInputGroup;
        }

        ModelInputGroupImpl modelInputGroupImpl = new ModelInputGroupImpl();

        modelInputGroupImpl.setNew(modelInputGroup.isNew());
        modelInputGroupImpl.setPrimaryKey(modelInputGroup.getPrimaryKey());

        modelInputGroupImpl.setModelInputGroupPK(modelInputGroup.getModelInputGroupPK());
        modelInputGroupImpl.setModelId(modelInputGroup.getModelId());
        modelInputGroupImpl.setNameAndDescriptionMetaDataId(modelInputGroup.getNameAndDescriptionMetaDataId());
        modelInputGroupImpl.setName(modelInputGroup.getName());
        modelInputGroupImpl.setDescription(modelInputGroup.getDescription());
        modelInputGroupImpl.setDisplayItemOrder(modelInputGroup.getDisplayItemOrder());
        modelInputGroupImpl.setGroupType(modelInputGroup.getGroupType());
        modelInputGroupImpl.setParentGroupPK(modelInputGroup.getParentGroupPK());

        return modelInputGroupImpl;
    }

    /**
     * Returns the model input group with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the model input group
     * @return the model input group
     * @throws com.liferay.portal.NoSuchModelException if a model input group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelInputGroup findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the model input group with the primary key or throws a {@link com.ext.portlet.NoSuchModelInputGroupException} if it could not be found.
     *
     * @param modelInputGroupPK the primary key of the model input group
     * @return the model input group
     * @throws com.ext.portlet.NoSuchModelInputGroupException if a model input group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputGroup findByPrimaryKey(long modelInputGroupPK)
        throws NoSuchModelInputGroupException, SystemException {
        ModelInputGroup modelInputGroup = fetchByPrimaryKey(modelInputGroupPK);

        if (modelInputGroup == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + modelInputGroupPK);
            }

            throw new NoSuchModelInputGroupException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                modelInputGroupPK);
        }

        return modelInputGroup;
    }

    /**
     * Returns the model input group with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the model input group
     * @return the model input group, or <code>null</code> if a model input group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelInputGroup fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the model input group with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param modelInputGroupPK the primary key of the model input group
     * @return the model input group, or <code>null</code> if a model input group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputGroup fetchByPrimaryKey(long modelInputGroupPK)
        throws SystemException {
        ModelInputGroup modelInputGroup = (ModelInputGroup) EntityCacheUtil.getResult(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
                ModelInputGroupImpl.class, modelInputGroupPK);

        if (modelInputGroup == _nullModelInputGroup) {
            return null;
        }

        if (modelInputGroup == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                modelInputGroup = (ModelInputGroup) session.get(ModelInputGroupImpl.class,
                        Long.valueOf(modelInputGroupPK));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (modelInputGroup != null) {
                    cacheResult(modelInputGroup);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(ModelInputGroupModelImpl.ENTITY_CACHE_ENABLED,
                        ModelInputGroupImpl.class, modelInputGroupPK,
                        _nullModelInputGroup);
                }

                closeSession(session);
            }
        }

        return modelInputGroup;
    }

    /**
     * Returns all the model input groups where parentGroupPK = &#63;.
     *
     * @param parentGroupPK the parent group p k
     * @return the matching model input groups
     * @throws SystemException if a system exception occurred
     */
    public List<ModelInputGroup> findByparentModelId(long parentGroupPK)
        throws SystemException {
        return findByparentModelId(parentGroupPK, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the model input groups where parentGroupPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param parentGroupPK the parent group p k
     * @param start the lower bound of the range of model input groups
     * @param end the upper bound of the range of model input groups (not inclusive)
     * @return the range of matching model input groups
     * @throws SystemException if a system exception occurred
     */
    public List<ModelInputGroup> findByparentModelId(long parentGroupPK,
        int start, int end) throws SystemException {
        return findByparentModelId(parentGroupPK, start, end, null);
    }

    /**
     * Returns an ordered range of all the model input groups where parentGroupPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param parentGroupPK the parent group p k
     * @param start the lower bound of the range of model input groups
     * @param end the upper bound of the range of model input groups (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching model input groups
     * @throws SystemException if a system exception occurred
     */
    public List<ModelInputGroup> findByparentModelId(long parentGroupPK,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PARENTMODELID;
            finderArgs = new Object[] { parentGroupPK };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PARENTMODELID;
            finderArgs = new Object[] {
                    parentGroupPK,
                    
                    start, end, orderByComparator
                };
        }

        List<ModelInputGroup> list = (List<ModelInputGroup>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_MODELINPUTGROUP_WHERE);

            query.append(_FINDER_COLUMN_PARENTMODELID_PARENTGROUPPK_2);

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

                qPos.add(parentGroupPK);

                list = (List<ModelInputGroup>) QueryUtil.list(q, getDialect(),
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
     * Returns the first model input group in the ordered set where parentGroupPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param parentGroupPK the parent group p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching model input group
     * @throws com.ext.portlet.NoSuchModelInputGroupException if a matching model input group could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputGroup findByparentModelId_First(long parentGroupPK,
        OrderByComparator orderByComparator)
        throws NoSuchModelInputGroupException, SystemException {
        List<ModelInputGroup> list = findByparentModelId(parentGroupPK, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("parentGroupPK=");
            msg.append(parentGroupPK);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelInputGroupException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last model input group in the ordered set where parentGroupPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param parentGroupPK the parent group p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching model input group
     * @throws com.ext.portlet.NoSuchModelInputGroupException if a matching model input group could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputGroup findByparentModelId_Last(long parentGroupPK,
        OrderByComparator orderByComparator)
        throws NoSuchModelInputGroupException, SystemException {
        int count = countByparentModelId(parentGroupPK);

        List<ModelInputGroup> list = findByparentModelId(parentGroupPK,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("parentGroupPK=");
            msg.append(parentGroupPK);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelInputGroupException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the model input groups before and after the current model input group in the ordered set where parentGroupPK = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param modelInputGroupPK the primary key of the current model input group
     * @param parentGroupPK the parent group p k
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next model input group
     * @throws com.ext.portlet.NoSuchModelInputGroupException if a model input group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputGroup[] findByparentModelId_PrevAndNext(
        long modelInputGroupPK, long parentGroupPK,
        OrderByComparator orderByComparator)
        throws NoSuchModelInputGroupException, SystemException {
        ModelInputGroup modelInputGroup = findByPrimaryKey(modelInputGroupPK);

        Session session = null;

        try {
            session = openSession();

            ModelInputGroup[] array = new ModelInputGroupImpl[3];

            array[0] = getByparentModelId_PrevAndNext(session, modelInputGroup,
                    parentGroupPK, orderByComparator, true);

            array[1] = modelInputGroup;

            array[2] = getByparentModelId_PrevAndNext(session, modelInputGroup,
                    parentGroupPK, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ModelInputGroup getByparentModelId_PrevAndNext(Session session,
        ModelInputGroup modelInputGroup, long parentGroupPK,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_MODELINPUTGROUP_WHERE);

        query.append(_FINDER_COLUMN_PARENTMODELID_PARENTGROUPPK_2);

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

        qPos.add(parentGroupPK);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(modelInputGroup);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ModelInputGroup> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the model input groups where modelId = &#63;.
     *
     * @param modelId the model ID
     * @return the matching model input groups
     * @throws SystemException if a system exception occurred
     */
    public List<ModelInputGroup> findByModelId(long modelId)
        throws SystemException {
        return findByModelId(modelId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the model input groups where modelId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param modelId the model ID
     * @param start the lower bound of the range of model input groups
     * @param end the upper bound of the range of model input groups (not inclusive)
     * @return the range of matching model input groups
     * @throws SystemException if a system exception occurred
     */
    public List<ModelInputGroup> findByModelId(long modelId, int start, int end)
        throws SystemException {
        return findByModelId(modelId, start, end, null);
    }

    /**
     * Returns an ordered range of all the model input groups where modelId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param modelId the model ID
     * @param start the lower bound of the range of model input groups
     * @param end the upper bound of the range of model input groups (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching model input groups
     * @throws SystemException if a system exception occurred
     */
    public List<ModelInputGroup> findByModelId(long modelId, int start,
        int end, OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELID;
            finderArgs = new Object[] { modelId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MODELID;
            finderArgs = new Object[] { modelId, start, end, orderByComparator };
        }

        List<ModelInputGroup> list = (List<ModelInputGroup>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_MODELINPUTGROUP_WHERE);

            query.append(_FINDER_COLUMN_MODELID_MODELID_2);

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

                qPos.add(modelId);

                list = (List<ModelInputGroup>) QueryUtil.list(q, getDialect(),
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
     * Returns the first model input group in the ordered set where modelId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param modelId the model ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching model input group
     * @throws com.ext.portlet.NoSuchModelInputGroupException if a matching model input group could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputGroup findByModelId_First(long modelId,
        OrderByComparator orderByComparator)
        throws NoSuchModelInputGroupException, SystemException {
        List<ModelInputGroup> list = findByModelId(modelId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("modelId=");
            msg.append(modelId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelInputGroupException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last model input group in the ordered set where modelId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param modelId the model ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching model input group
     * @throws com.ext.portlet.NoSuchModelInputGroupException if a matching model input group could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputGroup findByModelId_Last(long modelId,
        OrderByComparator orderByComparator)
        throws NoSuchModelInputGroupException, SystemException {
        int count = countByModelId(modelId);

        List<ModelInputGroup> list = findByModelId(modelId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("modelId=");
            msg.append(modelId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelInputGroupException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the model input groups before and after the current model input group in the ordered set where modelId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param modelInputGroupPK the primary key of the current model input group
     * @param modelId the model ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next model input group
     * @throws com.ext.portlet.NoSuchModelInputGroupException if a model input group with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputGroup[] findByModelId_PrevAndNext(long modelInputGroupPK,
        long modelId, OrderByComparator orderByComparator)
        throws NoSuchModelInputGroupException, SystemException {
        ModelInputGroup modelInputGroup = findByPrimaryKey(modelInputGroupPK);

        Session session = null;

        try {
            session = openSession();

            ModelInputGroup[] array = new ModelInputGroupImpl[3];

            array[0] = getByModelId_PrevAndNext(session, modelInputGroup,
                    modelId, orderByComparator, true);

            array[1] = modelInputGroup;

            array[2] = getByModelId_PrevAndNext(session, modelInputGroup,
                    modelId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ModelInputGroup getByModelId_PrevAndNext(Session session,
        ModelInputGroup modelInputGroup, long modelId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_MODELINPUTGROUP_WHERE);

        query.append(_FINDER_COLUMN_MODELID_MODELID_2);

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

        qPos.add(modelId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(modelInputGroup);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ModelInputGroup> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the model input groups.
     *
     * @return the model input groups
     * @throws SystemException if a system exception occurred
     */
    public List<ModelInputGroup> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the model input groups.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of model input groups
     * @param end the upper bound of the range of model input groups (not inclusive)
     * @return the range of model input groups
     * @throws SystemException if a system exception occurred
     */
    public List<ModelInputGroup> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the model input groups.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of model input groups
     * @param end the upper bound of the range of model input groups (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of model input groups
     * @throws SystemException if a system exception occurred
     */
    public List<ModelInputGroup> findAll(int start, int end,
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

        List<ModelInputGroup> list = (List<ModelInputGroup>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MODELINPUTGROUP);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MODELINPUTGROUP;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<ModelInputGroup>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ModelInputGroup>) QueryUtil.list(q,
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
     * Removes all the model input groups where parentGroupPK = &#63; from the database.
     *
     * @param parentGroupPK the parent group p k
     * @throws SystemException if a system exception occurred
     */
    public void removeByparentModelId(long parentGroupPK)
        throws SystemException {
        for (ModelInputGroup modelInputGroup : findByparentModelId(
                parentGroupPK)) {
            remove(modelInputGroup);
        }
    }

    /**
     * Removes all the model input groups where modelId = &#63; from the database.
     *
     * @param modelId the model ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByModelId(long modelId) throws SystemException {
        for (ModelInputGroup modelInputGroup : findByModelId(modelId)) {
            remove(modelInputGroup);
        }
    }

    /**
     * Removes all the model input groups from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (ModelInputGroup modelInputGroup : findAll()) {
            remove(modelInputGroup);
        }
    }

    /**
     * Returns the number of model input groups where parentGroupPK = &#63;.
     *
     * @param parentGroupPK the parent group p k
     * @return the number of matching model input groups
     * @throws SystemException if a system exception occurred
     */
    public int countByparentModelId(long parentGroupPK)
        throws SystemException {
        Object[] finderArgs = new Object[] { parentGroupPK };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PARENTMODELID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MODELINPUTGROUP_WHERE);

            query.append(_FINDER_COLUMN_PARENTMODELID_PARENTGROUPPK_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(parentGroupPK);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PARENTMODELID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of model input groups where modelId = &#63;.
     *
     * @param modelId the model ID
     * @return the number of matching model input groups
     * @throws SystemException if a system exception occurred
     */
    public int countByModelId(long modelId) throws SystemException {
        Object[] finderArgs = new Object[] { modelId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MODELID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MODELINPUTGROUP_WHERE);

            query.append(_FINDER_COLUMN_MODELID_MODELID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(modelId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MODELID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of model input groups.
     *
     * @return the number of model input groups
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_MODELINPUTGROUP);

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
     * Initializes the model input group persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ModelInputGroup")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ModelInputGroup>> listenersList = new ArrayList<ModelListener<ModelInputGroup>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ModelInputGroup>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ModelInputGroupImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
