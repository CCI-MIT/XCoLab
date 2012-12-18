package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanTypeException;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.impl.PlanTypeImpl;
import com.ext.portlet.plans.model.impl.PlanTypeModelImpl;
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
 * The persistence implementation for the plan type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypePersistence
 * @see PlanTypeUtil
 * @generated
 */
public class PlanTypePersistenceImpl extends BasePersistenceImpl<PlanType>
    implements PlanTypePersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanTypeUtil} to access the plan type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanTypeImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_FETCH_BY_DEFAULT = new FinderPath(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeModelImpl.FINDER_CACHE_ENABLED, PlanTypeImpl.class,
            FINDER_CLASS_NAME_ENTITY, "fetchBydefault",
            new String[] { Boolean.class.getName() },
            PlanTypeModelImpl.ISDEFAULT_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_DEFAULT = new FinderPath(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBydefault",
            new String[] { Boolean.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeModelImpl.FINDER_CACHE_ENABLED, PlanTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeModelImpl.FINDER_CACHE_ENABLED, PlanTypeImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    public static final FinderPath FINDER_PATH_GET_PLANTYPEATTRIBUTES = new FinderPath(com.ext.portlet.plans.model.impl.PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanTypeAttributeModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanTypeAttributeImpl.class,
            com.ext.portlet.plans.service.persistence.PlanTypeAttributePersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getPlanTypeAttributes",
            new String[] {
                Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_GET_PLANTYPEATTRIBUTES_SIZE = new FinderPath(com.ext.portlet.plans.model.impl.PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanTypeAttributeModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanTypeAttributeImpl.class,
            com.ext.portlet.plans.service.persistence.PlanTypeAttributePersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getPlanTypeAttributesSize", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_CONTAINS_PLANTYPEATTRIBUTE = new FinderPath(com.ext.portlet.plans.model.impl.PlanTypeAttributeModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanTypeAttributeModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanTypeAttributeImpl.class,
            com.ext.portlet.plans.service.persistence.PlanTypeAttributePersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "containsPlanTypeAttribute",
            new String[] { Long.class.getName(), Long.class.getName() });
    public static final FinderPath FINDER_PATH_GET_PLANTYPECOLUMNS = new FinderPath(com.ext.portlet.plans.model.impl.PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanTypeColumnModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanTypeColumnImpl.class,
            com.ext.portlet.plans.service.persistence.PlanTypeColumnPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getPlanTypeColumns",
            new String[] {
                Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_GET_PLANTYPECOLUMNS_SIZE = new FinderPath(com.ext.portlet.plans.model.impl.PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanTypeColumnModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanTypeColumnImpl.class,
            com.ext.portlet.plans.service.persistence.PlanTypeColumnPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "getPlanTypeColumnsSize", new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_CONTAINS_PLANTYPECOLUMN = new FinderPath(com.ext.portlet.plans.model.impl.PlanTypeColumnModelImpl.ENTITY_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanTypeColumnModelImpl.FINDER_CACHE_ENABLED,
            com.ext.portlet.plans.model.impl.PlanTypeColumnImpl.class,
            com.ext.portlet.plans.service.persistence.PlanTypeColumnPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
            "containsPlanTypeColumn",
            new String[] { Long.class.getName(), Long.class.getName() });
    private static final String _SQL_SELECT_PLANTYPE = "SELECT planType FROM PlanType planType";
    private static final String _SQL_SELECT_PLANTYPE_WHERE = "SELECT planType FROM PlanType planType WHERE ";
    private static final String _SQL_COUNT_PLANTYPE = "SELECT COUNT(planType) FROM PlanType planType";
    private static final String _SQL_COUNT_PLANTYPE_WHERE = "SELECT COUNT(planType) FROM PlanType planType WHERE ";
    private static final String _SQL_GETPLANTYPEATTRIBUTES = "SELECT {xcolab_PlanTypeAttribute.*} FROM xcolab_PlanTypeAttribute INNER JOIN xcolab_PlanType ON (xcolab_PlanType.planTypeId = xcolab_PlanTypeAttribute.planTypeId) WHERE (xcolab_PlanType.planTypeId = ?)";
    private static final String _SQL_GETPLANTYPEATTRIBUTESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM xcolab_PlanTypeAttribute WHERE planTypeId = ?";
    private static final String _SQL_CONTAINSPLANTYPEATTRIBUTE = "SELECT COUNT(*) AS COUNT_VALUE FROM xcolab_PlanTypeAttribute WHERE planTypeId = ? AND planTypeAttributeId = ?";
    private static final String _SQL_GETPLANTYPECOLUMNS = "SELECT {xcolab_PlanTypeColumn.*} FROM xcolab_PlanTypeColumn INNER JOIN xcolab_PlanType ON (xcolab_PlanType.planTypeId = xcolab_PlanTypeColumn.planTypeId) WHERE (xcolab_PlanType.planTypeId = ?)";
    private static final String _SQL_GETPLANTYPECOLUMNSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM xcolab_PlanTypeColumn WHERE planTypeId = ?";
    private static final String _SQL_CONTAINSPLANTYPECOLUMN = "SELECT COUNT(*) AS COUNT_VALUE FROM xcolab_PlanTypeColumn WHERE planTypeId = ? AND planTypeColumnId = ?";
    private static final String _FINDER_COLUMN_DEFAULT_ISDEFAULT_2 = "planType.isDefault = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planType.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanType exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PlanType exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanTypePersistenceImpl.class);
    private static PlanType _nullPlanType = new PlanTypeImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanType> toCacheModel() {
                return _nullPlanTypeCacheModel;
            }
        };

    private static CacheModel<PlanType> _nullPlanTypeCacheModel = new CacheModel<PlanType>() {
            public PlanType toEntityModel() {
                return _nullPlanType;
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
    protected ContainsPlanTypeAttribute containsPlanTypeAttribute;
    protected ContainsPlanTypeColumn containsPlanTypeColumn;

    /**
     * Caches the plan type in the entity cache if it is enabled.
     *
     * @param planType the plan type
     */
    public void cacheResult(PlanType planType) {
        EntityCacheUtil.putResult(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeImpl.class, planType.getPrimaryKey(), planType);

        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEFAULT,
            new Object[] { Boolean.valueOf(planType.getIsDefault()) }, planType);

        planType.resetOriginalValues();
    }

    /**
     * Caches the plan types in the entity cache if it is enabled.
     *
     * @param planTypes the plan types
     */
    public void cacheResult(List<PlanType> planTypes) {
        for (PlanType planType : planTypes) {
            if (EntityCacheUtil.getResult(
                        PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
                        PlanTypeImpl.class, planType.getPrimaryKey()) == null) {
                cacheResult(planType);
            } else {
                planType.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan types.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanTypeImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanTypeImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan type.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanType planType) {
        EntityCacheUtil.removeResult(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeImpl.class, planType.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        clearUniqueFindersCache(planType);
    }

    @Override
    public void clearCache(List<PlanType> planTypes) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanType planType : planTypes) {
            EntityCacheUtil.removeResult(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
                PlanTypeImpl.class, planType.getPrimaryKey());

            clearUniqueFindersCache(planType);
        }
    }

    protected void clearUniqueFindersCache(PlanType planType) {
        FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DEFAULT,
            new Object[] { Boolean.valueOf(planType.getIsDefault()) });
    }

    /**
     * Creates a new plan type with the primary key. Does not add the plan type to the database.
     *
     * @param planTypeId the primary key for the new plan type
     * @return the new plan type
     */
    public PlanType create(long planTypeId) {
        PlanType planType = new PlanTypeImpl();

        planType.setNew(true);
        planType.setPrimaryKey(planTypeId);

        return planType;
    }

    /**
     * Removes the plan type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param planTypeId the primary key of the plan type
     * @return the plan type that was removed
     * @throws com.ext.portlet.plans.NoSuchPlanTypeException if a plan type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanType remove(long planTypeId)
        throws NoSuchPlanTypeException, SystemException {
        return remove(Long.valueOf(planTypeId));
    }

    /**
     * Removes the plan type with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan type
     * @return the plan type that was removed
     * @throws com.ext.portlet.plans.NoSuchPlanTypeException if a plan type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanType remove(Serializable primaryKey)
        throws NoSuchPlanTypeException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanType planType = (PlanType) session.get(PlanTypeImpl.class,
                    primaryKey);

            if (planType == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planType);
        } catch (NoSuchPlanTypeException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanType removeImpl(PlanType planType) throws SystemException {
        planType = toUnwrappedModel(planType);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, planType);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(planType);

        return planType;
    }

    @Override
    public PlanType updateImpl(com.ext.portlet.plans.model.PlanType planType,
        boolean merge) throws SystemException {
        planType = toUnwrappedModel(planType);

        boolean isNew = planType.isNew();

        PlanTypeModelImpl planTypeModelImpl = (PlanTypeModelImpl) planType;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planType, merge);

            planType.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PlanTypeModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }

        EntityCacheUtil.putResult(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
            PlanTypeImpl.class, planType.getPrimaryKey(), planType);

        if (isNew) {
            FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEFAULT,
                new Object[] { Boolean.valueOf(planType.getIsDefault()) },
                planType);
        } else {
            if ((planTypeModelImpl.getColumnBitmask() &
                    FINDER_PATH_FETCH_BY_DEFAULT.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Boolean.valueOf(planTypeModelImpl.getOriginalIsDefault())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_DEFAULT, args);
                FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DEFAULT, args);

                FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEFAULT,
                    new Object[] { Boolean.valueOf(planType.getIsDefault()) },
                    planType);
            }
        }

        return planType;
    }

    protected PlanType toUnwrappedModel(PlanType planType) {
        if (planType instanceof PlanTypeImpl) {
            return planType;
        }

        PlanTypeImpl planTypeImpl = new PlanTypeImpl();

        planTypeImpl.setNew(planType.isNew());
        planTypeImpl.setPrimaryKey(planType.getPrimaryKey());

        planTypeImpl.setPlanTypeId(planType.getPlanTypeId());
        planTypeImpl.setName(planType.getName());
        planTypeImpl.setDescription(planType.getDescription());
        planTypeImpl.setModelId(planType.getModelId());
        planTypeImpl.setModelTypeName(planType.getModelTypeName());
        planTypeImpl.setPublished(planType.isPublished());
        planTypeImpl.setPublishedCounterpartId(planType.getPublishedCounterpartId());
        planTypeImpl.setIsDefault(planType.isIsDefault());
        planTypeImpl.setDefaultModelId(planType.getDefaultModelId());
        planTypeImpl.setDefaultScenarioId(planType.getDefaultScenarioId());

        return planTypeImpl;
    }

    /**
     * Returns the plan type with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan type
     * @return the plan type
     * @throws com.liferay.portal.NoSuchModelException if a plan type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanType findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the plan type with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanTypeException} if it could not be found.
     *
     * @param planTypeId the primary key of the plan type
     * @return the plan type
     * @throws com.ext.portlet.plans.NoSuchPlanTypeException if a plan type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanType findByPrimaryKey(long planTypeId)
        throws NoSuchPlanTypeException, SystemException {
        PlanType planType = fetchByPrimaryKey(planTypeId);

        if (planType == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + planTypeId);
            }

            throw new NoSuchPlanTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                planTypeId);
        }

        return planType;
    }

    /**
     * Returns the plan type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan type
     * @return the plan type, or <code>null</code> if a plan type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanType fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey(((Long) primaryKey).longValue());
    }

    /**
     * Returns the plan type with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param planTypeId the primary key of the plan type
     * @return the plan type, or <code>null</code> if a plan type with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanType fetchByPrimaryKey(long planTypeId)
        throws SystemException {
        PlanType planType = (PlanType) EntityCacheUtil.getResult(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
                PlanTypeImpl.class, planTypeId);

        if (planType == _nullPlanType) {
            return null;
        }

        if (planType == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                planType = (PlanType) session.get(PlanTypeImpl.class,
                        Long.valueOf(planTypeId));
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (planType != null) {
                    cacheResult(planType);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(PlanTypeModelImpl.ENTITY_CACHE_ENABLED,
                        PlanTypeImpl.class, planTypeId, _nullPlanType);
                }

                closeSession(session);
            }
        }

        return planType;
    }

    /**
     * Returns the plan type where isDefault = &#63; or throws a {@link com.ext.portlet.plans.NoSuchPlanTypeException} if it could not be found.
     *
     * @param isDefault the is default
     * @return the matching plan type
     * @throws com.ext.portlet.plans.NoSuchPlanTypeException if a matching plan type could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanType findBydefault(boolean isDefault)
        throws NoSuchPlanTypeException, SystemException {
        PlanType planType = fetchBydefault(isDefault);

        if (planType == null) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("isDefault=");
            msg.append(isDefault);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            if (_log.isWarnEnabled()) {
                _log.warn(msg.toString());
            }

            throw new NoSuchPlanTypeException(msg.toString());
        }

        return planType;
    }

    /**
     * Returns the plan type where isDefault = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
     *
     * @param isDefault the is default
     * @return the matching plan type, or <code>null</code> if a matching plan type could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanType fetchBydefault(boolean isDefault) throws SystemException {
        return fetchBydefault(isDefault, true);
    }

    /**
     * Returns the plan type where isDefault = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
     *
     * @param isDefault the is default
     * @param retrieveFromCache whether to use the finder cache
     * @return the matching plan type, or <code>null</code> if a matching plan type could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanType fetchBydefault(boolean isDefault, boolean retrieveFromCache)
        throws SystemException {
        Object[] finderArgs = new Object[] { isDefault };

        Object result = null;

        if (retrieveFromCache) {
            result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_DEFAULT,
                    finderArgs, this);
        }

        if (result == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_SELECT_PLANTYPE_WHERE);

            query.append(_FINDER_COLUMN_DEFAULT_ISDEFAULT_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(isDefault);

                List<PlanType> list = q.list();

                result = list;

                PlanType planType = null;

                if (list.isEmpty()) {
                    FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEFAULT,
                        finderArgs, list);
                } else {
                    planType = list.get(0);

                    cacheResult(planType);

                    if ((planType.getIsDefault() != isDefault)) {
                        FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_DEFAULT,
                            finderArgs, planType);
                    }
                }

                return planType;
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (result == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_DEFAULT,
                        finderArgs);
                }

                closeSession(session);
            }
        } else {
            if (result instanceof List<?>) {
                return null;
            } else {
                return (PlanType) result;
            }
        }
    }

    /**
     * Returns all the plan types.
     *
     * @return the plan types
     * @throws SystemException if a system exception occurred
     */
    public List<PlanType> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plan types
     * @param end the upper bound of the range of plan types (not inclusive)
     * @return the range of plan types
     * @throws SystemException if a system exception occurred
     */
    public List<PlanType> findAll(int start, int end) throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan types.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plan types
     * @param end the upper bound of the range of plan types (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan types
     * @throws SystemException if a system exception occurred
     */
    public List<PlanType> findAll(int start, int end,
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

        List<PlanType> list = (List<PlanType>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANTYPE);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANTYPE;
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<PlanType>) QueryUtil.list(q, getDialect(),
                            start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanType>) QueryUtil.list(q, getDialect(),
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
     * Removes the plan type where isDefault = &#63; from the database.
     *
     * @param isDefault the is default
     * @throws SystemException if a system exception occurred
     */
    public void removeBydefault(boolean isDefault)
        throws NoSuchPlanTypeException, SystemException {
        PlanType planType = findBydefault(isDefault);

        remove(planType);
    }

    /**
     * Removes all the plan types from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (PlanType planType : findAll()) {
            remove(planType);
        }
    }

    /**
     * Returns the number of plan types where isDefault = &#63;.
     *
     * @param isDefault the is default
     * @return the number of matching plan types
     * @throws SystemException if a system exception occurred
     */
    public int countBydefault(boolean isDefault) throws SystemException {
        Object[] finderArgs = new Object[] { isDefault };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_DEFAULT,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PLANTYPE_WHERE);

            query.append(_FINDER_COLUMN_DEFAULT_ISDEFAULT_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(isDefault);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_DEFAULT,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of plan types.
     *
     * @return the number of plan types
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_PLANTYPE);

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
     * Returns all the plan type attributes associated with the plan type.
     *
     * @param pk the primary key of the plan type
     * @return the plan type attributes associated with the plan type
     * @throws SystemException if a system exception occurred
     */
    public List<com.ext.portlet.plans.model.PlanTypeAttribute> getPlanTypeAttributes(
        long pk) throws SystemException {
        return getPlanTypeAttributes(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    /**
     * Returns a range of all the plan type attributes associated with the plan type.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the plan type
     * @param start the lower bound of the range of plan types
     * @param end the upper bound of the range of plan types (not inclusive)
     * @return the range of plan type attributes associated with the plan type
     * @throws SystemException if a system exception occurred
     */
    public List<com.ext.portlet.plans.model.PlanTypeAttribute> getPlanTypeAttributes(
        long pk, int start, int end) throws SystemException {
        return getPlanTypeAttributes(pk, start, end, null);
    }

    /**
     * Returns an ordered range of all the plan type attributes associated with the plan type.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the plan type
     * @param start the lower bound of the range of plan types
     * @param end the upper bound of the range of plan types (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan type attributes associated with the plan type
     * @throws SystemException if a system exception occurred
     */
    public List<com.ext.portlet.plans.model.PlanTypeAttribute> getPlanTypeAttributes(
        long pk, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

        List<com.ext.portlet.plans.model.PlanTypeAttribute> list = (List<com.ext.portlet.plans.model.PlanTypeAttribute>) FinderCacheUtil.getResult(FINDER_PATH_GET_PLANTYPEATTRIBUTES,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                String sql = null;

                if (orderByComparator != null) {
                    sql = _SQL_GETPLANTYPEATTRIBUTES.concat(ORDER_BY_CLAUSE)
                                                    .concat(orderByComparator.getOrderBy());
                } else {
                    sql = _SQL_GETPLANTYPEATTRIBUTES;
                }

                SQLQuery q = session.createSQLQuery(sql);

                q.addEntity("xcolab_PlanTypeAttribute",
                    com.ext.portlet.plans.model.impl.PlanTypeAttributeImpl.class);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                list = (List<com.ext.portlet.plans.model.PlanTypeAttribute>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_GET_PLANTYPEATTRIBUTES,
                        finderArgs);
                } else {
                    planTypeAttributePersistence.cacheResult(list);

                    FinderCacheUtil.putResult(FINDER_PATH_GET_PLANTYPEATTRIBUTES,
                        finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the number of plan type attributes associated with the plan type.
     *
     * @param pk the primary key of the plan type
     * @return the number of plan type attributes associated with the plan type
     * @throws SystemException if a system exception occurred
     */
    public int getPlanTypeAttributesSize(long pk) throws SystemException {
        Object[] finderArgs = new Object[] { pk };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_GET_PLANTYPEATTRIBUTES_SIZE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                SQLQuery q = session.createSQLQuery(_SQL_GETPLANTYPEATTRIBUTESSIZE);

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

                FinderCacheUtil.putResult(FINDER_PATH_GET_PLANTYPEATTRIBUTES_SIZE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns <code>true</code> if the plan type attribute is associated with the plan type.
     *
     * @param pk the primary key of the plan type
     * @param planTypeAttributePK the primary key of the plan type attribute
     * @return <code>true</code> if the plan type attribute is associated with the plan type; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsPlanTypeAttribute(long pk, long planTypeAttributePK)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, planTypeAttributePK };

        Boolean value = (Boolean) FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_PLANTYPEATTRIBUTE,
                finderArgs, this);

        if (value == null) {
            try {
                value = Boolean.valueOf(containsPlanTypeAttribute.contains(pk,
                            planTypeAttributePK));
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (value == null) {
                    value = Boolean.FALSE;
                }

                FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_PLANTYPEATTRIBUTE,
                    finderArgs, value);
            }
        }

        return value.booleanValue();
    }

    /**
     * Returns <code>true</code> if the plan type has any plan type attributes associated with it.
     *
     * @param pk the primary key of the plan type to check for associations with plan type attributes
     * @return <code>true</code> if the plan type has any plan type attributes associated with it; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsPlanTypeAttributes(long pk)
        throws SystemException {
        if (getPlanTypeAttributesSize(pk) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns all the plan type columns associated with the plan type.
     *
     * @param pk the primary key of the plan type
     * @return the plan type columns associated with the plan type
     * @throws SystemException if a system exception occurred
     */
    public List<com.ext.portlet.plans.model.PlanTypeColumn> getPlanTypeColumns(
        long pk) throws SystemException {
        return getPlanTypeColumns(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    /**
     * Returns a range of all the plan type columns associated with the plan type.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the plan type
     * @param start the lower bound of the range of plan types
     * @param end the upper bound of the range of plan types (not inclusive)
     * @return the range of plan type columns associated with the plan type
     * @throws SystemException if a system exception occurred
     */
    public List<com.ext.portlet.plans.model.PlanTypeColumn> getPlanTypeColumns(
        long pk, int start, int end) throws SystemException {
        return getPlanTypeColumns(pk, start, end, null);
    }

    /**
     * Returns an ordered range of all the plan type columns associated with the plan type.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param pk the primary key of the plan type
     * @param start the lower bound of the range of plan types
     * @param end the upper bound of the range of plan types (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan type columns associated with the plan type
     * @throws SystemException if a system exception occurred
     */
    public List<com.ext.portlet.plans.model.PlanTypeColumn> getPlanTypeColumns(
        long pk, int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

        List<com.ext.portlet.plans.model.PlanTypeColumn> list = (List<com.ext.portlet.plans.model.PlanTypeColumn>) FinderCacheUtil.getResult(FINDER_PATH_GET_PLANTYPECOLUMNS,
                finderArgs, this);

        if (list == null) {
            Session session = null;

            try {
                session = openSession();

                String sql = null;

                if (orderByComparator != null) {
                    sql = _SQL_GETPLANTYPECOLUMNS.concat(ORDER_BY_CLAUSE)
                                                 .concat(orderByComparator.getOrderBy());
                } else {
                    sql = _SQL_GETPLANTYPECOLUMNS.concat(com.ext.portlet.plans.model.impl.PlanTypeColumnModelImpl.ORDER_BY_SQL);
                }

                SQLQuery q = session.createSQLQuery(sql);

                q.addEntity("xcolab_PlanTypeColumn",
                    com.ext.portlet.plans.model.impl.PlanTypeColumnImpl.class);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(pk);

                list = (List<com.ext.portlet.plans.model.PlanTypeColumn>) QueryUtil.list(q,
                        getDialect(), start, end);
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (list == null) {
                    FinderCacheUtil.removeResult(FINDER_PATH_GET_PLANTYPECOLUMNS,
                        finderArgs);
                } else {
                    planTypeColumnPersistence.cacheResult(list);

                    FinderCacheUtil.putResult(FINDER_PATH_GET_PLANTYPECOLUMNS,
                        finderArgs, list);
                }

                closeSession(session);
            }
        }

        return list;
    }

    /**
     * Returns the number of plan type columns associated with the plan type.
     *
     * @param pk the primary key of the plan type
     * @return the number of plan type columns associated with the plan type
     * @throws SystemException if a system exception occurred
     */
    public int getPlanTypeColumnsSize(long pk) throws SystemException {
        Object[] finderArgs = new Object[] { pk };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_GET_PLANTYPECOLUMNS_SIZE,
                finderArgs, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                SQLQuery q = session.createSQLQuery(_SQL_GETPLANTYPECOLUMNSSIZE);

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

                FinderCacheUtil.putResult(FINDER_PATH_GET_PLANTYPECOLUMNS_SIZE,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns <code>true</code> if the plan type column is associated with the plan type.
     *
     * @param pk the primary key of the plan type
     * @param planTypeColumnPK the primary key of the plan type column
     * @return <code>true</code> if the plan type column is associated with the plan type; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsPlanTypeColumn(long pk, long planTypeColumnPK)
        throws SystemException {
        Object[] finderArgs = new Object[] { pk, planTypeColumnPK };

        Boolean value = (Boolean) FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_PLANTYPECOLUMN,
                finderArgs, this);

        if (value == null) {
            try {
                value = Boolean.valueOf(containsPlanTypeColumn.contains(pk,
                            planTypeColumnPK));
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (value == null) {
                    value = Boolean.FALSE;
                }

                FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_PLANTYPECOLUMN,
                    finderArgs, value);
            }
        }

        return value.booleanValue();
    }

    /**
     * Returns <code>true</code> if the plan type has any plan type columns associated with it.
     *
     * @param pk the primary key of the plan type to check for associations with plan type columns
     * @return <code>true</code> if the plan type has any plan type columns associated with it; <code>false</code> otherwise
     * @throws SystemException if a system exception occurred
     */
    public boolean containsPlanTypeColumns(long pk) throws SystemException {
        if (getPlanTypeColumnsSize(pk) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Initializes the plan type persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.plans.model.PlanType")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanType>> listenersList = new ArrayList<ModelListener<PlanType>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanType>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }

        containsPlanTypeAttribute = new ContainsPlanTypeAttribute();

        containsPlanTypeColumn = new ContainsPlanTypeColumn();
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanTypeImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    protected class ContainsPlanTypeAttribute {
        private MappingSqlQuery<Integer> _mappingSqlQuery;

        protected ContainsPlanTypeAttribute() {
            _mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
                    _SQL_CONTAINSPLANTYPEATTRIBUTE,
                    new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
                    RowMapper.COUNT);
        }

        protected boolean contains(long planTypeId, long planTypeAttributeId) {
            List<Integer> results = _mappingSqlQuery.execute(new Object[] {
                        new Long(planTypeId), new Long(planTypeAttributeId)
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

    protected class ContainsPlanTypeColumn {
        private MappingSqlQuery<Integer> _mappingSqlQuery;

        protected ContainsPlanTypeColumn() {
            _mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
                    _SQL_CONTAINSPLANTYPECOLUMN,
                    new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
                    RowMapper.COUNT);
        }

        protected boolean contains(long planTypeId, long planTypeColumnId) {
            List<Integer> results = _mappingSqlQuery.execute(new Object[] {
                        new Long(planTypeId), new Long(planTypeColumnId)
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
