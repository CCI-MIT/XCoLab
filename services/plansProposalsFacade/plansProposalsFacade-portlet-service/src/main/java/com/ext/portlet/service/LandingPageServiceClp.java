package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class LandingPageServiceClp implements LandingPageService {
    private ClassLoaderProxy _classLoaderProxy;

    public LandingPageServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
