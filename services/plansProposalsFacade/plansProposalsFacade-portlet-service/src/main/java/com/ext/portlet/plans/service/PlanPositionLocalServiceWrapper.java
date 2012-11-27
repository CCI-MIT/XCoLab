package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanPositionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanPositionLocalService
 * @generated
 */
public class PlanPositionLocalServiceWrapper implements PlanPositionLocalService,
    ServiceWrapper<PlanPositionLocalService> {
    private PlanPositionLocalService _planPositionLocalService;

    public PlanPositionLocalServiceWrapper(
        PlanPositionLocalService planPositionLocalService) {
        _planPositionLocalService = planPositionLocalService;
    }

    /**
    * Adds the plan position to the database. Also notifies the appropriate model listeners.
    *
    * @param planPosition the plan position
    * @return the plan position that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanPosition addPlanPosition(
        com.ext.portlet.plans.model.PlanPosition planPosition)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionLocalService.addPlanPosition(planPosition);
    }

    /**
    * Creates a new plan position with the primary key. Does not add the plan position to the database.
    *
    * @param planPositionPK the primary key for the new plan position
    * @return the new plan position
    */
    public com.ext.portlet.plans.model.PlanPosition createPlanPosition(
        com.ext.portlet.plans.service.persistence.PlanPositionPK planPositionPK) {
        return _planPositionLocalService.createPlanPosition(planPositionPK);
    }

    /**
    * Deletes the plan position with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planPositionPK the primary key of the plan position
    * @throws PortalException if a plan position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanPosition(
        com.ext.portlet.plans.service.persistence.PlanPositionPK planPositionPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planPositionLocalService.deletePlanPosition(planPositionPK);
    }

    /**
    * Deletes the plan position from the database. Also notifies the appropriate model listeners.
    *
    * @param planPosition the plan position
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanPosition(
        com.ext.portlet.plans.model.PlanPosition planPosition)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planPositionLocalService.deletePlanPosition(planPosition);
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
        return _planPositionLocalService.dynamicQuery(dynamicQuery);
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
        return _planPositionLocalService.dynamicQuery(dynamicQuery, start, end);
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
        return _planPositionLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _planPositionLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.plans.model.PlanPosition fetchPlanPosition(
        com.ext.portlet.plans.service.persistence.PlanPositionPK planPositionPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionLocalService.fetchPlanPosition(planPositionPK);
    }

    /**
    * Returns the plan position with the primary key.
    *
    * @param planPositionPK the primary key of the plan position
    * @return the plan position
    * @throws PortalException if a plan position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanPosition getPlanPosition(
        com.ext.portlet.plans.service.persistence.PlanPositionPK planPositionPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planPositionLocalService.getPlanPosition(planPositionPK);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planPositionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan positions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan positions
    * @param end the upper bound of the range of plan positions (not inclusive)
    * @return the range of plan positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanPosition> getPlanPositions(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionLocalService.getPlanPositions(start, end);
    }

    /**
    * Returns the number of plan positions.
    *
    * @return the number of plan positions
    * @throws SystemException if a system exception occurred
    */
    public int getPlanPositionsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionLocalService.getPlanPositionsCount();
    }

    /**
    * Updates the plan position in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planPosition the plan position
    * @return the plan position that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanPosition updatePlanPosition(
        com.ext.portlet.plans.model.PlanPosition planPosition)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionLocalService.updatePlanPosition(planPosition);
    }

    /**
    * Updates the plan position in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planPosition the plan position
    * @param merge whether to merge the plan position with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan position that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanPosition updatePlanPosition(
        com.ext.portlet.plans.model.PlanPosition planPosition, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planPositionLocalService.updatePlanPosition(planPosition, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _planPositionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planPositionLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanPositionLocalService getWrappedPlanPositionLocalService() {
        return _planPositionLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanPositionLocalService(
        PlanPositionLocalService planPositionLocalService) {
        _planPositionLocalService = planPositionLocalService;
    }

    public PlanPositionLocalService getWrappedService() {
        return _planPositionLocalService;
    }

    public void setWrappedService(
        PlanPositionLocalService planPositionLocalService) {
        _planPositionLocalService = planPositionLocalService;
    }
}
