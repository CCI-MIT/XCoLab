package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchUserSettingsException;
import com.ext.portlet.plans.model.PlansUserSettings;
import com.ext.portlet.plans.model.impl.PlansUserSettingsImpl;
import com.ext.portlet.plans.model.impl.PlansUserSettingsModelImpl;
import com.ext.portlet.plans.service.persistence.PlanAttributeFilterPersistence;
import com.ext.portlet.plans.service.persistence.PlanAttributePersistence;
import com.ext.portlet.plans.service.persistence.PlanColumnSettingsPersistence;
import com.ext.portlet.plans.service.persistence.PlanDescriptionPersistence;
import com.ext.portlet.plans.service.persistence.PlanFanPersistence;
import com.ext.portlet.plans.service.persistence.PlanItemPersistence;
import com.ext.portlet.plans.service.persistence.PlanMetaPersistence;
import com.ext.portlet.plans.service.persistence.PlanModelRunPersistence;
import com.ext.portlet.plans.service.persistence.PlanPositionItemPersistence;
import com.ext.portlet.plans.service.persistence.PlanPositionPersistence;
import com.ext.portlet.plans.service.persistence.PlanPositionsPersistence;
import com.ext.portlet.plans.service.persistence.PlanPropertyFilterPersistence;
import com.ext.portlet.plans.service.persistence.PlanRelatedPersistence;
import com.ext.portlet.plans.service.persistence.PlanSectionDefinitionPersistence;
import com.ext.portlet.plans.service.persistence.PlanSectionPersistence;
import com.ext.portlet.plans.service.persistence.PlanSectionPlanMapPersistence;
import com.ext.portlet.plans.service.persistence.PlanTeamHistoryPersistence;
import com.ext.portlet.plans.service.persistence.PlanTemplatePersistence;
import com.ext.portlet.plans.service.persistence.PlanTemplateSectionPersistence;
import com.ext.portlet.plans.service.persistence.PlanTypeAttributePersistence;
import com.ext.portlet.plans.service.persistence.PlanTypeColumnPersistence;
import com.ext.portlet.plans.service.persistence.PlanTypePersistence;
import com.ext.portlet.plans.service.persistence.PlanVotePersistence;
import com.ext.portlet.plans.service.persistence.PlansFilterPersistence;
import com.ext.portlet.plans.service.persistence.PlansFilterPositionPersistence;
import com.ext.portlet.plans.service.persistence.PlansUserSettingsPersistence;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
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
 * The persistence implementation for the plans user settings service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlansUserSettingsPersistence
 * @see PlansUserSettingsUtil
 * @generated
 */
