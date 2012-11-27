package com.ext.portlet.models.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class ModelInputGroupServiceClp implements ModelInputGroupService {
    private ClassLoaderProxy _classLoaderProxy;

    public ModelInputGroupServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
