package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanMetaLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanMetaLocalService
 * @generated
 */
public class PlanMetaLocalServiceWrapper implements PlanMetaLocalService,
    ServiceWrapper<PlanMetaLocalService> {
    private PlanMetaLocalService _planMetaLocalService;

    public PlanMetaLocalServiceWrapper(
        PlanMetaLocalService planMetaLocalService) {
        _planMetaLocalService = planMetaLocalService;
    }

    /**
    * Adds the plan meta to the database. Also notifies the appropriate model listeners.
    *
    * @param planMeta the plan meta
    * @return the plan meta that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanMeta addPlanMeta(
        com.ext.portlet.model.PlanMeta planMeta)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planMetaLocalService.addPlanMeta(planMeta);
    }

    /**
    * Creates a new plan meta with the primary key. Does not add the plan meta to the database.
    *
    * @param id the primary key for the new plan meta
    * @return the new plan meta
    */
    public com.ext.portlet.model.PlanMeta createPlanMeta(long id) {
        return _planMetaLocalService.createPlanMeta(id);
    }

    /**
    * Deletes the plan meta with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan meta
    * @throws PortalException if a plan meta with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanMeta(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planMetaLocalService.deletePlanMeta(id);
    }

    /**
    * Deletes the plan meta from the database. Also notifies the appropriate model listeners.
    *
    * @param planMeta the plan meta
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanMeta(com.ext.portlet.model.PlanMeta planMeta)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planMetaLocalService.deletePlanMeta(planMeta);
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
        return _planMetaLocalService.dynamicQuery(dynamicQuery);
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
        return _planMetaLocalService.dynamicQuery(dynamicQuery, start, end);
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
        return _planMetaLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _planMetaLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.model.PlanMeta fetchPlanMeta(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planMetaLocalService.fetchPlanMeta(id);
    }

    /**
    * Returns the plan meta with the primary key.
    *
    * @param id the primary key of the plan meta
    * @return the plan meta
    * @throws PortalException if a plan meta with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanMeta getPlanMeta(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planMetaLocalService.getPlanMeta(id);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planMetaLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan metas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan metas
    * @param end the upper bound of the range of plan metas (not inclusive)
    * @return the range of plan metas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanMeta> getPlanMetas(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planMetaLocalService.getPlanMetas(start, end);
    }

    /**
    * Returns the number of plan metas.
    *
    * @return the number of plan metas
    * @throws SystemException if a system exception occurred
    */
    public int getPlanMetasCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planMetaLocalService.getPlanMetasCount();
    }

    /**
    * Updates the plan meta in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planMeta the plan meta
    * @return the plan meta that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanMeta updatePlanMeta(
        com.ext.portlet.model.PlanMeta planMeta)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planMetaLocalService.updatePlanMeta(planMeta);
    }

    /**
    * Updates the plan meta in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planMeta the plan meta
    * @param merge whether to merge the plan meta with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan meta that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanMeta updatePlanMeta(
        com.ext.portlet.model.PlanMeta planMeta, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planMetaLocalService.updatePlanMeta(planMeta, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _planMetaLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planMetaLocalService.setBeanIdentifier(beanIdentifier);
    }

    public com.ext.portlet.model.PlanMeta createPlanMeta(
        com.ext.portlet.model.PlanItem plan, java.lang.Long planTypeId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planMetaLocalService.createPlanMeta(plan, planTypeId);
    }

    public com.ext.portlet.model.PlanMeta getCurrentForPlan(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planMetaLocalService.getCurrentForPlan(plan);
    }

    public java.util.List<com.ext.portlet.model.PlanMeta> getAllForPlan(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planMetaLocalService.getAllForPlan(plan);
    }

    public com.ext.portlet.model.PlanMeta createNewVersionForPlan(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planMetaLocalService.createNewVersionForPlan(plan);
    }

    public com.ext.portlet.model.PlanMeta createNewVersionForPlan(
        com.ext.portlet.model.PlanItem plan, boolean store)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planMetaLocalService.createNewVersionForPlan(plan, store);
    }

    public void store(com.ext.portlet.model.PlanMeta pm)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planMetaLocalService.store(pm);
    }

    public void vote(com.ext.portlet.model.PlanMeta pm)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planMetaLocalService.vote(pm);
    }

    public void unvote(com.ext.portlet.model.PlanMeta pm)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planMetaLocalService.unvote(pm);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanMetaLocalService getWrappedPlanMetaLocalService() {
        return _planMetaLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanMetaLocalService(
        PlanMetaLocalService planMetaLocalService) {
        _planMetaLocalService = planMetaLocalService;
    }

    public PlanMetaLocalService getWrappedService() {
        return _planMetaLocalService;
    }

    public void setWrappedService(PlanMetaLocalService planMetaLocalService) {
        _planMetaLocalService = planMetaLocalService;
    }
}
