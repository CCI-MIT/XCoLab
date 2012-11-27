package com.ext.portlet.contests.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class ContestTeamMemberServiceClp implements ContestTeamMemberService {
    private ClassLoaderProxy _classLoaderProxy;

    public ContestTeamMemberServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