public class PlansUserSettingsPersistenceImpl extends BasePersistenceImpl<PlansUserSettings>
    implements PlansUserSettingsPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlansUserSettingsUtil} to access the plans user settings persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlansUserSettingsImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_FETCH_BY_USERIDPLANTYPEID = new FinderPath(PlansUserSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlansUserSettingsModelImpl.FINDER_CACHE_ENABLED,
            PlansUserSettingsImpl.class, FINDER_CLASS_NAME_ENTITY,
            "fetchByuserIdPlanTypeId",
            new String[] { Long.class.getName(), Long.class.getName() },
            PlansUserSettingsModelImpl.USERID_COLUMN_BITMASK |
            PlansUserSettingsModelImpl.PLANTYPEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_USERIDPLANTYPEID = new FinderPath(PlansUserSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlansUserSettingsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "countByuserIdPlanTypeId",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlansUserSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlansUserSettingsModelImpl.FINDER_CACHE_ENABLED,
            PlansUserSettingsImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlansUserSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlansUserSettingsModelImpl.FINDER_CACHE_ENABLED,
            PlansUserSettingsImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlansUserSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlansUserSettingsModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_GET_PLANATTRIBUTEFILTERS = new FinderPath(com.ext.portlet.plans.model.impl.PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanAttributeFilterModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanAttributeFilterImpl.class,
            com.ext.portlet.plans.service.persistence.PlanAttributeFilterPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getPlanAttributeFilters",
            new String[] {
                Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_GET_PLANATTRIBUTEFILTERS_SIZE = new FinderPath(com.ext.portlet.plans.model.impl.PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanAttributeFilterModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanAttributeFilterImpl.class,
            com.ext.portlet.plans.service.persistence.PlanAttributeFilterPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getPlanAttributeFiltersSize", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_CONTAINS_PLANATTRIBUTEFILTER = new FinderPath(com.ext.portlet.plans.model.impl.PlanAttributeFilterModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanAttributeFilterModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanAttributeFilterImpl.class,
            com.ext.portlet.plans.service.persistence.PlanAttributeFilterPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "containsPlanAttributeFilter",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_GET_PLANCOLUMNSETTINGSES = new FinderPath(com.ext.portlet.plans.model.impl.PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanColumnSettingsModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanColumnSettingsImpl.class,
            com.ext.portlet.plans.service.persistence.PlanColumnSettingsPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getPlanColumnSettingses",
            new String[] {
                Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_GET_PLANCOLUMNSETTINGSES_SIZE = new FinderPath(com.ext.portlet.plans.model.impl.PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanColumnSettingsModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanColumnSettingsImpl.class,
            com.ext.portlet.plans.service.persistence.PlanColumnSettingsPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getPlanColumnSettingsesSize", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_CONTAINS_PLANCOLUMNSETTINGS = new FinderPath(com.ext.portlet.plans.model.impl.PlanColumnSettingsModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanColumnSettingsModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanColumnSettingsImpl.class,
            com.ext.portlet.plans.service.persistence.PlanColumnSettingsPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "containsPlanColumnSettings",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _SQL_SELECT_PLANSUSERSETTINGS = "SELECT plansUserSettings FROM PlansUserSettings plansUserSettings";
    private static final String _SQL_SELECT_PLANSUSERSETTINGS_WHERE = "SELECT plansUserSettings FROM PlansUserSettings plansUserSettings WHERE ";
    private static final String _SQL_COUNT_PLANSUSERSETTINGS = "SELECT COUNT(plansUserSettings) FROM PlansUserSettings plansUserSettings";
    private static final String _SQL_COUNT_PLANSUSERSETTINGS_WHERE = "SELECT COUNT(plansUserSettings) FROM PlansUserSettings plansUserSettings WHERE ";
    private static final String _SQL_GETPLANATTRIBUTEFILTERS = "SELECT {Plans_PlanAttributeFilter.*} FROM Plans_PlanAttributeFilter INNER JOIN Plans_PlansUserSettings ON (Plans_PlansUserSettings.planUserSettingsId = Plans_PlanAttributeFilter.planUserSettingsId) WHERE (Plans_PlansUserSettings.planUserSettingsId = ?)";
    private static final String _SQL_GETPLANATTRIBUTEFILTERSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Plans_PlanAttributeFilter WHERE planUserSettingsId = ?";
    private static final String _SQL_CONTAINSPLANATTRIBUTEFILTER = "SELECT COUNT(*) AS COUNT_VALUE FROM Plans_PlanAttributeFilter WHERE planUserSettingsId = ? AND planAttributeFilterId = ?";
    private static final String _SQL_GETPLANCOLUMNSETTINGSES = "SELECT {Plans_PlanColumnSettings.*} FROM Plans_PlanColumnSettings INNER JOIN Plans_PlansUserSettings ON (Plans_PlansUserSettings.planUserSettingsId = Plans_PlanColumnSettings.planUserSettingsId) WHERE (Plans_PlansUserSettings.planUserSettingsId = ?)";
    private static final String _SQL_GETPLANCOLUMNSETTINGSESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Plans_PlanColumnSettings WHERE planUserSettingsId = ?";
    private static final String _SQL_CONTAINSPLANCOLUMNSETTINGS = "SELECT COUNT(*) AS COUNT_VALUE FROM Plans_PlanColumnSettings WHERE planUserSettingsId = ? AND planColumnSettingsId = ?";
    private static final String _FINDER_COLUMN_USERIDPLANTYPEID_USERID_2 = "plansUserSettings.userId = ? AND ";
    private static final String _FINDER_COLUMN_USERIDPLANTYPEID_PLANTYPEID_2 = "plansUserSettings.planTypeId = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "plansUserSettings.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlansUserSettings exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PlansUserSettings exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlansUserSettingsPersistenceImpl.class);
    private static PlansUserSettings _nullPlansUserSettings = new PlansUserSettingsImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlansUserSettings> toCacheModel() {
                return _nullPlansUserSettingsCacheModel;
            }
        };

    private static CacheModel<PlansUserSettings> _nullPlansUserSettingsCacheModel =
        new CacheModel<PlansUserSettings>() {
            public PlansUserSettings toEntityModel() {
                return _nullPlansUserSettings;
            }
        };

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
    protected ContainsPlanAttributeFilter containsPlanAttributeFilter;
    protected ContainsPlanColumnSettings containsPlanColumnSettings;

    /**
     * Caches the plans user settings in the entity cache if it is enabled.
     *
     * @param plansUserSettings the plans user settings
     */
    public void cacheResult(PlansUserSettings plansUserSettings) {
        EntityCacheUtil.putResult(PlansUserSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlansUserSettingsImpl.class, plansUserSettings.getPrimaryKey(),
            plansUserSettings);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERIDPLANTYPEID,
            new Object[] {
                Long.valueOf(plansUserSettings.getUserId()),
                Long.valueOf(plansUserSettings.getPlanTypeId())
            }, plansUserSettings);

        plansUserSettings.resetOriginalValues();
    }

    /**
     * Caches the plans user settingses in the entity cache if it is enabled.
     *
     * @param plansUserSettingses the plans user settingses
     */
    public void cacheResult(List<PlansUserSettings> plansUserSettingses) {
        for (PlansUserSettings plansUserSettings : plansUserSettingses) {
            if (EntityCacheUtil.getResult(
                        PlansUserSettingsModelImpl.ENTITY_CACHE_ENABLED,
                        PlansUserSettingsImpl.class,
                        plansUserSettings.getPrimaryKey()) == null) {
                cacheResult(plansUserSettings);
            } else {
                plansUserSettings.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plans user settingses.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlansUserSettingsImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlansUserSettingsImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plans user settings.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlansUserSettings plansUserSettings) {
        EntityCacheUtil.removeResult(PlansUserSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlansUserSettingsImpl.class, plansUserSettings.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(plansUserSettings);
    }

    @Override
    public void clearCache(List<PlansUserSettings> plansUserSettingses) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlansUserSettings plansUserSettings : plansUserSettingses) {
            EntityCacheUtil.removeResult(PlansUserSettingsModelImpl.ENTITY_CACHE_ENABLED,
                PlansUserSettingsImpl.class, plansUserSettings.getPrimaryKey());

            clearUniqueFindersCache(plansUserSettings);
        }
    }

    protected void clearUniqueFindersCache(PlansUserSettings plansUserSettings) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERIDPLANTYPEID,
            new Object[] {
                Long.valueOf(plansUserSettings.getUserId()),
                Long.valueOf(plansUserSettings.getPlanTypeId())
            });
    }

    /**
     * Creates a new plans user settings with the primary key. Does not add the plans user settings to the database.
     *
     * @param planUserSettingsId the primary key for the new plans user settings
     * @return the new plans user settings
     */
    public PlansUserSettings create(Long planUserSettingsId) {
        PlansUserSettings plansUserSettings = new PlansUserSettingsImpl();

        plansUserSettings.setNew(true);
        plansUserSettings.setPrimaryKey(planUserSettingsId);

        return plansUserSettings;
    }

    /**
     * Removes the plans user settings with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param planUserSettingsId the primary key of the plans user settings
     * @return the plans user settings that was removed
     * @throws com.ext.portlet.plans.NoSuchUserSettingsException if a plans user settings with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlansUserSettings remove(Long planUserSettingsId)
        throws NoSuchUserSettingsException, SystemException {
        return remove((Serializable) planUserSettingsId);
    }

    /**
     * Removes the plans user settings with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plans user settings
     * @return the plans user settings that was removed
     * @throws com.ext.portlet.plans.NoSuchUserSettingsException if a plans user settings with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlansUserSettings remove(Serializable primaryKey)
        throws NoSuchUserSettingsException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlansUserSettings plansUserSettings = (PlansUserSettings) session.get(PlansUserSettingsImpl.class,
                    primaryKey);

            if (plansUserSettings == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchUserSettingsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(plansUserSettings);
        } catch (NoSuchUserSettingsException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlansUserSettings removeImpl(PlansUserSettings plansUserSettings)
        throws SystemException {
        plansUserSettings = toUnwrappedModel(plansUserSettings);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, plansUserSettings);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(plansUserSettings);

        return plansUserSettings;
    }

    @Override
    public PlansUserSettings updateImpl(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings,
        boolean merge) throws SystemException {
        plansUserSettings = toUnwrappedModel(plansUserSettings);

        boolean isNew = plansUserSettings.isNew();

        PlansUserSettingsModelImpl plansUserSettingsModelImpl = (PlansUserSettingsModelImpl) plansUserSettings;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, plansUserSettings, merge);

            plansUserSettings.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PlansUserSettingsModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(PlansUserSettingsModelImpl.ENTITY_CACHE_ENABLED,
            PlansUserSettingsImpl.class, plansUserSettings.getPrimaryKey(),
            plansUserSettings);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERIDPLANTYPEID,
                new Object[] {
                    Long.valueOf(plansUserSettings.getUserId()),
                    Long.valueOf(plansUserSettings.getPlanTypeId())
                }, plansUserSettings);
        } else {
            if ((plansUserSettingsModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_USERIDPLANTYPEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(plansUserSettingsModelImpl.getOriginalUserId()),
                        Long.valueOf(plansUserSettingsModelImpl.getOriginalPlanTypeId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERIDPLANTYPEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERIDPLANTYPEID,
                    args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERIDPLANTYPEID,
                    new Object[] {
                        Long.valueOf(plansUserSettings.getUserId()),
                        Long.valueOf(plansUserSettings.getPlanTypeId())
                    }, plansUserSettings);
            }
        }

        return plansUserSettings;
    }

    protected PlansUserSettings toUnwrappedModel(
        PlansUserSettings plansUserSettings) {
        if (plansUserSettings instanceof PlansUserSettingsImpl) {
            return plansUserSettings;
        }

        PlansUserSettingsImpl plansUserSettingsImpl = new PlansUserSettingsImpl();

        plansUserSettingsImpl.setNew(plansUserSettings.isNew());
        plansUserSettingsImpl.setPrimaryKey(plansUserSettings.getPrimaryKey());

        plansUserSettingsImpl.setPlanUserSettingsId(plansUserSettings.getPlanUserSettingsId());
        plansUserSettingsImpl.setUserId(plansUserSettings.getUserId());
        plansUserSettingsImpl.setPlanTypeId(plansUserSettings.getPlanTypeId());
        plansUserSettingsImpl.setSortColumn(plansUserSettings.getSortColumn());
        plansUserSettingsImpl.setSortDirection(plansUserSettings.getSortDirection());
        plansUserSettingsImpl.setFilterEnabled(plansUserSettings.getFilterEnabled());
        plansUserSettingsImpl.setFilterPositionsAll(plansUserSettings.getFilterPositionsAll());

        return plansUserSettingsImpl;
    }

    /**
     * Returns the plans user settings with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plans user settings
     * @return the plans user settings
     * @throws com.liferay.portal.NoSuchModelException if a plans user settings with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlansUserSettings findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey((Long) primaryKey);
    }

    /**
     * Returns the plans user settings with the primary key or throws a {@link com.ext.portlet.plans.NoSuchUserSettingsException} if it could not be found.
     *
     * @param planUserSettingsId the primary key of the plans user settings
     * @return the plans user settings
     * @throws com.ext.portlet.plans.NoSuchUserSettingsException if a plans user settings with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlansUserSettings findByPrimaryKey(Long planUserSettingsId)
        throws NoSuchUserSettingsException, SystemException {
        PlansUserSettings plansUserSettings = fetchByPrimaryKey(planUserSettingsId);

        if (plansUserSettings == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    planUserSettingsId);
            }

            throw new NoSuchUserSettingsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                planUserSettingsId);
        }

        return plansUserSettings;
    }

    /**
     * Returns the plans user settings with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plans user settings
     * @return the plans user settings, or <code>null</code> if a plans user settings with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlansUserSettings fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey((Long) primaryKey);
    }

    /**
     * Returns the plans user settings with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param planUserSettingsId the primary key of the plans user settings
     * @return the plans user settings, or <code>null</code> if a plans user settings with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlansUserSettings fetchByPrimaryKey(Long planUserSettingsId)
        throws SystemException {
        PlansUserSettings plansUserSettings = (PlansUserSettings) EntityCacheUtil.getResult(PlansUserSettingsModelImpl.ENTITY_CACHE_ENABLED,
                PlansUserSettingsImpl.class, planUserSettingsId);

        if (plansUserSettings == _nullPlansUserSettings) {
            return null;
        }

        if (plansUserSettings == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                plansUserSettings = (PlansUserSettings) session.get(PlansUserSettingsImpl.class,
                        Long.valueOf(planUserSettingsId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (plansUserSettings != null) {
                    cacheResult(plansUserSettings);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(PlansUserSettingsModelImpl.ENTITY_CACHE_ENABLED,
                        PlansUserSettingsImpl.class, planUserSettingsId,
                        _nullPlansUserSettings);
                }

                closeSession(session);
            }
        }

        return plansUserSettings;
    }

    /**
     * Returns the plans user settings where userId = &#63; and planTypeId = &#63; or throws a {@link com.ext.portlet.plans.NoSuchUserSettingsException} if it could not be found.
     *
     * @param userId the user ID
     * @param planTypeId the plan type ID
     * @return the matching plans user settings
     * @throws com.ext.portlet.plans.NoSuchUserSettingsException if a matching plans user settings could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlansUserSettings findByuserIdPlanTypeId(Long userId, Long planTypeId)
        throws NoSuchUserSettingsException, SystemException {
        PlansUserSettings plansUserSettings = fetchByuserIdPlanTypeId(userId,
                planTypeId);

        if (plansUserSettings == null) {
            StringBundler msg = new StringBundler(6);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("userId=");
            msg.append(userId);

            msg.append(", planTypeId=");
            msg.append(planTypeId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchUserSettingsException(msg.toString());
        }

        return plansUserSettings;
    }

    /**
     * Returns the plans user settings where userId = &#63; and planTypeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param userId the user ID
     * @param planTypeId the plan type ID
     * @return the matching plans user settings, or <code>null</code> if a matching plans user settings could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlansUserSettings fetchByuserIdPlanTypeId(Long userId,
        Long planTypeId) throws SystemException {
        return fetchByuserIdPlanTypeId(userId, planTypeId, true);
    }

    /**
     * Returns the plans user settings where userId = &#63; and planTypeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param userId the user ID
     * @param planTypeId the plan type ID
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching plans user settings, or <code>null</code> if a matching plans user settings could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlansUserSettings fetchByuserIdPlanTypeId(Long userId,
        Long planTypeId, boolean retrieveFromCache) throws SystemException {
        Object[] finderArgs = new Object[] { userId, planTypeId };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_USERIDPLANTYPEID,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_SELECT_PLANSUSERSETTINGS_WHERE);

            query.append(_FINDER_COLUMN_USERIDPLANTYPEID_USERID_2);

            query.append(_FINDER_COLUMN_USERIDPLANTYPEID_PLANTYPEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId.longValue());

                qPos.add(planTypeId.longValue());

                List<PlansUserSettings> list = q.list();

                result = list;

                PlansUserSettings plansUserSettings = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERIDPLANTYPEID,
                        finderArgs, list);
                } else {
                    plansUserSettings = list.get(0);

                    cacheResult(plansUserSettings);

                    if ((plansUserSettings.getUserId() != userId) ||
                            (plansUserSettings.getPlanTypeId() != planTypeId)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_USERIDPLANTYPEID,
                            finderArgs, plansUserSettings);
                    }
                }

                return plansUserSettings;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_USERIDPLANTYPEID,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (PlansUserSettings) result;
            }
        }
    }

    /**
     * Returns all the plans user settingses.
     *
     * @return the plans user settingses
     * @throws SystemException if a system exception occurred
     */
    public List<PlansUserSettings> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plans user settingses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plans user settingses
     * @param end the upper bound of the range of plans user settingses (not inclusive)
     * @return the range of plans user settingses
     * @throws SystemException if a system exception occurred
     */
    public List<PlansUserSettings> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plans user settingses.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plans user settingses
     * @param end the upper bound of the range of plans user settingses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plans user settingses
     * @throws SystemException if a system exception occurred
     */
    public List<PlansUserSettings> findAll(int start, int end,
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

        List<PlansUserSettings> list = (List<PlansUserSettings>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANSUSERSETTINGS);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANSUSERSETTINGS;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<PlansUserSettings>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlansUserSettings>) QueryUtil.list(q,
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
     * Removes the plans user settings where userId = &#63; and planTypeId = &#63; from the database.
     *
     * @param userId the user ID
     * @param planTypeId the plan type ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByuserIdPlanTypeId(Long userId, Long planTypeId)
        throws NoSuchUserSettingsException, SystemException {
        PlansUserSettings plansUserSettings = findByuserIdPlanTypeId(userId,
                planTypeId);

        remove(plansUserSettings);
    }

    /**
     * Removes all the plans user settingses from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (PlansUserSettings plansUserSettings : findAll()) {
            remove(plansUserSettings);
        }
    }

    /**
     * Returns the number of plans user settingses where userId = &#63; and planTypeId = &#63;.
     *
     * @param userId the user ID
     * @param planTypeId the plan type ID
     * @return the number of matching plans user settingses
     * @throws SystemException if a system exception occurred
     */
    public int countByuserIdPlanTypeId(Long userId, Long planTypeId)
        throws SystemException {
        Object[] finderArgs = new Object[] { userId, planTypeId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERIDPLANTYPEID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(3);

            query.append(_SQL_COUNT_PLANSUSERSETTINGS_WHERE);

            query.append(_FINDER_COLUMN_USERIDPLANTYPEID_USERID_2);

            query.append(_FINDER_COLUMN_USERIDPLANTYPEID_PLANTYPEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(userId.longValue());

                qPos.add(planTypeId.longValue());

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERIDPLANTYPEID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of plans user settingses.
     *
     * @return the number of plans user settingses
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_PLANSUSERSETTINGS);

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
     * Returns all the plan attribute filters associated with the plans user settings.
     *
     * @param pk the primary key of the plans user settings
     * @return the plan attribute filters associated with the plans user settings
     * @throws SystemException if a system exception occurred
     */
    public List<com.ext.portlet.plans.model.PlanAttributeFilter> getPlanAttributeFilters(
        Long pk) throws SystemException {
        return getPlanAttributeFilters(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    /**
     * Returns a range of all the plan attribute filters associated with the plans user settings.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the plans user settings
     * @param start the lower bound of the range of plans user settingses
     * @param end the upper bound of the range of plans user settingses (not inclusive)
     * @return the range of plan attribute filters associated with the plans user settings
     * @throws SystemException if a system exception occurred
     */
    public List<com.ext.portlet.plans.model.PlanAttributeFilter> getPlanAttributeFilters(
        Long pk, int start, int end) throws SystemException {
        return getPlanAttributeFilters(pk, start, end, null);
    }

    /**
     * Returns an ordered range of all the plan attribute filters associated with the plans user settings.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the plans user settings
     * @param start the lower bound of the range of plans user settingses
     * @param end the upper bound of the range of plans user settingses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan attribute filters associated with the plans user settings
     * @throws SystemException if a system exception occurred
     */
    public List<com.ext.portlet.plans.model.PlanAttributeFilter> getPlanAttributeFilters(
        Long pk, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

        List<com.ext.portlet.plans.model.PlanAttributeFilter> list = (List<com.ext.portlet.plans.model.PlanAttributeFilter>) FinderCacheUtil.getResult(FINDER_PATH_GET_PLANATTRIBUTEFILTERS,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                String sql = null;

                if (orderByComparator != null) {
                    sql = _SQL_GETPLANATTRIBUTEFILTERS.concat(ORDER_BY_CLAUSE)
                                                      .concat(orderByComparator.getOrderBy());
                } else {
                    sql = _SQL_GETPLANATTRIBUTEFILTERS;
                }

                SQLQuery q = session.createSQLQuery(sql);

                q.addEntity("Plans_PlanAttributeFilter",
                    com.ext.portlet.plans.model.impl.PlanAttributeFilterImpl.class);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                list = (List<com.ext.portlet.plans.model.PlanAttributeFilter>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_GET_PLANATTRIBUTEFILTERS,
                        finderArgs);
                } else {
                    planAttributeFilterPersistence.cacheResult(list);

                    FinderCacheUtil.putResult(FINDER_PATH_GET_PLANATTRIBUTEFILTERS,
                        finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the number of plan attribute filters associated with the plans user settings.
     *
     * @param pk the primary key of the plans user settings
     * @return the number of plan attribute filters associated with the plans user settings
     * @throws SystemException if a system exception occurred
     */
    public int getPlanAttributeFiltersSize(Long pk) throws SystemException {
        Object[] finderArgs = new Object[] { pk };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_GET_PLANATTRIBUTEFILTERS_SIZE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                SQLQuery q = session.createSQLQuery(_SQL_GETPLANATTRIBUTEFILTERSSIZE);

                q.addScalar(COUNT_COLUMN_NAME,
                    com.liferay.portal.kernel.dao.orm.Type.LONG);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_GET_PLANATTRIBUTEFILTERS_SIZE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns <code>true</code> if the plan attribute filter is associated with the plans user settings.
     *
     * @param pk the primary key of the plans user settings
     * @param planAttributeFilterPK the primary key of the plan attribute filter
     * @return <code>true</code> if the plan attribute filter is associated with the plans user settings; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsPlanAttributeFilter(Long pk,
        Long planAttributeFilterPK) throws SystemException {
        Object[] finderArgs = new Object[] { pk, planAttributeFilterPK };

        Boolean value = (Boolean) FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_PLANATTRIBUTEFILTER,
                finderArgs, this);

        if (value == null) {
            try {
                value = Boolean.valueOf(containsPlanAttributeFilter.contains(
                            pk, planAttributeFilterPK));
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (value == null) {
                    value = Boolean.FALSE;
                }

                FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_PLANATTRIBUTEFILTER,
                    finderArgs, value);
            }
        }

        return value.booleanValue();
    }

    /**
     * Returns <code>true</code> if the plans user settings has any plan attribute filters associated with it.
     *
     * @param pk the primary key of the plans user settings to check for associations with plan attribute filters
     * @return <code>true</code> if the plans user settings has any plan attribute filters associated with it; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsPlanAttributeFilters(Long pk)
        throws SystemException {
        if (getPlanAttributeFiltersSize(pk) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns all the plan column settingses associated with the plans user settings.
     *
     * @param pk the primary key of the plans user settings
     * @return the plan column settingses associated with the plans user settings
     * @throws SystemException if a system exception occurred
     */
    public List<com.ext.portlet.plans.model.PlanColumnSettings> getPlanColumnSettingses(
        Long pk) throws SystemException {
        return getPlanColumnSettingses(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    /**
     * Returns a range of all the plan column settingses associated with the plans user settings.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the plans user settings
     * @param start the lower bound of the range of plans user settingses
     * @param end the upper bound of the range of plans user settingses (not inclusive)
     * @return the range of plan column settingses associated with the plans user settings
     * @throws SystemException if a system exception occurred
     */
    public List<com.ext.portlet.plans.model.PlanColumnSettings> getPlanColumnSettingses(
        Long pk, int start, int end) throws SystemException {
        return getPlanColumnSettingses(pk, start, end, null);
    }

    /**
     * Returns an ordered range of all the plan column settingses associated with the plans user settings.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the plans user settings
     * @param start the lower bound of the range of plans user settingses
     * @param end the upper bound of the range of plans user settingses (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan column settingses associated with the plans user settings
     * @throws SystemException if a system exception occurred
     */
    public List<com.ext.portlet.plans.model.PlanColumnSettings> getPlanColumnSettingses(
        Long pk, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

        List<com.ext.portlet.plans.model.PlanColumnSettings> list = (List<com.ext.portlet.plans.model.PlanColumnSettings>) FinderCacheUtil.getResult(FINDER_PATH_GET_PLANCOLUMNSETTINGSES,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                String sql = null;

                if (orderByComparator != null) {
                    sql = _SQL_GETPLANCOLUMNSETTINGSES.concat(ORDER_BY_CLAUSE)
                                                      .concat(orderByComparator.getOrderBy());
                } else {
                    sql = _SQL_GETPLANCOLUMNSETTINGSES;
                }

                SQLQuery q = session.createSQLQuery(sql);

                q.addEntity("Plans_PlanColumnSettings",
                    com.ext.portlet.plans.model.impl.PlanColumnSettingsImpl.class);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                list = (List<com.ext.portlet.plans.model.PlanColumnSettings>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_GET_PLANCOLUMNSETTINGSES,
                        finderArgs);
                } else {
                    planColumnSettingsPersistence.cacheResult(list);

                    FinderCacheUtil.putResult(FINDER_PATH_GET_PLANCOLUMNSETTINGSES,
                        finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the number of plan column settingses associated with the plans user settings.
     *
     * @param pk the primary key of the plans user settings
     * @return the number of plan column settingses associated with the plans user settings
     * @throws SystemException if a system exception occurred
     */
    public int getPlanColumnSettingsesSize(Long pk) throws SystemException {
        Object[] finderArgs = new Object[] { pk };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_GET_PLANCOLUMNSETTINGSES_SIZE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                SQLQuery q = session.createSQLQuery(_SQL_GETPLANCOLUMNSETTINGSESSIZE);

                q.addScalar(COUNT_COLUMN_NAME,
                    com.liferay.portal.kernel.dao.orm.Type.LONG);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_GET_PLANCOLUMNSETTINGSES_SIZE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns <code>true</code> if the plan column settings is associated with the plans user settings.
     *
     * @param pk the primary key of the plans user settings
     * @param planColumnSettingsPK the primary key of the plan column settings
     * @return <code>true</code> if the plan column settings is associated with the plans user settings; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsPlanColumnSettings(Long pk, Long planColumnSettingsPK)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, planColumnSettingsPK };

        Boolean value = (Boolean) FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_PLANCOLUMNSETTINGS,
                finderArgs, this);

        if (value == null) {
            try {
                value = Boolean.valueOf(containsPlanColumnSettings.contains(
                            pk, planColumnSettingsPK));
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (value == null) {
                    value = Boolean.FALSE;
                }

                FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_PLANCOLUMNSETTINGS,
                    finderArgs, value);
            }
        }

        return value.booleanValue();
    }

    /**
     * Returns <code>true</code> if the plans user settings has any plan column settingses associated with it.
     *
     * @param pk the primary key of the plans user settings to check for associations with plan column settingses
     * @return <code>true</code> if the plans user settings has any plan column settingses associated with it; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsPlanColumnSettingses(Long pk)
        throws SystemException {
        if (getPlanColumnSettingsesSize(pk) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Initializes the plans user settings persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.plans.model.PlansUserSettings")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlansUserSettings>> listenersList = new ArrayList<ModelListener<PlansUserSettings>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlansUserSettings>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }

        containsPlanAttributeFilter = new ContainsPlanAttributeFilter();

        containsPlanColumnSettings = new ContainsPlanColumnSettings();
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlansUserSettingsImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    protected class ContainsPlanAttributeFilter {
        private MappingSqlQuery<Integer> _mappingSqlQuery;

        protected ContainsPlanAttributeFilter() {
            _mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
                    _SQL_CONTAINSPLANATTRIBUTEFILTER,
                    new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
                    RowMapper.COUNT);
        }

        protected boolean contains(Long planUserSettingsId,
            Long planAttributeFilterId) {
            List<Integer> results = _mappingSqlQuery.execute(new Object[] {
                        new Long(planUserSettingsId),
                        new Long(planAttributeFilterId)
                    });

            if (results.size() > 0) {
                Integer count = results.get(0);

                if (count.intValue() > 0) {
                    return true;
                }
            }

            return false;
        }
    }

    protected class ContainsPlanColumnSettings {
        private MappingSqlQuery<Integer> _mappingSqlQuery;

        protected ContainsPlanColumnSettings() {
            _mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
                    _SQL_CONTAINSPLANCOLUMNSETTINGS,
                    new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
                    RowMapper.COUNT);
        }

        protected boolean contains(Long planUserSettingsId,
            Long planColumnSettingsId) {
            List<Integer> results = _mappingSqlQuery.execute(new Object[] {
                        new Long(planUserSettingsId),
                        new Long(planColumnSettingsId)
                    });

            if (results.size() > 0) {
                Integer count = results.get(0);

                if (count.intValue() > 0) {
                    return true;
                }
            }

            return false;
        }
    }
}
