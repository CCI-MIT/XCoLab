package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plans filter position local service. This utility wraps {@link com.ext.portlet.plans.service.impl.PlansFilterPositionLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlansFilterPositionLocalService
 * @see com.ext.portlet.plans.service.base.PlansFilterPositionLocalServiceBaseImpl
 * @see com.ext.portlet.plans.service.impl.PlansFilterPositionLocalServiceImpl
 * @generated
 */
public class PlansFilterPositionLocalServiceUtil {
    private static PlansFilterPositionLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.plans.service.impl.PlansFilterPositionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the plans filter position to the database. Also notifies the appropriate model listeners.
    *
    * @param plansFilterPosition the plans filter position
    * @return the plans filter position that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlansFilterPosition addPlansFilterPosition(
        com.ext.portlet.plans.model.PlansFilterPosition plansFilterPosition)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addPlansFilterPosition(plansFilterPosition);
    }

    /**
    * Creates a new plans filter position with the primary key. Does not add the plans filter position to the database.
    *
    * @param plansFilterPositionPK the primary key for the new plans filter position
    * @return the new plans filter position
    */
    public static com.ext.portlet.plans.model.PlansFilterPosition createPlansFilterPosition(
        com.ext.portlet.plans.service.persistence.PlansFilterPositionPK plansFilterPositionPK) {
        return getService().createPlansFilterPosition(plansFilterPositionPK);
    }

    /**
    * Deletes the plans filter position with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param plansFilterPositionPK the primary key of the plans filter position
    * @throws PortalException if a plans filter position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlansFilterPosition(
        com.ext.portlet.plans.service.persistence.PlansFilterPositionPK plansFilterPositionPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlansFilterPosition(plansFilterPositionPK);
    }

    /**
    * Deletes the plans filter position from the database. Also notifies the appropriate model listeners.
    *
    * @param plansFilterPosition the plans filter position
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlansFilterPosition(
        com.ext.portlet.plans.model.PlansFilterPosition plansFilterPosition)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlansFilterPosition(plansFilterPosition);
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

    public static com.ext.portlet.plans.model.PlansFilterPosition fetchPlansFilterPosition(
        com.ext.portlet.plans.service.persistence.PlansFilterPositionPK plansFilterPositionPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchPlansFilterPosition(plansFilterPositionPK);
    }

    /**
    * Returns the plans filter position with the primary key.
    *
    * @param plansFilterPositionPK the primary key of the plans filter position
    * @return the plans filter position
    * @throws PortalException if a plans filter position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlansFilterPosition getPlansFilterPosition(
        com.ext.portlet.plans.service.persistence.PlansFilterPositionPK plansFilterPositionPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlansFilterPosition(plansFilterPositionPK);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plans filter positions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plans filter positions
    * @param end the upper bound of the range of plans filter positions (not inclusive)
    * @return the range of plans filter positions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlansFilterPosition> getPlansFilterPositions(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlansFilterPositions(start, end);
    }

    /**
    * Returns the number of plans filter positions.
    *
    * @return the number of plans filter positions
    * @throws SystemException if a system exception occurred
    */
    public static int getPlansFilterPositionsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlansFilterPositionsCount();
    }

    /**
    * Updates the plans filter position in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param plansFilterPosition the plans filter position
    * @return the plans filter position that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlansFilterPosition updatePlansFilterPosition(
        com.ext.portlet.plans.model.PlansFilterPosition plansFilterPosition)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlansFilterPosition(plansFilterPosition);
    }

    /**
    * Updates the plans filter position in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param plansFilterPosition the plans filter position
    * @param merge whether to merge the plans filter position with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plans filter position that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlansFilterPosition updatePlansFilterPosition(
        com.ext.portlet.plans.model.PlansFilterPosition plansFilterPosition,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlansFilterPosition(plansFilterPosition, merge);
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

    public static PlansFilterPositionLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlansFilterPositionLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlansFilterPositionLocalService.class.getName(),
                    portletClassLoader);

            _service = new PlansFilterPositionLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlansFilterPositionLocalServiceUtil.class,
                "_service");
            MethodCache.remove(PlansFilterPositionLocalService.class);
        }

        return _service;
    }

    public void setService(PlansFilterPositionLocalService service) {
        MethodCache.remove(PlansFilterPositionLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlansFilterPositionLocalServiceUtil.class,
            "_service");
        MethodCache.remove(PlansFilterPositionLocalService.class);
    }
}
