package com.ext.portlet.models.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;


public class ModelCategoryServiceClp implements ModelCategoryService {
    private ClassLoaderProxy _classLoaderProxy;

    public ModelCategoryServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
