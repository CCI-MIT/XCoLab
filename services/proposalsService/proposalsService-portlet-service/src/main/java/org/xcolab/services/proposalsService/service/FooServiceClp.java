package org.xcolab.services.proposalsService.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class FooServiceClp implements FooService {
    private ClassLoaderProxy _classLoaderProxy;

    public FooServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
