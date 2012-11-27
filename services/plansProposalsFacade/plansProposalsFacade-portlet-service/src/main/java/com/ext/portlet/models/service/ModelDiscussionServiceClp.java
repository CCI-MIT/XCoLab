package com.ext.portlet.models.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class ModelDiscussionServiceClp implements ModelDiscussionService {
    private ClassLoaderProxy _classLoaderProxy;

    public ModelDiscussionServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
