package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.model.PlanSection;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the plan section service. This utility wraps {@link PlanSectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionPersistence
 * @see PlanSectionPersistenceImpl
 * @generated
 */
public class PlanSectionUtil {
    private static PlanSectionPersistence _persistence;

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
    public static void clearCache(PlanSection planSection) {
        getPersistence().clearCache(planSection);
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
    public static List<PlanSection> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PlanSection> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PlanSection> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static PlanSection update(PlanSection planSection, boolean merge)
        throws SystemException {
        return getPersistence().update(planSection, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static PlanSection update(PlanSection planSection, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(planSection, merge, serviceContext);
    }

    /**
    * Caches the plan section in the entity cache if it is enabled.
    *
    * @param planSection the plan section
    */
    public static void cacheResult(
        com.ext.portlet.plans.model.PlanSection planSection) {
        getPersistence().cacheResult(planSection);
    }

    /**
    * Caches the plan sections in the entity cache if it is enabled.
    *
    * @param planSections the plan sections
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanSection> planSections) {
        getPersistence().cacheResult(planSections);
    }

    /**
    * Creates a new plan section with the primary key. Does not add the plan section to the database.
    *
    * @param id the primary key for the new plan section
    * @return the new plan section
    */
    public static com.ext.portlet.plans.model.PlanSection create(
        java.lang.Long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the plan section with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan section
    * @return the plan section that was removed
    * @throws com.ext.portlet.plans.NoSuchPlanSectionException if a plan section with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanSection remove(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.plans.model.PlanSection updateImpl(
        com.ext.portlet.plans.model.PlanSection planSection, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planSection, merge);
    }

    /**
    * Returns the plan section with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanSectionException} if it could not be found.
    *
    * @param id the primary key of the plan section
    * @return the plan section
    * @throws com.ext.portlet.plans.NoSuchPlanSectionException if a plan section with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanSection findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the plan section with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the plan section
    * @return the plan section, or <code>null</code> if a plan section with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanSection fetchByPrimaryKey(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the plan sections where planId = &#63; and planSectionDefinitionId = &#63;.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @return the matching plan sections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanSection> findByPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanIdSectionDefinitionId(planId,
            planSectionDefinitionId);
    }

    /**
    * Returns a range of all the plan sections where planId = &#63; and planSectionDefinitionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @param start the lower bound of the range of plan sections
    * @param end the upper bound of the range of plan sections (not inclusive)
    * @return the range of matching plan sections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanSection> findByPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanIdSectionDefinitionId(planId,
            planSectionDefinitionId, start, end);
    }

    /**
    * Returns an ordered range of all the plan sections where planId = &#63; and planSectionDefinitionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @param start the lower bound of the range of plan sections
    * @param end the upper bound of the range of plan sections (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan sections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanSection> findByPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId,
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanIdSectionDefinitionId(planId,
            planSectionDefinitionId, start, end, orderByComparator);
    }

    /**
    * Returns the first plan section in the ordered set where planId = &#63; and planSectionDefinitionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan section
    * @throws com.ext.portlet.plans.NoSuchPlanSectionException if a matching plan section could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanSection findByPlanIdSectionDefinitionId_First(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanIdSectionDefinitionId_First(planId,
            planSectionDefinitionId, orderByComparator);
    }

    /**
    * Returns the last plan section in the ordered set where planId = &#63; and planSectionDefinitionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan section
    * @throws com.ext.portlet.plans.NoSuchPlanSectionException if a matching plan section could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanSection findByPlanIdSectionDefinitionId_Last(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanIdSectionDefinitionId_Last(planId,
            planSectionDefinitionId, orderByComparator);
    }

    /**
    * Returns the plan sections before and after the current plan section in the ordered set where planId = &#63; and planSectionDefinitionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param id the primary key of the current plan section
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan section
    * @throws com.ext.portlet.plans.NoSuchPlanSectionException if a plan section with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanSection[] findByPlanIdSectionDefinitionId_PrevAndNext(
        java.lang.Long id, java.lang.Long planId,
        java.lang.Long planSectionDefinitionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanIdSectionDefinitionId_PrevAndNext(id, planId,
            planSectionDefinitionId, orderByComparator);
    }

    /**
    * Returns the plan section where planId = &#63; and planSectionDefinitionId = &#63; or throws a {@link com.ext.portlet.plans.NoSuchPlanSectionException} if it could not be found.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @return the matching plan section
    * @throws com.ext.portlet.plans.NoSuchPlanSectionException if a matching plan section could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanSection findByCurrentPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCurrentPlanIdSectionDefinitionId(planId,
            planSectionDefinitionId);
    }

    /**
    * Returns the plan section where planId = &#63; and planSectionDefinitionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @return the matching plan section, or <code>null</code> if a matching plan section could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanSection fetchByCurrentPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCurrentPlanIdSectionDefinitionId(planId,
            planSectionDefinitionId);
    }

    /**
    * Returns the plan section where planId = &#63; and planSectionDefinitionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan section, or <code>null</code> if a matching plan section could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanSection fetchByCurrentPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId,
        boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCurrentPlanIdSectionDefinitionId(planId,
            planSectionDefinitionId, retrieveFromCache);
    }

    /**
    * Returns the plan section where planId = &#63; and planSectionDefinitionId = &#63; and planVersion &le; &#63; or throws a {@link com.ext.portlet.plans.NoSuchPlanSectionException} if it could not be found.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @param planVersion the plan version
    * @return the matching plan section
    * @throws com.ext.portlet.plans.NoSuchPlanSectionException if a matching plan section could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanSection findByPlanIdPlanVersion(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId,
        java.lang.Long planVersion)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanIdPlanVersion(planId, planSectionDefinitionId,
            planVersion);
    }

    /**
    * Returns the plan section where planId = &#63; and planSectionDefinitionId = &#63; and planVersion &le; &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @param planVersion the plan version
    * @return the matching plan section, or <code>null</code> if a matching plan section could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanSection fetchByPlanIdPlanVersion(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId,
        java.lang.Long planVersion)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPlanIdPlanVersion(planId, planSectionDefinitionId,
            planVersion);
    }

    /**
    * Returns the plan section where planId = &#63; and planSectionDefinitionId = &#63; and planVersion &le; &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @param planVersion the plan version
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan section, or <code>null</code> if a matching plan section could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanSection fetchByPlanIdPlanVersion(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId,
        java.lang.Long planVersion, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPlanIdPlanVersion(planId, planSectionDefinitionId,
            planVersion, retrieveFromCache);
    }

    /**
    * Returns all the plan sections.
    *
    * @return the plan sections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanSection> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
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
    public static java.util.List<com.ext.portlet.plans.model.PlanSection> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the plan sections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan sections
    * @param end the upper bound of the range of plan sections (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan sections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanSection> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the plan sections where planId = &#63; and planSectionDefinitionId = &#63; from the database.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByPlanIdSectionDefinitionId(planId, planSectionDefinitionId);
    }

    /**
    * Removes the plan section where planId = &#63; and planSectionDefinitionId = &#63; from the database.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCurrentPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByCurrentPlanIdSectionDefinitionId(planId,
            planSectionDefinitionId);
    }

    /**
    * Removes the plan section where planId = &#63; and planSectionDefinitionId = &#63; and planVersion &le; &#63; from the database.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @param planVersion the plan version
    * @throws SystemException if a system exception occurred
    */
    public static void removeByPlanIdPlanVersion(java.lang.Long planId,
        java.lang.Long planSectionDefinitionId, java.lang.Long planVersion)
        throws com.ext.portlet.plans.NoSuchPlanSectionException,
            com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByPlanIdPlanVersion(planId, planSectionDefinitionId,
            planVersion);
    }

    /**
    * Removes all the plan sections from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of plan sections where planId = &#63; and planSectionDefinitionId = &#63;.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @return the number of matching plan sections
    * @throws SystemException if a system exception occurred
    */
    public static int countByPlanIdSectionDefinitionId(java.lang.Long planId,
        java.lang.Long planSectionDefinitionId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByPlanIdSectionDefinitionId(planId,
            planSectionDefinitionId);
    }

    /**
    * Returns the number of plan sections where planId = &#63; and planSectionDefinitionId = &#63;.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @return the number of matching plan sections
    * @throws SystemException if a system exception occurred
    */
    public static int countByCurrentPlanIdSectionDefinitionId(
        java.lang.Long planId, java.lang.Long planSectionDefinitionId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByCurrentPlanIdSectionDefinitionId(planId,
            planSectionDefinitionId);
    }

    /**
    * Returns the number of plan sections where planId = &#63; and planSectionDefinitionId = &#63; and planVersion &le; &#63;.
    *
    * @param planId the plan ID
    * @param planSectionDefinitionId the plan section definition ID
    * @param planVersion the plan version
    * @return the number of matching plan sections
    * @throws SystemException if a system exception occurred
    */
    public static int countByPlanIdPlanVersion(java.lang.Long planId,
        java.lang.Long planSectionDefinitionId, java.lang.Long planVersion)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByPlanIdPlanVersion(planId, planSectionDefinitionId,
            planVersion);
    }

    /**
    * Returns the number of plan sections.
    *
    * @return the number of plan sections
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PlanSectionPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PlanSectionPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.plans.service.ClpSerializer.getServletContextName(),
                    PlanSectionPersistence.class.getName());

            ReferenceRegistry.registerReference(PlanSectionUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(PlanSectionPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(PlanSectionUtil.class,
            "_persistence");
    }
}
