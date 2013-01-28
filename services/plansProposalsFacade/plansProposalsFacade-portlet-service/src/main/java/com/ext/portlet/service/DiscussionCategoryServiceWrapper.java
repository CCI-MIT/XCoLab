package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link DiscussionCategoryService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       DiscussionCategoryService
 * @generated
 */
public class DiscussionCategoryServiceWrapper
    implements DiscussionCategoryService,
        ServiceWrapper<DiscussionCategoryService> {
    private DiscussionCategoryService _discussionCategoryService;

    public DiscussionCategoryServiceWrapper(
        DiscussionCategoryService discussionCategoryService) {
        _discussionCategoryService = discussionCategoryService;
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public DiscussionCategoryService getWrappedDiscussionCategoryService() {
        return _discussionCategoryService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedDiscussionCategoryService(
        DiscussionCategoryService discussionCategoryService) {
        _discussionCategoryService = discussionCategoryService;
    }

    public DiscussionCategoryService getWrappedService() {
        return _discussionCategoryService;
    }

    public void setWrappedService(
        DiscussionCategoryService discussionCategoryService) {
        _discussionCategoryService = discussionCategoryService;
    }
}
