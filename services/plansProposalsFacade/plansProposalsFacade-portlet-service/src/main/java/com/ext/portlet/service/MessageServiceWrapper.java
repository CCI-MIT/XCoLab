package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MessageService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessageService
 * @generated
 */
public class MessageServiceWrapper implements MessageService,
    ServiceWrapper<MessageService> {
    private MessageService _messageService;

    public MessageServiceWrapper(MessageService messageService) {
        _messageService = messageService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public MessageService getWrappedMessageService() {
        return _messageService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedMessageService(MessageService messageService) {
        _messageService = messageService;
    }

    public MessageService getWrappedService() {
        return _messageService;
    }

    public void setWrappedService(MessageService messageService) {
        _messageService = messageService;
    }
}
