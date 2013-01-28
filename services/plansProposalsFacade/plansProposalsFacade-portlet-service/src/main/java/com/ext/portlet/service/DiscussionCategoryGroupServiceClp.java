package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class DiscussionCategoryGroupServiceClp
    implements DiscussionCategoryGroupService {
    private ClassLoaderProxy _classLoaderProxy;

    public DiscussionCategoryGroupServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
