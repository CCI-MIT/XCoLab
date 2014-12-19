package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanTemplateSectionService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanTemplateSectionService
 * @generated
 */
public class PlanTemplateSectionServiceWrapper
    implements PlanTemplateSectionService,
        ServiceWrapper<PlanTemplateSectionService> {
    private PlanTemplateSectionService _planTemplateSectionService;

    public PlanTemplateSectionServiceWrapper(
        PlanTemplateSectionService planTemplateSectionService) {
        _planTemplateSectionService = planTemplateSectionService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planTemplateSectionService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planTemplateSectionService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planTemplateSectionService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanTemplateSectionService getWrappedPlanTemplateSectionService() {
        return _planTemplateSectionService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanTemplateSectionService(
        PlanTemplateSectionService planTemplateSectionService) {
        _planTemplateSectionService = planTemplateSectionService;
    }

    @Override
    public PlanTemplateSectionService getWrappedService() {
        return _planTemplateSectionService;
    }

    @Override
    public void setWrappedService(
        PlanTemplateSectionService planTemplateSectionService) {
        _planTemplateSectionService = planTemplateSectionService;
    }
}
