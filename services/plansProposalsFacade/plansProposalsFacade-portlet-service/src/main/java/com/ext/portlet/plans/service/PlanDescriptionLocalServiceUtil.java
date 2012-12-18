package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan description local service. This utility wraps {@link com.ext.portlet.plans.service.impl.PlanDescriptionLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanDescriptionLocalService
 * @see com.ext.portlet.plans.service.base.PlanDescriptionLocalServiceBaseImpl
 * @see com.ext.portlet.plans.service.impl.PlanDescriptionLocalServiceImpl
 * @generated
 */
public class PlanDescriptionLocalServiceUtil {
    private static PlanDescriptionLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.plans.service.impl.PlanDescriptionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the plan description to the database. Also notifies the appropriate model listeners.
    *
    * @param planDescription the plan description
    * @return the plan description that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanDescription addPlanDescription(
        com.ext.portlet.plans.model.PlanDescription planDescription)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addPlanDescription(planDescription);
    }

    /**
    * Creates a new plan description with the primary key. Does not add the plan description to the database.
    *
    * @param id the primary key for the new plan description
    * @return the new plan description
    */
    public static com.ext.portlet.plans.model.PlanDescription createPlanDescription(
        long id) {
        return getService().createPlanDescription(id);
    }

    /**
    * Deletes the plan description with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the plan description
    * @throws PortalException if a plan description with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlanDescription(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlanDescription(id);
    }

    /**
    * Deletes the plan description from the database. Also notifies the appropriate model listeners.
    *
    * @param planDescription the plan description
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlanDescription(
        com.ext.portlet.plans.model.PlanDescription planDescription)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlanDescription(planDescription);
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

    public static com.ext.portlet.plans.model.PlanDescription fetchPlanDescription(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchPlanDescription(id);
    }

    /**
    * Returns the plan description with the primary key.
    *
    * @param id the primary key of the plan description
    * @return the plan description
    * @throws PortalException if a plan description with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanDescription getPlanDescription(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanDescription(id);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan descriptions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan descriptions
    * @param end the upper bound of the range of plan descriptions (not inclusive)
    * @return the range of plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanDescription> getPlanDescriptions(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanDescriptions(start, end);
    }

    /**
    * Returns the number of plan descriptions.
    *
    * @return the number of plan descriptions
    * @throws SystemException if a system exception occurred
    */
    public static int getPlanDescriptionsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanDescriptionsCount();
    }

    /**
    * Updates the plan description in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planDescription the plan description
    * @return the plan description that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanDescription updatePlanDescription(
        com.ext.portlet.plans.model.PlanDescription planDescription)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanDescription(planDescription);
    }

    /**
    * Updates the plan description in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planDescription the plan description
    * @param merge whether to merge the plan description with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan description that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanDescription updatePlanDescription(
        com.ext.portlet.plans.model.PlanDescription planDescription,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanDescription(planDescription, merge);
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

    public static com.ext.portlet.plans.model.PlanDescription createPlanDescription(
        com.ext.portlet.plans.model.PlanItem plan, java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().createPlanDescription(plan, name);
    }

    public static com.ext.portlet.plans.model.PlanDescription createPlanDescription(
        com.ext.portlet.plans.model.PlanItem plan, java.lang.String name,
        boolean store)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().createPlanDescription(plan, name, store);
    }

    public static com.ext.portlet.plans.model.PlanDescription getCurrentForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCurrentForPlan(plan);
    }

    public static com.ext.portlet.plans.model.PlanDescription getForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getForPlan(plan);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanDescription> getAllForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getAllForPlan(plan);
    }

    public static com.ext.portlet.plans.model.PlanDescription createNewVersionForPlan(
        com.ext.portlet.plans.model.PlanItem plan)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().createNewVersionForPlan(plan);
    }

    public static com.ext.portlet.plans.model.PlanDescription createNewVersionForPlan(
        com.ext.portlet.plans.model.PlanItem plan, boolean store)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().createNewVersionForPlan(plan, store);
    }

    public static com.ext.portlet.plans.model.PlanDescription createNewVersionForPlanFrom(
        com.ext.portlet.plans.model.PlanItem plan,
        com.ext.portlet.plans.model.PlanDescription from, boolean store)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().createNewVersionForPlanFrom(plan, from, store);
    }

    public static void store(com.ext.portlet.plans.model.PlanDescription pd)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().store(pd);
    }

    public static com.liferay.portal.model.User getUpdateAuthor(
        com.ext.portlet.plans.model.PlanDescription pd)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getUpdateAuthor(pd);
    }

    public static void clearService() {
        _service = null;
    }

    public static PlanDescriptionLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanDescriptionLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanDescriptionLocalService.class.getName(),
                    portletClassLoader);

            _service = new PlanDescriptionLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanDescriptionLocalServiceUtil.class,
                "_service");
            MethodCache.remove(PlanDescriptionLocalService.class);
        }

        return _service;
    }

    public void setService(PlanDescriptionLocalService service) {
        MethodCache.remove(PlanDescriptionLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanDescriptionLocalServiceUtil.class,
            "_service");
        MethodCache.remove(PlanDescriptionLocalService.class);
    }
}
