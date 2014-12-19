package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MessagingMessageService}.
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageService
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
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _messagingMessageService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _messagingMessageService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _messagingMessageService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public MessagingMessageService getWrappedMessagingMessageService() {
        return _messagingMessageService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedMessagingMessageService(
        MessagingMessageService messagingMessageService) {
        _messagingMessageService = messagingMessageService;
    }

    @Override
    public MessagingMessageService getWrappedService() {
        return _messagingMessageService;
    }

    @Override
    public void setWrappedService(
        MessagingMessageService messagingMessageService) {
        _messagingMessageService = messagingMessageService;
    }
}
