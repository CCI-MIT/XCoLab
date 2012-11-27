package com.ext.portlet.discussions.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link DiscussionMessageService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DiscussionMessageService
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
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public DiscussionMessageService getWrappedDiscussionMessageService() {
        return _discussionMessageService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedDiscussionMessageService(
        DiscussionMessageService discussionMessageService) {
        _discussionMessageService = discussionMessageService;
    }

    public DiscussionMessageService getWrappedService() {
        return _discussionMessageService;
    }

    public void setWrappedService(
        DiscussionMessageService discussionMessageService) {
        _discussionMessageService = discussionMessageService;
    }
}
