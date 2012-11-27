package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class PlanPropertyFilterServiceClp implements PlanPropertyFilterService {
    private ClassLoaderProxy _classLoaderProxy;

    public PlanPropertyFilterServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
