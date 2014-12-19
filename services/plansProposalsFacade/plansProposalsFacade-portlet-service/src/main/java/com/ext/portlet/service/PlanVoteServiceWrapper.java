package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanVoteService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanVoteService
 * @generated
 */
public class PlanVoteServiceWrapper implements PlanVoteService,
    ServiceWrapper<PlanVoteService> {
    private PlanVoteService _planVoteService;

    public PlanVoteServiceWrapper(PlanVoteService planVoteService) {
        _planVoteService = planVoteService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planVoteService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planVoteService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planVoteService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanVoteService getWrappedPlanVoteService() {
        return _planVoteService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanVoteService(PlanVoteService planVoteService) {
        _planVoteService = planVoteService;
    }

    @Override
    public PlanVoteService getWrappedService() {
        return _planVoteService;
    }

    @Override
    public void setWrappedService(PlanVoteService planVoteService) {
        _planVoteService = planVoteService;
    }
}
