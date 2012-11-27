package com.ext.portlet.discussions.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class DiscussionMessageFlagServiceClp
    implements DiscussionMessageFlagService {
    private ClassLoaderProxy _classLoaderProxy;

    public DiscussionMessageFlagServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
