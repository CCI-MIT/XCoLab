package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plan column settings local service. This utility wraps {@link com.ext.portlet.plans.service.impl.PlanColumnSettingsLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlanColumnSettingsLocalService
 * @see com.ext.portlet.plans.service.base.PlanColumnSettingsLocalServiceBaseImpl
 * @see com.ext.portlet.plans.service.impl.PlanColumnSettingsLocalServiceImpl
 * @generated
 */
public class PlanColumnSettingsLocalServiceUtil {
    private static PlanColumnSettingsLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.plans.service.impl.PlanColumnSettingsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the plan column settings to the database. Also notifies the appropriate model listeners.
    *
    * @param planColumnSettings the plan column settings
    * @return the plan column settings that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanColumnSettings addPlanColumnSettings(
        com.ext.portlet.plans.model.PlanColumnSettings planColumnSettings)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addPlanColumnSettings(planColumnSettings);
    }

    /**
    * Creates a new plan column settings with the primary key. Does not add the plan column settings to the database.
    *
    * @param planColumnSettingsId the primary key for the new plan column settings
    * @return the new plan column settings
    */
    public static com.ext.portlet.plans.model.PlanColumnSettings createPlanColumnSettings(
        java.lang.Long planColumnSettingsId) {
        return getService().createPlanColumnSettings(planColumnSettingsId);
    }

    /**
    * Deletes the plan column settings with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planColumnSettingsId the primary key of the plan column settings
    * @throws PortalException if a plan column settings with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlanColumnSettings(
        java.lang.Long planColumnSettingsId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlanColumnSettings(planColumnSettingsId);
    }

    /**
    * Deletes the plan column settings from the database. Also notifies the appropriate model listeners.
    *
    * @param planColumnSettings the plan column settings
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlanColumnSettings(
        com.ext.portlet.plans.model.PlanColumnSettings planColumnSettings)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlanColumnSettings(planColumnSettings);
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

    public static com.ext.portlet.plans.model.PlanColumnSettings fetchPlanColumnSettings(
        java.lang.Long planColumnSettingsId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchPlanColumnSettings(planColumnSettingsId);
    }

    /**
    * Returns the plan column settings with the primary key.
    *
    * @param planColumnSettingsId the primary key of the plan column settings
    * @return the plan column settings
    * @throws PortalException if a plan column settings with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanColumnSettings getPlanColumnSettings(
        java.lang.Long planColumnSettingsId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanColumnSettings(planColumnSettingsId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plan column settingses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plan column settingses
    * @param end the upper bound of the range of plan column settingses (not inclusive)
    * @return the range of plan column settingses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlanColumnSettings> getPlanColumnSettingses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanColumnSettingses(start, end);
    }

    /**
    * Returns the number of plan column settingses.
    *
    * @return the number of plan column settingses
    * @throws SystemException if a system exception occurred
    */
    public static int getPlanColumnSettingsesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanColumnSettingsesCount();
    }

    /**
    * Updates the plan column settings in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planColumnSettings the plan column settings
    * @return the plan column settings that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanColumnSettings updatePlanColumnSettings(
        com.ext.portlet.plans.model.PlanColumnSettings planColumnSettings)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanColumnSettings(planColumnSettings);
    }

    /**
    * Updates the plan column settings in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param planColumnSettings the plan column settings
    * @param merge whether to merge the plan column settings with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plan column settings that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlanColumnSettings updatePlanColumnSettings(
        com.ext.portlet.plans.model.PlanColumnSettings planColumnSettings,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlanColumnSettings(planColumnSettings, merge);
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

    public static com.ext.portlet.plans.model.PlanColumnSettings findByPlanUserSettingsIdColumnName(
        java.lang.Long planUserSettingsId, java.lang.String columnName)
        throws com.ext.portlet.plans.NoSuchPlanColumnSettingsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .findByPlanUserSettingsIdColumnName(planUserSettingsId,
            columnName);
    }

    public static void clearService() {
        _service = null;
    }

    public static PlanColumnSettingsLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlanColumnSettingsLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlanColumnSettingsLocalService.class.getName(),
                    portletClassLoader);

            _service = new PlanColumnSettingsLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlanColumnSettingsLocalServiceUtil.class,
                "_service");
            MethodCache.remove(PlanColumnSettingsLocalService.class);
        }

        return _service;
    }

    public void setService(PlanColumnSettingsLocalService service) {
        MethodCache.remove(PlanColumnSettingsLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlanColumnSettingsLocalServiceUtil.class,
            "_service");
        MethodCache.remove(PlanColumnSettingsLocalService.class);
    }
}
