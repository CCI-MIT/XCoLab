package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MessagingRedirectLinkService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessagingRedirectLinkService
 * @generated
 */
public class MessagingRedirectLinkServiceWrapper
    implements MessagingRedirectLinkService,
        ServiceWrapper<MessagingRedirectLinkService> {
    private MessagingRedirectLinkService _messagingRedirectLinkService;

    public MessagingRedirectLinkServiceWrapper(
        MessagingRedirectLinkService messagingRedirectLinkService) {
        _messagingRedirectLinkService = messagingRedirectLinkService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public MessagingRedirectLinkService getWrappedMessagingRedirectLinkService() {
        return _messagingRedirectLinkService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedMessagingRedirectLinkService(
        MessagingRedirectLinkService messagingRedirectLinkService) {
        _messagingRedirectLinkService = messagingRedirectLinkService;
    }

    public MessagingRedirectLinkService getWrappedService() {
        return _messagingRedirectLinkService;
    }

    public void setWrappedService(
        MessagingRedirectLinkService messagingRedirectLinkService) {
        _messagingRedirectLinkService = messagingRedirectLinkService;
    }
}
