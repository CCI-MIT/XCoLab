package com.ext.utils.authentication.service;

import com.ext.utils.authentication.AuthenticationService;

import com.ext.portlet.service.ClpSerializer;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

public class AuthenticationServiceUtil {
    private static AuthenticationService _service;
    

    public void setService(AuthenticationService service) {
    }

    public static void logUserIn(PortletRequest portletRequest,
			PortletResponse portletResponse, String username, String password) throws Exception {
    	
    	getService().logUserIn(portletRequest, portletResponse, username, password);
    }
    public static void sendPassword(PortletRequest request, String emailFromName, String emailFromAddress, 
            String emailToAddress, String subject, String body) throws Exception {
    	
    	getService().sendPassword(request, emailFromName, emailFromAddress, emailToAddress, subject, body);
    }
    
    public static void clearService() {
        _service = null;
    }

    public static AuthenticationService getService() {
        if (_service == null) {
        	Object tmp = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
            		AuthenticationService.class.getName());
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
            		AuthenticationService.class.getName());

            if (invokableLocalService instanceof AuthenticationService) {
                _service = (AuthenticationService) invokableLocalService;
            } else {
            	_service = new AuthenticationServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(AuthenticationServiceUtil.class,
                "_service");
        }

        return _service;
    }

}
