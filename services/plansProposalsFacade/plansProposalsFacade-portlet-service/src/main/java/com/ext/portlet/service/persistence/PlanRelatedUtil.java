package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanRelated;

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
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
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
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static PlanRelated update(PlanRelated planRelated)
        throws SystemException {
        return getPersistence().update(planRelated);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static PlanRelated update(PlanRelated planRelated,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(planRelated, serviceContext);
    }

    /**
    * Returns all the plan relateds where relatedPlanId = &#63;.
    *
    * @param relatedPlanId the related plan ID
    * @return the matching plan relateds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanRelated> findByPlanId(
        long relatedPlanId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanId(relatedPlanId);
    }

    /**
    * Returns a range of all the plan relateds where relatedPlanId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param relatedPlanId the related plan ID
    * @param start the lower bound of the range of plan relateds
    * @param end the upper bound of the range of plan relateds (not inclusive)
    * @return the range of matching plan relateds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanRelated> findByPlanId(
        long relatedPlanId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPlanId(relatedPlanId, start, end);
    }

    /**
    * Returns an ordered range of all the plan relateds where relatedPlanId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param relatedPlanId the related plan ID
    * @param start the lower bound of the range of plan relateds
    * @param end the upper bound of the range of plan relateds (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan relateds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanRelated> findByPlanId(
        long relatedPlanId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanId(relatedPlanId, start, end, orderByComparator);
    }

    /**
    * Returns the first plan related in the ordered set where relatedPlanId = &#63;.
    *
    * @param relatedPlanId the related plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan related
    * @throws com.ext.portlet.NoSuchPlanRelatedException if a matching plan related could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanRelated findByPlanId_First(
        long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanRelatedException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanId_First(relatedPlanId, orderByComparator);
    }

    /**
    * Returns the first plan related in the ordered set where relatedPlanId = &#63;.
    *
    * @param relatedPlanId the related plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan related, or <code>null</code> if a matching plan related could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanRelated fetchByPlanId_First(
        long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPlanId_First(relatedPlanId, orderByComparator);
    }

    /**
    * Returns the last plan related in the ordered set where relatedPlanId = &#63;.
    *
    * @param relatedPlanId the related plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan related
    * @throws com.ext.portlet.NoSuchPlanRelatedException if a matching plan related could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanRelated findByPlanId_Last(
        long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanRelatedException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanId_Last(relatedPlanId, orderByComparator);
    }

    /**
    * Returns the last plan related in the ordered set where relatedPlanId = &#63;.
    *
    * @param relatedPlanId the related plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan related, or <code>null</code> if a matching plan related could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanRelated fetchByPlanId_Last(
        long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByPlanId_Last(relatedPlanId, orderByComparator);
    }

    /**
    * Returns the plan relateds before and after the current plan related in the ordered set where relatedPlanId = &#63;.
    *
    * @param planRelatedPK the primary key of the current plan related
    * @param relatedPlanId the related plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan related
    * @throws com.ext.portlet.NoSuchPlanRelatedException if a plan related with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanRelated[] findByPlanId_PrevAndNext(
        com.ext.portlet.service.persistence.PlanRelatedPK planRelatedPK,
        long relatedPlanId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanRelatedException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPlanId_PrevAndNext(planRelatedPK, relatedPlanId,
            orderByComparator);
    }

    /**
    * Removes all the plan relateds where relatedPlanId = &#63; from the database.
    *
    * @param relatedPlanId the related plan ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByPlanId(long relatedPlanId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByPlanId(relatedPlanId);
    }

    /**
    * Returns the number of plan relateds where relatedPlanId = &#63;.
    *
    * @param relatedPlanId the related plan ID
    * @return the number of matching plan relateds
    * @throws SystemException if a system exception occurred
    */
    public static int countByPlanId(long relatedPlanId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByPlanId(relatedPlanId);
    }

    /**
    * Caches the plan related in the entity cache if it is enabled.
    *
    * @param planRelated the plan related
    */
    public static void cacheResult(
        com.ext.portlet.model.PlanRelated planRelated) {
        getPersistence().cacheResult(planRelated);
    }

    /**
    * Caches the plan relateds in the entity cache if it is enabled.
    *
    * @param planRelateds the plan relateds
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.PlanRelated> planRelateds) {
        getPersistence().cacheResult(planRelateds);
    }

    /**
    * Creates a new plan related with the primary key. Does not add the plan related to the database.
    *
    * @param planRelatedPK the primary key for the new plan related
    * @return the new plan related
    */
    public static com.ext.portlet.model.PlanRelated create(
        com.ext.portlet.service.persistence.PlanRelatedPK planRelatedPK) {
        return getPersistence().create(planRelatedPK);
    }

    /**
    * Removes the plan related with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planRelatedPK the primary key of the plan related
    * @return the plan related that was removed
    * @throws com.ext.portlet.NoSuchPlanRelatedException if a plan related with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanRelated remove(
        com.ext.portlet.service.persistence.PlanRelatedPK planRelatedPK)
        throws com.ext.portlet.NoSuchPlanRelatedException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(planRelatedPK);
    }

    public static com.ext.portlet.model.PlanRelated updateImpl(
        com.ext.portlet.model.PlanRelated planRelated)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planRelated);
    }

    /**
    * Returns the plan related with the primary key or throws a {@link com.ext.portlet.NoSuchPlanRelatedException} if it could not be found.
    *
    * @param planRelatedPK the primary key of the plan related
    * @return the plan related
    * @throws com.ext.portlet.NoSuchPlanRelatedException if a plan related with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanRelated findByPrimaryKey(
        com.ext.portlet.service.persistence.PlanRelatedPK planRelatedPK)
        throws com.ext.portlet.NoSuchPlanRelatedException,
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
    public static com.ext.portlet.model.PlanRelated fetchByPrimaryKey(
        com.ext.portlet.service.persistence.PlanRelatedPK planRelatedPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(planRelatedPK);
    }

    /**
    * Returns all the plan relateds.
    *
    * @return the plan relateds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanRelated> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the plan relateds.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan relateds
    * @param end the upper bound of the range of plan relateds (not inclusive)
    * @return the range of plan relateds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanRelated> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the plan relateds.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanRelatedModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan relateds
    * @param end the upper bound of the range of plan relateds (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan relateds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanRelated> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
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
            _persistence = (PlanRelatedPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    PlanRelatedPersistence.class.getName());

            ReferenceRegistry.registerReference(PlanRelatedUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(PlanRelatedPersistence persistence) {
    }
}
