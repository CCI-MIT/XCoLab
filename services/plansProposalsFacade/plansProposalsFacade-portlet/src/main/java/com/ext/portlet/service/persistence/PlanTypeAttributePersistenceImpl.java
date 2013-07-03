package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPlanTypeAttributeException;
import com.ext.portlet.model.PlanTypeAttribute;
import com.ext.portlet.model.impl.PlanTypeAttributeImpl;
import com.ext.portlet.model.impl.PlanTypeAttributeModelImpl;
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
 * The persistence implementation for the plan type attribute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypeAttributePersistence
 * @see PlanTypeAttributeUtil
 * @generated
 */
public class PlanTypeAttributePersistenceImpl extends BasePersistenceImpl<PlanTypeAttribute>
    implements PlanTypeAttributePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanTypeAttributeUtil} to access the plan type attribute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanTypeAttributeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_FETCH_BY_PLANTYPEIDATTRIBUTENAME = new FinderPath(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeAttributeModelImpl.FINDER_CACHE_ENABLED,
            PlanTypeAttributeImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByPlanTypeIdAttributeName",
            new String[] { Long.class.getName(), String.class.getName() },
            PlanTypeAttributeModelImpl.PLANTYPEID_COLUMN_BITMASK |
            PlanTypeAttributeModelImpl.ATTRIBUTENAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANTYPEIDATTRIBUTENAME = new FinderPath(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeAttributeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByPlanTypeIdAttributeName",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeAttributeModelImpl.FINDER_CACHE_ENABLED,
            PlanTypeAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeAttributeModelImpl.FINDER_CACHE_ENABLED,
            PlanTypeAttributeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeAttributeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PLANTYPEATTRIBUTE = "SELECT planTypeAttribute FROM PlanTypeAttribute planTypeAttribute";
    private static final String _SQL_SELECT_PLANTYPEATTRIBUTE_WHERE = "SELECT planTypeAttribute FROM PlanTypeAttribute planTypeAttribute WHERE ";
    private static final String _SQL_COUNT_PLANTYPEATTRIBUTE = "SELECT COUNT(planTypeAttribute) FROM PlanTypeAttribute planTypeAttribute";
    private static final String _SQL_COUNT_PLANTYPEATTRIBUTE_WHERE = "SELECT COUNT(planTypeAttribute) FROM PlanTypeAttribute planTypeAttribute WHERE ";
    private static final String _FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_PLANTYPEID_2 =
        "planTypeAttribute.planTypeId = ? AND ";
    private static final String _FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_ATTRIBUTENAME_1 =
        "planTypeAttribute.attributeName IS NULL";
    private static final String _FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_ATTRIBUTENAME_2 =
        "planTypeAttribute.attributeName = ?";
    private static final String _FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_ATTRIBUTENAME_3 =
        "(planTypeAttribute.attributeName IS NULL OR planTypeAttribute.attributeName = ?)";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planTypeAttribute.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanTypeAttribute exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PlanTypeAttribute exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanTypeAttributePersistenceImpl.class);
    private static PlanTypeAttribute _nullPlanTypeAttribute = new PlanTypeAttributeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanTypeAttribute> toCacheModel() {
                return _nullPlanTypeAttributeCacheModel;
            }
        };

    private static CacheModel<PlanTypeAttribute> _nullPlanTypeAttributeCacheModel =
        new CacheModel<PlanTypeAttribute>() {
            public PlanTypeAttribute toEntityModel() {
                return _nullPlanTypeAttribute;
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
    @BeanReference(type = ResourcePersistence.class)
    protected ResourcePersistence resourcePersistence;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;

    /**
     * Caches the plan type attribute in the entity cache if it is enabled.
     *
     * @param planTypeAttribute the plan type attribute
     */
    public void cacheResult(PlanTypeAttribute planTypeAttribute) {
        EntityCacheUtil.putResult(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeAttributeImpl.class, planTypeAttribute.getPrimaryKey(),
            planTypeAttribute);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANTYPEIDATTRIBUTENAME,
            new Object[] {
                Long.valueOf(planTypeAttribute.getPlanTypeId()),
                
            planTypeAttribute.getAttributeName()
            }, planTypeAttribute);

        planTypeAttribute.resetOriginalValues();
    }

    /**
     * Caches the plan type attributes in the entity cache if it is enabled.
     *
     * @param planTypeAttributes the plan type attributes
     */
    public void cacheResult(List<PlanTypeAttribute> planTypeAttributes) {
        for (PlanTypeAttribute planTypeAttribute : planTypeAttributes) {
            if (EntityCacheUtil.getResult(
                        PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
                        PlanTypeAttributeImpl.class,
                        planTypeAttribute.getPrimaryKey()) == null) {
                cacheResult(planTypeAttribute);
            } else {
                planTypeAttribute.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan type attributes.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanTypeAttributeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanTypeAttributeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan type attribute.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanTypeAttribute planTypeAttribute) {
        EntityCacheUtil.removeResult(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeAttributeImpl.class, planTypeAttribute.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(planTypeAttribute);
    }

    @Override
    public void clearCache(List<PlanTypeAttribute> planTypeAttributes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanTypeAttribute planTypeAttribute : planTypeAttributes) {
            EntityCacheUtil.removeResult(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
                PlanTypeAttributeImpl.class, planTypeAttribute.getPrimaryKey());

            clearUniqueFindersCache(planTypeAttribute);
        }
    }

    protected void clearUniqueFindersCache(PlanTypeAttribute planTypeAttribute) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANTYPEIDATTRIBUTENAME,
            new Object[] {
                Long.valueOf(planTypeAttribute.getPlanTypeId()),
                
            planTypeAttribute.getAttributeName()
            });
    }

    /**
     * Creates a new plan type attribute with the primary key. Does not add the plan type attribute to the database.
     *
     * @param planTypeAttributeId the primary key for the new plan type attribute
     * @return the new plan type attribute
     */
    public PlanTypeAttribute create(long planTypeAttributeId) {
        PlanTypeAttribute planTypeAttribute = new PlanTypeAttributeImpl();

        planTypeAttribute.setNew(true);
        planTypeAttribute.setPrimaryKey(planTypeAttributeId);

        return planTypeAttribute;
    }

    /**
     * Removes the plan type attribute with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param planTypeAttributeId the primary key of the plan type attribute
     * @return the plan type attribute that was removed
     * @throws com.ext.portlet.NoSuchPlanTypeAttributeException if a plan type attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTypeAttribute remove(long planTypeAttributeId)
        throws NoSuchPlanTypeAttributeException, SystemException {
        return remove(Long.valueOf(planTypeAttributeId));
    }

    /**
     * Removes the plan type attribute with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan type attribute
     * @return the plan type attribute that was removed
     * @throws com.ext.portlet.NoSuchPlanTypeAttributeException if a plan type attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTypeAttribute remove(Serializable primaryKey)
        throws NoSuchPlanTypeAttributeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanTypeAttribute planTypeAttribute = (PlanTypeAttribute) session.get(PlanTypeAttributeImpl.class,
                    primaryKey);

            if (planTypeAttribute == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanTypeAttributeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planTypeAttribute);
        } catch (NoSuchPlanTypeAttributeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanTypeAttribute removeImpl(PlanTypeAttribute planTypeAttribute)
        throws SystemException {
        planTypeAttribute = toUnwrappedModel(planTypeAttribute);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, planTypeAttribute);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(planTypeAttribute);

        return planTypeAttribute;
    }

    @Override
    public PlanTypeAttribute updateImpl(
        com.ext.portlet.model.PlanTypeAttribute planTypeAttribute, boolean merge)
        throws SystemException {
        planTypeAttribute = toUnwrappedModel(planTypeAttribute);

        boolean isNew = planTypeAttribute.isNew();

        PlanTypeAttributeModelImpl planTypeAttributeModelImpl = (PlanTypeAttributeModelImpl) planTypeAttribute;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planTypeAttribute, merge);

            planTypeAttribute.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PlanTypeAttributeModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeAttributeImpl.class, planTypeAttribute.getPrimaryKey(),
            planTypeAttribute);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANTYPEIDATTRIBUTENAME,
                new Object[] {
                    Long.valueOf(planTypeAttribute.getPlanTypeId()),
                    
                planTypeAttribute.getAttributeName()
                }, planTypeAttribute);
        } else {
            if ((planTypeAttributeModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_PLANTYPEIDATTRIBUTENAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(planTypeAttributeModelImpl.getOriginalPlanTypeId()),
                        
                        planTypeAttributeModelImpl.getOriginalAttributeName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANTYPEIDATTRIBUTENAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANTYPEIDATTRIBUTENAME,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANTYPEIDATTRIBUTENAME,
                    new Object[] {
                        Long.valueOf(planTypeAttribute.getPlanTypeId()),
                        
                    planTypeAttribute.getAttributeName()
                    }, planTypeAttribute);
            }
        }

        return planTypeAttribute;
    }

    protected PlanTypeAttribute toUnwrappedModel(
        PlanTypeAttribute planTypeAttribute) {
        if (planTypeAttribute instanceof PlanTypeAttributeImpl) {
            return planTypeAttribute;
        }

        PlanTypeAttributeImpl planTypeAttributeImpl = new PlanTypeAttributeImpl();

        planTypeAttributeImpl.setNew(planTypeAttribute.isNew());
        planTypeAttributeImpl.setPrimaryKey(planTypeAttribute.getPrimaryKey());

        planTypeAttributeImpl.setPlanTypeAttributeId(planTypeAttribute.getPlanTypeAttributeId());
        planTypeAttributeImpl.setPlanTypeId(planTypeAttribute.getPlanTypeId());
        planTypeAttributeImpl.setAttributeName(planTypeAttribute.getAttributeName());

        return planTypeAttributeImpl;
    }

    /**
     * Returns the plan type attribute with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan type attribute
     * @return the plan type attribute
     * @throws com.liferay.portal.NoSuchModelException if a plan type attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTypeAttribute findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the plan type attribute with the primary key or throws a {@link com.ext.portlet.NoSuchPlanTypeAttributeException} if it could not be found.
     *
     * @param planTypeAttributeId the primary key of the plan type attribute
     * @return the plan type attribute
     * @throws com.ext.portlet.NoSuchPlanTypeAttributeException if a plan type attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTypeAttribute findByPrimaryKey(long planTypeAttributeId)
        throws NoSuchPlanTypeAttributeException, SystemException {
        PlanTypeAttribute planTypeAttribute = fetchByPrimaryKey(planTypeAttributeId);

        if (planTypeAttribute == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    planTypeAttributeId);
            }

            throw new NoSuchPlanTypeAttributeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                planTypeAttributeId);
        }

        return planTypeAttribute;
    }

    /**
     * Returns the plan type attribute with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan type attribute
     * @return the plan type attribute, or <code>null</code> if a plan type attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTypeAttribute fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the plan type attribute with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param planTypeAttributeId the primary key of the plan type attribute
     * @return the plan type attribute, or <code>null</code> if a plan type attribute with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTypeAttribute fetchByPrimaryKey(long planTypeAttributeId)
        throws SystemException {
        PlanTypeAttribute planTypeAttribute = (PlanTypeAttribute) EntityCacheUtil.getResult(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
                PlanTypeAttributeImpl.class, planTypeAttributeId);

        if (planTypeAttribute == _nullPlanTypeAttribute) {
            return null;
        }

        if (planTypeAttribute == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                planTypeAttribute = (PlanTypeAttribute) session.get(PlanTypeAttributeImpl.class,
                        Long.valueOf(planTypeAttributeId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (planTypeAttribute != null) {
                    cacheResult(planTypeAttribute);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
                        PlanTypeAttributeImpl.class, planTypeAttributeId,
                        _nullPlanTypeAttribute);
                }

                closeSession(session);
            }
        }

        return planTypeAttribute;
    }

    /**
     * Returns the plan type attribute where planTypeId = &#63; and attributeName = &#63; or throws a {@link com.ext.portlet.NoSuchPlanTypeAttributeException} if it could not be found.
     *
     * @param planTypeId the plan type ID
     * @param attributeName the attribute name
     * @return the matching plan type attribute
     * @throws com.ext.portlet.NoSuchPlanTypeAttributeException if a matching plan type attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTypeAttribute findByPlanTypeIdAttributeName(long planTypeId,
        String attributeName)
        throws NoSuchPlanTypeAttributeException, SystemException {
        PlanTypeAttribute planTypeAttribute = fetchByPlanTypeIdAttributeName(planTypeId,
                attributeName);

        if (planTypeAttribute == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("planTypeId=");
            msg.append(planTypeId);

            msg.append(", attributeName=");
            msg.append(attributeName);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanTypeAttributeException(msg.toString());
        }

        return planTypeAttribute;
    }

    /**
     * Returns the plan type attribute where planTypeId = &#63; and attributeName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param planTypeId the plan type ID
     * @param attributeName the attribute name
     * @return the matching plan type attribute, or <code>null</code> if a matching plan type attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTypeAttribute fetchByPlanTypeIdAttributeName(long planTypeId,
        String attributeName) throws SystemException {
        return fetchByPlanTypeIdAttributeName(planTypeId, attributeName, true);
    }

    /**
     * Returns the plan type attribute where planTypeId = &#63; and attributeName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param planTypeId the plan type ID
     * @param attributeName the attribute name
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching plan type attribute, or <code>null</code> if a matching plan type attribute could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTypeAttribute fetchByPlanTypeIdAttributeName(long planTypeId,
        String attributeName, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { planTypeId, attributeName };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PLANTYPEIDATTRIBUTENAME,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_PLANTYPEATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_PLANTYPEID_2);

            if (attributeName == null) {
                query.append(_FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_ATTRIBUTENAME_1);
            } else {
                if (attributeName.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_ATTRIBUTENAME_3);
                } else {
                    query.append(_FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_ATTRIBUTENAME_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planTypeId);

                if (attributeName != null) {
                    qPos.add(attributeName);
                }

                List<PlanTypeAttribute> list = q.list();

                result = list;

                PlanTypeAttribute planTypeAttribute = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANTYPEIDATTRIBUTENAME,
                        finderArgs, list);
                } else {
                    planTypeAttribute = list.get(0);

                    cacheResult(planTypeAttribute);

                    if ((planTypeAttribute.getPlanTypeId() != planTypeId) ||
                            (planTypeAttribute.getAttributeName() == null) ||
                            !planTypeAttribute.getAttributeName()
                                                  .equals(attributeName)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANTYPEIDATTRIBUTENAME,
                            finderArgs, planTypeAttribute);
                    }
                }

                return planTypeAttribute;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANTYPEIDATTRIBUTENAME,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (PlanTypeAttribute) result;
            }
        }
    }

    /**
     * Returns all the plan type attributes.
     *
     * @return the plan type attributes
     * @throws SystemException if a system exception occurred
     */
    public List<PlanTypeAttribute> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan type attributes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plan type attributes
     * @param end the upper bound of the range of plan type attributes (not inclusive)
     * @return the range of plan type attributes
     * @throws SystemException if a system exception occurred
     */
    public List<PlanTypeAttribute> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan type attributes.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plan type attributes
     * @param end the upper bound of the range of plan type attributes (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan type attributes
     * @throws SystemException if a system exception occurred
     */
    public List<PlanTypeAttribute> findAll(int start, int end,
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

        List<PlanTypeAttribute> list = (List<PlanTypeAttribute>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANTYPEATTRIBUTE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANTYPEATTRIBUTE;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<PlanTypeAttribute>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanTypeAttribute>) QueryUtil.list(q,
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
     * Removes the plan type attribute where planTypeId = &#63; and attributeName = &#63; from the database.
     *
     * @param planTypeId the plan type ID
     * @param attributeName the attribute name
     * @throws SystemException if a system exception occurred
     */
    public void removeByPlanTypeIdAttributeName(long planTypeId,
        String attributeName)
        throws NoSuchPlanTypeAttributeException, SystemException {
        PlanTypeAttribute planTypeAttribute = findByPlanTypeIdAttributeName(planTypeId,
                attributeName);

        remove(planTypeAttribute);
    }

    /**
     * Removes all the plan type attributes from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (PlanTypeAttribute planTypeAttribute : findAll()) {
            remove(planTypeAttribute);
        }
    }

    /**
     * Returns the number of plan type attributes where planTypeId = &#63; and attributeName = &#63;.
     *
     * @param planTypeId the plan type ID
     * @param attributeName the attribute name
     * @return the number of matching plan type attributes
     * @throws SystemException if a system exception occurred
     */
    public int countByPlanTypeIdAttributeName(long planTypeId,
        String attributeName) throws SystemException {
        Object[] finderArgs = new Object[] { planTypeId, attributeName };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PLANTYPEIDATTRIBUTENAME,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PLANTYPEATTRIBUTE_WHERE);

            query.append(_FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_PLANTYPEID_2);

            if (attributeName == null) {
                query.append(_FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_ATTRIBUTENAME_1);
            } else {
                if (attributeName.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_ATTRIBUTENAME_3);
                } else {
                    query.append(_FINDER_COLUMN_PLANTYPEIDATTRIBUTENAME_ATTRIBUTENAME_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planTypeId);

                if (attributeName != null) {
                    qPos.add(attributeName);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLANTYPEIDATTRIBUTENAME,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of plan type attributes.
     *
     * @return the number of plan type attributes
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_PLANTYPEATTRIBUTE);

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
     * Initializes the plan type attribute persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.PlanTypeAttribute")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanTypeAttribute>> listenersList = new ArrayList<ModelListener<PlanTypeAttribute>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanTypeAttribute>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanTypeAttributeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
