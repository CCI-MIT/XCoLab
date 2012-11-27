package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanSectionDefinitionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanSectionDefinitionLocalService
 * @generated
 */
public class PlanSectionDefinitionLocalServiceWrapper
    implements PlanSectionDefinitionLocalService,
        ServiceWrapper<PlanSectionDefinitionLocalService> {
    private PlanSectionDefinitionLocalService _planSectionDefinitionLocalService;

    public PlanSectionDefinitionLocalServiceWrapper(
        PlanSectionDefinitionLocalService planSectionDefinitionLocalService) {
        _planSectionDefinitionLocalService = planSectionDefinitionLocalService;
    }

    /**
    * Adds the plan section definition to the database. Also notifies the appropriate model listeners.
    *
    * @param planSectionDefinition the plan section definition
    * @return the plan section definition that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSectionDefinition addPlanSectionDefinition(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionDefinitionLocalService.addPlanSectionDefinition(planSectionDefinition);
    }

    /**
    * Creates a new plan section definition with the primary key. Does not add the plan section definition to the database.
    *
    * @param id the primary key for the new plan section definition
    * @return the new plan section definition
    */
    public com.ext.portlet.plans.model.PlanSectionDefinition createPlanSectionDefinition(
        java.lang.Long id) {
        return _planSectionDefinitionLocalService.createPlanSectionDefinition(id);
    }

    /**
    * Deletes the plan section definition with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan section definition
    * @throws PortalException if a plan section definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanSectionDefinition(java.lang.Long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planSectionDefinitionLocalService.deletePlanSectionDefinition(id);
    }

    /**
    * Deletes the plan section definition from the database. Also notifies the appropriate model listeners.
    *
    * @param planSectionDefinition the plan section definition
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanSectionDefinition(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planSectionDefinitionLocalService.deletePlanSectionDefinition(planSectionDefinition);
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
        return _planSectionDefinitionLocalService.dynamicQuery(dynamicQuery);
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
        return _planSectionDefinitionLocalService.dynamicQuery(dynamicQuery,
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
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionDefinitionLocalService.dynamicQuery(dynamicQuery,
            start, end, orderByComparator);
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
        return _planSectionDefinitionLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.plans.model.PlanSectionDefinition fetchPlanSectionDefinition(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionDefinitionLocalService.fetchPlanSectionDefinition(id);
    }

    /**
    * Returns the plan section definition with the primary key.
    *
    * @param id the primary key of the plan section definition
    * @return the plan section definition
    * @throws PortalException if a plan section definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSectionDefinition getPlanSectionDefinition(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planSectionDefinitionLocalService.getPlanSectionDefinition(id);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planSectionDefinitionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan section definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan section definitions
    * @param end the upper bound of the range of plan section definitions (not inclusive)
    * @return the range of plan section definitions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanSectionDefinition> getPlanSectionDefinitions(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionDefinitionLocalService.getPlanSectionDefinitions(start,
            end);
    }

    /**
    * Returns the number of plan section definitions.
    *
    * @return the number of plan section definitions
    * @throws SystemException if a system exception occurred
    */
    public int getPlanSectionDefinitionsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionDefinitionLocalService.getPlanSectionDefinitionsCount();
    }

    /**
    * Updates the plan section definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planSectionDefinition the plan section definition
    * @return the plan section definition that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSectionDefinition updatePlanSectionDefinition(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionDefinitionLocalService.updatePlanSectionDefinition(planSectionDefinition);
    }

    /**
    * Updates the plan section definition in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planSectionDefinition the plan section definition
    * @param merge whether to merge the plan section definition with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan section definition that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanSectionDefinition updatePlanSectionDefinition(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionDefinitionLocalService.updatePlanSectionDefinition(planSectionDefinition,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _planSectionDefinitionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planSectionDefinitionLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanSectionDefinitionLocalService getWrappedPlanSectionDefinitionLocalService() {
        return _planSectionDefinitionLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanSectionDefinitionLocalService(
        PlanSectionDefinitionLocalService planSectionDefinitionLocalService) {
        _planSectionDefinitionLocalService = planSectionDefinitionLocalService;
    }

    public PlanSectionDefinitionLocalService getWrappedService() {
        return _planSectionDefinitionLocalService;
    }

    public void setWrappedService(
        PlanSectionDefinitionLocalService planSectionDefinitionLocalService) {
        _planSectionDefinitionLocalService = planSectionDefinitionLocalService;
    }
}
