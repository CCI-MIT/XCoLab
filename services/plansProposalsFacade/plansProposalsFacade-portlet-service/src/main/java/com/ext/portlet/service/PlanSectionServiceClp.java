package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class PlanSectionServiceClp implements PlanSectionService {
    private ClassLoaderProxy _classLoaderProxy;

    public PlanSectionServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
