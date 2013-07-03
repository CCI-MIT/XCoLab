package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanItemPhaseMap;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the plan item phase map service. This utility wraps {@link PlanItemPhaseMapPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanItemPhaseMapPersistence
 * @see PlanItemPhaseMapPersistenceImpl
 * @generated
 */
public class PlanItemPhaseMapUtil {
    private static PlanItemPhaseMapPersistence _persistence;

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
    public static void clearCache(PlanItemPhaseMap planItemPhaseMap) {
        getPersistence().clearCache(planItemPhaseMap);
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
    public static List<PlanItemPhaseMap> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PlanItemPhaseMap> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PlanItemPhaseMap> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static PlanItemPhaseMap update(PlanItemPhaseMap planItemPhaseMap,
        boolean merge) throws SystemException {
        return getPersistence().update(planItemPhaseMap, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static PlanItemPhaseMap update(PlanItemPhaseMap planItemPhaseMap,
        boolean merge, ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(planItemPhaseMap, merge, serviceContext);
    }

    /**
    * Caches the plan item phase map in the entity cache if it is enabled.
    *
    * @param planItemPhaseMap the plan item phase map
    */
    public static void cacheResult(
        com.ext.portlet.model.PlanItemPhaseMap planItemPhaseMap) {
        getPersistence().cacheResult(planItemPhaseMap);
    }

    /**
    * Caches the plan item phase maps in the entity cache if it is enabled.
    *
    * @param planItemPhaseMaps the plan item phase maps
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.PlanItemPhaseMap> planItemPhaseMaps) {
        getPersistence().cacheResult(planItemPhaseMaps);
    }

    /**
    * Creates a new plan item phase map with the primary key. Does not add the plan item phase map to the database.
    *
    * @param id the primary key for the new plan item phase map
    * @return the new plan item phase map
    */
    public static com.ext.portlet.model.PlanItemPhaseMap create(long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the plan item phase map with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan item phase map
    * @return the plan item phase map that was removed
    * @throws com.ext.portlet.NoSuchPlanItemPhaseMapException if a plan item phase map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanItemPhaseMap remove(long id)
        throws com.ext.portlet.NoSuchPlanItemPhaseMapException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.PlanItemPhaseMap updateImpl(
        com.ext.portlet.model.PlanItemPhaseMap planItemPhaseMap, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planItemPhaseMap, merge);
    }

    /**
    * Returns the plan item phase map with the primary key or throws a {@link com.ext.portlet.NoSuchPlanItemPhaseMapException} if it could not be found.
    *
    * @param id the primary key of the plan item phase map
    * @return the plan item phase map
    * @throws com.ext.portlet.NoSuchPlanItemPhaseMapException if a plan item phase map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanItemPhaseMap findByPrimaryKey(
        long id)
        throws com.ext.portlet.NoSuchPlanItemPhaseMapException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the plan item phase map with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the plan item phase map
    * @return the plan item phase map, or <code>null</code> if a plan item phase map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanItemPhaseMap fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the plan item phase maps.
    *
    * @return the plan item phase maps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanItemPhaseMap> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the plan item phase maps.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan item phase maps
    * @param end the upper bound of the range of plan item phase maps (not inclusive)
    * @return the range of plan item phase maps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanItemPhaseMap> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the plan item phase maps.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan item phase maps
    * @param end the upper bound of the range of plan item phase maps (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan item phase maps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanItemPhaseMap> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the plan item phase maps from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of plan item phase maps.
    *
    * @return the number of plan item phase maps
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PlanItemPhaseMapPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PlanItemPhaseMapPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    PlanItemPhaseMapPersistence.class.getName());

            ReferenceRegistry.registerReference(PlanItemPhaseMapUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(PlanItemPhaseMapPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(PlanItemPhaseMapUtil.class,
            "_persistence");
    }
}
