package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class MessagingUserPreferencesServiceClp
    implements MessagingUserPreferencesService {
    private ClassLoaderProxy _classLoaderProxy;

    public MessagingUserPreferencesServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
