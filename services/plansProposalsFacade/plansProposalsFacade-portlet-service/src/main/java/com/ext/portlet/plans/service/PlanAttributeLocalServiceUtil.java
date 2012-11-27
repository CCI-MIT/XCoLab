package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan attribute local service. This utility wraps {@link com.ext.portlet.plans.service.impl.PlanAttributeLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanAttributeLocalService
 * @see com.ext.portlet.plans.service.base.PlanAttributeLocalServiceBaseImpl
 * @see com.ext.portlet.plans.service.impl.PlanAttributeLocalServiceImpl
 * @generated
 */
public class PlanAttributeLocalServiceUtil {
    private static PlanAttributeLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.plans.service.impl.PlanAttributeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the plan attribute to the database. Also notifies the appropriate model listeners.
    *
    * @param planAttribute the plan attribute
    * @return the plan attribute that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanAttribute addPlanAttribute(
        com.ext.portlet.plans.model.PlanAttribute planAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addPlanAttribute(planAttribute);
    }

    /**
    * Creates a new plan attribute with the primary key. Does not add the plan attribute to the database.
    *
    * @param attributeId the primary key for the new plan attribute
    * @return the new plan attribute
    */
    public static com.ext.portlet.plans.model.PlanAttribute createPlanAttribute(
        java.lang.Long attributeId) {
        return getService().createPlanAttribute(attributeId);
    }

    /**
    * Deletes the plan attribute with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param attributeId the primary key of the plan attribute
    * @throws PortalException if a plan attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlanAttribute(java.lang.Long attributeId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlanAttribute(attributeId);
    }

    /**
    * Deletes the plan attribute from the database. Also notifies the appropriate model listeners.
    *
    * @param planAttribute the plan attribute
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlanAttribute(
        com.ext.portlet.plans.model.PlanAttribute planAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlanAttribute(planAttribute);
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

    public static com.ext.portlet.plans.model.PlanAttribute fetchPlanAttribute(
        java.lang.Long attributeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchPlanAttribute(attributeId);
    }

    /**
    * Returns the plan attribute with the primary key.
    *
    * @param attributeId the primary key of the plan attribute
    * @return the plan attribute
    * @throws PortalException if a plan attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanAttribute getPlanAttribute(
        java.lang.Long attributeId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanAttribute(attributeId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan attributes
    * @param end the upper bound of the range of plan attributes (not inclusive)
    * @return the range of plan attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributes(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanAttributes(start, end);
    }

    /**
    * Returns the number of plan attributes.
    *
    * @return the number of plan attributes
    * @throws SystemException if a system exception occurred
    */
    public static int getPlanAttributesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanAttributesCount();
    }

    /**
    * Updates the plan attribute in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planAttribute the plan attribute
    * @return the plan attribute that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanAttribute updatePlanAttribute(
        com.ext.portlet.plans.model.PlanAttribute planAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanAttribute(planAttribute);
    }

    /**
    * Updates the plan attribute in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planAttribute the plan attribute
    * @param merge whether to merge the plan attribute with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan attribute that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanAttribute updatePlanAttribute(
        com.ext.portlet.plans.model.PlanAttribute planAttribute, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanAttribute(planAttribute, merge);
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

    public static void addPlanAttribute(long planId,
        java.lang.String attributeName, java.lang.String attributeValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().addPlanAttribute(planId, attributeName, attributeValue);
    }

    public static com.ext.portlet.plans.model.PlanAttribute findPlanAttribute(
        long planId, java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findPlanAttribute(planId, attributeName);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributes(
        java.lang.Long planId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanAttributes(planId);
    }

    public static java.util.List<com.ext.portlet.plans.model.PlanAttribute> getPlanAttributesByNameValue(
        java.lang.String attributeName, java.lang.String attributeValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getPlanAttributesByNameValue(attributeName, attributeValue);
    }

    public static void clearService() {
        _service = null;
    }

    public static PlanAttributeLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanAttributeLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanAttributeLocalService.class.getName(),
                    portletClassLoader);

            _service = new PlanAttributeLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanAttributeLocalServiceUtil.class,
                "_service");
            MethodCache.remove(PlanAttributeLocalService.class);
        }

        return _service;
    }

    public void setService(PlanAttributeLocalService service) {
        MethodCache.remove(PlanAttributeLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanAttributeLocalServiceUtil.class,
            "_service");
        MethodCache.remove(PlanAttributeLocalService.class);
    }
}
