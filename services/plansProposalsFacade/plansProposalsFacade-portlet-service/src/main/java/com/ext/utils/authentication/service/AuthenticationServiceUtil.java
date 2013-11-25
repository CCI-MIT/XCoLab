package com.ext.utils.authentication.service;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import com.ext.portlet.service.ClpSerializer;
import com.ext.utils.authentication.AuthenticationService;
import com.ext.utils.userInput.UserInputFilter;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

public class AuthenticationServiceUtil {
    private static AuthenticationService _service;
    

    public void setService(AuthenticationService service) {
    }

    public static void logUserIn(PortletRequest portletRequest,
			PortletResponse portletResponse, String username, String password) throws PortalException {
    	try {
    		getService().logUserIn(portletRequest, portletResponse, username, password);
    	}
    	catch (Exception e) {
    		throw new PortalException("Can't log user in", e);
    	}
    }
    
    public static void clearService() {
        _service = null;
    }

    public static AuthenticationService getService() {
        if (_service == null) {
        	Object tmp = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
            		AuthenticationService.class.getName());
        	System.out.println(tmp.getClass());
        	System.out.println(AuthenticationService.class.isAssignableFrom(tmp.getClass()));
        	System.out.println(tmp instanceof AuthenticationService);
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
