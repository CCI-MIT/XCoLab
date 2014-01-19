package com.ext.utils.authentication;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.liferay.faces.util.lang.StringPool;
import com.liferay.portal.kernel.cluster.ClusterExecutorUtil;
import com.liferay.portal.kernel.cluster.ClusterNode;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.DestinationNames;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.CookieKeys;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.model.UserTracker;
import com.liferay.portal.security.auth.AuthException;
import com.liferay.portal.security.auth.AuthenticatedUserUUIDStoreUtil;
import com.liferay.portal.security.auth.Authenticator;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.util.Encryptor;

public class AuthenticationServiceImpl implements AuthenticationService, InvokableLocalService {
	public static final boolean AUTH_SIMULTANEOUS_LOGINS = GetterUtil.getBoolean(PropsUtil.get(PropsKeys.AUTH_SIMULTANEOUS_LOGINS));

	public static String[] SESSION_PHISHING_PROTECTED_ATTRIBUTES = PropsUtil.getArray(PropsKeys.SESSION_PHISHING_PROTECTED_ATTRIBUTES);

	public static final boolean SESSION_ENABLE_PHISHING_PROTECTION = GetterUtil.getBoolean(PropsUtil.get(PropsKeys.SESSION_ENABLE_PHISHING_PROTECTION));

	public static final boolean PORTAL_JAAS_PLAIN_PASSWORD = GetterUtil.getBoolean(PropsUtil.get(PropsKeys.PORTAL_JAAS_PLAIN_PASSWORD));

	public static boolean SESSION_STORE_PASSWORD = GetterUtil.getBoolean(PropsUtil.get(PropsKeys.SESSION_STORE_PASSWORD));

	public static final int COMPANY_SECURITY_AUTO_LOGIN_MAX_AGE = GetterUtil.getInteger(PropsUtil.get(PropsKeys.COMPANY_SECURITY_AUTO_LOGIN_MAX_AGE), CookieKeys.MAX_AGE);
	
	public static final boolean SESSION_DISABLED = GetterUtil.getBoolean(PropsUtil.get(PropsKeys.SESSION_DISABLED));

	public static final boolean COMPANY_SECURITY_AUTH_REQUIRES_HTTPS = GetterUtil.getBoolean(PropsUtil.get(PropsKeys.COMPANY_SECURITY_AUTH_REQUIRES_HTTPS));
	
	public static final String WEB_SERVER_PROTOCOL = "web.server.protocol";

    public void logUserIn(PortletRequest portletRequest, PortletResponse portletResponse, String username, String password) throws Exception {

		System.out.println("mam logUserIn");
    	AuthenticationServiceImpl.login(PortalUtil.getHttpServletRequest(portletRequest), 
    			PortalUtil.getHttpServletResponse(portletResponse), username, password, true, null);
    	System.out.println("Authentication service impl.logUserIn");
    	//Class loginUtilClass = getClass().getClassLoader().loadClass("com.liferay.portlet.login.util.LoginUtil");
    	//bSystem.out.println(loginUtilClass);
    	/*
        MethodKey key = new MethodKey("com.liferay.portlet.login.util.LoginUtil", "login", HttpServletRequest.class, 
                HttpServletResponse.class, String.class, String.class, boolean.class, String.class);
        PortalClassInvoker.invoke(false, key, new Object[] { PortalUtil.getHttpServletRequest(portletRequest), 
                PortalUtil.getHttpServletResponse(portletResponse), username, password, true, null});
                */
    }
    
    public void sendPassword(ActionRequest request, String emailFromName, String emailFromAddress, 
            String emailToAddress, String subject, String body) throws Exception {
    	/*
        MethodKey key = new MethodKey("com.liferay.portlet.login.util.LoginUtil", "sendPassword", ActionRequest.class, 
                String.class, String.class, String.class, String.class, String.class);

        PortalClassInvoker.invoke(false, key, 
                new Object[] {request, emailFromName, emailFromAddress, emailToAddress, subject, body});
                */
    }

	@Override
	public Object invokeMethod(String name, String[] parameterTypes,
			Object[] arguments) throws Throwable {
		System.out.println("mam invoke method");
		
		Class[] parameterTypesClass = new Class[parameterTypes.length];
		for (int i=0; i < parameterTypes.length; i++) {
			parameterTypesClass[i] = getClass().getClassLoader().loadClass(parameterTypes[i]);
		}
		
		Method m = this.getClass().getMethod(name, parameterTypesClass);
		System.out.println(m);
		Object ret = m.invoke(this, arguments);
		// TODO Auto-generated method stub
		return ret;
	}


