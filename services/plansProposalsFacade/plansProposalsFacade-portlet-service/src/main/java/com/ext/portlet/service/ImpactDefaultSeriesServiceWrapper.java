package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ImpactDefaultSeriesService}.
 *
 * @author Brian Wing Shun Chan
 * @see ImpactDefaultSeriesService
 * @generated
 */
public class ImpactDefaultSeriesServiceWrapper
    implements ImpactDefaultSeriesService,
        ServiceWrapper<ImpactDefaultSeriesService> {
    private ImpactDefaultSeriesService _impactDefaultSeriesService;

    public ImpactDefaultSeriesServiceWrapper(
        ImpactDefaultSeriesService impactDefaultSeriesService) {
        _impactDefaultSeriesService = impactDefaultSeriesService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _impactDefaultSeriesService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _impactDefaultSeriesService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _impactDefaultSeriesService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ImpactDefaultSeriesService getWrappedImpactDefaultSeriesService() {
        return _impactDefaultSeriesService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedImpactDefaultSeriesService(
        ImpactDefaultSeriesService impactDefaultSeriesService) {
        _impactDefaultSeriesService = impactDefaultSeriesService;
    }

    @Override
    public ImpactDefaultSeriesService getWrappedService() {
        return _impactDefaultSeriesService;
    }

    @Override
    public void setWrappedService(
        ImpactDefaultSeriesService impactDefaultSeriesService) {
        _impactDefaultSeriesService = impactDefaultSeriesService;
    }
}
