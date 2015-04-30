package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ImpactIterationService}.
 *
 * @author Brian Wing Shun Chan
 * @see ImpactIterationService
 * @generated
 */
public class ImpactIterationServiceWrapper implements ImpactIterationService,
    ServiceWrapper<ImpactIterationService> {
    private ImpactIterationService _impactIterationService;

    public ImpactIterationServiceWrapper(
        ImpactIterationService impactIterationService) {
        _impactIterationService = impactIterationService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _impactIterationService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _impactIterationService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _impactIterationService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ImpactIterationService getWrappedImpactIterationService() {
        return _impactIterationService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedImpactIterationService(
        ImpactIterationService impactIterationService) {
        _impactIterationService = impactIterationService;
    }

    @Override
    public ImpactIterationService getWrappedService() {
        return _impactIterationService;
    }

    @Override
    public void setWrappedService(ImpactIterationService impactIterationService) {
        _impactIterationService = impactIterationService;
    }
}