	public static void login(
			HttpServletRequest request, HttpServletResponse response,
			String login, String password, boolean rememberMe, String authType)
		throws Exception {

		CookieKeys.validateSupportCookie(request);

		HttpSession session = request.getSession();

		Company company = PortalUtil.getCompany(request);

		long userId = getAuthenticatedUserId(
			request, login, password, authType);
		
		/*
		if (!AUTH_SIMULTANEOUS_LOGINS) {
			Map<String, UserTracker> sessionUsers = LiveUsers.getSessionUsers(
				company.getCompanyId());

			List<UserTracker> userTrackers = new ArrayList<UserTracker>(
				sessionUsers.values());

			for (UserTracker userTracker : userTrackers) {
				if (userId != userTracker.getUserId()) {
					continue;
				}

				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

				ClusterNode clusterNode =
					ClusterExecutorUtil.getLocalClusterNode();

				if (clusterNode != null) {
					jsonObject.put(
						"clusterNodeId", clusterNode.getClusterNodeId());
				}

				jsonObject.put("command", "signOut");

				long companyId = CompanyLocalServiceUtil.getCompanyIdByUserId(
					userId);

				jsonObject.put("companyId", companyId);
				jsonObject.put("sessionId", userTracker.getSessionId());
				jsonObject.put("userId", userId);

				MessageBusUtil.sendMessage(
					DestinationNames.LIVE_USERS, jsonObject.toString());
			}
		}
		*/

		if (SESSION_ENABLE_PHISHING_PROTECTION) {
			session = renewSession(request, session);
		}

		// Set cookies

		String domain = CookieKeys.getDomain(request);

		User user = UserLocalServiceUtil.getUserById(userId);

		String userIdString = String.valueOf(userId);

		session.setAttribute("j_username", userIdString);

		if (PORTAL_JAAS_PLAIN_PASSWORD) {
			session.setAttribute("j_password", password);
		}
		else {
			session.setAttribute("j_password", user.getPassword());
		}

		session.setAttribute("j_remoteuser", userIdString);

		if (SESSION_STORE_PASSWORD) {
			session.setAttribute(WebKeys.USER_PASSWORD, password);
		}

		Cookie companyIdCookie = new Cookie(
			CookieKeys.COMPANY_ID, String.valueOf(company.getCompanyId()));

		if (Validator.isNotNull(domain)) {
			companyIdCookie.setDomain(domain);
		}

		companyIdCookie.setPath(StringPool.SLASH);

		Cookie idCookie = new Cookie(
			CookieKeys.ID,
			Encryptor.encrypt(company.getKeyObj(), userIdString));

		if (Validator.isNotNull(domain)) {
			idCookie.setDomain(domain);
		}

		idCookie.setPath(StringPool.SLASH);

		Cookie passwordCookie = new Cookie(
			CookieKeys.PASSWORD,
			Encryptor.encrypt(company.getKeyObj(), password));

		if (Validator.isNotNull(domain)) {
			passwordCookie.setDomain(domain);
		}

		passwordCookie.setPath(StringPool.SLASH);

		Cookie rememberMeCookie = new Cookie(
			CookieKeys.REMEMBER_ME, Boolean.TRUE.toString());

		if (Validator.isNotNull(domain)) {
			rememberMeCookie.setDomain(domain);
		}

		rememberMeCookie.setPath(StringPool.SLASH);

		int loginMaxAge = COMPANY_SECURITY_AUTO_LOGIN_MAX_AGE;

		String userUUID = userIdString.concat(StringPool.PERIOD).concat(
			String.valueOf(System.nanoTime()));

		Cookie userUUIDCookie = new Cookie(
			CookieKeys.USER_UUID,
			Encryptor.encrypt(company.getKeyObj(), userUUID));

		userUUIDCookie.setPath(StringPool.SLASH);

		session.setAttribute(WebKeys.USER_UUID, userUUID);

		if (SESSION_DISABLED) {
			rememberMe = true;
		}

		if (rememberMe) {
			companyIdCookie.setMaxAge(loginMaxAge);
			idCookie.setMaxAge(loginMaxAge);
			passwordCookie.setMaxAge(loginMaxAge);
			rememberMeCookie.setMaxAge(loginMaxAge);
			userUUIDCookie.setMaxAge(loginMaxAge);
		}
		else {

			// This was explicitly changed from 0 to -1 so that the cookie lasts
			// as long as the browser. This allows an external servlet wrapped
			// in AutoLoginFilter to work throughout the client connection. The
			// cookies ARE removed on an actual logout, so there is no security
			// issue. See LEP-4678 and LEP-5177.

			companyIdCookie.setMaxAge(-1);
			idCookie.setMaxAge(-1);
			passwordCookie.setMaxAge(-1);
			rememberMeCookie.setMaxAge(0);
			userUUIDCookie.setMaxAge(-1);
		}

		Cookie loginCookie = new Cookie(CookieKeys.LOGIN, login);

		if (Validator.isNotNull(domain)) {
			loginCookie.setDomain(domain);
		}

		loginCookie.setMaxAge(loginMaxAge);
		loginCookie.setPath(StringPool.SLASH);

		Cookie screenNameCookie = new Cookie(
			CookieKeys.SCREEN_NAME,
			Encryptor.encrypt(company.getKeyObj(), user.getScreenName()));

		if (Validator.isNotNull(domain)) {
			screenNameCookie.setDomain(domain);
		}

		screenNameCookie.setMaxAge(loginMaxAge);
		screenNameCookie.setPath(StringPool.SLASH);

		boolean secure = request.isSecure();

		if (secure && !COMPANY_SECURITY_AUTH_REQUIRES_HTTPS &&
			!StringUtil.equalsIgnoreCase(
				Http.HTTPS, WEB_SERVER_PROTOCOL)) {

			Boolean httpsInitial = (Boolean)session.getAttribute("HTTPS_INITIAL");

			if ((httpsInitial == null) || !httpsInitial.booleanValue()) {
				secure = false;
			}
		}

		CookieKeys.addCookie(request, response, companyIdCookie, secure);
		CookieKeys.addCookie(request, response, idCookie, secure);
		CookieKeys.addCookie(request, response, userUUIDCookie, secure);

		if (rememberMe) {
			CookieKeys.addCookie(request, response, loginCookie, secure);
			CookieKeys.addCookie(request, response, passwordCookie, secure);
			CookieKeys.addCookie(request, response, rememberMeCookie, secure);
			CookieKeys.addCookie(request, response, screenNameCookie, secure);
		}

		AuthenticatedUserUUIDStoreUtil.register(userUUID);
	}
	

