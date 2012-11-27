package com.ext.portlet.ontology.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class FocusAreaOntologyTermServiceClp
    implements FocusAreaOntologyTermService {
    private ClassLoaderProxy _classLoaderProxy;

    public FocusAreaOntologyTermServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
