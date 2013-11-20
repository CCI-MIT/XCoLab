package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DiscussionMessageFlagService}.
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionMessageFlagService
 * @generated
 */
public class DiscussionMessageFlagServiceWrapper
    implements DiscussionMessageFlagService,
        ServiceWrapper<DiscussionMessageFlagService> {
    private DiscussionMessageFlagService _discussionMessageFlagService;

    public DiscussionMessageFlagServiceWrapper(
        DiscussionMessageFlagService discussionMessageFlagService) {
        _discussionMessageFlagService = discussionMessageFlagService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _discussionMessageFlagService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _discussionMessageFlagService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _discussionMessageFlagService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public DiscussionMessageFlagService getWrappedDiscussionMessageFlagService() {
        return _discussionMessageFlagService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedDiscussionMessageFlagService(
        DiscussionMessageFlagService discussionMessageFlagService) {
        _discussionMessageFlagService = discussionMessageFlagService;
    }

    @Override
    public DiscussionMessageFlagService getWrappedService() {
        return _discussionMessageFlagService;
    }

    @Override
    public void setWrappedService(
        DiscussionMessageFlagService discussionMessageFlagService) {
        _discussionMessageFlagService = discussionMessageFlagService;
    }
}
