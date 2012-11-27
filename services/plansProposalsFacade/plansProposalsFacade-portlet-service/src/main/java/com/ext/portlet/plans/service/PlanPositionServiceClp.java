package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class PlanPositionServiceClp implements PlanPositionService {
    private ClassLoaderProxy _classLoaderProxy;

    public PlanPositionServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
