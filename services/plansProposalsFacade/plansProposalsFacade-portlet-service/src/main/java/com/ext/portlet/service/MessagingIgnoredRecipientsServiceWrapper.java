package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MessagingIgnoredRecipientsService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessagingIgnoredRecipientsService
 * @generated
 */
public class MessagingIgnoredRecipientsServiceWrapper
    implements MessagingIgnoredRecipientsService,
        ServiceWrapper<MessagingIgnoredRecipientsService> {
    private MessagingIgnoredRecipientsService _messagingIgnoredRecipientsService;

    public MessagingIgnoredRecipientsServiceWrapper(
        MessagingIgnoredRecipientsService messagingIgnoredRecipientsService) {
        _messagingIgnoredRecipientsService = messagingIgnoredRecipientsService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public MessagingIgnoredRecipientsService getWrappedMessagingIgnoredRecipientsService() {
        return _messagingIgnoredRecipientsService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedMessagingIgnoredRecipientsService(
        MessagingIgnoredRecipientsService messagingIgnoredRecipientsService) {
        _messagingIgnoredRecipientsService = messagingIgnoredRecipientsService;
    }

    public MessagingIgnoredRecipientsService getWrappedService() {
        return _messagingIgnoredRecipientsService;
    }

    public void setWrappedService(
        MessagingIgnoredRecipientsService messagingIgnoredRecipientsService) {
        _messagingIgnoredRecipientsService = messagingIgnoredRecipientsService;
    }
}
