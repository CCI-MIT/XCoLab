package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ImpactTemplateFocusAreaListService}.
 *
 * @author Brian Wing Shun Chan
 * @see ImpactTemplateFocusAreaListService
 * @generated
 */
public class ImpactTemplateFocusAreaListServiceWrapper
    implements ImpactTemplateFocusAreaListService,
        ServiceWrapper<ImpactTemplateFocusAreaListService> {
    private ImpactTemplateFocusAreaListService _impactTemplateFocusAreaListService;

    public ImpactTemplateFocusAreaListServiceWrapper(
        ImpactTemplateFocusAreaListService impactTemplateFocusAreaListService) {
        _impactTemplateFocusAreaListService = impactTemplateFocusAreaListService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _impactTemplateFocusAreaListService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _impactTemplateFocusAreaListService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _impactTemplateFocusAreaListService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ImpactTemplateFocusAreaListService getWrappedImpactTemplateFocusAreaListService() {
        return _impactTemplateFocusAreaListService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedImpactTemplateFocusAreaListService(
        ImpactTemplateFocusAreaListService impactTemplateFocusAreaListService) {
        _impactTemplateFocusAreaListService = impactTemplateFocusAreaListService;
    }

    @Override
    public ImpactTemplateFocusAreaListService getWrappedService() {
        return _impactTemplateFocusAreaListService;
    }

    @Override
    public void setWrappedService(
        ImpactTemplateFocusAreaListService impactTemplateFocusAreaListService) {
        _impactTemplateFocusAreaListService = impactTemplateFocusAreaListService;
    }
}
