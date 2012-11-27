package com.ext.portlet.models.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class ModelOutputItemServiceClp implements ModelOutputItemService {
    private ClassLoaderProxy _classLoaderProxy;

    public ModelOutputItemServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
