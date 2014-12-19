package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanSectionPlanMapService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanSectionPlanMapService
 * @generated
 */
public class PlanSectionPlanMapServiceWrapper
    implements PlanSectionPlanMapService,
        ServiceWrapper<PlanSectionPlanMapService> {
    private PlanSectionPlanMapService _planSectionPlanMapService;

    public PlanSectionPlanMapServiceWrapper(
        PlanSectionPlanMapService planSectionPlanMapService) {
        _planSectionPlanMapService = planSectionPlanMapService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planSectionPlanMapService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planSectionPlanMapService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planSectionPlanMapService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanSectionPlanMapService getWrappedPlanSectionPlanMapService() {
        return _planSectionPlanMapService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanSectionPlanMapService(
        PlanSectionPlanMapService planSectionPlanMapService) {
        _planSectionPlanMapService = planSectionPlanMapService;
    }

    @Override
    public PlanSectionPlanMapService getWrappedService() {
        return _planSectionPlanMapService;
    }

    @Override
    public void setWrappedService(
        PlanSectionPlanMapService planSectionPlanMapService) {
        _planSectionPlanMapService = planSectionPlanMapService;
    }
}
