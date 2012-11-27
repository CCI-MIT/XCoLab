package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class PlanFanServiceClp implements PlanFanService {
    private ClassLoaderProxy _classLoaderProxy;

    public PlanFanServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
