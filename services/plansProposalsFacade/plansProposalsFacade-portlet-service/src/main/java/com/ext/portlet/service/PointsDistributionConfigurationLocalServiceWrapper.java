package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PointsDistributionConfigurationLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see PointsDistributionConfigurationLocalService
 * @generated
 */
public class PointsDistributionConfigurationLocalServiceWrapper
    implements PointsDistributionConfigurationLocalService,
        ServiceWrapper<PointsDistributionConfigurationLocalService> {
    private PointsDistributionConfigurationLocalService _pointsDistributionConfigurationLocalService;

    public PointsDistributionConfigurationLocalServiceWrapper(
        PointsDistributionConfigurationLocalService pointsDistributionConfigurationLocalService) {
        _pointsDistributionConfigurationLocalService = pointsDistributionConfigurationLocalService;
    }

    /**
    * Adds the points distribution configuration to the database. Also notifies the appropriate model listeners.
    *
    * @param pointsDistributionConfiguration the points distribution configuration
    * @return the points distribution configuration that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PointsDistributionConfiguration addPointsDistributionConfiguration(
        com.ext.portlet.model.PointsDistributionConfiguration pointsDistributionConfiguration)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _pointsDistributionConfigurationLocalService.addPointsDistributionConfiguration(pointsDistributionConfiguration);
    }

    /**
    * Creates a new points distribution configuration with the primary key. Does not add the points distribution configuration to the database.
    *
    * @param id the primary key for the new points distribution configuration
    * @return the new points distribution configuration
    */
    @Override
    public com.ext.portlet.model.PointsDistributionConfiguration createPointsDistributionConfiguration(
        long id) {
        return _pointsDistributionConfigurationLocalService.createPointsDistributionConfiguration(id);
    }

    /**
    * Deletes the points distribution configuration with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the points distribution configuration
    * @return the points distribution configuration that was removed
    * @throws PortalException if a points distribution configuration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PointsDistributionConfiguration deletePointsDistributionConfiguration(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _pointsDistributionConfigurationLocalService.deletePointsDistributionConfiguration(id);
    }

    /**
    * Deletes the points distribution configuration from the database. Also notifies the appropriate model listeners.
    *
    * @param pointsDistributionConfiguration the points distribution configuration
    * @return the points distribution configuration that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PointsDistributionConfiguration deletePointsDistributionConfiguration(
        com.ext.portlet.model.PointsDistributionConfiguration pointsDistributionConfiguration)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _pointsDistributionConfigurationLocalService.deletePointsDistributionConfiguration(pointsDistributionConfiguration);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _pointsDistributionConfigurationLocalService.dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _pointsDistributionConfigurationLocalService.dynamicQuery(dynamicQuery);
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
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _pointsDistributionConfigurationLocalService.dynamicQuery(dynamicQuery,
            start, end);
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
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _pointsDistributionConfigurationLocalService.dynamicQuery(dynamicQuery,
            start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _pointsDistributionConfigurationLocalService.dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _pointsDistributionConfigurationLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.PointsDistributionConfiguration fetchPointsDistributionConfiguration(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return _pointsDistributionConfigurationLocalService.fetchPointsDistributionConfiguration(id);
    }

    /**
    * Returns the points distribution configuration with the primary key.
    *
    * @param id the primary key of the points distribution configuration
    * @return the points distribution configuration
    * @throws PortalException if a points distribution configuration with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PointsDistributionConfiguration getPointsDistributionConfiguration(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _pointsDistributionConfigurationLocalService.getPointsDistributionConfiguration(id);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _pointsDistributionConfigurationLocalService.getPersistedModel(primaryKeyObj);
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
    @Override
    public java.util.List<com.ext.portlet.model.PointsDistributionConfiguration> getPointsDistributionConfigurations(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _pointsDistributionConfigurationLocalService.getPointsDistributionConfigurations(start,
            end);
    }

    /**
    * Returns the number of points distribution configurations.
    *
    * @return the number of points distribution configurations
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getPointsDistributionConfigurationsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _pointsDistributionConfigurationLocalService.getPointsDistributionConfigurationsCount();
    }

    /**
    * Updates the points distribution configuration in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param pointsDistributionConfiguration the points distribution configuration
    * @return the points distribution configuration that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.PointsDistributionConfiguration updatePointsDistributionConfiguration(
        com.ext.portlet.model.PointsDistributionConfiguration pointsDistributionConfiguration)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _pointsDistributionConfigurationLocalService.updatePointsDistributionConfiguration(pointsDistributionConfiguration);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _pointsDistributionConfigurationLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _pointsDistributionConfigurationLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _pointsDistributionConfigurationLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PointsDistributionConfigurationLocalService getWrappedPointsDistributionConfigurationLocalService() {
        return _pointsDistributionConfigurationLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPointsDistributionConfigurationLocalService(
        PointsDistributionConfigurationLocalService pointsDistributionConfigurationLocalService) {
        _pointsDistributionConfigurationLocalService = pointsDistributionConfigurationLocalService;
    }

    @Override
    public PointsDistributionConfigurationLocalService getWrappedService() {
        return _pointsDistributionConfigurationLocalService;
    }

    @Override
    public void setWrappedService(
        PointsDistributionConfigurationLocalService pointsDistributionConfigurationLocalService) {
        _pointsDistributionConfigurationLocalService = pointsDistributionConfigurationLocalService;
    }
}
