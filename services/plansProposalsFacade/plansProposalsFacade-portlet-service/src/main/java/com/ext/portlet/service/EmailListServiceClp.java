package com.ext.portlet.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;


public class EmailListServiceClp implements EmailListService {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _helloWorldMethodKey0;

    public EmailListServiceClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _helloWorldMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
                "helloWorld", java.lang.String.class, java.lang.String.class);
    }

    public java.lang.String helloWorld(java.lang.String listName,
        java.lang.String email) {
        Object returnObj = null;

        MethodHandler methodHandler = new MethodHandler(_helloWorldMethodKey0,
                ClpSerializer.translateInput(listName),
                ClpSerializer.translateInput(email));

        try {
            returnObj = _classLoaderProxy.invoke(methodHandler);
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }

        return (java.lang.String) ClpSerializer.translateOutput(returnObj);
    }

    public ClassLoaderProxy getClassLoaderProxy() {
        return _classLoaderProxy;
    }
}
