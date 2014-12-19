package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for PlanItemGroup. This utility wraps
 * {@link com.ext.portlet.service.impl.PlanItemGroupLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see PlanItemGroupLocalService
 * @see com.ext.portlet.service.base.PlanItemGroupLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanItemGroupLocalServiceImpl
 * @generated
 */
public class PlanItemGroupLocalServiceUtil {
    private static PlanItemGroupLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlanItemGroupLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the plan item group to the database. Also notifies the appropriate model listeners.
    *
    * @param planItemGroup the plan item group
    * @return the plan item group that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanItemGroup addPlanItemGroup(
        com.ext.portlet.model.PlanItemGroup planItemGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addPlanItemGroup(planItemGroup);
    }

    /**
    * Creates a new plan item group with the primary key. Does not add the plan item group to the database.
    *
    * @param planId the primary key for the new plan item group
    * @return the new plan item group
    */
    public static com.ext.portlet.model.PlanItemGroup createPlanItemGroup(
        long planId) {
        return getService().createPlanItemGroup(planId);
    }

    /**
    * Deletes the plan item group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planId the primary key of the plan item group
    * @return the plan item group that was removed
    * @throws PortalException if a plan item group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanItemGroup deletePlanItemGroup(
        long planId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deletePlanItemGroup(planId);
    }

    /**
    * Deletes the plan item group from the database. Also notifies the appropriate model listeners.
    *
    * @param planItemGroup the plan item group
    * @return the plan item group that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanItemGroup deletePlanItemGroup(
        com.ext.portlet.model.PlanItemGroup planItemGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deletePlanItemGroup(planItemGroup);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.ext.portlet.model.PlanItemGroup fetchPlanItemGroup(
        long planId) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchPlanItemGroup(planId);
    }

    /**
    * Returns the plan item group with the primary key.
    *
    * @param planId the primary key of the plan item group
    * @return the plan item group
    * @throws PortalException if a plan item group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanItemGroup getPlanItemGroup(
        long planId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanItemGroup(planId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan item groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlanItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan item groups
    * @param end the upper bound of the range of plan item groups (not inclusive)
    * @return the range of plan item groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanItemGroup> getPlanItemGroups(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanItemGroups(start, end);
    }

    /**
    * Returns the number of plan item groups.
    *
    * @return the number of plan item groups
    * @throws SystemException if a system exception occurred
    */
    public static int getPlanItemGroupsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanItemGroupsCount();
    }

    /**
    * Updates the plan item group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planItemGroup the plan item group
    * @return the plan item group that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanItemGroup updatePlanItemGroup(
        com.ext.portlet.model.PlanItemGroup planItemGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanItemGroup(planItemGroup);
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

    /**
    * Adds given plans to a group, creates one if none exists.
    *
    * @param fromPlanId
    source plan id
    * @param toPlanId
    destination plan id
    * @return created PlanItemPhaseMap
    * @throws SystemException
    * @throws NoSuchModelException
    */
    public static void addToGroup(java.lang.Long fromPlanId,
        java.lang.Long toPlanId)
        throws com.liferay.portal.NoSuchModelException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().addToGroup(fromPlanId, toPlanId);
    }

    /**
    * Returns list of planIds that belong to the same group as given planId.
    *
    * @param planId
    plan id for which other group members should be returned
    * @return list of plan ids that belong to the same group (can be a single
    element array if plan doesn't belong to a group)
    * @throws NoSuchModelException
    * @throws SystemException
    */
    public static java.util.List<java.lang.Long> getPlansInGroup(
        java.lang.Long planId)
        throws com.liferay.portal.NoSuchModelException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlansInGroup(planId);
    }

    public static void clearService() {
        _service = null;
    }

    public static PlanItemGroupLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanItemGroupLocalService.class.getName());

            if (invokableLocalService instanceof PlanItemGroupLocalService) {
                _service = (PlanItemGroupLocalService) invokableLocalService;
            } else {
                _service = new PlanItemGroupLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(PlanItemGroupLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(PlanItemGroupLocalService service) {
    }
}
