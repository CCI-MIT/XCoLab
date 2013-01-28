package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanDescription;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the plan description service. This utility wraps {@link PlanDescriptionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanDescriptionPersistence
 * @see PlanDescriptionPersistenceImpl
 * @generated
 */
public class PlanDescriptionUtil {
    private static PlanDescriptionPersistence _persistence;

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
    public static void clearCache(PlanDescription planDescription) {
        getPersistence().clearCache(planDescription);
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
    public static List<PlanDescription> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PlanDescription> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PlanDescription> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static PlanDescription update(PlanDescription planDescription,
        boolean merge) throws SystemException {
        return getPersistence().update(planDescription, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static PlanDescription update(PlanDescription planDescription,
        boolean merge, ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(planDescription, merge, serviceContext);
    }

    /**
    * Caches the plan description in the entity cache if it is enabled.
    *
    * @param planDescription the plan description
    */
    public static void cacheResult(
        com.ext.portlet.model.PlanDescription planDescription) {
        getPersistence().cacheResult(planDescription);
    }

    /**
    * Caches the plan descriptions in the entity cache if it is enabled.
    *
    * @param planDescriptions the plan descriptions
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.PlanDescription> planDescriptions) {
        getPersistence().cacheResult(planDescriptions);
    }

    /**
    * Creates a new plan description with the primary key. Does not add the plan description to the database.
    *
    * @param id the primary key for the new plan description
    * @return the new plan description
    */
    public static com.ext.portlet.model.PlanDescription create(long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the plan description with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan description
    * @return the plan description that was removed
    * @throws com.ext.portlet.NoSuchPlanDescriptionException if a plan description with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanDescription remove(long id)
        throws com.ext.portlet.NoSuchPlanDescriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.PlanDescription updateImpl(
        com.ext.portlet.model.PlanDescription planDescription, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planDescription, merge);
    }

    /**
    * Returns the plan description with the primary key or throws a {@link com.ext.portlet.NoSuchPlanDescriptionException} if it could not be found.
    *
    * @param id the primary key of the plan description
    * @return the plan description
    * @throws com.ext.portlet.NoSuchPlanDescriptionException if a plan description with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanDescription findByPrimaryKey(
        long id)
        throws com.ext.portlet.NoSuchPlanDescriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the plan description with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the plan description
    * @return the plan description, or <code>null</code> if a plan description with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanDescription fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns the plan description where planId = &#63; or throws a {@link com.ext.portlet.NoSuchPlanDescriptionException} if it could not be found.
    *
    * @param planId the plan ID
    * @return the matching plan description
    * @throws com.ext.portlet.NoSuchPlanDescriptionException if a matching plan description could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanDescription findByCurrentByPlanId(
        long planId)
        throws com.ext.portlet.NoSuchPlanDescriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCurrentByPlanId(planId);
    }

    /**
    * Returns the plan description where planId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planId the plan ID
    * @return the matching plan description, or <code>null</code> if a matching plan description could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanDescription fetchByCurrentByPlanId(
        long planId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCurrentByPlanId(planId);
    }

    /**
    * Returns the plan description where planId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planId the plan ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan description, or <code>null</code> if a matching plan description could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanDescription fetchByCurrentByPlanId(
        long planId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCurrentByPlanId(planId, retrieveFromCache);
    }

    /**
    * Returns the plan description where planId = &#63; and planVersion &le; &#63; or throws a {@link com.ext.portlet.NoSuchPlanDescriptionException} if it could not be found.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @return the matching plan description
    * @throws com.ext.portlet.NoSuchPlanDescriptionException if a matching plan description could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanDescription findByPlanIdPlanVersion(
        long planId, long planVersion)
        throws com.ext.portlet.NoSuchPlanDescriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanIdPlanVersion(planId, planVersion);
    }

    /**
    * Returns the plan description where planId = &#63; and planVersion &le; &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @return the matching plan description, or <code>null</code> if a matching plan description could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanDescription fetchByPlanIdPlanVersion(
        long planId, long planVersion)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPlanIdPlanVersion(planId, planVersion);
    }

    /**
    * Returns the plan description where planId = &#63; and planVersion &le; &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan description, or <code>null</code> if a matching plan description could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanDescription fetchByPlanIdPlanVersion(
        long planId, long planVersion, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPlanIdPlanVersion(planId, planVersion,
            retrieveFromCache);
    }

    /**
    * Returns all the plan descriptions where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the matching plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanDescription> findByAllByPlanId(
        long planId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByAllByPlanId(planId);
    }

    /**
    * Returns a range of all the plan descriptions where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan descriptions
    * @param end the upper bound of the range of plan descriptions (not inclusive)
    * @return the range of matching plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanDescription> findByAllByPlanId(
        long planId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByAllByPlanId(planId, start, end);
    }

    /**
    * Returns an ordered range of all the plan descriptions where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan descriptions
    * @param end the upper bound of the range of plan descriptions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanDescription> findByAllByPlanId(
        long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAllByPlanId(planId, start, end, orderByComparator);
    }

    /**
    * Returns the first plan description in the ordered set where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan description
    * @throws com.ext.portlet.NoSuchPlanDescriptionException if a matching plan description could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanDescription findByAllByPlanId_First(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanDescriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAllByPlanId_First(planId, orderByComparator);
    }

    /**
    * Returns the last plan description in the ordered set where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan description
    * @throws com.ext.portlet.NoSuchPlanDescriptionException if a matching plan description could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanDescription findByAllByPlanId_Last(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanDescriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByAllByPlanId_Last(planId, orderByComparator);
    }

    /**
    * Returns the plan descriptions before and after the current plan description in the ordered set where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param id the primary key of the current plan description
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan description
    * @throws com.ext.portlet.NoSuchPlanDescriptionException if a plan description with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanDescription[] findByAllByPlanId_PrevAndNext(
        long id, long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanDescriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAllByPlanId_PrevAndNext(id, planId, orderByComparator);
    }

    /**
    * Returns all the plan descriptions.
    *
    * @return the plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanDescription> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the plan descriptions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan descriptions
    * @param end the upper bound of the range of plan descriptions (not inclusive)
    * @return the range of plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanDescription> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the plan descriptions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan descriptions
    * @param end the upper bound of the range of plan descriptions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanDescription> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes the plan description where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCurrentByPlanId(long planId)
        throws com.ext.portlet.NoSuchPlanDescriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCurrentByPlanId(planId);
    }

    /**
    * Removes the plan description where planId = &#63; and planVersion &le; &#63; from the database.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @throws SystemException if a system exception occurred
    */
    public static void removeByPlanIdPlanVersion(long planId, long planVersion)
        throws com.ext.portlet.NoSuchPlanDescriptionException,
            com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByPlanIdPlanVersion(planId, planVersion);
    }

    /**
    * Removes all the plan descriptions where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByAllByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByAllByPlanId(planId);
    }

    /**
    * Removes all the plan descriptions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of plan descriptions where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public static int countByCurrentByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCurrentByPlanId(planId);
    }

    /**
    * Returns the number of plan descriptions where planId = &#63; and planVersion &le; &#63;.
    *
    * @param planId the plan ID
    * @param planVersion the plan version
    * @return the number of matching plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public static int countByPlanIdPlanVersion(long planId, long planVersion)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByPlanIdPlanVersion(planId, planVersion);
    }

    /**
    * Returns the number of plan descriptions where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public static int countByAllByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByAllByPlanId(planId);
    }

    /**
    * Returns the number of plan descriptions.
    *
    * @return the number of plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PlanDescriptionPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PlanDescriptionPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    PlanDescriptionPersistence.class.getName());

            ReferenceRegistry.registerReference(PlanDescriptionUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(PlanDescriptionPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(PlanDescriptionUtil.class,
            "_persistence");
    }
}
