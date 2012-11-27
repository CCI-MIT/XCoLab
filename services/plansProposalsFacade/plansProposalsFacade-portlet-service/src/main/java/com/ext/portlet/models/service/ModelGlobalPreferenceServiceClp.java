package com.ext.portlet.models.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class ModelGlobalPreferenceServiceClp
    implements ModelGlobalPreferenceService {
    private ClassLoaderProxy _classLoaderProxy;

    public ModelGlobalPreferenceServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
