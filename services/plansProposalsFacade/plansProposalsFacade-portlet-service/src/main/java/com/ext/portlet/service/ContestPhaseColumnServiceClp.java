package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class ContestPhaseColumnServiceClp implements ContestPhaseColumnService {
    private ClassLoaderProxy _classLoaderProxy;

    public ContestPhaseColumnServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
