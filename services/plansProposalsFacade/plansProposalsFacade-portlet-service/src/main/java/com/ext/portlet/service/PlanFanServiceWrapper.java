package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanFanService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanFanService
 * @generated
 */
public class PlanFanServiceWrapper implements PlanFanService,
    ServiceWrapper<PlanFanService> {
    private PlanFanService _planFanService;

    public PlanFanServiceWrapper(PlanFanService planFanService) {
        _planFanService = planFanService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planFanService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planFanService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planFanService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanFanService getWrappedPlanFanService() {
        return _planFanService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanFanService(PlanFanService planFanService) {
        _planFanService = planFanService;
    }

    @Override
    public PlanFanService getWrappedService() {
        return _planFanService;
    }

    @Override
    public void setWrappedService(PlanFanService planFanService) {
        _planFanService = planFanService;
    }
}
