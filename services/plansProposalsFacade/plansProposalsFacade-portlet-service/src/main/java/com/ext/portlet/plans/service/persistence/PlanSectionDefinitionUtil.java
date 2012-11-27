package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.model.PlanSectionDefinition;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the plan section definition service. This utility wraps {@link PlanSectionDefinitionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionDefinitionPersistence
 * @see PlanSectionDefinitionPersistenceImpl
 * @generated
 */
public class PlanSectionDefinitionUtil {
    private static PlanSectionDefinitionPersistence _persistence;

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
    public static void clearCache(PlanSectionDefinition planSectionDefinition) {
        getPersistence().clearCache(planSectionDefinition);
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
    public static List<PlanSectionDefinition> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PlanSectionDefinition> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PlanSectionDefinition> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static PlanSectionDefinition update(
        PlanSectionDefinition planSectionDefinition, boolean merge)
        throws SystemException {
        return getPersistence().update(planSectionDefinition, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static PlanSectionDefinition update(
        PlanSectionDefinition planSectionDefinition, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence()
                   .update(planSectionDefinition, merge, serviceContext);
    }

    /**
    * Caches the plan section definition in the entity cache if it is enabled.
    *
    * @param planSectionDefinition the plan section definition
    */
    public static void cacheResult(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition) {
        getPersistence().cacheResult(planSectionDefinition);
    }

    /**
    * Caches the plan section definitions in the entity cache if it is enabled.
    *
    * @param planSectionDefinitions the plan section definitions
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanSectionDefinition> planSectionDefinitions) {
        getPersistence().cacheResult(planSectionDefinitions);
    }

    /**
    * Creates a new plan section definition with the primary key. Does not add the plan section definition to the database.
    *
    * @param id the primary key for the new plan section definition
    * @return the new plan section definition
    */
    public static com.ext.portlet.plans.model.PlanSectionDefinition create(
        java.lang.Long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the plan section definition with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan section definition
    * @return the plan section definition that was removed
    * @throws com.ext.portlet.plans.NoSuchPlanSectionDefinitionException if a plan section definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanSectionDefinition remove(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanSectionDefinitionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.plans.model.PlanSectionDefinition updateImpl(
        com.ext.portlet.plans.model.PlanSectionDefinition planSectionDefinition,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planSectionDefinition, merge);
    }

    /**
    * Returns the plan section definition with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanSectionDefinitionException} if it could not be found.
    *
    * @param id the primary key of the plan section definition
    * @return the plan section definition
    * @throws com.ext.portlet.plans.NoSuchPlanSectionDefinitionException if a plan section definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanSectionDefinition findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanSectionDefinitionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the plan section definition with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the plan section definition
    * @return the plan section definition, or <code>null</code> if a plan section definition with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanSectionDefinition fetchByPrimaryKey(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the plan section definitions.
    *
    * @return the plan section definitions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanSectionDefinition> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the plan section definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan section definitions
    * @param end the upper bound of the range of plan section definitions (not inclusive)
    * @return the range of plan section definitions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanSectionDefinition> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the plan section definitions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan section definitions
    * @param end the upper bound of the range of plan section definitions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan section definitions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanSectionDefinition> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the plan section definitions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of plan section definitions.
    *
    * @return the number of plan section definitions
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PlanSectionDefinitionPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PlanSectionDefinitionPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.plans.service.ClpSerializer.getServletContextName(),
                    PlanSectionDefinitionPersistence.class.getName());

            ReferenceRegistry.registerReference(PlanSectionDefinitionUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(PlanSectionDefinitionPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(PlanSectionDefinitionUtil.class,
            "_persistence");
    }
}
