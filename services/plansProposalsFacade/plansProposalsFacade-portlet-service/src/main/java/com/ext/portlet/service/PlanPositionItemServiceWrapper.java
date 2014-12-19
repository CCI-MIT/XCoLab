package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanPositionItemService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanPositionItemService
 * @generated
 */
public class PlanPositionItemServiceWrapper implements PlanPositionItemService,
    ServiceWrapper<PlanPositionItemService> {
    private PlanPositionItemService _planPositionItemService;

    public PlanPositionItemServiceWrapper(
        PlanPositionItemService planPositionItemService) {
        _planPositionItemService = planPositionItemService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planPositionItemService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planPositionItemService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planPositionItemService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanPositionItemService getWrappedPlanPositionItemService() {
        return _planPositionItemService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanPositionItemService(
        PlanPositionItemService planPositionItemService) {
        _planPositionItemService = planPositionItemService;
    }

    @Override
    public PlanPositionItemService getWrappedService() {
        return _planPositionItemService;
    }

    @Override
    public void setWrappedService(
        PlanPositionItemService planPositionItemService) {
        _planPositionItemService = planPositionItemService;
    }
}
