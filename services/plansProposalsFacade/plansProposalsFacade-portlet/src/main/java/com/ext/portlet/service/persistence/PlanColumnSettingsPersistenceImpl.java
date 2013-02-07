package com.ext.portlet.service.persistence;

import com.ext.portlet.NoSuchPlanColumnSettingsException;
import com.ext.portlet.model.PlanColumnSettings;
import com.ext.portlet.model.impl.PlanColumnSettingsImpl;
import com.ext.portlet.model.impl.PlanColumnSettingsModelImpl;
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
 * The persistence implementation for the plan column settings service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanColumnSettingsPersistence
 * @see PlanColumnSettingsUtil
 * @generated
 */
public class PlanColumnSettingsPersistenceImpl extends BasePersistenceImpl<PlanColumnSettings>
    implements PlanColumnSettingsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanColumnSettingsUtil} to access the plan column settings persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanColumnSettingsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME =
        new FinderPath(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlanColumnSettingsModelImpl.FINDER_CACHE_ENABLED,
            PlanColumnSettingsImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByPlanUserSettingsIdColumnName",
            new String[] { Long.class.getName(), String.class.getName() },
            PlanColumnSettingsModelImpl.PLANUSERSETTINGSID_COLUMN_BITMASK |
            PlanColumnSettingsModelImpl.COLUMNNAME_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDCOLUMNNAME =
        new FinderPath(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlanColumnSettingsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByPlanUserSettingsIdColumnName",
            new String[] { Long.class.getName(), String.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlanColumnSettingsModelImpl.FINDER_CACHE_ENABLED,
            PlanColumnSettingsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlanColumnSettingsModelImpl.FINDER_CACHE_ENABLED,
            PlanColumnSettingsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlanColumnSettingsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PLANCOLUMNSETTINGS = "SELECT planColumnSettings FROM PlanColumnSettings planColumnSettings";
    private static final String _SQL_SELECT_PLANCOLUMNSETTINGS_WHERE = "SELECT planColumnSettings FROM PlanColumnSettings planColumnSettings WHERE ";
    private static final String _SQL_COUNT_PLANCOLUMNSETTINGS = "SELECT COUNT(planColumnSettings) FROM PlanColumnSettings planColumnSettings";
    private static final String _SQL_COUNT_PLANCOLUMNSETTINGS_WHERE = "SELECT COUNT(planColumnSettings) FROM PlanColumnSettings planColumnSettings WHERE ";
    private static final String _FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_PLANUSERSETTINGSID_2 =
        "planColumnSettings.planUserSettingsId = ? AND ";
    private static final String _FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_COLUMNNAME_1 =
        "planColumnSettings.columnName IS NULL";
    private static final String _FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_COLUMNNAME_2 =
        "planColumnSettings.columnName = ?";
    private static final String _FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_COLUMNNAME_3 =
        "(planColumnSettings.columnName IS NULL OR planColumnSettings.columnName = ?)";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planColumnSettings.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanColumnSettings exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PlanColumnSettings exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanColumnSettingsPersistenceImpl.class);
    private static PlanColumnSettings _nullPlanColumnSettings = new PlanColumnSettingsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanColumnSettings> toCacheModel() {
                return _nullPlanColumnSettingsCacheModel;
            }
        };

    private static CacheModel<PlanColumnSettings> _nullPlanColumnSettingsCacheModel =
        new CacheModel<PlanColumnSettings>() {
            public PlanColumnSettings toEntityModel() {
                return _nullPlanColumnSettings;
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
     * Caches the plan column settings in the entity cache if it is enabled.
     *
     * @param planColumnSettings the plan column settings
     */
    public void cacheResult(PlanColumnSettings planColumnSettings) {
        EntityCacheUtil.putResult(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlanColumnSettingsImpl.class, planColumnSettings.getPrimaryKey(),
            planColumnSettings);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
            new Object[] {
                Long.valueOf(planColumnSettings.getPlanUserSettingsId()),
                
            planColumnSettings.getColumnName()
            }, planColumnSettings);

        planColumnSettings.resetOriginalValues();
    }

    /**
     * Caches the plan column settingses in the entity cache if it is enabled.
     *
     * @param planColumnSettingses the plan column settingses
     */
    public void cacheResult(List<PlanColumnSettings> planColumnSettingses) {
        for (PlanColumnSettings planColumnSettings : planColumnSettingses) {
            if (EntityCacheUtil.getResult(
                        PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
                        PlanColumnSettingsImpl.class,
                        planColumnSettings.getPrimaryKey()) == null) {
                cacheResult(planColumnSettings);
            } else {
                planColumnSettings.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan column settingses.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanColumnSettingsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanColumnSettingsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan column settings.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanColumnSettings planColumnSettings) {
        EntityCacheUtil.removeResult(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlanColumnSettingsImpl.class, planColumnSettings.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(planColumnSettings);
    }

    @Override
    public void clearCache(List<PlanColumnSettings> planColumnSettingses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanColumnSettings planColumnSettings : planColumnSettingses) {
            EntityCacheUtil.removeResult(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
                PlanColumnSettingsImpl.class, planColumnSettings.getPrimaryKey());

            clearUniqueFindersCache(planColumnSettings);
        }
    }

    protected void clearUniqueFindersCache(
        PlanColumnSettings planColumnSettings) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
            new Object[] {
                Long.valueOf(planColumnSettings.getPlanUserSettingsId()),
                
            planColumnSettings.getColumnName()
            });
    }

    /**
     * Creates a new plan column settings with the primary key. Does not add the plan column settings to the database.
     *
     * @param planColumnSettingsId the primary key for the new plan column settings
     * @return the new plan column settings
     */
    public PlanColumnSettings create(long planColumnSettingsId) {
        PlanColumnSettings planColumnSettings = new PlanColumnSettingsImpl();

        planColumnSettings.setNew(true);
        planColumnSettings.setPrimaryKey(planColumnSettingsId);

        return planColumnSettings;
    }

    /**
     * Removes the plan column settings with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param planColumnSettingsId the primary key of the plan column settings
     * @return the plan column settings that was removed
     * @throws com.ext.portlet.NoSuchPlanColumnSettingsException if a plan column settings with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanColumnSettings remove(long planColumnSettingsId)
        throws NoSuchPlanColumnSettingsException, SystemException {
        return remove(Long.valueOf(planColumnSettingsId));
    }

    /**
     * Removes the plan column settings with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan column settings
     * @return the plan column settings that was removed
     * @throws com.ext.portlet.NoSuchPlanColumnSettingsException if a plan column settings with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanColumnSettings remove(Serializable primaryKey)
        throws NoSuchPlanColumnSettingsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanColumnSettings planColumnSettings = (PlanColumnSettings) session.get(PlanColumnSettingsImpl.class,
                    primaryKey);

            if (planColumnSettings == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanColumnSettingsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planColumnSettings);
        } catch (NoSuchPlanColumnSettingsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanColumnSettings removeImpl(
        PlanColumnSettings planColumnSettings) throws SystemException {
        planColumnSettings = toUnwrappedModel(planColumnSettings);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, planColumnSettings);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(planColumnSettings);

        return planColumnSettings;
    }

    @Override
    public PlanColumnSettings updateImpl(
        com.ext.portlet.model.PlanColumnSettings planColumnSettings,
        boolean merge) throws SystemException {
        planColumnSettings = toUnwrappedModel(planColumnSettings);

        boolean isNew = planColumnSettings.isNew();

        PlanColumnSettingsModelImpl planColumnSettingsModelImpl = (PlanColumnSettingsModelImpl) planColumnSettings;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planColumnSettings, merge);

            planColumnSettings.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PlanColumnSettingsModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlanColumnSettingsImpl.class, planColumnSettings.getPrimaryKey(),
            planColumnSettings);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                new Object[] {
                    Long.valueOf(planColumnSettings.getPlanUserSettingsId()),
                    
                planColumnSettings.getColumnName()
                }, planColumnSettings);
        } else {
            if ((planColumnSettingsModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(planColumnSettingsModelImpl.getOriginalPlanUserSettingsId()),
                        
                        planColumnSettingsModelImpl.getOriginalColumnName()
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                    new Object[] {
                        Long.valueOf(planColumnSettings.getPlanUserSettingsId()),
                        
                    planColumnSettings.getColumnName()
                    }, planColumnSettings);
            }
        }

        return planColumnSettings;
    }

    protected PlanColumnSettings toUnwrappedModel(
        PlanColumnSettings planColumnSettings) {
        if (planColumnSettings instanceof PlanColumnSettingsImpl) {
            return planColumnSettings;
        }

        PlanColumnSettingsImpl planColumnSettingsImpl = new PlanColumnSettingsImpl();

        planColumnSettingsImpl.setNew(planColumnSettings.isNew());
        planColumnSettingsImpl.setPrimaryKey(planColumnSettings.getPrimaryKey());

        planColumnSettingsImpl.setPlanColumnSettingsId(planColumnSettings.getPlanColumnSettingsId());
        planColumnSettingsImpl.setColumnName(planColumnSettings.getColumnName());
        planColumnSettingsImpl.setPlanUserSettingsId(planColumnSettings.getPlanUserSettingsId());
        planColumnSettingsImpl.setVisible(planColumnSettings.isVisible());

        return planColumnSettingsImpl;
    }

    /**
     * Returns the plan column settings with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan column settings
     * @return the plan column settings
     * @throws com.liferay.portal.NoSuchModelException if a plan column settings with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanColumnSettings findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the plan column settings with the primary key or throws a {@link com.ext.portlet.NoSuchPlanColumnSettingsException} if it could not be found.
     *
     * @param planColumnSettingsId the primary key of the plan column settings
     * @return the plan column settings
     * @throws com.ext.portlet.NoSuchPlanColumnSettingsException if a plan column settings with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanColumnSettings findByPrimaryKey(long planColumnSettingsId)
        throws NoSuchPlanColumnSettingsException, SystemException {
        PlanColumnSettings planColumnSettings = fetchByPrimaryKey(planColumnSettingsId);

        if (planColumnSettings == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    planColumnSettingsId);
            }

            throw new NoSuchPlanColumnSettingsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                planColumnSettingsId);
        }

        return planColumnSettings;
    }

    /**
     * Returns the plan column settings with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan column settings
     * @return the plan column settings, or <code>null</code> if a plan column settings with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanColumnSettings fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the plan column settings with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param planColumnSettingsId the primary key of the plan column settings
     * @return the plan column settings, or <code>null</code> if a plan column settings with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanColumnSettings fetchByPrimaryKey(long planColumnSettingsId)
        throws SystemException {
        PlanColumnSettings planColumnSettings = (PlanColumnSettings) EntityCacheUtil.getResult(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
                PlanColumnSettingsImpl.class, planColumnSettingsId);

        if (planColumnSettings == _nullPlanColumnSettings) {
            return null;
        }

        if (planColumnSettings == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                planColumnSettings = (PlanColumnSettings) session.get(PlanColumnSettingsImpl.class,
                        Long.valueOf(planColumnSettingsId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (planColumnSettings != null) {
                    cacheResult(planColumnSettings);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
                        PlanColumnSettingsImpl.class, planColumnSettingsId,
                        _nullPlanColumnSettings);
                }

                closeSession(session);
            }
        }

        return planColumnSettings;
    }

    /**
     * Returns the plan column settings where planUserSettingsId = &#63; and columnName = &#63; or throws a {@link com.ext.portlet.NoSuchPlanColumnSettingsException} if it could not be found.
     *
     * @param planUserSettingsId the plan user settings ID
     * @param columnName the column name
     * @return the matching plan column settings
     * @throws com.ext.portlet.NoSuchPlanColumnSettingsException if a matching plan column settings could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanColumnSettings findByPlanUserSettingsIdColumnName(
        long planUserSettingsId, String columnName)
        throws NoSuchPlanColumnSettingsException, SystemException {
        PlanColumnSettings planColumnSettings = fetchByPlanUserSettingsIdColumnName(planUserSettingsId,
                columnName);

        if (planColumnSettings == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("planUserSettingsId=");
            msg.append(planUserSettingsId);

            msg.append(", columnName=");
            msg.append(columnName);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanColumnSettingsException(msg.toString());
        }

        return planColumnSettings;
    }

    /**
     * Returns the plan column settings where planUserSettingsId = &#63; and columnName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param planUserSettingsId the plan user settings ID
     * @param columnName the column name
     * @return the matching plan column settings, or <code>null</code> if a matching plan column settings could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanColumnSettings fetchByPlanUserSettingsIdColumnName(
        long planUserSettingsId, String columnName) throws SystemException {
        return fetchByPlanUserSettingsIdColumnName(planUserSettingsId,
            columnName, true);
    }

    /**
     * Returns the plan column settings where planUserSettingsId = &#63; and columnName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param planUserSettingsId the plan user settings ID
     * @param columnName the column name
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching plan column settings, or <code>null</code> if a matching plan column settings could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanColumnSettings fetchByPlanUserSettingsIdColumnName(
        long planUserSettingsId, String columnName, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { planUserSettingsId, columnName };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_PLANCOLUMNSETTINGS_WHERE);

            query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_PLANUSERSETTINGSID_2);

            if (columnName == null) {
                query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_COLUMNNAME_1);
            } else {
                if (columnName.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_COLUMNNAME_3);
                } else {
                    query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_COLUMNNAME_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planUserSettingsId);

                if (columnName != null) {
                    qPos.add(columnName);
                }

                List<PlanColumnSettings> list = q.list();

                result = list;

                PlanColumnSettings planColumnSettings = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                        finderArgs, list);
                } else {
                    planColumnSettings = list.get(0);

                    cacheResult(planColumnSettings);

                    if ((planColumnSettings.getPlanUserSettingsId() != planUserSettingsId) ||
                            (planColumnSettings.getColumnName() == null) ||
                            !planColumnSettings.getColumnName()
                                                   .equals(columnName)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                            finderArgs, planColumnSettings);
                    }
                }

                return planColumnSettings;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (PlanColumnSettings) result;
            }
        }
    }

    /**
     * Returns all the plan column settingses.
     *
     * @return the plan column settingses
     * @throws SystemException if a system exception occurred
     */
    public List<PlanColumnSettings> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan column settingses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plan column settingses
     * @param end the upper bound of the range of plan column settingses (not inclusive)
     * @return the range of plan column settingses
     * @throws SystemException if a system exception occurred
     */
    public List<PlanColumnSettings> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan column settingses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plan column settingses
     * @param end the upper bound of the range of plan column settingses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan column settingses
     * @throws SystemException if a system exception occurred
     */
    public List<PlanColumnSettings> findAll(int start, int end,
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

        List<PlanColumnSettings> list = (List<PlanColumnSettings>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANCOLUMNSETTINGS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANCOLUMNSETTINGS;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<PlanColumnSettings>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanColumnSettings>) QueryUtil.list(q,
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
     * Removes the plan column settings where planUserSettingsId = &#63; and columnName = &#63; from the database.
     *
     * @param planUserSettingsId the plan user settings ID
     * @param columnName the column name
     * @throws SystemException if a system exception occurred
     */
    public void removeByPlanUserSettingsIdColumnName(long planUserSettingsId,
        String columnName)
        throws NoSuchPlanColumnSettingsException, SystemException {
        PlanColumnSettings planColumnSettings = findByPlanUserSettingsIdColumnName(planUserSettingsId,
                columnName);

        remove(planColumnSettings);
    }

    /**
     * Removes all the plan column settingses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (PlanColumnSettings planColumnSettings : findAll()) {
            remove(planColumnSettings);
        }
    }

    /**
     * Returns the number of plan column settingses where planUserSettingsId = &#63; and columnName = &#63;.
     *
     * @param planUserSettingsId the plan user settings ID
     * @param columnName the column name
     * @return the number of matching plan column settingses
     * @throws SystemException if a system exception occurred
     */
    public int countByPlanUserSettingsIdColumnName(long planUserSettingsId,
        String columnName) throws SystemException {
        Object[] finderArgs = new Object[] { planUserSettingsId, columnName };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PLANCOLUMNSETTINGS_WHERE);

            query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_PLANUSERSETTINGSID_2);

            if (columnName == null) {
                query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_COLUMNNAME_1);
            } else {
                if (columnName.equals(StringPool.BLANK)) {
                    query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_COLUMNNAME_3);
                } else {
                    query.append(_FINDER_COLUMN_PLANUSERSETTINGSIDCOLUMNNAME_COLUMNNAME_2);
                }
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planUserSettingsId);

                if (columnName != null) {
                    qPos.add(columnName);
                }

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLANUSERSETTINGSIDCOLUMNNAME,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of plan column settingses.
     *
     * @return the number of plan column settingses
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_PLANCOLUMNSETTINGS);

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
     * Initializes the plan column settings persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.model.PlanColumnSettings")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanColumnSettings>> listenersList = new ArrayList<ModelListener<PlanColumnSettings>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanColumnSettings>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanColumnSettingsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
