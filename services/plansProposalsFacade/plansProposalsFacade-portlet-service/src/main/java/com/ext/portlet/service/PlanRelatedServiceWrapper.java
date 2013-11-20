package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanRelatedService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanRelatedService
 * @generated
 */
public class PlanRelatedServiceWrapper implements PlanRelatedService,
    ServiceWrapper<PlanRelatedService> {
    private PlanRelatedService _planRelatedService;

    public PlanRelatedServiceWrapper(PlanRelatedService planRelatedService) {
        _planRelatedService = planRelatedService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planRelatedService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planRelatedService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planRelatedService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanRelatedService getWrappedPlanRelatedService() {
        return _planRelatedService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanRelatedService(
        PlanRelatedService planRelatedService) {
        _planRelatedService = planRelatedService;
    }

    @Override
    public PlanRelatedService getWrappedService() {
        return _planRelatedService;
    }

    @Override
    public void setWrappedService(PlanRelatedService planRelatedService) {
        _planRelatedService = planRelatedService;
    }
}
