package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class PlanServiceClp implements PlanService {
    private ClassLoaderProxy _classLoaderProxy;

    public PlanServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
