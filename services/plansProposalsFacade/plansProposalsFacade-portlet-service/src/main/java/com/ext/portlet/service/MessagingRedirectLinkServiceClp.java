package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class MessagingRedirectLinkServiceClp
    implements MessagingRedirectLinkService {
    private ClassLoaderProxy _classLoaderProxy;

    public MessagingRedirectLinkServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
