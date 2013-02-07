package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link MessagingMessageConversionTypeService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       MessagingMessageConversionTypeService
 * @generated
 */
public class MessagingMessageConversionTypeServiceWrapper
    implements MessagingMessageConversionTypeService,
        ServiceWrapper<MessagingMessageConversionTypeService> {
    private MessagingMessageConversionTypeService _messagingMessageConversionTypeService;

    public MessagingMessageConversionTypeServiceWrapper(
        MessagingMessageConversionTypeService messagingMessageConversionTypeService) {
        _messagingMessageConversionTypeService = messagingMessageConversionTypeService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public MessagingMessageConversionTypeService getWrappedMessagingMessageConversionTypeService() {
        return _messagingMessageConversionTypeService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedMessagingMessageConversionTypeService(
        MessagingMessageConversionTypeService messagingMessageConversionTypeService) {
        _messagingMessageConversionTypeService = messagingMessageConversionTypeService;
    }

    public MessagingMessageConversionTypeService getWrappedService() {
        return _messagingMessageConversionTypeService;
    }

    public void setWrappedService(
        MessagingMessageConversionTypeService messagingMessageConversionTypeService) {
        _messagingMessageConversionTypeService = messagingMessageConversionTypeService;
    }
}
