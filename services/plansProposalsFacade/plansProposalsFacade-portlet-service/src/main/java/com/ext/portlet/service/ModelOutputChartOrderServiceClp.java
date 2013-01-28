package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class ModelOutputChartOrderServiceClp
    implements ModelOutputChartOrderService {
    private ClassLoaderProxy _classLoaderProxy;

    public ModelOutputChartOrderServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
