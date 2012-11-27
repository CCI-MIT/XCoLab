package com.ext.portlet.plans.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanRelatedLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanRelatedLocalService
 * @generated
 */
public class PlanRelatedLocalServiceWrapper implements PlanRelatedLocalService,
    ServiceWrapper<PlanRelatedLocalService> {
    private PlanRelatedLocalService _planRelatedLocalService;

    public PlanRelatedLocalServiceWrapper(
        PlanRelatedLocalService planRelatedLocalService) {
        _planRelatedLocalService = planRelatedLocalService;
    }

    /**
    * Adds the plan related to the database. Also notifies the appropriate model listeners.
    *
    * @param planRelated the plan related
    * @return the plan related that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanRelated addPlanRelated(
        com.ext.portlet.plans.model.PlanRelated planRelated)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planRelatedLocalService.addPlanRelated(planRelated);
    }

    /**
    * Creates a new plan related with the primary key. Does not add the plan related to the database.
    *
    * @param planRelatedPK the primary key for the new plan related
    * @return the new plan related
    */
    public com.ext.portlet.plans.model.PlanRelated createPlanRelated(
        com.ext.portlet.plans.service.persistence.PlanRelatedPK planRelatedPK) {
        return _planRelatedLocalService.createPlanRelated(planRelatedPK);
    }

    /**
    * Deletes the plan related with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planRelatedPK the primary key of the plan related
    * @throws PortalException if a plan related with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanRelated(
        com.ext.portlet.plans.service.persistence.PlanRelatedPK planRelatedPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planRelatedLocalService.deletePlanRelated(planRelatedPK);
    }

    /**
    * Deletes the plan related from the database. Also notifies the appropriate model listeners.
    *
    * @param planRelated the plan related
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanRelated(
        com.ext.portlet.plans.model.PlanRelated planRelated)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planRelatedLocalService.deletePlanRelated(planRelated);
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
        return _planRelatedLocalService.dynamicQuery(dynamicQuery);
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
        return _planRelatedLocalService.dynamicQuery(dynamicQuery, start, end);
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
        return _planRelatedLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _planRelatedLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.plans.model.PlanRelated fetchPlanRelated(
        com.ext.portlet.plans.service.persistence.PlanRelatedPK planRelatedPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planRelatedLocalService.fetchPlanRelated(planRelatedPK);
    }

    /**
    * Returns the plan related with the primary key.
    *
    * @param planRelatedPK the primary key of the plan related
    * @return the plan related
    * @throws PortalException if a plan related with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanRelated getPlanRelated(
        com.ext.portlet.plans.service.persistence.PlanRelatedPK planRelatedPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planRelatedLocalService.getPlanRelated(planRelatedPK);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planRelatedLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan relateds.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan relateds
    * @param end the upper bound of the range of plan relateds (not inclusive)
    * @return the range of plan relateds
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanRelated> getPlanRelateds(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planRelatedLocalService.getPlanRelateds(start, end);
    }

    /**
    * Returns the number of plan relateds.
    *
    * @return the number of plan relateds
    * @throws SystemException if a system exception occurred
    */
    public int getPlanRelatedsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planRelatedLocalService.getPlanRelatedsCount();
    }

    /**
    * Updates the plan related in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planRelated the plan related
    * @return the plan related that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanRelated updatePlanRelated(
        com.ext.portlet.plans.model.PlanRelated planRelated)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planRelatedLocalService.updatePlanRelated(planRelated);
    }

    /**
    * Updates the plan related in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planRelated the plan related
    * @param merge whether to merge the plan related with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan related that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanRelated updatePlanRelated(
        com.ext.portlet.plans.model.PlanRelated planRelated, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planRelatedLocalService.updatePlanRelated(planRelated, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _planRelatedLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planRelatedLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanRelatedLocalService getWrappedPlanRelatedLocalService() {
        return _planRelatedLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanRelatedLocalService(
        PlanRelatedLocalService planRelatedLocalService) {
        _planRelatedLocalService = planRelatedLocalService;
    }

    public PlanRelatedLocalService getWrappedService() {
        return _planRelatedLocalService;
    }

    public void setWrappedService(
        PlanRelatedLocalService planRelatedLocalService) {
        _planRelatedLocalService = planRelatedLocalService;
    }
}
