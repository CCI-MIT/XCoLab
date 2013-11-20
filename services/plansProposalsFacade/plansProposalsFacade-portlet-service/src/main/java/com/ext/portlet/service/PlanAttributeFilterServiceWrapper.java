package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanAttributeFilterService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanAttributeFilterService
 * @generated
 */
public class PlanAttributeFilterServiceWrapper
    implements PlanAttributeFilterService,
        ServiceWrapper<PlanAttributeFilterService> {
    private PlanAttributeFilterService _planAttributeFilterService;

    public PlanAttributeFilterServiceWrapper(
        PlanAttributeFilterService planAttributeFilterService) {
        _planAttributeFilterService = planAttributeFilterService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planAttributeFilterService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planAttributeFilterService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planAttributeFilterService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanAttributeFilterService getWrappedPlanAttributeFilterService() {
        return _planAttributeFilterService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanAttributeFilterService(
        PlanAttributeFilterService planAttributeFilterService) {
        _planAttributeFilterService = planAttributeFilterService;
    }

    @Override
    public PlanAttributeFilterService getWrappedService() {
        return _planAttributeFilterService;
    }

    @Override
    public void setWrappedService(
        PlanAttributeFilterService planAttributeFilterService) {
        _planAttributeFilterService = planAttributeFilterService;
    }
}
