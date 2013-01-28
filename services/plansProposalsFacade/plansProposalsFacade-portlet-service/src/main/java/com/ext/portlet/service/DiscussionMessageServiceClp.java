package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class DiscussionMessageServiceClp implements DiscussionMessageService {
    private ClassLoaderProxy _classLoaderProxy;

    public DiscussionMessageServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
