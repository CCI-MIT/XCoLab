package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContestEmailTemplateService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContestEmailTemplateService
 * @generated
 */
public class ContestEmailTemplateServiceWrapper
    implements ContestEmailTemplateService,
        ServiceWrapper<ContestEmailTemplateService> {
    private ContestEmailTemplateService _contestEmailTemplateService;

    public ContestEmailTemplateServiceWrapper(
        ContestEmailTemplateService contestEmailTemplateService) {
        _contestEmailTemplateService = contestEmailTemplateService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _contestEmailTemplateService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contestEmailTemplateService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _contestEmailTemplateService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ContestEmailTemplateService getWrappedContestEmailTemplateService() {
        return _contestEmailTemplateService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedContestEmailTemplateService(
        ContestEmailTemplateService contestEmailTemplateService) {
        _contestEmailTemplateService = contestEmailTemplateService;
    }

    @Override
    public ContestEmailTemplateService getWrappedService() {
        return _contestEmailTemplateService;
    }

    @Override
    public void setWrappedService(
        ContestEmailTemplateService contestEmailTemplateService) {
        _contestEmailTemplateService = contestEmailTemplateService;
    }
}
