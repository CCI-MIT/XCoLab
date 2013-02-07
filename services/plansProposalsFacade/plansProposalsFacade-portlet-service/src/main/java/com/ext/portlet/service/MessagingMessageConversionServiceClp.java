package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class MessagingMessageConversionServiceClp
    implements MessagingMessageConversionService {
    private ClassLoaderProxy _classLoaderProxy;

    public MessagingMessageConversionServiceClp(
        ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
