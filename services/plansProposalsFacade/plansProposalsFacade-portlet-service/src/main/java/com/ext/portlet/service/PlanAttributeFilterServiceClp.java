package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class PlanAttributeFilterServiceClp implements PlanAttributeFilterService {
    private ClassLoaderProxy _classLoaderProxy;

    public PlanAttributeFilterServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
