package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanSectionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanSectionLocalService
 * @generated
 */
public class PlanSectionLocalServiceWrapper implements PlanSectionLocalService,
    ServiceWrapper<PlanSectionLocalService> {
    private PlanSectionLocalService _planSectionLocalService;

    public PlanSectionLocalServiceWrapper(
        PlanSectionLocalService planSectionLocalService) {
        _planSectionLocalService = planSectionLocalService;
    }

    /**
    * Adds the plan section to the database. Also notifies the appropriate model listeners.
    *
    * @param planSection the plan section
    * @return the plan section that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanSection addPlanSection(
        com.ext.portlet.model.PlanSection planSection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionLocalService.addPlanSection(planSection);
    }

    /**
    * Creates a new plan section with the primary key. Does not add the plan section to the database.
    *
    * @param id the primary key for the new plan section
    * @return the new plan section
    */
    public com.ext.portlet.model.PlanSection createPlanSection(long id) {
        return _planSectionLocalService.createPlanSection(id);
    }

    /**
    * Deletes the plan section with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan section
    * @throws PortalException if a plan section with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanSection(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planSectionLocalService.deletePlanSection(id);
    }

    /**
    * Deletes the plan section from the database. Also notifies the appropriate model listeners.
    *
    * @param planSection the plan section
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanSection(com.ext.portlet.model.PlanSection planSection)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planSectionLocalService.deletePlanSection(planSection);
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
        return _planSectionLocalService.dynamicQuery(dynamicQuery);
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
        return _planSectionLocalService.dynamicQuery(dynamicQuery, start, end);
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
        return _planSectionLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _planSectionLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.model.PlanSection fetchPlanSection(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionLocalService.fetchPlanSection(id);
    }

    /**
    * Returns the plan section with the primary key.
    *
    * @param id the primary key of the plan section
    * @return the plan section
    * @throws PortalException if a plan section with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanSection getPlanSection(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planSectionLocalService.getPlanSection(id);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planSectionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan sections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan sections
    * @param end the upper bound of the range of plan sections (not inclusive)
    * @return the range of plan sections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanSection> getPlanSections(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionLocalService.getPlanSections(start, end);
    }

    /**
    * Returns the number of plan sections.
    *
    * @return the number of plan sections
    * @throws SystemException if a system exception occurred
    */
    public int getPlanSectionsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionLocalService.getPlanSectionsCount();
    }

    /**
    * Updates the plan section in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planSection the plan section
    * @return the plan section that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanSection updatePlanSection(
        com.ext.portlet.model.PlanSection planSection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionLocalService.updatePlanSection(planSection);
    }

    /**
    * Updates the plan section in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planSection the plan section
    * @param merge whether to merge the plan section with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan section that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanSection updatePlanSection(
        com.ext.portlet.model.PlanSection planSection, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionLocalService.updatePlanSection(planSection, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _planSectionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planSectionLocalService.setBeanIdentifier(beanIdentifier);
    }

    public com.ext.portlet.model.PlanSection getCurrentForPlanSectionDef(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSectionDefinition def)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionLocalService.getCurrentForPlanSectionDef(plan, def);
    }

    public com.ext.portlet.model.PlanSection getCurrentForPlanSectionDef(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSectionDefinition def, boolean createOnEmpty)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionLocalService.getCurrentForPlanSectionDef(plan, def,
            createOnEmpty);
    }

    public com.ext.portlet.model.PlanSection getForPlanSectionDef(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSectionDefinition def)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionLocalService.getForPlanSectionDef(plan, def);
    }

    public com.ext.portlet.model.PlanSection getForPlanSectionDef(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSectionDefinition def, boolean current,
        boolean createOnEmpty)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionLocalService.getForPlanSectionDef(plan, def,
            current, createOnEmpty);
    }

    public com.ext.portlet.model.PlanSection createForPlanFrom(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSection from, boolean store)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planSectionLocalService.createForPlanFrom(plan, from, store);
    }

    public com.ext.portlet.model.PlanSection createNewVersionForPlanSectionDefinition(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSectionDefinition def)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionLocalService.createNewVersionForPlanSectionDefinition(plan,
            def);
    }

    public com.ext.portlet.model.PlanSection createNewVersionForPlanSectionDefinition(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSectionDefinition def, boolean store)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionLocalService.createNewVersionForPlanSectionDefinition(plan,
            def, store);
    }

    public java.util.List<com.ext.portlet.model.PlanSection> getAllForPlanDefinition(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanSectionDefinition def)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planSectionLocalService.getAllForPlanDefinition(plan, def);
    }

    public void store(com.ext.portlet.model.PlanSection ps)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planSectionLocalService.store(ps);
    }

    public com.ext.portlet.model.PlanSectionDefinition getDefinition(
        com.ext.portlet.model.PlanSection ps)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planSectionLocalService.getDefinition(ps);
    }

    public void addPlanReference(com.ext.portlet.model.PlanSection ps,
        java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planSectionLocalService.addPlanReference(ps, planId);
    }

    public java.util.List<com.ext.portlet.model.PlanItem> getReferencedPlans(
        com.ext.portlet.model.PlanSection ps)
        throws com.ext.portlet.NoSuchPlanItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planSectionLocalService.getReferencedPlans(ps);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanSectionLocalService getWrappedPlanSectionLocalService() {
        return _planSectionLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanSectionLocalService(
        PlanSectionLocalService planSectionLocalService) {
        _planSectionLocalService = planSectionLocalService;
    }

    public PlanSectionLocalService getWrappedService() {
        return _planSectionLocalService;
    }

    public void setWrappedService(
        PlanSectionLocalService planSectionLocalService) {
        _planSectionLocalService = planSectionLocalService;
    }
}
