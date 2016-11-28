package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link EmailListService}.
 *
 * @author Brian Wing Shun Chan
 * @see EmailListService
 * @generated
 */
public class EmailListServiceWrapper implements EmailListService,
    ServiceWrapper<EmailListService> {
    private EmailListService _emailListService;

    public EmailListServiceWrapper(EmailListService emailListService) {
        _emailListService = emailListService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _emailListService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _emailListService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _emailListService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public EmailListService getWrappedEmailListService() {
        return _emailListService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedEmailListService(EmailListService emailListService) {
        _emailListService = emailListService;
    }

    @Override
    public EmailListService getWrappedService() {
        return _emailListService;
    }

    @Override
    public void setWrappedService(EmailListService emailListService) {
        _emailListService = emailListService;
    }
}
