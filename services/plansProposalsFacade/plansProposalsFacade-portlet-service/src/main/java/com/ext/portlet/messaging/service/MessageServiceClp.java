package com.ext.portlet.messaging.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class MessageServiceClp implements MessageService {
    private ClassLoaderProxy _classLoaderProxy;

    public MessageServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
