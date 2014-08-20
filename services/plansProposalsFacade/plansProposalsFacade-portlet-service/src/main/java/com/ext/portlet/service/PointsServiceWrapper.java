package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PointsService}.
 *
 * @author Brian Wing Shun Chan
 * @see PointsService
 * @generated
 */
public class PointsServiceWrapper implements PointsService,
    ServiceWrapper<PointsService> {
    private PointsService _pointsService;

    public PointsServiceWrapper(PointsService pointsService) {
        _pointsService = pointsService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _pointsService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _pointsService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _pointsService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PointsService getWrappedPointsService() {
        return _pointsService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPointsService(PointsService pointsService) {
        _pointsService = pointsService;
    }

    @Override
    public PointsService getWrappedService() {
        return _pointsService;
    }

    @Override
    public void setWrappedService(PointsService pointsService) {
        _pointsService = pointsService;
    }
}
