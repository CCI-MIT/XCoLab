package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanFanLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanFanLocalService
 * @generated
 */
public class PlanFanLocalServiceWrapper implements PlanFanLocalService,
    ServiceWrapper<PlanFanLocalService> {
    private PlanFanLocalService _planFanLocalService;

    public PlanFanLocalServiceWrapper(PlanFanLocalService planFanLocalService) {
        _planFanLocalService = planFanLocalService;
    }

    /**
    * Adds the plan fan to the database. Also notifies the appropriate model listeners.
    *
    * @param planFan the plan fan
    * @return the plan fan that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanFan addPlanFan(
        com.ext.portlet.plans.model.PlanFan planFan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planFanLocalService.addPlanFan(planFan);
    }

    /**
    * Creates a new plan fan with the primary key. Does not add the plan fan to the database.
    *
    * @param id the primary key for the new plan fan
    * @return the new plan fan
    */
    public com.ext.portlet.plans.model.PlanFan createPlanFan(long id) {
        return _planFanLocalService.createPlanFan(id);
    }

    /**
    * Deletes the plan fan with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan fan
    * @throws PortalException if a plan fan with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanFan(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planFanLocalService.deletePlanFan(id);
    }

    /**
    * Deletes the plan fan from the database. Also notifies the appropriate model listeners.
    *
    * @param planFan the plan fan
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanFan(com.ext.portlet.plans.model.PlanFan planFan)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planFanLocalService.deletePlanFan(planFan);
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
        return _planFanLocalService.dynamicQuery(dynamicQuery);
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
        return _planFanLocalService.dynamicQuery(dynamicQuery, start, end);
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
        return _planFanLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _planFanLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.plans.model.PlanFan fetchPlanFan(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planFanLocalService.fetchPlanFan(id);
    }

    /**
    * Returns the plan fan with the primary key.
    *
    * @param id the primary key of the plan fan
    * @return the plan fan
    * @throws PortalException if a plan fan with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanFan getPlanFan(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planFanLocalService.getPlanFan(id);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planFanLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan fans.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan fans
    * @param end the upper bound of the range of plan fans (not inclusive)
    * @return the range of plan fans
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanFan> getPlanFans(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planFanLocalService.getPlanFans(start, end);
    }

    /**
    * Returns the number of plan fans.
    *
    * @return the number of plan fans
    * @throws SystemException if a system exception occurred
    */
    public int getPlanFansCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planFanLocalService.getPlanFansCount();
    }

    /**
    * Updates the plan fan in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planFan the plan fan
    * @return the plan fan that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanFan updatePlanFan(
        com.ext.portlet.plans.model.PlanFan planFan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planFanLocalService.updatePlanFan(planFan);
    }

    /**
    * Updates the plan fan in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planFan the plan fan
    * @param merge whether to merge the plan fan with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan fan that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanFan updatePlanFan(
        com.ext.portlet.plans.model.PlanFan planFan, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planFanLocalService.updatePlanFan(planFan, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _planFanLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planFanLocalService.setBeanIdentifier(beanIdentifier);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanFan> getPlanFansForPlan(
        java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planFanLocalService.getPlanFansForPlan(planId);
    }

    public int countPlanFansForPlan(java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planFanLocalService.countPlanFansForPlan(planId);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanFan> getPlanFansForUser(
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planFanLocalService.getPlanFansForUser(userId);
    }

    public com.ext.portlet.plans.model.PlanFan addFan(java.lang.Long planId,
        java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planFanLocalService.addFan(planId, userId);
    }

    public void removePlanFan(java.lang.Long planId, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planFanLocalService.removePlanFan(planId, userId);
    }

    public com.ext.portlet.plans.model.PlanFan getPlanFanByPlanIdUserId(
        java.lang.Long planId, java.lang.Long userId)
        throws com.ext.portlet.plans.NoSuchPlanFanException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planFanLocalService.getPlanFanByPlanIdUserId(planId, userId);
    }

    public int countByUserId(java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planFanLocalService.countByUserId(userId);
    }

    public java.util.List<com.ext.portlet.plans.model.PlanFan> getByUserId(
        java.lang.Long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planFanLocalService.getByUserId(userId, start, end);
    }

    public void store(com.ext.portlet.plans.model.PlanFan pf)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planFanLocalService.store(pf);
    }

    public com.liferay.portal.model.User getUser(
        com.ext.portlet.plans.model.PlanFan pf)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planFanLocalService.getUser(pf);
    }

    public com.ext.portlet.plans.model.PlanItem getPlan(
        com.ext.portlet.plans.model.PlanFan pf)
        throws com.ext.portlet.plans.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planFanLocalService.getPlan(pf);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanFanLocalService getWrappedPlanFanLocalService() {
        return _planFanLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanFanLocalService(
        PlanFanLocalService planFanLocalService) {
        _planFanLocalService = planFanLocalService;
    }

    public PlanFanLocalService getWrappedService() {
        return _planFanLocalService;
    }

    public void setWrappedService(PlanFanLocalService planFanLocalService) {
        _planFanLocalService = planFanLocalService;
    }
}
