package org.xcolab.portlets.registration;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.portal.kernel.util.MethodKey;
import com.liferay.portal.kernel.util.PortalClassInvoker;
import com.liferay.portal.util.PortalUtil;

public class LoginUtil {

	public static void logUserIn(PortletRequest portletRequest,
			PortletResponse portletResponse, String username, String password)
			throws Exception {
		MethodKey key = new MethodKey(
				"com.liferay.portlet.login.util.LoginUtil", "login",
				HttpServletRequest.class, HttpServletResponse.class,
				String.class, String.class, boolean.class, String.class);
		PortalClassInvoker.invoke(false, key,
				new Object[] {
						PortalUtil.getHttpServletRequest(portletRequest),
						PortalUtil.getHttpServletResponse(portletResponse),
						username, password, false, null });
	}
}
