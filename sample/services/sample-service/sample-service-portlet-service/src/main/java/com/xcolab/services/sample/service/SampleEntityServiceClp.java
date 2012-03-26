package com.xcolab.services.sample.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class SampleEntityServiceClp implements SampleEntityService {
    private ClassLoaderProxy _classLoaderProxy;

    public SampleEntityServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
