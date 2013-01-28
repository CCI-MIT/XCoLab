package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanTypeColumnLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanTypeColumnLocalService
 * @generated
 */
public class PlanTypeColumnLocalServiceWrapper
    implements PlanTypeColumnLocalService,
        ServiceWrapper<PlanTypeColumnLocalService> {
    private PlanTypeColumnLocalService _planTypeColumnLocalService;

    public PlanTypeColumnLocalServiceWrapper(
        PlanTypeColumnLocalService planTypeColumnLocalService) {
        _planTypeColumnLocalService = planTypeColumnLocalService;
    }

    /**
    * Adds the plan type column to the database. Also notifies the appropriate model listeners.
    *
    * @param planTypeColumn the plan type column
    * @return the plan type column that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanTypeColumn addPlanTypeColumn(
        com.ext.portlet.model.PlanTypeColumn planTypeColumn)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeColumnLocalService.addPlanTypeColumn(planTypeColumn);
    }

    /**
    * Creates a new plan type column with the primary key. Does not add the plan type column to the database.
    *
    * @param planTypeColumnId the primary key for the new plan type column
    * @return the new plan type column
    */
    public com.ext.portlet.model.PlanTypeColumn createPlanTypeColumn(
        long planTypeColumnId) {
        return _planTypeColumnLocalService.createPlanTypeColumn(planTypeColumnId);
    }

    /**
    * Deletes the plan type column with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planTypeColumnId the primary key of the plan type column
    * @throws PortalException if a plan type column with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanTypeColumn(long planTypeColumnId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planTypeColumnLocalService.deletePlanTypeColumn(planTypeColumnId);
    }

    /**
    * Deletes the plan type column from the database. Also notifies the appropriate model listeners.
    *
    * @param planTypeColumn the plan type column
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanTypeColumn(
        com.ext.portlet.model.PlanTypeColumn planTypeColumn)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planTypeColumnLocalService.deletePlanTypeColumn(planTypeColumn);
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
        return _planTypeColumnLocalService.dynamicQuery(dynamicQuery);
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
        return _planTypeColumnLocalService.dynamicQuery(dynamicQuery, start, end);
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
        return _planTypeColumnLocalService.dynamicQuery(dynamicQuery, start,
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
        return _planTypeColumnLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.model.PlanTypeColumn fetchPlanTypeColumn(
        long planTypeColumnId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeColumnLocalService.fetchPlanTypeColumn(planTypeColumnId);
    }

    /**
    * Returns the plan type column with the primary key.
    *
    * @param planTypeColumnId the primary key of the plan type column
    * @return the plan type column
    * @throws PortalException if a plan type column with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanTypeColumn getPlanTypeColumn(
        long planTypeColumnId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planTypeColumnLocalService.getPlanTypeColumn(planTypeColumnId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planTypeColumnLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan type columns.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan type columns
    * @param end the upper bound of the range of plan type columns (not inclusive)
    * @return the range of plan type columns
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanTypeColumn> getPlanTypeColumns(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeColumnLocalService.getPlanTypeColumns(start, end);
    }

    /**
    * Returns the number of plan type columns.
    *
    * @return the number of plan type columns
    * @throws SystemException if a system exception occurred
    */
    public int getPlanTypeColumnsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeColumnLocalService.getPlanTypeColumnsCount();
    }

    /**
    * Updates the plan type column in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planTypeColumn the plan type column
    * @return the plan type column that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanTypeColumn updatePlanTypeColumn(
        com.ext.portlet.model.PlanTypeColumn planTypeColumn)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeColumnLocalService.updatePlanTypeColumn(planTypeColumn);
    }

    /**
    * Updates the plan type column in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planTypeColumn the plan type column
    * @param merge whether to merge the plan type column with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan type column that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanTypeColumn updatePlanTypeColumn(
        com.ext.portlet.model.PlanTypeColumn planTypeColumn, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTypeColumnLocalService.updatePlanTypeColumn(planTypeColumn,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _planTypeColumnLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planTypeColumnLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanTypeColumnLocalService getWrappedPlanTypeColumnLocalService() {
        return _planTypeColumnLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanTypeColumnLocalService(
        PlanTypeColumnLocalService planTypeColumnLocalService) {
        _planTypeColumnLocalService = planTypeColumnLocalService;
    }

    public PlanTypeColumnLocalService getWrappedService() {
        return _planTypeColumnLocalService;
    }

    public void setWrappedService(
        PlanTypeColumnLocalService planTypeColumnLocalService) {
        _planTypeColumnLocalService = planTypeColumnLocalService;
    }
}
