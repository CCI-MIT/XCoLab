package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan related local service. This utility wraps {@link com.ext.portlet.service.impl.PlanRelatedLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanRelatedLocalService
 * @see com.ext.portlet.service.base.PlanRelatedLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlanRelatedLocalServiceImpl
 * @generated
 */
public class PlanRelatedLocalServiceUtil {
    private static PlanRelatedLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlanRelatedLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the plan related to the database. Also notifies the appropriate model listeners.
    *
    * @param planRelated the plan related
    * @return the plan related that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanRelated addPlanRelated(
        com.ext.portlet.model.PlanRelated planRelated)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addPlanRelated(planRelated);
    }

    /**
    * Creates a new plan related with the primary key. Does not add the plan related to the database.
    *
    * @param planRelatedPK the primary key for the new plan related
    * @return the new plan related
    */
    public static com.ext.portlet.model.PlanRelated createPlanRelated(
        com.ext.portlet.service.persistence.PlanRelatedPK planRelatedPK) {
        return getService().createPlanRelated(planRelatedPK);
    }

    /**
    * Deletes the plan related with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planRelatedPK the primary key of the plan related
    * @throws PortalException if a plan related with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlanRelated(
        com.ext.portlet.service.persistence.PlanRelatedPK planRelatedPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlanRelated(planRelatedPK);
    }

    /**
    * Deletes the plan related from the database. Also notifies the appropriate model listeners.
    *
    * @param planRelated the plan related
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlanRelated(
        com.ext.portlet.model.PlanRelated planRelated)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlanRelated(planRelated);
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

    public static com.ext.portlet.model.PlanRelated fetchPlanRelated(
        com.ext.portlet.service.persistence.PlanRelatedPK planRelatedPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchPlanRelated(planRelatedPK);
    }

    /**
    * Returns the plan related with the primary key.
    *
    * @param planRelatedPK the primary key of the plan related
    * @return the plan related
    * @throws PortalException if a plan related with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanRelated getPlanRelated(
        com.ext.portlet.service.persistence.PlanRelatedPK planRelatedPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanRelated(planRelatedPK);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan relateds.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan relateds
    * @param end the upper bound of the range of plan relateds (not inclusive)
    * @return the range of plan relateds
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlanRelated> getPlanRelateds(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanRelateds(start, end);
    }

    /**
    * Returns the number of plan relateds.
    *
    * @return the number of plan relateds
    * @throws SystemException if a system exception occurred
    */
    public static int getPlanRelatedsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanRelatedsCount();
    }

    /**
    * Updates the plan related in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planRelated the plan related
    * @return the plan related that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanRelated updatePlanRelated(
        com.ext.portlet.model.PlanRelated planRelated)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanRelated(planRelated);
    }

    /**
    * Updates the plan related in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planRelated the plan related
    * @param merge whether to merge the plan related with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan related that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlanRelated updatePlanRelated(
        com.ext.portlet.model.PlanRelated planRelated, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanRelated(planRelated, merge);
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

    public static PlanRelatedLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanRelatedLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanRelatedLocalService.class.getName(), portletClassLoader);

            _service = new PlanRelatedLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanRelatedLocalServiceUtil.class,
                "_service");
            MethodCache.remove(PlanRelatedLocalService.class);
        }

        return _service;
    }

    public void setService(PlanRelatedLocalService service) {
        MethodCache.remove(PlanRelatedLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanRelatedLocalServiceUtil.class,
            "_service");
        MethodCache.remove(PlanRelatedLocalService.class);
    }
}
