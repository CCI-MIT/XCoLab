package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanTypeLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanTypeLocalService
 * @generated
 */
public class PlanTypeLocalServiceWrapper implements PlanTypeLocalService,
    ServiceWrapper<PlanTypeLocalService> {
    private PlanTypeLocalService _planTypeLocalService;

    public PlanTypeLocalServiceWrapper(
        PlanTypeLocalService planTypeLocalService) {
        _planTypeLocalService = planTypeLocalService;
    }

    /**
    * Adds the plan type to the database. Also notifies the appropriate model listeners.
    *
    * @param planType the plan type
    * @return the plan type that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanType addPlanType(
        com.ext.portlet.model.PlanType planType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeLocalService.addPlanType(planType);
    }

    /**
    * Creates a new plan type with the primary key. Does not add the plan type to the database.
    *
    * @param planTypeId the primary key for the new plan type
    * @return the new plan type
    */
    public com.ext.portlet.model.PlanType createPlanType(long planTypeId) {
        return _planTypeLocalService.createPlanType(planTypeId);
    }

    /**
    * Deletes the plan type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planTypeId the primary key of the plan type
    * @throws PortalException if a plan type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanType(long planTypeId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planTypeLocalService.deletePlanType(planTypeId);
    }

    /**
    * Deletes the plan type from the database. Also notifies the appropriate model listeners.
    *
    * @param planType the plan type
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanType(com.ext.portlet.model.PlanType planType)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planTypeLocalService.deletePlanType(planType);
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
        return _planTypeLocalService.dynamicQuery(dynamicQuery);
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
        return _planTypeLocalService.dynamicQuery(dynamicQuery, start, end);
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
        return _planTypeLocalService.dynamicQuery(dynamicQuery, start, end,
            orderByComparator);
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
        return _planTypeLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.model.PlanType fetchPlanType(long planTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeLocalService.fetchPlanType(planTypeId);
    }

    /**
    * Returns the plan type with the primary key.
    *
    * @param planTypeId the primary key of the plan type
    * @return the plan type
    * @throws PortalException if a plan type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanType getPlanType(long planTypeId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planTypeLocalService.getPlanType(planTypeId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planTypeLocalService.getPersistedModel(primaryKeyObj);
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
    public java.util.List<com.ext.portlet.model.PlanType> getPlanTypes(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeLocalService.getPlanTypes(start, end);
    }

    /**
    * Returns the number of plan types.
    *
    * @return the number of plan types
    * @throws SystemException if a system exception occurred
    */
    public int getPlanTypesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeLocalService.getPlanTypesCount();
    }

    /**
    * Updates the plan type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planType the plan type
    * @return the plan type that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanType updatePlanType(
        com.ext.portlet.model.PlanType planType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeLocalService.updatePlanType(planType);
    }

    /**
    * Updates the plan type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planType the plan type
    * @param merge whether to merge the plan type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan type that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanType updatePlanType(
        com.ext.portlet.model.PlanType planType, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeLocalService.updatePlanType(planType, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _planTypeLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planTypeLocalService.setBeanIdentifier(beanIdentifier);
    }

    public com.ext.portlet.model.PlanType getDefaultPlanType()
        throws com.ext.portlet.NoSuchPlanTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planTypeLocalService.getDefaultPlanType();
    }

    public java.util.List<com.ext.portlet.model.PlanTypeColumn> getColumnsByPlanTypeId(
        long planTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeLocalService.getColumnsByPlanTypeId(planTypeId);
    }

    public java.util.List<com.ext.portlet.model.PlanTypeAttribute> getAttributesByPlanTypeId(
        long planTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeLocalService.getAttributesByPlanTypeId(planTypeId);
    }

    public boolean isRegionalType(long planTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeLocalService.isRegionalType(planTypeId);
    }

    public java.util.List<edu.mit.cci.simulation.client.Simulation> getAvailableModels(
        com.ext.portlet.model.PlanType planType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeLocalService.getAvailableModels(planType);
    }

    public edu.mit.cci.simulation.client.Simulation getDefaultModel(
        com.ext.portlet.model.PlanType planType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeLocalService.getDefaultModel(planType);
    }

    public java.util.List<com.ext.portlet.model.PlanTypeColumn> getColumns(
        com.ext.portlet.model.PlanType planType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeLocalService.getColumns(planType);
    }

    public java.util.List<com.ext.portlet.model.PlanTypeAttribute> getAttributes(
        com.ext.portlet.model.PlanType planType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeLocalService.getAttributes(planType);
    }

    public boolean isRegional(com.ext.portlet.model.PlanType planType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeLocalService.isRegional(planType);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanTypeLocalService getWrappedPlanTypeLocalService() {
        return _planTypeLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanTypeLocalService(
        PlanTypeLocalService planTypeLocalService) {
        _planTypeLocalService = planTypeLocalService;
    }

    public PlanTypeLocalService getWrappedService() {
        return _planTypeLocalService;
    }

    public void setWrappedService(PlanTypeLocalService planTypeLocalService) {
        _planTypeLocalService = planTypeLocalService;
    }
}
