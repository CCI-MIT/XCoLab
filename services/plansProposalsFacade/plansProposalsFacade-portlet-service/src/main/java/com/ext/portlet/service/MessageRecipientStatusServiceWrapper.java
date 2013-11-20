package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MessageRecipientStatusService}.
 *
 * @author Brian Wing Shun Chan
 * @see MessageRecipientStatusService
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
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _messageRecipientStatusService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _messageRecipientStatusService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _messageRecipientStatusService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public MessageRecipientStatusService getWrappedMessageRecipientStatusService() {
        return _messageRecipientStatusService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedMessageRecipientStatusService(
        MessageRecipientStatusService messageRecipientStatusService) {
        _messageRecipientStatusService = messageRecipientStatusService;
    }

    @Override
    public MessageRecipientStatusService getWrappedService() {
        return _messageRecipientStatusService;
    }

    @Override
    public void setWrappedService(
        MessageRecipientStatusService messageRecipientStatusService) {
        _messageRecipientStatusService = messageRecipientStatusService;
    }
}
