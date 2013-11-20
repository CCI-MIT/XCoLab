package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MessagingMessageConversionTypeService}.
 *
 * @author Brian Wing Shun Chan
 * @see MessagingMessageConversionTypeService
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
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _messagingMessageConversionTypeService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _messagingMessageConversionTypeService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _messagingMessageConversionTypeService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public MessagingMessageConversionTypeService getWrappedMessagingMessageConversionTypeService() {
        return _messagingMessageConversionTypeService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedMessagingMessageConversionTypeService(
        MessagingMessageConversionTypeService messagingMessageConversionTypeService) {
        _messagingMessageConversionTypeService = messagingMessageConversionTypeService;
    }

    @Override
    public MessagingMessageConversionTypeService getWrappedService() {
        return _messagingMessageConversionTypeService;
    }

    @Override
    public void setWrappedService(
        MessagingMessageConversionTypeService messagingMessageConversionTypeService) {
        _messagingMessageConversionTypeService = messagingMessageConversionTypeService;
    }
}
