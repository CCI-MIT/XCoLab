package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class MessagingMessageRecipientServiceClp
    implements MessagingMessageRecipientService {
    private ClassLoaderProxy _classLoaderProxy;

    public MessagingMessageRecipientServiceClp(
        ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
