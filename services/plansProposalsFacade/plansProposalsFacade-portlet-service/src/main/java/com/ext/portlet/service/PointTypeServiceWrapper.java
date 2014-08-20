package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PointTypeService}.
 *
 * @author Brian Wing Shun Chan
 * @see PointTypeService
 * @generated
 */
public class PointTypeServiceWrapper implements PointTypeService,
    ServiceWrapper<PointTypeService> {
    private PointTypeService _pointTypeService;

    public PointTypeServiceWrapper(PointTypeService pointTypeService) {
        _pointTypeService = pointTypeService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _pointTypeService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _pointTypeService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _pointTypeService.invokeMethod(name, parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PointTypeService getWrappedPointTypeService() {
        return _pointTypeService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPointTypeService(PointTypeService pointTypeService) {
        _pointTypeService = pointTypeService;
    }

    @Override
    public PointTypeService getWrappedService() {
        return _pointTypeService;
    }

    @Override
    public void setWrappedService(PointTypeService pointTypeService) {
        _pointTypeService = pointTypeService;
    }
}
