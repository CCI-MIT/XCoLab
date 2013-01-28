package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link DiscussionMessageFlagService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DiscussionMessageFlagService
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
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public DiscussionMessageFlagService getWrappedDiscussionMessageFlagService() {
        return _discussionMessageFlagService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedDiscussionMessageFlagService(
        DiscussionMessageFlagService discussionMessageFlagService) {
        _discussionMessageFlagService = discussionMessageFlagService;
    }

    public DiscussionMessageFlagService getWrappedService() {
        return _discussionMessageFlagService;
    }

    public void setWrappedService(
        DiscussionMessageFlagService discussionMessageFlagService) {
        _discussionMessageFlagService = discussionMessageFlagService;
    }
}
