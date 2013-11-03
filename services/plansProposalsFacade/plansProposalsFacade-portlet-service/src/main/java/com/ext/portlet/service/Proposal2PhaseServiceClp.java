package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class Proposal2PhaseServiceClp implements Proposal2PhaseService {
    private ClassLoaderProxy _classLoaderProxy;

    public Proposal2PhaseServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
