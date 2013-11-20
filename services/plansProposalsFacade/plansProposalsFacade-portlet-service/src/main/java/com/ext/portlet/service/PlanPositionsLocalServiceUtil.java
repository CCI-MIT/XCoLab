package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for PlanPositions. This utility wraps
 * {@link com.ext.portlet.service.impl.PlanPositionsLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see PlanPositionsLocalService
 * @see com.ext.portlet.service.base.PlanPositionsLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanPositionsLocalServiceImpl
 * @generated
 */
public class PlanPositionsLocalServiceUtil {
    private static PlanPositionsLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlanPositionsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the plan positions to the database. Also notifies the appropriate model listeners.
    *
    * @param planPositions the plan positions
    * @return the plan positions that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanPositions addPlanPositions(
        com.ext.portlet.model.PlanPositions planPositions)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addPlanPositions(planPositions);
    }

    /**
    * Creates a new plan positions with the primary key. Does not add the plan positions to the database.
    *
    * @param id the primary key for the new plan positions
    * @return the new plan positions
    */
    public static com.ext.portlet.model.PlanPositions createPlanPositions(
        long id) {
        return getService().createPlanPositions(id);
    }

    /**
    * Deletes the plan positions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan positions
    * @return the plan positions that was removed
    * @throws PortalException if a plan positions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanPositions deletePlanPositions(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deletePlanPositions(id);
    }

    /**
    * Deletes the plan positions from the database. Also notifies the appropriate model listeners.
    *
    * @param planPositions the plan positions
    * @return the plan positions that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanPositions deletePlanPositions(
        com.ext.portlet.model.PlanPositions planPositions)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deletePlanPositions(planPositions);
    }

    public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return getService().dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery, projection);
    }

    public static com.ext.portlet.model.PlanPositions fetchPlanPositions(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchPlanPositions(id);
    }

    /**
    * Returns the plan positions with the primary key.
    *
    * @param id the primary key of the plan positions
    * @return the plan positions
    * @throws PortalException if a plan positions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanPositions getPlanPositions(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanPositions(id);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan positionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanPositionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan positionses
    * @param end the upper bound of the range of plan positionses (not inclusive)
    * @return the range of plan positionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanPositions> getPlanPositionses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanPositionses(start, end);
    }

    /**
    * Returns the number of plan positionses.
    *
    * @return the number of plan positionses
    * @throws SystemException if a system exception occurred
    */
    public static int getPlanPositionsesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanPositionsesCount();
    }

    /**
    * Updates the plan positions in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planPositions the plan positions
    * @return the plan positions that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanPositions updatePlanPositions(
        com.ext.portlet.model.PlanPositions planPositions)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanPositions(planPositions);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static com.ext.portlet.model.PlanPositions getCurrentForPlan(
        com.ext.portlet.model.PlanItem plan)
        throws com.ext.portlet.NoSuchPlanPositionsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getCurrentForPlan(plan);
    }

    public static com.ext.portlet.model.PlanPositions createPlanPositions(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().createPlanPositions(plan);
    }

    public static java.util.List<com.ext.portlet.model.PlanPositions> getAllForPlan(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getAllForPlan(plan);
    }

    public static com.ext.portlet.model.PlanPositions createNewVersionForPlan(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().createNewVersionForPlan(plan);
    }

    public static com.ext.portlet.model.PlanPositions createNewVersionForPlan(
        com.ext.portlet.model.PlanItem plan, boolean store)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().createNewVersionForPlan(plan, store);
    }

    public static java.util.List<java.lang.Long> getPositionsIds(
        com.ext.portlet.model.PlanPositions pp)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPositionsIds(pp);
    }

    public static void store(com.ext.portlet.model.PlanPositions pp)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().store(pp);
    }

    public static void setPositionsIds(com.ext.portlet.model.PlanPositions pp,
        java.util.List<java.lang.Long> positionsIds)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().setPositionsIds(pp, positionsIds);
    }

    public static com.liferay.portal.model.User getUpdateAuthor(
        com.ext.portlet.model.PlanPositions pp)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getUpdateAuthor(pp);
    }

    public static void clearService() {
        _service = null;
    }

    public static PlanPositionsLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanPositionsLocalService.class.getName());

            if (invokableLocalService instanceof PlanPositionsLocalService) {
                _service = (PlanPositionsLocalService) invokableLocalService;
            } else {
                _service = new PlanPositionsLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(PlanPositionsLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(PlanPositionsLocalService service) {
    }
}
