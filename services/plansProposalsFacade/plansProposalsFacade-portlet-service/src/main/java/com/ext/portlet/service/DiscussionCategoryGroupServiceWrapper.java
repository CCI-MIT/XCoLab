package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link DiscussionCategoryGroupService}.
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionCategoryGroupService
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
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _discussionCategoryGroupService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _discussionCategoryGroupService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _discussionCategoryGroupService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public DiscussionCategoryGroupService getWrappedDiscussionCategoryGroupService() {
        return _discussionCategoryGroupService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedDiscussionCategoryGroupService(
        DiscussionCategoryGroupService discussionCategoryGroupService) {
        _discussionCategoryGroupService = discussionCategoryGroupService;
    }

    @Override
    public DiscussionCategoryGroupService getWrappedService() {
        return _discussionCategoryGroupService;
    }

    @Override
    public void setWrappedService(
        DiscussionCategoryGroupService discussionCategoryGroupService) {
        _discussionCategoryGroupService = discussionCategoryGroupService;
    }
}
