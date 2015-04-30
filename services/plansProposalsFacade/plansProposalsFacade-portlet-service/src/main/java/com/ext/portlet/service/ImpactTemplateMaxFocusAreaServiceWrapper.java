package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ImpactTemplateMaxFocusAreaService}.
 *
 * @author Brian Wing Shun Chan
 * @see ImpactTemplateMaxFocusAreaService
 * @generated
 */
public class ImpactTemplateMaxFocusAreaServiceWrapper
    implements ImpactTemplateMaxFocusAreaService,
        ServiceWrapper<ImpactTemplateMaxFocusAreaService> {
    private ImpactTemplateMaxFocusAreaService _impactTemplateMaxFocusAreaService;

    public ImpactTemplateMaxFocusAreaServiceWrapper(
        ImpactTemplateMaxFocusAreaService impactTemplateMaxFocusAreaService) {
        _impactTemplateMaxFocusAreaService = impactTemplateMaxFocusAreaService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _impactTemplateMaxFocusAreaService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _impactTemplateMaxFocusAreaService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _impactTemplateMaxFocusAreaService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ImpactTemplateMaxFocusAreaService getWrappedImpactTemplateMaxFocusAreaService() {
        return _impactTemplateMaxFocusAreaService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedImpactTemplateMaxFocusAreaService(
        ImpactTemplateMaxFocusAreaService impactTemplateMaxFocusAreaService) {
        _impactTemplateMaxFocusAreaService = impactTemplateMaxFocusAreaService;
    }

    @Override
    public ImpactTemplateMaxFocusAreaService getWrappedService() {
        return _impactTemplateMaxFocusAreaService;
    }

    @Override
    public void setWrappedService(
        ImpactTemplateMaxFocusAreaService impactTemplateMaxFocusAreaService) {
        _impactTemplateMaxFocusAreaService = impactTemplateMaxFocusAreaService;
    }
}
