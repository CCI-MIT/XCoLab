package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan position local service. This utility wraps {@link com.ext.portlet.plans.service.impl.PlanPositionLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanPositionLocalService
 * @see com.ext.portlet.plans.service.base.PlanPositionLocalServiceBaseImpl
 * @see com.ext.portlet.plans.service.impl.PlanPositionLocalServiceImpl
 * @generated
 */
public class PlanPositionLocalServiceUtil {
    private static PlanPositionLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.plans.service.impl.PlanPositionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the plan position to the database. Also notifies the appropriate model listeners.
    *
    * @param planPosition the plan position
    * @return the plan position that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPosition addPlanPosition(
        com.ext.portlet.plans.model.PlanPosition planPosition)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addPlanPosition(planPosition);
    }

    /**
    * Creates a new plan position with the primary key. Does not add the plan position to the database.
    *
    * @param planPositionPK the primary key for the new plan position
    * @return the new plan position
    */
    public static com.ext.portlet.plans.model.PlanPosition createPlanPosition(
        com.ext.portlet.plans.service.persistence.PlanPositionPK planPositionPK) {
        return getService().createPlanPosition(planPositionPK);
    }

    /**
    * Deletes the plan position with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planPositionPK the primary key of the plan position
    * @throws PortalException if a plan position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlanPosition(
        com.ext.portlet.plans.service.persistence.PlanPositionPK planPositionPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlanPosition(planPositionPK);
    }

    /**
    * Deletes the plan position from the database. Also notifies the appropriate model listeners.
    *
    * @param planPosition the plan position
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlanPosition(
        com.ext.portlet.plans.model.PlanPosition planPosition)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlanPosition(planPosition);
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

    public static com.ext.portlet.plans.model.PlanPosition fetchPlanPosition(
        com.ext.portlet.plans.service.persistence.PlanPositionPK planPositionPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchPlanPosition(planPositionPK);
    }

    /**
    * Returns the plan position with the primary key.
    *
    * @param planPositionPK the primary key of the plan position
    * @return the plan position
    * @throws PortalException if a plan position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPosition getPlanPosition(
        com.ext.portlet.plans.service.persistence.PlanPositionPK planPositionPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanPosition(planPositionPK);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan positions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan positions
    * @param end the upper bound of the range of plan positions (not inclusive)
    * @return the range of plan positions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanPosition> getPlanPositions(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanPositions(start, end);
    }

    /**
    * Returns the number of plan positions.
    *
    * @return the number of plan positions
    * @throws SystemException if a system exception occurred
    */
    public static int getPlanPositionsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanPositionsCount();
    }

    /**
    * Updates the plan position in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planPosition the plan position
    * @return the plan position that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPosition updatePlanPosition(
        com.ext.portlet.plans.model.PlanPosition planPosition)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanPosition(planPosition);
    }

    /**
    * Updates the plan position in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planPosition the plan position
    * @param merge whether to merge the plan position with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan position that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanPosition updatePlanPosition(
        com.ext.portlet.plans.model.PlanPosition planPosition, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanPosition(planPosition, merge);
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

    public static PlanPositionLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanPositionLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanPositionLocalService.class.getName(), portletClassLoader);

            _service = new PlanPositionLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanPositionLocalServiceUtil.class,
                "_service");
            MethodCache.remove(PlanPositionLocalService.class);
        }

        return _service;
    }

    public void setService(PlanPositionLocalService service) {
        MethodCache.remove(PlanPositionLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanPositionLocalServiceUtil.class,
            "_service");
        MethodCache.remove(PlanPositionLocalService.class);
    }
}
