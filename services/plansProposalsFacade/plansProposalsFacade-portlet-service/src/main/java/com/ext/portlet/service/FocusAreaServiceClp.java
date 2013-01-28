package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class FocusAreaServiceClp implements FocusAreaService {
    private ClassLoaderProxy _classLoaderProxy;

    public FocusAreaServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
