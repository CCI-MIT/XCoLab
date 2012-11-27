package com.ext.portlet.ontology.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class OntologySpaceServiceClp implements OntologySpaceService {
    private ClassLoaderProxy _classLoaderProxy;

    public OntologySpaceServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
