package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanDescriptionService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanDescriptionService
 * @generated
 */
public class PlanDescriptionServiceWrapper implements PlanDescriptionService,
    ServiceWrapper<PlanDescriptionService> {
    private PlanDescriptionService _planDescriptionService;

    public PlanDescriptionServiceWrapper(
        PlanDescriptionService planDescriptionService) {
        _planDescriptionService = planDescriptionService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planDescriptionService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planDescriptionService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planDescriptionService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanDescriptionService getWrappedPlanDescriptionService() {
        return _planDescriptionService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanDescriptionService(
        PlanDescriptionService planDescriptionService) {
        _planDescriptionService = planDescriptionService;
    }

    @Override
    public PlanDescriptionService getWrappedService() {
        return _planDescriptionService;
    }

    @Override
    public void setWrappedService(PlanDescriptionService planDescriptionService) {
        _planDescriptionService = planDescriptionService;
    }
}
