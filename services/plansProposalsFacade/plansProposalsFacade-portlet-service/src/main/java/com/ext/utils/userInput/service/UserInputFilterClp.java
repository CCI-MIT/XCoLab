package com.ext.utils.userInput.service;

import com.ext.utils.userInput.UserInputFilter;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

public class UserInputFilterClp implements UserInputFilter {
    private ClassLoaderProxy _classLoaderProxy;
    private MethodKey _filterHtml;
    
    public UserInputFilterClp(ClassLoaderProxy classLoaderProxy) {
        _classLoaderProxy = classLoaderProxy;

        _filterHtml = new MethodKey(_classLoaderProxy.getClassName(),
                "filterHtml", String.class);
    }
    
    public String filterHtml(String html) {
        MethodHandler methodHandler = new MethodHandler(_filterHtml, html);
        Object returnObj;
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
        return (String) returnObj;
    }

}
