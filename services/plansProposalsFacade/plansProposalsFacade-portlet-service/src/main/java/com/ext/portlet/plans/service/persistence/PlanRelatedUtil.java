package com.ext.portlet.plans.service.persistence;

import com.ext.portlet.plans.model.PlanRelated;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the plan related service. This utility wraps {@link PlanRelatedPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanRelatedPersistence
 * @see PlanRelatedPersistenceImpl
 * @generated
 */
public class PlanRelatedUtil {
    private static PlanRelatedPersistence _persistence;

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
    public static void clearCache(PlanRelated planRelated) {
        getPersistence().clearCache(planRelated);
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
    public static List<PlanRelated> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PlanRelated> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PlanRelated> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static PlanRelated update(PlanRelated planRelated, boolean merge)
        throws SystemException {
        return getPersistence().update(planRelated, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static PlanRelated update(PlanRelated planRelated, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(planRelated, merge, serviceContext);
    }

    /**
    * Caches the plan related in the entity cache if it is enabled.
    *
    * @param planRelated the plan related
    */
    public static void cacheResult(
        com.ext.portlet.plans.model.PlanRelated planRelated) {
        getPersistence().cacheResult(planRelated);
    }

    /**
    * Caches the plan relateds in the entity cache if it is enabled.
    *
    * @param planRelateds the plan relateds
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.plans.model.PlanRelated> planRelateds) {
        getPersistence().cacheResult(planRelateds);
    }

    /**
    * Creates a new plan related with the primary key. Does not add the plan related to the database.
    *
    * @param planRelatedPK the primary key for the new plan related
    * @return the new plan related
    */
    public static com.ext.portlet.plans.model.PlanRelated create(
        PlanRelatedPK planRelatedPK) {
        return getPersistence().create(planRelatedPK);
    }

    /**
    * Removes the plan related with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planRelatedPK the primary key of the plan related
    * @return the plan related that was removed
    * @throws com.ext.portlet.plans.NoSuchPlanRelatedException if a plan related with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanRelated remove(
        PlanRelatedPK planRelatedPK)
        throws com.ext.portlet.plans.NoSuchPlanRelatedException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(planRelatedPK);
    }

    public static com.ext.portlet.plans.model.PlanRelated updateImpl(
        com.ext.portlet.plans.model.PlanRelated planRelated, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planRelated, merge);
    }

    /**
    * Returns the plan related with the primary key or throws a {@link com.ext.portlet.plans.NoSuchPlanRelatedException} if it could not be found.
    *
    * @param planRelatedPK the primary key of the plan related
    * @return the plan related
    * @throws com.ext.portlet.plans.NoSuchPlanRelatedException if a plan related with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanRelated findByPrimaryKey(
        PlanRelatedPK planRelatedPK)
        throws com.ext.portlet.plans.NoSuchPlanRelatedException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(planRelatedPK);
    }

    /**
    * Returns the plan related with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planRelatedPK the primary key of the plan related
    * @return the plan related, or <code>null</code> if a plan related with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanRelated fetchByPrimaryKey(
        PlanRelatedPK planRelatedPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(planRelatedPK);
    }

    /**
    * Returns all the plan relateds where relatedPlanId = &#63;.
    *
    * @param relatedPlanId the related plan ID
    * @return the matching plan relateds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanRelated> findByPlanId(
        java.lang.Long relatedPlanId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanId(relatedPlanId);
    }

    /**
    * Returns a range of all the plan relateds where relatedPlanId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param relatedPlanId the related plan ID
    * @param start the lower bound of the range of plan relateds
    * @param end the upper bound of the range of plan relateds (not inclusive)
    * @return the range of matching plan relateds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanRelated> findByPlanId(
        java.lang.Long relatedPlanId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanId(relatedPlanId, start, end);
    }

    /**
    * Returns an ordered range of all the plan relateds where relatedPlanId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param relatedPlanId the related plan ID
    * @param start the lower bound of the range of plan relateds
    * @param end the upper bound of the range of plan relateds (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan relateds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanRelated> findByPlanId(
        java.lang.Long relatedPlanId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanId(relatedPlanId, start, end, orderByComparator);
    }

    /**
    * Returns the first plan related in the ordered set where relatedPlanId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param relatedPlanId the related plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan related
    * @throws com.ext.portlet.plans.NoSuchPlanRelatedException if a matching plan related could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanRelated findByPlanId_First(
        java.lang.Long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanRelatedException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanId_First(relatedPlanId, orderByComparator);
    }

    /**
    * Returns the last plan related in the ordered set where relatedPlanId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param relatedPlanId the related plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan related
    * @throws com.ext.portlet.plans.NoSuchPlanRelatedException if a matching plan related could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanRelated findByPlanId_Last(
        java.lang.Long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanRelatedException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanId_Last(relatedPlanId, orderByComparator);
    }

    /**
    * Returns the plan relateds before and after the current plan related in the ordered set where relatedPlanId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planRelatedPK the primary key of the current plan related
    * @param relatedPlanId the related plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan related
    * @throws com.ext.portlet.plans.NoSuchPlanRelatedException if a plan related with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanRelated[] findByPlanId_PrevAndNext(
        PlanRelatedPK planRelatedPK, java.lang.Long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.plans.NoSuchPlanRelatedException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanId_PrevAndNext(planRelatedPK, relatedPlanId,
            orderByComparator);
    }

    /**
    * Returns all the plan relateds.
    *
    * @return the plan relateds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanRelated> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
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
    public static java.util.List<com.ext.portlet.plans.model.PlanRelated> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the plan relateds.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan relateds
    * @param end the upper bound of the range of plan relateds (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan relateds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanRelated> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the plan relateds where relatedPlanId = &#63; from the database.
    *
    * @param relatedPlanId the related plan ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByPlanId(java.lang.Long relatedPlanId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByPlanId(relatedPlanId);
    }

    /**
    * Removes all the plan relateds from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of plan relateds where relatedPlanId = &#63;.
    *
    * @param relatedPlanId the related plan ID
    * @return the number of matching plan relateds
    * @throws SystemException if a system exception occurred
    */
    public static int countByPlanId(java.lang.Long relatedPlanId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByPlanId(relatedPlanId);
    }

    /**
    * Returns the number of plan relateds.
    *
    * @return the number of plan relateds
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PlanRelatedPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PlanRelatedPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.plans.service.ClpSerializer.getServletContextName(),
                    PlanRelatedPersistence.class.getName());

            ReferenceRegistry.registerReference(PlanRelatedUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(PlanRelatedPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(PlanRelatedUtil.class,
            "_persistence");
    }
}
