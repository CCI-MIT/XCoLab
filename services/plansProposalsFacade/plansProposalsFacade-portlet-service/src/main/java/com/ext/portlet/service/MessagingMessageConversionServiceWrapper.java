package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MessagingMessageConversionService}.
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageConversionService
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
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _messagingMessageConversionService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _messagingMessageConversionService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _messagingMessageConversionService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public MessagingMessageConversionService getWrappedMessagingMessageConversionService() {
        return _messagingMessageConversionService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedMessagingMessageConversionService(
        MessagingMessageConversionService messagingMessageConversionService) {
        _messagingMessageConversionService = messagingMessageConversionService;
    }

    @Override
    public MessagingMessageConversionService getWrappedService() {
        return _messagingMessageConversionService;
    }

    @Override
    public void setWrappedService(
        MessagingMessageConversionService messagingMessageConversionService) {
        _messagingMessageConversionService = messagingMessageConversionService;
    }
}
