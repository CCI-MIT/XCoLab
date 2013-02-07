package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class MessagingIgnoredRecipientsServiceClp
    implements MessagingIgnoredRecipientsService {
    private ClassLoaderProxy _classLoaderProxy;

    public MessagingIgnoredRecipientsServiceClp(
        ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
