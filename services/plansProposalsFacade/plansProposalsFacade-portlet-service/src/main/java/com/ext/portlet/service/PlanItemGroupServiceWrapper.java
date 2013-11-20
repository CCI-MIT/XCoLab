package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanItemGroupService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanItemGroupService
 * @generated
 */
public class PlanItemGroupServiceWrapper implements PlanItemGroupService,
    ServiceWrapper<PlanItemGroupService> {
    private PlanItemGroupService _planItemGroupService;

    public PlanItemGroupServiceWrapper(
        PlanItemGroupService planItemGroupService) {
        _planItemGroupService = planItemGroupService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planItemGroupService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planItemGroupService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planItemGroupService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanItemGroupService getWrappedPlanItemGroupService() {
        return _planItemGroupService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanItemGroupService(
        PlanItemGroupService planItemGroupService) {
        _planItemGroupService = planItemGroupService;
    }

    @Override
    public PlanItemGroupService getWrappedService() {
        return _planItemGroupService;
    }

    @Override
    public void setWrappedService(PlanItemGroupService planItemGroupService) {
        _planItemGroupService = planItemGroupService;
    }
}
