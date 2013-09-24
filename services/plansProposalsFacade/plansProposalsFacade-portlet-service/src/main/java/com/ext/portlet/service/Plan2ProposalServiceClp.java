package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class Plan2ProposalServiceClp implements Plan2ProposalService {
    private ClassLoaderProxy _classLoaderProxy;

    public Plan2ProposalServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
