package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MessagingMessageConversionService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessagingMessageConversionService
 * @generated
 */
public class MessagingMessageConversionServiceWrapper
    implements MessagingMessageConversionService,
        ServiceWrapper<MessagingMessageConversionService> {
    private MessagingMessageConversionService _messagingMessageConversionService;

    public MessagingMessageConversionServiceWrapper(
        MessagingMessageConversionService messagingMessageConversionService) {
        _messagingMessageConversionService = messagingMessageConversionService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public MessagingMessageConversionService getWrappedMessagingMessageConversionService() {
        return _messagingMessageConversionService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedMessagingMessageConversionService(
        MessagingMessageConversionService messagingMessageConversionService) {
        _messagingMessageConversionService = messagingMessageConversionService;
    }

    public MessagingMessageConversionService getWrappedService() {
        return _messagingMessageConversionService;
    }

    public void setWrappedService(
        MessagingMessageConversionService messagingMessageConversionService) {
        _messagingMessageConversionService = messagingMessageConversionService;
    }
}
