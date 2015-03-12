package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ImpactTemplateSeriesService}.
 *
 * @author Brian Wing Shun Chan
 * @see ImpactTemplateSeriesService
 * @generated
 */
public class ImpactTemplateSeriesServiceWrapper
    implements ImpactTemplateSeriesService,
        ServiceWrapper<ImpactTemplateSeriesService> {
    private ImpactTemplateSeriesService _impactTemplateSeriesService;

    public ImpactTemplateSeriesServiceWrapper(
        ImpactTemplateSeriesService impactTemplateSeriesService) {
        _impactTemplateSeriesService = impactTemplateSeriesService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _impactTemplateSeriesService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _impactTemplateSeriesService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _impactTemplateSeriesService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ImpactTemplateSeriesService getWrappedImpactTemplateSeriesService() {
        return _impactTemplateSeriesService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedImpactTemplateSeriesService(
        ImpactTemplateSeriesService impactTemplateSeriesService) {
        _impactTemplateSeriesService = impactTemplateSeriesService;
    }

    @Override
    public ImpactTemplateSeriesService getWrappedService() {
        return _impactTemplateSeriesService;
    }

    @Override
    public void setWrappedService(
        ImpactTemplateSeriesService impactTemplateSeriesService) {
        _impactTemplateSeriesService = impactTemplateSeriesService;
    }
}
