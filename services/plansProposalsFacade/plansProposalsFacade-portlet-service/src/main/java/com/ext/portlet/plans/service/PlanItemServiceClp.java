package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class PlanItemServiceClp implements PlanItemService {
    private ClassLoaderProxy _classLoaderProxy;

    public PlanItemServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