	public static long getAuthenticatedUserId(
			HttpServletRequest request, String login, String password,
			String authType)
		throws PortalException, SystemException {

		long userId = GetterUtil.getLong(login);

		Company company = PortalUtil.getCompany(request);

		String requestURI = request.getRequestURI();

		String contextPath = PortalUtil.getPathContext();

		if (requestURI.startsWith(contextPath.concat("/api/liferay"))) {
			throw new AuthException();
		}
		else {
			Map<String, String[]> headerMap = new HashMap<String, String[]>();

			Enumeration<String> enu1 = request.getHeaderNames();

			while (enu1.hasMoreElements()) {
				String name = enu1.nextElement();

				Enumeration<String> enu2 = request.getHeaders(name);

				List<String> headers = new ArrayList<String>();

				while (enu2.hasMoreElements()) {
					String value = enu2.nextElement();

					headers.add(value);
				}

				headerMap.put(
					name, headers.toArray(new String[headers.size()]));
			}

			Map<String, String[]> parameterMap = request.getParameterMap();
			Map<String, Object> resultsMap = new HashMap<String, Object>();

			if (Validator.isNull(authType)) {
				authType = company.getAuthType();
			}

			int authResult = Authenticator.FAILURE;

			if (authType.equals(CompanyConstants.AUTH_TYPE_EA)) {
				authResult = UserLocalServiceUtil.authenticateByEmailAddress(
					company.getCompanyId(), login, password, headerMap,
					parameterMap, resultsMap);

				userId = MapUtil.getLong(resultsMap, "userId", userId);
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_SN)) {
				authResult = UserLocalServiceUtil.authenticateByScreenName(
					company.getCompanyId(), login, password, headerMap,
					parameterMap, resultsMap);

				userId = MapUtil.getLong(resultsMap, "userId", userId);
			}
			else if (authType.equals(CompanyConstants.AUTH_TYPE_ID)) {
				authResult = UserLocalServiceUtil.authenticateByUserId(
					company.getCompanyId(), userId, password, headerMap,
					parameterMap, resultsMap);
			}

			if (authResult != Authenticator.SUCCESS) {
				throw new AuthException();
			}
		}

		return userId;
	}
	

	public static HttpSession renewSession(
			HttpServletRequest request, HttpSession session)
		throws Exception {

		// Invalidate the previous session to prevent phishing

		String[] protectedAttributeNames =
			SESSION_PHISHING_PROTECTED_ATTRIBUTES;

		Map<String, Object> protectedAttributes = new HashMap<String, Object>();

		for (String protectedAttributeName : protectedAttributeNames) {
			Object protectedAttributeValue = session.getAttribute(
				protectedAttributeName);

			if (protectedAttributeValue == null) {
				continue;
			}

			protectedAttributes.put(
				protectedAttributeName, protectedAttributeValue);
		}

		try {
			session.invalidate();
		}
		catch (IllegalStateException ise) {

			// This only happens in Geronimo

			if (_log.isWarnEnabled()) {
				_log.warn(ise.getMessage());
			}
		}

		session = request.getSession(true);

		for (String protectedAttributeName : protectedAttributeNames) {
			Object protectedAttributeValue = protectedAttributes.get(
				protectedAttributeName);

			if (protectedAttributeValue == null) {
				continue;
			}

			session.setAttribute(
				protectedAttributeName, protectedAttributeValue);
		}

		return session;
	}
	
	private final static Log _log = LogFactoryUtil.getLog(AuthenticationServiceImpl.class);
}
