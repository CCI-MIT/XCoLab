package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class ProposalContestPhaseAttributeTypeServiceClp
    implements ProposalContestPhaseAttributeTypeService {
    private ClassLoaderProxy _classLoaderProxy;

    public ProposalContestPhaseAttributeTypeServiceClp(
        ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
