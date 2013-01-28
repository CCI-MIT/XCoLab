package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class ActivitySubscriptionServiceClp
    implements ActivitySubscriptionService {
    private ClassLoaderProxy _classLoaderProxy;

    public ActivitySubscriptionServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
