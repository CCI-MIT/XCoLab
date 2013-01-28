package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class PlanTemplateSectionServiceClp implements PlanTemplateSectionService {
    private ClassLoaderProxy _classLoaderProxy;

    public PlanTemplateSectionServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
