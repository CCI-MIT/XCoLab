package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class BalloonStatsEntryServiceClp implements BalloonStatsEntryService {
    private ClassLoaderProxy _classLoaderProxy;

    public BalloonStatsEntryServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
