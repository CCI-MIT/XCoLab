package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MessagingRedirectLinkService}.
 *
 * @author Brian Wing Shun Chan
 * @see MessagingRedirectLinkService
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
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _messagingRedirectLinkService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _messagingRedirectLinkService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _messagingRedirectLinkService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public MessagingRedirectLinkService getWrappedMessagingRedirectLinkService() {
        return _messagingRedirectLinkService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedMessagingRedirectLinkService(
        MessagingRedirectLinkService messagingRedirectLinkService) {
        _messagingRedirectLinkService = messagingRedirectLinkService;
    }

    @Override
    public MessagingRedirectLinkService getWrappedService() {
        return _messagingRedirectLinkService;
    }

    @Override
    public void setWrappedService(
        MessagingRedirectLinkService messagingRedirectLinkService) {
        _messagingRedirectLinkService = messagingRedirectLinkService;
    }
}
