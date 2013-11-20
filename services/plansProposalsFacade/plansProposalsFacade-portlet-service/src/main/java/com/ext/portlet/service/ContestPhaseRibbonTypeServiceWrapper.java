package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ContestPhaseRibbonTypeService}.
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseRibbonTypeService
 * @generated
 */
public class ContestPhaseRibbonTypeServiceWrapper
    implements ContestPhaseRibbonTypeService,
        ServiceWrapper<ContestPhaseRibbonTypeService> {
    private ContestPhaseRibbonTypeService _contestPhaseRibbonTypeService;

    public ContestPhaseRibbonTypeServiceWrapper(
        ContestPhaseRibbonTypeService contestPhaseRibbonTypeService) {
        _contestPhaseRibbonTypeService = contestPhaseRibbonTypeService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _contestPhaseRibbonTypeService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _contestPhaseRibbonTypeService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _contestPhaseRibbonTypeService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ContestPhaseRibbonTypeService getWrappedContestPhaseRibbonTypeService() {
        return _contestPhaseRibbonTypeService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedContestPhaseRibbonTypeService(
        ContestPhaseRibbonTypeService contestPhaseRibbonTypeService) {
        _contestPhaseRibbonTypeService = contestPhaseRibbonTypeService;
    }

    @Override
    public ContestPhaseRibbonTypeService getWrappedService() {
        return _contestPhaseRibbonTypeService;
    }

    @Override
    public void setWrappedService(
        ContestPhaseRibbonTypeService contestPhaseRibbonTypeService) {
        _contestPhaseRibbonTypeService = contestPhaseRibbonTypeService;
    }
}
