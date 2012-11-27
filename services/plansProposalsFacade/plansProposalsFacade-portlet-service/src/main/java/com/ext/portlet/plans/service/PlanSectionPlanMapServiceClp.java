package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class PlanSectionPlanMapServiceClp implements PlanSectionPlanMapService {
    private ClassLoaderProxy _classLoaderProxy;

    public PlanSectionPlanMapServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
