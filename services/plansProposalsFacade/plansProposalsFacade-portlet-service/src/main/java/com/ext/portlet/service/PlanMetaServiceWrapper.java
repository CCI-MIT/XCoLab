package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanMetaService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanMetaService
 * @generated
 */
public class PlanMetaServiceWrapper implements PlanMetaService,
    ServiceWrapper<PlanMetaService> {
    private PlanMetaService _planMetaService;

    public PlanMetaServiceWrapper(PlanMetaService planMetaService) {
        _planMetaService = planMetaService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planMetaService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planMetaService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planMetaService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanMetaService getWrappedPlanMetaService() {
        return _planMetaService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanMetaService(PlanMetaService planMetaService) {
        _planMetaService = planMetaService;
    }

    @Override
    public PlanMetaService getWrappedService() {
        return _planMetaService;
    }

    @Override
    public void setWrappedService(PlanMetaService planMetaService) {
        _planMetaService = planMetaService;
    }
}
