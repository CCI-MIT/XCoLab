package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class ProposalContestPhaseAttributeServiceClp
    implements ProposalContestPhaseAttributeService {
    private ClassLoaderProxy _classLoaderProxy;

    public ProposalContestPhaseAttributeServiceClp(
        ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
