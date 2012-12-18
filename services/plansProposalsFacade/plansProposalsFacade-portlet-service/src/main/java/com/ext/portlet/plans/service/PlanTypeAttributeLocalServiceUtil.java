package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan type attribute local service. This utility wraps {@link com.ext.portlet.plans.service.impl.PlanTypeAttributeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanTypeAttributeLocalService
 * @see com.ext.portlet.plans.service.base.PlanTypeAttributeLocalServiceBaseImpl
 * @see com.ext.portlet.plans.service.impl.PlanTypeAttributeLocalServiceImpl
 * @generated
 */
public class PlanTypeAttributeLocalServiceUtil {
    private static PlanTypeAttributeLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.plans.service.impl.PlanTypeAttributeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the plan type attribute to the database. Also notifies the appropriate model listeners.
    *
    * @param planTypeAttribute the plan type attribute
    * @return the plan type attribute that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanTypeAttribute addPlanTypeAttribute(
        com.ext.portlet.plans.model.PlanTypeAttribute planTypeAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addPlanTypeAttribute(planTypeAttribute);
    }

    /**
    * Creates a new plan type attribute with the primary key. Does not add the plan type attribute to the database.
    *
    * @param planTypeAttributeId the primary key for the new plan type attribute
    * @return the new plan type attribute
    */
    public static com.ext.portlet.plans.model.PlanTypeAttribute createPlanTypeAttribute(
        long planTypeAttributeId) {
        return getService().createPlanTypeAttribute(planTypeAttributeId);
    }

    /**
    * Deletes the plan type attribute with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planTypeAttributeId the primary key of the plan type attribute
    * @throws PortalException if a plan type attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlanTypeAttribute(long planTypeAttributeId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlanTypeAttribute(planTypeAttributeId);
    }

    /**
    * Deletes the plan type attribute from the database. Also notifies the appropriate model listeners.
    *
    * @param planTypeAttribute the plan type attribute
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlanTypeAttribute(
        com.ext.portlet.plans.model.PlanTypeAttribute planTypeAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlanTypeAttribute(planTypeAttribute);
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

    public static com.ext.portlet.plans.model.PlanTypeAttribute fetchPlanTypeAttribute(
        long planTypeAttributeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchPlanTypeAttribute(planTypeAttributeId);
    }

    /**
    * Returns the plan type attribute with the primary key.
    *
    * @param planTypeAttributeId the primary key of the plan type attribute
    * @return the plan type attribute
    * @throws PortalException if a plan type attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanTypeAttribute getPlanTypeAttribute(
        long planTypeAttributeId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanTypeAttribute(planTypeAttributeId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan type attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan type attributes
    * @param end the upper bound of the range of plan type attributes (not inclusive)
    * @return the range of plan type attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanTypeAttribute> getPlanTypeAttributes(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanTypeAttributes(start, end);
    }

    /**
    * Returns the number of plan type attributes.
    *
    * @return the number of plan type attributes
    * @throws SystemException if a system exception occurred
    */
    public static int getPlanTypeAttributesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanTypeAttributesCount();
    }

    /**
    * Updates the plan type attribute in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planTypeAttribute the plan type attribute
    * @return the plan type attribute that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanTypeAttribute updatePlanTypeAttribute(
        com.ext.portlet.plans.model.PlanTypeAttribute planTypeAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanTypeAttribute(planTypeAttribute);
    }

    /**
    * Updates the plan type attribute in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planTypeAttribute the plan type attribute
    * @param merge whether to merge the plan type attribute with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan type attribute that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanTypeAttribute updatePlanTypeAttribute(
        com.ext.portlet.plans.model.PlanTypeAttribute planTypeAttribute,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanTypeAttribute(planTypeAttribute, merge);
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

    public static PlanTypeAttributeLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanTypeAttributeLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanTypeAttributeLocalService.class.getName(),
                    portletClassLoader);

            _service = new PlanTypeAttributeLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanTypeAttributeLocalServiceUtil.class,
                "_service");
            MethodCache.remove(PlanTypeAttributeLocalService.class);
        }

        return _service;
    }

    public void setService(PlanTypeAttributeLocalService service) {
        MethodCache.remove(PlanTypeAttributeLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanTypeAttributeLocalServiceUtil.class,
            "_service");
        MethodCache.remove(PlanTypeAttributeLocalService.class);
    }
}
