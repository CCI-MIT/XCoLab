package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DiscussionMessageService}.
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionMessageService
 * @generated
 */
public class DiscussionMessageServiceWrapper implements DiscussionMessageService,
    ServiceWrapper<DiscussionMessageService> {
    private DiscussionMessageService _discussionMessageService;

    public DiscussionMessageServiceWrapper(
        DiscussionMessageService discussionMessageService) {
        _discussionMessageService = discussionMessageService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _discussionMessageService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _discussionMessageService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _discussionMessageService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public DiscussionMessageService getWrappedDiscussionMessageService() {
        return _discussionMessageService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedDiscussionMessageService(
        DiscussionMessageService discussionMessageService) {
        _discussionMessageService = discussionMessageService;
    }

    @Override
    public DiscussionMessageService getWrappedService() {
        return _discussionMessageService;
    }

    @Override
    public void setWrappedService(
        DiscussionMessageService discussionMessageService) {
        _discussionMessageService = discussionMessageService;
    }
}
