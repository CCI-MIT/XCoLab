package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanItemPhaseMapLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanItemPhaseMapLocalService
 * @generated
 */
public class PlanItemPhaseMapLocalServiceWrapper
    implements PlanItemPhaseMapLocalService,
        ServiceWrapper<PlanItemPhaseMapLocalService> {
    private PlanItemPhaseMapLocalService _planItemPhaseMapLocalService;

    public PlanItemPhaseMapLocalServiceWrapper(
        PlanItemPhaseMapLocalService planItemPhaseMapLocalService) {
        _planItemPhaseMapLocalService = planItemPhaseMapLocalService;
    }

    /**
    * Adds the plan item phase map to the database. Also notifies the appropriate model listeners.
    *
    * @param planItemPhaseMap the plan item phase map
    * @return the plan item phase map that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItemPhaseMap addPlanItemPhaseMap(
        com.ext.portlet.model.PlanItemPhaseMap planItemPhaseMap)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemPhaseMapLocalService.addPlanItemPhaseMap(planItemPhaseMap);
    }

    /**
    * Creates a new plan item phase map with the primary key. Does not add the plan item phase map to the database.
    *
    * @param id the primary key for the new plan item phase map
    * @return the new plan item phase map
    */
    public com.ext.portlet.model.PlanItemPhaseMap createPlanItemPhaseMap(
        long id) {
        return _planItemPhaseMapLocalService.createPlanItemPhaseMap(id);
    }

    /**
    * Deletes the plan item phase map with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan item phase map
    * @throws PortalException if a plan item phase map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanItemPhaseMap(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planItemPhaseMapLocalService.deletePlanItemPhaseMap(id);
    }

    /**
    * Deletes the plan item phase map from the database. Also notifies the appropriate model listeners.
    *
    * @param planItemPhaseMap the plan item phase map
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanItemPhaseMap(
        com.ext.portlet.model.PlanItemPhaseMap planItemPhaseMap)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planItemPhaseMapLocalService.deletePlanItemPhaseMap(planItemPhaseMap);
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemPhaseMapLocalService.dynamicQuery(dynamicQuery);
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
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemPhaseMapLocalService.dynamicQuery(dynamicQuery, start,
            end);
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
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemPhaseMapLocalService.dynamicQuery(dynamicQuery, start,
            end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemPhaseMapLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.model.PlanItemPhaseMap fetchPlanItemPhaseMap(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemPhaseMapLocalService.fetchPlanItemPhaseMap(id);
    }

    /**
    * Returns the plan item phase map with the primary key.
    *
    * @param id the primary key of the plan item phase map
    * @return the plan item phase map
    * @throws PortalException if a plan item phase map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItemPhaseMap getPlanItemPhaseMap(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemPhaseMapLocalService.getPlanItemPhaseMap(id);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planItemPhaseMapLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan item phase maps.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan item phase maps
    * @param end the upper bound of the range of plan item phase maps (not inclusive)
    * @return the range of plan item phase maps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanItemPhaseMap> getPlanItemPhaseMaps(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemPhaseMapLocalService.getPlanItemPhaseMaps(start, end);
    }

    /**
    * Returns the number of plan item phase maps.
    *
    * @return the number of plan item phase maps
    * @throws SystemException if a system exception occurred
    */
    public int getPlanItemPhaseMapsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemPhaseMapLocalService.getPlanItemPhaseMapsCount();
    }

    /**
    * Updates the plan item phase map in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planItemPhaseMap the plan item phase map
    * @return the plan item phase map that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItemPhaseMap updatePlanItemPhaseMap(
        com.ext.portlet.model.PlanItemPhaseMap planItemPhaseMap)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemPhaseMapLocalService.updatePlanItemPhaseMap(planItemPhaseMap);
    }

    /**
    * Updates the plan item phase map in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planItemPhaseMap the plan item phase map
    * @param merge whether to merge the plan item phase map with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan item phase map that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanItemPhaseMap updatePlanItemPhaseMap(
        com.ext.portlet.model.PlanItemPhaseMap planItemPhaseMap, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planItemPhaseMapLocalService.updatePlanItemPhaseMap(planItemPhaseMap,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _planItemPhaseMapLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planItemPhaseMapLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanItemPhaseMapLocalService getWrappedPlanItemPhaseMapLocalService() {
        return _planItemPhaseMapLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanItemPhaseMapLocalService(
        PlanItemPhaseMapLocalService planItemPhaseMapLocalService) {
        _planItemPhaseMapLocalService = planItemPhaseMapLocalService;
    }

    public PlanItemPhaseMapLocalService getWrappedService() {
        return _planItemPhaseMapLocalService;
    }

    public void setWrappedService(
        PlanItemPhaseMapLocalService planItemPhaseMapLocalService) {
        _planItemPhaseMapLocalService = planItemPhaseMapLocalService;
    }
}
