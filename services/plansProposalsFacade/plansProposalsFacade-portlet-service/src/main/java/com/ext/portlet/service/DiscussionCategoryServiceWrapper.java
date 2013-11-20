package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DiscussionCategoryService}.
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionCategoryService
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
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _discussionCategoryService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _discussionCategoryService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _discussionCategoryService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public DiscussionCategoryService getWrappedDiscussionCategoryService() {
        return _discussionCategoryService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedDiscussionCategoryService(
        DiscussionCategoryService discussionCategoryService) {
        _discussionCategoryService = discussionCategoryService;
    }

    @Override
    public DiscussionCategoryService getWrappedService() {
        return _discussionCategoryService;
    }

    @Override
    public void setWrappedService(
        DiscussionCategoryService discussionCategoryService) {
        _discussionCategoryService = discussionCategoryService;
    }
}
