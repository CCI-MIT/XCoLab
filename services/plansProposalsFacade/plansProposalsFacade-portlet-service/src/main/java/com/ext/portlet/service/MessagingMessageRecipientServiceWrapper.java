package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MessagingMessageRecipientService}.
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageRecipientService
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
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _messagingMessageRecipientService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _messagingMessageRecipientService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _messagingMessageRecipientService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public MessagingMessageRecipientService getWrappedMessagingMessageRecipientService() {
        return _messagingMessageRecipientService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedMessagingMessageRecipientService(
        MessagingMessageRecipientService messagingMessageRecipientService) {
        _messagingMessageRecipientService = messagingMessageRecipientService;
    }

    @Override
    public MessagingMessageRecipientService getWrappedService() {
        return _messagingMessageRecipientService;
    }

    @Override
    public void setWrappedService(
        MessagingMessageRecipientService messagingMessageRecipientService) {
        _messagingMessageRecipientService = messagingMessageRecipientService;
    }
}
