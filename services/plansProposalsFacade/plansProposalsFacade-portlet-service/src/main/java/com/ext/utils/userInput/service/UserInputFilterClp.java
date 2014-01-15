package com.ext.utils.userInput.service;

import com.ext.utils.userInput.UserInputFilter;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.service.InvokableLocalService;

public class UserInputFilterClp implements UserInputFilter {
    private InvokableLocalService _invokableLocalService;
    private MethodKey _filterHtml;
    private final static Log _log = LogFactoryUtil.getLog(UserInputFilterClp.class);
    
    public UserInputFilterClp(InvokableLocalService invokableLocalService) {
    	_invokableLocalService = invokableLocalService;
    }
    
    public String filterHtml(String html) {
        Object returnObj;
        try {
            returnObj = _invokableLocalService.invokeMethod("filterHtml", new String[] {"java.lang.String"}, new Object[] { html });
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
            	_log.error("Can't invoke filterHtml", t);
                throw (RuntimeException) t;
            } else {
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
        return (String) returnObj;
    }

}
