package org.xcolab.hooks.climatecolab.strutsaction;

import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.MessageListenerException;
import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kmang on 18/04/14.
 */
public class SchedulerDispatchStrutsAction extends BaseStrutsAction {

	private static final Log _log = LogFactoryUtil.getLog(SchedulerDispatchStrutsAction.class);

	private static final String LOCAL_IPv4_ADDRESS = "127.0.0.1";

	private static final String LOCAL_IPv6_ADDRESS = "0:0:0:0:0:0:0:1";

	public String execute(
			HttpServletRequest request, HttpServletResponse response) throws MessageListenerException {

		String clientIp = request.getRemoteAddr();
		if (!(clientIp.equals(LOCAL_IPv4_ADDRESS) || clientIp.equals(LOCAL_IPv6_ADDRESS))) {
			_log.warn(String.format("Denied request from address %s!", clientIp));
			return null;
		}

		ServiceContext serviceContext = new ServiceContext();
		serviceContext.setRequest(request);
        serviceContext.setPortalURL(((ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY)).getPortalURL());

		try {
			ActivitySubscriptionLocalServiceUtil.sendEmailNotifications(serviceContext);
		}
		catch (Exception e) {
			throw new MessageListenerException("Could not process email notification of proposal subscription feature", e);
		}

		return StringPool.BLANK;
	}

}