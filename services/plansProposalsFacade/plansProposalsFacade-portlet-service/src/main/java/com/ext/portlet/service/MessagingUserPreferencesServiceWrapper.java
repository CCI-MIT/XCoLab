package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MessagingUserPreferencesService}.
 *
 * @author Brian Wing Shun Chan
 * @see MessagingUserPreferencesService
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
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _messagingUserPreferencesService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _messagingUserPreferencesService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _messagingUserPreferencesService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public MessagingUserPreferencesService getWrappedMessagingUserPreferencesService() {
        return _messagingUserPreferencesService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedMessagingUserPreferencesService(
        MessagingUserPreferencesService messagingUserPreferencesService) {
        _messagingUserPreferencesService = messagingUserPreferencesService;
    }

    @Override
    public MessagingUserPreferencesService getWrappedService() {
        return _messagingUserPreferencesService;
    }

    @Override
    public void setWrappedService(
        MessagingUserPreferencesService messagingUserPreferencesService) {
        _messagingUserPreferencesService = messagingUserPreferencesService;
    }
}
