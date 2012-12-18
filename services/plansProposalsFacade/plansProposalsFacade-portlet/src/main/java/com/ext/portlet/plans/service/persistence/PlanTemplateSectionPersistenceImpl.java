package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.NoSuchPlanTemplateSectionException;
import com.ext.portlet.plans.model.PlanTemplateSection;
import com.ext.portlet.plans.model.impl.PlanTemplateSectionImpl;
import com.ext.portlet.plans.model.impl.PlanTemplateSectionModelImpl;
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
 * The persistence implementation for the plan template section service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTemplateSectionPersistence
 * @see PlanTemplateSectionUtil
 * @generated
 */
public class PlanTemplateSectionPersistenceImpl extends BasePersistenceImpl<PlanTemplateSection>
    implements PlanTemplateSectionPersistence {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link PlanTemplateSectionUtil} to access the plan template section persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */
    public static final String FINDER_CLASS_NAME_ENTITY = PlanTemplateSectionImpl.class.getName();
    public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List1";
    public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
        ".List2";
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PLANTEMPLATEID =
        new FinderPath(PlanTemplateSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanTemplateSectionModelImpl.FINDER_CACHE_ENABLED,
            PlanTemplateSectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByPlanTemplateId",
            new String[] {
                Long.class.getName(),
                
            "java.lang.Integer", "java.lang.Integer",
                "com.liferay.portal.kernel.util.OrderByComparator"
            });
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANTEMPLATEID =
        new FinderPath(PlanTemplateSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanTemplateSectionModelImpl.FINDER_CACHE_ENABLED,
            PlanTemplateSectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByPlanTemplateId",
            new String[] { Long.class.getName() },
            PlanTemplateSectionModelImpl.PLANTEMPLATEID_COLUMN_BITMASK);
    public static final FinderPath FINDER_PATH_COUNT_BY_PLANTEMPLATEID = new FinderPath(PlanTemplateSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanTemplateSectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPlanTemplateId",
            new String[] { Long.class.getName() });
    public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PlanTemplateSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanTemplateSectionModelImpl.FINDER_CACHE_ENABLED,
            PlanTemplateSectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PlanTemplateSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanTemplateSectionModelImpl.FINDER_CACHE_ENABLED,
            PlanTemplateSectionImpl.class,
            FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
    public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PlanTemplateSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanTemplateSectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
            FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
    private static final String _SQL_SELECT_PLANTEMPLATESECTION = "SELECT planTemplateSection FROM PlanTemplateSection planTemplateSection";
    private static final String _SQL_SELECT_PLANTEMPLATESECTION_WHERE = "SELECT planTemplateSection FROM PlanTemplateSection planTemplateSection WHERE ";
    private static final String _SQL_COUNT_PLANTEMPLATESECTION = "SELECT COUNT(planTemplateSection) FROM PlanTemplateSection planTemplateSection";
    private static final String _SQL_COUNT_PLANTEMPLATESECTION_WHERE = "SELECT COUNT(planTemplateSection) FROM PlanTemplateSection planTemplateSection WHERE ";
    private static final String _FINDER_COLUMN_PLANTEMPLATEID_PLANTEMPLATEID_2 = "planTemplateSection.id.planTemplateId = ?";
    private static final String _ORDER_BY_ENTITY_ALIAS = "planTemplateSection.";
    private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PlanTemplateSection exists with the primary key ";
    private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PlanTemplateSection exists with the key {";
    private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
                PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
    private static Log _log = LogFactoryUtil.getLog(PlanTemplateSectionPersistenceImpl.class);
    private static PlanTemplateSection _nullPlanTemplateSection = new PlanTemplateSectionImpl() {
            @Override
            public Object clone() {
                return this;
            }

            @Override
            public CacheModel<PlanTemplateSection> toCacheModel() {
                return _nullPlanTemplateSectionCacheModel;
            }
        };

    private static CacheModel<PlanTemplateSection> _nullPlanTemplateSectionCacheModel =
        new CacheModel<PlanTemplateSection>() {
            public PlanTemplateSection toEntityModel() {
                return _nullPlanTemplateSection;
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

    /**
     * Caches the plan template section in the entity cache if it is enabled.
     *
     * @param planTemplateSection the plan template section
     */
    public void cacheResult(PlanTemplateSection planTemplateSection) {
        EntityCacheUtil.putResult(PlanTemplateSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanTemplateSectionImpl.class, planTemplateSection.getPrimaryKey(),
            planTemplateSection);

        planTemplateSection.resetOriginalValues();
    }

    /**
     * Caches the plan template sections in the entity cache if it is enabled.
     *
     * @param planTemplateSections the plan template sections
     */
    public void cacheResult(List<PlanTemplateSection> planTemplateSections) {
        for (PlanTemplateSection planTemplateSection : planTemplateSections) {
            if (EntityCacheUtil.getResult(
                        PlanTemplateSectionModelImpl.ENTITY_CACHE_ENABLED,
                        PlanTemplateSectionImpl.class,
                        planTemplateSection.getPrimaryKey()) == null) {
                cacheResult(planTemplateSection);
            } else {
                planTemplateSection.resetOriginalValues();
            }
        }
    }

    /**
     * Clears the cache for all plan template sections.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache() {
        if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
            CacheRegistryUtil.clear(PlanTemplateSectionImpl.class.getName());
        }

        EntityCacheUtil.clearCache(PlanTemplateSectionImpl.class.getName());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    /**
     * Clears the cache for the plan template section.
     *
     * <p>
     * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
     * </p>
     */
    @Override
    public void clearCache(PlanTemplateSection planTemplateSection) {
        EntityCacheUtil.removeResult(PlanTemplateSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanTemplateSectionImpl.class, planTemplateSection.getPrimaryKey());

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }

    @Override
    public void clearCache(List<PlanTemplateSection> planTemplateSections) {
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

        for (PlanTemplateSection planTemplateSection : planTemplateSections) {
            EntityCacheUtil.removeResult(PlanTemplateSectionModelImpl.ENTITY_CACHE_ENABLED,
                PlanTemplateSectionImpl.class,
                planTemplateSection.getPrimaryKey());
        }
    }

    /**
     * Creates a new plan template section with the primary key. Does not add the plan template section to the database.
     *
     * @param planTemplateSectionPK the primary key for the new plan template section
     * @return the new plan template section
     */
    public PlanTemplateSection create(
        PlanTemplateSectionPK planTemplateSectionPK) {
        PlanTemplateSection planTemplateSection = new PlanTemplateSectionImpl();

        planTemplateSection.setNew(true);
        planTemplateSection.setPrimaryKey(planTemplateSectionPK);

        return planTemplateSection;
    }

    /**
     * Removes the plan template section with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param planTemplateSectionPK the primary key of the plan template section
     * @return the plan template section that was removed
     * @throws com.ext.portlet.plans.NoSuchPlanTemplateSectionException if a plan template section with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTemplateSection remove(
        PlanTemplateSectionPK planTemplateSectionPK)
        throws NoSuchPlanTemplateSectionException, SystemException {
        return remove((Serializable) planTemplateSectionPK);
    }

    /**
     * Removes the plan template section with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param primaryKey the primary key of the plan template section
     * @return the plan template section that was removed
     * @throws com.ext.portlet.plans.NoSuchPlanTemplateSectionException if a plan template section with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTemplateSection remove(Serializable primaryKey)
        throws NoSuchPlanTemplateSectionException, SystemException {
        Session session = null;

        try {
            session = openSession();

            PlanTemplateSection planTemplateSection = (PlanTemplateSection) session.get(PlanTemplateSectionImpl.class,
                    primaryKey);

            if (planTemplateSection == null) {
                if (_log.isWarnEnabled()) {
                    _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
                }

                throw new NoSuchPlanTemplateSectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    primaryKey);
            }

            return remove(planTemplateSection);
        } catch (NoSuchPlanTemplateSectionException nsee) {
            throw nsee;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    protected PlanTemplateSection removeImpl(
        PlanTemplateSection planTemplateSection) throws SystemException {
        planTemplateSection = toUnwrappedModel(planTemplateSection);

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.delete(session, planTemplateSection);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        clearCache(planTemplateSection);

        return planTemplateSection;
    }

    @Override
    public PlanTemplateSection updateImpl(
        com.ext.portlet.plans.model.PlanTemplateSection planTemplateSection,
        boolean merge) throws SystemException {
        planTemplateSection = toUnwrappedModel(planTemplateSection);

        boolean isNew = planTemplateSection.isNew();

        PlanTemplateSectionModelImpl planTemplateSectionModelImpl = (PlanTemplateSectionModelImpl) planTemplateSection;

        Session session = null;

        try {
            session = openSession();

            BatchSessionUtil.update(session, planTemplateSection, merge);

            planTemplateSection.setNew(false);
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }

        FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

        if (isNew || !PlanTemplateSectionModelImpl.COLUMN_BITMASK_ENABLED) {
            FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
        }
        else {
            if ((planTemplateSectionModelImpl.getColumnBitmask() &
                    FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANTEMPLATEID.getColumnBitmask()) != 0) {
                Object[] args = new Object[] {
                        Long.valueOf(planTemplateSectionModelImpl.getOriginalPlanTemplateId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANTEMPLATEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANTEMPLATEID,
                    args);

                args = new Object[] {
                        Long.valueOf(planTemplateSectionModelImpl.getPlanTemplateId())
                    };

                FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PLANTEMPLATEID,
                    args);
                FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANTEMPLATEID,
                    args);
            }
        }

        EntityCacheUtil.putResult(PlanTemplateSectionModelImpl.ENTITY_CACHE_ENABLED,
            PlanTemplateSectionImpl.class, planTemplateSection.getPrimaryKey(),
            planTemplateSection);

        return planTemplateSection;
    }

    protected PlanTemplateSection toUnwrappedModel(
        PlanTemplateSection planTemplateSection) {
        if (planTemplateSection instanceof PlanTemplateSectionImpl) {
            return planTemplateSection;
        }

        PlanTemplateSectionImpl planTemplateSectionImpl = new PlanTemplateSectionImpl();

        planTemplateSectionImpl.setNew(planTemplateSection.isNew());
        planTemplateSectionImpl.setPrimaryKey(planTemplateSection.getPrimaryKey());

        planTemplateSectionImpl.setPlanTemplateId(planTemplateSection.getPlanTemplateId());
        planTemplateSectionImpl.setPlanSectionId(planTemplateSection.getPlanSectionId());
        planTemplateSectionImpl.setWeight(planTemplateSection.getWeight());

        return planTemplateSectionImpl;
    }

    /**
     * Returns the plan template section with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
     *
     * @param primaryKey the primary key of the plan template section
     * @return the plan template section
     * @throws com.liferay.portal.NoSuchModelException if a plan template section with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTemplateSection findByPrimaryKey(Serializable primaryKey)
        throws NoSuchModelException, SystemException {
        return findByPrimaryKey((PlanTemplateSectionPK) primaryKey);
    }

    /**
     * Returns the plan template section with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanTemplateSectionException} if it could not be found.
     *
     * @param planTemplateSectionPK the primary key of the plan template section
     * @return the plan template section
     * @throws com.ext.portlet.plans.NoSuchPlanTemplateSectionException if a plan template section with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTemplateSection findByPrimaryKey(
        PlanTemplateSectionPK planTemplateSectionPK)
        throws NoSuchPlanTemplateSectionException, SystemException {
        PlanTemplateSection planTemplateSection = fetchByPrimaryKey(planTemplateSectionPK);

        if (planTemplateSection == null) {
            if (_log.isWarnEnabled()) {
                _log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                    planTemplateSectionPK);
            }

            throw new NoSuchPlanTemplateSectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
                planTemplateSectionPK);
        }

        return planTemplateSection;
    }

    /**
     * Returns the plan template section with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param primaryKey the primary key of the plan template section
     * @return the plan template section, or <code>null</code> if a plan template section with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    @Override
    public PlanTemplateSection fetchByPrimaryKey(Serializable primaryKey)
        throws SystemException {
        return fetchByPrimaryKey((PlanTemplateSectionPK) primaryKey);
    }

    /**
     * Returns the plan template section with the primary key or returns <code>null</code> if it could not be found.
     *
     * @param planTemplateSectionPK the primary key of the plan template section
     * @return the plan template section, or <code>null</code> if a plan template section with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTemplateSection fetchByPrimaryKey(
        PlanTemplateSectionPK planTemplateSectionPK) throws SystemException {
        PlanTemplateSection planTemplateSection = (PlanTemplateSection) EntityCacheUtil.getResult(PlanTemplateSectionModelImpl.ENTITY_CACHE_ENABLED,
                PlanTemplateSectionImpl.class, planTemplateSectionPK);

        if (planTemplateSection == _nullPlanTemplateSection) {
            return null;
        }

        if (planTemplateSection == null) {
            Session session = null;

            boolean hasException = false;

            try {
                session = openSession();

                planTemplateSection = (PlanTemplateSection) session.get(PlanTemplateSectionImpl.class,
                        planTemplateSectionPK);
            } catch (Exception e) {
                hasException = true;

                throw processException(e);
            } finally {
                if (planTemplateSection != null) {
                    cacheResult(planTemplateSection);
                } else if (!hasException) {
                    EntityCacheUtil.putResult(PlanTemplateSectionModelImpl.ENTITY_CACHE_ENABLED,
                        PlanTemplateSectionImpl.class, planTemplateSectionPK,
                        _nullPlanTemplateSection);
                }

                closeSession(session);
            }
        }

        return planTemplateSection;
    }

    /**
     * Returns all the plan template sections where planTemplateId = &#63;.
     *
     * @param planTemplateId the plan template ID
     * @return the matching plan template sections
     * @throws SystemException if a system exception occurred
     */
    public List<PlanTemplateSection> findByPlanTemplateId(long planTemplateId)
        throws SystemException {
        return findByPlanTemplateId(planTemplateId, QueryUtil.ALL_POS,
            QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan template sections where planTemplateId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param planTemplateId the plan template ID
     * @param start the lower bound of the range of plan template sections
     * @param end the upper bound of the range of plan template sections (not inclusive)
     * @return the range of matching plan template sections
     * @throws SystemException if a system exception occurred
     */
    public List<PlanTemplateSection> findByPlanTemplateId(long planTemplateId,
        int start, int end) throws SystemException {
        return findByPlanTemplateId(planTemplateId, start, end, null);
    }

    /**
     * Returns an ordered range of all the plan template sections where planTemplateId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param planTemplateId the plan template ID
     * @param start the lower bound of the range of plan template sections
     * @param end the upper bound of the range of plan template sections (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching plan template sections
     * @throws SystemException if a system exception occurred
     */
    public List<PlanTemplateSection> findByPlanTemplateId(long planTemplateId,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        FinderPath finderPath = null;
        Object[] finderArgs = null;

        if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
                (orderByComparator == null)) {
            finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PLANTEMPLATEID;
            finderArgs = new Object[] { planTemplateId };
        } else {
            finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PLANTEMPLATEID;
            finderArgs = new Object[] {
                    planTemplateId,
                    
                    start, end, orderByComparator
                };
        }

        List<PlanTemplateSection> list = (List<PlanTemplateSection>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;

            if (orderByComparator != null) {
                query = new StringBundler(3 +
                        (orderByComparator.getOrderByFields().length * 3));
            } else {
                query = new StringBundler(3);
            }

            query.append(_SQL_SELECT_PLANTEMPLATESECTION_WHERE);

            query.append(_FINDER_COLUMN_PLANTEMPLATEID_PLANTEMPLATEID_2);

            if (orderByComparator != null) {
                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);
            }
            else {
                query.append(PlanTemplateSectionModelImpl.ORDER_BY_JPQL);
            }

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planTemplateId);

                list = (List<PlanTemplateSection>) QueryUtil.list(q,
                        getDialect(), start, end);
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
     * Returns the first plan template section in the ordered set where planTemplateId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param planTemplateId the plan template ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the first matching plan template section
     * @throws com.ext.portlet.plans.NoSuchPlanTemplateSectionException if a matching plan template section could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTemplateSection findByPlanTemplateId_First(long planTemplateId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanTemplateSectionException, SystemException {
        List<PlanTemplateSection> list = findByPlanTemplateId(planTemplateId,
                0, 1, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("planTemplateId=");
            msg.append(planTemplateId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanTemplateSectionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the last plan template section in the ordered set where planTemplateId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param planTemplateId the plan template ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the last matching plan template section
     * @throws com.ext.portlet.plans.NoSuchPlanTemplateSectionException if a matching plan template section could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTemplateSection findByPlanTemplateId_Last(long planTemplateId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanTemplateSectionException, SystemException {
        int count = countByPlanTemplateId(planTemplateId);

        List<PlanTemplateSection> list = findByPlanTemplateId(planTemplateId,
                count - 1, count, orderByComparator);

        if (list.isEmpty()) {
            StringBundler msg = new StringBundler(4);

            msg.append(_NO_SUCH_ENTITY_WITH_KEY);

            msg.append("planTemplateId=");
            msg.append(planTemplateId);

            msg.append(StringPool.CLOSE_CURLY_BRACE);

            throw new NoSuchPlanTemplateSectionException(msg.toString());
        } else {
            return list.get(0);
        }
    }

    /**
     * Returns the plan template sections before and after the current plan template section in the ordered set where planTemplateId = &#63;.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param planTemplateSectionPK the primary key of the current plan template section
     * @param planTemplateId the plan template ID
     * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
     * @return the previous, current, and next plan template section
     * @throws com.ext.portlet.plans.NoSuchPlanTemplateSectionException if a plan template section with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlanTemplateSection[] findByPlanTemplateId_PrevAndNext(
        PlanTemplateSectionPK planTemplateSectionPK, long planTemplateId,
        OrderByComparator orderByComparator)
        throws NoSuchPlanTemplateSectionException, SystemException {
        PlanTemplateSection planTemplateSection = findByPrimaryKey(planTemplateSectionPK);

        Session session = null;

        try {
            session = openSession();

            PlanTemplateSection[] array = new PlanTemplateSectionImpl[3];

            array[0] = getByPlanTemplateId_PrevAndNext(session,
                    planTemplateSection, planTemplateId, orderByComparator, true);

            array[1] = planTemplateSection;

            array[2] = getByPlanTemplateId_PrevAndNext(session,
                    planTemplateSection, planTemplateId, orderByComparator,
                    false);

            return array;
        } catch (Exception e) {
            throw processException(e);
        } finally {
            closeSession(session);
        }
    }

    protected PlanTemplateSection getByPlanTemplateId_PrevAndNext(
        Session session, PlanTemplateSection planTemplateSection,
        long planTemplateId, OrderByComparator orderByComparator,
        boolean previous) {
        StringBundler query = null;

        if (orderByComparator != null) {
            query = new StringBundler(6 +
                    (orderByComparator.getOrderByFields().length * 6));
        } else {
            query = new StringBundler(3);
        }

        query.append(_SQL_SELECT_PLANTEMPLATESECTION_WHERE);

        query.append(_FINDER_COLUMN_PLANTEMPLATEID_PLANTEMPLATEID_2);

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
        else {
            query.append(PlanTemplateSectionModelImpl.ORDER_BY_JPQL);
        }

        String sql = query.toString();

        Query q = session.createQuery(sql);

        q.setFirstResult(0);
        q.setMaxResults(2);

        QueryPos qPos = QueryPos.getInstance(q);

        qPos.add(planTemplateId);

        if (orderByComparator != null) {
            Object[] values = orderByComparator.getOrderByConditionValues(planTemplateSection);

            for (Object value : values) {
                qPos.add(value);
            }
        }

        List<PlanTemplateSection> list = q.list();

        if (list.size() == 2) {
            return list.get(1);
        } else {
            return null;
        }
    }

    /**
     * Returns all the plan template sections.
     *
     * @return the plan template sections
     * @throws SystemException if a system exception occurred
     */
    public List<PlanTemplateSection> findAll() throws SystemException {
        return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    /**
     * Returns a range of all the plan template sections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plan template sections
     * @param end the upper bound of the range of plan template sections (not inclusive)
     * @return the range of plan template sections
     * @throws SystemException if a system exception occurred
     */
    public List<PlanTemplateSection> findAll(int start, int end)
        throws SystemException {
        return findAll(start, end, null);
    }

    /**
     * Returns an ordered range of all the plan template sections.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plan template sections
     * @param end the upper bound of the range of plan template sections (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of plan template sections
     * @throws SystemException if a system exception occurred
     */
    public List<PlanTemplateSection> findAll(int start, int end,
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

        List<PlanTemplateSection> list = (List<PlanTemplateSection>) FinderCacheUtil.getResult(finderPath,
                finderArgs, this);

        if (list == null) {
            StringBundler query = null;
            String sql = null;

            if (orderByComparator != null) {
                query = new StringBundler(2 +
                        (orderByComparator.getOrderByFields().length * 3));

                query.append(_SQL_SELECT_PLANTEMPLATESECTION);

                appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
                    orderByComparator);

                sql = query.toString();
            } else {
                sql = _SQL_SELECT_PLANTEMPLATESECTION.concat(PlanTemplateSectionModelImpl.ORDER_BY_JPQL);
            }

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                if (orderByComparator == null) {
                    list = (List<PlanTemplateSection>) QueryUtil.list(q,
                            getDialect(), start, end, false);

                    Collections.sort(list);
                } else {
                    list = (List<PlanTemplateSection>) QueryUtil.list(q,
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
     * Removes all the plan template sections where planTemplateId = &#63; from the database.
     *
     * @param planTemplateId the plan template ID
     * @throws SystemException if a system exception occurred
     */
    public void removeByPlanTemplateId(long planTemplateId)
        throws SystemException {
        for (PlanTemplateSection planTemplateSection : findByPlanTemplateId(
                planTemplateId)) {
            remove(planTemplateSection);
        }
    }

    /**
     * Removes all the plan template sections from the database.
     *
     * @throws SystemException if a system exception occurred
     */
    public void removeAll() throws SystemException {
        for (PlanTemplateSection planTemplateSection : findAll()) {
            remove(planTemplateSection);
        }
    }

    /**
     * Returns the number of plan template sections where planTemplateId = &#63;.
     *
     * @param planTemplateId the plan template ID
     * @return the number of matching plan template sections
     * @throws SystemException if a system exception occurred
     */
    public int countByPlanTemplateId(long planTemplateId)
        throws SystemException {
        Object[] finderArgs = new Object[] { planTemplateId };

        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_PLANTEMPLATEID,
                finderArgs, this);

        if (count == null) {
            StringBundler query = new StringBundler(2);

            query.append(_SQL_COUNT_PLANTEMPLATESECTION_WHERE);

            query.append(_FINDER_COLUMN_PLANTEMPLATEID_PLANTEMPLATEID_2);

            String sql = query.toString();

            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(sql);

                QueryPos qPos = QueryPos.getInstance(q);

                qPos.add(planTemplateId);

                count = (Long) q.uniqueResult();
            } catch (Exception e) {
                throw processException(e);
            } finally {
                if (count == null) {
                    count = Long.valueOf(0);
                }

                FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_PLANTEMPLATEID,
                    finderArgs, count);

                closeSession(session);
            }
        }

        return count.intValue();
    }

    /**
     * Returns the number of plan template sections.
     *
     * @return the number of plan template sections
     * @throws SystemException if a system exception occurred
     */
    public int countAll() throws SystemException {
        Long count = (Long) FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
                FINDER_ARGS_EMPTY, this);

        if (count == null) {
            Session session = null;

            try {
                session = openSession();

                Query q = session.createQuery(_SQL_COUNT_PLANTEMPLATESECTION);

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
     * Initializes the plan template section persistence.
     */
    public void afterPropertiesSet() {
        String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
                    com.liferay.util.service.ServiceProps.get(
                        "value.object.listener.com.ext.portlet.plans.model.PlanTemplateSection")));

        if (listenerClassNames.length > 0) {
            try {
                List<ModelListener<PlanTemplateSection>> listenersList = new ArrayList<ModelListener<PlanTemplateSection>>();

                for (String listenerClassName : listenerClassNames) {
                    listenersList.add((ModelListener<PlanTemplateSection>) InstanceFactory.newInstance(
                            listenerClassName));
                }

                listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
            } catch (Exception e) {
                _log.error(e);
            }
        }
    }

    public void destroy() {
        EntityCacheUtil.removeCache(PlanTemplateSectionImpl.class.getName());
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
        FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
    }
}
