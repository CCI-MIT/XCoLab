package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.model.PlanTemplate;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan template service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTemplatePersistenceImpl
 * @see PlanTemplateUtil
 * @generated
 */
public interface PlanTemplatePersistence extends BasePersistence<PlanTemplate> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PlanTemplateUtil} to access the plan template persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the plan template in the entity cache if it is enabled.
    *
    * @param planTemplate the plan template
    */
    public void cacheResult(
        com.ext.portlet.plans.model.PlanTemplate planTemplate);

    /**
    * Caches the plan templates in the entity cache if it is enabled.
    *
    * @param planTemplates the plan templates
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanTemplate> planTemplates);

    /**
    * Creates a new plan template with the primary key. Does not add the plan template to the database.
    *
    * @param id the primary key for the new plan template
    * @return the new plan template
    */
    public com.ext.portlet.plans.model.PlanTemplate create(java.lang.Long id);

    /**
    * Removes the plan template with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan template
    * @return the plan template that was removed
    * @throws com.ext.portlet.plans.NoSuchPlanTemplateException if a plan template with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanTemplate remove(java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanTemplateException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.plans.model.PlanTemplate updateImpl(
        com.ext.portlet.plans.model.PlanTemplate planTemplate, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan template with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanTemplateException} if it could not be found.
    *
    * @param id the primary key of the plan template
    * @return the plan template
    * @throws com.ext.portlet.plans.NoSuchPlanTemplateException if a plan template with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanTemplate findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanTemplateException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan template with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the plan template
    * @return the plan template, or <code>null</code> if a plan template with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.plans.model.PlanTemplate fetchByPrimaryKey(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan templates.
    *
    * @return the plan templates
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanTemplate> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.plans.model.PlanTemplate> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan templates.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan templates
    * @param end the upper bound of the range of plan templates (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan templates
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.plans.model.PlanTemplate> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan templates from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan templates.
    *
    * @return the number of plan templates
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
