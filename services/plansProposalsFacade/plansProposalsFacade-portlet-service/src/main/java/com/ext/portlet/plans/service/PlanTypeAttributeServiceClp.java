package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class PlanTypeAttributeServiceClp implements PlanTypeAttributeService {
    private ClassLoaderProxy _classLoaderProxy;

    public PlanTypeAttributeServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
