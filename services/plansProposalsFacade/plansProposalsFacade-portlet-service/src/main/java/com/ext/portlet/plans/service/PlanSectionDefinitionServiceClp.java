package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class PlanSectionDefinitionServiceClp
    implements PlanSectionDefinitionService {
    private ClassLoaderProxy _classLoaderProxy;

    public PlanSectionDefinitionServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
