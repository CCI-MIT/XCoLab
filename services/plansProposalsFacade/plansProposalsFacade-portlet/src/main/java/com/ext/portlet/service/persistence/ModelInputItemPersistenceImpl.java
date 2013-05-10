package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchModelInputItemException;
import com.ext.portlet.model.ModelInputItem;
import com.ext.portlet.model.impl.ModelInputItemImpl;
import com.ext.portlet.model.impl.ModelInputItemModelImpl;
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
 * The persistence implementation for the model input item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelInputItemPersistence
 * @see ModelInputItemUtil
 * @generated
 */
public class ModelInputItemPersistenceImpl extends BasePersistenceImpl<ModelInputItem>
    implements ModelInputItemPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link ModelInputItemUtil} to access the model input item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = ModelInputItemImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MODELGROUPID =
        new FinderPath(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemModelImpl.FINDER_CACHE_ENABLED,
            ModelInputItemImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByModelGroupId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELGROUPID =
        new FinderPath(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemModelImpl.FINDER_CACHE_ENABLED,
            ModelInputItemImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByModelGroupId",
            new String[] { Long.class.getName() },
            ModelInputItemModelImpl.MODELGROUPID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_MODELGROUPID = new FinderPath(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByModelGroupId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_MODELINPUTID = new FinderPath(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemModelImpl.FINDER_CACHE_ENABLED,
            ModelInputItemImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByModelInputId", new String[] { Long.class.getName() },
            ModelInputItemModelImpl.MODELINPUTITEMID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_MODELINPUTID = new FinderPath(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByModelInputId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MODELID = new FinderPath(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemModelImpl.FINDER_CACHE_ENABLED,
            ModelInputItemImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByModelId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELID =
        new FinderPath(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemModelImpl.FINDER_CACHE_ENABLED,
            ModelInputItemImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByModelId",
            new String[] { Long.class.getName() },
            ModelInputItemModelImpl.MODELID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_MODELID = new FinderPath(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByModelId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_FETCH_BY_MODELIDMODELINPUTID = new FinderPath(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemModelImpl.FINDER_CACHE_ENABLED,
            ModelInputItemImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByModelIdModelInputId",
            new String[] { Long.class.getName(), Long.class.getName() },
            ModelInputItemModelImpl.MODELID_COLUMN_BITMASK |
            ModelInputItemModelImpl.MODELINPUTITEMID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_MODELIDMODELINPUTID = new FinderPath(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByModelIdModelInputId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemModelImpl.FINDER_CACHE_ENABLED,
            ModelInputItemImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemModelImpl.FINDER_CACHE_ENABLED,
            ModelInputItemImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_MODELINPUTITEM = "SELECT modelInputItem FROM ModelInputItem modelInputItem";
    private static final String _SQL_SELECT_MODELINPUTITEM_WHERE = "SELECT modelInputItem FROM ModelInputItem modelInputItem WHERE ";
    private static final String _SQL_COUNT_MODELINPUTITEM = "SELECT COUNT(modelInputItem) FROM ModelInputItem modelInputItem";
    private static final String _SQL_COUNT_MODELINPUTITEM_WHERE = "SELECT COUNT(modelInputItem) FROM ModelInputItem modelInputItem WHERE ";
    private static final String _FINDER_COLUMN_MODELGROUPID_MODELGROUPID_2 = "modelInputItem.modelGroupId = ?";
    private static final String _FINDER_COLUMN_MODELINPUTID_MODELINPUTITEMID_2 = "modelInputItem.modelInputItemID = ?";
    private static final String _FINDER_COLUMN_MODELID_MODELID_2 = "modelInputItem.modelId = ?";
    private static final String _FINDER_COLUMN_MODELIDMODELINPUTID_MODELID_2 = "modelInputItem.modelId = ? AND ";
    private static final String _FINDER_COLUMN_MODELIDMODELINPUTID_MODELINPUTITEMID_2 =
        "modelInputItem.modelInputItemID = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "modelInputItem.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ModelInputItem exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ModelInputItem exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(ModelInputItemPersistenceImpl.class);
    private static ModelInputItem _nullModelInputItem = new ModelInputItemImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<ModelInputItem> toCacheModel() {
                return _nullModelInputItemCacheModel;
            }
        };

    private static CacheModel<ModelInputItem> _nullModelInputItemCacheModel = new CacheModel<ModelInputItem>() {
            public ModelInputItem toEntityModel() {
                return _nullModelInputItem;
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
     * Caches the model input item in the entity cache if it is enabled.
     *
     * @param modelInputItem the model input item
     */
    public void cacheResult(ModelInputItem modelInputItem) {
        EntityCacheUtil.putResult(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemImpl.class, modelInputItem.getPrimaryKey(),
            modelInputItem);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELINPUTID,
            new Object[] { Long.valueOf(modelInputItem.getModelInputItemID()) },
            modelInputItem);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELIDMODELINPUTID,
            new Object[] {
                Long.valueOf(modelInputItem.getModelId()),
                Long.valueOf(modelInputItem.getModelInputItemID())
            }, modelInputItem);

        modelInputItem.resetOriginalValues();
    }

    /**
     * Caches the model input items in the entity cache if it is enabled.
     *
     * @param modelInputItems the model input items
     */
    public void cacheResult(List<ModelInputItem> modelInputItems) {
        for (ModelInputItem modelInputItem : modelInputItems) {
            if (EntityCacheUtil.getResult(
                        ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
                        ModelInputItemImpl.class, modelInputItem.getPrimaryKey()) == null) {
                cacheResult(modelInputItem);
            } else {
                modelInputItem.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all model input items.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(ModelInputItemImpl.class.getName());
        }

        EntityCacheUtil.clearCache(ModelInputItemImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the model input item.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(ModelInputItem modelInputItem) {
        EntityCacheUtil.removeResult(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemImpl.class, modelInputItem.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(modelInputItem);
    }

    @Override
    public void clearCache(List<ModelInputItem> modelInputItems) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (ModelInputItem modelInputItem : modelInputItems) {
            EntityCacheUtil.removeResult(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
                ModelInputItemImpl.class, modelInputItem.getPrimaryKey());

            clearUniqueFindersCache(modelInputItem);
        }
    }

    protected void clearUniqueFindersCache(ModelInputItem modelInputItem) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MODELINPUTID,
            new Object[] { Long.valueOf(modelInputItem.getModelInputItemID()) });

        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MODELIDMODELINPUTID,
            new Object[] {
                Long.valueOf(modelInputItem.getModelId()),
                Long.valueOf(modelInputItem.getModelInputItemID())
            });
    }

    /**
     * Creates a new model input item with the primary key. Does not add the model input item to the database.
     *
     * @param modelInputItemPK the primary key for the new model input item
     * @return the new model input item
     */
    public ModelInputItem create(long modelInputItemPK) {
        ModelInputItem modelInputItem = new ModelInputItemImpl();

        modelInputItem.setNew(true);
        modelInputItem.setPrimaryKey(modelInputItemPK);

        return modelInputItem;
    }

    /**
     * Removes the model input item with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param modelInputItemPK the primary key of the model input item
     * @return the model input item that was removed
     * @throws com.ext.portlet.NoSuchModelInputItemException if a model input item with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputItem remove(long modelInputItemPK)
        throws NoSuchModelInputItemException, SystemException {
        return remove(Long.valueOf(modelInputItemPK));
    }

    /**
     * Removes the model input item with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the model input item
     * @return the model input item that was removed
     * @throws com.ext.portlet.NoSuchModelInputItemException if a model input item with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelInputItem remove(Serializable primaryKey)
        throws NoSuchModelInputItemException, SystemException {
        Session session = null;

        try {
            session = openSession();

            ModelInputItem modelInputItem = (ModelInputItem) session.get(ModelInputItemImpl.class,
                    primaryKey);

            if (modelInputItem == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchModelInputItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(modelInputItem);
        } catch (NoSuchModelInputItemException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected ModelInputItem removeImpl(ModelInputItem modelInputItem)
        throws SystemException {
        modelInputItem = toUnwrappedModel(modelInputItem);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, modelInputItem);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(modelInputItem);

        return modelInputItem;
    }

    @Override
    public ModelInputItem updateImpl(
        com.ext.portlet.model.ModelInputItem modelInputItem, boolean merge)
        throws SystemException {
        modelInputItem = toUnwrappedModel(modelInputItem);

        boolean isNew = modelInputItem.isNew();

        ModelInputItemModelImpl modelInputItemModelImpl = (ModelInputItemModelImpl) modelInputItem;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, modelInputItem, merge);

            modelInputItem.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !ModelInputItemModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((modelInputItemModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELGROUPID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(modelInputItemModelImpl.getOriginalModelGroupId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODELGROUPID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELGROUPID,
                    args);

                args = new Object[] {
                        Long.valueOf(modelInputItemModelImpl.getModelGroupId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODELGROUPID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELGROUPID,
                    args);
            }

            if ((modelInputItemModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(modelInputItemModelImpl.getOriginalModelId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODELID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELID,
                    args);

                args = new Object[] {
                        Long.valueOf(modelInputItemModelImpl.getModelId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODELID, args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELID,
                    args);
            }
        }

        EntityCacheUtil.putResult(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
            ModelInputItemImpl.class, modelInputItem.getPrimaryKey(),
            modelInputItem);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELINPUTID,
                new Object[] { Long.valueOf(
                        modelInputItem.getModelInputItemID()) }, modelInputItem);

            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELIDMODELINPUTID,
                new Object[] {
                    Long.valueOf(modelInputItem.getModelId()),
                    Long.valueOf(modelInputItem.getModelInputItemID())
                }, modelInputItem);
        } else {
            if ((modelInputItemModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_MODELINPUTID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(modelInputItemModelImpl.getOriginalModelInputItemID())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODELINPUTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MODELINPUTID,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELINPUTID,
                    new Object[] {
                        Long.valueOf(modelInputItem.getModelInputItemID())
                    }, modelInputItem);
            }

            if ((modelInputItemModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_MODELIDMODELINPUTID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(modelInputItemModelImpl.getOriginalModelId()),
                        Long.valueOf(modelInputItemModelImpl.getOriginalModelInputItemID())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MODELIDMODELINPUTID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MODELIDMODELINPUTID,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELIDMODELINPUTID,
                    new Object[] {
                        Long.valueOf(modelInputItem.getModelId()),
                        Long.valueOf(modelInputItem.getModelInputItemID())
                    }, modelInputItem);
            }
        }

        return modelInputItem;
    }

    protected ModelInputItem toUnwrappedModel(ModelInputItem modelInputItem) {
        if (modelInputItem instanceof ModelInputItemImpl) {
            return modelInputItem;
        }

        ModelInputItemImpl modelInputItemImpl = new ModelInputItemImpl();

        modelInputItemImpl.setNew(modelInputItem.isNew());
        modelInputItemImpl.setPrimaryKey(modelInputItem.getPrimaryKey());

        modelInputItemImpl.setModelInputItemPK(modelInputItem.getModelInputItemPK());
        modelInputItemImpl.setModelId(modelInputItem.getModelId());
        modelInputItemImpl.setModelInputItemID(modelInputItem.getModelInputItemID());
        modelInputItemImpl.setModelGroupId(modelInputItem.getModelGroupId());
        modelInputItemImpl.setDisplayItemOrder(modelInputItem.getDisplayItemOrder());
        modelInputItemImpl.setType(modelInputItem.getType());
        modelInputItemImpl.setProperties(modelInputItem.getProperties());

        return modelInputItemImpl;
    }

    /**
     * Returns the model input item with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the model input item
     * @return the model input item
     * @throws com.liferay.portal.NoSuchModelException if a model input item with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelInputItem findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the model input item with the primary key or throws a {@link com.ext.portlet.NoSuchModelInputItemException} if it could not be found.
     *
     * @param modelInputItemPK the primary key of the model input item
     * @return the model input item
     * @throws com.ext.portlet.NoSuchModelInputItemException if a model input item with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputItem findByPrimaryKey(long modelInputItemPK)
        throws NoSuchModelInputItemException, SystemException {
        ModelInputItem modelInputItem = fetchByPrimaryKey(modelInputItemPK);

        if (modelInputItem == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + modelInputItemPK);
            }

            throw new NoSuchModelInputItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                modelInputItemPK);
        }

        return modelInputItem;
    }

    /**
     * Returns the model input item with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the model input item
     * @return the model input item, or <code>null</code> if a model input item with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public ModelInputItem fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the model input item with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param modelInputItemPK the primary key of the model input item
     * @return the model input item, or <code>null</code> if a model input item with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputItem fetchByPrimaryKey(long modelInputItemPK)
        throws SystemException {
        ModelInputItem modelInputItem = (ModelInputItem) EntityCacheUtil.getResult(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
                ModelInputItemImpl.class, modelInputItemPK);

        if (modelInputItem == _nullModelInputItem) {
            return null;
        }

        if (modelInputItem == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                modelInputItem = (ModelInputItem) session.get(ModelInputItemImpl.class,
                        Long.valueOf(modelInputItemPK));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (modelInputItem != null) {
                    cacheResult(modelInputItem);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(ModelInputItemModelImpl.ENTITY_CACHE_ENABLED,
                        ModelInputItemImpl.class, modelInputItemPK,
                        _nullModelInputItem);
                }

                closeSession(session);
            }
        }

        return modelInputItem;
    }

    /**
     * Returns all the model input items where modelGroupId = &#63;.
     *
     * @param modelGroupId the model group ID
     * @return the matching model input items
     * @throws SystemException if a system exception occurred
     */
    public List<ModelInputItem> findByModelGroupId(long modelGroupId)
        throws SystemException {
        return findByModelGroupId(modelGroupId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the model input items where modelGroupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param modelGroupId the model group ID
     * @param start the lower bound of the range of model input items
     * @param end the upper bound of the range of model input items (not inclusive)
     * @return the range of matching model input items
     * @throws SystemException if a system exception occurred
     */
    public List<ModelInputItem> findByModelGroupId(long modelGroupId,
        int start, int end) throws SystemException {
        return findByModelGroupId(modelGroupId, start, end, null);
    }

    /**
     * Returns an ordered range of all the model input items where modelGroupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param modelGroupId the model group ID
     * @param start the lower bound of the range of model input items
     * @param end the upper bound of the range of model input items (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching model input items
     * @throws SystemException if a system exception occurred
     */
    public List<ModelInputItem> findByModelGroupId(long modelGroupId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MODELGROUPID;
            finderArgs = new Object[] { modelGroupId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MODELGROUPID;
            finderArgs = new Object[] {
                    modelGroupId,
                    
                    start, end, orderByComparator
                };
        }

        List<ModelInputItem> list = (List<ModelInputItem>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_MODELINPUTITEM_WHERE);

            query.append(_FINDER_COLUMN_MODELGROUPID_MODELGROUPID_2);

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

                qPos.add(modelGroupId);

                list = (List<ModelInputItem>) QueryUtil.list(q, getDialect(),
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
     * Returns the first model input item in the ordered set where modelGroupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param modelGroupId the model group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching model input item
     * @throws com.ext.portlet.NoSuchModelInputItemException if a matching model input item could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputItem findByModelGroupId_First(long modelGroupId,
        OrderByComparator orderByComparator)
        throws NoSuchModelInputItemException, SystemException {
        List<ModelInputItem> list = findByModelGroupId(modelGroupId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("modelGroupId=");
            msg.append(modelGroupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelInputItemException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last model input item in the ordered set where modelGroupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param modelGroupId the model group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching model input item
     * @throws com.ext.portlet.NoSuchModelInputItemException if a matching model input item could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputItem findByModelGroupId_Last(long modelGroupId,
        OrderByComparator orderByComparator)
        throws NoSuchModelInputItemException, SystemException {
        int count = countByModelGroupId(modelGroupId);

        List<ModelInputItem> list = findByModelGroupId(modelGroupId, count - 1,
                count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("modelGroupId=");
            msg.append(modelGroupId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelInputItemException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the model input items before and after the current model input item in the ordered set where modelGroupId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param modelInputItemPK the primary key of the current model input item
     * @param modelGroupId the model group ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next model input item
     * @throws com.ext.portlet.NoSuchModelInputItemException if a model input item with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputItem[] findByModelGroupId_PrevAndNext(
        long modelInputItemPK, long modelGroupId,
        OrderByComparator orderByComparator)
        throws NoSuchModelInputItemException, SystemException {
        ModelInputItem modelInputItem = findByPrimaryKey(modelInputItemPK);

        Session session = null;

        try {
            session = openSession();

            ModelInputItem[] array = new ModelInputItemImpl[3];

            array[0] = getByModelGroupId_PrevAndNext(session, modelInputItem,
                    modelGroupId, orderByComparator, true);

            array[1] = modelInputItem;

            array[2] = getByModelGroupId_PrevAndNext(session, modelInputItem,
                    modelGroupId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ModelInputItem getByModelGroupId_PrevAndNext(Session session,
        ModelInputItem modelInputItem, long modelGroupId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_MODELINPUTITEM_WHERE);

        query.append(_FINDER_COLUMN_MODELGROUPID_MODELGROUPID_2);

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

        qPos.add(modelGroupId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(modelInputItem);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ModelInputItem> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns the model input item where modelInputItemID = &#63; or throws a {@link com.ext.portlet.NoSuchModelInputItemException} if it could not be found.
     *
     * @param modelInputItemID the model input item i d
     * @return the matching model input item
     * @throws com.ext.portlet.NoSuchModelInputItemException if a matching model input item could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputItem findByModelInputId(long modelInputItemID)
        throws NoSuchModelInputItemException, SystemException {
        ModelInputItem modelInputItem = fetchByModelInputId(modelInputItemID);

        if (modelInputItem == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("modelInputItemID=");
            msg.append(modelInputItemID);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchModelInputItemException(msg.toString());
        }

        return modelInputItem;
    }

    /**
     * Returns the model input item where modelInputItemID = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param modelInputItemID the model input item i d
     * @return the matching model input item, or <code>null</code> if a matching model input item could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputItem fetchByModelInputId(long modelInputItemID)
        throws SystemException {
        return fetchByModelInputId(modelInputItemID, true);
    }

    /**
     * Returns the model input item where modelInputItemID = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param modelInputItemID the model input item i d
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching model input item, or <code>null</code> if a matching model input item could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputItem fetchByModelInputId(long modelInputItemID,
        boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { modelInputItemID };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_MODELINPUTID,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_SELECT_MODELINPUTITEM_WHERE);

            query.append(_FINDER_COLUMN_MODELINPUTID_MODELINPUTITEMID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(modelInputItemID);

                List<ModelInputItem> list = q.list();

                result = list;

                ModelInputItem modelInputItem = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELINPUTID,
                        finderArgs, list);
                } else {
                    modelInputItem = list.get(0);

                    cacheResult(modelInputItem);

                    if ((modelInputItem.getModelInputItemID() != modelInputItemID)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELINPUTID,
                            finderArgs, modelInputItem);
                    }
                }

                return modelInputItem;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MODELINPUTID,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (ModelInputItem) result;
            }
        }
    }

    /**
     * Returns all the model input items where modelId = &#63;.
     *
     * @param modelId the model ID
     * @return the matching model input items
     * @throws SystemException if a system exception occurred
     */
    public List<ModelInputItem> findByModelId(long modelId)
        throws SystemException {
        return findByModelId(modelId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the model input items where modelId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param modelId the model ID
     * @param start the lower bound of the range of model input items
     * @param end the upper bound of the range of model input items (not inclusive)
     * @return the range of matching model input items
     * @throws SystemException if a system exception occurred
     */
    public List<ModelInputItem> findByModelId(long modelId, int start, int end)
        throws SystemException {
        return findByModelId(modelId, start, end, null);
    }

    /**
     * Returns an ordered range of all the model input items where modelId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param modelId the model ID
     * @param start the lower bound of the range of model input items
     * @param end the upper bound of the range of model input items (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching model input items
     * @throws SystemException if a system exception occurred
     */
    public List<ModelInputItem> findByModelId(long modelId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
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

        List<ModelInputItem> list = (List<ModelInputItem>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_MODELINPUTITEM_WHERE);

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

                list = (List<ModelInputItem>) QueryUtil.list(q, getDialect(),
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
     * Returns the first model input item in the ordered set where modelId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param modelId the model ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching model input item
     * @throws com.ext.portlet.NoSuchModelInputItemException if a matching model input item could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputItem findByModelId_First(long modelId,
        OrderByComparator orderByComparator)
        throws NoSuchModelInputItemException, SystemException {
        List<ModelInputItem> list = findByModelId(modelId, 0, 1,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("modelId=");
            msg.append(modelId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelInputItemException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last model input item in the ordered set where modelId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param modelId the model ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching model input item
     * @throws com.ext.portlet.NoSuchModelInputItemException if a matching model input item could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputItem findByModelId_Last(long modelId,
        OrderByComparator orderByComparator)
        throws NoSuchModelInputItemException, SystemException {
        int count = countByModelId(modelId);

        List<ModelInputItem> list = findByModelId(modelId, count - 1, count,
                orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("modelId=");
            msg.append(modelId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchModelInputItemException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the model input items before and after the current model input item in the ordered set where modelId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param modelInputItemPK the primary key of the current model input item
     * @param modelId the model ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next model input item
     * @throws com.ext.portlet.NoSuchModelInputItemException if a model input item with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputItem[] findByModelId_PrevAndNext(long modelInputItemPK,
        long modelId, OrderByComparator orderByComparator)
        throws NoSuchModelInputItemException, SystemException {
        ModelInputItem modelInputItem = findByPrimaryKey(modelInputItemPK);

        Session session = null;

        try {
            session = openSession();

            ModelInputItem[] array = new ModelInputItemImpl[3];

            array[0] = getByModelId_PrevAndNext(session, modelInputItem,
                    modelId, orderByComparator, true);

            array[1] = modelInputItem;

            array[2] = getByModelId_PrevAndNext(session, modelInputItem,
                    modelId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected ModelInputItem getByModelId_PrevAndNext(Session session,
        ModelInputItem modelInputItem, long modelId,
        OrderByComparator orderByComparator, boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_MODELINPUTITEM_WHERE);

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
            Object[] values = orderByComparator.getOrderByConditionValues(modelInputItem);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<ModelInputItem> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns the model input item where modelId = &#63; and modelInputItemID = &#63; or throws a {@link com.ext.portlet.NoSuchModelInputItemException} if it could not be found.
     *
     * @param modelId the model ID
     * @param modelInputItemID the model input item i d
     * @return the matching model input item
     * @throws com.ext.portlet.NoSuchModelInputItemException if a matching model input item could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputItem findByModelIdModelInputId(long modelId,
        long modelInputItemID)
        throws NoSuchModelInputItemException, SystemException {
        ModelInputItem modelInputItem = fetchByModelIdModelInputId(modelId,
                modelInputItemID);

        if (modelInputItem == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("modelId=");
            msg.append(modelId);

            msg.append(", modelInputItemID=");
            msg.append(modelInputItemID);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchModelInputItemException(msg.toString());
        }

        return modelInputItem;
    }

    /**
     * Returns the model input item where modelId = &#63; and modelInputItemID = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param modelId the model ID
     * @param modelInputItemID the model input item i d
     * @return the matching model input item, or <code>null</code> if a matching model input item could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputItem fetchByModelIdModelInputId(long modelId,
        long modelInputItemID) throws SystemException {
        return fetchByModelIdModelInputId(modelId, modelInputItemID, true);
    }

    /**
     * Returns the model input item where modelId = &#63; and modelInputItemID = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param modelId the model ID
     * @param modelInputItemID the model input item i d
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching model input item, or <code>null</code> if a matching model input item could not be found
     * @throws SystemException if a system exception occurred
     */
    public ModelInputItem fetchByModelIdModelInputId(long modelId,
        long modelInputItemID, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { modelId, modelInputItemID };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_MODELIDMODELINPUTID,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_MODELINPUTITEM_WHERE);

            query.append(_FINDER_COLUMN_MODELIDMODELINPUTID_MODELID_2);

            query.append(_FINDER_COLUMN_MODELIDMODELINPUTID_MODELINPUTITEMID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(modelId);

                qPos.add(modelInputItemID);

                List<ModelInputItem> list = q.list();

                result = list;

                ModelInputItem modelInputItem = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELIDMODELINPUTID,
                        finderArgs, list);
                } else {
                    modelInputItem = list.get(0);

                    cacheResult(modelInputItem);

                    if ((modelInputItem.getModelId() != modelId) ||
                            (modelInputItem.getModelInputItemID() != modelInputItemID)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_MODELIDMODELINPUTID,
                            finderArgs, modelInputItem);
                    }
                }

                return modelInputItem;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_MODELIDMODELINPUTID,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (ModelInputItem) result;
            }
        }
    }

    /**
     * Returns all the model input items.
     *
     * @return the model input items
     * @throws SystemException if a system exception occurred
     */
    public List<ModelInputItem> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the model input items.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of model input items
     * @param end the upper bound of the range of model input items (not inclusive)
     * @return the range of model input items
     * @throws SystemException if a system exception occurred
     */
    public List<ModelInputItem> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the model input items.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of model input items
     * @param end the upper bound of the range of model input items (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of model input items
     * @throws SystemException if a system exception occurred
     */
    public List<ModelInputItem> findAll(int start, int end,
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

        List<ModelInputItem> list = (List<ModelInputItem>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_MODELINPUTITEM);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_MODELINPUTITEM;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<ModelInputItem>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<ModelInputItem>) QueryUtil.list(q,
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
     * Removes all the model input items where modelGroupId = &#63; from the database.
     *
     * @param modelGroupId the model group ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByModelGroupId(long modelGroupId)
        throws SystemException {
        for (ModelInputItem modelInputItem : findByModelGroupId(modelGroupId)) {
            remove(modelInputItem);
        }
    }

    /**
     * Removes the model input item where modelInputItemID = &#63; from the database.
     *
     * @param modelInputItemID the model input item i d
     * @throws SystemException if a system exception occurred
     */
    public void removeByModelInputId(long modelInputItemID)
        throws NoSuchModelInputItemException, SystemException {
        ModelInputItem modelInputItem = findByModelInputId(modelInputItemID);

        remove(modelInputItem);
    }

    /**
     * Removes all the model input items where modelId = &#63; from the database.
     *
     * @param modelId the model ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByModelId(long modelId) throws SystemException {
        for (ModelInputItem modelInputItem : findByModelId(modelId)) {
            remove(modelInputItem);
        }
    }

    /**
     * Removes the model input item where modelId = &#63; and modelInputItemID = &#63; from the database.
     *
     * @param modelId the model ID
     * @param modelInputItemID the model input item i d
     * @throws SystemException if a system exception occurred
     */
    public void removeByModelIdModelInputId(long modelId, long modelInputItemID)
        throws NoSuchModelInputItemException, SystemException {
        ModelInputItem modelInputItem = findByModelIdModelInputId(modelId,
                modelInputItemID);

        remove(modelInputItem);
    }

    /**
     * Removes all the model input items from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (ModelInputItem modelInputItem : findAll()) {
            remove(modelInputItem);
        }
    }

    /**
     * Returns the number of model input items where modelGroupId = &#63;.
     *
     * @param modelGroupId the model group ID
     * @return the number of matching model input items
     * @throws SystemException if a system exception occurred
     */
    public int countByModelGroupId(long modelGroupId) throws SystemException {
        Object[] finderArgs = new Object[] { modelGroupId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MODELGROUPID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MODELINPUTITEM_WHERE);

            query.append(_FINDER_COLUMN_MODELGROUPID_MODELGROUPID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(modelGroupId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MODELGROUPID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of model input items where modelInputItemID = &#63;.
     *
     * @param modelInputItemID the model input item i d
     * @return the number of matching model input items
     * @throws SystemException if a system exception occurred
     */
    public int countByModelInputId(long modelInputItemID)
        throws SystemException {
        Object[] finderArgs = new Object[] { modelInputItemID };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MODELINPUTID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MODELINPUTITEM_WHERE);

            query.append(_FINDER_COLUMN_MODELINPUTID_MODELINPUTITEMID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(modelInputItemID);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MODELINPUTID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of model input items where modelId = &#63;.
     *
     * @param modelId the model ID
     * @return the number of matching model input items
     * @throws SystemException if a system exception occurred
     */
    public int countByModelId(long modelId) throws SystemException {
        Object[] finderArgs = new Object[] { modelId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MODELID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_MODELINPUTITEM_WHERE);

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
     * Returns the number of model input items where modelId = &#63; and modelInputItemID = &#63;.
     *
     * @param modelId the model ID
     * @param modelInputItemID the model input item i d
     * @return the number of matching model input items
     * @throws SystemException if a system exception occurred
     */
    public int countByModelIdModelInputId(long modelId, long modelInputItemID)
        throws SystemException {
        Object[] finderArgs = new Object[] { modelId, modelInputItemID };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MODELIDMODELINPUTID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_MODELINPUTITEM_WHERE);

            query.append(_FINDER_COLUMN_MODELIDMODELINPUTID_MODELID_2);

            query.append(_FINDER_COLUMN_MODELIDMODELINPUTID_MODELINPUTITEMID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(modelId);

                qPos.add(modelInputItemID);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MODELIDMODELINPUTID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of model input items.
     *
     * @return the number of model input items
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_MODELINPUTITEM);

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
     * Initializes the model input item persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.ModelInputItem")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<ModelInputItem>> listenersList = new ArrayList<ModelListener<ModelInputItem>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<ModelInputItem>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(ModelInputItemImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
