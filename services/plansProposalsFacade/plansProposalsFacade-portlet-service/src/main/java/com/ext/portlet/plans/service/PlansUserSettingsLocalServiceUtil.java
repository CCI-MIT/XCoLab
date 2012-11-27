package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the plans user settings local service. This utility wraps {@link com.ext.portlet.plans.service.impl.PlansUserSettingsLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PlansUserSettingsLocalService
 * @see com.ext.portlet.plans.service.base.PlansUserSettingsLocalServiceBaseImpl
 * @see com.ext.portlet.plans.service.impl.PlansUserSettingsLocalServiceImpl
 * @generated
 */
public class PlansUserSettingsLocalServiceUtil {
    private static PlansUserSettingsLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.plans.service.impl.PlansUserSettingsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the plans user settings to the database. Also notifies the appropriate model listeners.
    *
    * @param plansUserSettings the plans user settings
    * @return the plans user settings that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlansUserSettings addPlansUserSettings(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addPlansUserSettings(plansUserSettings);
    }

    /**
    * Creates a new plans user settings with the primary key. Does not add the plans user settings to the database.
    *
    * @param planUserSettingsId the primary key for the new plans user settings
    * @return the new plans user settings
    */
    public static com.ext.portlet.plans.model.PlansUserSettings createPlansUserSettings(
        java.lang.Long planUserSettingsId) {
        return getService().createPlansUserSettings(planUserSettingsId);
    }

    /**
    * Deletes the plans user settings with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planUserSettingsId the primary key of the plans user settings
    * @throws PortalException if a plans user settings with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlansUserSettings(
        java.lang.Long planUserSettingsId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlansUserSettings(planUserSettingsId);
    }

    /**
    * Deletes the plans user settings from the database. Also notifies the appropriate model listeners.
    *
    * @param plansUserSettings the plans user settings
    * @throws SystemException if a system exception occurred
    */
    public static void deletePlansUserSettings(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deletePlansUserSettings(plansUserSettings);
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

    public static com.ext.portlet.plans.model.PlansUserSettings fetchPlansUserSettings(
        java.lang.Long planUserSettingsId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchPlansUserSettings(planUserSettingsId);
    }

    /**
    * Returns the plans user settings with the primary key.
    *
    * @param planUserSettingsId the primary key of the plans user settings
    * @return the plans user settings
    * @throws PortalException if a plans user settings with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlansUserSettings getPlansUserSettings(
        java.lang.Long planUserSettingsId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlansUserSettings(planUserSettingsId);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the plans user settingses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of plans user settingses
    * @param end the upper bound of the range of plans user settingses (not inclusive)
    * @return the range of plans user settingses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.plans.model.PlansUserSettings> getPlansUserSettingses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlansUserSettingses(start, end);
    }

    /**
    * Returns the number of plans user settingses.
    *
    * @return the number of plans user settingses
    * @throws SystemException if a system exception occurred
    */
    public static int getPlansUserSettingsesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlansUserSettingsesCount();
    }

    /**
    * Updates the plans user settings in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param plansUserSettings the plans user settings
    * @return the plans user settings that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlansUserSettings updatePlansUserSettings(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlansUserSettings(plansUserSettings);
    }

    /**
    * Updates the plans user settings in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param plansUserSettings the plans user settings
    * @param merge whether to merge the plans user settings with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the plans user settings that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.plans.model.PlansUserSettings updatePlansUserSettings(
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlansUserSettings(plansUserSettings, merge);
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

    public static com.ext.portlet.plans.model.PlansUserSettings getByUserIdPlanTypeId(
        java.lang.Long userId, java.lang.Long planTypeId)
        throws com.ext.portlet.plans.NoSuchUserSettingsException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getByUserIdPlanTypeId(userId, planTypeId);
    }

    public static com.ext.portlet.plans.model.PlansUserSettings getPlanUserSettings(
        java.util.Map sessionMap, java.util.Map requestMap,
        com.ext.portlet.plans.model.PlanType planType)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPlanUserSettings(sessionMap, requestMap, planType);
    }

    /**
    * Method responsible for storing columns configuration. Configuration is
    * stored in session also, if user is authorized then configuration is
    * persisted.
    *
    * @param requestMap           render request
    * @param plansUserSettings settings that should be stored
    * @throws PortalException passed up in case of framework error
    * @throws SystemException passed up in case of framework error
    */
    public static void saveUserSettings(java.util.Map sessionMap,
        java.util.Map requestMap,
        com.ext.portlet.plans.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().saveUserSettings(sessionMap, requestMap, plansUserSettings);
    }

    public static com.ext.portlet.plans.model.PlansUserSettings getPlanUserSettings(
        java.util.Map sessionMap, java.util.Map requestMap, long planTypeId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getPlanUserSettings(sessionMap, requestMap, planTypeId);
    }

    public static void clearService() {
        _service = null;
    }

    public static PlansUserSettingsLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlansUserSettingsLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    PlansUserSettingsLocalService.class.getName(),
                    portletClassLoader);

            _service = new PlansUserSettingsLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(PlansUserSettingsLocalServiceUtil.class,
                "_service");
            MethodCache.remove(PlansUserSettingsLocalService.class);
        }

        return _service;
    }

    public void setService(PlansUserSettingsLocalService service) {
        MethodCache.remove(PlansUserSettingsLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(PlansUserSettingsLocalServiceUtil.class,
            "_service");
        MethodCache.remove(PlansUserSettingsLocalService.class);
    }
}
