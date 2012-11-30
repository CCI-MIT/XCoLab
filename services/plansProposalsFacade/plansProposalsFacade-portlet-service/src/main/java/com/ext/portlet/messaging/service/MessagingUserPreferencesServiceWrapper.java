package com.ext.portlet.messaging.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MessagingUserPreferencesService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessagingUserPreferencesService
 * @generated
 */
public class MessagingUserPreferencesServiceWrapper
    implements MessagingUserPreferencesService,
        ServiceWrapper<MessagingUserPreferencesService> {
    private MessagingUserPreferencesService _messagingUserPreferencesService;

    public MessagingUserPreferencesServiceWrapper(
        MessagingUserPreferencesService messagingUserPreferencesService) {
        _messagingUserPreferencesService = messagingUserPreferencesService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public MessagingUserPreferencesService getWrappedMessagingUserPreferencesService() {
        return _messagingUserPreferencesService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedMessagingUserPreferencesService(
        MessagingUserPreferencesService messagingUserPreferencesService) {
        _messagingUserPreferencesService = messagingUserPreferencesService;
    }

    public MessagingUserPreferencesService getWrappedService() {
        return _messagingUserPreferencesService;
    }

    public void setWrappedService(
        MessagingUserPreferencesService messagingUserPreferencesService) {
        _messagingUserPreferencesService = messagingUserPreferencesService;
    }
}
