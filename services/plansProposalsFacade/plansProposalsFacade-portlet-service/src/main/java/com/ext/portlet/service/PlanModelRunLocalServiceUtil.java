package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for PlanModelRun. This utility wraps
 * {@link com.ext.portlet.service.impl.PlanModelRunLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see PlanModelRunLocalService
 * @see com.ext.portlet.service.base.PlanModelRunLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanModelRunLocalServiceImpl
 * @generated
 */
public class PlanModelRunLocalServiceUtil {
    private static PlanModelRunLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlanModelRunLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the plan model run to the database. Also notifies the appropriate model listeners.
    *
    * @param planModelRun the plan model run
    * @return the plan model run that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanModelRun addPlanModelRun(
        com.ext.portlet.model.PlanModelRun planModelRun)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addPlanModelRun(planModelRun);
    }

    /**
    * Creates a new plan model run with the primary key. Does not add the plan model run to the database.
    *
    * @param id the primary key for the new plan model run
    * @return the new plan model run
    */
    public static com.ext.portlet.model.PlanModelRun createPlanModelRun(long id) {
        return getService().createPlanModelRun(id);
    }

    /**
    * Deletes the plan model run with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan model run
    * @return the plan model run that was removed
    * @throws PortalException if a plan model run with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanModelRun deletePlanModelRun(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deletePlanModelRun(id);
    }

    /**
    * Deletes the plan model run from the database. Also notifies the appropriate model listeners.
    *
    * @param planModelRun the plan model run
    * @return the plan model run that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanModelRun deletePlanModelRun(
        com.ext.portlet.model.PlanModelRun planModelRun)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deletePlanModelRun(planModelRun);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanModelRunModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanModelRunModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.ext.portlet.model.PlanModelRun fetchPlanModelRun(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchPlanModelRun(id);
    }

    /**
    * Returns the plan model run with the primary key.
    *
    * @param id the primary key of the plan model run
    * @return the plan model run
    * @throws PortalException if a plan model run with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanModelRun getPlanModelRun(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanModelRun(id);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan model runs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanModelRunModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan model runs
    * @param end the upper bound of the range of plan model runs (not inclusive)
    * @return the range of plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanModelRun> getPlanModelRuns(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanModelRuns(start, end);
    }

    /**
    * Returns the number of plan model runs.
    *
    * @return the number of plan model runs
    * @throws SystemException if a system exception occurred
    */
    public static int getPlanModelRunsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanModelRunsCount();
    }

    /**
    * Updates the plan model run in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planModelRun the plan model run
    * @return the plan model run that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanModelRun updatePlanModelRun(
        com.ext.portlet.model.PlanModelRun planModelRun)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanModelRun(planModelRun);
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

    public static com.ext.portlet.model.PlanModelRun createPlanModelRun(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().createPlanModelRun(plan);
    }

    public static com.ext.portlet.model.PlanModelRun getCurrentForPlan(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCurrentForPlan(plan);
    }

    public static java.util.List<com.ext.portlet.model.PlanModelRun> getAllForPlan(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getAllForPlan(plan);
    }

    public static com.ext.portlet.model.PlanModelRun getForPlan(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getForPlan(plan);
    }

    public static com.ext.portlet.model.PlanModelRun createNewVersionForPlan(
        com.ext.portlet.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().createNewVersionForPlan(plan);
    }

    public static com.ext.portlet.model.PlanModelRun createNewVersionForPlan(
        com.ext.portlet.model.PlanItem plan, boolean store)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().createNewVersionForPlan(plan, store);
    }

    public static com.ext.portlet.model.PlanModelRun createNewVersionForPlanFrom(
        com.ext.portlet.model.PlanItem plan,
        com.ext.portlet.model.PlanModelRun from, boolean store)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().createNewVersionForPlanFrom(plan, from, store);
    }

    public static void store(com.ext.portlet.model.PlanModelRun pmr)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().store(pmr);
    }

    public static com.liferay.portal.model.User getUpdateAuthor(
        com.ext.portlet.model.PlanModelRun pmr)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getUpdateAuthor(pmr);
    }

    public static void clearService() {
        _service = null;
    }

    public static PlanModelRunLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanModelRunLocalService.class.getName());

            if (invokableLocalService instanceof PlanModelRunLocalService) {
                _service = (PlanModelRunLocalService) invokableLocalService;
            } else {
                _service = new PlanModelRunLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(PlanModelRunLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(PlanModelRunLocalService service) {
    }
}
