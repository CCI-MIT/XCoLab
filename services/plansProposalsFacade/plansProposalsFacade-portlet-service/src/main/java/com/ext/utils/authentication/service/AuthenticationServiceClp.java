package com.ext.utils.authentication.service;

import java.lang.reflect.InvocationTargetException;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import com.ext.utils.authentication.AuthenticationService;
import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.security.auth.AuthException;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.portal.service.PersistedModelLocalServiceRegistryUtil;

public class AuthenticationServiceClp implements AuthenticationService, InvokableLocalService {
    private InvokableLocalService _invokableLocalService;
    private MethodKey logUserIn;
    private ClassLoader _classLoader;
    
    public AuthenticationServiceClp(InvokableLocalService invokableLocalService) {
    	_invokableLocalService = invokableLocalService;
    }
    
    
	@Override
	public void logUserIn(PortletRequest portletRequest,
			PortletResponse portletResponse, String username, String password)
			throws Exception {
        try {
            _invokableLocalService.invokeMethod("logUserIn", 
            		new String[] {"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
            			"java.lang.String", "java.lang.String"
            		}, new Object[] { portletRequest, portletResponse, username, password });
            
        } catch (Throwable t) {
        	if (t instanceof InvocationTargetException) {
        		t = t.getCause();
        	}
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            }
            if (t instanceof AuthException) {
            	throw (AuthException) t;
            }
            if (t instanceof Exception) {
            	throw (Exception) t;
            }
            
        }
		
	}


	@Override
	public void sendPassword(PortletRequest request, String emailFromName,
			String emailFromAddress, String emailToAddress, String subject,
			String body) throws Exception {
        try {
            _invokableLocalService.invokeMethod("sendPassword", 
            		new String[] {"javax.portlet.PortletRequest", "java.lang.String",
            			"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String"
            		}, new Object[] { request, emailFromName, emailFromAddress, emailToAddress, 
            		subject, body });
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            }
        }
		
	}

    @Override
    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
    	
        Thread currentThread = Thread.currentThread();

        ClassLoader contextClassLoader = currentThread.getContextClassLoader();

        if (contextClassLoader != _classLoader) {
            currentThread.setContextClassLoader(_classLoader);
        }

        try {
            return _invokableLocalService.invokeMethod(name, parameterTypes, arguments);
        } finally {
            if (contextClassLoader != _classLoader) {
                currentThread.setContextClassLoader(contextClassLoader);
            }
        }
    }
    
    public void afterPropertiesSet() {
        Class<?> clazz = getClass();

        _classLoader = clazz.getClassLoader();
    }

    public void destroy() {
        PersistedModelLocalServiceRegistryUtil.unregister(
            "com.ext.portlet.model.Proposal");
    }


}
