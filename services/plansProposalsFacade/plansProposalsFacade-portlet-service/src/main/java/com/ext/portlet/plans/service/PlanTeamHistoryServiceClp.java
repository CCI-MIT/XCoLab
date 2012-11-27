package com.ext.portlet.plans.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class PlanTeamHistoryServiceClp implements PlanTeamHistoryService {
    private ClassLoaderProxy _classLoaderProxy;

    public PlanTeamHistoryServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
