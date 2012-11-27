package com.ext.portlet.plans.service.base;

import com.ext.portlet.plans.model.PlansFilterPosition;
import com.ext.portlet.plans.service.PlanAttributeFilterLocalService;
import com.ext.portlet.plans.service.PlanAttributeFilterService;
import com.ext.portlet.plans.service.PlanAttributeLocalService;
import com.ext.portlet.plans.service.PlanAttributeService;
import com.ext.portlet.plans.service.PlanColumnSettingsLocalService;
import com.ext.portlet.plans.service.PlanColumnSettingsService;
import com.ext.portlet.plans.service.PlanDescriptionLocalService;
import com.ext.portlet.plans.service.PlanDescriptionService;
import com.ext.portlet.plans.service.PlanFanLocalService;
import com.ext.portlet.plans.service.PlanFanService;
import com.ext.portlet.plans.service.PlanItemLocalService;
import com.ext.portlet.plans.service.PlanItemService;
import com.ext.portlet.plans.service.PlanMetaLocalService;
import com.ext.portlet.plans.service.PlanMetaService;
import com.ext.portlet.plans.service.PlanModelRunLocalService;
import com.ext.portlet.plans.service.PlanModelRunService;
import com.ext.portlet.plans.service.PlanPositionItemLocalService;
import com.ext.portlet.plans.service.PlanPositionItemService;
import com.ext.portlet.plans.service.PlanPositionLocalService;
import com.ext.portlet.plans.service.PlanPositionService;
import com.ext.portlet.plans.service.PlanPositionsLocalService;
import com.ext.portlet.plans.service.PlanPositionsService;
import com.ext.portlet.plans.service.PlanPropertyFilterLocalService;
import com.ext.portlet.plans.service.PlanPropertyFilterService;
import com.ext.portlet.plans.service.PlanRelatedLocalService;
import com.ext.portlet.plans.service.PlanRelatedService;
import com.ext.portlet.plans.service.PlanSectionDefinitionLocalService;
import com.ext.portlet.plans.service.PlanSectionDefinitionService;
import com.ext.portlet.plans.service.PlanSectionLocalService;
import com.ext.portlet.plans.service.PlanSectionPlanMapLocalService;
import com.ext.portlet.plans.service.PlanSectionPlanMapService;
import com.ext.portlet.plans.service.PlanSectionService;
import com.ext.portlet.plans.service.PlanTeamHistoryLocalService;
import com.ext.portlet.plans.service.PlanTeamHistoryService;
import com.ext.portlet.plans.service.PlanTemplateLocalService;
import com.ext.portlet.plans.service.PlanTemplateSectionLocalService;
import com.ext.portlet.plans.service.PlanTemplateSectionService;
import com.ext.portlet.plans.service.PlanTemplateService;
import com.ext.portlet.plans.service.PlanTypeAttributeLocalService;
import com.ext.portlet.plans.service.PlanTypeAttributeService;
import com.ext.portlet.plans.service.PlanTypeColumnLocalService;
import com.ext.portlet.plans.service.PlanTypeColumnService;
import com.ext.portlet.plans.service.PlanTypeLocalService;
import com.ext.portlet.plans.service.PlanTypeService;
import com.ext.portlet.plans.service.PlanVoteLocalService;
import com.ext.portlet.plans.service.PlanVoteService;
import com.ext.portlet.plans.service.PlansFilterLocalService;
import com.ext.portlet.plans.service.PlansFilterPositionLocalService;
import com.ext.portlet.plans.service.PlansFilterPositionService;
import com.ext.portlet.plans.service.PlansFilterService;
import com.ext.portlet.plans.service.PlansUserSettingsLocalService;
import com.ext.portlet.plans.service.PlansUserSettingsService;
import com.ext.portlet.plans.service.persistence.PlanAttributeFilterPersistence;
import com.ext.portlet.plans.service.persistence.PlanAttributePersistence;
import com.ext.portlet.plans.service.persistence.PlanColumnSettingsPersistence;
import com.ext.portlet.plans.service.persistence.PlanDescriptionPersistence;
import com.ext.portlet.plans.service.persistence.PlanFanPersistence;
import com.ext.portlet.plans.service.persistence.PlanItemFinder;
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
import com.ext.portlet.plans.service.persistence.PlansFilterPositionPK;
import com.ext.portlet.plans.service.persistence.PlansFilterPositionPersistence;
import com.ext.portlet.plans.service.persistence.PlansUserSettingsPersistence;

import com.liferay.counter.service.CounterLocalService;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.IdentifiableBean;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdate;
import com.liferay.portal.kernel.dao.jdbc.SqlUpdateFactoryUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.PersistedModel;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;
import com.liferay.portal.service.ResourceLocalService;
import com.liferay.portal.service.ResourceService;
import com.liferay.portal.service.UserLocalService;
import com.liferay.portal.service.UserService;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;

import java.io.Serializable;

import java.util.List;

import javax.sql.DataSource;

/**
 * The base implementation of the plans filter position local service.
 *
 * <p>
 * This implementation exists only as a container for the default service methods generated by ServiceBuilder. All custom service methods should be put in {@link com.ext.portlet.plans.service.impl.PlansFilterPositionLocalServiceImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.plans.service.impl.PlansFilterPositionLocalServiceImpl
 * @see com.ext.portlet.plans.service.PlansFilterPositionLocalServiceUtil
 * @generated
 */
