package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class ContestPhaseTypeServiceClp implements ContestPhaseTypeService {
    private ClassLoaderProxy _classLoaderProxy;

    public ContestPhaseTypeServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
