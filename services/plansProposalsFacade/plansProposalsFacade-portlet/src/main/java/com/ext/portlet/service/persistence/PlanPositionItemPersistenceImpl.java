package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPlanPositionItemException;
import com.ext.portlet.model.PlanPositionItem;
import com.ext.portlet.model.impl.PlanPositionItemImpl;
import com.ext.portlet.model.impl.PlanPositionItemModelImpl;
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
 * The persistence implementation for the plan position item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanPositionItemPersistence
 * @see PlanPositionItemUtil
 * @generated
 */
public class PlanPositionItemPersistenceImpl extends BasePersistenceImpl<PlanPositionItem>
    implements PlanPositionItemPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanPositionItemUtil} to access the plan position item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanPositionItemImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_ALLBYPLANPOSITIONSID =
        new FinderPath(PlanPositionItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionItemModelImpl.FINDER_CACHE_ENABLED,
            PlanPositionItemImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findByAllByPlanPositionsId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALLBYPLANPOSITIONSID =
        new FinderPath(PlanPositionItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionItemModelImpl.FINDER_CACHE_ENABLED,
            PlanPositionItemImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "findByAllByPlanPositionsId",
            new String[] { Long.class.getName() },
            PlanPositionItemModelImpl.PLANPOSITIONSID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_ALLBYPLANPOSITIONSID = new FinderPath(PlanPositionItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByAllByPlanPositionsId", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanPositionItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionItemModelImpl.FINDER_CACHE_ENABLED,
            PlanPositionItemImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanPositionItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionItemModelImpl.FINDER_CACHE_ENABLED,
            PlanPositionItemImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
            "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanPositionItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionItemModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PLANPOSITIONITEM = "SELECT planPositionItem FROM PlanPositionItem planPositionItem";
    private static final String _SQL_SELECT_PLANPOSITIONITEM_WHERE = "SELECT planPositionItem FROM PlanPositionItem planPositionItem WHERE ";
    private static final String _SQL_COUNT_PLANPOSITIONITEM = "SELECT COUNT(planPositionItem) FROM PlanPositionItem planPositionItem";
    private static final String _SQL_COUNT_PLANPOSITIONITEM_WHERE = "SELECT COUNT(planPositionItem) FROM PlanPositionItem planPositionItem WHERE ";
    private static final String _FINDER_COLUMN_ALLBYPLANPOSITIONSID_PLANPOSITIONSID_2 =
        "planPositionItem.id.planPositionsId = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planPositionItem.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanPositionItem exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PlanPositionItem exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanPositionItemPersistenceImpl.class);
    private static PlanPositionItem _nullPlanPositionItem = new PlanPositionItemImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanPositionItem> toCacheModel() {
                return _nullPlanPositionItemCacheModel;
            }
        };

    private static CacheModel<PlanPositionItem> _nullPlanPositionItemCacheModel = new CacheModel<PlanPositionItem>() {
            public PlanPositionItem toEntityModel() {
                return _nullPlanPositionItem;
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
     * Caches the plan position item in the entity cache if it is enabled.
     *
     * @param planPositionItem the plan position item
     */
    public void cacheResult(PlanPositionItem planPositionItem) {
        EntityCacheUtil.putResult(PlanPositionItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionItemImpl.class, planPositionItem.getPrimaryKey(),
            planPositionItem);

        planPositionItem.resetOriginalValues();
    }

    /**
     * Caches the plan position items in the entity cache if it is enabled.
     *
     * @param planPositionItems the plan position items
     */
    public void cacheResult(List<PlanPositionItem> planPositionItems) {
        for (PlanPositionItem planPositionItem : planPositionItems) {
            if (EntityCacheUtil.getResult(
                        PlanPositionItemModelImpl.ENTITY_CACHE_ENABLED,
                        PlanPositionItemImpl.class,
                        planPositionItem.getPrimaryKey()) == null) {
                cacheResult(planPositionItem);
            } else {
                planPositionItem.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan position items.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanPositionItemImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanPositionItemImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan position item.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanPositionItem planPositionItem) {
        EntityCacheUtil.removeResult(PlanPositionItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionItemImpl.class, planPositionItem.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<PlanPositionItem> planPositionItems) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanPositionItem planPositionItem : planPositionItems) {
            EntityCacheUtil.removeResult(PlanPositionItemModelImpl.ENTITY_CACHE_ENABLED,
                PlanPositionItemImpl.class, planPositionItem.getPrimaryKey());
        }
    }

    /**
     * Creates a new plan position item with the primary key. Does not add the plan position item to the database.
     *
     * @param planPositionItemPK the primary key for the new plan position item
     * @return the new plan position item
     */
    public PlanPositionItem create(PlanPositionItemPK planPositionItemPK) {
        PlanPositionItem planPositionItem = new PlanPositionItemImpl();

        planPositionItem.setNew(true);
        planPositionItem.setPrimaryKey(planPositionItemPK);

        return planPositionItem;
    }

    /**
     * Removes the plan position item with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param planPositionItemPK the primary key of the plan position item
     * @return the plan position item that was removed
     * @throws com.ext.portlet.NoSuchPlanPositionItemException if a plan position item with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanPositionItem remove(PlanPositionItemPK planPositionItemPK)
        throws NoSuchPlanPositionItemException, SystemException {
        return remove((Serializable) planPositionItemPK);
    }

    /**
     * Removes the plan position item with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan position item
     * @return the plan position item that was removed
     * @throws com.ext.portlet.NoSuchPlanPositionItemException if a plan position item with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPositionItem remove(Serializable primaryKey)
        throws NoSuchPlanPositionItemException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanPositionItem planPositionItem = (PlanPositionItem) session.get(PlanPositionItemImpl.class,
                    primaryKey);

            if (planPositionItem == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanPositionItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planPositionItem);
        } catch (NoSuchPlanPositionItemException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanPositionItem removeImpl(PlanPositionItem planPositionItem)
        throws SystemException {
        planPositionItem = toUnwrappedModel(planPositionItem);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, planPositionItem);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(planPositionItem);

        return planPositionItem;
    }

    @Override
    public PlanPositionItem updateImpl(
        com.ext.portlet.model.PlanPositionItem planPositionItem, boolean merge)
        throws SystemException {
        planPositionItem = toUnwrappedModel(planPositionItem);

        boolean isNew = planPositionItem.isNew();

        PlanPositionItemModelImpl planPositionItemModelImpl = (PlanPositionItemModelImpl) planPositionItem;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planPositionItem, merge);

            planPositionItem.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PlanPositionItemModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((planPositionItemModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALLBYPLANPOSITIONSID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(planPositionItemModelImpl.getOriginalPlanPositionsId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ALLBYPLANPOSITIONSID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALLBYPLANPOSITIONSID,
                    args);

                args = new Object[] {
                        Long.valueOf(planPositionItemModelImpl.getPlanPositionsId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_ALLBYPLANPOSITIONSID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALLBYPLANPOSITIONSID,
                    args);
            }
        }

        EntityCacheUtil.putResult(PlanPositionItemModelImpl.ENTITY_CACHE_ENABLED,
            PlanPositionItemImpl.class, planPositionItem.getPrimaryKey(),
            planPositionItem);

        return planPositionItem;
    }

    protected PlanPositionItem toUnwrappedModel(
        PlanPositionItem planPositionItem) {
        if (planPositionItem instanceof PlanPositionItemImpl) {
            return planPositionItem;
        }

        PlanPositionItemImpl planPositionItemImpl = new PlanPositionItemImpl();

        planPositionItemImpl.setNew(planPositionItem.isNew());
        planPositionItemImpl.setPrimaryKey(planPositionItem.getPrimaryKey());

        planPositionItemImpl.setPlanPositionsId(planPositionItem.getPlanPositionsId());
        planPositionItemImpl.setPositionId(planPositionItem.getPositionId());

        return planPositionItemImpl;
    }

    /**
     * Returns the plan position item with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan position item
     * @return the plan position item
     * @throws com.liferay.portal.NoSuchModelException if a plan position item with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPositionItem findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey((PlanPositionItemPK) primaryKey);
    }

    /**
     * Returns the plan position item with the primary key or throws a {@link com.ext.portlet.NoSuchPlanPositionItemException} if it could not be found.
     *
     * @param planPositionItemPK the primary key of the plan position item
     * @return the plan position item
     * @throws com.ext.portlet.NoSuchPlanPositionItemException if a plan position item with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanPositionItem findByPrimaryKey(
        PlanPositionItemPK planPositionItemPK)
        throws NoSuchPlanPositionItemException, SystemException {
        PlanPositionItem planPositionItem = fetchByPrimaryKey(planPositionItemPK);

        if (planPositionItem == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    planPositionItemPK);
            }

            throw new NoSuchPlanPositionItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                planPositionItemPK);
        }

        return planPositionItem;
    }

    /**
     * Returns the plan position item with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan position item
     * @return the plan position item, or <code>null</code> if a plan position item with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanPositionItem fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey((PlanPositionItemPK) primaryKey);
    }

    /**
     * Returns the plan position item with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param planPositionItemPK the primary key of the plan position item
     * @return the plan position item, or <code>null</code> if a plan position item with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanPositionItem fetchByPrimaryKey(
        PlanPositionItemPK planPositionItemPK) throws SystemException {
        PlanPositionItem planPositionItem = (PlanPositionItem) EntityCacheUtil.getResult(PlanPositionItemModelImpl.ENTITY_CACHE_ENABLED,
                PlanPositionItemImpl.class, planPositionItemPK);

        if (planPositionItem == _nullPlanPositionItem) {
            return null;
        }

        if (planPositionItem == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                planPositionItem = (PlanPositionItem) session.get(PlanPositionItemImpl.class,
                        planPositionItemPK);
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (planPositionItem != null) {
                    cacheResult(planPositionItem);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(PlanPositionItemModelImpl.ENTITY_CACHE_ENABLED,
                        PlanPositionItemImpl.class, planPositionItemPK,
                        _nullPlanPositionItem);
                }

                closeSession(session);
            }
        }

        return planPositionItem;
    }

    /**
     * Returns all the plan position items where planPositionsId = &#63;.
     *
     * @param planPositionsId the plan positions ID
     * @return the matching plan position items
     * @throws SystemException if a system exception occurred
     */
    public List<PlanPositionItem> findByAllByPlanPositionsId(
        long planPositionsId) throws SystemException {
        return findByAllByPlanPositionsId(planPositionsId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan position items where planPositionsId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param planPositionsId the plan positions ID
     * @param start the lower bound of the range of plan position items
     * @param end the upper bound of the range of plan position items (not inclusive)
     * @return the range of matching plan position items
     * @throws SystemException if a system exception occurred
     */
    public List<PlanPositionItem> findByAllByPlanPositionsId(
        long planPositionsId, int start, int end) throws SystemException {
        return findByAllByPlanPositionsId(planPositionsId, start, end, null);
    }

    /**
     * Returns an ordered range of all the plan position items where planPositionsId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param planPositionsId the plan positions ID
     * @param start the lower bound of the range of plan position items
     * @param end the upper bound of the range of plan position items (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching plan position items
     * @throws SystemException if a system exception occurred
     */
    public List<PlanPositionItem> findByAllByPlanPositionsId(
        long planPositionsId, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_ALLBYPLANPOSITIONSID;
            finderArgs = new Object[] { planPositionsId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_ALLBYPLANPOSITIONSID;
            finderArgs = new Object[] {
                    planPositionsId,
                    
                    start, end, orderByComparator
                };
        }

        List<PlanPositionItem> list = (List<PlanPositionItem>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(2);
            }

            query.append(_SQL_SELECT_PLANPOSITIONITEM_WHERE);

            query.append(_FINDER_COLUMN_ALLBYPLANPOSITIONSID_PLANPOSITIONSID_2);

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

                qPos.add(planPositionsId);

                list = (List<PlanPositionItem>) QueryUtil.list(q, getDialect(),
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
     * Returns the first plan position item in the ordered set where planPositionsId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param planPositionsId the plan positions ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan position item
     * @throws com.ext.portlet.NoSuchPlanPositionItemException if a matching plan position item could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanPositionItem findByAllByPlanPositionsId_First(
        long planPositionsId, OrderByComparator orderByComparator)
        throws NoSuchPlanPositionItemException, SystemException {
        List<PlanPositionItem> list = findByAllByPlanPositionsId(planPositionsId,
                0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("planPositionsId=");
            msg.append(planPositionsId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanPositionItemException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last plan position item in the ordered set where planPositionsId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param planPositionsId the plan positions ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan position item
     * @throws com.ext.portlet.NoSuchPlanPositionItemException if a matching plan position item could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanPositionItem findByAllByPlanPositionsId_Last(
        long planPositionsId, OrderByComparator orderByComparator)
        throws NoSuchPlanPositionItemException, SystemException {
        int count = countByAllByPlanPositionsId(planPositionsId);

        List<PlanPositionItem> list = findByAllByPlanPositionsId(planPositionsId,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("planPositionsId=");
            msg.append(planPositionsId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanPositionItemException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the plan position items before and after the current plan position item in the ordered set where planPositionsId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param planPositionItemPK the primary key of the current plan position item
     * @param planPositionsId the plan positions ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next plan position item
     * @throws com.ext.portlet.NoSuchPlanPositionItemException if a plan position item with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanPositionItem[] findByAllByPlanPositionsId_PrevAndNext(
        PlanPositionItemPK planPositionItemPK, long planPositionsId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanPositionItemException, SystemException {
        PlanPositionItem planPositionItem = findByPrimaryKey(planPositionItemPK);

        Session session = null;

        try {
            session = openSession();

            PlanPositionItem[] array = new PlanPositionItemImpl[3];

            array[0] = getByAllByPlanPositionsId_PrevAndNext(session,
                    planPositionItem, planPositionsId, orderByComparator, true);

            array[1] = planPositionItem;

            array[2] = getByAllByPlanPositionsId_PrevAndNext(session,
                    planPositionItem, planPositionsId, orderByComparator, false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PlanPositionItem getByAllByPlanPositionsId_PrevAndNext(
        Session session, PlanPositionItem planPositionItem,
        long planPositionsId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PLANPOSITIONITEM_WHERE);

        query.append(_FINDER_COLUMN_ALLBYPLANPOSITIONSID_PLANPOSITIONSID_2);

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

        qPos.add(planPositionsId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(planPositionItem);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PlanPositionItem> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the plan position items.
     *
     * @return the plan position items
     * @throws SystemException if a system exception occurred
     */
    public List<PlanPositionItem> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan position items.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plan position items
     * @param end the upper bound of the range of plan position items (not inclusive)
     * @return the range of plan position items
     * @throws SystemException if a system exception occurred
     */
    public List<PlanPositionItem> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan position items.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plan position items
     * @param end the upper bound of the range of plan position items (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan position items
     * @throws SystemException if a system exception occurred
     */
    public List<PlanPositionItem> findAll(int start, int end,
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

        List<PlanPositionItem> list = (List<PlanPositionItem>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANPOSITIONITEM);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANPOSITIONITEM;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<PlanPositionItem>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanPositionItem>) QueryUtil.list(q,
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
     * Removes all the plan position items where planPositionsId = &#63; from the database.
     *
     * @param planPositionsId the plan positions ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByAllByPlanPositionsId(long planPositionsId)
        throws SystemException {
        for (PlanPositionItem planPositionItem : findByAllByPlanPositionsId(
                planPositionsId)) {
            remove(planPositionItem);
        }
    }

    /**
     * Removes all the plan position items from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (PlanPositionItem planPositionItem : findAll()) {
            remove(planPositionItem);
        }
    }

    /**
     * Returns the number of plan position items where planPositionsId = &#63;.
     *
     * @param planPositionsId the plan positions ID
     * @return the number of matching plan position items
     * @throws SystemException if a system exception occurred
     */
    public int countByAllByPlanPositionsId(long planPositionsId)
        throws SystemException {
        Object[] finderArgs = new Object[] { planPositionsId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_ALLBYPLANPOSITIONSID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PLANPOSITIONITEM_WHERE);

            query.append(_FINDER_COLUMN_ALLBYPLANPOSITIONSID_PLANPOSITIONSID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planPositionsId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_ALLBYPLANPOSITIONSID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of plan position items.
     *
     * @return the number of plan position items
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_PLANPOSITIONITEM);

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
     * Initializes the plan position item persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.PlanPositionItem")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanPositionItem>> listenersList = new ArrayList<ModelListener<PlanPositionItem>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanPositionItem>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanPositionItemImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