public abstract class PlansFilterPositionLocalServiceBaseImpl
    implements PlansFilterPositionLocalService, IdentifiableBean {
    private static Log _log = LogFactoryUtil.getLog(PlansFilterPositionLocalServiceBaseImpl.class);
    @BeanReference(type = PlanAttributeLocalService.class)
    protected PlanAttributeLocalService planAttributeLocalService;
    @BeanReference(type = PlanAttributeService.class)
    protected PlanAttributeService planAttributeService;
    @BeanReference(type = PlanAttributePersistence.class)
    protected PlanAttributePersistence planAttributePersistence;
    @BeanReference(type = PlanAttributeFilterLocalService.class)
    protected PlanAttributeFilterLocalService planAttributeFilterLocalService;
    @BeanReference(type = PlanAttributeFilterService.class)
    protected PlanAttributeFilterService planAttributeFilterService;
    @BeanReference(type = PlanAttributeFilterPersistence.class)
    protected PlanAttributeFilterPersistence planAttributeFilterPersistence;
    @BeanReference(type = PlanColumnSettingsLocalService.class)
    protected PlanColumnSettingsLocalService planColumnSettingsLocalService;
    @BeanReference(type = PlanColumnSettingsService.class)
    protected PlanColumnSettingsService planColumnSettingsService;
    @BeanReference(type = PlanColumnSettingsPersistence.class)
    protected PlanColumnSettingsPersistence planColumnSettingsPersistence;
    @BeanReference(type = PlanDescriptionLocalService.class)
    protected PlanDescriptionLocalService planDescriptionLocalService;
    @BeanReference(type = PlanDescriptionService.class)
    protected PlanDescriptionService planDescriptionService;
    @BeanReference(type = PlanDescriptionPersistence.class)
    protected PlanDescriptionPersistence planDescriptionPersistence;
    @BeanReference(type = PlanFanLocalService.class)
    protected PlanFanLocalService planFanLocalService;
    @BeanReference(type = PlanFanService.class)
    protected PlanFanService planFanService;
    @BeanReference(type = PlanFanPersistence.class)
    protected PlanFanPersistence planFanPersistence;
    @BeanReference(type = PlanItemLocalService.class)
    protected PlanItemLocalService planItemLocalService;
    @BeanReference(type = PlanItemService.class)
    protected PlanItemService planItemService;
    @BeanReference(type = PlanItemPersistence.class)
    protected PlanItemPersistence planItemPersistence;
    @BeanReference(type = PlanItemFinder.class)
    protected PlanItemFinder planItemFinder;
    @BeanReference(type = PlanMetaLocalService.class)
    protected PlanMetaLocalService planMetaLocalService;
    @BeanReference(type = PlanMetaService.class)
    protected PlanMetaService planMetaService;
    @BeanReference(type = PlanMetaPersistence.class)
    protected PlanMetaPersistence planMetaPersistence;
    @BeanReference(type = PlanModelRunLocalService.class)
    protected PlanModelRunLocalService planModelRunLocalService;
    @BeanReference(type = PlanModelRunService.class)
    protected PlanModelRunService planModelRunService;
    @BeanReference(type = PlanModelRunPersistence.class)
    protected PlanModelRunPersistence planModelRunPersistence;
    @BeanReference(type = PlanPositionLocalService.class)
    protected PlanPositionLocalService planPositionLocalService;
    @BeanReference(type = PlanPositionService.class)
    protected PlanPositionService planPositionService;
    @BeanReference(type = PlanPositionPersistence.class)
    protected PlanPositionPersistence planPositionPersistence;
    @BeanReference(type = PlanPositionItemLocalService.class)
    protected PlanPositionItemLocalService planPositionItemLocalService;
    @BeanReference(type = PlanPositionItemService.class)
    protected PlanPositionItemService planPositionItemService;
    @BeanReference(type = PlanPositionItemPersistence.class)
    protected PlanPositionItemPersistence planPositionItemPersistence;
    @BeanReference(type = PlanPositionsLocalService.class)
    protected PlanPositionsLocalService planPositionsLocalService;
    @BeanReference(type = PlanPositionsService.class)
    protected PlanPositionsService planPositionsService;
    @BeanReference(type = PlanPositionsPersistence.class)
    protected PlanPositionsPersistence planPositionsPersistence;
    @BeanReference(type = PlanPropertyFilterLocalService.class)
    protected PlanPropertyFilterLocalService planPropertyFilterLocalService;
    @BeanReference(type = PlanPropertyFilterService.class)
    protected PlanPropertyFilterService planPropertyFilterService;
    @BeanReference(type = PlanPropertyFilterPersistence.class)
    protected PlanPropertyFilterPersistence planPropertyFilterPersistence;
    @BeanReference(type = PlanRelatedLocalService.class)
    protected PlanRelatedLocalService planRelatedLocalService;
    @BeanReference(type = PlanRelatedService.class)
    protected PlanRelatedService planRelatedService;
    @BeanReference(type = PlanRelatedPersistence.class)
    protected PlanRelatedPersistence planRelatedPersistence;
    @BeanReference(type = PlanSectionLocalService.class)
    protected PlanSectionLocalService planSectionLocalService;
    @BeanReference(type = PlanSectionService.class)
    protected PlanSectionService planSectionService;
    @BeanReference(type = PlanSectionPersistence.class)
    protected PlanSectionPersistence planSectionPersistence;
    @BeanReference(type = PlanSectionDefinitionLocalService.class)
    protected PlanSectionDefinitionLocalService planSectionDefinitionLocalService;
    @BeanReference(type = PlanSectionDefinitionService.class)
    protected PlanSectionDefinitionService planSectionDefinitionService;
    @BeanReference(type = PlanSectionDefinitionPersistence.class)
    protected PlanSectionDefinitionPersistence planSectionDefinitionPersistence;
    @BeanReference(type = PlanSectionPlanMapLocalService.class)
    protected PlanSectionPlanMapLocalService planSectionPlanMapLocalService;
    @BeanReference(type = PlanSectionPlanMapService.class)
    protected PlanSectionPlanMapService planSectionPlanMapService;
    @BeanReference(type = PlanSectionPlanMapPersistence.class)
    protected PlanSectionPlanMapPersistence planSectionPlanMapPersistence;
    @BeanReference(type = PlansFilterLocalService.class)
    protected PlansFilterLocalService plansFilterLocalService;
    @BeanReference(type = PlansFilterService.class)
    protected PlansFilterService plansFilterService;
    @BeanReference(type = PlansFilterPersistence.class)
    protected PlansFilterPersistence plansFilterPersistence;
    @BeanReference(type = PlansFilterPositionLocalService.class)
    protected PlansFilterPositionLocalService plansFilterPositionLocalService;
    @BeanReference(type = PlansFilterPositionService.class)
    protected PlansFilterPositionService plansFilterPositionService;
    @BeanReference(type = PlansFilterPositionPersistence.class)
    protected PlansFilterPositionPersistence plansFilterPositionPersistence;
    @BeanReference(type = PlansUserSettingsLocalService.class)
    protected PlansUserSettingsLocalService plansUserSettingsLocalService;
    @BeanReference(type = PlansUserSettingsService.class)
    protected PlansUserSettingsService plansUserSettingsService;
    @BeanReference(type = PlansUserSettingsPersistence.class)
    protected PlansUserSettingsPersistence plansUserSettingsPersistence;
    @BeanReference(type = PlanTeamHistoryLocalService.class)
    protected PlanTeamHistoryLocalService planTeamHistoryLocalService;
    @BeanReference(type = PlanTeamHistoryService.class)
    protected PlanTeamHistoryService planTeamHistoryService;
    @BeanReference(type = PlanTeamHistoryPersistence.class)
    protected PlanTeamHistoryPersistence planTeamHistoryPersistence;
    @BeanReference(type = PlanTemplateLocalService.class)
    protected PlanTemplateLocalService planTemplateLocalService;
    @BeanReference(type = PlanTemplateService.class)
    protected PlanTemplateService planTemplateService;
    @BeanReference(type = PlanTemplatePersistence.class)
    protected PlanTemplatePersistence planTemplatePersistence;
    @BeanReference(type = PlanTemplateSectionLocalService.class)
    protected PlanTemplateSectionLocalService planTemplateSectionLocalService;
    @BeanReference(type = PlanTemplateSectionService.class)
    protected PlanTemplateSectionService planTemplateSectionService;
    @BeanReference(type = PlanTemplateSectionPersistence.class)
    protected PlanTemplateSectionPersistence planTemplateSectionPersistence;
    @BeanReference(type = PlanTypeLocalService.class)
    protected PlanTypeLocalService planTypeLocalService;
    @BeanReference(type = PlanTypeService.class)
    protected PlanTypeService planTypeService;
    @BeanReference(type = PlanTypePersistence.class)
    protected PlanTypePersistence planTypePersistence;
    @BeanReference(type = PlanTypeAttributeLocalService.class)
    protected PlanTypeAttributeLocalService planTypeAttributeLocalService;
    @BeanReference(type = PlanTypeAttributeService.class)
    protected PlanTypeAttributeService planTypeAttributeService;
    @BeanReference(type = PlanTypeAttributePersistence.class)
    protected PlanTypeAttributePersistence planTypeAttributePersistence;
    @BeanReference(type = PlanTypeColumnLocalService.class)
    protected PlanTypeColumnLocalService planTypeColumnLocalService;
    @BeanReference(type = PlanTypeColumnService.class)
    protected PlanTypeColumnService planTypeColumnService;
    @BeanReference(type = PlanTypeColumnPersistence.class)
    protected PlanTypeColumnPersistence planTypeColumnPersistence;
    @BeanReference(type = PlanVoteLocalService.class)
    protected PlanVoteLocalService planVoteLocalService;
    @BeanReference(type = PlanVoteService.class)
    protected PlanVoteService planVoteService;
    @BeanReference(type = PlanVotePersistence.class)
    protected PlanVotePersistence planVotePersistence;
    @BeanReference(type = CounterLocalService.class)
    protected CounterLocalService counterLocalService;
    @BeanReference(type = ResourceLocalService.class)
    protected ResourceLocalService resourceLocalService;
    @BeanReference(type = ResourceService.class)
    protected ResourceService resourceService;
    @BeanReference(type = ResourcePersistence.class)
    protected ResourcePersistence resourcePersistence;
    @BeanReference(type = UserLocalService.class)
    protected UserLocalService userLocalService;
    @BeanReference(type = UserService.class)
    protected UserService userService;
    @BeanReference(type = UserPersistence.class)
    protected UserPersistence userPersistence;
    private String _beanIdentifier;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this class directly. Always use {@link com.ext.portlet.plans.service.PlansFilterPositionLocalServiceUtil} to access the plans filter position local service.
     */

    /**
     * Adds the plans filter position to the database. Also notifies the appropriate model listeners.
     *
     * @param plansFilterPosition the plans filter position
     * @return the plans filter position that was added
     * @throws SystemException if a system exception occurred
     */
    public PlansFilterPosition addPlansFilterPosition(
        PlansFilterPosition plansFilterPosition) throws SystemException {
        plansFilterPosition.setNew(true);

        plansFilterPosition = plansFilterPositionPersistence.update(plansFilterPosition,
                false);

        Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

        if (indexer != null) {
            try {
                indexer.reindex(plansFilterPosition);
            } catch (SearchException se) {
                if (_log.isWarnEnabled()) {
                    _log.warn(se, se);
                }
            }
        }

        return plansFilterPosition;
    }

    /**
     * Creates a new plans filter position with the primary key. Does not add the plans filter position to the database.
     *
     * @param plansFilterPositionPK the primary key for the new plans filter position
     * @return the new plans filter position
     */
    public PlansFilterPosition createPlansFilterPosition(
        PlansFilterPositionPK plansFilterPositionPK) {
        return plansFilterPositionPersistence.create(plansFilterPositionPK);
    }

    /**
     * Deletes the plans filter position with the primary key from the database. Also notifies the appropriate model listeners.
     *
     * @param plansFilterPositionPK the primary key of the plans filter position
     * @throws PortalException if a plans filter position with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public void deletePlansFilterPosition(
        PlansFilterPositionPK plansFilterPositionPK)
        throws PortalException, SystemException {
        PlansFilterPosition plansFilterPosition = plansFilterPositionPersistence.remove(plansFilterPositionPK);

        Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

        if (indexer != null) {
            try {
                indexer.delete(plansFilterPosition);
            } catch (SearchException se) {
                if (_log.isWarnEnabled()) {
                    _log.warn(se, se);
                }
            }
        }
    }

    /**
     * Deletes the plans filter position from the database. Also notifies the appropriate model listeners.
     *
     * @param plansFilterPosition the plans filter position
     * @throws SystemException if a system exception occurred
     */
    public void deletePlansFilterPosition(
        PlansFilterPosition plansFilterPosition) throws SystemException {
        plansFilterPositionPersistence.remove(plansFilterPosition);

        Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

        if (indexer != null) {
            try {
                indexer.delete(plansFilterPosition);
            } catch (SearchException se) {
                if (_log.isWarnEnabled()) {
                    _log.warn(se, se);
                }
            }
        }
    }

    /**
     * Performs a dynamic query on the database and returns the matching rows.
     *
     * @param dynamicQuery the dynamic query
     * @return the matching rows
     * @throws SystemException if a system exception occurred
     */
    @SuppressWarnings("rawtypes")
    public List dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return plansFilterPositionPersistence.findWithDynamicQuery(dynamicQuery);
    }

    /**
     * Performs a dynamic query on the database and returns a range of the matching rows.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param dynamicQuery the dynamic query
     * @param start the lower bound of the range of model instances
     * @param end the upper bound of the range of model instances (not inclusive)
     * @return the range of matching rows
     * @throws SystemException if a system exception occurred
     */
    @SuppressWarnings("rawtypes")
    public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return plansFilterPositionPersistence.findWithDynamicQuery(dynamicQuery,
            start, end);
    }

    /**
     * Performs a dynamic query on the database and returns an ordered range of the matching rows.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param dynamicQuery the dynamic query
     * @param start the lower bound of the range of model instances
     * @param end the upper bound of the range of model instances (not inclusive)
     * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
     * @return the ordered range of matching rows
     * @throws SystemException if a system exception occurred
     */
    @SuppressWarnings("rawtypes")
    public List dynamicQuery(DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return plansFilterPositionPersistence.findWithDynamicQuery(dynamicQuery,
            start, end, orderByComparator);
    }

    /**
     * Returns the number of rows that match the dynamic query.
     *
     * @param dynamicQuery the dynamic query
     * @return the number of rows that match the dynamic query
     * @throws SystemException if a system exception occurred
     */
    public long dynamicQueryCount(DynamicQuery dynamicQuery)
        throws SystemException {
        return plansFilterPositionPersistence.countWithDynamicQuery(dynamicQuery);
    }

    public PlansFilterPosition fetchPlansFilterPosition(
        PlansFilterPositionPK plansFilterPositionPK) throws SystemException {
        return plansFilterPositionPersistence.fetchByPrimaryKey(plansFilterPositionPK);
    }

    /**
     * Returns the plans filter position with the primary key.
     *
     * @param plansFilterPositionPK the primary key of the plans filter position
     * @return the plans filter position
     * @throws PortalException if a plans filter position with the primary key could not be found
     * @throws SystemException if a system exception occurred
     */
    public PlansFilterPosition getPlansFilterPosition(
        PlansFilterPositionPK plansFilterPositionPK)
        throws PortalException, SystemException {
        return plansFilterPositionPersistence.findByPrimaryKey(plansFilterPositionPK);
    }

    public PersistedModel getPersistedModel(Serializable primaryKeyObj)
        throws PortalException, SystemException {
        return plansFilterPositionPersistence.findByPrimaryKey(primaryKeyObj);
    }

    /**
     * Returns a range of all the plans filter positions.
     *
     * <p>
     * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
     * </p>
     *
     * @param start the lower bound of the range of plans filter positions
     * @param end the upper bound of the range of plans filter positions (not inclusive)
     * @return the range of plans filter positions
     * @throws SystemException if a system exception occurred
     */
    public List<PlansFilterPosition> getPlansFilterPositions(int start, int end)
        throws SystemException {
        return plansFilterPositionPersistence.findAll(start, end);
    }

    /**
     * Returns the number of plans filter positions.
     *
     * @return the number of plans filter positions
     * @throws SystemException if a system exception occurred
     */
    public int getPlansFilterPositionsCount() throws SystemException {
        return plansFilterPositionPersistence.countAll();
    }

    /**
     * Updates the plans filter position in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param plansFilterPosition the plans filter position
     * @return the plans filter position that was updated
     * @throws SystemException if a system exception occurred
     */
    public PlansFilterPosition updatePlansFilterPosition(
        PlansFilterPosition plansFilterPosition) throws SystemException {
        return updatePlansFilterPosition(plansFilterPosition, true);
    }

    /**
     * Updates the plans filter position in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
     *
     * @param plansFilterPosition the plans filter position
     * @param merge whether to merge the plans filter position with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
     * @return the plans filter position that was updated
     * @throws SystemException if a system exception occurred
     */
    public PlansFilterPosition updatePlansFilterPosition(
        PlansFilterPosition plansFilterPosition, boolean merge)
        throws SystemException {
        plansFilterPosition.setNew(false);

        plansFilterPosition = plansFilterPositionPersistence.update(plansFilterPosition,
                merge);

        Indexer indexer = IndexerRegistryUtil.getIndexer(getModelClassName());

        if (indexer != null) {
            try {
                indexer.reindex(plansFilterPosition);
            } catch (SearchException se) {
                if (_log.isWarnEnabled()) {
                    _log.warn(se, se);
                }
            }
        }

        return plansFilterPosition;
    }

    /**
     * Returns the plan attribute local service.
     *
     * @return the plan attribute local service
     */
    public PlanAttributeLocalService getPlanAttributeLocalService() {
        return planAttributeLocalService;
    }

    /**
     * Sets the plan attribute local service.
     *
     * @param planAttributeLocalService the plan attribute local service
     */
    public void setPlanAttributeLocalService(
        PlanAttributeLocalService planAttributeLocalService) {
        this.planAttributeLocalService = planAttributeLocalService;
    }

    /**
     * Returns the plan attribute remote service.
     *
     * @return the plan attribute remote service
     */
    public PlanAttributeService getPlanAttributeService() {
        return planAttributeService;
    }

    /**
     * Sets the plan attribute remote service.
     *
     * @param planAttributeService the plan attribute remote service
     */
    public void setPlanAttributeService(
        PlanAttributeService planAttributeService) {
        this.planAttributeService = planAttributeService;
    }

    /**
     * Returns the plan attribute persistence.
     *
     * @return the plan attribute persistence
     */
    public PlanAttributePersistence getPlanAttributePersistence() {
        return planAttributePersistence;
    }

    /**
     * Sets the plan attribute persistence.
     *
     * @param planAttributePersistence the plan attribute persistence
     */
    public void setPlanAttributePersistence(
        PlanAttributePersistence planAttributePersistence) {
        this.planAttributePersistence = planAttributePersistence;
    }

    /**
     * Returns the plan attribute filter local service.
     *
     * @return the plan attribute filter local service
     */
    public PlanAttributeFilterLocalService getPlanAttributeFilterLocalService() {
        return planAttributeFilterLocalService;
    }

    /**
     * Sets the plan attribute filter local service.
     *
     * @param planAttributeFilterLocalService the plan attribute filter local service
     */
    public void setPlanAttributeFilterLocalService(
        PlanAttributeFilterLocalService planAttributeFilterLocalService) {
        this.planAttributeFilterLocalService = planAttributeFilterLocalService;
    }

    /**
     * Returns the plan attribute filter remote service.
     *
     * @return the plan attribute filter remote service
     */
    public PlanAttributeFilterService getPlanAttributeFilterService() {
        return planAttributeFilterService;
    }

    /**
     * Sets the plan attribute filter remote service.
     *
     * @param planAttributeFilterService the plan attribute filter remote service
     */
    public void setPlanAttributeFilterService(
        PlanAttributeFilterService planAttributeFilterService) {
        this.planAttributeFilterService = planAttributeFilterService;
    }

    /**
     * Returns the plan attribute filter persistence.
     *
     * @return the plan attribute filter persistence
     */
    public PlanAttributeFilterPersistence getPlanAttributeFilterPersistence() {
        return planAttributeFilterPersistence;
    }

    /**
     * Sets the plan attribute filter persistence.
     *
     * @param planAttributeFilterPersistence the plan attribute filter persistence
     */
    public void setPlanAttributeFilterPersistence(
        PlanAttributeFilterPersistence planAttributeFilterPersistence) {
        this.planAttributeFilterPersistence = planAttributeFilterPersistence;
    }

    /**
     * Returns the plan column settings local service.
     *
     * @return the plan column settings local service
     */
    public PlanColumnSettingsLocalService getPlanColumnSettingsLocalService() {
        return planColumnSettingsLocalService;
    }

    /**
     * Sets the plan column settings local service.
     *
     * @param planColumnSettingsLocalService the plan column settings local service
     */
    public void setPlanColumnSettingsLocalService(
        PlanColumnSettingsLocalService planColumnSettingsLocalService) {
        this.planColumnSettingsLocalService = planColumnSettingsLocalService;
    }

    /**
     * Returns the plan column settings remote service.
     *
     * @return the plan column settings remote service
     */
    public PlanColumnSettingsService getPlanColumnSettingsService() {
        return planColumnSettingsService;
    }

    /**
     * Sets the plan column settings remote service.
     *
     * @param planColumnSettingsService the plan column settings remote service
     */
    public void setPlanColumnSettingsService(
        PlanColumnSettingsService planColumnSettingsService) {
        this.planColumnSettingsService = planColumnSettingsService;
    }

    /**
     * Returns the plan column settings persistence.
     *
     * @return the plan column settings persistence
     */
    public PlanColumnSettingsPersistence getPlanColumnSettingsPersistence() {
        return planColumnSettingsPersistence;
    }

    /**
     * Sets the plan column settings persistence.
     *
     * @param planColumnSettingsPersistence the plan column settings persistence
     */
    public void setPlanColumnSettingsPersistence(
        PlanColumnSettingsPersistence planColumnSettingsPersistence) {
        this.planColumnSettingsPersistence = planColumnSettingsPersistence;
    }

    /**
     * Returns the plan description local service.
     *
     * @return the plan description local service
     */
    public PlanDescriptionLocalService getPlanDescriptionLocalService() {
        return planDescriptionLocalService;
    }

    /**
     * Sets the plan description local service.
     *
     * @param planDescriptionLocalService the plan description local service
     */
    public void setPlanDescriptionLocalService(
        PlanDescriptionLocalService planDescriptionLocalService) {
        this.planDescriptionLocalService = planDescriptionLocalService;
    }

    /**
     * Returns the plan description remote service.
     *
     * @return the plan description remote service
     */
    public PlanDescriptionService getPlanDescriptionService() {
        return planDescriptionService;
    }

    /**
     * Sets the plan description remote service.
     *
     * @param planDescriptionService the plan description remote service
     */
    public void setPlanDescriptionService(
        PlanDescriptionService planDescriptionService) {
        this.planDescriptionService = planDescriptionService;
    }

    /**
     * Returns the plan description persistence.
     *
     * @return the plan description persistence
     */
    public PlanDescriptionPersistence getPlanDescriptionPersistence() {
        return planDescriptionPersistence;
    }

    /**
     * Sets the plan description persistence.
     *
     * @param planDescriptionPersistence the plan description persistence
     */
    public void setPlanDescriptionPersistence(
        PlanDescriptionPersistence planDescriptionPersistence) {
        this.planDescriptionPersistence = planDescriptionPersistence;
    }

    /**
     * Returns the plan fan local service.
     *
     * @return the plan fan local service
     */
    public PlanFanLocalService getPlanFanLocalService() {
        return planFanLocalService;
    }

    /**
     * Sets the plan fan local service.
     *
     * @param planFanLocalService the plan fan local service
     */
    public void setPlanFanLocalService(PlanFanLocalService planFanLocalService) {
        this.planFanLocalService = planFanLocalService;
    }

    /**
     * Returns the plan fan remote service.
     *
     * @return the plan fan remote service
     */
    public PlanFanService getPlanFanService() {
        return planFanService;
    }

    /**
     * Sets the plan fan remote service.
     *
     * @param planFanService the plan fan remote service
     */
    public void setPlanFanService(PlanFanService planFanService) {
        this.planFanService = planFanService;
    }

    /**
     * Returns the plan fan persistence.
     *
     * @return the plan fan persistence
     */
    public PlanFanPersistence getPlanFanPersistence() {
        return planFanPersistence;
    }

    /**
     * Sets the plan fan persistence.
     *
     * @param planFanPersistence the plan fan persistence
     */
    public void setPlanFanPersistence(PlanFanPersistence planFanPersistence) {
        this.planFanPersistence = planFanPersistence;
    }

    /**
     * Returns the plan item local service.
     *
     * @return the plan item local service
     */
    public PlanItemLocalService getPlanItemLocalService() {
        return planItemLocalService;
    }

    /**
     * Sets the plan item local service.
     *
     * @param planItemLocalService the plan item local service
     */
    public void setPlanItemLocalService(
        PlanItemLocalService planItemLocalService) {
        this.planItemLocalService = planItemLocalService;
    }

    /**
     * Returns the plan item remote service.
     *
     * @return the plan item remote service
     */
    public PlanItemService getPlanItemService() {
        return planItemService;
    }

    /**
     * Sets the plan item remote service.
     *
     * @param planItemService the plan item remote service
     */
    public void setPlanItemService(PlanItemService planItemService) {
        this.planItemService = planItemService;
    }

    /**
     * Returns the plan item persistence.
     *
     * @return the plan item persistence
     */
    public PlanItemPersistence getPlanItemPersistence() {
        return planItemPersistence;
    }

    /**
     * Sets the plan item persistence.
     *
     * @param planItemPersistence the plan item persistence
     */
    public void setPlanItemPersistence(PlanItemPersistence planItemPersistence) {
        this.planItemPersistence = planItemPersistence;
    }

    /**
     * Returns the plan item finder.
     *
     * @return the plan item finder
     */
    public PlanItemFinder getPlanItemFinder() {
        return planItemFinder;
    }

    /**
     * Sets the plan item finder.
     *
     * @param planItemFinder the plan item finder
     */
    public void setPlanItemFinder(PlanItemFinder planItemFinder) {
        this.planItemFinder = planItemFinder;
    }

    /**
     * Returns the plan meta local service.
     *
     * @return the plan meta local service
     */
    public PlanMetaLocalService getPlanMetaLocalService() {
        return planMetaLocalService;
    }

    /**
     * Sets the plan meta local service.
     *
     * @param planMetaLocalService the plan meta local service
     */
    public void setPlanMetaLocalService(
        PlanMetaLocalService planMetaLocalService) {
        this.planMetaLocalService = planMetaLocalService;
    }

    /**
     * Returns the plan meta remote service.
     *
     * @return the plan meta remote service
     */
    public PlanMetaService getPlanMetaService() {
        return planMetaService;
    }

    /**
     * Sets the plan meta remote service.
     *
     * @param planMetaService the plan meta remote service
     */
    public void setPlanMetaService(PlanMetaService planMetaService) {
        this.planMetaService = planMetaService;
    }

    /**
     * Returns the plan meta persistence.
     *
     * @return the plan meta persistence
     */
    public PlanMetaPersistence getPlanMetaPersistence() {
        return planMetaPersistence;
    }

    /**
     * Sets the plan meta persistence.
     *
     * @param planMetaPersistence the plan meta persistence
     */
    public void setPlanMetaPersistence(PlanMetaPersistence planMetaPersistence) {
        this.planMetaPersistence = planMetaPersistence;
    }

    /**
     * Returns the plan model run local service.
     *
     * @return the plan model run local service
     */
    public PlanModelRunLocalService getPlanModelRunLocalService() {
        return planModelRunLocalService;
    }

    /**
     * Sets the plan model run local service.
     *
     * @param planModelRunLocalService the plan model run local service
     */
    public void setPlanModelRunLocalService(
        PlanModelRunLocalService planModelRunLocalService) {
        this.planModelRunLocalService = planModelRunLocalService;
    }

    /**
     * Returns the plan model run remote service.
     *
     * @return the plan model run remote service
     */
    public PlanModelRunService getPlanModelRunService() {
        return planModelRunService;
    }

    /**
     * Sets the plan model run remote service.
     *
     * @param planModelRunService the plan model run remote service
     */
    public void setPlanModelRunService(PlanModelRunService planModelRunService) {
        this.planModelRunService = planModelRunService;
    }

    /**
     * Returns the plan model run persistence.
     *
     * @return the plan model run persistence
     */
    public PlanModelRunPersistence getPlanModelRunPersistence() {
        return planModelRunPersistence;
    }

    /**
     * Sets the plan model run persistence.
     *
     * @param planModelRunPersistence the plan model run persistence
     */
    public void setPlanModelRunPersistence(
        PlanModelRunPersistence planModelRunPersistence) {
        this.planModelRunPersistence = planModelRunPersistence;
    }

    /**
     * Returns the plan position local service.
     *
     * @return the plan position local service
     */
    public PlanPositionLocalService getPlanPositionLocalService() {
        return planPositionLocalService;
    }

    /**
     * Sets the plan position local service.
     *
     * @param planPositionLocalService the plan position local service
     */
    public void setPlanPositionLocalService(
        PlanPositionLocalService planPositionLocalService) {
        this.planPositionLocalService = planPositionLocalService;
    }

    /**
     * Returns the plan position remote service.
     *
     * @return the plan position remote service
     */
    public PlanPositionService getPlanPositionService() {
        return planPositionService;
    }

    /**
     * Sets the plan position remote service.
     *
     * @param planPositionService the plan position remote service
     */
    public void setPlanPositionService(PlanPositionService planPositionService) {
        this.planPositionService = planPositionService;
    }

    /**
     * Returns the plan position persistence.
     *
     * @return the plan position persistence
     */
    public PlanPositionPersistence getPlanPositionPersistence() {
        return planPositionPersistence;
    }

    /**
     * Sets the plan position persistence.
     *
     * @param planPositionPersistence the plan position persistence
     */
    public void setPlanPositionPersistence(
        PlanPositionPersistence planPositionPersistence) {
        this.planPositionPersistence = planPositionPersistence;
    }

    /**
     * Returns the plan position item local service.
     *
     * @return the plan position item local service
     */
    public PlanPositionItemLocalService getPlanPositionItemLocalService() {
        return planPositionItemLocalService;
    }

    /**
     * Sets the plan position item local service.
     *
     * @param planPositionItemLocalService the plan position item local service
     */
    public void setPlanPositionItemLocalService(
        PlanPositionItemLocalService planPositionItemLocalService) {
        this.planPositionItemLocalService = planPositionItemLocalService;
    }

    /**
     * Returns the plan position item remote service.
     *
     * @return the plan position item remote service
     */
    public PlanPositionItemService getPlanPositionItemService() {
        return planPositionItemService;
    }

    /**
     * Sets the plan position item remote service.
     *
     * @param planPositionItemService the plan position item remote service
     */
    public void setPlanPositionItemService(
        PlanPositionItemService planPositionItemService) {
        this.planPositionItemService = planPositionItemService;
    }

    /**
     * Returns the plan position item persistence.
     *
     * @return the plan position item persistence
     */
    public PlanPositionItemPersistence getPlanPositionItemPersistence() {
        return planPositionItemPersistence;
    }

    /**
     * Sets the plan position item persistence.
     *
     * @param planPositionItemPersistence the plan position item persistence
     */
    public void setPlanPositionItemPersistence(
        PlanPositionItemPersistence planPositionItemPersistence) {
        this.planPositionItemPersistence = planPositionItemPersistence;
    }

    /**
     * Returns the plan positions local service.
     *
     * @return the plan positions local service
     */
    public PlanPositionsLocalService getPlanPositionsLocalService() {
        return planPositionsLocalService;
    }

    /**
     * Sets the plan positions local service.
     *
     * @param planPositionsLocalService the plan positions local service
     */
    public void setPlanPositionsLocalService(
        PlanPositionsLocalService planPositionsLocalService) {
        this.planPositionsLocalService = planPositionsLocalService;
    }

    /**
     * Returns the plan positions remote service.
     *
     * @return the plan positions remote service
     */
    public PlanPositionsService getPlanPositionsService() {
        return planPositionsService;
    }

    /**
     * Sets the plan positions remote service.
     *
     * @param planPositionsService the plan positions remote service
     */
    public void setPlanPositionsService(
        PlanPositionsService planPositionsService) {
        this.planPositionsService = planPositionsService;
    }

    /**
     * Returns the plan positions persistence.
     *
     * @return the plan positions persistence
     */
    public PlanPositionsPersistence getPlanPositionsPersistence() {
        return planPositionsPersistence;
    }

    /**
     * Sets the plan positions persistence.
     *
     * @param planPositionsPersistence the plan positions persistence
     */
    public void setPlanPositionsPersistence(
        PlanPositionsPersistence planPositionsPersistence) {
        this.planPositionsPersistence = planPositionsPersistence;
    }

    /**
     * Returns the plan property filter local service.
     *
     * @return the plan property filter local service
     */
    public PlanPropertyFilterLocalService getPlanPropertyFilterLocalService() {
        return planPropertyFilterLocalService;
    }

    /**
     * Sets the plan property filter local service.
     *
     * @param planPropertyFilterLocalService the plan property filter local service
     */
    public void setPlanPropertyFilterLocalService(
        PlanPropertyFilterLocalService planPropertyFilterLocalService) {
        this.planPropertyFilterLocalService = planPropertyFilterLocalService;
    }

    /**
     * Returns the plan property filter remote service.
     *
     * @return the plan property filter remote service
     */
    public PlanPropertyFilterService getPlanPropertyFilterService() {
        return planPropertyFilterService;
    }

    /**
     * Sets the plan property filter remote service.
     *
     * @param planPropertyFilterService the plan property filter remote service
     */
    public void setPlanPropertyFilterService(
        PlanPropertyFilterService planPropertyFilterService) {
        this.planPropertyFilterService = planPropertyFilterService;
    }

    /**
     * Returns the plan property filter persistence.
     *
     * @return the plan property filter persistence
     */
    public PlanPropertyFilterPersistence getPlanPropertyFilterPersistence() {
        return planPropertyFilterPersistence;
    }

    /**
     * Sets the plan property filter persistence.
     *
     * @param planPropertyFilterPersistence the plan property filter persistence
     */
    public void setPlanPropertyFilterPersistence(
        PlanPropertyFilterPersistence planPropertyFilterPersistence) {
        this.planPropertyFilterPersistence = planPropertyFilterPersistence;
    }

    /**
     * Returns the plan related local service.
     *
     * @return the plan related local service
     */
    public PlanRelatedLocalService getPlanRelatedLocalService() {
        return planRelatedLocalService;
    }

    /**
     * Sets the plan related local service.
     *
     * @param planRelatedLocalService the plan related local service
     */
    public void setPlanRelatedLocalService(
        PlanRelatedLocalService planRelatedLocalService) {
        this.planRelatedLocalService = planRelatedLocalService;
    }

    /**
     * Returns the plan related remote service.
     *
     * @return the plan related remote service
     */
    public PlanRelatedService getPlanRelatedService() {
        return planRelatedService;
    }

    /**
     * Sets the plan related remote service.
     *
     * @param planRelatedService the plan related remote service
     */
    public void setPlanRelatedService(PlanRelatedService planRelatedService) {
        this.planRelatedService = planRelatedService;
    }

    /**
     * Returns the plan related persistence.
     *
     * @return the plan related persistence
     */
    public PlanRelatedPersistence getPlanRelatedPersistence() {
        return planRelatedPersistence;
    }

    /**
     * Sets the plan related persistence.
     *
     * @param planRelatedPersistence the plan related persistence
     */
    public void setPlanRelatedPersistence(
        PlanRelatedPersistence planRelatedPersistence) {
        this.planRelatedPersistence = planRelatedPersistence;
    }

    /**
     * Returns the plan section local service.
     *
     * @return the plan section local service
     */
    public PlanSectionLocalService getPlanSectionLocalService() {
        return planSectionLocalService;
    }

    /**
     * Sets the plan section local service.
     *
     * @param planSectionLocalService the plan section local service
     */
    public void setPlanSectionLocalService(
        PlanSectionLocalService planSectionLocalService) {
        this.planSectionLocalService = planSectionLocalService;
    }

    /**
     * Returns the plan section remote service.
     *
     * @return the plan section remote service
     */
    public PlanSectionService getPlanSectionService() {
        return planSectionService;
    }

    /**
     * Sets the plan section remote service.
     *
     * @param planSectionService the plan section remote service
     */
    public void setPlanSectionService(PlanSectionService planSectionService) {
        this.planSectionService = planSectionService;
    }

    /**
     * Returns the plan section persistence.
     *
     * @return the plan section persistence
     */
    public PlanSectionPersistence getPlanSectionPersistence() {
        return planSectionPersistence;
    }

    /**
     * Sets the plan section persistence.
     *
     * @param planSectionPersistence the plan section persistence
     */
    public void setPlanSectionPersistence(
        PlanSectionPersistence planSectionPersistence) {
        this.planSectionPersistence = planSectionPersistence;
    }

    /**
     * Returns the plan section definition local service.
     *
     * @return the plan section definition local service
     */
    public PlanSectionDefinitionLocalService getPlanSectionDefinitionLocalService() {
        return planSectionDefinitionLocalService;
    }

    /**
     * Sets the plan section definition local service.
     *
     * @param planSectionDefinitionLocalService the plan section definition local service
     */
    public void setPlanSectionDefinitionLocalService(
        PlanSectionDefinitionLocalService planSectionDefinitionLocalService) {
        this.planSectionDefinitionLocalService = planSectionDefinitionLocalService;
    }

    /**
     * Returns the plan section definition remote service.
     *
     * @return the plan section definition remote service
     */
    public PlanSectionDefinitionService getPlanSectionDefinitionService() {
        return planSectionDefinitionService;
    }

    /**
     * Sets the plan section definition remote service.
     *
     * @param planSectionDefinitionService the plan section definition remote service
     */
    public void setPlanSectionDefinitionService(
        PlanSectionDefinitionService planSectionDefinitionService) {
        this.planSectionDefinitionService = planSectionDefinitionService;
    }

    /**
     * Returns the plan section definition persistence.
     *
     * @return the plan section definition persistence
     */
    public PlanSectionDefinitionPersistence getPlanSectionDefinitionPersistence() {
        return planSectionDefinitionPersistence;
    }

    /**
     * Sets the plan section definition persistence.
     *
     * @param planSectionDefinitionPersistence the plan section definition persistence
     */
    public void setPlanSectionDefinitionPersistence(
        PlanSectionDefinitionPersistence planSectionDefinitionPersistence) {
        this.planSectionDefinitionPersistence = planSectionDefinitionPersistence;
    }

    /**
     * Returns the plan section plan map local service.
     *
     * @return the plan section plan map local service
     */
    public PlanSectionPlanMapLocalService getPlanSectionPlanMapLocalService() {
        return planSectionPlanMapLocalService;
    }

    /**
     * Sets the plan section plan map local service.
     *
     * @param planSectionPlanMapLocalService the plan section plan map local service
     */
    public void setPlanSectionPlanMapLocalService(
        PlanSectionPlanMapLocalService planSectionPlanMapLocalService) {
        this.planSectionPlanMapLocalService = planSectionPlanMapLocalService;
    }

    /**
     * Returns the plan section plan map remote service.
     *
     * @return the plan section plan map remote service
     */
    public PlanSectionPlanMapService getPlanSectionPlanMapService() {
        return planSectionPlanMapService;
    }

    /**
     * Sets the plan section plan map remote service.
     *
     * @param planSectionPlanMapService the plan section plan map remote service
     */
    public void setPlanSectionPlanMapService(
        PlanSectionPlanMapService planSectionPlanMapService) {
        this.planSectionPlanMapService = planSectionPlanMapService;
    }

    /**
     * Returns the plan section plan map persistence.
     *
     * @return the plan section plan map persistence
     */
    public PlanSectionPlanMapPersistence getPlanSectionPlanMapPersistence() {
        return planSectionPlanMapPersistence;
    }

    /**
     * Sets the plan section plan map persistence.
     *
     * @param planSectionPlanMapPersistence the plan section plan map persistence
     */
    public void setPlanSectionPlanMapPersistence(
        PlanSectionPlanMapPersistence planSectionPlanMapPersistence) {
        this.planSectionPlanMapPersistence = planSectionPlanMapPersistence;
    }

    /**
     * Returns the plans filter local service.
     *
     * @return the plans filter local service
     */
    public PlansFilterLocalService getPlansFilterLocalService() {
        return plansFilterLocalService;
    }

    /**
     * Sets the plans filter local service.
     *
     * @param plansFilterLocalService the plans filter local service
     */
    public void setPlansFilterLocalService(
        PlansFilterLocalService plansFilterLocalService) {
        this.plansFilterLocalService = plansFilterLocalService;
    }

    /**
     * Returns the plans filter remote service.
     *
     * @return the plans filter remote service
     */
    public PlansFilterService getPlansFilterService() {
        return plansFilterService;
    }

    /**
     * Sets the plans filter remote service.
     *
     * @param plansFilterService the plans filter remote service
     */
    public void setPlansFilterService(PlansFilterService plansFilterService) {
        this.plansFilterService = plansFilterService;
    }

    /**
     * Returns the plans filter persistence.
     *
     * @return the plans filter persistence
     */
    public PlansFilterPersistence getPlansFilterPersistence() {
        return plansFilterPersistence;
    }

    /**
     * Sets the plans filter persistence.
     *
     * @param plansFilterPersistence the plans filter persistence
     */
    public void setPlansFilterPersistence(
        PlansFilterPersistence plansFilterPersistence) {
        this.plansFilterPersistence = plansFilterPersistence;
    }

    /**
     * Returns the plans filter position local service.
     *
     * @return the plans filter position local service
     */
    public PlansFilterPositionLocalService getPlansFilterPositionLocalService() {
        return plansFilterPositionLocalService;
    }

    /**
     * Sets the plans filter position local service.
     *
     * @param plansFilterPositionLocalService the plans filter position local service
     */
    public void setPlansFilterPositionLocalService(
        PlansFilterPositionLocalService plansFilterPositionLocalService) {
        this.plansFilterPositionLocalService = plansFilterPositionLocalService;
    }

    /**
     * Returns the plans filter position remote service.
     *
     * @return the plans filter position remote service
     */
    public PlansFilterPositionService getPlansFilterPositionService() {
        return plansFilterPositionService;
    }

    /**
     * Sets the plans filter position remote service.
     *
     * @param plansFilterPositionService the plans filter position remote service
     */
    public void setPlansFilterPositionService(
        PlansFilterPositionService plansFilterPositionService) {
        this.plansFilterPositionService = plansFilterPositionService;
    }

    /**
     * Returns the plans filter position persistence.
     *
     * @return the plans filter position persistence
     */
    public PlansFilterPositionPersistence getPlansFilterPositionPersistence() {
        return plansFilterPositionPersistence;
    }

    /**
     * Sets the plans filter position persistence.
     *
     * @param plansFilterPositionPersistence the plans filter position persistence
     */
    public void setPlansFilterPositionPersistence(
        PlansFilterPositionPersistence plansFilterPositionPersistence) {
        this.plansFilterPositionPersistence = plansFilterPositionPersistence;
    }

    /**
     * Returns the plans user settings local service.
     *
     * @return the plans user settings local service
     */
    public PlansUserSettingsLocalService getPlansUserSettingsLocalService() {
        return plansUserSettingsLocalService;
    }

    /**
     * Sets the plans user settings local service.
     *
     * @param plansUserSettingsLocalService the plans user settings local service
     */
    public void setPlansUserSettingsLocalService(
        PlansUserSettingsLocalService plansUserSettingsLocalService) {
        this.plansUserSettingsLocalService = plansUserSettingsLocalService;
    }

    /**
     * Returns the plans user settings remote service.
     *
     * @return the plans user settings remote service
     */
    public PlansUserSettingsService getPlansUserSettingsService() {
        return plansUserSettingsService;
    }

    /**
     * Sets the plans user settings remote service.
     *
     * @param plansUserSettingsService the plans user settings remote service
     */
    public void setPlansUserSettingsService(
        PlansUserSettingsService plansUserSettingsService) {
        this.plansUserSettingsService = plansUserSettingsService;
    }

    /**
     * Returns the plans user settings persistence.
     *
     * @return the plans user settings persistence
     */
    public PlansUserSettingsPersistence getPlansUserSettingsPersistence() {
        return plansUserSettingsPersistence;
    }

    /**
     * Sets the plans user settings persistence.
     *
     * @param plansUserSettingsPersistence the plans user settings persistence
     */
    public void setPlansUserSettingsPersistence(
        PlansUserSettingsPersistence plansUserSettingsPersistence) {
        this.plansUserSettingsPersistence = plansUserSettingsPersistence;
    }

    /**
     * Returns the plan team history local service.
     *
     * @return the plan team history local service
     */
    public PlanTeamHistoryLocalService getPlanTeamHistoryLocalService() {
        return planTeamHistoryLocalService;
    }

    /**
     * Sets the plan team history local service.
     *
     * @param planTeamHistoryLocalService the plan team history local service
     */
    public void setPlanTeamHistoryLocalService(
        PlanTeamHistoryLocalService planTeamHistoryLocalService) {
        this.planTeamHistoryLocalService = planTeamHistoryLocalService;
    }

    /**
     * Returns the plan team history remote service.
     *
     * @return the plan team history remote service
     */
    public PlanTeamHistoryService getPlanTeamHistoryService() {
        return planTeamHistoryService;
    }

    /**
     * Sets the plan team history remote service.
     *
     * @param planTeamHistoryService the plan team history remote service
     */
    public void setPlanTeamHistoryService(
        PlanTeamHistoryService planTeamHistoryService) {
        this.planTeamHistoryService = planTeamHistoryService;
    }

    /**
     * Returns the plan team history persistence.
     *
     * @return the plan team history persistence
     */
    public PlanTeamHistoryPersistence getPlanTeamHistoryPersistence() {
        return planTeamHistoryPersistence;
    }

    /**
     * Sets the plan team history persistence.
     *
     * @param planTeamHistoryPersistence the plan team history persistence
     */
    public void setPlanTeamHistoryPersistence(
        PlanTeamHistoryPersistence planTeamHistoryPersistence) {
        this.planTeamHistoryPersistence = planTeamHistoryPersistence;
    }

    /**
     * Returns the plan template local service.
     *
     * @return the plan template local service
     */
    public PlanTemplateLocalService getPlanTemplateLocalService() {
        return planTemplateLocalService;
    }

    /**
     * Sets the plan template local service.
     *
     * @param planTemplateLocalService the plan template local service
     */
    public void setPlanTemplateLocalService(
        PlanTemplateLocalService planTemplateLocalService) {
        this.planTemplateLocalService = planTemplateLocalService;
    }

    /**
     * Returns the plan template remote service.
     *
     * @return the plan template remote service
     */
    public PlanTemplateService getPlanTemplateService() {
        return planTemplateService;
    }

    /**
     * Sets the plan template remote service.
     *
     * @param planTemplateService the plan template remote service
     */
    public void setPlanTemplateService(PlanTemplateService planTemplateService) {
        this.planTemplateService = planTemplateService;
    }

    /**
     * Returns the plan template persistence.
     *
     * @return the plan template persistence
     */
    public PlanTemplatePersistence getPlanTemplatePersistence() {
        return planTemplatePersistence;
    }

    /**
     * Sets the plan template persistence.
     *
     * @param planTemplatePersistence the plan template persistence
     */
    public void setPlanTemplatePersistence(
        PlanTemplatePersistence planTemplatePersistence) {
        this.planTemplatePersistence = planTemplatePersistence;
    }

    /**
     * Returns the plan template section local service.
     *
     * @return the plan template section local service
     */
    public PlanTemplateSectionLocalService getPlanTemplateSectionLocalService() {
        return planTemplateSectionLocalService;
    }

    /**
     * Sets the plan template section local service.
     *
     * @param planTemplateSectionLocalService the plan template section local service
     */
    public void setPlanTemplateSectionLocalService(
        PlanTemplateSectionLocalService planTemplateSectionLocalService) {
        this.planTemplateSectionLocalService = planTemplateSectionLocalService;
    }

    /**
     * Returns the plan template section remote service.
     *
     * @return the plan template section remote service
     */
    public PlanTemplateSectionService getPlanTemplateSectionService() {
        return planTemplateSectionService;
    }

    /**
     * Sets the plan template section remote service.
     *
     * @param planTemplateSectionService the plan template section remote service
     */
    public void setPlanTemplateSectionService(
        PlanTemplateSectionService planTemplateSectionService) {
        this.planTemplateSectionService = planTemplateSectionService;
    }

    /**
     * Returns the plan template section persistence.
     *
     * @return the plan template section persistence
     */
    public PlanTemplateSectionPersistence getPlanTemplateSectionPersistence() {
        return planTemplateSectionPersistence;
    }

    /**
     * Sets the plan template section persistence.
     *
     * @param planTemplateSectionPersistence the plan template section persistence
     */
    public void setPlanTemplateSectionPersistence(
        PlanTemplateSectionPersistence planTemplateSectionPersistence) {
        this.planTemplateSectionPersistence = planTemplateSectionPersistence;
    }

    /**
     * Returns the plan type local service.
     *
     * @return the plan type local service
     */
    public PlanTypeLocalService getPlanTypeLocalService() {
        return planTypeLocalService;
    }

    /**
     * Sets the plan type local service.
     *
     * @param planTypeLocalService the plan type local service
     */
    public void setPlanTypeLocalService(
        PlanTypeLocalService planTypeLocalService) {
        this.planTypeLocalService = planTypeLocalService;
    }

    /**
     * Returns the plan type remote service.
     *
     * @return the plan type remote service
     */
    public PlanTypeService getPlanTypeService() {
        return planTypeService;
    }

    /**
     * Sets the plan type remote service.
     *
     * @param planTypeService the plan type remote service
     */
    public void setPlanTypeService(PlanTypeService planTypeService) {
        this.planTypeService = planTypeService;
    }

    /**
     * Returns the plan type persistence.
     *
     * @return the plan type persistence
     */
    public PlanTypePersistence getPlanTypePersistence() {
        return planTypePersistence;
    }

    /**
     * Sets the plan type persistence.
     *
     * @param planTypePersistence the plan type persistence
     */
    public void setPlanTypePersistence(PlanTypePersistence planTypePersistence) {
        this.planTypePersistence = planTypePersistence;
    }

    /**
     * Returns the plan type attribute local service.
     *
     * @return the plan type attribute local service
     */
    public PlanTypeAttributeLocalService getPlanTypeAttributeLocalService() {
        return planTypeAttributeLocalService;
    }

    /**
     * Sets the plan type attribute local service.
     *
     * @param planTypeAttributeLocalService the plan type attribute local service
     */
    public void setPlanTypeAttributeLocalService(
        PlanTypeAttributeLocalService planTypeAttributeLocalService) {
        this.planTypeAttributeLocalService = planTypeAttributeLocalService;
    }

    /**
     * Returns the plan type attribute remote service.
     *
     * @return the plan type attribute remote service
     */
    public PlanTypeAttributeService getPlanTypeAttributeService() {
        return planTypeAttributeService;
    }

    /**
     * Sets the plan type attribute remote service.
     *
     * @param planTypeAttributeService the plan type attribute remote service
     */
    public void setPlanTypeAttributeService(
        PlanTypeAttributeService planTypeAttributeService) {
        this.planTypeAttributeService = planTypeAttributeService;
    }

    /**
     * Returns the plan type attribute persistence.
     *
     * @return the plan type attribute persistence
     */
    public PlanTypeAttributePersistence getPlanTypeAttributePersistence() {
        return planTypeAttributePersistence;
    }

    /**
     * Sets the plan type attribute persistence.
     *
     * @param planTypeAttributePersistence the plan type attribute persistence
     */
    public void setPlanTypeAttributePersistence(
        PlanTypeAttributePersistence planTypeAttributePersistence) {
        this.planTypeAttributePersistence = planTypeAttributePersistence;
    }

    /**
     * Returns the plan type column local service.
     *
     * @return the plan type column local service
     */
    public PlanTypeColumnLocalService getPlanTypeColumnLocalService() {
        return planTypeColumnLocalService;
    }

    /**
     * Sets the plan type column local service.
     *
     * @param planTypeColumnLocalService the plan type column local service
     */
    public void setPlanTypeColumnLocalService(
        PlanTypeColumnLocalService planTypeColumnLocalService) {
        this.planTypeColumnLocalService = planTypeColumnLocalService;
    }

    /**
     * Returns the plan type column remote service.
     *
     * @return the plan type column remote service
     */
    public PlanTypeColumnService getPlanTypeColumnService() {
        return planTypeColumnService;
    }

    /**
     * Sets the plan type column remote service.
     *
     * @param planTypeColumnService the plan type column remote service
     */
    public void setPlanTypeColumnService(
        PlanTypeColumnService planTypeColumnService) {
        this.planTypeColumnService = planTypeColumnService;
    }

    /**
     * Returns the plan type column persistence.
     *
     * @return the plan type column persistence
     */
    public PlanTypeColumnPersistence getPlanTypeColumnPersistence() {
        return planTypeColumnPersistence;
    }

    /**
     * Sets the plan type column persistence.
     *
     * @param planTypeColumnPersistence the plan type column persistence
     */
    public void setPlanTypeColumnPersistence(
        PlanTypeColumnPersistence planTypeColumnPersistence) {
        this.planTypeColumnPersistence = planTypeColumnPersistence;
    }

    /**
     * Returns the plan vote local service.
     *
     * @return the plan vote local service
     */
    public PlanVoteLocalService getPlanVoteLocalService() {
        return planVoteLocalService;
    }

    /**
     * Sets the plan vote local service.
     *
     * @param planVoteLocalService the plan vote local service
     */
    public void setPlanVoteLocalService(
        PlanVoteLocalService planVoteLocalService) {
        this.planVoteLocalService = planVoteLocalService;
    }

    /**
     * Returns the plan vote remote service.
     *
     * @return the plan vote remote service
     */
    public PlanVoteService getPlanVoteService() {
        return planVoteService;
    }

    /**
     * Sets the plan vote remote service.
     *
     * @param planVoteService the plan vote remote service
     */
    public void setPlanVoteService(PlanVoteService planVoteService) {
        this.planVoteService = planVoteService;
    }

    /**
     * Returns the plan vote persistence.
     *
     * @return the plan vote persistence
     */
    public PlanVotePersistence getPlanVotePersistence() {
        return planVotePersistence;
    }

    /**
     * Sets the plan vote persistence.
     *
     * @param planVotePersistence the plan vote persistence
     */
    public void setPlanVotePersistence(PlanVotePersistence planVotePersistence) {
        this.planVotePersistence = planVotePersistence;
    }

    /**
     * Returns the counter local service.
     *
     * @return the counter local service
     */
    public CounterLocalService getCounterLocalService() {
        return counterLocalService;
    }

    /**
     * Sets the counter local service.
     *
     * @param counterLocalService the counter local service
     */
    public void setCounterLocalService(CounterLocalService counterLocalService) {
        this.counterLocalService = counterLocalService;
    }

    /**
     * Returns the resource local service.
     *
     * @return the resource local service
     */
    public ResourceLocalService getResourceLocalService() {
        return resourceLocalService;
    }

    /**
     * Sets the resource local service.
     *
     * @param resourceLocalService the resource local service
     */
    public void setResourceLocalService(
        ResourceLocalService resourceLocalService) {
        this.resourceLocalService = resourceLocalService;
    }

    /**
     * Returns the resource remote service.
     *
     * @return the resource remote service
     */
    public ResourceService getResourceService() {
        return resourceService;
    }

    /**
     * Sets the resource remote service.
     *
     * @param resourceService the resource remote service
     */
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * Returns the resource persistence.
     *
     * @return the resource persistence
     */
    public ResourcePersistence getResourcePersistence() {
        return resourcePersistence;
    }

    /**
     * Sets the resource persistence.
     *
     * @param resourcePersistence the resource persistence
     */
    public void setResourcePersistence(ResourcePersistence resourcePersistence) {
        this.resourcePersistence = resourcePersistence;
    }

    /**
     * Returns the user local service.
     *
     * @return the user local service
     */
    public UserLocalService getUserLocalService() {
        return userLocalService;
    }

    /**
     * Sets the user local service.
     *
     * @param userLocalService the user local service
     */
    public void setUserLocalService(UserLocalService userLocalService) {
        this.userLocalService = userLocalService;
    }

    /**
     * Returns the user remote service.
     *
     * @return the user remote service
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * Sets the user remote service.
     *
     * @param userService the user remote service
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns the user persistence.
     *
     * @return the user persistence
     */
    public UserPersistence getUserPersistence() {
        return userPersistence;
    }

    /**
     * Sets the user persistence.
     *
     * @param userPersistence the user persistence
     */
    public void setUserPersistence(UserPersistence userPersistence) {
        this.userPersistence = userPersistence;
    }

    public void afterPropertiesSet() {
        PersistedModelLocalServiceRegistryUtil.register("com.ext.portlet.plans.model.PlansFilterPosition",
            plansFilterPositionLocalService);
    }

    public void destroy() {
        PersistedModelLocalServiceRegistryUtil.unregister(
            "com.ext.portlet.plans.model.PlansFilterPosition");
    }

    /**
     * Returns the Spring bean ID for this bean.
     *
     * @return the Spring bean ID for this bean
     */
    public String getBeanIdentifier() {
        return _beanIdentifier;
    }

    /**
     * Sets the Spring bean ID for this bean.
     *
     * @param beanIdentifier the Spring bean ID for this bean
     */
    public void setBeanIdentifier(String beanIdentifier) {
        _beanIdentifier = beanIdentifier;
    }

    protected Class<?> getModelClass() {
        return PlansFilterPosition.class;
    }

    protected String getModelClassName() {
        return PlansFilterPosition.class.getName();
    }

    /**
     * Performs an SQL query.
     *
     * @param sql the sql query
     */
    protected void runSQL(String sql) throws SystemException {
        try {
            DataSource dataSource = plansFilterPositionPersistence.getDataSource();

            SqlUpdate sqlUpdate = SqlUpdateFactoryUtil.getSqlUpdate(dataSource,
                    sql, new int[0]);

            sqlUpdate.update();
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
