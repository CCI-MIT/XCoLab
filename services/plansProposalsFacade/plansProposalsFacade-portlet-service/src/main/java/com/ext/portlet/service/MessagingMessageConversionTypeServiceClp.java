package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class MessagingMessageConversionTypeServiceClp
    implements MessagingMessageConversionTypeService {
    private ClassLoaderProxy _classLoaderProxy;

    public MessagingMessageConversionTypeServiceClp(
        ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
