package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan type local service. This utility wraps {@link com.ext.portlet.service.impl.PlanTypeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypeLocalService
 * @see com.ext.portlet.service.base.PlanTypeLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanTypeLocalServiceImpl
 * @generated
 */
public class PlanTypeLocalServiceUtil {
    private static PlanTypeLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlanTypeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the plan type to the database. Also notifies the appropriate model listeners.
    *
    * @param planType the plan type
    * @return the plan type that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanType addPlanType(
        com.ext.portlet.model.PlanType planType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addPlanType(planType);
    }

    /**
    * Creates a new plan type with the primary key. Does not add the plan type to the database.
    *
    * @param planTypeId the primary key for the new plan type
    * @return the new plan type
    */
    public static com.ext.portlet.model.PlanType createPlanType(long planTypeId) {
        return getService().createPlanType(planTypeId);
    }

    /**
    * Deletes the plan type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planTypeId the primary key of the plan type
    * @throws PortalException if a plan type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlanType(long planTypeId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlanType(planTypeId);
    }

    /**
    * Deletes the plan type from the database. Also notifies the appropriate model listeners.
    *
    * @param planType the plan type
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlanType(com.ext.portlet.model.PlanType planType)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlanType(planType);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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

    public static com.ext.portlet.model.PlanType fetchPlanType(long planTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchPlanType(planTypeId);
    }

    /**
    * Returns the plan type with the primary key.
    *
    * @param planTypeId the primary key of the plan type
    * @return the plan type
    * @throws PortalException if a plan type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanType getPlanType(long planTypeId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanType(planTypeId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan types
    * @param end the upper bound of the range of plan types (not inclusive)
    * @return the range of plan types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanType> getPlanTypes(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanTypes(start, end);
    }

    /**
    * Returns the number of plan types.
    *
    * @return the number of plan types
    * @throws SystemException if a system exception occurred
    */
    public static int getPlanTypesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanTypesCount();
    }

    /**
    * Updates the plan type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planType the plan type
    * @return the plan type that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanType updatePlanType(
        com.ext.portlet.model.PlanType planType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanType(planType);
    }

    /**
    * Updates the plan type in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planType the plan type
    * @param merge whether to merge the plan type with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan type that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanType updatePlanType(
        com.ext.portlet.model.PlanType planType, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanType(planType, merge);
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

    public static com.ext.portlet.model.PlanType getDefaultPlanType()
        throws com.ext.portlet.NoSuchPlanTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getDefaultPlanType();
    }

    public static java.util.List<com.ext.portlet.model.PlanTypeColumn> getColumnsByPlanTypeId(
        long planTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getColumnsByPlanTypeId(planTypeId);
    }

    public static java.util.List<com.ext.portlet.model.PlanTypeAttribute> getAttributesByPlanTypeId(
        long planTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getAttributesByPlanTypeId(planTypeId);
    }

    public static boolean isRegionalType(long planTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().isRegionalType(planTypeId);
    }

    public static java.util.List<edu.mit.cci.roma.client.Simulation> getAvailableModels(
        com.ext.portlet.model.PlanType planType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getAvailableModels(planType);
    }

    public static edu.mit.cci.roma.client.Simulation getDefaultModel(
        com.ext.portlet.model.PlanType planType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getDefaultModel(planType);
    }

    public static java.util.List<com.ext.portlet.model.PlanTypeColumn> getColumns(
        com.ext.portlet.model.PlanType planType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getColumns(planType);
    }

    public static java.util.List<com.ext.portlet.model.PlanTypeAttribute> getAttributes(
        com.ext.portlet.model.PlanType planType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getAttributes(planType);
    }

    public static boolean isRegional(com.ext.portlet.model.PlanType planType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().isRegional(planType);
    }

    public static void clearService() {
        _service = null;
    }

    public static PlanTypeLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanTypeLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanTypeLocalService.class.getName(), portletClassLoader);

            _service = new PlanTypeLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanTypeLocalServiceUtil.class,
                "_service");
            MethodCache.remove(PlanTypeLocalService.class);
        }

        return _service;
    }

    public void setService(PlanTypeLocalService service) {
        MethodCache.remove(PlanTypeLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanTypeLocalServiceUtil.class,
            "_service");
        MethodCache.remove(PlanTypeLocalService.class);
    }
}
