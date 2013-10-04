package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class ContestPhaseRibbonTypeServiceClp
    implements ContestPhaseRibbonTypeService {
    private ClassLoaderProxy _classLoaderProxy;

    public ContestPhaseRibbonTypeServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
