package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanTemplate;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the plan template service. This utility wraps {@link PlanTemplatePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTemplatePersistence
 * @see PlanTemplatePersistenceImpl
 * @generated
 */
public class PlanTemplateUtil {
    private static PlanTemplatePersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
     */
    public static void clearCache(PlanTemplate planTemplate) {
        getPersistence().clearCache(planTemplate);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<PlanTemplate> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PlanTemplate> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PlanTemplate> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static PlanTemplate update(PlanTemplate planTemplate, boolean merge)
        throws SystemException {
        return getPersistence().update(planTemplate, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static PlanTemplate update(PlanTemplate planTemplate, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(planTemplate, merge, serviceContext);
    }

    /**
    * Caches the plan template in the entity cache if it is enabled.
    *
    * @param planTemplate the plan template
    */
    public static void cacheResult(
        com.ext.portlet.model.PlanTemplate planTemplate) {
        getPersistence().cacheResult(planTemplate);
    }

    /**
    * Caches the plan templates in the entity cache if it is enabled.
    *
    * @param planTemplates the plan templates
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.PlanTemplate> planTemplates) {
        getPersistence().cacheResult(planTemplates);
    }

    /**
    * Creates a new plan template with the primary key. Does not add the plan template to the database.
    *
    * @param id the primary key for the new plan template
    * @return the new plan template
    */
    public static com.ext.portlet.model.PlanTemplate create(long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the plan template with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan template
    * @return the plan template that was removed
    * @throws com.ext.portlet.NoSuchPlanTemplateException if a plan template with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanTemplate remove(long id)
        throws com.ext.portlet.NoSuchPlanTemplateException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.PlanTemplate updateImpl(
        com.ext.portlet.model.PlanTemplate planTemplate, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planTemplate, merge);
    }

    /**
    * Returns the plan template with the primary key or throws a {@link com.ext.portlet.NoSuchPlanTemplateException} if it could not be found.
    *
    * @param id the primary key of the plan template
    * @return the plan template
    * @throws com.ext.portlet.NoSuchPlanTemplateException if a plan template with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanTemplate findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchPlanTemplateException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the plan template with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the plan template
    * @return the plan template, or <code>null</code> if a plan template with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanTemplate fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the plan templates.
    *
    * @return the plan templates
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanTemplate> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
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
    public static java.util.List<com.ext.portlet.model.PlanTemplate> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.PlanTemplate> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the plan templates from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of plan templates.
    *
    * @return the number of plan templates
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PlanTemplatePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PlanTemplatePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    PlanTemplatePersistence.class.getName());

            ReferenceRegistry.registerReference(PlanTemplateUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(PlanTemplatePersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(PlanTemplateUtil.class,
            "_persistence");
    }
}
