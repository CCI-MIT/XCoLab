package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class DiscussionCategoryServiceClp implements DiscussionCategoryService {
    private ClassLoaderProxy _classLoaderProxy;

    public DiscussionCategoryServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
