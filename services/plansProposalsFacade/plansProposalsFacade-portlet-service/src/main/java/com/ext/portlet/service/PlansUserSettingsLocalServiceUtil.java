package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for PlansUserSettings. This utility wraps
 * {@link com.ext.portlet.service.impl.PlansUserSettingsLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see PlansUserSettingsLocalService
 * @see com.ext.portlet.service.base.PlansUserSettingsLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.PlansUserSettingsLocalServiceImpl
 * @generated
 */
public class PlansUserSettingsLocalServiceUtil {
    private static PlansUserSettingsLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PlansUserSettingsLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the plans user settings to the database. Also notifies the appropriate model listeners.
    *
    * @param plansUserSettings the plans user settings
    * @return the plans user settings that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlansUserSettings addPlansUserSettings(
        com.ext.portlet.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addPlansUserSettings(plansUserSettings);
    }

    /**
    * Creates a new plans user settings with the primary key. Does not add the plans user settings to the database.
    *
    * @param planUserSettingsId the primary key for the new plans user settings
    * @return the new plans user settings
    */
    public static com.ext.portlet.model.PlansUserSettings createPlansUserSettings(
        long planUserSettingsId) {
        return getService().createPlansUserSettings(planUserSettingsId);
    }

    /**
    * Deletes the plans user settings with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planUserSettingsId the primary key of the plans user settings
    * @return the plans user settings that was removed
    * @throws PortalException if a plans user settings with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlansUserSettings deletePlansUserSettings(
        long planUserSettingsId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deletePlansUserSettings(planUserSettingsId);
    }

    /**
    * Deletes the plans user settings from the database. Also notifies the appropriate model listeners.
    *
    * @param plansUserSettings the plans user settings
    * @return the plans user settings that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PlansUserSettings deletePlansUserSettings(
        com.ext.portlet.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deletePlansUserSettings(plansUserSettings);
    }

    public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return getService().dynamicQuery();
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlansUserSettingsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlansUserSettingsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery, projection);
    }

    public static com.ext.portlet.model.PlansUserSettings fetchPlansUserSettings(
        long planUserSettingsId)
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
    public static com.ext.portlet.model.PlansUserSettings getPlansUserSettings(
        long planUserSettingsId)
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PlansUserSettingsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plans user settingses
    * @param end the upper bound of the range of plans user settingses (not inclusive)
    * @return the range of plans user settingses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PlansUserSettings> getPlansUserSettingses(
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
    public static com.ext.portlet.model.PlansUserSettings updatePlansUserSettings(
        com.ext.portlet.model.PlansUserSettings plansUserSettings)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updatePlansUserSettings(plansUserSettings);
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

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static void clearService() {
        _service = null;
    }

    public static PlansUserSettingsLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PlansUserSettingsLocalService.class.getName());

            if (invokableLocalService instanceof PlansUserSettingsLocalService) {
                _service = (PlansUserSettingsLocalService) invokableLocalService;
            } else {
                _service = new PlansUserSettingsLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(PlansUserSettingsLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(PlansUserSettingsLocalService service) {
    }
}
