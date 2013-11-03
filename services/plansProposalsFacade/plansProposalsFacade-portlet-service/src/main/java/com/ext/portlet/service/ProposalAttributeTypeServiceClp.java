package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class ProposalAttributeTypeServiceClp
    implements ProposalAttributeTypeService {
    private ClassLoaderProxy _classLoaderProxy;

    public ProposalAttributeTypeServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
