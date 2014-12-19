package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlansFilterPositionService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlansFilterPositionService
 * @generated
 */
public class PlansFilterPositionServiceWrapper
    implements PlansFilterPositionService,
        ServiceWrapper<PlansFilterPositionService> {
    private PlansFilterPositionService _plansFilterPositionService;

    public PlansFilterPositionServiceWrapper(
        PlansFilterPositionService plansFilterPositionService) {
        _plansFilterPositionService = plansFilterPositionService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _plansFilterPositionService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _plansFilterPositionService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _plansFilterPositionService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlansFilterPositionService getWrappedPlansFilterPositionService() {
        return _plansFilterPositionService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlansFilterPositionService(
        PlansFilterPositionService plansFilterPositionService) {
        _plansFilterPositionService = plansFilterPositionService;
    }

    @Override
    public PlansFilterPositionService getWrappedService() {
        return _plansFilterPositionService;
    }

    @Override
    public void setWrappedService(
        PlansFilterPositionService plansFilterPositionService) {
        _plansFilterPositionService = plansFilterPositionService;
    }
}
