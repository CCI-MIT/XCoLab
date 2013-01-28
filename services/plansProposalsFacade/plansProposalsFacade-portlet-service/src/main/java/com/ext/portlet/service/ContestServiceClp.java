package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class ContestServiceClp implements ContestService {
    private ClassLoaderProxy _classLoaderProxy;

    public ContestServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
