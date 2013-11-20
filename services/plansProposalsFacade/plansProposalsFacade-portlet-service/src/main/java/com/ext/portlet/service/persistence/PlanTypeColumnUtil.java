package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PlanTypeColumn;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the plan type column service. This utility wraps {@link PlanTypeColumnPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypeColumnPersistence
 * @see PlanTypeColumnPersistenceImpl
 * @generated
 */
public class PlanTypeColumnUtil {
    private static PlanTypeColumnPersistence _persistence;

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
    public static void clearCache(PlanTypeColumn planTypeColumn) {
        getPersistence().clearCache(planTypeColumn);
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
    public static List<PlanTypeColumn> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PlanTypeColumn> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PlanTypeColumn> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static PlanTypeColumn update(PlanTypeColumn planTypeColumn)
        throws SystemException {
        return getPersistence().update(planTypeColumn);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static PlanTypeColumn update(PlanTypeColumn planTypeColumn,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(planTypeColumn, serviceContext);
    }

    /**
    * Caches the plan type column in the entity cache if it is enabled.
    *
    * @param planTypeColumn the plan type column
    */
    public static void cacheResult(
        com.ext.portlet.model.PlanTypeColumn planTypeColumn) {
        getPersistence().cacheResult(planTypeColumn);
    }

    /**
    * Caches the plan type columns in the entity cache if it is enabled.
    *
    * @param planTypeColumns the plan type columns
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.PlanTypeColumn> planTypeColumns) {
        getPersistence().cacheResult(planTypeColumns);
    }

    /**
    * Creates a new plan type column with the primary key. Does not add the plan type column to the database.
    *
    * @param planTypeColumnId the primary key for the new plan type column
    * @return the new plan type column
    */
    public static com.ext.portlet.model.PlanTypeColumn create(
        long planTypeColumnId) {
        return getPersistence().create(planTypeColumnId);
    }

    /**
    * Removes the plan type column with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planTypeColumnId the primary key of the plan type column
    * @return the plan type column that was removed
    * @throws com.ext.portlet.NoSuchPlanTypeColumnException if a plan type column with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanTypeColumn remove(
        long planTypeColumnId)
        throws com.ext.portlet.NoSuchPlanTypeColumnException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(planTypeColumnId);
    }

    public static com.ext.portlet.model.PlanTypeColumn updateImpl(
        com.ext.portlet.model.PlanTypeColumn planTypeColumn)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(planTypeColumn);
    }

    /**
    * Returns the plan type column with the primary key or throws a {@link com.ext.portlet.NoSuchPlanTypeColumnException} if it could not be found.
    *
    * @param planTypeColumnId the primary key of the plan type column
    * @return the plan type column
    * @throws com.ext.portlet.NoSuchPlanTypeColumnException if a plan type column with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanTypeColumn findByPrimaryKey(
        long planTypeColumnId)
        throws com.ext.portlet.NoSuchPlanTypeColumnException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(planTypeColumnId);
    }

    /**
    * Returns the plan type column with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planTypeColumnId the primary key of the plan type column
    * @return the plan type column, or <code>null</code> if a plan type column with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanTypeColumn fetchByPrimaryKey(
        long planTypeColumnId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(planTypeColumnId);
    }

    /**
    * Returns all the plan type columns.
    *
    * @return the plan type columns
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanTypeColumn> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the plan type columns.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanTypeColumnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan type columns
    * @param end the upper bound of the range of plan type columns (not inclusive)
    * @return the range of plan type columns
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanTypeColumn> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the plan type columns.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanTypeColumnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan type columns
    * @param end the upper bound of the range of plan type columns (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan type columns
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanTypeColumn> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the plan type columns from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of plan type columns.
    *
    * @return the number of plan type columns
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PlanTypeColumnPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PlanTypeColumnPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    PlanTypeColumnPersistence.class.getName());

            ReferenceRegistry.registerReference(PlanTypeColumnUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(PlanTypeColumnPersistence persistence) {
    }
}
