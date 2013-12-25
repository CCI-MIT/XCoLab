package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class AnalyticsUserEventServiceClp implements AnalyticsUserEventService {
    private ClassLoaderProxy _classLoaderProxy;

    public AnalyticsUserEventServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
