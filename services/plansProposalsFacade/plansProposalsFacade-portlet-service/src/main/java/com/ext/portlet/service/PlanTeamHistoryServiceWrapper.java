package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlanTeamHistoryService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlanTeamHistoryService
 * @generated
 */
public class PlanTeamHistoryServiceWrapper implements PlanTeamHistoryService,
    ServiceWrapper<PlanTeamHistoryService> {
    private PlanTeamHistoryService _planTeamHistoryService;

    public PlanTeamHistoryServiceWrapper(
        PlanTeamHistoryService planTeamHistoryService) {
        _planTeamHistoryService = planTeamHistoryService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _planTeamHistoryService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _planTeamHistoryService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _planTeamHistoryService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlanTeamHistoryService getWrappedPlanTeamHistoryService() {
        return _planTeamHistoryService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlanTeamHistoryService(
        PlanTeamHistoryService planTeamHistoryService) {
        _planTeamHistoryService = planTeamHistoryService;
    }

    @Override
    public PlanTeamHistoryService getWrappedService() {
        return _planTeamHistoryService;
    }

    @Override
    public void setWrappedService(PlanTeamHistoryService planTeamHistoryService) {
        _planTeamHistoryService = planTeamHistoryService;
    }
}
