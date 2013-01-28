package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class OntologyTermEntityServiceClp implements OntologyTermEntityService {
    private ClassLoaderProxy _classLoaderProxy;

    public OntologyTermEntityServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
