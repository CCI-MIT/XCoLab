package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class PlanItemPhaseMapServiceClp implements PlanItemPhaseMapService {
    private ClassLoaderProxy _classLoaderProxy;

    public PlanItemPhaseMapServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
