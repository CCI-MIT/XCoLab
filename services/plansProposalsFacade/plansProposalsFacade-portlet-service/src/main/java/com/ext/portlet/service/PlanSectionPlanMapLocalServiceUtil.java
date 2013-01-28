package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan section plan map local service. This utility wraps {@link com.ext.portlet.service.impl.PlanSectionPlanMapLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionPlanMapLocalService
 * @see com.ext.portlet.service.base.PlanSectionPlanMapLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanSectionPlanMapLocalServiceImpl
 * @generated
 */
public class PlanSectionPlanMapLocalServiceUtil {
    private static PlanSectionPlanMapLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlanSectionPlanMapLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the plan section plan map to the database. Also notifies the appropriate model listeners.
    *
    * @param planSectionPlanMap the plan section plan map
    * @return the plan section plan map that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanSectionPlanMap addPlanSectionPlanMap(
        com.ext.portlet.model.PlanSectionPlanMap planSectionPlanMap)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addPlanSectionPlanMap(planSectionPlanMap);
    }

    /**
    * Creates a new plan section plan map with the primary key. Does not add the plan section plan map to the database.
    *
    * @param planSectionPlanMapPK the primary key for the new plan section plan map
    * @return the new plan section plan map
    */
    public static com.ext.portlet.model.PlanSectionPlanMap createPlanSectionPlanMap(
        com.ext.portlet.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK) {
        return getService().createPlanSectionPlanMap(planSectionPlanMapPK);
    }

    /**
    * Deletes the plan section plan map with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planSectionPlanMapPK the primary key of the plan section plan map
    * @throws PortalException if a plan section plan map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlanSectionPlanMap(
        com.ext.portlet.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlanSectionPlanMap(planSectionPlanMapPK);
    }

    /**
    * Deletes the plan section plan map from the database. Also notifies the appropriate model listeners.
    *
    * @param planSectionPlanMap the plan section plan map
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlanSectionPlanMap(
        com.ext.portlet.model.PlanSectionPlanMap planSectionPlanMap)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlanSectionPlanMap(planSectionPlanMap);
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

    public static com.ext.portlet.model.PlanSectionPlanMap fetchPlanSectionPlanMap(
        com.ext.portlet.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchPlanSectionPlanMap(planSectionPlanMapPK);
    }

    /**
    * Returns the plan section plan map with the primary key.
    *
    * @param planSectionPlanMapPK the primary key of the plan section plan map
    * @return the plan section plan map
    * @throws PortalException if a plan section plan map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanSectionPlanMap getPlanSectionPlanMap(
        com.ext.portlet.service.persistence.PlanSectionPlanMapPK planSectionPlanMapPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanSectionPlanMap(planSectionPlanMapPK);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan section plan maps.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan section plan maps
    * @param end the upper bound of the range of plan section plan maps (not inclusive)
    * @return the range of plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanSectionPlanMap> getPlanSectionPlanMaps(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanSectionPlanMaps(start, end);
    }

    /**
    * Returns the number of plan section plan maps.
    *
    * @return the number of plan section plan maps
    * @throws SystemException if a system exception occurred
    */
    public static int getPlanSectionPlanMapsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanSectionPlanMapsCount();
    }

    /**
    * Updates the plan section plan map in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planSectionPlanMap the plan section plan map
    * @return the plan section plan map that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanSectionPlanMap updatePlanSectionPlanMap(
        com.ext.portlet.model.PlanSectionPlanMap planSectionPlanMap)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanSectionPlanMap(planSectionPlanMap);
    }

    /**
    * Updates the plan section plan map in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planSectionPlanMap the plan section plan map
    * @param merge whether to merge the plan section plan map with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan section plan map that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanSectionPlanMap updatePlanSectionPlanMap(
        com.ext.portlet.model.PlanSectionPlanMap planSectionPlanMap,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanSectionPlanMap(planSectionPlanMap, merge);
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

    public static java.util.List<java.lang.Long> findPlanIdsForSection(
        java.lang.Long sectionId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findPlanIdsForSection(sectionId);
    }

    public static void store(com.ext.portlet.model.PlanSectionPlanMap pspm)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().store(pspm);
    }

    public static void clearService() {
        _service = null;
    }

    public static PlanSectionPlanMapLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanSectionPlanMapLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanSectionPlanMapLocalService.class.getName(),
                    portletClassLoader);

            _service = new PlanSectionPlanMapLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanSectionPlanMapLocalServiceUtil.class,
                "_service");
            MethodCache.remove(PlanSectionPlanMapLocalService.class);
        }

        return _service;
    }

    public void setService(PlanSectionPlanMapLocalService service) {
        MethodCache.remove(PlanSectionPlanMapLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanSectionPlanMapLocalServiceUtil.class,
            "_service");
        MethodCache.remove(PlanSectionPlanMapLocalService.class);
    }
}
