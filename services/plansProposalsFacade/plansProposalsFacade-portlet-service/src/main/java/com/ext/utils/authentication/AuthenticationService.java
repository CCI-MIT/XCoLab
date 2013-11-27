package com.ext.utils.authentication;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

public interface AuthenticationService {

    public void logUserIn(PortletRequest portletRequest, PortletResponse portletResponse,
    		String username, String password) throws Exception;
    public void sendPassword(PortletRequest request, String emailFromName, String emailFromAddress, 
            String emailToAddress, String subject, String body) throws Exception;
}
