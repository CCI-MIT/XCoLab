package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for PointsDistributionConfiguration. This utility wraps
 * {@link com.ext.portlet.service.impl.PointsDistributionConfigurationLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see PointsDistributionConfigurationLocalService
 * @see com.ext.portlet.service.base.PointsDistributionConfigurationLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.PointsDistributionConfigurationLocalServiceImpl
 * @generated
 */
public class PointsDistributionConfigurationLocalServiceUtil {
    private static PointsDistributionConfigurationLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.PointsDistributionConfigurationLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the points distribution configuration to the database. Also notifies the appropriate model listeners.
    *
    * @param pointsDistributionConfiguration the points distribution configuration
    * @return the points distribution configuration that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration addPointsDistributionConfiguration(
        com.ext.portlet.model.PointsDistributionConfiguration pointsDistributionConfiguration)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .addPointsDistributionConfiguration(pointsDistributionConfiguration);
    }

    /**
    * Creates a new points distribution configuration with the primary key. Does not add the points distribution configuration to the database.
    *
    * @param id the primary key for the new points distribution configuration
    * @return the new points distribution configuration
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration createPointsDistributionConfiguration(
        long id) {
        return getService().createPointsDistributionConfiguration(id);
    }

    /**
    * Deletes the points distribution configuration with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the points distribution configuration
    * @return the points distribution configuration that was removed
    * @throws PortalException if a points distribution configuration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration deletePointsDistributionConfiguration(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deletePointsDistributionConfiguration(id);
    }

    /**
    * Deletes the points distribution configuration from the database. Also notifies the appropriate model listeners.
    *
    * @param pointsDistributionConfiguration the points distribution configuration
    * @return the points distribution configuration that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration deletePointsDistributionConfiguration(
        com.ext.portlet.model.PointsDistributionConfiguration pointsDistributionConfiguration)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .deletePointsDistributionConfiguration(pointsDistributionConfiguration);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.ext.portlet.model.PointsDistributionConfiguration fetchPointsDistributionConfiguration(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchPointsDistributionConfiguration(id);
    }

    /**
    * Returns the points distribution configuration with the primary key.
    *
    * @param id the primary key of the points distribution configuration
    * @return the points distribution configuration
    * @throws PortalException if a points distribution configuration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration getPointsDistributionConfiguration(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPointsDistributionConfiguration(id);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the points distribution configurations.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointsDistributionConfigurationModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of points distribution configurations
    * @param end the upper bound of the range of points distribution configurations (not inclusive)
    * @return the range of points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> getPointsDistributionConfigurations(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPointsDistributionConfigurations(start, end);
    }

    /**
    * Returns the number of points distribution configurations.
    *
    * @return the number of points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    public static int getPointsDistributionConfigurationsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getPointsDistributionConfigurationsCount();
    }

    /**
    * Updates the points distribution configuration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param pointsDistributionConfiguration the points distribution configuration
    * @return the points distribution configuration that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointsDistributionConfiguration updatePointsDistributionConfiguration(
        com.ext.portlet.model.PointsDistributionConfiguration pointsDistributionConfiguration)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .updatePointsDistributionConfiguration(pointsDistributionConfiguration);
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

    public static PointsDistributionConfigurationLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    PointsDistributionConfigurationLocalService.class.getName());

            if (invokableLocalService instanceof PointsDistributionConfigurationLocalService) {
                _service = (PointsDistributionConfigurationLocalService) invokableLocalService;
            } else {
                _service = new PointsDistributionConfigurationLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(PointsDistributionConfigurationLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(PointsDistributionConfigurationLocalService service) {
    }
}
