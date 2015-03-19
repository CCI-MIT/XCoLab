package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContestDiscussionService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContestDiscussionService
 * @generated
 */
public class ContestDiscussionServiceWrapper implements ContestDiscussionService,
    ServiceWrapper<ContestDiscussionService> {
    private ContestDiscussionService _contestDiscussionService;

    public ContestDiscussionServiceWrapper(
        ContestDiscussionService contestDiscussionService) {
        _contestDiscussionService = contestDiscussionService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _contestDiscussionService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contestDiscussionService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _contestDiscussionService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ContestDiscussionService getWrappedContestDiscussionService() {
        return _contestDiscussionService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedContestDiscussionService(
        ContestDiscussionService contestDiscussionService) {
        _contestDiscussionService = contestDiscussionService;
    }

    @Override
    public ContestDiscussionService getWrappedService() {
        return _contestDiscussionService;
    }

    @Override
    public void setWrappedService(
        ContestDiscussionService contestDiscussionService) {
        _contestDiscussionService = contestDiscussionService;
    }
}
