package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ImpactDefaultSeriesDataService}.
 *
 * @author Brian Wing Shun Chan
 * @see ImpactDefaultSeriesDataService
 * @generated
 */
public class ImpactDefaultSeriesDataServiceWrapper
    implements ImpactDefaultSeriesDataService,
        ServiceWrapper<ImpactDefaultSeriesDataService> {
    private ImpactDefaultSeriesDataService _impactDefaultSeriesDataService;

    public ImpactDefaultSeriesDataServiceWrapper(
        ImpactDefaultSeriesDataService impactDefaultSeriesDataService) {
        _impactDefaultSeriesDataService = impactDefaultSeriesDataService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _impactDefaultSeriesDataService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _impactDefaultSeriesDataService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _impactDefaultSeriesDataService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ImpactDefaultSeriesDataService getWrappedImpactDefaultSeriesDataService() {
        return _impactDefaultSeriesDataService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedImpactDefaultSeriesDataService(
        ImpactDefaultSeriesDataService impactDefaultSeriesDataService) {
        _impactDefaultSeriesDataService = impactDefaultSeriesDataService;
    }

    @Override
    public ImpactDefaultSeriesDataService getWrappedService() {
        return _impactDefaultSeriesDataService;
    }

    @Override
    public void setWrappedService(
        ImpactDefaultSeriesDataService impactDefaultSeriesDataService) {
        _impactDefaultSeriesDataService = impactDefaultSeriesDataService;
    }
}
