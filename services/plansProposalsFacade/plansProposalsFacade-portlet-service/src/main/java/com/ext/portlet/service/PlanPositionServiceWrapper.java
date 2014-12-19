package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanPositionService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanPositionService
 * @generated
 */
public class PlanPositionServiceWrapper implements PlanPositionService,
    ServiceWrapper<PlanPositionService> {
    private PlanPositionService _planPositionService;

    public PlanPositionServiceWrapper(PlanPositionService planPositionService) {
        _planPositionService = planPositionService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planPositionService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planPositionService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planPositionService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanPositionService getWrappedPlanPositionService() {
        return _planPositionService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanPositionService(
        PlanPositionService planPositionService) {
        _planPositionService = planPositionService;
    }

    @Override
    public PlanPositionService getWrappedService() {
        return _planPositionService;
    }

    @Override
    public void setWrappedService(PlanPositionService planPositionService) {
        _planPositionService = planPositionService;
    }
}
