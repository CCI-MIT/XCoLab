package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class PlanPositionItemServiceClp implements PlanPositionItemService {
    private ClassLoaderProxy _classLoaderProxy;

    public PlanPositionItemServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
