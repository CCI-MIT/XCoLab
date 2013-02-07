package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class MessagingMessageServiceClp implements MessagingMessageService {
    private ClassLoaderProxy _classLoaderProxy;

    public MessagingMessageServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
