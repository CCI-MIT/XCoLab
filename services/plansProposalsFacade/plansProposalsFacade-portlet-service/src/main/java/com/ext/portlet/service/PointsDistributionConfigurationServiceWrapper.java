package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PointsDistributionConfigurationService}.
 *
 * @author Brian Wing Shun Chan
 * @see PointsDistributionConfigurationService
 * @generated
 */
public class PointsDistributionConfigurationServiceWrapper
    implements PointsDistributionConfigurationService,
        ServiceWrapper<PointsDistributionConfigurationService> {
    private PointsDistributionConfigurationService _pointsDistributionConfigurationService;

    public PointsDistributionConfigurationServiceWrapper(
        PointsDistributionConfigurationService pointsDistributionConfigurationService) {
        _pointsDistributionConfigurationService = pointsDistributionConfigurationService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _pointsDistributionConfigurationService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _pointsDistributionConfigurationService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _pointsDistributionConfigurationService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PointsDistributionConfigurationService getWrappedPointsDistributionConfigurationService() {
        return _pointsDistributionConfigurationService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPointsDistributionConfigurationService(
        PointsDistributionConfigurationService pointsDistributionConfigurationService) {
        _pointsDistributionConfigurationService = pointsDistributionConfigurationService;
    }

    @Override
    public PointsDistributionConfigurationService getWrappedService() {
        return _pointsDistributionConfigurationService;
    }

    @Override
    public void setWrappedService(
        PointsDistributionConfigurationService pointsDistributionConfigurationService) {
        _pointsDistributionConfigurationService = pointsDistributionConfigurationService;
    }
}
