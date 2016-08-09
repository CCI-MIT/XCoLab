package com.ext.utils.authentication;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

public interface AuthenticationService {

    void logUserIn(PortletRequest portletRequest, PortletResponse portletResponse,
            String username, String password) throws Exception;
    void sendPassword(PortletRequest request, String emailFromName, String emailFromAddress,
            String emailToAddress, String subject, String body) throws Exception;
}
