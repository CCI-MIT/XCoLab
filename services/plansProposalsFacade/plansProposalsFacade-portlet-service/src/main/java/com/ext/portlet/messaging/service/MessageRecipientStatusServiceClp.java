package com.ext.portlet.messaging.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class MessageRecipientStatusServiceClp
    implements MessageRecipientStatusService {
    private ClassLoaderProxy _classLoaderProxy;

    public MessageRecipientStatusServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
