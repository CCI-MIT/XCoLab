package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanModelRunService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanModelRunService
 * @generated
 */
public class PlanModelRunServiceWrapper implements PlanModelRunService,
    ServiceWrapper<PlanModelRunService> {
    private PlanModelRunService _planModelRunService;

    public PlanModelRunServiceWrapper(PlanModelRunService planModelRunService) {
        _planModelRunService = planModelRunService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planModelRunService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planModelRunService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planModelRunService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanModelRunService getWrappedPlanModelRunService() {
        return _planModelRunService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanModelRunService(
        PlanModelRunService planModelRunService) {
        _planModelRunService = planModelRunService;
    }

    @Override
    public PlanModelRunService getWrappedService() {
        return _planModelRunService;
    }

    @Override
    public void setWrappedService(PlanModelRunService planModelRunService) {
        _planModelRunService = planModelRunService;
    }
}
