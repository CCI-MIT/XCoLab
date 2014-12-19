package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PlansFilterService}.
 *
 * @author Brian Wing Shun Chan
 * @see PlansFilterService
 * @generated
 */
public class PlansFilterServiceWrapper implements PlansFilterService,
    ServiceWrapper<PlansFilterService> {
    private PlansFilterService _plansFilterService;

    public PlansFilterServiceWrapper(PlansFilterService plansFilterService) {
        _plansFilterService = plansFilterService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _plansFilterService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _plansFilterService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _plansFilterService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PlansFilterService getWrappedPlansFilterService() {
        return _plansFilterService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPlansFilterService(
        PlansFilterService plansFilterService) {
        _plansFilterService = plansFilterService;
    }

    @Override
    public PlansFilterService getWrappedService() {
        return _plansFilterService;
    }

    @Override
    public void setWrappedService(PlansFilterService plansFilterService) {
        _plansFilterService = plansFilterService;
    }
}
