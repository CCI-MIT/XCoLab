package com.ext.portlet.discussions.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link DiscussionCategoryGroupService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DiscussionCategoryGroupService
 * @generated
 */
public class DiscussionCategoryGroupServiceWrapper
    implements DiscussionCategoryGroupService,
        ServiceWrapper<DiscussionCategoryGroupService> {
    private DiscussionCategoryGroupService _discussionCategoryGroupService;

    public DiscussionCategoryGroupServiceWrapper(
        DiscussionCategoryGroupService discussionCategoryGroupService) {
        _discussionCategoryGroupService = discussionCategoryGroupService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public DiscussionCategoryGroupService getWrappedDiscussionCategoryGroupService() {
        return _discussionCategoryGroupService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedDiscussionCategoryGroupService(
        DiscussionCategoryGroupService discussionCategoryGroupService) {
        _discussionCategoryGroupService = discussionCategoryGroupService;
    }

    public DiscussionCategoryGroupService getWrappedService() {
        return _discussionCategoryGroupService;
    }

    public void setWrappedService(
        DiscussionCategoryGroupService discussionCategoryGroupService) {
        _discussionCategoryGroupService = discussionCategoryGroupService;
    }
}
