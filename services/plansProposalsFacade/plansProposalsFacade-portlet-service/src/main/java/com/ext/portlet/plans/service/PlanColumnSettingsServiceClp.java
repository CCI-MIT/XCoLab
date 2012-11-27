package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class PlanColumnSettingsServiceClp implements PlanColumnSettingsService {
    private ClassLoaderProxy _classLoaderProxy;

    public PlanColumnSettingsServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
