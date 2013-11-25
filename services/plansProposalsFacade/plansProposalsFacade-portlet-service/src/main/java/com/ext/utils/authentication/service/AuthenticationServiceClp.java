package com.ext.utils.authentication.service;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import com.ext.utils.authentication.AuthenticationService;
import com.liferay.portal.kernel.util.MethodKey;
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
        	System.out.println("mam logUserIn w clp");
            _invokableLocalService.invokeMethod("logUserIn", 
            		new String[] {"javax.portlet.PortletRequest", "javax.portlet.PortletResponse",
            			"java.lang.String", "java.lang.String"
            		}, new Object[] { portletRequest, portletResponse, username, password });
        } catch (Throwable t) {
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
            	t.printStackTrace();
                throw new RuntimeException(t.getClass().getName() +
                    " is not a valid exception");
            }
        }
		
	}


	@Override
	public void sendPassword(ActionRequest request, String emailFromName,
			String emailFromAddress, String emailToAddress, String subject,
			String body) throws Exception {
		// TODO Auto-generated method stub
		
	}

    @Override
    public Object invokeMethod(String name, String[] parameterTypes,
        Object[] arguments) throws Throwable {
    	
    	System.out.println("mam invoke method w clp");
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
