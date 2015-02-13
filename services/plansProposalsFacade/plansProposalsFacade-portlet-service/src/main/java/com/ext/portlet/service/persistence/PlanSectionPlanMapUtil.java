package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanSectionPlanMap;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the plan section plan map service. This utility wraps {@link PlanSectionPlanMapPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionPlanMapPersistence
 * @see PlanSectionPlanMapPersistenceImpl
 * @generated
 */
public class PlanSectionPlanMapUtil {
    private static PlanSectionPlanMapPersistence _persistence;

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
    public static void clearCache(PlanSectionPlanMap planSectionPlanMap) {
        getPersistence().clearCache(planSectionPlanMap);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<PlanSectionPlanMap> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PlanSectionPlanMap> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PlanSectionPlanMap> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static PlanSectionPlanMap update(
        PlanSectionPlanMap planSectionPlanMap) throws SystemException {
        return getPersistence().update(planSectionPlanMap);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static PlanSectionPlanMap update(
        PlanSectionPlanMap planSectionPlanMap, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(planSectionPlanMap, serviceContext);
    }

    /**
    * Returns all the plan section plan maps where relatedPlanId = &#63;.
    *
    * @param relatedPlanId the related plan ID
    * @return the matching plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanSectionPlanMap> findByPlanId(
        long relatedPlanId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanId(relatedPlanId);
    }

    /**
    * Returns a range of all the plan section plan maps where relatedPlanId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanSectionPlanMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param relatedPlanId the related plan ID
    * @param start the lower bound of the range of plan section plan maps
    * @param end the upper bound of the range of plan section plan maps (not inclusive)
    * @return the range of matching plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanSectionPlanMap> findByPlanId(
        long relatedPlanId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanId(relatedPlanId, start, end);
    }

    /**
    * Returns an ordered range of all the plan section plan maps where relatedPlanId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanSectionPlanMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param relatedPlanId the related plan ID
    * @param start the lower bound of the range of plan section plan maps
    * @param end the upper bound of the range of plan section plan maps (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanSectionPlanMap> findByPlanId(
        long relatedPlanId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanId(relatedPlanId, start, end, orderByComparator);
    }

    /**
    * Returns the first plan section plan map in the ordered set where relatedPlanId = &#63;.
    *
    * @param relatedPlanId the related plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan section plan map
    * @throws com.ext.portlet.NoSuchPlanSectionPlanMapException if a matching plan section plan map could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanSectionPlanMap findByPlanId_First(
        long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanId_First(relatedPlanId, orderByComparator);
    }

    /**
    * Returns the first plan section plan map in the ordered set where relatedPlanId = &#63;.
    *
    * @param relatedPlanId the related plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan section plan map, or <code>null</code> if a matching plan section plan map could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanSectionPlanMap fetchByPlanId_First(
        long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPlanId_First(relatedPlanId, orderByComparator);
    }

    /**
    * Returns the last plan section plan map in the ordered set where relatedPlanId = &#63;.
    *
    * @param relatedPlanId the related plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan section plan map
    * @throws com.ext.portlet.NoSuchPlanSectionPlanMapException if a matching plan section plan map could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanSectionPlanMap findByPlanId_Last(
        long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanId_Last(relatedPlanId, orderByComparator);
    }

    /**
    * Returns the last plan section plan map in the ordered set where relatedPlanId = &#63;.
    *
    * @param relatedPlanId the related plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan section plan map, or <code>null</code> if a matching plan section plan map could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanSectionPlanMap fetchByPlanId_Last(
        long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPlanId_Last(relatedPlanId, orderByComparator);
    }

    /**
    * Returns the plan section plan maps before and after the current plan section plan map in the ordered set where relatedPlanId = &#63;.
    *
    * @param planSectionPlanMapPK the primary key of the current plan section plan map
    * @param relatedPlanId the related plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan section plan map
    * @throws com.ext.portlet.NoSuchPlanSectionPlanMapException if a plan section plan map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanSectionPlanMap[] findByPlanId_PrevAndNext(
        PlanSectionPlanMapPK planSectionPlanMapPK, long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanId_PrevAndNext(planSectionPlanMapPK,
            relatedPlanId, orderByComparator);
    }

    /**
    * Removes all the plan section plan maps where relatedPlanId = &#63; from the database.
    *
    * @param relatedPlanId the related plan ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByPlanId(long relatedPlanId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByPlanId(relatedPlanId);
    }

    /**
    * Returns the number of plan section plan maps where relatedPlanId = &#63;.
    *
    * @param relatedPlanId the related plan ID
    * @return the number of matching plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public static int countByPlanId(long relatedPlanId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByPlanId(relatedPlanId);
    }

    /**
    * Returns all the plan section plan maps where sectionId = &#63;.
    *
    * @param sectionId the section ID
    * @return the matching plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanSectionPlanMap> findBySectionId(
        long sectionId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySectionId(sectionId);
    }

    /**
    * Returns a range of all the plan section plan maps where sectionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanSectionPlanMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param sectionId the section ID
    * @param start the lower bound of the range of plan section plan maps
    * @param end the upper bound of the range of plan section plan maps (not inclusive)
    * @return the range of matching plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanSectionPlanMap> findBySectionId(
        long sectionId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBySectionId(sectionId, start, end);
    }

    /**
    * Returns an ordered range of all the plan section plan maps where sectionId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanSectionPlanMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param sectionId the section ID
    * @param start the lower bound of the range of plan section plan maps
    * @param end the upper bound of the range of plan section plan maps (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanSectionPlanMap> findBySectionId(
        long sectionId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySectionId(sectionId, start, end, orderByComparator);
    }

    /**
    * Returns the first plan section plan map in the ordered set where sectionId = &#63;.
    *
    * @param sectionId the section ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan section plan map
    * @throws com.ext.portlet.NoSuchPlanSectionPlanMapException if a matching plan section plan map could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanSectionPlanMap findBySectionId_First(
        long sectionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySectionId_First(sectionId, orderByComparator);
    }

    /**
    * Returns the first plan section plan map in the ordered set where sectionId = &#63;.
    *
    * @param sectionId the section ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan section plan map, or <code>null</code> if a matching plan section plan map could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanSectionPlanMap fetchBySectionId_First(
        long sectionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySectionId_First(sectionId, orderByComparator);
    }

    /**
    * Returns the last plan section plan map in the ordered set where sectionId = &#63;.
    *
    * @param sectionId the section ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan section plan map
    * @throws com.ext.portlet.NoSuchPlanSectionPlanMapException if a matching plan section plan map could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanSectionPlanMap findBySectionId_Last(
        long sectionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySectionId_Last(sectionId, orderByComparator);
    }

    /**
    * Returns the last plan section plan map in the ordered set where sectionId = &#63;.
    *
    * @param sectionId the section ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan section plan map, or <code>null</code> if a matching plan section plan map could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanSectionPlanMap fetchBySectionId_Last(
        long sectionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBySectionId_Last(sectionId, orderByComparator);
    }

    /**
    * Returns the plan section plan maps before and after the current plan section plan map in the ordered set where sectionId = &#63;.
    *
    * @param planSectionPlanMapPK the primary key of the current plan section plan map
    * @param sectionId the section ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan section plan map
    * @throws com.ext.portlet.NoSuchPlanSectionPlanMapException if a plan section plan map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanSectionPlanMap[] findBySectionId_PrevAndNext(
        PlanSectionPlanMapPK planSectionPlanMapPK, long sectionId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findBySectionId_PrevAndNext(planSectionPlanMapPK,
            sectionId, orderByComparator);
    }

    /**
    * Removes all the plan section plan maps where sectionId = &#63; from the database.
    *
    * @param sectionId the section ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeBySectionId(long sectionId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeBySectionId(sectionId);
    }

    /**
    * Returns the number of plan section plan maps where sectionId = &#63;.
    *
    * @param sectionId the section ID
    * @return the number of matching plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public static int countBySectionId(long sectionId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countBySectionId(sectionId);
    }

    /**
    * Caches the plan section plan map in the entity cache if it is enabled.
    *
    * @param planSectionPlanMap the plan section plan map
    */
    public static void cacheResult(
        com.ext.portlet.model.PlanSectionPlanMap planSectionPlanMap) {
        getPersistence().cacheResult(planSectionPlanMap);
    }

    /**
    * Caches the plan section plan maps in the entity cache if it is enabled.
    *
    * @param planSectionPlanMaps the plan section plan maps
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.PlanSectionPlanMap> planSectionPlanMaps) {
        getPersistence().cacheResult(planSectionPlanMaps);
    }

    /**
    * Creates a new plan section plan map with the primary key. Does not add the plan section plan map to the database.
    *
    * @param planSectionPlanMapPK the primary key for the new plan section plan map
    * @return the new plan section plan map
    */
    public static com.ext.portlet.model.PlanSectionPlanMap create(
        PlanSectionPlanMapPK planSectionPlanMapPK) {
        return getPersistence().create(planSectionPlanMapPK);
    }

    /**
    * Removes the plan section plan map with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planSectionPlanMapPK the primary key of the plan section plan map
    * @return the plan section plan map that was removed
    * @throws com.ext.portlet.NoSuchPlanSectionPlanMapException if a plan section plan map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanSectionPlanMap remove(
        PlanSectionPlanMapPK planSectionPlanMapPK)
        throws com.ext.portlet.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(planSectionPlanMapPK);
    }

    public static com.ext.portlet.model.PlanSectionPlanMap updateImpl(
        com.ext.portlet.model.PlanSectionPlanMap planSectionPlanMap)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planSectionPlanMap);
    }

    /**
    * Returns the plan section plan map with the primary key or throws a {@link com.ext.portlet.NoSuchPlanSectionPlanMapException} if it could not be found.
    *
    * @param planSectionPlanMapPK the primary key of the plan section plan map
    * @return the plan section plan map
    * @throws com.ext.portlet.NoSuchPlanSectionPlanMapException if a plan section plan map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanSectionPlanMap findByPrimaryKey(
        PlanSectionPlanMapPK planSectionPlanMapPK)
        throws com.ext.portlet.NoSuchPlanSectionPlanMapException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(planSectionPlanMapPK);
    }

    /**
    * Returns the plan section plan map with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planSectionPlanMapPK the primary key of the plan section plan map
    * @return the plan section plan map, or <code>null</code> if a plan section plan map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanSectionPlanMap fetchByPrimaryKey(
        PlanSectionPlanMapPK planSectionPlanMapPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(planSectionPlanMapPK);
    }

    /**
    * Returns all the plan section plan maps.
    *
    * @return the plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanSectionPlanMap> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the plan section plan maps.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanSectionPlanMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan section plan maps
    * @param end the upper bound of the range of plan section plan maps (not inclusive)
    * @return the range of plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanSectionPlanMap> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the plan section plan maps.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanSectionPlanMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan section plan maps
    * @param end the upper bound of the range of plan section plan maps (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanSectionPlanMap> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the plan section plan maps from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of plan section plan maps.
    *
    * @return the number of plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PlanSectionPlanMapPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PlanSectionPlanMapPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    PlanSectionPlanMapPersistence.class.getName());

            ReferenceRegistry.registerReference(PlanSectionPlanMapUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(PlanSectionPlanMapPersistence persistence) {
    }
}
