package com.ext.portlet.contests.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class ContestDebateServiceClp implements ContestDebateService {
    private ClassLoaderProxy _classLoaderProxy;

    public ContestDebateServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
