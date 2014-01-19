package org.xcolab.portlets.loginregister;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.kernel.util.PortletClassInvoker;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.util.PortalUtil;

public class LoginUtil {
	private final static String LOGIN_PORTLET_ID = "58";

    public static void logUserIn(PortletRequest portletRequest, PortletResponse portletResponse, String username, String password) throws Exception {
        MethodKey key = new MethodKey("com.liferay.portlet.login.util.LoginUtil", "login", HttpServletRequest.class, 
                HttpServletResponse.class, String.class, String.class, boolean.class, String.class);
        PortletClassInvoker.invoke(false, LOGIN_PORTLET_ID, key, new Object[] { PortalUtil.getHttpServletRequest(portletRequest), 
                PortalUtil.getHttpServletResponse(portletResponse), username, password, true, null});
    }
    
    public static void sendPassword(ActionRequest request, String emailFromName, String emailFromAddress, 
            String emailToAddress, String subject, String body) throws Exception {
        MethodKey key = new MethodKey("com.liferay.portlet.login.util.LoginUtil", "sendPassword", ActionRequest.class, 
                String.class, String.class, String.class, String.class, String.class);

        PortalClassInvoker.invoke(false, key, 
                new Object[] {request, emailFromName, emailFromAddress, emailToAddress, subject, body});
    }
}
