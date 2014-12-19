package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanItemService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanItemService
 * @generated
 */
public class PlanItemServiceWrapper implements PlanItemService,
    ServiceWrapper<PlanItemService> {
    private PlanItemService _planItemService;

    public PlanItemServiceWrapper(PlanItemService planItemService) {
        _planItemService = planItemService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planItemService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planItemService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planItemService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanItemService getWrappedPlanItemService() {
        return _planItemService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanItemService(PlanItemService planItemService) {
        _planItemService = planItemService;
    }

    @Override
    public PlanItemService getWrappedService() {
        return _planItemService;
    }

    @Override
    public void setWrappedService(PlanItemService planItemService) {
        _planItemService = planItemService;
    }
}
