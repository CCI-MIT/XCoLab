package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MessagingMessageRecipientService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessagingMessageRecipientService
 * @generated
 */
public class MessagingMessageRecipientServiceWrapper
    implements MessagingMessageRecipientService,
        ServiceWrapper<MessagingMessageRecipientService> {
    private MessagingMessageRecipientService _messagingMessageRecipientService;

    public MessagingMessageRecipientServiceWrapper(
        MessagingMessageRecipientService messagingMessageRecipientService) {
        _messagingMessageRecipientService = messagingMessageRecipientService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public MessagingMessageRecipientService getWrappedMessagingMessageRecipientService() {
        return _messagingMessageRecipientService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedMessagingMessageRecipientService(
        MessagingMessageRecipientService messagingMessageRecipientService) {
        _messagingMessageRecipientService = messagingMessageRecipientService;
    }

    public MessagingMessageRecipientService getWrappedService() {
        return _messagingMessageRecipientService;
    }

    public void setWrappedService(
        MessagingMessageRecipientService messagingMessageRecipientService) {
        _messagingMessageRecipientService = messagingMessageRecipientService;
    }
}
