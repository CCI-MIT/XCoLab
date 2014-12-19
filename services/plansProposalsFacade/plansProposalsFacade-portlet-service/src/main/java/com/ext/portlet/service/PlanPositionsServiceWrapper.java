package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanPositionsService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanPositionsService
 * @generated
 */
public class PlanPositionsServiceWrapper implements PlanPositionsService,
    ServiceWrapper<PlanPositionsService> {
    private PlanPositionsService _planPositionsService;

    public PlanPositionsServiceWrapper(
        PlanPositionsService planPositionsService) {
        _planPositionsService = planPositionsService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planPositionsService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planPositionsService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planPositionsService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanPositionsService getWrappedPlanPositionsService() {
        return _planPositionsService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanPositionsService(
        PlanPositionsService planPositionsService) {
        _planPositionsService = planPositionsService;
    }

    @Override
    public PlanPositionsService getWrappedService() {
        return _planPositionsService;
    }

    @Override
    public void setWrappedService(PlanPositionsService planPositionsService) {
        _planPositionsService = planPositionsService;
    }
}
