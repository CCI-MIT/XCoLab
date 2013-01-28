package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class PlansFilterPositionServiceClp implements PlansFilterPositionService {
    private ClassLoaderProxy _classLoaderProxy;

    public PlansFilterPositionServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
