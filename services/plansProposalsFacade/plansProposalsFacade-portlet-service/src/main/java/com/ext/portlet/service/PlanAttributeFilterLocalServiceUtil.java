package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for PlanAttributeFilter. This utility wraps
 * {@link com.ext.portlet.service.impl.PlanAttributeFilterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see PlanAttributeFilterLocalService
 * @see com.ext.portlet.service.base.PlanAttributeFilterLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanAttributeFilterLocalServiceImpl
 * @generated
 */
public class PlanAttributeFilterLocalServiceUtil {
    private static PlanAttributeFilterLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlanAttributeFilterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the plan attribute filter to the database. Also notifies the appropriate model listeners.
    *
    * @param planAttributeFilter the plan attribute filter
    * @return the plan attribute filter that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanAttributeFilter addPlanAttributeFilter(
        com.ext.portlet.model.PlanAttributeFilter planAttributeFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addPlanAttributeFilter(planAttributeFilter);
    }

    /**
    * Creates a new plan attribute filter with the primary key. Does not add the plan attribute filter to the database.
    *
    * @param planAttributeFilterId the primary key for the new plan attribute filter
    * @return the new plan attribute filter
    */
    public static com.ext.portlet.model.PlanAttributeFilter createPlanAttributeFilter(
        long planAttributeFilterId) {
        return getService().createPlanAttributeFilter(planAttributeFilterId);
    }

    /**
    * Deletes the plan attribute filter with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planAttributeFilterId the primary key of the plan attribute filter
    * @return the plan attribute filter that was removed
    * @throws PortalException if a plan attribute filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanAttributeFilter deletePlanAttributeFilter(
        long planAttributeFilterId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deletePlanAttributeFilter(planAttributeFilterId);
    }

    /**
    * Deletes the plan attribute filter from the database. Also notifies the appropriate model listeners.
    *
    * @param planAttributeFilter the plan attribute filter
    * @return the plan attribute filter that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanAttributeFilter deletePlanAttributeFilter(
        com.ext.portlet.model.PlanAttributeFilter planAttributeFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deletePlanAttributeFilter(planAttributeFilter);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanAttributeFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanAttributeFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.ext.portlet.model.PlanAttributeFilter fetchPlanAttributeFilter(
        long planAttributeFilterId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchPlanAttributeFilter(planAttributeFilterId);
    }

    /**
    * Returns the plan attribute filter with the primary key.
    *
    * @param planAttributeFilterId the primary key of the plan attribute filter
    * @return the plan attribute filter
    * @throws PortalException if a plan attribute filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanAttributeFilter getPlanAttributeFilter(
        long planAttributeFilterId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanAttributeFilter(planAttributeFilterId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan attribute filters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanAttributeFilterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan attribute filters
    * @param end the upper bound of the range of plan attribute filters (not inclusive)
    * @return the range of plan attribute filters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanAttributeFilter> getPlanAttributeFilters(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanAttributeFilters(start, end);
    }

    /**
    * Returns the number of plan attribute filters.
    *
    * @return the number of plan attribute filters
    * @throws SystemException if a system exception occurred
    */
    public static int getPlanAttributeFiltersCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanAttributeFiltersCount();
    }

    /**
    * Updates the plan attribute filter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planAttributeFilter the plan attribute filter
    * @return the plan attribute filter that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanAttributeFilter updatePlanAttributeFilter(
        com.ext.portlet.model.PlanAttributeFilter planAttributeFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanAttributeFilter(planAttributeFilter);
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

    public static com.ext.portlet.model.PlanAttributeFilter getByPlansUserSettingsIdAttributeName(
        java.lang.Long planUserSettingsId, java.lang.String attributeName)
        throws com.ext.portlet.NoSuchPlanAttributeFilterException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getByPlansUserSettingsIdAttributeName(planUserSettingsId,
            attributeName);
    }

    public static void clearService() {
        _service = null;
    }

    public static PlanAttributeFilterLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanAttributeFilterLocalService.class.getName());

            if (invokableLocalService instanceof PlanAttributeFilterLocalService) {
                _service = (PlanAttributeFilterLocalService) invokableLocalService;
            } else {
                _service = new PlanAttributeFilterLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(PlanAttributeFilterLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(PlanAttributeFilterLocalService service) {
    }
}
