package com.ext.portlet.messaging.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MessageRecipientStatusService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessageRecipientStatusService
 * @generated
 */
public class MessageRecipientStatusServiceWrapper
    implements MessageRecipientStatusService,
        ServiceWrapper<MessageRecipientStatusService> {
    private MessageRecipientStatusService _messageRecipientStatusService;

    public MessageRecipientStatusServiceWrapper(
        MessageRecipientStatusService messageRecipientStatusService) {
        _messageRecipientStatusService = messageRecipientStatusService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public MessageRecipientStatusService getWrappedMessageRecipientStatusService() {
        return _messageRecipientStatusService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedMessageRecipientStatusService(
        MessageRecipientStatusService messageRecipientStatusService) {
        _messageRecipientStatusService = messageRecipientStatusService;
    }

    public MessageRecipientStatusService getWrappedService() {
        return _messageRecipientStatusService;
    }

    public void setWrappedService(
        MessageRecipientStatusService messageRecipientStatusService) {
        _messageRecipientStatusService = messageRecipientStatusService;
    }
}
