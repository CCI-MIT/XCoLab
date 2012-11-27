package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plans filter local service. This utility wraps {@link com.ext.portlet.plans.service.impl.PlansFilterLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlansFilterLocalService
 * @see com.ext.portlet.plans.service.base.PlansFilterLocalServiceBaseImpl
 * @see com.ext.portlet.plans.service.impl.PlansFilterLocalServiceImpl
 * @generated
 */
public class PlansFilterLocalServiceUtil {
    private static PlansFilterLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.plans.service.impl.PlansFilterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the plans filter to the database. Also notifies the appropriate model listeners.
    *
    * @param plansFilter the plans filter
    * @return the plans filter that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlansFilter addPlansFilter(
        com.ext.portlet.plans.model.PlansFilter plansFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addPlansFilter(plansFilter);
    }

    /**
    * Creates a new plans filter with the primary key. Does not add the plans filter to the database.
    *
    * @param plansFilterPK the primary key for the new plans filter
    * @return the new plans filter
    */
    public static com.ext.portlet.plans.model.PlansFilter createPlansFilter(
        com.ext.portlet.plans.service.persistence.PlansFilterPK plansFilterPK) {
        return getService().createPlansFilter(plansFilterPK);
    }

    /**
    * Deletes the plans filter with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param plansFilterPK the primary key of the plans filter
    * @throws PortalException if a plans filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlansFilter(
        com.ext.portlet.plans.service.persistence.PlansFilterPK plansFilterPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlansFilter(plansFilterPK);
    }

    /**
    * Deletes the plans filter from the database. Also notifies the appropriate model listeners.
    *
    * @param plansFilter the plans filter
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlansFilter(
        com.ext.portlet.plans.model.PlansFilter plansFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlansFilter(plansFilter);
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

    public static com.ext.portlet.plans.model.PlansFilter fetchPlansFilter(
        com.ext.portlet.plans.service.persistence.PlansFilterPK plansFilterPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchPlansFilter(plansFilterPK);
    }

    /**
    * Returns the plans filter with the primary key.
    *
    * @param plansFilterPK the primary key of the plans filter
    * @return the plans filter
    * @throws PortalException if a plans filter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlansFilter getPlansFilter(
        com.ext.portlet.plans.service.persistence.PlansFilterPK plansFilterPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlansFilter(plansFilterPK);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plans filters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plans filters
    * @param end the upper bound of the range of plans filters (not inclusive)
    * @return the range of plans filters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlansFilter> getPlansFilters(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlansFilters(start, end);
    }

    /**
    * Returns the number of plans filters.
    *
    * @return the number of plans filters
    * @throws SystemException if a system exception occurred
    */
    public static int getPlansFiltersCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlansFiltersCount();
    }

    /**
    * Updates the plans filter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param plansFilter the plans filter
    * @return the plans filter that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlansFilter updatePlansFilter(
        com.ext.portlet.plans.model.PlansFilter plansFilter)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlansFilter(plansFilter);
    }

    /**
    * Updates the plans filter in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param plansFilter the plans filter
    * @param merge whether to merge the plans filter with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plans filter that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlansFilter updatePlansFilter(
        com.ext.portlet.plans.model.PlansFilter plansFilter, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlansFilter(plansFilter, merge);
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

    public static void clearService() {
        _service = null;
    }

    public static PlansFilterLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlansFilterLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlansFilterLocalService.class.getName(), portletClassLoader);

            _service = new PlansFilterLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlansFilterLocalServiceUtil.class,
                "_service");
            MethodCache.remove(PlansFilterLocalService.class);
        }

        return _service;
    }

    public void setService(PlansFilterLocalService service) {
        MethodCache.remove(PlansFilterLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlansFilterLocalServiceUtil.class,
            "_service");
        MethodCache.remove(PlansFilterLocalService.class);
    }
}
