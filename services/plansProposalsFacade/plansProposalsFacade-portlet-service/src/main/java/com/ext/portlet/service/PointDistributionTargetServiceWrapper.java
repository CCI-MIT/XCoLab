package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PointDistributionTargetService}.
 *
 * @author Brian Wing Shun Chan
 * @see PointDistributionTargetService
 * @generated
 */
public class PointDistributionTargetServiceWrapper
    implements PointDistributionTargetService,
        ServiceWrapper<PointDistributionTargetService> {
    private PointDistributionTargetService _pointDistributionTargetService;

    public PointDistributionTargetServiceWrapper(
        PointDistributionTargetService pointDistributionTargetService) {
        _pointDistributionTargetService = pointDistributionTargetService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _pointDistributionTargetService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _pointDistributionTargetService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _pointDistributionTargetService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PointDistributionTargetService getWrappedPointDistributionTargetService() {
        return _pointDistributionTargetService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPointDistributionTargetService(
        PointDistributionTargetService pointDistributionTargetService) {
        _pointDistributionTargetService = pointDistributionTargetService;
    }

    @Override
    public PointDistributionTargetService getWrappedService() {
        return _pointDistributionTargetService;
    }

    @Override
    public void setWrappedService(
        PointDistributionTargetService pointDistributionTargetService) {
        _pointDistributionTargetService = pointDistributionTargetService;
    }
}
