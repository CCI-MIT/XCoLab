package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class PlanTypeServiceClp implements PlanTypeService {
    private ClassLoaderProxy _classLoaderProxy;

    public PlanTypeServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
