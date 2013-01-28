package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link PlanTemplateLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       PlanTemplateLocalService
 * @generated
 */
public class PlanTemplateLocalServiceWrapper implements PlanTemplateLocalService,
    ServiceWrapper<PlanTemplateLocalService> {
    private PlanTemplateLocalService _planTemplateLocalService;

    public PlanTemplateLocalServiceWrapper(
        PlanTemplateLocalService planTemplateLocalService) {
        _planTemplateLocalService = planTemplateLocalService;
    }

    /**
    * Adds the plan template to the database. Also notifies the appropriate model listeners.
    *
    * @param planTemplate the plan template
    * @return the plan template that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanTemplate addPlanTemplate(
        com.ext.portlet.model.PlanTemplate planTemplate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTemplateLocalService.addPlanTemplate(planTemplate);
    }

    /**
    * Creates a new plan template with the primary key. Does not add the plan template to the database.
    *
    * @param id the primary key for the new plan template
    * @return the new plan template
    */
    public com.ext.portlet.model.PlanTemplate createPlanTemplate(long id) {
        return _planTemplateLocalService.createPlanTemplate(id);
    }

    /**
    * Deletes the plan template with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan template
    * @throws PortalException if a plan template with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanTemplate(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planTemplateLocalService.deletePlanTemplate(id);
    }

    /**
    * Deletes the plan template from the database. Also notifies the appropriate model listeners.
    *
    * @param planTemplate the plan template
    * @throws SystemException if a system exception occurred
    */
    public void deletePlanTemplate(
        com.ext.portlet.model.PlanTemplate planTemplate)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planTemplateLocalService.deletePlanTemplate(planTemplate);
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
        return _planTemplateLocalService.dynamicQuery(dynamicQuery);
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
        return _planTemplateLocalService.dynamicQuery(dynamicQuery, start, end);
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
        return _planTemplateLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _planTemplateLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.model.PlanTemplate fetchPlanTemplate(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTemplateLocalService.fetchPlanTemplate(id);
    }

    /**
    * Returns the plan template with the primary key.
    *
    * @param id the primary key of the plan template
    * @return the plan template
    * @throws PortalException if a plan template with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanTemplate getPlanTemplate(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planTemplateLocalService.getPlanTemplate(id);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planTemplateLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan templates.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan templates
    * @param end the upper bound of the range of plan templates (not inclusive)
    * @return the range of plan templates
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PlanTemplate> getPlanTemplates(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTemplateLocalService.getPlanTemplates(start, end);
    }

    /**
    * Returns the number of plan templates.
    *
    * @return the number of plan templates
    * @throws SystemException if a system exception occurred
    */
    public int getPlanTemplatesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTemplateLocalService.getPlanTemplatesCount();
    }

    /**
    * Updates the plan template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planTemplate the plan template
    * @return the plan template that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanTemplate updatePlanTemplate(
        com.ext.portlet.model.PlanTemplate planTemplate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTemplateLocalService.updatePlanTemplate(planTemplate);
    }

    /**
    * Updates the plan template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planTemplate the plan template
    * @param merge whether to merge the plan template with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan template that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PlanTemplate updatePlanTemplate(
        com.ext.portlet.model.PlanTemplate planTemplate, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _planTemplateLocalService.updatePlanTemplate(planTemplate, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _planTemplateLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planTemplateLocalService.setBeanIdentifier(beanIdentifier);
    }

    public void store(com.ext.portlet.model.PlanTemplate template)
        throws com.liferay.portal.kernel.exception.SystemException {
        _planTemplateLocalService.store(template);
    }

    public java.util.List<com.ext.portlet.model.PlanSectionDefinition> getSections(
        com.ext.portlet.model.PlanTemplate template)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _planTemplateLocalService.getSections(template);
    }

    public void addSection(com.ext.portlet.model.PlanTemplate template,
        com.ext.portlet.model.PlanSectionDefinition section)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planTemplateLocalService.addSection(template, section);
    }

    public void removeSection(com.ext.portlet.model.PlanTemplate template,
        com.ext.portlet.model.PlanSectionDefinition section)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planTemplateLocalService.removeSection(template, section);
    }

    public void updateSectionWeight(
        com.ext.portlet.model.PlanTemplate template,
        com.ext.portlet.model.PlanSectionDefinition section, int weight)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _planTemplateLocalService.updateSectionWeight(template, section, weight);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public PlanTemplateLocalService getWrappedPlanTemplateLocalService() {
        return _planTemplateLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedPlanTemplateLocalService(
        PlanTemplateLocalService planTemplateLocalService) {
        _planTemplateLocalService = planTemplateLocalService;
    }

    public PlanTemplateLocalService getWrappedService() {
        return _planTemplateLocalService;
    }

    public void setWrappedService(
        PlanTemplateLocalService planTemplateLocalService) {
        _planTemplateLocalService = planTemplateLocalService;
    }
}
