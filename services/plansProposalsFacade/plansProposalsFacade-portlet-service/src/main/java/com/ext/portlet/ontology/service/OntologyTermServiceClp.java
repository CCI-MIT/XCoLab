package com.ext.portlet.ontology.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class OntologyTermServiceClp implements OntologyTermService {
    private ClassLoaderProxy _classLoaderProxy;

    public OntologyTermServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
