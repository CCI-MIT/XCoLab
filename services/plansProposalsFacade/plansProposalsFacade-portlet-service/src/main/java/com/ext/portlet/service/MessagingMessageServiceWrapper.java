package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MessagingMessageService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessagingMessageService
 * @generated
 */
public class MessagingMessageServiceWrapper implements MessagingMessageService,
    ServiceWrapper<MessagingMessageService> {
    private MessagingMessageService _messagingMessageService;

    public MessagingMessageServiceWrapper(
        MessagingMessageService messagingMessageService) {
        _messagingMessageService = messagingMessageService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public MessagingMessageService getWrappedMessagingMessageService() {
        return _messagingMessageService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedMessagingMessageService(
        MessagingMessageService messagingMessageService) {
        _messagingMessageService = messagingMessageService;
    }

    public MessagingMessageService getWrappedService() {
        return _messagingMessageService;
    }

    public void setWrappedService(
        MessagingMessageService messagingMessageService) {
        _messagingMessageService = messagingMessageService;
    }
}
