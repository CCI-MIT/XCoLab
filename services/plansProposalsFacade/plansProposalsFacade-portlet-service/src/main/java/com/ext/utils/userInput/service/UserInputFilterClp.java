package com.ext.utils.userInput.service;

import com.ext.utils.userInput.UserInputFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.service.InvokableLocalService;

public class UserInputFilterClp implements UserInputFilter {
    private InvokableLocalService _invokableLocalService;
    private MethodKey _filterHtml;
    private final static Logger _log = LoggerFactory.getLogger(UserInputFilterClp.class);
    
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
