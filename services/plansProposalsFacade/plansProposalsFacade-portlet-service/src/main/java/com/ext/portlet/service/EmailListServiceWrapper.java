package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link EmailListService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       EmailListService
 * @generated
 */
public class EmailListServiceWrapper implements EmailListService,
    ServiceWrapper<EmailListService> {
    private EmailListService _emailListService;

    public EmailListServiceWrapper(EmailListService emailListService) {
        _emailListService = emailListService;
    }

    public java.lang.String helloWorld(java.lang.String listName,
        java.lang.String email) {
        return _emailListService.helloWorld(listName, email);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public EmailListService getWrappedEmailListService() {
        return _emailListService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedEmailListService(EmailListService emailListService) {
        _emailListService = emailListService;
    }

    public EmailListService getWrappedService() {
        return _emailListService;
    }

    public void setWrappedService(EmailListService emailListService) {
        _emailListService = emailListService;
    }
}
