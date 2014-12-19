package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanMeta;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the plan meta service. This utility wraps {@link PlanMetaPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanMetaPersistence
 * @see PlanMetaPersistenceImpl
 * @generated
 */
public class PlanMetaUtil {
    private static PlanMetaPersistence _persistence;

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
    public static void clearCache(PlanMeta planMeta) {
        getPersistence().clearCache(planMeta);
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
    public static List<PlanMeta> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PlanMeta> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PlanMeta> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static PlanMeta update(PlanMeta planMeta) throws SystemException {
        return getPersistence().update(planMeta);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static PlanMeta update(PlanMeta planMeta,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(planMeta, serviceContext);
    }

    /**
    * Returns the plan meta where planId = &#63; or throws a {@link com.ext.portlet.NoSuchPlanMetaException} if it could not be found.
    *
    * @param planId the plan ID
    * @return the matching plan meta
    * @throws com.ext.portlet.NoSuchPlanMetaException if a matching plan meta could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanMeta findByCurrentByPlanId(
        long planId)
        throws com.ext.portlet.NoSuchPlanMetaException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCurrentByPlanId(planId);
    }

    /**
    * Returns the plan meta where planId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param planId the plan ID
    * @return the matching plan meta, or <code>null</code> if a matching plan meta could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanMeta fetchByCurrentByPlanId(
        long planId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCurrentByPlanId(planId);
    }

    /**
    * Returns the plan meta where planId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param planId the plan ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching plan meta, or <code>null</code> if a matching plan meta could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanMeta fetchByCurrentByPlanId(
        long planId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCurrentByPlanId(planId, retrieveFromCache);
    }

    /**
    * Removes the plan meta where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @return the plan meta that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanMeta removeByCurrentByPlanId(
        long planId)
        throws com.ext.portlet.NoSuchPlanMetaException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().removeByCurrentByPlanId(planId);
    }

    /**
    * Returns the number of plan metas where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan metas
    * @throws SystemException if a system exception occurred
    */
    public static int countByCurrentByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCurrentByPlanId(planId);
    }

    /**
    * Returns all the plan metas where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the matching plan metas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanMeta> findByAllByPlanId(
        long planId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByAllByPlanId(planId);
    }

    /**
    * Returns a range of all the plan metas where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanMetaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan metas
    * @param end the upper bound of the range of plan metas (not inclusive)
    * @return the range of matching plan metas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanMeta> findByAllByPlanId(
        long planId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByAllByPlanId(planId, start, end);
    }

    /**
    * Returns an ordered range of all the plan metas where planId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanMetaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param planId the plan ID
    * @param start the lower bound of the range of plan metas
    * @param end the upper bound of the range of plan metas (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan metas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanMeta> findByAllByPlanId(
        long planId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAllByPlanId(planId, start, end, orderByComparator);
    }

    /**
    * Returns the first plan meta in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan meta
    * @throws com.ext.portlet.NoSuchPlanMetaException if a matching plan meta could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanMeta findByAllByPlanId_First(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanMetaException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAllByPlanId_First(planId, orderByComparator);
    }

    /**
    * Returns the first plan meta in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan meta, or <code>null</code> if a matching plan meta could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanMeta fetchByAllByPlanId_First(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByAllByPlanId_First(planId, orderByComparator);
    }

    /**
    * Returns the last plan meta in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan meta
    * @throws com.ext.portlet.NoSuchPlanMetaException if a matching plan meta could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanMeta findByAllByPlanId_Last(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanMetaException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByAllByPlanId_Last(planId, orderByComparator);
    }

    /**
    * Returns the last plan meta in the ordered set where planId = &#63;.
    *
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan meta, or <code>null</code> if a matching plan meta could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanMeta fetchByAllByPlanId_Last(
        long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByAllByPlanId_Last(planId, orderByComparator);
    }

    /**
    * Returns the plan metas before and after the current plan meta in the ordered set where planId = &#63;.
    *
    * @param id the primary key of the current plan meta
    * @param planId the plan ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan meta
    * @throws com.ext.portlet.NoSuchPlanMetaException if a plan meta with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanMeta[] findByAllByPlanId_PrevAndNext(
        long id, long planId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlanMetaException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByAllByPlanId_PrevAndNext(id, planId, orderByComparator);
    }

    /**
    * Removes all the plan metas where planId = &#63; from the database.
    *
    * @param planId the plan ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByAllByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByAllByPlanId(planId);
    }

    /**
    * Returns the number of plan metas where planId = &#63;.
    *
    * @param planId the plan ID
    * @return the number of matching plan metas
    * @throws SystemException if a system exception occurred
    */
    public static int countByAllByPlanId(long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByAllByPlanId(planId);
    }

    /**
    * Caches the plan meta in the entity cache if it is enabled.
    *
    * @param planMeta the plan meta
    */
    public static void cacheResult(com.ext.portlet.model.PlanMeta planMeta) {
        getPersistence().cacheResult(planMeta);
    }

    /**
    * Caches the plan metas in the entity cache if it is enabled.
    *
    * @param planMetas the plan metas
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.PlanMeta> planMetas) {
        getPersistence().cacheResult(planMetas);
    }

    /**
    * Creates a new plan meta with the primary key. Does not add the plan meta to the database.
    *
    * @param id the primary key for the new plan meta
    * @return the new plan meta
    */
    public static com.ext.portlet.model.PlanMeta create(long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the plan meta with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan meta
    * @return the plan meta that was removed
    * @throws com.ext.portlet.NoSuchPlanMetaException if a plan meta with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanMeta remove(long id)
        throws com.ext.portlet.NoSuchPlanMetaException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.PlanMeta updateImpl(
        com.ext.portlet.model.PlanMeta planMeta)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planMeta);
    }

    /**
    * Returns the plan meta with the primary key or throws a {@link com.ext.portlet.NoSuchPlanMetaException} if it could not be found.
    *
    * @param id the primary key of the plan meta
    * @return the plan meta
    * @throws com.ext.portlet.NoSuchPlanMetaException if a plan meta with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanMeta findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchPlanMetaException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the plan meta with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the plan meta
    * @return the plan meta, or <code>null</code> if a plan meta with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanMeta fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the plan metas.
    *
    * @return the plan metas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanMeta> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the plan metas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanMetaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan metas
    * @param end the upper bound of the range of plan metas (not inclusive)
    * @return the range of plan metas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanMeta> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the plan metas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanMetaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan metas
    * @param end the upper bound of the range of plan metas (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan metas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanMeta> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the plan metas from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of plan metas.
    *
    * @return the number of plan metas
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PlanMetaPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PlanMetaPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    PlanMetaPersistence.class.getName());

            ReferenceRegistry.registerReference(PlanMetaUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(PlanMetaPersistence persistence) {
    }
}
